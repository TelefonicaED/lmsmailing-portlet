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

import com.tls.liferaylms.mail.model.MailJob;

import java.io.Serializable;

/**
 * The cache model class for representing MailJob in entity cache.
 *
 * @author je03042
 * @see MailJob
 * @generated
 */
public class MailJobCacheModel implements CacheModel<MailJob>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", idJob=");
		sb.append(idJob);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", idTemplate=");
		sb.append(idTemplate);
		sb.append(", conditionClassName=");
		sb.append(conditionClassName);
		sb.append(", conditionClassPK=");
		sb.append(conditionClassPK);
		sb.append(", conditionStatus=");
		sb.append(conditionStatus);
		sb.append(", dateClassName=");
		sb.append(dateClassName);
		sb.append(", dateClassPK=");
		sb.append(dateClassPK);
		sb.append(", dateReferenceDate=");
		sb.append(dateReferenceDate);
		sb.append(", dateShift=");
		sb.append(dateShift);
		sb.append(", teamId=");
		sb.append(teamId);
		sb.append(", processed=");
		sb.append(processed);
		sb.append("}");

		return sb.toString();
	}

	public MailJob toEntityModel() {
		MailJobImpl mailJobImpl = new MailJobImpl();

		if (uuid == null) {
			mailJobImpl.setUuid(StringPool.BLANK);
		}
		else {
			mailJobImpl.setUuid(uuid);
		}

		mailJobImpl.setIdJob(idJob);
		mailJobImpl.setCompanyId(companyId);
		mailJobImpl.setGroupId(groupId);
		mailJobImpl.setUserId(userId);
		mailJobImpl.setIdTemplate(idTemplate);

		if (conditionClassName == null) {
			mailJobImpl.setConditionClassName(StringPool.BLANK);
		}
		else {
			mailJobImpl.setConditionClassName(conditionClassName);
		}

		mailJobImpl.setConditionClassPK(conditionClassPK);

		if (conditionStatus == null) {
			mailJobImpl.setConditionStatus(StringPool.BLANK);
		}
		else {
			mailJobImpl.setConditionStatus(conditionStatus);
		}

		if (dateClassName == null) {
			mailJobImpl.setDateClassName(StringPool.BLANK);
		}
		else {
			mailJobImpl.setDateClassName(dateClassName);
		}

		mailJobImpl.setDateClassPK(dateClassPK);
		mailJobImpl.setDateReferenceDate(dateReferenceDate);
		mailJobImpl.setDateShift(dateShift);
		mailJobImpl.setTeamId(teamId);
		mailJobImpl.setProcessed(processed);

		mailJobImpl.resetOriginalValues();

		return mailJobImpl;
	}

	public String uuid;
	public long idJob;
	public long companyId;
	public long groupId;
	public long userId;
	public long idTemplate;
	public String conditionClassName;
	public long conditionClassPK;
	public String conditionStatus;
	public String dateClassName;
	public long dateClassPK;
	public long dateReferenceDate;
	public long dateShift;
	public long teamId;
	public boolean processed;
}