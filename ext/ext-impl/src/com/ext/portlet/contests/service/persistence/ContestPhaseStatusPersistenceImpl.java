package com.ext.portlet.contests.service.persistence;

import com.ext.portlet.contests.NoSuchContestPhaseStatusException;
import com.ext.portlet.contests.model.ContestPhaseStatus;
import com.ext.portlet.contests.model.impl.ContestPhaseStatusImpl;
import com.ext.portlet.contests.model.impl.ContestPhaseStatusModelImpl;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ContestPhaseStatusPersistenceImpl extends BasePersistenceImpl
    implements ContestPhaseStatusPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = ContestPhaseStatusImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ContestPhaseStatusModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseStatusModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContestPhaseStatusModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseStatusModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(ContestPhaseStatusPersistenceImpl.class);
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

    public void cacheResult(ContestPhaseStatus contestPhaseStatus) {
        EntityCacheUtil.putResult(ContestPhaseStatusModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseStatusImpl.class, contestPhaseStatus.getPrimaryKey(),
            contestPhaseStatus);
    }

    public void cacheResult(List<ContestPhaseStatus> contestPhaseStatuses) {
        for (ContestPhaseStatus contestPhaseStatus : contestPhaseStatuses) {
            if (EntityCacheUtil.getResult(
                        ContestPhaseStatusModelImpl.ENTITY_CACHE_ENABLED,
                        ContestPhaseStatusImpl.class,
                        contestPhaseStatus.getPrimaryKey(), this) == null) {
                cacheResult(contestPhaseStatus);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(ContestPhaseStatusImpl.class.getName());
        EntityCacheUtil.clearCache(ContestPhaseStatusImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public ContestPhaseStatus create(String name) {
        ContestPhaseStatus contestPhaseStatus = new ContestPhaseStatusImpl();

        contestPhaseStatus.setNew(true);
        contestPhaseStatus.setPrimaryKey(name);

        return contestPhaseStatus;
    }

    public ContestPhaseStatus remove(String name)
        throws NoSuchContestPhaseStatusException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ContestPhaseStatus contestPhaseStatus = (ContestPhaseStatus) session.get(ContestPhaseStatusImpl.class,
                    name);

            if (contestPhaseStatus == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No ContestPhaseStatus exists with the primary key " +
                        name);
                }

                throw new NoSuchContestPhaseStatusException(
                    "No ContestPhaseStatus exists with the primary key " +
                    name);
            }

            return remove(contestPhaseStatus);
        } catch (NoSuchContestPhaseStatusException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ContestPhaseStatus remove(ContestPhaseStatus contestPhaseStatus)
        throws SystemException {
        for (ModelListener<ContestPhaseStatus> listener : listeners) {
            listener.onBeforeRemove(contestPhaseStatus);
        }

        contestPhaseStatus = removeImpl(contestPhaseStatus);

        for (ModelListener<ContestPhaseStatus> listener : listeners) {
            listener.onAfterRemove(contestPhaseStatus);
        }

        return contestPhaseStatus;
    }

    protected ContestPhaseStatus removeImpl(
        ContestPhaseStatus contestPhaseStatus) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (contestPhaseStatus.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(ContestPhaseStatusImpl.class,
                        contestPhaseStatus.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(contestPhaseStatus);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(ContestPhaseStatusModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseStatusImpl.class, contestPhaseStatus.getPrimaryKey());

        return contestPhaseStatus;
    }

    /**
     * @deprecated Use <code>update(ContestPhaseStatus contestPhaseStatus, boolean merge)</code>.
     */
    public ContestPhaseStatus update(ContestPhaseStatus contestPhaseStatus)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(ContestPhaseStatus contestPhaseStatus) method. Use update(ContestPhaseStatus contestPhaseStatus, boolean merge) instead.");
        }

        return update(contestPhaseStatus, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                contestPhaseStatus the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when contestPhaseStatus is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public ContestPhaseStatus update(ContestPhaseStatus contestPhaseStatus,
        boolean merge) throws SystemException {
        boolean isNew = contestPhaseStatus.isNew();

        for (ModelListener<ContestPhaseStatus> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(contestPhaseStatus);
            } else {
                listener.onBeforeUpdate(contestPhaseStatus);
            }
        }

        contestPhaseStatus = updateImpl(contestPhaseStatus, merge);

        for (ModelListener<ContestPhaseStatus> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(contestPhaseStatus);
            } else {
                listener.onAfterUpdate(contestPhaseStatus);
            }
        }

        return contestPhaseStatus;
    }

    public ContestPhaseStatus updateImpl(
        com.ext.portlet.contests.model.ContestPhaseStatus contestPhaseStatus,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, contestPhaseStatus, merge);

            contestPhaseStatus.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(ContestPhaseStatusModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseStatusImpl.class, contestPhaseStatus.getPrimaryKey(),
            contestPhaseStatus);

        return contestPhaseStatus;
    }

    public ContestPhaseStatus findByPrimaryKey(String name)
        throws NoSuchContestPhaseStatusException, SystemException {
        ContestPhaseStatus contestPhaseStatus = fetchByPrimaryKey(name);

        if (contestPhaseStatus == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No ContestPhaseStatus exists with the primary key " +
                    name);
            }

            throw new NoSuchContestPhaseStatusException(
                "No ContestPhaseStatus exists with the primary key " + name);
        }

        return contestPhaseStatus;
    }

    public ContestPhaseStatus fetchByPrimaryKey(String name)
        throws SystemException {
        ContestPhaseStatus contestPhaseStatus = (ContestPhaseStatus) EntityCacheUtil.getResult(ContestPhaseStatusModelImpl.ENTITY_CACHE_ENABLED,
                ContestPhaseStatusImpl.class, name, this);

        if (contestPhaseStatus == null) {
            Session session = null;

            try {
                session = openSession();

                contestPhaseStatus = (ContestPhaseStatus) session.get(ContestPhaseStatusImpl.class,
                        name);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (contestPhaseStatus != null) {
                    cacheResult(contestPhaseStatus);
                }

                closeSession(session);
            }
        }

        return contestPhaseStatus;
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

    public List<ContestPhaseStatus> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<ContestPhaseStatus> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<ContestPhaseStatus> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ContestPhaseStatus> list = (List<ContestPhaseStatus>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.contests.model.ContestPhaseStatus ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<ContestPhaseStatus>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ContestPhaseStatus>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ContestPhaseStatus>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (ContestPhaseStatus contestPhaseStatus : findAll()) {
            remove(contestPhaseStatus);
        }
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
                        "SELECT COUNT(*) FROM com.ext.portlet.contests.model.ContestPhaseStatus");

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
                        "value.object.listener.com.ext.portlet.contests.model.ContestPhaseStatus")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ContestPhaseStatus>> listenersList = new ArrayList<ModelListener<ContestPhaseStatus>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ContestPhaseStatus>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
