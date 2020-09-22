package com.tls.liferaylms.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

public class MailPropsValues {
	public static final boolean USERS_EXTENDED_DATA = GetterUtil.getBoolean(PropsUtil.get(MailConstants.PREFS_USERS_EXTENDED_DATA));
}
