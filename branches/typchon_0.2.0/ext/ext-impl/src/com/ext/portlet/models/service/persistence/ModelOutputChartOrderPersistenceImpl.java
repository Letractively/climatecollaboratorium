package com.ext.portlet.models.service.persistence;

import com.ext.portlet.models.NoSuchModelOutputChartOrderException;
import com.ext.portlet.models.model.ModelOutputChartOrder;
import com.ext.portlet.models.model.impl.ModelOutputChartOrderImpl;
import com.ext.portlet.models.model.impl.ModelOutputChartOrderModelImpl;

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


public class ModelOutputChartOrderPersistenceImpl extends BasePersistenceImpl
    implements ModelOutputChartOrderPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = ModelOutputChartOrderImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_MODELIDANDLABEL = new FinderPath(ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputChartOrderModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByModelIdAndLabel",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_MODELIDANDLABEL = new FinderPath(ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputChartOrderModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByModelIdAndLabel",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputChartOrderModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputChartOrderModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(ModelOutputChartOrderPersistenceImpl.class);
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

    public void cacheResult(ModelOutputChartOrder modelOutputChartOrder) {
        EntityCacheUtil.putResult(ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputChartOrderImpl.class,
            modelOutputChartOrder.getPrimaryKey(), modelOutputChartOrder);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELIDANDLABEL,
            new Object[] {
                modelOutputChartOrder.getModelId(),
                
            modelOutputChartOrder.getModelOutputLabel()
            }, modelOutputChartOrder);
    }

    public void cacheResult(List<ModelOutputChartOrder> modelOutputChartOrders) {
        for (ModelOutputChartOrder modelOutputChartOrder : modelOutputChartOrders) {
            if (EntityCacheUtil.getResult(
                        ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
                        ModelOutputChartOrderImpl.class,
                        modelOutputChartOrder.getPrimaryKey(), this) == null) {
                cacheResult(modelOutputChartOrder);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(ModelOutputChartOrderImpl.class.getName());
        EntityCacheUtil.clearCache(ModelOutputChartOrderImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public ModelOutputChartOrder create(Long modelOutputChartOrderPK) {
        ModelOutputChartOrder modelOutputChartOrder = new ModelOutputChartOrderImpl();

        modelOutputChartOrder.setNew(true);
        modelOutputChartOrder.setPrimaryKey(modelOutputChartOrderPK);

        return modelOutputChartOrder;
    }

    public ModelOutputChartOrder remove(Long modelOutputChartOrderPK)
        throws NoSuchModelOutputChartOrderException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ModelOutputChartOrder modelOutputChartOrder = (ModelOutputChartOrder) session.get(ModelOutputChartOrderImpl.class,
                    modelOutputChartOrderPK);

            if (modelOutputChartOrder == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No ModelOutputChartOrder exists with the primary key " +
                        modelOutputChartOrderPK);
                }

                throw new NoSuchModelOutputChartOrderException(
                    "No ModelOutputChartOrder exists with the primary key " +
                    modelOutputChartOrderPK);
            }

            return remove(modelOutputChartOrder);
        } catch (NoSuchModelOutputChartOrderException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ModelOutputChartOrder remove(
        ModelOutputChartOrder modelOutputChartOrder) throws SystemException {
        for (ModelListener<ModelOutputChartOrder> listener : listeners) {
            listener.onBeforeRemove(modelOutputChartOrder);
        }

        modelOutputChartOrder = removeImpl(modelOutputChartOrder);

        for (ModelListener<ModelOutputChartOrder> listener : listeners) {
            listener.onAfterRemove(modelOutputChartOrder);
        }

        return modelOutputChartOrder;
    }

    protected ModelOutputChartOrder removeImpl(
        ModelOutputChartOrder modelOutputChartOrder) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (modelOutputChartOrder.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(ModelOutputChartOrderImpl.class,
                        modelOutputChartOrder.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(modelOutputChartOrder);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        ModelOutputChartOrderModelImpl modelOutputChartOrderModelImpl = (ModelOutputChartOrderModelImpl) modelOutputChartOrder;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MODELIDANDLABEL,
            new Object[] {
                modelOutputChartOrderModelImpl.getOriginalModelId(),
                
            modelOutputChartOrderModelImpl.getOriginalModelOutputLabel()
            });

        EntityCacheUtil.removeResult(ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputChartOrderImpl.class,
            modelOutputChartOrder.getPrimaryKey());

        return modelOutputChartOrder;
    }

    /**
     * @deprecated Use <code>update(ModelOutputChartOrder modelOutputChartOrder, boolean merge)</code>.
     */
    public ModelOutputChartOrder update(
        ModelOutputChartOrder modelOutputChartOrder) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(ModelOutputChartOrder modelOutputChartOrder) method. Use update(ModelOutputChartOrder modelOutputChartOrder, boolean merge) instead.");
        }

        return update(modelOutputChartOrder, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelOutputChartOrder the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelOutputChartOrder is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public ModelOutputChartOrder update(
        ModelOutputChartOrder modelOutputChartOrder, boolean merge)
        throws SystemException {
        boolean isNew = modelOutputChartOrder.isNew();

        for (ModelListener<ModelOutputChartOrder> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(modelOutputChartOrder);
            } else {
                listener.onBeforeUpdate(modelOutputChartOrder);
            }
        }

        modelOutputChartOrder = updateImpl(modelOutputChartOrder, merge);

        for (ModelListener<ModelOutputChartOrder> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(modelOutputChartOrder);
            } else {
                listener.onAfterUpdate(modelOutputChartOrder);
            }
        }

        return modelOutputChartOrder;
    }

    public ModelOutputChartOrder updateImpl(
        com.ext.portlet.models.model.ModelOutputChartOrder modelOutputChartOrder,
        boolean merge) throws SystemException {
        boolean isNew = modelOutputChartOrder.isNew();

        ModelOutputChartOrderModelImpl modelOutputChartOrderModelImpl = (ModelOutputChartOrderModelImpl) modelOutputChartOrder;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, modelOutputChartOrder, merge);

            modelOutputChartOrder.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputChartOrderImpl.class,
            modelOutputChartOrder.getPrimaryKey(), modelOutputChartOrder);

        if (!isNew &&
                (!Validator.equals(modelOutputChartOrder.getModelId(),
                    modelOutputChartOrderModelImpl.getOriginalModelId()) ||
                !Validator.equals(modelOutputChartOrder.getModelOutputLabel(),
                    modelOutputChartOrderModelImpl.getOriginalModelOutputLabel()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MODELIDANDLABEL,
                new Object[] {
                    modelOutputChartOrderModelImpl.getOriginalModelId(),
                    
                modelOutputChartOrderModelImpl.getOriginalModelOutputLabel()
                });
        }

        if (isNew ||
                (!Validator.equals(modelOutputChartOrder.getModelId(),
                    modelOutputChartOrderModelImpl.getOriginalModelId()) ||
                !Validator.equals(modelOutputChartOrder.getModelOutputLabel(),
                    modelOutputChartOrderModelImpl.getOriginalModelOutputLabel()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELIDANDLABEL,
                new Object[] {
                    modelOutputChartOrder.getModelId(),
                    
                modelOutputChartOrder.getModelOutputLabel()
                }, modelOutputChartOrder);
        }

        return modelOutputChartOrder;
    }

    public ModelOutputChartOrder findByPrimaryKey(Long modelOutputChartOrderPK)
        throws NoSuchModelOutputChartOrderException, SystemException {
        ModelOutputChartOrder modelOutputChartOrder = fetchByPrimaryKey(modelOutputChartOrderPK);

        if (modelOutputChartOrder == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No ModelOutputChartOrder exists with the primary key " +
                    modelOutputChartOrderPK);
            }

            throw new NoSuchModelOutputChartOrderException(
                "No ModelOutputChartOrder exists with the primary key " +
                modelOutputChartOrderPK);
        }

        return modelOutputChartOrder;
    }

    public ModelOutputChartOrder fetchByPrimaryKey(Long modelOutputChartOrderPK)
        throws SystemException {
        ModelOutputChartOrder modelOutputChartOrder = (ModelOutputChartOrder) EntityCacheUtil.getResult(ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
                ModelOutputChartOrderImpl.class, modelOutputChartOrderPK, this);

        if (modelOutputChartOrder == null) {
            Session session = null;

            try {
                session = openSession();

                modelOutputChartOrder = (ModelOutputChartOrder) session.get(ModelOutputChartOrderImpl.class,
                        modelOutputChartOrderPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (modelOutputChartOrder != null) {
                    cacheResult(modelOutputChartOrder);
                }

                closeSession(session);
            }
        }

        return modelOutputChartOrder;
    }

    public ModelOutputChartOrder findByModelIdAndLabel(Long modelId,
        String modelOutputLabel)
        throws NoSuchModelOutputChartOrderException, SystemException {
        ModelOutputChartOrder modelOutputChartOrder = fetchByModelIdAndLabel(modelId,
                modelOutputLabel);

        if (modelOutputChartOrder == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ModelOutputChartOrder exists with the key {");

            msg.append("modelId=" + modelId);

            msg.append(", ");
            msg.append("modelOutputLabel=" + modelOutputLabel);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchModelOutputChartOrderException(msg.toString());
        }

        return modelOutputChartOrder;
    }

    public ModelOutputChartOrder fetchByModelIdAndLabel(Long modelId,
        String modelOutputLabel) throws SystemException {
        return fetchByModelIdAndLabel(modelId, modelOutputLabel, true);
    }

    public ModelOutputChartOrder fetchByModelIdAndLabel(Long modelId,
        String modelOutputLabel, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { modelId, modelOutputLabel };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_MODELIDANDLABEL,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelOutputChartOrder WHERE ");

                if (modelId == null) {
                    query.append("modelId IS NULL");
                } else {
                    query.append("modelId = ?");
                }

                query.append(" AND ");

                if (modelOutputLabel == null) {
                    query.append("modelOutputLabel IS NULL");
                } else {
                    query.append("modelOutputLabel = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (modelId != null) {
                    qPos.add(modelId.longValue());
                }

                if (modelOutputLabel != null) {
                    qPos.add(modelOutputLabel);
                }

                List<ModelOutputChartOrder> list = q.list();

                result = list;

                ModelOutputChartOrder modelOutputChartOrder = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELIDANDLABEL,
                        finderArgs, list);
                } else {
                    modelOutputChartOrder = list.get(0);

                    cacheResult(modelOutputChartOrder);

                    if ((modelOutputChartOrder.getModelId() == null) ||
                            !modelOutputChartOrder.getModelId().equals(modelId) ||
                            (modelOutputChartOrder.getModelOutputLabel() == null) ||
                            !modelOutputChartOrder.getModelOutputLabel()
                                                      .equals(modelOutputLabel)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELIDANDLABEL,
                            finderArgs, modelOutputChartOrder);
                    }
                }

                return modelOutputChartOrder;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELIDANDLABEL,
                        finderArgs, new ArrayList<ModelOutputChartOrder>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (ModelOutputChartOrder) result;
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

    public List<ModelOutputChartOrder> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<ModelOutputChartOrder> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<ModelOutputChartOrder> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ModelOutputChartOrder> list = (List<ModelOutputChartOrder>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelOutputChartOrder ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<ModelOutputChartOrder>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ModelOutputChartOrder>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelOutputChartOrder>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByModelIdAndLabel(Long modelId, String modelOutputLabel)
        throws NoSuchModelOutputChartOrderException, SystemException {
        ModelOutputChartOrder modelOutputChartOrder = findByModelIdAndLabel(modelId,
                modelOutputLabel);

        remove(modelOutputChartOrder);
    }

    public void removeAll() throws SystemException {
        for (ModelOutputChartOrder modelOutputChartOrder : findAll()) {
            remove(modelOutputChartOrder);
        }
    }

    public int countByModelIdAndLabel(Long modelId, String modelOutputLabel)
        throws SystemException {
        Object[] finderArgs = new Object[] { modelId, modelOutputLabel };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MODELIDANDLABEL,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.models.model.ModelOutputChartOrder WHERE ");

                if (modelId == null) {
                    query.append("modelId IS NULL");
                } else {
                    query.append("modelId = ?");
                }

                query.append(" AND ");

                if (modelOutputLabel == null) {
                    query.append("modelOutputLabel IS NULL");
                } else {
                    query.append("modelOutputLabel = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (modelId != null) {
                    qPos.add(modelId.longValue());
                }

                if (modelOutputLabel != null) {
                    qPos.add(modelOutputLabel);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODELIDANDLABEL,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.models.model.ModelOutputChartOrder");

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
                        "value.object.listener.com.ext.portlet.models.model.ModelOutputChartOrder")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ModelOutputChartOrder>> listenersList = new ArrayList<ModelListener<ModelOutputChartOrder>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ModelOutputChartOrder>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
