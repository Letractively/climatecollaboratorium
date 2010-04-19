/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.model.impl;

import com.ext.portlet.mass_messaging.model.MessagingMessageRecipient;
import com.ext.portlet.mass_messaging.model.MessagingMessageRecipientSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="MessagingMessageRecipientModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>MessagingMessageRecipient</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.model.MessagingMessageRecipient
 * @see com.ext.portlet.mass_messaging.model.MessagingMessageRecipientModel
 * @see com.ext.portlet.mass_messaging.model.impl.MessagingMessageRecipientImpl
 *
 */
public class MessagingMessageRecipientModelImpl extends BaseModelImpl<MessagingMessageRecipient> {
    public static final String TABLE_NAME = "MessagingMessageRecipient";
    public static final Object[][] TABLE_COLUMNS = {
            { "recipientId", new Integer(Types.BIGINT) },
            

            { "messageId", new Integer(Types.BIGINT) },
            

            { "userId", new Integer(Types.BIGINT) },
            

            { "emailAddress", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table MessagingMessageRecipient (recipientId LONG not null primary key,messageId LONG,userId LONG,emailAddress VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table MessagingMessageRecipient";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.mass_messaging.model.MessagingMessageRecipient"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.mass_messaging.model.MessagingMessageRecipient"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.mass_messaging.model.MessagingMessageRecipient"));
    private Long _recipientId;
    private Long _messageId;
    private Long _userId;
    private String _emailAddress;

    public MessagingMessageRecipientModelImpl() {
    }

    public static MessagingMessageRecipient toModel(
        MessagingMessageRecipientSoap soapModel) {
        MessagingMessageRecipient model = new MessagingMessageRecipientImpl();

        model.setRecipientId(soapModel.getRecipientId());
        model.setMessageId(soapModel.getMessageId());
        model.setUserId(soapModel.getUserId());
        model.setEmailAddress(soapModel.getEmailAddress());

        return model;
    }

    public static List<MessagingMessageRecipient> toModels(
        MessagingMessageRecipientSoap[] soapModels) {
        List<MessagingMessageRecipient> models = new ArrayList<MessagingMessageRecipient>(soapModels.length);

        for (MessagingMessageRecipientSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _recipientId;
    }

    public void setPrimaryKey(Long pk) {
        setRecipientId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _recipientId;
    }

    public Long getRecipientId() {
        return _recipientId;
    }

    public void setRecipientId(Long recipientId) {
        _recipientId = recipientId;
    }

    public Long getMessageId() {
        return _messageId;
    }

    public void setMessageId(Long messageId) {
        _messageId = messageId;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public String getEmailAddress() {
        return GetterUtil.getString(_emailAddress);
    }

    public void setEmailAddress(String emailAddress) {
        _emailAddress = emailAddress;
    }

    public MessagingMessageRecipient toEscapedModel() {
        if (isEscapedModel()) {
            return (MessagingMessageRecipient) this;
        } else {
            MessagingMessageRecipient model = new MessagingMessageRecipientImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setRecipientId(getRecipientId());
            model.setMessageId(getMessageId());
            model.setUserId(getUserId());
            model.setEmailAddress(HtmlUtil.escape(getEmailAddress()));

            model = (MessagingMessageRecipient) Proxy.newProxyInstance(MessagingMessageRecipient.class.getClassLoader(),
                    new Class[] { MessagingMessageRecipient.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        MessagingMessageRecipientImpl clone = new MessagingMessageRecipientImpl();

        clone.setRecipientId(getRecipientId());
        clone.setMessageId(getMessageId());
        clone.setUserId(getUserId());
        clone.setEmailAddress(getEmailAddress());

        return clone;
    }

    public int compareTo(MessagingMessageRecipient messagingMessageRecipient) {
        Long pk = messagingMessageRecipient.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        MessagingMessageRecipient messagingMessageRecipient = null;

        try {
            messagingMessageRecipient = (MessagingMessageRecipient) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = messagingMessageRecipient.getPrimaryKey();

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

        sb.append("{recipientId=");
        sb.append(getRecipientId());
        sb.append(", messageId=");
        sb.append(getMessageId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", emailAddress=");
        sb.append(getEmailAddress());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append(
            "com.ext.portlet.mass_messaging.model.MessagingMessageRecipient");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>recipientId</column-name><column-value><![CDATA[");
        sb.append(getRecipientId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>messageId</column-name><column-value><![CDATA[");
        sb.append(getMessageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>emailAddress</column-name><column-value><![CDATA[");
        sb.append(getEmailAddress());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
