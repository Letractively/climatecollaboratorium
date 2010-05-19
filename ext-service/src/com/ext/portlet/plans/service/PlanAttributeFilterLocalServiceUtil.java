package com.ext.portlet.plans.service;


/**
 * <a href="PlanAttributeFilterLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanAttributeFilterLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanAttributeFilterLocalService
 *
 */
public class PlanAttributeFilterLocalServiceUtil {
    private static PlanAttributeFilterLocalService _service;

    public static com.ext.portlet.plans.model.PlanAttributeFilter addPlanAttributeFilter(
        com.ext.portlet.plans.model.PlanAttributeFilter planAttributeFilter)
        throws com.liferay.portal.SystemException {
        return getService().addPlanAttributeFilter(planAttributeFilter);
    }

    public static com.ext.portlet.plans.model.PlanAttributeFilter createPlanAttributeFilter(
        java.lang.Long planAttributeFilterId) {
        return getService().createPlanAttributeFilter(planAttributeFilterId);
    }

    public static void deletePlanAttributeFilter(
        java.lang.Long planAttributeFilterId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanAttributeFilter(planAttributeFilterId);
    }

    public static void deletePlanAttributeFilter(
        com.ext.portlet.plans.model.PlanAttributeFilter planAttributeFilter)
        throws com.liferay.portal.SystemException {
        getService().deletePlanAttributeFilter(planAttributeFilter);
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

    public static com.ext.portlet.plans.model.PlanAttributeFilter getPlanAttributeFilter(
        java.lang.Long planAttributeFilterId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanAttributeFilter(planAttributeFilterId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> getPlanAttributeFilters(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanAttributeFilters(start, end);
    }

    public static int getPlanAttributeFiltersCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanAttributeFiltersCount();
    }

    public static com.ext.portlet.plans.model.PlanAttributeFilter updatePlanAttributeFilter(
        com.ext.portlet.plans.model.PlanAttributeFilter planAttributeFilter)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanAttributeFilter(planAttributeFilter);
    }

    public static com.ext.portlet.plans.model.PlanAttributeFilter updatePlanAttributeFilter(
        com.ext.portlet.plans.model.PlanAttributeFilter planAttributeFilter,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updatePlanAttributeFilter(planAttributeFilter, merge);
    }

    public static com.ext.portlet.plans.model.PlanAttributeFilter getByPlansUserSettingsIdAttributeName(
        java.lang.Long planUserSettingsId, java.lang.String attributeName)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.liferay.portal.SystemException {
        return getService()
                   .getByPlansUserSettingsIdAttributeName(planUserSettingsId,
            attributeName);
    }

    public static PlanAttributeFilterLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "PlanAttributeFilterLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanAttributeFilterLocalService service) {
        _service = service;
    }
}
