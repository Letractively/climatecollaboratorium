package com.ext.portlet.debaterevision.service.persistence;

public class DebateItemVoteStatsUtil {
    private static DebateItemVoteStatsPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.debaterevision.model.DebateItemVoteStats debateItemVoteStats) {
        getPersistence().cacheResult(debateItemVoteStats);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.debaterevision.model.DebateItemVoteStats> debateItemVoteStatses) {
        getPersistence().cacheResult(debateItemVoteStatses);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVoteStats create(
        java.lang.Long debateItemVotesStats) {
        return getPersistence().create(debateItemVotesStats);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVoteStats remove(
        java.lang.Long debateItemVotesStats)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteStatsException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(debateItemVotesStats);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVoteStats remove(
        com.ext.portlet.debaterevision.model.DebateItemVoteStats debateItemVoteStats)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(debateItemVoteStats);
    }

    /**
     * @deprecated Use <code>update(DebateItemVoteStats debateItemVoteStats, boolean merge)</code>.
     */
    public static com.ext.portlet.debaterevision.model.DebateItemVoteStats update(
        com.ext.portlet.debaterevision.model.DebateItemVoteStats debateItemVoteStats)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(debateItemVoteStats);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateItemVoteStats the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateItemVoteStats is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.debaterevision.model.DebateItemVoteStats update(
        com.ext.portlet.debaterevision.model.DebateItemVoteStats debateItemVoteStats,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(debateItemVoteStats, merge);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVoteStats updateImpl(
        com.ext.portlet.debaterevision.model.DebateItemVoteStats debateItemVoteStats,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(debateItemVoteStats, merge);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVoteStats findByPrimaryKey(
        java.lang.Long debateItemVotesStats)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteStatsException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(debateItemVotesStats);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVoteStats fetchByPrimaryKey(
        java.lang.Long debateItemVotesStats)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(debateItemVotesStats);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVoteStats findByDebateItemId(
        java.lang.Long debateItemId)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteStatsException,
            com.liferay.portal.SystemException {
        return getPersistence().findByDebateItemId(debateItemId);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVoteStats fetchByDebateItemId(
        java.lang.Long debateItemId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByDebateItemId(debateItemId);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVoteStats fetchByDebateItemId(
        java.lang.Long debateItemId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByDebateItemId(debateItemId, retrieveFromCache);
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

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemVoteStats> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemVoteStats> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemVoteStats> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByDebateItemId(java.lang.Long debateItemId)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteStatsException,
            com.liferay.portal.SystemException {
        getPersistence().removeByDebateItemId(debateItemId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByDebateItemId(java.lang.Long debateItemId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByDebateItemId(debateItemId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static DebateItemVoteStatsPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(DebateItemVoteStatsPersistence persistence) {
        _persistence = persistence;
    }
}
