package com.ext.portlet.surveys.service.http;

import com.ext.portlet.surveys.service.UserSurveyServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MethodWrapper;
import com.liferay.portal.kernel.util.NullWrapper;
import com.liferay.portal.security.auth.HttpPrincipal;
import com.liferay.portal.service.http.TunnelUtil;


/**
 * <a href="UserSurveyServiceHttp.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides a HTTP utility for the
 * <code>com.ext.portlet.surveys.service.UserSurveyServiceUtil</code> service
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
 * @see com.ext.portlet.surveys.service.UserSurveyServiceUtil
 * @see com.ext.portlet.surveys.service.http.UserSurveyServiceSoap
 *
 */
public class UserSurveyServiceHttp {
    private static Log _log = LogFactoryUtil.getLog(UserSurveyServiceHttp.class);

    public static boolean hasUserViewedSurveyForEvent(
        HttpPrincipal httpPrincipal, java.lang.Long userId,
        java.lang.String event) throws com.liferay.portal.SystemException {
        try {
            Object paramObj0 = userId;

            if (userId == null) {
                paramObj0 = new NullWrapper("java.lang.Long");
            }

            Object paramObj1 = event;

            if (event == null) {
                paramObj1 = new NullWrapper("java.lang.String");
            }

            MethodWrapper methodWrapper = new MethodWrapper(UserSurveyServiceUtil.class.getName(),
                    "hasUserViewedSurveyForEvent",
                    new Object[] { paramObj0, paramObj1 });

            Object returnObj = null;

            try {
                returnObj = TunnelUtil.invoke(httpPrincipal, methodWrapper);
            } catch (Exception e) {
                if (e instanceof com.liferay.portal.SystemException) {
                    throw (com.liferay.portal.SystemException) e;
                }

                throw new com.liferay.portal.SystemException(e);
            }

            return ((Boolean) returnObj).booleanValue();
        } catch (com.liferay.portal.SystemException se) {
            _log.error(se, se);

            throw se;
        }
    }

    public static void setUserViewedSurvey(HttpPrincipal httpPrincipal,
        java.lang.Long userId, java.lang.String eventName)
        throws com.liferay.portal.SystemException {
        try {
            Object paramObj0 = userId;

            if (userId == null) {
                paramObj0 = new NullWrapper("java.lang.Long");
            }

            Object paramObj1 = eventName;

            if (eventName == null) {
                paramObj1 = new NullWrapper("java.lang.String");
            }

            MethodWrapper methodWrapper = new MethodWrapper(UserSurveyServiceUtil.class.getName(),
                    "setUserViewedSurvey", new Object[] { paramObj0, paramObj1 });

            try {
                TunnelUtil.invoke(httpPrincipal, methodWrapper);
            } catch (Exception e) {
                if (e instanceof com.liferay.portal.SystemException) {
                    throw (com.liferay.portal.SystemException) e;
                }

                throw new com.liferay.portal.SystemException(e);
            }
        } catch (com.liferay.portal.SystemException se) {
            _log.error(se, se);

            throw se;
        }
    }

    public static void removeUserViewedSurvey(HttpPrincipal httpPrincipal,
        java.lang.Long userId, java.lang.String eventName)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        try {
            Object paramObj0 = userId;

            if (userId == null) {
                paramObj0 = new NullWrapper("java.lang.Long");
            }

            Object paramObj1 = eventName;

            if (eventName == null) {
                paramObj1 = new NullWrapper("java.lang.String");
            }

            MethodWrapper methodWrapper = new MethodWrapper(UserSurveyServiceUtil.class.getName(),
                    "removeUserViewedSurvey",
                    new Object[] { paramObj0, paramObj1 });

            try {
                TunnelUtil.invoke(httpPrincipal, methodWrapper);
            } catch (Exception e) {
                if (e instanceof com.liferay.portal.PortalException) {
                    throw (com.liferay.portal.PortalException) e;
                }

                if (e instanceof com.liferay.portal.SystemException) {
                    throw (com.liferay.portal.SystemException) e;
                }

                throw new com.liferay.portal.SystemException(e);
            }
        } catch (com.liferay.portal.SystemException se) {
            _log.error(se, se);

            throw se;
        }
    }
}
