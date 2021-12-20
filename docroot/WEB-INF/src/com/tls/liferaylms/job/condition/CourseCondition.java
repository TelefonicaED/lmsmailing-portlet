package com.tls.liferaylms.job.condition;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.liferay.lms.model.Course;
import com.liferay.lms.model.CourseResult;
import com.liferay.lms.service.CourseLocalServiceUtil;
import com.liferay.lms.service.CourseResultLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.tls.liferaylms.util.MailStringPool;

public class CourseCondition extends MainCondition{
	Log log = LogFactoryUtil.getLog(CourseCondition.class);
	public CourseCondition(String className) {
		super(className);
	}

	@Override
	public Set<User> getUsersToSend() {
		List<User> groupUsers = null;
		log.debug(getMailJob().getGroupId());
		Course course = null;
		try {
			course = CourseLocalServiceUtil.fetchByGroupCreatedId(getMailJob().getGroupId());
			boolean sendToTutors = PrefsPropsUtil.getBoolean(getMailJob().getCompanyId(), MailStringPool.SEND_TO_TUTORS_KEY, true);
			log.debug("Send to tutos "+sendToTutors);
			if(!sendToTutors && course!=null){
				groupUsers =  CourseLocalServiceUtil.getStudentsFromCourse(course);
			}else{
				groupUsers = UserLocalServiceUtil.getGroupUsers(getMailJob().getGroupId());
			}
		} catch (Exception e) {
			if(log.isDebugEnabled())e.printStackTrace();
			if(log.isErrorEnabled())log.error(e.getMessage());
		}
		Set<User> users = new HashSet<User>();
		log.info("group users "+groupUsers.size());

		for(User user : groupUsers){
			try{
				if(course!=null){
					CourseResult cr = CourseResultLocalServiceUtil.getCourseResultByCourseAndUser(course.getCourseId(), user.getUserId());
					log.info("cr "+cr);
					if(cr!=null && cr.getRegistrationDate()!=null){
						log.info("Dentro y estoy registrado");
			    		String[] ids = new String[0];
						if(getMailJob().getConditionStatus().length()!=0) ids=  getMailJob().getConditionStatus().split(StringPool.COMMA);
						if(log.isDebugEnabled())log.debug("----------------------------- "+ids.length);
								
						for(String sid : ids){
								if(log.isDebugEnabled())log.debug("----------------------------- "+sid);
								int id = -1;
								try{
										id = (int)Integer.valueOf(sid);
								
								}catch(NumberFormatException nfe){
									if(log.isDebugEnabled())nfe.printStackTrace();
									if(log.isErrorEnabled())log.error(nfe.getMessage());
								}
									
							   if(log.isDebugEnabled())log.debug("--------------------id--------- "+id);
								
								switch (id) {
									//not started
									case 0:
										if(cr.getStartDate()==null){
											users.add(user);
											log.debug("usuario añadido 0 "+user.getUserId());
										}
									break;
									//started
									case 1:
										if(cr.getStartDate()!=null && cr.getPassedDate()==null){
											users.add(user);
											log.debug("usuario añadido 1 "+user.getUserId());
										}
									break;
									//not passed
									case 2:
										if(!cr.getPassed()&&cr.getPassedDate()!=null){
											users.add(user);
											log.debug("usuario añadido 2 "+user.getUserId());
										}
									break;
									//passed
									case 3:
										if(cr.getPassed()){
											users.add(user);
											log.debug("usuario añadido 3 "+user.getUserId());
										}
									break;
								}
							}
						}
					 }
				}catch(Exception e){
					log.error("No se pudo sacar el Curso result "+e.getMessage());
				}
			} 
		return users;
	}

