package com.ext.portlet.debatemigration.service.persistence;

import com.ext.portlet.debatemigration.NoSuchDebateMigrationCategoryException;
import com.ext.portlet.debatemigration.model.DebateMigrationCategory;
import com.ext.portlet.debatemigration.model.impl.DebateMigrationCategoryImpl;
import com.ext.portlet.debatemigration.model.impl.DebateMigrationCategoryModelImpl;

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


public class DebateMigrationCategoryPersistenceImpl extends BasePersistenceImpl
    implements DebateMigrationCategoryPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = DebateMigrationCategoryImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(DebateMigrationCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationCategoryModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DebateMigrationCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationCategoryModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(DebateMigrationCategoryPersistenceImpl.class);
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

    public void cacheResult(DebateMigrationCategory debateMigrationCategory) {
        EntityCacheUtil.putResult(DebateMigrationCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationCategoryImpl.class,
            debateMigrationCategory.getPrimaryKey(), debateMigrationCategory);
    }

    public void cacheResult(
        List<DebateMigrationCategory> debateMigrationCategories) {
        for (DebateMigrationCategory debateMigrationCategory : debateMigrationCategories) {
            if (EntityCacheUtil.getResult(
                        DebateMigrationCategoryModelImpl.ENTITY_CACHE_ENABLED,
                        DebateMigrationCategoryImpl.class,
                        debateMigrationCategory.getPrimaryKey(), this) == null) {
                cacheResult(debateMigrationCategory);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(DebateMigrationCategoryImpl.class.getName());
        EntityCacheUtil.clearCache(DebateMigrationCategoryImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public DebateMigrationCategory create(Long debateMigrationCategoryPK) {
        DebateMigrationCategory debateMigrationCategory = new DebateMigrationCategoryImpl();

        debateMigrationCategory.setNew(true);
        debateMigrationCategory.setPrimaryKey(debateMigrationCategoryPK);

        return debateMigrationCategory;
    }

    public DebateMigrationCategory remove(Long debateMigrationCategoryPK)
        throws NoSuchDebateMigrationCategoryException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DebateMigrationCategory debateMigrationCategory = (DebateMigrationCategory) session.get(DebateMigrationCategoryImpl.class,
                    debateMigrationCategoryPK);

            if (debateMigrationCategory == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No DebateMigrationCategory exists with the primary key " +
                        debateMigrationCategoryPK);
                }

                throw new NoSuchDebateMigrationCategoryException(
                    "No DebateMigrationCategory exists with the primary key " +
                    debateMigrationCategoryPK);
            }

            return remove(debateMigrationCategory);
        } catch (NoSuchDebateMigrationCategoryException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public DebateMigrationCategory remove(
        DebateMigrationCategory debateMigrationCategory)
        throws SystemException {
        for (ModelListener<DebateMigrationCategory> listener : listeners) {
            listener.onBeforeRemove(debateMigrationCategory);
        }

        debateMigrationCategory = removeImpl(debateMigrationCategory);

        for (ModelListener<DebateMigrationCategory> listener : listeners) {
            listener.onAfterRemove(debateMigrationCategory);
        }

        return debateMigrationCategory;
    }

    protected DebateMigrationCategory removeImpl(
        DebateMigrationCategory debateMigrationCategory)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (debateMigrationCategory.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(DebateMigrationCategoryImpl.class,
                        debateMigrationCategory.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(debateMigrationCategory);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(DebateMigrationCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationCategoryImpl.class,
            debateMigrationCategory.getPrimaryKey());

        return debateMigrationCategory;
    }

    /**
     * @deprecated Use <code>update(DebateMigrationCategory debateMigrationCategory, boolean merge)</code>.
     */
    public DebateMigrationCategory update(
        DebateMigrationCategory debateMigrationCategory)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(DebateMigrationCategory debateMigrationCategory) method. Use update(DebateMigrationCategory debateMigrationCategory, boolean merge) instead.");
        }

        return update(debateMigrationCategory, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateMigrationCategory the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateMigrationCategory is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public DebateMigrationCategory update(
        DebateMigrationCategory debateMigrationCategory, boolean merge)
        throws SystemException {
        boolean isNew = debateMigrationCategory.isNew();

        for (ModelListener<DebateMigrationCategory> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(debateMigrationCategory);
            } else {
                listener.onBeforeUpdate(debateMigrationCategory);
            }
        }

        debateMigrationCategory = updateImpl(debateMigrationCategory, merge);

        for (ModelListener<DebateMigrationCategory> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(debateMigrationCategory);
            } else {
                listener.onAfterUpdate(debateMigrationCategory);
            }
        }

        return debateMigrationCategory;
    }

    public DebateMigrationCategory updateImpl(
        com.ext.portlet.debatemigration.model.DebateMigrationCategory debateMigrationCategory,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, debateMigrationCategory, merge);

            debateMigrationCategory.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(DebateMigrationCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DebateMigrationCategoryImpl.class,
            debateMigrationCategory.getPrimaryKey(), debateMigrationCategory);

        return debateMigrationCategory;
    }

    public DebateMigrationCategory findByPrimaryKey(
        Long debateMigrationCategoryPK)
        throws NoSuchDebateMigrationCategoryException, SystemException {
        DebateMigrationCategory debateMigrationCategory = fetchByPrimaryKey(debateMigrationCategoryPK);

        if (debateMigrationCategory == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No DebateMigrationCategory exists with the primary key " +
                    debateMigrationCategoryPK);
            }

            throw new NoSuchDebateMigrationCategoryException(
                "No DebateMigrationCategory exists with the primary key " +
                debateMigrationCategoryPK);
        }

        return debateMigrationCategory;
    }

    public DebateMigrationCategory fetchByPrimaryKey(
        Long debateMigrationCategoryPK) throws SystemException {
        DebateMigrationCategory debateMigrationCategory = (DebateMigrationCategory) EntityCacheUtil.getResult(DebateMigrationCategoryModelImpl.ENTITY_CACHE_ENABLED,
                DebateMigrationCategoryImpl.class, debateMigrationCategoryPK,
                this);

        if (debateMigrationCategory == null) {
            Session session = null;

            try {
                session = openSession();

                debateMigrationCategory = (DebateMigrationCategory) session.get(DebateMigrationCategoryImpl.class,
                        debateMigrationCategoryPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (debateMigrationCategory != null) {
                    cacheResult(debateMigrationCategory);
                }

                closeSession(session);
            }
        }

        return debateMigrationCategory;
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

    public List<DebateMigrationCategory> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<DebateMigrationCategory> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<DebateMigrationCategory> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DebateMigrationCategory> list = (List<DebateMigrationCategory>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debatemigration.model.DebateMigrationCategory ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<DebateMigrationCategory>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<DebateMigrationCategory>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateMigrationCategory>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (DebateMigrationCategory debateMigrationCategory : findAll()) {
            remove(debateMigrationCategory);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.debatemigration.model.DebateMigrationCategory");

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
                        "value.object.listener.com.ext.portlet.debatemigration.model.DebateMigrationCategory")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DebateMigrationCategory>> listenersList = new ArrayList<ModelListener<DebateMigrationCategory>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DebateMigrationCategory>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
