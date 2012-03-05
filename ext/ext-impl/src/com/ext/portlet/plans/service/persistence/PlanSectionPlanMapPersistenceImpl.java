package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanSectionPlanMapException;
import com.ext.portlet.plans.model.PlanSectionPlanMap;
import com.ext.portlet.plans.model.impl.PlanSectionPlanMapImpl;
import com.ext.portlet.plans.model.impl.PlanSectionPlanMapModelImpl;

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


public class PlanSectionPlanMapPersistenceImpl extends BasePersistenceImpl
    implements PlanSectionPlanMapPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanSectionPlanMapImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_PLANID = new FinderPath(PlanSectionPlanMapModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionPlanMapModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByPlanId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_PLANID = new FinderPath(PlanSectionPlanMapModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionPlanMapModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByPlanId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANID = new FinderPath(PlanSectionPlanMapModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionPlanMapModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByPlanId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_SECTIONID = new FinderPath(PlanSectionPlanMapModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionPlanMapModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findBySectionId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_SECTIONID = new FinderPath(PlanSectionPlanMapModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionPlanMapModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findBySectionId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_SECTIONID = new FinderPath(PlanSectionPlanMapModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionPlanMapModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countBySectionId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanSectionPlanMapModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionPlanMapModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanSectionPlanMapModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionPlanMapModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanSectionPlanMapPersistenceImpl.class);
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

    public void cacheResult(PlanSectionPlanMap planSectionPlanMap) {
        EntityCacheUtil.putResult(PlanSectionPlanMapModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionPlanMapImpl.class, planSectionPlanMap.getPrimaryKey(),
            planSectionPlanMap);
    }

    public void cacheResult(List<PlanSectionPlanMap> planSectionPlanMaps) {
        for (PlanSectionPlanMap planSectionPlanMap : planSectionPlanMaps) {
            if (EntityCacheUtil.getResult(
                        PlanSectionPlanMapModelImpl.ENTITY_CACHE_ENABLED,
                        PlanSectionPlanMapImpl.class,
                        planSectionPlanMap.getPrimaryKey(), this) == null) {
                cacheResult(planSectionPlanMap);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanSectionPlanMapImpl.class.getName());
        EntityCacheUtil.clearCache(PlanSectionPlanMapImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanSectionPlanMap create(PlanSectionPlanMapPK planSectionPlanMapPK) {
        PlanSectionPlanMap planSectionPlanMap = new PlanSectionPlanMapImpl();

        planSectionPlanMap.setNew(true);
        planSectionPlanMap.setPrimaryKey(planSectionPlanMapPK);

        return planSectionPlanMap;
    }

    public PlanSectionPlanMap remove(PlanSectionPlanMapPK planSectionPlanMapPK)
        throws NoSuchPlanSectionPlanMapException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanSectionPlanMap planSectionPlanMap = (PlanSectionPlanMap) session.get(PlanSectionPlanMapImpl.class,
                    planSectionPlanMapPK);

            if (planSectionPlanMap == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
                        "No PlanSectionPlanMap exists with the primary key " +
                        planSectionPlanMapPK);
                }

                throw new NoSuchPlanSectionPlanMapException(
                    "No PlanSectionPlanMap exists with the primary key " +
                    planSectionPlanMapPK);
            }

            return remove(planSectionPlanMap);
        } catch (NoSuchPlanSectionPlanMapException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanSectionPlanMap remove(PlanSectionPlanMap planSectionPlanMap)
        throws SystemException {
        for (ModelListener<PlanSectionPlanMap> listener : listeners) {
            listener.onBeforeRemove(planSectionPlanMap);
        }

        planSectionPlanMap = removeImpl(planSectionPlanMap);

        for (ModelListener<PlanSectionPlanMap> listener : listeners) {
            listener.onAfterRemove(planSectionPlanMap);
        }

        return planSectionPlanMap;
    }

    protected PlanSectionPlanMap removeImpl(
        PlanSectionPlanMap planSectionPlanMap) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planSectionPlanMap.isCachedModel() ||
                    BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanSectionPlanMapImpl.class,
                        planSectionPlanMap.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planSectionPlanMap);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(PlanSectionPlanMapModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionPlanMapImpl.class, planSectionPlanMap.getPrimaryKey());

        return planSectionPlanMap;
    }

    /**
     * @deprecated Use <code>update(PlanSectionPlanMap planSectionPlanMap, boolean merge)</code>.
     */
    public PlanSectionPlanMap update(PlanSectionPlanMap planSectionPlanMap)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanSectionPlanMap planSectionPlanMap) method. Use update(PlanSectionPlanMap planSectionPlanMap, boolean merge) instead.");
        }

        return update(planSectionPlanMap, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planSectionPlanMap the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planSectionPlanMap is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanSectionPlanMap update(PlanSectionPlanMap planSectionPlanMap,
        boolean merge) throws SystemException {
        boolean isNew = planSectionPlanMap.isNew();

        for (ModelListener<PlanSectionPlanMap> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planSectionPlanMap);
            } else {
                listener.onBeforeUpdate(planSectionPlanMap);
            }
        }

        planSectionPlanMap = updateImpl(planSectionPlanMap, merge);

        for (ModelListener<PlanSectionPlanMap> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planSectionPlanMap);
            } else {
                listener.onAfterUpdate(planSectionPlanMap);
            }
        }

        return planSectionPlanMap;
    }

    public PlanSectionPlanMap updateImpl(
        com.ext.portlet.plans.model.PlanSectionPlanMap planSectionPlanMap,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planSectionPlanMap, merge);

            planSectionPlanMap.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanSectionPlanMapModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionPlanMapImpl.class, planSectionPlanMap.getPrimaryKey(),
            planSectionPlanMap);

        return planSectionPlanMap;
    }

    public PlanSectionPlanMap findByPrimaryKey(
        PlanSectionPlanMapPK planSectionPlanMapPK)
        throws NoSuchPlanSectionPlanMapException, SystemException {
        PlanSectionPlanMap planSectionPlanMap = fetchByPrimaryKey(planSectionPlanMapPK);

        if (planSectionPlanMap == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanSectionPlanMap exists with the primary key " +
                    planSectionPlanMapPK);
            }

            throw new NoSuchPlanSectionPlanMapException(
                "No PlanSectionPlanMap exists with the primary key " +
                planSectionPlanMapPK);
        }

        return planSectionPlanMap;
    }

    public PlanSectionPlanMap fetchByPrimaryKey(
        PlanSectionPlanMapPK planSectionPlanMapPK) throws SystemException {
        PlanSectionPlanMap planSectionPlanMap = (PlanSectionPlanMap) EntityCacheUtil.getResult(PlanSectionPlanMapModelImpl.ENTITY_CACHE_ENABLED,
                PlanSectionPlanMapImpl.class, planSectionPlanMapPK, this);

        if (planSectionPlanMap == null) {
            Session session = null;

            try {
                session = openSession();

                planSectionPlanMap = (PlanSectionPlanMap) session.get(PlanSectionPlanMapImpl.class,
                        planSectionPlanMapPK);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planSectionPlanMap != null) {
                    cacheResult(planSectionPlanMap);
                }

                closeSession(session);
            }
        }

        return planSectionPlanMap;
    }

    public List<PlanSectionPlanMap> findByPlanId(Long relatedPlanId)
        throws SystemException {
        Object[] finderArgs = new Object[] { relatedPlanId };

        List<PlanSectionPlanMap> list = (List<PlanSectionPlanMap>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_PLANID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanSectionPlanMap WHERE ");

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
                    list = new ArrayList<PlanSectionPlanMap>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_PLANID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<PlanSectionPlanMap> findByPlanId(Long relatedPlanId, int start,
        int end) throws SystemException {
        return findByPlanId(relatedPlanId, start, end, null);
    }

    public List<PlanSectionPlanMap> findByPlanId(Long relatedPlanId, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                relatedPlanId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanSectionPlanMap> list = (List<PlanSectionPlanMap>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_PLANID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanSectionPlanMap WHERE ");

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

                list = (List<PlanSectionPlanMap>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanSectionPlanMap>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_PLANID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public PlanSectionPlanMap findByPlanId_First(Long relatedPlanId,
        OrderByComparator obc)
        throws NoSuchPlanSectionPlanMapException, SystemException {
        List<PlanSectionPlanMap> list = findByPlanId(relatedPlanId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanSectionPlanMap exists with the key {");

            msg.append("relatedPlanId=" + relatedPlanId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanSectionPlanMapException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanSectionPlanMap findByPlanId_Last(Long relatedPlanId,
        OrderByComparator obc)
        throws NoSuchPlanSectionPlanMapException, SystemException {
        int count = countByPlanId(relatedPlanId);

        List<PlanSectionPlanMap> list = findByPlanId(relatedPlanId, count - 1,
                count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanSectionPlanMap exists with the key {");

            msg.append("relatedPlanId=" + relatedPlanId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanSectionPlanMapException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanSectionPlanMap[] findByPlanId_PrevAndNext(
        PlanSectionPlanMapPK planSectionPlanMapPK, Long relatedPlanId,
        OrderByComparator obc)
        throws NoSuchPlanSectionPlanMapException, SystemException {
        PlanSectionPlanMap planSectionPlanMap = findByPrimaryKey(planSectionPlanMapPK);

        int count = countByPlanId(relatedPlanId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.plans.model.PlanSectionPlanMap WHERE ");

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
                    planSectionPlanMap);

            PlanSectionPlanMap[] array = new PlanSectionPlanMapImpl[3];

            array[0] = (PlanSectionPlanMap) objArray[0];
            array[1] = (PlanSectionPlanMap) objArray[1];
            array[2] = (PlanSectionPlanMap) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<PlanSectionPlanMap> findBySectionId(Long sectionId)
        throws SystemException {
        Object[] finderArgs = new Object[] { sectionId };

        List<PlanSectionPlanMap> list = (List<PlanSectionPlanMap>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_SECTIONID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanSectionPlanMap WHERE ");

                if (sectionId == null) {
                    query.append("sectionId IS NULL");
                } else {
                    query.append("sectionId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (sectionId != null) {
                    qPos.add(sectionId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanSectionPlanMap>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_SECTIONID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<PlanSectionPlanMap> findBySectionId(Long sectionId, int start,
        int end) throws SystemException {
        return findBySectionId(sectionId, start, end, null);
    }

    public List<PlanSectionPlanMap> findBySectionId(Long sectionId, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                sectionId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanSectionPlanMap> list = (List<PlanSectionPlanMap>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_SECTIONID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanSectionPlanMap WHERE ");

                if (sectionId == null) {
                    query.append("sectionId IS NULL");
                } else {
                    query.append("sectionId = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (sectionId != null) {
                    qPos.add(sectionId.longValue());
                }

                list = (List<PlanSectionPlanMap>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanSectionPlanMap>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_SECTIONID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public PlanSectionPlanMap findBySectionId_First(Long sectionId,
        OrderByComparator obc)
        throws NoSuchPlanSectionPlanMapException, SystemException {
        List<PlanSectionPlanMap> list = findBySectionId(sectionId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanSectionPlanMap exists with the key {");

            msg.append("sectionId=" + sectionId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanSectionPlanMapException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanSectionPlanMap findBySectionId_Last(Long sectionId,
        OrderByComparator obc)
        throws NoSuchPlanSectionPlanMapException, SystemException {
        int count = countBySectionId(sectionId);

        List<PlanSectionPlanMap> list = findBySectionId(sectionId, count - 1,
                count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanSectionPlanMap exists with the key {");

            msg.append("sectionId=" + sectionId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanSectionPlanMapException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanSectionPlanMap[] findBySectionId_PrevAndNext(
        PlanSectionPlanMapPK planSectionPlanMapPK, Long sectionId,
        OrderByComparator obc)
        throws NoSuchPlanSectionPlanMapException, SystemException {
        PlanSectionPlanMap planSectionPlanMap = findByPrimaryKey(planSectionPlanMapPK);

        int count = countBySectionId(sectionId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.plans.model.PlanSectionPlanMap WHERE ");

            if (sectionId == null) {
                query.append("sectionId IS NULL");
            } else {
                query.append("sectionId = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (sectionId != null) {
                qPos.add(sectionId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    planSectionPlanMap);

            PlanSectionPlanMap[] array = new PlanSectionPlanMapImpl[3];

            array[0] = (PlanSectionPlanMap) objArray[0];
            array[1] = (PlanSectionPlanMap) objArray[1];
            array[2] = (PlanSectionPlanMap) objArray[2];

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

    public List<PlanSectionPlanMap> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanSectionPlanMap> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanSectionPlanMap> findAll(int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanSectionPlanMap> list = (List<PlanSectionPlanMap>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanSectionPlanMap ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlanSectionPlanMap>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanSectionPlanMap>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanSectionPlanMap>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByPlanId(Long relatedPlanId) throws SystemException {
        for (PlanSectionPlanMap planSectionPlanMap : findByPlanId(relatedPlanId)) {
            remove(planSectionPlanMap);
        }
    }

    public void removeBySectionId(Long sectionId) throws SystemException {
        for (PlanSectionPlanMap planSectionPlanMap : findBySectionId(sectionId)) {
            remove(planSectionPlanMap);
        }
    }

    public void removeAll() throws SystemException {
        for (PlanSectionPlanMap planSectionPlanMap : findAll()) {
            remove(planSectionPlanMap);
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
                    "FROM com.ext.portlet.plans.model.PlanSectionPlanMap WHERE ");

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

    public int countBySectionId(Long sectionId) throws SystemException {
        Object[] finderArgs = new Object[] { sectionId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SECTIONID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.plans.model.PlanSectionPlanMap WHERE ");

                if (sectionId == null) {
                    query.append("sectionId IS NULL");
                } else {
                    query.append("sectionId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (sectionId != null) {
                    qPos.add(sectionId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SECTIONID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanSectionPlanMap");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanSectionPlanMap")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanSectionPlanMap>> listenersList = new ArrayList<ModelListener<PlanSectionPlanMap>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanSectionPlanMap>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
