package com.ext.portlet.surveys.service;


/**
 * <a href="SurveyLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.surveys.service.SurveyLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.surveys.service.SurveyLocalService
 *
 */
public class SurveyLocalServiceUtil {
    private static SurveyLocalService _service;

    public static com.ext.portlet.surveys.model.Survey addSurvey(
        com.ext.portlet.surveys.model.Survey survey)
        throws com.liferay.portal.SystemException {
        return getService().addSurvey(survey);
    }

    public static com.ext.portlet.surveys.model.Survey createSurvey(
        java.lang.String eventName) {
        return getService().createSurvey(eventName);
    }

    public static void deleteSurvey(java.lang.String eventName)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteSurvey(eventName);
    }

    public static void deleteSurvey(com.ext.portlet.surveys.model.Survey survey)
        throws com.liferay.portal.SystemException {
        getService().deleteSurvey(survey);
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

    public static com.ext.portlet.surveys.model.Survey getSurvey(
        java.lang.String eventName)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getSurvey(eventName);
    }

    public static java.util.List<com.ext.portlet.surveys.model.Survey> getSurveies(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getSurveies(start, end);
    }

    public static int getSurveiesCount()
        throws com.liferay.portal.SystemException {
        return getService().getSurveiesCount();
    }

    public static com.ext.portlet.surveys.model.Survey updateSurvey(
        com.ext.portlet.surveys.model.Survey survey)
        throws com.liferay.portal.SystemException {
        return getService().updateSurvey(survey);
    }

    public static com.ext.portlet.surveys.model.Survey updateSurvey(
        com.ext.portlet.surveys.model.Survey survey, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updateSurvey(survey, merge);
    }

    public static SurveyLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("SurveyLocalService is not set");
        }

        return _service;
    }

    public void setService(SurveyLocalService service) {
        _service = service;
    }
}
