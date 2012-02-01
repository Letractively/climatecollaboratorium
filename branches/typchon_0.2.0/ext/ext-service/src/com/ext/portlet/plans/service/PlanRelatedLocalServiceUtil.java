package com.ext.portlet.plans.service;


/**
 * <a href="PlanRelatedLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanRelatedLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanRelatedLocalService
 *
 */
public class PlanRelatedLocalServiceUtil {
    private static PlanRelatedLocalService _service;

    public static com.ext.portlet.plans.model.PlanRelated addPlanRelated(
        com.ext.portlet.plans.model.PlanRelated planRelated)
        throws com.liferay.portal.SystemException {
        return getService().addPlanRelated(planRelated);
    }

    public static com.ext.portlet.plans.model.PlanRelated createPlanRelated(
        com.ext.portlet.plans.service.persistence.PlanRelatedPK planRelatedPK) {
        return getService().createPlanRelated(planRelatedPK);
    }

    public static void deletePlanRelated(
        com.ext.portlet.plans.service.persistence.PlanRelatedPK planRelatedPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanRelated(planRelatedPK);
    }

    public static void deletePlanRelated(
        com.ext.portlet.plans.model.PlanRelated planRelated)
        throws com.liferay.portal.SystemException {
        getService().deletePlanRelated(planRelated);
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

    public static com.ext.portlet.plans.model.PlanRelated getPlanRelated(
        com.ext.portlet.plans.service.persistence.PlanRelatedPK planRelatedPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanRelated(planRelatedPK);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanRelated> getPlanRelateds(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanRelateds(start, end);
    }

    public static int getPlanRelatedsCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanRelatedsCount();
    }

    public static com.ext.portlet.plans.model.PlanRelated updatePlanRelated(
        com.ext.portlet.plans.model.PlanRelated planRelated)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanRelated(planRelated);
    }

    public static com.ext.portlet.plans.model.PlanRelated updatePlanRelated(
        com.ext.portlet.plans.model.PlanRelated planRelated, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanRelated(planRelated, merge);
    }

    public static PlanRelatedLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("PlanRelatedLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanRelatedLocalService service) {
        _service = service;
    }
}
