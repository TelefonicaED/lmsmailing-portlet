package com.tls.liferaylms.listener;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import com.liferay.lms.model.Course;
import com.liferay.lms.service.CourseLocalServiceUtil;
import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.tls.liferaylms.mail.model.MailJob;
import com.tls.liferaylms.mail.model.MailTemplate;
import com.tls.liferaylms.mail.service.MailJobLocalServiceUtil;
import com.tls.liferaylms.mail.service.MailTemplateLocalServiceUtil;
import com.tls.liferaylms.util.MailConstants;
import com.tls.liferaylms.util.MailUtil;


public class GroupListener extends BaseModelListener<Group> {
	Log log = LogFactoryUtil.getLog(GroupListener.class);

	@Override
	public void onAfterCreate(Group group) throws ModelListenerException {
	/*	Course course=null;
		Course parent=null;
		try {
			course = CourseLocalServiceUtil.fetchByGroupCreatedId(group.getGroupId());
			if (course!=null){
				parent = CourseLocalServiceUtil.fetchCourse(course.getParentCourseId());
				if (parent!=null){
					List<MailJob> mailjobs = MailJobLocalServiceUtil.getMailJobsInGroupId(parent.getGroupCreatedId(), -1, -1);
					ServiceContext serviceContext = new com.liferay.portal.service.ServiceContext();
					for (MailJob mj : mailjobs){
						try {
							MailJob mailJob = MailJobLocalServiceUtil.addMailJob(mj.getIdTemplate(), mj.getConditionClassName(), mj.getConditionClassPK(), mj.getConditionStatus(), mj.getDateClassName(), mj.getDateClassPK(), mj.getDateShift(), mj.getDateToSend(), mj.getDateReferenceDate(), serviceContext);
							
							JSONObject extraOrig = mj.getExtraDataJSON();
							JSONObject extraData = JSONFactoryUtil.createJSONObject();
							extraData.put(MailConstants.EXTRA_DATA_SEND_COPY, extraOrig.getBoolean(MailConstants.EXTRA_DATA_SEND_COPY));
							extraData.put(MailConstants.EXTRA_DATA_RELATION_ARRAY, extraOrig.getJSONArray(MailConstants.EXTRA_DATA_RELATION_ARRAY));
							mailJob.setExtraData(extraData.toString());
							MailJobLocalServiceUtil.updateMailJob(mailJob);
						
						} catch (PortalException e) {
							if(log.isinfoEnabled())e.printStackTrace();
							if(log.isErrorEnabled())log.error(e.getMessage());
						} catch (SystemException e) {
							if(log.isinfoEnabled())e.printStackTrace();
							if(log.isErrorEnabled())log.error(e.getMessage());
						}
					}
				}
			}
		} catch (SystemException e1) {
			e1.printStackTrace();
		}*/
	
	}

	
	@Override
	public void onAfterAddAssociation(Object classPK,
			String associationClassName, Object associationClassPK)
			throws ModelListenerException {
		log.info("onAfterAddAssociation");
		
		long groupId = GetterUtil.getLong(classPK);
		long userId = GetterUtil.getLong(associationClassPK);
		log.info("Group "+groupId);
		List<MailJob> mailjobs = MailJobLocalServiceUtil.getMailJobsInGroupId(groupId, -1, -1);
		for (MailJob mj : mailjobs){
			log.info("Hay mj "+mj.isProcessed()+" dd "+mj.getDateClassName()+" date "+mj.getDateReferenceDate() );
			if (mj.getDateClassName().equals("CourseCondition") &&  mj.getDateReferenceDate()==2){
				try{
					log.info("HAY QUE MANDAR MAIL DE INSCRIPCION");
					Message message=new Message();

					User user = UserLocalServiceUtil.fetchUser(userId);
					MailTemplate mailTemplate = null;
					try {
						mailTemplate = MailTemplateLocalServiceUtil.getMailTemplate(mj.getIdTemplate());
					} catch (PortalException e) {
						e.printStackTrace();
					} catch (SystemException e) {
						e.printStackTrace();
					}
					String tutors = "";
					Group group = null;
					try {
						group = GroupLocalServiceUtil.getGroup(groupId);
					} catch (PortalException e) {
						e.printStackTrace();
					} catch (SystemException e) {
						e.printStackTrace();
					}
					if(group!=null){
						tutors = MailUtil.getTutors(group.getGroupId());
				    }
					Company company = null;
					String companyName = StringPool.BLANK;
					try {
						company = CompanyLocalServiceUtil.getCompanyById(mj.getCompanyId());
						companyName = company.getName();
					} catch (PortalException e) {
						e.printStackTrace();
					} catch (SystemException e) {
						e.printStackTrace();
					}

					Course course=null;
					try {
						course = CourseLocalServiceUtil.fetchByGroupCreatedId(group.getGroupId());
					} catch (SystemException e1) {
						e1.printStackTrace();
					}
					message.put("templateId",mailTemplate.getIdTemplate());

					message.put("to", user.getEmailAddress());
					message.put("tutors", tutors);
					message.put("subject", 	mailTemplate.getSubject());
					message.put("body", 	mailTemplate.getBody());
					message.put("groupId", 	mj.getGroupId());
					message.put("userId",  	mj.getUserId());
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
					log.info("url: " + portalUrl);
					
					File atachDir = new File (PropsUtil.get("liferay.home")+"/data/mailtemplate/"+mailTemplate.getIdTemplate());
					if (atachDir.exists()){
						log.info("HAY ATTACH");
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
				
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}			
	}
}