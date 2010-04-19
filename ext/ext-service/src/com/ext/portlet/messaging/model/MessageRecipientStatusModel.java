package com.ext.portlet.messaging.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="MessageRecipientStatusModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>MessageRecipientStatus</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.messaging.model.MessageRecipientStatus
 * @see com.ext.portlet.messaging.model.impl.MessageRecipientStatusImpl
 * @see com.ext.portlet.messaging.model.impl.MessageRecipientStatusModelImpl
 *
 */
public interface MessageRecipientStatusModel extends BaseModel<MessageRecipientStatus> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getMessageRecipientId();

    public void setMessageRecipientId(Long messageRecipientId);

    public Long getMessageId();

    public void setMessageId(Long messageId);

    public Long getUserId();

    public void setUserId(Long userId);

    public Boolean getOpened();

    public void setOpened(Boolean opened);

    public Boolean getArchived();

    public void setArchived(Boolean archived);

    public MessageRecipientStatus toEscapedModel();
}
