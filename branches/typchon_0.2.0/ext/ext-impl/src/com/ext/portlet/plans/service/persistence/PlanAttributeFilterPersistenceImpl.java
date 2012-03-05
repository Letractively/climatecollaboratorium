package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanAttributeFilterException;
import com.ext.portlet.plans.model.PlanAttributeFilter;
import com.ext.portlet.plans.model.impl.PlanAttributeFilterImpl;
import com.ext.portlet.plans.model.impl.PlanAttributeFilterModelImpl;

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


public class PlanAttributeFilterPersistenceImpl extends BasePersistenceImpl
    implements PlanAttributeFilterPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanAttributeFilterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDATTRIBUTENAME =
        new FinderPath(PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeFilterModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByPlanUserSettingsIdAttributeName",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDATTRIBUTENAME =
        new FinderPath(PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeFilterModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByPlanUserSettingsIdAttributeName",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeFilterModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeFilterModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanAttributeFilterPersistenceImpl.class);
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

    public void cacheResult(PlanAttributeFilter planAttributeFilter) {
        EntityCacheUtil.putResult(PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeFilterImpl.class, planAttributeFilter.getPrimaryKey(),
            planAttributeFilter);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
            new Object[] {
                planAttributeFilter.getPlanUserSettingsId(),
                
            planAttributeFilter.getAttributeName()
            }, planAttributeFilter);
    }

    public void cacheResult(List<PlanAttributeFilter> planAttributeFilters) {
        for (PlanAttributeFilter planAttributeFilter : planAttributeFilters) {
            if (EntityCacheUtil.getResult(
                        PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
                        PlanAttributeFilterImpl.class,
                        planAttributeFilter.getPrimaryKey(), this) == null) {
                cacheResult(planAttributeFilter);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanAttributeFilterImpl.class.getName());
        EntityCacheUtil.clearCache(PlanAttributeFilterImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanAttributeFilter create(Long planAttributeFilterId) {
        PlanAttributeFilter planAttributeFilter = new PlanAttributeFilterImpl();

        planAttributeFilter.setNew(true);
        planAttributeFilter.setPrimaryKey(planAttributeFilterId);

        return planAttributeFilter;
    }

    public PlanAttributeFilter remove(Long planAttributeFilterId)
        throws NoSuchPlanAttributeFilterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanAttributeFilter planAttributeFilter = (PlanAttributeFilter) session.get(PlanAttributeFilterImpl.class,
                    planAttributeFilterId);

            if (planAttributeFilter == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No PlanAttributeFilter exists with the primary key " +
                        planAttributeFilterId);
                }

                throw new NoSuchPlanAttributeFilterException(
                    "No PlanAttributeFilter exists with the primary key " +
                    planAttributeFilterId);
            }

            return remove(planAttributeFilter);
        } catch (NoSuchPlanAttributeFilterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanAttributeFilter remove(PlanAttributeFilter planAttributeFilter)
        throws SystemException {
        for (ModelListener<PlanAttributeFilter> listener : listeners) {
            listener.onBeforeRemove(planAttributeFilter);
        }

        planAttributeFilter = removeImpl(planAttributeFilter);

        for (ModelListener<PlanAttributeFilter> listener : listeners) {
            listener.onAfterRemove(planAttributeFilter);
        }

        return planAttributeFilter;
    }

    protected PlanAttributeFilter removeImpl(
        PlanAttributeFilter planAttributeFilter) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planAttributeFilter.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanAttributeFilterImpl.class,
                        planAttributeFilter.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planAttributeFilter);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        PlanAttributeFilterModelImpl planAttributeFilterModelImpl = (PlanAttributeFilterModelImpl) planAttributeFilter;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
            new Object[] {
                planAttributeFilterModelImpl.getOriginalPlanUserSettingsId(),
                
            planAttributeFilterModelImpl.getOriginalAttributeName()
            });

        EntityCacheUtil.removeResult(PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeFilterImpl.class, planAttributeFilter.getPrimaryKey());

        return planAttributeFilter;
    }

    /**
     * @deprecated Use <code>update(PlanAttributeFilter planAttributeFilter, boolean merge)</code>.
     */
    public PlanAttributeFilter update(PlanAttributeFilter planAttributeFilter)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanAttributeFilter planAttributeFilter) method. Use update(PlanAttributeFilter planAttributeFilter, boolean merge) instead.");
        }

        return update(planAttributeFilter, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planAttributeFilter the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planAttributeFilter is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanAttributeFilter update(PlanAttributeFilter planAttributeFilter,
        boolean merge) throws SystemException {
        boolean isNew = planAttributeFilter.isNew();

        for (ModelListener<PlanAttributeFilter> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planAttributeFilter);
            } else {
                listener.onBeforeUpdate(planAttributeFilter);
            }
        }

        planAttributeFilter = updateImpl(planAttributeFilter, merge);

        for (ModelListener<PlanAttributeFilter> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planAttributeFilter);
            } else {
                listener.onAfterUpdate(planAttributeFilter);
            }
        }

        return planAttributeFilter;
    }

    public PlanAttributeFilter updateImpl(
        com.ext.portlet.plans.model.PlanAttributeFilter planAttributeFilter,
        boolean merge) throws SystemException {
        boolean isNew = planAttributeFilter.isNew();

        PlanAttributeFilterModelImpl planAttributeFilterModelImpl = (PlanAttributeFilterModelImpl) planAttributeFilter;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planAttributeFilter, merge);

            planAttributeFilter.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeFilterImpl.class, planAttributeFilter.getPrimaryKey(),
            planAttributeFilter);

        if (!isNew &&
                (!Validator.equals(
                    planAttributeFilter.getPlanUserSettingsId(),
                    planAttributeFilterModelImpl.getOriginalPlanUserSettingsId()) ||
                !Validator.equals(planAttributeFilter.getAttributeName(),
                    planAttributeFilterModelImpl.getOriginalAttributeName()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
                new Object[] {
                    planAttributeFilterModelImpl.getOriginalPlanUserSettingsId(),
                    
                planAttributeFilterModelImpl.getOriginalAttributeName()
                });
        }

        if (isNew ||
                (!Validator.equals(
                    planAttributeFilter.getPlanUserSettingsId(),
                    planAttributeFilterModelImpl.getOriginalPlanUserSettingsId()) ||
                !Validator.equals(planAttributeFilter.getAttributeName(),
                    planAttributeFilterModelImpl.getOriginalAttributeName()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
                new Object[] {
                    planAttributeFilter.getPlanUserSettingsId(),
                    
                planAttributeFilter.getAttributeName()
                }, planAttributeFilter);
        }

        return planAttributeFilter;
    }

    public PlanAttributeFilter findByPrimaryKey(Long planAttributeFilterId)
        throws NoSuchPlanAttributeFilterException, SystemException {
        PlanAttributeFilter planAttributeFilter = fetchByPrimaryKey(planAttributeFilterId);

        if (planAttributeFilter == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanAttributeFilter exists with the primary key " +
                    planAttributeFilterId);
            }

            throw new NoSuchPlanAttributeFilterException(
                "No PlanAttributeFilter exists with the primary key " +
                planAttributeFilterId);
        }

        return planAttributeFilter;
    }

    public PlanAttributeFilter fetchByPrimaryKey(Long planAttributeFilterId)
        throws SystemException {
        PlanAttributeFilter planAttributeFilter = (PlanAttributeFilter) EntityCacheUtil.getResult(PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
                PlanAttributeFilterImpl.class, planAttributeFilterId, this);

        if (planAttributeFilter == null) {
            Session session = null;

            try {
                session = openSession();

                planAttributeFilter = (PlanAttributeFilter) session.get(PlanAttributeFilterImpl.class,
                        planAttributeFilterId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planAttributeFilter != null) {
                    cacheResult(planAttributeFilter);
                }

                closeSession(session);
            }
        }

        return planAttributeFilter;
    }

    public PlanAttributeFilter findByPlanUserSettingsIdAttributeName(
        Long planUserSettingsId, String attributeName)
        throws NoSuchPlanAttributeFilterException, SystemException {
        PlanAttributeFilter planAttributeFilter = fetchByPlanUserSettingsIdAttributeName(planUserSettingsId,
                attributeName);

        if (planAttributeFilter == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanAttributeFilter exists with the key {");

            msg.append("planUserSettingsId=" + planUserSettingsId);

            msg.append(", ");
            msg.append("attributeName=" + attributeName);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanAttributeFilterException(msg.toString());
        }

        return planAttributeFilter;
    }

    public PlanAttributeFilter fetchByPlanUserSettingsIdAttributeName(
        Long planUserSettingsId, String attributeName)
        throws SystemException {
        return fetchByPlanUserSettingsIdAttributeName(planUserSettingsId,
            attributeName, true);
    }

    public PlanAttributeFilter fetchByPlanUserSettingsIdAttributeName(
        Long planUserSettingsId, String attributeName, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { planUserSettingsId, attributeName };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanAttributeFilter WHERE ");

                if (planUserSettingsId == null) {
                    query.append("planUserSettingsId IS NULL");
                } else {
                    query.append("planUserSettingsId = ?");
                }

                query.append(" AND ");

                if (attributeName == null) {
                    query.append("attributeName IS NULL");
                } else {
                    query.append("attributeName = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planUserSettingsId != null) {
                    qPos.add(planUserSettingsId.longValue());
                }

                if (attributeName != null) {
                    qPos.add(attributeName);
                }

                List<PlanAttributeFilter> list = q.list();

                result = list;

                PlanAttributeFilter planAttributeFilter = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
                        finderArgs, list);
                } else {
                    planAttributeFilter = list.get(0);

                    cacheResult(planAttributeFilter);

                    if ((planAttributeFilter.getPlanUserSettingsId() == null) ||
                            !planAttributeFilter.getPlanUserSettingsId()
                                                    .equals(planUserSettingsId) ||
                            (planAttributeFilter.getAttributeName() == null) ||
                            !planAttributeFilter.getAttributeName()
                                                    .equals(attributeName)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
                            finderArgs, planAttributeFilter);
                    }
                }

                return planAttributeFilter;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
                        finderArgs, new ArrayList<PlanAttributeFilter>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (PlanAttributeFilter) result;
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

    public List<PlanAttributeFilter> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanAttributeFilter> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanAttributeFilter> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanAttributeFilter> list = (List<PlanAttributeFilter>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanAttributeFilter ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlanAttributeFilter>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanAttributeFilter>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanAttributeFilter>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByPlanUserSettingsIdAttributeName(
        Long planUserSettingsId, String attributeName)
        throws NoSuchPlanAttributeFilterException, SystemException {
        PlanAttributeFilter planAttributeFilter = findByPlanUserSettingsIdAttributeName(planUserSettingsId,
                attributeName);

        remove(planAttributeFilter);
    }

    public void removeAll() throws SystemException {
        for (PlanAttributeFilter planAttributeFilter : findAll()) {
            remove(planAttributeFilter);
        }
    }

    public int countByPlanUserSettingsIdAttributeName(Long planUserSettingsId,
        String attributeName) throws SystemException {
        Object[] finderArgs = new Object[] { planUserSettingsId, attributeName };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.plans.model.PlanAttributeFilter WHERE ");

                if (planUserSettingsId == null) {
                    query.append("planUserSettingsId IS NULL");
                } else {
                    query.append("planUserSettingsId = ?");
                }

                query.append(" AND ");

                if (attributeName == null) {
                    query.append("attributeName IS NULL");
                } else {
                    query.append("attributeName = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planUserSettingsId != null) {
                    qPos.add(planUserSettingsId.longValue());
                }

                if (attributeName != null) {
                    qPos.add(attributeName);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanAttributeFilter");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanAttributeFilter")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanAttributeFilter>> listenersList = new ArrayList<ModelListener<PlanAttributeFilter>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanAttributeFilter>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
