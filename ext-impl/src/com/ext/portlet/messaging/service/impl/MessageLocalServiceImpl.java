/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.messaging.service.impl;

import com.ext.portlet.messaging.model.Message;
import com.ext.portlet.messaging.service.base.MessageLocalServiceBaseImpl;
import com.liferay.portal.SystemException;

import java.util.List;


public class MessageLocalServiceImpl extends MessageLocalServiceBaseImpl {

    public int countSentMessage(long userid) throws SystemException {
        return messagePersistence.countBySendingUser(userid);
    }

    public List<Message> findSentMessages(long userid, int pagerstart, int pagerend) throws SystemException {
        return messagePersistence.findBySendingUser(userid,pagerstart,pagerend);

    }

}
