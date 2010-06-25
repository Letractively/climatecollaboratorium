package com.ext.portlet.plans.service;


/**
 * <a href="PlanPositionsLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanPositionsLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanPositionsLocalService
 *
 */
public class PlanPositionsLocalServiceUtil {
    private static PlanPositionsLocalService _service;

    public static com.ext.portlet.plans.model.PlanPositions addPlanPositions(
        com.ext.portlet.plans.model.PlanPositions planPositions)
        throws com.liferay.portal.SystemException {
        return getService().addPlanPositions(planPositions);
    }

    public static com.ext.portlet.plans.model.PlanPositions createPlanPositions(
        java.lang.Long id) {
        return getService().createPlanPositions(id);
    }

    public static void deletePlanPositions(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanPositions(id);
    }

    public static void deletePlanPositions(
        com.ext.portlet.plans.model.PlanPositions planPositions)
        throws com.liferay.portal.SystemException {
        getService().deletePlanPositions(planPositions);
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

    public static com.ext.portlet.plans.model.PlanPositions getPlanPositions(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanPositions(id);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanPositions> getPlanPositionses(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanPositionses(start, end);
    }

    public static int getPlanPositionsesCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanPositionsesCount();
    }

    public static com.ext.portlet.plans.model.PlanPositions updatePlanPositions(
        com.ext.portlet.plans.model.PlanPositions planPositions)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanPositions(planPositions);
    }

    public static com.ext.portlet.plans.model.PlanPositions updatePlanPositions(
        com.ext.portlet.plans.model.PlanPositions planPositions, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanPositions(planPositions, merge);
    }

    public static com.ext.portlet.plans.model.PlanPositions getCurrentForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.SystemException {
        return getService().getCurrentForPlan(plan);
    }

    public static com.ext.portlet.plans.model.PlanPositions createPlanPositions(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException {
        return getService().createPlanPositions(plan);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanPositions> getAllForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException {
        return getService().getAllForPlan(plan);
    }

    public static com.ext.portlet.plans.model.PlanPositions createNewVersionForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException {
        return getService().createNewVersionForPlan(plan);
    }

    public static com.ext.portlet.plans.model.PlanPositions createNewVersionForPlan(
        com.ext.portlet.plans.model.PlanItem plan, boolean store)
        throws com.liferay.portal.SystemException {
        return getService().createNewVersionForPlan(plan, store);
    }

    public static PlanPositionsLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("PlanPositionsLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanPositionsLocalService service) {
        _service = service;
    }
}
