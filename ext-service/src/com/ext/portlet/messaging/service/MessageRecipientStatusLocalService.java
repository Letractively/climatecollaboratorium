package com.ext.portlet.messaging.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="MessageRecipientStatusLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.messaging.service.impl.MessageRecipientStatusLocalServiceImpl</code>.
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
 * @see com.ext.portlet.messaging.service.MessageRecipientStatusLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface MessageRecipientStatusLocalService {
    public com.ext.portlet.messaging.model.MessageRecipientStatus addMessageRecipientStatus(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessageRecipientStatus createMessageRecipientStatus(
        java.lang.Long messageRecipientId);

    public void deleteMessageRecipientStatus(java.lang.Long messageRecipientId)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteMessageRecipientStatus(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.messaging.model.MessageRecipientStatus getMessageRecipientStatus(
        java.lang.Long messageRecipientId)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> getMessageRecipientStatuses(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getMessageRecipientStatusesCount()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessageRecipientStatus updateMessageRecipientStatus(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessageRecipientStatus updateMessageRecipientStatus(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus,
        boolean merge) throws com.liferay.portal.SystemException;

    public int countByMessageId(long messageId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByMessageId(
        long messageId, int start, int end)
        throws com.liferay.portal.SystemException;

    public int countArchivedMessagesForUser(long userid)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findArchivedMessagesForUser(
        long userid, int start, int end)
        throws com.liferay.portal.SystemException;

    public int countInboxMessagesForUser(long userid)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findInboxMessagesForUser(
        long userid, int start, int end)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.MessageRecipientStatus findByMessageRecipient(
        long userid, long messageid)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException;
}
