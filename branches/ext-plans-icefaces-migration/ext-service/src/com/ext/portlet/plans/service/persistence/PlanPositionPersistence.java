package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanPositionPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.plans.model.PlanPosition planPosition);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanPosition> planPositions);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanPosition create(
        com.ext.portlet.plans.service.persistence.PlanPositionPK planPositionPK);

    public com.ext.portlet.plans.model.PlanPosition remove(
        com.ext.portlet.plans.service.persistence.PlanPositionPK planPositionPK)
        throws com.ext.portlet.plans.NoSuchPlanPositionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPosition remove(
        com.ext.portlet.plans.model.PlanPosition planPosition)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlanPosition planPosition, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlanPosition update(
        com.ext.portlet.plans.model.PlanPosition planPosition)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planPosition the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planPosition is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.plans.model.PlanPosition update(
        com.ext.portlet.plans.model.PlanPosition planPosition, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPosition updateImpl(
        com.ext.portlet.plans.model.PlanPosition planPosition, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPosition findByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlanPositionPK planPositionPK)
        throws com.ext.portlet.plans.NoSuchPlanPositionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPosition fetchByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlanPositionPK planPositionPK)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPosition> findByPositionId(
        java.lang.Long positionId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPosition> findByPositionId(
        java.lang.Long positionId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPosition> findByPositionId(
        java.lang.Long positionId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPosition findByPositionId_First(
        java.lang.Long positionId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanPositionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPosition findByPositionId_Last(
        java.lang.Long positionId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanPositionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPosition[] findByPositionId_PrevAndNext(
        com.ext.portlet.plans.service.persistence.PlanPositionPK planPositionPK,
        java.lang.Long positionId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanPositionException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPosition> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPosition> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPosition> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByPositionId(java.lang.Long positionId)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByPositionId(java.lang.Long positionId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
