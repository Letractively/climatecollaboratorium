/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.persistence;

import com.ext.portlet.mass_messaging.NoSuchMessagingMessageConversionException;
import com.ext.portlet.mass_messaging.model.MessagingMessageConversion;
import com.ext.portlet.mass_messaging.model.impl.MessagingMessageConversionImpl;
import com.ext.portlet.mass_messaging.model.impl.MessagingMessageConversionModelImpl;

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
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MessagingMessageConversionPersistenceImpl
    extends BasePersistenceImpl implements MessagingMessageConversionPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = MessagingMessageConversionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_FINDBYTYPE = new FinderPath(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByfindByType",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_FINDBYTYPE = new FinderPath(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByfindByType",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_FINDBYTYPE = new FinderPath(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByfindByType",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(MessagingMessageConversionPersistenceImpl.class);
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
        MessagingMessageConversion messagingMessageConversion) {
        EntityCacheUtil.putResult(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionImpl.class,
            messagingMessageConversion.getPrimaryKey(),
            messagingMessageConversion);
    }

    public void cacheResult(
        List<MessagingMessageConversion> messagingMessageConversions) {
        for (MessagingMessageConversion messagingMessageConversion : messagingMessageConversions) {
            if (EntityCacheUtil.getResult(
                        MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingMessageConversionImpl.class,
                        messagingMessageConversion.getPrimaryKey(), this) == null) {
                cacheResult(messagingMessageConversion);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(MessagingMessageConversionImpl.class.getName());
        EntityCacheUtil.clearCache(MessagingMessageConversionImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public MessagingMessageConversion create(Long conversionId) {
        MessagingMessageConversion messagingMessageConversion = new MessagingMessageConversionImpl();

        messagingMessageConversion.setNew(true);
        messagingMessageConversion.setPrimaryKey(conversionId);

        return messagingMessageConversion;
    }

    public MessagingMessageConversion remove(Long conversionId)
        throws NoSuchMessagingMessageConversionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MessagingMessageConversion messagingMessageConversion = (MessagingMessageConversion) session.get(MessagingMessageConversionImpl.class,
                    conversionId);

            if (messagingMessageConversion == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No MessagingMessageConversion exists with the primary key " +
                        conversionId);
                }

                throw new NoSuchMessagingMessageConversionException(
                    "No MessagingMessageConversion exists with the primary key " +
                    conversionId);
            }

            return remove(messagingMessageConversion);
        } catch (NoSuchMessagingMessageConversionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public MessagingMessageConversion remove(
        MessagingMessageConversion messagingMessageConversion)
        throws SystemException {
        for (ModelListener<MessagingMessageConversion> listener : listeners) {
            listener.onBeforeRemove(messagingMessageConversion);
        }

        messagingMessageConversion = removeImpl(messagingMessageConversion);

        for (ModelListener<MessagingMessageConversion> listener : listeners) {
            listener.onAfterRemove(messagingMessageConversion);
        }

        return messagingMessageConversion;
    }

    protected MessagingMessageConversion removeImpl(
        MessagingMessageConversion messagingMessageConversion)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (messagingMessageConversion.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(MessagingMessageConversionImpl.class,
                        messagingMessageConversion.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(messagingMessageConversion);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionImpl.class,
            messagingMessageConversion.getPrimaryKey());

        return messagingMessageConversion;
    }

    /**
     * @deprecated Use <code>update(MessagingMessageConversion messagingMessageConversion, boolean merge)</code>.
     */
    public MessagingMessageConversion update(
        MessagingMessageConversion messagingMessageConversion)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(MessagingMessageConversion messagingMessageConversion) method. Use update(MessagingMessageConversion messagingMessageConversion, boolean merge) instead.");
        }

        return update(messagingMessageConversion, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                messagingMessageConversion the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when messagingMessageConversion is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public MessagingMessageConversion update(
        MessagingMessageConversion messagingMessageConversion, boolean merge)
        throws SystemException {
        boolean isNew = messagingMessageConversion.isNew();

        for (ModelListener<MessagingMessageConversion> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(messagingMessageConversion);
            } else {
                listener.onBeforeUpdate(messagingMessageConversion);
            }
        }

        messagingMessageConversion = updateImpl(messagingMessageConversion,
                merge);

        for (ModelListener<MessagingMessageConversion> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(messagingMessageConversion);
            } else {
                listener.onAfterUpdate(messagingMessageConversion);
            }
        }

        return messagingMessageConversion;
    }

    public MessagingMessageConversion updateImpl(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversion messagingMessageConversion,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, messagingMessageConversion, merge);

            messagingMessageConversion.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionImpl.class,
            messagingMessageConversion.getPrimaryKey(),
            messagingMessageConversion);

        return messagingMessageConversion;
    }

    public MessagingMessageConversion findByPrimaryKey(Long conversionId)
        throws NoSuchMessagingMessageConversionException, SystemException {
        MessagingMessageConversion messagingMessageConversion = fetchByPrimaryKey(conversionId);

        if (messagingMessageConversion == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No MessagingMessageConversion exists with the primary key " +
                    conversionId);
            }

            throw new NoSuchMessagingMessageConversionException(
                "No MessagingMessageConversion exists with the primary key " +
                conversionId);
        }

        return messagingMessageConversion;
    }

    public MessagingMessageConversion fetchByPrimaryKey(Long conversionId)
        throws SystemException {
        MessagingMessageConversion messagingMessageConversion = (MessagingMessageConversion) EntityCacheUtil.getResult(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
                MessagingMessageConversionImpl.class, conversionId, this);

        if (messagingMessageConversion == null) {
            Session session = null;

            try {
                session = openSession();

                messagingMessageConversion = (MessagingMessageConversion) session.get(MessagingMessageConversionImpl.class,
                        conversionId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (messagingMessageConversion != null) {
                    cacheResult(messagingMessageConversion);
                }

                closeSession(session);
            }
        }

        return messagingMessageConversion;
    }

    public List<MessagingMessageConversion> findByfindByType(Long messageId,
        Long conversionTypeId) throws SystemException {
        Object[] finderArgs = new Object[] { messageId, conversionTypeId };

        List<MessagingMessageConversion> list = (List<MessagingMessageConversion>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_FINDBYTYPE,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.mass_messaging.model.MessagingMessageConversion WHERE ");

                if (messageId == null) {
                    query.append("messageId IS NULL");
                } else {
                    query.append("messageId = ?");
                }

                query.append(" AND ");

                if (conversionTypeId == null) {
                    query.append("conversionTypeId IS NULL");
                } else {
                    query.append("conversionTypeId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (messageId != null) {
                    qPos.add(messageId.longValue());
                }

                if (conversionTypeId != null) {
                    qPos.add(conversionTypeId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<MessagingMessageConversion>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_FINDBYTYPE,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<MessagingMessageConversion> findByfindByType(Long messageId,
        Long conversionTypeId, int start, int end) throws SystemException {
        return findByfindByType(messageId, conversionTypeId, start, end, null);
    }

    public List<MessagingMessageConversion> findByfindByType(Long messageId,
        Long conversionTypeId, int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                messageId,
                
                conversionTypeId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<MessagingMessageConversion> list = (List<MessagingMessageConversion>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_FINDBYTYPE,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.mass_messaging.model.MessagingMessageConversion WHERE ");

                if (messageId == null) {
                    query.append("messageId IS NULL");
                } else {
                    query.append("messageId = ?");
                }

                query.append(" AND ");

                if (conversionTypeId == null) {
                    query.append("conversionTypeId IS NULL");
                } else {
                    query.append("conversionTypeId = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (messageId != null) {
                    qPos.add(messageId.longValue());
                }

                if (conversionTypeId != null) {
                    qPos.add(conversionTypeId.longValue());
                }

                list = (List<MessagingMessageConversion>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<MessagingMessageConversion>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_FINDBYTYPE,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public MessagingMessageConversion findByfindByType_First(Long messageId,
        Long conversionTypeId, OrderByComparator obc)
        throws NoSuchMessagingMessageConversionException, SystemException {
        List<MessagingMessageConversion> list = findByfindByType(messageId,
                conversionTypeId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No MessagingMessageConversion exists with the key {");

            msg.append("messageId=" + messageId);

            msg.append(", ");
            msg.append("conversionTypeId=" + conversionTypeId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchMessagingMessageConversionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public MessagingMessageConversion findByfindByType_Last(Long messageId,
        Long conversionTypeId, OrderByComparator obc)
        throws NoSuchMessagingMessageConversionException, SystemException {
        int count = countByfindByType(messageId, conversionTypeId);

        List<MessagingMessageConversion> list = findByfindByType(messageId,
                conversionTypeId, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No MessagingMessageConversion exists with the key {");

            msg.append("messageId=" + messageId);

            msg.append(", ");
            msg.append("conversionTypeId=" + conversionTypeId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchMessagingMessageConversionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public MessagingMessageConversion[] findByfindByType_PrevAndNext(
        Long conversionId, Long messageId, Long conversionTypeId,
        OrderByComparator obc)
        throws NoSuchMessagingMessageConversionException, SystemException {
        MessagingMessageConversion messagingMessageConversion = findByPrimaryKey(conversionId);

        int count = countByfindByType(messageId, conversionTypeId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.mass_messaging.model.MessagingMessageConversion WHERE ");

            if (messageId == null) {
                query.append("messageId IS NULL");
            } else {
                query.append("messageId = ?");
            }

            query.append(" AND ");

            if (conversionTypeId == null) {
                query.append("conversionTypeId IS NULL");
            } else {
                query.append("conversionTypeId = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (messageId != null) {
                qPos.add(messageId.longValue());
            }

            if (conversionTypeId != null) {
                qPos.add(conversionTypeId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    messagingMessageConversion);

            MessagingMessageConversion[] array = new MessagingMessageConversionImpl[3];

            array[0] = (MessagingMessageConversion) objArray[0];
            array[1] = (MessagingMessageConversion) objArray[1];
            array[2] = (MessagingMessageConversion) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
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

    public List<MessagingMessageConversion> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<MessagingMessageConversion> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<MessagingMessageConversion> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<MessagingMessageConversion> list = (List<MessagingMessageConversion>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.mass_messaging.model.MessagingMessageConversion ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<MessagingMessageConversion>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<MessagingMessageConversion>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<MessagingMessageConversion>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByfindByType(Long messageId, Long conversionTypeId)
        throws SystemException {
        for (MessagingMessageConversion messagingMessageConversion : findByfindByType(
                messageId, conversionTypeId)) {
            remove(messagingMessageConversion);
        }
    }

    public void removeAll() throws SystemException {
        for (MessagingMessageConversion messagingMessageConversion : findAll()) {
            remove(messagingMessageConversion);
        }
    }

    public int countByfindByType(Long messageId, Long conversionTypeId)
        throws SystemException {
        Object[] finderArgs = new Object[] { messageId, conversionTypeId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FINDBYTYPE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.mass_messaging.model.MessagingMessageConversion WHERE ");

                if (messageId == null) {
                    query.append("messageId IS NULL");
                } else {
                    query.append("messageId = ?");
                }

                query.append(" AND ");

                if (conversionTypeId == null) {
                    query.append("conversionTypeId IS NULL");
                } else {
                    query.append("conversionTypeId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (messageId != null) {
                    qPos.add(messageId.longValue());
                }

                if (conversionTypeId != null) {
                    qPos.add(conversionTypeId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FINDBYTYPE,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.mass_messaging.model.MessagingMessageConversion");

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
                        "value.object.listener.com.ext.portlet.mass_messaging.model.MessagingMessageConversion")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MessagingMessageConversion>> listenersList = new ArrayList<ModelListener<MessagingMessageConversion>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MessagingMessageConversion>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
