package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanDescriptionException;
import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.impl.PlanDescriptionImpl;
import com.ext.portlet.plans.model.impl.PlanDescriptionModelImpl;

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


public class PlanDescriptionPersistenceImpl extends BasePersistenceImpl
    implements PlanDescriptionPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanDescriptionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_CURRENTBYPLANID = new FinderPath(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByCurrentByPlanId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_CURRENTBYPLANID = new FinderPath(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByCurrentByPlanId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_ALLBYPLANID = new FinderPath(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByAllByPlanId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_ALLBYPLANID = new FinderPath(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByAllByPlanId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_ALLBYPLANID = new FinderPath(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByAllByPlanId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanDescriptionPersistenceImpl.class);
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
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanFanPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanFanPersistence planFanPersistence;

    public void cacheResult(PlanDescription planDescription) {
        EntityCacheUtil.putResult(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionImpl.class, planDescription.getPrimaryKey(),
            planDescription);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
            new Object[] { planDescription.getPlanId() }, planDescription);
    }

    public void cacheResult(List<PlanDescription> planDescriptions) {
        for (PlanDescription planDescription : planDescriptions) {
            if (EntityCacheUtil.getResult(
                        PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
                        PlanDescriptionImpl.class,
                        planDescription.getPrimaryKey(), this) == null) {
                cacheResult(planDescription);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanDescriptionImpl.class.getName());
        EntityCacheUtil.clearCache(PlanDescriptionImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanDescription create(Long id) {
        PlanDescription planDescription = new PlanDescriptionImpl();

        planDescription.setNew(true);
        planDescription.setPrimaryKey(id);

        return planDescription;
    }

    public PlanDescription remove(Long id)
        throws NoSuchPlanDescriptionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanDescription planDescription = (PlanDescription) session.get(PlanDescriptionImpl.class,
                    id);

            if (planDescription == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No PlanDescription exists with the primary key " +
                        id);
                }

                throw new NoSuchPlanDescriptionException(
                    "No PlanDescription exists with the primary key " + id);
            }

            return remove(planDescription);
        } catch (NoSuchPlanDescriptionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanDescription remove(PlanDescription planDescription)
        throws SystemException {
        for (ModelListener<PlanDescription> listener : listeners) {
            listener.onBeforeRemove(planDescription);
        }

        planDescription = removeImpl(planDescription);

        for (ModelListener<PlanDescription> listener : listeners) {
            listener.onAfterRemove(planDescription);
        }

        return planDescription;
    }

    protected PlanDescription removeImpl(PlanDescription planDescription)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planDescription.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanDescriptionImpl.class,
                        planDescription.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planDescription);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        PlanDescriptionModelImpl planDescriptionModelImpl = (PlanDescriptionModelImpl) planDescription;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
            new Object[] { planDescriptionModelImpl.getOriginalPlanId() });

        EntityCacheUtil.removeResult(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionImpl.class, planDescription.getPrimaryKey());

        return planDescription;
    }

    /**
     * @deprecated Use <code>update(PlanDescription planDescription, boolean merge)</code>.
     */
    public PlanDescription update(PlanDescription planDescription)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanDescription planDescription) method. Use update(PlanDescription planDescription, boolean merge) instead.");
        }

        return update(planDescription, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planDescription the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planDescription is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanDescription update(PlanDescription planDescription, boolean merge)
        throws SystemException {
        boolean isNew = planDescription.isNew();

        for (ModelListener<PlanDescription> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planDescription);
            } else {
                listener.onBeforeUpdate(planDescription);
            }
        }

        planDescription = updateImpl(planDescription, merge);

        for (ModelListener<PlanDescription> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planDescription);
            } else {
                listener.onAfterUpdate(planDescription);
            }
        }

        return planDescription;
    }

    public PlanDescription updateImpl(
        com.ext.portlet.plans.model.PlanDescription planDescription,
        boolean merge) throws SystemException {
        boolean isNew = planDescription.isNew();

        PlanDescriptionModelImpl planDescriptionModelImpl = (PlanDescriptionModelImpl) planDescription;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planDescription, merge);

            planDescription.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionImpl.class, planDescription.getPrimaryKey(),
            planDescription);

        if (!isNew &&
                (!Validator.equals(planDescription.getPlanId(),
                    planDescriptionModelImpl.getOriginalPlanId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                new Object[] { planDescriptionModelImpl.getOriginalPlanId() });
        }

        if (isNew ||
                (!Validator.equals(planDescription.getPlanId(),
                    planDescriptionModelImpl.getOriginalPlanId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                new Object[] { planDescription.getPlanId() }, planDescription);
        }

        return planDescription;
    }

    public PlanDescription findByPrimaryKey(Long id)
        throws NoSuchPlanDescriptionException, SystemException {
        PlanDescription planDescription = fetchByPrimaryKey(id);

        if (planDescription == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanDescription exists with the primary key " +
                    id);
            }

            throw new NoSuchPlanDescriptionException(
                "No PlanDescription exists with the primary key " + id);
        }

        return planDescription;
    }

    public PlanDescription fetchByPrimaryKey(Long id) throws SystemException {
        PlanDescription planDescription = (PlanDescription) EntityCacheUtil.getResult(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
                PlanDescriptionImpl.class, id, this);

        if (planDescription == null) {
            Session session = null;

            try {
                session = openSession();

                planDescription = (PlanDescription) session.get(PlanDescriptionImpl.class,
                        id);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planDescription != null) {
                    cacheResult(planDescription);
                }

                closeSession(session);
            }
        }

        return planDescription;
    }

    public PlanDescription findByCurrentByPlanId(Long planId)
        throws NoSuchPlanDescriptionException, SystemException {
        PlanDescription planDescription = fetchByCurrentByPlanId(planId);

        if (planDescription == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanDescription exists with the key {");

            msg.append("planId=" + planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanDescriptionException(msg.toString());
        }

        return planDescription;
    }

    public PlanDescription fetchByCurrentByPlanId(Long planId)
        throws SystemException {
        return fetchByCurrentByPlanId(planId, true);
    }

    public PlanDescription fetchByCurrentByPlanId(Long planId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanDescription WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("version DESC, ");
                query.append("created DESC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                List<PlanDescription> list = q.list();

                result = list;

                PlanDescription planDescription = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                        finderArgs, list);
                } else {
                    planDescription = list.get(0);

                    cacheResult(planDescription);

                    if ((planDescription.getPlanId() == null) ||
                            !planDescription.getPlanId().equals(planId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                            finderArgs, planDescription);
                    }
                }

                return planDescription;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                        finderArgs, new ArrayList<PlanDescription>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (PlanDescription) result;
            }
        }
    }

    public List<PlanDescription> findByAllByPlanId(Long planId)
        throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        List<PlanDescription> list = (List<PlanDescription>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ALLBYPLANID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanDescription WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("version DESC, ");
                query.append("created DESC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanDescription>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ALLBYPLANID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<PlanDescription> findByAllByPlanId(Long planId, int start,
        int end) throws SystemException {
        return findByAllByPlanId(planId, start, end, null);
    }

    public List<PlanDescription> findByAllByPlanId(Long planId, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                planId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanDescription> list = (List<PlanDescription>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_ALLBYPLANID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanDescription WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("version DESC, ");
                    query.append("created DESC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                list = (List<PlanDescription>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanDescription>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_ALLBYPLANID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public PlanDescription findByAllByPlanId_First(Long planId,
        OrderByComparator obc)
        throws NoSuchPlanDescriptionException, SystemException {
        List<PlanDescription> list = findByAllByPlanId(planId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanDescription exists with the key {");

            msg.append("planId=" + planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanDescriptionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanDescription findByAllByPlanId_Last(Long planId,
        OrderByComparator obc)
        throws NoSuchPlanDescriptionException, SystemException {
        int count = countByAllByPlanId(planId);

        List<PlanDescription> list = findByAllByPlanId(planId, count - 1,
                count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanDescription exists with the key {");

            msg.append("planId=" + planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanDescriptionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanDescription[] findByAllByPlanId_PrevAndNext(Long id,
        Long planId, OrderByComparator obc)
        throws NoSuchPlanDescriptionException, SystemException {
        PlanDescription planDescription = findByPrimaryKey(id);

        int count = countByAllByPlanId(planId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.plans.model.PlanDescription WHERE ");

            if (planId == null) {
                query.append("planId IS NULL");
            } else {
                query.append("planId = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("version DESC, ");
                query.append("created DESC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (planId != null) {
                qPos.add(planId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    planDescription);

            PlanDescription[] array = new PlanDescriptionImpl[3];

            array[0] = (PlanDescription) objArray[0];
            array[1] = (PlanDescription) objArray[1];
            array[2] = (PlanDescription) objArray[2];

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

    public List<PlanDescription> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanDescription> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanDescription> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanDescription> list = (List<PlanDescription>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanDescription ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("version DESC, ");
                    query.append("created DESC");
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlanDescription>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanDescription>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanDescription>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByCurrentByPlanId(Long planId)
        throws NoSuchPlanDescriptionException, SystemException {
        PlanDescription planDescription = findByCurrentByPlanId(planId);

        remove(planDescription);
    }

    public void removeByAllByPlanId(Long planId) throws SystemException {
        for (PlanDescription planDescription : findByAllByPlanId(planId)) {
            remove(planDescription);
        }
    }

    public void removeAll() throws SystemException {
        for (PlanDescription planDescription : findAll()) {
            remove(planDescription);
        }
    }

    public int countByCurrentByPlanId(Long planId) throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CURRENTBYPLANID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.plans.model.PlanDescription WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CURRENTBYPLANID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByAllByPlanId(Long planId) throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ALLBYPLANID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.plans.model.PlanDescription WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ALLBYPLANID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanDescription");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanDescription")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanDescription>> listenersList = new ArrayList<ModelListener<PlanDescription>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanDescription>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
