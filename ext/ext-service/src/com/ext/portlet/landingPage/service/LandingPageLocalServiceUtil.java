package com.ext.portlet.landingPage.service;


/**
 * <a href="LandingPageLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.landingPage.service.LandingPageLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.landingPage.service.LandingPageLocalService
 *
 */
public class LandingPageLocalServiceUtil {
    private static LandingPageLocalService _service;

    public static com.ext.portlet.landingPage.model.LandingPage addLandingPage(
        com.ext.portlet.landingPage.model.LandingPage landingPage)
        throws com.liferay.portal.SystemException {
        return getService().addLandingPage(landingPage);
    }

    public static com.ext.portlet.landingPage.model.LandingPage createLandingPage(
        java.lang.Long id) {
        return getService().createLandingPage(id);
    }

    public static void deleteLandingPage(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteLandingPage(id);
    }

    public static void deleteLandingPage(
        com.ext.portlet.landingPage.model.LandingPage landingPage)
        throws com.liferay.portal.SystemException {
        getService().deleteLandingPage(landingPage);
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

    public static com.ext.portlet.landingPage.model.LandingPage getLandingPage(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getLandingPage(id);
    }

    public static java.util.List<com.ext.portlet.landingPage.model.LandingPage> getLandingPages(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getLandingPages(start, end);
    }

    public static int getLandingPagesCount()
        throws com.liferay.portal.SystemException {
        return getService().getLandingPagesCount();
    }

    public static com.ext.portlet.landingPage.model.LandingPage updateLandingPage(
        com.ext.portlet.landingPage.model.LandingPage landingPage)
        throws com.liferay.portal.SystemException {
        return getService().updateLandingPage(landingPage);
    }

    public static com.ext.portlet.landingPage.model.LandingPage updateLandingPage(
        com.ext.portlet.landingPage.model.LandingPage landingPage, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updateLandingPage(landingPage, merge);
    }

    public static com.ext.portlet.landingPage.model.LandingPage createNewLandingPage(
        java.lang.String baseUrl, java.lang.String targetUrl)
        throws com.liferay.portal.SystemException {
        return getService().createNewLandingPage(baseUrl, targetUrl);
    }

    public static LandingPageLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("LandingPageLocalService is not set");
        }

        return _service;
    }

    public void setService(LandingPageLocalService service) {
        _service = service;
    }
}
