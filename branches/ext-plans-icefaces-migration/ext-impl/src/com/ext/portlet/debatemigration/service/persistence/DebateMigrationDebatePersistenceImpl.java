package com.ext.portlet.debatemigration.service.persistence;

import com.ext.portlet.debatemigration.NoSuchDebateMigrationDebateException;
import com.ext.portlet.debatemigration.model.DebateMigrationDebate;
import com.ext.portlet.debatemigration.model.impl.DebateMigrationDebateImpl;
import com.ext.portlet.debatemigration.model.impl.DebateMigrationDebateModelImpl;

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


public class DebateMigrationDebatePersistenceImpl extends BasePersistenceImpl
    implements DebateMigrationDebatePersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = DebateMigrationDebateImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(DebateMigrationDebateModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationDebateModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DebateMigrationDebateModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationDebateModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(DebateMigrationDebatePersistenceImpl.class);
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

    public void cacheResult(DebateMigrationDebate debateMigrationDebate) {
        EntityCacheUtil.putResult(DebateMigrationDebateModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationDebateImpl.class,
            debateMigrationDebate.getPrimaryKey(), debateMigrationDebate);
    }

    public void cacheResult(List<DebateMigrationDebate> debateMigrationDebates) {
        for (DebateMigrationDebate debateMigrationDebate : debateMigrationDebates) {
            if (EntityCacheUtil.getResult(
                        DebateMigrationDebateModelImpl.ENTITY_CACHE_ENABLED,
                        DebateMigrationDebateImpl.class,
                        debateMigrationDebate.getPrimaryKey(), this) == null) {
                cacheResult(debateMigrationDebate);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(DebateMigrationDebateImpl.class.getName());
        EntityCacheUtil.clearCache(DebateMigrationDebateImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public DebateMigrationDebate create(Long debateMigrationDebatePK) {
        DebateMigrationDebate debateMigrationDebate = new DebateMigrationDebateImpl();

        debateMigrationDebate.setNew(true);
        debateMigrationDebate.setPrimaryKey(debateMigrationDebatePK);

        return debateMigrationDebate;
    }

    public DebateMigrationDebate remove(Long debateMigrationDebatePK)
        throws NoSuchDebateMigrationDebateException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DebateMigrationDebate debateMigrationDebate = (DebateMigrationDebate) session.get(DebateMigrationDebateImpl.class,
                    debateMigrationDebatePK);

            if (debateMigrationDebate == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No DebateMigrationDebate exists with the primary key " +
                        debateMigrationDebatePK);
                }

                throw new NoSuchDebateMigrationDebateException(
                    "No DebateMigrationDebate exists with the primary key " +
                    debateMigrationDebatePK);
            }

            return remove(debateMigrationDebate);
        } catch (NoSuchDebateMigrationDebateException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public DebateMigrationDebate remove(
        DebateMigrationDebate debateMigrationDebate) throws SystemException {
        for (ModelListener<DebateMigrationDebate> listener : listeners) {
            listener.onBeforeRemove(debateMigrationDebate);
        }

        debateMigrationDebate = removeImpl(debateMigrationDebate);

        for (ModelListener<DebateMigrationDebate> listener : listeners) {
            listener.onAfterRemove(debateMigrationDebate);
        }

        return debateMigrationDebate;
    }

    protected DebateMigrationDebate removeImpl(
        DebateMigrationDebate debateMigrationDebate) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (debateMigrationDebate.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(DebateMigrationDebateImpl.class,
                        debateMigrationDebate.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(debateMigrationDebate);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(DebateMigrationDebateModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationDebateImpl.class,
            debateMigrationDebate.getPrimaryKey());

        return debateMigrationDebate;
    }

    /**
     * @deprecated Use <code>update(DebateMigrationDebate debateMigrationDebate, boolean merge)</code>.
     */
    public DebateMigrationDebate update(
        DebateMigrationDebate debateMigrationDebate) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(DebateMigrationDebate debateMigrationDebate) method. Use update(DebateMigrationDebate debateMigrationDebate, boolean merge) instead.");
        }

        return update(debateMigrationDebate, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateMigrationDebate the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateMigrationDebate is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public DebateMigrationDebate update(
        DebateMigrationDebate debateMigrationDebate, boolean merge)
        throws SystemException {
        boolean isNew = debateMigrationDebate.isNew();

        for (ModelListener<DebateMigrationDebate> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(debateMigrationDebate);
            } else {
                listener.onBeforeUpdate(debateMigrationDebate);
            }
        }

        debateMigrationDebate = updateImpl(debateMigrationDebate, merge);

        for (ModelListener<DebateMigrationDebate> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(debateMigrationDebate);
            } else {
                listener.onAfterUpdate(debateMigrationDebate);
            }
        }

        return debateMigrationDebate;
    }

    public DebateMigrationDebate updateImpl(
        com.ext.portlet.debatemigration.model.DebateMigrationDebate debateMigrationDebate,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, debateMigrationDebate, merge);

            debateMigrationDebate.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(DebateMigrationDebateModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationDebateImpl.class,
            debateMigrationDebate.getPrimaryKey(), debateMigrationDebate);

        return debateMigrationDebate;
    }

    public DebateMigrationDebate findByPrimaryKey(Long debateMigrationDebatePK)
        throws NoSuchDebateMigrationDebateException, SystemException {
        DebateMigrationDebate debateMigrationDebate = fetchByPrimaryKey(debateMigrationDebatePK);

        if (debateMigrationDebate == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No DebateMigrationDebate exists with the primary key " +
                    debateMigrationDebatePK);
            }

            throw new NoSuchDebateMigrationDebateException(
                "No DebateMigrationDebate exists with the primary key " +
                debateMigrationDebatePK);
        }

        return debateMigrationDebate;
    }

    public DebateMigrationDebate fetchByPrimaryKey(Long debateMigrationDebatePK)
        throws SystemException {
        DebateMigrationDebate debateMigrationDebate = (DebateMigrationDebate) EntityCacheUtil.getResult(DebateMigrationDebateModelImpl.ENTITY_CACHE_ENABLED,
                DebateMigrationDebateImpl.class, debateMigrationDebatePK, this);

        if (debateMigrationDebate == null) {
            Session session = null;

            try {
                session = openSession();

                debateMigrationDebate = (DebateMigrationDebate) session.get(DebateMigrationDebateImpl.class,
                        debateMigrationDebatePK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (debateMigrationDebate != null) {
                    cacheResult(debateMigrationDebate);
                }

                closeSession(session);
            }
        }

        return debateMigrationDebate;
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

    public List<DebateMigrationDebate> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<DebateMigrationDebate> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<DebateMigrationDebate> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DebateMigrationDebate> list = (List<DebateMigrationDebate>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debatemigration.model.DebateMigrationDebate ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<DebateMigrationDebate>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<DebateMigrationDebate>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateMigrationDebate>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (DebateMigrationDebate debateMigrationDebate : findAll()) {
            remove(debateMigrationDebate);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.debatemigration.model.DebateMigrationDebate");

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
                        "value.object.listener.com.ext.portlet.debatemigration.model.DebateMigrationDebate")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DebateMigrationDebate>> listenersList = new ArrayList<ModelListener<DebateMigrationDebate>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DebateMigrationDebate>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
