package com.ext.portlet.discussions.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.discussions.model.DiscussionMessageFlag;
import com.ext.portlet.discussions.service.base.DiscussionMessageFlagLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;


public class DiscussionMessageFlagLocalServiceImpl
    extends DiscussionMessageFlagLocalServiceBaseImpl {
    
    public List<DiscussionMessageFlag> findMessageFlags(Long messageId) throws SystemException {
        return discussionMessageFlagPersistence.findByMessageId(messageId);
    }
    
    public DiscussionMessageFlag createFlag(Long messageId, String flagType, String data, Long userId) throws SystemException {
        Long pk = CounterLocalServiceUtil.increment(DiscussionMessageFlag.class.getName());
        DiscussionMessageFlag flag = createDiscussionMessageFlag(pk);
        
        flag.setMessageId(messageId);
        flag.setData(data);
        flag.setUserId(userId);
        flag.setFlagType(flagType);
        flag.setCreated(new Date());
        
        addDiscussionMessageFlag(flag);
        
        return flag;
    }

}
