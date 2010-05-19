/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.impl;

import com.ext.portlet.mass_messaging.model.MessagingMessageConversionType;
import com.ext.portlet.mass_messaging.service.base.MessagingMessageConversionTypeLocalServiceBaseImpl;
import com.liferay.portal.SystemException;


public class MessagingMessageConversionTypeLocalServiceImpl
    extends MessagingMessageConversionTypeLocalServiceBaseImpl {
    public MessagingMessageConversionType getByName(String name) throws SystemException {
        return this.getMessagingMessageConversionTypePersistence().fetchByfindByName(name);
    }
}
