package com.ext.portlet.plans.service.persistence;

public class PlanTypeUtil {
    private static PlanTypePersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.plans.model.PlanType planType) {
        getPersistence().cacheResult(planType);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanType> planTypes) {
        getPersistence().cacheResult(planTypes);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlanType create(
        java.lang.Long planTypeId) {
        return getPersistence().create(planTypeId);
    }

    public static com.ext.portlet.plans.model.PlanType remove(
        java.lang.Long planTypeId)
        throws com.ext.portlet.plans.NoSuchPlanTypeException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(planTypeId);
    }

    public static com.ext.portlet.plans.model.PlanType remove(
        com.ext.portlet.plans.model.PlanType planType)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(planType);
    }

    /**
     * @deprecated Use <code>update(PlanType planType, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlanType update(
        com.ext.portlet.plans.model.PlanType planType)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planType);
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
    public static com.ext.portlet.plans.model.PlanType update(
        com.ext.portlet.plans.model.PlanType planType, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planType, merge);
    }

    public static com.ext.portlet.plans.model.PlanType updateImpl(
        com.ext.portlet.plans.model.PlanType planType, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(planType, merge);
    }

    public static com.ext.portlet.plans.model.PlanType findByPrimaryKey(
        java.lang.Long planTypeId)
        throws com.ext.portlet.plans.NoSuchPlanTypeException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(planTypeId);
    }

    public static com.ext.portlet.plans.model.PlanType fetchByPrimaryKey(
        java.lang.Long planTypeId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(planTypeId);
    }

    public static com.ext.portlet.plans.model.PlanType findBydefault(
        java.lang.Boolean isDefault)
        throws com.ext.portlet.plans.NoSuchPlanTypeException,
            com.liferay.portal.SystemException {
        return getPersistence().findBydefault(isDefault);
    }

    public static com.ext.portlet.plans.model.PlanType fetchBydefault(
        java.lang.Boolean isDefault) throws com.liferay.portal.SystemException {
        return getPersistence().fetchBydefault(isDefault);
    }

    public static com.ext.portlet.plans.model.PlanType fetchBydefault(
        java.lang.Boolean isDefault, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchBydefault(isDefault, retrieveFromCache);
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

    public static java.util.List<com.ext.portlet.plans.model.PlanType> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanType> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanType> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeBydefault(java.lang.Boolean isDefault)
        throws com.ext.portlet.plans.NoSuchPlanTypeException,
            com.liferay.portal.SystemException {
        getPersistence().removeBydefault(isDefault);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countBydefault(java.lang.Boolean isDefault)
        throws com.liferay.portal.SystemException {
        return getPersistence().countBydefault(isDefault);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> getPlanTypeAttributes(
        java.lang.Long pk) throws com.liferay.portal.SystemException {
        return getPersistence().getPlanTypeAttributes(pk);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> getPlanTypeAttributes(
        java.lang.Long pk, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().getPlanTypeAttributes(pk, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> getPlanTypeAttributes(
        java.lang.Long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().getPlanTypeAttributes(pk, start, end, obc);
    }

    public static int getPlanTypeAttributesSize(java.lang.Long pk)
        throws com.liferay.portal.SystemException {
        return getPersistence().getPlanTypeAttributesSize(pk);
    }

    public static boolean containsPlanTypeAttribute(java.lang.Long pk,
        java.lang.Long planTypeAttributePK)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .containsPlanTypeAttribute(pk, planTypeAttributePK);
    }

    public static boolean containsPlanTypeAttributes(java.lang.Long pk)
        throws com.liferay.portal.SystemException {
        return getPersistence().containsPlanTypeAttributes(pk);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> getPlanTypeColumns(
        java.lang.Long pk) throws com.liferay.portal.SystemException {
        return getPersistence().getPlanTypeColumns(pk);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> getPlanTypeColumns(
        java.lang.Long pk, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().getPlanTypeColumns(pk, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> getPlanTypeColumns(
        java.lang.Long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().getPlanTypeColumns(pk, start, end, obc);
    }

    public static int getPlanTypeColumnsSize(java.lang.Long pk)
        throws com.liferay.portal.SystemException {
        return getPersistence().getPlanTypeColumnsSize(pk);
    }

    public static boolean containsPlanTypeColumn(java.lang.Long pk,
        java.lang.Long planTypeColumnPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().containsPlanTypeColumn(pk, planTypeColumnPK);
    }

    public static boolean containsPlanTypeColumns(java.lang.Long pk)
        throws com.liferay.portal.SystemException {
        return getPersistence().containsPlanTypeColumns(pk);
    }

    public static PlanTypePersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlanTypePersistence persistence) {
        _persistence = persistence;
    }
}
