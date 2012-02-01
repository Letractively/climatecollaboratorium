package com.ext.portlet.surveys.service.http;

import com.ext.portlet.surveys.service.SurveyServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MethodWrapper;
import com.liferay.portal.kernel.util.NullWrapper;
import com.liferay.portal.security.auth.HttpPrincipal;
import com.liferay.portal.service.http.TunnelUtil;


/**
 * <a href="SurveyServiceHttp.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides a HTTP utility for the
 * <code>com.ext.portlet.surveys.service.SurveyServiceUtil</code> service
 * utility. The static methods of this class calls the same methods of the
 * service utility. However, the signatures are different because it requires an
 * additional <code>com.liferay.portal.security.auth.HttpPrincipal</code>
 * parameter.
 * </p>
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <code>tunnel.servlet.hosts.allowed</code> in
 * portal.properties to configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.liferay.portal.security.auth.HttpPrincipal
 * @see com.ext.portlet.surveys.service.SurveyServiceUtil
 * @see com.ext.portlet.surveys.service.http.SurveyServiceSoap
 *
 */
public class SurveyServiceHttp {
    private static Log _log = LogFactoryUtil.getLog(SurveyServiceHttp.class);

    public static java.util.List<com.ext.portlet.surveys.model.Survey> getSurveys(
        HttpPrincipal httpPrincipal) throws com.liferay.portal.SystemException {
        try {
            MethodWrapper methodWrapper = new MethodWrapper(SurveyServiceUtil.class.getName(),
                    "getSurveys", new Object[0]);

            Object returnObj = null;

            try {
                returnObj = TunnelUtil.invoke(httpPrincipal, methodWrapper);
            } catch (Exception e) {
                if (e instanceof com.liferay.portal.SystemException) {
                    throw (com.liferay.portal.SystemException) e;
                }

                throw new com.liferay.portal.SystemException(e);
            }

            return (java.util.List<com.ext.portlet.surveys.model.Survey>) returnObj;
        } catch (com.liferay.portal.SystemException se) {
            _log.error(se, se);

            throw se;
        }
    }

    public static com.ext.portlet.surveys.model.Survey getSurvey(
        HttpPrincipal httpPrincipal, java.lang.String eventName)
        throws com.ext.portlet.surveys.NoSuchSurveyException,
            com.liferay.portal.SystemException {
        try {
            Object paramObj0 = eventName;

            if (eventName == null) {
                paramObj0 = new NullWrapper("java.lang.String");
            }

            MethodWrapper methodWrapper = new MethodWrapper(SurveyServiceUtil.class.getName(),
                    "getSurvey", new Object[] { paramObj0 });

            Object returnObj = null;

            try {
                returnObj = TunnelUtil.invoke(httpPrincipal, methodWrapper);
            } catch (Exception e) {
                if (e instanceof com.ext.portlet.surveys.NoSuchSurveyException) {
                    throw (com.ext.portlet.surveys.NoSuchSurveyException) e;
                }

                if (e instanceof com.liferay.portal.SystemException) {
                    throw (com.liferay.portal.SystemException) e;
                }

                throw new com.liferay.portal.SystemException(e);
            }

            return (com.ext.portlet.surveys.model.Survey) returnObj;
        } catch (com.liferay.portal.SystemException se) {
            _log.error(se, se);

            throw se;
        }
    }
}
