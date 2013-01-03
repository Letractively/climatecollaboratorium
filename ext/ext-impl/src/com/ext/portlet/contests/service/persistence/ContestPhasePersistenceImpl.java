package com.ext.portlet.contests.service.persistence;

import com.ext.portlet.contests.NoSuchContestPhaseException;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.model.impl.ContestPhaseImpl;
import com.ext.portlet.contests.model.impl.ContestPhaseModelImpl;

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
import com.liferay.portal.kernel.util.CalendarUtil;
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
import java.util.Date;
import java.util.List;


public class ContestPhasePersistenceImpl extends BasePersistenceImpl
    implements ContestPhasePersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = ContestPhaseImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_CONTESTIDSTARTEND = new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByContestIdStartEnd",
            new String[] {
                Long.class.getName(), Date.class.getName(), Date.class.getName()
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTIDSTARTEND = new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByContestIdStartEnd",
            new String[] {
                Long.class.getName(), Date.class.getName(), Date.class.getName()
            });
    public static final FinderPath FINDER_PATH_FIND_BY_CONTESTID = new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByContestId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_CONTESTID = new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByContestId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTID = new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByContestId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(ContestPhasePersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestPersistence.impl")
    protected com.ext.portlet.contests.service.persistence.ContestPersistence contestPersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestDebatePersistence.impl")
    protected com.ext.portlet.contests.service.persistence.ContestDebatePersistence contestDebatePersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestPhasePersistence.impl")
    protected com.ext.portlet.contests.service.persistence.ContestPhasePersistence contestPhasePersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestPhaseStatusPersistence.impl")
    protected com.ext.portlet.contests.service.persistence.ContestPhaseStatusPersistence contestPhaseStatusPersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestPhaseColumnPersistence.impl")
    protected com.ext.portlet.contests.service.persistence.ContestPhaseColumnPersistence contestPhaseColumnPersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestTeamMemberPersistence.impl")
    protected com.ext.portlet.contests.service.persistence.ContestTeamMemberPersistence contestTeamMemberPersistence;

    public void cacheResult(ContestPhase contestPhase) {
        EntityCacheUtil.putResult(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseImpl.class, contestPhase.getPrimaryKey(), contestPhase);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDSTARTEND,
            new Object[] {
                contestPhase.getContestPK(),
                
            contestPhase.getPhaseStartDate(),
                
            contestPhase.getPhaseEndDate()
            }, contestPhase);
    }

    public void cacheResult(List<ContestPhase> contestPhases) {
        for (ContestPhase contestPhase : contestPhases) {
            if (EntityCacheUtil.getResult(
                        ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
                        ContestPhaseImpl.class, contestPhase.getPrimaryKey(),
                        this) == null) {
                cacheResult(contestPhase);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(ContestPhaseImpl.class.getName());
        EntityCacheUtil.clearCache(ContestPhaseImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public ContestPhase create(Long ContestPhasePK) {
        ContestPhase contestPhase = new ContestPhaseImpl();

        contestPhase.setNew(true);
        contestPhase.setPrimaryKey(ContestPhasePK);

        return contestPhase;
    }

    public ContestPhase remove(Long ContestPhasePK)
        throws NoSuchContestPhaseException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ContestPhase contestPhase = (ContestPhase) session.get(ContestPhaseImpl.class,
                    ContestPhasePK);

            if (contestPhase == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No ContestPhase exists with the primary key " +
                        ContestPhasePK);
                }

                throw new NoSuchContestPhaseException(
                    "No ContestPhase exists with the primary key " +
                    ContestPhasePK);
            }

            return remove(contestPhase);
        } catch (NoSuchContestPhaseException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ContestPhase remove(ContestPhase contestPhase)
        throws SystemException {
        for (ModelListener<ContestPhase> listener : listeners) {
            listener.onBeforeRemove(contestPhase);
        }

        contestPhase = removeImpl(contestPhase);

        for (ModelListener<ContestPhase> listener : listeners) {
            listener.onAfterRemove(contestPhase);
        }

        return contestPhase;
    }

    protected ContestPhase removeImpl(ContestPhase contestPhase)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (contestPhase.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(ContestPhaseImpl.class,
                        contestPhase.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(contestPhase);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        ContestPhaseModelImpl contestPhaseModelImpl = (ContestPhaseModelImpl) contestPhase;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTIDSTARTEND,
            new Object[] {
                contestPhaseModelImpl.getOriginalContestPK(),
                
            contestPhaseModelImpl.getOriginalPhaseStartDate(),
                
            contestPhaseModelImpl.getOriginalPhaseEndDate()
            });

        EntityCacheUtil.removeResult(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseImpl.class, contestPhase.getPrimaryKey());

        return contestPhase;
    }

    /**
     * @deprecated Use <code>update(ContestPhase contestPhase, boolean merge)</code>.
     */
    public ContestPhase update(ContestPhase contestPhase)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(ContestPhase contestPhase) method. Use update(ContestPhase contestPhase, boolean merge) instead.");
        }

        return update(contestPhase, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                contestPhase the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when contestPhase is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public ContestPhase update(ContestPhase contestPhase, boolean merge)
        throws SystemException {
        boolean isNew = contestPhase.isNew();

        for (ModelListener<ContestPhase> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(contestPhase);
            } else {
                listener.onBeforeUpdate(contestPhase);
            }
        }

        contestPhase = updateImpl(contestPhase, merge);

        for (ModelListener<ContestPhase> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(contestPhase);
            } else {
                listener.onAfterUpdate(contestPhase);
            }
        }

        return contestPhase;
    }

    public ContestPhase updateImpl(
        com.ext.portlet.contests.model.ContestPhase contestPhase, boolean merge)
        throws SystemException {
        boolean isNew = contestPhase.isNew();

        ContestPhaseModelImpl contestPhaseModelImpl = (ContestPhaseModelImpl) contestPhase;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, contestPhase, merge);

            contestPhase.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseImpl.class, contestPhase.getPrimaryKey(), contestPhase);

        if (!isNew &&
                (!Validator.equals(contestPhase.getContestPK(),
                    contestPhaseModelImpl.getOriginalContestPK()) ||
                !Validator.equals(contestPhase.getPhaseStartDate(),
                    contestPhaseModelImpl.getOriginalPhaseStartDate()) ||
                !Validator.equals(contestPhase.getPhaseEndDate(),
                    contestPhaseModelImpl.getOriginalPhaseEndDate()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTIDSTARTEND,
                new Object[] {
                    contestPhaseModelImpl.getOriginalContestPK(),
                    
                contestPhaseModelImpl.getOriginalPhaseStartDate(),
                    
                contestPhaseModelImpl.getOriginalPhaseEndDate()
                });
        }

        if (isNew ||
                (!Validator.equals(contestPhase.getContestPK(),
                    contestPhaseModelImpl.getOriginalContestPK()) ||
                !Validator.equals(contestPhase.getPhaseStartDate(),
                    contestPhaseModelImpl.getOriginalPhaseStartDate()) ||
                !Validator.equals(contestPhase.getPhaseEndDate(),
                    contestPhaseModelImpl.getOriginalPhaseEndDate()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDSTARTEND,
                new Object[] {
                    contestPhase.getContestPK(),
                    
                contestPhase.getPhaseStartDate(),
                    
                contestPhase.getPhaseEndDate()
                }, contestPhase);
        }

        return contestPhase;
    }

    public ContestPhase findByPrimaryKey(Long ContestPhasePK)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = fetchByPrimaryKey(ContestPhasePK);

        if (contestPhase == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No ContestPhase exists with the primary key " +
                    ContestPhasePK);
            }

            throw new NoSuchContestPhaseException(
                "No ContestPhase exists with the primary key " +
                ContestPhasePK);
        }

        return contestPhase;
    }

    public ContestPhase fetchByPrimaryKey(Long ContestPhasePK)
        throws SystemException {
        ContestPhase contestPhase = (ContestPhase) EntityCacheUtil.getResult(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
                ContestPhaseImpl.class, ContestPhasePK, this);

        if (contestPhase == null) {
            Session session = null;

            try {
                session = openSession();

                contestPhase = (ContestPhase) session.get(ContestPhaseImpl.class,
                        ContestPhasePK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (contestPhase != null) {
                    cacheResult(contestPhase);
                }

                closeSession(session);
            }
        }

        return contestPhase;
    }

    public ContestPhase findByContestIdStartEnd(Long ContestPK,
        Date PhaseStartDate, Date PhaseEndDate)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = fetchByContestIdStartEnd(ContestPK,
                PhaseStartDate, PhaseEndDate);

        if (contestPhase == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ContestPhase exists with the key {");

            msg.append("ContestPK=" + ContestPK);

            msg.append(", ");
            msg.append("PhaseStartDate=" + PhaseStartDate);

            msg.append(", ");
            msg.append("PhaseEndDate=" + PhaseEndDate);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchContestPhaseException(msg.toString());
        }

        return contestPhase;
    }

    public ContestPhase fetchByContestIdStartEnd(Long ContestPK,
        Date PhaseStartDate, Date PhaseEndDate) throws SystemException {
        return fetchByContestIdStartEnd(ContestPK, PhaseStartDate,
            PhaseEndDate, true);
    }

    public ContestPhase fetchByContestIdStartEnd(Long ContestPK,
        Date PhaseStartDate, Date PhaseEndDate, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                ContestPK,
                
                PhaseStartDate,
                
                PhaseEndDate
            };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CONTESTIDSTARTEND,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.contests.model.ContestPhase WHERE ");

                if (ContestPK == null) {
                    query.append("ContestPK IS NULL");
                } else {
                    query.append("ContestPK = ?");
                }

                query.append(" AND ");

                if (PhaseStartDate == null) {
                    query.append("PhaseStartDate <= null");
                } else {
                    query.append("PhaseStartDate <= ?");
                }

                query.append(" AND ");

                if (PhaseEndDate == null) {
                    query.append("PhaseEndDate >= null");
                } else {
                    query.append("PhaseEndDate >= ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("PhaseStartDate ASC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (ContestPK != null) {
                    qPos.add(ContestPK.longValue());
                }

                if (PhaseStartDate != null) {
                    qPos.add(CalendarUtil.getTimestamp(PhaseStartDate));
                }

                if (PhaseEndDate != null) {
                    qPos.add(CalendarUtil.getTimestamp(PhaseEndDate));
                }

                List<ContestPhase> list = q.list();

                result = list;

                ContestPhase contestPhase = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDSTARTEND,
                        finderArgs, list);
                } else {
                    contestPhase = list.get(0);

                    cacheResult(contestPhase);

                    if ((contestPhase.getContestPK() == null) ||
                            !contestPhase.getContestPK().equals(ContestPK) ||
                            (contestPhase.getPhaseStartDate() == null) ||
                            !contestPhase.getPhaseStartDate()
                                             .equals(PhaseStartDate) ||
                            (contestPhase.getPhaseEndDate() == null) ||
                            !contestPhase.getPhaseEndDate().equals(PhaseEndDate)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDSTARTEND,
                            finderArgs, contestPhase);
                    }
                }

                return contestPhase;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDSTARTEND,
                        finderArgs, new ArrayList<ContestPhase>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (ContestPhase) result;
            }
        }
    }

    public List<ContestPhase> findByContestId(Long ContestPK)
        throws SystemException {
        Object[] finderArgs = new Object[] { ContestPK };

        List<ContestPhase> list = (List<ContestPhase>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_CONTESTID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.contests.model.ContestPhase WHERE ");

                if (ContestPK == null) {
                    query.append("ContestPK IS NULL");
                } else {
                    query.append("ContestPK = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("PhaseStartDate ASC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (ContestPK != null) {
                    qPos.add(ContestPK.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ContestPhase>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_CONTESTID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<ContestPhase> findByContestId(Long ContestPK, int start, int end)
        throws SystemException {
        return findByContestId(ContestPK, start, end, null);
    }

    public List<ContestPhase> findByContestId(Long ContestPK, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                ContestPK,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ContestPhase> list = (List<ContestPhase>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_CONTESTID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.contests.model.ContestPhase WHERE ");

                if (ContestPK == null) {
                    query.append("ContestPK IS NULL");
                } else {
                    query.append("ContestPK = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("PhaseStartDate ASC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (ContestPK != null) {
                    qPos.add(ContestPK.longValue());
                }

                list = (List<ContestPhase>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ContestPhase>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_CONTESTID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public ContestPhase findByContestId_First(Long ContestPK,
        OrderByComparator obc)
        throws NoSuchContestPhaseException, SystemException {
        List<ContestPhase> list = findByContestId(ContestPK, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ContestPhase exists with the key {");

            msg.append("ContestPK=" + ContestPK);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestPhaseException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ContestPhase findByContestId_Last(Long ContestPK,
        OrderByComparator obc)
        throws NoSuchContestPhaseException, SystemException {
        int count = countByContestId(ContestPK);

        List<ContestPhase> list = findByContestId(ContestPK, count - 1, count,
                obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ContestPhase exists with the key {");

            msg.append("ContestPK=" + ContestPK);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestPhaseException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ContestPhase[] findByContestId_PrevAndNext(Long ContestPhasePK,
        Long ContestPK, OrderByComparator obc)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = findByPrimaryKey(ContestPhasePK);

        int count = countByContestId(ContestPK);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.contests.model.ContestPhase WHERE ");

            if (ContestPK == null) {
                query.append("ContestPK IS NULL");
            } else {
                query.append("ContestPK = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("PhaseStartDate ASC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (ContestPK != null) {
                qPos.add(ContestPK.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    contestPhase);

            ContestPhase[] array = new ContestPhaseImpl[3];

            array[0] = (ContestPhase) objArray[0];
            array[1] = (ContestPhase) objArray[1];
            array[2] = (ContestPhase) objArray[2];

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

    public List<ContestPhase> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<ContestPhase> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<ContestPhase> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ContestPhase> list = (List<ContestPhase>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.contests.model.ContestPhase ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("PhaseStartDate ASC");
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<ContestPhase>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ContestPhase>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ContestPhase>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByContestIdStartEnd(Long ContestPK, Date PhaseStartDate,
        Date PhaseEndDate) throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = findByContestIdStartEnd(ContestPK,
                PhaseStartDate, PhaseEndDate);

        remove(contestPhase);
    }

    public void removeByContestId(Long ContestPK) throws SystemException {
        for (ContestPhase contestPhase : findByContestId(ContestPK)) {
            remove(contestPhase);
        }
    }

    public void removeAll() throws SystemException {
        for (ContestPhase contestPhase : findAll()) {
            remove(contestPhase);
        }
    }

    public int countByContestIdStartEnd(Long ContestPK, Date PhaseStartDate,
        Date PhaseEndDate) throws SystemException {
        Object[] finderArgs = new Object[] {
                ContestPK,
                
                PhaseStartDate,
                
                PhaseEndDate
            };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CONTESTIDSTARTEND,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.contests.model.ContestPhase WHERE ");

                if (ContestPK == null) {
                    query.append("ContestPK IS NULL");
                } else {
                    query.append("ContestPK = ?");
                }

                query.append(" AND ");

                if (PhaseStartDate == null) {
                    query.append("PhaseStartDate <= null");
                } else {
                    query.append("PhaseStartDate <= ?");
                }

                query.append(" AND ");

                if (PhaseEndDate == null) {
                    query.append("PhaseEndDate >= null");
                } else {
                    query.append("PhaseEndDate >= ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (ContestPK != null) {
                    qPos.add(ContestPK.longValue());
                }

                if (PhaseStartDate != null) {
                    qPos.add(CalendarUtil.getTimestamp(PhaseStartDate));
                }

                if (PhaseEndDate != null) {
                    qPos.add(CalendarUtil.getTimestamp(PhaseEndDate));
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTESTIDSTARTEND,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByContestId(Long ContestPK) throws SystemException {
        Object[] finderArgs = new Object[] { ContestPK };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CONTESTID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.contests.model.ContestPhase WHERE ");

                if (ContestPK == null) {
                    query.append("ContestPK IS NULL");
                } else {
                    query.append("ContestPK = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (ContestPK != null) {
                    qPos.add(ContestPK.longValue());
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

    public int countAll() throws SystemException {
        Object[] finderArgs = new Object[0];

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(
                        "SELECT COUNT(*) FROM com.ext.portlet.contests.model.ContestPhase");

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
                        "value.object.listener.com.ext.portlet.contests.model.ContestPhase")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ContestPhase>> listenersList = new ArrayList<ModelListener<ContestPhase>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ContestPhase>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
