/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.persistence;

import com.ext.portlet.mass_messaging.NoSuchMessagingMessageRecipientException;
import com.ext.portlet.mass_messaging.model.MessagingMessageRecipient;
import com.ext.portlet.mass_messaging.model.impl.MessagingMessageRecipientImpl;
import com.ext.portlet.mass_messaging.model.impl.MessagingMessageRecipientModelImpl;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MessagingMessageRecipientPersistenceImpl
    extends BasePersistenceImpl implements MessagingMessageRecipientPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = MessagingMessageRecipientImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(MessagingMessageRecipientModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageRecipientModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MessagingMessageRecipientModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageRecipientModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(MessagingMessageRecipientPersistenceImpl.class);
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

    public void cacheResult(MessagingMessageRecipient messagingMessageRecipient) {
        EntityCacheUtil.putResult(MessagingMessageRecipientModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageRecipientImpl.class,
            messagingMessageRecipient.getPrimaryKey(), messagingMessageRecipient);
    }

    public void cacheResult(
        List<MessagingMessageRecipient> messagingMessageRecipients) {
        for (MessagingMessageRecipient messagingMessageRecipient : messagingMessageRecipients) {
            if (EntityCacheUtil.getResult(
                        MessagingMessageRecipientModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingMessageRecipientImpl.class,
                        messagingMessageRecipient.getPrimaryKey(), this) == null) {
                cacheResult(messagingMessageRecipient);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(MessagingMessageRecipientImpl.class.getName());
        EntityCacheUtil.clearCache(MessagingMessageRecipientImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public MessagingMessageRecipient create(Long recipientId) {
        MessagingMessageRecipient messagingMessageRecipient = new MessagingMessageRecipientImpl();

        messagingMessageRecipient.setNew(true);
        messagingMessageRecipient.setPrimaryKey(recipientId);

        return messagingMessageRecipient;
    }

    public MessagingMessageRecipient remove(Long recipientId)
        throws NoSuchMessagingMessageRecipientException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MessagingMessageRecipient messagingMessageRecipient = (MessagingMessageRecipient) session.get(MessagingMessageRecipientImpl.class,
                    recipientId);

            if (messagingMessageRecipient == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No MessagingMessageRecipient exists with the primary key " +
                        recipientId);
                }

                throw new NoSuchMessagingMessageRecipientException(
                    "No MessagingMessageRecipient exists with the primary key " +
                    recipientId);
            }

            return remove(messagingMessageRecipient);
        } catch (NoSuchMessagingMessageRecipientException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public MessagingMessageRecipient remove(
        MessagingMessageRecipient messagingMessageRecipient)
        throws SystemException {
        for (ModelListener<MessagingMessageRecipient> listener : listeners) {
            listener.onBeforeRemove(messagingMessageRecipient);
        }

        messagingMessageRecipient = removeImpl(messagingMessageRecipient);

        for (ModelListener<MessagingMessageRecipient> listener : listeners) {
            listener.onAfterRemove(messagingMessageRecipient);
        }

        return messagingMessageRecipient;
    }

    protected MessagingMessageRecipient removeImpl(
        MessagingMessageRecipient messagingMessageRecipient)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (messagingMessageRecipient.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(MessagingMessageRecipientImpl.class,
                        messagingMessageRecipient.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(messagingMessageRecipient);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(MessagingMessageRecipientModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageRecipientImpl.class,
            messagingMessageRecipient.getPrimaryKey());

        return messagingMessageRecipient;
    }

    /**
     * @deprecated Use <code>update(MessagingMessageRecipient messagingMessageRecipient, boolean merge)</code>.
     */
    public MessagingMessageRecipient update(
        MessagingMessageRecipient messagingMessageRecipient)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(MessagingMessageRecipient messagingMessageRecipient) method. Use update(MessagingMessageRecipient messagingMessageRecipient, boolean merge) instead.");
        }

        return update(messagingMessageRecipient, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                messagingMessageRecipient the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when messagingMessageRecipient is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public MessagingMessageRecipient update(
        MessagingMessageRecipient messagingMessageRecipient, boolean merge)
        throws SystemException {
        boolean isNew = messagingMessageRecipient.isNew();

        for (ModelListener<MessagingMessageRecipient> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(messagingMessageRecipient);
            } else {
                listener.onBeforeUpdate(messagingMessageRecipient);
            }
        }

        messagingMessageRecipient = updateImpl(messagingMessageRecipient, merge);

        for (ModelListener<MessagingMessageRecipient> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(messagingMessageRecipient);
            } else {
                listener.onAfterUpdate(messagingMessageRecipient);
            }
        }

        return messagingMessageRecipient;
    }

    public MessagingMessageRecipient updateImpl(
        com.ext.portlet.mass_messaging.model.MessagingMessageRecipient messagingMessageRecipient,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, messagingMessageRecipient, merge);

            messagingMessageRecipient.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(MessagingMessageRecipientModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageRecipientImpl.class,
            messagingMessageRecipient.getPrimaryKey(), messagingMessageRecipient);

        return messagingMessageRecipient;
    }

    public MessagingMessageRecipient findByPrimaryKey(Long recipientId)
        throws NoSuchMessagingMessageRecipientException, SystemException {
        MessagingMessageRecipient messagingMessageRecipient = fetchByPrimaryKey(recipientId);

        if (messagingMessageRecipient == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No MessagingMessageRecipient exists with the primary key " +
                    recipientId);
            }

            throw new NoSuchMessagingMessageRecipientException(
                "No MessagingMessageRecipient exists with the primary key " +
                recipientId);
        }

        return messagingMessageRecipient;
    }

    public MessagingMessageRecipient fetchByPrimaryKey(Long recipientId)
        throws SystemException {
        MessagingMessageRecipient messagingMessageRecipient = (MessagingMessageRecipient) EntityCacheUtil.getResult(MessagingMessageRecipientModelImpl.ENTITY_CACHE_ENABLED,
                MessagingMessageRecipientImpl.class, recipientId, this);

        if (messagingMessageRecipient == null) {
            Session session = null;

            try {
                session = openSession();

                messagingMessageRecipient = (MessagingMessageRecipient) session.get(MessagingMessageRecipientImpl.class,
                        recipientId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (messagingMessageRecipient != null) {
                    cacheResult(messagingMessageRecipient);
                }

                closeSession(session);
            }
        }

        return messagingMessageRecipient;
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

    public List<MessagingMessageRecipient> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<MessagingMessageRecipient> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<MessagingMessageRecipient> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<MessagingMessageRecipient> list = (List<MessagingMessageRecipient>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.mass_messaging.model.MessagingMessageRecipient ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<MessagingMessageRecipient>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<MessagingMessageRecipient>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<MessagingMessageRecipient>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (MessagingMessageRecipient messagingMessageRecipient : findAll()) {
            remove(messagingMessageRecipient);
        }
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
                        "SELECT COUNT(*) FROM com.ext.portlet.mass_messaging.model.MessagingMessageRecipient");

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
                        "value.object.listener.com.ext.portlet.mass_messaging.model.MessagingMessageRecipient")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MessagingMessageRecipient>> listenersList = new ArrayList<ModelListener<MessagingMessageRecipient>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MessagingMessageRecipient>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
