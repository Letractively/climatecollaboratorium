package com.ext.portlet.models.service.persistence;

import com.ext.portlet.models.NoSuchModelOutputItemException;
import com.ext.portlet.models.model.ModelOutputItem;
import com.ext.portlet.models.model.impl.ModelOutputItemImpl;
import com.ext.portlet.models.model.impl.ModelOutputItemModelImpl;

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


public class ModelOutputItemPersistenceImpl extends BasePersistenceImpl
    implements ModelOutputItemPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = ModelOutputItemImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_MODELOUTPUTID = new FinderPath(ModelOutputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByModelOutputId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_MODELOUTPUTID = new FinderPath(ModelOutputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByModelOutputId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ModelOutputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModelOutputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(ModelOutputItemPersistenceImpl.class);
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

    public void cacheResult(ModelOutputItem modelOutputItem) {
        EntityCacheUtil.putResult(ModelOutputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputItemImpl.class, modelOutputItem.getPrimaryKey(),
            modelOutputItem);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELOUTPUTID,
            new Object[] { modelOutputItem.getModelOutputItemId() },
            modelOutputItem);
    }

    public void cacheResult(List<ModelOutputItem> modelOutputItems) {
        for (ModelOutputItem modelOutputItem : modelOutputItems) {
            if (EntityCacheUtil.getResult(
                        ModelOutputItemModelImpl.ENTITY_CACHE_ENABLED,
                        ModelOutputItemImpl.class,
                        modelOutputItem.getPrimaryKey(), this) == null) {
                cacheResult(modelOutputItem);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(ModelOutputItemImpl.class.getName());
        EntityCacheUtil.clearCache(ModelOutputItemImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public ModelOutputItem create(Long modelOutputItemModifierPK) {
        ModelOutputItem modelOutputItem = new ModelOutputItemImpl();

        modelOutputItem.setNew(true);
        modelOutputItem.setPrimaryKey(modelOutputItemModifierPK);

        return modelOutputItem;
    }

    public ModelOutputItem remove(Long modelOutputItemModifierPK)
        throws NoSuchModelOutputItemException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ModelOutputItem modelOutputItem = (ModelOutputItem) session.get(ModelOutputItemImpl.class,
                    modelOutputItemModifierPK);

            if (modelOutputItem == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No ModelOutputItem exists with the primary key " +
                        modelOutputItemModifierPK);
                }

                throw new NoSuchModelOutputItemException(
                    "No ModelOutputItem exists with the primary key " +
                    modelOutputItemModifierPK);
            }

            return remove(modelOutputItem);
        } catch (NoSuchModelOutputItemException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ModelOutputItem remove(ModelOutputItem modelOutputItem)
        throws SystemException {
        for (ModelListener<ModelOutputItem> listener : listeners) {
            listener.onBeforeRemove(modelOutputItem);
        }

        modelOutputItem = removeImpl(modelOutputItem);

        for (ModelListener<ModelOutputItem> listener : listeners) {
            listener.onAfterRemove(modelOutputItem);
        }

        return modelOutputItem;
    }

    protected ModelOutputItem removeImpl(ModelOutputItem modelOutputItem)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (modelOutputItem.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(ModelOutputItemImpl.class,
                        modelOutputItem.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(modelOutputItem);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        ModelOutputItemModelImpl modelOutputItemModelImpl = (ModelOutputItemModelImpl) modelOutputItem;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MODELOUTPUTID,
            new Object[] { modelOutputItemModelImpl.getOriginalModelOutputItemId() });

        EntityCacheUtil.removeResult(ModelOutputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputItemImpl.class, modelOutputItem.getPrimaryKey());

        return modelOutputItem;
    }

    /**
     * @deprecated Use <code>update(ModelOutputItem modelOutputItem, boolean merge)</code>.
     */
    public ModelOutputItem update(ModelOutputItem modelOutputItem)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(ModelOutputItem modelOutputItem) method. Use update(ModelOutputItem modelOutputItem, boolean merge) instead.");
        }

        return update(modelOutputItem, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelOutputItem the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelOutputItem is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public ModelOutputItem update(ModelOutputItem modelOutputItem, boolean merge)
        throws SystemException {
        boolean isNew = modelOutputItem.isNew();

        for (ModelListener<ModelOutputItem> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(modelOutputItem);
            } else {
                listener.onBeforeUpdate(modelOutputItem);
            }
        }

        modelOutputItem = updateImpl(modelOutputItem, merge);

        for (ModelListener<ModelOutputItem> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(modelOutputItem);
            } else {
                listener.onAfterUpdate(modelOutputItem);
            }
        }

        return modelOutputItem;
    }

    public ModelOutputItem updateImpl(
        com.ext.portlet.models.model.ModelOutputItem modelOutputItem,
        boolean merge) throws SystemException {
        boolean isNew = modelOutputItem.isNew();

        ModelOutputItemModelImpl modelOutputItemModelImpl = (ModelOutputItemModelImpl) modelOutputItem;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, modelOutputItem, merge);

            modelOutputItem.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(ModelOutputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputItemImpl.class, modelOutputItem.getPrimaryKey(),
            modelOutputItem);

        if (!isNew &&
                (!Validator.equals(modelOutputItem.getModelOutputItemId(),
                    modelOutputItemModelImpl.getOriginalModelOutputItemId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MODELOUTPUTID,
                new Object[] {
                    modelOutputItemModelImpl.getOriginalModelOutputItemId()
                });
        }

        if (isNew ||
                (!Validator.equals(modelOutputItem.getModelOutputItemId(),
                    modelOutputItemModelImpl.getOriginalModelOutputItemId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELOUTPUTID,
                new Object[] { modelOutputItem.getModelOutputItemId() },
                modelOutputItem);
        }

        return modelOutputItem;
    }

    public ModelOutputItem findByPrimaryKey(Long modelOutputItemModifierPK)
        throws NoSuchModelOutputItemException, SystemException {
        ModelOutputItem modelOutputItem = fetchByPrimaryKey(modelOutputItemModifierPK);

        if (modelOutputItem == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No ModelOutputItem exists with the primary key " +
                    modelOutputItemModifierPK);
            }

            throw new NoSuchModelOutputItemException(
                "No ModelOutputItem exists with the primary key " +
                modelOutputItemModifierPK);
        }

        return modelOutputItem;
    }

    public ModelOutputItem fetchByPrimaryKey(Long modelOutputItemModifierPK)
        throws SystemException {
        ModelOutputItem modelOutputItem = (ModelOutputItem) EntityCacheUtil.getResult(ModelOutputItemModelImpl.ENTITY_CACHE_ENABLED,
                ModelOutputItemImpl.class, modelOutputItemModifierPK, this);

        if (modelOutputItem == null) {
            Session session = null;

            try {
                session = openSession();

                modelOutputItem = (ModelOutputItem) session.get(ModelOutputItemImpl.class,
                        modelOutputItemModifierPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (modelOutputItem != null) {
                    cacheResult(modelOutputItem);
                }

                closeSession(session);
            }
        }

        return modelOutputItem;
    }

    public ModelOutputItem findByModelOutputId(Long modelOutputItemId)
        throws NoSuchModelOutputItemException, SystemException {
        ModelOutputItem modelOutputItem = fetchByModelOutputId(modelOutputItemId);

        if (modelOutputItem == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ModelOutputItem exists with the key {");

            msg.append("modelOutputItemId=" + modelOutputItemId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchModelOutputItemException(msg.toString());
        }

        return modelOutputItem;
    }

    public ModelOutputItem fetchByModelOutputId(Long modelOutputItemId)
        throws SystemException {
        return fetchByModelOutputId(modelOutputItemId, true);
    }

    public ModelOutputItem fetchByModelOutputId(Long modelOutputItemId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { modelOutputItemId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_MODELOUTPUTID,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelOutputItem WHERE ");

                if (modelOutputItemId == null) {
                    query.append("modelOutputItemId IS NULL");
                } else {
                    query.append("modelOutputItemId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (modelOutputItemId != null) {
                    qPos.add(modelOutputItemId.longValue());
                }

                List<ModelOutputItem> list = q.list();

                result = list;

                ModelOutputItem modelOutputItem = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELOUTPUTID,
                        finderArgs, list);
                } else {
                    modelOutputItem = list.get(0);

                    cacheResult(modelOutputItem);

                    if ((modelOutputItem.getModelOutputItemId() == null) ||
                            !modelOutputItem.getModelOutputItemId()
                                                .equals(modelOutputItemId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELOUTPUTID,
                            finderArgs, modelOutputItem);
                    }
                }

                return modelOutputItem;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELOUTPUTID,
                        finderArgs, new ArrayList<ModelOutputItem>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (ModelOutputItem) result;
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

    public List<ModelOutputItem> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<ModelOutputItem> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<ModelOutputItem> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ModelOutputItem> list = (List<ModelOutputItem>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelOutputItem ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<ModelOutputItem>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ModelOutputItem>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelOutputItem>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByModelOutputId(Long modelOutputItemId)
        throws NoSuchModelOutputItemException, SystemException {
        ModelOutputItem modelOutputItem = findByModelOutputId(modelOutputItemId);

        remove(modelOutputItem);
    }

    public void removeAll() throws SystemException {
        for (ModelOutputItem modelOutputItem : findAll()) {
            remove(modelOutputItem);
        }
    }

    public int countByModelOutputId(Long modelOutputItemId)
        throws SystemException {
        Object[] finderArgs = new Object[] { modelOutputItemId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MODELOUTPUTID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.models.model.ModelOutputItem WHERE ");

                if (modelOutputItemId == null) {
                    query.append("modelOutputItemId IS NULL");
                } else {
                    query.append("modelOutputItemId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (modelOutputItemId != null) {
                    qPos.add(modelOutputItemId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODELOUTPUTID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.models.model.ModelOutputItem");

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
                        "value.object.listener.com.ext.portlet.models.model.ModelOutputItem")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ModelOutputItem>> listenersList = new ArrayList<ModelListener<ModelOutputItem>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ModelOutputItem>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
