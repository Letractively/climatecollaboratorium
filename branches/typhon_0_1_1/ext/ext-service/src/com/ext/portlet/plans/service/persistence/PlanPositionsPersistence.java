package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanPositionsPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.plans.model.PlanPositions planPositions);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanPositions> planPositionses);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanPositions create(java.lang.Long id);

    public com.ext.portlet.plans.model.PlanPositions remove(java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPositions remove(
        com.ext.portlet.plans.model.PlanPositions planPositions)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlanPositions planPositions, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlanPositions update(
        com.ext.portlet.plans.model.PlanPositions planPositions)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planPositions the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planPositions is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.plans.model.PlanPositions update(
        com.ext.portlet.plans.model.PlanPositions planPositions, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPositions updateImpl(
        com.ext.portlet.plans.model.PlanPositions planPositions, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPositions findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPositions fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPositions findByCurrentByPlanId(
        java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPositions fetchByCurrentByPlanId(
        java.lang.Long planId) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPositions fetchByCurrentByPlanId(
        java.lang.Long planId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPositions> findByAllByPlanId(
        java.lang.Long planId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPositions> findByAllByPlanId(
        java.lang.Long planId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPositions> findByAllByPlanId(
        java.lang.Long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPositions findByAllByPlanId_First(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPositions findByAllByPlanId_Last(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPositions[] findByAllByPlanId_PrevAndNext(
        java.lang.Long id, java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPositions> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPositions> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPositions> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByCurrentByPlanId(java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.SystemException;

    public void removeByAllByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByCurrentByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException;

    public int countByAllByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
