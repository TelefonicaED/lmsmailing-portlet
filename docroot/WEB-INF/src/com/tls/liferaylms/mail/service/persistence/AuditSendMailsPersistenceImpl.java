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
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.tls.liferaylms.mail.NoSuchAuditSendMailsException;
import com.tls.liferaylms.mail.model.AuditSendMails;
import com.tls.liferaylms.mail.model.impl.AuditSendMailsImpl;
import com.tls.liferaylms.mail.model.impl.AuditSendMailsModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the audit send mails service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author je03042
 * @see AuditSendMailsPersistence
 * @see AuditSendMailsUtil
 * @generated
 */
public class AuditSendMailsPersistenceImpl extends BasePersistenceImpl<AuditSendMails>
	implements AuditSendMailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AuditSendMailsUtil} to access the audit send mails persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AuditSendMailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(AuditSendMailsModelImpl.ENTITY_CACHE_ENABLED,
			AuditSendMailsModelImpl.FINDER_CACHE_ENABLED,
			AuditSendMailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(AuditSendMailsModelImpl.ENTITY_CACHE_ENABLED,
			AuditSendMailsModelImpl.FINDER_CACHE_ENABLED,
			AuditSendMailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			AuditSendMailsModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(AuditSendMailsModelImpl.ENTITY_CACHE_ENABLED,
			AuditSendMailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(AuditSendMailsModelImpl.ENTITY_CACHE_ENABLED,
			AuditSendMailsModelImpl.FINDER_CACHE_ENABLED,
			AuditSendMailsImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			AuditSendMailsModelImpl.UUID_COLUMN_BITMASK |
			AuditSendMailsModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(AuditSendMailsModelImpl.ENTITY_CACHE_ENABLED,
			AuditSendMailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AuditSendMailsModelImpl.ENTITY_CACHE_ENABLED,
			AuditSendMailsModelImpl.FINDER_CACHE_ENABLED,
			AuditSendMailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AuditSendMailsModelImpl.ENTITY_CACHE_ENABLED,
			AuditSendMailsModelImpl.FINDER_CACHE_ENABLED,
			AuditSendMailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AuditSendMailsModelImpl.ENTITY_CACHE_ENABLED,
			AuditSendMailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the audit send mails in the entity cache if it is enabled.
	 *
	 * @param auditSendMails the audit send mails
	 */
	public void cacheResult(AuditSendMails auditSendMails) {
		EntityCacheUtil.putResult(AuditSendMailsModelImpl.ENTITY_CACHE_ENABLED,
			AuditSendMailsImpl.class, auditSendMails.getPrimaryKey(),
			auditSendMails);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				auditSendMails.getUuid(),
				Long.valueOf(auditSendMails.getGroupId())
			}, auditSendMails);

		auditSendMails.resetOriginalValues();
	}

	/**
	 * Caches the audit send mailses in the entity cache if it is enabled.
	 *
	 * @param auditSendMailses the audit send mailses
	 */
	public void cacheResult(List<AuditSendMails> auditSendMailses) {
		for (AuditSendMails auditSendMails : auditSendMailses) {
			if (EntityCacheUtil.getResult(
						AuditSendMailsModelImpl.ENTITY_CACHE_ENABLED,
						AuditSendMailsImpl.class, auditSendMails.getPrimaryKey()) == null) {
				cacheResult(auditSendMails);
			}
			else {
				auditSendMails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all audit send mailses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AuditSendMailsImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AuditSendMailsImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the audit send mails.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AuditSendMails auditSendMails) {
		EntityCacheUtil.removeResult(AuditSendMailsModelImpl.ENTITY_CACHE_ENABLED,
			AuditSendMailsImpl.class, auditSendMails.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(auditSendMails);
	}

	@Override
	public void clearCache(List<AuditSendMails> auditSendMailses) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AuditSendMails auditSendMails : auditSendMailses) {
			EntityCacheUtil.removeResult(AuditSendMailsModelImpl.ENTITY_CACHE_ENABLED,
				AuditSendMailsImpl.class, auditSendMails.getPrimaryKey());

			clearUniqueFindersCache(auditSendMails);
		}
	}

	protected void clearUniqueFindersCache(AuditSendMails auditSendMails) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				auditSendMails.getUuid(),
				Long.valueOf(auditSendMails.getGroupId())
			});
	}

	/**
	 * Creates a new audit send mails with the primary key. Does not add the audit send mails to the database.
	 *
	 * @param auditSendMailsId the primary key for the new audit send mails
	 * @return the new audit send mails
	 */
	public AuditSendMails create(long auditSendMailsId) {
		AuditSendMails auditSendMails = new AuditSendMailsImpl();

		auditSendMails.setNew(true);
		auditSendMails.setPrimaryKey(auditSendMailsId);

		String uuid = PortalUUIDUtil.generate();

		auditSendMails.setUuid(uuid);

		return auditSendMails;
	}

	/**
	 * Removes the audit send mails with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditSendMailsId the primary key of the audit send mails
	 * @return the audit send mails that was removed
	 * @throws com.tls.liferaylms.mail.NoSuchAuditSendMailsException if a audit send mails with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditSendMails remove(long auditSendMailsId)
		throws NoSuchAuditSendMailsException, SystemException {
		return remove(Long.valueOf(auditSendMailsId));
	}

	/**
	 * Removes the audit send mails with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the audit send mails
	 * @return the audit send mails that was removed
	 * @throws com.tls.liferaylms.mail.NoSuchAuditSendMailsException if a audit send mails with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AuditSendMails remove(Serializable primaryKey)
		throws NoSuchAuditSendMailsException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AuditSendMails auditSendMails = (AuditSendMails)session.get(AuditSendMailsImpl.class,
					primaryKey);

			if (auditSendMails == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAuditSendMailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(auditSendMails);
		}
		catch (NoSuchAuditSendMailsException nsee) {
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
	protected AuditSendMails removeImpl(AuditSendMails auditSendMails)
		throws SystemException {
		auditSendMails = toUnwrappedModel(auditSendMails);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, auditSendMails);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(auditSendMails);

		return auditSendMails;
	}

	@Override
	public AuditSendMails updateImpl(
		com.tls.liferaylms.mail.model.AuditSendMails auditSendMails,
		boolean merge) throws SystemException {
		auditSendMails = toUnwrappedModel(auditSendMails);

		boolean isNew = auditSendMails.isNew();

		AuditSendMailsModelImpl auditSendMailsModelImpl = (AuditSendMailsModelImpl)auditSendMails;

		if (Validator.isNull(auditSendMails.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			auditSendMails.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, auditSendMails, merge);

			auditSendMails.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AuditSendMailsModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((auditSendMailsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						auditSendMailsModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { auditSendMailsModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}
		}

		EntityCacheUtil.putResult(AuditSendMailsModelImpl.ENTITY_CACHE_ENABLED,
			AuditSendMailsImpl.class, auditSendMails.getPrimaryKey(),
			auditSendMails);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					auditSendMails.getUuid(),
					Long.valueOf(auditSendMails.getGroupId())
				}, auditSendMails);
		}
		else {
			if ((auditSendMailsModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						auditSendMailsModelImpl.getOriginalUuid(),
						Long.valueOf(auditSendMailsModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
					new Object[] {
						auditSendMails.getUuid(),
						Long.valueOf(auditSendMails.getGroupId())
					}, auditSendMails);
			}
		}

		return auditSendMails;
	}

	protected AuditSendMails toUnwrappedModel(AuditSendMails auditSendMails) {
		if (auditSendMails instanceof AuditSendMailsImpl) {
			return auditSendMails;
		}

		AuditSendMailsImpl auditSendMailsImpl = new AuditSendMailsImpl();

		auditSendMailsImpl.setNew(auditSendMails.isNew());
		auditSendMailsImpl.setPrimaryKey(auditSendMails.getPrimaryKey());

		auditSendMailsImpl.setUuid(auditSendMails.getUuid());
		auditSendMailsImpl.setAuditSendMailsId(auditSendMails.getAuditSendMailsId());
		auditSendMailsImpl.setUserId(auditSendMails.getUserId());
		auditSendMailsImpl.setTemplateId(auditSendMails.getTemplateId());
		auditSendMailsImpl.setGroupId(auditSendMails.getGroupId());
		auditSendMailsImpl.setSendDate(auditSendMails.getSendDate());

		return auditSendMailsImpl;
	}

	/**
	 * Returns the audit send mails with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the audit send mails
	 * @return the audit send mails
	 * @throws com.liferay.portal.NoSuchModelException if a audit send mails with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AuditSendMails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the audit send mails with the primary key or throws a {@link com.tls.liferaylms.mail.NoSuchAuditSendMailsException} if it could not be found.
	 *
	 * @param auditSendMailsId the primary key of the audit send mails
	 * @return the audit send mails
	 * @throws com.tls.liferaylms.mail.NoSuchAuditSendMailsException if a audit send mails with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditSendMails findByPrimaryKey(long auditSendMailsId)
		throws NoSuchAuditSendMailsException, SystemException {
		AuditSendMails auditSendMails = fetchByPrimaryKey(auditSendMailsId);

		if (auditSendMails == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + auditSendMailsId);
			}

			throw new NoSuchAuditSendMailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				auditSendMailsId);
		}

		return auditSendMails;
	}

	/**
	 * Returns the audit send mails with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the audit send mails
	 * @return the audit send mails, or <code>null</code> if a audit send mails with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AuditSendMails fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the audit send mails with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param auditSendMailsId the primary key of the audit send mails
	 * @return the audit send mails, or <code>null</code> if a audit send mails with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditSendMails fetchByPrimaryKey(long auditSendMailsId)
		throws SystemException {
		AuditSendMails auditSendMails = (AuditSendMails)EntityCacheUtil.getResult(AuditSendMailsModelImpl.ENTITY_CACHE_ENABLED,
				AuditSendMailsImpl.class, auditSendMailsId);

		if (auditSendMails == _nullAuditSendMails) {
			return null;
		}

		if (auditSendMails == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				auditSendMails = (AuditSendMails)session.get(AuditSendMailsImpl.class,
						Long.valueOf(auditSendMailsId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (auditSendMails != null) {
					cacheResult(auditSendMails);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AuditSendMailsModelImpl.ENTITY_CACHE_ENABLED,
						AuditSendMailsImpl.class, auditSendMailsId,
						_nullAuditSendMails);
				}

				closeSession(session);
			}
		}

		return auditSendMails;
	}

	/**
	 * Returns all the audit send mailses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching audit send mailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditSendMails> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<AuditSendMails> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
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
	public List<AuditSendMails> findByUuid(String uuid, int start, int end,
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

		List<AuditSendMails> list = (List<AuditSendMails>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AuditSendMails auditSendMails : list) {
				if (!Validator.equals(uuid, auditSendMails.getUuid())) {
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
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_AUDITSENDMAILS_WHERE);

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

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<AuditSendMails>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first audit send mails in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit send mails
	 * @throws com.tls.liferaylms.mail.NoSuchAuditSendMailsException if a matching audit send mails could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditSendMails findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchAuditSendMailsException, SystemException {
		AuditSendMails auditSendMails = fetchByUuid_First(uuid,
				orderByComparator);

		if (auditSendMails != null) {
			return auditSendMails;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditSendMailsException(msg.toString());
	}

	/**
	 * Returns the first audit send mails in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit send mails, or <code>null</code> if a matching audit send mails could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditSendMails fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<AuditSendMails> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public AuditSendMails findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchAuditSendMailsException, SystemException {
		AuditSendMails auditSendMails = fetchByUuid_Last(uuid, orderByComparator);

		if (auditSendMails != null) {
			return auditSendMails;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditSendMailsException(msg.toString());
	}

	/**
	 * Returns the last audit send mails in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit send mails, or <code>null</code> if a matching audit send mails could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditSendMails fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<AuditSendMails> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public AuditSendMails[] findByUuid_PrevAndNext(long auditSendMailsId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchAuditSendMailsException, SystemException {
		AuditSendMails auditSendMails = findByPrimaryKey(auditSendMailsId);

		Session session = null;

		try {
			session = openSession();

			AuditSendMails[] array = new AuditSendMailsImpl[3];

			array[0] = getByUuid_PrevAndNext(session, auditSendMails, uuid,
					orderByComparator, true);

			array[1] = auditSendMails;

			array[2] = getByUuid_PrevAndNext(session, auditSendMails, uuid,
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

	protected AuditSendMails getByUuid_PrevAndNext(Session session,
		AuditSendMails auditSendMails, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_AUDITSENDMAILS_WHERE);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(auditSendMails);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AuditSendMails> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
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
	public AuditSendMails findByUUID_G(String uuid, long groupId)
		throws NoSuchAuditSendMailsException, SystemException {
		AuditSendMails auditSendMails = fetchByUUID_G(uuid, groupId);

		if (auditSendMails == null) {
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

			throw new NoSuchAuditSendMailsException(msg.toString());
		}

		return auditSendMails;
	}

	/**
	 * Returns the audit send mails where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching audit send mails, or <code>null</code> if a matching audit send mails could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditSendMails fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
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
	public AuditSendMails fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof AuditSendMails) {
			AuditSendMails auditSendMails = (AuditSendMails)result;

			if (!Validator.equals(uuid, auditSendMails.getUuid()) ||
					(groupId != auditSendMails.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_AUDITSENDMAILS_WHERE);

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

				List<AuditSendMails> list = q.list();

				result = list;

				AuditSendMails auditSendMails = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					auditSendMails = list.get(0);

					cacheResult(auditSendMails);

					if ((auditSendMails.getUuid() == null) ||
							!auditSendMails.getUuid().equals(uuid) ||
							(auditSendMails.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, auditSendMails);
					}
				}

				return auditSendMails;
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
				return (AuditSendMails)result;
			}
		}
	}

	/**
	 * Returns all the audit send mailses.
	 *
	 * @return the audit send mailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditSendMails> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<AuditSendMails> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	public List<AuditSendMails> findAll(int start, int end,
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

		List<AuditSendMails> list = (List<AuditSendMails>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_AUDITSENDMAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_AUDITSENDMAILS;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AuditSendMails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AuditSendMails>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the audit send mailses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (AuditSendMails auditSendMails : findByUuid(uuid)) {
			remove(auditSendMails);
		}
	}

	/**
	 * Removes the audit send mails where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the audit send mails that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AuditSendMails removeByUUID_G(String uuid, long groupId)
		throws NoSuchAuditSendMailsException, SystemException {
		AuditSendMails auditSendMails = findByUUID_G(uuid, groupId);

		return remove(auditSendMails);
	}

	/**
	 * Removes all the audit send mailses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AuditSendMails auditSendMails : findAll()) {
			remove(auditSendMails);
		}
	}

	/**
	 * Returns the number of audit send mailses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching audit send mailses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_AUDITSENDMAILS_WHERE);

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
	 * Returns the number of audit send mailses where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching audit send mailses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_AUDITSENDMAILS_WHERE);

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
	 * Returns the number of audit send mailses.
	 *
	 * @return the number of audit send mailses
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_AUDITSENDMAILS);

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
	 * Initializes the audit send mails persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.tls.liferaylms.mail.model.AuditSendMails")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AuditSendMails>> listenersList = new ArrayList<ModelListener<AuditSendMails>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<AuditSendMails>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AuditSendMailsImpl.class.getName());
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
	private static final String _SQL_SELECT_AUDITSENDMAILS = "SELECT auditSendMails FROM AuditSendMails auditSendMails";
	private static final String _SQL_SELECT_AUDITSENDMAILS_WHERE = "SELECT auditSendMails FROM AuditSendMails auditSendMails WHERE ";
	private static final String _SQL_COUNT_AUDITSENDMAILS = "SELECT COUNT(auditSendMails) FROM AuditSendMails auditSendMails";
	private static final String _SQL_COUNT_AUDITSENDMAILS_WHERE = "SELECT COUNT(auditSendMails) FROM AuditSendMails auditSendMails WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "auditSendMails.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "auditSendMails.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(auditSendMails.uuid IS NULL OR auditSendMails.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "auditSendMails.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "auditSendMails.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(auditSendMails.uuid IS NULL OR auditSendMails.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "auditSendMails.groupId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "auditSendMails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AuditSendMails exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AuditSendMails exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AuditSendMailsPersistenceImpl.class);
	private static AuditSendMails _nullAuditSendMails = new AuditSendMailsImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AuditSendMails> toCacheModel() {
				return _nullAuditSendMailsCacheModel;
			}
		};

	private static CacheModel<AuditSendMails> _nullAuditSendMailsCacheModel = new CacheModel<AuditSendMails>() {
			public AuditSendMails toEntityModel() {
				return _nullAuditSendMails;
			}
		};
}