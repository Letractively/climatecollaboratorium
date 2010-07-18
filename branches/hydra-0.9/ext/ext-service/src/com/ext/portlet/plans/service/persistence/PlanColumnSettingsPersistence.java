package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanColumnSettingsPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.plans.model.PlanColumnSettings planColumnSettings);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanColumnSettings> planColumnSettingses);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanColumnSettings create(
        java.lang.Long planColumnSettingsId);

    public com.ext.portlet.plans.model.PlanColumnSettings remove(
        java.lang.Long planColumnSettingsId)
        throws com.ext.portlet.plans.NoSuchPlanColumnSettingsException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanColumnSettings remove(
        com.ext.portlet.plans.model.PlanColumnSettings planColumnSettings)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlanColumnSettings planColumnSettings, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlanColumnSettings update(
        com.ext.portlet.plans.model.PlanColumnSettings planColumnSettings)
        throws com.liferay.portal.SystemException;

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
    public com.ext.portlet.plans.model.PlanColumnSettings update(
        com.ext.portlet.plans.model.PlanColumnSettings planColumnSettings,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanColumnSettings updateImpl(
        com.ext.portlet.plans.model.PlanColumnSettings planColumnSettings,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanColumnSettings findByPrimaryKey(
        java.lang.Long planColumnSettingsId)
        throws com.ext.portlet.plans.NoSuchPlanColumnSettingsException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanColumnSettings fetchByPrimaryKey(
        java.lang.Long planColumnSettingsId)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanColumnSettings findByPlanUserSettingsIdColumnName(
        java.lang.Long planUserSettingsId, java.lang.String columnName)
        throws com.ext.portlet.plans.NoSuchPlanColumnSettingsException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanColumnSettings fetchByPlanUserSettingsIdColumnName(
        java.lang.Long planUserSettingsId, java.lang.String columnName)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanColumnSettings fetchByPlanUserSettingsIdColumnName(
        java.lang.Long planUserSettingsId, java.lang.String columnName,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanColumnSettings> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanColumnSettings> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanColumnSettings> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByPlanUserSettingsIdColumnName(
        java.lang.Long planUserSettingsId, java.lang.String columnName)
        throws com.ext.portlet.plans.NoSuchPlanColumnSettingsException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByPlanUserSettingsIdColumnName(
        java.lang.Long planUserSettingsId, java.lang.String columnName)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
