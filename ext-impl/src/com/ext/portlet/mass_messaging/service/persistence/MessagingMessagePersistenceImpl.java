/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.persistence;

import com.ext.portlet.mass_messaging.NoSuchMessagingMessageException;
import com.ext.portlet.mass_messaging.model.MessagingMessage;
import com.ext.portlet.mass_messaging.model.impl.MessagingMessageImpl;
import com.ext.portlet.mass_messaging.model.impl.MessagingMessageModelImpl;

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


public class MessagingMessagePersistenceImpl extends BasePersistenceImpl
    implements MessagingMessagePersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = MessagingMessageImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(MessagingMessageModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MessagingMessageModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(MessagingMessagePersistenceImpl.class);
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

    public void cacheResult(MessagingMessage messagingMessage) {
        EntityCacheUtil.putResult(MessagingMessageModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageImpl.class, messagingMessage.getPrimaryKey(),
            messagingMessage);
    }

    public void cacheResult(List<MessagingMessage> messagingMessages) {
        for (MessagingMessage messagingMessage : messagingMessages) {
            if (EntityCacheUtil.getResult(
                        MessagingMessageModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingMessageImpl.class,
                        messagingMessage.getPrimaryKey(), this) == null) {
                cacheResult(messagingMessage);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(MessagingMessageImpl.class.getName());
        EntityCacheUtil.clearCache(MessagingMessageImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public MessagingMessage create(Long messageId) {
        MessagingMessage messagingMessage = new MessagingMessageImpl();

        messagingMessage.setNew(true);
        messagingMessage.setPrimaryKey(messageId);

        return messagingMessage;
    }

    public MessagingMessage remove(Long messageId)
        throws NoSuchMessagingMessageException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MessagingMessage messagingMessage = (MessagingMessage) session.get(MessagingMessageImpl.class,
                    messageId);

            if (messagingMessage == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No MessagingMessage exists with the primary key " +
                        messageId);
                }

                throw new NoSuchMessagingMessageException(
                    "No MessagingMessage exists with the primary key " +
                    messageId);
            }

            return remove(messagingMessage);
        } catch (NoSuchMessagingMessageException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public MessagingMessage remove(MessagingMessage messagingMessage)
        throws SystemException {
        for (ModelListener<MessagingMessage> listener : listeners) {
            listener.onBeforeRemove(messagingMessage);
        }

        messagingMessage = removeImpl(messagingMessage);

        for (ModelListener<MessagingMessage> listener : listeners) {
            listener.onAfterRemove(messagingMessage);
        }

        return messagingMessage;
    }

    protected MessagingMessage removeImpl(MessagingMessage messagingMessage)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (messagingMessage.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(MessagingMessageImpl.class,
                        messagingMessage.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(messagingMessage);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(MessagingMessageModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageImpl.class, messagingMessage.getPrimaryKey());

        return messagingMessage;
    }

    /**
     * @deprecated Use <code>update(MessagingMessage messagingMessage, boolean merge)</code>.
     */
    public MessagingMessage update(MessagingMessage messagingMessage)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(MessagingMessage messagingMessage) method. Use update(MessagingMessage messagingMessage, boolean merge) instead.");
        }

        return update(messagingMessage, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                messagingMessage the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when messagingMessage is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public MessagingMessage update(MessagingMessage messagingMessage,
        boolean merge) throws SystemException {
        boolean isNew = messagingMessage.isNew();

        for (ModelListener<MessagingMessage> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(messagingMessage);
            } else {
                listener.onBeforeUpdate(messagingMessage);
            }
        }

        messagingMessage = updateImpl(messagingMessage, merge);

        for (ModelListener<MessagingMessage> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(messagingMessage);
            } else {
                listener.onAfterUpdate(messagingMessage);
            }
        }

        return messagingMessage;
    }

    public MessagingMessage updateImpl(
        com.ext.portlet.mass_messaging.model.MessagingMessage messagingMessage,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, messagingMessage, merge);

            messagingMessage.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(MessagingMessageModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageImpl.class, messagingMessage.getPrimaryKey(),
            messagingMessage);

        return messagingMessage;
    }

    public MessagingMessage findByPrimaryKey(Long messageId)
        throws NoSuchMessagingMessageException, SystemException {
        MessagingMessage messagingMessage = fetchByPrimaryKey(messageId);

        if (messagingMessage == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No MessagingMessage exists with the primary key " +
                    messageId);
            }

            throw new NoSuchMessagingMessageException(
                "No MessagingMessage exists with the primary key " + messageId);
        }

        return messagingMessage;
    }

    public MessagingMessage fetchByPrimaryKey(Long messageId)
        throws SystemException {
        MessagingMessage messagingMessage = (MessagingMessage) EntityCacheUtil.getResult(MessagingMessageModelImpl.ENTITY_CACHE_ENABLED,
                MessagingMessageImpl.class, messageId, this);

        if (messagingMessage == null) {
            Session session = null;

            try {
                session = openSession();

                messagingMessage = (MessagingMessage) session.get(MessagingMessageImpl.class,
                        messageId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (messagingMessage != null) {
                    cacheResult(messagingMessage);
                }

                closeSession(session);
            }
        }

        return messagingMessage;
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

    public List<MessagingMessage> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<MessagingMessage> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<MessagingMessage> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<MessagingMessage> list = (List<MessagingMessage>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.mass_messaging.model.MessagingMessage ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("createDate DESC");
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<MessagingMessage>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<MessagingMessage>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<MessagingMessage>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (MessagingMessage messagingMessage : findAll()) {
            remove(messagingMessage);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.mass_messaging.model.MessagingMessage");

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
                        "value.object.listener.com.ext.portlet.mass_messaging.model.MessagingMessage")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MessagingMessage>> listenersList = new ArrayList<ModelListener<MessagingMessage>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MessagingMessage>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
