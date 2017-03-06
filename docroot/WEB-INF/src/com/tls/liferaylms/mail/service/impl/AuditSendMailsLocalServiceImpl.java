/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.tls.liferaylms.mail.service.impl;

import java.util.List;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactory;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.tls.liferaylms.mail.model.AuditSendMails;
import com.tls.liferaylms.mail.service.base.AuditSendMailsLocalServiceBaseImpl;
import com.tls.liferaylms.mail.service.persistence.AuditSendMailsUtil;

/**
 * The implementation of the audit send mails local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.tls.liferaylms.mail.service.AuditSendMailsLocalService} interface.
 * </p>
 *
 * <p>
 * Never reference this interface directly. Always use {@link com.tls.liferaylms.mail.service.AuditSendMailsLocalServiceUtil} to access the audit send mails local service.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author je03042
 * @see com.tls.liferaylms.mail.service.base.AuditSendMailsLocalServiceBaseImpl
 * @see com.tls.liferaylms.mail.service.AuditSendMailsLocalServiceUtil
 */
public class AuditSendMailsLocalServiceImpl
	extends AuditSendMailsLocalServiceBaseImpl {
	
	public List<AuditSendMails> getHistoryByCompanyId(long companyId) throws SystemException{
		return AuditSendMailsUtil.findByc(companyId);
	}

	
	public int countHistoryByCompanyId(long companyId) throws SystemException{
		return AuditSendMailsUtil.countByc(companyId);
	}

	
	
	public List<AuditSendMails> getHistoryByCompanyId(long companyId, int start, int end) throws SystemException{
		OrderByComparator comparator = OrderByComparatorFactoryUtil.create("lmsmail_auditsendmails", "sendDate", true);
		
		return AuditSendMailsUtil.findByc(companyId, start, end, comparator);
	}
	
	public AuditSendMails getInscriptionHistory(long groupId, long companyId) throws SystemException{
		return AuditSendMailsUtil.fetchByinscriptionByGC(groupId, companyId, "COURSE_INSCRIPTION");
	}
	
}