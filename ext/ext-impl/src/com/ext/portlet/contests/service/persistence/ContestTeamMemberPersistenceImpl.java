package com.ext.portlet.contests.service.persistence;

import com.ext.portlet.contests.NoSuchContestTeamMemberException;
import com.ext.portlet.contests.model.ContestTeamMember;
import com.ext.portlet.contests.model.impl.ContestTeamMemberImpl;
import com.ext.portlet.contests.model.impl.ContestTeamMemberModelImpl;

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


public class ContestTeamMemberPersistenceImpl extends BasePersistenceImpl
    implements ContestTeamMemberPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = ContestTeamMemberImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_CONTESTID = new FinderPath(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByContestId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_CONTESTID = new FinderPath(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByContestId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTID = new FinderPath(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByContestId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(ContestTeamMemberPersistenceImpl.class);
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestPersistence.impl")
    protected com.ext.portlet.contests.service.persistence.ContestPersistence contestPersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestDebatePersistence.impl")
    protected com.ext.portlet.contests.service.persistence.ContestDebatePersistence contestDebatePersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestPhasePersistence.impl")
    protected com.ext.portlet.contests.service.persistence.ContestPhasePersistence contestPhasePersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestPhaseTypePersistence.impl")
    protected com.ext.portlet.contests.service.persistence.ContestPhaseTypePersistence contestPhaseTypePersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestPhaseColumnPersistence.impl")
    protected com.ext.portlet.contests.service.persistence.ContestPhaseColumnPersistence contestPhaseColumnPersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestTeamMemberPersistence.impl")
    protected com.ext.portlet.contests.service.persistence.ContestTeamMemberPersistence contestTeamMemberPersistence;

    public void cacheResult(ContestTeamMember contestTeamMember) {
        EntityCacheUtil.putResult(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberImpl.class, contestTeamMember.getPrimaryKey(),
            contestTeamMember);
    }

    public void cacheResult(List<ContestTeamMember> contestTeamMembers) {
        for (ContestTeamMember contestTeamMember : contestTeamMembers) {
            if (EntityCacheUtil.getResult(
                        ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
                        ContestTeamMemberImpl.class,
                        contestTeamMember.getPrimaryKey(), this) == null) {
                cacheResult(contestTeamMember);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(ContestTeamMemberImpl.class.getName());
        EntityCacheUtil.clearCache(ContestTeamMemberImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public ContestTeamMember create(Long id) {
        ContestTeamMember contestTeamMember = new ContestTeamMemberImpl();

        contestTeamMember.setNew(true);
        contestTeamMember.setPrimaryKey(id);

        return contestTeamMember;
    }

    public ContestTeamMember remove(Long id)
        throws NoSuchContestTeamMemberException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ContestTeamMember contestTeamMember = (ContestTeamMember) session.get(ContestTeamMemberImpl.class,
                    id);

            if (contestTeamMember == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No ContestTeamMember exists with the primary key " +
                        id);
                }

                throw new NoSuchContestTeamMemberException(
                    "No ContestTeamMember exists with the primary key " + id);
            }

            return remove(contestTeamMember);
        } catch (NoSuchContestTeamMemberException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ContestTeamMember remove(ContestTeamMember contestTeamMember)
        throws SystemException {
        for (ModelListener<ContestTeamMember> listener : listeners) {
            listener.onBeforeRemove(contestTeamMember);
        }

        contestTeamMember = removeImpl(contestTeamMember);

        for (ModelListener<ContestTeamMember> listener : listeners) {
            listener.onAfterRemove(contestTeamMember);
        }

        return contestTeamMember;
    }

    protected ContestTeamMember removeImpl(ContestTeamMember contestTeamMember)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (contestTeamMember.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(ContestTeamMemberImpl.class,
                        contestTeamMember.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(contestTeamMember);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberImpl.class, contestTeamMember.getPrimaryKey());

        return contestTeamMember;
    }

    /**
     * @deprecated Use <code>update(ContestTeamMember contestTeamMember, boolean merge)</code>.
     */
    public ContestTeamMember update(ContestTeamMember contestTeamMember)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(ContestTeamMember contestTeamMember) method. Use update(ContestTeamMember contestTeamMember, boolean merge) instead.");
        }

        return update(contestTeamMember, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                contestTeamMember the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when contestTeamMember is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public ContestTeamMember update(ContestTeamMember contestTeamMember,
        boolean merge) throws SystemException {
        boolean isNew = contestTeamMember.isNew();

        for (ModelListener<ContestTeamMember> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(contestTeamMember);
            } else {
                listener.onBeforeUpdate(contestTeamMember);
            }
        }

        contestTeamMember = updateImpl(contestTeamMember, merge);

        for (ModelListener<ContestTeamMember> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(contestTeamMember);
            } else {
                listener.onAfterUpdate(contestTeamMember);
            }
        }

        return contestTeamMember;
    }

    public ContestTeamMember updateImpl(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, contestTeamMember, merge);

            contestTeamMember.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberImpl.class, contestTeamMember.getPrimaryKey(),
            contestTeamMember);

        return contestTeamMember;
    }

    public ContestTeamMember findByPrimaryKey(Long id)
        throws NoSuchContestTeamMemberException, SystemException {
        ContestTeamMember contestTeamMember = fetchByPrimaryKey(id);

        if (contestTeamMember == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No ContestTeamMember exists with the primary key " +
                    id);
            }

            throw new NoSuchContestTeamMemberException(
                "No ContestTeamMember exists with the primary key " + id);
        }

        return contestTeamMember;
    }

    public ContestTeamMember fetchByPrimaryKey(Long id)
        throws SystemException {
        ContestTeamMember contestTeamMember = (ContestTeamMember) EntityCacheUtil.getResult(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
                ContestTeamMemberImpl.class, id, this);

        if (contestTeamMember == null) {
            Session session = null;

            try {
                session = openSession();

                contestTeamMember = (ContestTeamMember) session.get(ContestTeamMemberImpl.class,
                        id);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (contestTeamMember != null) {
                    cacheResult(contestTeamMember);
                }

                closeSession(session);
            }
        }

        return contestTeamMember;
    }

    public List<ContestTeamMember> findByContestId(Long contestId)
        throws SystemException {
        Object[] finderArgs = new Object[] { contestId };

        List<ContestTeamMember> list = (List<ContestTeamMember>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_CONTESTID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.contests.model.ContestTeamMember WHERE ");

                if (contestId == null) {
                    query.append("contestId IS NULL");
                } else {
                    query.append("contestId = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("id_ ASC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (contestId != null) {
                    qPos.add(contestId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ContestTeamMember>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_CONTESTID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<ContestTeamMember> findByContestId(Long contestId, int start,
        int end) throws SystemException {
        return findByContestId(contestId, start, end, null);
    }

    public List<ContestTeamMember> findByContestId(Long contestId, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                contestId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ContestTeamMember> list = (List<ContestTeamMember>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_CONTESTID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.contests.model.ContestTeamMember WHERE ");

                if (contestId == null) {
                    query.append("contestId IS NULL");
                } else {
                    query.append("contestId = ?");
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

                if (contestId != null) {
                    qPos.add(contestId.longValue());
                }

                list = (List<ContestTeamMember>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ContestTeamMember>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_CONTESTID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public ContestTeamMember findByContestId_First(Long contestId,
        OrderByComparator obc)
        throws NoSuchContestTeamMemberException, SystemException {
        List<ContestTeamMember> list = findByContestId(contestId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ContestTeamMember exists with the key {");

            msg.append("contestId=" + contestId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestTeamMemberException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ContestTeamMember findByContestId_Last(Long contestId,
        OrderByComparator obc)
        throws NoSuchContestTeamMemberException, SystemException {
        int count = countByContestId(contestId);

        List<ContestTeamMember> list = findByContestId(contestId, count - 1,
                count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ContestTeamMember exists with the key {");

            msg.append("contestId=" + contestId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestTeamMemberException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ContestTeamMember[] findByContestId_PrevAndNext(Long id,
        Long contestId, OrderByComparator obc)
        throws NoSuchContestTeamMemberException, SystemException {
        ContestTeamMember contestTeamMember = findByPrimaryKey(id);

        int count = countByContestId(contestId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.contests.model.ContestTeamMember WHERE ");

            if (contestId == null) {
                query.append("contestId IS NULL");
            } else {
                query.append("contestId = ?");
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

            if (contestId != null) {
                qPos.add(contestId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    contestTeamMember);

            ContestTeamMember[] array = new ContestTeamMemberImpl[3];

            array[0] = (ContestTeamMember) objArray[0];
            array[1] = (ContestTeamMember) objArray[1];
            array[2] = (ContestTeamMember) objArray[2];

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

    public List<ContestTeamMember> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<ContestTeamMember> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<ContestTeamMember> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ContestTeamMember> list = (List<ContestTeamMember>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.contests.model.ContestTeamMember ");

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
                    list = (List<ContestTeamMember>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ContestTeamMember>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ContestTeamMember>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByContestId(Long contestId) throws SystemException {
        for (ContestTeamMember contestTeamMember : findByContestId(contestId)) {
            remove(contestTeamMember);
        }
    }

    public void removeAll() throws SystemException {
        for (ContestTeamMember contestTeamMember : findAll()) {
            remove(contestTeamMember);
        }
    }

    public int countByContestId(Long contestId) throws SystemException {
        Object[] finderArgs = new Object[] { contestId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CONTESTID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.contests.model.ContestTeamMember WHERE ");

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

    public int countAll() throws SystemException {
        Object[] finderArgs = new Object[0];

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(
                        "SELECT COUNT(*) FROM com.ext.portlet.contests.model.ContestTeamMember");

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
                        "value.object.listener.com.ext.portlet.contests.model.ContestTeamMember")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ContestTeamMember>> listenersList = new ArrayList<ModelListener<ContestTeamMember>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ContestTeamMember>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
