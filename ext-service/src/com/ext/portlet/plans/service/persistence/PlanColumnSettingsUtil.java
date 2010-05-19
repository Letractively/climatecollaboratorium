package com.ext.portlet.plans.service.persistence;

public class PlanColumnSettingsUtil {
    private static PlanColumnSettingsPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.plans.model.PlanColumnSettings planColumnSettings) {
        getPersistence().cacheResult(planColumnSettings);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanColumnSettings> planColumnSettingses) {
        getPersistence().cacheResult(planColumnSettingses);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlanColumnSettings create(
        java.lang.Long planColumnSettingsId) {
        return getPersistence().create(planColumnSettingsId);
    }

    public static com.ext.portlet.plans.model.PlanColumnSettings remove(
        java.lang.Long planColumnSettingsId)
        throws com.ext.portlet.plans.NoSuchPlanColumnSettingsException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(planColumnSettingsId);
    }

    public static com.ext.portlet.plans.model.PlanColumnSettings remove(
        com.ext.portlet.plans.model.PlanColumnSettings planColumnSettings)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(planColumnSettings);
    }

    /**
     * @deprecated Use <code>update(PlanColumnSettings planColumnSettings, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlanColumnSettings update(
        com.ext.portlet.plans.model.PlanColumnSettings planColumnSettings)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planColumnSettings);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planColumnSettings the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planColumnSettings is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.plans.model.PlanColumnSettings update(
        com.ext.portlet.plans.model.PlanColumnSettings planColumnSettings,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(planColumnSettings, merge);
    }

    public static com.ext.portlet.plans.model.PlanColumnSettings updateImpl(
        com.ext.portlet.plans.model.PlanColumnSettings planColumnSettings,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(planColumnSettings, merge);
    }

    public static com.ext.portlet.plans.model.PlanColumnSettings findByPrimaryKey(
        java.lang.Long planColumnSettingsId)
        throws com.ext.portlet.plans.NoSuchPlanColumnSettingsException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(planColumnSettingsId);
    }

    public static com.ext.portlet.plans.model.PlanColumnSettings fetchByPrimaryKey(
        java.lang.Long planColumnSettingsId)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(planColumnSettingsId);
    }

    public static com.ext.portlet.plans.model.PlanColumnSettings findByPlanUserSettingsIdColumnName(
        java.lang.Long planUserSettingsId, java.lang.String columnName)
        throws com.ext.portlet.plans.NoSuchPlanColumnSettingsException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByPlanUserSettingsIdColumnName(planUserSettingsId,
            columnName);
    }

    public static com.ext.portlet.plans.model.PlanColumnSettings fetchByPlanUserSettingsIdColumnName(
        java.lang.Long planUserSettingsId, java.lang.String columnName)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByPlanUserSettingsIdColumnName(planUserSettingsId,
            columnName);
    }

    public static com.ext.portlet.plans.model.PlanColumnSettings fetchByPlanUserSettingsIdColumnName(
        java.lang.Long planUserSettingsId, java.lang.String columnName,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByPlanUserSettingsIdColumnName(planUserSettingsId,
            columnName, retrieveFromCache);
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

    public static java.util.List<com.ext.portlet.plans.model.PlanColumnSettings> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanColumnSettings> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanColumnSettings> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByPlanUserSettingsIdColumnName(
        java.lang.Long planUserSettingsId, java.lang.String columnName)
        throws com.ext.portlet.plans.NoSuchPlanColumnSettingsException,
            com.liferay.portal.SystemException {
        getPersistence()
            .removeByPlanUserSettingsIdColumnName(planUserSettingsId, columnName);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByPlanUserSettingsIdColumnName(
        java.lang.Long planUserSettingsId, java.lang.String columnName)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .countByPlanUserSettingsIdColumnName(planUserSettingsId,
            columnName);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static PlanColumnSettingsPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlanColumnSettingsPersistence persistence) {
        _persistence = persistence;
    }
}
