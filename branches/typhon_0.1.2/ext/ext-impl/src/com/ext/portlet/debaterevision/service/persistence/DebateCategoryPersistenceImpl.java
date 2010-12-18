package com.ext.portlet.debaterevision.service.persistence;

import com.ext.portlet.debaterevision.NoSuchDebateCategoryException;
import com.ext.portlet.debaterevision.model.DebateCategory;
import com.ext.portlet.debaterevision.model.impl.DebateCategoryImpl;
import com.ext.portlet.debaterevision.model.impl.DebateCategoryModelImpl;

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


public class DebateCategoryPersistenceImpl extends BasePersistenceImpl
    implements DebateCategoryPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = DebateCategoryImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(DebateCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DebateCategoryModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DebateCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DebateCategoryModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(DebateCategoryPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebatePersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebatePersistence debatePersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateItemPersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebateItemPersistence debateItemPersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateItemReferencePersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebateItemReferencePersistence debateItemReferencePersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateCategoryPersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebateCategoryPersistence debateCategoryPersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateCategoryMapPersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebateCategoryMapPersistence debateCategoryMapPersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateCommentPersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebateCommentPersistence debateCommentPersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateItemVotePersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebateItemVotePersistence debateItemVotePersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateItemVoteStatsPersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebateItemVoteStatsPersistence debateItemVoteStatsPersistence;

    public void cacheResult(DebateCategory debateCategory) {
        EntityCacheUtil.putResult(DebateCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DebateCategoryImpl.class, debateCategory.getPrimaryKey(),
            debateCategory);
    }

    public void cacheResult(List<DebateCategory> debateCategories) {
        for (DebateCategory debateCategory : debateCategories) {
            if (EntityCacheUtil.getResult(
                        DebateCategoryModelImpl.ENTITY_CACHE_ENABLED,
                        DebateCategoryImpl.class,
                        debateCategory.getPrimaryKey(), this) == null) {
                cacheResult(debateCategory);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(DebateCategoryImpl.class.getName());
        EntityCacheUtil.clearCache(DebateCategoryImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public DebateCategory create(Long debateCategoryPK) {
        DebateCategory debateCategory = new DebateCategoryImpl();

        debateCategory.setNew(true);
        debateCategory.setPrimaryKey(debateCategoryPK);

        return debateCategory;
    }

    public DebateCategory remove(Long debateCategoryPK)
        throws NoSuchDebateCategoryException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DebateCategory debateCategory = (DebateCategory) session.get(DebateCategoryImpl.class,
                    debateCategoryPK);

            if (debateCategory == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No DebateCategory exists with the primary key " +
                        debateCategoryPK);
                }

                throw new NoSuchDebateCategoryException(
                    "No DebateCategory exists with the primary key " +
                    debateCategoryPK);
            }

            return remove(debateCategory);
        } catch (NoSuchDebateCategoryException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public DebateCategory remove(DebateCategory debateCategory)
        throws SystemException {
        for (ModelListener<DebateCategory> listener : listeners) {
            listener.onBeforeRemove(debateCategory);
        }

        debateCategory = removeImpl(debateCategory);

        for (ModelListener<DebateCategory> listener : listeners) {
            listener.onAfterRemove(debateCategory);
        }

        return debateCategory;
    }

    protected DebateCategory removeImpl(DebateCategory debateCategory)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (debateCategory.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(DebateCategoryImpl.class,
                        debateCategory.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(debateCategory);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(DebateCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DebateCategoryImpl.class, debateCategory.getPrimaryKey());

        return debateCategory;
    }

    /**
     * @deprecated Use <code>update(DebateCategory debateCategory, boolean merge)</code>.
     */
    public DebateCategory update(DebateCategory debateCategory)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(DebateCategory debateCategory) method. Use update(DebateCategory debateCategory, boolean merge) instead.");
        }

        return update(debateCategory, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateCategory the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateCategory is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public DebateCategory update(DebateCategory debateCategory, boolean merge)
        throws SystemException {
        boolean isNew = debateCategory.isNew();

        for (ModelListener<DebateCategory> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(debateCategory);
            } else {
                listener.onBeforeUpdate(debateCategory);
            }
        }

        debateCategory = updateImpl(debateCategory, merge);

        for (ModelListener<DebateCategory> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(debateCategory);
            } else {
                listener.onAfterUpdate(debateCategory);
            }
        }

        return debateCategory;
    }

    public DebateCategory updateImpl(
        com.ext.portlet.debaterevision.model.DebateCategory debateCategory,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, debateCategory, merge);

            debateCategory.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(DebateCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DebateCategoryImpl.class, debateCategory.getPrimaryKey(),
            debateCategory);

        return debateCategory;
    }

    public DebateCategory findByPrimaryKey(Long debateCategoryPK)
        throws NoSuchDebateCategoryException, SystemException {
        DebateCategory debateCategory = fetchByPrimaryKey(debateCategoryPK);

        if (debateCategory == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No DebateCategory exists with the primary key " +
                    debateCategoryPK);
            }

            throw new NoSuchDebateCategoryException(
                "No DebateCategory exists with the primary key " +
                debateCategoryPK);
        }

        return debateCategory;
    }

    public DebateCategory fetchByPrimaryKey(Long debateCategoryPK)
        throws SystemException {
        DebateCategory debateCategory = (DebateCategory) EntityCacheUtil.getResult(DebateCategoryModelImpl.ENTITY_CACHE_ENABLED,
                DebateCategoryImpl.class, debateCategoryPK, this);

        if (debateCategory == null) {
            Session session = null;

            try {
                session = openSession();

                debateCategory = (DebateCategory) session.get(DebateCategoryImpl.class,
                        debateCategoryPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (debateCategory != null) {
                    cacheResult(debateCategory);
                }

                closeSession(session);
            }
        }

        return debateCategory;
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

    public List<DebateCategory> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<DebateCategory> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<DebateCategory> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DebateCategory> list = (List<DebateCategory>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateCategory ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<DebateCategory>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<DebateCategory>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateCategory>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (DebateCategory debateCategory : findAll()) {
            remove(debateCategory);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.debaterevision.model.DebateCategory");

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
                        "value.object.listener.com.ext.portlet.debaterevision.model.DebateCategory")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DebateCategory>> listenersList = new ArrayList<ModelListener<DebateCategory>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DebateCategory>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
