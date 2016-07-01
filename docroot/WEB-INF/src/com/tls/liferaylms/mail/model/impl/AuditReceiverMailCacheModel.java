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

import com.tls.liferaylms.mail.model.AuditReceiverMail;

import java.io.Serializable;

/**
 * The cache model class for representing AuditReceiverMail in entity cache.
 *
 * @author je03042
 * @see AuditReceiverMail
 * @generated
 */
public class AuditReceiverMailCacheModel implements CacheModel<AuditReceiverMail>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{auditReceiverMailId=");
		sb.append(auditReceiverMailId);
		sb.append(", auditSendMailsId=");
		sb.append(auditSendMailsId);
		sb.append(", to=");
		sb.append(to);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	public AuditReceiverMail toEntityModel() {
		AuditReceiverMailImpl auditReceiverMailImpl = new AuditReceiverMailImpl();

		auditReceiverMailImpl.setAuditReceiverMailId(auditReceiverMailId);
		auditReceiverMailImpl.setAuditSendMailsId(auditSendMailsId);

		if (to == null) {
			auditReceiverMailImpl.setTo(StringPool.BLANK);
		}
		else {
			auditReceiverMailImpl.setTo(to);
		}

		auditReceiverMailImpl.setStatus(status);

		auditReceiverMailImpl.resetOriginalValues();

		return auditReceiverMailImpl;
	}

	public long auditReceiverMailId;
	public long auditSendMailsId;
	public String to;
	public Integer status;
}