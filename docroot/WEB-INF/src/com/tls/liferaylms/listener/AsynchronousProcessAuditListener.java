package com.tls.liferaylms.listener;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.lms.model.AsynchronousProcessAudit;
import com.liferay.lms.model.Course;
import com.liferay.lms.model.LearningActivity;
import com.liferay.lms.service.AsynchronousProcessAuditLocalServiceUtil;
import com.liferay.lms.service.CourseLocalServiceUtil;
import com.liferay.lms.service.LearningActivityLocalServiceUtil;
import com.liferay.lms.util.LmsConstant;
import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.service.ServiceContext;
import com.tls.liferaylms.mail.model.MailJob;
import com.tls.liferaylms.mail.model.MailTemplate;
import com.tls.liferaylms.mail.service.MailJobLocalServiceUtil;
import com.tls.liferaylms.mail.service.MailTemplateLocalServiceUtil;
import com.tls.liferaylms.util.MailConstants;

public class AsynchronousProcessAuditListener extends BaseModelListener<AsynchronousProcessAudit> {
	Log log = LogFactoryUtil.getLog(AsynchronousProcessAuditListener.class);

	@Override
	public void onAfterUpdate(AsynchronousProcessAudit as) throws ModelListenerException {
		log.debug("courseid "+as.getAsynchronousProcessAuditId());
		try  
        {  
           if (as.getStatus()==LmsConstant.STATUS_FINISH  && (as.getType().equals("liferay/lms/courseClone") || as.getType().equals("liferay/lms/createEdition"))){
        	   Course course = CourseLocalServiceUtil.fetchCourse(as.getClassPK());
   			log.debug("course "+course.getCourseId());
   			
   			Course parent=null;
   			if (as.getType().equals("liferay/lms/createEdition") && course.getParentCourseId()>0){
   				parent = CourseLocalServiceUtil.fetchCourse(course.getParentCourseId());
   				log.debug("parent "+course.getParentCourseId());
   			}else if (as.getType().equals("liferay/lms/courseClone")){
   				String extra = as.getExtraContent();
				if (extra.indexOf("courseId")>0){
					String sCourseId=extra.substring(extra.indexOf("courseId")+9,extra.length()).trim();
					sCourseId= sCourseId.substring(0,sCourseId.length()-2);
					log.debug("courseId parent "+sCourseId);
					try	{
						parent = CourseLocalServiceUtil.fetchCourse(Long.parseLong(sCourseId));
					}catch(Exception e){
						log.error("NO SE PUDO SACAR EL CURSO DEL QUE SE HA DUPLICADO "+e.getMessage());
					}
				}
   			}	
   			if (parent!=null){
   				try {
   					log.debug("empiezo a copiar");
   					List<MailJob> mailjobs = MailJobLocalServiceUtil.getMailJobsInGroupId(parent.getGroupCreatedId(), -1, -1);
   					ServiceContext serviceContext = new com.liferay.portal.service.ServiceContext();
   					serviceContext.setCompanyId(course.getCompanyId());
   					serviceContext.setScopeGroupId(course.getGroupCreatedId());
   				    Map<Long, Long> templateid=new HashMap<Long, Long>();
   					for (MailJob mj : mailjobs){
   						try {
   							log.debug("mail "+mj.getGroupId());
   							long classpk = 0;
   							long referenceclasspk=0;
   							if (mj.getConditionClassPK()> 0){
   								LearningActivity act = LearningActivityLocalServiceUtil.fetchLearningActivity(mj.getConditionClassPK());
   								List<LearningActivity> listactivities = LearningActivityLocalServiceUtil.getLearningActivitiesOfGroupAndType(course.getGroupCreatedId(), act.getTypeId());
   								for (LearningActivity la: listactivities){
   									if (act.getTitle().equals(la.getTitle())){
   										classpk= la.getActId();
   										break;
   									}
   								}
   							}
   							if (mj.getDateClassPK()> 0){
   								LearningActivity actref = LearningActivityLocalServiceUtil.fetchLearningActivity(mj.getDateClassPK());
   								List<LearningActivity> listactivities = LearningActivityLocalServiceUtil.getLearningActivitiesOfGroupAndType(course.getGroupCreatedId(), actref.getTypeId());
   								for (LearningActivity la: listactivities){
   									if (actref.getTitle().equals(la.getTitle())){
   										referenceclasspk= la.getActId();
   										break;
   									}
   								}
   							}
   							Date dateToSend =null;
   							try{
   								dateToSend = mj.getDateToSend();
   							}catch(Exception e1){
   								dateToSend =null;
   							}


   							MailTemplate mailTemplateParent = MailTemplateLocalServiceUtil.fetchMailTemplate(mj.getIdTemplate());
   							long idTemplate=0;
   							if (!templateid.containsKey(mj.getIdTemplate())){
	   							log.debug("mailTemplateParent "+mailTemplateParent);
	   							MailTemplate mailTemplate = MailTemplateLocalServiceUtil.createMailTemplate(CounterLocalServiceUtil.increment(MailTemplate.class.getName()));
	   							mailTemplate.setSubject(mailTemplateParent.getSubject());
	   							mailTemplate.setBody(changeToURL(mailTemplateParent.getBody(), serviceContext.getPortalURL()));
	   							mailTemplate.setGroupId(course.getGroupCreatedId());
	   							mailTemplate.setCompanyId(course.getCompanyId());
	   							mailTemplate.setUserId(course.getUserId());
	   					
	   							MailTemplateLocalServiceUtil.addMailTemplate(mailTemplate);
	   							templateid.put(mj.getIdTemplate(), mailTemplate.getIdTemplate());
	   							idTemplate=mailTemplate.getIdTemplate();
	   							log.debug("Creado el template");
	   							File atachDir = new File (PropsUtil.get("liferay.home")+"/data/mailtemplate/"+mj.getIdTemplate());
	   							if (atachDir.exists()){
	   								File origin = new File(PropsUtil.get("liferay.home")+"/data/mailtemplate/"+mj.getIdTemplate());
	   								File dest = new File (PropsUtil.get("liferay.home")+"/data/mailtemplate/"+mailTemplate.getIdTemplate());
	   								FileUtils.copyDirectoryToDirectory(origin, dest);
	   								log.debug("se copia");
	   							}
   							}else{
   								idTemplate = templateid.get(mj.getIdTemplate());
   							}
			
   							MailJob mailJob = MailJobLocalServiceUtil.addMailJob(idTemplate, mj.getConditionClassName(), classpk, mj.getConditionStatus(), mj.getDateClassName(), referenceclasspk, mj.getDateShift(), dateToSend, mj.getDateReferenceDate(), serviceContext);
   							log.debug("Creado mail "+mailJob.getUuid());
   							JSONObject extraOrig = mj.getExtraDataJSON();
   							JSONObject extraData = JSONFactoryUtil.createJSONObject();
   							extraData.put(MailConstants.EXTRA_DATA_SEND_COPY, extraOrig.getBoolean(MailConstants.EXTRA_DATA_SEND_COPY));
   							extraData.put(MailConstants.EXTRA_DATA_RELATION_ARRAY, extraOrig.getJSONArray(MailConstants.EXTRA_DATA_RELATION_ARRAY));
   							mailJob.setExtraData(extraData.toString());
							mailJob.setUserId(course.getUserId());
   							MailJobLocalServiceUtil.updateMailJob(mailJob);
   						
   						} catch (PortalException e1) {
   							e1.printStackTrace();
   							log.error(e1.getMessage());
   						} catch (SystemException e2) {
   							e2.printStackTrace();
   							log.error(e2.getMessage());
   						}
   					}
   				}catch(Exception e){
   					log.error("NO SE PUDO COPIAR LOS EMAILS PROGRAMADOS");
   				}
   				if(log.isDebugEnabled()){
   					log.debug(" ENDS!");
   				}
   			}
   			
           }
            
        }catch(Exception e){
        	
        }    

	}
	//Para imagenes
	private String changeToURL(String text, String url){
	
		text =  text.contains("img") ? 
				text.replace("src=\"/", "src=\"" + url + StringPool.SLASH) : 
				text;
				
		return text;
	}
}
