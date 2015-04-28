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

package com.tls.liferaylms.mail.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MailJobLocalService}.
 * </p>
 *
 * @author    je03042
 * @see       MailJobLocalService
 * @generated
 */
public class MailJobLocalServiceWrapper implements MailJobLocalService,
	ServiceWrapper<MailJobLocalService> {
	public MailJobLocalServiceWrapper(MailJobLocalService mailJobLocalService) {
		_mailJobLocalService = mailJobLocalService;
	}

	/**
	* Adds the mail job to the database. Also notifies the appropriate model listeners.
	*
	* @param mailJob the mail job
	* @return the mail job that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob addMailJob(
		com.tls.liferaylms.mail.model.MailJob mailJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailJobLocalService.addMailJob(mailJob);
	}

	/**
	* Creates a new mail job with the primary key. Does not add the mail job to the database.
	*
	* @param idJob the primary key for the new mail job
	* @return the new mail job
	*/
	public com.tls.liferaylms.mail.model.MailJob createMailJob(long idJob) {
		return _mailJobLocalService.createMailJob(idJob);
	}

	/**
	* Deletes the mail job with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param idJob the primary key of the mail job
	* @return the mail job that was removed
	* @throws PortalException if a mail job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob deleteMailJob(long idJob)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mailJobLocalService.deleteMailJob(idJob);
	}

	/**
	* Deletes the mail job from the database. Also notifies the appropriate model listeners.
	*
	* @param mailJob the mail job
	* @return the mail job that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob deleteMailJob(
		com.tls.liferaylms.mail.model.MailJob mailJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailJobLocalService.deleteMailJob(mailJob);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _mailJobLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailJobLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _mailJobLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailJobLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailJobLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.tls.liferaylms.mail.model.MailJob fetchMailJob(long idJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailJobLocalService.fetchMailJob(idJob);
	}

	/**
	* Returns the mail job with the primary key.
	*
	* @param idJob the primary key of the mail job
	* @return the mail job
	* @throws PortalException if a mail job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob getMailJob(long idJob)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mailJobLocalService.getMailJob(idJob);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mailJobLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the mail job with the UUID in the group.
	*
	* @param uuid the UUID of mail job
	* @param groupId the group id of the mail job
	* @return the mail job
	* @throws PortalException if a mail job with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob getMailJobByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mailJobLocalService.getMailJobByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the mail jobs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of mail jobs
	* @param end the upper bound of the range of mail jobs (not inclusive)
	* @return the range of mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.MailJob> getMailJobs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailJobLocalService.getMailJobs(start, end);
	}

	/**
	* Returns the number of mail jobs.
	*
	* @return the number of mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public int getMailJobsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailJobLocalService.getMailJobsCount();
	}

	/**
	* Updates the mail job in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mailJob the mail job
	* @return the mail job that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob updateMailJob(
		com.tls.liferaylms.mail.model.MailJob mailJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailJobLocalService.updateMailJob(mailJob);
	}

	/**
	* Updates the mail job in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mailJob the mail job
	* @param merge whether to merge the mail job with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the mail job that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob updateMailJob(
		com.tls.liferaylms.mail.model.MailJob mailJob, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailJobLocalService.updateMailJob(mailJob, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _mailJobLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_mailJobLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _mailJobLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.tls.liferaylms.mail.model.MailJob addMailJob(
		java.lang.Long template, java.lang.String conditionClassName,
		java.lang.Long conditionClassPK, java.lang.String conditionStatus,
		java.lang.String dateClassName, java.lang.Long dateClassPK,
		java.lang.Long dateShift, java.lang.Long referenceState,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mailJobLocalService.addMailJob(template, conditionClassName,
			conditionClassPK, conditionStatus, dateClassName, dateClassPK,
			dateShift, referenceState, serviceContext);
	}

	public java.util.List<com.tls.liferaylms.mail.model.MailJob> getNotProcessedMailJobs() {
		return _mailJobLocalService.getNotProcessedMailJobs();
	}

	public java.util.List<com.tls.liferaylms.mail.model.MailJob> getMailJobsInGroupId(
		java.lang.Long groupId, int start, int end) {
		return _mailJobLocalService.getMailJobsInGroupId(groupId, start, end);
	}

	public java.lang.Integer countByGroup(java.lang.Long groupId) {
		return _mailJobLocalService.countByGroup(groupId);
	}

	public java.util.List<com.tls.liferaylms.mail.model.MailJob> getMailJobsInGroupIdAndProcessed(
		java.lang.Long groupId, boolean processed, int start, int end) {
		return _mailJobLocalService.getMailJobsInGroupIdAndProcessed(groupId,
			processed, start, end);
	}

	public java.lang.Integer countByGroupAndProcessed(java.lang.Long groupId,
		boolean processed) {
		return _mailJobLocalService.countByGroupAndProcessed(groupId, processed);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public MailJobLocalService getWrappedMailJobLocalService() {
		return _mailJobLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedMailJobLocalService(
		MailJobLocalService mailJobLocalService) {
		_mailJobLocalService = mailJobLocalService;
	}

	public MailJobLocalService getWrappedService() {
		return _mailJobLocalService;
	}

	public void setWrappedService(MailJobLocalService mailJobLocalService) {
		_mailJobLocalService = mailJobLocalService;
	}

	private MailJobLocalService _mailJobLocalService;
}