/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.model.impl;

import com.ext.portlet.mass_messaging.model.MessagingMessageConversion;
import com.ext.portlet.mass_messaging.model.MessagingMessageConversionSoap;

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
 * <a href="MessagingMessageConversionModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>MessagingMessageConversion</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.model.MessagingMessageConversion
 * @see com.ext.portlet.mass_messaging.model.MessagingMessageConversionModel
 * @see com.ext.portlet.mass_messaging.model.impl.MessagingMessageConversionImpl
 *
 */
public class MessagingMessageConversionModelImpl extends BaseModelImpl<MessagingMessageConversion> {
    public static final String TABLE_NAME = "MessagingMessageConversion";
    public static final Object[][] TABLE_COLUMNS = {
            { "conversionId", new Integer(Types.BIGINT) },
            

            { "conversionTypeId", new Integer(Types.BIGINT) },
            

            { "messageId", new Integer(Types.BIGINT) },
            

            { "ipAddress", new Integer(Types.VARCHAR) },
            

            { "extraData", new Integer(Types.VARCHAR) },
            

            { "extraData2", new Integer(Types.VARCHAR) },
            

            { "createDate", new Integer(Types.TIMESTAMP) }
        };
    public static final String TABLE_SQL_CREATE = "create table MessagingMessageConversion (conversionId LONG not null primary key,conversionTypeId LONG,messageId LONG,ipAddress VARCHAR(75) null,extraData VARCHAR(75) null,extraData2 VARCHAR(75) null,createDate DATE null)";
    public static final String TABLE_SQL_DROP = "drop table MessagingMessageConversion";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.mass_messaging.model.MessagingMessageConversion"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.mass_messaging.model.MessagingMessageConversion"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.mass_messaging.model.MessagingMessageConversion"));
    private Long _conversionId;
    private Long _conversionTypeId;
    private Long _messageId;
    private String _ipAddress;
    private String _extraData;
    private String _extraData2;
    private Date _createDate;

    public MessagingMessageConversionModelImpl() {
    }

    public static MessagingMessageConversion toModel(
        MessagingMessageConversionSoap soapModel) {
        MessagingMessageConversion model = new MessagingMessageConversionImpl();

        model.setConversionId(soapModel.getConversionId());
        model.setConversionTypeId(soapModel.getConversionTypeId());
        model.setMessageId(soapModel.getMessageId());
        model.setIpAddress(soapModel.getIpAddress());
        model.setExtraData(soapModel.getExtraData());
        model.setExtraData2(soapModel.getExtraData2());
        model.setCreateDate(soapModel.getCreateDate());

        return model;
    }

    public static List<MessagingMessageConversion> toModels(
        MessagingMessageConversionSoap[] soapModels) {
        List<MessagingMessageConversion> models = new ArrayList<MessagingMessageConversion>(soapModels.length);

        for (MessagingMessageConversionSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _conversionId;
    }

    public void setPrimaryKey(Long pk) {
        setConversionId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _conversionId;
    }

    public Long getConversionId() {
        return _conversionId;
    }

    public void setConversionId(Long conversionId) {
        _conversionId = conversionId;
    }

    public Long getConversionTypeId() {
        return _conversionTypeId;
    }

    public void setConversionTypeId(Long conversionTypeId) {
        _conversionTypeId = conversionTypeId;
    }

    public Long getMessageId() {
        return _messageId;
    }

    public void setMessageId(Long messageId) {
        _messageId = messageId;
    }

    public String getIpAddress() {
        return GetterUtil.getString(_ipAddress);
    }

    public void setIpAddress(String ipAddress) {
        _ipAddress = ipAddress;
    }

    public String getExtraData() {
        return GetterUtil.getString(_extraData);
    }

    public void setExtraData(String extraData) {
        _extraData = extraData;
    }

    public String getExtraData2() {
        return GetterUtil.getString(_extraData2);
    }

    public void setExtraData2(String extraData2) {
        _extraData2 = extraData2;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public MessagingMessageConversion toEscapedModel() {
        if (isEscapedModel()) {
            return (MessagingMessageConversion) this;
        } else {
            MessagingMessageConversion model = new MessagingMessageConversionImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setConversionId(getConversionId());
            model.setConversionTypeId(getConversionTypeId());
            model.setMessageId(getMessageId());
            model.setIpAddress(HtmlUtil.escape(getIpAddress()));
            model.setExtraData(HtmlUtil.escape(getExtraData()));
            model.setExtraData2(HtmlUtil.escape(getExtraData2()));
            model.setCreateDate(getCreateDate());

            model = (MessagingMessageConversion) Proxy.newProxyInstance(MessagingMessageConversion.class.getClassLoader(),
                    new Class[] { MessagingMessageConversion.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        MessagingMessageConversionImpl clone = new MessagingMessageConversionImpl();

        clone.setConversionId(getConversionId());
        clone.setConversionTypeId(getConversionTypeId());
        clone.setMessageId(getMessageId());
        clone.setIpAddress(getIpAddress());
        clone.setExtraData(getExtraData());
        clone.setExtraData2(getExtraData2());
        clone.setCreateDate(getCreateDate());

        return clone;
    }

    public int compareTo(MessagingMessageConversion messagingMessageConversion) {
        Long pk = messagingMessageConversion.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        MessagingMessageConversion messagingMessageConversion = null;

        try {
            messagingMessageConversion = (MessagingMessageConversion) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = messagingMessageConversion.getPrimaryKey();

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

        sb.append("{conversionId=");
        sb.append(getConversionId());
        sb.append(", conversionTypeId=");
        sb.append(getConversionTypeId());
        sb.append(", messageId=");
        sb.append(getMessageId());
        sb.append(", ipAddress=");
        sb.append(getIpAddress());
        sb.append(", extraData=");
        sb.append(getExtraData());
        sb.append(", extraData2=");
        sb.append(getExtraData2());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append(
            "com.ext.portlet.mass_messaging.model.MessagingMessageConversion");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>conversionId</column-name><column-value><![CDATA[");
        sb.append(getConversionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>conversionTypeId</column-name><column-value><![CDATA[");
        sb.append(getConversionTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>messageId</column-name><column-value><![CDATA[");
        sb.append(getMessageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ipAddress</column-name><column-value><![CDATA[");
        sb.append(getIpAddress());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>extraData</column-name><column-value><![CDATA[");
        sb.append(getExtraData());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>extraData2</column-name><column-value><![CDATA[");
        sb.append(getExtraData2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
