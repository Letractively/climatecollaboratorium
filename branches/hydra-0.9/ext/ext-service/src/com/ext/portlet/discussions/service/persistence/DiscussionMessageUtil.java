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
        java.lang.Long messageId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        return getPersistence().findBySingleThreadId(messageId);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage fetchBySingleThreadId(
        java.lang.Long messageId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchBySingleThreadId(messageId);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage fetchBySingleThreadId(
        java.lang.Long messageId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .fetchBySingleThreadId(messageId, retrieveFromCache);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findBySubjectLike(
        java.lang.String subject, java.lang.Long categoryGroupId)
        throws com.liferay.portal.SystemException {
        return getPersistence().findBySubjectLike(subject, categoryGroupId);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findBySubjectLike(
        java.lang.String subject, java.lang.Long categoryGroupId, int start,
        int end) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findBySubjectLike(subject, categoryGroupId, start, end);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findBySubjectLike(
        java.lang.String subject, java.lang.Long categoryGroupId, int start,
        int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findBySubjectLike(subject, categoryGroupId, start, end, obc);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage findBySubjectLike_First(
        java.lang.String subject, java.lang.Long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findBySubjectLike_First(subject, categoryGroupId, obc);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage findBySubjectLike_Last(
        java.lang.String subject, java.lang.Long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findBySubjectLike_Last(subject, categoryGroupId, obc);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage[] findBySubjectLike_PrevAndNext(
        java.lang.Long pk, java.lang.String subject,
        java.lang.Long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findBySubjectLike_PrevAndNext(pk, subject, categoryGroupId,
            obc);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByBodyLike(
        java.lang.String body, java.lang.Long categoryGroupId)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByBodyLike(body, categoryGroupId);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByBodyLike(
        java.lang.String body, java.lang.Long categoryGroupId, int start,
        int end) throws com.liferay.portal.SystemException {
        return getPersistence().findByBodyLike(body, categoryGroupId, start, end);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByBodyLike(
        java.lang.String body, java.lang.Long categoryGroupId, int start,
        int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByBodyLike(body, categoryGroupId, start, end, obc);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage findByBodyLike_First(
        java.lang.String body, java.lang.Long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        return getPersistence().findByBodyLike_First(body, categoryGroupId, obc);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage findByBodyLike_Last(
        java.lang.String body, java.lang.Long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        return getPersistence().findByBodyLike_Last(body, categoryGroupId, obc);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage[] findByBodyLike_PrevAndNext(
        java.lang.Long pk, java.lang.String body,
        java.lang.Long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByBodyLike_PrevAndNext(pk, body, categoryGroupId, obc);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage findByMessageId(
        java.lang.Long messageId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        return getPersistence().findByMessageId(messageId);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage fetchByMessageId(
        java.lang.Long messageId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByMessageId(messageId);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage fetchByMessageId(
        java.lang.Long messageId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByMessageId(messageId, retrieveFromCache);
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

    public static void removeBySingleThreadId(java.lang.Long messageId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        getPersistence().removeBySingleThreadId(messageId);
    }

    public static void removeBySubjectLike(java.lang.String subject,
        java.lang.Long categoryGroupId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeBySubjectLike(subject, categoryGroupId);
    }

    public static void removeByBodyLike(java.lang.String body,
        java.lang.Long categoryGroupId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByBodyLike(body, categoryGroupId);
    }

    public static void removeByMessageId(java.lang.Long messageId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        getPersistence().removeByMessageId(messageId);
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

    public static int countBySingleThreadId(java.lang.Long messageId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countBySingleThreadId(messageId);
    }

    public static int countBySubjectLike(java.lang.String subject,
        java.lang.Long categoryGroupId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countBySubjectLike(subject, categoryGroupId);
    }

    public static int countByBodyLike(java.lang.String body,
        java.lang.Long categoryGroupId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByBodyLike(body, categoryGroupId);
    }

    public static int countByMessageId(java.lang.Long messageId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByMessageId(messageId);
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
