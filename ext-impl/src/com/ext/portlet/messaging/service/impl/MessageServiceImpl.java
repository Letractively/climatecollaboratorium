/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.messaging.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;

import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.messaging.service.base.MessageServiceBaseImpl;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.util.mail.MailEngineException;


public class MessageServiceImpl extends MessageServiceBaseImpl {
    public void addMessage(Long recipientId, String subject, String content) throws AddressException, MailEngineException {
        try {
            // check if user that sent the request is logged in
            User user = this.getUser();
            List<Long> recipients = new ArrayList<Long>();
            recipients.add(recipientId);
            
            MessageUtil.sendMessage(subject,content,user.getUserId(),user.getUserId(),recipients, null);
            
        } catch (PortalException e) {
            // ignore
        } catch (SystemException e) {
            // ignore
        }
    }
    
}
