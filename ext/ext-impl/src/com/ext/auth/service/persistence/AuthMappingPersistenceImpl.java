package com.ext.auth.service.persistence;

import com.ext.auth.NoSuchAuthMappingException;
import com.ext.auth.model.AuthMapping;
import com.ext.auth.model.impl.AuthMappingImpl;
import com.ext.auth.model.impl.AuthMappingModelImpl;

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


public class AuthMappingPersistenceImpl extends BasePersistenceImpl
    implements AuthMappingPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = AuthMappingImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(AuthMappingModelImpl.ENTITY_CACHE_ENABLED,
            AuthMappingModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AuthMappingModelImpl.ENTITY_CACHE_ENABLED,
            AuthMappingModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(AuthMappingPersistenceImpl.class);
    @BeanReference(name = "com.ext.auth.service.persistence.AuthMappingPersistence.impl")
    protected com.ext.auth.service.persistence.AuthMappingPersistence authMappingPersistence;

    public void cacheResult(AuthMapping authMapping) {
        EntityCacheUtil.putResult(AuthMappingModelImpl.ENTITY_CACHE_ENABLED,
            AuthMappingImpl.class, authMapping.getPrimaryKey(), authMapping);
    }

    public void cacheResult(List<AuthMapping> authMappings) {
        for (AuthMapping authMapping : authMappings) {
            if (EntityCacheUtil.getResult(
                        AuthMappingModelImpl.ENTITY_CACHE_ENABLED,
                        AuthMappingImpl.class, authMapping.getPrimaryKey(), this) == null) {
                cacheResult(authMapping);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(AuthMappingImpl.class.getName());
        EntityCacheUtil.clearCache(AuthMappingImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public AuthMapping create(String identifier) {
        AuthMapping authMapping = new AuthMappingImpl();

        authMapping.setNew(true);
        authMapping.setPrimaryKey(identifier);

        return authMapping;
    }

    public AuthMapping remove(String identifier)
        throws NoSuchAuthMappingException, SystemException {
        Session session = null;

        try {
            session = openSession();

            AuthMapping authMapping = (AuthMapping) session.get(AuthMappingImpl.class,
                    identifier);

            if (authMapping == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No AuthMapping exists with the primary key " +
                        identifier);
                }

                throw new NoSuchAuthMappingException(
                    "No AuthMapping exists with the primary key " + identifier);
            }

            return remove(authMapping);
        } catch (NoSuchAuthMappingException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public AuthMapping remove(AuthMapping authMapping)
        throws SystemException {
        for (ModelListener<AuthMapping> listener : listeners) {
            listener.onBeforeRemove(authMapping);
        }

        authMapping = removeImpl(authMapping);

        for (ModelListener<AuthMapping> listener : listeners) {
            listener.onAfterRemove(authMapping);
        }

        return authMapping;
    }

    protected AuthMapping removeImpl(AuthMapping authMapping)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (authMapping.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(AuthMappingImpl.class,
                        authMapping.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(authMapping);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(AuthMappingModelImpl.ENTITY_CACHE_ENABLED,
            AuthMappingImpl.class, authMapping.getPrimaryKey());

        return authMapping;
    }

    /**
     * @deprecated Use <code>update(AuthMapping authMapping, boolean merge)</code>.
     */
    public AuthMapping update(AuthMapping authMapping)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(AuthMapping authMapping) method. Use update(AuthMapping authMapping, boolean merge) instead.");
        }

        return update(authMapping, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                authMapping the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when authMapping is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public AuthMapping update(AuthMapping authMapping, boolean merge)
        throws SystemException {
        boolean isNew = authMapping.isNew();

        for (ModelListener<AuthMapping> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(authMapping);
            } else {
                listener.onBeforeUpdate(authMapping);
            }
        }

        authMapping = updateImpl(authMapping, merge);

        for (ModelListener<AuthMapping> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(authMapping);
            } else {
                listener.onAfterUpdate(authMapping);
            }
        }

        return authMapping;
    }

    public AuthMapping updateImpl(com.ext.auth.model.AuthMapping authMapping,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, authMapping, merge);

            authMapping.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(AuthMappingModelImpl.ENTITY_CACHE_ENABLED,
            AuthMappingImpl.class, authMapping.getPrimaryKey(), authMapping);

        return authMapping;
    }

    public AuthMapping findByPrimaryKey(String identifier)
        throws NoSuchAuthMappingException, SystemException {
        AuthMapping authMapping = fetchByPrimaryKey(identifier);

        if (authMapping == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No AuthMapping exists with the primary key " +
                    identifier);
            }

            throw new NoSuchAuthMappingException(
                "No AuthMapping exists with the primary key " + identifier);
        }

        return authMapping;
    }

    public AuthMapping fetchByPrimaryKey(String identifier)
        throws SystemException {
        AuthMapping authMapping = (AuthMapping) EntityCacheUtil.getResult(AuthMappingModelImpl.ENTITY_CACHE_ENABLED,
                AuthMappingImpl.class, identifier, this);

        if (authMapping == null) {
            Session session = null;

            try {
                session = openSession();

                authMapping = (AuthMapping) session.get(AuthMappingImpl.class,
                        identifier);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (authMapping != null) {
                    cacheResult(authMapping);
                }

                closeSession(session);
            }
        }

        return authMapping;
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

    public List<AuthMapping> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<AuthMapping> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<AuthMapping> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<AuthMapping> list = (List<AuthMapping>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.auth.model.AuthMapping ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<AuthMapping>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<AuthMapping>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<AuthMapping>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (AuthMapping authMapping : findAll()) {
            remove(authMapping);
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
                        "SELECT COUNT(*) FROM com.ext.auth.model.AuthMapping");

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
                        "value.object.listener.com.ext.auth.model.AuthMapping")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<AuthMapping>> listenersList = new ArrayList<ModelListener<AuthMapping>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<AuthMapping>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
