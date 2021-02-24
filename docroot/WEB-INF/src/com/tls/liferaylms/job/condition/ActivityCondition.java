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
import com.liferay.lms.model.LearningActivity;
import com.liferay.lms.model.LearningActivityResult;
import com.liferay.lms.service.CourseLocalServiceUtil;
import com.liferay.lms.service.CourseResultLocalServiceUtil;
import com.liferay.lms.service.LearningActivityLocalServiceUtil;
import com.liferay.lms.service.LearningActivityResultLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.tls.liferaylms.util.MailStringPool;

public class ActivityCondition extends MainCondition{
	Log log = LogFactoryUtil.getLog(ActivityCondition.class);

	public ActivityCondition(String className) {
		super(className);
	}

	@Override
	public Set<User> getUsersToSend() {
		List<User> groupUsers = null;
		if(log.isDebugEnabled())log.debug(getMailJob().getGroupId());
		try {
			Course course = CourseLocalServiceUtil.fetchByGroupCreatedId(getMailJob().getGroupId());
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
		

		for(User user : groupUsers){
			int referenceDate = (int)getMailJob().getDateReferenceDate();
			boolean addUserSend= true;
			if(referenceDate == 2){
				addUserSend= false;
				try {
					GregorianCalendar dateSend = new GregorianCalendar();
					Course course = CourseLocalServiceUtil.fetchByGroupCreatedId(getMailJob().getGroupId());
					if(course!=null){
						CourseResult cr = CourseResultLocalServiceUtil.getCourseResultByCourseAndUser(course.getCourseId(), user.getUserId());
					    if(cr!=null && cr.getRegistrationDate()!=null){
					    	dateSend.setTime(cr.getRegistrationDate());
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
								
								log.debug("USUARIO "+user.getFullName()+"  "+dateSend.getTime().getTime());
								log.debug("USUARIO "+user.getFullName()+"  "+today.getTime().getTime());
								log.debug("USUARIO "+user.getFullName()+"  "+dateSend.getTime());
								log.debug("USUARIO "+user.getFullName()+"  "+today.getTime());
							}
							if(dateSend.getTime().getTime()==today.getTime().getTime()){
								addUserSend=true;
							}
					    }
					}
				} catch (SystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
			
			if(addUserSend){
				if(log.isDebugEnabled())log.debug("-----userId---------- "+user.getUserId());
				LearningActivityResult lar = null;
				
				try {
					lar = LearningActivityResultLocalServiceUtil.getByActIdAndUserId(getMailJob().getConditionClassPK(), user.getUserId());
				} catch (SystemException e) {
					if(log.isDebugEnabled())e.printStackTrace();
					if(log.isErrorEnabled())log.error(e.getMessage());
				}
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
							if(lar==null){
								users.add(user);
								log.debug("usuario añadido 0");
							}
						break;
						//started
						case 1:
							if(lar!=null&&lar.getEndDate()==null){
								users.add(user);
								log.debug("usuario añadido 1");
							}
						break;
						//not passed
						case 2:
							if(lar!=null&&!lar.getPassed()&&lar.getEndDate()!=null){
								users.add(user);
								log.debug("usuario añadido 2");
							}
						break;
						//passed
						case 3:
							if(lar!=null&&lar.getPassed()){
								users.add(user);
								log.debug("usuario añadido 3");
							}
						break;
					}
				
				}
			}
			
			
		}

		return users;
	}
	
	

