/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.persistence;

import com.ext.portlet.mass_messaging.NoSuchMessagingMessageConversionTypeException;
import com.ext.portlet.mass_messaging.model.MessagingMessageConversionType;
import com.ext.portlet.mass_messaging.model.impl.MessagingMessageConversionTypeImpl;
import com.ext.portlet.mass_messaging.model.impl.MessagingMessageConversionTypeModelImpl;

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


public class MessagingMessageConversionTypePersistenceImpl
    extends BasePersistenceImpl
    implements MessagingMessageConversionTypePersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = MessagingMessageConversionTypeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_FINDBYNAME = new FinderPath(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByfindByName",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_FINDBYNAME = new FinderPath(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByfindByName",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(MessagingMessageConversionTypePersistenceImpl.class);
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
        MessagingMessageConversionType messagingMessageConversionType) {
        EntityCacheUtil.putResult(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeImpl.class,
            messagingMessageConversionType.getPrimaryKey(),
            messagingMessageConversionType);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
            new Object[] { messagingMessageConversionType.getName() },
            messagingMessageConversionType);
    }

    public void cacheResult(
        List<MessagingMessageConversionType> messagingMessageConversionTypes) {
        for (MessagingMessageConversionType messagingMessageConversionType : messagingMessageConversionTypes) {
            if (EntityCacheUtil.getResult(
                        MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingMessageConversionTypeImpl.class,
                        messagingMessageConversionType.getPrimaryKey(), this) == null) {
                cacheResult(messagingMessageConversionType);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(MessagingMessageConversionTypeImpl.class.getName());
        EntityCacheUtil.clearCache(MessagingMessageConversionTypeImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public MessagingMessageConversionType create(Long typeId) {
        MessagingMessageConversionType messagingMessageConversionType = new MessagingMessageConversionTypeImpl();

        messagingMessageConversionType.setNew(true);
        messagingMessageConversionType.setPrimaryKey(typeId);

        return messagingMessageConversionType;
    }

    public MessagingMessageConversionType remove(Long typeId)
        throws NoSuchMessagingMessageConversionTypeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MessagingMessageConversionType messagingMessageConversionType = (MessagingMessageConversionType) session.get(MessagingMessageConversionTypeImpl.class,
                    typeId);

            if (messagingMessageConversionType == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No MessagingMessageConversionType exists with the primary key " +
                        typeId);
                }

                throw new NoSuchMessagingMessageConversionTypeException(
                    "No MessagingMessageConversionType exists with the primary key " +
                    typeId);
            }

            return remove(messagingMessageConversionType);
        } catch (NoSuchMessagingMessageConversionTypeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public MessagingMessageConversionType remove(
        MessagingMessageConversionType messagingMessageConversionType)
        throws SystemException {
        for (ModelListener<MessagingMessageConversionType> listener : listeners) {
            listener.onBeforeRemove(messagingMessageConversionType);
        }

        messagingMessageConversionType = removeImpl(messagingMessageConversionType);

        for (ModelListener<MessagingMessageConversionType> listener : listeners) {
            listener.onAfterRemove(messagingMessageConversionType);
        }

        return messagingMessageConversionType;
    }

    protected MessagingMessageConversionType removeImpl(
        MessagingMessageConversionType messagingMessageConversionType)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (messagingMessageConversionType.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(MessagingMessageConversionTypeImpl.class,
                        messagingMessageConversionType.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(messagingMessageConversionType);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        MessagingMessageConversionTypeModelImpl messagingMessageConversionTypeModelImpl =
            (MessagingMessageConversionTypeModelImpl) messagingMessageConversionType;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
            new Object[] {
                messagingMessageConversionTypeModelImpl.getOriginalName()
            });

        EntityCacheUtil.removeResult(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeImpl.class,
            messagingMessageConversionType.getPrimaryKey());

        return messagingMessageConversionType;
    }

    /**
     * @deprecated Use <code>update(MessagingMessageConversionType messagingMessageConversionType, boolean merge)</code>.
     */
    public MessagingMessageConversionType update(
        MessagingMessageConversionType messagingMessageConversionType)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(MessagingMessageConversionType messagingMessageConversionType) method. Use update(MessagingMessageConversionType messagingMessageConversionType, boolean merge) instead.");
        }

        return update(messagingMessageConversionType, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                messagingMessageConversionType the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when messagingMessageConversionType is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public MessagingMessageConversionType update(
        MessagingMessageConversionType messagingMessageConversionType,
        boolean merge) throws SystemException {
        boolean isNew = messagingMessageConversionType.isNew();

        for (ModelListener<MessagingMessageConversionType> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(messagingMessageConversionType);
            } else {
                listener.onBeforeUpdate(messagingMessageConversionType);
            }
        }

        messagingMessageConversionType = updateImpl(messagingMessageConversionType,
                merge);

        for (ModelListener<MessagingMessageConversionType> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(messagingMessageConversionType);
            } else {
                listener.onAfterUpdate(messagingMessageConversionType);
            }
        }

        return messagingMessageConversionType;
    }

    public MessagingMessageConversionType updateImpl(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversionType messagingMessageConversionType,
        boolean merge) throws SystemException {
        boolean isNew = messagingMessageConversionType.isNew();

        MessagingMessageConversionTypeModelImpl messagingMessageConversionTypeModelImpl =
            (MessagingMessageConversionTypeModelImpl) messagingMessageConversionType;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, messagingMessageConversionType,
                merge);

            messagingMessageConversionType.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeImpl.class,
            messagingMessageConversionType.getPrimaryKey(),
            messagingMessageConversionType);

        if (!isNew &&
                (!Validator.equals(messagingMessageConversionType.getName(),
                    messagingMessageConversionTypeModelImpl.getOriginalName()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
                new Object[] {
                    messagingMessageConversionTypeModelImpl.getOriginalName()
                });
        }

        if (isNew ||
                (!Validator.equals(messagingMessageConversionType.getName(),
                    messagingMessageConversionTypeModelImpl.getOriginalName()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
                new Object[] { messagingMessageConversionType.getName() },
                messagingMessageConversionType);
        }

        return messagingMessageConversionType;
    }

    public MessagingMessageConversionType findByPrimaryKey(Long typeId)
        throws NoSuchMessagingMessageConversionTypeException, SystemException {
        MessagingMessageConversionType messagingMessageConversionType = fetchByPrimaryKey(typeId);

        if (messagingMessageConversionType == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No MessagingMessageConversionType exists with the primary key " +
                    typeId);
            }

            throw new NoSuchMessagingMessageConversionTypeException(
                "No MessagingMessageConversionType exists with the primary key " +
                typeId);
        }

        return messagingMessageConversionType;
    }

    public MessagingMessageConversionType fetchByPrimaryKey(Long typeId)
        throws SystemException {
        MessagingMessageConversionType messagingMessageConversionType = (MessagingMessageConversionType) EntityCacheUtil.getResult(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
                MessagingMessageConversionTypeImpl.class, typeId, this);

        if (messagingMessageConversionType == null) {
            Session session = null;

            try {
                session = openSession();

                messagingMessageConversionType = (MessagingMessageConversionType) session.get(MessagingMessageConversionTypeImpl.class,
                        typeId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (messagingMessageConversionType != null) {
                    cacheResult(messagingMessageConversionType);
                }

                closeSession(session);
            }
        }

        return messagingMessageConversionType;
    }

    public MessagingMessageConversionType findByfindByName(String name)
        throws NoSuchMessagingMessageConversionTypeException, SystemException {
        MessagingMessageConversionType messagingMessageConversionType = fetchByfindByName(name);

        if (messagingMessageConversionType == null) {
            StringBuilder msg = new StringBuilder();

            msg.append(
                "No MessagingMessageConversionType exists with the key {");

            msg.append("name=" + name);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchMessagingMessageConversionTypeException(msg.toString());
        }

        return messagingMessageConversionType;
    }

    public MessagingMessageConversionType fetchByfindByName(String name)
        throws SystemException {
        return fetchByfindByName(name, true);
    }

    public MessagingMessageConversionType fetchByfindByName(String name,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { name };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.mass_messaging.model.MessagingMessageConversionType WHERE ");

                if (name == null) {
                    query.append("name IS NULL");
                } else {
                    query.append("name = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (name != null) {
                    qPos.add(name);
                }

                List<MessagingMessageConversionType> list = q.list();

                result = list;

                MessagingMessageConversionType messagingMessageConversionType = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
                        finderArgs, list);
                } else {
                    messagingMessageConversionType = list.get(0);

                    cacheResult(messagingMessageConversionType);

                    if ((messagingMessageConversionType.getName() == null) ||
                            !messagingMessageConversionType.getName()
                                                               .equals(name)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
                            finderArgs, messagingMessageConversionType);
                    }
                }

                return messagingMessageConversionType;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
                        finderArgs,
                        new ArrayList<MessagingMessageConversionType>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (MessagingMessageConversionType) result;
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

    public List<MessagingMessageConversionType> findAll()
        throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<MessagingMessageConversionType> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<MessagingMessageConversionType> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<MessagingMessageConversionType> list = (List<MessagingMessageConversionType>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.mass_messaging.model.MessagingMessageConversionType ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<MessagingMessageConversionType>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<MessagingMessageConversionType>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<MessagingMessageConversionType>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByfindByName(String name)
        throws NoSuchMessagingMessageConversionTypeException, SystemException {
        MessagingMessageConversionType messagingMessageConversionType = findByfindByName(name);

        remove(messagingMessageConversionType);
    }

    public void removeAll() throws SystemException {
        for (MessagingMessageConversionType messagingMessageConversionType : findAll()) {
            remove(messagingMessageConversionType);
        }
    }

    public int countByfindByName(String name) throws SystemException {
        Object[] finderArgs = new Object[] { name };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FINDBYNAME,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.mass_messaging.model.MessagingMessageConversionType WHERE ");

                if (name == null) {
                    query.append("name IS NULL");
                } else {
                    query.append("name = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (name != null) {
                    qPos.add(name);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FINDBYNAME,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.mass_messaging.model.MessagingMessageConversionType");

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
                        "value.object.listener.com.ext.portlet.mass_messaging.model.MessagingMessageConversionType")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MessagingMessageConversionType>> listenersList =
                    new ArrayList<ModelListener<MessagingMessageConversionType>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MessagingMessageConversionType>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
