package com.ext.portlet.facebook.service.impl;

import com.ext.portlet.facebook.NoSuchUserFacebookMappingException;
import com.ext.portlet.facebook.model.UserFacebookMapping;
import com.ext.portlet.facebook.service.base.UserFacebookMappingLocalServiceBaseImpl;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;


public class UserFacebookMappingLocalServiceImpl
    extends UserFacebookMappingLocalServiceBaseImpl {


    public UserFacebookMapping findByFacebookId(String id) throws SystemException {
        try {
            return userFacebookMappingPersistence.findByfindByFacebookId(id);
        } catch (NoSuchUserFacebookMappingException e) {
            return null;
        }
    }

    public User findUserByFacebookId(String id) throws SystemException, PortalException {
        UserFacebookMapping mapping = findByFacebookId(id);
        return mapping==null?null:UserLocalServiceUtil.getUser(mapping.getUserId());  
    }
}
