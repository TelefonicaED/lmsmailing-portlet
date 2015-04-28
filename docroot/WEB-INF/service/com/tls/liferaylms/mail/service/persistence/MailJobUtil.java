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

import com.tls.liferaylms.mail.model.MailJob;

import java.util.List;

/**
 * The persistence utility for the mail job service. This utility wraps {@link MailJobPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author je03042
 * @see MailJobPersistence
 * @see MailJobPersistenceImpl
 * @generated
 */
public class MailJobUtil {
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
	public static void clearCache(MailJob mailJob) {
		getPersistence().clearCache(mailJob);
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
	public static List<MailJob> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MailJob> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MailJob> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static MailJob update(MailJob mailJob, boolean merge)
		throws SystemException {
		return getPersistence().update(mailJob, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static MailJob update(MailJob mailJob, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(mailJob, merge, serviceContext);
	}

	/**
	* Caches the mail job in the entity cache if it is enabled.
	*
	* @param mailJob the mail job
	*/
	public static void cacheResult(
		com.tls.liferaylms.mail.model.MailJob mailJob) {
		getPersistence().cacheResult(mailJob);
	}

	/**
	* Caches the mail jobs in the entity cache if it is enabled.
	*
	* @param mailJobs the mail jobs
	*/
	public static void cacheResult(
		java.util.List<com.tls.liferaylms.mail.model.MailJob> mailJobs) {
		getPersistence().cacheResult(mailJobs);
	}

	/**
	* Creates a new mail job with the primary key. Does not add the mail job to the database.
	*
	* @param idJob the primary key for the new mail job
	* @return the new mail job
	*/
	public static com.tls.liferaylms.mail.model.MailJob create(long idJob) {
		return getPersistence().create(idJob);
	}

	/**
	* Removes the mail job with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param idJob the primary key of the mail job
	* @return the mail job that was removed
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a mail job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob remove(long idJob)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException {
		return getPersistence().remove(idJob);
	}

	public static com.tls.liferaylms.mail.model.MailJob updateImpl(
		com.tls.liferaylms.mail.model.MailJob mailJob, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(mailJob, merge);
	}

	/**
	* Returns the mail job with the primary key or throws a {@link com.tls.liferaylms.mail.NoSuchMailJobException} if it could not be found.
	*
	* @param idJob the primary key of the mail job
	* @return the mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a mail job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob findByPrimaryKey(
		long idJob)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException {
		return getPersistence().findByPrimaryKey(idJob);
	}

	/**
	* Returns the mail job with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param idJob the primary key of the mail job
	* @return the mail job, or <code>null</code> if a mail job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob fetchByPrimaryKey(
		long idJob) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(idJob);
	}

	/**
	* Returns all the mail jobs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.MailJob> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

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
	public static java.util.List<com.tls.liferaylms.mail.model.MailJob> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static java.util.List<com.tls.liferaylms.mail.model.MailJob> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first mail job in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first mail job in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last mail job in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last mail job in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

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
	public static com.tls.liferaylms.mail.model.MailJob[] findByUuid_PrevAndNext(
		long idJob, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException {
		return getPersistence()
				   .findByUuid_PrevAndNext(idJob, uuid, orderByComparator);
	}

	/**
	* Returns the mail job where uuid = &#63; and groupId = &#63; or throws a {@link com.tls.liferaylms.mail.NoSuchMailJobException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the mail job where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the mail job where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Returns all the mail jobs where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.MailJob> findByu(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByu(userId);
	}

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
	public static java.util.List<com.tls.liferaylms.mail.model.MailJob> findByu(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByu(userId, start, end);
	}

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
	public static java.util.List<com.tls.liferaylms.mail.model.MailJob> findByu(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByu(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first mail job in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob findByu_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException {
		return getPersistence().findByu_First(userId, orderByComparator);
	}

	/**
	* Returns the first mail job in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob fetchByu_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByu_First(userId, orderByComparator);
	}

	/**
	* Returns the last mail job in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob findByu_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException {
		return getPersistence().findByu_Last(userId, orderByComparator);
	}

	/**
	* Returns the last mail job in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob fetchByu_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByu_Last(userId, orderByComparator);
	}

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
	public static com.tls.liferaylms.mail.model.MailJob[] findByu_PrevAndNext(
		long idJob, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException {
		return getPersistence()
				   .findByu_PrevAndNext(idJob, userId, orderByComparator);
	}

	/**
	* Returns all the mail jobs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.MailJob> findByg(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByg(groupId);
	}

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
	public static java.util.List<com.tls.liferaylms.mail.model.MailJob> findByg(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByg(groupId, start, end);
	}

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
	public static java.util.List<com.tls.liferaylms.mail.model.MailJob> findByg(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByg(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first mail job in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob findByg_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException {
		return getPersistence().findByg_First(groupId, orderByComparator);
	}

	/**
	* Returns the first mail job in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob fetchByg_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByg_First(groupId, orderByComparator);
	}

	/**
	* Returns the last mail job in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob findByg_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException {
		return getPersistence().findByg_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last mail job in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob fetchByg_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByg_Last(groupId, orderByComparator);
	}

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
	public static com.tls.liferaylms.mail.model.MailJob[] findByg_PrevAndNext(
		long idJob, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException {
		return getPersistence()
				   .findByg_PrevAndNext(idJob, groupId, orderByComparator);
	}

	/**
	* Returns all the mail jobs where groupId = &#63; and processed = &#63;.
	*
	* @param groupId the group ID
	* @param processed the processed
	* @return the matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.MailJob> findBygp(
		long groupId, boolean processed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBygp(groupId, processed);
	}

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
	public static java.util.List<com.tls.liferaylms.mail.model.MailJob> findBygp(
		long groupId, boolean processed, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBygp(groupId, processed, start, end);
	}

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
	public static java.util.List<com.tls.liferaylms.mail.model.MailJob> findBygp(
		long groupId, boolean processed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBygp(groupId, processed, start, end, orderByComparator);
	}

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
	public static com.tls.liferaylms.mail.model.MailJob findBygp_First(
		long groupId, boolean processed,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException {
		return getPersistence()
				   .findBygp_First(groupId, processed, orderByComparator);
	}

	/**
	* Returns the first mail job in the ordered set where groupId = &#63; and processed = &#63;.
	*
	* @param groupId the group ID
	* @param processed the processed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob fetchBygp_First(
		long groupId, boolean processed,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBygp_First(groupId, processed, orderByComparator);
	}

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
	public static com.tls.liferaylms.mail.model.MailJob findBygp_Last(
		long groupId, boolean processed,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException {
		return getPersistence()
				   .findBygp_Last(groupId, processed, orderByComparator);
	}

	/**
	* Returns the last mail job in the ordered set where groupId = &#63; and processed = &#63;.
	*
	* @param groupId the group ID
	* @param processed the processed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob fetchBygp_Last(
		long groupId, boolean processed,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBygp_Last(groupId, processed, orderByComparator);
	}

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
	public static com.tls.liferaylms.mail.model.MailJob[] findBygp_PrevAndNext(
		long idJob, long groupId, boolean processed,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException {
		return getPersistence()
				   .findBygp_PrevAndNext(idJob, groupId, processed,
			orderByComparator);
	}

	/**
	* Returns all the mail jobs where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.MailJob> findByc(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByc(companyId);
	}

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
	public static java.util.List<com.tls.liferaylms.mail.model.MailJob> findByc(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByc(companyId, start, end);
	}

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
	public static java.util.List<com.tls.liferaylms.mail.model.MailJob> findByc(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByc(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first mail job in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob findByc_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException {
		return getPersistence().findByc_First(companyId, orderByComparator);
	}

	/**
	* Returns the first mail job in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob fetchByc_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByc_First(companyId, orderByComparator);
	}

	/**
	* Returns the last mail job in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail job
	* @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob findByc_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException {
		return getPersistence().findByc_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last mail job in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail job, or <code>null</code> if a matching mail job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob fetchByc_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByc_Last(companyId, orderByComparator);
	}

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
	public static com.tls.liferaylms.mail.model.MailJob[] findByc_PrevAndNext(
		long idJob, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException {
		return getPersistence()
				   .findByc_PrevAndNext(idJob, companyId, orderByComparator);
	}

	/**
	* Returns all the mail jobs.
	*
	* @return the mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.MailJob> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.tls.liferaylms.mail.model.MailJob> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.tls.liferaylms.mail.model.MailJob> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the mail jobs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes the mail job where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the mail job that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailJob removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailJobException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Removes all the mail jobs where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByu(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByu(userId);
	}

	/**
	* Removes all the mail jobs where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByg(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByg(groupId);
	}

	/**
	* Removes all the mail jobs where groupId = &#63; and processed = &#63; from the database.
	*
	* @param groupId the group ID
	* @param processed the processed
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBygp(long groupId, boolean processed)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBygp(groupId, processed);
	}

	/**
	* Removes all the mail jobs where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByc(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByc(companyId);
	}

	/**
	* Removes all the mail jobs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of mail jobs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of mail jobs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of mail jobs where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByu(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByu(userId);
	}

	/**
	* Returns the number of mail jobs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByg(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByg(groupId);
	}

	/**
	* Returns the number of mail jobs where groupId = &#63; and processed = &#63;.
	*
	* @param groupId the group ID
	* @param processed the processed
	* @return the number of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public static int countBygp(long groupId, boolean processed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBygp(groupId, processed);
	}

	/**
	* Returns the number of mail jobs where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByc(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByc(companyId);
	}

	/**
	* Returns the number of mail jobs.
	*
	* @return the number of mail jobs
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static MailJobPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (MailJobPersistence)PortletBeanLocatorUtil.locate(com.tls.liferaylms.mail.service.ClpSerializer.getServletContextName(),
					MailJobPersistence.class.getName());

			ReferenceRegistry.registerReference(MailJobUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(MailJobPersistence persistence) {
	}

	private static MailJobPersistence _persistence;
}