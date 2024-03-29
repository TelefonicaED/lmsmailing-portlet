package com.tls.liferaylms.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.io.File;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.lms.model.Course;
import com.liferay.lms.service.CourseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.tls.liferaylms.job.condition.Condition;
import com.tls.liferaylms.job.condition.ConditionUtil;
import com.tls.liferaylms.mail.model.AuditSendMails;
import com.tls.liferaylms.mail.model.MailJob;
import com.tls.liferaylms.mail.model.MailTemplate;
import com.tls.liferaylms.mail.service.AuditSendMailsLocalServiceUtil;
import com.tls.liferaylms.mail.service.MailJobLocalServiceUtil;
import com.tls.liferaylms.mail.service.MailTemplateLocalServiceUtil;
import com.tls.liferaylms.util.MailConstants;
import com.tls.liferaylms.util.MailStringPool;
import com.tls.liferaylms.util.MailUtil;

public class ProcessMailJob extends MVCPortlet implements MessageListener{
	private static Log log = LogFactoryUtil.getLog(ProcessMailJob.class);

	public void executeMailJobs(ActionRequest actionRequest, ActionResponse actionResponse){
		log.debug(":::EJECUTANDO MAIL JOBS MANUALMENTE:::");
		try {
			executeMailJobs();
		} catch (MessageListenerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void receive(Message arg0) throws MessageListenerException {
		log.debug(":::EJECUTANDO MAIL JOBS CRON:::");
		executeMailJobs();
	}
	

	public void executeMailJobs() throws MessageListenerException {		
		if(log.isDebugEnabled())log.debug(MailStringPool.INIT+this.getClass().getName());
		List<MailJob> mailJobs = MailJobLocalServiceUtil.getNotProcessedMailJobs();
		
		for(MailJob mailJob : mailJobs){
			if(log.isDebugEnabled())log.debug(mailJob.getConditionClassName());
			Condition condition = null;
			try {
				condition = ConditionUtil.instance(mailJob.getConditionClassName(), mailJob);
				if(log.isDebugEnabled())log.debug(condition);
				if (!condition.getClassName().equals("Inscription")){
					Condition date = null;
					boolean isToSend = false;
					if (mailJob.getDateToSend() == null){		
						try {
							date = ConditionUtil.instance(mailJob.getDateClassName(), mailJob);
							if(log.isDebugEnabled())log.debug(date);
						} catch (ClassNotFoundException e) {
							if(log.isDebugEnabled())e.printStackTrace();
						}
					}else{
						Date now = new Date();
						String pattern = "yyyy-MM-dd";
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
						String today = simpleDateFormat.format(now);
						String dayToSend = simpleDateFormat.format(mailJob.getDateToSend());
						isToSend = today.equals(dayToSend)? true : false;
					}
					if(condition!=null){
						try {
							if(log.isDebugEnabled()){
								log.debug(mailJob.getIdJob());
								Set<User> users = condition.getUsersToSend();
								if(users!=null){
									for(User user : users){
										log.debug(user.getFullName());
									}
								}
							}
							
							if((date != null && date.shouldBeProcessed()) || (isToSend)){
								Set<User> users = condition.getUsersToSend();
	
								MailTemplate mailTemplate = null;
								try {
									mailTemplate = MailTemplateLocalServiceUtil.getMailTemplate(mailJob.getIdTemplate());
								} catch (PortalException e) {
									if(log.isDebugEnabled())e.printStackTrace();
								} catch (SystemException e) {
									if(log.isDebugEnabled())e.printStackTrace();
								}
								
								Company company = null;
								String companyName = StringPool.BLANK;
								try {
									company = CompanyLocalServiceUtil.getCompanyById(mailJob.getCompanyId());
									companyName = company.getName();
								} catch (PortalException e) {
									if(log.isDebugEnabled())e.printStackTrace();
								} catch (SystemException e) {
									if(log.isDebugEnabled())e.printStackTrace();
								}
								
								Group group = null;
								try {
									group = GroupLocalServiceUtil.getGroup(mailJob.getGroupId());
								} catch (PortalException e) {
									if(log.isDebugEnabled())e.printStackTrace();
								} catch (SystemException e) {
									if(log.isDebugEnabled())e.printStackTrace();
								}
								
								String tutors = "";
								if(group!=null)
									tutors = MailUtil.getTutors(group.getGroupId());
								
								
								
								
								if(users!=null&&mailTemplate!=null&&company!=null&&group!=null){
	
									//Guardar una auditoria del envio de emails.
									AuditSendMails auditSendMails = null;
									try{
										auditSendMails = AuditSendMailsLocalServiceUtil.createAuditSendMails(CounterLocalServiceUtil.increment(AuditSendMails.class.getName()));
										auditSendMails.setUserId(mailJob.getUserId());
										auditSendMails.setGroupId(mailJob.getGroupId());
										auditSendMails.setTemplateId(mailJob.getIdTemplate());
										auditSendMails.setCompanyId(mailJob.getCompanyId());
										
										auditSendMails.setSubject(mailTemplate.getSubject());
										auditSendMails.setBody(mailTemplate.getBody());
										
										
										AuditSendMailsLocalServiceUtil.addAuditSendMails(auditSendMails); 
									}catch(Exception e){
										e.printStackTrace();
									}
	
									List<User> sentToUsersList = new ArrayList<User>();
									JSONObject extraData = mailJob.getExtraDataJSON();
									boolean sendCopyToSocialRelation = false;
									List<Integer> sendCopyToTypeIds = new ArrayList<Integer>();
									try{
										sendCopyToSocialRelation = extraData.getBoolean(MailConstants.EXTRA_DATA_SEND_COPY);
										JSONArray mailRelationIds = extraData.getJSONArray(MailConstants.EXTRA_DATA_RELATION_ARRAY);
										if(mailRelationIds!=null){
											for(int i =0; i<mailRelationIds.length();i++){
												sendCopyToTypeIds.add(mailRelationIds.getInt(i));
											}	
										}
										
									}catch(Exception e){
										e.printStackTrace();
									}
									
									Course course=null;
									try {
										course = CourseLocalServiceUtil.fetchByGroupCreatedId(group.getGroupId());
									} catch (SystemException e1) {
										e1.printStackTrace();
									}
									List<User> socialRelationUsers = new ArrayList<User>();
									if(course!=null){
										for(User user : users){
											log.debug(user.getFullName());
											try{
	
												Message message=new Message();
	
												message.put("templateId",mailTemplate.getIdTemplate());
	
												message.put("to", user.getEmailAddress());
												message.put("tutors", tutors);
												message.put("subject", 	mailTemplate.getSubject());
												message.put("body", 	mailTemplate.getBody());
												message.put("groupId", 	mailJob.getGroupId());
												message.put("userId",  	mailJob.getUserId());
												message.put("testing", 	StringPool.FALSE);
	
												message.put("portal", 	companyName);
												
												if(course!=null){
													message.put("community",course.getTitle(user.getLocale()));
												}else{
													message.put("community",group.getName());
												}
												
	
												String portalUrl = PortalUtil.getPortalURL(company.getVirtualHostname(), 80, false);
										    	//QUITANDO PUERTOS
												String[] urls = portalUrl.split(":");
												portalUrl = urls[0] + ":" +urls[1];  // http:prueba.es:8080		
												log.debug("url: " + portalUrl);
												
												File atachDir = new File (PropsUtil.get("liferay.home")+"/data/mailtemplate/"+mailTemplate.getIdTemplate());
												if (atachDir.exists()){
													List<File> files = (List<File>) FileUtils.listFiles(atachDir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
													File[] copyAttachments = new File[files.size()];
													String[] attachmentNames = new String[files.size()];
													for(int i=0;i<files.size();i++){
														
														File tempFile = FileUtil.createTempFile();
														FileUtil.copyFile(files.get(i), tempFile);
														copyAttachments[i] = tempFile;
														attachmentNames[i]= files.get(i).getName();
													}
														
													message.put("attachments", copyAttachments);
													message.put("attachmentNames", attachmentNames);
												}
												message.put("url", 		portalUrl);
												message.put("urlcourse",portalUrl+PortalUtil.getPathFriendlyURLPublic()+group.getFriendlyURL());
	
												MessageBusUtil.sendMessage("lms/mailing", message);
	
												
												
												sentToUsersList.add(user);
												//Si corresponde se buscan los usuarios con relaciones sociales con los usuarios seleccionados
												socialRelationUsers = sendCopyToSocialRelation ? MailUtil.getSocialRelationUsers(user, sendCopyToTypeIds, socialRelationUsers, mailJob.getCompanyId()) : new ArrayList<User>();
												
	//										LmsMailMessageListener.addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), user.getEmailAddress(), LmsMailMessageListener.STATUS_OK);
											}catch(Exception e){
												e.printStackTrace();
	//										LmsMailMessageListener.addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), user.getEmailAddress(), LmsMailMessageListener.STATUS_KO);
											}
										}
	
									
									
										//Envio de emails a usuarios relacionados si corresponde
										log.debug(":::socialRelationUsers.size():: " + socialRelationUsers.size());
										if(socialRelationUsers!=null && socialRelationUsers.size()>0){
											for(User socialRelatedUser:socialRelationUsers){
												
												Message message=new Message();
												
												message.put("templateId",mailTemplate.getIdTemplate());
												message.put("to", socialRelatedUser.getEmailAddress());
												message.put("tutors", tutors);
												message.put("userName", socialRelatedUser.getFullName());
												message.put("subject", 	mailTemplate.getSubject());
												message.put("body", 	mailTemplate.getBody());
												message.put("groupId", 	mailJob.getGroupId());
												message.put("userId",  	mailJob.getUserId());
												message.put("testing", 	StringPool.FALSE);
											
												if(course!=null){
													message.put("community",course.getTitle(socialRelatedUser.getLocale()));
												}else{
													message.put("community",group.getName());
												}
												String portalUrl = PortalUtil.getPortalURL(company.getVirtualHostname(), 80, false);
										    	//QUITANDO PUERTOS
												String[] urls = portalUrl.split(":");
												portalUrl = urls[0] + ":" +urls[1];  
												
												message.put("url", 		portalUrl);
												message.put("urlcourse",portalUrl+PortalUtil.getPathFriendlyURLPublic()+group.getFriendlyURL());
												
												
											
												message.put("sendToRelatedUsers", sendCopyToSocialRelation);
												message.put("isUserRelated", true);
												message.put("mailRelationTypeIds", sendCopyToTypeIds);
												message.put("emailSentToUsersList", sentToUsersList);
												
												File atachDir = new File (PropsUtil.get("liferay.home")+"/data/mailtemplate/"+mailTemplate.getIdTemplate());
												if (atachDir.exists()){
													List<File> files = (List<File>) FileUtils.listFiles(atachDir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
													File[] copyAttachments = new File[files.size()];
													String[] attachmentNames = new String[files.size()];
													for(int i=0;i<files.size();i++){
														File tempFile = FileUtil.createTempFile();
														FileUtil.copyFile(files.get(i), tempFile);
														copyAttachments[i] = tempFile;
														attachmentNames[i]= files.get(i).getName();
													}
														
													message.put("attachments", copyAttachments);
													message.put("attachmentNames", attachmentNames);
												}
												
												
												MessageBusUtil.sendMessage("lms/mailing", message);
											}
										}
									}
									
									//Guardar una auditoria del envio de emails.
	//							try {
	//								auditSendMails.setSendDate(new Date(System.currentTimeMillis()));
	//								auditSendMails.setNumberOfPost(users.size());
	//								AuditSendMailsLocalServiceUtil.updateAuditSendMails(auditSendMails);
	//							} catch (SystemException e) {
	//								// TODO Auto-generated catch block
	//								e.printStackTrace();
	//							} 
								}
								
								try {
									int referenceDate = (int)mailJob.getDateReferenceDate();
									
									if(referenceDate != 2){
										mailJob.setProcessed(true);	
									}
									
									MailJobLocalServiceUtil.updateMailJob(mailJob);
								} catch (SystemException e) {
									if(log.isDebugEnabled())e.printStackTrace();
								}
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}	
					
				}
			
			} catch (ClassNotFoundException e) {
				if(log.isDebugEnabled())e.printStackTrace();
			}
			
		
		}

		if(log.isDebugEnabled())log.debug(MailStringPool.END+this.getClass().getName());
	}

}
