package com.tls.liferaylms;

import java.util.ArrayList;
import java.util.List;

import com.liferay.lms.model.AsynchronousProcessAudit;
import com.liferay.lms.model.Course;
import com.liferay.lms.model.LearningActivity;
import com.liferay.lms.service.AsynchronousProcessAuditLocalServiceUtil;
import com.liferay.lms.service.CourseLocalServiceUtil;
import com.liferay.lms.service.LearningActivityLocalServiceUtil;
import com.liferay.lms.util.LmsConstant;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.service.ServiceContext;
import com.tls.liferaylms.mail.model.MailJob;
import com.tls.liferaylms.mail.service.MailJobLocalServiceUtil;
import com.tls.liferaylms.util.MailConstants;

public class CloneMailJob implements MessageListener {
	private static Log log = LogFactoryUtil.getLog(CloneMailJob.class);
	
	long courseId;
	long parentId;
	AsynchronousProcessAudit process = null;
	
	public CloneMailJob(long courseId) {
		super();
		this.courseId = courseId;
	}
	
	public CloneMailJob() {

	}
	
	@Override
	public void receive(Message message) throws MessageListenerException {
		log.debug("Clone recibido");
		
		try {			
			long processId = message.getLong("asynchronousProcessAuditId");
			log.debug("processId "+processId);
			process = AsynchronousProcessAuditLocalServiceUtil.fetchAsynchronousProcessAudit(processId);
			process = AsynchronousProcessAuditLocalServiceUtil.updateProcessStatus(process, null, LmsConstant.STATUS_IN_PROGRESS, "");
			
			this.courseId	= message.getLong("courseId");

			doCloneMail();
			process = AsynchronousProcessAuditLocalServiceUtil.updateProcessStatus(process, null, LmsConstant.STATUS_FINISH, "");
		}catch(Exception e){
			
		}
	}
	@SuppressWarnings("unchecked")
	public void doCloneMail() throws Exception {
			Course course = CourseLocalServiceUtil.fetchCourse(courseId);
			log.debug("course "+courseId);
			List<AsynchronousProcessAudit> asynchronousProcessAuditListCourse=new ArrayList<AsynchronousProcessAudit>();
		
			while(asynchronousProcessAuditListCourse.size()<1){
				log.debug("busco");
			//	asynchronousProcessAuditListCourse = AsynchronousProcessAuditLocalServiceUtil.getByCompanyIdClassNameIdClassPKStatus(course.getCompanyId(), Long.parseLong(Course.class.getName()), courseId, LmsConstant.STATUS_FINISH,-1, -1);				
				DynamicQuery dq = DynamicQueryFactoryUtil.forClass(AsynchronousProcessAudit.class);
		    	dq.add(PropertyFactoryUtil.forName("companyId").eq(course.getCompanyId()));
		    	dq.add(PropertyFactoryUtil.forName("classPK").eq(this.courseId));
		    	dq.add(PropertyFactoryUtil.forName("status").eq( LmsConstant.STATUS_FINISH));
		    	dq.add(PropertyFactoryUtil.forName("classNameId").eq(10908L));
		            
		    	asynchronousProcessAuditListCourse = (List<AsynchronousProcessAudit>) AsynchronousProcessAuditLocalServiceUtil.dynamicQuery(dq);
				
				log.debug("sigo esperando "+asynchronousProcessAuditListCourse.size());
			}
			
			Course parent=null;
			log.debug("paso al siguiente ");
			if (course.getParentCourseId()>0){
				parent = CourseLocalServiceUtil.fetchCourse(course.getParentCourseId());
				log.debug("parent "+parent);
			}else{
				// dynamicquery para sacar el curso de donde se clona
				DynamicQuery dq = DynamicQueryFactoryUtil.forClass(AsynchronousProcessAudit.class);
		    	dq.add(PropertyFactoryUtil.forName("companyId").eq(course.getCompanyId()));
		    	dq.add(PropertyFactoryUtil.forName("classPK").eq(this.courseId));
		    	dq.add(PropertyFactoryUtil.forName("type_").eq("liferay/lms/courseClone"));
		    	dq.add(PropertyFactoryUtil.forName("status").eq( LmsConstant.STATUS_FINISH));
		            
		    	List<AsynchronousProcessAudit> asynchronousProcessAuditList = (List<AsynchronousProcessAudit>) AsynchronousProcessAuditLocalServiceUtil.dynamicQuery(dq);
			/*	List<AsynchronousProcessAudit> asynchronousProcessAuditList = AsynchronousProcessAuditLocalServiceUtil.getByCompanyIdClassNameIdClassPKStatus(course.getCompanyId(), Long.parseLong(Course.class.getName()), courseId, LmsConstant.STATUS_FINISH,-1, -1);		*/		
		        
				if (asynchronousProcessAuditList.size()>0){
					String extra = asynchronousProcessAuditList.get(0).getExtraContent();
					if (extra.indexOf("courseId")>0){
						String sCourseId=extra.substring(extra.indexOf("courseId")+9,extra.length()).trim();
						try	{
							parent = CourseLocalServiceUtil.fetchCourse(Long.parseLong(sCourseId));
						}catch(Exception e){
					
						}
					}
				}	
			}	
			if (parent!=null){
				try {
					List<MailJob> mailjobs = MailJobLocalServiceUtil.getMailJobsInGroupId(parent.getGroupCreatedId(), -1, -1);
					ServiceContext serviceContext = new com.liferay.portal.service.ServiceContext();
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
							MailJob mailJob = MailJobLocalServiceUtil.addMailJob(mj.getIdTemplate(), mj.getConditionClassName(), classpk, mj.getConditionStatus(), mj.getDateClassName(), referenceclasspk, mj.getDateShift(), mj.getDateToSend(), mj.getDateReferenceDate(), serviceContext);
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
}
