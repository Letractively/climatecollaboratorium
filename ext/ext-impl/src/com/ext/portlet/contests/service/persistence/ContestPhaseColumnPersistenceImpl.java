package com.ext.portlet.contests.service.persistence;

import com.ext.portlet.contests.NoSuchContestPhaseColumnException;
import com.ext.portlet.contests.model.ContestPhaseColumn;
import com.ext.portlet.contests.model.impl.ContestPhaseColumnImpl;
import com.ext.portlet.contests.model.impl.ContestPhaseColumnModelImpl;

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


public class ContestPhaseColumnPersistenceImpl extends BasePersistenceImpl
    implements ContestPhaseColumnPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = ContestPhaseColumnImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_CONTESTPHASEPK = new FinderPath(ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseColumnModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByContestPhasePK",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_CONTESTPHASEPK = new FinderPath(ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseColumnModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByContestPhasePK",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTPHASEPK = new FinderPath(ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseColumnModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByContestPhasePK",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseColumnModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseColumnModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(ContestPhaseColumnPersistenceImpl.class);
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

    public void cacheResult(ContestPhaseColumn contestPhaseColumn) {
        EntityCacheUtil.putResult(ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseColumnImpl.class, contestPhaseColumn.getPrimaryKey(),
            contestPhaseColumn);
    }

    public void cacheResult(List<ContestPhaseColumn> contestPhaseColumns) {
        for (ContestPhaseColumn contestPhaseColumn : contestPhaseColumns) {
            if (EntityCacheUtil.getResult(
                        ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
                        ContestPhaseColumnImpl.class,
                        contestPhaseColumn.getPrimaryKey(), this) == null) {
                cacheResult(contestPhaseColumn);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(ContestPhaseColumnImpl.class.getName());
        EntityCacheUtil.clearCache(ContestPhaseColumnImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public ContestPhaseColumn create(Long id) {
        ContestPhaseColumn contestPhaseColumn = new ContestPhaseColumnImpl();

        contestPhaseColumn.setNew(true);
        contestPhaseColumn.setPrimaryKey(id);

        return contestPhaseColumn;
    }

    public ContestPhaseColumn remove(Long id)
        throws NoSuchContestPhaseColumnException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ContestPhaseColumn contestPhaseColumn = (ContestPhaseColumn) session.get(ContestPhaseColumnImpl.class,
                    id);

            if (contestPhaseColumn == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No ContestPhaseColumn exists with the primary key " +
                        id);
                }

                throw new NoSuchContestPhaseColumnException(
                    "No ContestPhaseColumn exists with the primary key " + id);
            }

            return remove(contestPhaseColumn);
        } catch (NoSuchContestPhaseColumnException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ContestPhaseColumn remove(ContestPhaseColumn contestPhaseColumn)
        throws SystemException {
        for (ModelListener<ContestPhaseColumn> listener : listeners) {
            listener.onBeforeRemove(contestPhaseColumn);
        }

        contestPhaseColumn = removeImpl(contestPhaseColumn);

        for (ModelListener<ContestPhaseColumn> listener : listeners) {
            listener.onAfterRemove(contestPhaseColumn);
        }

        return contestPhaseColumn;
    }

    protected ContestPhaseColumn removeImpl(
        ContestPhaseColumn contestPhaseColumn) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (contestPhaseColumn.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(ContestPhaseColumnImpl.class,
                        contestPhaseColumn.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(contestPhaseColumn);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseColumnImpl.class, contestPhaseColumn.getPrimaryKey());

        return contestPhaseColumn;
    }

    /**
     * @deprecated Use <code>update(ContestPhaseColumn contestPhaseColumn, boolean merge)</code>.
     */
    public ContestPhaseColumn update(ContestPhaseColumn contestPhaseColumn)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(ContestPhaseColumn contestPhaseColumn) method. Use update(ContestPhaseColumn contestPhaseColumn, boolean merge) instead.");
        }

        return update(contestPhaseColumn, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                contestPhaseColumn the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when contestPhaseColumn is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public ContestPhaseColumn update(ContestPhaseColumn contestPhaseColumn,
        boolean merge) throws SystemException {
        boolean isNew = contestPhaseColumn.isNew();

        for (ModelListener<ContestPhaseColumn> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(contestPhaseColumn);
            } else {
                listener.onBeforeUpdate(contestPhaseColumn);
            }
        }

        contestPhaseColumn = updateImpl(contestPhaseColumn, merge);

        for (ModelListener<ContestPhaseColumn> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(contestPhaseColumn);
            } else {
                listener.onAfterUpdate(contestPhaseColumn);
            }
        }

        return contestPhaseColumn;
    }

    public ContestPhaseColumn updateImpl(
        com.ext.portlet.contests.model.ContestPhaseColumn contestPhaseColumn,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, contestPhaseColumn, merge);

            contestPhaseColumn.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseColumnImpl.class, contestPhaseColumn.getPrimaryKey(),
            contestPhaseColumn);

        return contestPhaseColumn;
    }

    public ContestPhaseColumn findByPrimaryKey(Long id)
        throws NoSuchContestPhaseColumnException, SystemException {
        ContestPhaseColumn contestPhaseColumn = fetchByPrimaryKey(id);

        if (contestPhaseColumn == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No ContestPhaseColumn exists with the primary key " +
                    id);
            }

            throw new NoSuchContestPhaseColumnException(
                "No ContestPhaseColumn exists with the primary key " + id);
        }

        return contestPhaseColumn;
    }

    public ContestPhaseColumn fetchByPrimaryKey(Long id)
        throws SystemException {
        ContestPhaseColumn contestPhaseColumn = (ContestPhaseColumn) EntityCacheUtil.getResult(ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
                ContestPhaseColumnImpl.class, id, this);

        if (contestPhaseColumn == null) {
            Session session = null;

            try {
                session = openSession();

                contestPhaseColumn = (ContestPhaseColumn) session.get(ContestPhaseColumnImpl.class,
                        id);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (contestPhaseColumn != null) {
                    cacheResult(contestPhaseColumn);
                }

                closeSession(session);
            }
        }

        return contestPhaseColumn;
    }

    public List<ContestPhaseColumn> findByContestPhasePK(Long ContestPhasePK)
        throws SystemException {
        Object[] finderArgs = new Object[] { ContestPhasePK };

        List<ContestPhaseColumn> list = (List<ContestPhaseColumn>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_CONTESTPHASEPK,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.contests.model.ContestPhaseColumn WHERE ");

                if (ContestPhasePK == null) {
                    query.append("ContestPhasePK IS NULL");
                } else {
                    query.append("ContestPhasePK = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("columnWeight ASC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (ContestPhasePK != null) {
                    qPos.add(ContestPhasePK.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ContestPhaseColumn>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_CONTESTPHASEPK,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<ContestPhaseColumn> findByContestPhasePK(Long ContestPhasePK,
        int start, int end) throws SystemException {
        return findByContestPhasePK(ContestPhasePK, start, end, null);
    }

    public List<ContestPhaseColumn> findByContestPhasePK(Long ContestPhasePK,
        int start, int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                ContestPhasePK,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ContestPhaseColumn> list = (List<ContestPhaseColumn>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_CONTESTPHASEPK,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.contests.model.ContestPhaseColumn WHERE ");

                if (ContestPhasePK == null) {
                    query.append("ContestPhasePK IS NULL");
                } else {
                    query.append("ContestPhasePK = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("columnWeight ASC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (ContestPhasePK != null) {
                    qPos.add(ContestPhasePK.longValue());
                }

                list = (List<ContestPhaseColumn>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ContestPhaseColumn>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_CONTESTPHASEPK,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public ContestPhaseColumn findByContestPhasePK_First(Long ContestPhasePK,
        OrderByComparator obc)
        throws NoSuchContestPhaseColumnException, SystemException {
        List<ContestPhaseColumn> list = findByContestPhasePK(ContestPhasePK, 0,
                1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ContestPhaseColumn exists with the key {");

            msg.append("ContestPhasePK=" + ContestPhasePK);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestPhaseColumnException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ContestPhaseColumn findByContestPhasePK_Last(Long ContestPhasePK,
        OrderByComparator obc)
        throws NoSuchContestPhaseColumnException, SystemException {
        int count = countByContestPhasePK(ContestPhasePK);

        List<ContestPhaseColumn> list = findByContestPhasePK(ContestPhasePK,
                count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No ContestPhaseColumn exists with the key {");

            msg.append("ContestPhasePK=" + ContestPhasePK);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestPhaseColumnException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public ContestPhaseColumn[] findByContestPhasePK_PrevAndNext(Long id,
        Long ContestPhasePK, OrderByComparator obc)
        throws NoSuchContestPhaseColumnException, SystemException {
        ContestPhaseColumn contestPhaseColumn = findByPrimaryKey(id);

        int count = countByContestPhasePK(ContestPhasePK);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.contests.model.ContestPhaseColumn WHERE ");

            if (ContestPhasePK == null) {
                query.append("ContestPhasePK IS NULL");
            } else {
                query.append("ContestPhasePK = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("columnWeight ASC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (ContestPhasePK != null) {
                qPos.add(ContestPhasePK.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    contestPhaseColumn);

            ContestPhaseColumn[] array = new ContestPhaseColumnImpl[3];

            array[0] = (ContestPhaseColumn) objArray[0];
            array[1] = (ContestPhaseColumn) objArray[1];
            array[2] = (ContestPhaseColumn) objArray[2];

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

    public List<ContestPhaseColumn> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<ContestPhaseColumn> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<ContestPhaseColumn> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ContestPhaseColumn> list = (List<ContestPhaseColumn>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.contests.model.ContestPhaseColumn ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("columnWeight ASC");
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<ContestPhaseColumn>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ContestPhaseColumn>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ContestPhaseColumn>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByContestPhasePK(Long ContestPhasePK)
        throws SystemException {
        for (ContestPhaseColumn contestPhaseColumn : findByContestPhasePK(
                ContestPhasePK)) {
            remove(contestPhaseColumn);
        }
    }

    public void removeAll() throws SystemException {
        for (ContestPhaseColumn contestPhaseColumn : findAll()) {
            remove(contestPhaseColumn);
        }
    }

    public int countByContestPhasePK(Long ContestPhasePK)
        throws SystemException {
        Object[] finderArgs = new Object[] { ContestPhasePK };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CONTESTPHASEPK,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.contests.model.ContestPhaseColumn WHERE ");

                if (ContestPhasePK == null) {
                    query.append("ContestPhasePK IS NULL");
                } else {
                    query.append("ContestPhasePK = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (ContestPhasePK != null) {
                    qPos.add(ContestPhasePK.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTESTPHASEPK,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.contests.model.ContestPhaseColumn");

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
                        "value.object.listener.com.ext.portlet.contests.model.ContestPhaseColumn")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ContestPhaseColumn>> listenersList = new ArrayList<ModelListener<ContestPhaseColumn>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ContestPhaseColumn>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
