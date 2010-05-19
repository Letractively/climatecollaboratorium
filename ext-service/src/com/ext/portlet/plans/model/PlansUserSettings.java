package com.ext.portlet.plans.model;


/**
 * <a href="PlansUserSettings.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlansUserSettings</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.plans.model.impl.PlansUserSettingsImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlansUserSettingsModel
 * @see com.ext.portlet.plans.model.impl.PlansUserSettingsImpl
 * @see com.ext.portlet.plans.model.impl.PlansUserSettingsModelImpl
 *
 */
public interface PlansUserSettings extends PlansUserSettingsModel {
    public com.ext.portlet.plans.model.PlanColumnSettings getColumnSettings(
        java.lang.String name)
        throws com.ext.portlet.plans.NoSuchPlanColumnSettingsException,
            com.liferay.portal.SystemException;

    public void addColumnSettings(
        com.ext.portlet.plans.model.PlanColumnSettings settings);

    public java.util.List<com.ext.portlet.plans.model.PlanColumnSettings> getUpdatedColumnSettings();

    public java.util.List<Long> getPositionsIds();

    public void setPositionsIds(java.util.List<Long> positionsIds);

    public com.ext.portlet.plans.model.PlanAttributeFilter getAttributeFilter(
        java.lang.String name)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.liferay.portal.SystemException;

    public void addPlanAttributeFilter(
        com.ext.portlet.plans.model.PlanAttributeFilter filter);

    public java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> getUpdatedPlanAttributeFilters();

    public com.ext.portlet.plans.model.PlanPropertyFilter getPropertyFilter(
        java.lang.String name)
        throws com.ext.portlet.plans.NoSuchPlanPropertyFilterException,
            com.liferay.portal.SystemException;

    public void addPlanPropertyFilter(
        com.ext.portlet.plans.model.PlanPropertyFilter filter);

    public java.util.List<com.ext.portlet.plans.model.PlanPropertyFilter> getUpdatedPlanPropertyFilters();

    public boolean isFiltersDefined();
}
