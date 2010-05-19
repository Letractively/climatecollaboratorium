/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.persistence;

import com.ext.portlet.mass_messaging.NoSuchMessagingIgnoredRecipientsException;
import com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients;
import com.ext.portlet.mass_messaging.model.impl.MessagingIgnoredRecipientsImpl;
import com.ext.portlet.mass_messaging.model.impl.MessagingIgnoredRecipientsModelImpl;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MessagingIgnoredRecipientsPersistenceImpl
    extends BasePersistenceImpl implements MessagingIgnoredRecipientsPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = MessagingIgnoredRecipientsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_FINDBYEMAIL = new FinderPath(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
            MessagingIgnoredRecipientsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByfindByEmail",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_FINDBYEMAIL = new FinderPath(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
            MessagingIgnoredRecipientsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByfindByEmail",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_FINDBYUSERID = new FinderPath(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
            MessagingIgnoredRecipientsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByfindByUserId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_FINDBYUSERID = new FinderPath(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
            MessagingIgnoredRecipientsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByfindByUserId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
            MessagingIgnoredRecipientsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
            MessagingIgnoredRecipientsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(MessagingIgnoredRecipientsPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.persistence.MessagingMessagePersistence.impl")
    protected com.ext.portlet.mass_messaging.service.persistence.MessagingMessagePersistence messagingMessagePersistence;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.persistence.MessagingMessageRecipientPersistence.impl")
    protected com.ext.portlet.mass_messaging.service.persistence.MessagingMessageRecipientPersistence messagingMessageRecipientPersistence;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.persistence.MessagingMessageConversionTypePersistence.impl")
    protected com.ext.portlet.mass_messaging.service.persistence.MessagingMessageConversionTypePersistence messagingMessageConversionTypePersistence;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.persistence.MessagingMessageConversionPersistence.impl")
    protected com.ext.portlet.mass_messaging.service.persistence.MessagingMessageConversionPersistence messagingMessageConversionPersistence;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.persistence.MessagingRedirectLinkPersistence.impl")
    protected com.ext.portlet.mass_messaging.service.persistence.MessagingRedirectLinkPersistence messagingRedirectLinkPersistence;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.persistence.MessagingIgnoredRecipientsPersistence.impl")
    protected com.ext.portlet.mass_messaging.service.persistence.MessagingIgnoredRecipientsPersistence messagingIgnoredRecipientsPersistence;

    public void cacheResult(
        MessagingIgnoredRecipients messagingIgnoredRecipients) {
        EntityCacheUtil.putResult(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
            MessagingIgnoredRecipientsImpl.class,
            messagingIgnoredRecipients.getPrimaryKey(),
            messagingIgnoredRecipients);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYEMAIL,
            new Object[] { messagingIgnoredRecipients.getEmail() },
            messagingIgnoredRecipients);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYUSERID,
            new Object[] { messagingIgnoredRecipients.getUserId() },
            messagingIgnoredRecipients);
    }

    public void cacheResult(
        List<MessagingIgnoredRecipients> messagingIgnoredRecipientses) {
        for (MessagingIgnoredRecipients messagingIgnoredRecipients : messagingIgnoredRecipientses) {
            if (EntityCacheUtil.getResult(
                        MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingIgnoredRecipientsImpl.class,
                        messagingIgnoredRecipients.getPrimaryKey(), this) == null) {
                cacheResult(messagingIgnoredRecipients);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(MessagingIgnoredRecipientsImpl.class.getName());
        EntityCacheUtil.clearCache(MessagingIgnoredRecipientsImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public MessagingIgnoredRecipients create(Long ignoredRecipientId) {
        MessagingIgnoredRecipients messagingIgnoredRecipients = new MessagingIgnoredRecipientsImpl();

        messagingIgnoredRecipients.setNew(true);
        messagingIgnoredRecipients.setPrimaryKey(ignoredRecipientId);

        return messagingIgnoredRecipients;
    }

    public MessagingIgnoredRecipients remove(Long ignoredRecipientId)
        throws NoSuchMessagingIgnoredRecipientsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MessagingIgnoredRecipients messagingIgnoredRecipients = (MessagingIgnoredRecipients) session.get(MessagingIgnoredRecipientsImpl.class,
                    ignoredRecipientId);

            if (messagingIgnoredRecipients == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No MessagingIgnoredRecipients exists with the primary key " +
                        ignoredRecipientId);
                }

                throw new NoSuchMessagingIgnoredRecipientsException(
                    "No MessagingIgnoredRecipients exists with the primary key " +
                    ignoredRecipientId);
            }

            return remove(messagingIgnoredRecipients);
        } catch (NoSuchMessagingIgnoredRecipientsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public MessagingIgnoredRecipients remove(
        MessagingIgnoredRecipients messagingIgnoredRecipients)
        throws SystemException {
        for (ModelListener<MessagingIgnoredRecipients> listener : listeners) {
            listener.onBeforeRemove(messagingIgnoredRecipients);
        }

        messagingIgnoredRecipients = removeImpl(messagingIgnoredRecipients);

        for (ModelListener<MessagingIgnoredRecipients> listener : listeners) {
            listener.onAfterRemove(messagingIgnoredRecipients);
        }

        return messagingIgnoredRecipients;
    }

    protected MessagingIgnoredRecipients removeImpl(
        MessagingIgnoredRecipients messagingIgnoredRecipients)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (messagingIgnoredRecipients.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(MessagingIgnoredRecipientsImpl.class,
                        messagingIgnoredRecipients.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(messagingIgnoredRecipients);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        MessagingIgnoredRecipientsModelImpl messagingIgnoredRecipientsModelImpl = (MessagingIgnoredRecipientsModelImpl) messagingIgnoredRecipients;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYEMAIL,
            new Object[] { messagingIgnoredRecipientsModelImpl.getOriginalEmail() });

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYUSERID,
            new Object[] { messagingIgnoredRecipientsModelImpl.getOriginalUserId() });

        EntityCacheUtil.removeResult(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
            MessagingIgnoredRecipientsImpl.class,
            messagingIgnoredRecipients.getPrimaryKey());

        return messagingIgnoredRecipients;
    }

    /**
     * @deprecated Use <code>update(MessagingIgnoredRecipients messagingIgnoredRecipients, boolean merge)</code>.
     */
    public MessagingIgnoredRecipients update(
        MessagingIgnoredRecipients messagingIgnoredRecipients)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(MessagingIgnoredRecipients messagingIgnoredRecipients) method. Use update(MessagingIgnoredRecipients messagingIgnoredRecipients, boolean merge) instead.");
        }

        return update(messagingIgnoredRecipients, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                messagingIgnoredRecipients the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when messagingIgnoredRecipients is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public MessagingIgnoredRecipients update(
        MessagingIgnoredRecipients messagingIgnoredRecipients, boolean merge)
        throws SystemException {
        boolean isNew = messagingIgnoredRecipients.isNew();

        for (ModelListener<MessagingIgnoredRecipients> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(messagingIgnoredRecipients);
            } else {
                listener.onBeforeUpdate(messagingIgnoredRecipients);
            }
        }

        messagingIgnoredRecipients = updateImpl(messagingIgnoredRecipients,
                merge);

        for (ModelListener<MessagingIgnoredRecipients> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(messagingIgnoredRecipients);
            } else {
                listener.onAfterUpdate(messagingIgnoredRecipients);
            }
        }

        return messagingIgnoredRecipients;
    }

    public MessagingIgnoredRecipients updateImpl(
        com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients messagingIgnoredRecipients,
        boolean merge) throws SystemException {
        boolean isNew = messagingIgnoredRecipients.isNew();

        MessagingIgnoredRecipientsModelImpl messagingIgnoredRecipientsModelImpl = (MessagingIgnoredRecipientsModelImpl) messagingIgnoredRecipients;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, messagingIgnoredRecipients, merge);

            messagingIgnoredRecipients.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
            MessagingIgnoredRecipientsImpl.class,
            messagingIgnoredRecipients.getPrimaryKey(),
            messagingIgnoredRecipients);

        if (!isNew &&
                (!Validator.equals(messagingIgnoredRecipients.getEmail(),
                    messagingIgnoredRecipientsModelImpl.getOriginalEmail()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYEMAIL,
                new Object[] {
                    messagingIgnoredRecipientsModelImpl.getOriginalEmail()
                });
        }

        if (isNew ||
                (!Validator.equals(messagingIgnoredRecipients.getEmail(),
                    messagingIgnoredRecipientsModelImpl.getOriginalEmail()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYEMAIL,
                new Object[] { messagingIgnoredRecipients.getEmail() },
                messagingIgnoredRecipients);
        }

        if (!isNew &&
                (!Validator.equals(messagingIgnoredRecipients.getUserId(),
                    messagingIgnoredRecipientsModelImpl.getOriginalUserId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYUSERID,
                new Object[] {
                    messagingIgnoredRecipientsModelImpl.getOriginalUserId()
                });
        }

        if (isNew ||
                (!Validator.equals(messagingIgnoredRecipients.getUserId(),
                    messagingIgnoredRecipientsModelImpl.getOriginalUserId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYUSERID,
                new Object[] { messagingIgnoredRecipients.getUserId() },
                messagingIgnoredRecipients);
        }

        return messagingIgnoredRecipients;
    }

    public MessagingIgnoredRecipients findByPrimaryKey(Long ignoredRecipientId)
        throws NoSuchMessagingIgnoredRecipientsException, SystemException {
        MessagingIgnoredRecipients messagingIgnoredRecipients = fetchByPrimaryKey(ignoredRecipientId);

        if (messagingIgnoredRecipients == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No MessagingIgnoredRecipients exists with the primary key " +
                    ignoredRecipientId);
            }

            throw new NoSuchMessagingIgnoredRecipientsException(
                "No MessagingIgnoredRecipients exists with the primary key " +
                ignoredRecipientId);
        }

        return messagingIgnoredRecipients;
    }

    public MessagingIgnoredRecipients fetchByPrimaryKey(Long ignoredRecipientId)
        throws SystemException {
        MessagingIgnoredRecipients messagingIgnoredRecipients = (MessagingIgnoredRecipients) EntityCacheUtil.getResult(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
                MessagingIgnoredRecipientsImpl.class, ignoredRecipientId, this);

        if (messagingIgnoredRecipients == null) {
            Session session = null;

            try {
                session = openSession();

                messagingIgnoredRecipients = (MessagingIgnoredRecipients) session.get(MessagingIgnoredRecipientsImpl.class,
                        ignoredRecipientId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (messagingIgnoredRecipients != null) {
                    cacheResult(messagingIgnoredRecipients);
                }

                closeSession(session);
            }
        }

        return messagingIgnoredRecipients;
    }

    public MessagingIgnoredRecipients findByfindByEmail(String email)
        throws NoSuchMessagingIgnoredRecipientsException, SystemException {
        MessagingIgnoredRecipients messagingIgnoredRecipients = fetchByfindByEmail(email);

        if (messagingIgnoredRecipients == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No MessagingIgnoredRecipients exists with the key {");

            msg.append("email=" + email);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchMessagingIgnoredRecipientsException(msg.toString());
        }

        return messagingIgnoredRecipients;
    }

    public MessagingIgnoredRecipients fetchByfindByEmail(String email)
        throws SystemException {
        return fetchByfindByEmail(email, true);
    }

    public MessagingIgnoredRecipients fetchByfindByEmail(String email,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { email };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_FINDBYEMAIL,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients WHERE ");

                if (email == null) {
                    query.append("email IS NULL");
                } else {
                    query.append("email = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("email ASC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (email != null) {
                    qPos.add(email);
                }

                List<MessagingIgnoredRecipients> list = q.list();

                result = list;

                MessagingIgnoredRecipients messagingIgnoredRecipients = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYEMAIL,
                        finderArgs, list);
                } else {
                    messagingIgnoredRecipients = list.get(0);

                    cacheResult(messagingIgnoredRecipients);

                    if ((messagingIgnoredRecipients.getEmail() == null) ||
                            !messagingIgnoredRecipients.getEmail().equals(email)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYEMAIL,
                            finderArgs, messagingIgnoredRecipients);
                    }
                }

                return messagingIgnoredRecipients;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYEMAIL,
                        finderArgs, new ArrayList<MessagingIgnoredRecipients>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (MessagingIgnoredRecipients) result;
            }
        }
    }

    public MessagingIgnoredRecipients findByfindByUserId(Long userId)
        throws NoSuchMessagingIgnoredRecipientsException, SystemException {
        MessagingIgnoredRecipients messagingIgnoredRecipients = fetchByfindByUserId(userId);

        if (messagingIgnoredRecipients == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No MessagingIgnoredRecipients exists with the key {");

            msg.append("userId=" + userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchMessagingIgnoredRecipientsException(msg.toString());
        }

        return messagingIgnoredRecipients;
    }

    public MessagingIgnoredRecipients fetchByfindByUserId(Long userId)
        throws SystemException {
        return fetchByfindByUserId(userId, true);
    }

    public MessagingIgnoredRecipients fetchByfindByUserId(Long userId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_FINDBYUSERID,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients WHERE ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("email ASC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                List<MessagingIgnoredRecipients> list = q.list();

                result = list;

                MessagingIgnoredRecipients messagingIgnoredRecipients = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYUSERID,
                        finderArgs, list);
                } else {
                    messagingIgnoredRecipients = list.get(0);

                    cacheResult(messagingIgnoredRecipients);

                    if ((messagingIgnoredRecipients.getUserId() == null) ||
                            !messagingIgnoredRecipients.getUserId()
                                                           .equals(userId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYUSERID,
                            finderArgs, messagingIgnoredRecipients);
                    }
                }

                return messagingIgnoredRecipients;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYUSERID,
                        finderArgs, new ArrayList<MessagingIgnoredRecipients>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (MessagingIgnoredRecipients) result;
            }
        }
    }

    public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            dynamicQuery.compile(session);

            return dynamicQuery.list();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            dynamicQuery.setLimit(start, end);

            dynamicQuery.compile(session);

            return dynamicQuery.list();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<MessagingIgnoredRecipients> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<MessagingIgnoredRecipients> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<MessagingIgnoredRecipients> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<MessagingIgnoredRecipients> list = (List<MessagingIgnoredRecipients>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("email ASC");
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<MessagingIgnoredRecipients>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<MessagingIgnoredRecipients>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<MessagingIgnoredRecipients>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByfindByEmail(String email)
        throws NoSuchMessagingIgnoredRecipientsException, SystemException {
        MessagingIgnoredRecipients messagingIgnoredRecipients = findByfindByEmail(email);

        remove(messagingIgnoredRecipients);
    }

    public void removeByfindByUserId(Long userId)
        throws NoSuchMessagingIgnoredRecipientsException, SystemException {
        MessagingIgnoredRecipients messagingIgnoredRecipients = findByfindByUserId(userId);

        remove(messagingIgnoredRecipients);
    }

    public void removeAll() throws SystemException {
        for (MessagingIgnoredRecipients messagingIgnoredRecipients : findAll()) {
            remove(messagingIgnoredRecipients);
        }
    }

    public int countByfindByEmail(String email) throws SystemException {
        Object[] finderArgs = new Object[] { email };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FINDBYEMAIL,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients WHERE ");

                if (email == null) {
                    query.append("email IS NULL");
                } else {
                    query.append("email = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (email != null) {
                    qPos.add(email);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FINDBYEMAIL,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByfindByUserId(Long userId) throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FINDBYUSERID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients WHERE ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FINDBYUSERID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countAll() throws SystemException {
        Object[] finderArgs = new Object[0];

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(
                        "SELECT COUNT(*) FROM com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients");

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
                    count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.portal.util.PropsUtil.get(
                        "value.object.listener.com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MessagingIgnoredRecipients>> listenersList = new ArrayList<ModelListener<MessagingIgnoredRecipients>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MessagingIgnoredRecipients>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
