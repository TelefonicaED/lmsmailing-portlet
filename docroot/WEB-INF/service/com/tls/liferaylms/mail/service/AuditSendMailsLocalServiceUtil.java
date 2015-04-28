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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the audit send mails local service. This utility wraps {@link com.tls.liferaylms.mail.service.impl.AuditSendMailsLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author je03042
 * @see AuditSendMailsLocalService
 * @see com.tls.liferaylms.mail.service.base.AuditSendMailsLocalServiceBaseImpl
 * @see com.tls.liferaylms.mail.service.impl.AuditSendMailsLocalServiceImpl
 * @generated
 */
public class AuditSendMailsLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.tls.liferaylms.mail.service.impl.AuditSendMailsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the audit send mails to the database. Also notifies the appropriate model listeners.
	*
	* @param auditSendMails the audit send mails
	* @return the audit send mails that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditSendMails addAuditSendMails(
		com.tls.liferaylms.mail.model.AuditSendMails auditSendMails)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAuditSendMails(auditSendMails);
	}

	/**
	* Creates a new audit send mails with the primary key. Does not add the audit send mails to the database.
	*
	* @param auditSendMailsId the primary key for the new audit send mails
	* @return the new audit send mails
	*/
	public static com.tls.liferaylms.mail.model.AuditSendMails createAuditSendMails(
		long auditSendMailsId) {
		return getService().createAuditSendMails(auditSendMailsId);
	}

	/**
	* Deletes the audit send mails with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditSendMailsId the primary key of the audit send mails
	* @return the audit send mails that was removed
	* @throws PortalException if a audit send mails with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditSendMails deleteAuditSendMails(
		long auditSendMailsId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAuditSendMails(auditSendMailsId);
	}

	/**
	* Deletes the audit send mails from the database. Also notifies the appropriate model listeners.
	*
	* @param auditSendMails the audit send mails
	* @return the audit send mails that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditSendMails deleteAuditSendMails(
		com.tls.liferaylms.mail.model.AuditSendMails auditSendMails)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAuditSendMails(auditSendMails);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.tls.liferaylms.mail.model.AuditSendMails fetchAuditSendMails(
		long auditSendMailsId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAuditSendMails(auditSendMailsId);
	}

	/**
	* Returns the audit send mails with the primary key.
	*
	* @param auditSendMailsId the primary key of the audit send mails
	* @return the audit send mails
	* @throws PortalException if a audit send mails with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditSendMails getAuditSendMails(
		long auditSendMailsId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAuditSendMails(auditSendMailsId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the audit send mails with the UUID in the group.
	*
	* @param uuid the UUID of audit send mails
	* @param groupId the group id of the audit send mails
	* @return the audit send mails
	* @throws PortalException if a audit send mails with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditSendMails getAuditSendMailsByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAuditSendMailsByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the audit send mailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of audit send mailses
	* @param end the upper bound of the range of audit send mailses (not inclusive)
	* @return the range of audit send mailses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.AuditSendMails> getAuditSendMailses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAuditSendMailses(start, end);
	}

	/**
	* Returns the number of audit send mailses.
	*
	* @return the number of audit send mailses
	* @throws SystemException if a system exception occurred
	*/
	public static int getAuditSendMailsesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAuditSendMailsesCount();
	}

	/**
	* Updates the audit send mails in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param auditSendMails the audit send mails
	* @return the audit send mails that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditSendMails updateAuditSendMails(
		com.tls.liferaylms.mail.model.AuditSendMails auditSendMails)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAuditSendMails(auditSendMails);
	}

	/**
	* Updates the audit send mails in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param auditSendMails the audit send mails
	* @param merge whether to merge the audit send mails with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the audit send mails that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditSendMails updateAuditSendMails(
		com.tls.liferaylms.mail.model.AuditSendMails auditSendMails,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAuditSendMails(auditSendMails, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static void clearService() {
		_service = null;
	}

	public static AuditSendMailsLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AuditSendMailsLocalService.class.getName());

			if (invokableLocalService instanceof AuditSendMailsLocalService) {
				_service = (AuditSendMailsLocalService)invokableLocalService;
			}
			else {
				_service = new AuditSendMailsLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AuditSendMailsLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AuditSendMailsLocalService service) {
	}

	private static AuditSendMailsLocalService _service;
}