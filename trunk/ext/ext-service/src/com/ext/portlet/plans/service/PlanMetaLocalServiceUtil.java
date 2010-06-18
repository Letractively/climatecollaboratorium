package com.ext.portlet.plans.service;


/**
 * <a href="PlanMetaLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanMetaLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanMetaLocalService
 *
 */
public class PlanMetaLocalServiceUtil {
    private static PlanMetaLocalService _service;

    public static com.ext.portlet.plans.model.PlanMeta addPlanMeta(
        com.ext.portlet.plans.model.PlanMeta planMeta)
        throws com.liferay.portal.SystemException {
        return getService().addPlanMeta(planMeta);
    }

    public static com.ext.portlet.plans.model.PlanMeta createPlanMeta(
        java.lang.Long id) {
        return getService().createPlanMeta(id);
    }

    public static void deletePlanMeta(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanMeta(id);
    }

    public static void deletePlanMeta(
        com.ext.portlet.plans.model.PlanMeta planMeta)
        throws com.liferay.portal.SystemException {
        getService().deletePlanMeta(planMeta);
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

    public static com.ext.portlet.plans.model.PlanMeta getPlanMeta(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanMeta(id);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanMeta> getPlanMetas(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanMetas(start, end);
    }

    public static int getPlanMetasCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanMetasCount();
    }

    public static com.ext.portlet.plans.model.PlanMeta updatePlanMeta(
        com.ext.portlet.plans.model.PlanMeta planMeta)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanMeta(planMeta);
    }

    public static com.ext.portlet.plans.model.PlanMeta updatePlanMeta(
        com.ext.portlet.plans.model.PlanMeta planMeta, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanMeta(planMeta, merge);
    }

    public static com.ext.portlet.plans.model.PlanMeta createPlanMeta(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException {
        return getService().createPlanMeta(plan);
    }

    public static com.ext.portlet.plans.model.PlanMeta getCurrentForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException {
        return getService().getCurrentForPlan(plan);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanMeta> getAllForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException {
        return getService().getAllForPlan(plan);
    }

    public static com.ext.portlet.plans.model.PlanMeta createNewVersionForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException {
        return getService().createNewVersionForPlan(plan);
    }

    public static com.ext.portlet.plans.model.PlanMeta createNewVersionForPlan(
        com.ext.portlet.plans.model.PlanItem plan, boolean store)
        throws com.liferay.portal.SystemException {
        return getService().createNewVersionForPlan(plan, store);
    }

    public static PlanMetaLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("PlanMetaLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanMetaLocalService service) {
        _service = service;
    }
}