	@Override
	public boolean shouldBeProcessed() {
		
		LearningActivity la = null;
		boolean process = false;
		try {
			la = LearningActivityLocalServiceUtil.getLearningActivity(getMailJob().getDateClassPK());
		} catch (PortalException e) {
			if(log.isDebugEnabled())e.printStackTrace();
			if(log.isErrorEnabled())log.error(e.getMessage());
		} catch (SystemException e) {
			if(log.isDebugEnabled())e.printStackTrace();
			if(log.isErrorEnabled())log.error(e.getMessage());
		}
		
		if(la!=null){
			GregorianCalendar dateSend = new GregorianCalendar();
			
			switch ((int)getMailJob().getDateReferenceDate()) {
				//start date
				case 0:
					dateSend.setTime(la.getStartdate());
					break;
				//end date
				case 1:
					dateSend.setTime(la.getEnddate());
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
				log.debug(la.getStartdate());
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
		if(getMailJob()!=null&&getMailJob().getConditionClassPK()>0){
			LearningActivity la = getLearningActivity(getMailJob().getConditionClassPK());
			if(la!=null){
				return la.getTitle();
			}else{
				return StringPool.BLANK;
			}
		}else{
			return StringPool.BLANK;
		}
	}

	@Override
	public String getConditionName(Locale locale) {
		if(getMailJob()!=null&&getMailJob().getConditionClassPK()>0){
			LearningActivity la = getLearningActivity(getMailJob().getConditionClassPK());
			if(la!=null){
				return la.getTitle(locale);
			}else{
				return StringPool.BLANK;
			}
		}else{
			return StringPool.BLANK;
		}
	}

	@Override
	public String getReferenceName() {
		if(getMailJob()!=null&&getMailJob().getDateClassPK()>0){
			LearningActivity la = getLearningActivity(getMailJob().getDateClassPK());
			if(la!=null){
				return la.getTitle();
			}else{
				return StringPool.BLANK;
			}
		}else{
			return StringPool.BLANK;
		}
	}

	@Override
	public String getReferenceName(Locale locale) {
		if(getMailJob()!=null&&getMailJob().getDateClassPK()>0){
			LearningActivity la = getLearningActivity(getMailJob().getDateClassPK());
			if(la!=null){
				return la.getTitle(locale);
			}else{
				return StringPool.BLANK;
			}
		}else{
			return StringPool.BLANK;
		}
	}
	
	private LearningActivity getLearningActivity(long id){
		try {
			return LearningActivityLocalServiceUtil.getLearningActivity(id);
		} catch (PortalException e) {
			return null;
		} catch (SystemException e) {
			return null;
		}
	}

	@Override
	public Long getActReferencePK() {
		if(getMailJob()!=null&&getMailJob().getDateClassPK()>0){
			return getMailJob().getDateClassPK();
		}else{
			return null;
		}
	}

	@Override
	public Long getActConditionPK() {
		if(getMailJob()!=null&&getMailJob().getConditionClassPK()>0){
			return getMailJob().getConditionClassPK();
		}else{
			return null;
		}
	}

	@Override
	public Long getModReferencePK() {
		if(getMailJob()!=null&&getMailJob().getDateClassPK()>0){
			LearningActivity la = getLearningActivity(getMailJob().getDateClassPK());
			if(la!=null&&la.getModuleId()>0){
				return la.getModuleId();
			}else{
				return null;
			}
		}else{
			return null;
		}
	}

	@Override
	public Long getModConditionPK() {
		if(getMailJob()!=null&&getMailJob().getConditionClassPK()>0){
			LearningActivity la = getLearningActivity(getMailJob().getConditionClassPK());
			if(la!=null&&la.getModuleId()>0){
				return la.getModuleId();
			}else{
				return null;
			}
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

	@Override
	public Date getDate() {
		LearningActivity la = null;
		
		try {
			la = LearningActivityLocalServiceUtil.getLearningActivity(getMailJob().getDateClassPK());
		} catch (PortalException e) {
			if(log.isDebugEnabled())e.printStackTrace();
			if(log.isErrorEnabled())log.error(e.getMessage());
		} catch (SystemException e) {
			if(log.isDebugEnabled())e.printStackTrace();
			if(log.isErrorEnabled())log.error(e.getMessage());
		}

		if(la!=null){
			GregorianCalendar dateSend = new GregorianCalendar();
			
			switch ((int)getMailJob().getDateReferenceDate()) {
				//start date
				case 0:
					dateSend.setTime(la.getStartdate());
					break;
				//end date
				case 1:
					dateSend.setTime(la.getEnddate());
					break;
				//inscription date
				case 2:
					dateSend.setTime(la.getStartdate());
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

}
