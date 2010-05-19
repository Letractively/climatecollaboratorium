package com.ext.portlet.plans.service;


/**
 * <a href="PlanLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanLocalService
 *
 */
public class PlanLocalServiceUtil {
    private static PlanLocalService _service;

    public static com.ext.portlet.plans.model.Plan addPlan(
        com.ext.portlet.plans.model.Plan plan)
        throws com.liferay.portal.SystemException {
        return getService().addPlan(plan);
    }

    public static com.ext.portlet.plans.model.Plan createPlan(
        java.lang.Long planId) {
        return getService().createPlan(planId);
    }

    public static void deletePlan(java.lang.Long planId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlan(planId);
    }

    public static void deletePlan(com.ext.portlet.plans.model.Plan plan)
        throws com.liferay.portal.SystemException {
        getService().deletePlan(plan);
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

    public static com.ext.portlet.plans.model.Plan getPlan(
        java.lang.Long planId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlan(planId);
    }

    public static java.util.List<com.ext.portlet.plans.model.Plan> getPlans(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlans(start, end);
    }

    public static int getPlansCount() throws com.liferay.portal.SystemException {
        return getService().getPlansCount();
    }

    public static com.ext.portlet.plans.model.Plan updatePlan(
        com.ext.portlet.plans.model.Plan plan)
        throws com.liferay.portal.SystemException {
        return getService().updatePlan(plan);
    }

    public static com.ext.portlet.plans.model.Plan updatePlan(
        com.ext.portlet.plans.model.Plan plan, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updatePlan(plan, merge);
    }

    public static java.util.List<com.ext.portlet.plans.model.Plan> getPlans(
        long planTypeId, int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.liferay.portal.SystemException {
        return getService()
                   .getPlans(planTypeId, start, end, sortColumn, sortDirection);
    }

    public static int countPlans(long planTypeId) {
        return getService().countPlans(planTypeId);
    }

    public static java.util.List<com.ext.portlet.plans.model.Plan> getFilteredPlans(
        com.ext.portlet.plans.model.PlansUserSettings filter, int start,
        int end, java.lang.String sortColumn, java.lang.String sortDirection)
        throws java.lang.Exception {
        return getService()
                   .getFilteredPlans(filter, start, end, sortColumn,
            sortDirection);
    }

    public static int countFilteredPlans(
        com.ext.portlet.plans.model.PlansUserSettings filter)
        throws java.lang.Exception {
        return getService().countFilteredPlans(filter);
    }

    public static int getUserVotePosition(long userId,
        java.lang.String sortColumn, java.lang.String sortOrder)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.ext.portlet.plans.NoSuchPlanTypeException,
            com.liferay.portal.SystemException {
        return getService().getUserVotePosition(userId, sortColumn, sortOrder);
    }

    public static int getFilteredUserVotePosition(
        com.ext.portlet.plans.model.PlansUserSettings filter, long userId,
        java.lang.String sortColumn, java.lang.String sortOrder)
        throws java.lang.Exception {
        return getService()
                   .getFilteredUserVotePosition(filter, userId, sortColumn,
            sortOrder);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanPosition> getPlansPositions(
        java.util.List<com.ext.portlet.plans.model.Plan> plans) {
        return getService().getPlansPositions(plans);
    }

    public static PlanLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("PlanLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanLocalService service) {
        _service = service;
    }
}
