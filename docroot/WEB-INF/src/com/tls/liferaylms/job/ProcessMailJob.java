package com.tls.liferaylms.job;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
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
import com.tls.liferaylms.mail.message.LmsMailMessageListener;
import com.tls.liferaylms.mail.model.AuditSendMails;
import com.tls.liferaylms.mail.model.MailJob;
import com.tls.liferaylms.mail.model.MailTemplate;
import com.tls.liferaylms.mail.service.AuditSendMailsLocalServiceUtil;
import com.tls.liferaylms.mail.service.MailJobLocalServiceUtil;
import com.tls.liferaylms.mail.service.MailTemplateLocalServiceUtil;
import com.tls.liferaylms.util.MailStringPool;

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
			} catch (ClassNotFoundException e) {
				if(log.isDebugEnabled())e.printStackTrace();
			}
			
			Condition date = null;
			try {
				date = ConditionUtil.instance(mailJob.getDateClassName(), mailJob);
				if(log.isDebugEnabled())log.debug(date);
			} catch (ClassNotFoundException e) {
				if(log.isDebugEnabled())e.printStackTrace();
			}
			
			
			if(log.isDebugEnabled()){
				log.debug(mailJob.getIdJob());
				Set<User> users = condition.getUsersToSend();
				if(users!=null){
					for(User user : users){
						log.debug(user.getFullName());
					}
				}
				
				log.debug(date.shouldBeProcessed());
			}
			
			if(date.shouldBeProcessed()){
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
				
				if(users!=null&&mailTemplate!=null&&company!=null&&group!=null){

					//Guardar una auditoria del envio de emails.
					AuditSendMails auditSendMails = null;
					try{
						auditSendMails = AuditSendMailsLocalServiceUtil.createAuditSendMails(CounterLocalServiceUtil.increment(AuditSendMails.class.getName()));
						auditSendMails.setUserId(mailJob.getUserId());
						auditSendMails.setGroupId(mailJob.getGroupId());
						auditSendMails.setTemplateId(mailJob.getIdTemplate());
						auditSendMails.setCompanyId(mailJob.getCompanyId());
						AuditSendMailsLocalServiceUtil.addAuditSendMails(auditSendMails); 
					}catch(Exception e){
						e.printStackTrace();
					}

					for(User user : users){
						log.debug(user.getFullName());
						try{

							Message message=new Message();

							message.put("templateId",mailTemplate.getIdTemplate());

							message.put("to", user.getEmailAddress());

							message.put("subject", 	mailTemplate.getSubject());
							message.put("body", 	mailTemplate.getBody());
							message.put("groupId", 	mailJob.getGroupId());
							message.put("userId",  	mailJob.getUserId());
							message.put("testing", 	StringPool.FALSE);

							message.put("portal", 	companyName);
							message.put("community",group.getName());

							String portalUrl = PortalUtil.getPortalURL(company.getVirtualHostname(), PortalUtil.getPortalPort(false), false);
							message.put("url", 		portalUrl);
							message.put("urlcourse",portalUrl+PortalUtil.getPathFriendlyURLPublic()+group.getFriendlyURL());							

							MessageBusUtil.sendMessage("lms/mailing", message);

							LmsMailMessageListener.addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), user.getEmailAddress(), LmsMailMessageListener.STATUS_OK);
						}catch(Exception e){
							e.printStackTrace();
							LmsMailMessageListener.addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), user.getEmailAddress(), LmsMailMessageListener.STATUS_KO);
						}
					}

					//Guardar una auditoria del envio de emails.
					try {
						auditSendMails.setSendDate(new Date(System.currentTimeMillis()));
						auditSendMails.setNumberOfPost(users.size());
						AuditSendMailsLocalServiceUtil.updateAuditSendMails(auditSendMails);
					} catch (SystemException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
				
				try {
					mailJob.setProcessed(true);
					MailJobLocalServiceUtil.updateMailJob(mailJob);
				} catch (SystemException e) {
					if(log.isDebugEnabled())e.printStackTrace();
				}
			}
		}

		if(log.isDebugEnabled())log.debug(MailStringPool.END+this.getClass().getName());
	}

}
