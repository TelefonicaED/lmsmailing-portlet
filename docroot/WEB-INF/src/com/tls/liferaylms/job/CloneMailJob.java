package com.tls.liferaylms.job;

import java.util.List;

import com.liferay.lms.model.AsynchronousProcessAudit;
import com.liferay.lms.model.Course;
import com.liferay.lms.model.LearningActivity;
import com.liferay.lms.service.AsynchronousProcessAuditLocalServiceUtil;
import com.liferay.lms.service.CourseLocalServiceUtil;
import com.liferay.lms.service.LearningActivityLocalServiceUtil;
import com.liferay.lms.util.LmsConstant;
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
	
	public CloneMailJob(long courseId, long parentId) {
		super();
		this.courseId = courseId;
		this.parentId = parentId;
	}
	

	
	@Override
	public void receive(Message message) throws MessageListenerException {
		
		try {
			
			long processId = message.getLong("asynchronousProcessAuditId");
			
			process = AsynchronousProcessAuditLocalServiceUtil.fetchAsynchronousProcessAudit(processId);
			process = AsynchronousProcessAuditLocalServiceUtil.updateProcessStatus(process, null, LmsConstant.STATUS_IN_PROGRESS, "");
			
			this.courseId	= message.getLong("courseId");
			this.parentId = message.getLong("parentId");
			Course parent = CourseLocalServiceUtil.fetchCourse(parentId);
			Course course = CourseLocalServiceUtil.fetchCourse(courseId);
			try {
				List<MailJob> mailjobs = MailJobLocalServiceUtil.getMailJobsInGroupId(parent.getGroupCreatedId(), -1, -1);
				ServiceContext serviceContext = new com.liferay.portal.service.ServiceContext();
				for (MailJob mj : mailjobs){
					try {
						log.info("mail "+mj.getGroupId());
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
						log.info("Creado mail "+mailJob.getUuid());
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
