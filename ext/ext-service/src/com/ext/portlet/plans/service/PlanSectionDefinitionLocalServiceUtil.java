package com.ext.portlet.plans.service;


/**
 * <a href="PlanSectionDefinitionLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanSectionDefinitionLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanSectionDefinitionLocalService
 *
 */
public class PlanSectionDefinitionLocalServiceUtil {
    private static PlanSectionDefinitionLocalService _service;

    public static com.ext.portlet.plans.model.PlanSectionDefinition addPlanSectionDefinition(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition)
        throws com.liferay.portal.SystemException {
        return getService().addPlanSectionDefinition(planSectionDefinition);
    }

    public static com.ext.portlet.plans.model.PlanSectionDefinition createPlanSectionDefinition(
        java.lang.Long id) {
        return getService().createPlanSectionDefinition(id);
    }

    public static void deletePlanSectionDefinition(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanSectionDefinition(id);
    }

    public static void deletePlanSectionDefinition(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition)
        throws com.liferay.portal.SystemException {
        getService().deletePlanSectionDefinition(planSectionDefinition);
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

    public static com.ext.portlet.plans.model.PlanSectionDefinition getPlanSectionDefinition(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanSectionDefinition(id);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanSectionDefinition> getPlanSectionDefinitions(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanSectionDefinitions(start, end);
    }

    public static int getPlanSectionDefinitionsCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanSectionDefinitionsCount();
    }

    public static com.ext.portlet.plans.model.PlanSectionDefinition updatePlanSectionDefinition(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanSectionDefinition(planSectionDefinition);
    }

    public static com.ext.portlet.plans.model.PlanSectionDefinition updatePlanSectionDefinition(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updatePlanSectionDefinition(planSectionDefinition, merge);
    }

    public static PlanSectionDefinitionLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "PlanSectionDefinitionLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanSectionDefinitionLocalService service) {
        _service = service;
    }
}
