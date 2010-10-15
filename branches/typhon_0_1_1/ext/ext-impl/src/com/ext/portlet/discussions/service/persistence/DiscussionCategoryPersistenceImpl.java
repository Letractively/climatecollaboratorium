package com.ext.portlet.discussions.service.persistence;

import com.ext.portlet.discussions.NoSuchDiscussionCategoryException;
import com.ext.portlet.discussions.model.DiscussionCategory;
import com.ext.portlet.discussions.model.impl.DiscussionCategoryImpl;
import com.ext.portlet.discussions.model.impl.DiscussionCategoryModelImpl;

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


public class DiscussionCategoryPersistenceImpl extends BasePersistenceImpl
    implements DiscussionCategoryPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = DiscussionCategoryImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_CATEGORYGROUPID = new FinderPath(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByCategoryGroupId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_CATEGORYGROUPID = new FinderPath(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByCategoryGroupId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_CATEGORYGROUPID = new FinderPath(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByCategoryGroupId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_CATEGORYID = new FinderPath(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByCategoryId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_CATEGORYID = new FinderPath(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByCategoryId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(DiscussionCategoryPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.discussions.service.persistence.DiscussionCategoryGroupPersistence.impl")
    protected com.ext.portlet.discussions.service.persistence.DiscussionCategoryGroupPersistence discussionCategoryGroupPersistence;
    @BeanReference(name = "com.ext.portlet.discussions.service.persistence.DiscussionCategoryPersistence.impl")
    protected com.ext.portlet.discussions.service.persistence.DiscussionCategoryPersistence discussionCategoryPersistence;
    @BeanReference(name = "com.ext.portlet.discussions.service.persistence.DiscussionMessagePersistence.impl")
    protected com.ext.portlet.discussions.service.persistence.DiscussionMessagePersistence discussionMessagePersistence;

    public void cacheResult(DiscussionCategory discussionCategory) {
        EntityCacheUtil.putResult(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryImpl.class, discussionCategory.getPrimaryKey(),
            discussionCategory);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CATEGORYID,
            new Object[] { discussionCategory.getCategoryId() },
            discussionCategory);
    }

    public void cacheResult(List<DiscussionCategory> discussionCategories) {
        for (DiscussionCategory discussionCategory : discussionCategories) {
            if (EntityCacheUtil.getResult(
                        DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
                        DiscussionCategoryImpl.class,
                        discussionCategory.getPrimaryKey(), this) == null) {
                cacheResult(discussionCategory);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(DiscussionCategoryImpl.class.getName());
        EntityCacheUtil.clearCache(DiscussionCategoryImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public DiscussionCategory create(Long pk) {
        DiscussionCategory discussionCategory = new DiscussionCategoryImpl();

        discussionCategory.setNew(true);
        discussionCategory.setPrimaryKey(pk);

        return discussionCategory;
    }

    public DiscussionCategory remove(Long pk)
        throws NoSuchDiscussionCategoryException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DiscussionCategory discussionCategory = (DiscussionCategory) session.get(DiscussionCategoryImpl.class,
                    pk);

            if (discussionCategory == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No DiscussionCategory exists with the primary key " +
                        pk);
                }

                throw new NoSuchDiscussionCategoryException(
                    "No DiscussionCategory exists with the primary key " + pk);
            }

            return remove(discussionCategory);
        } catch (NoSuchDiscussionCategoryException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public DiscussionCategory remove(DiscussionCategory discussionCategory)
        throws SystemException {
        for (ModelListener<DiscussionCategory> listener : listeners) {
            listener.onBeforeRemove(discussionCategory);
        }

        discussionCategory = removeImpl(discussionCategory);

        for (ModelListener<DiscussionCategory> listener : listeners) {
            listener.onAfterRemove(discussionCategory);
        }

        return discussionCategory;
    }

    protected DiscussionCategory removeImpl(
        DiscussionCategory discussionCategory) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (discussionCategory.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(DiscussionCategoryImpl.class,
                        discussionCategory.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(discussionCategory);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        DiscussionCategoryModelImpl discussionCategoryModelImpl = (DiscussionCategoryModelImpl) discussionCategory;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CATEGORYID,
            new Object[] { discussionCategoryModelImpl.getOriginalCategoryId() });

        EntityCacheUtil.removeResult(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryImpl.class, discussionCategory.getPrimaryKey());

        return discussionCategory;
    }

    /**
     * @deprecated Use <code>update(DiscussionCategory discussionCategory, boolean merge)</code>.
     */
    public DiscussionCategory update(DiscussionCategory discussionCategory)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(DiscussionCategory discussionCategory) method. Use update(DiscussionCategory discussionCategory, boolean merge) instead.");
        }

        return update(discussionCategory, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                discussionCategory the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when discussionCategory is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public DiscussionCategory update(DiscussionCategory discussionCategory,
        boolean merge) throws SystemException {
        boolean isNew = discussionCategory.isNew();

        for (ModelListener<DiscussionCategory> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(discussionCategory);
            } else {
                listener.onBeforeUpdate(discussionCategory);
            }
        }

        discussionCategory = updateImpl(discussionCategory, merge);

        for (ModelListener<DiscussionCategory> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(discussionCategory);
            } else {
                listener.onAfterUpdate(discussionCategory);
            }
        }

        return discussionCategory;
    }

    public DiscussionCategory updateImpl(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory,
        boolean merge) throws SystemException {
        boolean isNew = discussionCategory.isNew();

        DiscussionCategoryModelImpl discussionCategoryModelImpl = (DiscussionCategoryModelImpl) discussionCategory;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, discussionCategory, merge);

            discussionCategory.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryImpl.class, discussionCategory.getPrimaryKey(),
            discussionCategory);

        if (!isNew &&
                (!Validator.equals(discussionCategory.getCategoryId(),
                    discussionCategoryModelImpl.getOriginalCategoryId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CATEGORYID,
                new Object[] { discussionCategoryModelImpl.getOriginalCategoryId() });
        }

        if (isNew ||
                (!Validator.equals(discussionCategory.getCategoryId(),
                    discussionCategoryModelImpl.getOriginalCategoryId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CATEGORYID,
                new Object[] { discussionCategory.getCategoryId() },
                discussionCategory);
        }

        return discussionCategory;
    }

    public DiscussionCategory findByPrimaryKey(Long pk)
        throws NoSuchDiscussionCategoryException, SystemException {
        DiscussionCategory discussionCategory = fetchByPrimaryKey(pk);

        if (discussionCategory == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No DiscussionCategory exists with the primary key " +
                    pk);
            }

            throw new NoSuchDiscussionCategoryException(
                "No DiscussionCategory exists with the primary key " + pk);
        }

        return discussionCategory;
    }

    public DiscussionCategory fetchByPrimaryKey(Long pk)
        throws SystemException {
        DiscussionCategory discussionCategory = (DiscussionCategory) EntityCacheUtil.getResult(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
                DiscussionCategoryImpl.class, pk, this);

        if (discussionCategory == null) {
            Session session = null;

            try {
                session = openSession();

                discussionCategory = (DiscussionCategory) session.get(DiscussionCategoryImpl.class,
                        pk);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (discussionCategory != null) {
                    cacheResult(discussionCategory);
                }

                closeSession(session);
            }
        }

        return discussionCategory;
    }

    public List<DiscussionCategory> findByCategoryGroupId(Long categoryGroupId)
        throws SystemException {
        Object[] finderArgs = new Object[] { categoryGroupId };

        List<DiscussionCategory> list = (List<DiscussionCategory>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_CATEGORYGROUPID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionCategory WHERE ");

                if (categoryGroupId == null) {
                    query.append("categoryGroupId IS NULL");
                } else {
                    query.append("categoryGroupId = ?");
                }

                query.append(" AND deleted is null ");

                query.append("ORDER BY ");

                query.append("createDate DESC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (categoryGroupId != null) {
                    qPos.add(categoryGroupId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DiscussionCategory>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_CATEGORYGROUPID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<DiscussionCategory> findByCategoryGroupId(
        Long categoryGroupId, int start, int end) throws SystemException {
        return findByCategoryGroupId(categoryGroupId, start, end, null);
    }

    public List<DiscussionCategory> findByCategoryGroupId(
        Long categoryGroupId, int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                categoryGroupId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DiscussionCategory> list = (List<DiscussionCategory>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_CATEGORYGROUPID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionCategory WHERE ");

                if (categoryGroupId == null) {
                    query.append("categoryGroupId IS NULL");
                } else {
                    query.append("categoryGroupId = ?");
                }

                query.append(" AND deleted is null ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("createDate DESC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (categoryGroupId != null) {
                    qPos.add(categoryGroupId.longValue());
                }

                list = (List<DiscussionCategory>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DiscussionCategory>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_CATEGORYGROUPID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public DiscussionCategory findByCategoryGroupId_First(
        Long categoryGroupId, OrderByComparator obc)
        throws NoSuchDiscussionCategoryException, SystemException {
        List<DiscussionCategory> list = findByCategoryGroupId(categoryGroupId,
                0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DiscussionCategory exists with the key {");

            msg.append("categoryGroupId=" + categoryGroupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDiscussionCategoryException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DiscussionCategory findByCategoryGroupId_Last(Long categoryGroupId,
        OrderByComparator obc)
        throws NoSuchDiscussionCategoryException, SystemException {
        int count = countByCategoryGroupId(categoryGroupId);

        List<DiscussionCategory> list = findByCategoryGroupId(categoryGroupId,
                count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DiscussionCategory exists with the key {");

            msg.append("categoryGroupId=" + categoryGroupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDiscussionCategoryException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public DiscussionCategory[] findByCategoryGroupId_PrevAndNext(Long pk,
        Long categoryGroupId, OrderByComparator obc)
        throws NoSuchDiscussionCategoryException, SystemException {
        DiscussionCategory discussionCategory = findByPrimaryKey(pk);

        int count = countByCategoryGroupId(categoryGroupId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.discussions.model.DiscussionCategory WHERE ");

            if (categoryGroupId == null) {
                query.append("categoryGroupId IS NULL");
            } else {
                query.append("categoryGroupId = ?");
            }

            query.append(" AND deleted is null ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("createDate DESC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (categoryGroupId != null) {
                qPos.add(categoryGroupId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    discussionCategory);

            DiscussionCategory[] array = new DiscussionCategoryImpl[3];

            array[0] = (DiscussionCategory) objArray[0];
            array[1] = (DiscussionCategory) objArray[1];
            array[2] = (DiscussionCategory) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public DiscussionCategory findByCategoryId(Long categoryId)
        throws NoSuchDiscussionCategoryException, SystemException {
        DiscussionCategory discussionCategory = fetchByCategoryId(categoryId);

        if (discussionCategory == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DiscussionCategory exists with the key {");

            msg.append("categoryId=" + categoryId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchDiscussionCategoryException(msg.toString());
        }

        return discussionCategory;
    }

    public DiscussionCategory fetchByCategoryId(Long categoryId)
        throws SystemException {
        return fetchByCategoryId(categoryId, true);
    }

    public DiscussionCategory fetchByCategoryId(Long categoryId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { categoryId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CATEGORYID,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionCategory WHERE ");

                if (categoryId == null) {
                    query.append("categoryId IS NULL");
                } else {
                    query.append("categoryId = ?");
                }

                query.append(" AND deleted is null ");

                query.append("ORDER BY ");

                query.append("createDate DESC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (categoryId != null) {
                    qPos.add(categoryId.longValue());
                }

                List<DiscussionCategory> list = q.list();

                result = list;

                DiscussionCategory discussionCategory = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CATEGORYID,
                        finderArgs, list);
                } else {
                    discussionCategory = list.get(0);

                    cacheResult(discussionCategory);

                    if ((discussionCategory.getCategoryId() == null) ||
                            !discussionCategory.getCategoryId()
                                                   .equals(categoryId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CATEGORYID,
                            finderArgs, discussionCategory);
                    }
                }

                return discussionCategory;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CATEGORYID,
                        finderArgs, new ArrayList<DiscussionCategory>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (DiscussionCategory) result;
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

    public List<DiscussionCategory> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<DiscussionCategory> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<DiscussionCategory> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DiscussionCategory> list = (List<DiscussionCategory>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionCategory ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("createDate DESC");
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<DiscussionCategory>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<DiscussionCategory>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DiscussionCategory>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByCategoryGroupId(Long categoryGroupId)
        throws SystemException {
        for (DiscussionCategory discussionCategory : findByCategoryGroupId(
                categoryGroupId)) {
            remove(discussionCategory);
        }
    }

    public void removeByCategoryId(Long categoryId)
        throws NoSuchDiscussionCategoryException, SystemException {
        DiscussionCategory discussionCategory = findByCategoryId(categoryId);

        remove(discussionCategory);
    }

    public void removeAll() throws SystemException {
        for (DiscussionCategory discussionCategory : findAll()) {
            remove(discussionCategory);
        }
    }

    public int countByCategoryGroupId(Long categoryGroupId)
        throws SystemException {
        Object[] finderArgs = new Object[] { categoryGroupId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CATEGORYGROUPID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.discussions.model.DiscussionCategory WHERE ");

                if (categoryGroupId == null) {
                    query.append("categoryGroupId IS NULL");
                } else {
                    query.append("categoryGroupId = ?");
                }

                query.append(" AND deleted is null ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (categoryGroupId != null) {
                    qPos.add(categoryGroupId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CATEGORYGROUPID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
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
                    "FROM com.ext.portlet.discussions.model.DiscussionCategory WHERE ");

                if (categoryId == null) {
                    query.append("categoryId IS NULL");
                } else {
                    query.append("categoryId = ?");
                }

                query.append(" AND deleted is null ");

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
                        "SELECT COUNT(*) FROM com.ext.portlet.discussions.model.DiscussionCategory");

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
                        "value.object.listener.com.ext.portlet.discussions.model.DiscussionCategory")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DiscussionCategory>> listenersList = new ArrayList<ModelListener<DiscussionCategory>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DiscussionCategory>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
