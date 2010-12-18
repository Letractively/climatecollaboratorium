package com.ext.portlet.plans.service;


/**
 * <a href="PlansUserSettingsLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlansUserSettingsLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlansUserSettingsLocalService
 *
 */
public class PlansUserSettingsLocalServiceUtil {
    private static PlansUserSettingsLocalService _service;

    public static com.ext.portlet.plans.model.PlansUserSettings addPlansUserSettings(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.SystemException {
        return getService().addPlansUserSettings(plansUserSettings);
    }

    public static com.ext.portlet.plans.model.PlansUserSettings createPlansUserSettings(
        java.lang.Long planUserSettingsId) {
        return getService().createPlansUserSettings(planUserSettingsId);
    }

    public static void deletePlansUserSettings(
        java.lang.Long planUserSettingsId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlansUserSettings(planUserSettingsId);
    }

    public static void deletePlansUserSettings(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.SystemException {
        getService().deletePlansUserSettings(plansUserSettings);
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

    public static com.ext.portlet.plans.model.PlansUserSettings getPlansUserSettings(
        java.lang.Long planUserSettingsId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlansUserSettings(planUserSettingsId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlansUserSettings> getPlansUserSettingses(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlansUserSettingses(start, end);
    }

    public static int getPlansUserSettingsesCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlansUserSettingsesCount();
    }

    public static com.ext.portlet.plans.model.PlansUserSettings updatePlansUserSettings(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.SystemException {
        return getService().updatePlansUserSettings(plansUserSettings);
    }

    public static com.ext.portlet.plans.model.PlansUserSettings updatePlansUserSettings(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updatePlansUserSettings(plansUserSettings, merge);
    }

    public static com.ext.portlet.plans.model.PlansUserSettings getByUserIdPlanTypeId(
        java.lang.Long userId, java.lang.Long planTypeId)
        throws com.ext.portlet.plans.NoSuchUserSettingsException,
            com.liferay.portal.SystemException {
        return getService().getByUserIdPlanTypeId(userId, planTypeId);
    }

    public static com.ext.portlet.plans.model.PlansUserSettings getPlanUserSettings(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanUserSettings(sessionMap, requestMap, planType);
    }

    public static void saveUserSettings(java.util.Map sessionMap,
        java.util.Map requestMap,
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().saveUserSettings(sessionMap, requestMap, plansUserSettings);
    }

    public static com.ext.portlet.plans.model.PlansUserSettings getPlanUserSettings(
        java.util.Map sessionMap, java.util.Map requestMap, long planTypeId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService()
                   .getPlanUserSettings(sessionMap, requestMap, planTypeId);
    }

    public static PlansUserSettingsLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "PlansUserSettingsLocalService is not set");
        }

        return _service;
    }

    public void setService(PlansUserSettingsLocalService service) {
        _service = service;
    }
}
