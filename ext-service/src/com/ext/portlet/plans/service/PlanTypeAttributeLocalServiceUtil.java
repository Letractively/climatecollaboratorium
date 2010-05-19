package com.ext.portlet.plans.service;


/**
 * <a href="PlanTypeAttributeLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanTypeAttributeLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanTypeAttributeLocalService
 *
 */
public class PlanTypeAttributeLocalServiceUtil {
    private static PlanTypeAttributeLocalService _service;

    public static com.ext.portlet.plans.model.PlanTypeAttribute addPlanTypeAttribute(
        com.ext.portlet.plans.model.PlanTypeAttribute planTypeAttribute)
        throws com.liferay.portal.SystemException {
        return getService().addPlanTypeAttribute(planTypeAttribute);
    }

    public static com.ext.portlet.plans.model.PlanTypeAttribute createPlanTypeAttribute(
        java.lang.Long planTypeAttributeId) {
        return getService().createPlanTypeAttribute(planTypeAttributeId);
    }

    public static void deletePlanTypeAttribute(
        java.lang.Long planTypeAttributeId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanTypeAttribute(planTypeAttributeId);
    }

    public static void deletePlanTypeAttribute(
        com.ext.portlet.plans.model.PlanTypeAttribute planTypeAttribute)
        throws com.liferay.portal.SystemException {
        getService().deletePlanTypeAttribute(planTypeAttribute);
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

    public static com.ext.portlet.plans.model.PlanTypeAttribute getPlanTypeAttribute(
        java.lang.Long planTypeAttributeId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanTypeAttribute(planTypeAttributeId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> getPlanTypeAttributes(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanTypeAttributes(start, end);
    }

    public static int getPlanTypeAttributesCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanTypeAttributesCount();
    }

    public static com.ext.portlet.plans.model.PlanTypeAttribute updatePlanTypeAttribute(
        com.ext.portlet.plans.model.PlanTypeAttribute planTypeAttribute)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanTypeAttribute(planTypeAttribute);
    }

    public static com.ext.portlet.plans.model.PlanTypeAttribute updatePlanTypeAttribute(
        com.ext.portlet.plans.model.PlanTypeAttribute planTypeAttribute,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updatePlanTypeAttribute(planTypeAttribute, merge);
    }

    public static PlanTypeAttributeLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "PlanTypeAttributeLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanTypeAttributeLocalService service) {
        _service = service;
    }
}
