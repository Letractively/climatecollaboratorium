package com.ext.portlet.twitter.service.base;

import com.ext.portlet.twitter.service.UserTwitterMappingLocalService;
import com.ext.portlet.twitter.service.UserTwitterMappingService;
import com.ext.portlet.twitter.service.persistence.UserTwitterMappingPersistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.util.PortalUtil;


public abstract class UserTwitterMappingServiceBaseImpl extends PrincipalBean
    implements UserTwitterMappingService {
    @BeanReference(name = "com.ext.portlet.twitter.service.UserTwitterMappingLocalService.impl")
    protected UserTwitterMappingLocalService userTwitterMappingLocalService;
    @BeanReference(name = "com.ext.portlet.twitter.service.UserTwitterMappingService.impl")
    protected UserTwitterMappingService userTwitterMappingService;
    @BeanReference(name = "com.ext.portlet.twitter.service.persistence.UserTwitterMappingPersistence.impl")
    protected UserTwitterMappingPersistence userTwitterMappingPersistence;

    public UserTwitterMappingLocalService getUserTwitterMappingLocalService() {
        return userTwitterMappingLocalService;
    }

    public void setUserTwitterMappingLocalService(
        UserTwitterMappingLocalService userTwitterMappingLocalService) {
        this.userTwitterMappingLocalService = userTwitterMappingLocalService;
    }

    public UserTwitterMappingService getUserTwitterMappingService() {
        return userTwitterMappingService;
    }

    public void setUserTwitterMappingService(
        UserTwitterMappingService userTwitterMappingService) {
        this.userTwitterMappingService = userTwitterMappingService;
    }

    public UserTwitterMappingPersistence getUserTwitterMappingPersistence() {
        return userTwitterMappingPersistence;
    }

    public void setUserTwitterMappingPersistence(
        UserTwitterMappingPersistence userTwitterMappingPersistence) {
        this.userTwitterMappingPersistence = userTwitterMappingPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
