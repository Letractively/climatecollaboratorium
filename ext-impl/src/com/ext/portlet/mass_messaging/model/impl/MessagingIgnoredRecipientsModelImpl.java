/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.model.impl;

import com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients;
import com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipientsSoap;

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
 * <a href="MessagingIgnoredRecipientsModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>MessagingIgnoredRecipients</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients
 * @see com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipientsModel
 * @see com.ext.portlet.mass_messaging.model.impl.MessagingIgnoredRecipientsImpl
 *
 */
public class MessagingIgnoredRecipientsModelImpl extends BaseModelImpl<MessagingIgnoredRecipients> {
    public static final String TABLE_NAME = "MessagingIgnoredRecipients";
    public static final Object[][] TABLE_COLUMNS = {
            { "ignoredRecipientId", new Integer(Types.BIGINT) },
            

            { "email", new Integer(Types.VARCHAR) },
            

            { "name", new Integer(Types.VARCHAR) },
            

            { "userId", new Integer(Types.BIGINT) },
            

            { "createDate", new Integer(Types.TIMESTAMP) }
        };
    public static final String TABLE_SQL_CREATE = "create table MessagingIgnoredRecipients (ignoredRecipientId LONG not null primary key,email VARCHAR(75) null,name VARCHAR(75) null,userId LONG,createDate DATE null)";
    public static final String TABLE_SQL_DROP = "drop table MessagingIgnoredRecipients";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients"));
    private Long _ignoredRecipientId;
    private String _email;
    private String _originalEmail;
    private String _name;
    private Long _userId;
    private Long _originalUserId;
    private Date _createDate;

    public MessagingIgnoredRecipientsModelImpl() {
    }

    public static MessagingIgnoredRecipients toModel(
        MessagingIgnoredRecipientsSoap soapModel) {
        MessagingIgnoredRecipients model = new MessagingIgnoredRecipientsImpl();

        model.setIgnoredRecipientId(soapModel.getIgnoredRecipientId());
        model.setEmail(soapModel.getEmail());
        model.setName(soapModel.getName());
        model.setUserId(soapModel.getUserId());
        model.setCreateDate(soapModel.getCreateDate());

        return model;
    }

    public static List<MessagingIgnoredRecipients> toModels(
        MessagingIgnoredRecipientsSoap[] soapModels) {
        List<MessagingIgnoredRecipients> models = new ArrayList<MessagingIgnoredRecipients>(soapModels.length);

        for (MessagingIgnoredRecipientsSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _ignoredRecipientId;
    }

    public void setPrimaryKey(Long pk) {
        setIgnoredRecipientId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _ignoredRecipientId;
    }

    public Long getIgnoredRecipientId() {
        return _ignoredRecipientId;
    }

    public void setIgnoredRecipientId(Long ignoredRecipientId) {
        _ignoredRecipientId = ignoredRecipientId;
    }

    public String getEmail() {
        return GetterUtil.getString(_email);
    }

    public void setEmail(String email) {
        _email = email;

        if (_originalEmail == null) {
            _originalEmail = email;
        }
    }

    public String getOriginalEmail() {
        return GetterUtil.getString(_originalEmail);
    }

    public String getName() {
        return GetterUtil.getString(_name);
    }

    public void setName(String name) {
        _name = name;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;

        if (_originalUserId == null) {
            _originalUserId = userId;
        }
    }

    public Long getOriginalUserId() {
        return _originalUserId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public MessagingIgnoredRecipients toEscapedModel() {
        if (isEscapedModel()) {
            return (MessagingIgnoredRecipients) this;
        } else {
            MessagingIgnoredRecipients model = new MessagingIgnoredRecipientsImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setIgnoredRecipientId(getIgnoredRecipientId());
            model.setEmail(HtmlUtil.escape(getEmail()));
            model.setName(HtmlUtil.escape(getName()));
            model.setUserId(getUserId());
            model.setCreateDate(getCreateDate());

            model = (MessagingIgnoredRecipients) Proxy.newProxyInstance(MessagingIgnoredRecipients.class.getClassLoader(),
                    new Class[] { MessagingIgnoredRecipients.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        MessagingIgnoredRecipientsImpl clone = new MessagingIgnoredRecipientsImpl();

        clone.setIgnoredRecipientId(getIgnoredRecipientId());
        clone.setEmail(getEmail());
        clone.setName(getName());
        clone.setUserId(getUserId());
        clone.setCreateDate(getCreateDate());

        return clone;
    }

    public int compareTo(MessagingIgnoredRecipients messagingIgnoredRecipients) {
        int value = 0;

        value = getEmail().compareTo(messagingIgnoredRecipients.getEmail());

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        MessagingIgnoredRecipients messagingIgnoredRecipients = null;

        try {
            messagingIgnoredRecipients = (MessagingIgnoredRecipients) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = messagingIgnoredRecipients.getPrimaryKey();

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

        sb.append("{ignoredRecipientId=");
        sb.append(getIgnoredRecipientId());
        sb.append(", email=");
        sb.append(getEmail());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append(
            "com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>ignoredRecipientId</column-name><column-value><![CDATA[");
        sb.append(getIgnoredRecipientId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>email</column-name><column-value><![CDATA[");
        sb.append(getEmail());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
