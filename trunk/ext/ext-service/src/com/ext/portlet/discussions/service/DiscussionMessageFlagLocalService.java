package com.ext.portlet.discussions.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="DiscussionMessageFlagLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.discussions.service.impl.DiscussionMessageFlagLocalServiceImpl</code>.
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
 * @see com.ext.portlet.discussions.service.DiscussionMessageFlagLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface DiscussionMessageFlagLocalService {
    public com.ext.portlet.discussions.model.DiscussionMessageFlag addDiscussionMessageFlag(
        com.ext.portlet.discussions.model.DiscussionMessageFlag discussionMessageFlag)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessageFlag createDiscussionMessageFlag(
        java.lang.Long pk);

    public void deleteDiscussionMessageFlag(java.lang.Long pk)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteDiscussionMessageFlag(
        com.ext.portlet.discussions.model.DiscussionMessageFlag discussionMessageFlag)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.discussions.model.DiscussionMessageFlag getDiscussionMessageFlag(
        java.lang.Long pk)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessageFlag> getDiscussionMessageFlags(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getDiscussionMessageFlagsCount()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessageFlag updateDiscussionMessageFlag(
        com.ext.portlet.discussions.model.DiscussionMessageFlag discussionMessageFlag)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessageFlag updateDiscussionMessageFlag(
        com.ext.portlet.discussions.model.DiscussionMessageFlag discussionMessageFlag,
        boolean merge) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessageFlag> findMessageFlags(
        java.lang.Long messageId) throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessageFlag createFlag(
        java.lang.Long messageId, java.lang.String flagType,
        java.lang.String data, java.lang.Long userId)
        throws com.liferay.portal.SystemException;
}
