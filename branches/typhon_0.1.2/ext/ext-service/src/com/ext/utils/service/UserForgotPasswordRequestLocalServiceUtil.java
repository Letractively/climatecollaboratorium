package com.ext.utils.service;


/**
 * <a href="UserForgotPasswordRequestLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.utils.service.UserForgotPasswordRequestLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.utils.service.UserForgotPasswordRequestLocalService
 *
 */
public class UserForgotPasswordRequestLocalServiceUtil {
    private static UserForgotPasswordRequestLocalService _service;

    public static com.ext.utils.model.UserForgotPasswordRequest addUserForgotPasswordRequest(
        com.ext.utils.model.UserForgotPasswordRequest userForgotPasswordRequest)
        throws com.liferay.portal.SystemException {
        return getService()
                   .addUserForgotPasswordRequest(userForgotPasswordRequest);
    }

    public static com.ext.utils.model.UserForgotPasswordRequest createUserForgotPasswordRequest(
        java.lang.String token) {
        return getService().createUserForgotPasswordRequest(token);
    }

    public static void deleteUserForgotPasswordRequest(java.lang.String token)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteUserForgotPasswordRequest(token);
    }

    public static void deleteUserForgotPasswordRequest(
        com.ext.utils.model.UserForgotPasswordRequest userForgotPasswordRequest)
        throws com.liferay.portal.SystemException {
        getService().deleteUserForgotPasswordRequest(userForgotPasswordRequest);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    public static com.ext.utils.model.UserForgotPasswordRequest getUserForgotPasswordRequest(
        java.lang.String token)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getUserForgotPasswordRequest(token);
    }

    public static java.util.List<com.ext.utils.model.UserForgotPasswordRequest> getUserForgotPasswordRequests(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getUserForgotPasswordRequests(start, end);
    }

    public static int getUserForgotPasswordRequestsCount()
        throws com.liferay.portal.SystemException {
        return getService().getUserForgotPasswordRequestsCount();
    }

    public static com.ext.utils.model.UserForgotPasswordRequest updateUserForgotPasswordRequest(
        com.ext.utils.model.UserForgotPasswordRequest userForgotPasswordRequest)
        throws com.liferay.portal.SystemException {
        return getService()
                   .updateUserForgotPasswordRequest(userForgotPasswordRequest);
    }

    public static com.ext.utils.model.UserForgotPasswordRequest updateUserForgotPasswordRequest(
        com.ext.utils.model.UserForgotPasswordRequest userForgotPasswordRequest,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateUserForgotPasswordRequest(userForgotPasswordRequest,
            merge);
    }

    public static UserForgotPasswordRequestLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "UserForgotPasswordRequestLocalService is not set");
        }

        return _service;
    }

    public void setService(UserForgotPasswordRequestLocalService service) {
        _service = service;
    }
}
