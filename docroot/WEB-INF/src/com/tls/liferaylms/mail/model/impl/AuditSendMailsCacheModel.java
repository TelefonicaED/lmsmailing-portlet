/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.tls.liferaylms.mail.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.tls.liferaylms.mail.model.AuditSendMails;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AuditSendMails in entity cache.
 *
 * @author je03042
 * @see AuditSendMails
 * @generated
 */
public class AuditSendMailsCacheModel implements CacheModel<AuditSendMails>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", auditSendMailsId=");
		sb.append(auditSendMailsId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", templateId=");
		sb.append(templateId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", sendDate=");
		sb.append(sendDate);
		sb.append("}");

		return sb.toString();
	}

	public AuditSendMails toEntityModel() {
		AuditSendMailsImpl auditSendMailsImpl = new AuditSendMailsImpl();

		if (uuid == null) {
			auditSendMailsImpl.setUuid(StringPool.BLANK);
		}
		else {
			auditSendMailsImpl.setUuid(uuid);
		}

		auditSendMailsImpl.setAuditSendMailsId(auditSendMailsId);
		auditSendMailsImpl.setUserId(userId);
		auditSendMailsImpl.setTemplateId(templateId);
		auditSendMailsImpl.setGroupId(groupId);

		if (sendDate == Long.MIN_VALUE) {
			auditSendMailsImpl.setSendDate(null);
		}
		else {
			auditSendMailsImpl.setSendDate(new Date(sendDate));
		}

		auditSendMailsImpl.resetOriginalValues();

		return auditSendMailsImpl;
	}

	public String uuid;
	public long auditSendMailsId;
	public long userId;
	public long templateId;
	public long groupId;
	public long sendDate;
}