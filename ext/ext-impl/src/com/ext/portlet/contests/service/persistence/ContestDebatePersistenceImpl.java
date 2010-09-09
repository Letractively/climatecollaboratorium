package com.ext.portlet.contests.service.persistence;

import com.ext.portlet.contests.NoSuchContestDebateException;
import com.ext.portlet.contests.model.ContestDebate;
import com.ext.portlet.contests.model.impl.ContestDebateImpl;
import com.ext.portlet.contests.model.impl.ContestDebateModelImpl;

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
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ContestDebatePersistenceImpl extends BasePersistenceImpl
    implements ContestDebatePersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = ContestDebateImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_CONTESTPK = new FinderPath(ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
            ContestDebateModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByContestPK",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_CONTESTPK = new FinderPath(ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
            ContestDebateModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByContestPK",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTPK = new FinderPath(ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
            ContestDebateModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByContestPK",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
            ContestDebateModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
            ContestDebateModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(ContestDebatePersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestPersistence.impl")
    protected com.ext.portlet.contests.service.persistence.ContestPersistence contestPersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestDebatePersistence.impl")
    protected com.ext.portlet.contests.service.persistence.ContestDebatePersistence contestDebatePersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestPhasePersistence.impl")
    protected com.ext.portlet.contests.service.persistence.ContestPhasePersistence contestPhasePersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestPhaseColumnPersistence.impl")
    protected com.ext.portlet.contests.service.persistence.ContestPhaseColumnPersistence contestPhaseColumnPersistence;

    public void cacheResult(ContestDebate contestDebate) {
        EntityCacheUtil.putResult(ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
            ContestDebateImpl.class, contestDebate.getPrimaryKey(),
            contestDebate);
    }

    public void cacheResult(List<ContestDebate> contestDebates) {
        for (ContestDebate contestDebate : contestDebates) {
            if (EntityCacheUtil.getResult(
                        ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
                        ContestDebateImpl.class, contestDebate.getPrimaryKey(),
                        this) == null) {
                cacheResult(contestDebate);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(ContestDebateImpl.class.getName());
        EntityCacheUtil.clearCache(ContestDebateImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public ContestDebate create(Long id) {
        ContestDebate contestDebate = new ContestDebateImpl();

        contestDebate.setNew(true);
        contestDebate.setPrimaryKey(id);

        return contestDebate;
    }

    public ContestDebate remove(Long id)
        throws NoSuchContestDebateException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ContestDebate contestDebate = (ContestDebate) session.get(ContestDebateImpl.class,
                    id);

            if (contestDebate == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No ContestDebate exists with the primary key " +
                        id);
                }

                throw new NoSuchContestDebateException(
                    "No ContestDebate exists with the primary key " + id);
            }

            return remove(contestDebate);
        } catch (NoSuchContestDebateException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ContestDebate remove(ContestDebate contestDebate)
        throws SystemException {
        for (ModelListener<ContestDebate> listener : listeners) {
            listener.onBeforeRemove(contestDebate);
        }

        contestDebate = removeImpl(contestDebate);

        for (ModelListener<ContestDebate> listener : listeners) {
            listener.onAfterRemove(contestDebate);
        }

        return contestDebate;
    }

    protected ContestDebate removeImpl(ContestDebate contestDebate)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (contestDebate.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(ContestDebateImpl.class,
                        contestDebate.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(contestDebate);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
            ContestDebateImpl.class, contestDebate.getPrimaryKey());

        return contestDebate;
    }

    /**
     * @deprecated Use <code>update(ContestDebate contestDebate, boolean merge)</code>.
     */
    public ContestDebate update(ContestDebate contestDebate)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(ContestDebate contestDebate) method. Use update(ContestDebate contestDebate, boolean merge) instead.");
        }

        return update(contestDebate, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                contestDebate the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when contestDebate is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public ContestDebate update(ContestDebate contestDebate, boolean merge)
        throws SystemException {
        boolean isNew = contestDebate.isNew();

        for (ModelListener<ContestDebate> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(contestDebate);
            } else {
                listener.onBeforeUpdate(contestDebate);
            }
        }

        contestDebate = updateImpl(contestDebate, merge);

        for (ModelListener<ContestDebate> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(contestDebate);
            } else {
                listener.onAfterUpdate(contestDebate);
            }
        }

        return contestDebate;
    }

    public ContestDebate updateImpl(
        com.ext.portlet.contests.model.ContestDebate contestDebate,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, contestDebate, merge);

            contestDebate.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
            ContestDebateImpl.class, contestDebate.getPrimaryKey(),
            contestDebate);

        return contestDebate;
    }

    public ContestDebate findByPrimaryKey(Long id)
        throws NoSuchContestDebateException, SystemException {
        ContestDebate contestDebate = fetchByPrimaryKey(id);

        if (contestDebate == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No ContestDebate exists with the primary key " + id);
            }

            throw new NoSuchContestDebateException(
                "No ContestDebate exists with the primary key " + id);
        }

        return contestDebate;
    }

    public ContestDebate fetchByPrimaryKey(Long id) throws SystemException {
        ContestDebate contestDebate = (ContestDebate) EntityCacheUtil.getResult(ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
                ContestDebateImpl.class, id, this);

        if (contestDebate == null) {
            Session session = null;

            try {
                session = openSession();

                contestDebate = (ContestDebate) session.get(ContestDebateImpl.class,
                        id);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (contestDebate != null) {
                    cacheResult(contestDebate);
                }

                closeSession(session);
            }
        }

        return contestDebate;
    }

    public List<ContestDebate> findByContestPK(Long ContestPK)
        throws SystemException {
        Object[] finderArgs = new Object[] { ContestPK };

        List<ContestDebate> list = (List<ContestDebate>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_CONTESTPK,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.contests.model.ContestDebate WHERE ");

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

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ContestDebate>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_CONTESTPK,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<ContestDebate> findByContestPK(Long ContestPK, int start,
        int end) throws SystemException {
        return findByContestPK(ContestPK, start, end, null);
    }

    public List<ContestDebate> findByContestPK(Long ContestPK, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                ContestPK,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ContestDebate> list = (List<ContestDebate>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_CONTESTPK,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.contests.model.ContestDebate WHERE ");

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

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (ContestPK != null) {
                    qPos.add(ContestPK.longValue());
                }

                list = (List<ContestDebate>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ContestDebate>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_CONTESTPK,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public ContestDebate findByContestPK_First(Long ContestPK,
        OrderByComparator obc)
        throws NoSuchContestDebateException, SystemException {
        List<ContestDebate> list = findByContestPK(ContestPK, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ContestDebate exists with the key {");

            msg.append("ContestPK=" + ContestPK);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestDebateException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ContestDebate findByContestPK_Last(Long ContestPK,
        OrderByComparator obc)
        throws NoSuchContestDebateException, SystemException {
        int count = countByContestPK(ContestPK);

        List<ContestDebate> list = findByContestPK(ContestPK, count - 1, count,
                obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ContestDebate exists with the key {");

            msg.append("ContestPK=" + ContestPK);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestDebateException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ContestDebate[] findByContestPK_PrevAndNext(Long id, Long ContestPK,
        OrderByComparator obc)
        throws NoSuchContestDebateException, SystemException {
        ContestDebate contestDebate = findByPrimaryKey(id);

        int count = countByContestPK(ContestPK);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.contests.model.ContestDebate WHERE ");

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

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (ContestPK != null) {
                qPos.add(ContestPK.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    contestDebate);

            ContestDebate[] array = new ContestDebateImpl[3];

            array[0] = (ContestDebate) objArray[0];
            array[1] = (ContestDebate) objArray[1];
            array[2] = (ContestDebate) objArray[2];

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

    public List<ContestDebate> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<ContestDebate> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<ContestDebate> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ContestDebate> list = (List<ContestDebate>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.contests.model.ContestDebate ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<ContestDebate>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ContestDebate>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ContestDebate>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByContestPK(Long ContestPK) throws SystemException {
        for (ContestDebate contestDebate : findByContestPK(ContestPK)) {
            remove(contestDebate);
        }
    }

    public void removeAll() throws SystemException {
        for (ContestDebate contestDebate : findAll()) {
            remove(contestDebate);
        }
    }

    public int countByContestPK(Long ContestPK) throws SystemException {
        Object[] finderArgs = new Object[] { ContestPK };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CONTESTPK,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.contests.model.ContestDebate WHERE ");

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

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTESTPK,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.contests.model.ContestDebate");

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
                        "value.object.listener.com.ext.portlet.contests.model.ContestDebate")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ContestDebate>> listenersList = new ArrayList<ModelListener<ContestDebate>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ContestDebate>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
