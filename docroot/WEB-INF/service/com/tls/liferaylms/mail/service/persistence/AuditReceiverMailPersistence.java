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

import com.tls.liferaylms.mail.model.AuditReceiverMail;

/**
 * The persistence interface for the audit receiver mail service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author je03042
 * @see AuditReceiverMailPersistenceImpl
 * @see AuditReceiverMailUtil
 * @generated
 */
public interface AuditReceiverMailPersistence extends BasePersistence<AuditReceiverMail> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AuditReceiverMailUtil} to access the audit receiver mail persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the audit receiver mail in the entity cache if it is enabled.
	*
	* @param auditReceiverMail the audit receiver mail
	*/
	public void cacheResult(
		com.tls.liferaylms.mail.model.AuditReceiverMail auditReceiverMail);

	/**
	* Caches the audit receiver mails in the entity cache if it is enabled.
	*
	* @param auditReceiverMails the audit receiver mails
	*/
	public void cacheResult(
		java.util.List<com.tls.liferaylms.mail.model.AuditReceiverMail> auditReceiverMails);

	/**
	* Creates a new audit receiver mail with the primary key. Does not add the audit receiver mail to the database.
	*
	* @param auditReceiverMailId the primary key for the new audit receiver mail
	* @return the new audit receiver mail
	*/
	public com.tls.liferaylms.mail.model.AuditReceiverMail create(
		long auditReceiverMailId);

	/**
	* Removes the audit receiver mail with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditReceiverMailId the primary key of the audit receiver mail
	* @return the audit receiver mail that was removed
	* @throws com.tls.liferaylms.mail.NoSuchAuditReceiverMailException if a audit receiver mail with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditReceiverMail remove(
		long auditReceiverMailId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditReceiverMailException;

	public com.tls.liferaylms.mail.model.AuditReceiverMail updateImpl(
		com.tls.liferaylms.mail.model.AuditReceiverMail auditReceiverMail,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the audit receiver mail with the primary key or throws a {@link com.tls.liferaylms.mail.NoSuchAuditReceiverMailException} if it could not be found.
	*
	* @param auditReceiverMailId the primary key of the audit receiver mail
	* @return the audit receiver mail
	* @throws com.tls.liferaylms.mail.NoSuchAuditReceiverMailException if a audit receiver mail with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditReceiverMail findByPrimaryKey(
		long auditReceiverMailId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditReceiverMailException;

	/**
	* Returns the audit receiver mail with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param auditReceiverMailId the primary key of the audit receiver mail
	* @return the audit receiver mail, or <code>null</code> if a audit receiver mail with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditReceiverMail fetchByPrimaryKey(
		long auditReceiverMailId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the audit receiver mails where auditSendMailsId = &#63;.
	*
	* @param auditSendMailsId the audit send mails ID
	* @return the matching audit receiver mails
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.AuditReceiverMail> findByauditSendMail(
		long auditSendMailsId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.tls.liferaylms.mail.model.AuditReceiverMail> findByauditSendMail(
		long auditSendMailsId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.tls.liferaylms.mail.model.AuditReceiverMail> findByauditSendMail(
		long auditSendMailsId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first audit receiver mail in the ordered set where auditSendMailsId = &#63;.
	*
	* @param auditSendMailsId the audit send mails ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit receiver mail
	* @throws com.tls.liferaylms.mail.NoSuchAuditReceiverMailException if a matching audit receiver mail could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditReceiverMail findByauditSendMail_First(
		long auditSendMailsId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditReceiverMailException;

	/**
	* Returns the first audit receiver mail in the ordered set where auditSendMailsId = &#63;.
	*
	* @param auditSendMailsId the audit send mails ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit receiver mail, or <code>null</code> if a matching audit receiver mail could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditReceiverMail fetchByauditSendMail_First(
		long auditSendMailsId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last audit receiver mail in the ordered set where auditSendMailsId = &#63;.
	*
	* @param auditSendMailsId the audit send mails ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit receiver mail
	* @throws com.tls.liferaylms.mail.NoSuchAuditReceiverMailException if a matching audit receiver mail could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditReceiverMail findByauditSendMail_Last(
		long auditSendMailsId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditReceiverMailException;

	/**
	* Returns the last audit receiver mail in the ordered set where auditSendMailsId = &#63;.
	*
	* @param auditSendMailsId the audit send mails ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit receiver mail, or <code>null</code> if a matching audit receiver mail could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.AuditReceiverMail fetchByauditSendMail_Last(
		long auditSendMailsId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.tls.liferaylms.mail.model.AuditReceiverMail[] findByauditSendMail_PrevAndNext(
		long auditReceiverMailId, long auditSendMailsId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchAuditReceiverMailException;

	/**
	* Returns all the audit receiver mails.
	*
	* @return the audit receiver mails
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.AuditReceiverMail> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.tls.liferaylms.mail.model.AuditReceiverMail> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.tls.liferaylms.mail.model.AuditReceiverMail> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the audit receiver mails where auditSendMailsId = &#63; from the database.
	*
	* @param auditSendMailsId the audit send mails ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByauditSendMail(long auditSendMailsId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the audit receiver mails from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of audit receiver mails where auditSendMailsId = &#63;.
	*
	* @param auditSendMailsId the audit send mails ID
	* @return the number of matching audit receiver mails
	* @throws SystemException if a system exception occurred
	*/
	public int countByauditSendMail(long auditSendMailsId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of audit receiver mails.
	*
	* @return the number of audit receiver mails
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}