package com.ext.portlet.contests.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="ContestDebateLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.contests.service.impl.ContestDebateLocalServiceImpl</code>.
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
 * @see com.ext.portlet.contests.service.ContestDebateLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface ContestDebateLocalService {
    public com.ext.portlet.contests.model.ContestDebate addContestDebate(
        com.ext.portlet.contests.model.ContestDebate contestDebate)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestDebate createContestDebate(
        java.lang.Long id);

    public void deleteContestDebate(java.lang.Long id)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteContestDebate(
        com.ext.portlet.contests.model.ContestDebate contestDebate)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.contests.model.ContestDebate getContestDebate(
        java.lang.Long id)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.contests.model.ContestDebate> getContestDebates(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getContestDebatesCount()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestDebate updateContestDebate(
        com.ext.portlet.contests.model.ContestDebate contestDebate)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestDebate updateContestDebate(
        com.ext.portlet.contests.model.ContestDebate contestDebate,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestDebate createContestDebate(
        java.lang.Long debateId, java.lang.Long contestId)
        throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.contests.model.ContestDebate> getContestDebates(
        java.lang.Long contestId) throws com.liferay.portal.SystemException;
}
