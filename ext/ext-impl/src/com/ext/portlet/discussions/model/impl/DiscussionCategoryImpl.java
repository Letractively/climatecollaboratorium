package com.ext.portlet.discussions.model.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.discussions.model.DiscussionCategory;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.discussions.model.DiscussionMessage;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.discussions.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.discussions.service.DiscussionMessageLocalServiceUtil;
import com.ext.portlet.discussions.util.Indexer;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;


public class DiscussionCategoryImpl extends DiscussionCategoryModelImpl
    implements DiscussionCategory {
    
    private static final Log _log = LogFactoryUtil.getLog(DiscussionCategoryImpl.class);
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
        Long lastActivityAuthor = getLastActivityAuthorId();
        if (lastActivityAuthor != null) {
            return UserLocalServiceUtil.getUser(getLastActivityAuthorId());
        }
        return getAuthor();
    }
    
    public void delete() throws SystemException {
        setDeleted(new Date());
        store();
        try {
            // remove from index all messages from deleted category
            for (DiscussionMessage thread: DiscussionMessageLocalServiceUtil.getThreadsByCategory(getCategoryId())) {
                Indexer.deleteEntry(10112L, thread.getMessageId());
                for (DiscussionMessage msg: DiscussionMessageLocalServiceUtil.getThreadMessages(thread.getMessageId())) {
                    Indexer.deleteEntry(10112L, msg.getMessageId());
                }
            }
        } catch (SearchException e) {
            _log.error("Can't remove category messages from search index.", e);
        }
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

