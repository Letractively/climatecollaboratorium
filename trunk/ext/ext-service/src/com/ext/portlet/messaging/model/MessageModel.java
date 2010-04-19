package com.ext.portlet.messaging.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="MessageModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>Message</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.messaging.model.Message
 * @see com.ext.portlet.messaging.model.impl.MessageImpl
 * @see com.ext.portlet.messaging.model.impl.MessageModelImpl
 *
 */
public interface MessageModel extends BaseModel<Message> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getMessageId();

    public void setMessageId(Long messageId);

    public Long getFromId();

    public void setFromId(Long fromId);

    public Long getRepliesTo();

    public void setRepliesTo(Long repliesTo);

    public Date getCreateDate();

    public void setCreateDate(Date createDate);

    public String getSubject();

    public void setSubject(String subject);

    public String getContent();

    public void setContent(String content);

    public Message toEscapedModel();
}
