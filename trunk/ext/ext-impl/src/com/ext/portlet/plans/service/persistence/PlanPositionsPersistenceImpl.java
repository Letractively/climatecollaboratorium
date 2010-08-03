package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanPositionsException;
import com.ext.portlet.plans.model.PlanPositions;
import com.ext.portlet.plans.model.impl.PlanPositionsImpl;
import com.ext.portlet.plans.model.impl.PlanPositionsModelImpl;

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


public class PlanPositionsPersistenceImpl extends BasePersistenceImpl
    implements PlanPositionsPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanPositionsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_CURRENTBYPLANID = new FinderPath(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByCurrentByPlanId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_CURRENTBYPLANID = new FinderPath(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByCurrentByPlanId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_ALLBYPLANID = new FinderPath(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByAllByPlanId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_ALLBYPLANID = new FinderPath(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByAllByPlanId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_ALLBYPLANID = new FinderPath(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByAllByPlanId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanPositionsPersistenceImpl.class);
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

    public void cacheResult(PlanPositions planPositions) {
        EntityCacheUtil.putResult(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionsImpl.class, planPositions.getPrimaryKey(),
            planPositions);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
            new Object[] { planPositions.getPlanId() }, planPositions);
    }

    public void cacheResult(List<PlanPositions> planPositionses) {
        for (PlanPositions planPositions : planPositionses) {
            if (EntityCacheUtil.getResult(
                        PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
                        PlanPositionsImpl.class, planPositions.getPrimaryKey(),
                        this) == null) {
                cacheResult(planPositions);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanPositionsImpl.class.getName());
        EntityCacheUtil.clearCache(PlanPositionsImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanPositions create(Long id) {
        PlanPositions planPositions = new PlanPositionsImpl();

        planPositions.setNew(true);
        planPositions.setPrimaryKey(id);

        return planPositions;
    }

    public PlanPositions remove(Long id)
        throws NoSuchPlanPositionsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanPositions planPositions = (PlanPositions) session.get(PlanPositionsImpl.class,
                    id);

            if (planPositions == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No PlanPositions exists with the primary key " +
                        id);
                }

                throw new NoSuchPlanPositionsException(
                    "No PlanPositions exists with the primary key " + id);
            }

            return remove(planPositions);
        } catch (NoSuchPlanPositionsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanPositions remove(PlanPositions planPositions)
        throws SystemException {
        for (ModelListener<PlanPositions> listener : listeners) {
            listener.onBeforeRemove(planPositions);
        }

        planPositions = removeImpl(planPositions);

        for (ModelListener<PlanPositions> listener : listeners) {
            listener.onAfterRemove(planPositions);
        }

        return planPositions;
    }

    protected PlanPositions removeImpl(PlanPositions planPositions)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planPositions.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanPositionsImpl.class,
                        planPositions.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planPositions);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        PlanPositionsModelImpl planPositionsModelImpl = (PlanPositionsModelImpl) planPositions;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
            new Object[] { planPositionsModelImpl.getOriginalPlanId() });

        EntityCacheUtil.removeResult(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionsImpl.class, planPositions.getPrimaryKey());

        return planPositions;
    }

    /**
     * @deprecated Use <code>update(PlanPositions planPositions, boolean merge)</code>.
     */
    public PlanPositions update(PlanPositions planPositions)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanPositions planPositions) method. Use update(PlanPositions planPositions, boolean merge) instead.");
        }

        return update(planPositions, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planPositions the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planPositions is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanPositions update(PlanPositions planPositions, boolean merge)
        throws SystemException {
        boolean isNew = planPositions.isNew();

        for (ModelListener<PlanPositions> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planPositions);
            } else {
                listener.onBeforeUpdate(planPositions);
            }
        }

        planPositions = updateImpl(planPositions, merge);

        for (ModelListener<PlanPositions> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planPositions);
            } else {
                listener.onAfterUpdate(planPositions);
            }
        }

        return planPositions;
    }

    public PlanPositions updateImpl(
        com.ext.portlet.plans.model.PlanPositions planPositions, boolean merge)
        throws SystemException {
        boolean isNew = planPositions.isNew();

        PlanPositionsModelImpl planPositionsModelImpl = (PlanPositionsModelImpl) planPositions;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planPositions, merge);

            planPositions.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionsImpl.class, planPositions.getPrimaryKey(),
            planPositions);

        if (!isNew &&
                (!Validator.equals(planPositions.getPlanId(),
                    planPositionsModelImpl.getOriginalPlanId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                new Object[] { planPositionsModelImpl.getOriginalPlanId() });
        }

        if (isNew ||
                (!Validator.equals(planPositions.getPlanId(),
                    planPositionsModelImpl.getOriginalPlanId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                new Object[] { planPositions.getPlanId() }, planPositions);
        }

        return planPositions;
    }

    public PlanPositions findByPrimaryKey(Long id)
        throws NoSuchPlanPositionsException, SystemException {
        PlanPositions planPositions = fetchByPrimaryKey(id);

        if (planPositions == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanPositions exists with the primary key " + id);
            }

            throw new NoSuchPlanPositionsException(
                "No PlanPositions exists with the primary key " + id);
        }

        return planPositions;
    }

    public PlanPositions fetchByPrimaryKey(Long id) throws SystemException {
        PlanPositions planPositions = (PlanPositions) EntityCacheUtil.getResult(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
                PlanPositionsImpl.class, id, this);

        if (planPositions == null) {
            Session session = null;

            try {
                session = openSession();

                planPositions = (PlanPositions) session.get(PlanPositionsImpl.class,
                        id);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planPositions != null) {
                    cacheResult(planPositions);
                }

                closeSession(session);
            }
        }

        return planPositions;
    }

    public PlanPositions findByCurrentByPlanId(Long planId)
        throws NoSuchPlanPositionsException, SystemException {
        PlanPositions planPositions = fetchByCurrentByPlanId(planId);

        if (planPositions == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanPositions exists with the key {");

            msg.append("planId=" + planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanPositionsException(msg.toString());
        }

        return planPositions;
    }

    public PlanPositions fetchByCurrentByPlanId(Long planId)
        throws SystemException {
        return fetchByCurrentByPlanId(planId, true);
    }

    public PlanPositions fetchByCurrentByPlanId(Long planId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanPositions WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("version DESC, ");
                query.append("created DESC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                List<PlanPositions> list = q.list();

                result = list;

                PlanPositions planPositions = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                        finderArgs, list);
                } else {
                    planPositions = list.get(0);

                    cacheResult(planPositions);

                    if ((planPositions.getPlanId() == null) ||
                            !planPositions.getPlanId().equals(planId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                            finderArgs, planPositions);
                    }
                }

                return planPositions;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                        finderArgs, new ArrayList<PlanPositions>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (PlanPositions) result;
            }
        }
    }

    public List<PlanPositions> findByAllByPlanId(Long planId)
        throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        List<PlanPositions> list = (List<PlanPositions>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ALLBYPLANID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanPositions WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("version DESC, ");
                query.append("created DESC");

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
                    list = new ArrayList<PlanPositions>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ALLBYPLANID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<PlanPositions> findByAllByPlanId(Long planId, int start, int end)
        throws SystemException {
        return findByAllByPlanId(planId, start, end, null);
    }

    public List<PlanPositions> findByAllByPlanId(Long planId, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                planId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanPositions> list = (List<PlanPositions>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_ALLBYPLANID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanPositions WHERE ");

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

                    query.append("version DESC, ");
                    query.append("created DESC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                list = (List<PlanPositions>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanPositions>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_ALLBYPLANID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public PlanPositions findByAllByPlanId_First(Long planId,
        OrderByComparator obc)
        throws NoSuchPlanPositionsException, SystemException {
        List<PlanPositions> list = findByAllByPlanId(planId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanPositions exists with the key {");

            msg.append("planId=" + planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanPositionsException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanPositions findByAllByPlanId_Last(Long planId,
        OrderByComparator obc)
        throws NoSuchPlanPositionsException, SystemException {
        int count = countByAllByPlanId(planId);

        List<PlanPositions> list = findByAllByPlanId(planId, count - 1, count,
                obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanPositions exists with the key {");

            msg.append("planId=" + planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanPositionsException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanPositions[] findByAllByPlanId_PrevAndNext(Long id, Long planId,
        OrderByComparator obc)
        throws NoSuchPlanPositionsException, SystemException {
        PlanPositions planPositions = findByPrimaryKey(id);

        int count = countByAllByPlanId(planId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.plans.model.PlanPositions WHERE ");

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

                query.append("version DESC, ");
                query.append("created DESC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (planId != null) {
                qPos.add(planId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    planPositions);

            PlanPositions[] array = new PlanPositionsImpl[3];

            array[0] = (PlanPositions) objArray[0];
            array[1] = (PlanPositions) objArray[1];
            array[2] = (PlanPositions) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
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

    public List<PlanPositions> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanPositions> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanPositions> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanPositions> list = (List<PlanPositions>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanPositions ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("version DESC, ");
                    query.append("created DESC");
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlanPositions>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanPositions>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanPositions>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByCurrentByPlanId(Long planId)
        throws NoSuchPlanPositionsException, SystemException {
        PlanPositions planPositions = findByCurrentByPlanId(planId);

        remove(planPositions);
    }

    public void removeByAllByPlanId(Long planId) throws SystemException {
        for (PlanPositions planPositions : findByAllByPlanId(planId)) {
            remove(planPositions);
        }
    }

    public void removeAll() throws SystemException {
        for (PlanPositions planPositions : findAll()) {
            remove(planPositions);
        }
    }

    public int countByCurrentByPlanId(Long planId) throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CURRENTBYPLANID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.plans.model.PlanPositions WHERE ");

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

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CURRENTBYPLANID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByAllByPlanId(Long planId) throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ALLBYPLANID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.plans.model.PlanPositions WHERE ");

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

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ALLBYPLANID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanPositions");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanPositions")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanPositions>> listenersList = new ArrayList<ModelListener<PlanPositions>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanPositions>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
