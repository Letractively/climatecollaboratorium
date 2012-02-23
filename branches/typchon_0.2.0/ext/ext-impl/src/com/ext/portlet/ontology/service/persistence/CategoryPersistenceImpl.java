package com.ext.portlet.ontology.service.persistence;

import com.ext.portlet.ontology.NoSuchCategoryException;
import com.ext.portlet.ontology.model.Category;
import com.ext.portlet.ontology.model.impl.CategoryImpl;
import com.ext.portlet.ontology.model.impl.CategoryModelImpl;

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


public class CategoryPersistenceImpl extends BasePersistenceImpl
    implements CategoryPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = CategoryImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(CategoryModelImpl.ENTITY_CACHE_ENABLED,
            CategoryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
            "fetchByName", new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(CategoryModelImpl.ENTITY_CACHE_ENABLED,
            CategoryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByName", new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(CategoryModelImpl.ENTITY_CACHE_ENABLED,
            CategoryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CategoryModelImpl.ENTITY_CACHE_ENABLED,
            CategoryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(CategoryPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.OntologyTermPersistence.impl")
    protected com.ext.portlet.ontology.service.persistence.OntologyTermPersistence ontologyTermPersistence;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.OntologyTermEntityPersistence.impl")
    protected com.ext.portlet.ontology.service.persistence.OntologyTermEntityPersistence ontologyTermEntityPersistence;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.CategoryPersistence.impl")
    protected com.ext.portlet.ontology.service.persistence.CategoryPersistence categoryPersistence;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.CategoryOntologyTermPersistence.impl")
    protected com.ext.portlet.ontology.service.persistence.CategoryOntologyTermPersistence categoryOntologyTermPersistence;

    public void cacheResult(Category category) {
        EntityCacheUtil.putResult(CategoryModelImpl.ENTITY_CACHE_ENABLED,
            CategoryImpl.class, category.getPrimaryKey(), category);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
            new Object[] { category.getName() }, category);
    }

    public void cacheResult(List<Category> categories) {
        for (Category category : categories) {
            if (EntityCacheUtil.getResult(
                        CategoryModelImpl.ENTITY_CACHE_ENABLED,
                        CategoryImpl.class, category.getPrimaryKey(), this) == null) {
                cacheResult(category);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(CategoryImpl.class.getName());
        EntityCacheUtil.clearCache(CategoryImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public Category create(Long id) {
        Category category = new CategoryImpl();

        category.setNew(true);
        category.setPrimaryKey(id);

        return category;
    }

    public Category remove(Long id)
        throws NoSuchCategoryException, SystemException {
        Session session = null;

        try {
            session = openSession();

            Category category = (Category) session.get(CategoryImpl.class, id);

            if (category == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No Category exists with the primary key " + id);
                }

                throw new NoSuchCategoryException(
                    "No Category exists with the primary key " + id);
            }

            return remove(category);
        } catch (NoSuchCategoryException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public Category remove(Category category) throws SystemException {
        for (ModelListener<Category> listener : listeners) {
            listener.onBeforeRemove(category);
        }

        category = removeImpl(category);

        for (ModelListener<Category> listener : listeners) {
            listener.onAfterRemove(category);
        }

        return category;
    }

    protected Category removeImpl(Category category) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (category.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(CategoryImpl.class,
                        category.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(category);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        CategoryModelImpl categoryModelImpl = (CategoryModelImpl) category;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME,
            new Object[] { categoryModelImpl.getOriginalName() });

        EntityCacheUtil.removeResult(CategoryModelImpl.ENTITY_CACHE_ENABLED,
            CategoryImpl.class, category.getPrimaryKey());

        return category;
    }

    /**
     * @deprecated Use <code>update(Category category, boolean merge)</code>.
     */
    public Category update(Category category) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(Category category) method. Use update(Category category, boolean merge) instead.");
        }

        return update(category, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                category the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when category is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public Category update(Category category, boolean merge)
        throws SystemException {
        boolean isNew = category.isNew();

        for (ModelListener<Category> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(category);
            } else {
                listener.onBeforeUpdate(category);
            }
        }

        category = updateImpl(category, merge);

        for (ModelListener<Category> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(category);
            } else {
                listener.onAfterUpdate(category);
            }
        }

        return category;
    }

    public Category updateImpl(
        com.ext.portlet.ontology.model.Category category, boolean merge)
        throws SystemException {
        boolean isNew = category.isNew();

        CategoryModelImpl categoryModelImpl = (CategoryModelImpl) category;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, category, merge);

            category.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(CategoryModelImpl.ENTITY_CACHE_ENABLED,
            CategoryImpl.class, category.getPrimaryKey(), category);

        if (!isNew &&
                (!Validator.equals(category.getName(),
                    categoryModelImpl.getOriginalName()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME,
                new Object[] { categoryModelImpl.getOriginalName() });
        }

        if (isNew ||
                (!Validator.equals(category.getName(),
                    categoryModelImpl.getOriginalName()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                new Object[] { category.getName() }, category);
        }

        return category;
    }

    public Category findByPrimaryKey(Long id)
        throws NoSuchCategoryException, SystemException {
        Category category = fetchByPrimaryKey(id);

        if (category == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No Category exists with the primary key " + id);
            }

            throw new NoSuchCategoryException(
                "No Category exists with the primary key " + id);
        }

        return category;
    }

    public Category fetchByPrimaryKey(Long id) throws SystemException {
        Category category = (Category) EntityCacheUtil.getResult(CategoryModelImpl.ENTITY_CACHE_ENABLED,
                CategoryImpl.class, id, this);

        if (category == null) {
            Session session = null;

            try {
                session = openSession();

                category = (Category) session.get(CategoryImpl.class, id);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (category != null) {
                    cacheResult(category);
                }

                closeSession(session);
            }
        }

        return category;
    }

    public Category findByName(String name)
        throws NoSuchCategoryException, SystemException {
        Category category = fetchByName(name);

        if (category == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No Category exists with the key {");

            msg.append("name=" + name);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchCategoryException(msg.toString());
        }

        return category;
    }

    public Category fetchByName(String name) throws SystemException {
        return fetchByName(name, true);
    }

    public Category fetchByName(String name, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { name };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_NAME,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.ontology.model.Category WHERE ");

                if (name == null) {
                    query.append("name IS NULL");
                } else {
                    query.append("name = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (name != null) {
                    qPos.add(name);
                }

                List<Category> list = q.list();

                result = list;

                Category category = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                        finderArgs, list);
                } else {
                    category = list.get(0);

                    cacheResult(category);

                    if ((category.getName() == null) ||
                            !category.getName().equals(name)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                            finderArgs, category);
                    }
                }

                return category;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                        finderArgs, new ArrayList<Category>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (Category) result;
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

    public List<Category> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<Category> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    public List<Category> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<Category> list = (List<Category>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.ontology.model.Category ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<Category>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<Category>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<Category>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByName(String name)
        throws NoSuchCategoryException, SystemException {
        Category category = findByName(name);

        remove(category);
    }

    public void removeAll() throws SystemException {
        for (Category category : findAll()) {
            remove(category);
        }
    }

    public int countByName(String name) throws SystemException {
        Object[] finderArgs = new Object[] { name };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_NAME,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.ontology.model.Category WHERE ");

                if (name == null) {
                    query.append("name IS NULL");
                } else {
                    query.append("name = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (name != null) {
                    qPos.add(name);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.ontology.model.Category");

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
                        "value.object.listener.com.ext.portlet.ontology.model.Category")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<Category>> listenersList = new ArrayList<ModelListener<Category>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<Category>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
