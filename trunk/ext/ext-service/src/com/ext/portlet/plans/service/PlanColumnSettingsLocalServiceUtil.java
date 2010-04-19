package com.ext.portlet.plans.service;


/**
 * <a href="PlanColumnSettingsLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanColumnSettingsLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanColumnSettingsLocalService
 *
 */
public class PlanColumnSettingsLocalServiceUtil {
    private static PlanColumnSettingsLocalService _service;

    public static com.ext.portlet.plans.model.PlanColumnSettings addPlanColumnSettings(
        com.ext.portlet.plans.model.PlanColumnSettings planColumnSettings)
        throws com.liferay.portal.SystemException {
        return getService().addPlanColumnSettings(planColumnSettings);
    }

    public static com.ext.portlet.plans.model.PlanColumnSettings createPlanColumnSettings(
        java.lang.Long planColumnSettingsId) {
        return getService().createPlanColumnSettings(planColumnSettingsId);
    }

    public static void deletePlanColumnSettings(
        java.lang.Long planColumnSettingsId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanColumnSettings(planColumnSettingsId);
    }

    public static void deletePlanColumnSettings(
        com.ext.portlet.plans.model.PlanColumnSettings planColumnSettings)
        throws com.liferay.portal.SystemException {
        getService().deletePlanColumnSettings(planColumnSettings);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    public static com.ext.portlet.plans.model.PlanColumnSettings getPlanColumnSettings(
        java.lang.Long planColumnSettingsId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanColumnSettings(planColumnSettingsId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanColumnSettings> getPlanColumnSettingses(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanColumnSettingses(start, end);
    }

    public static int getPlanColumnSettingsesCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanColumnSettingsesCount();
    }

    public static com.ext.portlet.plans.model.PlanColumnSettings updatePlanColumnSettings(
        com.ext.portlet.plans.model.PlanColumnSettings planColumnSettings)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanColumnSettings(planColumnSettings);
    }

    public static com.ext.portlet.plans.model.PlanColumnSettings updatePlanColumnSettings(
        com.ext.portlet.plans.model.PlanColumnSettings planColumnSettings,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updatePlanColumnSettings(planColumnSettings, merge);
    }

    public static com.ext.portlet.plans.model.PlanColumnSettings findByPlanUserSettingsIdColumnName(
        java.lang.Long planUserSettingsId, java.lang.String columnName)
        throws com.ext.portlet.plans.NoSuchPlanColumnSettingsException,
            com.liferay.portal.SystemException {
        return getService()
                   .findByPlanUserSettingsIdColumnName(planUserSettingsId,
            columnName);
    }

    public static PlanColumnSettingsLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "PlanColumnSettingsLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanColumnSettingsLocalService service) {
        _service = service;
    }
}
