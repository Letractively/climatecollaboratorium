package com.ext.portlet.twitter.service.impl;

import com.ext.portlet.twitter.model.UserTwitterMapping;
import com.ext.portlet.twitter.service.base.UserTwitterMappingLocalServiceBaseImpl;
import com.liferay.portal.SystemException;


public class UserTwitterMappingLocalServiceImpl
    extends UserTwitterMappingLocalServiceBaseImpl {
    
    public UserTwitterMapping createUserTwitterMapping(Long twitterId, Long userId) throws SystemException {
        UserTwitterMapping utm = createUserTwitterMapping(twitterId);
        utm.setUserId(userId);
        utm.store();
        
        return utm;
    }
}
