/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service;


/**
 * <a href="MessagingMessageRecipientLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.mass_messaging.service.MessagingMessageRecipientLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.service.MessagingMessageRecipientLocalService
 *
 */
public class MessagingMessageRecipientLocalServiceUtil {
    private static MessagingMessageRecipientLocalService _service;

    public static com.ext.portlet.mass_messaging.model.MessagingMessageRecipient addMessagingMessageRecipient(
        com.ext.portlet.mass_messaging.model.MessagingMessageRecipient messagingMessageRecipient)
        throws com.liferay.portal.SystemException {
        return getService()
                   .addMessagingMessageRecipient(messagingMessageRecipient);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageRecipient createMessagingMessageRecipient(
        java.lang.Long recipientId) {
        return getService().createMessagingMessageRecipient(recipientId);
    }

    public static void deleteMessagingMessageRecipient(
        java.lang.Long recipientId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteMessagingMessageRecipient(recipientId);
    }

    public static void deleteMessagingMessageRecipient(
        com.ext.portlet.mass_messaging.model.MessagingMessageRecipient messagingMessageRecipient)
        throws com.liferay.portal.SystemException {
        getService().deleteMessagingMessageRecipient(messagingMessageRecipient);
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

    public static com.ext.portlet.mass_messaging.model.MessagingMessageRecipient getMessagingMessageRecipient(
        java.lang.Long recipientId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getMessagingMessageRecipient(recipientId);
    }

    public static java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageRecipient> getMessagingMessageRecipients(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getMessagingMessageRecipients(start, end);
    }

    public static int getMessagingMessageRecipientsCount()
        throws com.liferay.portal.SystemException {
        return getService().getMessagingMessageRecipientsCount();
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageRecipient updateMessagingMessageRecipient(
        com.ext.portlet.mass_messaging.model.MessagingMessageRecipient messagingMessageRecipient)
        throws com.liferay.portal.SystemException {
        return getService()
                   .updateMessagingMessageRecipient(messagingMessageRecipient);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageRecipient updateMessagingMessageRecipient(
        com.ext.portlet.mass_messaging.model.MessagingMessageRecipient messagingMessageRecipient,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateMessagingMessageRecipient(messagingMessageRecipient,
            merge);
    }

    public static MessagingMessageRecipientLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "MessagingMessageRecipientLocalService is not set");
        }

        return _service;
    }

    public void setService(MessagingMessageRecipientLocalService service) {
        _service = service;
    }
}
