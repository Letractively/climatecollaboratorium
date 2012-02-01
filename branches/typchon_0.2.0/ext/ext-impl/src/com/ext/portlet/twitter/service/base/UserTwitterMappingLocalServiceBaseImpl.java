package com.ext.portlet.twitter.service.base;

import com.ext.portlet.twitter.model.UserTwitterMapping;
import com.ext.portlet.twitter.service.UserTwitterMappingLocalService;
import com.ext.portlet.twitter.service.UserTwitterMappingService;
import com.ext.portlet.twitter.service.persistence.UserTwitterMappingPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class UserTwitterMappingLocalServiceBaseImpl
    implements UserTwitterMappingLocalService {
    @BeanReference(name = "com.ext.portlet.twitter.service.UserTwitterMappingLocalService.impl")
    protected UserTwitterMappingLocalService userTwitterMappingLocalService;
    @BeanReference(name = "com.ext.portlet.twitter.service.UserTwitterMappingService.impl")
    protected UserTwitterMappingService userTwitterMappingService;
    @BeanReference(name = "com.ext.portlet.twitter.service.persistence.UserTwitterMappingPersistence.impl")
    protected UserTwitterMappingPersistence userTwitterMappingPersistence;

    public UserTwitterMapping addUserTwitterMapping(
        UserTwitterMapping userTwitterMapping) throws SystemException {
        userTwitterMapping.setNew(true);

        return userTwitterMappingPersistence.update(userTwitterMapping, false);
    }

    public UserTwitterMapping createUserTwitterMapping(Long twitterId) {
        return userTwitterMappingPersistence.create(twitterId);
    }

    public void deleteUserTwitterMapping(Long twitterId)
        throws PortalException, SystemException {
        userTwitterMappingPersistence.remove(twitterId);
    }

    public void deleteUserTwitterMapping(UserTwitterMapping userTwitterMapping)
        throws SystemException {
        userTwitterMappingPersistence.remove(userTwitterMapping);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return userTwitterMappingPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return userTwitterMappingPersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    public UserTwitterMapping getUserTwitterMapping(Long twitterId)
        throws PortalException, SystemException {
        return userTwitterMappingPersistence.findByPrimaryKey(twitterId);
    }

    public List<UserTwitterMapping> getUserTwitterMappings(int start, int end)
        throws SystemException {
        return userTwitterMappingPersistence.findAll(start, end);
    }

    public int getUserTwitterMappingsCount() throws SystemException {
        return userTwitterMappingPersistence.countAll();
    }

    public UserTwitterMapping updateUserTwitterMapping(
        UserTwitterMapping userTwitterMapping) throws SystemException {
        userTwitterMapping.setNew(false);

        return userTwitterMappingPersistence.update(userTwitterMapping, true);
    }

    public UserTwitterMapping updateUserTwitterMapping(
        UserTwitterMapping userTwitterMapping, boolean merge)
        throws SystemException {
        userTwitterMapping.setNew(false);

        return userTwitterMappingPersistence.update(userTwitterMapping, merge);
    }

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
