package com.ext.portlet.discussions.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.discussions.NoSuchDiscussionMessageException;
import com.ext.portlet.discussions.model.DiscussionMessage;
import com.ext.portlet.discussions.service.DiscussionMessageLocalServiceUtil;
import com.ext.portlet.discussions.service.base.DiscussionMessageLocalServiceBaseImpl;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;


public class DiscussionMessageLocalServiceImpl
    extends DiscussionMessageLocalServiceBaseImpl {
    
    public List<DiscussionMessage> getThreadsByCategory(Long categoryId) throws SystemException {
        return discussionMessagePersistence.findByCategoryIdThreadId(categoryId, null);
    }
    
    public List<DiscussionMessage> getThreadMessages(Long threadId) throws SystemException {
        return discussionMessagePersistence.findByThreadId(threadId);
    }
    
    public int getThreadMessagesCount(Long threadId) throws SystemException {
        return discussionMessagePersistence.countByThreadId(threadId);
    }
    
    public DiscussionMessage getThreadByThreadId(Long threadId) throws NoSuchDiscussionMessageException, SystemException {
        return discussionMessagePersistence.findBySingleThreadId(threadId);
    }
    
    public DiscussionMessage addThread(Long categoryId, String subject, String body, User author) throws SystemException  {
        return addMessage(categoryId, null, subject, body, author);
    }
    
    public DiscussionMessage addMessage(Long categoryId, Long threadId, String subject, String body, User author) throws SystemException {
        Long id = CounterUtil.increment(DiscussionMessage.class.getName());
        Long messageId = CounterUtil.increment(DiscussionMessage.class.getName() + ".discussion");
        
        DiscussionMessage message = DiscussionMessageLocalServiceUtil.createDiscussionMessage(id);
        
        message.setMessageId(messageId);
        message.setCategoryId(categoryId);
        message.setSubject(subject);
        message.setBody(body);
        message.setAuthorId(author.getUserId());
        message.setCreateDate(new Date());
        message.setThreadId(threadId);
        message.setResponsesCount(0);
        
        message.store();
        return message;
    }
}
