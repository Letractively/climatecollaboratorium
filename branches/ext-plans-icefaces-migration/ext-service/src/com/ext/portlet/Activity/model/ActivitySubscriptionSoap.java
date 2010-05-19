/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity.model;

import com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="ActivitySubscriptionSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.Activity.service.http.ActivitySubscriptionServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.Activity.service.http.ActivitySubscriptionServiceSoap
 *
 */
public class ActivitySubscriptionSoap implements Serializable {
    private Long _entityId;
    private Long _receiverId;
    private String _activitytype;
    private String _portletId;
    private Date _createDate;
    private Date _modifiedDate;

    public ActivitySubscriptionSoap() {
    }

    public static ActivitySubscriptionSoap toSoapModel(
        ActivitySubscription model) {
        ActivitySubscriptionSoap soapModel = new ActivitySubscriptionSoap();

        soapModel.setEntityId(model.getEntityId());
        soapModel.setReceiverId(model.getReceiverId());
        soapModel.setActivitytype(model.getActivitytype());
        soapModel.setPortletId(model.getPortletId());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setModifiedDate(model.getModifiedDate());

        return soapModel;
    }

    public static ActivitySubscriptionSoap[] toSoapModels(
        ActivitySubscription[] models) {
        ActivitySubscriptionSoap[] soapModels = new ActivitySubscriptionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ActivitySubscriptionSoap[][] toSoapModels(
        ActivitySubscription[][] models) {
        ActivitySubscriptionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ActivitySubscriptionSoap[models.length][models[0].length];
        } else {
            soapModels = new ActivitySubscriptionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ActivitySubscriptionSoap[] toSoapModels(
        List<ActivitySubscription> models) {
        List<ActivitySubscriptionSoap> soapModels = new ArrayList<ActivitySubscriptionSoap>(models.size());

        for (ActivitySubscription model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ActivitySubscriptionSoap[soapModels.size()]);
    }

    public ActivitySubscriptionPK getPrimaryKey() {
        return new ActivitySubscriptionPK(_entityId, _receiverId,
            _activitytype, _portletId);
    }

    public void setPrimaryKey(ActivitySubscriptionPK pk) {
        setEntityId(pk.entityId);
        setReceiverId(pk.receiverId);
        setActivitytype(pk.activitytype);
        setPortletId(pk.portletId);
    }

    public Long getEntityId() {
        return _entityId;
    }

    public void setEntityId(Long entityId) {
        _entityId = entityId;
    }

    public Long getReceiverId() {
        return _receiverId;
    }

    public void setReceiverId(Long receiverId) {
        _receiverId = receiverId;
    }

    public String getActivitytype() {
        return _activitytype;
    }

    public void setActivitytype(String activitytype) {
        _activitytype = activitytype;
    }

    public String getPortletId() {
        return _portletId;
    }

    public void setPortletId(String portletId) {
        _portletId = portletId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }
}
