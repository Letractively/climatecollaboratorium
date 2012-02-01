package com.ext.portlet.landingPage.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="LandingPageLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.landingPage.service.impl.LandingPageLocalServiceImpl</code>.
 * Modify methods in that class and rerun ServiceBuilder to populate this class
 * and all other generated classes.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.landingPage.service.LandingPageLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface LandingPageLocalService {
    public com.ext.portlet.landingPage.model.LandingPage addLandingPage(
        com.ext.portlet.landingPage.model.LandingPage landingPage)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.landingPage.model.LandingPage createLandingPage(
        java.lang.Long id);

    public void deleteLandingPage(java.lang.Long id)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteLandingPage(
        com.ext.portlet.landingPage.model.LandingPage landingPage)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.landingPage.model.LandingPage getLandingPage(
        java.lang.Long id)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.landingPage.model.LandingPage> getLandingPages(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getLandingPagesCount() throws com.liferay.portal.SystemException;

    public com.ext.portlet.landingPage.model.LandingPage updateLandingPage(
        com.ext.portlet.landingPage.model.LandingPage landingPage)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.landingPage.model.LandingPage updateLandingPage(
        com.ext.portlet.landingPage.model.LandingPage landingPage, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.landingPage.model.LandingPage createNewLandingPage(
        java.lang.String baseUrl, java.lang.String targetUrl)
        throws com.liferay.portal.SystemException;
}
