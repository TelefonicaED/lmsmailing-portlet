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
 * This class is used by SOAP remote services, specifically {@link com.tls.liferaylms.mail.service.http.AuditReceiverMailServiceSoap}.
 *
 * @author    je03042
 * @see       com.tls.liferaylms.mail.service.http.AuditReceiverMailServiceSoap
 * @generated
 */
public class AuditReceiverMailSoap implements Serializable {
	public static AuditReceiverMailSoap toSoapModel(AuditReceiverMail model) {
		AuditReceiverMailSoap soapModel = new AuditReceiverMailSoap();

		soapModel.setAuditReceiverMailId(model.getAuditReceiverMailId());
		soapModel.setAuditSendMailsId(model.getAuditSendMailsId());
		soapModel.setTo(model.getTo());
		soapModel.setStatus(model.getStatus());
		soapModel.setSendDate(model.getSendDate());

		return soapModel;
	}

	public static AuditReceiverMailSoap[] toSoapModels(
		AuditReceiverMail[] models) {
		AuditReceiverMailSoap[] soapModels = new AuditReceiverMailSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AuditReceiverMailSoap[][] toSoapModels(
		AuditReceiverMail[][] models) {
		AuditReceiverMailSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AuditReceiverMailSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AuditReceiverMailSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AuditReceiverMailSoap[] toSoapModels(
		List<AuditReceiverMail> models) {
		List<AuditReceiverMailSoap> soapModels = new ArrayList<AuditReceiverMailSoap>(models.size());

		for (AuditReceiverMail model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AuditReceiverMailSoap[soapModels.size()]);
	}

	public AuditReceiverMailSoap() {
	}

	public long getPrimaryKey() {
		return _auditReceiverMailId;
	}

	public void setPrimaryKey(long pk) {
		setAuditReceiverMailId(pk);
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

	public Date getSendDate() {
		return _sendDate;
	}

	public void setSendDate(Date sendDate) {
		_sendDate = sendDate;
	}

	private long _auditReceiverMailId;
	private long _auditSendMailsId;
	private String _to;
	private Integer _status;
	private Date _sendDate;
}