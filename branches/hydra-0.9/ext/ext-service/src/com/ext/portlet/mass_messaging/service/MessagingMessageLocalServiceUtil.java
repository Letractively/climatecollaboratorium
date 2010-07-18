/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service;


/**
 * <a href="MessagingMessageLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.mass_messaging.service.MessagingMessageLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.service.MessagingMessageLocalService
 *
 */
public class MessagingMessageLocalServiceUtil {
    private static MessagingMessageLocalService _service;

    public static com.ext.portlet.mass_messaging.model.MessagingMessage addMessagingMessage(
        com.ext.portlet.mass_messaging.model.MessagingMessage messagingMessage)
        throws com.liferay.portal.SystemException {
        return getService().addMessagingMessage(messagingMessage);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessage createMessagingMessage(
        java.lang.Long messageId) {
        return getService().createMessagingMessage(messageId);
    }

    public static void deleteMessagingMessage(java.lang.Long messageId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteMessagingMessage(messageId);
    }

    public static void deleteMessagingMessage(
        com.ext.portlet.mass_messaging.model.MessagingMessage messagingMessage)
        throws com.liferay.portal.SystemException {
        getService().deleteMessagingMessage(messagingMessage);
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

    public static com.ext.portlet.mass_messaging.model.MessagingMessage getMessagingMessage(
        java.lang.Long messageId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getMessagingMessage(messageId);
    }

    public static java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessage> getMessagingMessages(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getMessagingMessages(start, end);
    }

    public static int getMessagingMessagesCount()
        throws com.liferay.portal.SystemException {
        return getService().getMessagingMessagesCount();
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessage updateMessagingMessage(
        com.ext.portlet.mass_messaging.model.MessagingMessage messagingMessage)
        throws com.liferay.portal.SystemException {
        return getService().updateMessagingMessage(messagingMessage);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessage updateMessagingMessage(
        com.ext.portlet.mass_messaging.model.MessagingMessage messagingMessage,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateMessagingMessage(messagingMessage, merge);
    }

    public static MessagingMessageLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "MessagingMessageLocalService is not set");
        }

        return _service;
    }

    public void setService(MessagingMessageLocalService service) {
        _service = service;
    }
}
