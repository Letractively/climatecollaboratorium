package com.ext.portlet.debaterevision.service.persistence;

import com.ext.portlet.debaterevision.NoSuchDebateItemVoteStatsException;
import com.ext.portlet.debaterevision.model.DebateItemVoteStats;
import com.ext.portlet.debaterevision.model.impl.DebateItemVoteStatsImpl;
import com.ext.portlet.debaterevision.model.impl.DebateItemVoteStatsModelImpl;

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


public class DebateItemVoteStatsPersistenceImpl extends BasePersistenceImpl
    implements DebateItemVoteStatsPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = DebateItemVoteStatsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_DEBATEITEMID = new FinderPath(DebateItemVoteStatsModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemVoteStatsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByDebateItemId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_DEBATEITEMID = new FinderPath(DebateItemVoteStatsModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemVoteStatsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByDebateItemId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(DebateItemVoteStatsModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemVoteStatsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DebateItemVoteStatsModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemVoteStatsModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(DebateItemVoteStatsPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebatePersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebatePersistence debatePersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateItemPersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebateItemPersistence debateItemPersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateItemReferencePersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebateItemReferencePersistence debateItemReferencePersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateCategoryPersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebateCategoryPersistence debateCategoryPersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateCategoryMapPersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebateCategoryMapPersistence debateCategoryMapPersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateCommentPersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebateCommentPersistence debateCommentPersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateItemVotePersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebateItemVotePersistence debateItemVotePersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateItemVoteStatsPersistence.impl")
    protected com.ext.portlet.debaterevision.service.persistence.DebateItemVoteStatsPersistence debateItemVoteStatsPersistence;

    public void cacheResult(DebateItemVoteStats debateItemVoteStats) {
        EntityCacheUtil.putResult(DebateItemVoteStatsModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemVoteStatsImpl.class, debateItemVoteStats.getPrimaryKey(),
            debateItemVoteStats);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEBATEITEMID,
            new Object[] { debateItemVoteStats.getDebateItemId() },
            debateItemVoteStats);
    }

    public void cacheResult(List<DebateItemVoteStats> debateItemVoteStatses) {
        for (DebateItemVoteStats debateItemVoteStats : debateItemVoteStatses) {
            if (EntityCacheUtil.getResult(
                        DebateItemVoteStatsModelImpl.ENTITY_CACHE_ENABLED,
                        DebateItemVoteStatsImpl.class,
                        debateItemVoteStats.getPrimaryKey(), this) == null) {
                cacheResult(debateItemVoteStats);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(DebateItemVoteStatsImpl.class.getName());
        EntityCacheUtil.clearCache(DebateItemVoteStatsImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public DebateItemVoteStats create(Long debateItemVotesStats) {
        DebateItemVoteStats debateItemVoteStats = new DebateItemVoteStatsImpl();

        debateItemVoteStats.setNew(true);
        debateItemVoteStats.setPrimaryKey(debateItemVotesStats);

        return debateItemVoteStats;
    }

    public DebateItemVoteStats remove(Long debateItemVotesStats)
        throws NoSuchDebateItemVoteStatsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DebateItemVoteStats debateItemVoteStats = (DebateItemVoteStats) session.get(DebateItemVoteStatsImpl.class,
                    debateItemVotesStats);

            if (debateItemVoteStats == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No DebateItemVoteStats exists with the primary key " +
                        debateItemVotesStats);
                }

                throw new NoSuchDebateItemVoteStatsException(
                    "No DebateItemVoteStats exists with the primary key " +
                    debateItemVotesStats);
            }

            return remove(debateItemVoteStats);
        } catch (NoSuchDebateItemVoteStatsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public DebateItemVoteStats remove(DebateItemVoteStats debateItemVoteStats)
        throws SystemException {
        for (ModelListener<DebateItemVoteStats> listener : listeners) {
            listener.onBeforeRemove(debateItemVoteStats);
        }

        debateItemVoteStats = removeImpl(debateItemVoteStats);

        for (ModelListener<DebateItemVoteStats> listener : listeners) {
            listener.onAfterRemove(debateItemVoteStats);
        }

        return debateItemVoteStats;
    }

    protected DebateItemVoteStats removeImpl(
        DebateItemVoteStats debateItemVoteStats) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (debateItemVoteStats.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(DebateItemVoteStatsImpl.class,
                        debateItemVoteStats.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(debateItemVoteStats);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        DebateItemVoteStatsModelImpl debateItemVoteStatsModelImpl = (DebateItemVoteStatsModelImpl) debateItemVoteStats;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DEBATEITEMID,
            new Object[] { debateItemVoteStatsModelImpl.getOriginalDebateItemId() });

        EntityCacheUtil.removeResult(DebateItemVoteStatsModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemVoteStatsImpl.class, debateItemVoteStats.getPrimaryKey());

        return debateItemVoteStats;
    }

    /**
     * @deprecated Use <code>update(DebateItemVoteStats debateItemVoteStats, boolean merge)</code>.
     */
    public DebateItemVoteStats update(DebateItemVoteStats debateItemVoteStats)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(DebateItemVoteStats debateItemVoteStats) method. Use update(DebateItemVoteStats debateItemVoteStats, boolean merge) instead.");
        }

        return update(debateItemVoteStats, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateItemVoteStats the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateItemVoteStats is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public DebateItemVoteStats update(DebateItemVoteStats debateItemVoteStats,
        boolean merge) throws SystemException {
        boolean isNew = debateItemVoteStats.isNew();

        for (ModelListener<DebateItemVoteStats> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(debateItemVoteStats);
            } else {
                listener.onBeforeUpdate(debateItemVoteStats);
            }
        }

        debateItemVoteStats = updateImpl(debateItemVoteStats, merge);

        for (ModelListener<DebateItemVoteStats> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(debateItemVoteStats);
            } else {
                listener.onAfterUpdate(debateItemVoteStats);
            }
        }

        return debateItemVoteStats;
    }

    public DebateItemVoteStats updateImpl(
        com.ext.portlet.debaterevision.model.DebateItemVoteStats debateItemVoteStats,
        boolean merge) throws SystemException {
        boolean isNew = debateItemVoteStats.isNew();

        DebateItemVoteStatsModelImpl debateItemVoteStatsModelImpl = (DebateItemVoteStatsModelImpl) debateItemVoteStats;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, debateItemVoteStats, merge);

            debateItemVoteStats.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(DebateItemVoteStatsModelImpl.ENTITY_CACHE_ENABLED,
            DebateItemVoteStatsImpl.class, debateItemVoteStats.getPrimaryKey(),
            debateItemVoteStats);

        if (!isNew &&
                (!Validator.equals(debateItemVoteStats.getDebateItemId(),
                    debateItemVoteStatsModelImpl.getOriginalDebateItemId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DEBATEITEMID,
                new Object[] {
                    debateItemVoteStatsModelImpl.getOriginalDebateItemId()
                });
        }

        if (isNew ||
                (!Validator.equals(debateItemVoteStats.getDebateItemId(),
                    debateItemVoteStatsModelImpl.getOriginalDebateItemId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEBATEITEMID,
                new Object[] { debateItemVoteStats.getDebateItemId() },
                debateItemVoteStats);
        }

        return debateItemVoteStats;
    }

    public DebateItemVoteStats findByPrimaryKey(Long debateItemVotesStats)
        throws NoSuchDebateItemVoteStatsException, SystemException {
        DebateItemVoteStats debateItemVoteStats = fetchByPrimaryKey(debateItemVotesStats);

        if (debateItemVoteStats == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No DebateItemVoteStats exists with the primary key " +
                    debateItemVotesStats);
            }

            throw new NoSuchDebateItemVoteStatsException(
                "No DebateItemVoteStats exists with the primary key " +
                debateItemVotesStats);
        }

        return debateItemVoteStats;
    }

    public DebateItemVoteStats fetchByPrimaryKey(Long debateItemVotesStats)
        throws SystemException {
        DebateItemVoteStats debateItemVoteStats = (DebateItemVoteStats) EntityCacheUtil.getResult(DebateItemVoteStatsModelImpl.ENTITY_CACHE_ENABLED,
                DebateItemVoteStatsImpl.class, debateItemVotesStats, this);

        if (debateItemVoteStats == null) {
            Session session = null;

            try {
                session = openSession();

                debateItemVoteStats = (DebateItemVoteStats) session.get(DebateItemVoteStatsImpl.class,
                        debateItemVotesStats);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (debateItemVoteStats != null) {
                    cacheResult(debateItemVoteStats);
                }

                closeSession(session);
            }
        }

        return debateItemVoteStats;
    }

    public DebateItemVoteStats findByDebateItemId(Long debateItemId)
        throws NoSuchDebateItemVoteStatsException, SystemException {
        DebateItemVoteStats debateItemVoteStats = fetchByDebateItemId(debateItemId);

        if (debateItemVoteStats == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No DebateItemVoteStats exists with the key {");

            msg.append("debateItemId=" + debateItemId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchDebateItemVoteStatsException(msg.toString());
        }

        return debateItemVoteStats;
    }

    public DebateItemVoteStats fetchByDebateItemId(Long debateItemId)
        throws SystemException {
        return fetchByDebateItemId(debateItemId, true);
    }

    public DebateItemVoteStats fetchByDebateItemId(Long debateItemId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { debateItemId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_DEBATEITEMID,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItemVoteStats WHERE ");

                if (debateItemId == null) {
                    query.append("debateItemId IS NULL");
                } else {
                    query.append("debateItemId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateItemId != null) {
                    qPos.add(debateItemId.longValue());
                }

                List<DebateItemVoteStats> list = q.list();

                result = list;

                DebateItemVoteStats debateItemVoteStats = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEBATEITEMID,
                        finderArgs, list);
                } else {
                    debateItemVoteStats = list.get(0);

                    cacheResult(debateItemVoteStats);

                    if ((debateItemVoteStats.getDebateItemId() == null) ||
                            !debateItemVoteStats.getDebateItemId()
                                                    .equals(debateItemId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEBATEITEMID,
                            finderArgs, debateItemVoteStats);
                    }
                }

                return debateItemVoteStats;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEBATEITEMID,
                        finderArgs, new ArrayList<DebateItemVoteStats>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (DebateItemVoteStats) result;
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

    public List<DebateItemVoteStats> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<DebateItemVoteStats> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<DebateItemVoteStats> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<DebateItemVoteStats> list = (List<DebateItemVoteStats>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItemVoteStats ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<DebateItemVoteStats>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<DebateItemVoteStats>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<DebateItemVoteStats>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByDebateItemId(Long debateItemId)
        throws NoSuchDebateItemVoteStatsException, SystemException {
        DebateItemVoteStats debateItemVoteStats = findByDebateItemId(debateItemId);

        remove(debateItemVoteStats);
    }

    public void removeAll() throws SystemException {
        for (DebateItemVoteStats debateItemVoteStats : findAll()) {
            remove(debateItemVoteStats);
        }
    }

    public int countByDebateItemId(Long debateItemId) throws SystemException {
        Object[] finderArgs = new Object[] { debateItemId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_DEBATEITEMID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.debaterevision.model.DebateItemVoteStats WHERE ");

                if (debateItemId == null) {
                    query.append("debateItemId IS NULL");
                } else {
                    query.append("debateItemId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (debateItemId != null) {
                    qPos.add(debateItemId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DEBATEITEMID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.debaterevision.model.DebateItemVoteStats");

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
                        "value.object.listener.com.ext.portlet.debaterevision.model.DebateItemVoteStats")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DebateItemVoteStats>> listenersList = new ArrayList<ModelListener<DebateItemVoteStats>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DebateItemVoteStats>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
