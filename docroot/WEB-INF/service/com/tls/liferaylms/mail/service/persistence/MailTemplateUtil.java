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

import com.tls.liferaylms.mail.model.MailTemplate;

import java.util.List;

/**
 * The persistence utility for the mail template service. This utility wraps {@link MailTemplatePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author je03042
 * @see MailTemplatePersistence
 * @see MailTemplatePersistenceImpl
 * @generated
 */
public class MailTemplateUtil {
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
	public static void clearCache(MailTemplate mailTemplate) {
		getPersistence().clearCache(mailTemplate);
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
	public static List<MailTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MailTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MailTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static MailTemplate update(MailTemplate mailTemplate, boolean merge)
		throws SystemException {
		return getPersistence().update(mailTemplate, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static MailTemplate update(MailTemplate mailTemplate, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(mailTemplate, merge, serviceContext);
	}

	/**
	* Caches the mail template in the entity cache if it is enabled.
	*
	* @param mailTemplate the mail template
	*/
	public static void cacheResult(
		com.tls.liferaylms.mail.model.MailTemplate mailTemplate) {
		getPersistence().cacheResult(mailTemplate);
	}

	/**
	* Caches the mail templates in the entity cache if it is enabled.
	*
	* @param mailTemplates the mail templates
	*/
	public static void cacheResult(
		java.util.List<com.tls.liferaylms.mail.model.MailTemplate> mailTemplates) {
		getPersistence().cacheResult(mailTemplates);
	}

	/**
	* Creates a new mail template with the primary key. Does not add the mail template to the database.
	*
	* @param idTemplate the primary key for the new mail template
	* @return the new mail template
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate create(
		long idTemplate) {
		return getPersistence().create(idTemplate);
	}

	/**
	* Removes the mail template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param idTemplate the primary key of the mail template
	* @return the mail template that was removed
	* @throws com.tls.liferaylms.mail.NoSuchMailTemplateException if a mail template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate remove(
		long idTemplate)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailTemplateException {
		return getPersistence().remove(idTemplate);
	}

	public static com.tls.liferaylms.mail.model.MailTemplate updateImpl(
		com.tls.liferaylms.mail.model.MailTemplate mailTemplate, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(mailTemplate, merge);
	}

	/**
	* Returns the mail template with the primary key or throws a {@link com.tls.liferaylms.mail.NoSuchMailTemplateException} if it could not be found.
	*
	* @param idTemplate the primary key of the mail template
	* @return the mail template
	* @throws com.tls.liferaylms.mail.NoSuchMailTemplateException if a mail template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate findByPrimaryKey(
		long idTemplate)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailTemplateException {
		return getPersistence().findByPrimaryKey(idTemplate);
	}

	/**
	* Returns the mail template with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param idTemplate the primary key of the mail template
	* @return the mail template, or <code>null</code> if a mail template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate fetchByPrimaryKey(
		long idTemplate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(idTemplate);
	}

	/**
	* Returns all the mail templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching mail templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.MailTemplate> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the mail templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of mail templates
	* @param end the upper bound of the range of mail templates (not inclusive)
	* @return the range of matching mail templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.MailTemplate> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the mail templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of mail templates
	* @param end the upper bound of the range of mail templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching mail templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.MailTemplate> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first mail template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail template
	* @throws com.tls.liferaylms.mail.NoSuchMailTemplateException if a matching mail template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailTemplateException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first mail template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail template, or <code>null</code> if a matching mail template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last mail template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail template
	* @throws com.tls.liferaylms.mail.NoSuchMailTemplateException if a matching mail template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailTemplateException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last mail template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail template, or <code>null</code> if a matching mail template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the mail templates before and after the current mail template in the ordered set where uuid = &#63;.
	*
	* @param idTemplate the primary key of the current mail template
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next mail template
	* @throws com.tls.liferaylms.mail.NoSuchMailTemplateException if a mail template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate[] findByUuid_PrevAndNext(
		long idTemplate, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailTemplateException {
		return getPersistence()
				   .findByUuid_PrevAndNext(idTemplate, uuid, orderByComparator);
	}

	/**
	* Returns the mail template where uuid = &#63; and groupId = &#63; or throws a {@link com.tls.liferaylms.mail.NoSuchMailTemplateException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching mail template
	* @throws com.tls.liferaylms.mail.NoSuchMailTemplateException if a matching mail template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailTemplateException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the mail template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching mail template, or <code>null</code> if a matching mail template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the mail template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching mail template, or <code>null</code> if a matching mail template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Returns all the mail templates where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching mail templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.MailTemplate> findByg(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByg(groupId);
	}

	/**
	* Returns a range of all the mail templates where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of mail templates
	* @param end the upper bound of the range of mail templates (not inclusive)
	* @return the range of matching mail templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.MailTemplate> findByg(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByg(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the mail templates where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of mail templates
	* @param end the upper bound of the range of mail templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching mail templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.MailTemplate> findByg(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByg(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first mail template in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail template
	* @throws com.tls.liferaylms.mail.NoSuchMailTemplateException if a matching mail template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate findByg_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailTemplateException {
		return getPersistence().findByg_First(groupId, orderByComparator);
	}

	/**
	* Returns the first mail template in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail template, or <code>null</code> if a matching mail template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate fetchByg_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByg_First(groupId, orderByComparator);
	}

	/**
	* Returns the last mail template in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail template
	* @throws com.tls.liferaylms.mail.NoSuchMailTemplateException if a matching mail template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate findByg_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailTemplateException {
		return getPersistence().findByg_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last mail template in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail template, or <code>null</code> if a matching mail template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate fetchByg_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByg_Last(groupId, orderByComparator);
	}

	/**
	* Returns the mail templates before and after the current mail template in the ordered set where groupId = &#63;.
	*
	* @param idTemplate the primary key of the current mail template
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next mail template
	* @throws com.tls.liferaylms.mail.NoSuchMailTemplateException if a mail template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate[] findByg_PrevAndNext(
		long idTemplate, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailTemplateException {
		return getPersistence()
				   .findByg_PrevAndNext(idTemplate, groupId, orderByComparator);
	}

	/**
	* Returns all the mail templates that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching mail templates that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.MailTemplate> filterFindByg(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByg(groupId);
	}

	/**
	* Returns a range of all the mail templates that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of mail templates
	* @param end the upper bound of the range of mail templates (not inclusive)
	* @return the range of matching mail templates that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.MailTemplate> filterFindByg(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByg(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the mail templates that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of mail templates
	* @param end the upper bound of the range of mail templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching mail templates that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.MailTemplate> filterFindByg(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByg(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the mail templates before and after the current mail template in the ordered set of mail templates that the user has permission to view where groupId = &#63;.
	*
	* @param idTemplate the primary key of the current mail template
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next mail template
	* @throws com.tls.liferaylms.mail.NoSuchMailTemplateException if a mail template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate[] filterFindByg_PrevAndNext(
		long idTemplate, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailTemplateException {
		return getPersistence()
				   .filterFindByg_PrevAndNext(idTemplate, groupId,
			orderByComparator);
	}

	/**
	* Returns all the mail templates where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching mail templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.MailTemplate> findByc(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByc(companyId);
	}

	/**
	* Returns a range of all the mail templates where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of mail templates
	* @param end the upper bound of the range of mail templates (not inclusive)
	* @return the range of matching mail templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.MailTemplate> findByc(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByc(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the mail templates where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of mail templates
	* @param end the upper bound of the range of mail templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching mail templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.MailTemplate> findByc(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByc(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first mail template in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail template
	* @throws com.tls.liferaylms.mail.NoSuchMailTemplateException if a matching mail template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate findByc_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailTemplateException {
		return getPersistence().findByc_First(companyId, orderByComparator);
	}

	/**
	* Returns the first mail template in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mail template, or <code>null</code> if a matching mail template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate fetchByc_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByc_First(companyId, orderByComparator);
	}

	/**
	* Returns the last mail template in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail template
	* @throws com.tls.liferaylms.mail.NoSuchMailTemplateException if a matching mail template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate findByc_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailTemplateException {
		return getPersistence().findByc_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last mail template in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mail template, or <code>null</code> if a matching mail template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate fetchByc_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByc_Last(companyId, orderByComparator);
	}

	/**
	* Returns the mail templates before and after the current mail template in the ordered set where companyId = &#63;.
	*
	* @param idTemplate the primary key of the current mail template
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next mail template
	* @throws com.tls.liferaylms.mail.NoSuchMailTemplateException if a mail template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate[] findByc_PrevAndNext(
		long idTemplate, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailTemplateException {
		return getPersistence()
				   .findByc_PrevAndNext(idTemplate, companyId, orderByComparator);
	}

	/**
	* Returns all the mail templates.
	*
	* @return the mail templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.MailTemplate> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the mail templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of mail templates
	* @param end the upper bound of the range of mail templates (not inclusive)
	* @return the range of mail templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.MailTemplate> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the mail templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of mail templates
	* @param end the upper bound of the range of mail templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of mail templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tls.liferaylms.mail.model.MailTemplate> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the mail templates where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes the mail template where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the mail template that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.tls.liferaylms.mail.model.MailTemplate removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tls.liferaylms.mail.NoSuchMailTemplateException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Removes all the mail templates where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByg(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByg(groupId);
	}

	/**
	* Removes all the mail templates where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByc(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByc(companyId);
	}

	/**
	* Removes all the mail templates from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of mail templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching mail templates
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of mail templates where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching mail templates
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of mail templates where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching mail templates
	* @throws SystemException if a system exception occurred
	*/
	public static int countByg(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByg(groupId);
	}

	/**
	* Returns the number of mail templates that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching mail templates that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByg(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByg(groupId);
	}

	/**
	* Returns the number of mail templates where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching mail templates
	* @throws SystemException if a system exception occurred
	*/
	public static int countByc(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByc(companyId);
	}

	/**
	* Returns the number of mail templates.
	*
	* @return the number of mail templates
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static MailTemplatePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (MailTemplatePersistence)PortletBeanLocatorUtil.locate(com.tls.liferaylms.mail.service.ClpSerializer.getServletContextName(),
					MailTemplatePersistence.class.getName());

			ReferenceRegistry.registerReference(MailTemplateUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(MailTemplatePersistence persistence) {
	}

	private static MailTemplatePersistence _persistence;
}