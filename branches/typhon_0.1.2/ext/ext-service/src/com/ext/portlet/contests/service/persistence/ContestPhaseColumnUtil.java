package com.ext.portlet.contests.service.persistence;

public class ContestPhaseColumnUtil {
    private static ContestPhaseColumnPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.contests.model.ContestPhaseColumn contestPhaseColumn) {
        getPersistence().cacheResult(contestPhaseColumn);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.contests.model.ContestPhaseColumn> contestPhaseColumns) {
        getPersistence().cacheResult(contestPhaseColumns);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.contests.model.ContestPhaseColumn create(
        java.lang.Long id) {
        return getPersistence().create(id);
    }

    public static com.ext.portlet.contests.model.ContestPhaseColumn remove(
        java.lang.Long id)
        throws com.ext.portlet.contests.NoSuchContestPhaseColumnException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.contests.model.ContestPhaseColumn remove(
        com.ext.portlet.contests.model.ContestPhaseColumn contestPhaseColumn)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(contestPhaseColumn);
    }

    /**
     * @deprecated Use <code>update(ContestPhaseColumn contestPhaseColumn, boolean merge)</code>.
     */
    public static com.ext.portlet.contests.model.ContestPhaseColumn update(
        com.ext.portlet.contests.model.ContestPhaseColumn contestPhaseColumn)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(contestPhaseColumn);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                contestPhaseColumn the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when contestPhaseColumn is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.contests.model.ContestPhaseColumn update(
        com.ext.portlet.contests.model.ContestPhaseColumn contestPhaseColumn,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(contestPhaseColumn, merge);
    }

    public static com.ext.portlet.contests.model.ContestPhaseColumn updateImpl(
        com.ext.portlet.contests.model.ContestPhaseColumn contestPhaseColumn,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(contestPhaseColumn, merge);
    }

    public static com.ext.portlet.contests.model.ContestPhaseColumn findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.contests.NoSuchContestPhaseColumnException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    public static com.ext.portlet.contests.model.ContestPhaseColumn fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestPhaseColumn> findByContestPhasePK(
        java.lang.Long ContestPhasePK)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByContestPhasePK(ContestPhasePK);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestPhaseColumn> findByContestPhasePK(
        java.lang.Long ContestPhasePK, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByContestPhasePK(ContestPhasePK, start, end);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestPhaseColumn> findByContestPhasePK(
        java.lang.Long ContestPhasePK, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByContestPhasePK(ContestPhasePK, start, end, obc);
    }

    public static com.ext.portlet.contests.model.ContestPhaseColumn findByContestPhasePK_First(
        java.lang.Long ContestPhasePK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestPhaseColumnException,
            com.liferay.portal.SystemException {
        return getPersistence().findByContestPhasePK_First(ContestPhasePK, obc);
    }

    public static com.ext.portlet.contests.model.ContestPhaseColumn findByContestPhasePK_Last(
        java.lang.Long ContestPhasePK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestPhaseColumnException,
            com.liferay.portal.SystemException {
        return getPersistence().findByContestPhasePK_Last(ContestPhasePK, obc);
    }

    public static com.ext.portlet.contests.model.ContestPhaseColumn[] findByContestPhasePK_PrevAndNext(
        java.lang.Long id, java.lang.Long ContestPhasePK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestPhaseColumnException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByContestPhasePK_PrevAndNext(id, ContestPhasePK, obc);
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

    public static java.util.List<com.ext.portlet.contests.model.ContestPhaseColumn> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestPhaseColumn> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestPhaseColumn> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByContestPhasePK(java.lang.Long ContestPhasePK)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByContestPhasePK(ContestPhasePK);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByContestPhasePK(java.lang.Long ContestPhasePK)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByContestPhasePK(ContestPhasePK);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static ContestPhaseColumnPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ContestPhaseColumnPersistence persistence) {
        _persistence = persistence;
    }
}
