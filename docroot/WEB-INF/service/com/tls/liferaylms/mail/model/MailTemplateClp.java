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

import com.tls.liferaylms.mail.service.MailTemplateLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author je03042
 */
public class MailTemplateClp extends BaseModelImpl<MailTemplate>
	implements MailTemplate {
	public MailTemplateClp() {
	}

	public Class<?> getModelClass() {
		return MailTemplate.class;
	}

	public String getModelClassName() {
		return MailTemplate.class.getName();
	}

	public long getPrimaryKey() {
		return _idTemplate;
	}

	public void setPrimaryKey(long primaryKey) {
		setIdTemplate(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_idTemplate);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("idTemplate", getIdTemplate());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("subject", getSubject());
		attributes.put("body", getBody());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long idTemplate = (Long)attributes.get("idTemplate");

		if (idTemplate != null) {
			setIdTemplate(idTemplate);
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

		String subject = (String)attributes.get("subject");

		if (subject != null) {
			setSubject(subject);
		}

		String body = (String)attributes.get("body");

		if (body != null) {
			setBody(body);
		}
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

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
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

	public BaseModel<?> getMailTemplateRemoteModel() {
		return _mailTemplateRemoteModel;
	}

	public void setMailTemplateRemoteModel(BaseModel<?> mailTemplateRemoteModel) {
		_mailTemplateRemoteModel = mailTemplateRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			MailTemplateLocalServiceUtil.addMailTemplate(this);
		}
		else {
			MailTemplateLocalServiceUtil.updateMailTemplate(this);
		}
	}

	@Override
	public MailTemplate toEscapedModel() {
		return (MailTemplate)Proxy.newProxyInstance(MailTemplate.class.getClassLoader(),
			new Class[] { MailTemplate.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		MailTemplateClp clone = new MailTemplateClp();

		clone.setUuid(getUuid());
		clone.setIdTemplate(getIdTemplate());
		clone.setCompanyId(getCompanyId());
		clone.setGroupId(getGroupId());
		clone.setUserId(getUserId());
		clone.setSubject(getSubject());
		clone.setBody(getBody());

		return clone;
	}

	public int compareTo(MailTemplate mailTemplate) {
		int value = 0;

		value = getSubject().compareTo(mailTemplate.getSubject());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		MailTemplateClp mailTemplate = null;

		try {
			mailTemplate = (MailTemplateClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = mailTemplate.getPrimaryKey();

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
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", idTemplate=");
		sb.append(getIdTemplate());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", subject=");
		sb.append(getSubject());
		sb.append(", body=");
		sb.append(getBody());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.tls.liferaylms.mail.model.MailTemplate");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>idTemplate</column-name><column-value><![CDATA[");
		sb.append(getIdTemplate());
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
			"<column><column-name>subject</column-name><column-value><![CDATA[");
		sb.append(getSubject());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>body</column-name><column-value><![CDATA[");
		sb.append(getBody());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _idTemplate;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userUuid;
	private String _subject;
	private String _body;
	private BaseModel<?> _mailTemplateRemoteModel;
}