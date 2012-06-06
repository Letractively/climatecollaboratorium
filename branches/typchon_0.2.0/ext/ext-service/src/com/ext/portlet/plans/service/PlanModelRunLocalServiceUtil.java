package com.ext.portlet.plans.service;


/**
 * <a href="PlanModelRunLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanModelRunLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanModelRunLocalService
 *
 */
public class PlanModelRunLocalServiceUtil {
    private static PlanModelRunLocalService _service;

    public static com.ext.portlet.plans.model.PlanModelRun addPlanModelRun(
        com.ext.portlet.plans.model.PlanModelRun planModelRun)
        throws com.liferay.portal.SystemException {
        return getService().addPlanModelRun(planModelRun);
    }

    public static com.ext.portlet.plans.model.PlanModelRun createPlanModelRun(
        java.lang.Long id) {
        return getService().createPlanModelRun(id);
    }

    public static void deletePlanModelRun(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanModelRun(id);
    }

    public static void deletePlanModelRun(
        com.ext.portlet.plans.model.PlanModelRun planModelRun)
        throws com.liferay.portal.SystemException {
        getService().deletePlanModelRun(planModelRun);
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

    public static com.ext.portlet.plans.model.PlanModelRun getPlanModelRun(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanModelRun(id);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanModelRun> getPlanModelRuns(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanModelRuns(start, end);
    }

    public static int getPlanModelRunsCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanModelRunsCount();
    }

    public static com.ext.portlet.plans.model.PlanModelRun updatePlanModelRun(
        com.ext.portlet.plans.model.PlanModelRun planModelRun)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanModelRun(planModelRun);
    }

    public static com.ext.portlet.plans.model.PlanModelRun updatePlanModelRun(
        com.ext.portlet.plans.model.PlanModelRun planModelRun, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanModelRun(planModelRun, merge);
    }

    public static com.ext.portlet.plans.model.PlanModelRun createPlanModelRun(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException {
        return getService().createPlanModelRun(plan);
    }

    public static com.ext.portlet.plans.model.PlanModelRun getCurrentForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException {
        return getService().getCurrentForPlan(plan);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanModelRun> getAllForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException {
        return getService().getAllForPlan(plan);
    }

    public static com.ext.portlet.plans.model.PlanModelRun getForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException {
        return getService().getForPlan(plan);
    }

    public static com.ext.portlet.plans.model.PlanModelRun createNewVersionForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException {
        return getService().createNewVersionForPlan(plan);
    }

    public static com.ext.portlet.plans.model.PlanModelRun createNewVersionForPlan(
        com.ext.portlet.plans.model.PlanItem plan, boolean store)
        throws com.liferay.portal.SystemException {
        return getService().createNewVersionForPlan(plan, store);
    }

    public static com.ext.portlet.plans.model.PlanModelRun createNewVersionForPlanFrom(
        com.ext.portlet.plans.model.PlanItem plan,
        com.ext.portlet.plans.model.PlanModelRun from, boolean store)
        throws com.liferay.portal.SystemException {
        return getService().createNewVersionForPlanFrom(plan, from, store);
    }

    public static PlanModelRunLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("PlanModelRunLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanModelRunLocalService service) {
        _service = service;
    }
}
