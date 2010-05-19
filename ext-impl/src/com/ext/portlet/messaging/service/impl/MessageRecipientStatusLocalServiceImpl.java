/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.messaging.service.impl;

import com.ext.portlet.messaging.NoSuchMessageRecipientStatusException;
import com.ext.portlet.messaging.model.MessageRecipientStatus;
import com.ext.portlet.messaging.service.base.MessageRecipientStatusLocalServiceBaseImpl;
import com.liferay.portal.SystemException;

import java.util.List;


public class MessageRecipientStatusLocalServiceImpl
    extends MessageRecipientStatusLocalServiceBaseImpl {

    public int countByMessageId(long messageId) throws SystemException {
        return messageRecipientStatusPersistence.countByMessageId(messageId);
    }

    public List<MessageRecipientStatus> findByMessageId(long messageId,int start,int end) throws SystemException {
        return messageRecipientStatusPersistence.findByMessageId(messageId,start,end);
    }


    public int countArchivedMessagesForUser(long userid) throws SystemException {
        return messageRecipientStatusPersistence.countByReceivingUserArchived(userid,true);
    }

    public List<MessageRecipientStatus> findArchivedMessagesForUser(long userid,int start, int end) throws SystemException {
        return messageRecipientStatusPersistence.findByReceivingUserArchived(userid,true,start,end);
    }

    public int countInboxMessagesForUser(long userid) throws SystemException {
        return messageRecipientStatusPersistence.countByReceivingUserArchived(userid,false);
    }

    public List<MessageRecipientStatus> findInboxMessagesForUser(long userid,int start, int end) throws SystemException {
        return messageRecipientStatusPersistence.findByReceivingUserArchived(userid,false,start,end);
    }

    public MessageRecipientStatus findByMessageRecipient(long userid, long messageid) throws SystemException, NoSuchMessageRecipientStatusException {
        return messageRecipientStatusPersistence.findByMessageReciever(messageid,userid);

    }
    
}
