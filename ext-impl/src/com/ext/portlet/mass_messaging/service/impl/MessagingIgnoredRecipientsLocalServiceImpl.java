/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.impl;

import com.ext.portlet.mass_messaging.NoSuchMessagingIgnoredRecipientsException;
import com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients;
import com.ext.portlet.mass_messaging.service.base.MessagingIgnoredRecipientsLocalServiceBaseImpl;
import com.liferay.portal.SystemException;


public class MessagingIgnoredRecipientsLocalServiceImpl
    extends MessagingIgnoredRecipientsLocalServiceBaseImpl {
    
    public MessagingIgnoredRecipients findByUserId(Long userId) throws SystemException, NoSuchMessagingIgnoredRecipientsException {
        return this.getMessagingIgnoredRecipientsPersistence().findByfindByUserId(userId);
    }
    
    public MessagingIgnoredRecipients findByEmail(String email) throws SystemException, NoSuchMessagingIgnoredRecipientsException {
        return this.getMessagingIgnoredRecipientsPersistence().findByfindByEmail(email);
    }
    
}
