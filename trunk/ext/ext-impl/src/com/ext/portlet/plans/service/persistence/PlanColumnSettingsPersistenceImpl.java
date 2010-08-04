package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanColumnSettingsException;
import com.ext.portlet.plans.model.PlanColumnSettings;
import com.ext.portlet.plans.model.impl.PlanColumnSettingsImpl;
import com.ext.portlet.plans.model.impl.PlanColumnSettingsModelImpl;

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


public class PlanColumnSettingsPersistenceImpl extends BasePersistenceImpl
    implements PlanColumnSettingsPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanColumnSettingsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME =
        new FinderPath(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByPlanUserSettingsIdColumnName",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDCOLUMNNAME =
        new FinderPath(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByPlanUserSettingsIdColumnName",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanColumnSettingsPersistenceImpl.class);
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

    public void cacheResult(PlanColumnSettings planColumnSettings) {
        EntityCacheUtil.putResult(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsImpl.class, planColumnSettings.getPrimaryKey(),
            planColumnSettings);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
            new Object[] {
                planColumnSettings.getPlanUserSettingsId(),
                
            planColumnSettings.getColumnName()
            }, planColumnSettings);
    }

    public void cacheResult(List<PlanColumnSettings> planColumnSettingses) {
        for (PlanColumnSettings planColumnSettings : planColumnSettingses) {
            if (EntityCacheUtil.getResult(
                        PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
                        PlanColumnSettingsImpl.class,
                        planColumnSettings.getPrimaryKey(), this) == null) {
                cacheResult(planColumnSettings);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanColumnSettingsImpl.class.getName());
        EntityCacheUtil.clearCache(PlanColumnSettingsImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanColumnSettings create(Long planColumnSettingsId) {
        PlanColumnSettings planColumnSettings = new PlanColumnSettingsImpl();

        planColumnSettings.setNew(true);
        planColumnSettings.setPrimaryKey(planColumnSettingsId);

        return planColumnSettings;
    }

    public PlanColumnSettings remove(Long planColumnSettingsId)
        throws NoSuchPlanColumnSettingsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanColumnSettings planColumnSettings = (PlanColumnSettings) session.get(PlanColumnSettingsImpl.class,
                    planColumnSettingsId);

            if (planColumnSettings == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No PlanColumnSettings exists with the primary key " +
                        planColumnSettingsId);
                }

                throw new NoSuchPlanColumnSettingsException(
                    "No PlanColumnSettings exists with the primary key " +
                    planColumnSettingsId);
            }

            return remove(planColumnSettings);
        } catch (NoSuchPlanColumnSettingsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanColumnSettings remove(PlanColumnSettings planColumnSettings)
        throws SystemException {
        for (ModelListener<PlanColumnSettings> listener : listeners) {
            listener.onBeforeRemove(planColumnSettings);
        }

        planColumnSettings = removeImpl(planColumnSettings);

        for (ModelListener<PlanColumnSettings> listener : listeners) {
            listener.onAfterRemove(planColumnSettings);
        }

        return planColumnSettings;
    }

    protected PlanColumnSettings removeImpl(
        PlanColumnSettings planColumnSettings) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planColumnSettings.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanColumnSettingsImpl.class,
                        planColumnSettings.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planColumnSettings);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        PlanColumnSettingsModelImpl planColumnSettingsModelImpl = (PlanColumnSettingsModelImpl) planColumnSettings;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
            new Object[] {
                planColumnSettingsModelImpl.getOriginalPlanUserSettingsId(),
                
            planColumnSettingsModelImpl.getOriginalColumnName()
            });

        EntityCacheUtil.removeResult(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsImpl.class, planColumnSettings.getPrimaryKey());

        return planColumnSettings;
    }

    /**
     * @deprecated Use <code>update(PlanColumnSettings planColumnSettings, boolean merge)</code>.
     */
    public PlanColumnSettings update(PlanColumnSettings planColumnSettings)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanColumnSettings planColumnSettings) method. Use update(PlanColumnSettings planColumnSettings, boolean merge) instead.");
        }

        return update(planColumnSettings, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planColumnSettings the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planColumnSettings is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanColumnSettings update(PlanColumnSettings planColumnSettings,
        boolean merge) throws SystemException {
        boolean isNew = planColumnSettings.isNew();

        for (ModelListener<PlanColumnSettings> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planColumnSettings);
            } else {
                listener.onBeforeUpdate(planColumnSettings);
            }
        }

        planColumnSettings = updateImpl(planColumnSettings, merge);

        for (ModelListener<PlanColumnSettings> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planColumnSettings);
            } else {
                listener.onAfterUpdate(planColumnSettings);
            }
        }

        return planColumnSettings;
    }

    public PlanColumnSettings updateImpl(
        com.ext.portlet.plans.model.PlanColumnSettings planColumnSettings,
        boolean merge) throws SystemException {
        boolean isNew = planColumnSettings.isNew();

        PlanColumnSettingsModelImpl planColumnSettingsModelImpl = (PlanColumnSettingsModelImpl) planColumnSettings;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planColumnSettings, merge);

            planColumnSettings.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsImpl.class, planColumnSettings.getPrimaryKey(),
            planColumnSettings);

        if (!isNew &&
                (!Validator.equals(planColumnSettings.getPlanUserSettingsId(),
                    planColumnSettingsModelImpl.getOriginalPlanUserSettingsId()) ||
                !Validator.equals(planColumnSettings.getColumnName(),
                    planColumnSettingsModelImpl.getOriginalColumnName()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                new Object[] {
                    planColumnSettingsModelImpl.getOriginalPlanUserSettingsId(),
                    
                planColumnSettingsModelImpl.getOriginalColumnName()
                });
        }

        if (isNew ||
                (!Validator.equals(planColumnSettings.getPlanUserSettingsId(),
                    planColumnSettingsModelImpl.getOriginalPlanUserSettingsId()) ||
                !Validator.equals(planColumnSettings.getColumnName(),
                    planColumnSettingsModelImpl.getOriginalColumnName()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                new Object[] {
                    planColumnSettings.getPlanUserSettingsId(),
                    
                planColumnSettings.getColumnName()
                }, planColumnSettings);
        }

        return planColumnSettings;
    }

    public PlanColumnSettings findByPrimaryKey(Long planColumnSettingsId)
        throws NoSuchPlanColumnSettingsException, SystemException {
        PlanColumnSettings planColumnSettings = fetchByPrimaryKey(planColumnSettingsId);

        if (planColumnSettings == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanColumnSettings exists with the primary key " +
                    planColumnSettingsId);
            }

            throw new NoSuchPlanColumnSettingsException(
                "No PlanColumnSettings exists with the primary key " +
                planColumnSettingsId);
        }

        return planColumnSettings;
    }

    public PlanColumnSettings fetchByPrimaryKey(Long planColumnSettingsId)
        throws SystemException {
        PlanColumnSettings planColumnSettings = (PlanColumnSettings) EntityCacheUtil.getResult(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
                PlanColumnSettingsImpl.class, planColumnSettingsId, this);

        if (planColumnSettings == null) {
            Session session = null;

            try {
                session = openSession();

                planColumnSettings = (PlanColumnSettings) session.get(PlanColumnSettingsImpl.class,
                        planColumnSettingsId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planColumnSettings != null) {
                    cacheResult(planColumnSettings);
                }

                closeSession(session);
            }
        }

        return planColumnSettings;
    }

    public PlanColumnSettings findByPlanUserSettingsIdColumnName(
        Long planUserSettingsId, String columnName)
        throws NoSuchPlanColumnSettingsException, SystemException {
        PlanColumnSettings planColumnSettings = fetchByPlanUserSettingsIdColumnName(planUserSettingsId,
                columnName);

        if (planColumnSettings == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanColumnSettings exists with the key {");

            msg.append("planUserSettingsId=" + planUserSettingsId);

            msg.append(", ");
            msg.append("columnName=" + columnName);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanColumnSettingsException(msg.toString());
        }

        return planColumnSettings;
    }

    public PlanColumnSettings fetchByPlanUserSettingsIdColumnName(
        Long planUserSettingsId, String columnName) throws SystemException {
        return fetchByPlanUserSettingsIdColumnName(planUserSettingsId,
            columnName, true);
    }

    public PlanColumnSettings fetchByPlanUserSettingsIdColumnName(
        Long planUserSettingsId, String columnName, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { planUserSettingsId, columnName };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanColumnSettings WHERE ");

                if (planUserSettingsId == null) {
                    query.append("planUserSettingsId IS NULL");
                } else {
                    query.append("planUserSettingsId = ?");
                }

                query.append(" AND ");

                if (columnName == null) {
                    query.append("columnName IS NULL");
                } else {
                    query.append("columnName = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planUserSettingsId != null) {
                    qPos.add(planUserSettingsId.longValue());
                }

                if (columnName != null) {
                    qPos.add(columnName);
                }

                List<PlanColumnSettings> list = q.list();

                result = list;

                PlanColumnSettings planColumnSettings = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                        finderArgs, list);
                } else {
                    planColumnSettings = list.get(0);

                    cacheResult(planColumnSettings);

                    if ((planColumnSettings.getPlanUserSettingsId() == null) ||
                            !planColumnSettings.getPlanUserSettingsId()
                                                   .equals(planUserSettingsId) ||
                            (planColumnSettings.getColumnName() == null) ||
                            !planColumnSettings.getColumnName()
                                                   .equals(columnName)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                            finderArgs, planColumnSettings);
                    }
                }

                return planColumnSettings;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                        finderArgs, new ArrayList<PlanColumnSettings>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (PlanColumnSettings) result;
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

    public List<PlanColumnSettings> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanColumnSettings> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanColumnSettings> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanColumnSettings> list = (List<PlanColumnSettings>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanColumnSettings ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlanColumnSettings>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanColumnSettings>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanColumnSettings>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByPlanUserSettingsIdColumnName(Long planUserSettingsId,
        String columnName)
        throws NoSuchPlanColumnSettingsException, SystemException {
        PlanColumnSettings planColumnSettings = findByPlanUserSettingsIdColumnName(planUserSettingsId,
                columnName);

        remove(planColumnSettings);
    }

    public void removeAll() throws SystemException {
        for (PlanColumnSettings planColumnSettings : findAll()) {
            remove(planColumnSettings);
        }
    }

    public int countByPlanUserSettingsIdColumnName(Long planUserSettingsId,
        String columnName) throws SystemException {
        Object[] finderArgs = new Object[] { planUserSettingsId, columnName };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.plans.model.PlanColumnSettings WHERE ");

                if (planUserSettingsId == null) {
                    query.append("planUserSettingsId IS NULL");
                } else {
                    query.append("planUserSettingsId = ?");
                }

                query.append(" AND ");

                if (columnName == null) {
                    query.append("columnName IS NULL");
                } else {
                    query.append("columnName = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planUserSettingsId != null) {
                    qPos.add(planUserSettingsId.longValue());
                }

                if (columnName != null) {
                    qPos.add(columnName);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDCOLUMNNAME,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanColumnSettings");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanColumnSettings")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanColumnSettings>> listenersList = new ArrayList<ModelListener<PlanColumnSettings>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanColumnSettings>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
