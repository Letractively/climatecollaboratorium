package com.ext.portlet.facebook.service.base;

import com.ext.portlet.facebook.service.UserFacebookMappingLocalService;
import com.ext.portlet.facebook.service.UserFacebookMappingService;
import com.ext.portlet.facebook.service.persistence.UserFacebookMappingPersistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.util.PortalUtil;


public abstract class UserFacebookMappingServiceBaseImpl extends PrincipalBean
    implements UserFacebookMappingService {
    @BeanReference(name = "com.ext.portlet.facebook.service.UserFacebookMappingLocalService.impl")
    protected UserFacebookMappingLocalService userFacebookMappingLocalService;
    @BeanReference(name = "com.ext.portlet.facebook.service.UserFacebookMappingService.impl")
    protected UserFacebookMappingService userFacebookMappingService;
    @BeanReference(name = "com.ext.portlet.facebook.service.persistence.UserFacebookMappingPersistence.impl")
    protected UserFacebookMappingPersistence userFacebookMappingPersistence;

    public UserFacebookMappingLocalService getUserFacebookMappingLocalService() {
        return userFacebookMappingLocalService;
    }

    public void setUserFacebookMappingLocalService(
        UserFacebookMappingLocalService userFacebookMappingLocalService) {
        this.userFacebookMappingLocalService = userFacebookMappingLocalService;
    }

    public UserFacebookMappingService getUserFacebookMappingService() {
        return userFacebookMappingService;
    }

    public void setUserFacebookMappingService(
        UserFacebookMappingService userFacebookMappingService) {
        this.userFacebookMappingService = userFacebookMappingService;
    }

    public UserFacebookMappingPersistence getUserFacebookMappingPersistence() {
        return userFacebookMappingPersistence;
    }

    public void setUserFacebookMappingPersistence(
        UserFacebookMappingPersistence userFacebookMappingPersistence) {
        this.userFacebookMappingPersistence = userFacebookMappingPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
