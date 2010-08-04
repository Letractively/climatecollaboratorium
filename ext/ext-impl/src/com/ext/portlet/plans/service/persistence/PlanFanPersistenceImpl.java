package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanFanException;
import com.ext.portlet.plans.model.PlanFan;
import com.ext.portlet.plans.model.impl.PlanFanImpl;
import com.ext.portlet.plans.model.impl.PlanFanModelImpl;

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


public class PlanFanPersistenceImpl extends BasePersistenceImpl
    implements PlanFanPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanFanImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_PLANID = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByPlanId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_PLANID = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByPlanId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANID = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByPlanId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_USERID = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByUserId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_USERID = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByUserId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByUserId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_PLANIDUSERID = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
            "fetchByPlanIdUserId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANIDUSERID = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByPlanIdUserId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanFanPersistenceImpl.class);
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

    public void cacheResult(PlanFan planFan) {
        EntityCacheUtil.putResult(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanImpl.class, planFan.getPrimaryKey(), planFan);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
            new Object[] { planFan.getPlanId(), planFan.getUserId() }, planFan);
    }

    public void cacheResult(List<PlanFan> planFans) {
        for (PlanFan planFan : planFans) {
            if (EntityCacheUtil.getResult(
                        PlanFanModelImpl.ENTITY_CACHE_ENABLED,
                        PlanFanImpl.class, planFan.getPrimaryKey(), this) == null) {
                cacheResult(planFan);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanFanImpl.class.getName());
        EntityCacheUtil.clearCache(PlanFanImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanFan create(Long id) {
        PlanFan planFan = new PlanFanImpl();

        planFan.setNew(true);
        planFan.setPrimaryKey(id);

        return planFan;
    }

    public PlanFan remove(Long id)
        throws NoSuchPlanFanException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanFan planFan = (PlanFan) session.get(PlanFanImpl.class, id);

            if (planFan == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No PlanFan exists with the primary key " + id);
                }

                throw new NoSuchPlanFanException(
                    "No PlanFan exists with the primary key " + id);
            }

            return remove(planFan);
        } catch (NoSuchPlanFanException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanFan remove(PlanFan planFan) throws SystemException {
        for (ModelListener<PlanFan> listener : listeners) {
            listener.onBeforeRemove(planFan);
        }

        planFan = removeImpl(planFan);

        for (ModelListener<PlanFan> listener : listeners) {
            listener.onAfterRemove(planFan);
        }

        return planFan;
    }

    protected PlanFan removeImpl(PlanFan planFan) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planFan.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanFanImpl.class,
                        planFan.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planFan);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        PlanFanModelImpl planFanModelImpl = (PlanFanModelImpl) planFan;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
            new Object[] {
                planFanModelImpl.getOriginalPlanId(),
                
            planFanModelImpl.getOriginalUserId()
            });

        EntityCacheUtil.removeResult(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanImpl.class, planFan.getPrimaryKey());

        return planFan;
    }

    /**
     * @deprecated Use <code>update(PlanFan planFan, boolean merge)</code>.
     */
    public PlanFan update(PlanFan planFan) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanFan planFan) method. Use update(PlanFan planFan, boolean merge) instead.");
        }

        return update(planFan, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planFan the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planFan is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanFan update(PlanFan planFan, boolean merge)
        throws SystemException {
        boolean isNew = planFan.isNew();

        for (ModelListener<PlanFan> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planFan);
            } else {
                listener.onBeforeUpdate(planFan);
            }
        }

        planFan = updateImpl(planFan, merge);

        for (ModelListener<PlanFan> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planFan);
            } else {
                listener.onAfterUpdate(planFan);
            }
        }

        return planFan;
    }

    public PlanFan updateImpl(com.ext.portlet.plans.model.PlanFan planFan,
        boolean merge) throws SystemException {
        boolean isNew = planFan.isNew();

        PlanFanModelImpl planFanModelImpl = (PlanFanModelImpl) planFan;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planFan, merge);

            planFan.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanImpl.class, planFan.getPrimaryKey(), planFan);

        if (!isNew &&
                (!Validator.equals(planFan.getPlanId(),
                    planFanModelImpl.getOriginalPlanId()) ||
                !Validator.equals(planFan.getUserId(),
                    planFanModelImpl.getOriginalUserId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
                new Object[] {
                    planFanModelImpl.getOriginalPlanId(),
                    
                planFanModelImpl.getOriginalUserId()
                });
        }

        if (isNew ||
                (!Validator.equals(planFan.getPlanId(),
                    planFanModelImpl.getOriginalPlanId()) ||
                !Validator.equals(planFan.getUserId(),
                    planFanModelImpl.getOriginalUserId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
                new Object[] { planFan.getPlanId(), planFan.getUserId() },
                planFan);
        }

        return planFan;
    }

    public PlanFan findByPrimaryKey(Long id)
        throws NoSuchPlanFanException, SystemException {
        PlanFan planFan = fetchByPrimaryKey(id);

        if (planFan == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanFan exists with the primary key " + id);
            }

            throw new NoSuchPlanFanException(
                "No PlanFan exists with the primary key " + id);
        }

        return planFan;
    }

    public PlanFan fetchByPrimaryKey(Long id) throws SystemException {
        PlanFan planFan = (PlanFan) EntityCacheUtil.getResult(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
                PlanFanImpl.class, id, this);

        if (planFan == null) {
            Session session = null;

            try {
                session = openSession();

                planFan = (PlanFan) session.get(PlanFanImpl.class, id);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planFan != null) {
                    cacheResult(planFan);
                }

                closeSession(session);
            }
        }

        return planFan;
    }

    public List<PlanFan> findByPlanId(Long planId) throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        List<PlanFan> list = (List<PlanFan>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_PLANID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanFan WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" AND deleted is null ");

                query.append("ORDER BY ");

                query.append("created ASC");

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
                    list = new ArrayList<PlanFan>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_PLANID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<PlanFan> findByPlanId(Long planId, int start, int end)
        throws SystemException {
        return findByPlanId(planId, start, end, null);
    }

    public List<PlanFan> findByPlanId(Long planId, int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                planId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanFan> list = (List<PlanFan>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_PLANID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanFan WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" AND deleted is null ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("created ASC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                list = (List<PlanFan>) QueryUtil.list(q, getDialect(), start,
                        end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanFan>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_PLANID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public PlanFan findByPlanId_First(Long planId, OrderByComparator obc)
        throws NoSuchPlanFanException, SystemException {
        List<PlanFan> list = findByPlanId(planId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanFan exists with the key {");

            msg.append("planId=" + planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanFanException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanFan findByPlanId_Last(Long planId, OrderByComparator obc)
        throws NoSuchPlanFanException, SystemException {
        int count = countByPlanId(planId);

        List<PlanFan> list = findByPlanId(planId, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanFan exists with the key {");

            msg.append("planId=" + planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanFanException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanFan[] findByPlanId_PrevAndNext(Long id, Long planId,
        OrderByComparator obc) throws NoSuchPlanFanException, SystemException {
        PlanFan planFan = findByPrimaryKey(id);

        int count = countByPlanId(planId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append("FROM com.ext.portlet.plans.model.PlanFan WHERE ");

            if (planId == null) {
                query.append("planId IS NULL");
            } else {
                query.append("planId = ?");
            }

            query.append(" AND deleted is null ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("created ASC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (planId != null) {
                qPos.add(planId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, planFan);

            PlanFan[] array = new PlanFanImpl[3];

            array[0] = (PlanFan) objArray[0];
            array[1] = (PlanFan) objArray[1];
            array[2] = (PlanFan) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<PlanFan> findByUserId(Long userId) throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        List<PlanFan> list = (List<PlanFan>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanFan WHERE ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" AND deleted is null ");

                query.append("ORDER BY ");

                query.append("created ASC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanFan>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_USERID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<PlanFan> findByUserId(Long userId, int start, int end)
        throws SystemException {
        return findByUserId(userId, start, end, null);
    }

    public List<PlanFan> findByUserId(Long userId, int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                userId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanFan> list = (List<PlanFan>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_USERID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanFan WHERE ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" AND deleted is null ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("created ASC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                list = (List<PlanFan>) QueryUtil.list(q, getDialect(), start,
                        end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanFan>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_USERID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public PlanFan findByUserId_First(Long userId, OrderByComparator obc)
        throws NoSuchPlanFanException, SystemException {
        List<PlanFan> list = findByUserId(userId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanFan exists with the key {");

            msg.append("userId=" + userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanFanException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanFan findByUserId_Last(Long userId, OrderByComparator obc)
        throws NoSuchPlanFanException, SystemException {
        int count = countByUserId(userId);

        List<PlanFan> list = findByUserId(userId, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanFan exists with the key {");

            msg.append("userId=" + userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanFanException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanFan[] findByUserId_PrevAndNext(Long id, Long userId,
        OrderByComparator obc) throws NoSuchPlanFanException, SystemException {
        PlanFan planFan = findByPrimaryKey(id);

        int count = countByUserId(userId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append("FROM com.ext.portlet.plans.model.PlanFan WHERE ");

            if (userId == null) {
                query.append("userId IS NULL");
            } else {
                query.append("userId = ?");
            }

            query.append(" AND deleted is null ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("created ASC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (userId != null) {
                qPos.add(userId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, planFan);

            PlanFan[] array = new PlanFanImpl[3];

            array[0] = (PlanFan) objArray[0];
            array[1] = (PlanFan) objArray[1];
            array[2] = (PlanFan) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanFan findByPlanIdUserId(Long planId, Long userId)
        throws NoSuchPlanFanException, SystemException {
        PlanFan planFan = fetchByPlanIdUserId(planId, userId);

        if (planFan == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanFan exists with the key {");

            msg.append("planId=" + planId);

            msg.append(", ");
            msg.append("userId=" + userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanFanException(msg.toString());
        }

        return planFan;
    }

    public PlanFan fetchByPlanIdUserId(Long planId, Long userId)
        throws SystemException {
        return fetchByPlanIdUserId(planId, userId, true);
    }

    public PlanFan fetchByPlanIdUserId(Long planId, Long userId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { planId, userId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanFan WHERE ");

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

                query.append(" AND deleted is null ");

                query.append("ORDER BY ");

                query.append("created ASC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                List<PlanFan> list = q.list();

                result = list;

                PlanFan planFan = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
                        finderArgs, list);
                } else {
                    planFan = list.get(0);

                    cacheResult(planFan);

                    if ((planFan.getPlanId() == null) ||
                            !planFan.getPlanId().equals(planId) ||
                            (planFan.getUserId() == null) ||
                            !planFan.getUserId().equals(userId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
                            finderArgs, planFan);
                    }
                }

                return planFan;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
                        finderArgs, new ArrayList<PlanFan>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (PlanFan) result;
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

    public List<PlanFan> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanFan> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanFan> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanFan> list = (List<PlanFan>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanFan ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("created ASC");
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlanFan>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanFan>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanFan>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByPlanId(Long planId) throws SystemException {
        for (PlanFan planFan : findByPlanId(planId)) {
            remove(planFan);
        }
    }

    public void removeByUserId(Long userId) throws SystemException {
        for (PlanFan planFan : findByUserId(userId)) {
            remove(planFan);
        }
    }

    public void removeByPlanIdUserId(Long planId, Long userId)
        throws NoSuchPlanFanException, SystemException {
        PlanFan planFan = findByPlanIdUserId(planId, userId);

        remove(planFan);
    }

    public void removeAll() throws SystemException {
        for (PlanFan planFan : findAll()) {
            remove(planFan);
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
                query.append("FROM com.ext.portlet.plans.model.PlanFan WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" AND deleted is null ");

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

    public int countByUserId(Long userId) throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append("FROM com.ext.portlet.plans.model.PlanFan WHERE ");

                if (userId == null) {
                    query.append("userId IS NULL");
                } else {
                    query.append("userId = ?");
                }

                query.append(" AND deleted is null ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

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

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByPlanIdUserId(Long planId, Long userId)
        throws SystemException {
        Object[] finderArgs = new Object[] { planId, userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PLANIDUSERID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append("FROM com.ext.portlet.plans.model.PlanFan WHERE ");

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

                query.append(" AND deleted is null ");

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

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANIDUSERID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanFan");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanFan")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanFan>> listenersList = new ArrayList<ModelListener<PlanFan>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanFan>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
