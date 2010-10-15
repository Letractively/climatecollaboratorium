package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchUserSettingsException;
import com.ext.portlet.plans.model.PlansUserSettings;
import com.ext.portlet.plans.model.impl.PlansUserSettingsImpl;
import com.ext.portlet.plans.model.impl.PlansUserSettingsModelImpl;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
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

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PlansUserSettingsPersistenceImpl extends BasePersistenceImpl
    implements PlansUserSettingsPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlansUserSettingsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_USERIDPLANTYPEID = new FinderPath(PlansUserSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlansUserSettingsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByuserIdPlanTypeId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_USERIDPLANTYPEID = new FinderPath(PlansUserSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlansUserSettingsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByuserIdPlanTypeId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlansUserSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlansUserSettingsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlansUserSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlansUserSettingsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_GET_PLANATTRIBUTEFILTERS = new FinderPath(com.ext.portlet.plans.model.impl.PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanAttributeFilterModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.service.persistence.PlanAttributeFilterPersistenceImpl.FINDER_CLASS_NAME_LIST,
            "getPlanAttributeFilters",
            new String[] {
                Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_GET_PLANATTRIBUTEFILTERS_SIZE = new FinderPath(com.ext.portlet.plans.model.impl.PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanAttributeFilterModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.service.persistence.PlanAttributeFilterPersistenceImpl.FINDER_CLASS_NAME_LIST,
            "getPlanAttributeFiltersSize", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_CONTAINS_PLANATTRIBUTEFILTER = new FinderPath(com.ext.portlet.plans.model.impl.PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanAttributeFilterModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.service.persistence.PlanAttributeFilterPersistenceImpl.FINDER_CLASS_NAME_LIST,
            "containsPlanAttributeFilter",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_GET_PLANCOLUMNSETTINGSES = new FinderPath(com.ext.portlet.plans.model.impl.PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanColumnSettingsModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.service.persistence.PlanColumnSettingsPersistenceImpl.FINDER_CLASS_NAME_LIST,
            "getPlanColumnSettingses",
            new String[] {
                Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_GET_PLANCOLUMNSETTINGSES_SIZE = new FinderPath(com.ext.portlet.plans.model.impl.PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanColumnSettingsModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.service.persistence.PlanColumnSettingsPersistenceImpl.FINDER_CLASS_NAME_LIST,
            "getPlanColumnSettingsesSize", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_CONTAINS_PLANCOLUMNSETTINGS = new FinderPath(com.ext.portlet.plans.model.impl.PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanColumnSettingsModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.service.persistence.PlanColumnSettingsPersistenceImpl.FINDER_CLASS_NAME_LIST,
            "containsPlanColumnSettings",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _SQL_GETPLANATTRIBUTEFILTERS = "SELECT {PlanAttributeFilter.*} FROM PlanAttributeFilter INNER JOIN PlansUserSettings ON (PlansUserSettings.planUserSettingsId = PlanAttributeFilter.planUserSettingsId) WHERE (PlansUserSettings.planUserSettingsId = ?)";
    private static final String _SQL_GETPLANATTRIBUTEFILTERSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM PlanAttributeFilter WHERE planUserSettingsId = ?";
    private static final String _SQL_CONTAINSPLANATTRIBUTEFILTER = "SELECT COUNT(*) AS COUNT_VALUE FROM PlanAttributeFilter WHERE planUserSettingsId = ? AND planAttributeFilterId = ?";
    private static final String _SQL_GETPLANCOLUMNSETTINGSES = "SELECT {PlanColumnSettings.*} FROM PlanColumnSettings INNER JOIN PlansUserSettings ON (PlansUserSettings.planUserSettingsId = PlanColumnSettings.planUserSettingsId) WHERE (PlansUserSettings.planUserSettingsId = ?)";
    private static final String _SQL_GETPLANCOLUMNSETTINGSESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM PlanColumnSettings WHERE planUserSettingsId = ?";
    private static final String _SQL_CONTAINSPLANCOLUMNSETTINGS = "SELECT COUNT(*) AS COUNT_VALUE FROM PlanColumnSettings WHERE planUserSettingsId = ? AND planColumnSettingsId = ?";
    private static Log _log = LogFactoryUtil.getLog(PlansUserSettingsPersistenceImpl.class);
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
    protected ContainsPlanAttributeFilter containsPlanAttributeFilter;
    protected ContainsPlanColumnSettings containsPlanColumnSettings;

    public void cacheResult(PlansUserSettings plansUserSettings) {
        EntityCacheUtil.putResult(PlansUserSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlansUserSettingsImpl.class, plansUserSettings.getPrimaryKey(),
            plansUserSettings);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERIDPLANTYPEID,
            new Object[] {
                plansUserSettings.getUserId(),
                
            plansUserSettings.getPlanTypeId()
            }, plansUserSettings);
    }

    public void cacheResult(List<PlansUserSettings> plansUserSettingses) {
        for (PlansUserSettings plansUserSettings : plansUserSettingses) {
            if (EntityCacheUtil.getResult(
                        PlansUserSettingsModelImpl.ENTITY_CACHE_ENABLED,
                        PlansUserSettingsImpl.class,
                        plansUserSettings.getPrimaryKey(), this) == null) {
                cacheResult(plansUserSettings);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlansUserSettingsImpl.class.getName());
        EntityCacheUtil.clearCache(PlansUserSettingsImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlansUserSettings create(Long planUserSettingsId) {
        PlansUserSettings plansUserSettings = new PlansUserSettingsImpl();

        plansUserSettings.setNew(true);
        plansUserSettings.setPrimaryKey(planUserSettingsId);

        return plansUserSettings;
    }

    public PlansUserSettings remove(Long planUserSettingsId)
        throws NoSuchUserSettingsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlansUserSettings plansUserSettings = (PlansUserSettings) session.get(PlansUserSettingsImpl.class,
                    planUserSettingsId);

            if (plansUserSettings == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No PlansUserSettings exists with the primary key " +
                        planUserSettingsId);
                }

                throw new NoSuchUserSettingsException(
                    "No PlansUserSettings exists with the primary key " +
                    planUserSettingsId);
            }

            return remove(plansUserSettings);
        } catch (NoSuchUserSettingsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlansUserSettings remove(PlansUserSettings plansUserSettings)
        throws SystemException {
        for (ModelListener<PlansUserSettings> listener : listeners) {
            listener.onBeforeRemove(plansUserSettings);
        }

        plansUserSettings = removeImpl(plansUserSettings);

        for (ModelListener<PlansUserSettings> listener : listeners) {
            listener.onAfterRemove(plansUserSettings);
        }

        return plansUserSettings;
    }

    protected PlansUserSettings removeImpl(PlansUserSettings plansUserSettings)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (plansUserSettings.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlansUserSettingsImpl.class,
                        plansUserSettings.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(plansUserSettings);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        PlansUserSettingsModelImpl plansUserSettingsModelImpl = (PlansUserSettingsModelImpl) plansUserSettings;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERIDPLANTYPEID,
            new Object[] {
                plansUserSettingsModelImpl.getOriginalUserId(),
                
            plansUserSettingsModelImpl.getOriginalPlanTypeId()
            });

        EntityCacheUtil.removeResult(PlansUserSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlansUserSettingsImpl.class, plansUserSettings.getPrimaryKey());

        return plansUserSettings;
    }

    /**
     * @deprecated Use <code>update(PlansUserSettings plansUserSettings, boolean merge)</code>.
     */
    public PlansUserSettings update(PlansUserSettings plansUserSettings)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlansUserSettings plansUserSettings) method. Use update(PlansUserSettings plansUserSettings, boolean merge) instead.");
        }

        return update(plansUserSettings, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                plansUserSettings the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when plansUserSettings is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlansUserSettings update(PlansUserSettings plansUserSettings,
        boolean merge) throws SystemException {
        boolean isNew = plansUserSettings.isNew();

        for (ModelListener<PlansUserSettings> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(plansUserSettings);
            } else {
                listener.onBeforeUpdate(plansUserSettings);
            }
        }

        plansUserSettings = updateImpl(plansUserSettings, merge);

        for (ModelListener<PlansUserSettings> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(plansUserSettings);
            } else {
                listener.onAfterUpdate(plansUserSettings);
            }
        }

        return plansUserSettings;
    }

    public PlansUserSettings updateImpl(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings,
        boolean merge) throws SystemException {
        boolean isNew = plansUserSettings.isNew();

        PlansUserSettingsModelImpl plansUserSettingsModelImpl = (PlansUserSettingsModelImpl) plansUserSettings;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, plansUserSettings, merge);

            plansUserSettings.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlansUserSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlansUserSettingsImpl.class, plansUserSettings.getPrimaryKey(),
            plansUserSettings);

        if (!isNew &&
                (!Validator.equals(plansUserSettings.getUserId(),
                    plansUserSettingsModelImpl.getOriginalUserId()) ||
                !Validator.equals(plansUserSettings.getPlanTypeId(),
                    plansUserSettingsModelImpl.getOriginalPlanTypeId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERIDPLANTYPEID,
                new Object[] {
                    plansUserSettingsModelImpl.getOriginalUserId(),
                    
                plansUserSettingsModelImpl.getOriginalPlanTypeId()
                });
        }

        if (isNew ||
                (!Validator.equals(plansUserSettings.getUserId(),
                    plansUserSettingsModelImpl.getOriginalUserId()) ||
                !Validator.equals(plansUserSettings.getPlanTypeId(),
                    plansUserSettingsModelImpl.getOriginalPlanTypeId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERIDPLANTYPEID,
                new Object[] {
                    plansUserSettings.getUserId(),
                    
                plansUserSettings.getPlanTypeId()
                }, plansUserSettings);
        }

        return plansUserSettings;
    }

    public PlansUserSettings findByPrimaryKey(Long planUserSettingsId)
        throws NoSuchUserSettingsException, SystemException {
        PlansUserSettings plansUserSettings = fetchByPrimaryKey(planUserSettingsId);

        if (plansUserSettings == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlansUserSettings exists with the primary key " +
                    planUserSettingsId);
            }

            throw new NoSuchUserSettingsException(
                "No PlansUserSettings exists with the primary key " +
                planUserSettingsId);
        }

        return plansUserSettings;
    }

    public PlansUserSettings fetchByPrimaryKey(Long planUserSettingsId)
        throws SystemException {
        PlansUserSettings plansUserSettings = (PlansUserSettings) EntityCacheUtil.getResult(PlansUserSettingsModelImpl.ENTITY_CACHE_ENABLED,
                PlansUserSettingsImpl.class, planUserSettingsId, this);

        if (plansUserSettings == null) {
            Session session = null;

            try {
                session = openSession();

                plansUserSettings = (PlansUserSettings) session.get(PlansUserSettingsImpl.class,
                        planUserSettingsId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (plansUserSettings != null) {
                    cacheResult(plansUserSettings);
                }

                closeSession(session);
            }
        }

        return plansUserSettings;
    }

    public PlansUserSettings findByuserIdPlanTypeId(Long userId, Long planTypeId)
        throws NoSuchUserSettingsException, SystemException {
        PlansUserSettings plansUserSettings = fetchByuserIdPlanTypeId(userId,
                planTypeId);

        if (plansUserSettings == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlansUserSettings exists with the key {");

            msg.append("userId=" + userId);

            msg.append(", ");
            msg.append("planTypeId=" + planTypeId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchUserSettingsException(msg.toString());
        }

        return plansUserSettings;
    }

    public PlansUserSettings fetchByuserIdPlanTypeId(Long userId,
        Long planTypeId) throws SystemException {
        return fetchByuserIdPlanTypeId(userId, planTypeId, true);
    }

    public PlansUserSettings fetchByuserIdPlanTypeId(Long userId,
        Long planTypeId, boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { userId, planTypeId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_USERIDPLANTYPEID,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlansUserSettings WHERE ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" AND ");

                if (planTypeId == null) {
                    query.append("planTypeId IS NULL");
                } else {
                    query.append("planTypeId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                if (planTypeId != null) {
                    qPos.add(planTypeId.longValue());
                }

                List<PlansUserSettings> list = q.list();

                result = list;

                PlansUserSettings plansUserSettings = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERIDPLANTYPEID,
                        finderArgs, list);
                } else {
                    plansUserSettings = list.get(0);

                    cacheResult(plansUserSettings);

                    if ((plansUserSettings.getUserId() == null) ||
                            !plansUserSettings.getUserId().equals(userId) ||
                            (plansUserSettings.getPlanTypeId() == null) ||
                            !plansUserSettings.getPlanTypeId().equals(planTypeId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERIDPLANTYPEID,
                            finderArgs, plansUserSettings);
                    }
                }

                return plansUserSettings;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERIDPLANTYPEID,
                        finderArgs, new ArrayList<PlansUserSettings>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (PlansUserSettings) result;
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

    public List<PlansUserSettings> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlansUserSettings> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlansUserSettings> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlansUserSettings> list = (List<PlansUserSettings>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlansUserSettings ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlansUserSettings>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlansUserSettings>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlansUserSettings>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByuserIdPlanTypeId(Long userId, Long planTypeId)
        throws NoSuchUserSettingsException, SystemException {
        PlansUserSettings plansUserSettings = findByuserIdPlanTypeId(userId,
                planTypeId);

        remove(plansUserSettings);
    }

    public void removeAll() throws SystemException {
        for (PlansUserSettings plansUserSettings : findAll()) {
            remove(plansUserSettings);
        }
    }

    public int countByuserIdPlanTypeId(Long userId, Long planTypeId)
        throws SystemException {
        Object[] finderArgs = new Object[] { userId, planTypeId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERIDPLANTYPEID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.plans.model.PlansUserSettings WHERE ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" AND ");

                if (planTypeId == null) {
                    query.append("planTypeId IS NULL");
                } else {
                    query.append("planTypeId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                if (planTypeId != null) {
                    qPos.add(planTypeId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERIDPLANTYPEID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlansUserSettings");

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

    public List<com.ext.portlet.plans.model.PlanAttributeFilter> getPlanAttributeFilters(
        Long pk) throws SystemException {
        return getPlanAttributeFilters(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    public List<com.ext.portlet.plans.model.PlanAttributeFilter> getPlanAttributeFilters(
        Long pk, int start, int end) throws SystemException {
        return getPlanAttributeFilters(pk, start, end, null);
    }

    public List<com.ext.portlet.plans.model.PlanAttributeFilter> getPlanAttributeFilters(
        Long pk, int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                pk, String.valueOf(start), String.valueOf(end),
                String.valueOf(obc)
            };

        List<com.ext.portlet.plans.model.PlanAttributeFilter> list = (List<com.ext.portlet.plans.model.PlanAttributeFilter>) FinderCacheUtil.getResult(FINDER_PATH_GET_PLANATTRIBUTEFILTERS,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder sb = new StringBuilder();

                sb.append(_SQL_GETPLANATTRIBUTEFILTERS);

                if (obc != null) {
                    sb.append("ORDER BY ");
                    sb.append(obc.getOrderBy());
                }

                String sql = sb.toString();

                SQLQuery q = session.createSQLQuery(sql);

                q.addEntity("PlanAttributeFilter",
                    com.ext.portlet.plans.model.impl.PlanAttributeFilterImpl.class);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                list = (List<com.ext.portlet.plans.model.PlanAttributeFilter>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<com.ext.portlet.plans.model.PlanAttributeFilter>();
                }

                planAttributeFilterPersistence.cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_GET_PLANATTRIBUTEFILTERS,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public int getPlanAttributeFiltersSize(Long pk) throws SystemException {
        Object[] finderArgs = new Object[] { pk };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_GET_PLANATTRIBUTEFILTERS_SIZE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                SQLQuery q = session.createSQLQuery(_SQL_GETPLANATTRIBUTEFILTERSSIZE);

                q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_GET_PLANATTRIBUTEFILTERS_SIZE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public boolean containsPlanAttributeFilter(Long pk,
        Long planAttributeFilterPK) throws SystemException {
        Object[] finderArgs = new Object[] { pk, planAttributeFilterPK };

        Boolean value = (Boolean) FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_PLANATTRIBUTEFILTER,
                finderArgs, this);

        if (value == null) {
            try {
                value = Boolean.valueOf(containsPlanAttributeFilter.contains(
                            pk, planAttributeFilterPK));
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (value == null) {
                    value = Boolean.FALSE;
                }

                FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_PLANATTRIBUTEFILTER,
                    finderArgs, value);
            }
        }

        return value.booleanValue();
    }

    public boolean containsPlanAttributeFilters(Long pk)
        throws SystemException {
        if (getPlanAttributeFiltersSize(pk) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<com.ext.portlet.plans.model.PlanColumnSettings> getPlanColumnSettingses(
        Long pk) throws SystemException {
        return getPlanColumnSettingses(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    public List<com.ext.portlet.plans.model.PlanColumnSettings> getPlanColumnSettingses(
        Long pk, int start, int end) throws SystemException {
        return getPlanColumnSettingses(pk, start, end, null);
    }

    public List<com.ext.portlet.plans.model.PlanColumnSettings> getPlanColumnSettingses(
        Long pk, int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                pk, String.valueOf(start), String.valueOf(end),
                String.valueOf(obc)
            };

        List<com.ext.portlet.plans.model.PlanColumnSettings> list = (List<com.ext.portlet.plans.model.PlanColumnSettings>) FinderCacheUtil.getResult(FINDER_PATH_GET_PLANCOLUMNSETTINGSES,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder sb = new StringBuilder();

                sb.append(_SQL_GETPLANCOLUMNSETTINGSES);

                if (obc != null) {
                    sb.append("ORDER BY ");
                    sb.append(obc.getOrderBy());
                }

                String sql = sb.toString();

                SQLQuery q = session.createSQLQuery(sql);

                q.addEntity("PlanColumnSettings",
                    com.ext.portlet.plans.model.impl.PlanColumnSettingsImpl.class);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                list = (List<com.ext.portlet.plans.model.PlanColumnSettings>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<com.ext.portlet.plans.model.PlanColumnSettings>();
                }

                planColumnSettingsPersistence.cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_GET_PLANCOLUMNSETTINGSES,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public int getPlanColumnSettingsesSize(Long pk) throws SystemException {
        Object[] finderArgs = new Object[] { pk };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_GET_PLANCOLUMNSETTINGSES_SIZE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                SQLQuery q = session.createSQLQuery(_SQL_GETPLANCOLUMNSETTINGSESSIZE);

                q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_GET_PLANCOLUMNSETTINGSES_SIZE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public boolean containsPlanColumnSettings(Long pk, Long planColumnSettingsPK)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, planColumnSettingsPK };

        Boolean value = (Boolean) FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_PLANCOLUMNSETTINGS,
                finderArgs, this);

        if (value == null) {
            try {
                value = Boolean.valueOf(containsPlanColumnSettings.contains(
                            pk, planColumnSettingsPK));
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (value == null) {
                    value = Boolean.FALSE;
                }

                FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_PLANCOLUMNSETTINGS,
                    finderArgs, value);
            }
        }

        return value.booleanValue();
    }

    public boolean containsPlanColumnSettingses(Long pk)
        throws SystemException {
        if (getPlanColumnSettingsesSize(pk) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.portal.util.PropsUtil.get(
                        "value.object.listener.com.ext.portlet.plans.model.PlansUserSettings")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlansUserSettings>> listenersList = new ArrayList<ModelListener<PlansUserSettings>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlansUserSettings>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }

        containsPlanAttributeFilter = new ContainsPlanAttributeFilter(this);

        containsPlanColumnSettings = new ContainsPlanColumnSettings(this);
    }

    protected class ContainsPlanAttributeFilter {
        private MappingSqlQuery _mappingSqlQuery;

        protected ContainsPlanAttributeFilter(
            PlansUserSettingsPersistenceImpl persistenceImpl) {
            super();

            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSPLANATTRIBUTEFILTER,
                    new int[] { Types.BIGINT, Types.BIGINT }, RowMapper.COUNT);
        }

        protected boolean contains(Long planUserSettingsId,
            Long planAttributeFilterId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        planUserSettingsId, planAttributeFilterId
                    });

            if (results.size() > 0) {
                Integer count = results.get(0);

                if (count.intValue() > 0) {
                    return true;
                }
            }

            return false;
        }
    }

    protected class ContainsPlanColumnSettings {
        private MappingSqlQuery _mappingSqlQuery;

        protected ContainsPlanColumnSettings(
            PlansUserSettingsPersistenceImpl persistenceImpl) {
            super();

            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSPLANCOLUMNSETTINGS,
                    new int[] { Types.BIGINT, Types.BIGINT }, RowMapper.COUNT);
        }

        protected boolean contains(Long planUserSettingsId,
            Long planColumnSettingsId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        planUserSettingsId, planColumnSettingsId
                    });

            if (results.size() > 0) {
                Integer count = results.get(0);

                if (count.intValue() > 0) {
                    return true;
                }
            }

            return false;
        }
    }
}
