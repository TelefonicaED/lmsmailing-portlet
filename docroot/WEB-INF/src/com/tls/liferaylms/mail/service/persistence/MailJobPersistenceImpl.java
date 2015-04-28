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

import com.tls.liferaylms.mail.NoSuchMailJobException;
import com.tls.liferaylms.mail.model.MailJob;
import com.tls.liferaylms.mail.model.impl.MailJobImpl;
import com.tls.liferaylms.mail.model.impl.MailJobModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the mail job service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author je03042
 * @see MailJobPersistence
 * @see MailJobUtil
 * @generated
 */
public class MailJobPersistenceImpl extends BasePersistenceImpl<MailJob>
	implements MailJobPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MailJobUtil} to access the mail job persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MailJobImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobModelImpl.FINDER_CACHE_ENABLED, MailJobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobModelImpl.FINDER_CACHE_ENABLED, MailJobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			MailJobModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobModelImpl.FINDER_CACHE_ENABLED, MailJobImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			MailJobModelImpl.UUID_COLUMN_BITMASK |
			MailJobModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U = new FinderPath(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobModelImpl.FINDER_CACHE_ENABLED, MailJobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByu",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U = new FinderPath(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobModelImpl.FINDER_CACHE_ENABLED, MailJobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByu",
			new String[] { Long.class.getName() },
			MailJobModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U = new FinderPath(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByu",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G = new FinderPath(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobModelImpl.FINDER_CACHE_ENABLED, MailJobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByg",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G = new FinderPath(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobModelImpl.FINDER_CACHE_ENABLED, MailJobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByg",
			new String[] { Long.class.getName() },
			MailJobModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G = new FinderPath(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByg",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GP = new FinderPath(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobModelImpl.FINDER_CACHE_ENABLED, MailJobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBygp",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GP = new FinderPath(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobModelImpl.FINDER_CACHE_ENABLED, MailJobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBygp",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			MailJobModelImpl.GROUPID_COLUMN_BITMASK |
			MailJobModelImpl.PROCESSED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GP = new FinderPath(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBygp",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C = new FinderPath(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobModelImpl.FINDER_CACHE_ENABLED, MailJobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByc",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C = new FinderPath(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobModelImpl.FINDER_CACHE_ENABLED, MailJobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByc",
			new String[] { Long.class.getName() },
			MailJobModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C = new FinderPath(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByc",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobModelImpl.FINDER_CACHE_ENABLED, MailJobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobModelImpl.FINDER_CACHE_ENABLED, MailJobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the mail job in the entity cache if it is enabled.
	 *
	 * @param mailJob the mail job
	 */
	public void cacheResult(MailJob mailJob) {
		EntityCacheUtil.putResult(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobImpl.class, mailJob.getPrimaryKey(), mailJob);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { mailJob.getUuid(), Long.valueOf(mailJob.getGroupId()) },
			mailJob);

		mailJob.resetOriginalValues();
	}

	/**
	 * Caches the mail jobs in the entity cache if it is enabled.
	 *
	 * @param mailJobs the mail jobs
	 */
	public void cacheResult(List<MailJob> mailJobs) {
		for (MailJob mailJob : mailJobs) {
			if (EntityCacheUtil.getResult(
						MailJobModelImpl.ENTITY_CACHE_ENABLED,
						MailJobImpl.class, mailJob.getPrimaryKey()) == null) {
				cacheResult(mailJob);
			}
			else {
				mailJob.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all mail jobs.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(MailJobImpl.class.getName());
		}

		EntityCacheUtil.clearCache(MailJobImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the mail job.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MailJob mailJob) {
		EntityCacheUtil.removeResult(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobImpl.class, mailJob.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(mailJob);
	}

	@Override
	public void clearCache(List<MailJob> mailJobs) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MailJob mailJob : mailJobs) {
			EntityCacheUtil.removeResult(MailJobModelImpl.ENTITY_CACHE_ENABLED,
				MailJobImpl.class, mailJob.getPrimaryKey());

			clearUniqueFindersCache(mailJob);
		}
	}

	protected void clearUniqueFindersCache(MailJob mailJob) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { mailJob.getUuid(), Long.valueOf(mailJob.getGroupId()) });
	}

	/**
	 * Creates a new mail job with the primary key. Does not add the mail job to the database.
	 *
	 * @param idJob the primary key for the new mail job
	 * @return the new mail job
	 */
	public MailJob create(long idJob) {
		MailJob mailJob = new MailJobImpl();

		mailJob.setNew(true);
		mailJob.setPrimaryKey(idJob);

		String uuid = PortalUUIDUtil.generate();

		mailJob.setUuid(uuid);

		return mailJob;
	}

	/**
	 * Removes the mail job with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param idJob the primary key of the mail job
	 * @return the mail job that was removed
	 * @throws com.tls.liferaylms.mail.NoSuchMailJobException if a mail job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailJob remove(long idJob)
		throws NoSuchMailJobException, SystemException {
		return remove(Long.valueOf(idJob));
	}

	/**
	 * Removes the mail job with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the mail job
	 * @return the mail job that was removed
	 * @throws com.tls.liferaylms.mail.NoSuchMailJobException if a mail job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MailJob remove(Serializable primaryKey)
		throws NoSuchMailJobException, SystemException {
		Session session = null;

		try {
			session = openSession();

			MailJob mailJob = (MailJob)session.get(MailJobImpl.class, primaryKey);

			if (mailJob == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMailJobException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(mailJob);
		}
		catch (NoSuchMailJobException nsee) {
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
	protected MailJob removeImpl(MailJob mailJob) throws SystemException {
		mailJob = toUnwrappedModel(mailJob);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, mailJob);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(mailJob);

		return mailJob;
	}

	@Override
	public MailJob updateImpl(com.tls.liferaylms.mail.model.MailJob mailJob,
		boolean merge) throws SystemException {
		mailJob = toUnwrappedModel(mailJob);

		boolean isNew = mailJob.isNew();

		MailJobModelImpl mailJobModelImpl = (MailJobModelImpl)mailJob;

		if (Validator.isNull(mailJob.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			mailJob.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, mailJob, merge);

			mailJob.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !MailJobModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((mailJobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { mailJobModelImpl.getOriginalUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { mailJobModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((mailJobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(mailJobModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U,
					args);

				args = new Object[] { Long.valueOf(mailJobModelImpl.getUserId()) };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U,
					args);
			}

			if ((mailJobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(mailJobModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G,
					args);

				args = new Object[] { Long.valueOf(mailJobModelImpl.getGroupId()) };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G,
					args);
			}

			if ((mailJobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(mailJobModelImpl.getOriginalGroupId()),
						Boolean.valueOf(mailJobModelImpl.getOriginalProcessed())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GP, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GP,
					args);

				args = new Object[] {
						Long.valueOf(mailJobModelImpl.getGroupId()),
						Boolean.valueOf(mailJobModelImpl.getProcessed())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GP, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GP,
					args);
			}

			if ((mailJobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(mailJobModelImpl.getOriginalCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C,
					args);

				args = new Object[] {
						Long.valueOf(mailJobModelImpl.getCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C,
					args);
			}
		}

		EntityCacheUtil.putResult(MailJobModelImpl.ENTITY_CACHE_ENABLED,
			MailJobImpl.class, mailJob.getPrimaryKey(), mailJob);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					mailJob.getUuid(), Long.valueOf(mailJob.getGroupId())
				}, mailJob);
		}
		else {
			if ((mailJobModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						mailJobModelImpl.getOriginalUuid(),
						Long.valueOf(mailJobModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
					new Object[] {
						mailJob.getUuid(), Long.valueOf(mailJob.getGroupId())
					}, mailJob);
			}
		}

		return mailJob;
	}

	protected MailJob toUnwrappedModel(MailJob mailJob) {
		if (mailJob instanceof MailJobImpl) {
			return mailJob;
		}

		MailJobImpl mailJobImpl = new MailJobImpl();

		mailJobImpl.setNew(mailJob.isNew());
		mailJobImpl.setPrimaryKey(mailJob.getPrimaryKey());

		mailJobImpl.setUuid(mailJob.getUuid());
		mailJobImpl.setIdJob(mailJob.getIdJob());
		mailJobImpl.setCompanyId(mailJob.getCompanyId());
		mailJobImpl.setGroupId(mailJob.getGroupId());
		mailJobImpl.setUserId(mailJob.getUserId());
		mailJobImpl.setIdTemplate(mailJob.getIdTemplate());
		mailJobImpl.setConditionClassName(mailJob.getConditionClassName());
		mailJobImpl.setConditionClassPK(mailJob.getConditionClassPK());
		mailJobImpl.setConditionStatus(mailJob.getConditionStatus());
		mailJobImpl.setDateClassName(mailJob.getDateClassName());
		mailJobImpl.setDateClassPK(mailJob.getDateClassPK());
		mailJobImpl.setDateReferenceDate(mailJob.getDateReferenceDate());
		mailJobImpl.setDateShift(mailJob.getDateShift());
		mailJobImpl.setTeamId(mailJob.getTeamId());
		mailJobImpl.setProcessed(mailJob.isProcessed());

		return mailJobImpl;
	}

	/**
	 * Returns the mail job with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the mail job
	 * @return the mail job
	 * @throws com.liferay.portal.NoSuchModelException if a mail job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MailJob findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the mail job with the primary key or throws a {@link com.tls.liferaylms.mail.NoSuchMailJobException} if it could not be found.
	 *
	 * @param idJob the primary key of the mail job
	 * @return the mail job
	 * @throws com.tls.liferaylms.mail.NoSuchMailJobException if a mail job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailJob findByPrimaryKey(long idJob)
		throws NoSuchMailJobException, SystemException {
		MailJob mailJob = fetchByPrimaryKey(idJob);

		if (mailJob == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + idJob);
			}

			throw new NoSuchMailJobException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				idJob);
		}

		return mailJob;
	}

	/**
	 * Returns the mail job with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the mail job
	 * @return the mail job, or <code>null</code> if a mail job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MailJob fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the mail job with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param idJob the primary key of the mail job
	 * @return the mail job, or <code>null</code> if a mail job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailJob fetchByPrimaryKey(long idJob) throws SystemException {
		MailJob mailJob = (MailJob)EntityCacheUtil.getResult(MailJobModelImpl.ENTITY_CACHE_ENABLED,
				MailJobImpl.class, idJob);

		if (mailJob == _nullMailJob) {
			return null;
		}

		if (mailJob == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				mailJob = (MailJob)session.get(MailJobImpl.class,
						Long.valueOf(idJob));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (mailJob != null) {
					cacheResult(mailJob);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(MailJobModelImpl.ENTITY_CACHE_ENABLED,
						MailJobImpl.class, idJob, _nullMailJob);
				}

				closeSession(session);
			}
		}

		return mailJob;
	}

	/**
	 * Returns all the mail jobs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching mail jobs
	 * @throws SystemException if a system exception occurred
	 */
	public List<MailJob> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<MailJob> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
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
	public List<MailJob> findByUuid(String uuid, int start, int end,
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

		List<MailJob> list = (List<MailJob>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (MailJob mailJob : list) {
				if (!Validator.equals(uuid, mailJob.getUuid())) {
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

			query.append(_SQL_SELECT_MAILJOB_WHERE);

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

				list = (List<MailJob>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first mail job in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mail job
	 * @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailJob findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchMailJobException, SystemException {
		MailJob mailJob = fetchByUuid_First(uuid, orderByComparator);

		if (mailJob != null) {
			return mailJob;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMailJobException(msg.toString());
	}

	/**
	 * Returns the first mail job in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mail job, or <code>null</code> if a matching mail job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailJob fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<MailJob> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public MailJob findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchMailJobException, SystemException {
		MailJob mailJob = fetchByUuid_Last(uuid, orderByComparator);

		if (mailJob != null) {
			return mailJob;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMailJobException(msg.toString());
	}

	/**
	 * Returns the last mail job in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mail job, or <code>null</code> if a matching mail job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailJob fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<MailJob> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public MailJob[] findByUuid_PrevAndNext(long idJob, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchMailJobException, SystemException {
		MailJob mailJob = findByPrimaryKey(idJob);

		Session session = null;

		try {
			session = openSession();

			MailJob[] array = new MailJobImpl[3];

			array[0] = getByUuid_PrevAndNext(session, mailJob, uuid,
					orderByComparator, true);

			array[1] = mailJob;

			array[2] = getByUuid_PrevAndNext(session, mailJob, uuid,
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

	protected MailJob getByUuid_PrevAndNext(Session session, MailJob mailJob,
		String uuid, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MAILJOB_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(mailJob);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MailJob> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
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
	public MailJob findByUUID_G(String uuid, long groupId)
		throws NoSuchMailJobException, SystemException {
		MailJob mailJob = fetchByUUID_G(uuid, groupId);

		if (mailJob == null) {
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

			throw new NoSuchMailJobException(msg.toString());
		}

		return mailJob;
	}

	/**
	 * Returns the mail job where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching mail job, or <code>null</code> if a matching mail job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailJob fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
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
	public MailJob fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof MailJob) {
			MailJob mailJob = (MailJob)result;

			if (!Validator.equals(uuid, mailJob.getUuid()) ||
					(groupId != mailJob.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_MAILJOB_WHERE);

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

				List<MailJob> list = q.list();

				result = list;

				MailJob mailJob = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					mailJob = list.get(0);

					cacheResult(mailJob);

					if ((mailJob.getUuid() == null) ||
							!mailJob.getUuid().equals(uuid) ||
							(mailJob.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, mailJob);
					}
				}

				return mailJob;
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
				return (MailJob)result;
			}
		}
	}

	/**
	 * Returns all the mail jobs where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching mail jobs
	 * @throws SystemException if a system exception occurred
	 */
	public List<MailJob> findByu(long userId) throws SystemException {
		return findByu(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<MailJob> findByu(long userId, int start, int end)
		throws SystemException {
		return findByu(userId, start, end, null);
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
	public List<MailJob> findByu(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<MailJob> list = (List<MailJob>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (MailJob mailJob : list) {
				if ((userId != mailJob.getUserId())) {
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

			query.append(_SQL_SELECT_MAILJOB_WHERE);

			query.append(_FINDER_COLUMN_U_USERID_2);

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

				qPos.add(userId);

				list = (List<MailJob>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first mail job in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mail job
	 * @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailJob findByu_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchMailJobException, SystemException {
		MailJob mailJob = fetchByu_First(userId, orderByComparator);

		if (mailJob != null) {
			return mailJob;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMailJobException(msg.toString());
	}

	/**
	 * Returns the first mail job in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mail job, or <code>null</code> if a matching mail job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailJob fetchByu_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<MailJob> list = findByu(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public MailJob findByu_Last(long userId, OrderByComparator orderByComparator)
		throws NoSuchMailJobException, SystemException {
		MailJob mailJob = fetchByu_Last(userId, orderByComparator);

		if (mailJob != null) {
			return mailJob;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMailJobException(msg.toString());
	}

	/**
	 * Returns the last mail job in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mail job, or <code>null</code> if a matching mail job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailJob fetchByu_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByu(userId);

		List<MailJob> list = findByu(userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public MailJob[] findByu_PrevAndNext(long idJob, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchMailJobException, SystemException {
		MailJob mailJob = findByPrimaryKey(idJob);

		Session session = null;

		try {
			session = openSession();

			MailJob[] array = new MailJobImpl[3];

			array[0] = getByu_PrevAndNext(session, mailJob, userId,
					orderByComparator, true);

			array[1] = mailJob;

			array[2] = getByu_PrevAndNext(session, mailJob, userId,
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

	protected MailJob getByu_PrevAndNext(Session session, MailJob mailJob,
		long userId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MAILJOB_WHERE);

		query.append(_FINDER_COLUMN_U_USERID_2);

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

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(mailJob);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MailJob> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the mail jobs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching mail jobs
	 * @throws SystemException if a system exception occurred
	 */
	public List<MailJob> findByg(long groupId) throws SystemException {
		return findByg(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<MailJob> findByg(long groupId, int start, int end)
		throws SystemException {
		return findByg(groupId, start, end, null);
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
	public List<MailJob> findByg(long groupId, int start, int end,
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

		List<MailJob> list = (List<MailJob>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (MailJob mailJob : list) {
				if ((groupId != mailJob.getGroupId())) {
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

			query.append(_SQL_SELECT_MAILJOB_WHERE);

			query.append(_FINDER_COLUMN_G_GROUPID_2);

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

				qPos.add(groupId);

				list = (List<MailJob>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first mail job in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mail job
	 * @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailJob findByg_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchMailJobException, SystemException {
		MailJob mailJob = fetchByg_First(groupId, orderByComparator);

		if (mailJob != null) {
			return mailJob;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMailJobException(msg.toString());
	}

	/**
	 * Returns the first mail job in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mail job, or <code>null</code> if a matching mail job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailJob fetchByg_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<MailJob> list = findByg(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public MailJob findByg_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchMailJobException, SystemException {
		MailJob mailJob = fetchByg_Last(groupId, orderByComparator);

		if (mailJob != null) {
			return mailJob;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMailJobException(msg.toString());
	}

	/**
	 * Returns the last mail job in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mail job, or <code>null</code> if a matching mail job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailJob fetchByg_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByg(groupId);

		List<MailJob> list = findByg(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public MailJob[] findByg_PrevAndNext(long idJob, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchMailJobException, SystemException {
		MailJob mailJob = findByPrimaryKey(idJob);

		Session session = null;

		try {
			session = openSession();

			MailJob[] array = new MailJobImpl[3];

			array[0] = getByg_PrevAndNext(session, mailJob, groupId,
					orderByComparator, true);

			array[1] = mailJob;

			array[2] = getByg_PrevAndNext(session, mailJob, groupId,
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

	protected MailJob getByg_PrevAndNext(Session session, MailJob mailJob,
		long groupId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MAILJOB_WHERE);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(mailJob);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MailJob> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the mail jobs where groupId = &#63; and processed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param processed the processed
	 * @return the matching mail jobs
	 * @throws SystemException if a system exception occurred
	 */
	public List<MailJob> findBygp(long groupId, boolean processed)
		throws SystemException {
		return findBygp(groupId, processed, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	public List<MailJob> findBygp(long groupId, boolean processed, int start,
		int end) throws SystemException {
		return findBygp(groupId, processed, start, end, null);
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
	public List<MailJob> findBygp(long groupId, boolean processed, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GP;
			finderArgs = new Object[] { groupId, processed };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GP;
			finderArgs = new Object[] {
					groupId, processed,
					
					start, end, orderByComparator
				};
		}

		List<MailJob> list = (List<MailJob>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (MailJob mailJob : list) {
				if ((groupId != mailJob.getGroupId()) ||
						(processed != mailJob.getProcessed())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MAILJOB_WHERE);

			query.append(_FINDER_COLUMN_GP_GROUPID_2);

			query.append(_FINDER_COLUMN_GP_PROCESSED_2);

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

				qPos.add(groupId);

				qPos.add(processed);

				list = (List<MailJob>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first mail job in the ordered set where groupId = &#63; and processed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param processed the processed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mail job
	 * @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailJob findBygp_First(long groupId, boolean processed,
		OrderByComparator orderByComparator)
		throws NoSuchMailJobException, SystemException {
		MailJob mailJob = fetchBygp_First(groupId, processed, orderByComparator);

		if (mailJob != null) {
			return mailJob;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", processed=");
		msg.append(processed);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMailJobException(msg.toString());
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
	public MailJob fetchBygp_First(long groupId, boolean processed,
		OrderByComparator orderByComparator) throws SystemException {
		List<MailJob> list = findBygp(groupId, processed, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public MailJob findBygp_Last(long groupId, boolean processed,
		OrderByComparator orderByComparator)
		throws NoSuchMailJobException, SystemException {
		MailJob mailJob = fetchBygp_Last(groupId, processed, orderByComparator);

		if (mailJob != null) {
			return mailJob;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", processed=");
		msg.append(processed);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMailJobException(msg.toString());
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
	public MailJob fetchBygp_Last(long groupId, boolean processed,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBygp(groupId, processed);

		List<MailJob> list = findBygp(groupId, processed, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public MailJob[] findBygp_PrevAndNext(long idJob, long groupId,
		boolean processed, OrderByComparator orderByComparator)
		throws NoSuchMailJobException, SystemException {
		MailJob mailJob = findByPrimaryKey(idJob);

		Session session = null;

		try {
			session = openSession();

			MailJob[] array = new MailJobImpl[3];

			array[0] = getBygp_PrevAndNext(session, mailJob, groupId,
					processed, orderByComparator, true);

			array[1] = mailJob;

			array[2] = getBygp_PrevAndNext(session, mailJob, groupId,
					processed, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MailJob getBygp_PrevAndNext(Session session, MailJob mailJob,
		long groupId, boolean processed, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MAILJOB_WHERE);

		query.append(_FINDER_COLUMN_GP_GROUPID_2);

		query.append(_FINDER_COLUMN_GP_PROCESSED_2);

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

		qPos.add(groupId);

		qPos.add(processed);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(mailJob);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MailJob> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the mail jobs where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching mail jobs
	 * @throws SystemException if a system exception occurred
	 */
	public List<MailJob> findByc(long companyId) throws SystemException {
		return findByc(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<MailJob> findByc(long companyId, int start, int end)
		throws SystemException {
		return findByc(companyId, start, end, null);
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
	public List<MailJob> findByc(long companyId, int start, int end,
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

		List<MailJob> list = (List<MailJob>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (MailJob mailJob : list) {
				if ((companyId != mailJob.getCompanyId())) {
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

			query.append(_SQL_SELECT_MAILJOB_WHERE);

			query.append(_FINDER_COLUMN_C_COMPANYID_2);

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

				qPos.add(companyId);

				list = (List<MailJob>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first mail job in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mail job
	 * @throws com.tls.liferaylms.mail.NoSuchMailJobException if a matching mail job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailJob findByc_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchMailJobException, SystemException {
		MailJob mailJob = fetchByc_First(companyId, orderByComparator);

		if (mailJob != null) {
			return mailJob;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMailJobException(msg.toString());
	}

	/**
	 * Returns the first mail job in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mail job, or <code>null</code> if a matching mail job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailJob fetchByc_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<MailJob> list = findByc(companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public MailJob findByc_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchMailJobException, SystemException {
		MailJob mailJob = fetchByc_Last(companyId, orderByComparator);

		if (mailJob != null) {
			return mailJob;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMailJobException(msg.toString());
	}

	/**
	 * Returns the last mail job in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mail job, or <code>null</code> if a matching mail job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MailJob fetchByc_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByc(companyId);

		List<MailJob> list = findByc(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public MailJob[] findByc_PrevAndNext(long idJob, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchMailJobException, SystemException {
		MailJob mailJob = findByPrimaryKey(idJob);

		Session session = null;

		try {
			session = openSession();

			MailJob[] array = new MailJobImpl[3];

			array[0] = getByc_PrevAndNext(session, mailJob, companyId,
					orderByComparator, true);

			array[1] = mailJob;

			array[2] = getByc_PrevAndNext(session, mailJob, companyId,
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

	protected MailJob getByc_PrevAndNext(Session session, MailJob mailJob,
		long companyId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MAILJOB_WHERE);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(mailJob);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MailJob> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the mail jobs.
	 *
	 * @return the mail jobs
	 * @throws SystemException if a system exception occurred
	 */
	public List<MailJob> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<MailJob> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
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
	public List<MailJob> findAll(int start, int end,
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

		List<MailJob> list = (List<MailJob>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_MAILJOB);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MAILJOB;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<MailJob>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<MailJob>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the mail jobs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (MailJob mailJob : findByUuid(uuid)) {
			remove(mailJob);
		}
	}

	/**
	 * Removes the mail job where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the mail job that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public MailJob removeByUUID_G(String uuid, long groupId)
		throws NoSuchMailJobException, SystemException {
		MailJob mailJob = findByUUID_G(uuid, groupId);

		return remove(mailJob);
	}

	/**
	 * Removes all the mail jobs where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByu(long userId) throws SystemException {
		for (MailJob mailJob : findByu(userId)) {
			remove(mailJob);
		}
	}

	/**
	 * Removes all the mail jobs where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByg(long groupId) throws SystemException {
		for (MailJob mailJob : findByg(groupId)) {
			remove(mailJob);
		}
	}

	/**
	 * Removes all the mail jobs where groupId = &#63; and processed = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param processed the processed
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBygp(long groupId, boolean processed)
		throws SystemException {
		for (MailJob mailJob : findBygp(groupId, processed)) {
			remove(mailJob);
		}
	}

	/**
	 * Removes all the mail jobs where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByc(long companyId) throws SystemException {
		for (MailJob mailJob : findByc(companyId)) {
			remove(mailJob);
		}
	}

	/**
	 * Removes all the mail jobs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (MailJob mailJob : findAll()) {
			remove(mailJob);
		}
	}

	/**
	 * Returns the number of mail jobs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching mail jobs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MAILJOB_WHERE);

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
	 * Returns the number of mail jobs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching mail jobs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MAILJOB_WHERE);

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
	 * Returns the number of mail jobs where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching mail jobs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByu(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MAILJOB_WHERE);

			query.append(_FINDER_COLUMN_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of mail jobs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching mail jobs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByg(long groupId) throws SystemException {
		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MAILJOB_WHERE);

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
	 * Returns the number of mail jobs where groupId = &#63; and processed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param processed the processed
	 * @return the number of matching mail jobs
	 * @throws SystemException if a system exception occurred
	 */
	public int countBygp(long groupId, boolean processed)
		throws SystemException {
		Object[] finderArgs = new Object[] { groupId, processed };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GP,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MAILJOB_WHERE);

			query.append(_FINDER_COLUMN_GP_GROUPID_2);

			query.append(_FINDER_COLUMN_GP_PROCESSED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(processed);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GP, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of mail jobs where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching mail jobs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByc(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MAILJOB_WHERE);

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
	 * Returns the number of mail jobs.
	 *
	 * @return the number of mail jobs
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MAILJOB);

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
	 * Initializes the mail job persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.tls.liferaylms.mail.model.MailJob")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<MailJob>> listenersList = new ArrayList<ModelListener<MailJob>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<MailJob>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(MailJobImpl.class.getName());
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
	private static final String _SQL_SELECT_MAILJOB = "SELECT mailJob FROM MailJob mailJob";
	private static final String _SQL_SELECT_MAILJOB_WHERE = "SELECT mailJob FROM MailJob mailJob WHERE ";
	private static final String _SQL_COUNT_MAILJOB = "SELECT COUNT(mailJob) FROM MailJob mailJob";
	private static final String _SQL_COUNT_MAILJOB_WHERE = "SELECT COUNT(mailJob) FROM MailJob mailJob WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "mailJob.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "mailJob.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(mailJob.uuid IS NULL OR mailJob.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "mailJob.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "mailJob.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(mailJob.uuid IS NULL OR mailJob.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "mailJob.groupId = ?";
	private static final String _FINDER_COLUMN_U_USERID_2 = "mailJob.userId = ?";
	private static final String _FINDER_COLUMN_G_GROUPID_2 = "mailJob.groupId = ?";
	private static final String _FINDER_COLUMN_GP_GROUPID_2 = "mailJob.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GP_PROCESSED_2 = "mailJob.processed = ?";
	private static final String _FINDER_COLUMN_C_COMPANYID_2 = "mailJob.companyId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "mailJob.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MailJob exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MailJob exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(MailJobPersistenceImpl.class);
	private static MailJob _nullMailJob = new MailJobImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<MailJob> toCacheModel() {
				return _nullMailJobCacheModel;
			}
		};

	private static CacheModel<MailJob> _nullMailJobCacheModel = new CacheModel<MailJob>() {
			public MailJob toEntityModel() {
				return _nullMailJob;
			}
		};
}