package com.ext.portlet.ontology.service.persistence;

import com.ext.portlet.ontology.NoSuchCategoryOntologyTermException;
import com.ext.portlet.ontology.model.CategoryOntologyTerm;
import com.ext.portlet.ontology.model.impl.CategoryOntologyTermImpl;
import com.ext.portlet.ontology.model.impl.CategoryOntologyTermModelImpl;

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


public class CategoryOntologyTermPersistenceImpl extends BasePersistenceImpl
    implements CategoryOntologyTermPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = CategoryOntologyTermImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_CATEGORYID = new FinderPath(CategoryOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            CategoryOntologyTermModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByCategoryId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_CATEGORYID = new FinderPath(CategoryOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            CategoryOntologyTermModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByCategoryId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_CATEGORYID = new FinderPath(CategoryOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            CategoryOntologyTermModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByCategoryId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(CategoryOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            CategoryOntologyTermModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CategoryOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            CategoryOntologyTermModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(CategoryOntologyTermPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.OntologyTermPersistence.impl")
    protected com.ext.portlet.ontology.service.persistence.OntologyTermPersistence ontologyTermPersistence;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.OntologyTermEntityPersistence.impl")
    protected com.ext.portlet.ontology.service.persistence.OntologyTermEntityPersistence ontologyTermEntityPersistence;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.CategoryPersistence.impl")
    protected com.ext.portlet.ontology.service.persistence.CategoryPersistence categoryPersistence;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.CategoryOntologyTermPersistence.impl")
    protected com.ext.portlet.ontology.service.persistence.CategoryOntologyTermPersistence categoryOntologyTermPersistence;

    public void cacheResult(CategoryOntologyTerm categoryOntologyTerm) {
        EntityCacheUtil.putResult(CategoryOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            CategoryOntologyTermImpl.class,
            categoryOntologyTerm.getPrimaryKey(), categoryOntologyTerm);
    }

    public void cacheResult(List<CategoryOntologyTerm> categoryOntologyTerms) {
        for (CategoryOntologyTerm categoryOntologyTerm : categoryOntologyTerms) {
            if (EntityCacheUtil.getResult(
                        CategoryOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
                        CategoryOntologyTermImpl.class,
                        categoryOntologyTerm.getPrimaryKey(), this) == null) {
                cacheResult(categoryOntologyTerm);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(CategoryOntologyTermImpl.class.getName());
        EntityCacheUtil.clearCache(CategoryOntologyTermImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public CategoryOntologyTerm create(
        CategoryOntologyTermPK categoryOntologyTermPK) {
        CategoryOntologyTerm categoryOntologyTerm = new CategoryOntologyTermImpl();

        categoryOntologyTerm.setNew(true);
        categoryOntologyTerm.setPrimaryKey(categoryOntologyTermPK);

        return categoryOntologyTerm;
    }

    public CategoryOntologyTerm remove(
        CategoryOntologyTermPK categoryOntologyTermPK)
        throws NoSuchCategoryOntologyTermException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CategoryOntologyTerm categoryOntologyTerm = (CategoryOntologyTerm) session.get(CategoryOntologyTermImpl.class,
                    categoryOntologyTermPK);

            if (categoryOntologyTerm == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No CategoryOntologyTerm exists with the primary key " +
                        categoryOntologyTermPK);
                }

                throw new NoSuchCategoryOntologyTermException(
                    "No CategoryOntologyTerm exists with the primary key " +
                    categoryOntologyTermPK);
            }

            return remove(categoryOntologyTerm);
        } catch (NoSuchCategoryOntologyTermException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public CategoryOntologyTerm remove(
        CategoryOntologyTerm categoryOntologyTerm) throws SystemException {
        for (ModelListener<CategoryOntologyTerm> listener : listeners) {
            listener.onBeforeRemove(categoryOntologyTerm);
        }

        categoryOntologyTerm = removeImpl(categoryOntologyTerm);

        for (ModelListener<CategoryOntologyTerm> listener : listeners) {
            listener.onAfterRemove(categoryOntologyTerm);
        }

        return categoryOntologyTerm;
    }

    protected CategoryOntologyTerm removeImpl(
        CategoryOntologyTerm categoryOntologyTerm) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (categoryOntologyTerm.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(CategoryOntologyTermImpl.class,
                        categoryOntologyTerm.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(categoryOntologyTerm);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(CategoryOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            CategoryOntologyTermImpl.class, categoryOntologyTerm.getPrimaryKey());

        return categoryOntologyTerm;
    }

    /**
     * @deprecated Use <code>update(CategoryOntologyTerm categoryOntologyTerm, boolean merge)</code>.
     */
    public CategoryOntologyTerm update(
        CategoryOntologyTerm categoryOntologyTerm) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(CategoryOntologyTerm categoryOntologyTerm) method. Use update(CategoryOntologyTerm categoryOntologyTerm, boolean merge) instead.");
        }

        return update(categoryOntologyTerm, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                categoryOntologyTerm the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when categoryOntologyTerm is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public CategoryOntologyTerm update(
        CategoryOntologyTerm categoryOntologyTerm, boolean merge)
        throws SystemException {
        boolean isNew = categoryOntologyTerm.isNew();

        for (ModelListener<CategoryOntologyTerm> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(categoryOntologyTerm);
            } else {
                listener.onBeforeUpdate(categoryOntologyTerm);
            }
        }

        categoryOntologyTerm = updateImpl(categoryOntologyTerm, merge);

        for (ModelListener<CategoryOntologyTerm> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(categoryOntologyTerm);
            } else {
                listener.onAfterUpdate(categoryOntologyTerm);
            }
        }

        return categoryOntologyTerm;
    }

    public CategoryOntologyTerm updateImpl(
        com.ext.portlet.ontology.model.CategoryOntologyTerm categoryOntologyTerm,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, categoryOntologyTerm, merge);

            categoryOntologyTerm.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(CategoryOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            CategoryOntologyTermImpl.class,
            categoryOntologyTerm.getPrimaryKey(), categoryOntologyTerm);

        return categoryOntologyTerm;
    }

    public CategoryOntologyTerm findByPrimaryKey(
        CategoryOntologyTermPK categoryOntologyTermPK)
        throws NoSuchCategoryOntologyTermException, SystemException {
        CategoryOntologyTerm categoryOntologyTerm = fetchByPrimaryKey(categoryOntologyTermPK);

        if (categoryOntologyTerm == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No CategoryOntologyTerm exists with the primary key " +
                    categoryOntologyTermPK);
            }

            throw new NoSuchCategoryOntologyTermException(
                "No CategoryOntologyTerm exists with the primary key " +
                categoryOntologyTermPK);
        }

        return categoryOntologyTerm;
    }

    public CategoryOntologyTerm fetchByPrimaryKey(
        CategoryOntologyTermPK categoryOntologyTermPK)
        throws SystemException {
        CategoryOntologyTerm categoryOntologyTerm = (CategoryOntologyTerm) EntityCacheUtil.getResult(CategoryOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
                CategoryOntologyTermImpl.class, categoryOntologyTermPK, this);

        if (categoryOntologyTerm == null) {
            Session session = null;

            try {
                session = openSession();

                categoryOntologyTerm = (CategoryOntologyTerm) session.get(CategoryOntologyTermImpl.class,
                        categoryOntologyTermPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (categoryOntologyTerm != null) {
                    cacheResult(categoryOntologyTerm);
                }

                closeSession(session);
            }
        }

        return categoryOntologyTerm;
    }

    public List<CategoryOntologyTerm> findByCategoryId(Long categoryId)
        throws SystemException {
        Object[] finderArgs = new Object[] { categoryId };

        List<CategoryOntologyTerm> list = (List<CategoryOntologyTerm>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_CATEGORYID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.ontology.model.CategoryOntologyTerm WHERE ");

                if (categoryId == null) {
                    query.append("categoryId IS NULL");
                } else {
                    query.append("categoryId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (categoryId != null) {
                    qPos.add(categoryId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<CategoryOntologyTerm>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_CATEGORYID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<CategoryOntologyTerm> findByCategoryId(Long categoryId,
        int start, int end) throws SystemException {
        return findByCategoryId(categoryId, start, end, null);
    }

    public List<CategoryOntologyTerm> findByCategoryId(Long categoryId,
        int start, int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                categoryId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<CategoryOntologyTerm> list = (List<CategoryOntologyTerm>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_CATEGORYID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.ontology.model.CategoryOntologyTerm WHERE ");

                if (categoryId == null) {
                    query.append("categoryId IS NULL");
                } else {
                    query.append("categoryId = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (categoryId != null) {
                    qPos.add(categoryId.longValue());
                }

                list = (List<CategoryOntologyTerm>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<CategoryOntologyTerm>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_CATEGORYID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public CategoryOntologyTerm findByCategoryId_First(Long categoryId,
        OrderByComparator obc)
        throws NoSuchCategoryOntologyTermException, SystemException {
        List<CategoryOntologyTerm> list = findByCategoryId(categoryId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No CategoryOntologyTerm exists with the key {");

            msg.append("categoryId=" + categoryId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCategoryOntologyTermException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public CategoryOntologyTerm findByCategoryId_Last(Long categoryId,
        OrderByComparator obc)
        throws NoSuchCategoryOntologyTermException, SystemException {
        int count = countByCategoryId(categoryId);

        List<CategoryOntologyTerm> list = findByCategoryId(categoryId,
                count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No CategoryOntologyTerm exists with the key {");

            msg.append("categoryId=" + categoryId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCategoryOntologyTermException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public CategoryOntologyTerm[] findByCategoryId_PrevAndNext(
        CategoryOntologyTermPK categoryOntologyTermPK, Long categoryId,
        OrderByComparator obc)
        throws NoSuchCategoryOntologyTermException, SystemException {
        CategoryOntologyTerm categoryOntologyTerm = findByPrimaryKey(categoryOntologyTermPK);

        int count = countByCategoryId(categoryId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.ontology.model.CategoryOntologyTerm WHERE ");

            if (categoryId == null) {
                query.append("categoryId IS NULL");
            } else {
                query.append("categoryId = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (categoryId != null) {
                qPos.add(categoryId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    categoryOntologyTerm);

            CategoryOntologyTerm[] array = new CategoryOntologyTermImpl[3];

            array[0] = (CategoryOntologyTerm) objArray[0];
            array[1] = (CategoryOntologyTerm) objArray[1];
            array[2] = (CategoryOntologyTerm) objArray[2];

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

    public List<CategoryOntologyTerm> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<CategoryOntologyTerm> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<CategoryOntologyTerm> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<CategoryOntologyTerm> list = (List<CategoryOntologyTerm>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.ontology.model.CategoryOntologyTerm ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<CategoryOntologyTerm>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<CategoryOntologyTerm>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<CategoryOntologyTerm>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByCategoryId(Long categoryId) throws SystemException {
        for (CategoryOntologyTerm categoryOntologyTerm : findByCategoryId(
                categoryId)) {
            remove(categoryOntologyTerm);
        }
    }

    public void removeAll() throws SystemException {
        for (CategoryOntologyTerm categoryOntologyTerm : findAll()) {
            remove(categoryOntologyTerm);
        }
    }

    public int countByCategoryId(Long categoryId) throws SystemException {
        Object[] finderArgs = new Object[] { categoryId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CATEGORYID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.ontology.model.CategoryOntologyTerm WHERE ");

                if (categoryId == null) {
                    query.append("categoryId IS NULL");
                } else {
                    query.append("categoryId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (categoryId != null) {
                    qPos.add(categoryId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CATEGORYID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.ontology.model.CategoryOntologyTerm");

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
                        "value.object.listener.com.ext.portlet.ontology.model.CategoryOntologyTerm")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CategoryOntologyTerm>> listenersList = new ArrayList<ModelListener<CategoryOntologyTerm>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CategoryOntologyTerm>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
