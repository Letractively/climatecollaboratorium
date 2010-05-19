/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service;


/**
 * <a href="MessagingIgnoredRecipientsLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.mass_messaging.service.MessagingIgnoredRecipientsLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.service.MessagingIgnoredRecipientsLocalService
 *
 */
public class MessagingIgnoredRecipientsLocalServiceUtil {
    private static MessagingIgnoredRecipientsLocalService _service;

    public static com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients addMessagingIgnoredRecipients(
        com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients messagingIgnoredRecipients)
        throws com.liferay.portal.SystemException {
        return getService()
                   .addMessagingIgnoredRecipients(messagingIgnoredRecipients);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients createMessagingIgnoredRecipients(
        java.lang.Long ignoredRecipientId) {
        return getService().createMessagingIgnoredRecipients(ignoredRecipientId);
    }

    public static void deleteMessagingIgnoredRecipients(
        java.lang.Long ignoredRecipientId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteMessagingIgnoredRecipients(ignoredRecipientId);
    }

    public static void deleteMessagingIgnoredRecipients(
        com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients messagingIgnoredRecipients)
        throws com.liferay.portal.SystemException {
        getService().deleteMessagingIgnoredRecipients(messagingIgnoredRecipients);
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

    public static com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients getMessagingIgnoredRecipients(
        java.lang.Long ignoredRecipientId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getMessagingIgnoredRecipients(ignoredRecipientId);
    }

    public static java.util.List<com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients> getMessagingIgnoredRecipientses(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getMessagingIgnoredRecipientses(start, end);
    }

    public static int getMessagingIgnoredRecipientsesCount()
        throws com.liferay.portal.SystemException {
        return getService().getMessagingIgnoredRecipientsesCount();
    }

    public static com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients updateMessagingIgnoredRecipients(
        com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients messagingIgnoredRecipients)
        throws com.liferay.portal.SystemException {
        return getService()
                   .updateMessagingIgnoredRecipients(messagingIgnoredRecipients);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients updateMessagingIgnoredRecipients(
        com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients messagingIgnoredRecipients,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateMessagingIgnoredRecipients(messagingIgnoredRecipients,
            merge);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients findByUserId(
        java.lang.Long userId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.SystemException {
        return getService().findByUserId(userId);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients findByEmail(
        java.lang.String email)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.SystemException {
        return getService().findByEmail(email);
    }

    public static MessagingIgnoredRecipientsLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "MessagingIgnoredRecipientsLocalService is not set");
        }

        return _service;
    }

    public void setService(MessagingIgnoredRecipientsLocalService service) {
        _service = service;
    }
}
