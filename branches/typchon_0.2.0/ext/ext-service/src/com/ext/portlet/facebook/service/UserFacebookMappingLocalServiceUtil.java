package com.ext.portlet.facebook.service;


/**
 * <a href="UserFacebookMappingLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.facebook.service.UserFacebookMappingLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.facebook.service.UserFacebookMappingLocalService
 *
 */
public class UserFacebookMappingLocalServiceUtil {
    private static UserFacebookMappingLocalService _service;

    public static com.ext.portlet.facebook.model.UserFacebookMapping addUserFacebookMapping(
        com.ext.portlet.facebook.model.UserFacebookMapping userFacebookMapping)
        throws com.liferay.portal.SystemException {
        return getService().addUserFacebookMapping(userFacebookMapping);
    }

    public static com.ext.portlet.facebook.model.UserFacebookMapping createUserFacebookMapping(
        java.lang.Long userId) {
        return getService().createUserFacebookMapping(userId);
    }

    public static void deleteUserFacebookMapping(java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteUserFacebookMapping(userId);
    }

    public static void deleteUserFacebookMapping(
        com.ext.portlet.facebook.model.UserFacebookMapping userFacebookMapping)
        throws com.liferay.portal.SystemException {
        getService().deleteUserFacebookMapping(userFacebookMapping);
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

    public static com.ext.portlet.facebook.model.UserFacebookMapping getUserFacebookMapping(
        java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getUserFacebookMapping(userId);
    }

    public static java.util.List<com.ext.portlet.facebook.model.UserFacebookMapping> getUserFacebookMappings(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getUserFacebookMappings(start, end);
    }

    public static int getUserFacebookMappingsCount()
        throws com.liferay.portal.SystemException {
        return getService().getUserFacebookMappingsCount();
    }

    public static com.ext.portlet.facebook.model.UserFacebookMapping updateUserFacebookMapping(
        com.ext.portlet.facebook.model.UserFacebookMapping userFacebookMapping)
        throws com.liferay.portal.SystemException {
        return getService().updateUserFacebookMapping(userFacebookMapping);
    }

    public static com.ext.portlet.facebook.model.UserFacebookMapping updateUserFacebookMapping(
        com.ext.portlet.facebook.model.UserFacebookMapping userFacebookMapping,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateUserFacebookMapping(userFacebookMapping, merge);
    }

    public static com.ext.portlet.facebook.model.UserFacebookMapping findByFacebookId(
        java.lang.String id) throws com.liferay.portal.SystemException {
        return getService().findByFacebookId(id);
    }

    public static com.liferay.portal.model.User findUserByFacebookId(
        java.lang.String id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().findUserByFacebookId(id);
    }

    public static UserFacebookMappingLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "UserFacebookMappingLocalService is not set");
        }

        return _service;
    }

    public void setService(UserFacebookMappingLocalService service) {
        _service = service;
    }
}
