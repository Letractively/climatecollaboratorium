package com.ext.portlet.plans.service.persistence;

public class PlanVoteUtil {
    private static PlanVotePersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.plans.model.PlanVote planVote) {
        getPersistence().cacheResult(planVote);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanVote> planVotes) {
        getPersistence().cacheResult(planVotes);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.plans.model.PlanVote create(
        PlanVotePK planVotePK) {
        return getPersistence().create(planVotePK);
    }

    public static com.ext.portlet.plans.model.PlanVote remove(
        PlanVotePK planVotePK)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(planVotePK);
    }

    public static com.ext.portlet.plans.model.PlanVote remove(
        com.ext.portlet.plans.model.PlanVote planVote)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(planVote);
    }

    /**
     * @deprecated Use <code>update(PlanVote planVote, boolean merge)</code>.
     */
    public static com.ext.portlet.plans.model.PlanVote update(
        com.ext.portlet.plans.model.PlanVote planVote)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planVote);
    }

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
    public static com.ext.portlet.plans.model.PlanVote update(
        com.ext.portlet.plans.model.PlanVote planVote, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(planVote, merge);
    }

    public static com.ext.portlet.plans.model.PlanVote updateImpl(
        com.ext.portlet.plans.model.PlanVote planVote, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(planVote, merge);
    }

    public static com.ext.portlet.plans.model.PlanVote findByPrimaryKey(
        PlanVotePK planVotePK)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(planVotePK);
    }

    public static com.ext.portlet.plans.model.PlanVote fetchByPrimaryKey(
        PlanVotePK planVotePK) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(planVotePK);
    }

    public static com.ext.portlet.plans.model.PlanVote findBycontestId(
        java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException {
        return getPersistence().findBycontestId(userId);
    }

    public static com.ext.portlet.plans.model.PlanVote fetchBycontestId(
        java.lang.Long userId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchBycontestId(userId);
    }

    public static com.ext.portlet.plans.model.PlanVote fetchBycontestId(
        java.lang.Long userId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchBycontestId(userId, retrieveFromCache);
    }

    public static com.ext.portlet.plans.model.PlanVote findByPlanId(
        java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPlanId(planId);
    }

    public static com.ext.portlet.plans.model.PlanVote fetchByPlanId(
        java.lang.Long planId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPlanId(planId);
    }

    public static com.ext.portlet.plans.model.PlanVote fetchByPlanId(
        java.lang.Long planId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPlanId(planId, retrieveFromCache);
    }

    public static com.ext.portlet.plans.model.PlanVote findByContestIdUserId(
        java.lang.Long contestId, java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException {
        return getPersistence().findByContestIdUserId(contestId, userId);
    }

    public static com.ext.portlet.plans.model.PlanVote fetchByContestIdUserId(
        java.lang.Long contestId, java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByContestIdUserId(contestId, userId);
    }

    public static com.ext.portlet.plans.model.PlanVote fetchByContestIdUserId(
        java.lang.Long contestId, java.lang.Long userId,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByContestIdUserId(contestId, userId, retrieveFromCache);
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

    public static java.util.List<com.ext.portlet.plans.model.PlanVote> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanVote> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanVote> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeBycontestId(java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException {
        getPersistence().removeBycontestId(userId);
    }

    public static void removeByPlanId(java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException {
        getPersistence().removeByPlanId(planId);
    }

    public static void removeByContestIdUserId(java.lang.Long contestId,
        java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException {
        getPersistence().removeByContestIdUserId(contestId, userId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countBycontestId(java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countBycontestId(userId);
    }

    public static int countByPlanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByPlanId(planId);
    }

    public static int countByContestIdUserId(java.lang.Long contestId,
        java.lang.Long userId) throws com.liferay.portal.SystemException {
        return getPersistence().countByContestIdUserId(contestId, userId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static PlanVotePersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(PlanVotePersistence persistence) {
        _persistence = persistence;
    }
}
