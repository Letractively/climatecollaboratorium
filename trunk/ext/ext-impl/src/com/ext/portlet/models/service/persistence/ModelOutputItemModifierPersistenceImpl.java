package com.ext.portlet.models.service.persistence;

import com.ext.portlet.models.NoSuchModelOutputItemModifierException;
import com.ext.portlet.models.model.ModelOutputItemModifier;
import com.ext.portlet.models.model.impl.ModelOutputItemModifierImpl;
import com.ext.portlet.models.model.impl.ModelOutputItemModifierModelImpl;

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


public class ModelOutputItemModifierPersistenceImpl extends BasePersistenceImpl
    implements ModelOutputItemModifierPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = ModelOutputItemModifierImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ModelOutputItemModifierModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputItemModifierModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModelOutputItemModifierModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputItemModifierModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(ModelOutputItemModifierPersistenceImpl.class);
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

    public void cacheResult(ModelOutputItemModifier modelOutputItemModifier) {
        EntityCacheUtil.putResult(ModelOutputItemModifierModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputItemModifierImpl.class,
            modelOutputItemModifier.getPrimaryKey(), modelOutputItemModifier);
    }

    public void cacheResult(
        List<ModelOutputItemModifier> modelOutputItemModifiers) {
        for (ModelOutputItemModifier modelOutputItemModifier : modelOutputItemModifiers) {
            if (EntityCacheUtil.getResult(
                        ModelOutputItemModifierModelImpl.ENTITY_CACHE_ENABLED,
                        ModelOutputItemModifierImpl.class,
                        modelOutputItemModifier.getPrimaryKey(), this) == null) {
                cacheResult(modelOutputItemModifier);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(ModelOutputItemModifierImpl.class.getName());
        EntityCacheUtil.clearCache(ModelOutputItemModifierImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public ModelOutputItemModifier create(Long modelOutputItemModifierPK) {
        ModelOutputItemModifier modelOutputItemModifier = new ModelOutputItemModifierImpl();

        modelOutputItemModifier.setNew(true);
        modelOutputItemModifier.setPrimaryKey(modelOutputItemModifierPK);

        return modelOutputItemModifier;
    }

    public ModelOutputItemModifier remove(Long modelOutputItemModifierPK)
        throws NoSuchModelOutputItemModifierException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ModelOutputItemModifier modelOutputItemModifier = (ModelOutputItemModifier) session.get(ModelOutputItemModifierImpl.class,
                    modelOutputItemModifierPK);

            if (modelOutputItemModifier == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No ModelOutputItemModifier exists with the primary key " +
                        modelOutputItemModifierPK);
                }

                throw new NoSuchModelOutputItemModifierException(
                    "No ModelOutputItemModifier exists with the primary key " +
                    modelOutputItemModifierPK);
            }

            return remove(modelOutputItemModifier);
        } catch (NoSuchModelOutputItemModifierException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ModelOutputItemModifier remove(
        ModelOutputItemModifier modelOutputItemModifier)
        throws SystemException {
        for (ModelListener<ModelOutputItemModifier> listener : listeners) {
            listener.onBeforeRemove(modelOutputItemModifier);
        }

        modelOutputItemModifier = removeImpl(modelOutputItemModifier);

        for (ModelListener<ModelOutputItemModifier> listener : listeners) {
            listener.onAfterRemove(modelOutputItemModifier);
        }

        return modelOutputItemModifier;
    }

    protected ModelOutputItemModifier removeImpl(
        ModelOutputItemModifier modelOutputItemModifier)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (modelOutputItemModifier.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(ModelOutputItemModifierImpl.class,
                        modelOutputItemModifier.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(modelOutputItemModifier);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(ModelOutputItemModifierModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputItemModifierImpl.class,
            modelOutputItemModifier.getPrimaryKey());

        return modelOutputItemModifier;
    }

    /**
     * @deprecated Use <code>update(ModelOutputItemModifier modelOutputItemModifier, boolean merge)</code>.
     */
    public ModelOutputItemModifier update(
        ModelOutputItemModifier modelOutputItemModifier)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(ModelOutputItemModifier modelOutputItemModifier) method. Use update(ModelOutputItemModifier modelOutputItemModifier, boolean merge) instead.");
        }

        return update(modelOutputItemModifier, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelOutputItemModifier the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelOutputItemModifier is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public ModelOutputItemModifier update(
        ModelOutputItemModifier modelOutputItemModifier, boolean merge)
        throws SystemException {
        boolean isNew = modelOutputItemModifier.isNew();

        for (ModelListener<ModelOutputItemModifier> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(modelOutputItemModifier);
            } else {
                listener.onBeforeUpdate(modelOutputItemModifier);
            }
        }

        modelOutputItemModifier = updateImpl(modelOutputItemModifier, merge);

        for (ModelListener<ModelOutputItemModifier> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(modelOutputItemModifier);
            } else {
                listener.onAfterUpdate(modelOutputItemModifier);
            }
        }

        return modelOutputItemModifier;
    }

    public ModelOutputItemModifier updateImpl(
        com.ext.portlet.models.model.ModelOutputItemModifier modelOutputItemModifier,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, modelOutputItemModifier, merge);

            modelOutputItemModifier.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(ModelOutputItemModifierModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputItemModifierImpl.class,
            modelOutputItemModifier.getPrimaryKey(), modelOutputItemModifier);

        return modelOutputItemModifier;
    }

    public ModelOutputItemModifier findByPrimaryKey(
        Long modelOutputItemModifierPK)
        throws NoSuchModelOutputItemModifierException, SystemException {
        ModelOutputItemModifier modelOutputItemModifier = fetchByPrimaryKey(modelOutputItemModifierPK);

        if (modelOutputItemModifier == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No ModelOutputItemModifier exists with the primary key " +
                    modelOutputItemModifierPK);
            }

            throw new NoSuchModelOutputItemModifierException(
                "No ModelOutputItemModifier exists with the primary key " +
                modelOutputItemModifierPK);
        }

        return modelOutputItemModifier;
    }

    public ModelOutputItemModifier fetchByPrimaryKey(
        Long modelOutputItemModifierPK) throws SystemException {
        ModelOutputItemModifier modelOutputItemModifier = (ModelOutputItemModifier) EntityCacheUtil.getResult(ModelOutputItemModifierModelImpl.ENTITY_CACHE_ENABLED,
                ModelOutputItemModifierImpl.class, modelOutputItemModifierPK,
                this);

        if (modelOutputItemModifier == null) {
            Session session = null;

            try {
                session = openSession();

                modelOutputItemModifier = (ModelOutputItemModifier) session.get(ModelOutputItemModifierImpl.class,
                        modelOutputItemModifierPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (modelOutputItemModifier != null) {
                    cacheResult(modelOutputItemModifier);
                }

                closeSession(session);
            }
        }

        return modelOutputItemModifier;
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

    public List<ModelOutputItemModifier> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<ModelOutputItemModifier> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<ModelOutputItemModifier> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ModelOutputItemModifier> list = (List<ModelOutputItemModifier>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.models.model.ModelOutputItemModifier ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<ModelOutputItemModifier>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ModelOutputItemModifier>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ModelOutputItemModifier>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (ModelOutputItemModifier modelOutputItemModifier : findAll()) {
            remove(modelOutputItemModifier);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.models.model.ModelOutputItemModifier");

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
                        "value.object.listener.com.ext.portlet.models.model.ModelOutputItemModifier")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ModelOutputItemModifier>> listenersList = new ArrayList<ModelListener<ModelOutputItemModifier>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ModelOutputItemModifier>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
