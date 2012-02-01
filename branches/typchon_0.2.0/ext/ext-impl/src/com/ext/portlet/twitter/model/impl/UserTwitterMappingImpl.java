package com.ext.portlet.twitter.model.impl;

import com.ext.portlet.twitter.model.UserTwitterMapping;
import com.ext.portlet.twitter.service.UserTwitterMappingLocalServiceUtil;
import com.liferay.portal.SystemException;


public class UserTwitterMappingImpl extends UserTwitterMappingModelImpl
    implements UserTwitterMapping {
    
    private static final long serialVersionUID = -8633249297797960357L;

    public UserTwitterMappingImpl() {
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            UserTwitterMappingLocalServiceUtil.addUserTwitterMapping(this);
        }
        else {
            UserTwitterMappingLocalServiceUtil.updateUserTwitterMapping(this);
        }
    }
}
