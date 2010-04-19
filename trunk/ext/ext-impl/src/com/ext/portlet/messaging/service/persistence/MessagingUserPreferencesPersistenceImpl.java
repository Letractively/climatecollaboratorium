package com.ext.portlet.messaging.service.persistence;

import com.ext.portlet.messaging.NoSuchUserPreferencesException;
import com.ext.portlet.messaging.model.MessagingUserPreferences;
import com.ext.portlet.messaging.model.impl.MessagingUserPreferencesImpl;
import com.ext.portlet.messaging.model.impl.MessagingUserPreferencesModelImpl;

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


public class MessagingUserPreferencesPersistenceImpl extends BasePersistenceImpl
    implements MessagingUserPreferencesPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = MessagingUserPreferencesImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_MESSAGINGPREFERENCES = new FinderPath(MessagingUserPreferencesModelImpl.ENTITY_CACHE_ENABLED,
            MessagingUserPreferencesModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchBymessagingPreferences",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_MESSAGINGPREFERENCES = new FinderPath(MessagingUserPreferencesModelImpl.ENTITY_CACHE_ENABLED,
            MessagingUserPreferencesModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countBymessagingPreferences",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(MessagingUserPreferencesModelImpl.ENTITY_CACHE_ENABLED,
            MessagingUserPreferencesModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MessagingUserPreferencesModelImpl.ENTITY_CACHE_ENABLED,
            MessagingUserPreferencesModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(MessagingUserPreferencesPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.messaging.service.persistence.MessagePersistence.impl")
    protected com.ext.portlet.messaging.service.persistence.MessagePersistence messagePersistence;
    @BeanReference(name = "com.ext.portlet.messaging.service.persistence.MessageRecipientStatusPersistence.impl")
    protected com.ext.portlet.messaging.service.persistence.MessageRecipientStatusPersistence messageRecipientStatusPersistence;
    @BeanReference(name = "com.ext.portlet.messaging.service.persistence.MessagingUserPreferencesPersistence.impl")
    protected com.ext.portlet.messaging.service.persistence.MessagingUserPreferencesPersistence messagingUserPreferencesPersistence;

    public void cacheResult(MessagingUserPreferences messagingUserPreferences) {
        EntityCacheUtil.putResult(MessagingUserPreferencesModelImpl.ENTITY_CACHE_ENABLED,
            MessagingUserPreferencesImpl.class,
            messagingUserPreferences.getPrimaryKey(), messagingUserPreferences);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGINGPREFERENCES,
            new Object[] { messagingUserPreferences.getUserId() },
            messagingUserPreferences);
    }

    public void cacheResult(
        List<MessagingUserPreferences> messagingUserPreferenceses) {
        for (MessagingUserPreferences messagingUserPreferences : messagingUserPreferenceses) {
            if (EntityCacheUtil.getResult(
                        MessagingUserPreferencesModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingUserPreferencesImpl.class,
                        messagingUserPreferences.getPrimaryKey(), this) == null) {
                cacheResult(messagingUserPreferences);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(MessagingUserPreferencesImpl.class.getName());
        EntityCacheUtil.clearCache(MessagingUserPreferencesImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public MessagingUserPreferences create(Long messagingPreferencesId) {
        MessagingUserPreferences messagingUserPreferences = new MessagingUserPreferencesImpl();

        messagingUserPreferences.setNew(true);
        messagingUserPreferences.setPrimaryKey(messagingPreferencesId);

        return messagingUserPreferences;
    }

    public MessagingUserPreferences remove(Long messagingPreferencesId)
        throws NoSuchUserPreferencesException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MessagingUserPreferences messagingUserPreferences = (MessagingUserPreferences) session.get(MessagingUserPreferencesImpl.class,
                    messagingPreferencesId);

            if (messagingUserPreferences == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No MessagingUserPreferences exists with the primary key " +
                        messagingPreferencesId);
                }

                throw new NoSuchUserPreferencesException(
                    "No MessagingUserPreferences exists with the primary key " +
                    messagingPreferencesId);
            }

            return remove(messagingUserPreferences);
        } catch (NoSuchUserPreferencesException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public MessagingUserPreferences remove(
        MessagingUserPreferences messagingUserPreferences)
        throws SystemException {
        for (ModelListener<MessagingUserPreferences> listener : listeners) {
            listener.onBeforeRemove(messagingUserPreferences);
        }

        messagingUserPreferences = removeImpl(messagingUserPreferences);

        for (ModelListener<MessagingUserPreferences> listener : listeners) {
            listener.onAfterRemove(messagingUserPreferences);
        }

        return messagingUserPreferences;
    }

    protected MessagingUserPreferences removeImpl(
        MessagingUserPreferences messagingUserPreferences)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (messagingUserPreferences.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(MessagingUserPreferencesImpl.class,
                        messagingUserPreferences.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(messagingUserPreferences);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        MessagingUserPreferencesModelImpl messagingUserPreferencesModelImpl = (MessagingUserPreferencesModelImpl) messagingUserPreferences;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MESSAGINGPREFERENCES,
            new Object[] { messagingUserPreferencesModelImpl.getOriginalUserId() });

        EntityCacheUtil.removeResult(MessagingUserPreferencesModelImpl.ENTITY_CACHE_ENABLED,
            MessagingUserPreferencesImpl.class,
            messagingUserPreferences.getPrimaryKey());

        return messagingUserPreferences;
    }

    /**
     * @deprecated Use <code>update(MessagingUserPreferences messagingUserPreferences, boolean merge)</code>.
     */
    public MessagingUserPreferences update(
        MessagingUserPreferences messagingUserPreferences)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(MessagingUserPreferences messagingUserPreferences) method. Use update(MessagingUserPreferences messagingUserPreferences, boolean merge) instead.");
        }

        return update(messagingUserPreferences, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                messagingUserPreferences the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when messagingUserPreferences is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public MessagingUserPreferences update(
        MessagingUserPreferences messagingUserPreferences, boolean merge)
        throws SystemException {
        boolean isNew = messagingUserPreferences.isNew();

        for (ModelListener<MessagingUserPreferences> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(messagingUserPreferences);
            } else {
                listener.onBeforeUpdate(messagingUserPreferences);
            }
        }

        messagingUserPreferences = updateImpl(messagingUserPreferences, merge);

        for (ModelListener<MessagingUserPreferences> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(messagingUserPreferences);
            } else {
                listener.onAfterUpdate(messagingUserPreferences);
            }
        }

        return messagingUserPreferences;
    }

    public MessagingUserPreferences updateImpl(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences,
        boolean merge) throws SystemException {
        boolean isNew = messagingUserPreferences.isNew();

        MessagingUserPreferencesModelImpl messagingUserPreferencesModelImpl = (MessagingUserPreferencesModelImpl) messagingUserPreferences;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, messagingUserPreferences, merge);

            messagingUserPreferences.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(MessagingUserPreferencesModelImpl.ENTITY_CACHE_ENABLED,
            MessagingUserPreferencesImpl.class,
            messagingUserPreferences.getPrimaryKey(), messagingUserPreferences);

        if (!isNew &&
                (!Validator.equals(messagingUserPreferences.getUserId(),
                    messagingUserPreferencesModelImpl.getOriginalUserId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MESSAGINGPREFERENCES,
                new Object[] {
                    messagingUserPreferencesModelImpl.getOriginalUserId()
                });
        }

        if (isNew ||
                (!Validator.equals(messagingUserPreferences.getUserId(),
                    messagingUserPreferencesModelImpl.getOriginalUserId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGINGPREFERENCES,
                new Object[] { messagingUserPreferences.getUserId() },
                messagingUserPreferences);
        }

        return messagingUserPreferences;
    }

    public MessagingUserPreferences findByPrimaryKey(
        Long messagingPreferencesId)
        throws NoSuchUserPreferencesException, SystemException {
        MessagingUserPreferences messagingUserPreferences = fetchByPrimaryKey(messagingPreferencesId);

        if (messagingUserPreferences == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No MessagingUserPreferences exists with the primary key " +
                    messagingPreferencesId);
            }

            throw new NoSuchUserPreferencesException(
                "No MessagingUserPreferences exists with the primary key " +
                messagingPreferencesId);
        }

        return messagingUserPreferences;
    }

    public MessagingUserPreferences fetchByPrimaryKey(
        Long messagingPreferencesId) throws SystemException {
        MessagingUserPreferences messagingUserPreferences = (MessagingUserPreferences) EntityCacheUtil.getResult(MessagingUserPreferencesModelImpl.ENTITY_CACHE_ENABLED,
                MessagingUserPreferencesImpl.class, messagingPreferencesId, this);

        if (messagingUserPreferences == null) {
            Session session = null;

            try {
                session = openSession();

                messagingUserPreferences = (MessagingUserPreferences) session.get(MessagingUserPreferencesImpl.class,
                        messagingPreferencesId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (messagingUserPreferences != null) {
                    cacheResult(messagingUserPreferences);
                }

                closeSession(session);
            }
        }

        return messagingUserPreferences;
    }

    public MessagingUserPreferences findBymessagingPreferences(Long userId)
        throws NoSuchUserPreferencesException, SystemException {
        MessagingUserPreferences messagingUserPreferences = fetchBymessagingPreferences(userId);

        if (messagingUserPreferences == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No MessagingUserPreferences exists with the key {");

            msg.append("userId=" + userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchUserPreferencesException(msg.toString());
        }

        return messagingUserPreferences;
    }

    public MessagingUserPreferences fetchBymessagingPreferences(Long userId)
        throws SystemException {
        return fetchBymessagingPreferences(userId, true);
    }

    public MessagingUserPreferences fetchBymessagingPreferences(Long userId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_MESSAGINGPREFERENCES,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.messaging.model.MessagingUserPreferences WHERE ");

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

                List<MessagingUserPreferences> list = q.list();

                result = list;

                MessagingUserPreferences messagingUserPreferences = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGINGPREFERENCES,
                        finderArgs, list);
                } else {
                    messagingUserPreferences = list.get(0);

                    cacheResult(messagingUserPreferences);

                    if ((messagingUserPreferences.getUserId() == null) ||
                            !messagingUserPreferences.getUserId().equals(userId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGINGPREFERENCES,
                            finderArgs, messagingUserPreferences);
                    }
                }

                return messagingUserPreferences;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGINGPREFERENCES,
                        finderArgs, new ArrayList<MessagingUserPreferences>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (MessagingUserPreferences) result;
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

    public List<MessagingUserPreferences> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<MessagingUserPreferences> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<MessagingUserPreferences> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<MessagingUserPreferences> list = (List<MessagingUserPreferences>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.messaging.model.MessagingUserPreferences ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<MessagingUserPreferences>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<MessagingUserPreferences>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<MessagingUserPreferences>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeBymessagingPreferences(Long userId)
        throws NoSuchUserPreferencesException, SystemException {
        MessagingUserPreferences messagingUserPreferences = findBymessagingPreferences(userId);

        remove(messagingUserPreferences);
    }

    public void removeAll() throws SystemException {
        for (MessagingUserPreferences messagingUserPreferences : findAll()) {
            remove(messagingUserPreferences);
        }
    }

    public int countBymessagingPreferences(Long userId)
        throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MESSAGINGPREFERENCES,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.messaging.model.MessagingUserPreferences WHERE ");

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

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MESSAGINGPREFERENCES,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.messaging.model.MessagingUserPreferences");

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
                        "value.object.listener.com.ext.portlet.messaging.model.MessagingUserPreferences")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MessagingUserPreferences>> listenersList = new ArrayList<ModelListener<MessagingUserPreferences>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MessagingUserPreferences>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
