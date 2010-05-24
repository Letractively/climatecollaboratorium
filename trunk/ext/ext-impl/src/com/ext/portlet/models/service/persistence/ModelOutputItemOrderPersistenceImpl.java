package com.ext.portlet.models.service.persistence;

import com.ext.portlet.models.NoSuchModelOutputItemOrderException;
import com.ext.portlet.models.model.ModelOutputItemOrder;
import com.ext.portlet.models.model.impl.ModelOutputItemOrderImpl;
import com.ext.portlet.models.model.impl.ModelOutputItemOrderModelImpl;

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


public class ModelOutputItemOrderPersistenceImpl extends BasePersistenceImpl
    implements ModelOutputItemOrderPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = ModelOutputItemOrderImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ModelOutputItemOrderModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputItemOrderModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModelOutputItemOrderModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputItemOrderModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(ModelOutputItemOrderPersistenceImpl.class);
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
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelOutputItemOrderPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelOutputItemOrderPersistence modelOutputItemOrderPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelOutputItemModifierPersistence.impl")
    protected com.ext.portlet.models.service.persistence.ModelOutputItemModifierPersistence modelOutputItemModifierPersistence;

    public void cacheResult(ModelOutputItemOrder modelOutputItemOrder) {
        EntityCacheUtil.putResult(ModelOutputItemOrderModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputItemOrderImpl.class,
            modelOutputItemOrder.getPrimaryKey(), modelOutputItemOrder);
    }

    public void cacheResult(List<ModelOutputItemOrder> modelOutputItemOrders) {
        for (ModelOutputItemOrder modelOutputItemOrder : modelOutputItemOrders) {
            if (EntityCacheUtil.getResult(
                        ModelOutputItemOrderModelImpl.ENTITY_CACHE_ENABLED,
                        ModelOutputItemOrderImpl.class,
                        modelOutputItemOrder.getPrimaryKey(), this) == null) {
                cacheResult(modelOutputItemOrder);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(ModelOutputItemOrderImpl.class.getName());
        EntityCacheUtil.clearCache(ModelOutputItemOrderImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public ModelOutputItemOrder create(Long modelOutputItemModifierPK) {
        ModelOutputItemOrder modelOutputItemOrder = new ModelOutputItemOrderImpl();

        modelOutputItemOrder.setNew(true);
        modelOutputItemOrder.setPrimaryKey(modelOutputItemModifierPK);

        return modelOutputItemOrder;
    }

    public ModelOutputItemOrder remove(Long modelOutputItemModifierPK)
        throws NoSuchModelOutputItemOrderException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ModelOutputItemOrder modelOutputItemOrder = (ModelOutputItemOrder) session.get(ModelOutputItemOrderImpl.class,
                    modelOutputItemModifierPK);

            if (modelOutputItemOrder == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No ModelOutputItemOrder exists with the primary key " +
                        modelOutputItemModifierPK);
                }

                throw new NoSuchModelOutputItemOrderException(
                    "No ModelOutputItemOrder exists with the primary key " +
                    modelOutputItemModifierPK);
            }

            return remove(modelOutputItemOrder);
        } catch (NoSuchModelOutputItemOrderException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ModelOutputItemOrder remove(
        ModelOutputItemOrder modelOutputItemOrder) throws SystemException {
        for (ModelListener<ModelOutputItemOrder> listener : listeners) {
            listener.onBeforeRemove(modelOutputItemOrder);
        }

        modelOutputItemOrder = removeImpl(modelOutputItemOrder);

        for (ModelListener<ModelOutputItemOrder> listener : listeners) {
            listener.onAfterRemove(modelOutputItemOrder);
        }

        return modelOutputItemOrder;
    }

    protected ModelOutputItemOrder removeImpl(
        ModelOutputItemOrder modelOutputItemOrder) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (modelOutputItemOrder.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(ModelOutputItemOrderImpl.class,
                        modelOutputItemOrder.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(modelOutputItemOrder);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(ModelOutputItemOrderModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputItemOrderImpl.class, modelOutputItemOrder.getPrimaryKey());

        return modelOutputItemOrder;
    }

    /**
     * @deprecated Use <code>update(ModelOutputItemOrder modelOutputItemOrder, boolean merge)</code>.
     */
    public ModelOutputItemOrder update(
        ModelOutputItemOrder modelOutputItemOrder) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(ModelOutputItemOrder modelOutputItemOrder) method. Use update(ModelOutputItemOrder modelOutputItemOrder, boolean merge) instead.");
        }

        return update(modelOutputItemOrder, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelOutputItemOrder the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelOutputItemOrder is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public ModelOutputItemOrder update(
        ModelOutputItemOrder modelOutputItemOrder, boolean merge)
        throws SystemException {
        boolean isNew = modelOutputItemOrder.isNew();

        for (ModelListener<ModelOutputItemOrder> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(modelOutputItemOrder);
            } else {
                listener.onBeforeUpdate(modelOutputItemOrder);
            }
        }

        modelOutputItemOrder = updateImpl(modelOutputItemOrder, merge);

        for (ModelListener<ModelOutputItemOrder> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(modelOutputItemOrder);
            } else {
                listener.onAfterUpdate(modelOutputItemOrder);
            }
        }

        return modelOutputItemOrder;
    }

    public ModelOutputItemOrder updateImpl(
        com.ext.portlet.models.model.ModelOutputItemOrder modelOutputItemOrder,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, modelOutputItemOrder, merge);

            modelOutputItemOrder.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(ModelOutputItemOrderModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputItemOrderImpl.class,
            modelOutputItemOrder.getPrimaryKey(), modelOutputItemOrder);

        return modelOutputItemOrder;
    }

    public ModelOutputItemOrder findByPrimaryKey(Long modelOutputItemModifierPK)
        throws NoSuchModelOutputItemOrderException, SystemException {
        ModelOutputItemOrder modelOutputItemOrder = fetchByPrimaryKey(modelOutputItemModifierPK);

        if (modelOutputItemOrder == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No ModelOutputItemOrder exists with the primary key " +
                    modelOutputItemModifierPK);
            }

            throw new NoSuchModelOutputItemOrderException(
                "No ModelOutputItemOrder exists with the primary key " +
                modelOutputItemModifierPK);
        }

        return modelOutputItemOrder;
    }

    public ModelOutputItemOrder fetchByPrimaryKey(
        Long modelOutputItemModifierPK) throws SystemException {
        ModelOutputItemOrder modelOutputItemOrder = (ModelOutputItemOrder) EntityCacheUtil.getResult(ModelOutputItemOrderModelImpl.ENTITY_CACHE_ENABLED,
                ModelOutputItemOrderImpl.class, modelOutputItemModifierPK, this);

        if (modelOutputItemOrder == null) {
            Session session = null;

            try {
                session = openSession();

                modelOutputItemOrder = (ModelOutputItemOrder) session.get(ModelOutputItemOrderImpl.class,
                        modelOutputItemModifierPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (modelOutputItemOrder != null) {
                    cacheResult(modelOutputItemOrder);
                }

                closeSession(session);
            }
        }

        return modelOutputItemOrder;
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

    public List<ModelOutputItemOrder> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<ModelOutputItemOrder> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<ModelOutputItemOrder> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ModelOutputItemOrder> list = (List<ModelOutputItemOrder>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelOutputItemOrder ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<ModelOutputItemOrder>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ModelOutputItemOrder>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelOutputItemOrder>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (ModelOutputItemOrder modelOutputItemOrder : findAll()) {
            remove(modelOutputItemOrder);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.models.model.ModelOutputItemOrder");

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
                        "value.object.listener.com.ext.portlet.models.model.ModelOutputItemOrder")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ModelOutputItemOrder>> listenersList = new ArrayList<ModelListener<ModelOutputItemOrder>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ModelOutputItemOrder>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
