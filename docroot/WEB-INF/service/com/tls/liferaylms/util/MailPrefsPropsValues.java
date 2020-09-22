package com.tls.liferaylms.util;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PrefsPropsUtil;

public class MailPrefsPropsValues {
	
	public static boolean getUsersExtendedData(long companyId) {
		boolean usersExtendedData = MailConstants.PREFS_USERS_EXTENDED_DATA_DEFAULT;
		try {
			usersExtendedData = PrefsPropsUtil.getBoolean(companyId, MailConstants.PREFS_USERS_EXTENDED_DATA, MailPropsValues.USERS_EXTENDED_DATA);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return usersExtendedData;
	}
}
