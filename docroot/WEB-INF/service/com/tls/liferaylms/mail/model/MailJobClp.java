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

package com.tls.liferaylms.mail.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import com.tls.liferaylms.mail.service.MailJobLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author je03042
 */
public class MailJobClp extends BaseModelImpl<MailJob> implements MailJob {
	public MailJobClp() {
	}

	public Class<?> getModelClass() {
		return MailJob.class;
	}

	public String getModelClassName() {
		return MailJob.class.getName();
	}

	public long getPrimaryKey() {
		return _idJob;
	}

	public void setPrimaryKey(long primaryKey) {
		setIdJob(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_idJob);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("idJob", getIdJob());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("idTemplate", getIdTemplate());
		attributes.put("conditionClassName", getConditionClassName());
		attributes.put("conditionClassPK", getConditionClassPK());
		attributes.put("conditionStatus", getConditionStatus());
		attributes.put("dateClassName", getDateClassName());
		attributes.put("dateClassPK", getDateClassPK());
		attributes.put("dateReferenceDate", getDateReferenceDate());
		attributes.put("dateShift", getDateShift());
		attributes.put("teamId", getTeamId());
		attributes.put("processed", getProcessed());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long idJob = (Long)attributes.get("idJob");

		if (idJob != null) {
			setIdJob(idJob);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long idTemplate = (Long)attributes.get("idTemplate");

		if (idTemplate != null) {
			setIdTemplate(idTemplate);
		}

		String conditionClassName = (String)attributes.get("conditionClassName");

		if (conditionClassName != null) {
			setConditionClassName(conditionClassName);
		}

		Long conditionClassPK = (Long)attributes.get("conditionClassPK");

		if (conditionClassPK != null) {
			setConditionClassPK(conditionClassPK);
		}

		String conditionStatus = (String)attributes.get("conditionStatus");

		if (conditionStatus != null) {
			setConditionStatus(conditionStatus);
		}

		String dateClassName = (String)attributes.get("dateClassName");

		if (dateClassName != null) {
			setDateClassName(dateClassName);
		}

		Long dateClassPK = (Long)attributes.get("dateClassPK");

		if (dateClassPK != null) {
			setDateClassPK(dateClassPK);
		}

		Long dateReferenceDate = (Long)attributes.get("dateReferenceDate");

		if (dateReferenceDate != null) {
			setDateReferenceDate(dateReferenceDate);
		}

		Long dateShift = (Long)attributes.get("dateShift");

		if (dateShift != null) {
			setDateShift(dateShift);
		}

		Long teamId = (Long)attributes.get("teamId");

		if (teamId != null) {
			setTeamId(teamId);
		}

		Boolean processed = (Boolean)attributes.get("processed");

		if (processed != null) {
			setProcessed(processed);
		}
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getIdJob() {
		return _idJob;
	}

	public void setIdJob(long idJob) {
		_idJob = idJob;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public long getIdTemplate() {
		return _idTemplate;
	}

	public void setIdTemplate(long idTemplate) {
		_idTemplate = idTemplate;
	}

	public String getConditionClassName() {
		return _conditionClassName;
	}

	public void setConditionClassName(String conditionClassName) {
		_conditionClassName = conditionClassName;
	}

	public long getConditionClassPK() {
		return _conditionClassPK;
	}

	public void setConditionClassPK(long conditionClassPK) {
		_conditionClassPK = conditionClassPK;
	}

	public String getConditionStatus() {
		return _conditionStatus;
	}

	public void setConditionStatus(String conditionStatus) {
		_conditionStatus = conditionStatus;
	}

	public String getDateClassName() {
		return _dateClassName;
	}

	public void setDateClassName(String dateClassName) {
		_dateClassName = dateClassName;
	}

	public long getDateClassPK() {
		return _dateClassPK;
	}

	public void setDateClassPK(long dateClassPK) {
		_dateClassPK = dateClassPK;
	}

	public long getDateReferenceDate() {
		return _dateReferenceDate;
	}

	public void setDateReferenceDate(long dateReferenceDate) {
		_dateReferenceDate = dateReferenceDate;
	}

	public long getDateShift() {
		return _dateShift;
	}

	public void setDateShift(long dateShift) {
		_dateShift = dateShift;
	}

	public long getTeamId() {
		return _teamId;
	}

	public void setTeamId(long teamId) {
		_teamId = teamId;
	}

	public boolean getProcessed() {
		return _processed;
	}

	public boolean isProcessed() {
		return _processed;
	}

	public void setProcessed(boolean processed) {
		_processed = processed;
	}

	public BaseModel<?> getMailJobRemoteModel() {
		return _mailJobRemoteModel;
	}

	public void setMailJobRemoteModel(BaseModel<?> mailJobRemoteModel) {
		_mailJobRemoteModel = mailJobRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			MailJobLocalServiceUtil.addMailJob(this);
		}
		else {
			MailJobLocalServiceUtil.updateMailJob(this);
		}
	}

	@Override
	public MailJob toEscapedModel() {
		return (MailJob)Proxy.newProxyInstance(MailJob.class.getClassLoader(),
			new Class[] { MailJob.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		MailJobClp clone = new MailJobClp();

		clone.setUuid(getUuid());
		clone.setIdJob(getIdJob());
		clone.setCompanyId(getCompanyId());
		clone.setGroupId(getGroupId());
		clone.setUserId(getUserId());
		clone.setIdTemplate(getIdTemplate());
		clone.setConditionClassName(getConditionClassName());
		clone.setConditionClassPK(getConditionClassPK());
		clone.setConditionStatus(getConditionStatus());
		clone.setDateClassName(getDateClassName());
		clone.setDateClassPK(getDateClassPK());
		clone.setDateReferenceDate(getDateReferenceDate());
		clone.setDateShift(getDateShift());
		clone.setTeamId(getTeamId());
		clone.setProcessed(getProcessed());

		return clone;
	}

	public int compareTo(MailJob mailJob) {
		long primaryKey = mailJob.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		MailJobClp mailJob = null;

		try {
			mailJob = (MailJobClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = mailJob.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", idJob=");
		sb.append(getIdJob());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", idTemplate=");
		sb.append(getIdTemplate());
		sb.append(", conditionClassName=");
		sb.append(getConditionClassName());
		sb.append(", conditionClassPK=");
		sb.append(getConditionClassPK());
		sb.append(", conditionStatus=");
		sb.append(getConditionStatus());
		sb.append(", dateClassName=");
		sb.append(getDateClassName());
		sb.append(", dateClassPK=");
		sb.append(getDateClassPK());
		sb.append(", dateReferenceDate=");
		sb.append(getDateReferenceDate());
		sb.append(", dateShift=");
		sb.append(getDateShift());
		sb.append(", teamId=");
		sb.append(getTeamId());
		sb.append(", processed=");
		sb.append(getProcessed());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(49);

		sb.append("<model><model-name>");
		sb.append("com.tls.liferaylms.mail.model.MailJob");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>idJob</column-name><column-value><![CDATA[");
		sb.append(getIdJob());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>idTemplate</column-name><column-value><![CDATA[");
		sb.append(getIdTemplate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>conditionClassName</column-name><column-value><![CDATA[");
		sb.append(getConditionClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>conditionClassPK</column-name><column-value><![CDATA[");
		sb.append(getConditionClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>conditionStatus</column-name><column-value><![CDATA[");
		sb.append(getConditionStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dateClassName</column-name><column-value><![CDATA[");
		sb.append(getDateClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dateClassPK</column-name><column-value><![CDATA[");
		sb.append(getDateClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dateReferenceDate</column-name><column-value><![CDATA[");
		sb.append(getDateReferenceDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dateShift</column-name><column-value><![CDATA[");
		sb.append(getDateShift());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>teamId</column-name><column-value><![CDATA[");
		sb.append(getTeamId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>processed</column-name><column-value><![CDATA[");
		sb.append(getProcessed());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _idJob;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userUuid;
	private long _idTemplate;
	private String _conditionClassName;
	private long _conditionClassPK;
	private String _conditionStatus;
	private String _dateClassName;
	private long _dateClassPK;
	private long _dateReferenceDate;
	private long _dateShift;
	private long _teamId;
	private boolean _processed;
	private BaseModel<?> _mailJobRemoteModel;
}