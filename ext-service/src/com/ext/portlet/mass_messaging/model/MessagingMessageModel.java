/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="MessagingMessageModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>MessagingMessage</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.model.MessagingMessage
 * @see com.ext.portlet.mass_messaging.model.impl.MessagingMessageImpl
 * @see com.ext.portlet.mass_messaging.model.impl.MessagingMessageModelImpl
 *
 */
public interface MessagingMessageModel extends BaseModel<MessagingMessage> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getMessageId();

    public void setMessageId(Long messageId);

    public String getName();

    public void setName(String name);

    public String getDescription();

    public void setDescription(String description);

    public String getSubject();

    public void setSubject(String subject);

    public String getBody();

    public void setBody(String body);

    public String getReplyTo();

    public void setReplyTo(String replyTo);

    public Boolean getSendToAll();

    public void setSendToAll(Boolean sendToAll);

    public Long getConversionCount();

    public void setConversionCount(Long conversionCount);

    public String getRedirectURL();

    public void setRedirectURL(String redirectURL);

    public Long getCreatorId();

    public void setCreatorId(Long creatorId);

    public Date getCreateDate();

    public void setCreateDate(Date createDate);

    public Date getModifiedDate();

    public void setModifiedDate(Date modifiedDate);

    public MessagingMessage toEscapedModel();
}
