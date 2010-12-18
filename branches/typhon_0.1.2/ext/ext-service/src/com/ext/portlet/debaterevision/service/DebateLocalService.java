package com.ext.portlet.debaterevision.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="DebateLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.debaterevision.service.impl.DebateLocalServiceImpl</code>.
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
 * @see com.ext.portlet.debaterevision.service.DebateLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface DebateLocalService {
    public com.ext.portlet.debaterevision.model.Debate addDebate(
        com.ext.portlet.debaterevision.model.Debate debate)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.Debate createDebate(
        java.lang.Long debatePK);

    public void deleteDebate(java.lang.Long debatePK)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteDebate(com.ext.portlet.debaterevision.model.Debate debate)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.debaterevision.model.Debate getDebate(
        java.lang.Long debatePK)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.debaterevision.model.Debate> getDebates(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getDebatesCount() throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.Debate updateDebate(
        com.ext.portlet.debaterevision.model.Debate debate)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.Debate updateDebate(
        com.ext.portlet.debaterevision.model.Debate debate, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.Debate createNewDebate(
        java.lang.String title, java.lang.String detail, long authorId)
        throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getLastTreeVersion(long debateId);

    public com.ext.portlet.debaterevision.model.Debate findLastVersion(
        long debateId);

    public com.ext.portlet.debaterevision.model.Debate findByVersion(
        long debateId, long treeVersion)
        throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.debaterevision.model.DebateItem getCurrentRoot(
        com.ext.portlet.debaterevision.model.Debate debate);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.debaterevision.model.DebateComment getMostRecentComment(
        long debateId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getNumberOfComments(long debateId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.debaterevision.model.Debate> getDebates();
}
