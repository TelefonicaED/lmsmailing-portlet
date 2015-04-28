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

import com.tls.liferaylms.mail.model.MailTemplate;

import java.io.Serializable;

/**
 * The cache model class for representing MailTemplate in entity cache.
 *
 * @author je03042
 * @see MailTemplate
 * @generated
 */
public class MailTemplateCacheModel implements CacheModel<MailTemplate>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", idTemplate=");
		sb.append(idTemplate);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", subject=");
		sb.append(subject);
		sb.append(", body=");
		sb.append(body);
		sb.append("}");

		return sb.toString();
	}

	public MailTemplate toEntityModel() {
		MailTemplateImpl mailTemplateImpl = new MailTemplateImpl();

		if (uuid == null) {
			mailTemplateImpl.setUuid(StringPool.BLANK);
		}
		else {
			mailTemplateImpl.setUuid(uuid);
		}

		mailTemplateImpl.setIdTemplate(idTemplate);
		mailTemplateImpl.setCompanyId(companyId);
		mailTemplateImpl.setGroupId(groupId);
		mailTemplateImpl.setUserId(userId);

		if (subject == null) {
			mailTemplateImpl.setSubject(StringPool.BLANK);
		}
		else {
			mailTemplateImpl.setSubject(subject);
		}

		if (body == null) {
			mailTemplateImpl.setBody(StringPool.BLANK);
		}
		else {
			mailTemplateImpl.setBody(body);
		}

		mailTemplateImpl.resetOriginalValues();

		return mailTemplateImpl;
	}

	public String uuid;
	public long idTemplate;
	public long companyId;
	public long groupId;
	public long userId;
	public String subject;
	public String body;
}