package com.ext.portlet.contests.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="ContestPhaseTypeLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.contests.service.impl.ContestPhaseTypeLocalServiceImpl</code>.
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
 * @see com.ext.portlet.contests.service.ContestPhaseTypeLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface ContestPhaseTypeLocalService {
    public com.ext.portlet.contests.model.ContestPhaseType addContestPhaseType(
        com.ext.portlet.contests.model.ContestPhaseType contestPhaseType)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhaseType createContestPhaseType(
        java.lang.Long id);

    public void deleteContestPhaseType(java.lang.Long id)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteContestPhaseType(
        com.ext.portlet.contests.model.ContestPhaseType contestPhaseType)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.contests.model.ContestPhaseType getContestPhaseType(
        java.lang.Long id)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.contests.model.ContestPhaseType> getContestPhaseTypes(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getContestPhaseTypesCount()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhaseType updateContestPhaseType(
        com.ext.portlet.contests.model.ContestPhaseType contestPhaseType)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhaseType updateContestPhaseType(
        com.ext.portlet.contests.model.ContestPhaseType contestPhaseType,
        boolean merge) throws com.liferay.portal.SystemException;
}
