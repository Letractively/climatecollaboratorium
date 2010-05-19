package com.ext.portlet.plans.service;


/**
 * <a href="PlanAttributeLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanAttributeLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanAttributeLocalService
 *
 */
public class PlanAttributeLocalServiceUtil {
    private static PlanAttributeLocalService _service;

    public static com.ext.portlet.plans.model.PlanAttribute addPlanAttribute(
        com.ext.portlet.plans.model.PlanAttribute planAttribute)
        throws com.liferay.portal.SystemException {
        return getService().addPlanAttribute(planAttribute);
    }

    public static com.ext.portlet.plans.model.PlanAttribute createPlanAttribute(
        java.lang.Long attributeId) {
        return getService().createPlanAttribute(attributeId);
    }

    public static void deletePlanAttribute(java.lang.Long attributeId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanAttribute(attributeId);
    }

    public static void deletePlanAttribute(
        com.ext.portlet.plans.model.PlanAttribute planAttribute)
        throws com.liferay.portal.SystemException {
        getService().deletePlanAttribute(planAttribute);
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

    public static com.ext.portlet.plans.model.PlanAttribute getPlanAttribute(
        java.lang.Long attributeId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanAttribute(attributeId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanAttributes(start, end);
    }

    public static int getPlanAttributesCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanAttributesCount();
    }

    public static com.ext.portlet.plans.model.PlanAttribute updatePlanAttribute(
        com.ext.portlet.plans.model.PlanAttribute planAttribute)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanAttribute(planAttribute);
    }

    public static com.ext.portlet.plans.model.PlanAttribute updatePlanAttribute(
        com.ext.portlet.plans.model.PlanAttribute planAttribute, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanAttribute(planAttribute, merge);
    }

    public static void addPlanAttribute(long planId,
        java.lang.String attributeName, java.lang.String attributeValue)
        throws com.liferay.portal.SystemException {
        getService().addPlanAttribute(planId, attributeName, attributeValue);
    }

    public static com.ext.portlet.plans.model.PlanAttribute findPlanAttribute(
        long planId, java.lang.String attributeName)
        throws com.liferay.portal.SystemException {
        return getService().findPlanAttribute(planId, attributeName);
    }

    public static PlanAttributeLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("PlanAttributeLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanAttributeLocalService service) {
        _service = service;
    }
}
