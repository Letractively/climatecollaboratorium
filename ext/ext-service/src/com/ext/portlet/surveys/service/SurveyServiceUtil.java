package com.ext.portlet.surveys.service;


/**
 * <a href="SurveyServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.surveys.service.SurveyService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.surveys.service.SurveyService
 *
 */
public class SurveyServiceUtil {
    private static SurveyService _service;

    public static java.util.List<com.ext.portlet.surveys.model.Survey> getSurveys()
        throws com.liferay.portal.SystemException {
        return getService().getSurveys();
    }

    public static com.ext.portlet.surveys.model.Survey getSurvey(
        java.lang.String eventName)
        throws com.ext.portlet.surveys.NoSuchSurveyException,
            com.liferay.portal.SystemException {
        return getService().getSurvey(eventName);
    }

    public static SurveyService getService() {
        if (_service == null) {
            throw new RuntimeException("SurveyService is not set");
        }

        return _service;
    }

    public void setService(SurveyService service) {
        _service = service;
    }
}
