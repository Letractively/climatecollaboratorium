/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="MessagingMessageConversionTypeModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>MessagingMessageConversionType</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.model.MessagingMessageConversionType
 * @see com.ext.portlet.mass_messaging.model.impl.MessagingMessageConversionTypeImpl
 * @see com.ext.portlet.mass_messaging.model.impl.MessagingMessageConversionTypeModelImpl
 *
 */
public interface MessagingMessageConversionTypeModel extends BaseModel<MessagingMessageConversionType> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getTypeId();

    public void setTypeId(Long typeId);

    public String getName();

    public void setName(String name);

    public String getDescription();

    public void setDescription(String description);

    public MessagingMessageConversionType toEscapedModel();
}
