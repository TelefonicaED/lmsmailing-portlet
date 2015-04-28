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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.tls.liferaylms.mail.service.http.AuditSendMailsServiceSoap}.
 *
 * @author    je03042
 * @see       com.tls.liferaylms.mail.service.http.AuditSendMailsServiceSoap
 * @generated
 */
public class AuditSendMailsSoap implements Serializable {
	public static AuditSendMailsSoap toSoapModel(AuditSendMails model) {
		AuditSendMailsSoap soapModel = new AuditSendMailsSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setAuditSendMailsId(model.getAuditSendMailsId());
		soapModel.setUserId(model.getUserId());
		soapModel.setTemplateId(model.getTemplateId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setSendDate(model.getSendDate());

		return soapModel;
	}

	public static AuditSendMailsSoap[] toSoapModels(AuditSendMails[] models) {
		AuditSendMailsSoap[] soapModels = new AuditSendMailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AuditSendMailsSoap[][] toSoapModels(AuditSendMails[][] models) {
		AuditSendMailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AuditSendMailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AuditSendMailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AuditSendMailsSoap[] toSoapModels(List<AuditSendMails> models) {
		List<AuditSendMailsSoap> soapModels = new ArrayList<AuditSendMailsSoap>(models.size());

		for (AuditSendMails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AuditSendMailsSoap[soapModels.size()]);
	}

	public AuditSendMailsSoap() {
	}

	public long getPrimaryKey() {
		return _auditSendMailsId;
	}

	public void setPrimaryKey(long pk) {
		setAuditSendMailsId(pk);
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

	private String _uuid;
	private long _auditSendMailsId;
	private long _userId;
	private long _templateId;
	private long _groupId;
	private Date _sendDate;
}