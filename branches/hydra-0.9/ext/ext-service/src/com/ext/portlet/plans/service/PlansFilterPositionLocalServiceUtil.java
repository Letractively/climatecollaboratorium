package com.ext.portlet.plans.service;


/**
 * <a href="PlansFilterPositionLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlansFilterPositionLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlansFilterPositionLocalService
 *
 */
public class PlansFilterPositionLocalServiceUtil {
    private static PlansFilterPositionLocalService _service;

    public static com.ext.portlet.plans.model.PlansFilterPosition addPlansFilterPosition(
        com.ext.portlet.plans.model.PlansFilterPosition plansFilterPosition)
        throws com.liferay.portal.SystemException {
        return getService().addPlansFilterPosition(plansFilterPosition);
    }

    public static com.ext.portlet.plans.model.PlansFilterPosition createPlansFilterPosition(
        com.ext.portlet.plans.service.persistence.PlansFilterPositionPK plansFilterPositionPK) {
        return getService().createPlansFilterPosition(plansFilterPositionPK);
    }

    public static void deletePlansFilterPosition(
        com.ext.portlet.plans.service.persistence.PlansFilterPositionPK plansFilterPositionPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlansFilterPosition(plansFilterPositionPK);
    }

    public static void deletePlansFilterPosition(
        com.ext.portlet.plans.model.PlansFilterPosition plansFilterPosition)
        throws com.liferay.portal.SystemException {
        getService().deletePlansFilterPosition(plansFilterPosition);
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

    public static com.ext.portlet.plans.model.PlansFilterPosition getPlansFilterPosition(
        com.ext.portlet.plans.service.persistence.PlansFilterPositionPK plansFilterPositionPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlansFilterPosition(plansFilterPositionPK);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlansFilterPosition> getPlansFilterPositions(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlansFilterPositions(start, end);
    }

    public static int getPlansFilterPositionsCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlansFilterPositionsCount();
    }

    public static com.ext.portlet.plans.model.PlansFilterPosition updatePlansFilterPosition(
        com.ext.portlet.plans.model.PlansFilterPosition plansFilterPosition)
        throws com.liferay.portal.SystemException {
        return getService().updatePlansFilterPosition(plansFilterPosition);
    }

    public static com.ext.portlet.plans.model.PlansFilterPosition updatePlansFilterPosition(
        com.ext.portlet.plans.model.PlansFilterPosition plansFilterPosition,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updatePlansFilterPosition(plansFilterPosition, merge);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlansFilterPosition> getPositionsForUserPlanType(
        java.lang.Long userId, java.lang.Long planTypeId)
        throws com.liferay.portal.SystemException {
        return getService().getPositionsForUserPlanType(userId, planTypeId);
    }

    public static java.util.List<Long> getPositionsIds(java.lang.Long userId,
        java.lang.Long planTypeId) throws com.liferay.portal.SystemException {
        return getService().getPositionsIds(userId, planTypeId);
    }

    public static void storeFilterPositionsIds(java.lang.Long userId,
        java.lang.Long planTypeId, java.util.List<Long> positionsIds)
        throws com.liferay.portal.SystemException {
        getService().storeFilterPositionsIds(userId, planTypeId, positionsIds);
    }

    public static PlansFilterPositionLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "PlansFilterPositionLocalService is not set");
        }

        return _service;
    }

    public void setService(PlansFilterPositionLocalService service) {
        _service = service;
    }
}
