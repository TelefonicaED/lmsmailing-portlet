package com.tls.liferaylms.job.condition;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.liferay.lms.model.LearningActivity;
import com.liferay.lms.model.LearningActivityResult;
import com.liferay.lms.service.LearningActivityLocalServiceUtil;
import com.liferay.lms.service.LearningActivityResultLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
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
		try {
			groupUsers = UserLocalServiceUtil.getGroupUsers(getMailJob().getGroupId());
		} catch (SystemException e) {
			if(log.isDebugEnabled())e.printStackTrace();
			if(log.isErrorEnabled())log.error(e.getMessage());
		}
		Set<User> users = new HashSet<User>();
		

		for(User user : groupUsers){
			LearningActivityResult lar = null;
			
			//Excluye los usuarios que son algo más que estudiantes
			/*try {
				if((PermissionCheckerFactoryUtil.create(user)).hasPermission(getMailJob().getGroupId(), "com.liferay.lms.model", getMailJob().getGroupId(), "VIEW_RESULTS")){
					continue;
				}
			} catch (Exception e) {
				if(log.isDebugEnabled())e.printStackTrace();
				if(log.isErrorEnabled())log.error(e.getMessage());
			}*/
			
			try {
				lar = LearningActivityResultLocalServiceUtil.getByActIdAndUserId(getMailJob().getConditionClassPK(), user.getUserId());
			} catch (SystemException e) {
				if(log.isDebugEnabled())e.printStackTrace();
				if(log.isErrorEnabled())log.error(e.getMessage());
			}
			
			String[] ids = getMailJob().getConditionStatus().split(StringPool.COMMA);
			
			for(String sid : ids){
				
				int id = -1;
				try{
					id = (int)Integer.valueOf(sid);
				}catch(NumberFormatException nfe){
					if(log.isDebugEnabled())nfe.printStackTrace();
					if(log.isErrorEnabled())log.error(nfe.getMessage());
				}
			
				switch (id) {
					//not started
					case 0:
						if(lar==null){
							users.add(user);
						}
					break;
					//started
					case 1:
						if(lar!=null&&lar.getEndDate()==null){
							users.add(user);
						}
					break;
					//not passed
					case 2:
						if(lar!=null&&!lar.getPassed()&&lar.getEndDate()!=null){
							users.add(user);
						}
					break;
					//passed
					case 3:
						if(lar!=null&&lar.getPassed()){
							users.add(user);
						}
					break;
				}
			}
		}

		return users;
	}
	
	

	@Override
	public boolean shouldBeProcessed() {
		
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
