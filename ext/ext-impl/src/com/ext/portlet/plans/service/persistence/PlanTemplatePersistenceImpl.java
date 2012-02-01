package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanTemplateException;
import com.ext.portlet.plans.model.PlanTemplate;
import com.ext.portlet.plans.model.impl.PlanTemplateImpl;
import com.ext.portlet.plans.model.impl.PlanTemplateModelImpl;

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


public class PlanTemplatePersistenceImpl extends BasePersistenceImpl
    implements PlanTemplatePersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanTemplateImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanTemplateModelImpl.ENTITY_CACHE_ENABLED,
            PlanTemplateModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanTemplateModelImpl.ENTITY_CACHE_ENABLED,
            PlanTemplateModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanTemplatePersistenceImpl.class);
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

    public void cacheResult(PlanTemplate planTemplate) {
        EntityCacheUtil.putResult(PlanTemplateModelImpl.ENTITY_CACHE_ENABLED,
            PlanTemplateImpl.class, planTemplate.getPrimaryKey(), planTemplate);
    }

    public void cacheResult(List<PlanTemplate> planTemplates) {
        for (PlanTemplate planTemplate : planTemplates) {
            if (EntityCacheUtil.getResult(
                        PlanTemplateModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTemplateImpl.class, planTemplate.getPrimaryKey(),
                        this) == null) {
                cacheResult(planTemplate);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanTemplateImpl.class.getName());
        EntityCacheUtil.clearCache(PlanTemplateImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanTemplate create(Long id) {
        PlanTemplate planTemplate = new PlanTemplateImpl();

        planTemplate.setNew(true);
        planTemplate.setPrimaryKey(id);

        return planTemplate;
    }

    public PlanTemplate remove(Long id)
        throws NoSuchPlanTemplateException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanTemplate planTemplate = (PlanTemplate) session.get(PlanTemplateImpl.class,
                    id);

            if (planTemplate == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No PlanTemplate exists with the primary key " +
                        id);
                }

                throw new NoSuchPlanTemplateException(
                    "No PlanTemplate exists with the primary key " + id);
            }

            return remove(planTemplate);
        } catch (NoSuchPlanTemplateException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanTemplate remove(PlanTemplate planTemplate)
        throws SystemException {
        for (ModelListener<PlanTemplate> listener : listeners) {
            listener.onBeforeRemove(planTemplate);
        }

        planTemplate = removeImpl(planTemplate);

        for (ModelListener<PlanTemplate> listener : listeners) {
            listener.onAfterRemove(planTemplate);
        }

        return planTemplate;
    }

    protected PlanTemplate removeImpl(PlanTemplate planTemplate)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planTemplate.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanTemplateImpl.class,
                        planTemplate.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planTemplate);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(PlanTemplateModelImpl.ENTITY_CACHE_ENABLED,
            PlanTemplateImpl.class, planTemplate.getPrimaryKey());

        return planTemplate;
    }

    /**
     * @deprecated Use <code>update(PlanTemplate planTemplate, boolean merge)</code>.
     */
    public PlanTemplate update(PlanTemplate planTemplate)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanTemplate planTemplate) method. Use update(PlanTemplate planTemplate, boolean merge) instead.");
        }

        return update(planTemplate, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planTemplate the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planTemplate is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanTemplate update(PlanTemplate planTemplate, boolean merge)
        throws SystemException {
        boolean isNew = planTemplate.isNew();

        for (ModelListener<PlanTemplate> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planTemplate);
            } else {
                listener.onBeforeUpdate(planTemplate);
            }
        }

        planTemplate = updateImpl(planTemplate, merge);

        for (ModelListener<PlanTemplate> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planTemplate);
            } else {
                listener.onAfterUpdate(planTemplate);
            }
        }

        return planTemplate;
    }

    public PlanTemplate updateImpl(
        com.ext.portlet.plans.model.PlanTemplate planTemplate, boolean merge)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planTemplate, merge);

            planTemplate.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanTemplateModelImpl.ENTITY_CACHE_ENABLED,
            PlanTemplateImpl.class, planTemplate.getPrimaryKey(), planTemplate);

        return planTemplate;
    }

    public PlanTemplate findByPrimaryKey(Long id)
        throws NoSuchPlanTemplateException, SystemException {
        PlanTemplate planTemplate = fetchByPrimaryKey(id);

        if (planTemplate == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanTemplate exists with the primary key " + id);
            }

            throw new NoSuchPlanTemplateException(
                "No PlanTemplate exists with the primary key " + id);
        }

        return planTemplate;
    }

    public PlanTemplate fetchByPrimaryKey(Long id) throws SystemException {
        PlanTemplate planTemplate = (PlanTemplate) EntityCacheUtil.getResult(PlanTemplateModelImpl.ENTITY_CACHE_ENABLED,
                PlanTemplateImpl.class, id, this);

        if (planTemplate == null) {
            Session session = null;

            try {
                session = openSession();

                planTemplate = (PlanTemplate) session.get(PlanTemplateImpl.class,
                        id);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planTemplate != null) {
                    cacheResult(planTemplate);
                }

                closeSession(session);
            }
        }

        return planTemplate;
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

    public List<PlanTemplate> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanTemplate> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanTemplate> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanTemplate> list = (List<PlanTemplate>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanTemplate ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlanTemplate>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanTemplate>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanTemplate>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (PlanTemplate planTemplate : findAll()) {
            remove(planTemplate);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanTemplate");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanTemplate")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanTemplate>> listenersList = new ArrayList<ModelListener<PlanTemplate>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanTemplate>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
