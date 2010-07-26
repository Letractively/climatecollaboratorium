package com.ext.portlet.discussions.service.persistence;

public class DiscussionMessageUtil {
    private static DiscussionMessagePersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage) {
        getPersistence().cacheResult(discussionMessage);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> discussionMessages) {
        getPersistence().cacheResult(discussionMessages);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage create(
        java.lang.Long pk) {
        return getPersistence().create(pk);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage remove(
        java.lang.Long pk)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(pk);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage remove(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(discussionMessage);
    }

    /**
     * @deprecated Use <code>update(DiscussionMessage discussionMessage, boolean merge)</code>.
     */
    public static com.ext.portlet.discussions.model.DiscussionMessage update(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(discussionMessage);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                discussionMessage the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when discussionMessage is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.discussions.model.DiscussionMessage update(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(discussionMessage, merge);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage updateImpl(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(discussionMessage, merge);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage findByPrimaryKey(
        java.lang.Long pk)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(pk);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage fetchByPrimaryKey(
        java.lang.Long pk) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(pk);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByCategoryIdThreadId(
        java.lang.Long categoryId, java.lang.Long threadId)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByCategoryIdThreadId(categoryId, threadId);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByCategoryIdThreadId(
        java.lang.Long categoryId, java.lang.Long threadId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByCategoryIdThreadId(categoryId, threadId, start, end);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByCategoryIdThreadId(
        java.lang.Long categoryId, java.lang.Long threadId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByCategoryIdThreadId(categoryId, threadId, start, end,
            obc);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage findByCategoryIdThreadId_First(
        java.lang.Long categoryId, java.lang.Long threadId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByCategoryIdThreadId_First(categoryId, threadId, obc);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage findByCategoryIdThreadId_Last(
        java.lang.Long categoryId, java.lang.Long threadId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByCategoryIdThreadId_Last(categoryId, threadId, obc);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage[] findByCategoryIdThreadId_PrevAndNext(
        java.lang.Long pk, java.lang.Long categoryId, java.lang.Long threadId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByCategoryIdThreadId_PrevAndNext(pk, categoryId,
            threadId, obc);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByThreadId(
        java.lang.Long threadId) throws com.liferay.portal.SystemException {
        return getPersistence().findByThreadId(threadId);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByThreadId(
        java.lang.Long threadId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByThreadId(threadId, start, end);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByThreadId(
        java.lang.Long threadId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByThreadId(threadId, start, end, obc);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage findByThreadId_First(
        java.lang.Long threadId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        return getPersistence().findByThreadId_First(threadId, obc);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage findByThreadId_Last(
        java.lang.Long threadId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        return getPersistence().findByThreadId_Last(threadId, obc);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage[] findByThreadId_PrevAndNext(
        java.lang.Long pk, java.lang.Long threadId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        return getPersistence().findByThreadId_PrevAndNext(pk, threadId, obc);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage findBySingleThreadId(
        java.lang.Long threadId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        return getPersistence().findBySingleThreadId(threadId);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage fetchBySingleThreadId(
        java.lang.Long threadId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchBySingleThreadId(threadId);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage fetchBySingleThreadId(
        java.lang.Long threadId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchBySingleThreadId(threadId, retrieveFromCache);
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

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByCategoryIdThreadId(java.lang.Long categoryId,
        java.lang.Long threadId) throws com.liferay.portal.SystemException {
        getPersistence().removeByCategoryIdThreadId(categoryId, threadId);
    }

    public static void removeByThreadId(java.lang.Long threadId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByThreadId(threadId);
    }

    public static void removeBySingleThreadId(java.lang.Long threadId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        getPersistence().removeBySingleThreadId(threadId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByCategoryIdThreadId(java.lang.Long categoryId,
        java.lang.Long threadId) throws com.liferay.portal.SystemException {
        return getPersistence().countByCategoryIdThreadId(categoryId, threadId);
    }

    public static int countByThreadId(java.lang.Long threadId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByThreadId(threadId);
    }

    public static int countBySingleThreadId(java.lang.Long threadId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countBySingleThreadId(threadId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static DiscussionMessagePersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(DiscussionMessagePersistence persistence) {
        _persistence = persistence;
    }
}
