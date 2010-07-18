package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanAttributeException;
import com.ext.portlet.plans.model.PlanAttribute;
import com.ext.portlet.plans.model.impl.PlanAttributeImpl;
import com.ext.portlet.plans.model.impl.PlanAttributeModelImpl;

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


public class PlanAttributePersistenceImpl extends BasePersistenceImpl
    implements PlanAttributePersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = PlanAttributeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_BY_PLANATTRIBUTES = new FinderPath(PlanAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByplanAttributes",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_PLANATTRIBUTES = new FinderPath(PlanAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByplanAttributes",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANATTRIBUTES = new FinderPath(PlanAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByplanAttributes",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_ATTRIBUTEFORPLAN = new FinderPath(PlanAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_ENTITY, "fetchByattributeForPlan",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_COUNT_BY_ATTRIBUTEFORPLAN = new FinderPath(PlanAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByattributeForPlan",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_ATTRIBUTEBYNAMEVALUE = new FinderPath(PlanAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByattributeByNameValue",
            new String[] { String.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_BY_OBC_ATTRIBUTEBYNAMEVALUE = new FinderPath(PlanAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findByattributeByNameValue",
            new String[] {
                String.class.getName(), String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_COUNT_BY_ATTRIBUTEBYNAMEVALUE = new FinderPath(PlanAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countByattributeByNameValue",
            new String[] { String.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PlanAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeModelImpl.FINDER_CACHE_ENABLED,
            FINDER_CLASS_NAME_LIST, "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(PlanAttributePersistenceImpl.class);
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

    public void cacheResult(PlanAttribute planAttribute) {
        EntityCacheUtil.putResult(PlanAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeImpl.class, planAttribute.getPrimaryKey(),
            planAttribute);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ATTRIBUTEFORPLAN,
            new Object[] {
                planAttribute.getPlanId(),
                
            planAttribute.getAttributeName()
            }, planAttribute);
    }

    public void cacheResult(List<PlanAttribute> planAttributes) {
        for (PlanAttribute planAttribute : planAttributes) {
            if (EntityCacheUtil.getResult(
                        PlanAttributeModelImpl.ENTITY_CACHE_ENABLED,
                        PlanAttributeImpl.class, planAttribute.getPrimaryKey(),
                        this) == null) {
                cacheResult(planAttribute);
            }
        }
    }

    public void clearCache() {
        CacheRegistry.clear(PlanAttributeImpl.class.getName());
        EntityCacheUtil.clearCache(PlanAttributeImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public PlanAttribute create(Long attributeId) {
        PlanAttribute planAttribute = new PlanAttributeImpl();

        planAttribute.setNew(true);
        planAttribute.setPrimaryKey(attributeId);

        return planAttribute;
    }

    public PlanAttribute remove(Long attributeId)
        throws NoSuchPlanAttributeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanAttribute planAttribute = (PlanAttribute) session.get(PlanAttributeImpl.class,
                    attributeId);

            if (planAttribute == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No PlanAttribute exists with the primary key " +
                        attributeId);
                }

                throw new NoSuchPlanAttributeException(
                    "No PlanAttribute exists with the primary key " +
                    attributeId);
            }

            return remove(planAttribute);
        } catch (NoSuchPlanAttributeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanAttribute remove(PlanAttribute planAttribute)
        throws SystemException {
        for (ModelListener<PlanAttribute> listener : listeners) {
            listener.onBeforeRemove(planAttribute);
        }

        planAttribute = removeImpl(planAttribute);

        for (ModelListener<PlanAttribute> listener : listeners) {
            listener.onAfterRemove(planAttribute);
        }

        return planAttribute;
    }

    protected PlanAttribute removeImpl(PlanAttribute planAttribute)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (planAttribute.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(PlanAttributeImpl.class,
                        planAttribute.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(planAttribute);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        PlanAttributeModelImpl planAttributeModelImpl = (PlanAttributeModelImpl) planAttribute;

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ATTRIBUTEFORPLAN,
            new Object[] {
                planAttributeModelImpl.getOriginalPlanId(),
                
            planAttributeModelImpl.getOriginalAttributeName()
            });

        EntityCacheUtil.removeResult(PlanAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeImpl.class, planAttribute.getPrimaryKey());

        return planAttribute;
    }

    /**
     * @deprecated Use <code>update(PlanAttribute planAttribute, boolean merge)</code>.
     */
    public PlanAttribute update(PlanAttribute planAttribute)
        throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(PlanAttribute planAttribute) method. Use update(PlanAttribute planAttribute, boolean merge) instead.");
        }

        return update(planAttribute, false);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planAttribute the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planAttribute is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public PlanAttribute update(PlanAttribute planAttribute, boolean merge)
        throws SystemException {
        boolean isNew = planAttribute.isNew();

        for (ModelListener<PlanAttribute> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(planAttribute);
            } else {
                listener.onBeforeUpdate(planAttribute);
            }
        }

        planAttribute = updateImpl(planAttribute, merge);

        for (ModelListener<PlanAttribute> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(planAttribute);
            } else {
                listener.onAfterUpdate(planAttribute);
            }
        }

        return planAttribute;
    }

    public PlanAttribute updateImpl(
        com.ext.portlet.plans.model.PlanAttribute planAttribute, boolean merge)
        throws SystemException {
        boolean isNew = planAttribute.isNew();

        PlanAttributeModelImpl planAttributeModelImpl = (PlanAttributeModelImpl) planAttribute;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planAttribute, merge);

            planAttribute.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(PlanAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeImpl.class, planAttribute.getPrimaryKey(),
            planAttribute);

        if (!isNew &&
                (!Validator.equals(planAttribute.getPlanId(),
                    planAttributeModelImpl.getOriginalPlanId()) ||
                !Validator.equals(planAttribute.getAttributeName(),
                    planAttributeModelImpl.getOriginalAttributeName()))) {
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ATTRIBUTEFORPLAN,
                new Object[] {
                    planAttributeModelImpl.getOriginalPlanId(),
                    
                planAttributeModelImpl.getOriginalAttributeName()
                });
        }

        if (isNew ||
                (!Validator.equals(planAttribute.getPlanId(),
                    planAttributeModelImpl.getOriginalPlanId()) ||
                !Validator.equals(planAttribute.getAttributeName(),
                    planAttributeModelImpl.getOriginalAttributeName()))) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ATTRIBUTEFORPLAN,
                new Object[] {
                    planAttribute.getPlanId(),
                    
                planAttribute.getAttributeName()
                }, planAttribute);
        }

        return planAttribute;
    }

    public PlanAttribute findByPrimaryKey(Long attributeId)
        throws NoSuchPlanAttributeException, SystemException {
        PlanAttribute planAttribute = fetchByPrimaryKey(attributeId);

        if (planAttribute == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No PlanAttribute exists with the primary key " +
                    attributeId);
            }

            throw new NoSuchPlanAttributeException(
                "No PlanAttribute exists with the primary key " + attributeId);
        }

        return planAttribute;
    }

    public PlanAttribute fetchByPrimaryKey(Long attributeId)
        throws SystemException {
        PlanAttribute planAttribute = (PlanAttribute) EntityCacheUtil.getResult(PlanAttributeModelImpl.ENTITY_CACHE_ENABLED,
                PlanAttributeImpl.class, attributeId, this);

        if (planAttribute == null) {
            Session session = null;

            try {
                session = openSession();

                planAttribute = (PlanAttribute) session.get(PlanAttributeImpl.class,
                        attributeId);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (planAttribute != null) {
                    cacheResult(planAttribute);
                }

                closeSession(session);
            }
        }

        return planAttribute;
    }

    public List<PlanAttribute> findByplanAttributes(Long planId)
        throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        List<PlanAttribute> list = (List<PlanAttribute>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_PLANATTRIBUTES,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanAttribute WHERE ");

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

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanAttribute>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_PLANATTRIBUTES,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<PlanAttribute> findByplanAttributes(Long planId, int start,
        int end) throws SystemException {
        return findByplanAttributes(planId, start, end, null);
    }

    public List<PlanAttribute> findByplanAttributes(Long planId, int start,
        int end, OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                planId,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanAttribute> list = (List<PlanAttribute>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_PLANATTRIBUTES,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanAttribute WHERE ");

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

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                list = (List<PlanAttribute>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanAttribute>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_PLANATTRIBUTES,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public PlanAttribute findByplanAttributes_First(Long planId,
        OrderByComparator obc)
        throws NoSuchPlanAttributeException, SystemException {
        List<PlanAttribute> list = findByplanAttributes(planId, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanAttribute exists with the key {");

            msg.append("planId=" + planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanAttributeException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanAttribute findByplanAttributes_Last(Long planId,
        OrderByComparator obc)
        throws NoSuchPlanAttributeException, SystemException {
        int count = countByplanAttributes(planId);

        List<PlanAttribute> list = findByplanAttributes(planId, count - 1,
                count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanAttribute exists with the key {");

            msg.append("planId=" + planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanAttributeException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanAttribute[] findByplanAttributes_PrevAndNext(Long attributeId,
        Long planId, OrderByComparator obc)
        throws NoSuchPlanAttributeException, SystemException {
        PlanAttribute planAttribute = findByPrimaryKey(attributeId);

        int count = countByplanAttributes(planId);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.plans.model.PlanAttribute WHERE ");

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

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (planId != null) {
                qPos.add(planId.longValue());
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    planAttribute);

            PlanAttribute[] array = new PlanAttributeImpl[3];

            array[0] = (PlanAttribute) objArray[0];
            array[1] = (PlanAttribute) objArray[1];
            array[2] = (PlanAttribute) objArray[2];

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public PlanAttribute findByattributeForPlan(Long planId,
        String attributeName)
        throws NoSuchPlanAttributeException, SystemException {
        PlanAttribute planAttribute = fetchByattributeForPlan(planId,
                attributeName);

        if (planAttribute == null) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanAttribute exists with the key {");

            msg.append("planId=" + planId);

            msg.append(", ");
            msg.append("attributeName=" + attributeName);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanAttributeException(msg.toString());
        }

        return planAttribute;
    }

    public PlanAttribute fetchByattributeForPlan(Long planId,
        String attributeName) throws SystemException {
        return fetchByattributeForPlan(planId, attributeName, true);
    }

    public PlanAttribute fetchByattributeForPlan(Long planId,
        String attributeName, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { planId, attributeName };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_ATTRIBUTEFORPLAN,
                    finderArgs, this);
        }

        if (result == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanAttribute WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" AND ");

                if (attributeName == null) {
                    query.append("attributeName IS NULL");
                } else {
                    query.append("attributeName = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                if (attributeName != null) {
                    qPos.add(attributeName);
                }

                List<PlanAttribute> list = q.list();

                result = list;

                PlanAttribute planAttribute = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ATTRIBUTEFORPLAN,
                        finderArgs, list);
                } else {
                    planAttribute = list.get(0);

                    cacheResult(planAttribute);

                    if ((planAttribute.getPlanId() == null) ||
                            !planAttribute.getPlanId().equals(planId) ||
                            (planAttribute.getAttributeName() == null) ||
                            !planAttribute.getAttributeName()
                                              .equals(attributeName)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ATTRIBUTEFORPLAN,
                            finderArgs, planAttribute);
                    }
                }

                return planAttribute;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ATTRIBUTEFORPLAN,
                        finderArgs, new ArrayList<PlanAttribute>());
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List) {
                return null;
            } else {
                return (PlanAttribute) result;
            }
        }
    }

    public List<PlanAttribute> findByattributeByNameValue(
        String attributeName, String attributeValue) throws SystemException {
        Object[] finderArgs = new Object[] { attributeName, attributeValue };

        List<PlanAttribute> list = (List<PlanAttribute>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ATTRIBUTEBYNAMEVALUE,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanAttribute WHERE ");

                if (attributeName == null) {
                    query.append("attributeName IS NULL");
                } else {
                    query.append("attributeName = ?");
                }

                query.append(" AND ");

                if (attributeValue == null) {
                    query.append("attributeValue IS NULL");
                } else {
                    query.append("attributeValue = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (attributeName != null) {
                    qPos.add(attributeName);
                }

                if (attributeValue != null) {
                    qPos.add(attributeValue);
                }

                list = q.list();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanAttribute>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ATTRIBUTEBYNAMEVALUE,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public List<PlanAttribute> findByattributeByNameValue(
        String attributeName, String attributeValue, int start, int end)
        throws SystemException {
        return findByattributeByNameValue(attributeName, attributeValue, start,
            end, null);
    }

    public List<PlanAttribute> findByattributeByNameValue(
        String attributeName, String attributeValue, int start, int end,
        OrderByComparator obc) throws SystemException {
        Object[] finderArgs = new Object[] {
                attributeName,
                
                attributeValue,
                
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanAttribute> list = (List<PlanAttribute>) FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_ATTRIBUTEBYNAMEVALUE,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append(
                    "FROM com.ext.portlet.plans.model.PlanAttribute WHERE ");

                if (attributeName == null) {
                    query.append("attributeName IS NULL");
                } else {
                    query.append("attributeName = ?");
                }

                query.append(" AND ");

                if (attributeValue == null) {
                    query.append("attributeValue IS NULL");
                } else {
                    query.append("attributeValue = ?");
                }

                query.append(" ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (attributeName != null) {
                    qPos.add(attributeName);
                }

                if (attributeValue != null) {
                    qPos.add(attributeValue);
                }

                list = (List<PlanAttribute>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanAttribute>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_ATTRIBUTEBYNAMEVALUE,
                    finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public PlanAttribute findByattributeByNameValue_First(
        String attributeName, String attributeValue, OrderByComparator obc)
        throws NoSuchPlanAttributeException, SystemException {
        List<PlanAttribute> list = findByattributeByNameValue(attributeName,
                attributeValue, 0, 1, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanAttribute exists with the key {");

            msg.append("attributeName=" + attributeName);

            msg.append(", ");
            msg.append("attributeValue=" + attributeValue);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanAttributeException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanAttribute findByattributeByNameValue_Last(String attributeName,
        String attributeValue, OrderByComparator obc)
        throws NoSuchPlanAttributeException, SystemException {
        int count = countByattributeByNameValue(attributeName, attributeValue);

        List<PlanAttribute> list = findByattributeByNameValue(attributeName,
                attributeValue, count - 1, count, obc);

        if (list.isEmpty()) {
            StringBuilder msg = new StringBuilder();

            msg.append("No PlanAttribute exists with the key {");

            msg.append("attributeName=" + attributeName);

            msg.append(", ");
            msg.append("attributeValue=" + attributeValue);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanAttributeException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    public PlanAttribute[] findByattributeByNameValue_PrevAndNext(
        Long attributeId, String attributeName, String attributeValue,
        OrderByComparator obc)
        throws NoSuchPlanAttributeException, SystemException {
        PlanAttribute planAttribute = findByPrimaryKey(attributeId);

        int count = countByattributeByNameValue(attributeName, attributeValue);

        Session session = null;

        try {
            session = openSession();

            StringBuilder query = new StringBuilder();

            query.append(
                "FROM com.ext.portlet.plans.model.PlanAttribute WHERE ");

            if (attributeName == null) {
                query.append("attributeName IS NULL");
            } else {
                query.append("attributeName = ?");
            }

            query.append(" AND ");

            if (attributeValue == null) {
                query.append("attributeValue IS NULL");
            } else {
                query.append("attributeValue = ?");
            }

            query.append(" ");

            if (obc != null) {
                query.append("ORDER BY ");
                query.append(obc.getOrderBy());
            }

            Query q = session.createQuery(query.toString());

            QueryPos qPos = QueryPos.getInstance(q);

            if (attributeName != null) {
                qPos.add(attributeName);
            }

            if (attributeValue != null) {
                qPos.add(attributeValue);
            }

            Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
                    planAttribute);

            PlanAttribute[] array = new PlanAttributeImpl[3];

            array[0] = (PlanAttribute) objArray[0];
            array[1] = (PlanAttribute) objArray[1];
            array[2] = (PlanAttribute) objArray[2];

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

    public List<PlanAttribute> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<PlanAttribute> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    public List<PlanAttribute> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<PlanAttribute> list = (List<PlanAttribute>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM com.ext.portlet.plans.model.PlanAttribute ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<PlanAttribute>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanAttribute>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<PlanAttribute>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeByplanAttributes(Long planId) throws SystemException {
        for (PlanAttribute planAttribute : findByplanAttributes(planId)) {
            remove(planAttribute);
        }
    }

    public void removeByattributeForPlan(Long planId, String attributeName)
        throws NoSuchPlanAttributeException, SystemException {
        PlanAttribute planAttribute = findByattributeForPlan(planId,
                attributeName);

        remove(planAttribute);
    }

    public void removeByattributeByNameValue(String attributeName,
        String attributeValue) throws SystemException {
        for (PlanAttribute planAttribute : findByattributeByNameValue(
                attributeName, attributeValue)) {
            remove(planAttribute);
        }
    }

    public void removeAll() throws SystemException {
        for (PlanAttribute planAttribute : findAll()) {
            remove(planAttribute);
        }
    }

    public int countByplanAttributes(Long planId) throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PLANATTRIBUTES,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.plans.model.PlanAttribute WHERE ");

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

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANATTRIBUTES,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByattributeForPlan(Long planId, String attributeName)
        throws SystemException {
        Object[] finderArgs = new Object[] { planId, attributeName };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ATTRIBUTEFORPLAN,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.plans.model.PlanAttribute WHERE ");

                if (planId == null) {
                    query.append("planId IS NULL");
                } else {
                    query.append("planId = ?");
                }

                query.append(" AND ");

                if (attributeName == null) {
                    query.append("attributeName IS NULL");
                } else {
                    query.append("attributeName = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (planId != null) {
                    qPos.add(planId.longValue());
                }

                if (attributeName != null) {
                    qPos.add(attributeName);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ATTRIBUTEFORPLAN,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public int countByattributeByNameValue(String attributeName,
        String attributeValue) throws SystemException {
        Object[] finderArgs = new Object[] { attributeName, attributeValue };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ATTRIBUTEBYNAMEVALUE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("SELECT COUNT(*) ");
                query.append(
                    "FROM com.ext.portlet.plans.model.PlanAttribute WHERE ");

                if (attributeName == null) {
                    query.append("attributeName IS NULL");
                } else {
                    query.append("attributeName = ?");
                }

                query.append(" AND ");

                if (attributeValue == null) {
                    query.append("attributeValue IS NULL");
                } else {
                    query.append("attributeValue = ?");
                }

                query.append(" ");

                Query q = session.createQuery(query.toString());

                QueryPos qPos = QueryPos.getInstance(q);

                if (attributeName != null) {
                    qPos.add(attributeName);
                }

                if (attributeValue != null) {
                    qPos.add(attributeValue);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ATTRIBUTEBYNAMEVALUE,
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
                        "SELECT COUNT(*) FROM com.ext.portlet.plans.model.PlanAttribute");

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
                        "value.object.listener.com.ext.portlet.plans.model.PlanAttribute")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanAttribute>> listenersList = new ArrayList<ModelListener<PlanAttribute>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanAttribute>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
