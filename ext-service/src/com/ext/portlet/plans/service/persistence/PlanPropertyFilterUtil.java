package com.ext.portlet.plans.service.persistence;

public class PlanPropertyFilterUtil {
    private static PlanPropertyFilterPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.plans.model.PlanPropertyFilter planPropertyFilter) {
        getPersistence().cacheResult(planPropertyFilter);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanPropertyFilter> planPropertyFilters) {
        getPersistence().cacheResult(planPropertyFilters);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlanPropertyFilter create(
        java.lang.Long planPropertyFilterId) {
        return getPersistence().create(planPropertyFilterId);
    }

    public static com.ext.portlet.plans.model.PlanPropertyFilter remove(
        java.lang.Long planPropertyFilterId)
        throws com.ext.portlet.plans.NoSuchPlanPropertyFilterException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(planPropertyFilterId);
    }

    public static com.ext.portlet.plans.model.PlanPropertyFilter remove(
        com.ext.portlet.plans.model.PlanPropertyFilter planPropertyFilter)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(planPropertyFilter);
    }

    /**
     * @deprecated Use <code>update(PlanPropertyFilter planPropertyFilter, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlanPropertyFilter update(
        com.ext.portlet.plans.model.PlanPropertyFilter planPropertyFilter)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planPropertyFilter);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planPropertyFilter the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planPropertyFilter is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.plans.model.PlanPropertyFilter update(
        com.ext.portlet.plans.model.PlanPropertyFilter planPropertyFilter,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(planPropertyFilter, merge);
    }

    public static com.ext.portlet.plans.model.PlanPropertyFilter updateImpl(
        com.ext.portlet.plans.model.PlanPropertyFilter planPropertyFilter,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(planPropertyFilter, merge);
    }

    public static com.ext.portlet.plans.model.PlanPropertyFilter findByPrimaryKey(
        java.lang.Long planPropertyFilterId)
        throws com.ext.portlet.plans.NoSuchPlanPropertyFilterException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(planPropertyFilterId);
    }

    public static com.ext.portlet.plans.model.PlanPropertyFilter fetchByPrimaryKey(
        java.lang.Long planPropertyFilterId)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(planPropertyFilterId);
    }

    public static com.ext.portlet.plans.model.PlanPropertyFilter findByPlanUserSettingsIdPropertyName(
        java.lang.Long planUserSettingsId, java.lang.String propertyName)
        throws com.ext.portlet.plans.NoSuchPlanPropertyFilterException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByPlanUserSettingsIdPropertyName(planUserSettingsId,
            propertyName);
    }

    public static com.ext.portlet.plans.model.PlanPropertyFilter fetchByPlanUserSettingsIdPropertyName(
        java.lang.Long planUserSettingsId, java.lang.String propertyName)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByPlanUserSettingsIdPropertyName(planUserSettingsId,
            propertyName);
    }

    public static com.ext.portlet.plans.model.PlanPropertyFilter fetchByPlanUserSettingsIdPropertyName(
        java.lang.Long planUserSettingsId, java.lang.String propertyName,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByPlanUserSettingsIdPropertyName(planUserSettingsId,
            propertyName, retrieveFromCache);
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

    public static java.util.List<com.ext.portlet.plans.model.PlanPropertyFilter> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanPropertyFilter> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanPropertyFilter> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByPlanUserSettingsIdPropertyName(
        java.lang.Long planUserSettingsId, java.lang.String propertyName)
        throws com.ext.portlet.plans.NoSuchPlanPropertyFilterException,
            com.liferay.portal.SystemException {
        getPersistence()
            .removeByPlanUserSettingsIdPropertyName(planUserSettingsId,
            propertyName);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByPlanUserSettingsIdPropertyName(
        java.lang.Long planUserSettingsId, java.lang.String propertyName)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .countByPlanUserSettingsIdPropertyName(planUserSettingsId,
            propertyName);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static PlanPropertyFilterPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlanPropertyFilterPersistence persistence) {
        _persistence = persistence;
    }
}
