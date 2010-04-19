package com.ext.portlet.debaterevision.service.persistence;

public class DebateItemVoteUtil {
    private static DebateItemVotePersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.debaterevision.model.DebateItemVote debateItemVote) {
        getPersistence().cacheResult(debateItemVote);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> debateItemVotes) {
        getPersistence().cacheResult(debateItemVotes);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVote create(
        java.lang.Long debateItemVoteId) {
        return getPersistence().create(debateItemVoteId);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVote remove(
        java.lang.Long debateItemVoteId)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(debateItemVoteId);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVote remove(
        com.ext.portlet.debaterevision.model.DebateItemVote debateItemVote)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(debateItemVote);
    }

    /**
     * @deprecated Use <code>update(DebateItemVote debateItemVote, boolean merge)</code>.
     */
    public static com.ext.portlet.debaterevision.model.DebateItemVote update(
        com.ext.portlet.debaterevision.model.DebateItemVote debateItemVote)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(debateItemVote);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateItemVote the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateItemVote is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.debaterevision.model.DebateItemVote update(
        com.ext.portlet.debaterevision.model.DebateItemVote debateItemVote,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(debateItemVote, merge);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVote updateImpl(
        com.ext.portlet.debaterevision.model.DebateItemVote debateItemVote,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(debateItemVote, merge);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVote findByPrimaryKey(
        java.lang.Long debateItemVoteId)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(debateItemVoteId);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVote fetchByPrimaryKey(
        java.lang.Long debateItemVoteId)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(debateItemVoteId);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findByDebateItemId(
        java.lang.Long debateItemId) throws com.liferay.portal.SystemException {
        return getPersistence().findByDebateItemId(debateItemId);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findByDebateItemId(
        java.lang.Long debateItemId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByDebateItemId(debateItemId, start, end);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findByDebateItemId(
        java.lang.Long debateItemId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByDebateItemId(debateItemId, start, end, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVote findByDebateItemId_First(
        java.lang.Long debateItemId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException {
        return getPersistence().findByDebateItemId_First(debateItemId, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVote findByDebateItemId_Last(
        java.lang.Long debateItemId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException {
        return getPersistence().findByDebateItemId_Last(debateItemId, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVote[] findByDebateItemId_PrevAndNext(
        java.lang.Long debateItemVoteId, java.lang.Long debateItemId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByDebateItemId_PrevAndNext(debateItemVoteId,
            debateItemId, obc);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findByUserId(
        java.lang.Long userId) throws com.liferay.portal.SystemException {
        return getPersistence().findByUserId(userId);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findByUserId(
        java.lang.Long userId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByUserId(userId, start, end);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findByUserId(
        java.lang.Long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByUserId(userId, start, end, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVote findByUserId_First(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException {
        return getPersistence().findByUserId_First(userId, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVote findByUserId_Last(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException {
        return getPersistence().findByUserId_Last(userId, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVote[] findByUserId_PrevAndNext(
        java.lang.Long debateItemVoteId, java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByUserId_PrevAndNext(debateItemVoteId, userId, obc);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findByDebateItemIdUserId(
        java.lang.Long debateItemId, java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByDebateItemIdUserId(debateItemId, userId);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findByDebateItemIdUserId(
        java.lang.Long debateItemId, java.lang.Long userId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByDebateItemIdUserId(debateItemId, userId, start, end);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findByDebateItemIdUserId(
        java.lang.Long debateItemId, java.lang.Long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByDebateItemIdUserId(debateItemId, userId, start, end,
            obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVote findByDebateItemIdUserId_First(
        java.lang.Long debateItemId, java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByDebateItemIdUserId_First(debateItemId, userId, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVote findByDebateItemIdUserId_Last(
        java.lang.Long debateItemId, java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByDebateItemIdUserId_Last(debateItemId, userId, obc);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVote[] findByDebateItemIdUserId_PrevAndNext(
        java.lang.Long debateItemVoteId, java.lang.Long debateItemId,
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByDebateItemIdUserId_PrevAndNext(debateItemVoteId,
            debateItemId, userId, obc);
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

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByDebateItemId(java.lang.Long debateItemId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByDebateItemId(debateItemId);
    }

    public static void removeByUserId(java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByUserId(userId);
    }

    public static void removeByDebateItemIdUserId(java.lang.Long debateItemId,
        java.lang.Long userId) throws com.liferay.portal.SystemException {
        getPersistence().removeByDebateItemIdUserId(debateItemId, userId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByDebateItemId(java.lang.Long debateItemId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByDebateItemId(debateItemId);
    }

    public static int countByUserId(java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByUserId(userId);
    }

    public static int countByDebateItemIdUserId(java.lang.Long debateItemId,
        java.lang.Long userId) throws com.liferay.portal.SystemException {
        return getPersistence().countByDebateItemIdUserId(debateItemId, userId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static DebateItemVotePersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(DebateItemVotePersistence persistence) {
        _persistence = persistence;
    }
}
