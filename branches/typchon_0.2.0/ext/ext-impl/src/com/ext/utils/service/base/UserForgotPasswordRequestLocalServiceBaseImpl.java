package com.ext.utils.service.base;

import com.ext.utils.model.UserForgotPasswordRequest;
import com.ext.utils.service.UserForgotPasswordRequestLocalService;
import com.ext.utils.service.UserForgotPasswordRequestService;
import com.ext.utils.service.persistence.UserForgotPasswordRequestPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class UserForgotPasswordRequestLocalServiceBaseImpl
    implements UserForgotPasswordRequestLocalService {
    @BeanReference(name = "com.ext.utils.service.UserForgotPasswordRequestLocalService.impl")
    protected UserForgotPasswordRequestLocalService userForgotPasswordRequestLocalService;
    @BeanReference(name = "com.ext.utils.service.UserForgotPasswordRequestService.impl")
    protected UserForgotPasswordRequestService userForgotPasswordRequestService;
    @BeanReference(name = "com.ext.utils.service.persistence.UserForgotPasswordRequestPersistence.impl")
    protected UserForgotPasswordRequestPersistence userForgotPasswordRequestPersistence;

    public UserForgotPasswordRequest addUserForgotPasswordRequest(
        UserForgotPasswordRequest userForgotPasswordRequest)
        throws SystemException {
        userForgotPasswordRequest.setNew(true);

        return userForgotPasswordRequestPersistence.update(userForgotPasswordRequest,
            false);
    }

    public UserForgotPasswordRequest createUserForgotPasswordRequest(
        String token) {
        return userForgotPasswordRequestPersistence.create(token);
    }

    public void deleteUserForgotPasswordRequest(String token)
        throws PortalException, SystemException {
        userForgotPasswordRequestPersistence.remove(token);
    }

    public void deleteUserForgotPasswordRequest(
        UserForgotPasswordRequest userForgotPasswordRequest)
        throws SystemException {
        userForgotPasswordRequestPersistence.remove(userForgotPasswordRequest);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return userForgotPasswordRequestPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return userForgotPasswordRequestPersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    public UserForgotPasswordRequest getUserForgotPasswordRequest(String token)
        throws PortalException, SystemException {
        return userForgotPasswordRequestPersistence.findByPrimaryKey(token);
    }

    public List<UserForgotPasswordRequest> getUserForgotPasswordRequests(
        int start, int end) throws SystemException {
        return userForgotPasswordRequestPersistence.findAll(start, end);
    }

    public int getUserForgotPasswordRequestsCount() throws SystemException {
        return userForgotPasswordRequestPersistence.countAll();
    }

    public UserForgotPasswordRequest updateUserForgotPasswordRequest(
        UserForgotPasswordRequest userForgotPasswordRequest)
        throws SystemException {
        userForgotPasswordRequest.setNew(false);

        return userForgotPasswordRequestPersistence.update(userForgotPasswordRequest,
            true);
    }

    public UserForgotPasswordRequest updateUserForgotPasswordRequest(
        UserForgotPasswordRequest userForgotPasswordRequest, boolean merge)
        throws SystemException {
        userForgotPasswordRequest.setNew(false);

        return userForgotPasswordRequestPersistence.update(userForgotPasswordRequest,
            merge);
    }

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
