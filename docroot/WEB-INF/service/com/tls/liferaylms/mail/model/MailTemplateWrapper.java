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

import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MailTemplate}.
 * </p>
 *
 * @author    je03042
 * @see       MailTemplate
 * @generated
 */
public class MailTemplateWrapper implements MailTemplate,
	ModelWrapper<MailTemplate> {
	public MailTemplateWrapper(MailTemplate mailTemplate) {
		_mailTemplate = mailTemplate;
	}

	public Class<?> getModelClass() {
		return MailTemplate.class;
	}

	public String getModelClassName() {
		return MailTemplate.class.getName();
	}

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

	/**
	* Returns the primary key of this mail template.
	*
	* @return the primary key of this mail template
	*/
	public long getPrimaryKey() {
		return _mailTemplate.getPrimaryKey();
	}

	/**
	* Sets the primary key of this mail template.
	*
	* @param primaryKey the primary key of this mail template
	*/
	public void setPrimaryKey(long primaryKey) {
		_mailTemplate.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this mail template.
	*
	* @return the uuid of this mail template
	*/
	public java.lang.String getUuid() {
		return _mailTemplate.getUuid();
	}

	/**
	* Sets the uuid of this mail template.
	*
	* @param uuid the uuid of this mail template
	*/
	public void setUuid(java.lang.String uuid) {
		_mailTemplate.setUuid(uuid);
	}

	/**
	* Returns the id template of this mail template.
	*
	* @return the id template of this mail template
	*/
	public long getIdTemplate() {
		return _mailTemplate.getIdTemplate();
	}

	/**
	* Sets the id template of this mail template.
	*
	* @param idTemplate the id template of this mail template
	*/
	public void setIdTemplate(long idTemplate) {
		_mailTemplate.setIdTemplate(idTemplate);
	}

	/**
	* Returns the company ID of this mail template.
	*
	* @return the company ID of this mail template
	*/
	public long getCompanyId() {
		return _mailTemplate.getCompanyId();
	}

	/**
	* Sets the company ID of this mail template.
	*
	* @param companyId the company ID of this mail template
	*/
	public void setCompanyId(long companyId) {
		_mailTemplate.setCompanyId(companyId);
	}

	/**
	* Returns the group ID of this mail template.
	*
	* @return the group ID of this mail template
	*/
	public long getGroupId() {
		return _mailTemplate.getGroupId();
	}

	/**
	* Sets the group ID of this mail template.
	*
	* @param groupId the group ID of this mail template
	*/
	public void setGroupId(long groupId) {
		_mailTemplate.setGroupId(groupId);
	}

	/**
	* Returns the user ID of this mail template.
	*
	* @return the user ID of this mail template
	*/
	public long getUserId() {
		return _mailTemplate.getUserId();
	}

	/**
	* Sets the user ID of this mail template.
	*
	* @param userId the user ID of this mail template
	*/
	public void setUserId(long userId) {
		_mailTemplate.setUserId(userId);
	}

	/**
	* Returns the user uuid of this mail template.
	*
	* @return the user uuid of this mail template
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailTemplate.getUserUuid();
	}

	/**
	* Sets the user uuid of this mail template.
	*
	* @param userUuid the user uuid of this mail template
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_mailTemplate.setUserUuid(userUuid);
	}

	/**
	* Returns the subject of this mail template.
	*
	* @return the subject of this mail template
	*/
	public java.lang.String getSubject() {
		return _mailTemplate.getSubject();
	}

	/**
	* Sets the subject of this mail template.
	*
	* @param subject the subject of this mail template
	*/
	public void setSubject(java.lang.String subject) {
		_mailTemplate.setSubject(subject);
	}

	/**
	* Returns the body of this mail template.
	*
	* @return the body of this mail template
	*/
	public java.lang.String getBody() {
		return _mailTemplate.getBody();
	}

	/**
	* Sets the body of this mail template.
	*
	* @param body the body of this mail template
	*/
	public void setBody(java.lang.String body) {
		_mailTemplate.setBody(body);
	}

	public boolean isNew() {
		return _mailTemplate.isNew();
	}

	public void setNew(boolean n) {
		_mailTemplate.setNew(n);
	}

	public boolean isCachedModel() {
		return _mailTemplate.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_mailTemplate.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _mailTemplate.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _mailTemplate.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_mailTemplate.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _mailTemplate.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_mailTemplate.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new MailTemplateWrapper((MailTemplate)_mailTemplate.clone());
	}

	public int compareTo(
		com.tls.liferaylms.mail.model.MailTemplate mailTemplate) {
		return _mailTemplate.compareTo(mailTemplate);
	}

	@Override
	public int hashCode() {
		return _mailTemplate.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.tls.liferaylms.mail.model.MailTemplate> toCacheModel() {
		return _mailTemplate.toCacheModel();
	}

	public com.tls.liferaylms.mail.model.MailTemplate toEscapedModel() {
		return new MailTemplateWrapper(_mailTemplate.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _mailTemplate.toString();
	}

	public java.lang.String toXmlString() {
		return _mailTemplate.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_mailTemplate.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public MailTemplate getWrappedMailTemplate() {
		return _mailTemplate;
	}

	public MailTemplate getWrappedModel() {
		return _mailTemplate;
	}

	public void resetOriginalValues() {
		_mailTemplate.resetOriginalValues();
	}

	private MailTemplate _mailTemplate;
}