package com.tls.liferaylms.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.portlet.ActionRequest;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.lms.model.Course;
import com.liferay.lms.service.CourseLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.announcements.model.AnnouncementsEntry;
import com.liferay.portlet.announcements.model.AnnouncementsFlagConstants;
import com.liferay.portlet.announcements.service.AnnouncementsEntryLocalServiceUtil;
import com.liferay.portlet.announcements.service.AnnouncementsFlagLocalServiceUtil;
import com.liferay.portlet.social.service.SocialRelationLocalServiceUtil;
import com.tls.liferaylms.mail.service.MailRelationLocalServiceUtil;

public class MailUtil {

	private static Log log = LogFactoryUtil.getLog(MailUtil.class);

	public static void sendInternalMessageNotification(Long entryId,
			String title, String content, long groupId, long senderUserId,
			long userId, long companyId) {
		long classNameId = PortalUtil.getClassNameId(Group.class.getName());
		try {
			String type = "announcements.type.general";
			log.debug("-- Sending Interal Messaging Notification");
			log.debug("-- Content "+content);
			Date now = new Date();
			log.debug("NOW " + now);
			Calendar displayDate = Calendar.getInstance();
			Calendar expirationDate = Calendar.getInstance();
			displayDate.setTime(now);
			expirationDate.setTime(now);

			expirationDate.add(Calendar.MONTH, 1);

			AnnouncementsEntry ae = AnnouncementsEntryLocalServiceUtil
					.createAnnouncementsEntry(CounterLocalServiceUtil
							.increment());

			ae.setCompanyId(companyId);
			ae.setUserId(senderUserId);
			ae.setUserName(StringPool.BLANK);
			ae.setCreateDate(now);
			ae.setModifiedDate(now);
			ae.setClassNameId(classNameId);
			ae.setClassPK(groupId);
			ae.setTitle(title);
			ae.setContent(content);
			ae.setUrl(StringPool.BLANK);
			ae.setType(type);
			ae.setDisplayDate(displayDate.getTime());
			ae.setExpirationDate(expirationDate.getTime());
			ae.setPriority(0);
			ae.setAlert(true);

			ae = AnnouncementsEntryLocalServiceUtil
					.updateAnnouncementsEntry(ae);
			AnnouncementsFlagLocalServiceUtil.addFlag(userId, ae.getEntryId(),
					AnnouncementsFlagConstants.UNREAD);

			if (entryId != null && entryId > 0) {
				AnnouncementsFlagLocalServiceUtil.addFlag(userId, entryId,
						MailConstants.ANNOUNCEMENT_FLAG_DELETED);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static AnnouncementsEntry createInternalMessageNotification(
			String title, String content, long groupId, long senderUserId,
			long companyId) {
		long classNameId = PortalUtil.getClassNameId(Group.class.getName());
		AnnouncementsEntry ae = null;
		try {
			String type = "announcements.type.general";
			log.debug("-- Creating Internal Messaging Notification");
			Date now = new Date();
			log.debug("NOW " + now);
			Calendar displayDate = Calendar.getInstance();
			Calendar expirationDate = Calendar.getInstance();
			displayDate.setTime(now);
			expirationDate.setTime(now);

			expirationDate.add(Calendar.MONTH, 1);

			ae = AnnouncementsEntryLocalServiceUtil
					.createAnnouncementsEntry(CounterLocalServiceUtil
							.increment());

			ae.setCompanyId(companyId);
			ae.setUserId(senderUserId);
			ae.setUserName(StringPool.BLANK);
			ae.setCreateDate(now);
			ae.setModifiedDate(now);
			ae.setClassNameId(classNameId);
			ae.setClassPK(groupId);
			ae.setTitle(title);
			ae.setContent(content);
			ae.setUrl(StringPool.BLANK);
			ae.setType(type);
			ae.setDisplayDate(displayDate.getTime());
			ae.setExpirationDate(expirationDate.getTime());
			ae.setPriority(0);
			ae.setAlert(true);

			ae = AnnouncementsEntryLocalServiceUtil
					.updateAnnouncementsEntry(ae);
			AnnouncementsFlagLocalServiceUtil.addFlag(senderUserId,
					ae.getEntryId(), AnnouncementsFlagConstants.NOT_HIDDEN);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ae;
	}
	
	
	public static String getURLPortal(Company company, ActionRequest request){
		String url = "";
		if(request.getScheme().equals("https")){
			url = PortalUtil.getPortalURL(company.getVirtualHostname(), 80, true);
		}else{
			url = PortalUtil.getPortalURL(company.getVirtualHostname(), 80, false);
		}
		
    	//QUITANDO PUERTOS
		String[] urls = url.split(":");
		url = urls[0] + ":" +urls[1];  // http:prueba.es:8080		
		log.debug("url: " + url);
		return url;
	}
	
	public static String replaceMessageConstants(String text, String portal, String community, String student, String studentScreenName, String teacher, String url, String urlcourse, String startDate, String endDate, String userSender){
		String res = "";
		
		res = text.replace("[@portal]", 	portal);
		res = res.replace ("[@course]", 	community);
		res = res.replace ("[@teacher]", 	teacher);
		res = res.replace ("[@url]", 		"<a href=\""+url+"\">"+portal+"</a>");
		res = res.replace ("[@urlcourse]", 	"<a href=\""+urlcourse+"\">"+community+"</a>");	
		res = res.replace ("[@startDate]", startDate);
		res = res.replace ("[@endDate]", endDate);
		
		//Cambiamos las variables nuevas:
		res = res.replace("[$PORTAL$]", 	portal);
		res = res.replace ("[$TITLE_COURSE$]", 	community);
		res = res.replace ("[$TEACHER$]", 	teacher);
		res = res.replace ("[$USER_SENDER$]", 	userSender);
		res = res.replace ("[$URL$]", 		"<a href=\""+url+"\">"+portal+"</a>");
		res = res.replace ("[$PAGE_URL$]", 	"<a href=\""+urlcourse+"\">"+community+"</a>");	
		res = res.replace ("[$START_DATE$]", startDate);
		res = res.replace ("[$END_DATE$]", endDate);
		
		res = replaceStudent(res, student, studentScreenName);
		//Se cambiala URL des.
		res = MailUtil.changeToURL(res, url);
		
		return res;
	}

	public static String getCourseStartDate(long groupId, Locale locale ,TimeZone timeZone){
	
		String startDate = "";
		Course course;
		try {
			course = CourseLocalServiceUtil.fetchByGroupCreatedId(groupId);
			if(course!=null){
				SimpleDateFormat dateFormatDate = new SimpleDateFormat("dd/MM/yyyy");
				startDate = dateFormatDate.format(course.getExecutionStartDate());
			}
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return startDate;
	}
	
	public static String getCourseEndDate(long groupId, Locale locale ,TimeZone timeZone){
		
		String endDate = "";
		try {
			Course course = CourseLocalServiceUtil.fetchByGroupCreatedId(groupId);
			if(course!=null){
				SimpleDateFormat dateFormatDate = new SimpleDateFormat("dd/MM/yyyy");
				endDate  = dateFormatDate.format(course.getExecutionEndDate());
			}
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return endDate;
	}
	/*
	 * Método que cambia cambia el nombre del usuario de la plantilla.
	 */
	public static String replaceStudent(String text, String student, String studentScreenName) {
		if(text != null) {
			if(student!=null){
				text = text.replace ("[@student]", 	student);
				//Cambiamos la variable nueva
				text = text.replace("[$USER_FULLNAME$]", student);
			}
			
			//Cambiamos la variable nueva
			if(studentScreenName!=null){
				text = text.replace("[$USER_SCREENNAME$]", studentScreenName);
			}
			
			return text;
		}
		else {
			return "";
		}
	}

	
	public static String changeToURL(String text, String url){
		String res ="";

		//Para imï¿½genes
		res = text.replaceAll("src=\"/image/image_gallery", "src=\""+url+"/image/image_gallery");
		
		return res;
	}
	
	public static String getTutors(long courseGroupCreatedId) {
		long courseId=0;
		Course course=null;
		List<User> courseTutors = null;
		String tutors = "";
		
		try{
			course=CourseLocalServiceUtil.getCourseByGroupCreatedId( courseGroupCreatedId );
			if(course!=null){
				courseId=course.getCourseId();
				courseTutors = CourseLocalServiceUtil.getTeachersFromCourse(courseId);
				if(courseTutors!=null && courseTutors.size()>0){
					int numTutors = courseTutors.size();
					tutors = courseTutors.get(0).getFullName();
					if(numTutors>1)
					{
						for(int idx=1; idx<=numTutors; idx++)
							tutors = tutors.concat(StringPool.COMMA_AND_SPACE)
										.concat(courseTutors.get(idx).getFullName());
					}
				}
				else
					log.debug("There are no course tutors.  CourseId: " + courseId );
			}
			else
				log.debug("NULL course for groupCreatedId: " + courseGroupCreatedId);
		}catch(Exception e){}
		
		return tutors;
	}
	
	public static List<User> getSocialRelationUsers(User user, List<Integer> socialRelationTypeIds, List<User> socialRelationUsers, long companyId){
		List<User> socialRelationUsersTmp = new ArrayList<User>();
		log.debug("Social Relation Users ");
		for(int relationTypeId:socialRelationTypeIds){
			socialRelationUsersTmp = MailRelationLocalServiceUtil.findUsersByCompanyIdSocialRelationTypeIdToUserId(user.getUserId(), relationTypeId, companyId);
			log.debug("::socialRelationUsersTmp OK::: " + Validator.isNotNull(socialRelationUsersTmp));
			if(Validator.isNotNull(socialRelationUsersTmp) && socialRelationUsersTmp.size()>0){
				log.debug("::socialRelationUsersTmp.size()::: " + socialRelationUsersTmp.size());
				for(User userRelated:socialRelationUsersTmp){
					if(!socialRelationUsers.contains(userRelated))
						socialRelationUsers.add(userRelated);
				}
			}
		}
		return socialRelationUsers;
	}
	
	public static String getExtraContentSocialRelationHeader(User user){
		Locale locale = LocaleUtil.getDefault();
		if(Validator.isNotNull(user))
			locale = user.getLocale();
		return "<p>" + LanguageUtil.get(locale, "groupmailing.messages.email-sent-to-students") +"</p>";
	}
	
	public static String getExtraContentSocialRelation(List<User> listUsers, User user, List<Integer> typeIds){
		Locale locale = user.getLocale();
		String extraContent = "<p>" + LanguageUtil.get(locale, "groupmailing.messages.email-received-by") +"</p>";
		if(Validator.isNotNull(listUsers) && listUsers.size()>0){
			extraContent += "<p><em>";
			for(User u:listUsers){
				//Comprobar si es subordinado
				if(MailRelationLocalServiceUtil.countSocialRelationsBeetweenUsersBySocialRelationTypeIds(u.getUserId(), user.getUserId(), typeIds, user.getCompanyId())>0)
					extraContent += u.getEmailAddress() + StringPool.SEMICOLON + StringPool.SPACE;
			}
			extraContent = extraContent.substring(0,extraContent.length()-2);
			extraContent += "</em></p>";
		}
		extraContent += "<p>" + LanguageUtil.get(locale, "groupmailing.messages.email-sent") + "</p>";
		extraContent += "<p>" + StringPool.UNDERLINE + StringPool.UNDERLINE + StringPool.UNDERLINE + StringPool.UNDERLINE + StringPool.UNDERLINE + StringPool.UNDERLINE + "</p>";
		return extraContent;
	}
}
