package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanVoteException;
import com.ext.portlet.plans.model.PlanVote;
import com.ext.portlet.plans.model.impl.PlanVoteImpl;
import com.ext.portlet.plans.model.impl.PlanVoteModelImpl;

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


public class PlanVotePersistenceImpl extends BasePersistenceImpl
    implements PlanVotePersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanVoteImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_CONTESTID = new FinderPath(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
            "fetchBycontestId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTID = new FinderPath(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countBycontestId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_PLANID = new FinderPath(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
            "fetchByPlanId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANID = new FinderPath(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByPlanId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_CONTESTIDUSERID = new FinderPath(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
            "fetchByContestIdUserId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTIDUSERID = new FinderPath(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByContestIdUserId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanVotePersistenceImpl.class);
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

    public void cacheResult(PlanVote planVote) {
        EntityCacheUtil.putResult(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteImpl.class, planVote.getPrimaryKey(), planVote);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTID,
            new Object[] { planVote.getContestId() }, planVote);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANID,
            new Object[] { planVote.getPlanId() }, planVote);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDUSERID,
            new Object[] { planVote.getContestId(), planVote.getUserId() },
            planVote);
    }

    public void cacheResult(List<PlanVote> planVotes) {
        for (PlanVote planVote : planVotes) {
            if (EntityCacheUtil.getResult(
                        PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
                        PlanVoteImpl.class, planVote.getPrimaryKey(), this) == null) {
                cacheResult(planVote);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanVoteImpl.class.getName());
        EntityCacheUtil.clearCache(PlanVoteImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanVote create(PlanVotePK planVotePK) {
        PlanVote planVote = new PlanVoteImpl();

        planVote.setNew(true);
        planVote.setPrimaryKey(planVotePK);

        return planVote;
    }

    public PlanVote remove(PlanVotePK planVotePK)
        throws NoSuchPlanVoteException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanVote planVote = (PlanVote) session.get(PlanVoteImpl.class,
                    planVotePK);

            if (planVote == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No PlanVote exists with the primary key " +
                        planVotePK);
                }

                throw new NoSuchPlanVoteException(
                    "No PlanVote exists with the primary key " + planVotePK);
            }

            return remove(planVote);
        } catch (NoSuchPlanVoteException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanVote remove(PlanVote planVote) throws SystemException {
        for (ModelListener<PlanVote> listener : listeners) {
            listener.onBeforeRemove(planVote);
        }

        planVote = removeImpl(planVote);

        for (ModelListener<PlanVote> listener : listeners) {
            listener.onAfterRemove(planVote);
        }

        return planVote;
    }

    protected PlanVote removeImpl(PlanVote planVote) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planVote.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanVoteImpl.class,
                        planVote.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planVote);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        PlanVoteModelImpl planVoteModelImpl = (PlanVoteModelImpl) planVote;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTID,
            new Object[] { planVoteModelImpl.getOriginalContestId() });

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANID,
            new Object[] { planVoteModelImpl.getOriginalPlanId() });

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTIDUSERID,
            new Object[] {
                planVoteModelImpl.getOriginalContestId(),
                
            planVoteModelImpl.getOriginalUserId()
            });

        EntityCacheUtil.removeResult(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteImpl.class, planVote.getPrimaryKey());

        return planVote;
    }

    /**
     * @deprecated Use <code>update(PlanVote planVote, boolean merge)</code>.
     */
    public PlanVote update(PlanVote planVote) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanVote planVote) method. Use update(PlanVote planVote, boolean merge) instead.");
        }

        return update(planVote, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planVote the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planVote is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanVote update(PlanVote planVote, boolean merge)
        throws SystemException {
        boolean isNew = planVote.isNew();

        for (ModelListener<PlanVote> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planVote);
            } else {
                listener.onBeforeUpdate(planVote);
            }
        }

        planVote = updateImpl(planVote, merge);

        for (ModelListener<PlanVote> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planVote);
            } else {
                listener.onAfterUpdate(planVote);
            }
        }

        return planVote;
    }

    public PlanVote updateImpl(com.ext.portlet.plans.model.PlanVote planVote,
        boolean merge) throws SystemException {
        boolean isNew = planVote.isNew();

        PlanVoteModelImpl planVoteModelImpl = (PlanVoteModelImpl) planVote;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planVote, merge);

            planVote.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteImpl.class, planVote.getPrimaryKey(), planVote);

        if (!isNew &&
                (!Validator.equals(planVote.getContestId(),
                    planVoteModelImpl.getOriginalContestId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTID,
                new Object[] { planVoteModelImpl.getOriginalContestId() });
        }

        if (isNew ||
                (!Validator.equals(planVote.getContestId(),
                    planVoteModelImpl.getOriginalContestId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTID,
                new Object[] { planVote.getContestId() }, planVote);
        }

        if (!isNew &&
                (!Validator.equals(planVote.getPlanId(),
                    planVoteModelImpl.getOriginalPlanId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANID,
                new Object[] { planVoteModelImpl.getOriginalPlanId() });
        }

        if (isNew ||
                (!Validator.equals(planVote.getPlanId(),
                    planVoteModelImpl.getOriginalPlanId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANID,
                new Object[] { planVote.getPlanId() }, planVote);
        }

        if (!isNew &&
                (!Validator.equals(planVote.getContestId(),
                    planVoteModelImpl.getOriginalContestId()) ||
                !Validator.equals(planVote.getUserId(),
                    planVoteModelImpl.getOriginalUserId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTIDUSERID,
                new Object[] {
                    planVoteModelImpl.getOriginalContestId(),
                    
                planVoteModelImpl.getOriginalUserId()
                });
        }

        if (isNew ||
                (!Validator.equals(planVote.getContestId(),
                    planVoteModelImpl.getOriginalContestId()) ||
                !Validator.equals(planVote.getUserId(),
                    planVoteModelImpl.getOriginalUserId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDUSERID,
                new Object[] { planVote.getContestId(), planVote.getUserId() },
                planVote);
        }

        return planVote;
    }

    public PlanVote findByPrimaryKey(PlanVotePK planVotePK)
        throws NoSuchPlanVoteException, SystemException {
        PlanVote planVote = fetchByPrimaryKey(planVotePK);

        if (planVote == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanVote exists with the primary key " +
                    planVotePK);
            }

            throw new NoSuchPlanVoteException(
                "No PlanVote exists with the primary key " + planVotePK);
        }

        return planVote;
    }

    public PlanVote fetchByPrimaryKey(PlanVotePK planVotePK)
        throws SystemException {
        PlanVote planVote = (PlanVote) EntityCacheUtil.getResult(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
                PlanVoteImpl.class, planVotePK, this);

        if (planVote == null) {
            Session session = null;

            try {
                session = openSession();

                planVote = (PlanVote) session.get(PlanVoteImpl.class, planVotePK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planVote != null) {
                    cacheResult(planVote);
                }

                closeSession(session);
            }
        }

        return planVote;
    }

    public PlanVote findBycontestId(Long contestId)
        throws NoSuchPlanVoteException, SystemException {
        PlanVote planVote = fetchBycontestId(contestId);

        if (planVote == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanVote exists with the key {");

            msg.append("contestId=" + contestId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanVoteException(msg.toString());
        }

        return planVote;
    }

    public PlanVote fetchBycontestId(Long contestId) throws SystemException {
        return fetchBycontestId(contestId, true);
    }

    public PlanVote fetchBycontestId(Long contestId, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { contestId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CONTESTID,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanVote WHERE ");

                if (contestId == null) {
                    query.append("contestId IS NULL");
                } else {
                    query.append("contestId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (contestId != null) {
                    qPos.add(contestId.longValue());
                }

                List<PlanVote> list = q.list();

                result = list;

                PlanVote planVote = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTID,
                        finderArgs, list);
                } else {
                    planVote = list.get(0);

                    cacheResult(planVote);

                    if ((planVote.getContestId() == null) ||
                            !planVote.getContestId().equals(contestId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTID,
                            finderArgs, planVote);
                    }
                }

                return planVote;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTID,
                        finderArgs, new ArrayList<PlanVote>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (PlanVote) result;
            }
        }
    }

    public PlanVote findByPlanId(Long planId)
        throws NoSuchPlanVoteException, SystemException {
        PlanVote planVote = fetchByPlanId(planId);

        if (planVote == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanVote exists with the key {");

            msg.append("planId=" + planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanVoteException(msg.toString());
        }

        return planVote;
    }

    public PlanVote fetchByPlanId(Long planId) throws SystemException {
        return fetchByPlanId(planId, true);
    }

    public PlanVote fetchByPlanId(Long planId, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PLANID,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanVote WHERE ");

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

                List<PlanVote> list = q.list();

                result = list;

                PlanVote planVote = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANID,
                        finderArgs, list);
                } else {
                    planVote = list.get(0);

                    cacheResult(planVote);

                    if ((planVote.getPlanId() == null) ||
                            !planVote.getPlanId().equals(planId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANID,
                            finderArgs, planVote);
                    }
                }

                return planVote;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANID,
                        finderArgs, new ArrayList<PlanVote>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (PlanVote) result;
            }
        }
    }

    public PlanVote findByContestIdUserId(Long contestId, Long userId)
        throws NoSuchPlanVoteException, SystemException {
        PlanVote planVote = fetchByContestIdUserId(contestId, userId);

        if (planVote == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanVote exists with the key {");

            msg.append("contestId=" + contestId);

            msg.append(", ");
            msg.append("userId=" + userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanVoteException(msg.toString());
        }

        return planVote;
    }

    public PlanVote fetchByContestIdUserId(Long contestId, Long userId)
        throws SystemException {
        return fetchByContestIdUserId(contestId, userId, true);
    }

    public PlanVote fetchByContestIdUserId(Long contestId, Long userId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { contestId, userId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CONTESTIDUSERID,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanVote WHERE ");

                if (contestId == null) {
                    query.append("contestId IS NULL");
                } else {
                    query.append("contestId = ?");
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

                if (contestId != null) {
                    qPos.add(contestId.longValue());
                }

                if (userId != null) {
                    qPos.add(userId.longValue());
                }

                List<PlanVote> list = q.list();

                result = list;

                PlanVote planVote = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDUSERID,
                        finderArgs, list);
                } else {
                    planVote = list.get(0);

                    cacheResult(planVote);

                    if ((planVote.getContestId() == null) ||
                            !planVote.getContestId().equals(contestId) ||
                            (planVote.getUserId() == null) ||
                            !planVote.getUserId().equals(userId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDUSERID,
                            finderArgs, planVote);
                    }
                }

                return planVote;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDUSERID,
                        finderArgs, new ArrayList<PlanVote>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (PlanVote) result;
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

    public List<PlanVote> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanVote> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanVote> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanVote> list = (List<PlanVote>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanVote ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlanVote>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanVote>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanVote>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeBycontestId(Long contestId)
        throws NoSuchPlanVoteException, SystemException {
        PlanVote planVote = findBycontestId(contestId);

        remove(planVote);
    }

    public void removeByPlanId(Long planId)
        throws NoSuchPlanVoteException, SystemException {
        PlanVote planVote = findByPlanId(planId);

        remove(planVote);
    }

    public void removeByContestIdUserId(Long contestId, Long userId)
        throws NoSuchPlanVoteException, SystemException {
        PlanVote planVote = findByContestIdUserId(contestId, userId);

        remove(planVote);
    }

    public void removeAll() throws SystemException {
        for (PlanVote planVote : findAll()) {
            remove(planVote);
        }
    }

    public int countBycontestId(Long contestId) throws SystemException {
        Object[] finderArgs = new Object[] { contestId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CONTESTID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append("FROM com.ext.portlet.plans.model.PlanVote WHERE ");

                if (contestId == null) {
                    query.append("contestId IS NULL");
                } else {
                    query.append("contestId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (contestId != null) {
                    qPos.add(contestId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTESTID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
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
                query.append("FROM com.ext.portlet.plans.model.PlanVote WHERE ");

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

    public int countByContestIdUserId(Long contestId, Long userId)
        throws SystemException {
        Object[] finderArgs = new Object[] { contestId, userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CONTESTIDUSERID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append("FROM com.ext.portlet.plans.model.PlanVote WHERE ");

                if (contestId == null) {
                    query.append("contestId IS NULL");
                } else {
                    query.append("contestId = ?");
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

                if (contestId != null) {
                    qPos.add(contestId.longValue());
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

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTESTIDUSERID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanVote");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanVote")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanVote>> listenersList = new ArrayList<ModelListener<PlanVote>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanVote>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
