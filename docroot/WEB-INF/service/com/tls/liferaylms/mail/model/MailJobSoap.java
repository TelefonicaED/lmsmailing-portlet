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
 * This class is used by SOAP remote services.
 *
 * @author    je03042
 * @generated
 */
public class MailJobSoap implements Serializable {
	public static MailJobSoap toSoapModel(MailJob model) {
		MailJobSoap soapModel = new MailJobSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setIdJob(model.getIdJob());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setIdTemplate(model.getIdTemplate());
		soapModel.setConditionClassName(model.getConditionClassName());
		soapModel.setConditionClassPK(model.getConditionClassPK());
		soapModel.setConditionStatus(model.getConditionStatus());
		soapModel.setDateClassName(model.getDateClassName());
		soapModel.setDateClassPK(model.getDateClassPK());
		soapModel.setDateReferenceDate(model.getDateReferenceDate());
		soapModel.setDateShift(model.getDateShift());
		soapModel.setTeamId(model.getTeamId());
		soapModel.setProcessed(model.getProcessed());

		return soapModel;
	}

	public static MailJobSoap[] toSoapModels(MailJob[] models) {
		MailJobSoap[] soapModels = new MailJobSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MailJobSoap[][] toSoapModels(MailJob[][] models) {
		MailJobSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MailJobSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MailJobSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MailJobSoap[] toSoapModels(List<MailJob> models) {
		List<MailJobSoap> soapModels = new ArrayList<MailJobSoap>(models.size());

		for (MailJob model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MailJobSoap[soapModels.size()]);
	}

	public MailJobSoap() {
	}

	public long getPrimaryKey() {
		return _idJob;
	}

	public void setPrimaryKey(long pk) {
		setIdJob(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getIdJob() {
		return _idJob;
	}

	public void setIdJob(long idJob) {
		_idJob = idJob;
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

	public long getIdTemplate() {
		return _idTemplate;
	}

	public void setIdTemplate(long idTemplate) {
		_idTemplate = idTemplate;
	}

	public String getConditionClassName() {
		return _conditionClassName;
	}

	public void setConditionClassName(String conditionClassName) {
		_conditionClassName = conditionClassName;
	}

	public long getConditionClassPK() {
		return _conditionClassPK;
	}

	public void setConditionClassPK(long conditionClassPK) {
		_conditionClassPK = conditionClassPK;
	}

	public String getConditionStatus() {
		return _conditionStatus;
	}

	public void setConditionStatus(String conditionStatus) {
		_conditionStatus = conditionStatus;
	}

	public String getDateClassName() {
		return _dateClassName;
	}

	public void setDateClassName(String dateClassName) {
		_dateClassName = dateClassName;
	}

	public long getDateClassPK() {
		return _dateClassPK;
	}

	public void setDateClassPK(long dateClassPK) {
		_dateClassPK = dateClassPK;
	}

	public long getDateReferenceDate() {
		return _dateReferenceDate;
	}

	public void setDateReferenceDate(long dateReferenceDate) {
		_dateReferenceDate = dateReferenceDate;
	}

	public long getDateShift() {
		return _dateShift;
	}

	public void setDateShift(long dateShift) {
		_dateShift = dateShift;
	}

	public long getTeamId() {
		return _teamId;
	}

	public void setTeamId(long teamId) {
		_teamId = teamId;
	}

	public boolean getProcessed() {
		return _processed;
	}

	public boolean isProcessed() {
		return _processed;
	}

	public void setProcessed(boolean processed) {
		_processed = processed;
	}

	private String _uuid;
	private long _idJob;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private long _idTemplate;
	private String _conditionClassName;
	private long _conditionClassPK;
	private String _conditionStatus;
	private String _dateClassName;
	private long _dateClassPK;
	private long _dateReferenceDate;
	private long _dateShift;
	private long _teamId;
	private boolean _processed;
}