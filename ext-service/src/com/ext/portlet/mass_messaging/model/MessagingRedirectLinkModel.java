/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="MessagingRedirectLinkModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>MessagingRedirectLink</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.model.MessagingRedirectLink
 * @see com.ext.portlet.mass_messaging.model.impl.MessagingRedirectLinkImpl
 * @see com.ext.portlet.mass_messaging.model.impl.MessagingRedirectLinkModelImpl
 *
 */
public interface MessagingRedirectLinkModel extends BaseModel<MessagingRedirectLink> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getRedirectId();

    public void setRedirectId(Long redirectId);

    public String getLink();

    public void setLink(String link);

    public Long getMessageId();

    public void setMessageId(Long messageId);

    public Date getCreateDate();

    public void setCreateDate(Date createDate);

    public MessagingRedirectLink toEscapedModel();
}
