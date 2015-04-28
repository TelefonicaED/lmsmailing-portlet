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

package com.tls.liferaylms.mail.service.impl;

import java.util.List;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactory;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.ServiceContext;
import com.tls.liferaylms.job.MailJobPortlet;
import com.tls.liferaylms.mail.model.MailJob;
import com.tls.liferaylms.mail.service.base.MailJobLocalServiceBaseImpl;
import com.tls.liferaylms.util.MailStringPool;

/**
 * The implementation of the mail job local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.tls.liferaylms.mail.service.MailJobLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author je03042
 * @see com.tls.liferaylms.mail.service.base.MailJobLocalServiceBaseImpl
 * @see com.tls.liferaylms.mail.service.MailJobLocalServiceUtil
 */
public class MailJobLocalServiceImpl extends MailJobLocalServiceBaseImpl {
	Log log = LogFactoryUtil.getLog(MailJobLocalServiceImpl.class);
	
	public MailJob addMailJob(Long template, String conditionClassName, Long conditionClassPK, String conditionStatus, String dateClassName, Long dateClassPK, Long dateShift, Long referenceState, ServiceContext serviceContext) throws SystemException, PortalException{
		MailJob mailJob = mailJobPersistence.create(counterLocalService.increment(MailJob.class.getName()));

		mailJob.setIdTemplate(template);
		mailJob.setUserId(serviceContext.getUserId());
		mailJob.setCompanyId(serviceContext.getCompanyId());
		mailJob.setGroupId(serviceContext.getScopeGroupId());
		mailJob.setConditionClassName(conditionClassName);
		mailJob.setConditionClassPK(conditionClassPK);
		mailJob.setConditionStatus(conditionStatus);
		mailJob.setDateClassName(dateClassName);
		mailJob.setDateClassPK(dateClassPK);
		mailJob.setDateShift(dateShift);
		mailJob.setDateReferenceDate(referenceState);
		
		return mailJobPersistence.update(mailJob, true);
	}
	
	public List<MailJob> getNotProcessedMailJobs(){
		
		try {
			return mailJobPersistence.findWithDynamicQuery(DynamicQueryFactoryUtil.forClass(MailJob.class).add(PropertyFactoryUtil.forName(MailStringPool.PROCESSED).eq(false)));
		} catch (SystemException e) {
			if(log.isDebugEnabled())e.printStackTrace();
			if(log.isErrorEnabled())log.error(e.getMessage());
		}
		
		return null;
	}
	
	public List<MailJob> getMailJobsInGroupId(Long groupId, int start, int end){
		try {
			return mailJobPersistence.findByg(groupId, start, end);
		} catch (SystemException e) {
			if(log.isDebugEnabled())e.printStackTrace();
			if(log.isErrorEnabled())log.error(e.getMessage());
			
			return null;
		}
	}
	
	public Integer countByGroup(Long groupId){
		try {
			return mailJobPersistence.countByg(groupId);
		} catch (SystemException e) {
			if(log.isDebugEnabled())e.printStackTrace();
			if(log.isErrorEnabled())log.error(e.getMessage());
			
			return 0;
		}
	}

	public List<MailJob> getMailJobsInGroupIdAndProcessed(Long groupId, boolean processed, int start, int end){
		try {
			return mailJobPersistence.findBygp(groupId, processed, start, end);
		} catch (SystemException e) {
			if(log.isDebugEnabled())e.printStackTrace();
			if(log.isErrorEnabled())log.error(e.getMessage());
			
			return null;
		}
	}

	public Integer countByGroupAndProcessed(Long groupId, boolean processed){
		try {
			return mailJobPersistence.countBygp(groupId, processed);
		} catch (SystemException e) {
			if(log.isDebugEnabled())e.printStackTrace();
			if(log.isErrorEnabled())log.error(e.getMessage());
			
			return 0;
		}
	}
}