package com.ext.portlet.discussions.model.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.discussions.NoSuchDiscussionCategoryException;
import com.ext.portlet.discussions.model.DiscussionCategory;
import com.ext.portlet.discussions.model.DiscussionMessage;
import com.ext.portlet.discussions.service.DiscussionCategoryLocalServiceUtil;
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
    
    public DiscussionMessage addThreadMessage(String subject, String body, User author) throws SystemException, NoSuchDiscussionCategoryException {
        Long threadId = getThreadId();
        if (threadId == null) {
            // threadId is null so we have first message (that represents the thread) 
            // use messageId instead of threadId
            threadId = getMessageId();
        }
        DiscussionMessage msg = DiscussionMessageLocalServiceUtil.addMessage(this.getCategoryGroupId(), this.getCategoryId(), threadId, subject, body, author);
        
        this.setResponsesCount(this.getResponsesCount() + 1);
        this.setLastActivityAuthorId(msg.getAuthorId());
        this.setLastActivityDate(msg.getCreateDate());
        this.store();
        
        // set last activity info in category
        DiscussionCategory category = getCategory();
        category.setLastActivityAuthorId(msg.getAuthorId());
        category.setLastActivityDate(msg.getCreateDate());
        category.store();
        
        
        return msg;
    }
    
    public User getAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(getAuthorId());
    }
    
    public User getLastActivityAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(getLastActivityAuthorId());
    }
    
    public void delete() throws SystemException {
        setDeleted(new Date());
        store();
    }
    
    public void update(String subject, String body) throws SystemException {
        setSubject(subject);
        setBody(body);
        store();
    }
    
    public DiscussionCategory getCategory() throws NoSuchDiscussionCategoryException, SystemException {
        return DiscussionCategoryLocalServiceUtil.getDiscussionCategoryById(getCategoryId());
    }
}
