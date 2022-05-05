package com.tls.liferaylms.listener;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.lms.model.AsynchronousProcessAudit;
import com.liferay.lms.model.Course;
import com.liferay.lms.model.LearningActivity;
import com.liferay.lms.service.AsynchronousProcessAuditLocalServiceUtil;
import com.liferay.lms.service.ClpSerializer;
import com.liferay.lms.service.CourseLocalServiceUtil;
import com.liferay.lms.service.LearningActivityLocalServiceUtil;
import com.liferay.lms.util.LmsConstant;
import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
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
   			}else{
   				// dynamicquery para sacar el curso de donde se clona
   				ClassLoader cl2= (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(), "portletClassLoader");
   				DynamicQuery dq = DynamicQueryFactoryUtil.forClass(AsynchronousProcessAudit.class, cl2);
   		    	dq.add(PropertyFactoryUtil.forName("companyId").eq(course.getCompanyId()));
   		    	dq.add(PropertyFactoryUtil.forName("classPK").eq(course.getCourseId()));
   		    	dq.add(PropertyFactoryUtil.forName("type_").eq("liferay/lms/courseClone"));
   		    	dq.add(PropertyFactoryUtil.forName("status").eq( LmsConstant.STATUS_FINISH));
   		            
   		    	List<AsynchronousProcessAudit> asynchronousProcessAuditList = (List<AsynchronousProcessAudit>) AsynchronousProcessAuditLocalServiceUtil.dynamicQuery(dq);
   			/*	List<AsynchronousProcessAudit> asynchronousProcessAuditList = AsynchronousProcessAuditLocalServiceUtil.getByCompanyIdClassNameIdClassPKStatus(course.getCompanyId(), Long.parseLong(Course.class.getName()), courseId, LmsConstant.STATUS_FINISH,-1, -1);		*/		
   		        
   				if (Validator.isNotNull(asynchronousProcessAuditList) && asynchronousProcessAuditList.size()>0){
   					String extra = asynchronousProcessAuditList.get(0).getExtraContent();
   					if (extra.indexOf("courseId")>0){
   						String sCourseId=extra.substring(extra.indexOf("courseId")+9,extra.length()).trim();
   						log.debug("courseId parent "+sCourseId);
   						try	{
   							parent = CourseLocalServiceUtil.fetchCourse(Long.parseLong(sCourseId));
   						}catch(Exception e){
   					
   						}
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
   										classpk= la.getActId();
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
   							log.debug("mailTemplateParent "+mailTemplateParent);
   							MailTemplate mailTemplate = MailTemplateLocalServiceUtil.createMailTemplate(CounterLocalServiceUtil.increment(MailTemplate.class.getName()));
   							mailTemplate.setSubject(mailTemplateParent.getSubject());
   							mailTemplate.setBody(changeToURL(mailTemplateParent.getBody(), serviceContext.getPortalURL()));
   							mailTemplate.setGroupId(course.getGroupCreatedId());
   							mailTemplate.setCompanyId(course.getCompanyId());
   							mailTemplate.setUserId(course.getUserId());
   					
   							MailTemplateLocalServiceUtil.addMailTemplate(mailTemplate);
   							log.debug("Creado el template");
   							File atachDir = new File (PropsUtil.get("liferay.home")+"/data/mailtemplate/"+mj.getIdTemplate());
   							if (atachDir.exists()){
   								File origin = new File(PropsUtil.get("liferay.home")+"/data/mailtemplate/"+mj.getIdTemplate());
   								File dest = new File (PropsUtil.get("liferay.home")+"/data/mailtemplate/"+mailTemplate.getIdTemplate());
   								FileUtils.copyDirectoryToDirectory(origin, dest);
   								log.debug("se copia");
   							}
   							
   							MailJob mailJob = MailJobLocalServiceUtil.addMailJob(mailTemplate.getIdTemplate(), mj.getConditionClassName(), classpk, mj.getConditionStatus(), mj.getDateClassName(), referenceclasspk, mj.getDateShift(), dateToSend, mj.getDateReferenceDate(), serviceContext);
   							log.debug("Creado mail "+mailJob.getUuid());
   							JSONObject extraOrig = mj.getExtraDataJSON();
   							JSONObject extraData = JSONFactoryUtil.createJSONObject();
   							extraData.put(MailConstants.EXTRA_DATA_SEND_COPY, extraOrig.getBoolean(MailConstants.EXTRA_DATA_SEND_COPY));
   							extraData.put(MailConstants.EXTRA_DATA_RELATION_ARRAY, extraOrig.getJSONArray(MailConstants.EXTRA_DATA_RELATION_ARRAY));
   							mailJob.setExtraData(extraData.toString());
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
	//Para im�genes
	private String changeToURL(String text, String url){
	
		text =  text.contains("img") ? 
				text.replace("src=\"/", "src=\"" + url + StringPool.SLASH) : 
				text;
				
		return text;
	}
}
