package com.ext.auth.service;


/**
 * <a href="AuthMappingLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.auth.service.AuthMappingLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.auth.service.AuthMappingLocalService
 *
 */
public class AuthMappingLocalServiceUtil {
    private static AuthMappingLocalService _service;

    public static com.ext.auth.model.AuthMapping addAuthMapping(
        com.ext.auth.model.AuthMapping authMapping)
        throws com.liferay.portal.SystemException {
        return getService().addAuthMapping(authMapping);
    }

    public static com.ext.auth.model.AuthMapping createAuthMapping(
        java.lang.String identifier) {
        return getService().createAuthMapping(identifier);
    }

    public static void deleteAuthMapping(java.lang.String identifier)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteAuthMapping(identifier);
    }

    public static void deleteAuthMapping(
        com.ext.auth.model.AuthMapping authMapping)
        throws com.liferay.portal.SystemException {
        getService().deleteAuthMapping(authMapping);
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

    public static com.ext.auth.model.AuthMapping getAuthMapping(
        java.lang.String identifier)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getAuthMapping(identifier);
    }

    public static java.util.List<com.ext.auth.model.AuthMapping> getAuthMappings(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getAuthMappings(start, end);
    }

    public static int getAuthMappingsCount()
        throws com.liferay.portal.SystemException {
        return getService().getAuthMappingsCount();
    }

    public static com.ext.auth.model.AuthMapping updateAuthMapping(
        com.ext.auth.model.AuthMapping authMapping)
        throws com.liferay.portal.SystemException {
        return getService().updateAuthMapping(authMapping);
    }

    public static com.ext.auth.model.AuthMapping updateAuthMapping(
        com.ext.auth.model.AuthMapping authMapping, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updateAuthMapping(authMapping, merge);
    }

    public static void login(com.liferay.portal.model.User user,
        javax.portlet.PortletRequest portletRequest,
        javax.portlet.PortletResponse portletResp)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException,
            com.liferay.util.EncryptorException {
        getService().login(user, portletRequest, portletResp);
    }

    public static AuthMappingLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("AuthMappingLocalService is not set");
        }

        return _service;
    }

    public void setService(AuthMappingLocalService service) {
        _service = service;
    }
}
