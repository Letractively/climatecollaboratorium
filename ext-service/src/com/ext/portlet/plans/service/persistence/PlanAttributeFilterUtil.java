package com.ext.portlet.plans.service.persistence;

public class PlanAttributeFilterUtil {
    private static PlanAttributeFilterPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.plans.model.PlanAttributeFilter planAttributeFilter) {
        getPersistence().cacheResult(planAttributeFilter);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> planAttributeFilters) {
        getPersistence().cacheResult(planAttributeFilters);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlanAttributeFilter create(
        java.lang.Long planAttributeFilterId) {
        return getPersistence().create(planAttributeFilterId);
    }

    public static com.ext.portlet.plans.model.PlanAttributeFilter remove(
        java.lang.Long planAttributeFilterId)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(planAttributeFilterId);
    }

    public static com.ext.portlet.plans.model.PlanAttributeFilter remove(
        com.ext.portlet.plans.model.PlanAttributeFilter planAttributeFilter)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(planAttributeFilter);
    }

    /**
     * @deprecated Use <code>update(PlanAttributeFilter planAttributeFilter, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlanAttributeFilter update(
        com.ext.portlet.plans.model.PlanAttributeFilter planAttributeFilter)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planAttributeFilter);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planAttributeFilter the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planAttributeFilter is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.plans.model.PlanAttributeFilter update(
        com.ext.portlet.plans.model.PlanAttributeFilter planAttributeFilter,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(planAttributeFilter, merge);
    }

    public static com.ext.portlet.plans.model.PlanAttributeFilter updateImpl(
        com.ext.portlet.plans.model.PlanAttributeFilter planAttributeFilter,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(planAttributeFilter, merge);
    }

    public static com.ext.portlet.plans.model.PlanAttributeFilter findByPrimaryKey(
        java.lang.Long planAttributeFilterId)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(planAttributeFilterId);
    }

    public static com.ext.portlet.plans.model.PlanAttributeFilter fetchByPrimaryKey(
        java.lang.Long planAttributeFilterId)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(planAttributeFilterId);
    }

    public static com.ext.portlet.plans.model.PlanAttributeFilter findByPlanUserSettingsIdAttributeName(
        java.lang.Long planUserSettingsId, java.lang.String attributeName)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByPlanUserSettingsIdAttributeName(planUserSettingsId,
            attributeName);
    }

    public static com.ext.portlet.plans.model.PlanAttributeFilter fetchByPlanUserSettingsIdAttributeName(
        java.lang.Long planUserSettingsId, java.lang.String attributeName)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByPlanUserSettingsIdAttributeName(planUserSettingsId,
            attributeName);
    }

    public static com.ext.portlet.plans.model.PlanAttributeFilter fetchByPlanUserSettingsIdAttributeName(
        java.lang.Long planUserSettingsId, java.lang.String attributeName,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByPlanUserSettingsIdAttributeName(planUserSettingsId,
            attributeName, retrieveFromCache);
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

    public static java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByPlanUserSettingsIdAttributeName(
        java.lang.Long planUserSettingsId, java.lang.String attributeName)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.liferay.portal.SystemException {
        getPersistence()
            .removeByPlanUserSettingsIdAttributeName(planUserSettingsId,
            attributeName);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByPlanUserSettingsIdAttributeName(
        java.lang.Long planUserSettingsId, java.lang.String attributeName)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .countByPlanUserSettingsIdAttributeName(planUserSettingsId,
            attributeName);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static PlanAttributeFilterPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlanAttributeFilterPersistence persistence) {
        _persistence = persistence;
    }
}
