package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanPositionItemPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.plans.model.PlanPositionItem planPositionItem);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanPositionItem> planPositionItems);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanPositionItem create(
        com.ext.portlet.plans.service.persistence.PlanPositionItemPK planPositionItemPK);

    public com.ext.portlet.plans.model.PlanPositionItem remove(
        com.ext.portlet.plans.service.persistence.PlanPositionItemPK planPositionItemPK)
        throws com.ext.portlet.plans.NoSuchPlanPositionItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPositionItem remove(
        com.ext.portlet.plans.model.PlanPositionItem planPositionItem)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlanPositionItem planPositionItem, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlanPositionItem update(
        com.ext.portlet.plans.model.PlanPositionItem planPositionItem)
        throws com.liferay.portal.SystemException;

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
    public com.ext.portlet.plans.model.PlanPositionItem update(
        com.ext.portlet.plans.model.PlanPositionItem planPositionItem,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPositionItem updateImpl(
        com.ext.portlet.plans.model.PlanPositionItem planPositionItem,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPositionItem findByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlanPositionItemPK planPositionItemPK)
        throws com.ext.portlet.plans.NoSuchPlanPositionItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPositionItem fetchByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlanPositionItemPK planPositionItemPK)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPositionItem> findByAllByPlanPositionsId(
        java.lang.Long planPositionsId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPositionItem> findByAllByPlanPositionsId(
        java.lang.Long planPositionsId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPositionItem> findByAllByPlanPositionsId(
        java.lang.Long planPositionsId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPositionItem findByAllByPlanPositionsId_First(
        java.lang.Long planPositionsId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanPositionItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPositionItem findByAllByPlanPositionsId_Last(
        java.lang.Long planPositionsId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanPositionItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPositionItem[] findByAllByPlanPositionsId_PrevAndNext(
        com.ext.portlet.plans.service.persistence.PlanPositionItemPK planPositionItemPK,
        java.lang.Long planPositionsId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanPositionItemException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPositionItem> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPositionItem> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPositionItem> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByAllByPlanPositionsId(java.lang.Long planPositionsId)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByAllByPlanPositionsId(java.lang.Long planPositionsId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
