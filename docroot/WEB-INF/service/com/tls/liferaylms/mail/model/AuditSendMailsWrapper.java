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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AuditSendMails}.
 * </p>
 *
 * @author    je03042
 * @see       AuditSendMails
 * @generated
 */
public class AuditSendMailsWrapper implements AuditSendMails,
	ModelWrapper<AuditSendMails> {
	public AuditSendMailsWrapper(AuditSendMails auditSendMails) {
		_auditSendMails = auditSendMails;
	}

	public Class<?> getModelClass() {
		return AuditSendMails.class;
	}

	public String getModelClassName() {
		return AuditSendMails.class.getName();
	}

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

	/**
	* Returns the primary key of this audit send mails.
	*
	* @return the primary key of this audit send mails
	*/
	public long getPrimaryKey() {
		return _auditSendMails.getPrimaryKey();
	}

	/**
	* Sets the primary key of this audit send mails.
	*
	* @param primaryKey the primary key of this audit send mails
	*/
	public void setPrimaryKey(long primaryKey) {
		_auditSendMails.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this audit send mails.
	*
	* @return the uuid of this audit send mails
	*/
	public java.lang.String getUuid() {
		return _auditSendMails.getUuid();
	}

	/**
	* Sets the uuid of this audit send mails.
	*
	* @param uuid the uuid of this audit send mails
	*/
	public void setUuid(java.lang.String uuid) {
		_auditSendMails.setUuid(uuid);
	}

	/**
	* Returns the audit send mails ID of this audit send mails.
	*
	* @return the audit send mails ID of this audit send mails
	*/
	public long getAuditSendMailsId() {
		return _auditSendMails.getAuditSendMailsId();
	}

	/**
	* Sets the audit send mails ID of this audit send mails.
	*
	* @param auditSendMailsId the audit send mails ID of this audit send mails
	*/
	public void setAuditSendMailsId(long auditSendMailsId) {
		_auditSendMails.setAuditSendMailsId(auditSendMailsId);
	}

	/**
	* Returns the user ID of this audit send mails.
	*
	* @return the user ID of this audit send mails
	*/
	public long getUserId() {
		return _auditSendMails.getUserId();
	}

	/**
	* Sets the user ID of this audit send mails.
	*
	* @param userId the user ID of this audit send mails
	*/
	public void setUserId(long userId) {
		_auditSendMails.setUserId(userId);
	}

	/**
	* Returns the user uuid of this audit send mails.
	*
	* @return the user uuid of this audit send mails
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditSendMails.getUserUuid();
	}

	/**
	* Sets the user uuid of this audit send mails.
	*
	* @param userUuid the user uuid of this audit send mails
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_auditSendMails.setUserUuid(userUuid);
	}

	/**
	* Returns the template ID of this audit send mails.
	*
	* @return the template ID of this audit send mails
	*/
	public long getTemplateId() {
		return _auditSendMails.getTemplateId();
	}

	/**
	* Sets the template ID of this audit send mails.
	*
	* @param templateId the template ID of this audit send mails
	*/
	public void setTemplateId(long templateId) {
		_auditSendMails.setTemplateId(templateId);
	}

	/**
	* Returns the group ID of this audit send mails.
	*
	* @return the group ID of this audit send mails
	*/
	public long getGroupId() {
		return _auditSendMails.getGroupId();
	}

	/**
	* Sets the group ID of this audit send mails.
	*
	* @param groupId the group ID of this audit send mails
	*/
	public void setGroupId(long groupId) {
		_auditSendMails.setGroupId(groupId);
	}

	/**
	* Returns the send date of this audit send mails.
	*
	* @return the send date of this audit send mails
	*/
	public java.util.Date getSendDate() {
		return _auditSendMails.getSendDate();
	}

	/**
	* Sets the send date of this audit send mails.
	*
	* @param sendDate the send date of this audit send mails
	*/
	public void setSendDate(java.util.Date sendDate) {
		_auditSendMails.setSendDate(sendDate);
	}

	public boolean isNew() {
		return _auditSendMails.isNew();
	}

	public void setNew(boolean n) {
		_auditSendMails.setNew(n);
	}

	public boolean isCachedModel() {
		return _auditSendMails.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_auditSendMails.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _auditSendMails.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _auditSendMails.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_auditSendMails.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _auditSendMails.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_auditSendMails.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AuditSendMailsWrapper((AuditSendMails)_auditSendMails.clone());
	}

	public int compareTo(
		com.tls.liferaylms.mail.model.AuditSendMails auditSendMails) {
		return _auditSendMails.compareTo(auditSendMails);
	}

	@Override
	public int hashCode() {
		return _auditSendMails.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.tls.liferaylms.mail.model.AuditSendMails> toCacheModel() {
		return _auditSendMails.toCacheModel();
	}

	public com.tls.liferaylms.mail.model.AuditSendMails toEscapedModel() {
		return new AuditSendMailsWrapper(_auditSendMails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _auditSendMails.toString();
	}

	public java.lang.String toXmlString() {
		return _auditSendMails.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_auditSendMails.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AuditSendMails getWrappedAuditSendMails() {
		return _auditSendMails;
	}

	public AuditSendMails getWrappedModel() {
		return _auditSendMails;
	}

	public void resetOriginalValues() {
		_auditSendMails.resetOriginalValues();
	}

	private AuditSendMails _auditSendMails;
}