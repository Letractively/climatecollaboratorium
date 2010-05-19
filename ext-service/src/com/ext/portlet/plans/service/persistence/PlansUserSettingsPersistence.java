package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlansUserSettingsPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlansUserSettings> plansUserSettingses);

    public void clearCache();

    public com.ext.portlet.plans.model.PlansUserSettings create(
        java.lang.Long planUserSettingsId);

    public com.ext.portlet.plans.model.PlansUserSettings remove(
        java.lang.Long planUserSettingsId)
        throws com.ext.portlet.plans.NoSuchUserSettingsException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlansUserSettings remove(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlansUserSettings plansUserSettings, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlansUserSettings update(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.SystemException;

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
    public com.ext.portlet.plans.model.PlansUserSettings update(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlansUserSettings updateImpl(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlansUserSettings findByPrimaryKey(
        java.lang.Long planUserSettingsId)
        throws com.ext.portlet.plans.NoSuchUserSettingsException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlansUserSettings fetchByPrimaryKey(
        java.lang.Long planUserSettingsId)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlansUserSettings findByuserIdPlanTypeId(
        java.lang.Long userId, java.lang.Long planTypeId)
        throws com.ext.portlet.plans.NoSuchUserSettingsException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlansUserSettings fetchByuserIdPlanTypeId(
        java.lang.Long userId, java.lang.Long planTypeId)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlansUserSettings fetchByuserIdPlanTypeId(
        java.lang.Long userId, java.lang.Long planTypeId,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlansUserSettings> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlansUserSettings> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlansUserSettings> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByuserIdPlanTypeId(java.lang.Long userId,
        java.lang.Long planTypeId)
        throws com.ext.portlet.plans.NoSuchUserSettingsException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByuserIdPlanTypeId(java.lang.Long userId,
        java.lang.Long planTypeId) throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> getPlanAttributeFilters(
        java.lang.Long pk) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> getPlanAttributeFilters(
        java.lang.Long pk, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> getPlanAttributeFilters(
        java.lang.Long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public int getPlanAttributeFiltersSize(java.lang.Long pk)
        throws com.liferay.portal.SystemException;

    public boolean containsPlanAttributeFilter(java.lang.Long pk,
        java.lang.Long planAttributeFilterPK)
        throws com.liferay.portal.SystemException;

    public boolean containsPlanAttributeFilters(java.lang.Long pk)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanColumnSettings> getPlanColumnSettingses(
        java.lang.Long pk) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanColumnSettings> getPlanColumnSettingses(
        java.lang.Long pk, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanColumnSettings> getPlanColumnSettingses(
        java.lang.Long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public int getPlanColumnSettingsesSize(java.lang.Long pk)
        throws com.liferay.portal.SystemException;

    public boolean containsPlanColumnSettings(java.lang.Long pk,
        java.lang.Long planColumnSettingsPK)
        throws com.liferay.portal.SystemException;

    public boolean containsPlanColumnSettingses(java.lang.Long pk)
        throws com.liferay.portal.SystemException;
}
