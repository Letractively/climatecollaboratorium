package com.ext.portlet.discussions.service.impl;

import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.discussions.service.base.DiscussionCategoryGroupLocalServiceBaseImpl;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.SystemException;


public class DiscussionCategoryGroupLocalServiceImpl
    extends DiscussionCategoryGroupLocalServiceBaseImpl {
    
    public DiscussionCategoryGroup createDiscussionCategoryGroup(String description) throws SystemException {
        Long id = CounterUtil.increment(DiscussionCategoryGroup.class.getName());
        DiscussionCategoryGroup discussionCategoryGroup = createDiscussionCategoryGroup(id);
        discussionCategoryGroup.setDescription(description);
        discussionCategoryGroup.store();
        
        return discussionCategoryGroup;
    }
}
