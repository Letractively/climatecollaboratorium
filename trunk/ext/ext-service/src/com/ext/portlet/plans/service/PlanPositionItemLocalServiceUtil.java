package com.ext.portlet.plans.service;


/**
 * <a href="PlanPositionItemLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanPositionItemLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanPositionItemLocalService
 *
 */
public class PlanPositionItemLocalServiceUtil {
    private static PlanPositionItemLocalService _service;

    public static com.ext.portlet.plans.model.PlanPositionItem addPlanPositionItem(
        com.ext.portlet.plans.model.PlanPositionItem planPositionItem)
        throws com.liferay.portal.SystemException {
        return getService().addPlanPositionItem(planPositionItem);
    }

    public static com.ext.portlet.plans.model.PlanPositionItem createPlanPositionItem(
        com.ext.portlet.plans.service.persistence.PlanPositionItemPK planPositionItemPK) {
        return getService().createPlanPositionItem(planPositionItemPK);
    }

    public static void deletePlanPositionItem(
        com.ext.portlet.plans.service.persistence.PlanPositionItemPK planPositionItemPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanPositionItem(planPositionItemPK);
    }

    public static void deletePlanPositionItem(
        com.ext.portlet.plans.model.PlanPositionItem planPositionItem)
        throws com.liferay.portal.SystemException {
        getService().deletePlanPositionItem(planPositionItem);
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

    public static com.ext.portlet.plans.model.PlanPositionItem getPlanPositionItem(
        com.ext.portlet.plans.service.persistence.PlanPositionItemPK planPositionItemPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanPositionItem(planPositionItemPK);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanPositionItem> getPlanPositionItems(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanPositionItems(start, end);
    }

    public static int getPlanPositionItemsCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanPositionItemsCount();
    }

    public static com.ext.portlet.plans.model.PlanPositionItem updatePlanPositionItem(
        com.ext.portlet.plans.model.PlanPositionItem planPositionItem)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanPositionItem(planPositionItem);
    }

    public static com.ext.portlet.plans.model.PlanPositionItem updatePlanPositionItem(
        com.ext.portlet.plans.model.PlanPositionItem planPositionItem,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updatePlanPositionItem(planPositionItem, merge);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanPositionItem> getAllIdsForPlanPositions(
        com.ext.portlet.plans.model.PlanPositions planPositions)
        throws com.liferay.portal.SystemException {
        return getService().getAllIdsForPlanPositions(planPositions);
    }

    public static PlanPositionItemLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "PlanPositionItemLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanPositionItemLocalService service) {
        _service = service;
    }
}
