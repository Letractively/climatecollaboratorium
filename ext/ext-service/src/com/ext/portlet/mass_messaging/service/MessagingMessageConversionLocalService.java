/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="MessagingMessageConversionLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.mass_messaging.service.impl.MessagingMessageConversionLocalServiceImpl</code>.
 * Modify methods in that class and rerun ServiceBuilder to populate this class
 * and all other generated classes.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.service.MessagingMessageConversionLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface MessagingMessageConversionLocalService {
    public com.ext.portlet.mass_messaging.model.MessagingMessageConversion addMessagingMessageConversion(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversion messagingMessageConversion)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingMessageConversion createMessagingMessageConversion(
        java.lang.Long conversionId);

    public void deleteMessagingMessageConversion(java.lang.Long conversionId)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteMessagingMessageConversion(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversion messagingMessageConversion)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.mass_messaging.model.MessagingMessageConversion getMessagingMessageConversion(
        java.lang.Long conversionId)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversion> getMessagingMessageConversions(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getMessagingMessageConversionsCount()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingMessageConversion updateMessagingMessageConversion(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversion messagingMessageConversion)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingMessageConversion updateMessagingMessageConversion(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversion messagingMessageConversion,
        boolean merge) throws com.liferay.portal.SystemException;

    public int countByType(java.lang.Long messageId,
        com.ext.portlet.mass_messaging.model.MessagingMessageConversionType type)
        throws com.liferay.portal.SystemException;

    public int countByType(java.lang.Long messageId, java.lang.String typeName)
        throws com.liferay.portal.SystemException;
}
