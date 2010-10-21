package com.ext.portlet.facebook.service.persistence;

import com.ext.portlet.facebook.NoSuchUserFacebookMappingException;
import com.ext.portlet.facebook.model.UserFacebookMapping;
import com.ext.portlet.facebook.model.impl.UserFacebookMappingImpl;
import com.ext.portlet.facebook.model.impl.UserFacebookMappingModelImpl;

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


public class UserFacebookMappingPersistenceImpl extends BasePersistenceImpl
    implements UserFacebookMappingPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = UserFacebookMappingImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_FINDBYFACEBOOKID = new FinderPath(UserFacebookMappingModelImpl.ENTITY_CACHE_ENABLED,
            UserFacebookMappingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByfindByFacebookId",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_FINDBYFACEBOOKID = new FinderPath(UserFacebookMappingModelImpl.ENTITY_CACHE_ENABLED,
            UserFacebookMappingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByfindByFacebookId",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(UserFacebookMappingModelImpl.ENTITY_CACHE_ENABLED,
            UserFacebookMappingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UserFacebookMappingModelImpl.ENTITY_CACHE_ENABLED,
            UserFacebookMappingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(UserFacebookMappingPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.facebook.service.persistence.UserFacebookMappingPersistence.impl")
    protected com.ext.portlet.facebook.service.persistence.UserFacebookMappingPersistence userFacebookMappingPersistence;

    public void cacheResult(UserFacebookMapping userFacebookMapping) {
        EntityCacheUtil.putResult(UserFacebookMappingModelImpl.ENTITY_CACHE_ENABLED,
            UserFacebookMappingImpl.class, userFacebookMapping.getPrimaryKey(),
            userFacebookMapping);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYFACEBOOKID,
            new Object[] { userFacebookMapping.getFacebookId() },
            userFacebookMapping);
    }

    public void cacheResult(List<UserFacebookMapping> userFacebookMappings) {
        for (UserFacebookMapping userFacebookMapping : userFacebookMappings) {
            if (EntityCacheUtil.getResult(
                        UserFacebookMappingModelImpl.ENTITY_CACHE_ENABLED,
                        UserFacebookMappingImpl.class,
                        userFacebookMapping.getPrimaryKey(), this) == null) {
                cacheResult(userFacebookMapping);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(UserFacebookMappingImpl.class.getName());
        EntityCacheUtil.clearCache(UserFacebookMappingImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public UserFacebookMapping create(Long userId) {
        UserFacebookMapping userFacebookMapping = new UserFacebookMappingImpl();

        userFacebookMapping.setNew(true);
        userFacebookMapping.setPrimaryKey(userId);

        return userFacebookMapping;
    }

    public UserFacebookMapping remove(Long userId)
        throws NoSuchUserFacebookMappingException, SystemException {
        Session session = null;

        try {
            session = openSession();

            UserFacebookMapping userFacebookMapping = (UserFacebookMapping) session.get(UserFacebookMappingImpl.class,
                    userId);

            if (userFacebookMapping == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No UserFacebookMapping exists with the primary key " +
                        userId);
                }

                throw new NoSuchUserFacebookMappingException(
                    "No UserFacebookMapping exists with the primary key " +
                    userId);
            }

            return remove(userFacebookMapping);
        } catch (NoSuchUserFacebookMappingException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public UserFacebookMapping remove(UserFacebookMapping userFacebookMapping)
        throws SystemException {
        for (ModelListener<UserFacebookMapping> listener : listeners) {
            listener.onBeforeRemove(userFacebookMapping);
        }

        userFacebookMapping = removeImpl(userFacebookMapping);

        for (ModelListener<UserFacebookMapping> listener : listeners) {
            listener.onAfterRemove(userFacebookMapping);
        }

        return userFacebookMapping;
    }

    protected UserFacebookMapping removeImpl(
        UserFacebookMapping userFacebookMapping) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (userFacebookMapping.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(UserFacebookMappingImpl.class,
                        userFacebookMapping.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(userFacebookMapping);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        UserFacebookMappingModelImpl userFacebookMappingModelImpl = (UserFacebookMappingModelImpl) userFacebookMapping;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYFACEBOOKID,
            new Object[] { userFacebookMappingModelImpl.getOriginalFacebookId() });

        EntityCacheUtil.removeResult(UserFacebookMappingModelImpl.ENTITY_CACHE_ENABLED,
            UserFacebookMappingImpl.class, userFacebookMapping.getPrimaryKey());

        return userFacebookMapping;
    }

    /**
     * @deprecated Use <code>update(UserFacebookMapping userFacebookMapping, boolean merge)</code>.
     */
    public UserFacebookMapping update(UserFacebookMapping userFacebookMapping)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(UserFacebookMapping userFacebookMapping) method. Use update(UserFacebookMapping userFacebookMapping, boolean merge) instead.");
        }

        return update(userFacebookMapping, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                userFacebookMapping the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when userFacebookMapping is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public UserFacebookMapping update(UserFacebookMapping userFacebookMapping,
        boolean merge) throws SystemException {
        boolean isNew = userFacebookMapping.isNew();

        for (ModelListener<UserFacebookMapping> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(userFacebookMapping);
            } else {
                listener.onBeforeUpdate(userFacebookMapping);
            }
        }

        userFacebookMapping = updateImpl(userFacebookMapping, merge);

        for (ModelListener<UserFacebookMapping> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(userFacebookMapping);
            } else {
                listener.onAfterUpdate(userFacebookMapping);
            }
        }

        return userFacebookMapping;
    }

    public UserFacebookMapping updateImpl(
        com.ext.portlet.facebook.model.UserFacebookMapping userFacebookMapping,
        boolean merge) throws SystemException {
        boolean isNew = userFacebookMapping.isNew();

        UserFacebookMappingModelImpl userFacebookMappingModelImpl = (UserFacebookMappingModelImpl) userFacebookMapping;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, userFacebookMapping, merge);

            userFacebookMapping.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(UserFacebookMappingModelImpl.ENTITY_CACHE_ENABLED,
            UserFacebookMappingImpl.class, userFacebookMapping.getPrimaryKey(),
            userFacebookMapping);

        if (!isNew &&
                (!Validator.equals(userFacebookMapping.getFacebookId(),
                    userFacebookMappingModelImpl.getOriginalFacebookId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYFACEBOOKID,
                new Object[] {
                    userFacebookMappingModelImpl.getOriginalFacebookId()
                });
        }

        if (isNew ||
                (!Validator.equals(userFacebookMapping.getFacebookId(),
                    userFacebookMappingModelImpl.getOriginalFacebookId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYFACEBOOKID,
                new Object[] { userFacebookMapping.getFacebookId() },
                userFacebookMapping);
        }

        return userFacebookMapping;
    }

    public UserFacebookMapping findByPrimaryKey(Long userId)
        throws NoSuchUserFacebookMappingException, SystemException {
        UserFacebookMapping userFacebookMapping = fetchByPrimaryKey(userId);

        if (userFacebookMapping == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No UserFacebookMapping exists with the primary key " +
                    userId);
            }

            throw new NoSuchUserFacebookMappingException(
                "No UserFacebookMapping exists with the primary key " + userId);
        }

        return userFacebookMapping;
    }

    public UserFacebookMapping fetchByPrimaryKey(Long userId)
        throws SystemException {
        UserFacebookMapping userFacebookMapping = (UserFacebookMapping) EntityCacheUtil.getResult(UserFacebookMappingModelImpl.ENTITY_CACHE_ENABLED,
                UserFacebookMappingImpl.class, userId, this);

        if (userFacebookMapping == null) {
            Session session = null;

            try {
                session = openSession();

                userFacebookMapping = (UserFacebookMapping) session.get(UserFacebookMappingImpl.class,
                        userId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (userFacebookMapping != null) {
                    cacheResult(userFacebookMapping);
                }

                closeSession(session);
            }
        }

        return userFacebookMapping;
    }

    public UserFacebookMapping findByfindByFacebookId(String facebookId)
        throws NoSuchUserFacebookMappingException, SystemException {
        UserFacebookMapping userFacebookMapping = fetchByfindByFacebookId(facebookId);

        if (userFacebookMapping == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No UserFacebookMapping exists with the key {");

            msg.append("facebookId=" + facebookId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchUserFacebookMappingException(msg.toString());
        }

        return userFacebookMapping;
    }

    public UserFacebookMapping fetchByfindByFacebookId(String facebookId)
        throws SystemException {
        return fetchByfindByFacebookId(facebookId, true);
    }

    public UserFacebookMapping fetchByfindByFacebookId(String facebookId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { facebookId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_FINDBYFACEBOOKID,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.facebook.model.UserFacebookMapping WHERE ");

                if (facebookId == null) {
                    query.append("facebookId IS NULL");
                } else {
                    query.append("facebookId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (facebookId != null) {
                    qPos.add(facebookId);
                }

                List<UserFacebookMapping> list = q.list();

                result = list;

                UserFacebookMapping userFacebookMapping = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYFACEBOOKID,
                        finderArgs, list);
                } else {
                    userFacebookMapping = list.get(0);

                    cacheResult(userFacebookMapping);

                    if ((userFacebookMapping.getFacebookId() == null) ||
                            !userFacebookMapping.getFacebookId()
                                                    .equals(facebookId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYFACEBOOKID,
                            finderArgs, userFacebookMapping);
                    }
                }

                return userFacebookMapping;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYFACEBOOKID,
                        finderArgs, new ArrayList<UserFacebookMapping>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (UserFacebookMapping) result;
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

    public List<UserFacebookMapping> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<UserFacebookMapping> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<UserFacebookMapping> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<UserFacebookMapping> list = (List<UserFacebookMapping>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.facebook.model.UserFacebookMapping ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<UserFacebookMapping>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<UserFacebookMapping>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<UserFacebookMapping>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByfindByFacebookId(String facebookId)
        throws NoSuchUserFacebookMappingException, SystemException {
        UserFacebookMapping userFacebookMapping = findByfindByFacebookId(facebookId);

        remove(userFacebookMapping);
    }

    public void removeAll() throws SystemException {
        for (UserFacebookMapping userFacebookMapping : findAll()) {
            remove(userFacebookMapping);
        }
    }

    public int countByfindByFacebookId(String facebookId)
        throws SystemException {
        Object[] finderArgs = new Object[] { facebookId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FINDBYFACEBOOKID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.facebook.model.UserFacebookMapping WHERE ");

                if (facebookId == null) {
                    query.append("facebookId IS NULL");
                } else {
                    query.append("facebookId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (facebookId != null) {
                    qPos.add(facebookId);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FINDBYFACEBOOKID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.facebook.model.UserFacebookMapping");

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
                        "value.object.listener.com.ext.portlet.facebook.model.UserFacebookMapping")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<UserFacebookMapping>> listenersList = new ArrayList<ModelListener<UserFacebookMapping>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<UserFacebookMapping>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
