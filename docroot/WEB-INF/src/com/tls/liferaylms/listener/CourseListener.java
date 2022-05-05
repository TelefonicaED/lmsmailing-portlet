package com.tls.liferaylms.listener;

import com.liferay.lms.model.AsynchronousProcessAudit;
import com.liferay.lms.model.Course;
import com.liferay.lms.service.AsynchronousProcessAuditLocalServiceUtil;
import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.model.BaseModelListener;
import com.tls.liferaylms.mail.model.MailJob;


public class CourseListener extends BaseModelListener<Course> {
	Log log = LogFactoryUtil.getLog(CourseListener.class);

	@Override
	public void onAfterCreate(Course course) throws ModelListenerException {
		log.info("courseid "+course.getCourseId());
		if (course!=null){
			AsynchronousProcessAudit process = AsynchronousProcessAuditLocalServiceUtil.addAsynchronousProcessAudit(course.getCompanyId(), course.getUserId(), MailJob.class.getName(), "liferay/lms/cloneMailJob");
			Message message=new Message();
			message.put("asynchronousProcessAuditId", process.getAsynchronousProcessAuditId());
			message.put("courseId",course.getCourseId());
			log.info("send message");
			MessageBusUtil.sendMessage("liferay/lms/cloneMailJob", message);		
			log.info("mandado clone");
		}
	}

}
