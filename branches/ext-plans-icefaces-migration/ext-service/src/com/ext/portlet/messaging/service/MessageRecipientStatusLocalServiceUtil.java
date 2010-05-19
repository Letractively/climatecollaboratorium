package com.ext.portlet.messaging.service;


/**
 * <a href="MessageRecipientStatusLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.messaging.service.MessageRecipientStatusLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.messaging.service.MessageRecipientStatusLocalService
 *
 */
public class MessageRecipientStatusLocalServiceUtil {
    private static MessageRecipientStatusLocalService _service;

    public static com.ext.portlet.messaging.model.MessageRecipientStatus addMessageRecipientStatus(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus)
        throws com.liferay.portal.SystemException {
        return getService().addMessageRecipientStatus(messageRecipientStatus);
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus createMessageRecipientStatus(
        java.lang.Long messageRecipientId) {
        return getService().createMessageRecipientStatus(messageRecipientId);
    }

    public static void deleteMessageRecipientStatus(
        java.lang.Long messageRecipientId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteMessageRecipientStatus(messageRecipientId);
    }

    public static void deleteMessageRecipientStatus(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus)
        throws com.liferay.portal.SystemException {
        getService().deleteMessageRecipientStatus(messageRecipientStatus);
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

    public static com.ext.portlet.messaging.model.MessageRecipientStatus getMessageRecipientStatus(
        java.lang.Long messageRecipientId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getMessageRecipientStatus(messageRecipientId);
    }

    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> getMessageRecipientStatuses(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getMessageRecipientStatuses(start, end);
    }

    public static int getMessageRecipientStatusesCount()
        throws com.liferay.portal.SystemException {
        return getService().getMessageRecipientStatusesCount();
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus updateMessageRecipientStatus(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus)
        throws com.liferay.portal.SystemException {
        return getService().updateMessageRecipientStatus(messageRecipientStatus);
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus updateMessageRecipientStatus(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateMessageRecipientStatus(messageRecipientStatus, merge);
    }

    public static int countByMessageId(long messageId)
        throws com.liferay.portal.SystemException {
        return getService().countByMessageId(messageId);
    }

    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByMessageId(
        long messageId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getService().findByMessageId(messageId, start, end);
    }

    public static int countArchivedMessagesForUser(long userid)
        throws com.liferay.portal.SystemException {
        return getService().countArchivedMessagesForUser(userid);
    }

    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findArchivedMessagesForUser(
        long userid, int start, int end)
        throws com.liferay.portal.SystemException {
        return getService().findArchivedMessagesForUser(userid, start, end);
    }

    public static int countInboxMessagesForUser(long userid)
        throws com.liferay.portal.SystemException {
        return getService().countInboxMessagesForUser(userid);
    }

    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findInboxMessagesForUser(
        long userid, int start, int end)
        throws com.liferay.portal.SystemException {
        return getService().findInboxMessagesForUser(userid, start, end);
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus findByMessageRecipient(
        long userid, long messageid)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException {
        return getService().findByMessageRecipient(userid, messageid);
    }

    public static MessageRecipientStatusLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "MessageRecipientStatusLocalService is not set");
        }

        return _service;
    }

    public void setService(MessageRecipientStatusLocalService service) {
        _service = service;
    }
}
