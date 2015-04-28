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
 * This class is a wrapper for {@link MailJob}.
 * </p>
 *
 * @author    je03042
 * @see       MailJob
 * @generated
 */
public class MailJobWrapper implements MailJob, ModelWrapper<MailJob> {
	public MailJobWrapper(MailJob mailJob) {
		_mailJob = mailJob;
	}

	public Class<?> getModelClass() {
		return MailJob.class;
	}

	public String getModelClassName() {
		return MailJob.class.getName();
	}

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

	/**
	* Returns the primary key of this mail job.
	*
	* @return the primary key of this mail job
	*/
	public long getPrimaryKey() {
		return _mailJob.getPrimaryKey();
	}

	/**
	* Sets the primary key of this mail job.
	*
	* @param primaryKey the primary key of this mail job
	*/
	public void setPrimaryKey(long primaryKey) {
		_mailJob.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this mail job.
	*
	* @return the uuid of this mail job
	*/
	public java.lang.String getUuid() {
		return _mailJob.getUuid();
	}

	/**
	* Sets the uuid of this mail job.
	*
	* @param uuid the uuid of this mail job
	*/
	public void setUuid(java.lang.String uuid) {
		_mailJob.setUuid(uuid);
	}

	/**
	* Returns the id job of this mail job.
	*
	* @return the id job of this mail job
	*/
	public long getIdJob() {
		return _mailJob.getIdJob();
	}

	/**
	* Sets the id job of this mail job.
	*
	* @param idJob the id job of this mail job
	*/
	public void setIdJob(long idJob) {
		_mailJob.setIdJob(idJob);
	}

	/**
	* Returns the company ID of this mail job.
	*
	* @return the company ID of this mail job
	*/
	public long getCompanyId() {
		return _mailJob.getCompanyId();
	}

	/**
	* Sets the company ID of this mail job.
	*
	* @param companyId the company ID of this mail job
	*/
	public void setCompanyId(long companyId) {
		_mailJob.setCompanyId(companyId);
	}

	/**
	* Returns the group ID of this mail job.
	*
	* @return the group ID of this mail job
	*/
	public long getGroupId() {
		return _mailJob.getGroupId();
	}

	/**
	* Sets the group ID of this mail job.
	*
	* @param groupId the group ID of this mail job
	*/
	public void setGroupId(long groupId) {
		_mailJob.setGroupId(groupId);
	}

	/**
	* Returns the user ID of this mail job.
	*
	* @return the user ID of this mail job
	*/
	public long getUserId() {
		return _mailJob.getUserId();
	}

	/**
	* Sets the user ID of this mail job.
	*
	* @param userId the user ID of this mail job
	*/
	public void setUserId(long userId) {
		_mailJob.setUserId(userId);
	}

	/**
	* Returns the user uuid of this mail job.
	*
	* @return the user uuid of this mail job
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailJob.getUserUuid();
	}

	/**
	* Sets the user uuid of this mail job.
	*
	* @param userUuid the user uuid of this mail job
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_mailJob.setUserUuid(userUuid);
	}

	/**
	* Returns the id template of this mail job.
	*
	* @return the id template of this mail job
	*/
	public long getIdTemplate() {
		return _mailJob.getIdTemplate();
	}

	/**
	* Sets the id template of this mail job.
	*
	* @param idTemplate the id template of this mail job
	*/
	public void setIdTemplate(long idTemplate) {
		_mailJob.setIdTemplate(idTemplate);
	}

	/**
	* Returns the condition class name of this mail job.
	*
	* @return the condition class name of this mail job
	*/
	public java.lang.String getConditionClassName() {
		return _mailJob.getConditionClassName();
	}

	/**
	* Sets the condition class name of this mail job.
	*
	* @param conditionClassName the condition class name of this mail job
	*/
	public void setConditionClassName(java.lang.String conditionClassName) {
		_mailJob.setConditionClassName(conditionClassName);
	}

	/**
	* Returns the condition class p k of this mail job.
	*
	* @return the condition class p k of this mail job
	*/
	public long getConditionClassPK() {
		return _mailJob.getConditionClassPK();
	}

	/**
	* Sets the condition class p k of this mail job.
	*
	* @param conditionClassPK the condition class p k of this mail job
	*/
	public void setConditionClassPK(long conditionClassPK) {
		_mailJob.setConditionClassPK(conditionClassPK);
	}

	/**
	* Returns the condition status of this mail job.
	*
	* @return the condition status of this mail job
	*/
	public java.lang.String getConditionStatus() {
		return _mailJob.getConditionStatus();
	}

	/**
	* Sets the condition status of this mail job.
	*
	* @param conditionStatus the condition status of this mail job
	*/
	public void setConditionStatus(java.lang.String conditionStatus) {
		_mailJob.setConditionStatus(conditionStatus);
	}

	/**
	* Returns the date class name of this mail job.
	*
	* @return the date class name of this mail job
	*/
	public java.lang.String getDateClassName() {
		return _mailJob.getDateClassName();
	}

	/**
	* Sets the date class name of this mail job.
	*
	* @param dateClassName the date class name of this mail job
	*/
	public void setDateClassName(java.lang.String dateClassName) {
		_mailJob.setDateClassName(dateClassName);
	}

	/**
	* Returns the date class p k of this mail job.
	*
	* @return the date class p k of this mail job
	*/
	public long getDateClassPK() {
		return _mailJob.getDateClassPK();
	}

	/**
	* Sets the date class p k of this mail job.
	*
	* @param dateClassPK the date class p k of this mail job
	*/
	public void setDateClassPK(long dateClassPK) {
		_mailJob.setDateClassPK(dateClassPK);
	}

	/**
	* Returns the date reference date of this mail job.
	*
	* @return the date reference date of this mail job
	*/
	public long getDateReferenceDate() {
		return _mailJob.getDateReferenceDate();
	}

	/**
	* Sets the date reference date of this mail job.
	*
	* @param dateReferenceDate the date reference date of this mail job
	*/
	public void setDateReferenceDate(long dateReferenceDate) {
		_mailJob.setDateReferenceDate(dateReferenceDate);
	}

	/**
	* Returns the date shift of this mail job.
	*
	* @return the date shift of this mail job
	*/
	public long getDateShift() {
		return _mailJob.getDateShift();
	}

	/**
	* Sets the date shift of this mail job.
	*
	* @param dateShift the date shift of this mail job
	*/
	public void setDateShift(long dateShift) {
		_mailJob.setDateShift(dateShift);
	}

	/**
	* Returns the team ID of this mail job.
	*
	* @return the team ID of this mail job
	*/
	public long getTeamId() {
		return _mailJob.getTeamId();
	}

	/**
	* Sets the team ID of this mail job.
	*
	* @param teamId the team ID of this mail job
	*/
	public void setTeamId(long teamId) {
		_mailJob.setTeamId(teamId);
	}

	/**
	* Returns the processed of this mail job.
	*
	* @return the processed of this mail job
	*/
	public boolean getProcessed() {
		return _mailJob.getProcessed();
	}

	/**
	* Returns <code>true</code> if this mail job is processed.
	*
	* @return <code>true</code> if this mail job is processed; <code>false</code> otherwise
	*/
	public boolean isProcessed() {
		return _mailJob.isProcessed();
	}

	/**
	* Sets whether this mail job is processed.
	*
	* @param processed the processed of this mail job
	*/
	public void setProcessed(boolean processed) {
		_mailJob.setProcessed(processed);
	}

	public boolean isNew() {
		return _mailJob.isNew();
	}

	public void setNew(boolean n) {
		_mailJob.setNew(n);
	}

	public boolean isCachedModel() {
		return _mailJob.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_mailJob.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _mailJob.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _mailJob.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_mailJob.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _mailJob.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_mailJob.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new MailJobWrapper((MailJob)_mailJob.clone());
	}

	public int compareTo(com.tls.liferaylms.mail.model.MailJob mailJob) {
		return _mailJob.compareTo(mailJob);
	}

	@Override
	public int hashCode() {
		return _mailJob.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.tls.liferaylms.mail.model.MailJob> toCacheModel() {
		return _mailJob.toCacheModel();
	}

	public com.tls.liferaylms.mail.model.MailJob toEscapedModel() {
		return new MailJobWrapper(_mailJob.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _mailJob.toString();
	}

	public java.lang.String toXmlString() {
		return _mailJob.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_mailJob.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public MailJob getWrappedMailJob() {
		return _mailJob;
	}

	public MailJob getWrappedModel() {
		return _mailJob;
	}

	public void resetOriginalValues() {
		_mailJob.resetOriginalValues();
	}

	private MailJob _mailJob;
}