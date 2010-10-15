package com.ext.portlet.contests.service.persistence;

public class ContestPhaseUtil {
    private static ContestPhasePersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.contests.model.ContestPhase contestPhase) {
        getPersistence().cacheResult(contestPhase);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.contests.model.ContestPhase> contestPhases) {
        getPersistence().cacheResult(contestPhases);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.contests.model.ContestPhase create(
        java.lang.Long ContestPhasePK) {
        return getPersistence().create(ContestPhasePK);
    }

    public static com.ext.portlet.contests.model.ContestPhase remove(
        java.lang.Long ContestPhasePK)
        throws com.ext.portlet.contests.NoSuchContestPhaseException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(ContestPhasePK);
    }

    public static com.ext.portlet.contests.model.ContestPhase remove(
        com.ext.portlet.contests.model.ContestPhase contestPhase)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(contestPhase);
    }

    /**
     * @deprecated Use <code>update(ContestPhase contestPhase, boolean merge)</code>.
     */
    public static com.ext.portlet.contests.model.ContestPhase update(
        com.ext.portlet.contests.model.ContestPhase contestPhase)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(contestPhase);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                contestPhase the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when contestPhase is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.contests.model.ContestPhase update(
        com.ext.portlet.contests.model.ContestPhase contestPhase, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(contestPhase, merge);
    }

    public static com.ext.portlet.contests.model.ContestPhase updateImpl(
        com.ext.portlet.contests.model.ContestPhase contestPhase, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(contestPhase, merge);
    }

    public static com.ext.portlet.contests.model.ContestPhase findByPrimaryKey(
        java.lang.Long ContestPhasePK)
        throws com.ext.portlet.contests.NoSuchContestPhaseException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(ContestPhasePK);
    }

    public static com.ext.portlet.contests.model.ContestPhase fetchByPrimaryKey(
        java.lang.Long ContestPhasePK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(ContestPhasePK);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestPhase> findByContestId(
        java.lang.Long ContestPK) throws com.liferay.portal.SystemException {
        return getPersistence().findByContestId(ContestPK);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestPhase> findByContestId(
        java.lang.Long ContestPK, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByContestId(ContestPK, start, end);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestPhase> findByContestId(
        java.lang.Long ContestPK, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByContestId(ContestPK, start, end, obc);
    }

    public static com.ext.portlet.contests.model.ContestPhase findByContestId_First(
        java.lang.Long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestPhaseException,
            com.liferay.portal.SystemException {
        return getPersistence().findByContestId_First(ContestPK, obc);
    }

    public static com.ext.portlet.contests.model.ContestPhase findByContestId_Last(
        java.lang.Long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestPhaseException,
            com.liferay.portal.SystemException {
        return getPersistence().findByContestId_Last(ContestPK, obc);
    }

    public static com.ext.portlet.contests.model.ContestPhase[] findByContestId_PrevAndNext(
        java.lang.Long ContestPhasePK, java.lang.Long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestPhaseException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByContestId_PrevAndNext(ContestPhasePK, ContestPK, obc);
    }

    public static com.ext.portlet.contests.model.ContestPhase findByContestIdActive(
        java.lang.Long ContestPK, java.lang.Boolean phaseActive)
        throws com.ext.portlet.contests.NoSuchContestPhaseException,
            com.liferay.portal.SystemException {
        return getPersistence().findByContestIdActive(ContestPK, phaseActive);
    }

    public static com.ext.portlet.contests.model.ContestPhase fetchByContestIdActive(
        java.lang.Long ContestPK, java.lang.Boolean phaseActive)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByContestIdActive(ContestPK, phaseActive);
    }

    public static com.ext.portlet.contests.model.ContestPhase fetchByContestIdActive(
        java.lang.Long ContestPK, java.lang.Boolean phaseActive,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByContestIdActive(ContestPK, phaseActive,
            retrieveFromCache);
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

    public static java.util.List<com.ext.portlet.contests.model.ContestPhase> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestPhase> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestPhase> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByContestId(java.lang.Long ContestPK)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByContestId(ContestPK);
    }

    public static void removeByContestIdActive(java.lang.Long ContestPK,
        java.lang.Boolean phaseActive)
        throws com.ext.portlet.contests.NoSuchContestPhaseException,
            com.liferay.portal.SystemException {
        getPersistence().removeByContestIdActive(ContestPK, phaseActive);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByContestId(java.lang.Long ContestPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByContestId(ContestPK);
    }

    public static int countByContestIdActive(java.lang.Long ContestPK,
        java.lang.Boolean phaseActive)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByContestIdActive(ContestPK, phaseActive);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static ContestPhasePersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ContestPhasePersistence persistence) {
        _persistence = persistence;
    }
}
