package com.ext.portlet.messaging.model;


/**
 * <a href="Message.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>Message</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.messaging.model.impl.MessageImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.messaging.model.MessageModel
 * @see com.ext.portlet.messaging.model.impl.MessageImpl
 * @see com.ext.portlet.messaging.model.impl.MessageModelImpl
 *
 */
public interface Message extends MessageModel {
    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> getRecipients()
        throws com.liferay.portal.SystemException;

    public boolean hasReciever(long userid)
        throws com.liferay.portal.SystemException;

    public boolean isOpened(long userid)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException;

    public void setOpened(long userid)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException;

    public boolean isArchived(long userid)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException;

    public void setArchived(long userid)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.SystemException;
}
