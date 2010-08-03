package com.ext.portlet.discussions.model.impl;

import java.util.Date;
import java.util.List;

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


public class DiscussionCategoryImpl extends DiscussionCategoryModelImpl
    implements DiscussionCategory {
    public DiscussionCategoryImpl() {
    }
    
    public List<DiscussionMessage> getThreads() throws SystemException {
        return DiscussionMessageLocalServiceUtil.getThreadsByCategory(this.getCategoryId());
    }
    
    public DiscussionMessage addThread(String subject, String body, User author) throws SystemException {
        DiscussionMessage thread = DiscussionMessageLocalServiceUtil.addThread(this.getCategoryGroupId(), this.getCategoryId(), subject, body, author);
        
        this.setThreadsCount(getThreadsCount() + 1);
        this.setLastActivityAuthorId(thread.getAuthorId());
        this.setLastActivityDate(thread.getCreateDate());
        this.store();
        
        return thread;
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            DiscussionCategoryLocalServiceUtil.addDiscussionCategory(this);
        }
        else {
            DiscussionCategoryLocalServiceUtil.updateDiscussionCategory(this);
        }
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
    
    public void update(String name, String description) throws SystemException {
        setName(name);
        setDescription(description);
        store();
    }
    
    public DiscussionCategoryGroup getCategoryGroup() throws PortalException, SystemException {
        return DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(getCategoryGroupId());
    }
}

