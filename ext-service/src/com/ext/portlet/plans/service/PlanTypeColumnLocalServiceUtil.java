package com.ext.portlet.plans.service;


/**
 * <a href="PlanTypeColumnLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanTypeColumnLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanTypeColumnLocalService
 *
 */
public class PlanTypeColumnLocalServiceUtil {
    private static PlanTypeColumnLocalService _service;

    public static com.ext.portlet.plans.model.PlanTypeColumn addPlanTypeColumn(
        com.ext.portlet.plans.model.PlanTypeColumn planTypeColumn)
        throws com.liferay.portal.SystemException {
        return getService().addPlanTypeColumn(planTypeColumn);
    }

    public static com.ext.portlet.plans.model.PlanTypeColumn createPlanTypeColumn(
        java.lang.Long planTypeColumnId) {
        return getService().createPlanTypeColumn(planTypeColumnId);
    }

    public static void deletePlanTypeColumn(java.lang.Long planTypeColumnId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanTypeColumn(planTypeColumnId);
    }

    public static void deletePlanTypeColumn(
        com.ext.portlet.plans.model.PlanTypeColumn planTypeColumn)
        throws com.liferay.portal.SystemException {
        getService().deletePlanTypeColumn(planTypeColumn);
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

    public static com.ext.portlet.plans.model.PlanTypeColumn getPlanTypeColumn(
        java.lang.Long planTypeColumnId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanTypeColumn(planTypeColumnId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> getPlanTypeColumns(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanTypeColumns(start, end);
    }

    public static int getPlanTypeColumnsCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanTypeColumnsCount();
    }

    public static com.ext.portlet.plans.model.PlanTypeColumn updatePlanTypeColumn(
        com.ext.portlet.plans.model.PlanTypeColumn planTypeColumn)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanTypeColumn(planTypeColumn);
    }

    public static com.ext.portlet.plans.model.PlanTypeColumn updatePlanTypeColumn(
        com.ext.portlet.plans.model.PlanTypeColumn planTypeColumn, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanTypeColumn(planTypeColumn, merge);
    }

    public static PlanTypeColumnLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("PlanTypeColumnLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanTypeColumnLocalService service) {
        _service = service;
    }
}
