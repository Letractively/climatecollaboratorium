package com.ext.portlet.plans.service.persistence;

public class PlansUserSettingsUtil {
    private static PlansUserSettingsPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings) {
        getPersistence().cacheResult(plansUserSettings);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlansUserSettings> plansUserSettingses) {
        getPersistence().cacheResult(plansUserSettingses);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlansUserSettings create(
        java.lang.Long planUserSettingsId) {
        return getPersistence().create(planUserSettingsId);
    }

    public static com.ext.portlet.plans.model.PlansUserSettings remove(
        java.lang.Long planUserSettingsId)
        throws com.ext.portlet.plans.NoSuchUserSettingsException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(planUserSettingsId);
    }

    public static com.ext.portlet.plans.model.PlansUserSettings remove(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(plansUserSettings);
    }

    /**
     * @deprecated Use <code>update(PlansUserSettings plansUserSettings, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlansUserSettings update(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(plansUserSettings);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                plansUserSettings the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when plansUserSettings is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.plans.model.PlansUserSettings update(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(plansUserSettings, merge);
    }

    public static com.ext.portlet.plans.model.PlansUserSettings updateImpl(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(plansUserSettings, merge);
    }

    public static com.ext.portlet.plans.model.PlansUserSettings findByPrimaryKey(
        java.lang.Long planUserSettingsId)
        throws com.ext.portlet.plans.NoSuchUserSettingsException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(planUserSettingsId);
    }

    public static com.ext.portlet.plans.model.PlansUserSettings fetchByPrimaryKey(
        java.lang.Long planUserSettingsId)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(planUserSettingsId);
    }

    public static com.ext.portlet.plans.model.PlansUserSettings findByuserIdPlanTypeId(
        java.lang.Long userId, java.lang.Long planTypeId)
        throws com.ext.portlet.plans.NoSuchUserSettingsException,
            com.liferay.portal.SystemException {
        return getPersistence().findByuserIdPlanTypeId(userId, planTypeId);
    }

    public static com.ext.portlet.plans.model.PlansUserSettings fetchByuserIdPlanTypeId(
        java.lang.Long userId, java.lang.Long planTypeId)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByuserIdPlanTypeId(userId, planTypeId);
    }

    public static com.ext.portlet.plans.model.PlansUserSettings fetchByuserIdPlanTypeId(
        java.lang.Long userId, java.lang.Long planTypeId,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByuserIdPlanTypeId(userId, planTypeId,
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

    public static java.util.List<com.ext.portlet.plans.model.PlansUserSettings> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlansUserSettings> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlansUserSettings> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByuserIdPlanTypeId(java.lang.Long userId,
        java.lang.Long planTypeId)
        throws com.ext.portlet.plans.NoSuchUserSettingsException,
            com.liferay.portal.SystemException {
        getPersistence().removeByuserIdPlanTypeId(userId, planTypeId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByuserIdPlanTypeId(java.lang.Long userId,
        java.lang.Long planTypeId) throws com.liferay.portal.SystemException {
        return getPersistence().countByuserIdPlanTypeId(userId, planTypeId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> getPlanAttributeFilters(
        java.lang.Long pk) throws com.liferay.portal.SystemException {
        return getPersistence().getPlanAttributeFilters(pk);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> getPlanAttributeFilters(
        java.lang.Long pk, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().getPlanAttributeFilters(pk, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> getPlanAttributeFilters(
        java.lang.Long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().getPlanAttributeFilters(pk, start, end, obc);
    }

    public static int getPlanAttributeFiltersSize(java.lang.Long pk)
        throws com.liferay.portal.SystemException {
        return getPersistence().getPlanAttributeFiltersSize(pk);
    }

    public static boolean containsPlanAttributeFilter(java.lang.Long pk,
        java.lang.Long planAttributeFilterPK)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .containsPlanAttributeFilter(pk, planAttributeFilterPK);
    }

    public static boolean containsPlanAttributeFilters(java.lang.Long pk)
        throws com.liferay.portal.SystemException {
        return getPersistence().containsPlanAttributeFilters(pk);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanColumnSettings> getPlanColumnSettingses(
        java.lang.Long pk) throws com.liferay.portal.SystemException {
        return getPersistence().getPlanColumnSettingses(pk);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanColumnSettings> getPlanColumnSettingses(
        java.lang.Long pk, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().getPlanColumnSettingses(pk, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanColumnSettings> getPlanColumnSettingses(
        java.lang.Long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().getPlanColumnSettingses(pk, start, end, obc);
    }

    public static int getPlanColumnSettingsesSize(java.lang.Long pk)
        throws com.liferay.portal.SystemException {
        return getPersistence().getPlanColumnSettingsesSize(pk);
    }

    public static boolean containsPlanColumnSettings(java.lang.Long pk,
        java.lang.Long planColumnSettingsPK)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .containsPlanColumnSettings(pk, planColumnSettingsPK);
    }

    public static boolean containsPlanColumnSettingses(java.lang.Long pk)
        throws com.liferay.portal.SystemException {
        return getPersistence().containsPlanColumnSettingses(pk);
    }

    public static PlansUserSettingsPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlansUserSettingsPersistence persistence) {
        _persistence = persistence;
    }
}
