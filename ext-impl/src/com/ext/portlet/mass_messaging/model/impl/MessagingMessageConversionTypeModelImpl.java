/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.model.impl;

import com.ext.portlet.mass_messaging.model.MessagingMessageConversionType;
import com.ext.portlet.mass_messaging.model.MessagingMessageConversionTypeSoap;

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
 * <a href="MessagingMessageConversionTypeModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>MessagingMessageConversionType</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.model.MessagingMessageConversionType
 * @see com.ext.portlet.mass_messaging.model.MessagingMessageConversionTypeModel
 * @see com.ext.portlet.mass_messaging.model.impl.MessagingMessageConversionTypeImpl
 *
 */
public class MessagingMessageConversionTypeModelImpl extends BaseModelImpl<MessagingMessageConversionType> {
    public static final String TABLE_NAME = "MessagingMessageConversionType";
    public static final Object[][] TABLE_COLUMNS = {
            { "typeId", new Integer(Types.BIGINT) },
            

            { "name", new Integer(Types.VARCHAR) },
            

            { "description", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table MessagingMessageConversionType (typeId LONG not null primary key,name VARCHAR(75) null,description VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table MessagingMessageConversionType";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.mass_messaging.model.MessagingMessageConversionType"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.mass_messaging.model.MessagingMessageConversionType"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.mass_messaging.model.MessagingMessageConversionType"));
    private Long _typeId;
    private String _name;
    private String _originalName;
    private String _description;

    public MessagingMessageConversionTypeModelImpl() {
    }

    public static MessagingMessageConversionType toModel(
        MessagingMessageConversionTypeSoap soapModel) {
        MessagingMessageConversionType model = new MessagingMessageConversionTypeImpl();

        model.setTypeId(soapModel.getTypeId());
        model.setName(soapModel.getName());
        model.setDescription(soapModel.getDescription());

        return model;
    }

    public static List<MessagingMessageConversionType> toModels(
        MessagingMessageConversionTypeSoap[] soapModels) {
        List<MessagingMessageConversionType> models = new ArrayList<MessagingMessageConversionType>(soapModels.length);

        for (MessagingMessageConversionTypeSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _typeId;
    }

    public void setPrimaryKey(Long pk) {
        setTypeId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _typeId;
    }

    public Long getTypeId() {
        return _typeId;
    }

    public void setTypeId(Long typeId) {
        _typeId = typeId;
    }

    public String getName() {
        return GetterUtil.getString(_name);
    }

    public void setName(String name) {
        _name = name;

        if (_originalName == null) {
            _originalName = name;
        }
    }

    public String getOriginalName() {
        return GetterUtil.getString(_originalName);
    }

    public String getDescription() {
        return GetterUtil.getString(_description);
    }

    public void setDescription(String description) {
        _description = description;
    }

    public MessagingMessageConversionType toEscapedModel() {
        if (isEscapedModel()) {
            return (MessagingMessageConversionType) this;
        } else {
            MessagingMessageConversionType model = new MessagingMessageConversionTypeImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setTypeId(getTypeId());
            model.setName(HtmlUtil.escape(getName()));
            model.setDescription(HtmlUtil.escape(getDescription()));

            model = (MessagingMessageConversionType) Proxy.newProxyInstance(MessagingMessageConversionType.class.getClassLoader(),
                    new Class[] { MessagingMessageConversionType.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        MessagingMessageConversionTypeImpl clone = new MessagingMessageConversionTypeImpl();

        clone.setTypeId(getTypeId());
        clone.setName(getName());
        clone.setDescription(getDescription());

        return clone;
    }

    public int compareTo(
        MessagingMessageConversionType messagingMessageConversionType) {
        Long pk = messagingMessageConversionType.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        MessagingMessageConversionType messagingMessageConversionType = null;

        try {
            messagingMessageConversionType = (MessagingMessageConversionType) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = messagingMessageConversionType.getPrimaryKey();

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

        sb.append("{typeId=");
        sb.append(getTypeId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append(
            "com.ext.portlet.mass_messaging.model.MessagingMessageConversionType");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>typeId</column-name><column-value><![CDATA[");
        sb.append(getTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
