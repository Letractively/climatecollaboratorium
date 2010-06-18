package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.impl.PlanItemImpl;
import com.ext.portlet.plans.model.impl.PlanItemModelImpl;

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


public class PlanItemPersistenceImpl extends BasePersistenceImpl
    implements PlanItemPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanItemImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanItemPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanPersistence planPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanAttributePersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanAttributePersistence planAttributePersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanPositionPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanPositionPersistence planPositionPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlansUserSettingsPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlansUserSettingsPersistence plansUserSettingsPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanVotePersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanVotePersistence planVotePersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlansFilterPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlansFilterPersistence plansFilterPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanAttributeFilterPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanAttributeFilterPersistence planAttributeFilterPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanPropertyFilterPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanPropertyFilterPersistence planPropertyFilterPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanColumnSettingsPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanColumnSettingsPersistence planColumnSettingsPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlansFilterPositionPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlansFilterPositionPersistence plansFilterPositionPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanTypePersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanTypePersistence planTypePersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanTypeAttributePersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanTypeAttributePersistence planTypeAttributePersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanTypeColumnPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanTypeColumnPersistence planTypeColumnPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanItemPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanItemPersistence planItemPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanDescriptionPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanDescriptionPersistence planDescriptionPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanMetaPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanMetaPersistence planMetaPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanModelRunPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanModelRunPersistence planModelRunPersistence;

    public void cacheResult(PlanItem planItem) {
        EntityCacheUtil.putResult(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemImpl.class, planItem.getPrimaryKey(), planItem);
    }

    public void cacheResult(List<PlanItem> planItems) {
        for (PlanItem planItem : planItems) {
            if (EntityCacheUtil.getResult(
                        PlanItemModelImpl.ENTITY_CACHE_ENABLED,
                        PlanItemImpl.class, planItem.getPrimaryKey(), this) == null) {
                cacheResult(planItem);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanItemImpl.class.getName());
        EntityCacheUtil.clearCache(PlanItemImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanItem create(Long id) {
        PlanItem planItem = new PlanItemImpl();

        planItem.setNew(true);
        planItem.setPrimaryKey(id);

        return planItem;
    }

    public PlanItem remove(Long id)
        throws NoSuchPlanItemException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanItem planItem = (PlanItem) session.get(PlanItemImpl.class, id);

            if (planItem == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No PlanItem exists with the primary key " + id);
                }

                throw new NoSuchPlanItemException(
                    "No PlanItem exists with the primary key " + id);
            }

            return remove(planItem);
        } catch (NoSuchPlanItemException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanItem remove(PlanItem planItem) throws SystemException {
        for (ModelListener<PlanItem> listener : listeners) {
            listener.onBeforeRemove(planItem);
        }

        planItem = removeImpl(planItem);

        for (ModelListener<PlanItem> listener : listeners) {
            listener.onAfterRemove(planItem);
        }

        return planItem;
    }

    protected PlanItem removeImpl(PlanItem planItem) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planItem.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanItemImpl.class,
                        planItem.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planItem);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemImpl.class, planItem.getPrimaryKey());

        return planItem;
    }

    /**
     * @deprecated Use <code>update(PlanItem planItem, boolean merge)</code>.
     */
    public PlanItem update(PlanItem planItem) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanItem planItem) method. Use update(PlanItem planItem, boolean merge) instead.");
        }

        return update(planItem, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planItem the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planItem is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanItem update(PlanItem planItem, boolean merge)
        throws SystemException {
        boolean isNew = planItem.isNew();

        for (ModelListener<PlanItem> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planItem);
            } else {
                listener.onBeforeUpdate(planItem);
            }
        }

        planItem = updateImpl(planItem, merge);

        for (ModelListener<PlanItem> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planItem);
            } else {
                listener.onAfterUpdate(planItem);
            }
        }

        return planItem;
    }

    public PlanItem updateImpl(com.ext.portlet.plans.model.PlanItem planItem,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planItem, merge);

            planItem.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemImpl.class, planItem.getPrimaryKey(), planItem);

        return planItem;
    }

    public PlanItem findByPrimaryKey(Long id)
        throws NoSuchPlanItemException, SystemException {
        PlanItem planItem = fetchByPrimaryKey(id);

        if (planItem == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanItem exists with the primary key " + id);
            }

            throw new NoSuchPlanItemException(
                "No PlanItem exists with the primary key " + id);
        }

        return planItem;
    }

    public PlanItem fetchByPrimaryKey(Long id) throws SystemException {
        PlanItem planItem = (PlanItem) EntityCacheUtil.getResult(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
                PlanItemImpl.class, id, this);

        if (planItem == null) {
            Session session = null;

            try {
                session = openSession();

                planItem = (PlanItem) session.get(PlanItemImpl.class, id);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planItem != null) {
                    cacheResult(planItem);
                }

                closeSession(session);
            }
        }

        return planItem;
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

    public List<PlanItem> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanItem> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanItem> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanItem> list = (List<PlanItem>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanItem ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlanItem>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanItem>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanItem>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (PlanItem planItem : findAll()) {
            remove(planItem);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanItem");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanItem")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanItem>> listenersList = new ArrayList<ModelListener<PlanItem>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanItem>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
