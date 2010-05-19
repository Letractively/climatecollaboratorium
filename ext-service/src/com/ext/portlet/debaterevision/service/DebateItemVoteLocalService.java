package com.ext.portlet.debaterevision.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="DebateItemVoteLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.debaterevision.service.impl.DebateItemVoteLocalServiceImpl</code>.
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
 * @see com.ext.portlet.debaterevision.service.DebateItemVoteLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface DebateItemVoteLocalService {
    public com.ext.portlet.debaterevision.model.DebateItemVote addDebateItemVote(
        com.ext.portlet.debaterevision.model.DebateItemVote debateItemVote)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVote createDebateItemVote(
        java.lang.Long debateItemVoteId);

    public void deleteDebateItemVote(java.lang.Long debateItemVoteId)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteDebateItemVote(
        com.ext.portlet.debaterevision.model.DebateItemVote debateItemVote)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.debaterevision.model.DebateItemVote getDebateItemVote(
        java.lang.Long debateItemVoteId)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> getDebateItemVotes(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getDebateItemVotesCount()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVote updateDebateItemVote(
        com.ext.portlet.debaterevision.model.DebateItemVote debateItemVote)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVote updateDebateItemVote(
        com.ext.portlet.debaterevision.model.DebateItemVote debateItemVote,
        boolean merge) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> getVotesByDebateItemIdUserId(
        long debateItemId, long userId)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> getVotesByUserId(
        long userId)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> getVotesByDebateItemId(
        long debateItemId) throws com.liferay.portal.SystemException;

    public void flush();

    public void removeVote(long debateItemId, long userId);
}
