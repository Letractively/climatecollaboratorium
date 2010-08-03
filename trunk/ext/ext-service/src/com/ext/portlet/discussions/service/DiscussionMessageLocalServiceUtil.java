package com.ext.portlet.discussions.service;


/**
 * <a href="DiscussionMessageLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.discussions.service.DiscussionMessageLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.service.DiscussionMessageLocalService
 *
 */
public class DiscussionMessageLocalServiceUtil {
    private static DiscussionMessageLocalService _service;

    public static com.ext.portlet.discussions.model.DiscussionMessage addDiscussionMessage(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.SystemException {
        return getService().addDiscussionMessage(discussionMessage);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage createDiscussionMessage(
        java.lang.Long pk) {
        return getService().createDiscussionMessage(pk);
    }

    public static void deleteDiscussionMessage(java.lang.Long pk)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteDiscussionMessage(pk);
    }

    public static void deleteDiscussionMessage(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.SystemException {
        getService().deleteDiscussionMessage(discussionMessage);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage getDiscussionMessage(
        java.lang.Long pk)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getDiscussionMessage(pk);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> getDiscussionMessages(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getDiscussionMessages(start, end);
    }

    public static int getDiscussionMessagesCount()
        throws com.liferay.portal.SystemException {
        return getService().getDiscussionMessagesCount();
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage updateDiscussionMessage(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.SystemException {
        return getService().updateDiscussionMessage(discussionMessage);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage updateDiscussionMessage(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateDiscussionMessage(discussionMessage, merge);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> getThreadsByCategory(
        java.lang.Long categoryId) throws com.liferay.portal.SystemException {
        return getService().getThreadsByCategory(categoryId);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> getThreadMessages(
        java.lang.Long threadId) throws com.liferay.portal.SystemException {
        return getService().getThreadMessages(threadId);
    }

    public static int getThreadMessagesCount(java.lang.Long threadId)
        throws com.liferay.portal.SystemException {
        return getService().getThreadMessagesCount(threadId);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage getThreadByThreadId(
        java.lang.Long threadId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        return getService().getThreadByThreadId(threadId);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage addThread(
        java.lang.Long categoryGroupId, java.lang.Long categoryId,
        java.lang.String subject, java.lang.String body,
        com.liferay.portal.model.User author)
        throws com.liferay.portal.SystemException {
        return getService()
                   .addThread(categoryGroupId, categoryId, subject, body, author);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage addMessage(
        java.lang.Long categoryGroupId, java.lang.Long categoryId,
        java.lang.Long threadId, java.lang.String subject,
        java.lang.String body, com.liferay.portal.model.User author)
        throws com.liferay.portal.SystemException {
        return getService()
                   .addMessage(categoryGroupId, categoryId, threadId, subject,
            body, author);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> search(
        java.lang.String query, java.lang.Long categoryGroupId)
        throws com.liferay.portal.SystemException {
        return getService().search(query, categoryGroupId);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage getMessageByMessageId(
        java.lang.Long messageId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException {
        return getService().getMessageByMessageId(messageId);
    }

    public static DiscussionMessageLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "DiscussionMessageLocalService is not set");
        }

        return _service;
    }

    public void setService(DiscussionMessageLocalService service) {
        _service = service;
    }
}
