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

import com.liferay.portal.service.persistence.BasePersistence;

import com.tls.liferaylms.mail.model.AuditSendMails;

/**
 * The persistence interface for the audit send mails service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author je03042
 * @see AuditSendMailsPersistenceImpl
 * @see AuditSendMailsUtil
 * @generated
 */
public interface AuditSendMailsPersistence extends BasePersistence<AuditSendMails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AuditSendMailsUtil} to access the audit send mails persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the audit send mails in the entity cache if it is enabled.
	*
	* @param auditSendMails the audit send mails
	*/
	public void cacheResult(
		com.tls.liferaylms.mail.model.AuditSendMails auditSendMails);

	/**
	* Caches the audit send mailses in the entity cache if it is enabled.
	*
	* @param auditSendMailses the audit send mailses
	*/
	public void cacheResult(
		java.util.List<com.tls.liferaylms.mail.model.AuditSendMails> auditSendMailses);

	/**
	* Creates a new audit send mails with the primary key. Does not add the audit send mails to the database.
	*
	* @param auditSendMailsId the primary key for the new audit send mails
	* @return the new audit send mails
	*/
	public com.tls.liferaylms.mail.model.AuditSendMails create(
		long auditSendMailsId);

	/**
	* Removes the audit send mails with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditSendMailsId the primary key of the audit send mails
	* @return the audit send mails that was removed
	* @throws com.tls.liferaylms.mail.NoSuchAuditSendMailsException if a audit send mails with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditSendMails remove(
		long auditSendMailsId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditSendMailsException;

	public com.tls.liferaylms.mail.model.AuditSendMails updateImpl(
		com.tls.liferaylms.mail.model.AuditSendMails auditSendMails,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the audit send mails with the primary key or throws a {@link com.tls.liferaylms.mail.NoSuchAuditSendMailsException} if it could not be found.
	*
	* @param auditSendMailsId the primary key of the audit send mails
	* @return the audit send mails
	* @throws com.tls.liferaylms.mail.NoSuchAuditSendMailsException if a audit send mails with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditSendMails findByPrimaryKey(
		long auditSendMailsId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditSendMailsException;

	/**
	* Returns the audit send mails with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param auditSendMailsId the primary key of the audit send mails
	* @return the audit send mails, or <code>null</code> if a audit send mails with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditSendMails fetchByPrimaryKey(
		long auditSendMailsId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the audit send mailses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching audit send mailses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.AuditSendMails> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.tls.liferaylms.mail.model.AuditSendMails> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.tls.liferaylms.mail.model.AuditSendMails> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first audit send mails in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit send mails
	* @throws com.tls.liferaylms.mail.NoSuchAuditSendMailsException if a matching audit send mails could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditSendMails findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditSendMailsException;

	/**
	* Returns the first audit send mails in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit send mails, or <code>null</code> if a matching audit send mails could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditSendMails fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last audit send mails in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit send mails
	* @throws com.tls.liferaylms.mail.NoSuchAuditSendMailsException if a matching audit send mails could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditSendMails findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditSendMailsException;

	/**
	* Returns the last audit send mails in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit send mails, or <code>null</code> if a matching audit send mails could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditSendMails fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.tls.liferaylms.mail.model.AuditSendMails[] findByUuid_PrevAndNext(
		long auditSendMailsId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditSendMailsException;

	/**
	* Returns the audit send mails where uuid = &#63; and groupId = &#63; or throws a {@link com.tls.liferaylms.mail.NoSuchAuditSendMailsException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching audit send mails
	* @throws com.tls.liferaylms.mail.NoSuchAuditSendMailsException if a matching audit send mails could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditSendMails findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditSendMailsException;

	/**
	* Returns the audit send mails where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching audit send mails, or <code>null</code> if a matching audit send mails could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditSendMails fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the audit send mails where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching audit send mails, or <code>null</code> if a matching audit send mails could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditSendMails fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the audit send mailses.
	*
	* @return the audit send mailses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.AuditSendMails> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.tls.liferaylms.mail.model.AuditSendMails> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.tls.liferaylms.mail.model.AuditSendMails> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the audit send mailses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the audit send mails where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the audit send mails that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditSendMails removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditSendMailsException;

	/**
	* Removes all the audit send mailses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of audit send mailses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching audit send mailses
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of audit send mailses where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching audit send mailses
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of audit send mailses.
	*
	* @return the number of audit send mailses
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}