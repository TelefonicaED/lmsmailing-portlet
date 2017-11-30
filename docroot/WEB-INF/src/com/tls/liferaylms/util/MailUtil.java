package com.tls.liferaylms.util;

import java.util.Calendar;
import java.util.Date;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
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

}
