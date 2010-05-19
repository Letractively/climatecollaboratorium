/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service;


/**
 * <a href="MessagingMessageConversionTypeLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.mass_messaging.service.MessagingMessageConversionTypeLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.service.MessagingMessageConversionTypeLocalService
 *
 */
public class MessagingMessageConversionTypeLocalServiceUtil {
    private static MessagingMessageConversionTypeLocalService _service;

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversionType addMessagingMessageConversionType(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversionType messagingMessageConversionType)
        throws com.liferay.portal.SystemException {
        return getService()
                   .addMessagingMessageConversionType(messagingMessageConversionType);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversionType createMessagingMessageConversionType(
        java.lang.Long typeId) {
        return getService().createMessagingMessageConversionType(typeId);
    }

    public static void deleteMessagingMessageConversionType(
        java.lang.Long typeId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteMessagingMessageConversionType(typeId);
    }

    public static void deleteMessagingMessageConversionType(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversionType messagingMessageConversionType)
        throws com.liferay.portal.SystemException {
        getService()
            .deleteMessagingMessageConversionType(messagingMessageConversionType);
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

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversionType getMessagingMessageConversionType(
        java.lang.Long typeId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getMessagingMessageConversionType(typeId);
    }

    public static java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversionType> getMessagingMessageConversionTypes(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getMessagingMessageConversionTypes(start, end);
    }

    public static int getMessagingMessageConversionTypesCount()
        throws com.liferay.portal.SystemException {
        return getService().getMessagingMessageConversionTypesCount();
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversionType updateMessagingMessageConversionType(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversionType messagingMessageConversionType)
        throws com.liferay.portal.SystemException {
        return getService()
                   .updateMessagingMessageConversionType(messagingMessageConversionType);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversionType updateMessagingMessageConversionType(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversionType messagingMessageConversionType,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateMessagingMessageConversionType(messagingMessageConversionType,
            merge);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversionType getByName(
        java.lang.String name) throws com.liferay.portal.SystemException {
        return getService().getByName(name);
    }

    public static MessagingMessageConversionTypeLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "MessagingMessageConversionTypeLocalService is not set");
        }

        return _service;
    }

    public void setService(MessagingMessageConversionTypeLocalService service) {
        _service = service;
    }
}
