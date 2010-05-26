package com.ext.portlet.models.service.persistence;

import com.ext.portlet.models.NoSuchModelInputItemException;
import com.ext.portlet.models.model.ModelInputItem;
import com.ext.portlet.models.model.impl.ModelInputItemImpl;
import com.ext.portlet.models.model.impl.ModelInputItemModelImpl;

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


public class ModelInputItemPersistenceImpl extends BasePersistenceImpl
    implements ModelInputItemPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = ModelInputItemImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_MODELGROUPID = new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByModelGroupId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_MODELGROUPID = new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByModelGroupId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_MODELGROUPID = new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByModelGroupId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_MODELINPUTID = new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByModelInputId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_MODELINPUTID = new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByModelInputId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_MODELID = new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByModelId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_MODELID = new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByModelId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_MODELID = new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByModelId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(ModelInputItemPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelDiscussionPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelDiscussionPersistence modelDiscussionPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelPositionPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelPositionPersistence modelPositionPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelInputGroupPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelInputGroupPersistence modelInputGroupPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelInputItemPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelInputItemPersistence modelInputItemPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelOutputChartOrderPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelOutputChartOrderPersistence modelOutputChartOrderPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelOutputItemPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelOutputItemPersistence modelOutputItemPersistence;

    public void cacheResult(ModelInputItem modelInputItem) {
        EntityCacheUtil.putResult(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemImpl.class, modelInputItem.getPrimaryKey(),
            modelInputItem);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELINPUTID,
            new Object[] { modelInputItem.getModelInputItemID() },
            modelInputItem);
    }

    public void cacheResult(List<ModelInputItem> modelInputItems) {
        for (ModelInputItem modelInputItem : modelInputItems) {
            if (EntityCacheUtil.getResult(
                        ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
                        ModelInputItemImpl.class,
                        modelInputItem.getPrimaryKey(), this) == null) {
                cacheResult(modelInputItem);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(ModelInputItemImpl.class.getName());
        EntityCacheUtil.clearCache(ModelInputItemImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public ModelInputItem create(Long modelInputItemPK) {
        ModelInputItem modelInputItem = new ModelInputItemImpl();

        modelInputItem.setNew(true);
        modelInputItem.setPrimaryKey(modelInputItemPK);

        return modelInputItem;
    }

    public ModelInputItem remove(Long modelInputItemPK)
        throws NoSuchModelInputItemException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ModelInputItem modelInputItem = (ModelInputItem) session.get(ModelInputItemImpl.class,
                    modelInputItemPK);

            if (modelInputItem == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No ModelInputItem exists with the primary key " +
                        modelInputItemPK);
                }

                throw new NoSuchModelInputItemException(
                    "No ModelInputItem exists with the primary key " +
                    modelInputItemPK);
            }

            return remove(modelInputItem);
        } catch (NoSuchModelInputItemException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ModelInputItem remove(ModelInputItem modelInputItem)
        throws SystemException {
        for (ModelListener<ModelInputItem> listener : listeners) {
            listener.onBeforeRemove(modelInputItem);
        }

        modelInputItem = removeImpl(modelInputItem);

        for (ModelListener<ModelInputItem> listener : listeners) {
            listener.onAfterRemove(modelInputItem);
        }

        return modelInputItem;
    }

    protected ModelInputItem removeImpl(ModelInputItem modelInputItem)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (modelInputItem.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(ModelInputItemImpl.class,
                        modelInputItem.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(modelInputItem);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        ModelInputItemModelImpl modelInputItemModelImpl = (ModelInputItemModelImpl) modelInputItem;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MODELINPUTID,
            new Object[] { modelInputItemModelImpl.getOriginalModelInputItemID() });

        EntityCacheUtil.removeResult(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemImpl.class, modelInputItem.getPrimaryKey());

        return modelInputItem;
    }

    /**
     * @deprecated Use <code>update(ModelInputItem modelInputItem, boolean merge)</code>.
     */
    public ModelInputItem update(ModelInputItem modelInputItem)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(ModelInputItem modelInputItem) method. Use update(ModelInputItem modelInputItem, boolean merge) instead.");
        }

        return update(modelInputItem, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelInputItem the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelInputItem is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public ModelInputItem update(ModelInputItem modelInputItem, boolean merge)
        throws SystemException {
        boolean isNew = modelInputItem.isNew();

        for (ModelListener<ModelInputItem> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(modelInputItem);
            } else {
                listener.onBeforeUpdate(modelInputItem);
            }
        }

        modelInputItem = updateImpl(modelInputItem, merge);

        for (ModelListener<ModelInputItem> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(modelInputItem);
            } else {
                listener.onAfterUpdate(modelInputItem);
            }
        }

        return modelInputItem;
    }

    public ModelInputItem updateImpl(
        com.ext.portlet.models.model.ModelInputItem modelInputItem,
        boolean merge) throws SystemException {
        boolean isNew = modelInputItem.isNew();

        ModelInputItemModelImpl modelInputItemModelImpl = (ModelInputItemModelImpl) modelInputItem;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, modelInputItem, merge);

            modelInputItem.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemImpl.class, modelInputItem.getPrimaryKey(),
            modelInputItem);

        if (!isNew &&
                (!Validator.equals(modelInputItem.getModelInputItemID(),
                    modelInputItemModelImpl.getOriginalModelInputItemID()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MODELINPUTID,
                new Object[] {
                    modelInputItemModelImpl.getOriginalModelInputItemID()
                });
        }

        if (isNew ||
                (!Validator.equals(modelInputItem.getModelInputItemID(),
                    modelInputItemModelImpl.getOriginalModelInputItemID()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELINPUTID,
                new Object[] { modelInputItem.getModelInputItemID() },
                modelInputItem);
        }

        return modelInputItem;
    }

    public ModelInputItem findByPrimaryKey(Long modelInputItemPK)
        throws NoSuchModelInputItemException, SystemException {
        ModelInputItem modelInputItem = fetchByPrimaryKey(modelInputItemPK);

        if (modelInputItem == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No ModelInputItem exists with the primary key " +
                    modelInputItemPK);
            }

            throw new NoSuchModelInputItemException(
                "No ModelInputItem exists with the primary key " +
                modelInputItemPK);
        }

        return modelInputItem;
    }

    public ModelInputItem fetchByPrimaryKey(Long modelInputItemPK)
        throws SystemException {
        ModelInputItem modelInputItem = (ModelInputItem) EntityCacheUtil.getResult(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
                ModelInputItemImpl.class, modelInputItemPK, this);

        if (modelInputItem == null) {
            Session session = null;

            try {
                session = openSession();

                modelInputItem = (ModelInputItem) session.get(ModelInputItemImpl.class,
                        modelInputItemPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (modelInputItem != null) {
                    cacheResult(modelInputItem);
                }

                closeSession(session);
            }
        }

        return modelInputItem;
    }

    public List<ModelInputItem> findByModelGroupId(Long modelGroupId)
        throws SystemException {
        Object[] finderArgs = new Object[] { modelGroupId };

        List<ModelInputItem> list = (List<ModelInputItem>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_MODELGROUPID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelInputItem WHERE ");

                if (modelGroupId == null) {
                    query.append("modelGroupId IS NULL");
                } else {
                    query.append("modelGroupId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (modelGroupId != null) {
                    qPos.add(modelGroupId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelInputItem>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_MODELGROUPID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<ModelInputItem> findByModelGroupId(Long modelGroupId,
        int start, int end) throws SystemException {
        return findByModelGroupId(modelGroupId, start, end, null);
    }

    public List<ModelInputItem> findByModelGroupId(Long modelGroupId,
        int start, int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                modelGroupId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ModelInputItem> list = (List<ModelInputItem>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_MODELGROUPID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelInputItem WHERE ");

                if (modelGroupId == null) {
                    query.append("modelGroupId IS NULL");
                } else {
                    query.append("modelGroupId = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (modelGroupId != null) {
                    qPos.add(modelGroupId.longValue());
                }

                list = (List<ModelInputItem>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelInputItem>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_MODELGROUPID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public ModelInputItem findByModelGroupId_First(Long modelGroupId,
        OrderByComparator obc)
        throws NoSuchModelInputItemException, SystemException {
        List<ModelInputItem> list = findByModelGroupId(modelGroupId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ModelInputItem exists with the key {");

            msg.append("modelGroupId=" + modelGroupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelInputItemException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ModelInputItem findByModelGroupId_Last(Long modelGroupId,
        OrderByComparator obc)
        throws NoSuchModelInputItemException, SystemException {
        int count = countByModelGroupId(modelGroupId);

        List<ModelInputItem> list = findByModelGroupId(modelGroupId, count - 1,
                count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ModelInputItem exists with the key {");

            msg.append("modelGroupId=" + modelGroupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelInputItemException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ModelInputItem[] findByModelGroupId_PrevAndNext(
        Long modelInputItemPK, Long modelGroupId, OrderByComparator obc)
        throws NoSuchModelInputItemException, SystemException {
        ModelInputItem modelInputItem = findByPrimaryKey(modelInputItemPK);

        int count = countByModelGroupId(modelGroupId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.models.model.ModelInputItem WHERE ");

            if (modelGroupId == null) {
                query.append("modelGroupId IS NULL");
            } else {
                query.append("modelGroupId = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (modelGroupId != null) {
                qPos.add(modelGroupId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    modelInputItem);

            ModelInputItem[] array = new ModelInputItemImpl[3];

            array[0] = (ModelInputItem) objArray[0];
            array[1] = (ModelInputItem) objArray[1];
            array[2] = (ModelInputItem) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ModelInputItem findByModelInputId(Long modelInputItemID)
        throws NoSuchModelInputItemException, SystemException {
        ModelInputItem modelInputItem = fetchByModelInputId(modelInputItemID);

        if (modelInputItem == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ModelInputItem exists with the key {");

            msg.append("modelInputItemID=" + modelInputItemID);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchModelInputItemException(msg.toString());
        }

        return modelInputItem;
    }

    public ModelInputItem fetchByModelInputId(Long modelInputItemID)
        throws SystemException {
        return fetchByModelInputId(modelInputItemID, true);
    }

    public ModelInputItem fetchByModelInputId(Long modelInputItemID,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { modelInputItemID };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_MODELINPUTID,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelInputItem WHERE ");

                if (modelInputItemID == null) {
                    query.append("modelInputItemID IS NULL");
                } else {
                    query.append("modelInputItemID = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (modelInputItemID != null) {
                    qPos.add(modelInputItemID.longValue());
                }

                List<ModelInputItem> list = q.list();

                result = list;

                ModelInputItem modelInputItem = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELINPUTID,
                        finderArgs, list);
                } else {
                    modelInputItem = list.get(0);

                    cacheResult(modelInputItem);

                    if ((modelInputItem.getModelInputItemID() == null) ||
                            !modelInputItem.getModelInputItemID()
                                               .equals(modelInputItemID)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELINPUTID,
                            finderArgs, modelInputItem);
                    }
                }

                return modelInputItem;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELINPUTID,
                        finderArgs, new ArrayList<ModelInputItem>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (ModelInputItem) result;
            }
        }
    }

    public List<ModelInputItem> findByModelId(Long modelInputItemID)
        throws SystemException {
        Object[] finderArgs = new Object[] { modelInputItemID };

        List<ModelInputItem> list = (List<ModelInputItem>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_MODELID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelInputItem WHERE ");

                if (modelInputItemID == null) {
                    query.append("modelInputItemID IS NULL");
                } else {
                    query.append("modelInputItemID = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (modelInputItemID != null) {
                    qPos.add(modelInputItemID.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelInputItem>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_MODELID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<ModelInputItem> findByModelId(Long modelInputItemID, int start,
        int end) throws SystemException {
        return findByModelId(modelInputItemID, start, end, null);
    }

    public List<ModelInputItem> findByModelId(Long modelInputItemID, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                modelInputItemID,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ModelInputItem> list = (List<ModelInputItem>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_MODELID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelInputItem WHERE ");

                if (modelInputItemID == null) {
                    query.append("modelInputItemID IS NULL");
                } else {
                    query.append("modelInputItemID = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (modelInputItemID != null) {
                    qPos.add(modelInputItemID.longValue());
                }

                list = (List<ModelInputItem>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelInputItem>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_MODELID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public ModelInputItem findByModelId_First(Long modelInputItemID,
        OrderByComparator obc)
        throws NoSuchModelInputItemException, SystemException {
        List<ModelInputItem> list = findByModelId(modelInputItemID, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ModelInputItem exists with the key {");

            msg.append("modelInputItemID=" + modelInputItemID);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelInputItemException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ModelInputItem findByModelId_Last(Long modelInputItemID,
        OrderByComparator obc)
        throws NoSuchModelInputItemException, SystemException {
        int count = countByModelId(modelInputItemID);

        List<ModelInputItem> list = findByModelId(modelInputItemID, count - 1,
                count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ModelInputItem exists with the key {");

            msg.append("modelInputItemID=" + modelInputItemID);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelInputItemException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ModelInputItem[] findByModelId_PrevAndNext(Long modelInputItemPK,
        Long modelInputItemID, OrderByComparator obc)
        throws NoSuchModelInputItemException, SystemException {
        ModelInputItem modelInputItem = findByPrimaryKey(modelInputItemPK);

        int count = countByModelId(modelInputItemID);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.models.model.ModelInputItem WHERE ");

            if (modelInputItemID == null) {
                query.append("modelInputItemID IS NULL");
            } else {
                query.append("modelInputItemID = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (modelInputItemID != null) {
                qPos.add(modelInputItemID.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    modelInputItem);

            ModelInputItem[] array = new ModelInputItemImpl[3];

            array[0] = (ModelInputItem) objArray[0];
            array[1] = (ModelInputItem) objArray[1];
            array[2] = (ModelInputItem) objArray[2];

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

    public List<ModelInputItem> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<ModelInputItem> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<ModelInputItem> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ModelInputItem> list = (List<ModelInputItem>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelInputItem ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<ModelInputItem>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ModelInputItem>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelInputItem>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByModelGroupId(Long modelGroupId)
        throws SystemException {
        for (ModelInputItem modelInputItem : findByModelGroupId(modelGroupId)) {
            remove(modelInputItem);
        }
    }

    public void removeByModelInputId(Long modelInputItemID)
        throws NoSuchModelInputItemException, SystemException {
        ModelInputItem modelInputItem = findByModelInputId(modelInputItemID);

        remove(modelInputItem);
    }

    public void removeByModelId(Long modelInputItemID)
        throws SystemException {
        for (ModelInputItem modelInputItem : findByModelId(modelInputItemID)) {
            remove(modelInputItem);
        }
    }

    public void removeAll() throws SystemException {
        for (ModelInputItem modelInputItem : findAll()) {
            remove(modelInputItem);
        }
    }

    public int countByModelGroupId(Long modelGroupId) throws SystemException {
        Object[] finderArgs = new Object[] { modelGroupId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MODELGROUPID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.models.model.ModelInputItem WHERE ");

                if (modelGroupId == null) {
                    query.append("modelGroupId IS NULL");
                } else {
                    query.append("modelGroupId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (modelGroupId != null) {
                    qPos.add(modelGroupId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODELGROUPID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByModelInputId(Long modelInputItemID)
        throws SystemException {
        Object[] finderArgs = new Object[] { modelInputItemID };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MODELINPUTID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.models.model.ModelInputItem WHERE ");

                if (modelInputItemID == null) {
                    query.append("modelInputItemID IS NULL");
                } else {
                    query.append("modelInputItemID = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (modelInputItemID != null) {
                    qPos.add(modelInputItemID.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODELINPUTID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByModelId(Long modelInputItemID) throws SystemException {
        Object[] finderArgs = new Object[] { modelInputItemID };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MODELID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.models.model.ModelInputItem WHERE ");

                if (modelInputItemID == null) {
                    query.append("modelInputItemID IS NULL");
                } else {
                    query.append("modelInputItemID = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (modelInputItemID != null) {
                    qPos.add(modelInputItemID.longValue());
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
                        "SELECT COUNT(*) FROM com.ext.portlet.models.model.ModelInputItem");

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
                        "value.object.listener.com.ext.portlet.models.model.ModelInputItem")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ModelInputItem>> listenersList = new ArrayList<ModelListener<ModelInputItem>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ModelInputItem>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
