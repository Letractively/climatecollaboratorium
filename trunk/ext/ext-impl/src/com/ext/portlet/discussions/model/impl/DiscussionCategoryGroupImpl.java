package com.ext.portlet.discussions.model.impl;

import java.util.List;

import com.ext.portlet.discussions.NoSuchDiscussionCategoryException;
import com.ext.portlet.discussions.NoSuchDiscussionMessageException;
import com.ext.portlet.discussions.model.DiscussionCategory;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.discussions.model.DiscussionMessage;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.discussions.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.discussions.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;


public class DiscussionCategoryGroupImpl
    extends DiscussionCategoryGroupModelImpl implements DiscussionCategoryGroup {
    public DiscussionCategoryGroupImpl() {
    }
    
    public DiscussionCategory getCategoryById(Long categoryId) throws NoSuchDiscussionCategoryException, SystemException {
        return DiscussionCategoryLocalServiceUtil.getDiscussionCategoryById(categoryId);
        
    }
    
    public DiscussionMessage getThreadById(Long threadId) throws NoSuchDiscussionMessageException, SystemException {
        return DiscussionMessageLocalServiceUtil.getThreadByThreadId(threadId);   
    }
    
    public List<DiscussionCategory> getCategories() throws SystemException {
        return DiscussionCategoryLocalServiceUtil.getCategoriesByCategoryGroupId(this.getId());
    }
    
    public DiscussionCategory addCategory(String name, String description, User creator) throws SystemException  {
        return DiscussionCategoryLocalServiceUtil.createDebateCategory(this.getId(), name, description, creator);
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            DiscussionCategoryGroupLocalServiceUtil.addDiscussionCategoryGroup(this);
        }
        else {
            DiscussionCategoryGroupLocalServiceUtil.updateDiscussionCategoryGroup(this);
        }
    }
    
    public DiscussionMessage getCommentThread() throws SystemException, PortalException {
        DiscussionMessage thread;
        if (getCommentsThread() == null || getCommentsThread() <= 0L) {
            return thread = null;
        }
        else {
            thread = DiscussionMessageLocalServiceUtil.getThreadByThreadId(getCommentsThread());
        }
        
        return thread;
        
    }
    
    public DiscussionMessage addComment(String title, String description, User author) throws SystemException, PortalException {
        DiscussionMessage comment = null;
        if (getCommentsThread() == null || getCommentsThread() <= 0L) {
            // create new thread
            comment = DiscussionMessageLocalServiceUtil.addThread(getId(), 0L, title, description, author);
            this.setCommentsThread(comment.getMessageId());
            
            this.store();
        }
        else {
            DiscussionMessage thread = getCommentThread();
            comment = thread.addThreadMessage(title, description, author);
        }
        return comment;
    }
    
    public int getCommentsCount() throws SystemException, PortalException {
        if (getCommentsThread() == null || getCommentsThread() <= 0L) {
            return 0;
        }
        else {
            return getCommentThread().getThreadMessagesCount() + 1;
        }
    }
    
}
