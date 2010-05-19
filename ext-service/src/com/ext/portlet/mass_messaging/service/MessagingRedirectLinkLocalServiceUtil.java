/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service;


/**
 * <a href="MessagingRedirectLinkLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.mass_messaging.service.MessagingRedirectLinkLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.service.MessagingRedirectLinkLocalService
 *
 */
public class MessagingRedirectLinkLocalServiceUtil {
    private static MessagingRedirectLinkLocalService _service;

    public static com.ext.portlet.mass_messaging.model.MessagingRedirectLink addMessagingRedirectLink(
        com.ext.portlet.mass_messaging.model.MessagingRedirectLink messagingRedirectLink)
        throws com.liferay.portal.SystemException {
        return getService().addMessagingRedirectLink(messagingRedirectLink);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingRedirectLink createMessagingRedirectLink(
        java.lang.Long redirectId) {
        return getService().createMessagingRedirectLink(redirectId);
    }

    public static void deleteMessagingRedirectLink(java.lang.Long redirectId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteMessagingRedirectLink(redirectId);
    }

    public static void deleteMessagingRedirectLink(
        com.ext.portlet.mass_messaging.model.MessagingRedirectLink messagingRedirectLink)
        throws com.liferay.portal.SystemException {
        getService().deleteMessagingRedirectLink(messagingRedirectLink);
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

    public static com.ext.portlet.mass_messaging.model.MessagingRedirectLink getMessagingRedirectLink(
        java.lang.Long redirectId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getMessagingRedirectLink(redirectId);
    }

    public static java.util.List<com.ext.portlet.mass_messaging.model.MessagingRedirectLink> getMessagingRedirectLinks(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getMessagingRedirectLinks(start, end);
    }

    public static int getMessagingRedirectLinksCount()
        throws com.liferay.portal.SystemException {
        return getService().getMessagingRedirectLinksCount();
    }

    public static com.ext.portlet.mass_messaging.model.MessagingRedirectLink updateMessagingRedirectLink(
        com.ext.portlet.mass_messaging.model.MessagingRedirectLink messagingRedirectLink)
        throws com.liferay.portal.SystemException {
        return getService().updateMessagingRedirectLink(messagingRedirectLink);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingRedirectLink updateMessagingRedirectLink(
        com.ext.portlet.mass_messaging.model.MessagingRedirectLink messagingRedirectLink,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateMessagingRedirectLink(messagingRedirectLink, merge);
    }

    public static MessagingRedirectLinkLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "MessagingRedirectLinkLocalService is not set");
        }

        return _service;
    }

    public void setService(MessagingRedirectLinkLocalService service) {
        _service = service;
    }
}
