package com.ext.portlet.discussions.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="DiscussionMessageLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.discussions.service.impl.DiscussionMessageLocalServiceImpl</code>.
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
 * @see com.ext.portlet.discussions.service.DiscussionMessageLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface DiscussionMessageLocalService {
    public com.ext.portlet.discussions.model.DiscussionMessage addDiscussionMessage(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage createDiscussionMessage(
        java.lang.Long pk);

    public void deleteDiscussionMessage(java.lang.Long pk)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteDiscussionMessage(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.discussions.model.DiscussionMessage getDiscussionMessage(
        java.lang.Long pk)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> getDiscussionMessages(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getDiscussionMessagesCount()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage updateDiscussionMessage(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage updateDiscussionMessage(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage,
        boolean merge) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> getThreadsByCategory(
        java.lang.Long categoryId) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> getThreadMessages(
        java.lang.Long threadId) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getThreadMessagesCount(java.lang.Long threadId)
        throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.discussions.model.DiscussionMessage getThreadByThreadId(
        java.lang.Long threadId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage addThread(
        java.lang.Long categoryId, java.lang.String subject,
        java.lang.String body, com.liferay.portal.model.User author)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage addMessage(
        java.lang.Long categoryId, java.lang.Long threadId,
        java.lang.String subject, java.lang.String body,
        com.liferay.portal.model.User author)
        throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> search(
        java.lang.String query) throws com.liferay.portal.SystemException;
}
