/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity.model.impl;

import com.ext.portlet.Activity.model.ActivitySubscription;
import com.ext.portlet.Activity.model.ActivitySubscriptionSoap;
import com.ext.portlet.Activity.service.persistence.ActivitySubscriptionPK;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="ActivitySubscriptionModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>ActivitySubscription</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.Activity.model.ActivitySubscription
 * @see com.ext.portlet.Activity.model.ActivitySubscriptionModel
 * @see com.ext.portlet.Activity.model.impl.ActivitySubscriptionImpl
 *
 */
public class ActivitySubscriptionModelImpl extends BaseModelImpl<ActivitySubscription> {
    public static final String TABLE_NAME = "ActivitySubscription";
    public static final Object[][] TABLE_COLUMNS = {
            { "entityId", new Integer(Types.BIGINT) },
            

            { "receiverId", new Integer(Types.BIGINT) },
            

            { "activitytype", new Integer(Types.VARCHAR) },
            

            { "portletId", new Integer(Types.VARCHAR) },
            

            { "createDate", new Integer(Types.TIMESTAMP) },
            

            { "modifiedDate", new Integer(Types.TIMESTAMP) }
        };
    public static final String TABLE_SQL_CREATE = "create table ActivitySubscription (entityId LONG not null,receiverId LONG not null,activitytype VARCHAR(75) not null,portletId VARCHAR(75) not null,createDate DATE null,modifiedDate DATE null,primary key (entityId, receiverId, activitytype, portletId))";
    public static final String TABLE_SQL_DROP = "drop table ActivitySubscription";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.Activity.model.ActivitySubscription"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.Activity.model.ActivitySubscription"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.Activity.model.ActivitySubscription"));
    private Long _entityId;
    private Long _receiverId;
    private String _activitytype;
    private String _portletId;
    private Date _createDate;
    private Date _modifiedDate;

    public ActivitySubscriptionModelImpl() {
    }

    public static ActivitySubscription toModel(
        ActivitySubscriptionSoap soapModel) {
        ActivitySubscription model = new ActivitySubscriptionImpl();

        model.setEntityId(soapModel.getEntityId());
        model.setReceiverId(soapModel.getReceiverId());
        model.setActivitytype(soapModel.getActivitytype());
        model.setPortletId(soapModel.getPortletId());
        model.setCreateDate(soapModel.getCreateDate());
        model.setModifiedDate(soapModel.getModifiedDate());

        return model;
    }

    public static List<ActivitySubscription> toModels(
        ActivitySubscriptionSoap[] soapModels) {
        List<ActivitySubscription> models = new ArrayList<ActivitySubscription>(soapModels.length);

        for (ActivitySubscriptionSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
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

    public Serializable getPrimaryKeyObj() {
        return new ActivitySubscriptionPK(_entityId, _receiverId,
            _activitytype, _portletId);
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
        return GetterUtil.getString(_activitytype);
    }

    public void setActivitytype(String activitytype) {
        _activitytype = activitytype;
    }

    public String getPortletId() {
        return GetterUtil.getString(_portletId);
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

    public ActivitySubscription toEscapedModel() {
        if (isEscapedModel()) {
            return (ActivitySubscription) this;
        } else {
            ActivitySubscription model = new ActivitySubscriptionImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setEntityId(getEntityId());
            model.setReceiverId(getReceiverId());
            model.setActivitytype(HtmlUtil.escape(getActivitytype()));
            model.setPortletId(HtmlUtil.escape(getPortletId()));
            model.setCreateDate(getCreateDate());
            model.setModifiedDate(getModifiedDate());

            model = (ActivitySubscription) Proxy.newProxyInstance(ActivitySubscription.class.getClassLoader(),
                    new Class[] { ActivitySubscription.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        ActivitySubscriptionImpl clone = new ActivitySubscriptionImpl();

        clone.setEntityId(getEntityId());
        clone.setReceiverId(getReceiverId());
        clone.setActivitytype(getActivitytype());
        clone.setPortletId(getPortletId());
        clone.setCreateDate(getCreateDate());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    public int compareTo(ActivitySubscription activitySubscription) {
        ActivitySubscriptionPK pk = activitySubscription.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ActivitySubscription activitySubscription = null;

        try {
            activitySubscription = (ActivitySubscription) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        ActivitySubscriptionPK pk = activitySubscription.getPrimaryKey();

        if (getPrimaryKey().equals(pk)) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return getPrimaryKey().hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{entityId=");
        sb.append(getEntityId());
        sb.append(", receiverId=");
        sb.append(getReceiverId());
        sb.append(", activitytype=");
        sb.append(getActivitytype());
        sb.append(", portletId=");
        sb.append(getPortletId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.Activity.model.ActivitySubscription");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>entityId</column-name><column-value><![CDATA[");
        sb.append(getEntityId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>receiverId</column-name><column-value><![CDATA[");
        sb.append(getReceiverId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>activitytype</column-name><column-value><![CDATA[");
        sb.append(getActivitytype());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>portletId</column-name><column-value><![CDATA[");
        sb.append(getPortletId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
