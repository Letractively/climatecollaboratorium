package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanModelRunException;
import com.ext.portlet.plans.model.PlanModelRun;
import com.ext.portlet.plans.model.impl.PlanModelRunImpl;
import com.ext.portlet.plans.model.impl.PlanModelRunModelImpl;

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


public class PlanModelRunPersistenceImpl extends BasePersistenceImpl
    implements PlanModelRunPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanModelRunImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_CURRENTBYPLANID = new FinderPath(PlanModelRunModelImpl.ENTITY_CACHE_ENABLED,
            PlanModelRunModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByCurrentByPlanId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_CURRENTBYPLANID = new FinderPath(PlanModelRunModelImpl.ENTITY_CACHE_ENABLED,
            PlanModelRunModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByCurrentByPlanId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_ALLBYPLANID = new FinderPath(PlanModelRunModelImpl.ENTITY_CACHE_ENABLED,
            PlanModelRunModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByAllByPlanId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_ALLBYPLANID = new FinderPath(PlanModelRunModelImpl.ENTITY_CACHE_ENABLED,
            PlanModelRunModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByAllByPlanId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_ALLBYPLANID = new FinderPath(PlanModelRunModelImpl.ENTITY_CACHE_ENABLED,
            PlanModelRunModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByAllByPlanId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanModelRunModelImpl.ENTITY_CACHE_ENABLED,
            PlanModelRunModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanModelRunModelImpl.ENTITY_CACHE_ENABLED,
            PlanModelRunModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanModelRunPersistenceImpl.class);
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

    public void cacheResult(PlanModelRun planModelRun) {
        EntityCacheUtil.putResult(PlanModelRunModelImpl.ENTITY_CACHE_ENABLED,
            PlanModelRunImpl.class, planModelRun.getPrimaryKey(), planModelRun);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
            new Object[] { planModelRun.getPlanId() }, planModelRun);
    }

    public void cacheResult(List<PlanModelRun> planModelRuns) {
        for (PlanModelRun planModelRun : planModelRuns) {
            if (EntityCacheUtil.getResult(
                        PlanModelRunModelImpl.ENTITY_CACHE_ENABLED,
                        PlanModelRunImpl.class, planModelRun.getPrimaryKey(),
                        this) == null) {
                cacheResult(planModelRun);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanModelRunImpl.class.getName());
        EntityCacheUtil.clearCache(PlanModelRunImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanModelRun create(Long id) {
        PlanModelRun planModelRun = new PlanModelRunImpl();

        planModelRun.setNew(true);
        planModelRun.setPrimaryKey(id);

        return planModelRun;
    }

    public PlanModelRun remove(Long id)
        throws NoSuchPlanModelRunException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanModelRun planModelRun = (PlanModelRun) session.get(PlanModelRunImpl.class,
                    id);

            if (planModelRun == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No PlanModelRun exists with the primary key " +
                        id);
                }

                throw new NoSuchPlanModelRunException(
                    "No PlanModelRun exists with the primary key " + id);
            }

            return remove(planModelRun);
        } catch (NoSuchPlanModelRunException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanModelRun remove(PlanModelRun planModelRun)
        throws SystemException {
        for (ModelListener<PlanModelRun> listener : listeners) {
            listener.onBeforeRemove(planModelRun);
        }

        planModelRun = removeImpl(planModelRun);

        for (ModelListener<PlanModelRun> listener : listeners) {
            listener.onAfterRemove(planModelRun);
        }

        return planModelRun;
    }

    protected PlanModelRun removeImpl(PlanModelRun planModelRun)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planModelRun.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanModelRunImpl.class,
                        planModelRun.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planModelRun);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        PlanModelRunModelImpl planModelRunModelImpl = (PlanModelRunModelImpl) planModelRun;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
            new Object[] { planModelRunModelImpl.getOriginalPlanId() });

        EntityCacheUtil.removeResult(PlanModelRunModelImpl.ENTITY_CACHE_ENABLED,
            PlanModelRunImpl.class, planModelRun.getPrimaryKey());

        return planModelRun;
    }

    /**
     * @deprecated Use <code>update(PlanModelRun planModelRun, boolean merge)</code>.
     */
    public PlanModelRun update(PlanModelRun planModelRun)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanModelRun planModelRun) method. Use update(PlanModelRun planModelRun, boolean merge) instead.");
        }

        return update(planModelRun, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planModelRun the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planModelRun is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanModelRun update(PlanModelRun planModelRun, boolean merge)
        throws SystemException {
        boolean isNew = planModelRun.isNew();

        for (ModelListener<PlanModelRun> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planModelRun);
            } else {
                listener.onBeforeUpdate(planModelRun);
            }
        }

        planModelRun = updateImpl(planModelRun, merge);

        for (ModelListener<PlanModelRun> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planModelRun);
            } else {
                listener.onAfterUpdate(planModelRun);
            }
        }

        return planModelRun;
    }

    public PlanModelRun updateImpl(
        com.ext.portlet.plans.model.PlanModelRun planModelRun, boolean merge)
        throws SystemException {
        boolean isNew = planModelRun.isNew();

        PlanModelRunModelImpl planModelRunModelImpl = (PlanModelRunModelImpl) planModelRun;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planModelRun, merge);

            planModelRun.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanModelRunModelImpl.ENTITY_CACHE_ENABLED,
            PlanModelRunImpl.class, planModelRun.getPrimaryKey(), planModelRun);

        if (!isNew &&
                (!Validator.equals(planModelRun.getPlanId(),
                    planModelRunModelImpl.getOriginalPlanId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                new Object[] { planModelRunModelImpl.getOriginalPlanId() });
        }

        if (isNew ||
                (!Validator.equals(planModelRun.getPlanId(),
                    planModelRunModelImpl.getOriginalPlanId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                new Object[] { planModelRun.getPlanId() }, planModelRun);
        }

        return planModelRun;
    }

    public PlanModelRun findByPrimaryKey(Long id)
        throws NoSuchPlanModelRunException, SystemException {
        PlanModelRun planModelRun = fetchByPrimaryKey(id);

        if (planModelRun == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanModelRun exists with the primary key " + id);
            }

            throw new NoSuchPlanModelRunException(
                "No PlanModelRun exists with the primary key " + id);
        }

        return planModelRun;
    }

    public PlanModelRun fetchByPrimaryKey(Long id) throws SystemException {
        PlanModelRun planModelRun = (PlanModelRun) EntityCacheUtil.getResult(PlanModelRunModelImpl.ENTITY_CACHE_ENABLED,
                PlanModelRunImpl.class, id, this);

        if (planModelRun == null) {
            Session session = null;

            try {
                session = openSession();

                planModelRun = (PlanModelRun) session.get(PlanModelRunImpl.class,
                        id);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planModelRun != null) {
                    cacheResult(planModelRun);
                }

                closeSession(session);
            }
        }

        return planModelRun;
    }

    public PlanModelRun findByCurrentByPlanId(Long planId)
        throws NoSuchPlanModelRunException, SystemException {
        PlanModelRun planModelRun = fetchByCurrentByPlanId(planId);

        if (planModelRun == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanModelRun exists with the key {");

            msg.append("planId=" + planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanModelRunException(msg.toString());
        }

        return planModelRun;
    }

    public PlanModelRun fetchByCurrentByPlanId(Long planId)
        throws SystemException {
        return fetchByCurrentByPlanId(planId, true);
    }

    public PlanModelRun fetchByCurrentByPlanId(Long planId,
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
                    "FROM com.ext.portlet.plans.model.PlanModelRun WHERE ");

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

                List<PlanModelRun> list = q.list();

                result = list;

                PlanModelRun planModelRun = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                        finderArgs, list);
                } else {
                    planModelRun = list.get(0);

                    cacheResult(planModelRun);

                    if ((planModelRun.getPlanId() == null) ||
                            !planModelRun.getPlanId().equals(planId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                            finderArgs, planModelRun);
                    }
                }

                return planModelRun;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                        finderArgs, new ArrayList<PlanModelRun>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (PlanModelRun) result;
            }
        }
    }

    public List<PlanModelRun> findByAllByPlanId(Long planId)
        throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        List<PlanModelRun> list = (List<PlanModelRun>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ALLBYPLANID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanModelRun WHERE ");

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
                    list = new ArrayList<PlanModelRun>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ALLBYPLANID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<PlanModelRun> findByAllByPlanId(Long planId, int start, int end)
        throws SystemException {
        return findByAllByPlanId(planId, start, end, null);
    }

    public List<PlanModelRun> findByAllByPlanId(Long planId, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                planId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanModelRun> list = (List<PlanModelRun>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_ALLBYPLANID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanModelRun WHERE ");

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

                list = (List<PlanModelRun>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanModelRun>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_ALLBYPLANID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public PlanModelRun findByAllByPlanId_First(Long planId,
        OrderByComparator obc)
        throws NoSuchPlanModelRunException, SystemException {
        List<PlanModelRun> list = findByAllByPlanId(planId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanModelRun exists with the key {");

            msg.append("planId=" + planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanModelRunException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanModelRun findByAllByPlanId_Last(Long planId,
        OrderByComparator obc)
        throws NoSuchPlanModelRunException, SystemException {
        int count = countByAllByPlanId(planId);

        List<PlanModelRun> list = findByAllByPlanId(planId, count - 1, count,
                obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanModelRun exists with the key {");

            msg.append("planId=" + planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanModelRunException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanModelRun[] findByAllByPlanId_PrevAndNext(Long id, Long planId,
        OrderByComparator obc)
        throws NoSuchPlanModelRunException, SystemException {
        PlanModelRun planModelRun = findByPrimaryKey(id);

        int count = countByAllByPlanId(planId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append("FROM com.ext.portlet.plans.model.PlanModelRun WHERE ");

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
                    planModelRun);

            PlanModelRun[] array = new PlanModelRunImpl[3];

            array[0] = (PlanModelRun) objArray[0];
            array[1] = (PlanModelRun) objArray[1];
            array[2] = (PlanModelRun) objArray[2];

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

    public List<PlanModelRun> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanModelRun> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanModelRun> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanModelRun> list = (List<PlanModelRun>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanModelRun ");

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
                    list = (List<PlanModelRun>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanModelRun>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanModelRun>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByCurrentByPlanId(Long planId)
        throws NoSuchPlanModelRunException, SystemException {
        PlanModelRun planModelRun = findByCurrentByPlanId(planId);

        remove(planModelRun);
    }

    public void removeByAllByPlanId(Long planId) throws SystemException {
        for (PlanModelRun planModelRun : findByAllByPlanId(planId)) {
            remove(planModelRun);
        }
    }

    public void removeAll() throws SystemException {
        for (PlanModelRun planModelRun : findAll()) {
            remove(planModelRun);
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
                    "FROM com.ext.portlet.plans.model.PlanModelRun WHERE ");

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
                    "FROM com.ext.portlet.plans.model.PlanModelRun WHERE ");

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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanModelRun");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanModelRun")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanModelRun>> listenersList = new ArrayList<ModelListener<PlanModelRun>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanModelRun>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
