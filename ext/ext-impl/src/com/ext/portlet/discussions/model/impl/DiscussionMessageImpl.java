package com.ext.portlet.discussions.model.impl;

import java.util.List;

import com.ext.portlet.discussions.model.DiscussionMessage;
import com.ext.portlet.discussions.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;


public class DiscussionMessageImpl extends DiscussionMessageModelImpl
    implements DiscussionMessage {
    public DiscussionMessageImpl() {
    }
    
    public List<DiscussionMessage> getThreadMessages() throws SystemException {
        if (this.getThreadId() == null) {
            // threadId is null so we have first message (that represents the thread) 
            // use messageId instead of threadId
            return DiscussionMessageLocalServiceUtil.getThreadMessages(this.getMessageId());
        }
        return DiscussionMessageLocalServiceUtil.getThreadMessages(this.getThreadId());
        
    }
    
    public int getThreadMessagesCount() throws SystemException {
        if (this.getThreadId() == null) {
            return DiscussionMessageLocalServiceUtil.getThreadMessagesCount(getMessageId());
        }
        return DiscussionMessageLocalServiceUtil.getThreadMessagesCount(getThreadId());
        
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            DiscussionMessageLocalServiceUtil.addDiscussionMessage(this);
        }
        else {
            DiscussionMessageLocalServiceUtil.updateDiscussionMessage(this);
        }
    }
    
    public DiscussionMessage addThreadMessage(String subject, String body, User author) throws SystemException {
        Long threadId = getThreadId();
        if (threadId == null) {
            // threadId is null so we have first message (that represents the thread) 
            // use messageId instead of threadId
            threadId = getMessageId();
        }
        DiscussionMessage msg = DiscussionMessageLocalServiceUtil.addMessage(this.getCategoryId(), threadId, subject, body, author);
        
        this.setResponsesCount(this.getResponsesCount() + 1);
        this.setLastActivityAuthorId(msg.getAuthorId());
        this.setLastActivityDate(msg.getCreateDate());
        this.store();
        
        return msg;
    }
    
    public User getAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(getAuthorId());
    }
    
    public User getLastActivityAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(getLastActivityAuthorId());
    }
}
