package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanRelatedException;
import com.ext.portlet.plans.model.PlanRelated;
import com.ext.portlet.plans.model.impl.PlanRelatedImpl;
import com.ext.portlet.plans.model.impl.PlanRelatedModelImpl;

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


public class PlanRelatedPersistenceImpl extends BasePersistenceImpl
    implements PlanRelatedPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanRelatedImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_PLANID = new FinderPath(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByPlanId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_PLANID = new FinderPath(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByPlanId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANID = new FinderPath(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByPlanId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanRelatedPersistenceImpl.class);
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
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPersistence planSectionPlanMapPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanRelatedPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanRelatedPersistence planRelatedPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanTemplatePersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanTemplatePersistence planTemplatePersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanTemplateSectionPersistence.impl")
    protected com.ext.portlet.plans.service.persistence.PlanTemplateSectionPersistence planTemplateSectionPersistence;

    public void cacheResult(PlanRelated planRelated) {
        EntityCacheUtil.putResult(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedImpl.class, planRelated.getPrimaryKey(), planRelated);
    }

    public void cacheResult(List<PlanRelated> planRelateds) {
        for (PlanRelated planRelated : planRelateds) {
            if (EntityCacheUtil.getResult(
                        PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
                        PlanRelatedImpl.class, planRelated.getPrimaryKey(), this) == null) {
                cacheResult(planRelated);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanRelatedImpl.class.getName());
        EntityCacheUtil.clearCache(PlanRelatedImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanRelated create(PlanRelatedPK planRelatedPK) {
        PlanRelated planRelated = new PlanRelatedImpl();

        planRelated.setNew(true);
        planRelated.setPrimaryKey(planRelatedPK);

        return planRelated;
    }

    public PlanRelated remove(PlanRelatedPK planRelatedPK)
        throws NoSuchPlanRelatedException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanRelated planRelated = (PlanRelated) session.get(PlanRelatedImpl.class,
                    planRelatedPK);

            if (planRelated == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No PlanRelated exists with the primary key " +
                        planRelatedPK);
                }

                throw new NoSuchPlanRelatedException(
                    "No PlanRelated exists with the primary key " +
                    planRelatedPK);
            }

            return remove(planRelated);
        } catch (NoSuchPlanRelatedException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanRelated remove(PlanRelated planRelated)
        throws SystemException {
        for (ModelListener<PlanRelated> listener : listeners) {
            listener.onBeforeRemove(planRelated);
        }

        planRelated = removeImpl(planRelated);

        for (ModelListener<PlanRelated> listener : listeners) {
            listener.onAfterRemove(planRelated);
        }

        return planRelated;
    }

    protected PlanRelated removeImpl(PlanRelated planRelated)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planRelated.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanRelatedImpl.class,
                        planRelated.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planRelated);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedImpl.class, planRelated.getPrimaryKey());

        return planRelated;
    }

    /**
     * @deprecated Use <code>update(PlanRelated planRelated, boolean merge)</code>.
     */
    public PlanRelated update(PlanRelated planRelated)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanRelated planRelated) method. Use update(PlanRelated planRelated, boolean merge) instead.");
        }

        return update(planRelated, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planRelated the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planRelated is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanRelated update(PlanRelated planRelated, boolean merge)
        throws SystemException {
        boolean isNew = planRelated.isNew();

        for (ModelListener<PlanRelated> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planRelated);
            } else {
                listener.onBeforeUpdate(planRelated);
            }
        }

        planRelated = updateImpl(planRelated, merge);

        for (ModelListener<PlanRelated> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planRelated);
            } else {
                listener.onAfterUpdate(planRelated);
            }
        }

        return planRelated;
    }

    public PlanRelated updateImpl(
        com.ext.portlet.plans.model.PlanRelated planRelated, boolean merge)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planRelated, merge);

            planRelated.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedImpl.class, planRelated.getPrimaryKey(), planRelated);

        return planRelated;
    }

    public PlanRelated findByPrimaryKey(PlanRelatedPK planRelatedPK)
        throws NoSuchPlanRelatedException, SystemException {
        PlanRelated planRelated = fetchByPrimaryKey(planRelatedPK);

        if (planRelated == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanRelated exists with the primary key " +
                    planRelatedPK);
            }

            throw new NoSuchPlanRelatedException(
                "No PlanRelated exists with the primary key " + planRelatedPK);
        }

        return planRelated;
    }

    public PlanRelated fetchByPrimaryKey(PlanRelatedPK planRelatedPK)
        throws SystemException {
        PlanRelated planRelated = (PlanRelated) EntityCacheUtil.getResult(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
                PlanRelatedImpl.class, planRelatedPK, this);

        if (planRelated == null) {
            Session session = null;

            try {
                session = openSession();

                planRelated = (PlanRelated) session.get(PlanRelatedImpl.class,
                        planRelatedPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planRelated != null) {
                    cacheResult(planRelated);
                }

                closeSession(session);
            }
        }

        return planRelated;
    }

    public List<PlanRelated> findByPlanId(Long relatedPlanId)
        throws SystemException {
        Object[] finderArgs = new Object[] { relatedPlanId };

        List<PlanRelated> list = (List<PlanRelated>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_PLANID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanRelated WHERE ");

                if (relatedPlanId == null) {
                    query.append("relatedPlanId IS NULL");
                } else {
                    query.append("relatedPlanId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (relatedPlanId != null) {
                    qPos.add(relatedPlanId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanRelated>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_PLANID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<PlanRelated> findByPlanId(Long relatedPlanId, int start, int end)
        throws SystemException {
        return findByPlanId(relatedPlanId, start, end, null);
    }

    public List<PlanRelated> findByPlanId(Long relatedPlanId, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                relatedPlanId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanRelated> list = (List<PlanRelated>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_PLANID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanRelated WHERE ");

                if (relatedPlanId == null) {
                    query.append("relatedPlanId IS NULL");
                } else {
                    query.append("relatedPlanId = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (relatedPlanId != null) {
                    qPos.add(relatedPlanId.longValue());
                }

                list = (List<PlanRelated>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanRelated>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_PLANID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public PlanRelated findByPlanId_First(Long relatedPlanId,
        OrderByComparator obc)
        throws NoSuchPlanRelatedException, SystemException {
        List<PlanRelated> list = findByPlanId(relatedPlanId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanRelated exists with the key {");

            msg.append("relatedPlanId=" + relatedPlanId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanRelatedException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanRelated findByPlanId_Last(Long relatedPlanId,
        OrderByComparator obc)
        throws NoSuchPlanRelatedException, SystemException {
        int count = countByPlanId(relatedPlanId);

        List<PlanRelated> list = findByPlanId(relatedPlanId, count - 1, count,
                obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanRelated exists with the key {");

            msg.append("relatedPlanId=" + relatedPlanId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanRelatedException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanRelated[] findByPlanId_PrevAndNext(PlanRelatedPK planRelatedPK,
        Long relatedPlanId, OrderByComparator obc)
        throws NoSuchPlanRelatedException, SystemException {
        PlanRelated planRelated = findByPrimaryKey(planRelatedPK);

        int count = countByPlanId(relatedPlanId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append("FROM com.ext.portlet.plans.model.PlanRelated WHERE ");

            if (relatedPlanId == null) {
                query.append("relatedPlanId IS NULL");
            } else {
                query.append("relatedPlanId = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (relatedPlanId != null) {
                qPos.add(relatedPlanId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    planRelated);

            PlanRelated[] array = new PlanRelatedImpl[3];

            array[0] = (PlanRelated) objArray[0];
            array[1] = (PlanRelated) objArray[1];
            array[2] = (PlanRelated) objArray[2];

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

    public List<PlanRelated> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanRelated> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanRelated> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanRelated> list = (List<PlanRelated>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanRelated ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlanRelated>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanRelated>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanRelated>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByPlanId(Long relatedPlanId) throws SystemException {
        for (PlanRelated planRelated : findByPlanId(relatedPlanId)) {
            remove(planRelated);
        }
    }

    public void removeAll() throws SystemException {
        for (PlanRelated planRelated : findAll()) {
            remove(planRelated);
        }
    }

    public int countByPlanId(Long relatedPlanId) throws SystemException {
        Object[] finderArgs = new Object[] { relatedPlanId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PLANID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.plans.model.PlanRelated WHERE ");

                if (relatedPlanId == null) {
                    query.append("relatedPlanId IS NULL");
                } else {
                    query.append("relatedPlanId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (relatedPlanId != null) {
                    qPos.add(relatedPlanId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanRelated");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanRelated")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanRelated>> listenersList = new ArrayList<ModelListener<PlanRelated>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanRelated>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
