package com.ext.portlet.plans.service;


/**
 * <a href="PlanPropertyFilterLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanPropertyFilterLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanPropertyFilterLocalService
 *
 */
public class PlanPropertyFilterLocalServiceUtil {
    private static PlanPropertyFilterLocalService _service;

    public static com.ext.portlet.plans.model.PlanPropertyFilter addPlanPropertyFilter(
        com.ext.portlet.plans.model.PlanPropertyFilter planPropertyFilter)
        throws com.liferay.portal.SystemException {
        return getService().addPlanPropertyFilter(planPropertyFilter);
    }

    public static com.ext.portlet.plans.model.PlanPropertyFilter createPlanPropertyFilter(
        java.lang.Long planPropertyFilterId) {
        return getService().createPlanPropertyFilter(planPropertyFilterId);
    }

    public static void deletePlanPropertyFilter(
        java.lang.Long planPropertyFilterId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanPropertyFilter(planPropertyFilterId);
    }

    public static void deletePlanPropertyFilter(
        com.ext.portlet.plans.model.PlanPropertyFilter planPropertyFilter)
        throws com.liferay.portal.SystemException {
        getService().deletePlanPropertyFilter(planPropertyFilter);
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

    public static com.ext.portlet.plans.model.PlanPropertyFilter getPlanPropertyFilter(
        java.lang.Long planPropertyFilterId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanPropertyFilter(planPropertyFilterId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanPropertyFilter> getPlanPropertyFilters(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanPropertyFilters(start, end);
    }

    public static int getPlanPropertyFiltersCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanPropertyFiltersCount();
    }

    public static com.ext.portlet.plans.model.PlanPropertyFilter updatePlanPropertyFilter(
        com.ext.portlet.plans.model.PlanPropertyFilter planPropertyFilter)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanPropertyFilter(planPropertyFilter);
    }

    public static com.ext.portlet.plans.model.PlanPropertyFilter updatePlanPropertyFilter(
        com.ext.portlet.plans.model.PlanPropertyFilter planPropertyFilter,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updatePlanPropertyFilter(planPropertyFilter, merge);
    }

    public static com.ext.portlet.plans.model.PlanPropertyFilter getByPlanPlanUserSettingsIdPropertyName(
        java.lang.Long planUserSettingsId, java.lang.String propertyName)
        throws com.ext.portlet.plans.NoSuchPlanPropertyFilterException,
            com.liferay.portal.SystemException {
        return getService()
                   .getByPlanPlanUserSettingsIdPropertyName(planUserSettingsId,
            propertyName);
    }

    public static PlanPropertyFilterLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "PlanPropertyFilterLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanPropertyFilterLocalService service) {
        _service = service;
    }
}
