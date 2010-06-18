package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanPositionException;
import com.ext.portlet.plans.model.PlanPosition;
import com.ext.portlet.plans.model.impl.PlanPositionImpl;
import com.ext.portlet.plans.model.impl.PlanPositionModelImpl;

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


public class PlanPositionPersistenceImpl extends BasePersistenceImpl
    implements PlanPositionPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanPositionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_POSITIONID = new FinderPath(PlanPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByPositionId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_POSITIONID = new FinderPath(PlanPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByPositionId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_POSITIONID = new FinderPath(PlanPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByPositionId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanPositionPersistenceImpl.class);
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

    public void cacheResult(PlanPosition planPosition) {
        EntityCacheUtil.putResult(PlanPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionImpl.class, planPosition.getPrimaryKey(), planPosition);
    }

    public void cacheResult(List<PlanPosition> planPositions) {
        for (PlanPosition planPosition : planPositions) {
            if (EntityCacheUtil.getResult(
                        PlanPositionModelImpl.ENTITY_CACHE_ENABLED,
                        PlanPositionImpl.class, planPosition.getPrimaryKey(),
                        this) == null) {
                cacheResult(planPosition);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanPositionImpl.class.getName());
        EntityCacheUtil.clearCache(PlanPositionImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanPosition create(PlanPositionPK planPositionPK) {
        PlanPosition planPosition = new PlanPositionImpl();

        planPosition.setNew(true);
        planPosition.setPrimaryKey(planPositionPK);

        return planPosition;
    }

    public PlanPosition remove(PlanPositionPK planPositionPK)
        throws NoSuchPlanPositionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanPosition planPosition = (PlanPosition) session.get(PlanPositionImpl.class,
                    planPositionPK);

            if (planPosition == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No PlanPosition exists with the primary key " +
                        planPositionPK);
                }

                throw new NoSuchPlanPositionException(
                    "No PlanPosition exists with the primary key " +
                    planPositionPK);
            }

            return remove(planPosition);
        } catch (NoSuchPlanPositionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanPosition remove(PlanPosition planPosition)
        throws SystemException {
        for (ModelListener<PlanPosition> listener : listeners) {
            listener.onBeforeRemove(planPosition);
        }

        planPosition = removeImpl(planPosition);

        for (ModelListener<PlanPosition> listener : listeners) {
            listener.onAfterRemove(planPosition);
        }

        return planPosition;
    }

    protected PlanPosition removeImpl(PlanPosition planPosition)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planPosition.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanPositionImpl.class,
                        planPosition.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planPosition);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(PlanPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionImpl.class, planPosition.getPrimaryKey());

        return planPosition;
    }

    /**
     * @deprecated Use <code>update(PlanPosition planPosition, boolean merge)</code>.
     */
    public PlanPosition update(PlanPosition planPosition)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanPosition planPosition) method. Use update(PlanPosition planPosition, boolean merge) instead.");
        }

        return update(planPosition, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planPosition the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planPosition is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanPosition update(PlanPosition planPosition, boolean merge)
        throws SystemException {
        boolean isNew = planPosition.isNew();

        for (ModelListener<PlanPosition> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planPosition);
            } else {
                listener.onBeforeUpdate(planPosition);
            }
        }

        planPosition = updateImpl(planPosition, merge);

        for (ModelListener<PlanPosition> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planPosition);
            } else {
                listener.onAfterUpdate(planPosition);
            }
        }

        return planPosition;
    }

    public PlanPosition updateImpl(
        com.ext.portlet.plans.model.PlanPosition planPosition, boolean merge)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planPosition, merge);

            planPosition.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionImpl.class, planPosition.getPrimaryKey(), planPosition);

        return planPosition;
    }

    public PlanPosition findByPrimaryKey(PlanPositionPK planPositionPK)
        throws NoSuchPlanPositionException, SystemException {
        PlanPosition planPosition = fetchByPrimaryKey(planPositionPK);

        if (planPosition == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanPosition exists with the primary key " +
                    planPositionPK);
            }

            throw new NoSuchPlanPositionException(
                "No PlanPosition exists with the primary key " +
                planPositionPK);
        }

        return planPosition;
    }

    public PlanPosition fetchByPrimaryKey(PlanPositionPK planPositionPK)
        throws SystemException {
        PlanPosition planPosition = (PlanPosition) EntityCacheUtil.getResult(PlanPositionModelImpl.ENTITY_CACHE_ENABLED,
                PlanPositionImpl.class, planPositionPK, this);

        if (planPosition == null) {
            Session session = null;

            try {
                session = openSession();

                planPosition = (PlanPosition) session.get(PlanPositionImpl.class,
                        planPositionPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planPosition != null) {
                    cacheResult(planPosition);
                }

                closeSession(session);
            }
        }

        return planPosition;
    }

    public List<PlanPosition> findByPositionId(Long positionId)
        throws SystemException {
        Object[] finderArgs = new Object[] { positionId };

        List<PlanPosition> list = (List<PlanPosition>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_POSITIONID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanPosition WHERE ");

                if (positionId == null) {
                    query.append("positionId IS NULL");
                } else {
                    query.append("positionId = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("planId ASC, ");
                query.append("positionId ASC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (positionId != null) {
                    qPos.add(positionId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanPosition>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_POSITIONID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<PlanPosition> findByPositionId(Long positionId, int start,
        int end) throws SystemException {
        return findByPositionId(positionId, start, end, null);
    }

    public List<PlanPosition> findByPositionId(Long positionId, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                positionId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanPosition> list = (List<PlanPosition>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_POSITIONID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanPosition WHERE ");

                if (positionId == null) {
                    query.append("positionId IS NULL");
                } else {
                    query.append("positionId = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("planId ASC, ");
                    query.append("positionId ASC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (positionId != null) {
                    qPos.add(positionId.longValue());
                }

                list = (List<PlanPosition>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanPosition>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_POSITIONID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public PlanPosition findByPositionId_First(Long positionId,
        OrderByComparator obc)
        throws NoSuchPlanPositionException, SystemException {
        List<PlanPosition> list = findByPositionId(positionId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanPosition exists with the key {");

            msg.append("positionId=" + positionId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanPositionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanPosition findByPositionId_Last(Long positionId,
        OrderByComparator obc)
        throws NoSuchPlanPositionException, SystemException {
        int count = countByPositionId(positionId);

        List<PlanPosition> list = findByPositionId(positionId, count - 1,
                count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanPosition exists with the key {");

            msg.append("positionId=" + positionId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanPositionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanPosition[] findByPositionId_PrevAndNext(
        PlanPositionPK planPositionPK, Long positionId, OrderByComparator obc)
        throws NoSuchPlanPositionException, SystemException {
        PlanPosition planPosition = findByPrimaryKey(planPositionPK);

        int count = countByPositionId(positionId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append("FROM com.ext.portlet.plans.model.PlanPosition WHERE ");

            if (positionId == null) {
                query.append("positionId IS NULL");
            } else {
                query.append("positionId = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("planId ASC, ");
                query.append("positionId ASC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (positionId != null) {
                qPos.add(positionId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    planPosition);

            PlanPosition[] array = new PlanPositionImpl[3];

            array[0] = (PlanPosition) objArray[0];
            array[1] = (PlanPosition) objArray[1];
            array[2] = (PlanPosition) objArray[2];

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

    public List<PlanPosition> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanPosition> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanPosition> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanPosition> list = (List<PlanPosition>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanPosition ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("planId ASC, ");
                    query.append("positionId ASC");
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlanPosition>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanPosition>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanPosition>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByPositionId(Long positionId) throws SystemException {
        for (PlanPosition planPosition : findByPositionId(positionId)) {
            remove(planPosition);
        }
    }

    public void removeAll() throws SystemException {
        for (PlanPosition planPosition : findAll()) {
            remove(planPosition);
        }
    }

    public int countByPositionId(Long positionId) throws SystemException {
        Object[] finderArgs = new Object[] { positionId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_POSITIONID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.plans.model.PlanPosition WHERE ");

                if (positionId == null) {
                    query.append("positionId IS NULL");
                } else {
                    query.append("positionId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (positionId != null) {
                    qPos.add(positionId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_POSITIONID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanPosition");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanPosition")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanPosition>> listenersList = new ArrayList<ModelListener<PlanPosition>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanPosition>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
