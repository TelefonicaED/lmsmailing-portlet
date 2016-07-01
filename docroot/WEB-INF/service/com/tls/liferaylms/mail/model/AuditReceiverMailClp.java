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

import com.tls.liferaylms.mail.service.AuditReceiverMailLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author je03042
 */
public class AuditReceiverMailClp extends BaseModelImpl<AuditReceiverMail>
	implements AuditReceiverMail {
	public AuditReceiverMailClp() {
	}

	public Class<?> getModelClass() {
		return AuditReceiverMail.class;
	}

	public String getModelClassName() {
		return AuditReceiverMail.class.getName();
	}

	public long getPrimaryKey() {
		return _auditReceiverMailId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAuditReceiverMailId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_auditReceiverMailId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("auditReceiverMailId", getAuditReceiverMailId());
		attributes.put("auditSendMailsId", getAuditSendMailsId());
		attributes.put("to", getTo());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long auditReceiverMailId = (Long)attributes.get("auditReceiverMailId");

		if (auditReceiverMailId != null) {
			setAuditReceiverMailId(auditReceiverMailId);
		}

		Long auditSendMailsId = (Long)attributes.get("auditSendMailsId");

		if (auditSendMailsId != null) {
			setAuditSendMailsId(auditSendMailsId);
		}

		String to = (String)attributes.get("to");

		if (to != null) {
			setTo(to);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	public long getAuditReceiverMailId() {
		return _auditReceiverMailId;
	}

	public void setAuditReceiverMailId(long auditReceiverMailId) {
		_auditReceiverMailId = auditReceiverMailId;
	}

	public long getAuditSendMailsId() {
		return _auditSendMailsId;
	}

	public void setAuditSendMailsId(long auditSendMailsId) {
		_auditSendMailsId = auditSendMailsId;
	}

	public String getTo() {
		return _to;
	}

	public void setTo(String to) {
		_to = to;
	}

	public Integer getStatus() {
		return _status;
	}

	public void setStatus(Integer status) {
		_status = status;
	}

	public BaseModel<?> getAuditReceiverMailRemoteModel() {
		return _auditReceiverMailRemoteModel;
	}

	public void setAuditReceiverMailRemoteModel(
		BaseModel<?> auditReceiverMailRemoteModel) {
		_auditReceiverMailRemoteModel = auditReceiverMailRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AuditReceiverMailLocalServiceUtil.addAuditReceiverMail(this);
		}
		else {
			AuditReceiverMailLocalServiceUtil.updateAuditReceiverMail(this);
		}
	}

	@Override
	public AuditReceiverMail toEscapedModel() {
		return (AuditReceiverMail)Proxy.newProxyInstance(AuditReceiverMail.class.getClassLoader(),
			new Class[] { AuditReceiverMail.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		AuditReceiverMailClp clone = new AuditReceiverMailClp();

		clone.setAuditReceiverMailId(getAuditReceiverMailId());
		clone.setAuditSendMailsId(getAuditSendMailsId());
		clone.setTo(getTo());
		clone.setStatus(getStatus());

		return clone;
	}

	public int compareTo(AuditReceiverMail auditReceiverMail) {
		long primaryKey = auditReceiverMail.getPrimaryKey();

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

		AuditReceiverMailClp auditReceiverMail = null;

		try {
			auditReceiverMail = (AuditReceiverMailClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = auditReceiverMail.getPrimaryKey();

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
		StringBundler sb = new StringBundler(9);

		sb.append("{auditReceiverMailId=");
		sb.append(getAuditReceiverMailId());
		sb.append(", auditSendMailsId=");
		sb.append(getAuditSendMailsId());
		sb.append(", to=");
		sb.append(getTo());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.tls.liferaylms.mail.model.AuditReceiverMail");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>auditReceiverMailId</column-name><column-value><![CDATA[");
		sb.append(getAuditReceiverMailId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>auditSendMailsId</column-name><column-value><![CDATA[");
		sb.append(getAuditSendMailsId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>to</column-name><column-value><![CDATA[");
		sb.append(getTo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _auditReceiverMailId;
	private long _auditSendMailsId;
	private String _to;
	private Integer _status;
	private BaseModel<?> _auditReceiverMailRemoteModel;
}