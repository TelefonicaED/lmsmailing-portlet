package com.tls.liferaylms.util;

public class MailConstants {
	public static int STATUS_OK = 1;
	public static int STATUS_KO = 0;
	public static final String TYPE_INSCRIPTION = "COURSE_INSCRIPTION";
	public static final String TYPE_MAILJOB = "MAIL_JOB";
	public static final String TYPE_MASSIVE = "MASS_MAILING";
	public static final String TYPE_MAIL_TO_TUTOR = "MAIL_TO_TUTOR";

	
	public static final String ATTACHMENTS_MAX_SIZE_KEY = "com.lms.mailing.attachment.max.size";
	public static final String ATTACHMENTS_ACCEPTED_FILES_KEY = "com.lms.mailing.attachment.accepted.files";
	
	public static final String ATTACHMENTS_DEFAULT_ACCEPTED_FILES = "png|jpg|pdf";
	public static final int ATTACHMENTS_DEFAULT_MAX_SIZE = 20480;
	
	public static int ANNOUNCEMENT_FLAG_DELETED = 4;
	
	public static final String USER_EXPANDOS_TO_SHOW = "mail-preferences.show-user-expandos";
	public static final String USER_EXPANDO_TO_SHOW = "mail-preferences.show-user-expando.";
	public static final String COURSE_EXPANDOS_TO_SHOW = "mail-preferences.show-course-expandos";
	public static final String COURSE_EXPANDO_TO_SHOW = "mail-preferences.show-course-expando.";
	
	public static final String FOOTER_PREFS = "mail-preferences.footer";
	public static final String HEADER_PREFS = "mail-preferences.header";
	public static final String INTERNAL_MESSAGING_FILE_PATH_FOLDER = "internal-messaging";
	public static final String ATTACHMENTS_FILE_PATH_FOLDER = "attachments";
	public final static String PREFS_USERS_EXTENDED_DATA = "users.view.extended.data";
	public final static boolean PREFS_USERS_EXTENDED_DATA_DEFAULT = false;
	public final static String ACTION_VIEW_USER_EXTENDED = "VIEW_USER_EXTENDED";}
