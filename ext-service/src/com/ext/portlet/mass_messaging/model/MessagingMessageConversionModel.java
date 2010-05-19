/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="MessagingMessageConversionModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>MessagingMessageConversion</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.model.MessagingMessageConversion
 * @see com.ext.portlet.mass_messaging.model.impl.MessagingMessageConversionImpl
 * @see com.ext.portlet.mass_messaging.model.impl.MessagingMessageConversionModelImpl
 *
 */
public interface MessagingMessageConversionModel extends BaseModel<MessagingMessageConversion> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getConversionId();

    public void setConversionId(Long conversionId);

    public Long getConversionTypeId();

    public void setConversionTypeId(Long conversionTypeId);

    public Long getMessageId();

    public void setMessageId(Long messageId);

    public String getIpAddress();

    public void setIpAddress(String ipAddress);

    public String getExtraData();

    public void setExtraData(String extraData);

    public String getExtraData2();

    public void setExtraData2(String extraData2);

    public Date getCreateDate();

    public void setCreateDate(Date createDate);

    public MessagingMessageConversion toEscapedModel();
}
