package com.ext.auth.service.base;

import com.ext.auth.model.AuthMapping;
import com.ext.auth.service.AuthMappingLocalService;
import com.ext.auth.service.AuthMappingService;
import com.ext.auth.service.persistence.AuthMappingPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class AuthMappingLocalServiceBaseImpl
    implements AuthMappingLocalService {
    @BeanReference(name = "com.ext.auth.service.AuthMappingLocalService.impl")
    protected AuthMappingLocalService authMappingLocalService;
    @BeanReference(name = "com.ext.auth.service.AuthMappingService.impl")
    protected AuthMappingService authMappingService;
    @BeanReference(name = "com.ext.auth.service.persistence.AuthMappingPersistence.impl")
    protected AuthMappingPersistence authMappingPersistence;

    public AuthMapping addAuthMapping(AuthMapping authMapping)
        throws SystemException {
        authMapping.setNew(true);

        return authMappingPersistence.update(authMapping, false);
    }

    public AuthMapping createAuthMapping(String identifier) {
        return authMappingPersistence.create(identifier);
    }

    public void deleteAuthMapping(String identifier)
        throws PortalException, SystemException {
        authMappingPersistence.remove(identifier);
    }

    public void deleteAuthMapping(AuthMapping authMapping)
        throws SystemException {
        authMappingPersistence.remove(authMapping);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return authMappingPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return authMappingPersistence.findWithDynamicQuery(dynamicQuery, start,
            end);
    }

    public AuthMapping getAuthMapping(String identifier)
        throws PortalException, SystemException {
        return authMappingPersistence.findByPrimaryKey(identifier);
    }

    public List<AuthMapping> getAuthMappings(int start, int end)
        throws SystemException {
        return authMappingPersistence.findAll(start, end);
    }

    public int getAuthMappingsCount() throws SystemException {
        return authMappingPersistence.countAll();
    }

    public AuthMapping updateAuthMapping(AuthMapping authMapping)
        throws SystemException {
        authMapping.setNew(false);

        return authMappingPersistence.update(authMapping, true);
    }

    public AuthMapping updateAuthMapping(AuthMapping authMapping, boolean merge)
        throws SystemException {
        authMapping.setNew(false);

        return authMappingPersistence.update(authMapping, merge);
    }

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
