/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="MessagingMessageRecipientModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>MessagingMessageRecipient</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.model.MessagingMessageRecipient
 * @see com.ext.portlet.mass_messaging.model.impl.MessagingMessageRecipientImpl
 * @see com.ext.portlet.mass_messaging.model.impl.MessagingMessageRecipientModelImpl
 *
 */
public interface MessagingMessageRecipientModel extends BaseModel<MessagingMessageRecipient> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getRecipientId();

    public void setRecipientId(Long recipientId);

    public Long getMessageId();

    public void setMessageId(Long messageId);

    public Long getUserId();

    public void setUserId(Long userId);

    public String getEmailAddress();

    public void setEmailAddress(String emailAddress);

    public MessagingMessageRecipient toEscapedModel();
}
