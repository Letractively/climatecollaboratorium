package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanVotePersistence extends BasePersistence {
    public void cacheResult(com.ext.portlet.plans.model.PlanVote planVote);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanVote> planVotes);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanVote create(
        com.ext.portlet.plans.service.persistence.PlanVotePK planVotePK);

    public com.ext.portlet.plans.model.PlanVote remove(
        com.ext.portlet.plans.service.persistence.PlanVotePK planVotePK)
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
        com.ext.portlet.plans.service.persistence.PlanVotePK planVotePK)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanVote fetchByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlanVotePK planVotePK)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanVote> findBycontestId(
        java.lang.Long contestId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanVote> findBycontestId(
        java.lang.Long contestId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanVote> findBycontestId(
        java.lang.Long contestId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanVote findBycontestId_First(
        java.lang.Long contestId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanVote findBycontestId_Last(
        java.lang.Long contestId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanVote[] findBycontestId_PrevAndNext(
        com.ext.portlet.plans.service.persistence.PlanVotePK planVotePK,
        java.lang.Long contestId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanVote> findByPlanId(
        java.lang.Long planId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanVote> findByPlanId(
        java.lang.Long planId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanVote> findByPlanId(
        java.lang.Long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanVote findByPlanId_First(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanVote findByPlanId_Last(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanVote[] findByPlanId_PrevAndNext(
        com.ext.portlet.plans.service.persistence.PlanVotePK planVotePK,
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanVote findByContestIdUserId(
        java.lang.Long contestId, java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanVote fetchByContestIdUserId(
        java.lang.Long contestId, java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanVote fetchByContestIdUserId(
        java.lang.Long contestId, java.lang.Long userId,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException;

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

    public void removeBycontestId(java.lang.Long contestId)
        throws com.liferay.portal.SystemException;

    public void removeByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException;

    public void removeByContestIdUserId(java.lang.Long contestId,
        java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countBycontestId(java.lang.Long contestId)
        throws com.liferay.portal.SystemException;

    public int countByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException;

    public int countByContestIdUserId(java.lang.Long contestId,
        java.lang.Long userId) throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
