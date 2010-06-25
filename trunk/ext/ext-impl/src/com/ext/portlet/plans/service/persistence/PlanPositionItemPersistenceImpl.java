package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanPositionItemException;
import com.ext.portlet.plans.model.PlanPositionItem;
import com.ext.portlet.plans.model.impl.PlanPositionItemImpl;
import com.ext.portlet.plans.model.impl.PlanPositionItemModelImpl;

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


public class PlanPositionItemPersistenceImpl extends BasePersistenceImpl
    implements PlanPositionItemPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanPositionItemImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_ALLBYPLANPOSITIONSID = new FinderPath(PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByAllByPlanPositionsId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_ALLBYPLANPOSITIONSID = new FinderPath(PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByAllByPlanPositionsId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_ALLBYPLANPOSITIONSID = new FinderPath(PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByAllByPlanPositionsId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionItemModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanPositionItemPersistenceImpl.class);
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

    public void cacheResult(PlanPositionItem planPositionItem) {
        EntityCacheUtil.putResult(PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionItemImpl.class, planPositionItem.getPrimaryKey(),
            planPositionItem);
    }

    public void cacheResult(List<PlanPositionItem> planPositionItems) {
        for (PlanPositionItem planPositionItem : planPositionItems) {
            if (EntityCacheUtil.getResult(
                        PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
                        PlanPositionItemImpl.class,
                        planPositionItem.getPrimaryKey(), this) == null) {
                cacheResult(planPositionItem);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanPositionItemImpl.class.getName());
        EntityCacheUtil.clearCache(PlanPositionItemImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanPositionItem create(PlanPositionItemPK planPositionItemPK) {
        PlanPositionItem planPositionItem = new PlanPositionItemImpl();

        planPositionItem.setNew(true);
        planPositionItem.setPrimaryKey(planPositionItemPK);

        return planPositionItem;
    }

    public PlanPositionItem remove(PlanPositionItemPK planPositionItemPK)
        throws NoSuchPlanPositionItemException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanPositionItem planPositionItem = (PlanPositionItem) session.get(PlanPositionItemImpl.class,
                    planPositionItemPK);

            if (planPositionItem == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No PlanPositionItem exists with the primary key " +
                        planPositionItemPK);
                }

                throw new NoSuchPlanPositionItemException(
                    "No PlanPositionItem exists with the primary key " +
                    planPositionItemPK);
            }

            return remove(planPositionItem);
        } catch (NoSuchPlanPositionItemException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanPositionItem remove(PlanPositionItem planPositionItem)
        throws SystemException {
        for (ModelListener<PlanPositionItem> listener : listeners) {
            listener.onBeforeRemove(planPositionItem);
        }

        planPositionItem = removeImpl(planPositionItem);

        for (ModelListener<PlanPositionItem> listener : listeners) {
            listener.onAfterRemove(planPositionItem);
        }

        return planPositionItem;
    }

    protected PlanPositionItem removeImpl(PlanPositionItem planPositionItem)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planPositionItem.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanPositionItemImpl.class,
                        planPositionItem.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planPositionItem);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionItemImpl.class, planPositionItem.getPrimaryKey());

        return planPositionItem;
    }

    /**
     * @deprecated Use <code>update(PlanPositionItem planPositionItem, boolean merge)</code>.
     */
    public PlanPositionItem update(PlanPositionItem planPositionItem)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanPositionItem planPositionItem) method. Use update(PlanPositionItem planPositionItem, boolean merge) instead.");
        }

        return update(planPositionItem, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planPositionItem the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planPositionItem is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanPositionItem update(PlanPositionItem planPositionItem,
        boolean merge) throws SystemException {
        boolean isNew = planPositionItem.isNew();

        for (ModelListener<PlanPositionItem> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planPositionItem);
            } else {
                listener.onBeforeUpdate(planPositionItem);
            }
        }

        planPositionItem = updateImpl(planPositionItem, merge);

        for (ModelListener<PlanPositionItem> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planPositionItem);
            } else {
                listener.onAfterUpdate(planPositionItem);
            }
        }

        return planPositionItem;
    }

    public PlanPositionItem updateImpl(
        com.ext.portlet.plans.model.PlanPositionItem planPositionItem,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planPositionItem, merge);

            planPositionItem.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionItemImpl.class, planPositionItem.getPrimaryKey(),
            planPositionItem);

        return planPositionItem;
    }

    public PlanPositionItem findByPrimaryKey(
        PlanPositionItemPK planPositionItemPK)
        throws NoSuchPlanPositionItemException, SystemException {
        PlanPositionItem planPositionItem = fetchByPrimaryKey(planPositionItemPK);

        if (planPositionItem == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanPositionItem exists with the primary key " +
                    planPositionItemPK);
            }

            throw new NoSuchPlanPositionItemException(
                "No PlanPositionItem exists with the primary key " +
                planPositionItemPK);
        }

        return planPositionItem;
    }

    public PlanPositionItem fetchByPrimaryKey(
        PlanPositionItemPK planPositionItemPK) throws SystemException {
        PlanPositionItem planPositionItem = (PlanPositionItem) EntityCacheUtil.getResult(PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
                PlanPositionItemImpl.class, planPositionItemPK, this);

        if (planPositionItem == null) {
            Session session = null;

            try {
                session = openSession();

                planPositionItem = (PlanPositionItem) session.get(PlanPositionItemImpl.class,
                        planPositionItemPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planPositionItem != null) {
                    cacheResult(planPositionItem);
                }

                closeSession(session);
            }
        }

        return planPositionItem;
    }

    public List<PlanPositionItem> findByAllByPlanPositionsId(
        Long planPositionsId) throws SystemException {
        Object[] finderArgs = new Object[] { planPositionsId };

        List<PlanPositionItem> list = (List<PlanPositionItem>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ALLBYPLANPOSITIONSID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanPositionItem WHERE ");

                if (planPositionsId == null) {
                    query.append("planPositionsId IS NULL");
                } else {
                    query.append("planPositionsId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planPositionsId != null) {
                    qPos.add(planPositionsId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanPositionItem>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ALLBYPLANPOSITIONSID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<PlanPositionItem> findByAllByPlanPositionsId(
        Long planPositionsId, int start, int end) throws SystemException {
        return findByAllByPlanPositionsId(planPositionsId, start, end, null);
    }

    public List<PlanPositionItem> findByAllByPlanPositionsId(
        Long planPositionsId, int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                planPositionsId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanPositionItem> list = (List<PlanPositionItem>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_ALLBYPLANPOSITIONSID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanPositionItem WHERE ");

                if (planPositionsId == null) {
                    query.append("planPositionsId IS NULL");
                } else {
                    query.append("planPositionsId = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planPositionsId != null) {
                    qPos.add(planPositionsId.longValue());
                }

                list = (List<PlanPositionItem>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanPositionItem>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_ALLBYPLANPOSITIONSID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public PlanPositionItem findByAllByPlanPositionsId_First(
        Long planPositionsId, OrderByComparator obc)
        throws NoSuchPlanPositionItemException, SystemException {
        List<PlanPositionItem> list = findByAllByPlanPositionsId(planPositionsId,
                0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanPositionItem exists with the key {");

            msg.append("planPositionsId=" + planPositionsId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanPositionItemException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanPositionItem findByAllByPlanPositionsId_Last(
        Long planPositionsId, OrderByComparator obc)
        throws NoSuchPlanPositionItemException, SystemException {
        int count = countByAllByPlanPositionsId(planPositionsId);

        List<PlanPositionItem> list = findByAllByPlanPositionsId(planPositionsId,
                count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanPositionItem exists with the key {");

            msg.append("planPositionsId=" + planPositionsId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanPositionItemException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanPositionItem[] findByAllByPlanPositionsId_PrevAndNext(
        PlanPositionItemPK planPositionItemPK, Long planPositionsId,
        OrderByComparator obc)
        throws NoSuchPlanPositionItemException, SystemException {
        PlanPositionItem planPositionItem = findByPrimaryKey(planPositionItemPK);

        int count = countByAllByPlanPositionsId(planPositionsId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.plans.model.PlanPositionItem WHERE ");

            if (planPositionsId == null) {
                query.append("planPositionsId IS NULL");
            } else {
                query.append("planPositionsId = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (planPositionsId != null) {
                qPos.add(planPositionsId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    planPositionItem);

            PlanPositionItem[] array = new PlanPositionItemImpl[3];

            array[0] = (PlanPositionItem) objArray[0];
            array[1] = (PlanPositionItem) objArray[1];
            array[2] = (PlanPositionItem) objArray[2];

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

    public List<PlanPositionItem> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanPositionItem> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanPositionItem> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanPositionItem> list = (List<PlanPositionItem>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanPositionItem ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlanPositionItem>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanPositionItem>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanPositionItem>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByAllByPlanPositionsId(Long planPositionsId)
        throws SystemException {
        for (PlanPositionItem planPositionItem : findByAllByPlanPositionsId(
                planPositionsId)) {
            remove(planPositionItem);
        }
    }

    public void removeAll() throws SystemException {
        for (PlanPositionItem planPositionItem : findAll()) {
            remove(planPositionItem);
        }
    }

    public int countByAllByPlanPositionsId(Long planPositionsId)
        throws SystemException {
        Object[] finderArgs = new Object[] { planPositionsId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ALLBYPLANPOSITIONSID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.plans.model.PlanPositionItem WHERE ");

                if (planPositionsId == null) {
                    query.append("planPositionsId IS NULL");
                } else {
                    query.append("planPositionsId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planPositionsId != null) {
                    qPos.add(planPositionsId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ALLBYPLANPOSITIONSID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanPositionItem");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanPositionItem")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanPositionItem>> listenersList = new ArrayList<ModelListener<PlanPositionItem>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanPositionItem>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
