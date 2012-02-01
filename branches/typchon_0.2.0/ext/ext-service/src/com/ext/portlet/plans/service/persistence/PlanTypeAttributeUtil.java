package com.ext.portlet.plans.service.persistence;

public class PlanTypeAttributeUtil {
    private static PlanTypeAttributePersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.plans.model.PlanTypeAttribute planTypeAttribute) {
        getPersistence().cacheResult(planTypeAttribute);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> planTypeAttributes) {
        getPersistence().cacheResult(planTypeAttributes);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlanTypeAttribute create(
        java.lang.Long planTypeAttributeId) {
        return getPersistence().create(planTypeAttributeId);
    }

    public static com.ext.portlet.plans.model.PlanTypeAttribute remove(
        java.lang.Long planTypeAttributeId)
        throws com.ext.portlet.plans.NoSuchPlanTypeAttributeException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(planTypeAttributeId);
    }

    public static com.ext.portlet.plans.model.PlanTypeAttribute remove(
        com.ext.portlet.plans.model.PlanTypeAttribute planTypeAttribute)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(planTypeAttribute);
    }

    /**
     * @deprecated Use <code>update(PlanTypeAttribute planTypeAttribute, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlanTypeAttribute update(
        com.ext.portlet.plans.model.PlanTypeAttribute planTypeAttribute)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planTypeAttribute);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planTypeAttribute the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planTypeAttribute is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.plans.model.PlanTypeAttribute update(
        com.ext.portlet.plans.model.PlanTypeAttribute planTypeAttribute,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(planTypeAttribute, merge);
    }

    public static com.ext.portlet.plans.model.PlanTypeAttribute updateImpl(
        com.ext.portlet.plans.model.PlanTypeAttribute planTypeAttribute,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(planTypeAttribute, merge);
    }

    public static com.ext.portlet.plans.model.PlanTypeAttribute findByPrimaryKey(
        java.lang.Long planTypeAttributeId)
        throws com.ext.portlet.plans.NoSuchPlanTypeAttributeException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(planTypeAttributeId);
    }

    public static com.ext.portlet.plans.model.PlanTypeAttribute fetchByPrimaryKey(
        java.lang.Long planTypeAttributeId)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(planTypeAttributeId);
    }

    public static com.ext.portlet.plans.model.PlanTypeAttribute findByPlanTypeIdAttributeName(
        java.lang.Long planTypeId, java.lang.String attributeName)
        throws com.ext.portlet.plans.NoSuchPlanTypeAttributeException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByPlanTypeIdAttributeName(planTypeId, attributeName);
    }

    public static com.ext.portlet.plans.model.PlanTypeAttribute fetchByPlanTypeIdAttributeName(
        java.lang.Long planTypeId, java.lang.String attributeName)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByPlanTypeIdAttributeName(planTypeId, attributeName);
    }

    public static com.ext.portlet.plans.model.PlanTypeAttribute fetchByPlanTypeIdAttributeName(
        java.lang.Long planTypeId, java.lang.String attributeName,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByPlanTypeIdAttributeName(planTypeId, attributeName,
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

    public static java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByPlanTypeIdAttributeName(
        java.lang.Long planTypeId, java.lang.String attributeName)
        throws com.ext.portlet.plans.NoSuchPlanTypeAttributeException,
            com.liferay.portal.SystemException {
        getPersistence()
            .removeByPlanTypeIdAttributeName(planTypeId, attributeName);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByPlanTypeIdAttributeName(
        java.lang.Long planTypeId, java.lang.String attributeName)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .countByPlanTypeIdAttributeName(planTypeId, attributeName);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static PlanTypeAttributePersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlanTypeAttributePersistence persistence) {
        _persistence = persistence;
    }
}
