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

package com.tls.liferaylms.mail.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.tls.liferaylms.mail.model.AuditSendMails;

import java.util.List;

/**
 * The persistence utility for the audit send mails service. This utility wraps {@link AuditSendMailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author je03042
 * @see AuditSendMailsPersistence
 * @see AuditSendMailsPersistenceImpl
 * @generated
 */
public class AuditSendMailsUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(AuditSendMails auditSendMails) {
		getPersistence().clearCache(auditSendMails);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AuditSendMails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AuditSendMails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AuditSendMails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AuditSendMails update(AuditSendMails auditSendMails,
		boolean merge) throws SystemException {
		return getPersistence().update(auditSendMails, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AuditSendMails update(AuditSendMails auditSendMails,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(auditSendMails, merge, serviceContext);
	}

	/**
	* Caches the audit send mails in the entity cache if it is enabled.
	*
	* @param auditSendMails the audit send mails
	*/
	public static void cacheResult(
		com.tls.liferaylms.mail.model.AuditSendMails auditSendMails) {
		getPersistence().cacheResult(auditSendMails);
	}

	/**
	* Caches the audit send mailses in the entity cache if it is enabled.
	*
	* @param auditSendMailses the audit send mailses
	*/
	public static void cacheResult(
		java.util.List<com.tls.liferaylms.mail.model.AuditSendMails> auditSendMailses) {
		getPersistence().cacheResult(auditSendMailses);
	}

	/**
	* Creates a new audit send mails with the primary key. Does not add the audit send mails to the database.
	*
	* @param auditSendMailsId the primary key for the new audit send mails
	* @return the new audit send mails
	*/
	public static com.tls.liferaylms.mail.model.AuditSendMails create(
		long auditSendMailsId) {
		return getPersistence().create(auditSendMailsId);
	}

	/**
	* Removes the audit send mails with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditSendMailsId the primary key of the audit send mails
	* @return the audit send mails that was removed
	* @throws com.tls.liferaylms.mail.NoSuchAuditSendMailsException if a audit send mails with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditSendMails remove(
		long auditSendMailsId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditSendMailsException {
		return getPersistence().remove(auditSendMailsId);
	}

	public static com.tls.liferaylms.mail.model.AuditSendMails updateImpl(
		com.tls.liferaylms.mail.model.AuditSendMails auditSendMails,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(auditSendMails, merge);
	}

	/**
	* Returns the audit send mails with the primary key or throws a {@link com.tls.liferaylms.mail.NoSuchAuditSendMailsException} if it could not be found.
	*
	* @param auditSendMailsId the primary key of the audit send mails
	* @return the audit send mails
	* @throws com.tls.liferaylms.mail.NoSuchAuditSendMailsException if a audit send mails with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditSendMails findByPrimaryKey(
		long auditSendMailsId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditSendMailsException {
		return getPersistence().findByPrimaryKey(auditSendMailsId);
	}

	/**
	* Returns the audit send mails with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param auditSendMailsId the primary key of the audit send mails
	* @return the audit send mails, or <code>null</code> if a audit send mails with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditSendMails fetchByPrimaryKey(
		long auditSendMailsId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(auditSendMailsId);
	}

	/**
	* Returns all the audit send mailses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching audit send mailses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.AuditSendMails> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the audit send mailses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of audit send mailses
	* @param end the upper bound of the range of audit send mailses (not inclusive)
	* @return the range of matching audit send mailses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.AuditSendMails> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the audit send mailses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of audit send mailses
	* @param end the upper bound of the range of audit send mailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit send mailses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.AuditSendMails> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first audit send mails in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit send mails
	* @throws com.tls.liferaylms.mail.NoSuchAuditSendMailsException if a matching audit send mails could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditSendMails findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditSendMailsException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first audit send mails in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit send mails, or <code>null</code> if a matching audit send mails could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditSendMails fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last audit send mails in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit send mails
	* @throws com.tls.liferaylms.mail.NoSuchAuditSendMailsException if a matching audit send mails could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditSendMails findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditSendMailsException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last audit send mails in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit send mails, or <code>null</code> if a matching audit send mails could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditSendMails fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the audit send mailses before and after the current audit send mails in the ordered set where uuid = &#63;.
	*
	* @param auditSendMailsId the primary key of the current audit send mails
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit send mails
	* @throws com.tls.liferaylms.mail.NoSuchAuditSendMailsException if a audit send mails with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditSendMails[] findByUuid_PrevAndNext(
		long auditSendMailsId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditSendMailsException {
		return getPersistence()
				   .findByUuid_PrevAndNext(auditSendMailsId, uuid,
			orderByComparator);
	}

	/**
	* Returns the audit send mails where uuid = &#63; and groupId = &#63; or throws a {@link com.tls.liferaylms.mail.NoSuchAuditSendMailsException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching audit send mails
	* @throws com.tls.liferaylms.mail.NoSuchAuditSendMailsException if a matching audit send mails could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditSendMails findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditSendMailsException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the audit send mails where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching audit send mails, or <code>null</code> if a matching audit send mails could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditSendMails fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the audit send mails where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching audit send mails, or <code>null</code> if a matching audit send mails could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditSendMails fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Returns all the audit send mailses.
	*
	* @return the audit send mailses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.AuditSendMails> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.tls.liferaylms.mail.model.AuditSendMails> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the audit send mailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of audit send mailses
	* @param end the upper bound of the range of audit send mailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of audit send mailses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.AuditSendMails> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the audit send mailses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes the audit send mails where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the audit send mails that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditSendMails removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditSendMailsException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Removes all the audit send mailses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of audit send mailses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching audit send mailses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of audit send mailses where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching audit send mailses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of audit send mailses.
	*
	* @return the number of audit send mailses
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AuditSendMailsPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AuditSendMailsPersistence)PortletBeanLocatorUtil.locate(com.tls.liferaylms.mail.service.ClpSerializer.getServletContextName(),
					AuditSendMailsPersistence.class.getName());

			ReferenceRegistry.registerReference(AuditSendMailsUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AuditSendMailsPersistence persistence) {
	}

	private static AuditSendMailsPersistence _persistence;
}