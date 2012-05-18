package com.ext.auth.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="AuthMappingLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.auth.service.impl.AuthMappingLocalServiceImpl</code>.
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
 * @see com.ext.auth.service.AuthMappingLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface AuthMappingLocalService {
    public com.ext.auth.model.AuthMapping addAuthMapping(
        com.ext.auth.model.AuthMapping authMapping)
        throws com.liferay.portal.SystemException;

    public com.ext.auth.model.AuthMapping createAuthMapping(
        java.lang.String identifier);

    public void deleteAuthMapping(java.lang.String identifier)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteAuthMapping(com.ext.auth.model.AuthMapping authMapping)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.auth.model.AuthMapping getAuthMapping(
        java.lang.String identifier)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.auth.model.AuthMapping> getAuthMappings(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getAuthMappingsCount() throws com.liferay.portal.SystemException;

    public com.ext.auth.model.AuthMapping updateAuthMapping(
        com.ext.auth.model.AuthMapping authMapping)
        throws com.liferay.portal.SystemException;

    public com.ext.auth.model.AuthMapping updateAuthMapping(
        com.ext.auth.model.AuthMapping authMapping, boolean merge)
        throws com.liferay.portal.SystemException;

    public void login(com.liferay.portal.model.User user,
        javax.portlet.PortletRequest portletRequest,
        javax.portlet.PortletResponse portletResp)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException,
            com.liferay.util.EncryptorException;
}
