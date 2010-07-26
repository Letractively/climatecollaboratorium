package com.ext.portlet.models.service.persistence;

import com.ext.portlet.models.NoSuchModelInputGroupException;
import com.ext.portlet.models.model.ModelInputGroup;
import com.ext.portlet.models.model.impl.ModelInputGroupImpl;
import com.ext.portlet.models.model.impl.ModelInputGroupModelImpl;

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


public class ModelInputGroupPersistenceImpl extends BasePersistenceImpl
    implements ModelInputGroupPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = ModelInputGroupImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_PARENTMODELID = new FinderPath(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByparentModelId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_PARENTMODELID = new FinderPath(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByparentModelId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_PARENTMODELID = new FinderPath(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByparentModelId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_MODELID = new FinderPath(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByModelId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_MODELID = new FinderPath(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByModelId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_MODELID = new FinderPath(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByModelId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(ModelInputGroupPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelDiscussionPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelDiscussionPersistence modelDiscussionPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelPositionPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelPositionPersistence modelPositionPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelGlobalPreferencePersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelGlobalPreferencePersistence modelGlobalPreferencePersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelInputGroupPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelInputGroupPersistence modelInputGroupPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelInputItemPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelInputItemPersistence modelInputItemPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelOutputChartOrderPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelOutputChartOrderPersistence modelOutputChartOrderPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelOutputItemPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelOutputItemPersistence modelOutputItemPersistence;

    public void cacheResult(ModelInputGroup modelInputGroup) {
        EntityCacheUtil.putResult(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupImpl.class, modelInputGroup.getPrimaryKey(),
            modelInputGroup);
    }

    public void cacheResult(List<ModelInputGroup> modelInputGroups) {
        for (ModelInputGroup modelInputGroup : modelInputGroups) {
            if (EntityCacheUtil.getResult(
                        ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
                        ModelInputGroupImpl.class,
                        modelInputGroup.getPrimaryKey(), this) == null) {
                cacheResult(modelInputGroup);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(ModelInputGroupImpl.class.getName());
        EntityCacheUtil.clearCache(ModelInputGroupImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public ModelInputGroup create(Long modelInputGroupPK) {
        ModelInputGroup modelInputGroup = new ModelInputGroupImpl();

        modelInputGroup.setNew(true);
        modelInputGroup.setPrimaryKey(modelInputGroupPK);

        return modelInputGroup;
    }

    public ModelInputGroup remove(Long modelInputGroupPK)
        throws NoSuchModelInputGroupException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ModelInputGroup modelInputGroup = (ModelInputGroup) session.get(ModelInputGroupImpl.class,
                    modelInputGroupPK);

            if (modelInputGroup == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No ModelInputGroup exists with the primary key " +
                        modelInputGroupPK);
                }

                throw new NoSuchModelInputGroupException(
                    "No ModelInputGroup exists with the primary key " +
                    modelInputGroupPK);
            }

            return remove(modelInputGroup);
        } catch (NoSuchModelInputGroupException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ModelInputGroup remove(ModelInputGroup modelInputGroup)
        throws SystemException {
        for (ModelListener<ModelInputGroup> listener : listeners) {
            listener.onBeforeRemove(modelInputGroup);
        }

        modelInputGroup = removeImpl(modelInputGroup);

        for (ModelListener<ModelInputGroup> listener : listeners) {
            listener.onAfterRemove(modelInputGroup);
        }

        return modelInputGroup;
    }

    protected ModelInputGroup removeImpl(ModelInputGroup modelInputGroup)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (modelInputGroup.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(ModelInputGroupImpl.class,
                        modelInputGroup.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(modelInputGroup);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupImpl.class, modelInputGroup.getPrimaryKey());

        return modelInputGroup;
    }

    /**
     * @deprecated Use <code>update(ModelInputGroup modelInputGroup, boolean merge)</code>.
     */
    public ModelInputGroup update(ModelInputGroup modelInputGroup)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(ModelInputGroup modelInputGroup) method. Use update(ModelInputGroup modelInputGroup, boolean merge) instead.");
        }

        return update(modelInputGroup, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelInputGroup the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelInputGroup is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public ModelInputGroup update(ModelInputGroup modelInputGroup, boolean merge)
        throws SystemException {
        boolean isNew = modelInputGroup.isNew();

        for (ModelListener<ModelInputGroup> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(modelInputGroup);
            } else {
                listener.onBeforeUpdate(modelInputGroup);
            }
        }

        modelInputGroup = updateImpl(modelInputGroup, merge);

        for (ModelListener<ModelInputGroup> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(modelInputGroup);
            } else {
                listener.onAfterUpdate(modelInputGroup);
            }
        }

        return modelInputGroup;
    }

    public ModelInputGroup updateImpl(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, modelInputGroup, merge);

            modelInputGroup.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupImpl.class, modelInputGroup.getPrimaryKey(),
            modelInputGroup);

        return modelInputGroup;
    }

    public ModelInputGroup findByPrimaryKey(Long modelInputGroupPK)
        throws NoSuchModelInputGroupException, SystemException {
        ModelInputGroup modelInputGroup = fetchByPrimaryKey(modelInputGroupPK);

        if (modelInputGroup == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No ModelInputGroup exists with the primary key " +
                    modelInputGroupPK);
            }

            throw new NoSuchModelInputGroupException(
                "No ModelInputGroup exists with the primary key " +
                modelInputGroupPK);
        }

        return modelInputGroup;
    }

    public ModelInputGroup fetchByPrimaryKey(Long modelInputGroupPK)
        throws SystemException {
        ModelInputGroup modelInputGroup = (ModelInputGroup) EntityCacheUtil.getResult(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
                ModelInputGroupImpl.class, modelInputGroupPK, this);

        if (modelInputGroup == null) {
            Session session = null;

            try {
                session = openSession();

                modelInputGroup = (ModelInputGroup) session.get(ModelInputGroupImpl.class,
                        modelInputGroupPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (modelInputGroup != null) {
                    cacheResult(modelInputGroup);
                }

                closeSession(session);
            }
        }

        return modelInputGroup;
    }

    public List<ModelInputGroup> findByparentModelId(Long parentGroupPK)
        throws SystemException {
        Object[] finderArgs = new Object[] { parentGroupPK };

        List<ModelInputGroup> list = (List<ModelInputGroup>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_PARENTMODELID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelInputGroup WHERE ");

                if (parentGroupPK == null) {
                    query.append("parentGroupPK IS NULL");
                } else {
                    query.append("parentGroupPK = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (parentGroupPK != null) {
                    qPos.add(parentGroupPK.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelInputGroup>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_PARENTMODELID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<ModelInputGroup> findByparentModelId(Long parentGroupPK,
        int start, int end) throws SystemException {
        return findByparentModelId(parentGroupPK, start, end, null);
    }

    public List<ModelInputGroup> findByparentModelId(Long parentGroupPK,
        int start, int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                parentGroupPK,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ModelInputGroup> list = (List<ModelInputGroup>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_PARENTMODELID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelInputGroup WHERE ");

                if (parentGroupPK == null) {
                    query.append("parentGroupPK IS NULL");
                } else {
                    query.append("parentGroupPK = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (parentGroupPK != null) {
                    qPos.add(parentGroupPK.longValue());
                }

                list = (List<ModelInputGroup>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelInputGroup>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_PARENTMODELID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public ModelInputGroup findByparentModelId_First(Long parentGroupPK,
        OrderByComparator obc)
        throws NoSuchModelInputGroupException, SystemException {
        List<ModelInputGroup> list = findByparentModelId(parentGroupPK, 0, 1,
                obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ModelInputGroup exists with the key {");

            msg.append("parentGroupPK=" + parentGroupPK);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelInputGroupException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ModelInputGroup findByparentModelId_Last(Long parentGroupPK,
        OrderByComparator obc)
        throws NoSuchModelInputGroupException, SystemException {
        int count = countByparentModelId(parentGroupPK);

        List<ModelInputGroup> list = findByparentModelId(parentGroupPK,
                count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ModelInputGroup exists with the key {");

            msg.append("parentGroupPK=" + parentGroupPK);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelInputGroupException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ModelInputGroup[] findByparentModelId_PrevAndNext(
        Long modelInputGroupPK, Long parentGroupPK, OrderByComparator obc)
        throws NoSuchModelInputGroupException, SystemException {
        ModelInputGroup modelInputGroup = findByPrimaryKey(modelInputGroupPK);

        int count = countByparentModelId(parentGroupPK);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.models.model.ModelInputGroup WHERE ");

            if (parentGroupPK == null) {
                query.append("parentGroupPK IS NULL");
            } else {
                query.append("parentGroupPK = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (parentGroupPK != null) {
                qPos.add(parentGroupPK.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    modelInputGroup);

            ModelInputGroup[] array = new ModelInputGroupImpl[3];

            array[0] = (ModelInputGroup) objArray[0];
            array[1] = (ModelInputGroup) objArray[1];
            array[2] = (ModelInputGroup) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<ModelInputGroup> findByModelId(Long modelId)
        throws SystemException {
        Object[] finderArgs = new Object[] { modelId };

        List<ModelInputGroup> list = (List<ModelInputGroup>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_MODELID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelInputGroup WHERE ");

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
                    list = new ArrayList<ModelInputGroup>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_MODELID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<ModelInputGroup> findByModelId(Long modelId, int start, int end)
        throws SystemException {
        return findByModelId(modelId, start, end, null);
    }

    public List<ModelInputGroup> findByModelId(Long modelId, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                modelId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ModelInputGroup> list = (List<ModelInputGroup>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_MODELID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelInputGroup WHERE ");

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

                list = (List<ModelInputGroup>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelInputGroup>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_MODELID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public ModelInputGroup findByModelId_First(Long modelId,
        OrderByComparator obc)
        throws NoSuchModelInputGroupException, SystemException {
        List<ModelInputGroup> list = findByModelId(modelId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ModelInputGroup exists with the key {");

            msg.append("modelId=" + modelId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelInputGroupException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ModelInputGroup findByModelId_Last(Long modelId,
        OrderByComparator obc)
        throws NoSuchModelInputGroupException, SystemException {
        int count = countByModelId(modelId);

        List<ModelInputGroup> list = findByModelId(modelId, count - 1, count,
                obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ModelInputGroup exists with the key {");

            msg.append("modelId=" + modelId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelInputGroupException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ModelInputGroup[] findByModelId_PrevAndNext(Long modelInputGroupPK,
        Long modelId, OrderByComparator obc)
        throws NoSuchModelInputGroupException, SystemException {
        ModelInputGroup modelInputGroup = findByPrimaryKey(modelInputGroupPK);

        int count = countByModelId(modelId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.models.model.ModelInputGroup WHERE ");

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
                    modelInputGroup);

            ModelInputGroup[] array = new ModelInputGroupImpl[3];

            array[0] = (ModelInputGroup) objArray[0];
            array[1] = (ModelInputGroup) objArray[1];
            array[2] = (ModelInputGroup) objArray[2];

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

    public List<ModelInputGroup> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<ModelInputGroup> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<ModelInputGroup> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ModelInputGroup> list = (List<ModelInputGroup>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelInputGroup ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<ModelInputGroup>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ModelInputGroup>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelInputGroup>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByparentModelId(Long parentGroupPK)
        throws SystemException {
        for (ModelInputGroup modelInputGroup : findByparentModelId(
                parentGroupPK)) {
            remove(modelInputGroup);
        }
    }

    public void removeByModelId(Long modelId) throws SystemException {
        for (ModelInputGroup modelInputGroup : findByModelId(modelId)) {
            remove(modelInputGroup);
        }
    }

    public void removeAll() throws SystemException {
        for (ModelInputGroup modelInputGroup : findAll()) {
            remove(modelInputGroup);
        }
    }

    public int countByparentModelId(Long parentGroupPK)
        throws SystemException {
        Object[] finderArgs = new Object[] { parentGroupPK };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PARENTMODELID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.models.model.ModelInputGroup WHERE ");

                if (parentGroupPK == null) {
                    query.append("parentGroupPK IS NULL");
                } else {
                    query.append("parentGroupPK = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (parentGroupPK != null) {
                    qPos.add(parentGroupPK.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PARENTMODELID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
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
                    "FROM com.ext.portlet.models.model.ModelInputGroup WHERE ");

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

    public int countAll() throws SystemException {
        Object[] finderArgs = new Object[0];

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(
                        "SELECT COUNT(*) FROM com.ext.portlet.models.model.ModelInputGroup");

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
                        "value.object.listener.com.ext.portlet.models.model.ModelInputGroup")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ModelInputGroup>> listenersList = new ArrayList<ModelListener<ModelInputGroup>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ModelInputGroup>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
