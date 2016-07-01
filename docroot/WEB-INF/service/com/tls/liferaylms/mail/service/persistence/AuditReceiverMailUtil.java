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

import com.tls.liferaylms.mail.model.AuditReceiverMail;

import java.util.List;

/**
 * The persistence utility for the audit receiver mail service. This utility wraps {@link AuditReceiverMailPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author je03042
 * @see AuditReceiverMailPersistence
 * @see AuditReceiverMailPersistenceImpl
 * @generated
 */
public class AuditReceiverMailUtil {
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
	public static void clearCache(AuditReceiverMail auditReceiverMail) {
		getPersistence().clearCache(auditReceiverMail);
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
	public static List<AuditReceiverMail> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AuditReceiverMail> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AuditReceiverMail> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AuditReceiverMail update(
		AuditReceiverMail auditReceiverMail, boolean merge)
		throws SystemException {
		return getPersistence().update(auditReceiverMail, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AuditReceiverMail update(
		AuditReceiverMail auditReceiverMail, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(auditReceiverMail, merge, serviceContext);
	}

	/**
	* Caches the audit receiver mail in the entity cache if it is enabled.
	*
	* @param auditReceiverMail the audit receiver mail
	*/
	public static void cacheResult(
		com.tls.liferaylms.mail.model.AuditReceiverMail auditReceiverMail) {
		getPersistence().cacheResult(auditReceiverMail);
	}

	/**
	* Caches the audit receiver mails in the entity cache if it is enabled.
	*
	* @param auditReceiverMails the audit receiver mails
	*/
	public static void cacheResult(
		java.util.List<com.tls.liferaylms.mail.model.AuditReceiverMail> auditReceiverMails) {
		getPersistence().cacheResult(auditReceiverMails);
	}

	/**
	* Creates a new audit receiver mail with the primary key. Does not add the audit receiver mail to the database.
	*
	* @param auditReceiverMailId the primary key for the new audit receiver mail
	* @return the new audit receiver mail
	*/
	public static com.tls.liferaylms.mail.model.AuditReceiverMail create(
		long auditReceiverMailId) {
		return getPersistence().create(auditReceiverMailId);
	}

	/**
	* Removes the audit receiver mail with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditReceiverMailId the primary key of the audit receiver mail
	* @return the audit receiver mail that was removed
	* @throws com.tls.liferaylms.mail.NoSuchAuditReceiverMailException if a audit receiver mail with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditReceiverMail remove(
		long auditReceiverMailId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditReceiverMailException {
		return getPersistence().remove(auditReceiverMailId);
	}

	public static com.tls.liferaylms.mail.model.AuditReceiverMail updateImpl(
		com.tls.liferaylms.mail.model.AuditReceiverMail auditReceiverMail,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(auditReceiverMail, merge);
	}

	/**
	* Returns the audit receiver mail with the primary key or throws a {@link com.tls.liferaylms.mail.NoSuchAuditReceiverMailException} if it could not be found.
	*
	* @param auditReceiverMailId the primary key of the audit receiver mail
	* @return the audit receiver mail
	* @throws com.tls.liferaylms.mail.NoSuchAuditReceiverMailException if a audit receiver mail with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditReceiverMail findByPrimaryKey(
		long auditReceiverMailId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditReceiverMailException {
		return getPersistence().findByPrimaryKey(auditReceiverMailId);
	}

	/**
	* Returns the audit receiver mail with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param auditReceiverMailId the primary key of the audit receiver mail
	* @return the audit receiver mail, or <code>null</code> if a audit receiver mail with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditReceiverMail fetchByPrimaryKey(
		long auditReceiverMailId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(auditReceiverMailId);
	}

	/**
	* Returns all the audit receiver mails where auditSendMailsId = &#63;.
	*
	* @param auditSendMailsId the audit send mails ID
	* @return the matching audit receiver mails
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.AuditReceiverMail> findByauditSendMail(
		long auditSendMailsId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByauditSendMail(auditSendMailsId);
	}

	/**
	* Returns a range of all the audit receiver mails where auditSendMailsId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param auditSendMailsId the audit send mails ID
	* @param start the lower bound of the range of audit receiver mails
	* @param end the upper bound of the range of audit receiver mails (not inclusive)
	* @return the range of matching audit receiver mails
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.AuditReceiverMail> findByauditSendMail(
		long auditSendMailsId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByauditSendMail(auditSendMailsId, start, end);
	}

	/**
	* Returns an ordered range of all the audit receiver mails where auditSendMailsId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param auditSendMailsId the audit send mails ID
	* @param start the lower bound of the range of audit receiver mails
	* @param end the upper bound of the range of audit receiver mails (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit receiver mails
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.AuditReceiverMail> findByauditSendMail(
		long auditSendMailsId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByauditSendMail(auditSendMailsId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first audit receiver mail in the ordered set where auditSendMailsId = &#63;.
	*
	* @param auditSendMailsId the audit send mails ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit receiver mail
	* @throws com.tls.liferaylms.mail.NoSuchAuditReceiverMailException if a matching audit receiver mail could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditReceiverMail findByauditSendMail_First(
		long auditSendMailsId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditReceiverMailException {
		return getPersistence()
				   .findByauditSendMail_First(auditSendMailsId,
			orderByComparator);
	}

	/**
	* Returns the first audit receiver mail in the ordered set where auditSendMailsId = &#63;.
	*
	* @param auditSendMailsId the audit send mails ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit receiver mail, or <code>null</code> if a matching audit receiver mail could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditReceiverMail fetchByauditSendMail_First(
		long auditSendMailsId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByauditSendMail_First(auditSendMailsId,
			orderByComparator);
	}

	/**
	* Returns the last audit receiver mail in the ordered set where auditSendMailsId = &#63;.
	*
	* @param auditSendMailsId the audit send mails ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit receiver mail
	* @throws com.tls.liferaylms.mail.NoSuchAuditReceiverMailException if a matching audit receiver mail could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditReceiverMail findByauditSendMail_Last(
		long auditSendMailsId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditReceiverMailException {
		return getPersistence()
				   .findByauditSendMail_Last(auditSendMailsId, orderByComparator);
	}

	/**
	* Returns the last audit receiver mail in the ordered set where auditSendMailsId = &#63;.
	*
	* @param auditSendMailsId the audit send mails ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit receiver mail, or <code>null</code> if a matching audit receiver mail could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditReceiverMail fetchByauditSendMail_Last(
		long auditSendMailsId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByauditSendMail_Last(auditSendMailsId,
			orderByComparator);
	}

	/**
	* Returns the audit receiver mails before and after the current audit receiver mail in the ordered set where auditSendMailsId = &#63;.
	*
	* @param auditReceiverMailId the primary key of the current audit receiver mail
	* @param auditSendMailsId the audit send mails ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit receiver mail
	* @throws com.tls.liferaylms.mail.NoSuchAuditReceiverMailException if a audit receiver mail with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.AuditReceiverMail[] findByauditSendMail_PrevAndNext(
		long auditReceiverMailId, long auditSendMailsId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditReceiverMailException {
		return getPersistence()
				   .findByauditSendMail_PrevAndNext(auditReceiverMailId,
			auditSendMailsId, orderByComparator);
	}

	/**
	* Returns all the audit receiver mails.
	*
	* @return the audit receiver mails
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.AuditReceiverMail> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.tls.liferaylms.mail.model.AuditReceiverMail> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the audit receiver mails.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of audit receiver mails
	* @param end the upper bound of the range of audit receiver mails (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of audit receiver mails
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.AuditReceiverMail> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the audit receiver mails where auditSendMailsId = &#63; from the database.
	*
	* @param auditSendMailsId the audit send mails ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByauditSendMail(long auditSendMailsId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByauditSendMail(auditSendMailsId);
	}

	/**
	* Removes all the audit receiver mails from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of audit receiver mails where auditSendMailsId = &#63;.
	*
	* @param auditSendMailsId the audit send mails ID
	* @return the number of matching audit receiver mails
	* @throws SystemException if a system exception occurred
	*/
	public static int countByauditSendMail(long auditSendMailsId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByauditSendMail(auditSendMailsId);
	}

	/**
	* Returns the number of audit receiver mails.
	*
	* @return the number of audit receiver mails
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AuditReceiverMailPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AuditReceiverMailPersistence)PortletBeanLocatorUtil.locate(com.tls.liferaylms.mail.service.ClpSerializer.getServletContextName(),
					AuditReceiverMailPersistence.class.getName());

			ReferenceRegistry.registerReference(AuditReceiverMailUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AuditReceiverMailPersistence persistence) {
	}

	private static AuditReceiverMailPersistence _persistence;
}