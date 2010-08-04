package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanFanPersistence extends BasePersistence {
    public void cacheResult(com.ext.portlet.plans.model.PlanFan planFan);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanFan> planFans);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanFan create(java.lang.Long id);

    public com.ext.portlet.plans.model.PlanFan remove(java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanFan remove(
        com.ext.portlet.plans.model.PlanFan planFan)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlanFan planFan, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlanFan update(
        com.ext.portlet.plans.model.PlanFan planFan)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planFan the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planFan is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.plans.model.PlanFan update(
        com.ext.portlet.plans.model.PlanFan planFan, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanFan updateImpl(
        com.ext.portlet.plans.model.PlanFan planFan, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanFan findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanFan fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanFan> findByPlanId(
        java.lang.Long planId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanFan> findByPlanId(
        java.lang.Long planId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanFan> findByPlanId(
        java.lang.Long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanFan findByPlanId_First(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanFan findByPlanId_Last(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanFan[] findByPlanId_PrevAndNext(
        java.lang.Long id, java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanFan> findByUserId(
        java.lang.Long userId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanFan> findByUserId(
        java.lang.Long userId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanFan> findByUserId(
        java.lang.Long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanFan findByUserId_First(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanFan findByUserId_Last(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanFan[] findByUserId_PrevAndNext(
        java.lang.Long id, java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanFan findByPlanIdUserId(
        java.lang.Long planId, java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanFan fetchByPlanIdUserId(
        java.lang.Long planId, java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanFan fetchByPlanIdUserId(
        java.lang.Long planId, java.lang.Long userId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanFan> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanFan> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanFan> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException;

    public void removeByUserId(java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public void removeByPlanIdUserId(java.lang.Long planId,
        java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException;

    public int countByUserId(java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public int countByPlanIdUserId(java.lang.Long planId, java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
