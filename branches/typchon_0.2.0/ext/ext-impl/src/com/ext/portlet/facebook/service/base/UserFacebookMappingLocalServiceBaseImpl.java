package com.ext.portlet.facebook.service.base;

import com.ext.portlet.facebook.model.UserFacebookMapping;
import com.ext.portlet.facebook.service.UserFacebookMappingLocalService;
import com.ext.portlet.facebook.service.UserFacebookMappingService;
import com.ext.portlet.facebook.service.persistence.UserFacebookMappingPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class UserFacebookMappingLocalServiceBaseImpl
    implements UserFacebookMappingLocalService {
    @BeanReference(name = "com.ext.portlet.facebook.service.UserFacebookMappingLocalService.impl")
    protected UserFacebookMappingLocalService userFacebookMappingLocalService;
    @BeanReference(name = "com.ext.portlet.facebook.service.UserFacebookMappingService.impl")
    protected UserFacebookMappingService userFacebookMappingService;
    @BeanReference(name = "com.ext.portlet.facebook.service.persistence.UserFacebookMappingPersistence.impl")
    protected UserFacebookMappingPersistence userFacebookMappingPersistence;

    public UserFacebookMapping addUserFacebookMapping(
        UserFacebookMapping userFacebookMapping) throws SystemException {
        userFacebookMapping.setNew(true);

        return userFacebookMappingPersistence.update(userFacebookMapping, false);
    }

    public UserFacebookMapping createUserFacebookMapping(Long userId) {
        return userFacebookMappingPersistence.create(userId);
    }

    public void deleteUserFacebookMapping(Long userId)
        throws PortalException, SystemException {
        userFacebookMappingPersistence.remove(userId);
    }

    public void deleteUserFacebookMapping(
        UserFacebookMapping userFacebookMapping) throws SystemException {
        userFacebookMappingPersistence.remove(userFacebookMapping);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return userFacebookMappingPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return userFacebookMappingPersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    public UserFacebookMapping getUserFacebookMapping(Long userId)
        throws PortalException, SystemException {
        return userFacebookMappingPersistence.findByPrimaryKey(userId);
    }

    public List<UserFacebookMapping> getUserFacebookMappings(int start, int end)
        throws SystemException {
        return userFacebookMappingPersistence.findAll(start, end);
    }

    public int getUserFacebookMappingsCount() throws SystemException {
        return userFacebookMappingPersistence.countAll();
    }

    public UserFacebookMapping updateUserFacebookMapping(
        UserFacebookMapping userFacebookMapping) throws SystemException {
        userFacebookMapping.setNew(false);

        return userFacebookMappingPersistence.update(userFacebookMapping, true);
    }

    public UserFacebookMapping updateUserFacebookMapping(
        UserFacebookMapping userFacebookMapping, boolean merge)
        throws SystemException {
        userFacebookMapping.setNew(false);

        return userFacebookMappingPersistence.update(userFacebookMapping, merge);
    }

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
