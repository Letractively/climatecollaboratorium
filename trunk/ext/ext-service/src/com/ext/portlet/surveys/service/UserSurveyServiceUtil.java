package com.ext.portlet.surveys.service;


/**
 * <a href="UserSurveyServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.surveys.service.UserSurveyService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.surveys.service.UserSurveyService
 *
 */
public class UserSurveyServiceUtil {
    private static UserSurveyService _service;

    public static boolean hasUserViewedSurveyForEvent(java.lang.Long userId,
        java.lang.String event) throws com.liferay.portal.SystemException {
        return getService().hasUserViewedSurveyForEvent(userId, event);
    }

    public static void setUserViewedSurvey(java.lang.Long userId,
        java.lang.String eventName) throws com.liferay.portal.SystemException {
        getService().setUserViewedSurvey(userId, eventName);
    }

    public static void removeUserViewedSurvey(java.lang.Long userId,
        java.lang.String eventName)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().removeUserViewedSurvey(userId, eventName);
    }

    public static UserSurveyService getService() {
        if (_service == null) {
            throw new RuntimeException("UserSurveyService is not set");
        }

        return _service;
    }

    public void setService(UserSurveyService service) {
        _service = service;
    }
}
