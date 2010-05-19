package com.ext.portlet.messaging.service;


/**
 * <a href="MessagingUserPreferencesLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.messaging.service.MessagingUserPreferencesLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.messaging.service.MessagingUserPreferencesLocalService
 *
 */
public class MessagingUserPreferencesLocalServiceUtil {
    private static MessagingUserPreferencesLocalService _service;

    public static com.ext.portlet.messaging.model.MessagingUserPreferences addMessagingUserPreferences(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences)
        throws com.liferay.portal.SystemException {
        return getService().addMessagingUserPreferences(messagingUserPreferences);
    }

    public static com.ext.portlet.messaging.model.MessagingUserPreferences createMessagingUserPreferences(
        java.lang.Long messagingPreferencesId) {
        return getService()
                   .createMessagingUserPreferences(messagingPreferencesId);
    }

    public static void deleteMessagingUserPreferences(
        java.lang.Long messagingPreferencesId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteMessagingUserPreferences(messagingPreferencesId);
    }

    public static void deleteMessagingUserPreferences(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences)
        throws com.liferay.portal.SystemException {
        getService().deleteMessagingUserPreferences(messagingUserPreferences);
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

    public static com.ext.portlet.messaging.model.MessagingUserPreferences getMessagingUserPreferences(
        java.lang.Long messagingPreferencesId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getMessagingUserPreferences(messagingPreferencesId);
    }

    public static java.util.List<com.ext.portlet.messaging.model.MessagingUserPreferences> getMessagingUserPreferenceses(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getMessagingUserPreferenceses(start, end);
    }

    public static int getMessagingUserPreferencesesCount()
        throws com.liferay.portal.SystemException {
        return getService().getMessagingUserPreferencesesCount();
    }

    public static com.ext.portlet.messaging.model.MessagingUserPreferences updateMessagingUserPreferences(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences)
        throws com.liferay.portal.SystemException {
        return getService()
                   .updateMessagingUserPreferences(messagingUserPreferences);
    }

    public static com.ext.portlet.messaging.model.MessagingUserPreferences updateMessagingUserPreferences(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateMessagingUserPreferences(messagingUserPreferences,
            merge);
    }

    public static com.ext.portlet.messaging.model.MessagingUserPreferences findByUser(
        long userId) throws com.liferay.portal.SystemException {
        return getService().findByUser(userId);
    }

    public static MessagingUserPreferencesLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "MessagingUserPreferencesLocalService is not set");
        }

        return _service;
    }

    public void setService(MessagingUserPreferencesLocalService service) {
        _service = service;
    }
}
