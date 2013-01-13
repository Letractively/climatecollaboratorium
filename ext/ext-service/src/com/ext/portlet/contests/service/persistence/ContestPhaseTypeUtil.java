package com.ext.portlet.contests.service.persistence;

public class ContestPhaseTypeUtil {
    private static ContestPhaseTypePersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.contests.model.ContestPhaseType contestPhaseType) {
        getPersistence().cacheResult(contestPhaseType);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.contests.model.ContestPhaseType> contestPhaseTypes) {
        getPersistence().cacheResult(contestPhaseTypes);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.contests.model.ContestPhaseType create(
        java.lang.Long id) {
        return getPersistence().create(id);
    }

    public static com.ext.portlet.contests.model.ContestPhaseType remove(
        java.lang.Long id)
        throws com.ext.portlet.contests.NoSuchContestPhaseTypeException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.contests.model.ContestPhaseType remove(
        com.ext.portlet.contests.model.ContestPhaseType contestPhaseType)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(contestPhaseType);
    }

    /**
     * @deprecated Use <code>update(ContestPhaseType contestPhaseType, boolean merge)</code>.
     */
    public static com.ext.portlet.contests.model.ContestPhaseType update(
        com.ext.portlet.contests.model.ContestPhaseType contestPhaseType)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(contestPhaseType);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                contestPhaseType the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when contestPhaseType is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.contests.model.ContestPhaseType update(
        com.ext.portlet.contests.model.ContestPhaseType contestPhaseType,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(contestPhaseType, merge);
    }

    public static com.ext.portlet.contests.model.ContestPhaseType updateImpl(
        com.ext.portlet.contests.model.ContestPhaseType contestPhaseType,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(contestPhaseType, merge);
    }

    public static com.ext.portlet.contests.model.ContestPhaseType findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.contests.NoSuchContestPhaseTypeException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    public static com.ext.portlet.contests.model.ContestPhaseType fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
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

    public static java.util.List<com.ext.portlet.contests.model.ContestPhaseType> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestPhaseType> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestPhaseType> findAll(
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

    public static ContestPhaseTypePersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ContestPhaseTypePersistence persistence) {
        _persistence = persistence;
    }
}
