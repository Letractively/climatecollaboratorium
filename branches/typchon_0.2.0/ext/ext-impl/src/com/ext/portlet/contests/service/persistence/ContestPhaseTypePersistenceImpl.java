package com.ext.portlet.contests.service.persistence;

import com.ext.portlet.contests.NoSuchContestPhaseTypeException;
import com.ext.portlet.contests.model.ContestPhaseType;
import com.ext.portlet.contests.model.impl.ContestPhaseTypeImpl;
import com.ext.portlet.contests.model.impl.ContestPhaseTypeModelImpl;

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


public class ContestPhaseTypePersistenceImpl extends BasePersistenceImpl
    implements ContestPhaseTypePersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = ContestPhaseTypeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ContestPhaseTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseTypeModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContestPhaseTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseTypeModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(ContestPhaseTypePersistenceImpl.class);
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

    public void cacheResult(ContestPhaseType contestPhaseType) {
        EntityCacheUtil.putResult(ContestPhaseTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseTypeImpl.class, contestPhaseType.getPrimaryKey(),
            contestPhaseType);
    }

    public void cacheResult(List<ContestPhaseType> contestPhaseTypes) {
        for (ContestPhaseType contestPhaseType : contestPhaseTypes) {
            if (EntityCacheUtil.getResult(
                        ContestPhaseTypeModelImpl.ENTITY_CACHE_ENABLED,
                        ContestPhaseTypeImpl.class,
                        contestPhaseType.getPrimaryKey(), this) == null) {
                cacheResult(contestPhaseType);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(ContestPhaseTypeImpl.class.getName());
        EntityCacheUtil.clearCache(ContestPhaseTypeImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public ContestPhaseType create(Long id) {
        ContestPhaseType contestPhaseType = new ContestPhaseTypeImpl();

        contestPhaseType.setNew(true);
        contestPhaseType.setPrimaryKey(id);

        return contestPhaseType;
    }

    public ContestPhaseType remove(Long id)
        throws NoSuchContestPhaseTypeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ContestPhaseType contestPhaseType = (ContestPhaseType) session.get(ContestPhaseTypeImpl.class,
                    id);

            if (contestPhaseType == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No ContestPhaseType exists with the primary key " +
                        id);
                }

                throw new NoSuchContestPhaseTypeException(
                    "No ContestPhaseType exists with the primary key " + id);
            }

            return remove(contestPhaseType);
        } catch (NoSuchContestPhaseTypeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ContestPhaseType remove(ContestPhaseType contestPhaseType)
        throws SystemException {
        for (ModelListener<ContestPhaseType> listener : listeners) {
            listener.onBeforeRemove(contestPhaseType);
        }

        contestPhaseType = removeImpl(contestPhaseType);

        for (ModelListener<ContestPhaseType> listener : listeners) {
            listener.onAfterRemove(contestPhaseType);
        }

        return contestPhaseType;
    }

    protected ContestPhaseType removeImpl(ContestPhaseType contestPhaseType)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (contestPhaseType.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(ContestPhaseTypeImpl.class,
                        contestPhaseType.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(contestPhaseType);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(ContestPhaseTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseTypeImpl.class, contestPhaseType.getPrimaryKey());

        return contestPhaseType;
    }

    /**
     * @deprecated Use <code>update(ContestPhaseType contestPhaseType, boolean merge)</code>.
     */
    public ContestPhaseType update(ContestPhaseType contestPhaseType)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(ContestPhaseType contestPhaseType) method. Use update(ContestPhaseType contestPhaseType, boolean merge) instead.");
        }

        return update(contestPhaseType, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                contestPhaseType the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when contestPhaseType is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public ContestPhaseType update(ContestPhaseType contestPhaseType,
        boolean merge) throws SystemException {
        boolean isNew = contestPhaseType.isNew();

        for (ModelListener<ContestPhaseType> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(contestPhaseType);
            } else {
                listener.onBeforeUpdate(contestPhaseType);
            }
        }

        contestPhaseType = updateImpl(contestPhaseType, merge);

        for (ModelListener<ContestPhaseType> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(contestPhaseType);
            } else {
                listener.onAfterUpdate(contestPhaseType);
            }
        }

        return contestPhaseType;
    }

    public ContestPhaseType updateImpl(
        com.ext.portlet.contests.model.ContestPhaseType contestPhaseType,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, contestPhaseType, merge);

            contestPhaseType.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(ContestPhaseTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseTypeImpl.class, contestPhaseType.getPrimaryKey(),
            contestPhaseType);

        return contestPhaseType;
    }

    public ContestPhaseType findByPrimaryKey(Long id)
        throws NoSuchContestPhaseTypeException, SystemException {
        ContestPhaseType contestPhaseType = fetchByPrimaryKey(id);

        if (contestPhaseType == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No ContestPhaseType exists with the primary key " +
                    id);
            }

            throw new NoSuchContestPhaseTypeException(
                "No ContestPhaseType exists with the primary key " + id);
        }

        return contestPhaseType;
    }

    public ContestPhaseType fetchByPrimaryKey(Long id)
        throws SystemException {
        ContestPhaseType contestPhaseType = (ContestPhaseType) EntityCacheUtil.getResult(ContestPhaseTypeModelImpl.ENTITY_CACHE_ENABLED,
                ContestPhaseTypeImpl.class, id, this);

        if (contestPhaseType == null) {
            Session session = null;

            try {
                session = openSession();

                contestPhaseType = (ContestPhaseType) session.get(ContestPhaseTypeImpl.class,
                        id);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (contestPhaseType != null) {
                    cacheResult(contestPhaseType);
                }

                closeSession(session);
            }
        }

        return contestPhaseType;
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

    public List<ContestPhaseType> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<ContestPhaseType> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<ContestPhaseType> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ContestPhaseType> list = (List<ContestPhaseType>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.contests.model.ContestPhaseType ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<ContestPhaseType>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ContestPhaseType>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ContestPhaseType>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (ContestPhaseType contestPhaseType : findAll()) {
            remove(contestPhaseType);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.contests.model.ContestPhaseType");

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
                        "value.object.listener.com.ext.portlet.contests.model.ContestPhaseType")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ContestPhaseType>> listenersList = new ArrayList<ModelListener<ContestPhaseType>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ContestPhaseType>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
