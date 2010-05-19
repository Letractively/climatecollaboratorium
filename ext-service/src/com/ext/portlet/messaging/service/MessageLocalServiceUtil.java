package com.ext.portlet.messaging.service;


/**
 * <a href="MessageLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.messaging.service.MessageLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.messaging.service.MessageLocalService
 *
 */
public class MessageLocalServiceUtil {
    private static MessageLocalService _service;

    public static com.ext.portlet.messaging.model.Message addMessage(
        com.ext.portlet.messaging.model.Message message)
        throws com.liferay.portal.SystemException {
        return getService().addMessage(message);
    }

    public static com.ext.portlet.messaging.model.Message createMessage(
        java.lang.Long messageId) {
        return getService().createMessage(messageId);
    }

    public static void deleteMessage(java.lang.Long messageId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteMessage(messageId);
    }

    public static void deleteMessage(
        com.ext.portlet.messaging.model.Message message)
        throws com.liferay.portal.SystemException {
        getService().deleteMessage(message);
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

    public static com.ext.portlet.messaging.model.Message getMessage(
        java.lang.Long messageId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getMessage(messageId);
    }

    public static java.util.List<com.ext.portlet.messaging.model.Message> getMessages(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getMessages(start, end);
    }

    public static int getMessagesCount()
        throws com.liferay.portal.SystemException {
        return getService().getMessagesCount();
    }

    public static com.ext.portlet.messaging.model.Message updateMessage(
        com.ext.portlet.messaging.model.Message message)
        throws com.liferay.portal.SystemException {
        return getService().updateMessage(message);
    }

    public static com.ext.portlet.messaging.model.Message updateMessage(
        com.ext.portlet.messaging.model.Message message, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updateMessage(message, merge);
    }

    public static int countSentMessage(long userid)
        throws com.liferay.portal.SystemException {
        return getService().countSentMessage(userid);
    }

    public static java.util.List<com.ext.portlet.messaging.model.Message> findSentMessages(
        long userid, int pagerstart, int pagerend)
        throws com.liferay.portal.SystemException {
        return getService().findSentMessages(userid, pagerstart, pagerend);
    }

    public static MessageLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("MessageLocalService is not set");
        }

        return _service;
    }

    public void setService(MessageLocalService service) {
        _service = service;
    }
}
