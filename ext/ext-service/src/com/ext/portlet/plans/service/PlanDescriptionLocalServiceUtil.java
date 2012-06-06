package com.ext.portlet.plans.service;


/**
 * <a href="PlanDescriptionLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanDescriptionLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanDescriptionLocalService
 *
 */
public class PlanDescriptionLocalServiceUtil {
    private static PlanDescriptionLocalService _service;

    public static com.ext.portlet.plans.model.PlanDescription addPlanDescription(
        com.ext.portlet.plans.model.PlanDescription planDescription)
        throws com.liferay.portal.SystemException {
        return getService().addPlanDescription(planDescription);
    }

    public static com.ext.portlet.plans.model.PlanDescription createPlanDescription(
        java.lang.Long id) {
        return getService().createPlanDescription(id);
    }

    public static void deletePlanDescription(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanDescription(id);
    }

    public static void deletePlanDescription(
        com.ext.portlet.plans.model.PlanDescription planDescription)
        throws com.liferay.portal.SystemException {
        getService().deletePlanDescription(planDescription);
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

    public static com.ext.portlet.plans.model.PlanDescription getPlanDescription(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanDescription(id);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanDescription> getPlanDescriptions(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanDescriptions(start, end);
    }

    public static int getPlanDescriptionsCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanDescriptionsCount();
    }

    public static com.ext.portlet.plans.model.PlanDescription updatePlanDescription(
        com.ext.portlet.plans.model.PlanDescription planDescription)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanDescription(planDescription);
    }

    public static com.ext.portlet.plans.model.PlanDescription updatePlanDescription(
        com.ext.portlet.plans.model.PlanDescription planDescription,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updatePlanDescription(planDescription, merge);
    }

    public static com.ext.portlet.plans.model.PlanDescription createPlanDescription(
        com.ext.portlet.plans.model.PlanItem plan, java.lang.String name)
        throws com.liferay.portal.SystemException {
        return getService().createPlanDescription(plan, name);
    }

    public static com.ext.portlet.plans.model.PlanDescription createPlanDescription(
        com.ext.portlet.plans.model.PlanItem plan, java.lang.String name,
        boolean store) throws com.liferay.portal.SystemException {
        return getService().createPlanDescription(plan, name, store);
    }

    public static com.ext.portlet.plans.model.PlanDescription getCurrentForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException {
        return getService().getCurrentForPlan(plan);
    }

    public static com.ext.portlet.plans.model.PlanDescription getForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException {
        return getService().getForPlan(plan);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanDescription> getAllForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException {
        return getService().getAllForPlan(plan);
    }

    public static com.ext.portlet.plans.model.PlanDescription createNewVersionForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException {
        return getService().createNewVersionForPlan(plan);
    }

    public static com.ext.portlet.plans.model.PlanDescription createNewVersionForPlan(
        com.ext.portlet.plans.model.PlanItem plan, boolean store)
        throws com.liferay.portal.SystemException {
        return getService().createNewVersionForPlan(plan, store);
    }

    public static com.ext.portlet.plans.model.PlanDescription createNewVersionForPlanFrom(
        com.ext.portlet.plans.model.PlanItem plan,
        com.ext.portlet.plans.model.PlanDescription from, boolean store)
        throws com.liferay.portal.SystemException {
        return getService().createNewVersionForPlanFrom(plan, from, store);
    }

    public static PlanDescriptionLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("PlanDescriptionLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanDescriptionLocalService service) {
        _service = service;
    }
}
