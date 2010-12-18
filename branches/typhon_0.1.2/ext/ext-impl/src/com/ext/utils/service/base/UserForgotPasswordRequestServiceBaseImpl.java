package com.ext.utils.service.base;

import com.ext.utils.service.UserForgotPasswordRequestLocalService;
import com.ext.utils.service.UserForgotPasswordRequestService;
import com.ext.utils.service.persistence.UserForgotPasswordRequestPersistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.util.PortalUtil;


public abstract class UserForgotPasswordRequestServiceBaseImpl
    extends PrincipalBean implements UserForgotPasswordRequestService {
    @BeanReference(name = "com.ext.utils.service.UserForgotPasswordRequestLocalService.impl")
    protected UserForgotPasswordRequestLocalService userForgotPasswordRequestLocalService;
    @BeanReference(name = "com.ext.utils.service.UserForgotPasswordRequestService.impl")
    protected UserForgotPasswordRequestService userForgotPasswordRequestService;
    @BeanReference(name = "com.ext.utils.service.persistence.UserForgotPasswordRequestPersistence.impl")
    protected UserForgotPasswordRequestPersistence userForgotPasswordRequestPersistence;

    public UserForgotPasswordRequestLocalService getUserForgotPasswordRequestLocalService() {
        return userForgotPasswordRequestLocalService;
    }

    public void setUserForgotPasswordRequestLocalService(
        UserForgotPasswordRequestLocalService userForgotPasswordRequestLocalService) {
        this.userForgotPasswordRequestLocalService = userForgotPasswordRequestLocalService;
    }

    public UserForgotPasswordRequestService getUserForgotPasswordRequestService() {
        return userForgotPasswordRequestService;
    }

    public void setUserForgotPasswordRequestService(
        UserForgotPasswordRequestService userForgotPasswordRequestService) {
        this.userForgotPasswordRequestService = userForgotPasswordRequestService;
    }

    public UserForgotPasswordRequestPersistence getUserForgotPasswordRequestPersistence() {
        return userForgotPasswordRequestPersistence;
    }

    public void setUserForgotPasswordRequestPersistence(
        UserForgotPasswordRequestPersistence userForgotPasswordRequestPersistence) {
        this.userForgotPasswordRequestPersistence = userForgotPasswordRequestPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
