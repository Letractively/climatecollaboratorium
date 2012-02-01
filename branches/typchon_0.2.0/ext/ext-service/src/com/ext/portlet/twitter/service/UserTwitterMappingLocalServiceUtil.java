package com.ext.portlet.twitter.service;


/**
 * <a href="UserTwitterMappingLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.twitter.service.UserTwitterMappingLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.twitter.service.UserTwitterMappingLocalService
 *
 */
public class UserTwitterMappingLocalServiceUtil {
    private static UserTwitterMappingLocalService _service;

    public static com.ext.portlet.twitter.model.UserTwitterMapping addUserTwitterMapping(
        com.ext.portlet.twitter.model.UserTwitterMapping userTwitterMapping)
        throws com.liferay.portal.SystemException {
        return getService().addUserTwitterMapping(userTwitterMapping);
    }

    public static com.ext.portlet.twitter.model.UserTwitterMapping createUserTwitterMapping(
        java.lang.Long twitterId) {
        return getService().createUserTwitterMapping(twitterId);
    }

    public static void deleteUserTwitterMapping(java.lang.Long twitterId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteUserTwitterMapping(twitterId);
    }

    public static void deleteUserTwitterMapping(
        com.ext.portlet.twitter.model.UserTwitterMapping userTwitterMapping)
        throws com.liferay.portal.SystemException {
        getService().deleteUserTwitterMapping(userTwitterMapping);
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

    public static com.ext.portlet.twitter.model.UserTwitterMapping getUserTwitterMapping(
        java.lang.Long twitterId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getUserTwitterMapping(twitterId);
    }

    public static java.util.List<com.ext.portlet.twitter.model.UserTwitterMapping> getUserTwitterMappings(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getUserTwitterMappings(start, end);
    }

    public static int getUserTwitterMappingsCount()
        throws com.liferay.portal.SystemException {
        return getService().getUserTwitterMappingsCount();
    }

    public static com.ext.portlet.twitter.model.UserTwitterMapping updateUserTwitterMapping(
        com.ext.portlet.twitter.model.UserTwitterMapping userTwitterMapping)
        throws com.liferay.portal.SystemException {
        return getService().updateUserTwitterMapping(userTwitterMapping);
    }

    public static com.ext.portlet.twitter.model.UserTwitterMapping updateUserTwitterMapping(
        com.ext.portlet.twitter.model.UserTwitterMapping userTwitterMapping,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateUserTwitterMapping(userTwitterMapping, merge);
    }

    public static com.ext.portlet.twitter.model.UserTwitterMapping createUserTwitterMapping(
        java.lang.Long twitterId, java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        return getService().createUserTwitterMapping(twitterId, userId);
    }

    public static UserTwitterMappingLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "UserTwitterMappingLocalService is not set");
        }

        return _service;
    }

    public void setService(UserTwitterMappingLocalService service) {
        _service = service;
    }
}
