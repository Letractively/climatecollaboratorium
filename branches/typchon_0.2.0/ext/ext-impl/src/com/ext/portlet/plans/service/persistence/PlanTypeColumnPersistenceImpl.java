package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanTypeColumnException;
import com.ext.portlet.plans.model.PlanTypeColumn;
import com.ext.portlet.plans.model.impl.PlanTypeColumnImpl;
import com.ext.portlet.plans.model.impl.PlanTypeColumnModelImpl;

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


public class PlanTypeColumnPersistenceImpl extends BasePersistenceImpl
    implements PlanTypeColumnPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanTypeColumnImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeColumnModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeColumnModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanTypeColumnPersistenceImpl.class);
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
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanRelatedPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanRelatedPersistence planRelatedPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanTemplatePersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanTemplatePersistence planTemplatePersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanTemplateSectionPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanTemplateSectionPersistence planTemplateSectionPersistence;

    public void cacheResult(PlanTypeColumn planTypeColumn) {
        EntityCacheUtil.putResult(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeColumnImpl.class, planTypeColumn.getPrimaryKey(),
            planTypeColumn);
    }

    public void cacheResult(List<PlanTypeColumn> planTypeColumns) {
        for (PlanTypeColumn planTypeColumn : planTypeColumns) {
            if (EntityCacheUtil.getResult(
                        PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTypeColumnImpl.class,
                        planTypeColumn.getPrimaryKey(), this) == null) {
                cacheResult(planTypeColumn);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanTypeColumnImpl.class.getName());
        EntityCacheUtil.clearCache(PlanTypeColumnImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanTypeColumn create(Long planTypeColumnId) {
        PlanTypeColumn planTypeColumn = new PlanTypeColumnImpl();

        planTypeColumn.setNew(true);
        planTypeColumn.setPrimaryKey(planTypeColumnId);

        return planTypeColumn;
    }

    public PlanTypeColumn remove(Long planTypeColumnId)
        throws NoSuchPlanTypeColumnException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanTypeColumn planTypeColumn = (PlanTypeColumn) session.get(PlanTypeColumnImpl.class,
                    planTypeColumnId);

            if (planTypeColumn == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No PlanTypeColumn exists with the primary key " +
                        planTypeColumnId);
                }

                throw new NoSuchPlanTypeColumnException(
                    "No PlanTypeColumn exists with the primary key " +
                    planTypeColumnId);
            }

            return remove(planTypeColumn);
        } catch (NoSuchPlanTypeColumnException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanTypeColumn remove(PlanTypeColumn planTypeColumn)
        throws SystemException {
        for (ModelListener<PlanTypeColumn> listener : listeners) {
            listener.onBeforeRemove(planTypeColumn);
        }

        planTypeColumn = removeImpl(planTypeColumn);

        for (ModelListener<PlanTypeColumn> listener : listeners) {
            listener.onAfterRemove(planTypeColumn);
        }

        return planTypeColumn;
    }

    protected PlanTypeColumn removeImpl(PlanTypeColumn planTypeColumn)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planTypeColumn.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanTypeColumnImpl.class,
                        planTypeColumn.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planTypeColumn);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeColumnImpl.class, planTypeColumn.getPrimaryKey());

        return planTypeColumn;
    }

    /**
     * @deprecated Use <code>update(PlanTypeColumn planTypeColumn, boolean merge)</code>.
     */
    public PlanTypeColumn update(PlanTypeColumn planTypeColumn)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanTypeColumn planTypeColumn) method. Use update(PlanTypeColumn planTypeColumn, boolean merge) instead.");
        }

        return update(planTypeColumn, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planTypeColumn the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planTypeColumn is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanTypeColumn update(PlanTypeColumn planTypeColumn, boolean merge)
        throws SystemException {
        boolean isNew = planTypeColumn.isNew();

        for (ModelListener<PlanTypeColumn> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planTypeColumn);
            } else {
                listener.onBeforeUpdate(planTypeColumn);
            }
        }

        planTypeColumn = updateImpl(planTypeColumn, merge);

        for (ModelListener<PlanTypeColumn> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planTypeColumn);
            } else {
                listener.onAfterUpdate(planTypeColumn);
            }
        }

        return planTypeColumn;
    }

    public PlanTypeColumn updateImpl(
        com.ext.portlet.plans.model.PlanTypeColumn planTypeColumn, boolean merge)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planTypeColumn, merge);

            planTypeColumn.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeColumnImpl.class, planTypeColumn.getPrimaryKey(),
            planTypeColumn);

        return planTypeColumn;
    }

    public PlanTypeColumn findByPrimaryKey(Long planTypeColumnId)
        throws NoSuchPlanTypeColumnException, SystemException {
        PlanTypeColumn planTypeColumn = fetchByPrimaryKey(planTypeColumnId);

        if (planTypeColumn == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanTypeColumn exists with the primary key " +
                    planTypeColumnId);
            }

            throw new NoSuchPlanTypeColumnException(
                "No PlanTypeColumn exists with the primary key " +
                planTypeColumnId);
        }

        return planTypeColumn;
    }

    public PlanTypeColumn fetchByPrimaryKey(Long planTypeColumnId)
        throws SystemException {
        PlanTypeColumn planTypeColumn = (PlanTypeColumn) EntityCacheUtil.getResult(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
                PlanTypeColumnImpl.class, planTypeColumnId, this);

        if (planTypeColumn == null) {
            Session session = null;

            try {
                session = openSession();

                planTypeColumn = (PlanTypeColumn) session.get(PlanTypeColumnImpl.class,
                        planTypeColumnId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planTypeColumn != null) {
                    cacheResult(planTypeColumn);
                }

                closeSession(session);
            }
        }

        return planTypeColumn;
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

    public List<PlanTypeColumn> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanTypeColumn> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanTypeColumn> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanTypeColumn> list = (List<PlanTypeColumn>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanTypeColumn ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("weight ASC, ");
                    query.append("columnName ASC");
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlanTypeColumn>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanTypeColumn>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanTypeColumn>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (PlanTypeColumn planTypeColumn : findAll()) {
            remove(planTypeColumn);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanTypeColumn");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanTypeColumn")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanTypeColumn>> listenersList = new ArrayList<ModelListener<PlanTypeColumn>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanTypeColumn>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
