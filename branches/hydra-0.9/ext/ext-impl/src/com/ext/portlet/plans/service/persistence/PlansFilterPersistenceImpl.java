package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchFilterException;
import com.ext.portlet.plans.model.PlansFilter;
import com.ext.portlet.plans.model.impl.PlansFilterImpl;
import com.ext.portlet.plans.model.impl.PlansFilterModelImpl;

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


public class PlansFilterPersistenceImpl extends BasePersistenceImpl
    implements PlansFilterPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlansFilterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlansFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlansFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlansFilterPersistenceImpl.class);
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
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanPositionsPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanPositionsPersistence planPositionsPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanPositionItemPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanPositionItemPersistence planPositionItemPersistence;

    public void cacheResult(PlansFilter plansFilter) {
        EntityCacheUtil.putResult(PlansFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterImpl.class, plansFilter.getPrimaryKey(), plansFilter);
    }

    public void cacheResult(List<PlansFilter> plansFilters) {
        for (PlansFilter plansFilter : plansFilters) {
            if (EntityCacheUtil.getResult(
                        PlansFilterModelImpl.ENTITY_CACHE_ENABLED,
                        PlansFilterImpl.class, plansFilter.getPrimaryKey(), this) == null) {
                cacheResult(plansFilter);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlansFilterImpl.class.getName());
        EntityCacheUtil.clearCache(PlansFilterImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlansFilter create(PlansFilterPK plansFilterPK) {
        PlansFilter plansFilter = new PlansFilterImpl();

        plansFilter.setNew(true);
        plansFilter.setPrimaryKey(plansFilterPK);

        return plansFilter;
    }

    public PlansFilter remove(PlansFilterPK plansFilterPK)
        throws NoSuchFilterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlansFilter plansFilter = (PlansFilter) session.get(PlansFilterImpl.class,
                    plansFilterPK);

            if (plansFilter == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No PlansFilter exists with the primary key " +
                        plansFilterPK);
                }

                throw new NoSuchFilterException(
                    "No PlansFilter exists with the primary key " +
                    plansFilterPK);
            }

            return remove(plansFilter);
        } catch (NoSuchFilterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlansFilter remove(PlansFilter plansFilter)
        throws SystemException {
        for (ModelListener<PlansFilter> listener : listeners) {
            listener.onBeforeRemove(plansFilter);
        }

        plansFilter = removeImpl(plansFilter);

        for (ModelListener<PlansFilter> listener : listeners) {
            listener.onAfterRemove(plansFilter);
        }

        return plansFilter;
    }

    protected PlansFilter removeImpl(PlansFilter plansFilter)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (plansFilter.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlansFilterImpl.class,
                        plansFilter.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(plansFilter);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(PlansFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterImpl.class, plansFilter.getPrimaryKey());

        return plansFilter;
    }

    /**
     * @deprecated Use <code>update(PlansFilter plansFilter, boolean merge)</code>.
     */
    public PlansFilter update(PlansFilter plansFilter)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlansFilter plansFilter) method. Use update(PlansFilter plansFilter, boolean merge) instead.");
        }

        return update(plansFilter, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                plansFilter the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when plansFilter is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlansFilter update(PlansFilter plansFilter, boolean merge)
        throws SystemException {
        boolean isNew = plansFilter.isNew();

        for (ModelListener<PlansFilter> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(plansFilter);
            } else {
                listener.onBeforeUpdate(plansFilter);
            }
        }

        plansFilter = updateImpl(plansFilter, merge);

        for (ModelListener<PlansFilter> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(plansFilter);
            } else {
                listener.onAfterUpdate(plansFilter);
            }
        }

        return plansFilter;
    }

    public PlansFilter updateImpl(
        com.ext.portlet.plans.model.PlansFilter plansFilter, boolean merge)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, plansFilter, merge);

            plansFilter.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlansFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterImpl.class, plansFilter.getPrimaryKey(), plansFilter);

        return plansFilter;
    }

    public PlansFilter findByPrimaryKey(PlansFilterPK plansFilterPK)
        throws NoSuchFilterException, SystemException {
        PlansFilter plansFilter = fetchByPrimaryKey(plansFilterPK);

        if (plansFilter == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlansFilter exists with the primary key " +
                    plansFilterPK);
            }

            throw new NoSuchFilterException(
                "No PlansFilter exists with the primary key " + plansFilterPK);
        }

        return plansFilter;
    }

    public PlansFilter fetchByPrimaryKey(PlansFilterPK plansFilterPK)
        throws SystemException {
        PlansFilter plansFilter = (PlansFilter) EntityCacheUtil.getResult(PlansFilterModelImpl.ENTITY_CACHE_ENABLED,
                PlansFilterImpl.class, plansFilterPK, this);

        if (plansFilter == null) {
            Session session = null;

            try {
                session = openSession();

                plansFilter = (PlansFilter) session.get(PlansFilterImpl.class,
                        plansFilterPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (plansFilter != null) {
                    cacheResult(plansFilter);
                }

                closeSession(session);
            }
        }

        return plansFilter;
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

    public List<PlansFilter> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlansFilter> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlansFilter> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlansFilter> list = (List<PlansFilter>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlansFilter ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlansFilter>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlansFilter>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlansFilter>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (PlansFilter plansFilter : findAll()) {
            remove(plansFilter);
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlansFilter");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlansFilter")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlansFilter>> listenersList = new ArrayList<ModelListener<PlansFilter>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlansFilter>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
