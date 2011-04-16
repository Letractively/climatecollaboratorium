package com.ext.portlet.surveys.service;


/**
 * <a href="UserSurveyLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.surveys.service.UserSurveyLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.surveys.service.UserSurveyLocalService
 *
 */
public class UserSurveyLocalServiceUtil {
    private static UserSurveyLocalService _service;

    public static com.ext.portlet.surveys.model.UserSurvey addUserSurvey(
        com.ext.portlet.surveys.model.UserSurvey userSurvey)
        throws com.liferay.portal.SystemException {
        return getService().addUserSurvey(userSurvey);
    }

    public static com.ext.portlet.surveys.model.UserSurvey createUserSurvey(
        com.ext.portlet.surveys.service.persistence.UserSurveyPK userSurveyPK) {
        return getService().createUserSurvey(userSurveyPK);
    }

    public static void deleteUserSurvey(
        com.ext.portlet.surveys.service.persistence.UserSurveyPK userSurveyPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteUserSurvey(userSurveyPK);
    }

    public static void deleteUserSurvey(
        com.ext.portlet.surveys.model.UserSurvey userSurvey)
        throws com.liferay.portal.SystemException {
        getService().deleteUserSurvey(userSurvey);
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

    public static com.ext.portlet.surveys.model.UserSurvey getUserSurvey(
        com.ext.portlet.surveys.service.persistence.UserSurveyPK userSurveyPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getUserSurvey(userSurveyPK);
    }

    public static java.util.List<com.ext.portlet.surveys.model.UserSurvey> getUserSurveies(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getUserSurveies(start, end);
    }

    public static int getUserSurveiesCount()
        throws com.liferay.portal.SystemException {
        return getService().getUserSurveiesCount();
    }

    public static com.ext.portlet.surveys.model.UserSurvey updateUserSurvey(
        com.ext.portlet.surveys.model.UserSurvey userSurvey)
        throws com.liferay.portal.SystemException {
        return getService().updateUserSurvey(userSurvey);
    }

    public static com.ext.portlet.surveys.model.UserSurvey updateUserSurvey(
        com.ext.portlet.surveys.model.UserSurvey userSurvey, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updateUserSurvey(userSurvey, merge);
    }

    public static UserSurveyLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("UserSurveyLocalService is not set");
        }

        return _service;
    }

    public void setService(UserSurveyLocalService service) {
        _service = service;
    }
}
