package com.ext.portlet.plans.service;


/**
 * <a href="PlanTypeLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanTypeLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanTypeLocalService
 *
 */
public class PlanTypeLocalServiceUtil {
    private static PlanTypeLocalService _service;

    public static com.ext.portlet.plans.model.PlanType addPlanType(
        com.ext.portlet.plans.model.PlanType planType)
        throws com.liferay.portal.SystemException {
        return getService().addPlanType(planType);
    }

    public static com.ext.portlet.plans.model.PlanType createPlanType(
        java.lang.Long planTypeId) {
        return getService().createPlanType(planTypeId);
    }

    public static void deletePlanType(java.lang.Long planTypeId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanType(planTypeId);
    }

    public static void deletePlanType(
        com.ext.portlet.plans.model.PlanType planType)
        throws com.liferay.portal.SystemException {
        getService().deletePlanType(planType);
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

    public static com.ext.portlet.plans.model.PlanType getPlanType(
        java.lang.Long planTypeId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanType(planTypeId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanType> getPlanTypes(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanTypes(start, end);
    }

    public static int getPlanTypesCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanTypesCount();
    }

    public static com.ext.portlet.plans.model.PlanType updatePlanType(
        com.ext.portlet.plans.model.PlanType planType)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanType(planType);
    }

    public static com.ext.portlet.plans.model.PlanType updatePlanType(
        com.ext.portlet.plans.model.PlanType planType, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanType(planType, merge);
    }

    public static com.ext.portlet.plans.model.PlanType getDefaultPlanType()
        throws com.ext.portlet.plans.NoSuchPlanTypeException,
            com.liferay.portal.SystemException {
        return getService().getDefaultPlanType();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> getColumnsByPlanTypeId(
        long planTypeId) throws com.liferay.portal.SystemException {
        return getService().getColumnsByPlanTypeId(planTypeId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> getAttributesByPlanTypeId(
        long planTypeId) throws com.liferay.portal.SystemException {
        return getService().getAttributesByPlanTypeId(planTypeId);
    }

    public static boolean isRegionalType(long planTypeId)
        throws com.liferay.portal.SystemException {
        return getService().isRegionalType(planTypeId);
    }

    public static PlanTypeLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("PlanTypeLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanTypeLocalService service) {
        _service = service;
    }
}
