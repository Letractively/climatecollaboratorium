/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.messaging.service.impl;

import com.ext.portlet.messaging.NoSuchUserPreferencesException;
import com.ext.portlet.messaging.model.MessagingUserPreferences;
import com.ext.portlet.messaging.service.base.MessagingUserPreferencesLocalServiceBaseImpl;
import com.liferay.portal.SystemException;


public class MessagingUserPreferencesLocalServiceImpl
    extends MessagingUserPreferencesLocalServiceBaseImpl {

    public MessagingUserPreferences findByUser(long userId) throws SystemException {
        try {
            return messagingUserPreferencesPersistence.findBymessagingPreferences(userId);
        } catch (NoSuchUserPreferencesException e) {
            return null;
        } catch (SystemException e) {
            throw(e);
        }
    }
}
