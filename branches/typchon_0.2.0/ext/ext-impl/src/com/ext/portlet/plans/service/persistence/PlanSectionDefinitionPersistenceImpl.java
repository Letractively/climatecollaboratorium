package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanSectionDefinitionException;
import com.ext.portlet.plans.model.PlanSectionDefinition;
import com.ext.portlet.plans.model.impl.PlanSectionDefinitionImpl;
import com.ext.portlet.plans.model.impl.PlanSectionDefinitionModelImpl;

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


public class PlanSectionDefinitionPersistenceImpl extends BasePersistenceImpl
    implements PlanSectionDefinitionPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanSectionDefinitionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionDefinitionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionDefinitionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanSectionDefinitionPersistenceImpl.class);
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
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanTeamHistoryPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanTeamHistoryPersistence planTeamHistoryPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanSectionDefinitionPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanSectionDefinitionPersistence planSectionDefinitionPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanSectionPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanSectionPersistence planSectionPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPersistence planSectionPlanMapPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanRelatedPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanRelatedPersistence planRelatedPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanTemplatePersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanTemplatePersistence planTemplatePersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanTemplateSectionPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanTemplateSectionPersistence planTemplateSectionPersistence;

    public void cacheResult(PlanSectionDefinition planSectionDefinition) {
        EntityCacheUtil.putResult(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionDefinitionImpl.class,
            planSectionDefinition.getPrimaryKey(), planSectionDefinition);
    }

    public void cacheResult(List<PlanSectionDefinition> planSectionDefinitions) {
        for (PlanSectionDefinition planSectionDefinition : planSectionDefinitions) {
            if (EntityCacheUtil.getResult(
                        PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                        PlanSectionDefinitionImpl.class,
                        planSectionDefinition.getPrimaryKey(), this) == null) {
                cacheResult(planSectionDefinition);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanSectionDefinitionImpl.class.getName());
        EntityCacheUtil.clearCache(PlanSectionDefinitionImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanSectionDefinition create(Long id) {
        PlanSectionDefinition planSectionDefinition = new PlanSectionDefinitionImpl();

        planSectionDefinition.setNew(true);
        planSectionDefinition.setPrimaryKey(id);

        return planSectionDefinition;
    }

    public PlanSectionDefinition remove(Long id)
        throws NoSuchPlanSectionDefinitionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanSectionDefinition planSectionDefinition = (PlanSectionDefinition) session.get(PlanSectionDefinitionImpl.class,
                    id);

            if (planSectionDefinition == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No PlanSectionDefinition exists with the primary key " +
                        id);
                }

                throw new NoSuchPlanSectionDefinitionException(
                    "No PlanSectionDefinition exists with the primary key " +
                    id);
            }

            return remove(planSectionDefinition);
        } catch (NoSuchPlanSectionDefinitionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanSectionDefinition remove(
        PlanSectionDefinition planSectionDefinition) throws SystemException {
        for (ModelListener<PlanSectionDefinition> listener : listeners) {
            listener.onBeforeRemove(planSectionDefinition);
        }

        planSectionDefinition = removeImpl(planSectionDefinition);

        for (ModelListener<PlanSectionDefinition> listener : listeners) {
            listener.onAfterRemove(planSectionDefinition);
        }

        return planSectionDefinition;
    }

    protected PlanSectionDefinition removeImpl(
        PlanSectionDefinition planSectionDefinition) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planSectionDefinition.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanSectionDefinitionImpl.class,
                        planSectionDefinition.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planSectionDefinition);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionDefinitionImpl.class,
            planSectionDefinition.getPrimaryKey());

        return planSectionDefinition;
    }

    /**
     * @deprecated Use <code>update(PlanSectionDefinition planSectionDefinition, boolean merge)</code>.
     */
    public PlanSectionDefinition update(
        PlanSectionDefinition planSectionDefinition) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanSectionDefinition planSectionDefinition) method. Use update(PlanSectionDefinition planSectionDefinition, boolean merge) instead.");
        }

        return update(planSectionDefinition, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planSectionDefinition the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planSectionDefinition is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanSectionDefinition update(
        PlanSectionDefinition planSectionDefinition, boolean merge)
        throws SystemException {
        boolean isNew = planSectionDefinition.isNew();

        for (ModelListener<PlanSectionDefinition> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planSectionDefinition);
            } else {
                listener.onBeforeUpdate(planSectionDefinition);
            }
        }

        planSectionDefinition = updateImpl(planSectionDefinition, merge);

        for (ModelListener<PlanSectionDefinition> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planSectionDefinition);
            } else {
                listener.onAfterUpdate(planSectionDefinition);
            }
        }

        return planSectionDefinition;
    }

    public PlanSectionDefinition updateImpl(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planSectionDefinition, merge);

            planSectionDefinition.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionDefinitionImpl.class,
            planSectionDefinition.getPrimaryKey(), planSectionDefinition);

        return planSectionDefinition;
    }

    public PlanSectionDefinition findByPrimaryKey(Long id)
        throws NoSuchPlanSectionDefinitionException, SystemException {
        PlanSectionDefinition planSectionDefinition = fetchByPrimaryKey(id);

        if (planSectionDefinition == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "No PlanSectionDefinition exists with the primary key " +
                    id);
            }

            throw new NoSuchPlanSectionDefinitionException(
                "No PlanSectionDefinition exists with the primary key " + id);
        }

        return planSectionDefinition;
    }

    public PlanSectionDefinition fetchByPrimaryKey(Long id)
        throws SystemException {
        PlanSectionDefinition planSectionDefinition = (PlanSectionDefinition) EntityCacheUtil.getResult(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                PlanSectionDefinitionImpl.class, id, this);

        if (planSectionDefinition == null) {
            Session session = null;

            try {
                session = openSession();

                planSectionDefinition = (PlanSectionDefinition) session.get(PlanSectionDefinitionImpl.class,
                        id);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planSectionDefinition != null) {
                    cacheResult(planSectionDefinition);
                }

                closeSession(session);
            }
        }

        return planSectionDefinition;
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

    public List<PlanSectionDefinition> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanSectionDefinition> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanSectionDefinition> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanSectionDefinition> list = (List<PlanSectionDefinition>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanSectionDefinition ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlanSectionDefinition>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanSectionDefinition>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanSectionDefinition>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (PlanSectionDefinition planSectionDefinition : findAll()) {
            remove(planSectionDefinition);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanSectionDefinition");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanSectionDefinition")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanSectionDefinition>> listenersList = new ArrayList<ModelListener<PlanSectionDefinition>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanSectionDefinition>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
