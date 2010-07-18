package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanVotePersistence extends BasePersistence {
    public void cacheResult(com.ext.portlet.plans.model.PlanVote planVote);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanVote> planVotes);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanVote create(java.lang.Long userId);

    public com.ext.portlet.plans.model.PlanVote remove(java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanVote remove(
        com.ext.portlet.plans.model.PlanVote planVote)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlanVote planVote, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlanVote update(
        com.ext.portlet.plans.model.PlanVote planVote)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planVote the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planVote is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.plans.model.PlanVote update(
        com.ext.portlet.plans.model.PlanVote planVote, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanVote updateImpl(
        com.ext.portlet.plans.model.PlanVote planVote, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanVote findByPrimaryKey(
        java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanVote fetchByPrimaryKey(
        java.lang.Long userId) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanVote findByuserId(
        java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanVote fetchByuserId(
        java.lang.Long userId) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanVote fetchByuserId(
        java.lang.Long userId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanVote> findByplanId(
        java.lang.Long planId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanVote> findByplanId(
        java.lang.Long planId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanVote> findByplanId(
        java.lang.Long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanVote findByplanId_First(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanVote findByplanId_Last(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanVote[] findByplanId_PrevAndNext(
        java.lang.Long userId, java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanVote> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanVote> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanVote> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByuserId(java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException;

    public void removeByplanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByuserId(java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public int countByplanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
