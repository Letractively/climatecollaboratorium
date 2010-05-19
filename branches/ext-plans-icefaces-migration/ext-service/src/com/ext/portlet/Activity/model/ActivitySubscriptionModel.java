/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity.model;

import com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="ActivitySubscriptionModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ActivitySubscription</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.Activity.model.ActivitySubscription
 * @see com.ext.portlet.Activity.model.impl.ActivitySubscriptionImpl
 * @see com.ext.portlet.Activity.model.impl.ActivitySubscriptionModelImpl
 *
 */
public interface ActivitySubscriptionModel extends BaseModel<ActivitySubscription> {
    public ActivitySubscriptionPK getPrimaryKey();

    public void setPrimaryKey(ActivitySubscriptionPK pk);

    public Long getEntityId();

    public void setEntityId(Long entityId);

    public Long getReceiverId();

    public void setReceiverId(Long receiverId);

    public String getActivitytype();

    public void setActivitytype(String activitytype);

    public String getPortletId();

    public void setPortletId(String portletId);

    public Date getCreateDate();

    public void setCreateDate(Date createDate);

    public Date getModifiedDate();

    public void setModifiedDate(Date modifiedDate);

    public ActivitySubscription toEscapedModel();
}
