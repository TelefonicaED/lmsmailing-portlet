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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.tls.liferaylms.mail.NoSuchAuditReceiverMailException;
import com.tls.liferaylms.mail.model.AuditReceiverMail;
import com.tls.liferaylms.mail.model.impl.AuditReceiverMailImpl;
import com.tls.liferaylms.mail.model.impl.AuditReceiverMailModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the audit receiver mail service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author je03042
 * @see AuditReceiverMailPersistence
 * @see AuditReceiverMailUtil
 * @generated
 */
public class AuditReceiverMailPersistenceImpl extends BasePersistenceImpl<AuditReceiverMail>
	implements AuditReceiverMailPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AuditReceiverMailUtil} to access the audit receiver mail persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AuditReceiverMailImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AUDITSENDMAIL =
		new FinderPath(AuditReceiverMailModelImpl.ENTITY_CACHE_ENABLED,
			AuditReceiverMailModelImpl.FINDER_CACHE_ENABLED,
			AuditReceiverMailImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByauditSendMail",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AUDITSENDMAIL =
		new FinderPath(AuditReceiverMailModelImpl.ENTITY_CACHE_ENABLED,
			AuditReceiverMailModelImpl.FINDER_CACHE_ENABLED,
			AuditReceiverMailImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByauditSendMail",
			new String[] { Long.class.getName() },
			AuditReceiverMailModelImpl.AUDITSENDMAILSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AUDITSENDMAIL = new FinderPath(AuditReceiverMailModelImpl.ENTITY_CACHE_ENABLED,
			AuditReceiverMailModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByauditSendMail",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AuditReceiverMailModelImpl.ENTITY_CACHE_ENABLED,
			AuditReceiverMailModelImpl.FINDER_CACHE_ENABLED,
			AuditReceiverMailImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AuditReceiverMailModelImpl.ENTITY_CACHE_ENABLED,
			AuditReceiverMailModelImpl.FINDER_CACHE_ENABLED,
			AuditReceiverMailImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AuditReceiverMailModelImpl.ENTITY_CACHE_ENABLED,
			AuditReceiverMailModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the audit receiver mail in the entity cache if it is enabled.
	 *
	 * @param auditReceiverMail the audit receiver mail
	 */
	public void cacheResult(AuditReceiverMail auditReceiverMail) {
		EntityCacheUtil.putResult(AuditReceiverMailModelImpl.ENTITY_CACHE_ENABLED,
			AuditReceiverMailImpl.class, auditReceiverMail.getPrimaryKey(),
			auditReceiverMail);

		auditReceiverMail.resetOriginalValues();
	}

	/**
	 * Caches the audit receiver mails in the entity cache if it is enabled.
	 *
	 * @param auditReceiverMails the audit receiver mails
	 */
	public void cacheResult(List<AuditReceiverMail> auditReceiverMails) {
		for (AuditReceiverMail auditReceiverMail : auditReceiverMails) {
			if (EntityCacheUtil.getResult(
						AuditReceiverMailModelImpl.ENTITY_CACHE_ENABLED,
						AuditReceiverMailImpl.class,
						auditReceiverMail.getPrimaryKey()) == null) {
				cacheResult(auditReceiverMail);
			}
			else {
				auditReceiverMail.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all audit receiver mails.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AuditReceiverMailImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AuditReceiverMailImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the audit receiver mail.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AuditReceiverMail auditReceiverMail) {
		EntityCacheUtil.removeResult(AuditReceiverMailModelImpl.ENTITY_CACHE_ENABLED,
			AuditReceiverMailImpl.class, auditReceiverMail.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AuditReceiverMail> auditReceiverMails) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AuditReceiverMail auditReceiverMail : auditReceiverMails) {
			EntityCacheUtil.removeResult(AuditReceiverMailModelImpl.ENTITY_CACHE_ENABLED,
				AuditReceiverMailImpl.class, auditReceiverMail.getPrimaryKey());
		}
	}

	/**
	 * Creates a new audit receiver mail with the primary key. Does not add the audit receiver mail to the database.
	 *
	 * @param auditReceiverMailId the primary key for the new audit receiver mail
	 * @return the new audit receiver mail
	 */
	public AuditReceiverMail create(long auditReceiverMailId) {
		AuditReceiverMail auditReceiverMail = new AuditReceiverMailImpl();

		auditReceiverMail.setNew(true);
		auditReceiverMail.setPrimaryKey(auditReceiverMailId);

		return auditReceiverMail;
	}

	/**
	 * Removes the audit receiver mail with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditReceiverMailId the primary key of the audit receiver mail
	 * @return the audit receiver mail that was removed
	 * @throws com.tls.liferaylms.mail.NoSuchAuditReceiverMailException if a audit receiver mail with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditReceiverMail remove(long auditReceiverMailId)
		throws NoSuchAuditReceiverMailException, SystemException {
		return remove(Long.valueOf(auditReceiverMailId));
	}

	/**
	 * Removes the audit receiver mail with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the audit receiver mail
	 * @return the audit receiver mail that was removed
	 * @throws com.tls.liferaylms.mail.NoSuchAuditReceiverMailException if a audit receiver mail with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AuditReceiverMail remove(Serializable primaryKey)
		throws NoSuchAuditReceiverMailException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AuditReceiverMail auditReceiverMail = (AuditReceiverMail)session.get(AuditReceiverMailImpl.class,
					primaryKey);

			if (auditReceiverMail == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAuditReceiverMailException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(auditReceiverMail);
		}
		catch (NoSuchAuditReceiverMailException nsee) {
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
	protected AuditReceiverMail removeImpl(AuditReceiverMail auditReceiverMail)
		throws SystemException {
		auditReceiverMail = toUnwrappedModel(auditReceiverMail);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, auditReceiverMail);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(auditReceiverMail);

		return auditReceiverMail;
	}

	@Override
	public AuditReceiverMail updateImpl(
		com.tls.liferaylms.mail.model.AuditReceiverMail auditReceiverMail,
		boolean merge) throws SystemException {
		auditReceiverMail = toUnwrappedModel(auditReceiverMail);

		boolean isNew = auditReceiverMail.isNew();

		AuditReceiverMailModelImpl auditReceiverMailModelImpl = (AuditReceiverMailModelImpl)auditReceiverMail;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, auditReceiverMail, merge);

			auditReceiverMail.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AuditReceiverMailModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((auditReceiverMailModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AUDITSENDMAIL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(auditReceiverMailModelImpl.getOriginalAuditSendMailsId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AUDITSENDMAIL,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AUDITSENDMAIL,
					args);

				args = new Object[] {
						Long.valueOf(auditReceiverMailModelImpl.getAuditSendMailsId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AUDITSENDMAIL,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AUDITSENDMAIL,
					args);
			}
		}

		EntityCacheUtil.putResult(AuditReceiverMailModelImpl.ENTITY_CACHE_ENABLED,
			AuditReceiverMailImpl.class, auditReceiverMail.getPrimaryKey(),
			auditReceiverMail);

		return auditReceiverMail;
	}

	protected AuditReceiverMail toUnwrappedModel(
		AuditReceiverMail auditReceiverMail) {
		if (auditReceiverMail instanceof AuditReceiverMailImpl) {
			return auditReceiverMail;
		}

		AuditReceiverMailImpl auditReceiverMailImpl = new AuditReceiverMailImpl();

		auditReceiverMailImpl.setNew(auditReceiverMail.isNew());
		auditReceiverMailImpl.setPrimaryKey(auditReceiverMail.getPrimaryKey());

		auditReceiverMailImpl.setAuditReceiverMailId(auditReceiverMail.getAuditReceiverMailId());
		auditReceiverMailImpl.setAuditSendMailsId(auditReceiverMail.getAuditSendMailsId());
		auditReceiverMailImpl.setTo(auditReceiverMail.getTo());
		auditReceiverMailImpl.setStatus(auditReceiverMail.getStatus());

		return auditReceiverMailImpl;
	}

	/**
	 * Returns the audit receiver mail with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the audit receiver mail
	 * @return the audit receiver mail
	 * @throws com.liferay.portal.NoSuchModelException if a audit receiver mail with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AuditReceiverMail findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the audit receiver mail with the primary key or throws a {@link com.tls.liferaylms.mail.NoSuchAuditReceiverMailException} if it could not be found.
	 *
	 * @param auditReceiverMailId the primary key of the audit receiver mail
	 * @return the audit receiver mail
	 * @throws com.tls.liferaylms.mail.NoSuchAuditReceiverMailException if a audit receiver mail with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditReceiverMail findByPrimaryKey(long auditReceiverMailId)
		throws NoSuchAuditReceiverMailException, SystemException {
		AuditReceiverMail auditReceiverMail = fetchByPrimaryKey(auditReceiverMailId);

		if (auditReceiverMail == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					auditReceiverMailId);
			}

			throw new NoSuchAuditReceiverMailException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				auditReceiverMailId);
		}

		return auditReceiverMail;
	}

	/**
	 * Returns the audit receiver mail with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the audit receiver mail
	 * @return the audit receiver mail, or <code>null</code> if a audit receiver mail with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AuditReceiverMail fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the audit receiver mail with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param auditReceiverMailId the primary key of the audit receiver mail
	 * @return the audit receiver mail, or <code>null</code> if a audit receiver mail with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditReceiverMail fetchByPrimaryKey(long auditReceiverMailId)
		throws SystemException {
		AuditReceiverMail auditReceiverMail = (AuditReceiverMail)EntityCacheUtil.getResult(AuditReceiverMailModelImpl.ENTITY_CACHE_ENABLED,
				AuditReceiverMailImpl.class, auditReceiverMailId);

		if (auditReceiverMail == _nullAuditReceiverMail) {
			return null;
		}

		if (auditReceiverMail == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				auditReceiverMail = (AuditReceiverMail)session.get(AuditReceiverMailImpl.class,
						Long.valueOf(auditReceiverMailId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (auditReceiverMail != null) {
					cacheResult(auditReceiverMail);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AuditReceiverMailModelImpl.ENTITY_CACHE_ENABLED,
						AuditReceiverMailImpl.class, auditReceiverMailId,
						_nullAuditReceiverMail);
				}

				closeSession(session);
			}
		}

		return auditReceiverMail;
	}

	/**
	 * Returns all the audit receiver mails where auditSendMailsId = &#63;.
	 *
	 * @param auditSendMailsId the audit send mails ID
	 * @return the matching audit receiver mails
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditReceiverMail> findByauditSendMail(long auditSendMailsId)
		throws SystemException {
		return findByauditSendMail(auditSendMailsId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	public List<AuditReceiverMail> findByauditSendMail(long auditSendMailsId,
		int start, int end) throws SystemException {
		return findByauditSendMail(auditSendMailsId, start, end, null);
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
	public List<AuditReceiverMail> findByauditSendMail(long auditSendMailsId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AUDITSENDMAIL;
			finderArgs = new Object[] { auditSendMailsId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AUDITSENDMAIL;
			finderArgs = new Object[] {
					auditSendMailsId,
					
					start, end, orderByComparator
				};
		}

		List<AuditReceiverMail> list = (List<AuditReceiverMail>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AuditReceiverMail auditReceiverMail : list) {
				if ((auditSendMailsId != auditReceiverMail.getAuditSendMailsId())) {
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

			query.append(_SQL_SELECT_AUDITRECEIVERMAIL_WHERE);

			query.append(_FINDER_COLUMN_AUDITSENDMAIL_AUDITSENDMAILSID_2);

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

				qPos.add(auditSendMailsId);

				list = (List<AuditReceiverMail>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first audit receiver mail in the ordered set where auditSendMailsId = &#63;.
	 *
	 * @param auditSendMailsId the audit send mails ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit receiver mail
	 * @throws com.tls.liferaylms.mail.NoSuchAuditReceiverMailException if a matching audit receiver mail could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditReceiverMail findByauditSendMail_First(long auditSendMailsId,
		OrderByComparator orderByComparator)
		throws NoSuchAuditReceiverMailException, SystemException {
		AuditReceiverMail auditReceiverMail = fetchByauditSendMail_First(auditSendMailsId,
				orderByComparator);

		if (auditReceiverMail != null) {
			return auditReceiverMail;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("auditSendMailsId=");
		msg.append(auditSendMailsId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditReceiverMailException(msg.toString());
	}

	/**
	 * Returns the first audit receiver mail in the ordered set where auditSendMailsId = &#63;.
	 *
	 * @param auditSendMailsId the audit send mails ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit receiver mail, or <code>null</code> if a matching audit receiver mail could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditReceiverMail fetchByauditSendMail_First(long auditSendMailsId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AuditReceiverMail> list = findByauditSendMail(auditSendMailsId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public AuditReceiverMail findByauditSendMail_Last(long auditSendMailsId,
		OrderByComparator orderByComparator)
		throws NoSuchAuditReceiverMailException, SystemException {
		AuditReceiverMail auditReceiverMail = fetchByauditSendMail_Last(auditSendMailsId,
				orderByComparator);

		if (auditReceiverMail != null) {
			return auditReceiverMail;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("auditSendMailsId=");
		msg.append(auditSendMailsId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditReceiverMailException(msg.toString());
	}

	/**
	 * Returns the last audit receiver mail in the ordered set where auditSendMailsId = &#63;.
	 *
	 * @param auditSendMailsId the audit send mails ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit receiver mail, or <code>null</code> if a matching audit receiver mail could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditReceiverMail fetchByauditSendMail_Last(long auditSendMailsId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByauditSendMail(auditSendMailsId);

		List<AuditReceiverMail> list = findByauditSendMail(auditSendMailsId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public AuditReceiverMail[] findByauditSendMail_PrevAndNext(
		long auditReceiverMailId, long auditSendMailsId,
		OrderByComparator orderByComparator)
		throws NoSuchAuditReceiverMailException, SystemException {
		AuditReceiverMail auditReceiverMail = findByPrimaryKey(auditReceiverMailId);

		Session session = null;

		try {
			session = openSession();

			AuditReceiverMail[] array = new AuditReceiverMailImpl[3];

			array[0] = getByauditSendMail_PrevAndNext(session,
					auditReceiverMail, auditSendMailsId, orderByComparator, true);

			array[1] = auditReceiverMail;

			array[2] = getByauditSendMail_PrevAndNext(session,
					auditReceiverMail, auditSendMailsId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AuditReceiverMail getByauditSendMail_PrevAndNext(
		Session session, AuditReceiverMail auditReceiverMail,
		long auditSendMailsId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_AUDITRECEIVERMAIL_WHERE);

		query.append(_FINDER_COLUMN_AUDITSENDMAIL_AUDITSENDMAILSID_2);

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

		qPos.add(auditSendMailsId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(auditReceiverMail);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AuditReceiverMail> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the audit receiver mails.
	 *
	 * @return the audit receiver mails
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditReceiverMail> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<AuditReceiverMail> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	public List<AuditReceiverMail> findAll(int start, int end,
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

		List<AuditReceiverMail> list = (List<AuditReceiverMail>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_AUDITRECEIVERMAIL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_AUDITRECEIVERMAIL;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AuditReceiverMail>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AuditReceiverMail>)QueryUtil.list(q,
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
	 * Removes all the audit receiver mails where auditSendMailsId = &#63; from the database.
	 *
	 * @param auditSendMailsId the audit send mails ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByauditSendMail(long auditSendMailsId)
		throws SystemException {
		for (AuditReceiverMail auditReceiverMail : findByauditSendMail(
				auditSendMailsId)) {
			remove(auditReceiverMail);
		}
	}

	/**
	 * Removes all the audit receiver mails from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AuditReceiverMail auditReceiverMail : findAll()) {
			remove(auditReceiverMail);
		}
	}

	/**
	 * Returns the number of audit receiver mails where auditSendMailsId = &#63;.
	 *
	 * @param auditSendMailsId the audit send mails ID
	 * @return the number of matching audit receiver mails
	 * @throws SystemException if a system exception occurred
	 */
	public int countByauditSendMail(long auditSendMailsId)
		throws SystemException {
		Object[] finderArgs = new Object[] { auditSendMailsId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AUDITSENDMAIL,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_AUDITRECEIVERMAIL_WHERE);

			query.append(_FINDER_COLUMN_AUDITSENDMAIL_AUDITSENDMAILSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(auditSendMailsId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AUDITSENDMAIL,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of audit receiver mails.
	 *
	 * @return the number of audit receiver mails
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_AUDITRECEIVERMAIL);

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
	 * Initializes the audit receiver mail persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.tls.liferaylms.mail.model.AuditReceiverMail")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AuditReceiverMail>> listenersList = new ArrayList<ModelListener<AuditReceiverMail>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<AuditReceiverMail>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AuditReceiverMailImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = AuditReceiverMailPersistence.class)
	protected AuditReceiverMailPersistence auditReceiverMailPersistence;
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
	private static final String _SQL_SELECT_AUDITRECEIVERMAIL = "SELECT auditReceiverMail FROM AuditReceiverMail auditReceiverMail";
	private static final String _SQL_SELECT_AUDITRECEIVERMAIL_WHERE = "SELECT auditReceiverMail FROM AuditReceiverMail auditReceiverMail WHERE ";
	private static final String _SQL_COUNT_AUDITRECEIVERMAIL = "SELECT COUNT(auditReceiverMail) FROM AuditReceiverMail auditReceiverMail";
	private static final String _SQL_COUNT_AUDITRECEIVERMAIL_WHERE = "SELECT COUNT(auditReceiverMail) FROM AuditReceiverMail auditReceiverMail WHERE ";
	private static final String _FINDER_COLUMN_AUDITSENDMAIL_AUDITSENDMAILSID_2 = "auditReceiverMail.auditSendMailsId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "auditReceiverMail.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AuditReceiverMail exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AuditReceiverMail exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AuditReceiverMailPersistenceImpl.class);
	private static AuditReceiverMail _nullAuditReceiverMail = new AuditReceiverMailImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AuditReceiverMail> toCacheModel() {
				return _nullAuditReceiverMailCacheModel;
			}
		};

	private static CacheModel<AuditReceiverMail> _nullAuditReceiverMailCacheModel =
		new CacheModel<AuditReceiverMail>() {
			public AuditReceiverMail toEntityModel() {
				return _nullAuditReceiverMail;
			}
		};
}