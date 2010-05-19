package com.ext.portlet.messaging.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="MessageLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.messaging.service.impl.MessageLocalServiceImpl</code>.
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
 * @see com.ext.portlet.messaging.service.MessageLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface MessageLocalService {
    public com.ext.portlet.messaging.model.Message addMessage(
        com.ext.portlet.messaging.model.Message message)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.Message createMessage(
        java.lang.Long messageId);

    public void deleteMessage(java.lang.Long messageId)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteMessage(com.ext.portlet.messaging.model.Message message)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.messaging.model.Message getMessage(
        java.lang.Long messageId)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.messaging.model.Message> getMessages(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getMessagesCount() throws com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.Message updateMessage(
        com.ext.portlet.messaging.model.Message message)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.messaging.model.Message updateMessage(
        com.ext.portlet.messaging.model.Message message, boolean merge)
        throws com.liferay.portal.SystemException;

    public int countSentMessage(long userid)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.messaging.model.Message> findSentMessages(
        long userid, int pagerstart, int pagerend)
        throws com.liferay.portal.SystemException;
}
