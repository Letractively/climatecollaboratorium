package com.ext.portlet.plans.service;


/**
 * <a href="PlanTemplateLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanTemplateLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanTemplateLocalService
 *
 */
public class PlanTemplateLocalServiceUtil {
    private static PlanTemplateLocalService _service;

    public static com.ext.portlet.plans.model.PlanTemplate addPlanTemplate(
        com.ext.portlet.plans.model.PlanTemplate planTemplate)
        throws com.liferay.portal.SystemException {
        return getService().addPlanTemplate(planTemplate);
    }

    public static com.ext.portlet.plans.model.PlanTemplate createPlanTemplate(
        java.lang.Long id) {
        return getService().createPlanTemplate(id);
    }

    public static void deletePlanTemplate(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanTemplate(id);
    }

    public static void deletePlanTemplate(
        com.ext.portlet.plans.model.PlanTemplate planTemplate)
        throws com.liferay.portal.SystemException {
        getService().deletePlanTemplate(planTemplate);
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

    public static com.ext.portlet.plans.model.PlanTemplate getPlanTemplate(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanTemplate(id);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanTemplate> getPlanTemplates(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanTemplates(start, end);
    }

    public static int getPlanTemplatesCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanTemplatesCount();
    }

    public static com.ext.portlet.plans.model.PlanTemplate updatePlanTemplate(
        com.ext.portlet.plans.model.PlanTemplate planTemplate)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanTemplate(planTemplate);
    }

    public static com.ext.portlet.plans.model.PlanTemplate updatePlanTemplate(
        com.ext.portlet.plans.model.PlanTemplate planTemplate, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanTemplate(planTemplate, merge);
    }

    public static PlanTemplateLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("PlanTemplateLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanTemplateLocalService service) {
        _service = service;
    }
}
