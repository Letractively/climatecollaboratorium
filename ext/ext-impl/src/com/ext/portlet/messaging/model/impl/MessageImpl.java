/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.messaging.model.impl;

import com.ext.portlet.messaging.NoSuchMessageRecipientStatusException;
import com.ext.portlet.messaging.model.Message;
import com.ext.portlet.messaging.model.MessageRecipientStatus;
import com.ext.portlet.messaging.service.MessageRecipientStatusLocalServiceUtil;
import com.liferay.portal.SystemException;

import java.util.List;


public class MessageImpl extends MessageModelImpl implements Message {
    public MessageImpl() {

    }

    public List<MessageRecipientStatus> getRecipients() throws SystemException {
        return MessageRecipientStatusLocalServiceUtil.findByMessageId(getMessageId(),0,Short.MAX_VALUE);
    }

    public boolean hasReciever(long userid) throws SystemException {
        MessageRecipientStatus status = null;
        try {
            status = MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(userid,getMessageId());
        }  catch (NoSuchMessageRecipientStatusException e) {
           //no worries
        }
        return status!=null;
    }

    public boolean isOpened(long userid) throws SystemException, NoSuchMessageRecipientStatusException {
        return MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(userid,getMessageId()).getOpened();
    }

    public void setOpened(long userid) throws SystemException, NoSuchMessageRecipientStatusException {
        MessageRecipientStatus status = MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(userid,getMessageId());
        status.setOpened(true);
        MessageRecipientStatusLocalServiceUtil.updateMessageRecipientStatus(status);
    }

    public boolean isArchived(long userid) throws SystemException, NoSuchMessageRecipientStatusException {
        return MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(userid,getMessageId()).getArchived();
    }

    public void setArchived(long userid) throws SystemException, NoSuchMessageRecipientStatusException {
        MessageRecipientStatus status = MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(userid,getMessageId());
        status.setArchived(true);
        MessageRecipientStatusLocalServiceUtil.updateMessageRecipientStatus(status);
    }


}
