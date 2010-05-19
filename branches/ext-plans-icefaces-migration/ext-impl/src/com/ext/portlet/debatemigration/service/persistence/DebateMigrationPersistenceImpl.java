package com.ext.portlet.debatemigration.service.persistence;

import com.ext.portlet.debatemigration.NoSuchDebateMigrationException;
import com.ext.portlet.debatemigration.model.DebateMigration;
import com.ext.portlet.debatemigration.model.impl.DebateMigrationImpl;
import com.ext.portlet.debatemigration.model.impl.DebateMigrationModelImpl;

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


public class DebateMigrationPersistenceImpl extends BasePersistenceImpl
    implements DebateMigrationPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = DebateMigrationImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(DebateMigrationModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DebateMigrationModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(DebateMigrationPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.debatemigration.service.persistence.DebateMigrationPersistence.impl")
    protected com.ext.portlet.debatemigration.service.persistence.DebateMigrationPersistence debateMigrationPersistence;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.persistence.DebateMigrationCategoryPersistence.impl")
    protected com.ext.portlet.debatemigration.service.persistence.DebateMigrationCategoryPersistence debateMigrationCategoryPersistence;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.persistence.DebateMigrationDebatePersistence.impl")
    protected com.ext.portlet.debatemigration.service.persistence.DebateMigrationDebatePersistence debateMigrationDebatePersistence;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.persistence.DebateMigrationItemPersistence.impl")
    protected com.ext.portlet.debatemigration.service.persistence.DebateMigrationItemPersistence debateMigrationItemPersistence;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.persistence.DebateMigrationCommentPersistence.impl")
    protected com.ext.portlet.debatemigration.service.persistence.DebateMigrationCommentPersistence debateMigrationCommentPersistence;

    public void cacheResult(DebateMigration debateMigration) {
        EntityCacheUtil.putResult(DebateMigrationModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationImpl.class, debateMigration.getPrimaryKey(),
            debateMigration);
    }

    public void cacheResult(List<DebateMigration> debateMigrations) {
        for (DebateMigration debateMigration : debateMigrations) {
            if (EntityCacheUtil.getResult(
                        DebateMigrationModelImpl.ENTITY_CACHE_ENABLED,
                        DebateMigrationImpl.class,
                        debateMigration.getPrimaryKey(), this) == null) {
                cacheResult(debateMigration);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(DebateMigrationImpl.class.getName());
        EntityCacheUtil.clearCache(DebateMigrationImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public DebateMigration create(Long debateMigrationPK) {
        DebateMigration debateMigration = new DebateMigrationImpl();

        debateMigration.setNew(true);
        debateMigration.setPrimaryKey(debateMigrationPK);

        return debateMigration;
    }

    public DebateMigration remove(Long debateMigrationPK)
        throws NoSuchDebateMigrationException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DebateMigration debateMigration = (DebateMigration) session.get(DebateMigrationImpl.class,
                    debateMigrationPK);

            if (debateMigration == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No DebateMigration exists with the primary key " +
                        debateMigrationPK);
                }

                throw new NoSuchDebateMigrationException(
                    "No DebateMigration exists with the primary key " +
                    debateMigrationPK);
            }

            return remove(debateMigration);
        } catch (NoSuchDebateMigrationException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public DebateMigration remove(DebateMigration debateMigration)
        throws SystemException {
        for (ModelListener<DebateMigration> listener : listeners) {
            listener.onBeforeRemove(debateMigration);
        }

        debateMigration = removeImpl(debateMigration);

        for (ModelListener<DebateMigration> listener : listeners) {
            listener.onAfterRemove(debateMigration);
        }

        return debateMigration;
    }

    protected DebateMigration removeImpl(DebateMigration debateMigration)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (debateMigration.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(DebateMigrationImpl.class,
                        debateMigration.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(debateMigration);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(DebateMigrationModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationImpl.class, debateMigration.getPrimaryKey());

        return debateMigration;
    }

    /**
     * @deprecated Use <code>update(DebateMigration debateMigration, boolean merge)</code>.
     */
    public DebateMigration update(DebateMigration debateMigration)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(DebateMigration debateMigration) method. Use update(DebateMigration debateMigration, boolean merge) instead.");
        }

        return update(debateMigration, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateMigration the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateMigration is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public DebateMigration update(DebateMigration debateMigration, boolean merge)
        throws SystemException {
        boolean isNew = debateMigration.isNew();

        for (ModelListener<DebateMigration> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(debateMigration);
            } else {
                listener.onBeforeUpdate(debateMigration);
            }
        }

        debateMigration = updateImpl(debateMigration, merge);

        for (ModelListener<DebateMigration> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(debateMigration);
            } else {
                listener.onAfterUpdate(debateMigration);
            }
        }

        return debateMigration;
    }

    public DebateMigration updateImpl(
        com.ext.portlet.debatemigration.model.DebateMigration debateMigration,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, debateMigration, merge);

            debateMigration.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(DebateMigrationModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationImpl.class, debateMigration.getPrimaryKey(),
            debateMigration);

        return debateMigration;
    }

    public DebateMigration findByPrimaryKey(Long debateMigrationPK)
        throws NoSuchDebateMigrationException, SystemException {
        DebateMigration debateMigration = fetchByPrimaryKey(debateMigrationPK);

        if (debateMigration == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No DebateMigration exists with the primary key " +
                    debateMigrationPK);
            }

            throw new NoSuchDebateMigrationException(
                "No DebateMigration exists with the primary key " +
                debateMigrationPK);
        }

        return debateMigration;
    }

    public DebateMigration fetchByPrimaryKey(Long debateMigrationPK)
        throws SystemException {
        DebateMigration debateMigration = (DebateMigration) EntityCacheUtil.getResult(DebateMigrationModelImpl.ENTITY_CACHE_ENABLED,
                DebateMigrationImpl.class, debateMigrationPK, this);

        if (debateMigration == null) {
            Session session = null;

            try {
                session = openSession();

                debateMigration = (DebateMigration) session.get(DebateMigrationImpl.class,
                        debateMigrationPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (debateMigration != null) {
                    cacheResult(debateMigration);
                }

                closeSession(session);
            }
        }

        return debateMigration;
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

    public List<DebateMigration> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<DebateMigration> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<DebateMigration> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DebateMigration> list = (List<DebateMigration>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debatemigration.model.DebateMigration ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<DebateMigration>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<DebateMigration>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateMigration>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (DebateMigration debateMigration : findAll()) {
            remove(debateMigration);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.debatemigration.model.DebateMigration");

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
                        "value.object.listener.com.ext.portlet.debatemigration.model.DebateMigration")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DebateMigration>> listenersList = new ArrayList<ModelListener<DebateMigration>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DebateMigration>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
