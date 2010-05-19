/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="MessagingIgnoredRecipientsModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>MessagingIgnoredRecipients</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients
 * @see com.ext.portlet.mass_messaging.model.impl.MessagingIgnoredRecipientsImpl
 * @see com.ext.portlet.mass_messaging.model.impl.MessagingIgnoredRecipientsModelImpl
 *
 */
public interface MessagingIgnoredRecipientsModel extends BaseModel<MessagingIgnoredRecipients> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getIgnoredRecipientId();

    public void setIgnoredRecipientId(Long ignoredRecipientId);

    public String getEmail();

    public void setEmail(String email);

    public String getName();

    public void setName(String name);

    public Long getUserId();

    public void setUserId(Long userId);

    public Date getCreateDate();

    public void setCreateDate(Date createDate);

    public MessagingIgnoredRecipients toEscapedModel();
}
