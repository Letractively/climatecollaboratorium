package com.ext.utils.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="UserForgotPasswordRequestLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.utils.service.impl.UserForgotPasswordRequestLocalServiceImpl</code>.
 * Modify methods in that class and rerun ServiceBuilder to populate this class
 * and all other generated classes.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.utils.service.UserForgotPasswordRequestLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface UserForgotPasswordRequestLocalService {
    public com.ext.utils.model.UserForgotPasswordRequest addUserForgotPasswordRequest(
        com.ext.utils.model.UserForgotPasswordRequest userForgotPasswordRequest)
        throws com.liferay.portal.SystemException;

    public com.ext.utils.model.UserForgotPasswordRequest createUserForgotPasswordRequest(
        java.lang.String token);

    public void deleteUserForgotPasswordRequest(java.lang.String token)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteUserForgotPasswordRequest(
        com.ext.utils.model.UserForgotPasswordRequest userForgotPasswordRequest)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.utils.model.UserForgotPasswordRequest getUserForgotPasswordRequest(
        java.lang.String token)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.utils.model.UserForgotPasswordRequest> getUserForgotPasswordRequests(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getUserForgotPasswordRequestsCount()
        throws com.liferay.portal.SystemException;

    public com.ext.utils.model.UserForgotPasswordRequest updateUserForgotPasswordRequest(
        com.ext.utils.model.UserForgotPasswordRequest userForgotPasswordRequest)
        throws com.liferay.portal.SystemException;

    public com.ext.utils.model.UserForgotPasswordRequest updateUserForgotPasswordRequest(
        com.ext.utils.model.UserForgotPasswordRequest userForgotPasswordRequest,
        boolean merge) throws com.liferay.portal.SystemException;
}
