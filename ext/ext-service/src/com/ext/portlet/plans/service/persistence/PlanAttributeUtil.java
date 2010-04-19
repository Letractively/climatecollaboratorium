package com.ext.portlet.plans.service.persistence;

public class PlanAttributeUtil {
    private static PlanAttributePersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.plans.model.PlanAttribute planAttribute) {
        getPersistence().cacheResult(planAttribute);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanAttribute> planAttributes) {
        getPersistence().cacheResult(planAttributes);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlanAttribute create(
        java.lang.Long attributeId) {
        return getPersistence().create(attributeId);
    }

    public static com.ext.portlet.plans.model.PlanAttribute remove(
        java.lang.Long attributeId)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(attributeId);
    }

    public static com.ext.portlet.plans.model.PlanAttribute remove(
        com.ext.portlet.plans.model.PlanAttribute planAttribute)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(planAttribute);
    }

    /**
     * @deprecated Use <code>update(PlanAttribute planAttribute, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlanAttribute update(
        com.ext.portlet.plans.model.PlanAttribute planAttribute)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planAttribute);
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
    public static com.ext.portlet.plans.model.PlanAttribute update(
        com.ext.portlet.plans.model.PlanAttribute planAttribute, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planAttribute, merge);
    }

    public static com.ext.portlet.plans.model.PlanAttribute updateImpl(
        com.ext.portlet.plans.model.PlanAttribute planAttribute, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(planAttribute, merge);
    }

    public static com.ext.portlet.plans.model.PlanAttribute findByPrimaryKey(
        java.lang.Long attributeId)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(attributeId);
    }

    public static com.ext.portlet.plans.model.PlanAttribute fetchByPrimaryKey(
        java.lang.Long attributeId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(attributeId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> findByplanAttributes(
        java.lang.Long planId) throws com.liferay.portal.SystemException {
        return getPersistence().findByplanAttributes(planId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> findByplanAttributes(
        java.lang.Long planId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByplanAttributes(planId, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> findByplanAttributes(
        java.lang.Long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByplanAttributes(planId, start, end, obc);
    }

    public static com.ext.portlet.plans.model.PlanAttribute findByplanAttributes_First(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.SystemException {
        return getPersistence().findByplanAttributes_First(planId, obc);
    }

    public static com.ext.portlet.plans.model.PlanAttribute findByplanAttributes_Last(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.SystemException {
        return getPersistence().findByplanAttributes_Last(planId, obc);
    }

    public static com.ext.portlet.plans.model.PlanAttribute[] findByplanAttributes_PrevAndNext(
        java.lang.Long attributeId, java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByplanAttributes_PrevAndNext(attributeId, planId, obc);
    }

    public static com.ext.portlet.plans.model.PlanAttribute findByattributeForPlan(
        java.lang.Long planId, java.lang.String attributeName)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.SystemException {
        return getPersistence().findByattributeForPlan(planId, attributeName);
    }

    public static com.ext.portlet.plans.model.PlanAttribute fetchByattributeForPlan(
        java.lang.Long planId, java.lang.String attributeName)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByattributeForPlan(planId, attributeName);
    }

    public static com.ext.portlet.plans.model.PlanAttribute fetchByattributeForPlan(
        java.lang.Long planId, java.lang.String attributeName,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByattributeForPlan(planId, attributeName,
            retrieveFromCache);
    }

    public static java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    public static java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByplanAttributes(java.lang.Long planId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByplanAttributes(planId);
    }

    public static void removeByattributeForPlan(java.lang.Long planId,
        java.lang.String attributeName)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.SystemException {
        getPersistence().removeByattributeForPlan(planId, attributeName);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByplanAttributes(java.lang.Long planId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByplanAttributes(planId);
    }

    public static int countByattributeForPlan(java.lang.Long planId,
        java.lang.String attributeName)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByattributeForPlan(planId, attributeName);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static PlanAttributePersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlanAttributePersistence persistence) {
        _persistence = persistence;
    }
}
