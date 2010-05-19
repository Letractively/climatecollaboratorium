/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.impl;

import com.ext.portlet.mass_messaging.model.MessagingMessageConversionType;
import com.ext.portlet.mass_messaging.service.MessagingMessageConversionTypeLocalServiceUtil;
import com.ext.portlet.mass_messaging.service.base.MessagingMessageConversionLocalServiceBaseImpl;
import com.liferay.portal.SystemException;


public class MessagingMessageConversionLocalServiceImpl
    extends MessagingMessageConversionLocalServiceBaseImpl {
        public int countByType(Long messageId, MessagingMessageConversionType type) throws SystemException {
            return this.getMessagingMessageConversionPersistence().countByfindByType(messageId, type.getTypeId());
        }
        
        public int countByType(Long messageId, String typeName) throws SystemException {
            MessagingMessageConversionType type = MessagingMessageConversionTypeLocalServiceUtil.getByName(typeName);
            return this.getMessagingMessageConversionPersistence().countByfindByType(messageId, type.getTypeId());
        }
}
