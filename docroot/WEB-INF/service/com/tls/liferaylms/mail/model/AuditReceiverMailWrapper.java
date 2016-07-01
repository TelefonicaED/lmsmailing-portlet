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
 * This class is a wrapper for {@link AuditReceiverMail}.
 * </p>
 *
 * @author    je03042
 * @see       AuditReceiverMail
 * @generated
 */
public class AuditReceiverMailWrapper implements AuditReceiverMail,
	ModelWrapper<AuditReceiverMail> {
	public AuditReceiverMailWrapper(AuditReceiverMail auditReceiverMail) {
		_auditReceiverMail = auditReceiverMail;
	}

	public Class<?> getModelClass() {
		return AuditReceiverMail.class;
	}

	public String getModelClassName() {
		return AuditReceiverMail.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("auditReceiverMailId", getAuditReceiverMailId());
		attributes.put("auditSendMailsId", getAuditSendMailsId());
		attributes.put("to", getTo());
		attributes.put("status", getStatus());

		return attributes;
	}

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

	/**
	* Returns the primary key of this audit receiver mail.
	*
	* @return the primary key of this audit receiver mail
	*/
	public long getPrimaryKey() {
		return _auditReceiverMail.getPrimaryKey();
	}

	/**
	* Sets the primary key of this audit receiver mail.
	*
	* @param primaryKey the primary key of this audit receiver mail
	*/
	public void setPrimaryKey(long primaryKey) {
		_auditReceiverMail.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the audit receiver mail ID of this audit receiver mail.
	*
	* @return the audit receiver mail ID of this audit receiver mail
	*/
	public long getAuditReceiverMailId() {
		return _auditReceiverMail.getAuditReceiverMailId();
	}

	/**
	* Sets the audit receiver mail ID of this audit receiver mail.
	*
	* @param auditReceiverMailId the audit receiver mail ID of this audit receiver mail
	*/
	public void setAuditReceiverMailId(long auditReceiverMailId) {
		_auditReceiverMail.setAuditReceiverMailId(auditReceiverMailId);
	}

	/**
	* Returns the audit send mails ID of this audit receiver mail.
	*
	* @return the audit send mails ID of this audit receiver mail
	*/
	public long getAuditSendMailsId() {
		return _auditReceiverMail.getAuditSendMailsId();
	}

	/**
	* Sets the audit send mails ID of this audit receiver mail.
	*
	* @param auditSendMailsId the audit send mails ID of this audit receiver mail
	*/
	public void setAuditSendMailsId(long auditSendMailsId) {
		_auditReceiverMail.setAuditSendMailsId(auditSendMailsId);
	}

	/**
	* Returns the to of this audit receiver mail.
	*
	* @return the to of this audit receiver mail
	*/
	public java.lang.String getTo() {
		return _auditReceiverMail.getTo();
	}

	/**
	* Sets the to of this audit receiver mail.
	*
	* @param to the to of this audit receiver mail
	*/
	public void setTo(java.lang.String to) {
		_auditReceiverMail.setTo(to);
	}

	/**
	* Returns the status of this audit receiver mail.
	*
	* @return the status of this audit receiver mail
	*/
	public java.lang.Integer getStatus() {
		return _auditReceiverMail.getStatus();
	}

	/**
	* Sets the status of this audit receiver mail.
	*
	* @param status the status of this audit receiver mail
	*/
	public void setStatus(java.lang.Integer status) {
		_auditReceiverMail.setStatus(status);
	}

	public boolean isNew() {
		return _auditReceiverMail.isNew();
	}

	public void setNew(boolean n) {
		_auditReceiverMail.setNew(n);
	}

	public boolean isCachedModel() {
		return _auditReceiverMail.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_auditReceiverMail.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _auditReceiverMail.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _auditReceiverMail.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_auditReceiverMail.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _auditReceiverMail.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_auditReceiverMail.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AuditReceiverMailWrapper((AuditReceiverMail)_auditReceiverMail.clone());
	}

	public int compareTo(
		com.tls.liferaylms.mail.model.AuditReceiverMail auditReceiverMail) {
		return _auditReceiverMail.compareTo(auditReceiverMail);
	}

	@Override
	public int hashCode() {
		return _auditReceiverMail.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.tls.liferaylms.mail.model.AuditReceiverMail> toCacheModel() {
		return _auditReceiverMail.toCacheModel();
	}

	public com.tls.liferaylms.mail.model.AuditReceiverMail toEscapedModel() {
		return new AuditReceiverMailWrapper(_auditReceiverMail.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _auditReceiverMail.toString();
	}

	public java.lang.String toXmlString() {
		return _auditReceiverMail.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_auditReceiverMail.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AuditReceiverMail getWrappedAuditReceiverMail() {
		return _auditReceiverMail;
	}

	public AuditReceiverMail getWrappedModel() {
		return _auditReceiverMail;
	}

	public void resetOriginalValues() {
		_auditReceiverMail.resetOriginalValues();
	}

	private AuditReceiverMail _auditReceiverMail;
}