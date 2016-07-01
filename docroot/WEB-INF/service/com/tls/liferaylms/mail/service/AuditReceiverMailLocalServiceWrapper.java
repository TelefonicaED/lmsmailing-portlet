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
 * This class is a wrapper for {@link AuditReceiverMailLocalService}.
 * </p>
 *
 * @author    je03042
 * @see       AuditReceiverMailLocalService
 * @generated
 */
public class AuditReceiverMailLocalServiceWrapper
	implements AuditReceiverMailLocalService,
		ServiceWrapper<AuditReceiverMailLocalService> {
	public AuditReceiverMailLocalServiceWrapper(
		AuditReceiverMailLocalService auditReceiverMailLocalService) {
		_auditReceiverMailLocalService = auditReceiverMailLocalService;
	}

	/**
	* Adds the audit receiver mail to the database. Also notifies the appropriate model listeners.
	*
	* @param auditReceiverMail the audit receiver mail
	* @return the audit receiver mail that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditReceiverMail addAuditReceiverMail(
		com.tls.liferaylms.mail.model.AuditReceiverMail auditReceiverMail)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditReceiverMailLocalService.addAuditReceiverMail(auditReceiverMail);
	}

	/**
	* Creates a new audit receiver mail with the primary key. Does not add the audit receiver mail to the database.
	*
	* @param auditReceiverMailId the primary key for the new audit receiver mail
	* @return the new audit receiver mail
	*/
	public com.tls.liferaylms.mail.model.AuditReceiverMail createAuditReceiverMail(
		long auditReceiverMailId) {
		return _auditReceiverMailLocalService.createAuditReceiverMail(auditReceiverMailId);
	}

	/**
	* Deletes the audit receiver mail with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditReceiverMailId the primary key of the audit receiver mail
	* @return the audit receiver mail that was removed
	* @throws PortalException if a audit receiver mail with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditReceiverMail deleteAuditReceiverMail(
		long auditReceiverMailId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _auditReceiverMailLocalService.deleteAuditReceiverMail(auditReceiverMailId);
	}

	/**
	* Deletes the audit receiver mail from the database. Also notifies the appropriate model listeners.
	*
	* @param auditReceiverMail the audit receiver mail
	* @return the audit receiver mail that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditReceiverMail deleteAuditReceiverMail(
		com.tls.liferaylms.mail.model.AuditReceiverMail auditReceiverMail)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditReceiverMailLocalService.deleteAuditReceiverMail(auditReceiverMail);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _auditReceiverMailLocalService.dynamicQuery();
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
		return _auditReceiverMailLocalService.dynamicQuery(dynamicQuery);
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
		return _auditReceiverMailLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
		return _auditReceiverMailLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _auditReceiverMailLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.tls.liferaylms.mail.model.AuditReceiverMail fetchAuditReceiverMail(
		long auditReceiverMailId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditReceiverMailLocalService.fetchAuditReceiverMail(auditReceiverMailId);
	}

	/**
	* Returns the audit receiver mail with the primary key.
	*
	* @param auditReceiverMailId the primary key of the audit receiver mail
	* @return the audit receiver mail
	* @throws PortalException if a audit receiver mail with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditReceiverMail getAuditReceiverMail(
		long auditReceiverMailId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _auditReceiverMailLocalService.getAuditReceiverMail(auditReceiverMailId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _auditReceiverMailLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the audit receiver mails.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of audit receiver mails
	* @param end the upper bound of the range of audit receiver mails (not inclusive)
	* @return the range of audit receiver mails
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.AuditReceiverMail> getAuditReceiverMails(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditReceiverMailLocalService.getAuditReceiverMails(start, end);
	}

	/**
	* Returns the number of audit receiver mails.
	*
	* @return the number of audit receiver mails
	* @throws SystemException if a system exception occurred
	*/
	public int getAuditReceiverMailsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditReceiverMailLocalService.getAuditReceiverMailsCount();
	}

	/**
	* Updates the audit receiver mail in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param auditReceiverMail the audit receiver mail
	* @return the audit receiver mail that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditReceiverMail updateAuditReceiverMail(
		com.tls.liferaylms.mail.model.AuditReceiverMail auditReceiverMail)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditReceiverMailLocalService.updateAuditReceiverMail(auditReceiverMail);
	}

	/**
	* Updates the audit receiver mail in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param auditReceiverMail the audit receiver mail
	* @param merge whether to merge the audit receiver mail with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the audit receiver mail that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditReceiverMail updateAuditReceiverMail(
		com.tls.liferaylms.mail.model.AuditReceiverMail auditReceiverMail,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditReceiverMailLocalService.updateAuditReceiverMail(auditReceiverMail,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _auditReceiverMailLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_auditReceiverMailLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _auditReceiverMailLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public java.util.List<com.tls.liferaylms.mail.model.AuditReceiverMail> getRecieverMailsBySendMail(
		long auditSendMailsId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditReceiverMailLocalService.getRecieverMailsBySendMail(auditSendMailsId,
			start, end);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AuditReceiverMailLocalService getWrappedAuditReceiverMailLocalService() {
		return _auditReceiverMailLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAuditReceiverMailLocalService(
		AuditReceiverMailLocalService auditReceiverMailLocalService) {
		_auditReceiverMailLocalService = auditReceiverMailLocalService;
	}

	public AuditReceiverMailLocalService getWrappedService() {
		return _auditReceiverMailLocalService;
	}

	public void setWrappedService(
		AuditReceiverMailLocalService auditReceiverMailLocalService) {
		_auditReceiverMailLocalService = auditReceiverMailLocalService;
	}

	private AuditReceiverMailLocalService _auditReceiverMailLocalService;
}