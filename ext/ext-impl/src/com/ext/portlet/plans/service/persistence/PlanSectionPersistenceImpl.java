package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanSectionException;
import com.ext.portlet.plans.model.PlanSection;
import com.ext.portlet.plans.model.impl.PlanSectionImpl;
import com.ext.portlet.plans.model.impl.PlanSectionModelImpl;

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


public class PlanSectionPersistenceImpl extends BasePersistenceImpl
    implements PlanSectionPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanSectionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_PLANIDSECTIONDEFINITIONID =
        new FinderPath(PlanSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByPlanIdSectionDefinitionId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_PLANIDSECTIONDEFINITIONID =
        new FinderPath(PlanSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByPlanIdSectionDefinitionId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANIDSECTIONDEFINITIONID =
        new FinderPath(PlanSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByPlanIdSectionDefinitionId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_CURRENTPLANIDSECTIONDEFINITIONID =
        new FinderPath(PlanSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY,
            "fetchByCurrentPlanIdSectionDefinitionId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_CURRENTPLANIDSECTIONDEFINITIONID =
        new FinderPath(PlanSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByCurrentPlanIdSectionDefinitionId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanSectionPersistenceImpl.class);
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

    public void cacheResult(PlanSection planSection) {
        EntityCacheUtil.putResult(PlanSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionImpl.class, planSection.getPrimaryKey(), planSection);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTPLANIDSECTIONDEFINITIONID,
            new Object[] {
                planSection.getPlanId(),
                
            planSection.getPlanSectionDefinitionId()
            }, planSection);
    }

    public void cacheResult(List<PlanSection> planSections) {
        for (PlanSection planSection : planSections) {
            if (EntityCacheUtil.getResult(
                        PlanSectionModelImpl.ENTITY_CACHE_ENABLED,
                        PlanSectionImpl.class, planSection.getPrimaryKey(), this) == null) {
                cacheResult(planSection);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanSectionImpl.class.getName());
        EntityCacheUtil.clearCache(PlanSectionImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanSection create(Long id) {
        PlanSection planSection = new PlanSectionImpl();

        planSection.setNew(true);
        planSection.setPrimaryKey(id);

        return planSection;
    }

    public PlanSection remove(Long id)
        throws NoSuchPlanSectionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanSection planSection = (PlanSection) session.get(PlanSectionImpl.class,
                    id);

            if (planSection == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No PlanSection exists with the primary key " +
                        id);
                }

                throw new NoSuchPlanSectionException(
                    "No PlanSection exists with the primary key " + id);
            }

            return remove(planSection);
        } catch (NoSuchPlanSectionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanSection remove(PlanSection planSection)
        throws SystemException {
        for (ModelListener<PlanSection> listener : listeners) {
            listener.onBeforeRemove(planSection);
        }

        planSection = removeImpl(planSection);

        for (ModelListener<PlanSection> listener : listeners) {
            listener.onAfterRemove(planSection);
        }

        return planSection;
    }

    protected PlanSection removeImpl(PlanSection planSection)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planSection.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanSectionImpl.class,
                        planSection.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planSection);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        PlanSectionModelImpl planSectionModelImpl = (PlanSectionModelImpl) planSection;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CURRENTPLANIDSECTIONDEFINITIONID,
            new Object[] {
                planSectionModelImpl.getOriginalPlanId(),
                
            planSectionModelImpl.getOriginalPlanSectionDefinitionId()
            });

        EntityCacheUtil.removeResult(PlanSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionImpl.class, planSection.getPrimaryKey());

        return planSection;
    }

    /**
     * @deprecated Use <code>update(PlanSection planSection, boolean merge)</code>.
     */
    public PlanSection update(PlanSection planSection)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanSection planSection) method. Use update(PlanSection planSection, boolean merge) instead.");
        }

        return update(planSection, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planSection the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planSection is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanSection update(PlanSection planSection, boolean merge)
        throws SystemException {
        boolean isNew = planSection.isNew();

        for (ModelListener<PlanSection> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planSection);
            } else {
                listener.onBeforeUpdate(planSection);
            }
        }

        planSection = updateImpl(planSection, merge);

        for (ModelListener<PlanSection> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planSection);
            } else {
                listener.onAfterUpdate(planSection);
            }
        }

        return planSection;
    }

    public PlanSection updateImpl(
        com.ext.portlet.plans.model.PlanSection planSection, boolean merge)
        throws SystemException {
        boolean isNew = planSection.isNew();

        PlanSectionModelImpl planSectionModelImpl = (PlanSectionModelImpl) planSection;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planSection, merge);

            planSection.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionImpl.class, planSection.getPrimaryKey(), planSection);

        if (!isNew &&
                (!Validator.equals(planSection.getPlanId(),
                    planSectionModelImpl.getOriginalPlanId()) ||
                !Validator.equals(planSection.getPlanSectionDefinitionId(),
                    planSectionModelImpl.getOriginalPlanSectionDefinitionId()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CURRENTPLANIDSECTIONDEFINITIONID,
                new Object[] {
                    planSectionModelImpl.getOriginalPlanId(),
                    
                planSectionModelImpl.getOriginalPlanSectionDefinitionId()
                });
        }

        if (isNew ||
                (!Validator.equals(planSection.getPlanId(),
                    planSectionModelImpl.getOriginalPlanId()) ||
                !Validator.equals(planSection.getPlanSectionDefinitionId(),
                    planSectionModelImpl.getOriginalPlanSectionDefinitionId()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTPLANIDSECTIONDEFINITIONID,
                new Object[] {
                    planSection.getPlanId(),
                    
                planSection.getPlanSectionDefinitionId()
                }, planSection);
        }

        return planSection;
    }

    public PlanSection findByPrimaryKey(Long id)
        throws NoSuchPlanSectionException, SystemException {
        PlanSection planSection = fetchByPrimaryKey(id);

        if (planSection == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanSection exists with the primary key " + id);
            }

            throw new NoSuchPlanSectionException(
                "No PlanSection exists with the primary key " + id);
        }

        return planSection;
    }

    public PlanSection fetchByPrimaryKey(Long id) throws SystemException {
        PlanSection planSection = (PlanSection) EntityCacheUtil.getResult(PlanSectionModelImpl.ENTITY_CACHE_ENABLED,
                PlanSectionImpl.class, id, this);

        if (planSection == null) {
            Session session = null;

            try {
                session = openSession();

                planSection = (PlanSection) session.get(PlanSectionImpl.class,
                        id);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planSection != null) {
                    cacheResult(planSection);
                }

                closeSession(session);
            }
        }

        return planSection;
    }

    public List<PlanSection> findByPlanIdSectionDefinitionId(Long planId,
        Long planSectionDefinitionId) throws SystemException {
        Object[] finderArgs = new Object[] { planId, planSectionDefinitionId };

        List<PlanSection> list = (List<PlanSection>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_PLANIDSECTIONDEFINITIONID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanSection WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" AND ");

                if (planSectionDefinitionId == null) {
                    query.append("planSectionDefinitionId IS NULL");
                } else {
                    query.append("planSectionDefinitionId = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("id_ DESC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                if (planSectionDefinitionId != null) {
                    qPos.add(planSectionDefinitionId.longValue());
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanSection>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_PLANIDSECTIONDEFINITIONID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<PlanSection> findByPlanIdSectionDefinitionId(Long planId,
        Long planSectionDefinitionId, int start, int end)
        throws SystemException {
        return findByPlanIdSectionDefinitionId(planId, planSectionDefinitionId,
            start, end, null);
    }

    public List<PlanSection> findByPlanIdSectionDefinitionId(Long planId,
        Long planSectionDefinitionId, int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                planId,
                
                planSectionDefinitionId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanSection> list = (List<PlanSection>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_PLANIDSECTIONDEFINITIONID,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanSection WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" AND ");

                if (planSectionDefinitionId == null) {
                    query.append("planSectionDefinitionId IS NULL");
                } else {
                    query.append("planSectionDefinitionId = ?");
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

                if (planSectionDefinitionId != null) {
                    qPos.add(planSectionDefinitionId.longValue());
                }

                list = (List<PlanSection>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanSection>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_PLANIDSECTIONDEFINITIONID,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public PlanSection findByPlanIdSectionDefinitionId_First(Long planId,
        Long planSectionDefinitionId, OrderByComparator obc)
        throws NoSuchPlanSectionException, SystemException {
        List<PlanSection> list = findByPlanIdSectionDefinitionId(planId,
                planSectionDefinitionId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanSection exists with the key {");

            msg.append("planId=" + planId);

            msg.append(", ");
            msg.append("planSectionDefinitionId=" + planSectionDefinitionId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanSectionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanSection findByPlanIdSectionDefinitionId_Last(Long planId,
        Long planSectionDefinitionId, OrderByComparator obc)
        throws NoSuchPlanSectionException, SystemException {
        int count = countByPlanIdSectionDefinitionId(planId,
                planSectionDefinitionId);

        List<PlanSection> list = findByPlanIdSectionDefinitionId(planId,
                planSectionDefinitionId, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanSection exists with the key {");

            msg.append("planId=" + planId);

            msg.append(", ");
            msg.append("planSectionDefinitionId=" + planSectionDefinitionId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanSectionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanSection[] findByPlanIdSectionDefinitionId_PrevAndNext(Long id,
        Long planId, Long planSectionDefinitionId, OrderByComparator obc)
        throws NoSuchPlanSectionException, SystemException {
        PlanSection planSection = findByPrimaryKey(id);

        int count = countByPlanIdSectionDefinitionId(planId,
                planSectionDefinitionId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append("FROM com.ext.portlet.plans.model.PlanSection WHERE ");

            if (planId == null) {
                query.append("planId IS NULL");
            } else {
                query.append("planId = ?");
            }

            query.append(" AND ");

            if (planSectionDefinitionId == null) {
                query.append("planSectionDefinitionId IS NULL");
            } else {
                query.append("planSectionDefinitionId = ?");
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

            if (planSectionDefinitionId != null) {
                qPos.add(planSectionDefinitionId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    planSection);

            PlanSection[] array = new PlanSectionImpl[3];

            array[0] = (PlanSection) objArray[0];
            array[1] = (PlanSection) objArray[1];
            array[2] = (PlanSection) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanSection findByCurrentPlanIdSectionDefinitionId(Long planId,
        Long planSectionDefinitionId)
        throws NoSuchPlanSectionException, SystemException {
        PlanSection planSection = fetchByCurrentPlanIdSectionDefinitionId(planId,
                planSectionDefinitionId);

        if (planSection == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanSection exists with the key {");

            msg.append("planId=" + planId);

            msg.append(", ");
            msg.append("planSectionDefinitionId=" + planSectionDefinitionId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanSectionException(msg.toString());
        }

        return planSection;
    }

    public PlanSection fetchByCurrentPlanIdSectionDefinitionId(Long planId,
        Long planSectionDefinitionId) throws SystemException {
        return fetchByCurrentPlanIdSectionDefinitionId(planId,
            planSectionDefinitionId, true);
    }

    public PlanSection fetchByCurrentPlanIdSectionDefinitionId(Long planId,
        Long planSectionDefinitionId, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { planId, planSectionDefinitionId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CURRENTPLANIDSECTIONDEFINITIONID,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanSection WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" AND ");

                if (planSectionDefinitionId == null) {
                    query.append("planSectionDefinitionId IS NULL");
                } else {
                    query.append("planSectionDefinitionId = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("id_ DESC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                if (planSectionDefinitionId != null) {
                    qPos.add(planSectionDefinitionId.longValue());
                }

                List<PlanSection> list = q.list();

                result = list;

                PlanSection planSection = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTPLANIDSECTIONDEFINITIONID,
                        finderArgs, list);
                } else {
                    planSection = list.get(0);

                    cacheResult(planSection);

                    if ((planSection.getPlanId() == null) ||
                            !planSection.getPlanId().equals(planId) ||
                            (planSection.getPlanSectionDefinitionId() == null) ||
                            !planSection.getPlanSectionDefinitionId()
                                            .equals(planSectionDefinitionId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTPLANIDSECTIONDEFINITIONID,
                            finderArgs, planSection);
                    }
                }

                return planSection;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTPLANIDSECTIONDEFINITIONID,
                        finderArgs, new ArrayList<PlanSection>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (PlanSection) result;
            }
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

    public List<PlanSection> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanSection> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanSection> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanSection> list = (List<PlanSection>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanSection ");

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
                    list = (List<PlanSection>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanSection>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanSection>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByPlanIdSectionDefinitionId(Long planId,
        Long planSectionDefinitionId) throws SystemException {
        for (PlanSection planSection : findByPlanIdSectionDefinitionId(planId,
                planSectionDefinitionId)) {
            remove(planSection);
        }
    }

    public void removeByCurrentPlanIdSectionDefinitionId(Long planId,
        Long planSectionDefinitionId)
        throws NoSuchPlanSectionException, SystemException {
        PlanSection planSection = findByCurrentPlanIdSectionDefinitionId(planId,
                planSectionDefinitionId);

        remove(planSection);
    }

    public void removeAll() throws SystemException {
        for (PlanSection planSection : findAll()) {
            remove(planSection);
        }
    }

    public int countByPlanIdSectionDefinitionId(Long planId,
        Long planSectionDefinitionId) throws SystemException {
        Object[] finderArgs = new Object[] { planId, planSectionDefinitionId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PLANIDSECTIONDEFINITIONID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.plans.model.PlanSection WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" AND ");

                if (planSectionDefinitionId == null) {
                    query.append("planSectionDefinitionId IS NULL");
                } else {
                    query.append("planSectionDefinitionId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                if (planSectionDefinitionId != null) {
                    qPos.add(planSectionDefinitionId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANIDSECTIONDEFINITIONID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByCurrentPlanIdSectionDefinitionId(Long planId,
        Long planSectionDefinitionId) throws SystemException {
        Object[] finderArgs = new Object[] { planId, planSectionDefinitionId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CURRENTPLANIDSECTIONDEFINITIONID,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.plans.model.PlanSection WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" AND ");

                if (planSectionDefinitionId == null) {
                    query.append("planSectionDefinitionId IS NULL");
                } else {
                    query.append("planSectionDefinitionId = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                if (planSectionDefinitionId != null) {
                    qPos.add(planSectionDefinitionId.longValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CURRENTPLANIDSECTIONDEFINITIONID,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanSection");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanSection")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanSection>> listenersList = new ArrayList<ModelListener<PlanSection>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanSection>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
