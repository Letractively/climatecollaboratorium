package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanTypeAttributeException;
import com.ext.portlet.plans.model.PlanTypeAttribute;
import com.ext.portlet.plans.model.impl.PlanTypeAttributeImpl;
import com.ext.portlet.plans.model.impl.PlanTypeAttributeModelImpl;

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


public class PlanTypeAttributePersistenceImpl extends BasePersistenceImpl
    implements PlanTypeAttributePersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanTypeAttributeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeAttributeModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeAttributeModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanTypeAttributePersistenceImpl.class);
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
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanPositionsPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanPositionsPersistence planPositionsPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanPositionItemPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanPositionItemPersistence planPositionItemPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanFanPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanFanPersistence planFanPersistence;

    public void cacheResult(PlanTypeAttribute planTypeAttribute) {
        EntityCacheUtil.putResult(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeAttributeImpl.class, planTypeAttribute.getPrimaryKey(),
            planTypeAttribute);
    }

    public void cacheResult(List<PlanTypeAttribute> planTypeAttributes) {
        for (PlanTypeAttribute planTypeAttribute : planTypeAttributes) {
            if (EntityCacheUtil.getResult(
                        PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTypeAttributeImpl.class,
                        planTypeAttribute.getPrimaryKey(), this) == null) {
                cacheResult(planTypeAttribute);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanTypeAttributeImpl.class.getName());
        EntityCacheUtil.clearCache(PlanTypeAttributeImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanTypeAttribute create(Long planTypeAttributeId) {
        PlanTypeAttribute planTypeAttribute = new PlanTypeAttributeImpl();

        planTypeAttribute.setNew(true);
        planTypeAttribute.setPrimaryKey(planTypeAttributeId);

        return planTypeAttribute;
    }

    public PlanTypeAttribute remove(Long planTypeAttributeId)
        throws NoSuchPlanTypeAttributeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanTypeAttribute planTypeAttribute = (PlanTypeAttribute) session.get(PlanTypeAttributeImpl.class,
                    planTypeAttributeId);

            if (planTypeAttribute == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No PlanTypeAttribute exists with the primary key " +
                        planTypeAttributeId);
                }

                throw new NoSuchPlanTypeAttributeException(
                    "No PlanTypeAttribute exists with the primary key " +
                    planTypeAttributeId);
            }

            return remove(planTypeAttribute);
        } catch (NoSuchPlanTypeAttributeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanTypeAttribute remove(PlanTypeAttribute planTypeAttribute)
        throws SystemException {
        for (ModelListener<PlanTypeAttribute> listener : listeners) {
            listener.onBeforeRemove(planTypeAttribute);
        }

        planTypeAttribute = removeImpl(planTypeAttribute);

        for (ModelListener<PlanTypeAttribute> listener : listeners) {
            listener.onAfterRemove(planTypeAttribute);
        }

        return planTypeAttribute;
    }

    protected PlanTypeAttribute removeImpl(PlanTypeAttribute planTypeAttribute)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planTypeAttribute.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanTypeAttributeImpl.class,
                        planTypeAttribute.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planTypeAttribute);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeAttributeImpl.class, planTypeAttribute.getPrimaryKey());

        return planTypeAttribute;
    }

    /**
     * @deprecated Use <code>update(PlanTypeAttribute planTypeAttribute, boolean merge)</code>.
     */
    public PlanTypeAttribute update(PlanTypeAttribute planTypeAttribute)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanTypeAttribute planTypeAttribute) method. Use update(PlanTypeAttribute planTypeAttribute, boolean merge) instead.");
        }

        return update(planTypeAttribute, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planTypeAttribute the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planTypeAttribute is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanTypeAttribute update(PlanTypeAttribute planTypeAttribute,
        boolean merge) throws SystemException {
        boolean isNew = planTypeAttribute.isNew();

        for (ModelListener<PlanTypeAttribute> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planTypeAttribute);
            } else {
                listener.onBeforeUpdate(planTypeAttribute);
            }
        }

        planTypeAttribute = updateImpl(planTypeAttribute, merge);

        for (ModelListener<PlanTypeAttribute> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planTypeAttribute);
            } else {
                listener.onAfterUpdate(planTypeAttribute);
            }
        }

        return planTypeAttribute;
    }

    public PlanTypeAttribute updateImpl(
        com.ext.portlet.plans.model.PlanTypeAttribute planTypeAttribute,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planTypeAttribute, merge);

            planTypeAttribute.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeAttributeImpl.class, planTypeAttribute.getPrimaryKey(),
            planTypeAttribute);

        return planTypeAttribute;
    }

    public PlanTypeAttribute findByPrimaryKey(Long planTypeAttributeId)
        throws NoSuchPlanTypeAttributeException, SystemException {
        PlanTypeAttribute planTypeAttribute = fetchByPrimaryKey(planTypeAttributeId);

        if (planTypeAttribute == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanTypeAttribute exists with the primary key " +
                    planTypeAttributeId);
            }

            throw new NoSuchPlanTypeAttributeException(
                "No PlanTypeAttribute exists with the primary key " +
                planTypeAttributeId);
        }

        return planTypeAttribute;
    }

    public PlanTypeAttribute fetchByPrimaryKey(Long planTypeAttributeId)
        throws SystemException {
        PlanTypeAttribute planTypeAttribute = (PlanTypeAttribute) EntityCacheUtil.getResult(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
                PlanTypeAttributeImpl.class, planTypeAttributeId, this);

        if (planTypeAttribute == null) {
            Session session = null;

            try {
                session = openSession();

                planTypeAttribute = (PlanTypeAttribute) session.get(PlanTypeAttributeImpl.class,
                        planTypeAttributeId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planTypeAttribute != null) {
                    cacheResult(planTypeAttribute);
                }

                closeSession(session);
            }
        }

        return planTypeAttribute;
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

    public List<PlanTypeAttribute> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanTypeAttribute> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanTypeAttribute> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanTypeAttribute> list = (List<PlanTypeAttribute>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanTypeAttribute ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlanTypeAttribute>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanTypeAttribute>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanTypeAttribute>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (PlanTypeAttribute planTypeAttribute : findAll()) {
            remove(planTypeAttribute);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanTypeAttribute");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanTypeAttribute")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanTypeAttribute>> listenersList = new ArrayList<ModelListener<PlanTypeAttribute>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanTypeAttribute>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
