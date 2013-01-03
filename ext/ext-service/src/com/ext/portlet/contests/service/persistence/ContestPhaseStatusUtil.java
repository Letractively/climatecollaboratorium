package com.ext.portlet.contests.service.persistence;

public class ContestPhaseStatusUtil {
    private static ContestPhaseStatusPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.contests.model.ContestPhaseStatus contestPhaseStatus) {
        getPersistence().cacheResult(contestPhaseStatus);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.contests.model.ContestPhaseStatus> contestPhaseStatuses) {
        getPersistence().cacheResult(contestPhaseStatuses);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.contests.model.ContestPhaseStatus create(
        java.lang.String name) {
        return getPersistence().create(name);
    }

    public static com.ext.portlet.contests.model.ContestPhaseStatus remove(
        java.lang.String name)
        throws com.ext.portlet.contests.NoSuchContestPhaseStatusException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(name);
    }

    public static com.ext.portlet.contests.model.ContestPhaseStatus remove(
        com.ext.portlet.contests.model.ContestPhaseStatus contestPhaseStatus)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(contestPhaseStatus);
    }

    /**
     * @deprecated Use <code>update(ContestPhaseStatus contestPhaseStatus, boolean merge)</code>.
     */
    public static com.ext.portlet.contests.model.ContestPhaseStatus update(
        com.ext.portlet.contests.model.ContestPhaseStatus contestPhaseStatus)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(contestPhaseStatus);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                contestPhaseStatus the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when contestPhaseStatus is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.contests.model.ContestPhaseStatus update(
        com.ext.portlet.contests.model.ContestPhaseStatus contestPhaseStatus,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(contestPhaseStatus, merge);
    }

    public static com.ext.portlet.contests.model.ContestPhaseStatus updateImpl(
        com.ext.portlet.contests.model.ContestPhaseStatus contestPhaseStatus,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(contestPhaseStatus, merge);
    }

    public static com.ext.portlet.contests.model.ContestPhaseStatus findByPrimaryKey(
        java.lang.String name)
        throws com.ext.portlet.contests.NoSuchContestPhaseStatusException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(name);
    }

    public static com.ext.portlet.contests.model.ContestPhaseStatus fetchByPrimaryKey(
        java.lang.String name) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(name);
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

    public static java.util.List<com.ext.portlet.contests.model.ContestPhaseStatus> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestPhaseStatus> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestPhaseStatus> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static ContestPhaseStatusPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ContestPhaseStatusPersistence persistence) {
        _persistence = persistence;
    }
}