	@Override
	public boolean shouldBeProcessed() {
		Course course = null;
		boolean process = false;
		try {
			course = CourseLocalServiceUtil.fetchByGroupCreatedId(getMailJob().getGroupId());
		} catch (Exception e) {
			if(log.isDebugEnabled())e.printStackTrace();
			log.error(e.getMessage());
		} 
		
		if(course!=null){
			GregorianCalendar dateSend = new GregorianCalendar();
			
			switch ((int)getMailJob().getDateReferenceDate()) {
				//start date
				case 0:
					dateSend.setTime(course.getExecutionStartDate()==null? course.getStartDate() : course.getExecutionStartDate());
					break;
				//end date
				case 1:
					dateSend.setTime(course.getExecutionEndDate()==null? course.getEndDate() : course.getExecutionEndDate());
					break;
				//inscription date
				case 2:
					process=true;
					break;
			}
			
			dateSend.set(Calendar.HOUR_OF_DAY, 0);
			dateSend.set(Calendar.MINUTE, 0);
			dateSend.set(Calendar.SECOND, 0);
			dateSend.set(Calendar.MILLISECOND, 0);
			dateSend.add(Calendar.DAY_OF_MONTH, (int)getMailJob().getDateShift());
			
			GregorianCalendar today = new GregorianCalendar();
			today.set(Calendar.HOUR_OF_DAY, 0);
			today.set(Calendar.MINUTE, 0);
			today.set(Calendar.SECOND, 0);
			today.set(Calendar.MILLISECOND, 0);
			
			if(log.isDebugEnabled()){
				log.debug(course.getExecutionStartDate()==null? course.getStartDate() : course.getExecutionStartDate());
				log.debug(dateSend.getTime());
				log.debug(today.getTime());
			}
			if(process){
				return true;
			}
			
			if(dateSend.getTime().getTime()<=today.getTime().getTime()){
				return true;
			}else{
				return false;
			}
			
		}else{
			return false;
		}
	}

	@Override
	public String getConditionName() {
		return LanguageUtil.format(Locale.getDefault(), "com.liferay.lms.model.Course", "");
	}

	@Override
	public String getConditionName(Locale locale) {
		return LanguageUtil.format(locale, "com.liferay.lms.model.Course", "");
	}

	@Override
	public String getReferenceName() {
		return LanguageUtil.format(Locale.getDefault(), "com.liferay.lms.model.Course", "");
	}

	@Override
	public String getReferenceName(Locale locale) {
		return LanguageUtil.format(locale, "com.liferay.lms.model.Course", "");
	}

	@Override
	public Long getActReferencePK() {
		return null;
	}

	@Override
	public Long getActConditionPK() {
		return null;
	}

	@Override
	public Long getModReferencePK() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getModConditionPK() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDate() {
		Course course = null;
		
		try {
			course = getCourse(getMailJob().getGroupId());
		} catch (Exception e) {
			if(log.isDebugEnabled())e.printStackTrace();
			if(log.isErrorEnabled())log.error(e.getMessage());
		} 

		if(course!=null){
			GregorianCalendar dateSend = new GregorianCalendar();
			
			switch ((int)getMailJob().getDateReferenceDate()) {
				//start date
				case 0:
					dateSend.setTime(course.getExecutionStartDate()==null? course.getStartDate() : course.getExecutionStartDate());
					break;
				//end date
				case 1:
					dateSend.setTime(course.getExecutionEndDate()==null? course.getEndDate() : course.getExecutionEndDate());
					break;
				//inscription date
				case 2:
					dateSend.setTime(course.getStartDate());
					break;
			}
			
			dateSend.set(Calendar.HOUR_OF_DAY, 0);
			dateSend.set(Calendar.MINUTE, 0);
			dateSend.set(Calendar.SECOND, 0);
			dateSend.set(Calendar.MILLISECOND, 0);
			dateSend.add(Calendar.DAY_OF_MONTH, (int)getMailJob().getDateShift());
			
			return dateSend.getTime();
		}else{
			return null;
		}
	}

	@Override
	public String getFormatDate() {
		if(getDate()!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(MailStringPool.DATE_FORMAT);
			return sdf.format(getDate());
		}else{
			return null;
		}
	}

	private Course  getCourse(long id){
		try {
			return CourseLocalServiceUtil.fetchByGroupCreatedId(id);
		} catch (Exception e) {
			return null;
		}
	}
}
