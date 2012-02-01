package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanException;
import com.ext.portlet.plans.model.Plan;
import com.ext.portlet.plans.model.impl.PlanImpl;
import com.ext.portlet.plans.model.impl.PlanModelImpl;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PlanPersistenceImpl extends BasePersistenceImpl
    implements PlanPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_NAME = new FinderPath(PlanModelImpl.ENTITY_CACHE_ENABLED,
            PlanModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByname", new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_NAME = new FinderPath(PlanModelImpl.ENTITY_CACHE_ENABLED,
            PlanModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findByname",
            new String[] {
                String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(PlanModelImpl.ENTITY_CACHE_ENABLED,
            PlanModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countByname", new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanModelImpl.ENTITY_CACHE_ENABLED,
            PlanModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanModelImpl.ENTITY_CACHE_ENABLED,
            PlanModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_GET_PLANATTRIBUTES = new FinderPath(com.ext.portlet.plans.model.impl.PlanAttributeModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanAttributeModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.service.persistence.PlanAttributePersistenceImpl.FINDER_CLASS_NAME_LIST,
            "getPlanAttributes",
            new String[] {
                Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_GET_PLANATTRIBUTES_SIZE = new FinderPath(com.ext.portlet.plans.model.impl.PlanAttributeModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanAttributeModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.service.persistence.PlanAttributePersistenceImpl.FINDER_CLASS_NAME_LIST,
            "getPlanAttributesSize", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_CONTAINS_PLANATTRIBUTE = new FinderPath(com.ext.portlet.plans.model.impl.PlanAttributeModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanAttributeModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.service.persistence.PlanAttributePersistenceImpl.FINDER_CLASS_NAME_LIST,
            "containsPlanAttribute",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _SQL_GETPLANATTRIBUTES = "SELECT {PlanAttribute.*} FROM PlanAttribute INNER JOIN Plan ON (Plan.planId = PlanAttribute.planId) WHERE (Plan.planId = ?)";
    private static final String _SQL_GETPLANATTRIBUTESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM PlanAttribute WHERE planId = ?";
    private static final String _SQL_CONTAINSPLANATTRIBUTE = "SELECT COUNT(*) AS COUNT_VALUE FROM PlanAttribute WHERE planId = ? AND attributeId = ?";
    private static Log _log = LogFactoryUtil.getLog(PlanPersistenceImpl.class);
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
    protected ContainsPlanAttribute containsPlanAttribute;

    public void cacheResult(Plan plan) {
        EntityCacheUtil.putResult(PlanModelImpl.ENTITY_CACHE_ENABLED,
            PlanImpl.class, plan.getPrimaryKey(), plan);
    }

    public void cacheResult(List<Plan> plans) {
        for (Plan plan : plans) {
            if (EntityCacheUtil.getResult(PlanModelImpl.ENTITY_CACHE_ENABLED,
                        PlanImpl.class, plan.getPrimaryKey(), this) == null) {
                cacheResult(plan);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanImpl.class.getName());
        EntityCacheUtil.clearCache(PlanImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public Plan create(Long planId) {
        Plan plan = new PlanImpl();

        plan.setNew(true);
        plan.setPrimaryKey(planId);

        return plan;
    }

    public Plan remove(Long planId) throws NoSuchPlanException, SystemException {
        Session session = null;

        try {
            session = openSession();

            Plan plan = (Plan) session.get(PlanImpl.class, planId);

            if (plan == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No Plan exists with the primary key " + planId);
                }

                throw new NoSuchPlanException(
                    "No Plan exists with the primary key " + planId);
            }

            return remove(plan);
        } catch (NoSuchPlanException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public Plan remove(Plan plan) throws SystemException {
        for (ModelListener<Plan> listener : listeners) {
            listener.onBeforeRemove(plan);
        }

        plan = removeImpl(plan);

        for (ModelListener<Plan> listener : listeners) {
            listener.onAfterRemove(plan);
        }

        return plan;
    }

    protected Plan removeImpl(Plan plan) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (plan.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanImpl.class,
                        plan.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(plan);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(PlanModelImpl.ENTITY_CACHE_ENABLED,
            PlanImpl.class, plan.getPrimaryKey());

        return plan;
    }

    /**
     * @deprecated Use <code>update(Plan plan, boolean merge)</code>.
     */
    public Plan update(Plan plan) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(Plan plan) method. Use update(Plan plan, boolean merge) instead.");
        }

        return update(plan, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                plan the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when plan is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public Plan update(Plan plan, boolean merge) throws SystemException {
        boolean isNew = plan.isNew();

        for (ModelListener<Plan> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(plan);
            } else {
                listener.onBeforeUpdate(plan);
            }
        }

        plan = updateImpl(plan, merge);

        for (ModelListener<Plan> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(plan);
            } else {
                listener.onAfterUpdate(plan);
            }
        }

        return plan;
    }

    public Plan updateImpl(com.ext.portlet.plans.model.Plan plan, boolean merge)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, plan, merge);

            plan.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanModelImpl.ENTITY_CACHE_ENABLED,
            PlanImpl.class, plan.getPrimaryKey(), plan);

        return plan;
    }

    public Plan findByPrimaryKey(Long planId)
        throws NoSuchPlanException, SystemException {
        Plan plan = fetchByPrimaryKey(planId);

        if (plan == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No Plan exists with the primary key " + planId);
            }

            throw new NoSuchPlanException(
                "No Plan exists with the primary key " + planId);
        }

        return plan;
    }

    public Plan fetchByPrimaryKey(Long planId) throws SystemException {
        Plan plan = (Plan) EntityCacheUtil.getResult(PlanModelImpl.ENTITY_CACHE_ENABLED,
                PlanImpl.class, planId, this);

        if (plan == null) {
            Session session = null;

            try {
                session = openSession();

                plan = (Plan) session.get(PlanImpl.class, planId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (plan != null) {
                    cacheResult(plan);
                }

                closeSession(session);
            }
        }

        return plan;
    }

    public List<Plan> findByname(String name) throws SystemException {
        Object[] finderArgs = new Object[] { name };

        List<Plan> list = (List<Plan>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_NAME,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.Plan WHERE ");

                if (name == null) {
                    query.append("name IS NULL");
                } else {
                    query.append("name = ?");
                }

                query.append(" ");

                query.append("ORDER BY ");

                query.append("name ASC");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (name != null) {
                    qPos.add(name);
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<Plan>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_NAME, finderArgs,
                    list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<Plan> findByname(String name, int start, int end)
        throws SystemException {
        return findByname(name, start, end, null);
    }

    public List<Plan> findByname(String name, int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                name,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<Plan> list = (List<Plan>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_NAME,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.Plan WHERE ");

                if (name == null) {
                    query.append("name IS NULL");
                } else {
                    query.append("name = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("name ASC");
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (name != null) {
                    qPos.add(name);
                }

                list = (List<Plan>) QueryUtil.list(q, getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<Plan>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_NAME,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public Plan findByname_First(String name, OrderByComparator obc)
        throws NoSuchPlanException, SystemException {
        List<Plan> list = findByname(name, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No Plan exists with the key {");

            msg.append("name=" + name);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public Plan findByname_Last(String name, OrderByComparator obc)
        throws NoSuchPlanException, SystemException {
        int count = countByname(name);

        List<Plan> list = findByname(name, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No Plan exists with the key {");

            msg.append("name=" + name);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public Plan[] findByname_PrevAndNext(Long planId, String name,
        OrderByComparator obc) throws NoSuchPlanException, SystemException {
        Plan plan = findByPrimaryKey(planId);

        int count = countByname(name);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append("FROM com.ext.portlet.plans.model.Plan WHERE ");

            if (name == null) {
                query.append("name IS NULL");
            } else {
                query.append("name = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }
            else {
                query.append("ORDER BY ");

                query.append("name ASC");
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (name != null) {
                qPos.add(name);
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, plan);

            Plan[] array = new PlanImpl[3];

            array[0] = (Plan) objArray[0];
            array[1] = (Plan) objArray[1];
            array[2] = (Plan) objArray[2];

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

    public List<Plan> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<Plan> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    public List<Plan> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<Plan> list = (List<Plan>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.Plan ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }
                else {
                    query.append("ORDER BY ");

                    query.append("name ASC");
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<Plan>) QueryUtil.list(q, getDialect(), start,
                            end, false);

                    Collections.sort(list);
                } else {
                    list = (List<Plan>) QueryUtil.list(q, getDialect(), start,
                            end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<Plan>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByname(String name) throws SystemException {
        for (Plan plan : findByname(name)) {
            remove(plan);
        }
    }

    public void removeAll() throws SystemException {
        for (Plan plan : findAll()) {
            remove(plan);
        }
    }

    public int countByname(String name) throws SystemException {
        Object[] finderArgs = new Object[] { name };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_NAME,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append("FROM com.ext.portlet.plans.model.Plan WHERE ");

                if (name == null) {
                    query.append("name IS NULL");
                } else {
                    query.append("name = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (name != null) {
                    qPos.add(name);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.Plan");

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

    public List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes(
        Long pk) throws SystemException {
        return getPlanAttributes(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    public List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes(
        Long pk, int start, int end) throws SystemException {
        return getPlanAttributes(pk, start, end, null);
    }

    public List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes(
        Long pk, int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                pk, String.valueOf(start), String.valueOf(end),
                String.valueOf(obc)
            };

        List<com.ext.portlet.plans.model.PlanAttribute> list = (List<com.ext.portlet.plans.model.PlanAttribute>) FinderCacheUtil.getResult(FINDER_PATH_GET_PLANATTRIBUTES,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder sb = new StringBuilder();

                sb.append(_SQL_GETPLANATTRIBUTES);

                if (obc != null) {
                    sb.append("ORDER BY ");
                    sb.append(obc.getOrderBy());
                }

                String sql = sb.toString();

                SQLQuery q = session.createSQLQuery(sql);

                q.addEntity("PlanAttribute",
                    com.ext.portlet.plans.model.impl.PlanAttributeImpl.class);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                list = (List<com.ext.portlet.plans.model.PlanAttribute>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<com.ext.portlet.plans.model.PlanAttribute>();
                }

                planAttributePersistence.cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_GET_PLANATTRIBUTES,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public int getPlanAttributesSize(Long pk) throws SystemException {
        Object[] finderArgs = new Object[] { pk };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_GET_PLANATTRIBUTES_SIZE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                SQLQuery q = session.createSQLQuery(_SQL_GETPLANATTRIBUTESSIZE);

                q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_GET_PLANATTRIBUTES_SIZE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public boolean containsPlanAttribute(Long pk, Long planAttributePK)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, planAttributePK };

        Boolean value = (Boolean) FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_PLANATTRIBUTE,
                finderArgs, this);

        if (value == null) {
            try {
                value = Boolean.valueOf(containsPlanAttribute.contains(pk,
                            planAttributePK));
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (value == null) {
                    value = Boolean.FALSE;
                }

                FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_PLANATTRIBUTE,
                    finderArgs, value);
            }
        }

        return value.booleanValue();
    }

    public boolean containsPlanAttributes(Long pk) throws SystemException {
        if (getPlanAttributesSize(pk) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.portal.util.PropsUtil.get(
                        "value.object.listener.com.ext.portlet.plans.model.Plan")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<Plan>> listenersList = new ArrayList<ModelListener<Plan>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<Plan>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }

        containsPlanAttribute = new ContainsPlanAttribute(this);
    }

    protected class ContainsPlanAttribute {
        private MappingSqlQuery _mappingSqlQuery;

        protected ContainsPlanAttribute(PlanPersistenceImpl persistenceImpl) {
            super();

            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSPLANATTRIBUTE,
                    new int[] { Types.BIGINT, Types.BIGINT }, RowMapper.COUNT);
        }

        protected boolean contains(Long planId, Long attributeId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        planId, attributeId
                    });

            if (results.size() > 0) {
                Integer count = results.get(0);

                if (count.intValue() > 0) {
                    return true;
                }
            }

            return false;
        }
    }
}
