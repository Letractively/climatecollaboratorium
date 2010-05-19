package com.ext.portlet.plans.service;


/**
 * <a href="PlanPositionLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanPositionLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanPositionLocalService
 *
 */
public class PlanPositionLocalServiceUtil {
    private static PlanPositionLocalService _service;

    public static com.ext.portlet.plans.model.PlanPosition addPlanPosition(
        com.ext.portlet.plans.model.PlanPosition planPosition)
        throws com.liferay.portal.SystemException {
        return getService().addPlanPosition(planPosition);
    }

    public static com.ext.portlet.plans.model.PlanPosition createPlanPosition(
        com.ext.portlet.plans.service.persistence.PlanPositionPK planPositionPK) {
        return getService().createPlanPosition(planPositionPK);
    }

    public static void deletePlanPosition(
        com.ext.portlet.plans.service.persistence.PlanPositionPK planPositionPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanPosition(planPositionPK);
    }

    public static void deletePlanPosition(
        com.ext.portlet.plans.model.PlanPosition planPosition)
        throws com.liferay.portal.SystemException {
        getService().deletePlanPosition(planPosition);
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

    public static com.ext.portlet.plans.model.PlanPosition getPlanPosition(
        com.ext.portlet.plans.service.persistence.PlanPositionPK planPositionPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanPosition(planPositionPK);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanPosition> getPlanPositions(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanPositions(start, end);
    }

    public static int getPlanPositionsCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanPositionsCount();
    }

    public static com.ext.portlet.plans.model.PlanPosition updatePlanPosition(
        com.ext.portlet.plans.model.PlanPosition planPosition)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanPosition(planPosition);
    }

    public static com.ext.portlet.plans.model.PlanPosition updatePlanPosition(
        com.ext.portlet.plans.model.PlanPosition planPosition, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanPosition(planPosition, merge);
    }

    public static PlanPositionLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("PlanPositionLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanPositionLocalService service) {
        _service = service;
    }
}
