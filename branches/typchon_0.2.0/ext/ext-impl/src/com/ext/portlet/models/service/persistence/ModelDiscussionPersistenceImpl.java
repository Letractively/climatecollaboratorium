package com.ext.portlet.models.service.persistence;

import com.ext.portlet.models.NoSuchModelDiscussionException;
import com.ext.portlet.models.model.ModelDiscussion;
import com.ext.portlet.models.model.impl.ModelDiscussionImpl;
import com.ext.portlet.models.model.impl.ModelDiscussionModelImpl;

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


public class ModelDiscussionPersistenceImpl extends BasePersistenceImpl
    implements ModelDiscussionPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = ModelDiscussionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_MODELID = new FinderPath(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByModelId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_MODELID = new FinderPath(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByModelId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_MODELID = new FinderPath(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByModelId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_DISCUSSIONID = new FinderPath(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByDiscussionId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_DISCUSSIONID = new FinderPath(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByDiscussionId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_DISCUSSIONID = new FinderPath(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByDiscussionId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(ModelDiscussionPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelDiscussionPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelDiscussionPersistence modelDiscussionPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelPositionPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelPositionPersistence modelPositionPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelGlobalPreferencePersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelGlobalPreferencePersistence modelGlobalPreferencePersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelCategoryPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelCategoryPersistence modelCategoryPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelInputGroupPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelInputGroupPersistence modelInputGroupPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelInputItemPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelInputItemPersistence modelInputItemPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelOutputChartOrderPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelOutputChartOrderPersistence modelOutputChartOrderPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelOutputItemPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelOutputItemPersistence modelOutputItemPersistence;

    public void cacheResult(ModelDiscussion modelDiscussion) {
        EntityCacheUtil.putResult(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionImpl.class, modelDiscussion.getPrimaryKey(),
            modelDiscussion);
    }

    public void cacheResult(List<ModelDiscussion> modelDiscussions) {
        for (ModelDiscussion modelDiscussion : modelDiscussions) {
            if (EntityCacheUtil.getResult(
                        ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
                        ModelDiscussionImpl.class,
                        modelDiscussion.getPrimaryKey(), this) == null) {
                cacheResult(modelDiscussion);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(ModelDiscussionImpl.class.getName());
        EntityCacheUtil.clearCache(ModelDiscussionImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public ModelDiscussion create(Long modelDiscussionId) {
        ModelDiscussion modelDiscussion = new ModelDiscussionImpl();

        modelDiscussion.setNew(true);
        modelDiscussion.setPrimaryKey(modelDiscussionId);

        return modelDiscussion;
    }

    public ModelDiscussion remove(Long modelDiscussionId)
        throws NoSuchModelDiscussionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ModelDiscussion modelDiscussion = (ModelDiscussion) session.get(ModelDiscussionImpl.class,
                    modelDiscussionId);

            if (modelDiscussion == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No ModelDiscussion exists with the primary key " +
                        modelDiscussionId);
                }

                throw new NoSuchModelDiscussionException(
                    "No ModelDiscussion exists with the primary key " +
                    modelDiscussionId);
            }

            return remove(modelDiscussion);
        } catch (NoSuchModelDiscussionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ModelDiscussion remove(ModelDiscussion modelDiscussion)
        throws SystemException {
        for (ModelListener<ModelDiscussion> listener : listeners) {
            listener.onBeforeRemove(modelDiscussion);
        }

        modelDiscussion = removeImpl(modelDiscussion);

        for (ModelListener<ModelDiscussion> listener : listeners) {
            listener.onAfterRemove(modelDiscussion);
        }

        return modelDiscussion;
    }

    protected ModelDiscussion removeImpl(ModelDiscussion modelDiscussion)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (modelDiscussion.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(ModelDiscussionImpl.class,
                        modelDiscussion.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(modelDiscussion);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionImpl.class, modelDiscussion.getPrimaryKey());

        return modelDiscussion;
    }

    /**
     * @deprecated Use <code>update(ModelDiscussion modelDiscussion, boolean merge)</code>.
     */
    public ModelDiscussion update(ModelDiscussion modelDiscussion)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(ModelDiscussion modelDiscussion) method. Use update(ModelDiscussion modelDiscussion, boolean merge) instead.");
        }

        return update(modelDiscussion, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelDiscussion the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelDiscussion is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public ModelDiscussion update(ModelDiscussion modelDiscussion, boolean merge)
        throws SystemException {
        boolean isNew = modelDiscussion.isNew();

        for (ModelListener<ModelDiscussion> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(modelDiscussion);
            } else {
                listener.onBeforeUpdate(modelDiscussion);
            }
        }

        modelDiscussion = updateImpl(modelDiscussion, merge);

        for (ModelListener<ModelDiscussion> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(modelDiscussion);
            } else {
                listener.onAfterUpdate(modelDiscussion);
            }
        }

        return modelDiscussion;
    }

    public ModelDiscussion updateImpl(
        com.ext.portlet.models.model.ModelDiscussion modelDiscussion,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, modelDiscussion, merge);

            modelDiscussion.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionImpl.class, modelDiscussion.getPrimaryKey(),
            modelDiscussion);

        return modelDiscussion;
    }

    public ModelDiscussion findByPrimaryKey(Long modelDiscussionId)
        throws NoSuchModelDiscussionException, SystemException {
        ModelDiscussion modelDiscussion = fetchByPrimaryKey(modelDiscussionId);

        if (modelDiscussion == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No ModelDiscussion exists with the primary key " +
                    modelDiscussionId);
            }

            throw new NoSuchModelDiscussionException(
                "No ModelDiscussion exists with the primary key " +
                modelDiscussionId);
        }

        return modelDiscussion;
    }

    public ModelDiscussion fetchByPrimaryKey(Long modelDiscussionId)
        throws SystemException {
        ModelDiscussion modelDiscussion = (ModelDiscussion) EntityCacheUtil.getResult(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
                ModelDiscussionImpl.class, modelDiscussionId, this);

        if (modelDiscussion == null) {
            Session session = null;

            try {
                session = openSession();

                modelDiscussion = (ModelDiscussion) session.get(ModelDiscussionImpl.class,
                        modelDiscussionId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (modelDiscussion != null) {
                    cacheResult(modelDiscussion);
                }

                closeSession(session);
            }
        }

        return modelDiscussion;
    }

    public List<ModelDiscussion> findByModelId(Long modelId)
        throws SystemException {
        Object[] finderArgs = new Object[] { modelId };

        List<ModelDiscussion> list = (List<ModelDiscussion>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_MODELID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelDiscussion WHERE ");

                if (modelId == null) {
                    query.append("modelId IS NULL");
                } else {
                    query.append("modelId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (modelId != null) {
                    qPos.add(modelId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelDiscussion>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_MODELID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<ModelDiscussion> findByModelId(Long modelId, int start, int end)
        throws SystemException {
        return findByModelId(modelId, start, end, null);
    }

    public List<ModelDiscussion> findByModelId(Long modelId, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                modelId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ModelDiscussion> list = (List<ModelDiscussion>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_MODELID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelDiscussion WHERE ");

                if (modelId == null) {
                    query.append("modelId IS NULL");
                } else {
                    query.append("modelId = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (modelId != null) {
                    qPos.add(modelId.longValue());
                }

                list = (List<ModelDiscussion>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelDiscussion>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_MODELID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public ModelDiscussion findByModelId_First(Long modelId,
        OrderByComparator obc)
        throws NoSuchModelDiscussionException, SystemException {
        List<ModelDiscussion> list = findByModelId(modelId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ModelDiscussion exists with the key {");

            msg.append("modelId=" + modelId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelDiscussionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ModelDiscussion findByModelId_Last(Long modelId,
        OrderByComparator obc)
        throws NoSuchModelDiscussionException, SystemException {
        int count = countByModelId(modelId);

        List<ModelDiscussion> list = findByModelId(modelId, count - 1, count,
                obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ModelDiscussion exists with the key {");

            msg.append("modelId=" + modelId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelDiscussionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ModelDiscussion[] findByModelId_PrevAndNext(Long modelDiscussionId,
        Long modelId, OrderByComparator obc)
        throws NoSuchModelDiscussionException, SystemException {
        ModelDiscussion modelDiscussion = findByPrimaryKey(modelDiscussionId);

        int count = countByModelId(modelId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.models.model.ModelDiscussion WHERE ");

            if (modelId == null) {
                query.append("modelId IS NULL");
            } else {
                query.append("modelId = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (modelId != null) {
                qPos.add(modelId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    modelDiscussion);

            ModelDiscussion[] array = new ModelDiscussionImpl[3];

            array[0] = (ModelDiscussion) objArray[0];
            array[1] = (ModelDiscussion) objArray[1];
            array[2] = (ModelDiscussion) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<ModelDiscussion> findByDiscussionId(Long categoryId)
        throws SystemException {
        Object[] finderArgs = new Object[] { categoryId };

        List<ModelDiscussion> list = (List<ModelDiscussion>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_DISCUSSIONID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelDiscussion WHERE ");

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
                    list = new ArrayList<ModelDiscussion>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_DISCUSSIONID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<ModelDiscussion> findByDiscussionId(Long categoryId, int start,
        int end) throws SystemException {
        return findByDiscussionId(categoryId, start, end, null);
    }

    public List<ModelDiscussion> findByDiscussionId(Long categoryId, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                categoryId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ModelDiscussion> list = (List<ModelDiscussion>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_DISCUSSIONID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelDiscussion WHERE ");

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

                list = (List<ModelDiscussion>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelDiscussion>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_DISCUSSIONID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public ModelDiscussion findByDiscussionId_First(Long categoryId,
        OrderByComparator obc)
        throws NoSuchModelDiscussionException, SystemException {
        List<ModelDiscussion> list = findByDiscussionId(categoryId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ModelDiscussion exists with the key {");

            msg.append("categoryId=" + categoryId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelDiscussionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ModelDiscussion findByDiscussionId_Last(Long categoryId,
        OrderByComparator obc)
        throws NoSuchModelDiscussionException, SystemException {
        int count = countByDiscussionId(categoryId);

        List<ModelDiscussion> list = findByDiscussionId(categoryId, count - 1,
                count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ModelDiscussion exists with the key {");

            msg.append("categoryId=" + categoryId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelDiscussionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ModelDiscussion[] findByDiscussionId_PrevAndNext(
        Long modelDiscussionId, Long categoryId, OrderByComparator obc)
        throws NoSuchModelDiscussionException, SystemException {
        ModelDiscussion modelDiscussion = findByPrimaryKey(modelDiscussionId);

        int count = countByDiscussionId(categoryId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.models.model.ModelDiscussion WHERE ");

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
                    modelDiscussion);

            ModelDiscussion[] array = new ModelDiscussionImpl[3];

            array[0] = (ModelDiscussion) objArray[0];
            array[1] = (ModelDiscussion) objArray[1];
            array[2] = (ModelDiscussion) objArray[2];

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

    public List<ModelDiscussion> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<ModelDiscussion> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<ModelDiscussion> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ModelDiscussion> list = (List<ModelDiscussion>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelDiscussion ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<ModelDiscussion>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ModelDiscussion>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelDiscussion>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByModelId(Long modelId) throws SystemException {
        for (ModelDiscussion modelDiscussion : findByModelId(modelId)) {
            remove(modelDiscussion);
        }
    }

    public void removeByDiscussionId(Long categoryId) throws SystemException {
        for (ModelDiscussion modelDiscussion : findByDiscussionId(categoryId)) {
            remove(modelDiscussion);
        }
    }

    public void removeAll() throws SystemException {
        for (ModelDiscussion modelDiscussion : findAll()) {
            remove(modelDiscussion);
        }
    }

    public int countByModelId(Long modelId) throws SystemException {
        Object[] finderArgs = new Object[] { modelId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MODELID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.models.model.ModelDiscussion WHERE ");

                if (modelId == null) {
                    query.append("modelId IS NULL");
                } else {
                    query.append("modelId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (modelId != null) {
                    qPos.add(modelId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODELID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByDiscussionId(Long categoryId) throws SystemException {
        Object[] finderArgs = new Object[] { categoryId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_DISCUSSIONID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.models.model.ModelDiscussion WHERE ");

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

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DISCUSSIONID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.models.model.ModelDiscussion");

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
                        "value.object.listener.com.ext.portlet.models.model.ModelDiscussion")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ModelDiscussion>> listenersList = new ArrayList<ModelListener<ModelDiscussion>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ModelDiscussion>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
