package com.tls.liferaylms.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.lms.model.Course;
import com.liferay.lms.service.CourseLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.announcements.model.AnnouncementsEntry;
import com.liferay.portlet.announcements.model.AnnouncementsEntryConstants;
import com.liferay.portlet.announcements.model.AnnouncementsFlagConstants;
import com.liferay.portlet.announcements.service.AnnouncementsEntryLocalServiceUtil;
import com.liferay.portlet.announcements.service.AnnouncementsFlagLocalServiceUtil;

public class MailUtil {

	private static Log log = LogFactoryUtil.getLog(MailUtil.class);

	public static void sendInternalMessageNotification(Long entryId,
			MailMessage mailMessage, long groupId, long senderUserId,
			long userId, long companyId) {
		long classNameId = PortalUtil.getClassNameId(Group.class.getName());
		try {
			String title = mailMessage.getSubject();
			String content = mailMessage.getBody();
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
	
	public static String createMessage(String text, String portal, String community, String student, String teacher, String url, String urlcourse){
		String res = "";
		res = text.replace("[@portal]", 	portal);
		res = res.replace ("[@course]", 	community);
		res = res.replace ("[@student]", 	student);
		res = res.replace ("[@teacher]", 	teacher);
		res = res.replace ("[@url]", 		"<a href=\""+url+"\">"+portal+"</a>");
		res = res.replace ("[@urlcourse]", 	"<a href=\""+urlcourse+"\">"+community+"</a>");	

		//Para poner la url desde la pï¿½gina para que se vean los correos.
		res = changeToURL(res, url);
		
		return res;
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
}
