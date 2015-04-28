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

import com.tls.liferaylms.mail.model.MailJob;

/**
 * The persistence interface for the mail job service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author je03042
 * @see MailJobPersistenceImpl
 * @see MailJobUtil
 * @generated
 */
public interface MailJobPersistence extends BasePersistence<MailJob> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MailJobUtil} to access the mail job persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the mail job in the entity cache if it is enabled.
	*
	* @param mailJob the mail job
	*/
	public void cacheResult(com.tls.liferaylms.mail.model.MailJob mailJob);

	/**
	* Caches the mail jobs in the entity cache if it is enabled.
	*
	* @param mailJobs the mail jobs
	*/
	public void cacheResult(
		java.util.List<com.tls.liferaylms.mail.model.MailJob> mailJobs);

	/**
	* Creates a new mail job with the primary key. Does not add the mail job to the database.
	*
	* @param idJob the primary key for the new mail job
	* @return the new mail job
	*/
	public com.tls.liferaylms.mail.model.MailJob create(long idJob);

	/**
	* Removes the mail job with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param idJob the primary key of the mail job
	* @return the mail job that was removed
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a mail job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob remove(long idJob)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException;

	public com.tls.liferaylms.mail.model.MailJob updateImpl(
		com.tls.liferaylms.mail.model.MailJob mailJob, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the mail job with the primary key or throws a {@link com.tls.liferaylms.mail.NoSuchMailJobException} if it could not be found.
	*
	* @param idJob the primary key of the mail job
	* @return the mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a mail job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob findByPrimaryKey(long idJob)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException;

	/**
	* Returns the mail job with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param idJob the primary key of the mail job
	* @return the mail job, or <code>null</code> if a mail job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob fetchByPrimaryKey(long idJob)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the mail jobs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.MailJob> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the mail jobs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of mail jobs
	* @param end the upper bound of the range of mail jobs (not inclusive)
	* @return the range of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.MailJob> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the mail jobs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of mail jobs
	* @param end the upper bound of the range of mail jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.MailJob> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first mail job in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException;

	/**
	* Returns the first mail job in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last mail job in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException;

	/**
	* Returns the last mail job in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the mail jobs before and after the current mail job in the ordered set where uuid = &#63;.
	*
	* @param idJob the primary key of the current mail job
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a mail job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob[] findByUuid_PrevAndNext(
		long idJob, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException;

	/**
	* Returns the mail job where uuid = &#63; and groupId = &#63; or throws a {@link com.tls.liferaylms.mail.NoSuchMailJobException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException;

	/**
	* Returns the mail job where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the mail job where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the mail jobs where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.MailJob> findByu(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the mail jobs where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of mail jobs
	* @param end the upper bound of the range of mail jobs (not inclusive)
	* @return the range of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.MailJob> findByu(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the mail jobs where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of mail jobs
	* @param end the upper bound of the range of mail jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.MailJob> findByu(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first mail job in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob findByu_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException;

	/**
	* Returns the first mail job in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob fetchByu_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last mail job in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob findByu_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException;

	/**
	* Returns the last mail job in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob fetchByu_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the mail jobs before and after the current mail job in the ordered set where userId = &#63;.
	*
	* @param idJob the primary key of the current mail job
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a mail job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob[] findByu_PrevAndNext(
		long idJob, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException;

	/**
	* Returns all the mail jobs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.MailJob> findByg(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the mail jobs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of mail jobs
	* @param end the upper bound of the range of mail jobs (not inclusive)
	* @return the range of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.MailJob> findByg(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the mail jobs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of mail jobs
	* @param end the upper bound of the range of mail jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.MailJob> findByg(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first mail job in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob findByg_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException;

	/**
	* Returns the first mail job in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob fetchByg_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last mail job in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob findByg_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException;

	/**
	* Returns the last mail job in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob fetchByg_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the mail jobs before and after the current mail job in the ordered set where groupId = &#63;.
	*
	* @param idJob the primary key of the current mail job
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a mail job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob[] findByg_PrevAndNext(
		long idJob, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException;

	/**
	* Returns all the mail jobs where groupId = &#63; and processed = &#63;.
	*
	* @param groupId the group ID
	* @param processed the processed
	* @return the matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.MailJob> findBygp(
		long groupId, boolean processed)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the mail jobs where groupId = &#63; and processed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param processed the processed
	* @param start the lower bound of the range of mail jobs
	* @param end the upper bound of the range of mail jobs (not inclusive)
	* @return the range of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.MailJob> findBygp(
		long groupId, boolean processed, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the mail jobs where groupId = &#63; and processed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param processed the processed
	* @param start the lower bound of the range of mail jobs
	* @param end the upper bound of the range of mail jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.MailJob> findBygp(
		long groupId, boolean processed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first mail job in the ordered set where groupId = &#63; and processed = &#63;.
	*
	* @param groupId the group ID
	* @param processed the processed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob findBygp_First(long groupId,
		boolean processed,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException;

	/**
	* Returns the first mail job in the ordered set where groupId = &#63; and processed = &#63;.
	*
	* @param groupId the group ID
	* @param processed the processed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob fetchBygp_First(long groupId,
		boolean processed,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last mail job in the ordered set where groupId = &#63; and processed = &#63;.
	*
	* @param groupId the group ID
	* @param processed the processed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob findBygp_Last(long groupId,
		boolean processed,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException;

	/**
	* Returns the last mail job in the ordered set where groupId = &#63; and processed = &#63;.
	*
	* @param groupId the group ID
	* @param processed the processed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob fetchBygp_Last(long groupId,
		boolean processed,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the mail jobs before and after the current mail job in the ordered set where groupId = &#63; and processed = &#63;.
	*
	* @param idJob the primary key of the current mail job
	* @param groupId the group ID
	* @param processed the processed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a mail job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob[] findBygp_PrevAndNext(
		long idJob, long groupId, boolean processed,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException;

	/**
	* Returns all the mail jobs where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.MailJob> findByc(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the mail jobs where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of mail jobs
	* @param end the upper bound of the range of mail jobs (not inclusive)
	* @return the range of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.MailJob> findByc(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the mail jobs where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of mail jobs
	* @param end the upper bound of the range of mail jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.MailJob> findByc(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first mail job in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob findByc_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException;

	/**
	* Returns the first mail job in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob fetchByc_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last mail job in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob findByc_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException;

	/**
	* Returns the last mail job in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob fetchByc_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the mail jobs before and after the current mail job in the ordered set where companyId = &#63;.
	*
	* @param idJob the primary key of the current mail job
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a mail job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob[] findByc_PrevAndNext(
		long idJob, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException;

	/**
	* Returns all the mail jobs.
	*
	* @return the mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.MailJob> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.tls.liferaylms.mail.model.MailJob> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the mail jobs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of mail jobs
	* @param end the upper bound of the range of mail jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.MailJob> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the mail jobs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the mail job where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the mail job that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailJob removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException;

	/**
	* Removes all the mail jobs where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByu(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the mail jobs where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByg(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the mail jobs where groupId = &#63; and processed = &#63; from the database.
	*
	* @param groupId the group ID
	* @param processed the processed
	* @throws SystemException if a system exception occurred
	*/
	public void removeBygp(long groupId, boolean processed)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the mail jobs where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByc(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the mail jobs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of mail jobs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of mail jobs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of mail jobs where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public int countByu(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of mail jobs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public int countByg(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of mail jobs where groupId = &#63; and processed = &#63;.
	*
	* @param groupId the group ID
	* @param processed the processed
	* @return the number of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public int countBygp(long groupId, boolean processed)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of mail jobs where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public int countByc(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of mail jobs.
	*
	* @return the number of mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}