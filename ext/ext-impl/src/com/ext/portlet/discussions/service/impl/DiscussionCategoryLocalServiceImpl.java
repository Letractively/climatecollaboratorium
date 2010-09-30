package com.ext.portlet.discussions.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.discussions.NoSuchDiscussionCategoryException;
import com.ext.portlet.discussions.model.DiscussionCategory;
import com.ext.portlet.discussions.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.discussions.service.base.DiscussionCategoryLocalServiceBaseImpl;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;


public class DiscussionCategoryLocalServiceImpl
    extends DiscussionCategoryLocalServiceBaseImpl {
    
    public List<DiscussionCategory> getCategoriesByCategoryGroupId(Long categoryGroupId) throws SystemException {
        return discussionCategoryPersistence.findByCategoryGroupId(categoryGroupId);
    }
    
    public DiscussionCategory getDiscussionCategoryById(Long categoryId) throws NoSuchDiscussionCategoryException, SystemException {
        return discussionCategoryPersistence.findByCategoryId(categoryId);
    }
    
    public DiscussionCategory createDebateCategory(Long categoryGroupId, String name, String description, User author)
    throws SystemException {
        Long id = CounterUtil.increment(DiscussionCategory.class.getName());
        Long categoryId = CounterUtil.increment(DiscussionCategory.class.getName() + ".category");
        DiscussionCategory category = DiscussionCategoryLocalServiceUtil.createDiscussionCategory(id);
        
        category.setName(name);
        category.setDescription(description);
        category.setCreateDate(new Date());
        category.setAuthorId(author.getUserId());
        category.setCategoryGroupId(categoryGroupId);
        category.setCategoryId(categoryId);
        category.setThreadsCount(0);
        
        category.store();
        
        return category;
    }
}