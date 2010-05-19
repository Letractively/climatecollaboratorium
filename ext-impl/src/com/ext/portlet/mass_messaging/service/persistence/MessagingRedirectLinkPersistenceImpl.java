/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.persistence;

import com.ext.portlet.mass_messaging.NoSuchMessagingRedirectLinkException;
import com.ext.portlet.mass_messaging.model.MessagingRedirectLink;
import com.ext.portlet.mass_messaging.model.impl.MessagingRedirectLinkImpl;
import com.ext.portlet.mass_messaging.model.impl.MessagingRedirectLinkModelImpl;

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


public class MessagingRedirectLinkPersistenceImpl extends BasePersistenceImpl
    implements MessagingRedirectLinkPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = MessagingRedirectLinkImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(MessagingRedirectLinkModelImpl.ENTITY_CACHE_ENABLED,
            MessagingRedirectLinkModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MessagingRedirectLinkModelImpl.ENTITY_CACHE_ENABLED,
            MessagingRedirectLinkModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(MessagingRedirectLinkPersistenceImpl.class);
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

    public void cacheResult(MessagingRedirectLink messagingRedirectLink) {
        EntityCacheUtil.putResult(MessagingRedirectLinkModelImpl.ENTITY_CACHE_ENABLED,
            MessagingRedirectLinkImpl.class,
            messagingRedirectLink.getPrimaryKey(), messagingRedirectLink);
    }

    public void cacheResult(List<MessagingRedirectLink> messagingRedirectLinks) {
        for (MessagingRedirectLink messagingRedirectLink : messagingRedirectLinks) {
            if (EntityCacheUtil.getResult(
                        MessagingRedirectLinkModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingRedirectLinkImpl.class,
                        messagingRedirectLink.getPrimaryKey(), this) == null) {
                cacheResult(messagingRedirectLink);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(MessagingRedirectLinkImpl.class.getName());
        EntityCacheUtil.clearCache(MessagingRedirectLinkImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public MessagingRedirectLink create(Long redirectId) {
        MessagingRedirectLink messagingRedirectLink = new MessagingRedirectLinkImpl();

        messagingRedirectLink.setNew(true);
        messagingRedirectLink.setPrimaryKey(redirectId);

        return messagingRedirectLink;
    }

    public MessagingRedirectLink remove(Long redirectId)
        throws NoSuchMessagingRedirectLinkException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MessagingRedirectLink messagingRedirectLink = (MessagingRedirectLink) session.get(MessagingRedirectLinkImpl.class,
                    redirectId);

            if (messagingRedirectLink == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No MessagingRedirectLink exists with the primary key " +
                        redirectId);
                }

                throw new NoSuchMessagingRedirectLinkException(
                    "No MessagingRedirectLink exists with the primary key " +
                    redirectId);
            }

            return remove(messagingRedirectLink);
        } catch (NoSuchMessagingRedirectLinkException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public MessagingRedirectLink remove(
        MessagingRedirectLink messagingRedirectLink) throws SystemException {
        for (ModelListener<MessagingRedirectLink> listener : listeners) {
            listener.onBeforeRemove(messagingRedirectLink);
        }

        messagingRedirectLink = removeImpl(messagingRedirectLink);

        for (ModelListener<MessagingRedirectLink> listener : listeners) {
            listener.onAfterRemove(messagingRedirectLink);
        }

        return messagingRedirectLink;
    }

    protected MessagingRedirectLink removeImpl(
        MessagingRedirectLink messagingRedirectLink) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (messagingRedirectLink.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(MessagingRedirectLinkImpl.class,
                        messagingRedirectLink.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(messagingRedirectLink);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(MessagingRedirectLinkModelImpl.ENTITY_CACHE_ENABLED,
            MessagingRedirectLinkImpl.class,
            messagingRedirectLink.getPrimaryKey());

        return messagingRedirectLink;
    }

    /**
     * @deprecated Use <code>update(MessagingRedirectLink messagingRedirectLink, boolean merge)</code>.
     */
    public MessagingRedirectLink update(
        MessagingRedirectLink messagingRedirectLink) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(MessagingRedirectLink messagingRedirectLink) method. Use update(MessagingRedirectLink messagingRedirectLink, boolean merge) instead.");
        }

        return update(messagingRedirectLink, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                messagingRedirectLink the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when messagingRedirectLink is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public MessagingRedirectLink update(
        MessagingRedirectLink messagingRedirectLink, boolean merge)
        throws SystemException {
        boolean isNew = messagingRedirectLink.isNew();

        for (ModelListener<MessagingRedirectLink> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(messagingRedirectLink);
            } else {
                listener.onBeforeUpdate(messagingRedirectLink);
            }
        }

        messagingRedirectLink = updateImpl(messagingRedirectLink, merge);

        for (ModelListener<MessagingRedirectLink> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(messagingRedirectLink);
            } else {
                listener.onAfterUpdate(messagingRedirectLink);
            }
        }

        return messagingRedirectLink;
    }

    public MessagingRedirectLink updateImpl(
        com.ext.portlet.mass_messaging.model.MessagingRedirectLink messagingRedirectLink,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, messagingRedirectLink, merge);

            messagingRedirectLink.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(MessagingRedirectLinkModelImpl.ENTITY_CACHE_ENABLED,
            MessagingRedirectLinkImpl.class,
            messagingRedirectLink.getPrimaryKey(), messagingRedirectLink);

        return messagingRedirectLink;
    }

    public MessagingRedirectLink findByPrimaryKey(Long redirectId)
        throws NoSuchMessagingRedirectLinkException, SystemException {
        MessagingRedirectLink messagingRedirectLink = fetchByPrimaryKey(redirectId);

        if (messagingRedirectLink == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No MessagingRedirectLink exists with the primary key " +
                    redirectId);
            }

            throw new NoSuchMessagingRedirectLinkException(
                "No MessagingRedirectLink exists with the primary key " +
                redirectId);
        }

        return messagingRedirectLink;
    }

    public MessagingRedirectLink fetchByPrimaryKey(Long redirectId)
        throws SystemException {
        MessagingRedirectLink messagingRedirectLink = (MessagingRedirectLink) EntityCacheUtil.getResult(MessagingRedirectLinkModelImpl.ENTITY_CACHE_ENABLED,
                MessagingRedirectLinkImpl.class, redirectId, this);

        if (messagingRedirectLink == null) {
            Session session = null;

            try {
                session = openSession();

                messagingRedirectLink = (MessagingRedirectLink) session.get(MessagingRedirectLinkImpl.class,
                        redirectId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (messagingRedirectLink != null) {
                    cacheResult(messagingRedirectLink);
                }

                closeSession(session);
            }
        }

        return messagingRedirectLink;
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

    public List<MessagingRedirectLink> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<MessagingRedirectLink> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<MessagingRedirectLink> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<MessagingRedirectLink> list = (List<MessagingRedirectLink>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.mass_messaging.model.MessagingRedirectLink ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<MessagingRedirectLink>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<MessagingRedirectLink>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<MessagingRedirectLink>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (MessagingRedirectLink messagingRedirectLink : findAll()) {
            remove(messagingRedirectLink);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.mass_messaging.model.MessagingRedirectLink");

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
                        "value.object.listener.com.ext.portlet.mass_messaging.model.MessagingRedirectLink")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MessagingRedirectLink>> listenersList = new ArrayList<ModelListener<MessagingRedirectLink>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MessagingRedirectLink>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
