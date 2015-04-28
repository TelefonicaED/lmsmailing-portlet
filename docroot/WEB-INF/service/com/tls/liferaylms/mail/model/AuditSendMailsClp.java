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

import com.tls.liferaylms.mail.service.AuditSendMailsLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author je03042
 */
public class AuditSendMailsClp extends BaseModelImpl<AuditSendMails>
	implements AuditSendMails {
	public AuditSendMailsClp() {
	}

	public Class<?> getModelClass() {
		return AuditSendMails.class;
	}

	public String getModelClassName() {
		return AuditSendMails.class.getName();
	}

	public long getPrimaryKey() {
		return _auditSendMailsId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAuditSendMailsId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_auditSendMailsId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("auditSendMailsId", getAuditSendMailsId());
		attributes.put("userId", getUserId());
		attributes.put("templateId", getTemplateId());
		attributes.put("groupId", getGroupId());
		attributes.put("sendDate", getSendDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long auditSendMailsId = (Long)attributes.get("auditSendMailsId");

		if (auditSendMailsId != null) {
			setAuditSendMailsId(auditSendMailsId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long templateId = (Long)attributes.get("templateId");

		if (templateId != null) {
			setTemplateId(templateId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Date sendDate = (Date)attributes.get("sendDate");

		if (sendDate != null) {
			setSendDate(sendDate);
		}
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getAuditSendMailsId() {
		return _auditSendMailsId;
	}

	public void setAuditSendMailsId(long auditSendMailsId) {
		_auditSendMailsId = auditSendMailsId;
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

	public long getTemplateId() {
		return _templateId;
	}

	public void setTemplateId(long templateId) {
		_templateId = templateId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public Date getSendDate() {
		return _sendDate;
	}

	public void setSendDate(Date sendDate) {
		_sendDate = sendDate;
	}

	public BaseModel<?> getAuditSendMailsRemoteModel() {
		return _auditSendMailsRemoteModel;
	}

	public void setAuditSendMailsRemoteModel(
		BaseModel<?> auditSendMailsRemoteModel) {
		_auditSendMailsRemoteModel = auditSendMailsRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AuditSendMailsLocalServiceUtil.addAuditSendMails(this);
		}
		else {
			AuditSendMailsLocalServiceUtil.updateAuditSendMails(this);
		}
	}

	@Override
	public AuditSendMails toEscapedModel() {
		return (AuditSendMails)Proxy.newProxyInstance(AuditSendMails.class.getClassLoader(),
			new Class[] { AuditSendMails.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		AuditSendMailsClp clone = new AuditSendMailsClp();

		clone.setUuid(getUuid());
		clone.setAuditSendMailsId(getAuditSendMailsId());
		clone.setUserId(getUserId());
		clone.setTemplateId(getTemplateId());
		clone.setGroupId(getGroupId());
		clone.setSendDate(getSendDate());

		return clone;
	}

	public int compareTo(AuditSendMails auditSendMails) {
		long primaryKey = auditSendMails.getPrimaryKey();

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

		AuditSendMailsClp auditSendMails = null;

		try {
			auditSendMails = (AuditSendMailsClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = auditSendMails.getPrimaryKey();

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
		StringBundler sb = new StringBundler(13);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", auditSendMailsId=");
		sb.append(getAuditSendMailsId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", templateId=");
		sb.append(getTemplateId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", sendDate=");
		sb.append(getSendDate());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.tls.liferaylms.mail.model.AuditSendMails");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>auditSendMailsId</column-name><column-value><![CDATA[");
		sb.append(getAuditSendMailsId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>templateId</column-name><column-value><![CDATA[");
		sb.append(getTemplateId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sendDate</column-name><column-value><![CDATA[");
		sb.append(getSendDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _auditSendMailsId;
	private long _userId;
	private String _userUuid;
	private long _templateId;
	private long _groupId;
	private Date _sendDate;
	private BaseModel<?> _auditSendMailsRemoteModel;
}