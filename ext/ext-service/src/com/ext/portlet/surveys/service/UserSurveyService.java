package com.ext.portlet.surveys.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="UserSurveyService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.surveys.service.impl.UserSurveyServiceImpl</code>.
 * Modify methods in that class and rerun ServiceBuilder to populate this class
 * and all other generated classes.
 * </p>
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.surveys.service.UserSurveyServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface UserSurveyService {
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasUserViewedSurveyForEvent(java.lang.Long userId,
        java.lang.String event) throws com.liferay.portal.SystemException;

    public void setUserViewedSurvey(java.lang.Long userId,
        java.lang.String eventName) throws com.liferay.portal.SystemException;

    public void removeUserViewedSurvey(java.lang.Long userId,
        java.lang.String eventName)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;
}
