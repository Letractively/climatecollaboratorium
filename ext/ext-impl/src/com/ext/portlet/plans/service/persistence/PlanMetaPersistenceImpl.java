package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanMetaException;
import com.ext.portlet.plans.model.PlanMeta;
import com.ext.portlet.plans.model.impl.PlanMetaImpl;
import com.ext.portlet.plans.model.impl.PlanMetaModelImpl;

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


public class PlanMetaPersistenceImpl extends BasePersistenceImpl
    implements PlanMetaPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanMetaImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_CURRENTBYPLANID = new FinderPath(PlanMetaModelImpl.ENTITY_CACHE_ENABLED,
            PlanMetaModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
            "fetchByCurrentByPlanId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_CURRENTBYPLANID = new FinderPath(PlanMetaModelImpl.ENTITY_CACHE_ENABLED,
            PlanMetaModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByCurrentByPlanId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_ALLBYPLANID = new FinderPath(PlanMetaModelImpl.ENTITY_CACHE_ENABLED,
            PlanMetaModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByAllByPlanId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_ALLBYPLANID = new FinderPath(PlanMetaModelImpl.ENTITY_CACHE_ENABLED,
            PlanMetaModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByAllByPlanId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_ALLBYPLANID = new FinderPath(PlanMetaModelImpl.ENTITY_CACHE_ENABLED,
            PlanMetaModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByAllByPlanId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanMetaModelImpl.ENTITY_CACHE_ENABLED,
            PlanMetaModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanMetaModelImpl.ENTITY_CACHE_ENABLED,
            PlanMetaModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanMetaPersistenceImpl.class);
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
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanTeamHistoryPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanTeamHistoryPersistence planTeamHistoryPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanSectionDefinitionPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanSectionDefinitionPersistence planSectionDefinitionPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanSectionPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanSectionPersistence planSectionPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanRelatedPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanRelatedPersistence planRelatedPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanTemplatePersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanTemplatePersistence planTemplatePersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanTemplateSectionPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanTemplateSectionPersistence planTemplateSectionPersistence;

    public void cacheResult(PlanMeta planMeta) {
        EntityCacheUtil.putResult(PlanMetaModelImpl.ENTITY_CACHE_ENABLED,
            PlanMetaImpl.class, planMeta.getPrimaryKey(), planMeta);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
            new Object[] { planMeta.getPlanId() }, planMeta);
    }

    public void cacheResult(List<PlanMeta> planMetas) {
        for (PlanMeta planMeta : planMetas) {
            if (EntityCacheUtil.getResult(
                        PlanMetaModelImpl.ENTITY_CACHE_ENABLED,
                        PlanMetaImpl.class, planMeta.getPrimaryKey(), this) == null) {
                cacheResult(planMeta);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanMetaImpl.class.getName());
        EntityCacheUtil.clearCache(PlanMetaImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanMeta create(Long id) {
        PlanMeta planMeta = new PlanMetaImpl();

        planMeta.setNew(true);
        planMeta.setPrimaryKey(id);

        return planMeta;
    }

    public PlanMeta remove(Long id)
        throws NoSuchPlanMetaException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanMeta planMeta = (PlanMeta) session.get(PlanMetaImpl.class, id);

            if (planMeta == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No PlanMeta exists with the primary key " + id);
                }

                throw new NoSuchPlanMetaException(
                    "No PlanMeta exists with the primary key " + id);
            }

            return remove(planMeta);
        } catch (NoSuchPlanMetaException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanMeta remove(PlanMeta planMeta) throws SystemException {
        for (ModelListener<PlanMeta> listener : listeners) {
            listener.onBeforeRemove(planMeta);
        }

        planMeta = removeImpl(planMeta);

        for (ModelListener<PlanMeta> listener : listeners) {
            listener.onAfterRemove(planMeta);
        }

        return planMeta;
    }

    protected PlanMeta removeImpl(PlanMeta planMeta) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planMeta.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanMetaImpl.class,
                        planMeta.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planMeta);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        PlanMetaModelImpl planMetaModelImpl = (PlanMetaModelImpl) planMeta;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
            new Object[] { planMetaModelImpl.getOriginalPlanId() });

        EntityCacheUtil.removeResult(PlanMetaModelImpl.ENTITY_CACHE_ENABLED,
            PlanMetaImpl.class, planMeta.getPrimaryKey());

        return planMeta;
    }

    /**
     * @deprecated Use <code>update(PlanMeta planMeta, boolean merge)</code>.
     */
    public PlanMeta update(PlanMeta planMeta) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanMeta planMeta) method. Use update(PlanMeta planMeta, boolean merge) instead.");
        }

        return update(planMeta, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planMeta the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planMeta is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanMeta update(PlanMeta planMeta, boolean merge)
        throws SystemException {
        boolean isNew = planMeta.isNew();

        for (ModelListener<PlanMeta> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planMeta);
            } else {
                listener.onBeforeUpdate(planMeta);
            }
        }

        planMeta = updateImpl(planMeta, merge);

        for (ModelListener<PlanMeta> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planMeta);
            } else {
                listener.onAfterUpdate(planMeta);
            }
        }

        return planMeta;
    }

    public PlanMeta updateImpl(com.ext.portlet.plans.model.PlanMeta planMeta,
        boolean merge) throws SystemException {
        boolean isNew = planMeta.isNew();

        PlanMetaModelImpl planMetaModelImpl = (PlanMetaModelImpl) planMeta;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planMeta, merge);

            planMeta.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanMetaModelImpl.ENTITY_CACHE_ENABLED,
            PlanMetaImpl.class, planMeta.getPrimaryKey(), planMeta);

        if (!isNew &&
                (!Validator.equals(planMeta.getPlanId(),
                    planMetaModelImpl.getOriginalPlanId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                new Object[] { planMetaModelImpl.getOriginalPlanId() });
        }

        if (isNew ||
                (!Validator.equals(planMeta.getPlanId(),
                    planMetaModelImpl.getOriginalPlanId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                new Object[] { planMeta.getPlanId() }, planMeta);
        }

        return planMeta;
    }

    public PlanMeta findByPrimaryKey(Long id)
        throws NoSuchPlanMetaException, SystemException {
        PlanMeta planMeta = fetchByPrimaryKey(id);

        if (planMeta == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanMeta exists with the primary key " + id);
            }

            throw new NoSuchPlanMetaException(
                "No PlanMeta exists with the primary key " + id);
        }

        return planMeta;
    }

    public PlanMeta fetchByPrimaryKey(Long id) throws SystemException {
        PlanMeta planMeta = (PlanMeta) EntityCacheUtil.getResult(PlanMetaModelImpl.ENTITY_CACHE_ENABLED,
                PlanMetaImpl.class, id, this);

        if (planMeta == null) {
            Session session = null;

            try {
                session = openSession();

                planMeta = (PlanMeta) session.get(PlanMetaImpl.class, id);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planMeta != null) {
                    cacheResult(planMeta);
                }

                closeSession(session);
            }
        }

        return planMeta;
    }

    public PlanMeta findByCurrentByPlanId(Long planId)
        throws NoSuchPlanMetaException, SystemException {
        PlanMeta planMeta = fetchByCurrentByPlanId(planId);

        if (planMeta == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanMeta exists with the key {");

            msg.append("planId=" + planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanMetaException(msg.toString());
        }

        return planMeta;
    }

    public PlanMeta fetchByCurrentByPlanId(Long planId)
        throws SystemException {
        return fetchByCurrentByPlanId(planId, true);
    }

    public PlanMeta fetchByCurrentByPlanId(Long planId,
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

                query.append("FROM com.ext.portlet.plans.model.PlanMeta WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("id_ DESC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                List<PlanMeta> list = q.list();

                result = list;

                PlanMeta planMeta = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                        finderArgs, list);
                } else {
                    planMeta = list.get(0);

                    cacheResult(planMeta);

                    if ((planMeta.getPlanId() == null) ||
                            !planMeta.getPlanId().equals(planId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                            finderArgs, planMeta);
                    }
                }

                return planMeta;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                        finderArgs, new ArrayList<PlanMeta>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (PlanMeta) result;
            }
        }
    }

    public List<PlanMeta> findByAllByPlanId(Long planId)
        throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        List<PlanMeta> list = (List<PlanMeta>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ALLBYPLANID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanMeta WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("id_ DESC");

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
                    list = new ArrayList<PlanMeta>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ALLBYPLANID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<PlanMeta> findByAllByPlanId(Long planId, int start, int end)
        throws SystemException {
        return findByAllByPlanId(planId, start, end, null);
    }

    public List<PlanMeta> findByAllByPlanId(Long planId, int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                planId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanMeta> list = (List<PlanMeta>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_ALLBYPLANID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanMeta WHERE ");

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

                    query.append("id_ DESC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                list = (List<PlanMeta>) QueryUtil.list(q, getDialect(), start,
                        end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanMeta>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_ALLBYPLANID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public PlanMeta findByAllByPlanId_First(Long planId, OrderByComparator obc)
        throws NoSuchPlanMetaException, SystemException {
        List<PlanMeta> list = findByAllByPlanId(planId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanMeta exists with the key {");

            msg.append("planId=" + planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanMetaException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanMeta findByAllByPlanId_Last(Long planId, OrderByComparator obc)
        throws NoSuchPlanMetaException, SystemException {
        int count = countByAllByPlanId(planId);

        List<PlanMeta> list = findByAllByPlanId(planId, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanMeta exists with the key {");

            msg.append("planId=" + planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanMetaException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanMeta[] findByAllByPlanId_PrevAndNext(Long id, Long planId,
        OrderByComparator obc) throws NoSuchPlanMetaException, SystemException {
        PlanMeta planMeta = findByPrimaryKey(id);

        int count = countByAllByPlanId(planId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append("FROM com.ext.portlet.plans.model.PlanMeta WHERE ");

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

                query.append("id_ DESC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (planId != null) {
                qPos.add(planId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, planMeta);

            PlanMeta[] array = new PlanMetaImpl[3];

            array[0] = (PlanMeta) objArray[0];
            array[1] = (PlanMeta) objArray[1];
            array[2] = (PlanMeta) objArray[2];

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

    public List<PlanMeta> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanMeta> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanMeta> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanMeta> list = (List<PlanMeta>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanMeta ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("id_ DESC");
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlanMeta>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanMeta>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanMeta>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByCurrentByPlanId(Long planId)
        throws NoSuchPlanMetaException, SystemException {
        PlanMeta planMeta = findByCurrentByPlanId(planId);

        remove(planMeta);
    }

    public void removeByAllByPlanId(Long planId) throws SystemException {
        for (PlanMeta planMeta : findByAllByPlanId(planId)) {
            remove(planMeta);
        }
    }

    public void removeAll() throws SystemException {
        for (PlanMeta planMeta : findAll()) {
            remove(planMeta);
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
                query.append("FROM com.ext.portlet.plans.model.PlanMeta WHERE ");

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
                query.append("FROM com.ext.portlet.plans.model.PlanMeta WHERE ");

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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanMeta");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanMeta")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanMeta>> listenersList = new ArrayList<ModelListener<PlanMeta>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanMeta>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
