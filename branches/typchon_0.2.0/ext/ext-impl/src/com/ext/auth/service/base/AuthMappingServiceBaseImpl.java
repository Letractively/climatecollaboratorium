package com.ext.auth.service.base;

import com.ext.auth.service.AuthMappingLocalService;
import com.ext.auth.service.AuthMappingService;
import com.ext.auth.service.persistence.AuthMappingPersistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.util.PortalUtil;


public abstract class AuthMappingServiceBaseImpl extends PrincipalBean
    implements AuthMappingService {
    @BeanReference(name = "com.ext.auth.service.AuthMappingLocalService.impl")
    protected AuthMappingLocalService authMappingLocalService;
    @BeanReference(name = "com.ext.auth.service.AuthMappingService.impl")
    protected AuthMappingService authMappingService;
    @BeanReference(name = "com.ext.auth.service.persistence.AuthMappingPersistence.impl")
    protected AuthMappingPersistence authMappingPersistence;

    public AuthMappingLocalService getAuthMappingLocalService() {
        return authMappingLocalService;
    }

    public void setAuthMappingLocalService(
        AuthMappingLocalService authMappingLocalService) {
        this.authMappingLocalService = authMappingLocalService;
    }

    public AuthMappingService getAuthMappingService() {
        return authMappingService;
    }

    public void setAuthMappingService(AuthMappingService authMappingService) {
        this.authMappingService = authMappingService;
    }

    public AuthMappingPersistence getAuthMappingPersistence() {
        return authMappingPersistence;
    }

    public void setAuthMappingPersistence(
        AuthMappingPersistence authMappingPersistence) {
        this.authMappingPersistence = authMappingPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
