package com.ext.portlet.plans.service.persistence;

public class PlanPositionItemUtil {
    private static PlanPositionItemPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.plans.model.PlanPositionItem planPositionItem) {
        getPersistence().cacheResult(planPositionItem);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanPositionItem> planPositionItems) {
        getPersistence().cacheResult(planPositionItems);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlanPositionItem create(
        com.ext.portlet.plans.service.persistence.PlanPositionItemPK planPositionItemPK) {
        return getPersistence().create(planPositionItemPK);
    }

    public static com.ext.portlet.plans.model.PlanPositionItem remove(
        com.ext.portlet.plans.service.persistence.PlanPositionItemPK planPositionItemPK)
        throws com.ext.portlet.plans.NoSuchPlanPositionItemException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(planPositionItemPK);
    }

    public static com.ext.portlet.plans.model.PlanPositionItem remove(
        com.ext.portlet.plans.model.PlanPositionItem planPositionItem)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(planPositionItem);
    }

    /**
     * @deprecated Use <code>update(PlanPositionItem planPositionItem, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlanPositionItem update(
        com.ext.portlet.plans.model.PlanPositionItem planPositionItem)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planPositionItem);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planPositionItem the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planPositionItem is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.plans.model.PlanPositionItem update(
        com.ext.portlet.plans.model.PlanPositionItem planPositionItem,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(planPositionItem, merge);
    }

    public static com.ext.portlet.plans.model.PlanPositionItem updateImpl(
        com.ext.portlet.plans.model.PlanPositionItem planPositionItem,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(planPositionItem, merge);
    }

    public static com.ext.portlet.plans.model.PlanPositionItem findByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlanPositionItemPK planPositionItemPK)
        throws com.ext.portlet.plans.NoSuchPlanPositionItemException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(planPositionItemPK);
    }

    public static com.ext.portlet.plans.model.PlanPositionItem fetchByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlanPositionItemPK planPositionItemPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(planPositionItemPK);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanPositionItem> findByAllByPlanPositionsId(
        java.lang.Long planPositionsId)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByAllByPlanPositionsId(planPositionsId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanPositionItem> findByAllByPlanPositionsId(
        java.lang.Long planPositionsId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByAllByPlanPositionsId(planPositionsId, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanPositionItem> findByAllByPlanPositionsId(
        java.lang.Long planPositionsId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByAllByPlanPositionsId(planPositionsId, start, end, obc);
    }

    public static com.ext.portlet.plans.model.PlanPositionItem findByAllByPlanPositionsId_First(
        java.lang.Long planPositionsId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanPositionItemException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByAllByPlanPositionsId_First(planPositionsId, obc);
    }

    public static com.ext.portlet.plans.model.PlanPositionItem findByAllByPlanPositionsId_Last(
        java.lang.Long planPositionsId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanPositionItemException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByAllByPlanPositionsId_Last(planPositionsId, obc);
    }

    public static com.ext.portlet.plans.model.PlanPositionItem[] findByAllByPlanPositionsId_PrevAndNext(
        com.ext.portlet.plans.service.persistence.PlanPositionItemPK planPositionItemPK,
        java.lang.Long planPositionsId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanPositionItemException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByAllByPlanPositionsId_PrevAndNext(planPositionItemPK,
            planPositionsId, obc);
    }

    public static java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    public static java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanPositionItem> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanPositionItem> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanPositionItem> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByAllByPlanPositionsId(
        java.lang.Long planPositionsId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByAllByPlanPositionsId(planPositionsId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByAllByPlanPositionsId(
        java.lang.Long planPositionsId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByAllByPlanPositionsId(planPositionsId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static PlanPositionItemPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlanPositionItemPersistence persistence) {
        _persistence = persistence;
    }
}
