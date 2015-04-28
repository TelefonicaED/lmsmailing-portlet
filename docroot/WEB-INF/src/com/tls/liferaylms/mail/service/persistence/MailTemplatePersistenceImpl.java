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

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.tls.liferaylms.mail.NoSuchMailTemplateException;
import com.tls.liferaylms.mail.model.MailTemplate;
import com.tls.liferaylms.mail.model.impl.MailTemplateImpl;
import com.tls.liferaylms.mail.model.impl.MailTemplateModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the mail template service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author je03042
 * @see MailTemplatePersistence
 * @see MailTemplateUtil
 * @generated
 */
public class MailTemplatePersistenceImpl extends BasePersistenceImpl<MailTemplate>
	implements MailTemplatePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MailTemplateUtil} to access the mail template persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MailTemplateImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(MailTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MailTemplateModelImpl.FINDER_CACHE_ENABLED, MailTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(MailTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MailTemplateModelImpl.FINDER_CACHE_ENABLED, MailTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			MailTemplateModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(MailTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MailTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(MailTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MailTemplateModelImpl.FINDER_CACHE_ENABLED, MailTemplateImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			MailTemplateModelImpl.UUID_COLUMN_BITMASK |
			MailTemplateModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(MailTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MailTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G = new FinderPath(MailTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MailTemplateModelImpl.FINDER_CACHE_ENABLED, MailTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByg",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G = new FinderPath(MailTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MailTemplateModelImpl.FINDER_CACHE_ENABLED, MailTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByg",
			new String[] { Long.class.getName() },
			MailTemplateModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G = new FinderPath(MailTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MailTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByg",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C = new FinderPath(MailTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MailTemplateModelImpl.FINDER_CACHE_ENABLED, MailTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByc",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C = new FinderPath(MailTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MailTemplateModelImpl.FINDER_CACHE_ENABLED, MailTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByc",
			new String[] { Long.class.getName() },
			MailTemplateModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C = new FinderPath(MailTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MailTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByc",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MailTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MailTemplateModelImpl.FINDER_CACHE_ENABLED, MailTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MailTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MailTemplateModelImpl.FINDER_CACHE_ENABLED, MailTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MailTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MailTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the mail template in the entity cache if it is enabled.
	 *
	 * @param mailTemplate the mail template
	 */
	public void cacheResult(MailTemplate mailTemplate) {
		EntityCacheUtil.putResult(MailTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MailTemplateImpl.class, mailTemplate.getPrimaryKey(), mailTemplate);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				mailTemplate.getUuid(), Long.valueOf(mailTemplate.getGroupId())
			}, mailTemplate);

		mailTemplate.resetOriginalValues();
	}

	/**
	 * Caches the mail templates in the entity cache if it is enabled.
	 *
	 * @param mailTemplates the mail templates
	 */
	public void cacheResult(List<MailTemplate> mailTemplates) {
		for (MailTemplate mailTemplate : mailTemplates) {
			if (EntityCacheUtil.getResult(
						MailTemplateModelImpl.ENTITY_CACHE_ENABLED,
						MailTemplateImpl.class, mailTemplate.getPrimaryKey()) == null) {
				cacheResult(mailTemplate);
			}
			else {
				mailTemplate.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all mail templates.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(MailTemplateImpl.class.getName());
		}

		EntityCacheUtil.clearCache(MailTemplateImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the mail template.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MailTemplate mailTemplate) {
		EntityCacheUtil.removeResult(MailTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MailTemplateImpl.class, mailTemplate.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(mailTemplate);
	}

	@Override
	public void clearCache(List<MailTemplate> mailTemplates) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MailTemplate mailTemplate : mailTemplates) {
			EntityCacheUtil.removeResult(MailTemplateModelImpl.ENTITY_CACHE_ENABLED,
				MailTemplateImpl.class, mailTemplate.getPrimaryKey());

			clearUniqueFindersCache(mailTemplate);
		}
	}

	protected void clearUniqueFindersCache(MailTemplate mailTemplate) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				mailTemplate.getUuid(), Long.valueOf(mailTemplate.getGroupId())
			});
	}

	/**
	 * Creates a new mail template with the primary key. Does not add the mail template to the database.
	 *
	 * @param idTemplate the primary key for the new mail template
	 * @return the new mail template
	 */
	public MailTemplate create(long idTemplate) {
		MailTemplate mailTemplate = new MailTemplateImpl();

		mailTemplate.setNew(true);
		mailTemplate.setPrimaryKey(idTemplate);

		String uuid = PortalUUIDUtil.generate();

		mailTemplate.setUuid(uuid);

		return mailTemplate;
	}

	/**
	 * Removes the mail template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param idTemplate the primary key of the mail template
	 * @return the mail template that was removed
	 * @throws com.tls.liferaylms.mail.NoSuchMailTemplateException if a mail template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailTemplate remove(long idTemplate)
		throws NoSuchMailTemplateException, SystemException {
		return remove(Long.valueOf(idTemplate));
	}

	/**
	 * Removes the mail template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the mail template
	 * @return the mail template that was removed
	 * @throws com.tls.liferaylms.mail.NoSuchMailTemplateException if a mail template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MailTemplate remove(Serializable primaryKey)
		throws NoSuchMailTemplateException, SystemException {
		Session session = null;

		try {
			session = openSession();

			MailTemplate mailTemplate = (MailTemplate)session.get(MailTemplateImpl.class,
					primaryKey);

			if (mailTemplate == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMailTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(mailTemplate);
		}
		catch (NoSuchMailTemplateException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected MailTemplate removeImpl(MailTemplate mailTemplate)
		throws SystemException {
		mailTemplate = toUnwrappedModel(mailTemplate);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, mailTemplate);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(mailTemplate);

		return mailTemplate;
	}

	@Override
	public MailTemplate updateImpl(
		com.tls.liferaylms.mail.model.MailTemplate mailTemplate, boolean merge)
		throws SystemException {
		mailTemplate = toUnwrappedModel(mailTemplate);

		boolean isNew = mailTemplate.isNew();

		MailTemplateModelImpl mailTemplateModelImpl = (MailTemplateModelImpl)mailTemplate;

		if (Validator.isNull(mailTemplate.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			mailTemplate.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, mailTemplate, merge);

			mailTemplate.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !MailTemplateModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((mailTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						mailTemplateModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { mailTemplateModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((mailTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(mailTemplateModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G,
					args);

				args = new Object[] {
						Long.valueOf(mailTemplateModelImpl.getGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G,
					args);
			}

			if ((mailTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(mailTemplateModelImpl.getOriginalCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C,
					args);

				args = new Object[] {
						Long.valueOf(mailTemplateModelImpl.getCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C,
					args);
			}
		}

		EntityCacheUtil.putResult(MailTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MailTemplateImpl.class, mailTemplate.getPrimaryKey(), mailTemplate);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					mailTemplate.getUuid(),
					Long.valueOf(mailTemplate.getGroupId())
				}, mailTemplate);
		}
		else {
			if ((mailTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						mailTemplateModelImpl.getOriginalUuid(),
						Long.valueOf(mailTemplateModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
					new Object[] {
						mailTemplate.getUuid(),
						Long.valueOf(mailTemplate.getGroupId())
					}, mailTemplate);
			}
		}

		return mailTemplate;
	}

	protected MailTemplate toUnwrappedModel(MailTemplate mailTemplate) {
		if (mailTemplate instanceof MailTemplateImpl) {
			return mailTemplate;
		}

		MailTemplateImpl mailTemplateImpl = new MailTemplateImpl();

		mailTemplateImpl.setNew(mailTemplate.isNew());
		mailTemplateImpl.setPrimaryKey(mailTemplate.getPrimaryKey());

		mailTemplateImpl.setUuid(mailTemplate.getUuid());
		mailTemplateImpl.setIdTemplate(mailTemplate.getIdTemplate());
		mailTemplateImpl.setCompanyId(mailTemplate.getCompanyId());
		mailTemplateImpl.setGroupId(mailTemplate.getGroupId());
		mailTemplateImpl.setUserId(mailTemplate.getUserId());
		mailTemplateImpl.setSubject(mailTemplate.getSubject());
		mailTemplateImpl.setBody(mailTemplate.getBody());

		return mailTemplateImpl;
	}

	/**
	 * Returns the mail template with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the mail template
	 * @return the mail template
	 * @throws com.liferay.portal.NoSuchModelException if a mail template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MailTemplate findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the mail template with the primary key or throws a {@link com.tls.liferaylms.mail.NoSuchMailTemplateException} if it could not be found.
	 *
	 * @param idTemplate the primary key of the mail template
	 * @return the mail template
	 * @throws com.tls.liferaylms.mail.NoSuchMailTemplateException if a mail template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailTemplate findByPrimaryKey(long idTemplate)
		throws NoSuchMailTemplateException, SystemException {
		MailTemplate mailTemplate = fetchByPrimaryKey(idTemplate);

		if (mailTemplate == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + idTemplate);
			}

			throw new NoSuchMailTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				idTemplate);
		}

		return mailTemplate;
	}

	/**
	 * Returns the mail template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the mail template
	 * @return the mail template, or <code>null</code> if a mail template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MailTemplate fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the mail template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param idTemplate the primary key of the mail template
	 * @return the mail template, or <code>null</code> if a mail template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailTemplate fetchByPrimaryKey(long idTemplate)
		throws SystemException {
		MailTemplate mailTemplate = (MailTemplate)EntityCacheUtil.getResult(MailTemplateModelImpl.ENTITY_CACHE_ENABLED,
				MailTemplateImpl.class, idTemplate);

		if (mailTemplate == _nullMailTemplate) {
			return null;
		}

		if (mailTemplate == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				mailTemplate = (MailTemplate)session.get(MailTemplateImpl.class,
						Long.valueOf(idTemplate));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (mailTemplate != null) {
					cacheResult(mailTemplate);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(MailTemplateModelImpl.ENTITY_CACHE_ENABLED,
						MailTemplateImpl.class, idTemplate, _nullMailTemplate);
				}

				closeSession(session);
			}
		}

		return mailTemplate;
	}

	/**
	 * Returns all the mail templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching mail templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<MailTemplate> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<MailTemplate> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
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
	public List<MailTemplate> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<MailTemplate> list = (List<MailTemplate>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (MailTemplate mailTemplate : list) {
				if (!Validator.equals(uuid, mailTemplate.getUuid())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MAILTEMPLATE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(MailTemplateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<MailTemplate>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
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
	public MailTemplate findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchMailTemplateException, SystemException {
		MailTemplate mailTemplate = fetchByUuid_First(uuid, orderByComparator);

		if (mailTemplate != null) {
			return mailTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMailTemplateException(msg.toString());
	}

	/**
	 * Returns the first mail template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mail template, or <code>null</code> if a matching mail template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailTemplate fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<MailTemplate> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public MailTemplate findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchMailTemplateException, SystemException {
		MailTemplate mailTemplate = fetchByUuid_Last(uuid, orderByComparator);

		if (mailTemplate != null) {
			return mailTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMailTemplateException(msg.toString());
	}

	/**
	 * Returns the last mail template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mail template, or <code>null</code> if a matching mail template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailTemplate fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<MailTemplate> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public MailTemplate[] findByUuid_PrevAndNext(long idTemplate, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchMailTemplateException, SystemException {
		MailTemplate mailTemplate = findByPrimaryKey(idTemplate);

		Session session = null;

		try {
			session = openSession();

			MailTemplate[] array = new MailTemplateImpl[3];

			array[0] = getByUuid_PrevAndNext(session, mailTemplate, uuid,
					orderByComparator, true);

			array[1] = mailTemplate;

			array[2] = getByUuid_PrevAndNext(session, mailTemplate, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MailTemplate getByUuid_PrevAndNext(Session session,
		MailTemplate mailTemplate, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MAILTEMPLATE_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(MailTemplateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(mailTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MailTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
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
	public MailTemplate findByUUID_G(String uuid, long groupId)
		throws NoSuchMailTemplateException, SystemException {
		MailTemplate mailTemplate = fetchByUUID_G(uuid, groupId);

		if (mailTemplate == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchMailTemplateException(msg.toString());
		}

		return mailTemplate;
	}

	/**
	 * Returns the mail template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching mail template, or <code>null</code> if a matching mail template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailTemplate fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
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
	public MailTemplate fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof MailTemplate) {
			MailTemplate mailTemplate = (MailTemplate)result;

			if (!Validator.equals(uuid, mailTemplate.getUuid()) ||
					(groupId != mailTemplate.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_MAILTEMPLATE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_G_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			query.append(MailTemplateModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<MailTemplate> list = q.list();

				result = list;

				MailTemplate mailTemplate = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					mailTemplate = list.get(0);

					cacheResult(mailTemplate);

					if ((mailTemplate.getUuid() == null) ||
							!mailTemplate.getUuid().equals(uuid) ||
							(mailTemplate.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, mailTemplate);
					}
				}

				return mailTemplate;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (MailTemplate)result;
			}
		}
	}

	/**
	 * Returns all the mail templates where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching mail templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<MailTemplate> findByg(long groupId) throws SystemException {
		return findByg(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<MailTemplate> findByg(long groupId, int start, int end)
		throws SystemException {
		return findByg(groupId, start, end, null);
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
	public List<MailTemplate> findByg(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<MailTemplate> list = (List<MailTemplate>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (MailTemplate mailTemplate : list) {
				if ((groupId != mailTemplate.getGroupId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MAILTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_G_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(MailTemplateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				list = (List<MailTemplate>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
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
	public MailTemplate findByg_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchMailTemplateException, SystemException {
		MailTemplate mailTemplate = fetchByg_First(groupId, orderByComparator);

		if (mailTemplate != null) {
			return mailTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMailTemplateException(msg.toString());
	}

	/**
	 * Returns the first mail template in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mail template, or <code>null</code> if a matching mail template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailTemplate fetchByg_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<MailTemplate> list = findByg(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public MailTemplate findByg_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchMailTemplateException, SystemException {
		MailTemplate mailTemplate = fetchByg_Last(groupId, orderByComparator);

		if (mailTemplate != null) {
			return mailTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMailTemplateException(msg.toString());
	}

	/**
	 * Returns the last mail template in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mail template, or <code>null</code> if a matching mail template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailTemplate fetchByg_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByg(groupId);

		List<MailTemplate> list = findByg(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public MailTemplate[] findByg_PrevAndNext(long idTemplate, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchMailTemplateException, SystemException {
		MailTemplate mailTemplate = findByPrimaryKey(idTemplate);

		Session session = null;

		try {
			session = openSession();

			MailTemplate[] array = new MailTemplateImpl[3];

			array[0] = getByg_PrevAndNext(session, mailTemplate, groupId,
					orderByComparator, true);

			array[1] = mailTemplate;

			array[2] = getByg_PrevAndNext(session, mailTemplate, groupId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MailTemplate getByg_PrevAndNext(Session session,
		MailTemplate mailTemplate, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MAILTEMPLATE_WHERE);

		query.append(_FINDER_COLUMN_G_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(MailTemplateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(mailTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MailTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the mail templates that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching mail templates that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MailTemplate> filterFindByg(long groupId)
		throws SystemException {
		return filterFindByg(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<MailTemplate> filterFindByg(long groupId, int start, int end)
		throws SystemException {
		return filterFindByg(groupId, start, end, null);
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
	public List<MailTemplate> filterFindByg(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByg(groupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MAILTEMPLATE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MAILTEMPLATE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MAILTEMPLATE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MailTemplateModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MailTemplateModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MailTemplate.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, MailTemplateImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, MailTemplateImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<MailTemplate>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
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
	public MailTemplate[] filterFindByg_PrevAndNext(long idTemplate,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchMailTemplateException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByg_PrevAndNext(idTemplate, groupId, orderByComparator);
		}

		MailTemplate mailTemplate = findByPrimaryKey(idTemplate);

		Session session = null;

		try {
			session = openSession();

			MailTemplate[] array = new MailTemplateImpl[3];

			array[0] = filterGetByg_PrevAndNext(session, mailTemplate, groupId,
					orderByComparator, true);

			array[1] = mailTemplate;

			array[2] = filterGetByg_PrevAndNext(session, mailTemplate, groupId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MailTemplate filterGetByg_PrevAndNext(Session session,
		MailTemplate mailTemplate, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MAILTEMPLATE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MAILTEMPLATE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MAILTEMPLATE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MailTemplateModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MailTemplateModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MailTemplate.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, MailTemplateImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, MailTemplateImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(mailTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MailTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the mail templates where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching mail templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<MailTemplate> findByc(long companyId) throws SystemException {
		return findByc(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<MailTemplate> findByc(long companyId, int start, int end)
		throws SystemException {
		return findByc(companyId, start, end, null);
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
	public List<MailTemplate> findByc(long companyId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<MailTemplate> list = (List<MailTemplate>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (MailTemplate mailTemplate : list) {
				if ((companyId != mailTemplate.getCompanyId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MAILTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(MailTemplateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<MailTemplate>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
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
	public MailTemplate findByc_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchMailTemplateException, SystemException {
		MailTemplate mailTemplate = fetchByc_First(companyId, orderByComparator);

		if (mailTemplate != null) {
			return mailTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMailTemplateException(msg.toString());
	}

	/**
	 * Returns the first mail template in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mail template, or <code>null</code> if a matching mail template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailTemplate fetchByc_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<MailTemplate> list = findByc(companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public MailTemplate findByc_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchMailTemplateException, SystemException {
		MailTemplate mailTemplate = fetchByc_Last(companyId, orderByComparator);

		if (mailTemplate != null) {
			return mailTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMailTemplateException(msg.toString());
	}

	/**
	 * Returns the last mail template in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mail template, or <code>null</code> if a matching mail template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailTemplate fetchByc_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByc(companyId);

		List<MailTemplate> list = findByc(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public MailTemplate[] findByc_PrevAndNext(long idTemplate, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchMailTemplateException, SystemException {
		MailTemplate mailTemplate = findByPrimaryKey(idTemplate);

		Session session = null;

		try {
			session = openSession();

			MailTemplate[] array = new MailTemplateImpl[3];

			array[0] = getByc_PrevAndNext(session, mailTemplate, companyId,
					orderByComparator, true);

			array[1] = mailTemplate;

			array[2] = getByc_PrevAndNext(session, mailTemplate, companyId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MailTemplate getByc_PrevAndNext(Session session,
		MailTemplate mailTemplate, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MAILTEMPLATE_WHERE);

		query.append(_FINDER_COLUMN_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(MailTemplateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(mailTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MailTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the mail templates.
	 *
	 * @return the mail templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<MailTemplate> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<MailTemplate> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	public List<MailTemplate> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<MailTemplate> list = (List<MailTemplate>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_MAILTEMPLATE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MAILTEMPLATE.concat(MailTemplateModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<MailTemplate>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<MailTemplate>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the mail templates where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (MailTemplate mailTemplate : findByUuid(uuid)) {
			remove(mailTemplate);
		}
	}

	/**
	 * Removes the mail template where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the mail template that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public MailTemplate removeByUUID_G(String uuid, long groupId)
		throws NoSuchMailTemplateException, SystemException {
		MailTemplate mailTemplate = findByUUID_G(uuid, groupId);

		return remove(mailTemplate);
	}

	/**
	 * Removes all the mail templates where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByg(long groupId) throws SystemException {
		for (MailTemplate mailTemplate : findByg(groupId)) {
			remove(mailTemplate);
		}
	}

	/**
	 * Removes all the mail templates where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByc(long companyId) throws SystemException {
		for (MailTemplate mailTemplate : findByc(companyId)) {
			remove(mailTemplate);
		}
	}

	/**
	 * Removes all the mail templates from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (MailTemplate mailTemplate : findAll()) {
			remove(mailTemplate);
		}
	}

	/**
	 * Returns the number of mail templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching mail templates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MAILTEMPLATE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of mail templates where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching mail templates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MAILTEMPLATE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_G_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of mail templates where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching mail templates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByg(long groupId) throws SystemException {
		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MAILTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of mail templates that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching mail templates that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByg(long groupId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByg(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_MAILTEMPLATE_WHERE);

		query.append(_FINDER_COLUMN_G_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MailTemplate.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of mail templates where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching mail templates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByc(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MAILTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of mail templates.
	 *
	 * @return the number of mail templates
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MAILTEMPLATE);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the mail template persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.tls.liferaylms.mail.model.MailTemplate")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<MailTemplate>> listenersList = new ArrayList<ModelListener<MailTemplate>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<MailTemplate>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(MailTemplateImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = AuditSendMailsPersistence.class)
	protected AuditSendMailsPersistence auditSendMailsPersistence;
	@BeanReference(type = MailJobPersistence.class)
	protected MailJobPersistence mailJobPersistence;
	@BeanReference(type = MailTemplatePersistence.class)
	protected MailTemplatePersistence mailTemplatePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_MAILTEMPLATE = "SELECT mailTemplate FROM MailTemplate mailTemplate";
	private static final String _SQL_SELECT_MAILTEMPLATE_WHERE = "SELECT mailTemplate FROM MailTemplate mailTemplate WHERE ";
	private static final String _SQL_COUNT_MAILTEMPLATE = "SELECT COUNT(mailTemplate) FROM MailTemplate mailTemplate";
	private static final String _SQL_COUNT_MAILTEMPLATE_WHERE = "SELECT COUNT(mailTemplate) FROM MailTemplate mailTemplate WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "mailTemplate.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "mailTemplate.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(mailTemplate.uuid IS NULL OR mailTemplate.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "mailTemplate.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "mailTemplate.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(mailTemplate.uuid IS NULL OR mailTemplate.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "mailTemplate.groupId = ?";
	private static final String _FINDER_COLUMN_G_GROUPID_2 = "mailTemplate.groupId = ?";
	private static final String _FINDER_COLUMN_C_COMPANYID_2 = "mailTemplate.companyId = ?";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "mailTemplate.idTemplate";
	private static final String _FILTER_SQL_SELECT_MAILTEMPLATE_WHERE = "SELECT DISTINCT {mailTemplate.*} FROM lmsmail_MailTemplate mailTemplate WHERE ";
	private static final String _FILTER_SQL_SELECT_MAILTEMPLATE_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {lmsmail_MailTemplate.*} FROM (SELECT DISTINCT mailTemplate.idTemplate FROM lmsmail_MailTemplate mailTemplate WHERE ";
	private static final String _FILTER_SQL_SELECT_MAILTEMPLATE_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN lmsmail_MailTemplate ON TEMP_TABLE.idTemplate = lmsmail_MailTemplate.idTemplate";
	private static final String _FILTER_SQL_COUNT_MAILTEMPLATE_WHERE = "SELECT COUNT(DISTINCT mailTemplate.idTemplate) AS COUNT_VALUE FROM lmsmail_MailTemplate mailTemplate WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "mailTemplate";
	private static final String _FILTER_ENTITY_TABLE = "lmsmail_MailTemplate";
	private static final String _ORDER_BY_ENTITY_ALIAS = "mailTemplate.";
	private static final String _ORDER_BY_ENTITY_TABLE = "lmsmail_MailTemplate.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MailTemplate exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MailTemplate exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(MailTemplatePersistenceImpl.class);
	private static MailTemplate _nullMailTemplate = new MailTemplateImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<MailTemplate> toCacheModel() {
				return _nullMailTemplateCacheModel;
			}
		};

	private static CacheModel<MailTemplate> _nullMailTemplateCacheModel = new CacheModel<MailTemplate>() {
			public MailTemplate toEntityModel() {
				return _nullMailTemplate;
			}
		};
}