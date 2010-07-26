package com.ext.portlet.discussions.model.impl;

import java.util.List;

import com.ext.portlet.discussions.NoSuchDiscussionCategoryException;
import com.ext.portlet.discussions.NoSuchDiscussionMessageException;
import com.ext.portlet.discussions.model.DiscussionCategory;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.discussions.model.DiscussionMessage;
import com.ext.portlet.discussions.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.discussions.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;


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
    
}
