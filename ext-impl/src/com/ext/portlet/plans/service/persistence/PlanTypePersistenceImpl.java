package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanTypeException;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.impl.PlanTypeImpl;
import com.ext.portlet.plans.model.impl.PlanTypeModelImpl;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PlanTypePersistenceImpl extends BasePersistenceImpl
    implements PlanTypePersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanTypeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FETCH_BY_DEFAULT = new FinderPath(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
            "fetchBydefault", new String[] { Boolean.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_DEFAULT = new FinderPath(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countBydefault", new String[] { Boolean.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_GET_PLANTYPEATTRIBUTES = new FinderPath(com.ext.portlet.plans.model.impl.PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanTypeAttributeModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.service.persistence.PlanTypeAttributePersistenceImpl.FINDER_CLASS_NAME_LIST,
            "getPlanTypeAttributes",
            new String[] {
                Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_GET_PLANTYPEATTRIBUTES_SIZE = new FinderPath(com.ext.portlet.plans.model.impl.PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanTypeAttributeModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.service.persistence.PlanTypeAttributePersistenceImpl.FINDER_CLASS_NAME_LIST,
            "getPlanTypeAttributesSize", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_CONTAINS_PLANTYPEATTRIBUTE = new FinderPath(com.ext.portlet.plans.model.impl.PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanTypeAttributeModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.service.persistence.PlanTypeAttributePersistenceImpl.FINDER_CLASS_NAME_LIST,
            "containsPlanTypeAttribute",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_GET_PLANTYPECOLUMNS = new FinderPath(com.ext.portlet.plans.model.impl.PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanTypeColumnModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.service.persistence.PlanTypeColumnPersistenceImpl.FINDER_CLASS_NAME_LIST,
            "getPlanTypeColumns",
            new String[] {
                Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_GET_PLANTYPECOLUMNS_SIZE = new FinderPath(com.ext.portlet.plans.model.impl.PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanTypeColumnModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.service.persistence.PlanTypeColumnPersistenceImpl.FINDER_CLASS_NAME_LIST,
            "getPlanTypeColumnsSize", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_CONTAINS_PLANTYPECOLUMN = new FinderPath(com.ext.portlet.plans.model.impl.PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanTypeColumnModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.service.persistence.PlanTypeColumnPersistenceImpl.FINDER_CLASS_NAME_LIST,
            "containsPlanTypeColumn",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _SQL_GETPLANTYPEATTRIBUTES = "SELECT {PlanTypeAttribute.*} FROM PlanTypeAttribute INNER JOIN PlanType ON (PlanType.planTypeId = PlanTypeAttribute.planTypeId) WHERE (PlanType.planTypeId = ?)";
    private static final String _SQL_GETPLANTYPEATTRIBUTESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM PlanTypeAttribute WHERE planTypeId = ?";
    private static final String _SQL_CONTAINSPLANTYPEATTRIBUTE = "SELECT COUNT(*) AS COUNT_VALUE FROM PlanTypeAttribute WHERE planTypeId = ? AND planTypeAttributeId = ?";
    private static final String _SQL_GETPLANTYPECOLUMNS = "SELECT {PlanTypeColumn.*} FROM PlanTypeColumn INNER JOIN PlanType ON (PlanType.planTypeId = PlanTypeColumn.planTypeId) WHERE (PlanType.planTypeId = ?)";
    private static final String _SQL_GETPLANTYPECOLUMNSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM PlanTypeColumn WHERE planTypeId = ?";
    private static final String _SQL_CONTAINSPLANTYPECOLUMN = "SELECT COUNT(*) AS COUNT_VALUE FROM PlanTypeColumn WHERE planTypeId = ? AND planTypeColumnId = ?";
    private static Log _log = LogFactoryUtil.getLog(PlanTypePersistenceImpl.class);
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
    protected ContainsPlanTypeAttribute containsPlanTypeAttribute;
    protected ContainsPlanTypeColumn containsPlanTypeColumn;

    public void cacheResult(PlanType planType) {
        EntityCacheUtil.putResult(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeImpl.class, planType.getPrimaryKey(), planType);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEFAULT,
            new Object[] { planType.getIsDefault() }, planType);
    }

    public void cacheResult(List<PlanType> planTypes) {
        for (PlanType planType : planTypes) {
            if (EntityCacheUtil.getResult(
                        PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTypeImpl.class, planType.getPrimaryKey(), this) == null) {
                cacheResult(planType);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanTypeImpl.class.getName());
        EntityCacheUtil.clearCache(PlanTypeImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanType create(Long planTypeId) {
        PlanType planType = new PlanTypeImpl();

        planType.setNew(true);
        planType.setPrimaryKey(planTypeId);

        return planType;
    }

    public PlanType remove(Long planTypeId)
        throws NoSuchPlanTypeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanType planType = (PlanType) session.get(PlanTypeImpl.class,
                    planTypeId);

            if (planType == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No PlanType exists with the primary key " +
                        planTypeId);
                }

                throw new NoSuchPlanTypeException(
                    "No PlanType exists with the primary key " + planTypeId);
            }

            return remove(planType);
        } catch (NoSuchPlanTypeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanType remove(PlanType planType) throws SystemException {
        for (ModelListener<PlanType> listener : listeners) {
            listener.onBeforeRemove(planType);
        }

        planType = removeImpl(planType);

        for (ModelListener<PlanType> listener : listeners) {
            listener.onAfterRemove(planType);
        }

        return planType;
    }

    protected PlanType removeImpl(PlanType planType) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planType.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanTypeImpl.class,
                        planType.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planType);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        PlanTypeModelImpl planTypeModelImpl = (PlanTypeModelImpl) planType;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DEFAULT,
            new Object[] { planTypeModelImpl.getOriginalIsDefault() });

        EntityCacheUtil.removeResult(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeImpl.class, planType.getPrimaryKey());

        return planType;
    }

    /**
     * @deprecated Use <code>update(PlanType planType, boolean merge)</code>.
     */
    public PlanType update(PlanType planType) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanType planType) method. Use update(PlanType planType, boolean merge) instead.");
        }

        return update(planType, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planType the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planType is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanType update(PlanType planType, boolean merge)
        throws SystemException {
        boolean isNew = planType.isNew();

        for (ModelListener<PlanType> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planType);
            } else {
                listener.onBeforeUpdate(planType);
            }
        }

        planType = updateImpl(planType, merge);

        for (ModelListener<PlanType> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planType);
            } else {
                listener.onAfterUpdate(planType);
            }
        }

        return planType;
    }

    public PlanType updateImpl(com.ext.portlet.plans.model.PlanType planType,
        boolean merge) throws SystemException {
        boolean isNew = planType.isNew();

        PlanTypeModelImpl planTypeModelImpl = (PlanTypeModelImpl) planType;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planType, merge);

            planType.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeImpl.class, planType.getPrimaryKey(), planType);

        if (!isNew &&
                (!Validator.equals(planType.getIsDefault(),
                    planTypeModelImpl.getOriginalIsDefault()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DEFAULT,
                new Object[] { planTypeModelImpl.getOriginalIsDefault() });
        }

        if (isNew ||
                (!Validator.equals(planType.getIsDefault(),
                    planTypeModelImpl.getOriginalIsDefault()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEFAULT,
                new Object[] { planType.getIsDefault() }, planType);
        }

        return planType;
    }

    public PlanType findByPrimaryKey(Long planTypeId)
        throws NoSuchPlanTypeException, SystemException {
        PlanType planType = fetchByPrimaryKey(planTypeId);

        if (planType == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanType exists with the primary key " +
                    planTypeId);
            }

            throw new NoSuchPlanTypeException(
                "No PlanType exists with the primary key " + planTypeId);
        }

        return planType;
    }

    public PlanType fetchByPrimaryKey(Long planTypeId)
        throws SystemException {
        PlanType planType = (PlanType) EntityCacheUtil.getResult(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
                PlanTypeImpl.class, planTypeId, this);

        if (planType == null) {
            Session session = null;

            try {
                session = openSession();

                planType = (PlanType) session.get(PlanTypeImpl.class, planTypeId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planType != null) {
                    cacheResult(planType);
                }

                closeSession(session);
            }
        }

        return planType;
    }

    public PlanType findBydefault(Boolean isDefault)
        throws NoSuchPlanTypeException, SystemException {
        PlanType planType = fetchBydefault(isDefault);

        if (planType == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanType exists with the key {");

            msg.append("isDefault=" + isDefault);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanTypeException(msg.toString());
        }

        return planType;
    }

    public PlanType fetchBydefault(Boolean isDefault) throws SystemException {
        return fetchBydefault(isDefault, true);
    }

    public PlanType fetchBydefault(Boolean isDefault, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { isDefault };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_DEFAULT,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanType WHERE ");

                if (isDefault == null) {
                    query.append("isDefault IS NULL");
                } else {
                    query.append("isDefault = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (isDefault != null) {
                    qPos.add(isDefault.booleanValue());
                }

                List<PlanType> list = q.list();

                result = list;

                PlanType planType = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEFAULT,
                        finderArgs, list);
                } else {
                    planType = list.get(0);

                    cacheResult(planType);

                    if ((planType.getIsDefault() == null) ||
                            !planType.getIsDefault().equals(isDefault)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEFAULT,
                            finderArgs, planType);
                    }
                }

                return planType;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEFAULT,
                        finderArgs, new ArrayList<PlanType>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (PlanType) result;
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

    public List<PlanType> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanType> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanType> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanType> list = (List<PlanType>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanType ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlanType>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanType>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanType>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeBydefault(Boolean isDefault)
        throws NoSuchPlanTypeException, SystemException {
        PlanType planType = findBydefault(isDefault);

        remove(planType);
    }

    public void removeAll() throws SystemException {
        for (PlanType planType : findAll()) {
            remove(planType);
        }
    }

    public int countBydefault(Boolean isDefault) throws SystemException {
        Object[] finderArgs = new Object[] { isDefault };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_DEFAULT,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append("FROM com.ext.portlet.plans.model.PlanType WHERE ");

                if (isDefault == null) {
                    query.append("isDefault IS NULL");
                } else {
                    query.append("isDefault = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (isDefault != null) {
                    qPos.add(isDefault.booleanValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DEFAULT,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanType");

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

    public List<com.ext.portlet.plans.model.PlanTypeAttribute> getPlanTypeAttributes(
        Long pk) throws SystemException {
        return getPlanTypeAttributes(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    public List<com.ext.portlet.plans.model.PlanTypeAttribute> getPlanTypeAttributes(
        Long pk, int start, int end) throws SystemException {
        return getPlanTypeAttributes(pk, start, end, null);
    }

    public List<com.ext.portlet.plans.model.PlanTypeAttribute> getPlanTypeAttributes(
        Long pk, int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                pk, String.valueOf(start), String.valueOf(end),
                String.valueOf(obc)
            };

        List<com.ext.portlet.plans.model.PlanTypeAttribute> list = (List<com.ext.portlet.plans.model.PlanTypeAttribute>) FinderCacheUtil.getResult(FINDER_PATH_GET_PLANTYPEATTRIBUTES,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder sb = new StringBuilder();

                sb.append(_SQL_GETPLANTYPEATTRIBUTES);

                if (obc != null) {
                    sb.append("ORDER BY ");
                    sb.append(obc.getOrderBy());
                }

                String sql = sb.toString();

                SQLQuery q = session.createSQLQuery(sql);

                q.addEntity("PlanTypeAttribute",
                    com.ext.portlet.plans.model.impl.PlanTypeAttributeImpl.class);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                list = (List<com.ext.portlet.plans.model.PlanTypeAttribute>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<com.ext.portlet.plans.model.PlanTypeAttribute>();
                }

                planTypeAttributePersistence.cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_GET_PLANTYPEATTRIBUTES,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public int getPlanTypeAttributesSize(Long pk) throws SystemException {
        Object[] finderArgs = new Object[] { pk };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_GET_PLANTYPEATTRIBUTES_SIZE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                SQLQuery q = session.createSQLQuery(_SQL_GETPLANTYPEATTRIBUTESSIZE);

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

                FinderCacheUtil.putResult(FINDER_PATH_GET_PLANTYPEATTRIBUTES_SIZE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public boolean containsPlanTypeAttribute(Long pk, Long planTypeAttributePK)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, planTypeAttributePK };

        Boolean value = (Boolean) FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_PLANTYPEATTRIBUTE,
                finderArgs, this);

        if (value == null) {
            try {
                value = Boolean.valueOf(containsPlanTypeAttribute.contains(pk,
                            planTypeAttributePK));
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (value == null) {
                    value = Boolean.FALSE;
                }

                FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_PLANTYPEATTRIBUTE,
                    finderArgs, value);
            }
        }

        return value.booleanValue();
    }

    public boolean containsPlanTypeAttributes(Long pk)
        throws SystemException {
        if (getPlanTypeAttributesSize(pk) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<com.ext.portlet.plans.model.PlanTypeColumn> getPlanTypeColumns(
        Long pk) throws SystemException {
        return getPlanTypeColumns(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    public List<com.ext.portlet.plans.model.PlanTypeColumn> getPlanTypeColumns(
        Long pk, int start, int end) throws SystemException {
        return getPlanTypeColumns(pk, start, end, null);
    }

    public List<com.ext.portlet.plans.model.PlanTypeColumn> getPlanTypeColumns(
        Long pk, int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                pk, String.valueOf(start), String.valueOf(end),
                String.valueOf(obc)
            };

        List<com.ext.portlet.plans.model.PlanTypeColumn> list = (List<com.ext.portlet.plans.model.PlanTypeColumn>) FinderCacheUtil.getResult(FINDER_PATH_GET_PLANTYPECOLUMNS,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder sb = new StringBuilder();

                sb.append(_SQL_GETPLANTYPECOLUMNS);

                if (obc != null) {
                    sb.append("ORDER BY ");
                    sb.append(obc.getOrderBy());
                }
                else {
                    sb.append("ORDER BY ");

                    sb.append("PlanTypeColumn.weight ASC, ");
                    sb.append("PlanTypeColumn.columnName ASC");
                }

                String sql = sb.toString();

                SQLQuery q = session.createSQLQuery(sql);

                q.addEntity("PlanTypeColumn",
                    com.ext.portlet.plans.model.impl.PlanTypeColumnImpl.class);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                list = (List<com.ext.portlet.plans.model.PlanTypeColumn>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<com.ext.portlet.plans.model.PlanTypeColumn>();
                }

                planTypeColumnPersistence.cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_GET_PLANTYPECOLUMNS,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public int getPlanTypeColumnsSize(Long pk) throws SystemException {
        Object[] finderArgs = new Object[] { pk };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_GET_PLANTYPECOLUMNS_SIZE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                SQLQuery q = session.createSQLQuery(_SQL_GETPLANTYPECOLUMNSSIZE);

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

                FinderCacheUtil.putResult(FINDER_PATH_GET_PLANTYPECOLUMNS_SIZE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public boolean containsPlanTypeColumn(Long pk, Long planTypeColumnPK)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, planTypeColumnPK };

        Boolean value = (Boolean) FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_PLANTYPECOLUMN,
                finderArgs, this);

        if (value == null) {
            try {
                value = Boolean.valueOf(containsPlanTypeColumn.contains(pk,
                            planTypeColumnPK));
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (value == null) {
                    value = Boolean.FALSE;
                }

                FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_PLANTYPECOLUMN,
                    finderArgs, value);
            }
        }

        return value.booleanValue();
    }

    public boolean containsPlanTypeColumns(Long pk) throws SystemException {
        if (getPlanTypeColumnsSize(pk) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.portal.util.PropsUtil.get(
                        "value.object.listener.com.ext.portlet.plans.model.PlanType")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanType>> listenersList = new ArrayList<ModelListener<PlanType>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanType>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }

        containsPlanTypeAttribute = new ContainsPlanTypeAttribute(this);

        containsPlanTypeColumn = new ContainsPlanTypeColumn(this);
    }

    protected class ContainsPlanTypeAttribute {
        private MappingSqlQuery _mappingSqlQuery;

        protected ContainsPlanTypeAttribute(
            PlanTypePersistenceImpl persistenceImpl) {
            super();

            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSPLANTYPEATTRIBUTE,
                    new int[] { Types.BIGINT, Types.BIGINT }, RowMapper.COUNT);
        }

        protected boolean contains(Long planTypeId, Long planTypeAttributeId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        planTypeId, planTypeAttributeId
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

    protected class ContainsPlanTypeColumn {
        private MappingSqlQuery _mappingSqlQuery;

        protected ContainsPlanTypeColumn(
            PlanTypePersistenceImpl persistenceImpl) {
            super();

            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSPLANTYPECOLUMN,
                    new int[] { Types.BIGINT, Types.BIGINT }, RowMapper.COUNT);
        }

        protected boolean contains(Long planTypeId, Long planTypeColumnId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        planTypeId, planTypeColumnId
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
