package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchFilterPositionException;
import com.ext.portlet.plans.model.PlansFilterPosition;
import com.ext.portlet.plans.model.impl.PlansFilterPositionImpl;
import com.ext.portlet.plans.model.impl.PlansFilterPositionModelImpl;

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


public class PlansFilterPositionPersistenceImpl extends BasePersistenceImpl
    implements PlansFilterPositionPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlansFilterPositionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlansFilterPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterPositionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlansFilterPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterPositionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlansFilterPositionPersistenceImpl.class);
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

    public void cacheResult(PlansFilterPosition plansFilterPosition) {
        EntityCacheUtil.putResult(PlansFilterPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterPositionImpl.class, plansFilterPosition.getPrimaryKey(),
            plansFilterPosition);
    }

    public void cacheResult(List<PlansFilterPosition> plansFilterPositions) {
        for (PlansFilterPosition plansFilterPosition : plansFilterPositions) {
            if (EntityCacheUtil.getResult(
                        PlansFilterPositionModelImpl.ENTITY_CACHE_ENABLED,
                        PlansFilterPositionImpl.class,
                        plansFilterPosition.getPrimaryKey(), this) == null) {
                cacheResult(plansFilterPosition);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlansFilterPositionImpl.class.getName());
        EntityCacheUtil.clearCache(PlansFilterPositionImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlansFilterPosition create(
        PlansFilterPositionPK plansFilterPositionPK) {
        PlansFilterPosition plansFilterPosition = new PlansFilterPositionImpl();

        plansFilterPosition.setNew(true);
        plansFilterPosition.setPrimaryKey(plansFilterPositionPK);

        return plansFilterPosition;
    }

    public PlansFilterPosition remove(
        PlansFilterPositionPK plansFilterPositionPK)
        throws NoSuchFilterPositionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlansFilterPosition plansFilterPosition = (PlansFilterPosition) session.get(PlansFilterPositionImpl.class,
                    plansFilterPositionPK);

            if (plansFilterPosition == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No PlansFilterPosition exists with the primary key " +
                        plansFilterPositionPK);
                }

                throw new NoSuchFilterPositionException(
                    "No PlansFilterPosition exists with the primary key " +
                    plansFilterPositionPK);
            }

            return remove(plansFilterPosition);
        } catch (NoSuchFilterPositionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlansFilterPosition remove(PlansFilterPosition plansFilterPosition)
        throws SystemException {
        for (ModelListener<PlansFilterPosition> listener : listeners) {
            listener.onBeforeRemove(plansFilterPosition);
        }

        plansFilterPosition = removeImpl(plansFilterPosition);

        for (ModelListener<PlansFilterPosition> listener : listeners) {
            listener.onAfterRemove(plansFilterPosition);
        }

        return plansFilterPosition;
    }

    protected PlansFilterPosition removeImpl(
        PlansFilterPosition plansFilterPosition) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (plansFilterPosition.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlansFilterPositionImpl.class,
                        plansFilterPosition.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(plansFilterPosition);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(PlansFilterPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterPositionImpl.class, plansFilterPosition.getPrimaryKey());

        return plansFilterPosition;
    }

    /**
     * @deprecated Use <code>update(PlansFilterPosition plansFilterPosition, boolean merge)</code>.
     */
    public PlansFilterPosition update(PlansFilterPosition plansFilterPosition)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlansFilterPosition plansFilterPosition) method. Use update(PlansFilterPosition plansFilterPosition, boolean merge) instead.");
        }

        return update(plansFilterPosition, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                plansFilterPosition the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when plansFilterPosition is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlansFilterPosition update(PlansFilterPosition plansFilterPosition,
        boolean merge) throws SystemException {
        boolean isNew = plansFilterPosition.isNew();

        for (ModelListener<PlansFilterPosition> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(plansFilterPosition);
            } else {
                listener.onBeforeUpdate(plansFilterPosition);
            }
        }

        plansFilterPosition = updateImpl(plansFilterPosition, merge);

        for (ModelListener<PlansFilterPosition> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(plansFilterPosition);
            } else {
                listener.onAfterUpdate(plansFilterPosition);
            }
        }

        return plansFilterPosition;
    }

    public PlansFilterPosition updateImpl(
        com.ext.portlet.plans.model.PlansFilterPosition plansFilterPosition,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, plansFilterPosition, merge);

            plansFilterPosition.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlansFilterPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterPositionImpl.class, plansFilterPosition.getPrimaryKey(),
            plansFilterPosition);

        return plansFilterPosition;
    }

    public PlansFilterPosition findByPrimaryKey(
        PlansFilterPositionPK plansFilterPositionPK)
        throws NoSuchFilterPositionException, SystemException {
        PlansFilterPosition plansFilterPosition = fetchByPrimaryKey(plansFilterPositionPK);

        if (plansFilterPosition == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlansFilterPosition exists with the primary key " +
                    plansFilterPositionPK);
            }

            throw new NoSuchFilterPositionException(
                "No PlansFilterPosition exists with the primary key " +
                plansFilterPositionPK);
        }

        return plansFilterPosition;
    }

    public PlansFilterPosition fetchByPrimaryKey(
        PlansFilterPositionPK plansFilterPositionPK) throws SystemException {
        PlansFilterPosition plansFilterPosition = (PlansFilterPosition) EntityCacheUtil.getResult(PlansFilterPositionModelImpl.ENTITY_CACHE_ENABLED,
                PlansFilterPositionImpl.class, plansFilterPositionPK, this);

        if (plansFilterPosition == null) {
            Session session = null;

            try {
                session = openSession();

                plansFilterPosition = (PlansFilterPosition) session.get(PlansFilterPositionImpl.class,
                        plansFilterPositionPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (plansFilterPosition != null) {
                    cacheResult(plansFilterPosition);
                }

                closeSession(session);
            }
        }

        return plansFilterPosition;
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

    public List<PlansFilterPosition> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlansFilterPosition> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlansFilterPosition> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlansFilterPosition> list = (List<PlansFilterPosition>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlansFilterPosition ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlansFilterPosition>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlansFilterPosition>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlansFilterPosition>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (PlansFilterPosition plansFilterPosition : findAll()) {
            remove(plansFilterPosition);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlansFilterPosition");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlansFilterPosition")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlansFilterPosition>> listenersList = new ArrayList<ModelListener<PlansFilterPosition>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlansFilterPosition>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}