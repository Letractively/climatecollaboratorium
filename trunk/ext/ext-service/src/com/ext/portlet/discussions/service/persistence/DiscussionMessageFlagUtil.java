package com.ext.portlet.discussions.service.persistence;

public class DiscussionMessageFlagUtil {
    private static DiscussionMessageFlagPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.discussions.model.DiscussionMessageFlag discussionMessageFlag) {
        getPersistence().cacheResult(discussionMessageFlag);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.discussions.model.DiscussionMessageFlag> discussionMessageFlags) {
        getPersistence().cacheResult(discussionMessageFlags);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.discussions.model.DiscussionMessageFlag create(
        java.lang.Long pk) {
        return getPersistence().create(pk);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessageFlag remove(
        java.lang.Long pk)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageFlagException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(pk);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessageFlag remove(
        com.ext.portlet.discussions.model.DiscussionMessageFlag discussionMessageFlag)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(discussionMessageFlag);
    }

    /**
     * @deprecated Use <code>update(DiscussionMessageFlag discussionMessageFlag, boolean merge)</code>.
     */
    public static com.ext.portlet.discussions.model.DiscussionMessageFlag update(
        com.ext.portlet.discussions.model.DiscussionMessageFlag discussionMessageFlag)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(discussionMessageFlag);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                discussionMessageFlag the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when discussionMessageFlag is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.discussions.model.DiscussionMessageFlag update(
        com.ext.portlet.discussions.model.DiscussionMessageFlag discussionMessageFlag,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(discussionMessageFlag, merge);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessageFlag updateImpl(
        com.ext.portlet.discussions.model.DiscussionMessageFlag discussionMessageFlag,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(discussionMessageFlag, merge);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessageFlag findByPrimaryKey(
        java.lang.Long pk)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageFlagException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(pk);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessageFlag fetchByPrimaryKey(
        java.lang.Long pk) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(pk);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessageFlag> findByMessageId(
        java.lang.Long messageId) throws com.liferay.portal.SystemException {
        return getPersistence().findByMessageId(messageId);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessageFlag> findByMessageId(
        java.lang.Long messageId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByMessageId(messageId, start, end);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessageFlag> findByMessageId(
        java.lang.Long messageId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByMessageId(messageId, start, end, obc);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessageFlag findByMessageId_First(
        java.lang.Long messageId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageFlagException,
            com.liferay.portal.SystemException {
        return getPersistence().findByMessageId_First(messageId, obc);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessageFlag findByMessageId_Last(
        java.lang.Long messageId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageFlagException,
            com.liferay.portal.SystemException {
        return getPersistence().findByMessageId_Last(messageId, obc);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessageFlag[] findByMessageId_PrevAndNext(
        java.lang.Long pk, java.lang.Long messageId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageFlagException,
            com.liferay.portal.SystemException {
        return getPersistence().findByMessageId_PrevAndNext(pk, messageId, obc);
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

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessageFlag> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessageFlag> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessageFlag> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByMessageId(java.lang.Long messageId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByMessageId(messageId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByMessageId(java.lang.Long messageId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByMessageId(messageId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static DiscussionMessageFlagPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(DiscussionMessageFlagPersistence persistence) {
        _persistence = persistence;
    }
}
