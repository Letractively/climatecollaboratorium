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
        java.lang.Long userId) {
        return getPersistence().create(userId);
    }

    public static com.ext.portlet.plans.model.PlanVote remove(
        java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(userId);
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
        java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(userId);
    }

    public static com.ext.portlet.plans.model.PlanVote fetchByPrimaryKey(
        java.lang.Long userId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(userId);
    }

    public static com.ext.portlet.plans.model.PlanVote findByuserId(
        java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException {
        return getPersistence().findByuserId(userId);
    }

    public static com.ext.portlet.plans.model.PlanVote fetchByuserId(
        java.lang.Long userId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByuserId(userId);
    }

    public static com.ext.portlet.plans.model.PlanVote fetchByuserId(
        java.lang.Long userId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByuserId(userId, retrieveFromCache);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanVote> findByplanId(
        java.lang.Long planId) throws com.liferay.portal.SystemException {
        return getPersistence().findByplanId(planId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanVote> findByplanId(
        java.lang.Long planId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByplanId(planId, start, end);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanVote> findByplanId(
        java.lang.Long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByplanId(planId, start, end, obc);
    }

    public static com.ext.portlet.plans.model.PlanVote findByplanId_First(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException {
        return getPersistence().findByplanId_First(planId, obc);
    }

    public static com.ext.portlet.plans.model.PlanVote findByplanId_Last(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException {
        return getPersistence().findByplanId_Last(planId, obc);
    }

    public static com.ext.portlet.plans.model.PlanVote[] findByplanId_PrevAndNext(
        java.lang.Long userId, java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException {
        return getPersistence().findByplanId_PrevAndNext(userId, planId, obc);
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

    public static void removeByuserId(java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanVoteException,
            com.liferay.portal.SystemException {
        getPersistence().removeByuserId(userId);
    }

    public static void removeByplanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByplanId(planId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByuserId(java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByuserId(userId);
    }

    public static int countByplanId(java.lang.Long planId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByplanId(planId);
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
