/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.model.impl;

import com.ext.portlet.mass_messaging.model.MessagingMessage;
import com.ext.portlet.mass_messaging.model.MessagingMessageSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.DateUtil;
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
 * <a href="MessagingMessageModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>MessagingMessage</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.model.MessagingMessage
 * @see com.ext.portlet.mass_messaging.model.MessagingMessageModel
 * @see com.ext.portlet.mass_messaging.model.impl.MessagingMessageImpl
 *
 */
public class MessagingMessageModelImpl extends BaseModelImpl<MessagingMessage> {
    public static final String TABLE_NAME = "MessagingMessage";
    public static final Object[][] TABLE_COLUMNS = {
            { "messageId", new Integer(Types.BIGINT) },
            

            { "name", new Integer(Types.VARCHAR) },
            

            { "description", new Integer(Types.VARCHAR) },
            

            { "subject", new Integer(Types.VARCHAR) },
            

            { "body", new Integer(Types.VARCHAR) },
            

            { "replyTo", new Integer(Types.VARCHAR) },
            

            { "sendToAll", new Integer(Types.BOOLEAN) },
            

            { "conversionCount", new Integer(Types.BIGINT) },
            

            { "redirectURL", new Integer(Types.VARCHAR) },
            

            { "creatorId", new Integer(Types.BIGINT) },
            

            { "createDate", new Integer(Types.TIMESTAMP) },
            

            { "modifiedDate", new Integer(Types.TIMESTAMP) }
        };
    public static final String TABLE_SQL_CREATE = "create table MessagingMessage (messageId LONG not null primary key,name VARCHAR(75) null,description VARCHAR(75) null,subject VARCHAR(75) null,body VARCHAR(75) null,replyTo VARCHAR(75) null,sendToAll BOOLEAN,conversionCount LONG,redirectURL VARCHAR(75) null,creatorId LONG,createDate DATE null,modifiedDate DATE null)";
    public static final String TABLE_SQL_DROP = "drop table MessagingMessage";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.mass_messaging.model.MessagingMessage"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.mass_messaging.model.MessagingMessage"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.mass_messaging.model.MessagingMessage"));
    private Long _messageId;
    private String _name;
    private String _description;
    private String _subject;
    private String _body;
    private String _replyTo;
    private Boolean _sendToAll;
    private Long _conversionCount;
    private String _redirectURL;
    private Long _creatorId;
    private Date _createDate;
    private Date _modifiedDate;

    public MessagingMessageModelImpl() {
    }

    public static MessagingMessage toModel(MessagingMessageSoap soapModel) {
        MessagingMessage model = new MessagingMessageImpl();

        model.setMessageId(soapModel.getMessageId());
        model.setName(soapModel.getName());
        model.setDescription(soapModel.getDescription());
        model.setSubject(soapModel.getSubject());
        model.setBody(soapModel.getBody());
        model.setReplyTo(soapModel.getReplyTo());
        model.setSendToAll(soapModel.getSendToAll());
        model.setConversionCount(soapModel.getConversionCount());
        model.setRedirectURL(soapModel.getRedirectURL());
        model.setCreatorId(soapModel.getCreatorId());
        model.setCreateDate(soapModel.getCreateDate());
        model.setModifiedDate(soapModel.getModifiedDate());

        return model;
    }

