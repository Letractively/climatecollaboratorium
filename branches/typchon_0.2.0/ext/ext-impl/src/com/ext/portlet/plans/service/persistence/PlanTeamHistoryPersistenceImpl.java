package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanTeamHistoryException;
import com.ext.portlet.plans.model.PlanTeamHistory;
import com.ext.portlet.plans.model.impl.PlanTeamHistoryImpl;
import com.ext.portlet.plans.model.impl.PlanTeamHistoryModelImpl;

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


public class PlanTeamHistoryPersistenceImpl extends BasePersistenceImpl
    implements PlanTeamHistoryPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanTeamHistoryImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_PLANID = new FinderPath(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
            PlanTeamHistoryModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByPlanId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_PLANID = new FinderPath(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
            PlanTeamHistoryModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByPlanId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANID = new FinderPath(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
            PlanTeamHistoryModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByPlanId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_LASTUSERACTIONINPLAN = new FinderPath(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
            PlanTeamHistoryModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByLastUserActionInPlan",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_LASTUSERACTIONINPLAN = new FinderPath(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
            PlanTeamHistoryModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByLastUserActionInPlan",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
            PlanTeamHistoryModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
            PlanTeamHistoryModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanTeamHistoryPersistenceImpl.class);
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

    public void cacheResult(PlanTeamHistory planTeamHistory) {
        EntityCacheUtil.putResult(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
            PlanTeamHistoryImpl.class, planTeamHistory.getPrimaryKey(),
            planTeamHistory);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_LASTUSERACTIONINPLAN,
            new Object[] {
                planTeamHistory.getPlanId(),
                
            planTeamHistory.getUserId()
            }, planTeamHistory);
    }

    public void cacheResult(List<PlanTeamHistory> planTeamHistories) {
        for (PlanTeamHistory planTeamHistory : planTeamHistories) {
            if (EntityCacheUtil.getResult(
                        PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTeamHistoryImpl.class,
                        planTeamHistory.getPrimaryKey(), this) == null) {
                cacheResult(planTeamHistory);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanTeamHistoryImpl.class.getName());
        EntityCacheUtil.clearCache(PlanTeamHistoryImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanTeamHistory create(Long id) {
        PlanTeamHistory planTeamHistory = new PlanTeamHistoryImpl();

        planTeamHistory.setNew(true);
        planTeamHistory.setPrimaryKey(id);

        return planTeamHistory;
    }

    public PlanTeamHistory remove(Long id)
        throws NoSuchPlanTeamHistoryException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanTeamHistory planTeamHistory = (PlanTeamHistory) session.get(PlanTeamHistoryImpl.class,
                    id);

            if (planTeamHistory == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No PlanTeamHistory exists with the primary key " +
                        id);
                }

                throw new NoSuchPlanTeamHistoryException(
                    "No PlanTeamHistory exists with the primary key " + id);
            }

            return remove(planTeamHistory);
        } catch (NoSuchPlanTeamHistoryException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanTeamHistory remove(PlanTeamHistory planTeamHistory)
        throws SystemException {
        for (ModelListener<PlanTeamHistory> listener : listeners) {
            listener.onBeforeRemove(planTeamHistory);
        }

        planTeamHistory = removeImpl(planTeamHistory);

        for (ModelListener<PlanTeamHistory> listener : listeners) {
            listener.onAfterRemove(planTeamHistory);
        }

        return planTeamHistory;
    }

    protected PlanTeamHistory removeImpl(PlanTeamHistory planTeamHistory)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planTeamHistory.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanTeamHistoryImpl.class,
                        planTeamHistory.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planTeamHistory);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        PlanTeamHistoryModelImpl planTeamHistoryModelImpl = (PlanTeamHistoryModelImpl) planTeamHistory;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_LASTUSERACTIONINPLAN,
            new Object[] {
                planTeamHistoryModelImpl.getOriginalPlanId(),
                
            planTeamHistoryModelImpl.getOriginalUserId()
            });

        EntityCacheUtil.removeResult(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
            PlanTeamHistoryImpl.class, planTeamHistory.getPrimaryKey());

        return planTeamHistory;
    }

    /**
     * @deprecated Use <code>update(PlanTeamHistory planTeamHistory, boolean merge)</code>.
     */
    public PlanTeamHistory update(PlanTeamHistory planTeamHistory)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanTeamHistory planTeamHistory) method. Use update(PlanTeamHistory planTeamHistory, boolean merge) instead.");
        }

        return update(planTeamHistory, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planTeamHistory the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planTeamHistory is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanTeamHistory update(PlanTeamHistory planTeamHistory, boolean merge)
        throws SystemException {
        boolean isNew = planTeamHistory.isNew();

        for (ModelListener<PlanTeamHistory> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planTeamHistory);
            } else {
                listener.onBeforeUpdate(planTeamHistory);
            }
        }

        planTeamHistory = updateImpl(planTeamHistory, merge);

        for (ModelListener<PlanTeamHistory> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planTeamHistory);
            } else {
                listener.onAfterUpdate(planTeamHistory);
            }
        }

        return planTeamHistory;
    }

    public PlanTeamHistory updateImpl(
        com.ext.portlet.plans.model.PlanTeamHistory planTeamHistory,
        boolean merge) throws SystemException {
        boolean isNew = planTeamHistory.isNew();

        PlanTeamHistoryModelImpl planTeamHistoryModelImpl = (PlanTeamHistoryModelImpl) planTeamHistory;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planTeamHistory, merge);

            planTeamHistory.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
            PlanTeamHistoryImpl.class, planTeamHistory.getPrimaryKey(),
            planTeamHistory);

        if (!isNew &&
                (!Validator.equals(planTeamHistory.getPlanId(),
                    planTeamHistoryModelImpl.getOriginalPlanId()) ||
                !Validator.equals(planTeamHistory.getUserId(),
                    planTeamHistoryModelImpl.getOriginalUserId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_LASTUSERACTIONINPLAN,
                new Object[] {
                    planTeamHistoryModelImpl.getOriginalPlanId(),
                    
                planTeamHistoryModelImpl.getOriginalUserId()
                });
        }

        if (isNew ||
                (!Validator.equals(planTeamHistory.getPlanId(),
                    planTeamHistoryModelImpl.getOriginalPlanId()) ||
                !Validator.equals(planTeamHistory.getUserId(),
                    planTeamHistoryModelImpl.getOriginalUserId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_LASTUSERACTIONINPLAN,
                new Object[] {
                    planTeamHistory.getPlanId(),
                    
                planTeamHistory.getUserId()
                }, planTeamHistory);
        }

        return planTeamHistory;
    }

    public PlanTeamHistory findByPrimaryKey(Long id)
        throws NoSuchPlanTeamHistoryException, SystemException {
        PlanTeamHistory planTeamHistory = fetchByPrimaryKey(id);

        if (planTeamHistory == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanTeamHistory exists with the primary key " +
                    id);
            }

            throw new NoSuchPlanTeamHistoryException(
                "No PlanTeamHistory exists with the primary key " + id);
        }

        return planTeamHistory;
    }

    public PlanTeamHistory fetchByPrimaryKey(Long id) throws SystemException {
        PlanTeamHistory planTeamHistory = (PlanTeamHistory) EntityCacheUtil.getResult(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
                PlanTeamHistoryImpl.class, id, this);

        if (planTeamHistory == null) {
            Session session = null;

            try {
                session = openSession();

                planTeamHistory = (PlanTeamHistory) session.get(PlanTeamHistoryImpl.class,
                        id);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planTeamHistory != null) {
                    cacheResult(planTeamHistory);
                }

                closeSession(session);
            }
        }

        return planTeamHistory;
    }

    public List<PlanTeamHistory> findByPlanId(Long planId)
        throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        List<PlanTeamHistory> list = (List<PlanTeamHistory>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_PLANID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanTeamHistory WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("id_ ASC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanTeamHistory>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_PLANID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<PlanTeamHistory> findByPlanId(Long planId, int start, int end)
        throws SystemException {
        return findByPlanId(planId, start, end, null);
    }

    public List<PlanTeamHistory> findByPlanId(Long planId, int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                planId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanTeamHistory> list = (List<PlanTeamHistory>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_PLANID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanTeamHistory WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("id_ ASC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                list = (List<PlanTeamHistory>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanTeamHistory>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_PLANID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public PlanTeamHistory findByPlanId_First(Long planId, OrderByComparator obc)
        throws NoSuchPlanTeamHistoryException, SystemException {
        List<PlanTeamHistory> list = findByPlanId(planId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanTeamHistory exists with the key {");

            msg.append("planId=" + planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanTeamHistoryException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanTeamHistory findByPlanId_Last(Long planId, OrderByComparator obc)
        throws NoSuchPlanTeamHistoryException, SystemException {
        int count = countByPlanId(planId);

        List<PlanTeamHistory> list = findByPlanId(planId, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanTeamHistory exists with the key {");

            msg.append("planId=" + planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanTeamHistoryException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanTeamHistory[] findByPlanId_PrevAndNext(Long id, Long planId,
        OrderByComparator obc)
        throws NoSuchPlanTeamHistoryException, SystemException {
        PlanTeamHistory planTeamHistory = findByPrimaryKey(id);

        int count = countByPlanId(planId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.plans.model.PlanTeamHistory WHERE ");

            if (planId == null) {
                query.append("planId IS NULL");
            } else {
                query.append("planId = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("id_ ASC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (planId != null) {
                qPos.add(planId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    planTeamHistory);

            PlanTeamHistory[] array = new PlanTeamHistoryImpl[3];

            array[0] = (PlanTeamHistory) objArray[0];
            array[1] = (PlanTeamHistory) objArray[1];
            array[2] = (PlanTeamHistory) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanTeamHistory findByLastUserActionInPlan(Long planId, Long userId)
        throws NoSuchPlanTeamHistoryException, SystemException {
        PlanTeamHistory planTeamHistory = fetchByLastUserActionInPlan(planId,
                userId);

        if (planTeamHistory == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanTeamHistory exists with the key {");

            msg.append("planId=" + planId);

            msg.append(", ");
            msg.append("userId=" + userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanTeamHistoryException(msg.toString());
        }

        return planTeamHistory;
    }

    public PlanTeamHistory fetchByLastUserActionInPlan(Long planId, Long userId)
        throws SystemException {
        return fetchByLastUserActionInPlan(planId, userId, true);
    }

    public PlanTeamHistory fetchByLastUserActionInPlan(Long planId,
        Long userId, boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { planId, userId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_LASTUSERACTIONINPLAN,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanTeamHistory WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" AND ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("id_ ASC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                List<PlanTeamHistory> list = q.list();

                result = list;

                PlanTeamHistory planTeamHistory = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_LASTUSERACTIONINPLAN,
                        finderArgs, list);
                } else {
                    planTeamHistory = list.get(0);

                    cacheResult(planTeamHistory);

                    if ((planTeamHistory.getPlanId() == null) ||
                            !planTeamHistory.getPlanId().equals(planId) ||
                            (planTeamHistory.getUserId() == null) ||
                            !planTeamHistory.getUserId().equals(userId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_LASTUSERACTIONINPLAN,
                            finderArgs, planTeamHistory);
                    }
                }

                return planTeamHistory;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_LASTUSERACTIONINPLAN,
                        finderArgs, new ArrayList<PlanTeamHistory>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (PlanTeamHistory) result;
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

    public List<PlanTeamHistory> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanTeamHistory> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanTeamHistory> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanTeamHistory> list = (List<PlanTeamHistory>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanTeamHistory ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("id_ ASC");
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlanTeamHistory>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanTeamHistory>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanTeamHistory>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByPlanId(Long planId) throws SystemException {
        for (PlanTeamHistory planTeamHistory : findByPlanId(planId)) {
            remove(planTeamHistory);
        }
    }

    public void removeByLastUserActionInPlan(Long planId, Long userId)
        throws NoSuchPlanTeamHistoryException, SystemException {
        PlanTeamHistory planTeamHistory = findByLastUserActionInPlan(planId,
                userId);

        remove(planTeamHistory);
    }

    public void removeAll() throws SystemException {
        for (PlanTeamHistory planTeamHistory : findAll()) {
            remove(planTeamHistory);
        }
    }

    public int countByPlanId(Long planId) throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PLANID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.plans.model.PlanTeamHistory WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByLastUserActionInPlan(Long planId, Long userId)
        throws SystemException {
        Object[] finderArgs = new Object[] { planId, userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_LASTUSERACTIONINPLAN,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.plans.model.PlanTeamHistory WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" AND ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_LASTUSERACTIONINPLAN,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanTeamHistory");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanTeamHistory")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanTeamHistory>> listenersList = new ArrayList<ModelListener<PlanTeamHistory>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanTeamHistory>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
