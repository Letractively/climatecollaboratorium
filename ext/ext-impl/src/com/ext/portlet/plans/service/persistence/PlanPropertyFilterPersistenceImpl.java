package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanPropertyFilterException;
import com.ext.portlet.plans.model.PlanPropertyFilter;
import com.ext.portlet.plans.model.impl.PlanPropertyFilterImpl;
import com.ext.portlet.plans.model.impl.PlanPropertyFilterModelImpl;

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


public class PlanPropertyFilterPersistenceImpl extends BasePersistenceImpl
    implements PlanPropertyFilterPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanPropertyFilterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDPROPERTYNAME =
        new FinderPath(PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanPropertyFilterModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByPlanUserSettingsIdPropertyName",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDPROPERTYNAME =
        new FinderPath(PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanPropertyFilterModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByPlanUserSettingsIdPropertyName",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanPropertyFilterModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanPropertyFilterModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanPropertyFilterPersistenceImpl.class);
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

    public void cacheResult(PlanPropertyFilter planPropertyFilter) {
        EntityCacheUtil.putResult(PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanPropertyFilterImpl.class, planPropertyFilter.getPrimaryKey(),
            planPropertyFilter);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDPROPERTYNAME,
            new Object[] {
                planPropertyFilter.getPlanUserSettingsId(),
                
            planPropertyFilter.getPropertyName()
            }, planPropertyFilter);
    }

    public void cacheResult(List<PlanPropertyFilter> planPropertyFilters) {
        for (PlanPropertyFilter planPropertyFilter : planPropertyFilters) {
            if (EntityCacheUtil.getResult(
                        PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
                        PlanPropertyFilterImpl.class,
                        planPropertyFilter.getPrimaryKey(), this) == null) {
                cacheResult(planPropertyFilter);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanPropertyFilterImpl.class.getName());
        EntityCacheUtil.clearCache(PlanPropertyFilterImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanPropertyFilter create(Long planPropertyFilterId) {
        PlanPropertyFilter planPropertyFilter = new PlanPropertyFilterImpl();

        planPropertyFilter.setNew(true);
        planPropertyFilter.setPrimaryKey(planPropertyFilterId);

        return planPropertyFilter;
    }

    public PlanPropertyFilter remove(Long planPropertyFilterId)
        throws NoSuchPlanPropertyFilterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanPropertyFilter planPropertyFilter = (PlanPropertyFilter) session.get(PlanPropertyFilterImpl.class,
                    planPropertyFilterId);

            if (planPropertyFilter == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No PlanPropertyFilter exists with the primary key " +
                        planPropertyFilterId);
                }

                throw new NoSuchPlanPropertyFilterException(
                    "No PlanPropertyFilter exists with the primary key " +
                    planPropertyFilterId);
            }

            return remove(planPropertyFilter);
        } catch (NoSuchPlanPropertyFilterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanPropertyFilter remove(PlanPropertyFilter planPropertyFilter)
        throws SystemException {
        for (ModelListener<PlanPropertyFilter> listener : listeners) {
            listener.onBeforeRemove(planPropertyFilter);
        }

        planPropertyFilter = removeImpl(planPropertyFilter);

        for (ModelListener<PlanPropertyFilter> listener : listeners) {
            listener.onAfterRemove(planPropertyFilter);
        }

        return planPropertyFilter;
    }

    protected PlanPropertyFilter removeImpl(
        PlanPropertyFilter planPropertyFilter) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planPropertyFilter.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanPropertyFilterImpl.class,
                        planPropertyFilter.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planPropertyFilter);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        PlanPropertyFilterModelImpl planPropertyFilterModelImpl = (PlanPropertyFilterModelImpl) planPropertyFilter;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDPROPERTYNAME,
            new Object[] {
                planPropertyFilterModelImpl.getOriginalPlanUserSettingsId(),
                
            planPropertyFilterModelImpl.getOriginalPropertyName()
            });

        EntityCacheUtil.removeResult(PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanPropertyFilterImpl.class, planPropertyFilter.getPrimaryKey());

        return planPropertyFilter;
    }

    /**
     * @deprecated Use <code>update(PlanPropertyFilter planPropertyFilter, boolean merge)</code>.
     */
    public PlanPropertyFilter update(PlanPropertyFilter planPropertyFilter)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanPropertyFilter planPropertyFilter) method. Use update(PlanPropertyFilter planPropertyFilter, boolean merge) instead.");
        }

        return update(planPropertyFilter, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planPropertyFilter the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planPropertyFilter is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanPropertyFilter update(PlanPropertyFilter planPropertyFilter,
        boolean merge) throws SystemException {
        boolean isNew = planPropertyFilter.isNew();

        for (ModelListener<PlanPropertyFilter> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planPropertyFilter);
            } else {
                listener.onBeforeUpdate(planPropertyFilter);
            }
        }

        planPropertyFilter = updateImpl(planPropertyFilter, merge);

        for (ModelListener<PlanPropertyFilter> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planPropertyFilter);
            } else {
                listener.onAfterUpdate(planPropertyFilter);
            }
        }

        return planPropertyFilter;
    }

    public PlanPropertyFilter updateImpl(
        com.ext.portlet.plans.model.PlanPropertyFilter planPropertyFilter,
        boolean merge) throws SystemException {
        boolean isNew = planPropertyFilter.isNew();

        PlanPropertyFilterModelImpl planPropertyFilterModelImpl = (PlanPropertyFilterModelImpl) planPropertyFilter;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planPropertyFilter, merge);

            planPropertyFilter.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanPropertyFilterImpl.class, planPropertyFilter.getPrimaryKey(),
            planPropertyFilter);

        if (!isNew &&
                (!Validator.equals(planPropertyFilter.getPlanUserSettingsId(),
                    planPropertyFilterModelImpl.getOriginalPlanUserSettingsId()) ||
                !Validator.equals(planPropertyFilter.getPropertyName(),
                    planPropertyFilterModelImpl.getOriginalPropertyName()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDPROPERTYNAME,
                new Object[] {
                    planPropertyFilterModelImpl.getOriginalPlanUserSettingsId(),
                    
                planPropertyFilterModelImpl.getOriginalPropertyName()
                });
        }

        if (isNew ||
                (!Validator.equals(planPropertyFilter.getPlanUserSettingsId(),
                    planPropertyFilterModelImpl.getOriginalPlanUserSettingsId()) ||
                !Validator.equals(planPropertyFilter.getPropertyName(),
                    planPropertyFilterModelImpl.getOriginalPropertyName()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDPROPERTYNAME,
                new Object[] {
                    planPropertyFilter.getPlanUserSettingsId(),
                    
                planPropertyFilter.getPropertyName()
                }, planPropertyFilter);
        }

        return planPropertyFilter;
    }

    public PlanPropertyFilter findByPrimaryKey(Long planPropertyFilterId)
        throws NoSuchPlanPropertyFilterException, SystemException {
        PlanPropertyFilter planPropertyFilter = fetchByPrimaryKey(planPropertyFilterId);

        if (planPropertyFilter == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanPropertyFilter exists with the primary key " +
                    planPropertyFilterId);
            }

            throw new NoSuchPlanPropertyFilterException(
                "No PlanPropertyFilter exists with the primary key " +
                planPropertyFilterId);
        }

        return planPropertyFilter;
    }

    public PlanPropertyFilter fetchByPrimaryKey(Long planPropertyFilterId)
        throws SystemException {
        PlanPropertyFilter planPropertyFilter = (PlanPropertyFilter) EntityCacheUtil.getResult(PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
                PlanPropertyFilterImpl.class, planPropertyFilterId, this);

        if (planPropertyFilter == null) {
            Session session = null;

            try {
                session = openSession();

                planPropertyFilter = (PlanPropertyFilter) session.get(PlanPropertyFilterImpl.class,
                        planPropertyFilterId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planPropertyFilter != null) {
                    cacheResult(planPropertyFilter);
                }

                closeSession(session);
            }
        }

        return planPropertyFilter;
    }

    public PlanPropertyFilter findByPlanUserSettingsIdPropertyName(
        Long planUserSettingsId, String propertyName)
        throws NoSuchPlanPropertyFilterException, SystemException {
        PlanPropertyFilter planPropertyFilter = fetchByPlanUserSettingsIdPropertyName(planUserSettingsId,
                propertyName);

        if (planPropertyFilter == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanPropertyFilter exists with the key {");

            msg.append("planUserSettingsId=" + planUserSettingsId);

            msg.append(", ");
            msg.append("propertyName=" + propertyName);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanPropertyFilterException(msg.toString());
        }

        return planPropertyFilter;
    }

    public PlanPropertyFilter fetchByPlanUserSettingsIdPropertyName(
        Long planUserSettingsId, String propertyName) throws SystemException {
        return fetchByPlanUserSettingsIdPropertyName(planUserSettingsId,
            propertyName, true);
    }

    public PlanPropertyFilter fetchByPlanUserSettingsIdPropertyName(
        Long planUserSettingsId, String propertyName, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { planUserSettingsId, propertyName };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDPROPERTYNAME,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanPropertyFilter WHERE ");

                if (planUserSettingsId == null) {
                    query.append("planUserSettingsId IS NULL");
                } else {
                    query.append("planUserSettingsId = ?");
                }

                query.append(" AND ");

                if (propertyName == null) {
                    query.append("propertyName IS NULL");
                } else {
                    query.append("propertyName = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planUserSettingsId != null) {
                    qPos.add(planUserSettingsId.longValue());
                }

                if (propertyName != null) {
                    qPos.add(propertyName);
                }

                List<PlanPropertyFilter> list = q.list();

                result = list;

                PlanPropertyFilter planPropertyFilter = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDPROPERTYNAME,
                        finderArgs, list);
                } else {
                    planPropertyFilter = list.get(0);

                    cacheResult(planPropertyFilter);

                    if ((planPropertyFilter.getPlanUserSettingsId() == null) ||
                            !planPropertyFilter.getPlanUserSettingsId()
                                                   .equals(planUserSettingsId) ||
                            (planPropertyFilter.getPropertyName() == null) ||
                            !planPropertyFilter.getPropertyName()
                                                   .equals(propertyName)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDPROPERTYNAME,
                            finderArgs, planPropertyFilter);
                    }
                }

                return planPropertyFilter;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDPROPERTYNAME,
                        finderArgs, new ArrayList<PlanPropertyFilter>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (PlanPropertyFilter) result;
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

    public List<PlanPropertyFilter> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanPropertyFilter> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanPropertyFilter> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanPropertyFilter> list = (List<PlanPropertyFilter>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanPropertyFilter ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlanPropertyFilter>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanPropertyFilter>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanPropertyFilter>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByPlanUserSettingsIdPropertyName(
        Long planUserSettingsId, String propertyName)
        throws NoSuchPlanPropertyFilterException, SystemException {
        PlanPropertyFilter planPropertyFilter = findByPlanUserSettingsIdPropertyName(planUserSettingsId,
                propertyName);

        remove(planPropertyFilter);
    }

    public void removeAll() throws SystemException {
        for (PlanPropertyFilter planPropertyFilter : findAll()) {
            remove(planPropertyFilter);
        }
    }

    public int countByPlanUserSettingsIdPropertyName(Long planUserSettingsId,
        String propertyName) throws SystemException {
        Object[] finderArgs = new Object[] { planUserSettingsId, propertyName };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDPROPERTYNAME,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.plans.model.PlanPropertyFilter WHERE ");

                if (planUserSettingsId == null) {
                    query.append("planUserSettingsId IS NULL");
                } else {
                    query.append("planUserSettingsId = ?");
                }

                query.append(" AND ");

                if (propertyName == null) {
                    query.append("propertyName IS NULL");
                } else {
                    query.append("propertyName = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planUserSettingsId != null) {
                    qPos.add(planUserSettingsId.longValue());
                }

                if (propertyName != null) {
                    qPos.add(propertyName);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDPROPERTYNAME,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanPropertyFilter");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanPropertyFilter")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanPropertyFilter>> listenersList = new ArrayList<ModelListener<PlanPropertyFilter>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanPropertyFilter>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