    public static List<MessagingMessage> toModels(
        MessagingMessageSoap[] soapModels) {
        List<MessagingMessage> models = new ArrayList<MessagingMessage>(soapModels.length);

        for (MessagingMessageSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _messageId;
    }

    public void setPrimaryKey(Long pk) {
        setMessageId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _messageId;
    }

    public Long getMessageId() {
        return _messageId;
    }

    public void setMessageId(Long messageId) {
        _messageId = messageId;
    }

    public String getName() {
        return GetterUtil.getString(_name);
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return GetterUtil.getString(_description);
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getSubject() {
        return GetterUtil.getString(_subject);
    }

    public void setSubject(String subject) {
        _subject = subject;
    }

    public String getBody() {
        return GetterUtil.getString(_body);
    }

    public void setBody(String body) {
        _body = body;
    }

    public String getReplyTo() {
        return GetterUtil.getString(_replyTo);
    }

    public void setReplyTo(String replyTo) {
        _replyTo = replyTo;
    }

    public Boolean getSendToAll() {
        return _sendToAll;
    }

    public void setSendToAll(Boolean sendToAll) {
        _sendToAll = sendToAll;
    }

    public Long getConversionCount() {
        return _conversionCount;
    }

    public void setConversionCount(Long conversionCount) {
        _conversionCount = conversionCount;
    }

    public String getRedirectURL() {
        return GetterUtil.getString(_redirectURL);
    }

    public void setRedirectURL(String redirectURL) {
        _redirectURL = redirectURL;
    }

    public Long getCreatorId() {
        return _creatorId;
    }

    public void setCreatorId(Long creatorId) {
        _creatorId = creatorId;
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

    public MessagingMessage toEscapedModel() {
        if (isEscapedModel()) {
            return (MessagingMessage) this;
        } else {
            MessagingMessage model = new MessagingMessageImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setMessageId(getMessageId());
            model.setName(HtmlUtil.escape(getName()));
            model.setDescription(HtmlUtil.escape(getDescription()));
            model.setSubject(HtmlUtil.escape(getSubject()));
            model.setBody(HtmlUtil.escape(getBody()));
            model.setReplyTo(HtmlUtil.escape(getReplyTo()));
            model.setSendToAll(getSendToAll());
            model.setConversionCount(getConversionCount());
            model.setRedirectURL(HtmlUtil.escape(getRedirectURL()));
            model.setCreatorId(getCreatorId());
            model.setCreateDate(getCreateDate());
            model.setModifiedDate(getModifiedDate());

            model = (MessagingMessage) Proxy.newProxyInstance(MessagingMessage.class.getClassLoader(),
                    new Class[] { MessagingMessage.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        MessagingMessageImpl clone = new MessagingMessageImpl();

        clone.setMessageId(getMessageId());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setSubject(getSubject());
        clone.setBody(getBody());
        clone.setReplyTo(getReplyTo());
        clone.setSendToAll(getSendToAll());
        clone.setConversionCount(getConversionCount());
        clone.setRedirectURL(getRedirectURL());
        clone.setCreatorId(getCreatorId());
        clone.setCreateDate(getCreateDate());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    public int compareTo(MessagingMessage messagingMessage) {
        int value = 0;

        value = DateUtil.compareTo(getCreateDate(),
                messagingMessage.getCreateDate());

        value = value * -1;

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        MessagingMessage messagingMessage = null;

        try {
            messagingMessage = (MessagingMessage) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = messagingMessage.getPrimaryKey();

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

        sb.append("{messageId=");
        sb.append(getMessageId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", subject=");
        sb.append(getSubject());
        sb.append(", body=");
        sb.append(getBody());
        sb.append(", replyTo=");
        sb.append(getReplyTo());
        sb.append(", sendToAll=");
        sb.append(getSendToAll());
        sb.append(", conversionCount=");
        sb.append(getConversionCount());
        sb.append(", redirectURL=");
        sb.append(getRedirectURL());
        sb.append(", creatorId=");
        sb.append(getCreatorId());
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
        sb.append("com.ext.portlet.mass_messaging.model.MessagingMessage");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>messageId</column-name><column-value><![CDATA[");
        sb.append(getMessageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>subject</column-name><column-value><![CDATA[");
        sb.append(getSubject());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>body</column-name><column-value><![CDATA[");
        sb.append(getBody());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>replyTo</column-name><column-value><![CDATA[");
        sb.append(getReplyTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sendToAll</column-name><column-value><![CDATA[");
        sb.append(getSendToAll());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>conversionCount</column-name><column-value><![CDATA[");
        sb.append(getConversionCount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>redirectURL</column-name><column-value><![CDATA[");
        sb.append(getRedirectURL());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>creatorId</column-name><column-value><![CDATA[");
        sb.append(getCreatorId());
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
