package com.ext.portlet.plans.service;


/**
 * <a href="PlanFanLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanFanLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanFanLocalService
 *
 */
public class PlanFanLocalServiceUtil {
    private static PlanFanLocalService _service;

    public static com.ext.portlet.plans.model.PlanFan addPlanFan(
        com.ext.portlet.plans.model.PlanFan planFan)
        throws com.liferay.portal.SystemException {
        return getService().addPlanFan(planFan);
    }

    public static com.ext.portlet.plans.model.PlanFan createPlanFan(
        java.lang.Long id) {
        return getService().createPlanFan(id);
    }

    public static void deletePlanFan(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanFan(id);
    }

    public static void deletePlanFan(
        com.ext.portlet.plans.model.PlanFan planFan)
        throws com.liferay.portal.SystemException {
        getService().deletePlanFan(planFan);
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

    public static com.ext.portlet.plans.model.PlanFan getPlanFan(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanFan(id);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanFan> getPlanFans(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanFans(start, end);
    }

    public static int getPlanFansCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanFansCount();
    }

    public static com.ext.portlet.plans.model.PlanFan updatePlanFan(
        com.ext.portlet.plans.model.PlanFan planFan)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanFan(planFan);
    }

    public static com.ext.portlet.plans.model.PlanFan updatePlanFan(
        com.ext.portlet.plans.model.PlanFan planFan, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanFan(planFan, merge);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanFan> getPlanFansForPlan(
        java.lang.Long planId) throws com.liferay.portal.SystemException {
        return getService().getPlanFansForPlan(planId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanFan> getPlanFansForUser(
        java.lang.Long userId) throws com.liferay.portal.SystemException {
        return getService().getPlanFansForUser(userId);
    }

    public static com.ext.portlet.plans.model.PlanFan addFan(
        java.lang.Long planId, java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        return getService().addFan(planId, userId);
    }

    public static void removePlanFan(java.lang.Long planId,
        java.lang.Long userId) throws com.liferay.portal.SystemException {
        getService().removePlanFan(planId, userId);
    }

    public static com.ext.portlet.plans.model.PlanFan getPlanFanByPlanIdUserId(
        java.lang.Long planId, java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.SystemException {
        return getService().getPlanFanByPlanIdUserId(planId, userId);
    }

    public static int countByUserId(java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        return getService().countByUserId(userId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanFan> getByUserId(
        java.lang.Long userId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getService().getByUserId(userId, start, end);
    }

    public static PlanFanLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("PlanFanLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanFanLocalService service) {
        _service = service;
    }
}
