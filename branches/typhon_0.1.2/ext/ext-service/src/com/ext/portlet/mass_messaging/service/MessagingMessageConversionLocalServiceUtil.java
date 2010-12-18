/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service;


/**
 * <a href="MessagingMessageConversionLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.mass_messaging.service.MessagingMessageConversionLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.service.MessagingMessageConversionLocalService
 *
 */
public class MessagingMessageConversionLocalServiceUtil {
    private static MessagingMessageConversionLocalService _service;

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversion addMessagingMessageConversion(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversion messagingMessageConversion)
        throws com.liferay.portal.SystemException {
        return getService()
                   .addMessagingMessageConversion(messagingMessageConversion);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversion createMessagingMessageConversion(
        java.lang.Long conversionId) {
        return getService().createMessagingMessageConversion(conversionId);
    }

    public static void deleteMessagingMessageConversion(
        java.lang.Long conversionId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteMessagingMessageConversion(conversionId);
    }

    public static void deleteMessagingMessageConversion(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversion messagingMessageConversion)
        throws com.liferay.portal.SystemException {
        getService().deleteMessagingMessageConversion(messagingMessageConversion);
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

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversion getMessagingMessageConversion(
        java.lang.Long conversionId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getMessagingMessageConversion(conversionId);
    }

    public static java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversion> getMessagingMessageConversions(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getMessagingMessageConversions(start, end);
    }

    public static int getMessagingMessageConversionsCount()
        throws com.liferay.portal.SystemException {
        return getService().getMessagingMessageConversionsCount();
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversion updateMessagingMessageConversion(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversion messagingMessageConversion)
        throws com.liferay.portal.SystemException {
        return getService()
                   .updateMessagingMessageConversion(messagingMessageConversion);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversion updateMessagingMessageConversion(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversion messagingMessageConversion,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateMessagingMessageConversion(messagingMessageConversion,
            merge);
    }

    public static int countByType(java.lang.Long messageId,
        com.ext.portlet.mass_messaging.model.MessagingMessageConversionType type)
        throws com.liferay.portal.SystemException {
        return getService().countByType(messageId, type);
    }

    public static int countByType(java.lang.Long messageId,
        java.lang.String typeName) throws com.liferay.portal.SystemException {
        return getService().countByType(messageId, typeName);
    }

    public static MessagingMessageConversionLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "MessagingMessageConversionLocalService is not set");
        }

        return _service;
    }

    public void setService(MessagingMessageConversionLocalService service) {
        _service = service;
    }
}
