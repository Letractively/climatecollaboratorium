package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanTemplateSectionException;
import com.ext.portlet.plans.model.PlanTemplateSection;
import com.ext.portlet.plans.model.impl.PlanTemplateSectionImpl;
import com.ext.portlet.plans.model.impl.PlanTemplateSectionModelImpl;

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


public class PlanTemplateSectionPersistenceImpl extends BasePersistenceImpl
    implements PlanTemplateSectionPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanTemplateSectionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanTemplateSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanTemplateSectionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanTemplateSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanTemplateSectionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanTemplateSectionPersistenceImpl.class);
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

    public void cacheResult(PlanTemplateSection planTemplateSection) {
        EntityCacheUtil.putResult(PlanTemplateSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanTemplateSectionImpl.class, planTemplateSection.getPrimaryKey(),
            planTemplateSection);
    }

    public void cacheResult(List<PlanTemplateSection> planTemplateSections) {
        for (PlanTemplateSection planTemplateSection : planTemplateSections) {
            if (EntityCacheUtil.getResult(
                        PlanTemplateSectionModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTemplateSectionImpl.class,
                        planTemplateSection.getPrimaryKey(), this) == null) {
                cacheResult(planTemplateSection);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanTemplateSectionImpl.class.getName());
        EntityCacheUtil.clearCache(PlanTemplateSectionImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanTemplateSection create(
        PlanTemplateSectionPK planTemplateSectionPK) {
        PlanTemplateSection planTemplateSection = new PlanTemplateSectionImpl();

        planTemplateSection.setNew(true);
        planTemplateSection.setPrimaryKey(planTemplateSectionPK);

        return planTemplateSection;
    }

    public PlanTemplateSection remove(
        PlanTemplateSectionPK planTemplateSectionPK)
        throws NoSuchPlanTemplateSectionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanTemplateSection planTemplateSection = (PlanTemplateSection) session.get(PlanTemplateSectionImpl.class,
                    planTemplateSectionPK);

            if (planTemplateSection == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No PlanTemplateSection exists with the primary key " +
                        planTemplateSectionPK);
                }

                throw new NoSuchPlanTemplateSectionException(
                    "No PlanTemplateSection exists with the primary key " +
                    planTemplateSectionPK);
            }

            return remove(planTemplateSection);
        } catch (NoSuchPlanTemplateSectionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanTemplateSection remove(PlanTemplateSection planTemplateSection)
        throws SystemException {
        for (ModelListener<PlanTemplateSection> listener : listeners) {
            listener.onBeforeRemove(planTemplateSection);
        }

        planTemplateSection = removeImpl(planTemplateSection);

        for (ModelListener<PlanTemplateSection> listener : listeners) {
            listener.onAfterRemove(planTemplateSection);
        }

        return planTemplateSection;
    }

    protected PlanTemplateSection removeImpl(
        PlanTemplateSection planTemplateSection) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planTemplateSection.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanTemplateSectionImpl.class,
                        planTemplateSection.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planTemplateSection);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(PlanTemplateSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanTemplateSectionImpl.class, planTemplateSection.getPrimaryKey());

        return planTemplateSection;
    }

    /**
     * @deprecated Use <code>update(PlanTemplateSection planTemplateSection, boolean merge)</code>.
     */
    public PlanTemplateSection update(PlanTemplateSection planTemplateSection)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanTemplateSection planTemplateSection) method. Use update(PlanTemplateSection planTemplateSection, boolean merge) instead.");
        }

        return update(planTemplateSection, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planTemplateSection the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planTemplateSection is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanTemplateSection update(PlanTemplateSection planTemplateSection,
        boolean merge) throws SystemException {
        boolean isNew = planTemplateSection.isNew();

        for (ModelListener<PlanTemplateSection> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planTemplateSection);
            } else {
                listener.onBeforeUpdate(planTemplateSection);
            }
        }

        planTemplateSection = updateImpl(planTemplateSection, merge);

        for (ModelListener<PlanTemplateSection> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planTemplateSection);
            } else {
                listener.onAfterUpdate(planTemplateSection);
            }
        }

        return planTemplateSection;
    }

    public PlanTemplateSection updateImpl(
        com.ext.portlet.plans.model.PlanTemplateSection planTemplateSection,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planTemplateSection, merge);

            planTemplateSection.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanTemplateSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanTemplateSectionImpl.class, planTemplateSection.getPrimaryKey(),
            planTemplateSection);

        return planTemplateSection;
    }

    public PlanTemplateSection findByPrimaryKey(
        PlanTemplateSectionPK planTemplateSectionPK)
        throws NoSuchPlanTemplateSectionException, SystemException {
        PlanTemplateSection planTemplateSection = fetchByPrimaryKey(planTemplateSectionPK);

        if (planTemplateSection == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanTemplateSection exists with the primary key " +
                    planTemplateSectionPK);
            }

            throw new NoSuchPlanTemplateSectionException(
                "No PlanTemplateSection exists with the primary key " +
                planTemplateSectionPK);
        }

        return planTemplateSection;
    }

    public PlanTemplateSection fetchByPrimaryKey(
        PlanTemplateSectionPK planTemplateSectionPK) throws SystemException {
        PlanTemplateSection planTemplateSection = (PlanTemplateSection) EntityCacheUtil.getResult(PlanTemplateSectionModelImpl.ENTITY_CACHE_ENABLED,
                PlanTemplateSectionImpl.class, planTemplateSectionPK, this);

        if (planTemplateSection == null) {
            Session session = null;

            try {
                session = openSession();

                planTemplateSection = (PlanTemplateSection) session.get(PlanTemplateSectionImpl.class,
                        planTemplateSectionPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planTemplateSection != null) {
                    cacheResult(planTemplateSection);
                }

                closeSession(session);
            }
        }

        return planTemplateSection;
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

    public List<PlanTemplateSection> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanTemplateSection> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanTemplateSection> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanTemplateSection> list = (List<PlanTemplateSection>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanTemplateSection ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlanTemplateSection>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanTemplateSection>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanTemplateSection>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (PlanTemplateSection planTemplateSection : findAll()) {
            remove(planTemplateSection);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanTemplateSection");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanTemplateSection")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanTemplateSection>> listenersList = new ArrayList<ModelListener<PlanTemplateSection>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanTemplateSection>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
