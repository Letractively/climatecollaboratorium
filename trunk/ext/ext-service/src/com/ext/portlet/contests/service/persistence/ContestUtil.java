package com.ext.portlet.contests.service.persistence;

public class ContestUtil {
    private static ContestPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.contests.model.Contest contest) {
        getPersistence().cacheResult(contest);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.contests.model.Contest> contests) {
        getPersistence().cacheResult(contests);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.contests.model.Contest create(
        java.lang.Long ContestPK) {
        return getPersistence().create(ContestPK);
    }

    public static com.ext.portlet.contests.model.Contest remove(
        java.lang.Long ContestPK)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(ContestPK);
    }

    public static com.ext.portlet.contests.model.Contest remove(
        com.ext.portlet.contests.model.Contest contest)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(contest);
    }

    /**
     * @deprecated Use <code>update(Contest contest, boolean merge)</code>.
     */
    public static com.ext.portlet.contests.model.Contest update(
        com.ext.portlet.contests.model.Contest contest)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(contest);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                contest the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when contest is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.contests.model.Contest update(
        com.ext.portlet.contests.model.Contest contest, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(contest, merge);
    }

    public static com.ext.portlet.contests.model.Contest updateImpl(
        com.ext.portlet.contests.model.Contest contest, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(contest, merge);
    }

    public static com.ext.portlet.contests.model.Contest findByPrimaryKey(
        java.lang.Long ContestPK)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(ContestPK);
    }

    public static com.ext.portlet.contests.model.Contest fetchByPrimaryKey(
        java.lang.Long ContestPK) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(ContestPK);
    }

    public static java.util.List<com.ext.portlet.contests.model.Contest> findByType(
        java.lang.Long PlanTypeId) throws com.liferay.portal.SystemException {
        return getPersistence().findByType(PlanTypeId);
    }

    public static java.util.List<com.ext.portlet.contests.model.Contest> findByType(
        java.lang.Long PlanTypeId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByType(PlanTypeId, start, end);
    }

    public static java.util.List<com.ext.portlet.contests.model.Contest> findByType(
        java.lang.Long PlanTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByType(PlanTypeId, start, end, obc);
    }

    public static com.ext.portlet.contests.model.Contest findByType_First(
        java.lang.Long PlanTypeId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.SystemException {
        return getPersistence().findByType_First(PlanTypeId, obc);
    }

    public static com.ext.portlet.contests.model.Contest findByType_Last(
        java.lang.Long PlanTypeId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.SystemException {
        return getPersistence().findByType_Last(PlanTypeId, obc);
    }

    public static com.ext.portlet.contests.model.Contest[] findByType_PrevAndNext(
        java.lang.Long ContestPK, java.lang.Long PlanTypeId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByType_PrevAndNext(ContestPK, PlanTypeId, obc);
    }

    public static com.ext.portlet.contests.model.Contest findBycontestActive(
        java.lang.Boolean contestActive)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.SystemException {
        return getPersistence().findBycontestActive(contestActive);
    }

    public static com.ext.portlet.contests.model.Contest fetchBycontestActive(
        java.lang.Boolean contestActive)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchBycontestActive(contestActive);
    }

    public static com.ext.portlet.contests.model.Contest fetchBycontestActive(
        java.lang.Boolean contestActive, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchBycontestActive(contestActive, retrieveFromCache);
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

    public static java.util.List<com.ext.portlet.contests.model.Contest> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.contests.model.Contest> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.contests.model.Contest> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByType(java.lang.Long PlanTypeId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByType(PlanTypeId);
    }

    public static void removeBycontestActive(java.lang.Boolean contestActive)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.SystemException {
        getPersistence().removeBycontestActive(contestActive);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByType(java.lang.Long PlanTypeId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByType(PlanTypeId);
    }

    public static int countBycontestActive(java.lang.Boolean contestActive)
        throws com.liferay.portal.SystemException {
        return getPersistence().countBycontestActive(contestActive);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static ContestPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ContestPersistence persistence) {
        _persistence = persistence;
    }
}
