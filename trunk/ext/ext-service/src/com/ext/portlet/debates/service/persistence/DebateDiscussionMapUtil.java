package com.ext.portlet.debates.service.persistence;

public class DebateDiscussionMapUtil {
    private static DebateDiscussionMapPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.debates.model.DebateDiscussionMap debateDiscussionMap) {
        getPersistence().cacheResult(debateDiscussionMap);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.debates.model.DebateDiscussionMap> debateDiscussionMaps) {
        getPersistence().cacheResult(debateDiscussionMaps);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.debates.model.DebateDiscussionMap create(
        java.lang.Long debateMessageId) {
        return getPersistence().create(debateMessageId);
    }

    public static com.ext.portlet.debates.model.DebateDiscussionMap remove(
        java.lang.Long debateMessageId)
        throws com.ext.portlet.debates.NoSuchDebateDiscussionMapException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(debateMessageId);
    }

    public static com.ext.portlet.debates.model.DebateDiscussionMap remove(
        com.ext.portlet.debates.model.DebateDiscussionMap debateDiscussionMap)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(debateDiscussionMap);
    }

    /**
     * @deprecated Use <code>update(DebateDiscussionMap debateDiscussionMap, boolean merge)</code>.
     */
    public static com.ext.portlet.debates.model.DebateDiscussionMap update(
        com.ext.portlet.debates.model.DebateDiscussionMap debateDiscussionMap)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(debateDiscussionMap);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateDiscussionMap the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateDiscussionMap is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.debates.model.DebateDiscussionMap update(
        com.ext.portlet.debates.model.DebateDiscussionMap debateDiscussionMap,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(debateDiscussionMap, merge);
    }

    public static com.ext.portlet.debates.model.DebateDiscussionMap updateImpl(
        com.ext.portlet.debates.model.DebateDiscussionMap debateDiscussionMap,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(debateDiscussionMap, merge);
    }

    public static com.ext.portlet.debates.model.DebateDiscussionMap findByPrimaryKey(
        java.lang.Long debateMessageId)
        throws com.ext.portlet.debates.NoSuchDebateDiscussionMapException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(debateMessageId);
    }

    public static com.ext.portlet.debates.model.DebateDiscussionMap fetchByPrimaryKey(
        java.lang.Long debateMessageId)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(debateMessageId);
    }

    public static com.ext.portlet.debates.model.DebateDiscussionMap findByDebateMessageFromDiscussion(
        java.lang.Long debateMessageId)
        throws com.ext.portlet.debates.NoSuchDebateDiscussionMapException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByDebateMessageFromDiscussion(debateMessageId);
    }

    public static com.ext.portlet.debates.model.DebateDiscussionMap fetchByDebateMessageFromDiscussion(
        java.lang.Long debateMessageId)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByDebateMessageFromDiscussion(debateMessageId);
    }

    public static com.ext.portlet.debates.model.DebateDiscussionMap fetchByDebateMessageFromDiscussion(
        java.lang.Long debateMessageId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchByDebateMessageFromDiscussion(debateMessageId,
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

    public static java.util.List<com.ext.portlet.debates.model.DebateDiscussionMap> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.debates.model.DebateDiscussionMap> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.debates.model.DebateDiscussionMap> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByDebateMessageFromDiscussion(
        java.lang.Long debateMessageId)
        throws com.ext.portlet.debates.NoSuchDebateDiscussionMapException,
            com.liferay.portal.SystemException {
        getPersistence().removeByDebateMessageFromDiscussion(debateMessageId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByDebateMessageFromDiscussion(
        java.lang.Long debateMessageId)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .countByDebateMessageFromDiscussion(debateMessageId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static DebateDiscussionMapPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(DebateDiscussionMapPersistence persistence) {
        _persistence = persistence;
    }
}
