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
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.tls.liferaylms.mail.service.http.MailTemplateServiceSoap}.
 *
 * @author    je03042
 * @see       com.tls.liferaylms.mail.service.http.MailTemplateServiceSoap
 * @generated
 */
public class MailTemplateSoap implements Serializable {
	public static MailTemplateSoap toSoapModel(MailTemplate model) {
		MailTemplateSoap soapModel = new MailTemplateSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setIdTemplate(model.getIdTemplate());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setSubject(model.getSubject());
		soapModel.setBody(model.getBody());

		return soapModel;
	}

	public static MailTemplateSoap[] toSoapModels(MailTemplate[] models) {
		MailTemplateSoap[] soapModels = new MailTemplateSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MailTemplateSoap[][] toSoapModels(MailTemplate[][] models) {
		MailTemplateSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MailTemplateSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MailTemplateSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MailTemplateSoap[] toSoapModels(List<MailTemplate> models) {
		List<MailTemplateSoap> soapModels = new ArrayList<MailTemplateSoap>(models.size());

		for (MailTemplate model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MailTemplateSoap[soapModels.size()]);
	}

	public MailTemplateSoap() {
	}

	public long getPrimaryKey() {
		return _idTemplate;
	}

	public void setPrimaryKey(long pk) {
		setIdTemplate(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getIdTemplate() {
		return _idTemplate;
	}

	public void setIdTemplate(long idTemplate) {
		_idTemplate = idTemplate;
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

	public String getSubject() {
		return _subject;
	}

	public void setSubject(String subject) {
		_subject = subject;
	}

	public String getBody() {
		return _body;
	}

	public void setBody(String body) {
		_body = body;
	}

	private String _uuid;
	private long _idTemplate;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _subject;
	private String _body;
}