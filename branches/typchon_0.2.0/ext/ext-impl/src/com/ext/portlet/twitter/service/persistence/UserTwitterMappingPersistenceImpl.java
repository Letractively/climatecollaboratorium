package com.ext.portlet.twitter.service.persistence;

import com.ext.portlet.twitter.NoSuchUserTwitterMappingException;
import com.ext.portlet.twitter.model.UserTwitterMapping;
import com.ext.portlet.twitter.model.impl.UserTwitterMappingImpl;
import com.ext.portlet.twitter.model.impl.UserTwitterMappingModelImpl;

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


public class UserTwitterMappingPersistenceImpl extends BasePersistenceImpl
    implements UserTwitterMappingPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = UserTwitterMappingImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(UserTwitterMappingModelImpl.ENTITY_CACHE_ENABLED,
            UserTwitterMappingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UserTwitterMappingModelImpl.ENTITY_CACHE_ENABLED,
            UserTwitterMappingModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(UserTwitterMappingPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.twitter.service.persistence.UserTwitterMappingPersistence.impl")
    protected com.ext.portlet.twitter.service.persistence.UserTwitterMappingPersistence userTwitterMappingPersistence;

    public void cacheResult(UserTwitterMapping userTwitterMapping) {
        EntityCacheUtil.putResult(UserTwitterMappingModelImpl.ENTITY_CACHE_ENABLED,
            UserTwitterMappingImpl.class, userTwitterMapping.getPrimaryKey(),
            userTwitterMapping);
    }

    public void cacheResult(List<UserTwitterMapping> userTwitterMappings) {
        for (UserTwitterMapping userTwitterMapping : userTwitterMappings) {
            if (EntityCacheUtil.getResult(
                        UserTwitterMappingModelImpl.ENTITY_CACHE_ENABLED,
                        UserTwitterMappingImpl.class,
                        userTwitterMapping.getPrimaryKey(), this) == null) {
                cacheResult(userTwitterMapping);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(UserTwitterMappingImpl.class.getName());
        EntityCacheUtil.clearCache(UserTwitterMappingImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public UserTwitterMapping create(Long twitterId) {
        UserTwitterMapping userTwitterMapping = new UserTwitterMappingImpl();

        userTwitterMapping.setNew(true);
        userTwitterMapping.setPrimaryKey(twitterId);

        return userTwitterMapping;
    }

    public UserTwitterMapping remove(Long twitterId)
        throws NoSuchUserTwitterMappingException, SystemException {
        Session session = null;

        try {
            session = openSession();

            UserTwitterMapping userTwitterMapping = (UserTwitterMapping) session.get(UserTwitterMappingImpl.class,
                    twitterId);

            if (userTwitterMapping == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No UserTwitterMapping exists with the primary key " +
                        twitterId);
                }

                throw new NoSuchUserTwitterMappingException(
                    "No UserTwitterMapping exists with the primary key " +
                    twitterId);
            }

            return remove(userTwitterMapping);
        } catch (NoSuchUserTwitterMappingException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public UserTwitterMapping remove(UserTwitterMapping userTwitterMapping)
        throws SystemException {
        for (ModelListener<UserTwitterMapping> listener : listeners) {
            listener.onBeforeRemove(userTwitterMapping);
        }

        userTwitterMapping = removeImpl(userTwitterMapping);

        for (ModelListener<UserTwitterMapping> listener : listeners) {
            listener.onAfterRemove(userTwitterMapping);
        }

        return userTwitterMapping;
    }

    protected UserTwitterMapping removeImpl(
        UserTwitterMapping userTwitterMapping) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (userTwitterMapping.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(UserTwitterMappingImpl.class,
                        userTwitterMapping.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(userTwitterMapping);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(UserTwitterMappingModelImpl.ENTITY_CACHE_ENABLED,
            UserTwitterMappingImpl.class, userTwitterMapping.getPrimaryKey());

        return userTwitterMapping;
    }

    /**
     * @deprecated Use <code>update(UserTwitterMapping userTwitterMapping, boolean merge)</code>.
     */
    public UserTwitterMapping update(UserTwitterMapping userTwitterMapping)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(UserTwitterMapping userTwitterMapping) method. Use update(UserTwitterMapping userTwitterMapping, boolean merge) instead.");
        }

        return update(userTwitterMapping, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                userTwitterMapping the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when userTwitterMapping is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public UserTwitterMapping update(UserTwitterMapping userTwitterMapping,
        boolean merge) throws SystemException {
        boolean isNew = userTwitterMapping.isNew();

        for (ModelListener<UserTwitterMapping> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(userTwitterMapping);
            } else {
                listener.onBeforeUpdate(userTwitterMapping);
            }
        }

        userTwitterMapping = updateImpl(userTwitterMapping, merge);

        for (ModelListener<UserTwitterMapping> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(userTwitterMapping);
            } else {
                listener.onAfterUpdate(userTwitterMapping);
            }
        }

        return userTwitterMapping;
    }

    public UserTwitterMapping updateImpl(
        com.ext.portlet.twitter.model.UserTwitterMapping userTwitterMapping,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, userTwitterMapping, merge);

            userTwitterMapping.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(UserTwitterMappingModelImpl.ENTITY_CACHE_ENABLED,
            UserTwitterMappingImpl.class, userTwitterMapping.getPrimaryKey(),
            userTwitterMapping);

        return userTwitterMapping;
    }

    public UserTwitterMapping findByPrimaryKey(Long twitterId)
        throws NoSuchUserTwitterMappingException, SystemException {
        UserTwitterMapping userTwitterMapping = fetchByPrimaryKey(twitterId);

        if (userTwitterMapping == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No UserTwitterMapping exists with the primary key " +
                    twitterId);
            }

            throw new NoSuchUserTwitterMappingException(
                "No UserTwitterMapping exists with the primary key " +
                twitterId);
        }

        return userTwitterMapping;
    }

    public UserTwitterMapping fetchByPrimaryKey(Long twitterId)
        throws SystemException {
        UserTwitterMapping userTwitterMapping = (UserTwitterMapping) EntityCacheUtil.getResult(UserTwitterMappingModelImpl.ENTITY_CACHE_ENABLED,
                UserTwitterMappingImpl.class, twitterId, this);

        if (userTwitterMapping == null) {
            Session session = null;

            try {
                session = openSession();

                userTwitterMapping = (UserTwitterMapping) session.get(UserTwitterMappingImpl.class,
                        twitterId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (userTwitterMapping != null) {
                    cacheResult(userTwitterMapping);
                }

                closeSession(session);
            }
        }

        return userTwitterMapping;
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

    public List<UserTwitterMapping> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<UserTwitterMapping> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<UserTwitterMapping> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<UserTwitterMapping> list = (List<UserTwitterMapping>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.twitter.model.UserTwitterMapping ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<UserTwitterMapping>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<UserTwitterMapping>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<UserTwitterMapping>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (UserTwitterMapping userTwitterMapping : findAll()) {
            remove(userTwitterMapping);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.twitter.model.UserTwitterMapping");

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
                        "value.object.listener.com.ext.portlet.twitter.model.UserTwitterMapping")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<UserTwitterMapping>> listenersList = new ArrayList<ModelListener<UserTwitterMapping>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<UserTwitterMapping>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
