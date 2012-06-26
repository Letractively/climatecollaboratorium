package com.ext.portlet.contests.service.persistence;

import com.ext.portlet.contests.NoSuchContestException;
import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.impl.ContestImpl;
import com.ext.portlet.contests.model.impl.ContestModelImpl;

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


public class ContestPersistenceImpl extends BasePersistenceImpl
    implements ContestPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = ContestImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_TYPE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByType", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_TYPE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByType",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_TYPE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByType", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_CONTESTACTIVE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
            "fetchBycontestActive", new String[] { Boolean.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTACTIVE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countBycontestActive", new String[] { Boolean.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_ACTIVEFEATURED = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByActiveFeatured",
            new String[] { Boolean.class.getName(), Boolean.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_ACTIVEFEATURED = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByActiveFeatured",
            new String[] {
                Boolean.class.getName(), Boolean.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVEFEATURED = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByActiveFeatured",
            new String[] { Boolean.class.getName(), Boolean.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(ContestPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestPersistence.impl")
    protected com.ext.portlet.contests.service.persistence.ContestPersistence contestPersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestDebatePersistence.impl")
    protected com.ext.portlet.contests.service.persistence.ContestDebatePersistence contestDebatePersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestPhasePersistence.impl")
    protected com.ext.portlet.contests.service.persistence.ContestPhasePersistence contestPhasePersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestPhaseColumnPersistence.impl")
    protected com.ext.portlet.contests.service.persistence.ContestPhaseColumnPersistence contestPhaseColumnPersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestTeamMemberPersistence.impl")
    protected com.ext.portlet.contests.service.persistence.ContestTeamMemberPersistence contestTeamMemberPersistence;

    public void cacheResult(Contest contest) {
        EntityCacheUtil.putResult(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestImpl.class, contest.getPrimaryKey(), contest);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTACTIVE,
            new Object[] { contest.getContestActive() }, contest);
    }

    public void cacheResult(List<Contest> contests) {
        for (Contest contest : contests) {
            if (EntityCacheUtil.getResult(
                        ContestModelImpl.ENTITY_CACHE_ENABLED,
                        ContestImpl.class, contest.getPrimaryKey(), this) == null) {
                cacheResult(contest);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(ContestImpl.class.getName());
        EntityCacheUtil.clearCache(ContestImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public Contest create(Long ContestPK) {
        Contest contest = new ContestImpl();

        contest.setNew(true);
        contest.setPrimaryKey(ContestPK);

        return contest;
    }

    public Contest remove(Long ContestPK)
        throws NoSuchContestException, SystemException {
        Session session = null;

        try {
            session = openSession();

            Contest contest = (Contest) session.get(ContestImpl.class, ContestPK);

            if (contest == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No Contest exists with the primary key " +
                        ContestPK);
                }

                throw new NoSuchContestException(
                    "No Contest exists with the primary key " + ContestPK);
            }

            return remove(contest);
        } catch (NoSuchContestException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public Contest remove(Contest contest) throws SystemException {
        for (ModelListener<Contest> listener : listeners) {
            listener.onBeforeRemove(contest);
        }

        contest = removeImpl(contest);

        for (ModelListener<Contest> listener : listeners) {
            listener.onAfterRemove(contest);
        }

        return contest;
    }

    protected Contest removeImpl(Contest contest) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (contest.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(ContestImpl.class,
                        contest.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(contest);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        ContestModelImpl contestModelImpl = (ContestModelImpl) contest;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTACTIVE,
            new Object[] { contestModelImpl.getOriginalContestActive() });

        EntityCacheUtil.removeResult(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestImpl.class, contest.getPrimaryKey());

        return contest;
    }

    /**
     * @deprecated Use <code>update(Contest contest, boolean merge)</code>.
     */
    public Contest update(Contest contest) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(Contest contest) method. Use update(Contest contest, boolean merge) instead.");
        }

        return update(contest, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                contest the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when contest is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public Contest update(Contest contest, boolean merge)
        throws SystemException {
        boolean isNew = contest.isNew();

        for (ModelListener<Contest> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(contest);
            } else {
                listener.onBeforeUpdate(contest);
            }
        }

        contest = updateImpl(contest, merge);

        for (ModelListener<Contest> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(contest);
            } else {
                listener.onAfterUpdate(contest);
            }
        }

        return contest;
    }

    public Contest updateImpl(com.ext.portlet.contests.model.Contest contest,
        boolean merge) throws SystemException {
        boolean isNew = contest.isNew();

        ContestModelImpl contestModelImpl = (ContestModelImpl) contest;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, contest, merge);

            contest.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestImpl.class, contest.getPrimaryKey(), contest);

        if (!isNew &&
                (!Validator.equals(contest.getContestActive(),
                    contestModelImpl.getOriginalContestActive()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTACTIVE,
                new Object[] { contestModelImpl.getOriginalContestActive() });
        }

        if (isNew ||
                (!Validator.equals(contest.getContestActive(),
                    contestModelImpl.getOriginalContestActive()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTACTIVE,
                new Object[] { contest.getContestActive() }, contest);
        }

        return contest;
    }

    public Contest findByPrimaryKey(Long ContestPK)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByPrimaryKey(ContestPK);

        if (contest == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No Contest exists with the primary key " +
                    ContestPK);
            }

            throw new NoSuchContestException(
                "No Contest exists with the primary key " + ContestPK);
        }

        return contest;
    }

    public Contest fetchByPrimaryKey(Long ContestPK) throws SystemException {
        Contest contest = (Contest) EntityCacheUtil.getResult(ContestModelImpl.ENTITY_CACHE_ENABLED,
                ContestImpl.class, ContestPK, this);

        if (contest == null) {
            Session session = null;

            try {
                session = openSession();

                contest = (Contest) session.get(ContestImpl.class, ContestPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (contest != null) {
                    cacheResult(contest);
                }

                closeSession(session);
            }
        }

        return contest;
    }

    public List<Contest> findByType(Long PlanTypeId) throws SystemException {
        Object[] finderArgs = new Object[] { PlanTypeId };

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_TYPE,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.contests.model.Contest WHERE ");

                if (PlanTypeId == null) {
                    query.append("PlanTypeId IS NULL");
                } else {
                    query.append("PlanTypeId = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("weight ASC, ");
                query.append("created ASC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (PlanTypeId != null) {
                    qPos.add(PlanTypeId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<Contest>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_TYPE, finderArgs,
                    list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<Contest> findByType(Long PlanTypeId, int start, int end)
        throws SystemException {
        return findByType(PlanTypeId, start, end, null);
    }

    public List<Contest> findByType(Long PlanTypeId, int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                PlanTypeId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_TYPE,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.contests.model.Contest WHERE ");

                if (PlanTypeId == null) {
                    query.append("PlanTypeId IS NULL");
                } else {
                    query.append("PlanTypeId = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("weight ASC, ");
                    query.append("created ASC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (PlanTypeId != null) {
                    qPos.add(PlanTypeId.longValue());
                }

                list = (List<Contest>) QueryUtil.list(q, getDialect(), start,
                        end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<Contest>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_TYPE,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public Contest findByType_First(Long PlanTypeId, OrderByComparator obc)
        throws NoSuchContestException, SystemException {
        List<Contest> list = findByType(PlanTypeId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No Contest exists with the key {");

            msg.append("PlanTypeId=" + PlanTypeId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public Contest findByType_Last(Long PlanTypeId, OrderByComparator obc)
        throws NoSuchContestException, SystemException {
        int count = countByType(PlanTypeId);

        List<Contest> list = findByType(PlanTypeId, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No Contest exists with the key {");

            msg.append("PlanTypeId=" + PlanTypeId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public Contest[] findByType_PrevAndNext(Long ContestPK, Long PlanTypeId,
        OrderByComparator obc) throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        int count = countByType(PlanTypeId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append("FROM com.ext.portlet.contests.model.Contest WHERE ");

            if (PlanTypeId == null) {
                query.append("PlanTypeId IS NULL");
            } else {
                query.append("PlanTypeId = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("weight ASC, ");
                query.append("created ASC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (PlanTypeId != null) {
                qPos.add(PlanTypeId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, contest);

            Contest[] array = new ContestImpl[3];

            array[0] = (Contest) objArray[0];
            array[1] = (Contest) objArray[1];
            array[2] = (Contest) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public Contest findBycontestActive(Boolean contestActive)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchBycontestActive(contestActive);

        if (contest == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No Contest exists with the key {");

            msg.append("contestActive=" + contestActive);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchContestException(msg.toString());
        }

        return contest;
    }

    public Contest fetchBycontestActive(Boolean contestActive)
        throws SystemException {
        return fetchBycontestActive(contestActive, true);
    }

    public Contest fetchBycontestActive(Boolean contestActive,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { contestActive };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CONTESTACTIVE,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.contests.model.Contest WHERE ");

                if (contestActive == null) {
                    query.append("contestActive IS NULL");
                } else {
                    query.append("contestActive = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("weight ASC, ");
                query.append("created ASC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (contestActive != null) {
                    qPos.add(contestActive.booleanValue());
                }

                List<Contest> list = q.list();

                result = list;

                Contest contest = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTACTIVE,
                        finderArgs, list);
                } else {
                    contest = list.get(0);

                    cacheResult(contest);

                    if ((contest.getContestActive() == null) ||
                            !contest.getContestActive().equals(contestActive)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTACTIVE,
                            finderArgs, contest);
                    }
                }

                return contest;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTACTIVE,
                        finderArgs, new ArrayList<Contest>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (Contest) result;
            }
        }
    }

    public List<Contest> findByActiveFeatured(Boolean contestActive,
        Boolean featured) throws SystemException {
        Object[] finderArgs = new Object[] { contestActive, featured };

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ACTIVEFEATURED,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.contests.model.Contest WHERE ");

                if (contestActive == null) {
                    query.append("contestActive IS NULL");
                } else {
                    query.append("contestActive = ?");
                }

                query.append(" AND ");

                if (featured == null) {
                    query.append("featured_ IS NULL");
                } else {
                    query.append("featured_ = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("weight ASC, ");
                query.append("created ASC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (contestActive != null) {
                    qPos.add(contestActive.booleanValue());
                }

                if (featured != null) {
                    qPos.add(featured.booleanValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<Contest>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ACTIVEFEATURED,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<Contest> findByActiveFeatured(Boolean contestActive,
        Boolean featured, int start, int end) throws SystemException {
        return findByActiveFeatured(contestActive, featured, start, end, null);
    }

    public List<Contest> findByActiveFeatured(Boolean contestActive,
        Boolean featured, int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                contestActive,
                
                featured,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_ACTIVEFEATURED,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.contests.model.Contest WHERE ");

                if (contestActive == null) {
                    query.append("contestActive IS NULL");
                } else {
                    query.append("contestActive = ?");
                }

                query.append(" AND ");

                if (featured == null) {
                    query.append("featured_ IS NULL");
                } else {
                    query.append("featured_ = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("weight ASC, ");
                    query.append("created ASC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (contestActive != null) {
                    qPos.add(contestActive.booleanValue());
                }

                if (featured != null) {
                    qPos.add(featured.booleanValue());
                }

                list = (List<Contest>) QueryUtil.list(q, getDialect(), start,
                        end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<Contest>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_ACTIVEFEATURED,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public Contest findByActiveFeatured_First(Boolean contestActive,
        Boolean featured, OrderByComparator obc)
        throws NoSuchContestException, SystemException {
        List<Contest> list = findByActiveFeatured(contestActive, featured, 0,
                1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No Contest exists with the key {");

            msg.append("contestActive=" + contestActive);

            msg.append(", ");
            msg.append("featured=" + featured);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public Contest findByActiveFeatured_Last(Boolean contestActive,
        Boolean featured, OrderByComparator obc)
        throws NoSuchContestException, SystemException {
        int count = countByActiveFeatured(contestActive, featured);

        List<Contest> list = findByActiveFeatured(contestActive, featured,
                count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No Contest exists with the key {");

            msg.append("contestActive=" + contestActive);

            msg.append(", ");
            msg.append("featured=" + featured);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public Contest[] findByActiveFeatured_PrevAndNext(Long ContestPK,
        Boolean contestActive, Boolean featured, OrderByComparator obc)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        int count = countByActiveFeatured(contestActive, featured);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append("FROM com.ext.portlet.contests.model.Contest WHERE ");

            if (contestActive == null) {
                query.append("contestActive IS NULL");
            } else {
                query.append("contestActive = ?");
            }

            query.append(" AND ");

            if (featured == null) {
                query.append("featured_ IS NULL");
            } else {
                query.append("featured_ = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("weight ASC, ");
                query.append("created ASC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (contestActive != null) {
                qPos.add(contestActive.booleanValue());
            }

            if (featured != null) {
                qPos.add(featured.booleanValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, contest);

            Contest[] array = new ContestImpl[3];

            array[0] = (Contest) objArray[0];
            array[1] = (Contest) objArray[1];
            array[2] = (Contest) objArray[2];

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

    public List<Contest> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<Contest> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    public List<Contest> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.contests.model.Contest ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("weight ASC, ");
                    query.append("created ASC");
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<Contest>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByType(Long PlanTypeId) throws SystemException {
        for (Contest contest : findByType(PlanTypeId)) {
            remove(contest);
        }
    }

    public void removeBycontestActive(Boolean contestActive)
        throws NoSuchContestException, SystemException {
        Contest contest = findBycontestActive(contestActive);

        remove(contest);
    }

    public void removeByActiveFeatured(Boolean contestActive, Boolean featured)
        throws SystemException {
        for (Contest contest : findByActiveFeatured(contestActive, featured)) {
            remove(contest);
        }
    }

    public void removeAll() throws SystemException {
        for (Contest contest : findAll()) {
            remove(contest);
        }
    }

    public int countByType(Long PlanTypeId) throws SystemException {
        Object[] finderArgs = new Object[] { PlanTypeId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TYPE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.contests.model.Contest WHERE ");

                if (PlanTypeId == null) {
                    query.append("PlanTypeId IS NULL");
                } else {
                    query.append("PlanTypeId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (PlanTypeId != null) {
                    qPos.add(PlanTypeId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TYPE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countBycontestActive(Boolean contestActive)
        throws SystemException {
        Object[] finderArgs = new Object[] { contestActive };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CONTESTACTIVE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.contests.model.Contest WHERE ");

                if (contestActive == null) {
                    query.append("contestActive IS NULL");
                } else {
                    query.append("contestActive = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (contestActive != null) {
                    qPos.add(contestActive.booleanValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTESTACTIVE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByActiveFeatured(Boolean contestActive, Boolean featured)
        throws SystemException {
        Object[] finderArgs = new Object[] { contestActive, featured };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACTIVEFEATURED,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.contests.model.Contest WHERE ");

                if (contestActive == null) {
                    query.append("contestActive IS NULL");
                } else {
                    query.append("contestActive = ?");
                }

                query.append(" AND ");

                if (featured == null) {
                    query.append("featured_ IS NULL");
                } else {
                    query.append("featured_ = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (contestActive != null) {
                    qPos.add(contestActive.booleanValue());
                }

                if (featured != null) {
                    qPos.add(featured.booleanValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACTIVEFEATURED,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.contests.model.Contest");

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
                        "value.object.listener.com.ext.portlet.contests.model.Contest")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<Contest>> listenersList = new ArrayList<ModelListener<Contest>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<Contest>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
