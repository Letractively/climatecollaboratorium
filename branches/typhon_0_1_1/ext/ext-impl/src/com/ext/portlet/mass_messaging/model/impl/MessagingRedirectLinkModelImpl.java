/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.model.impl;

import com.ext.portlet.mass_messaging.model.MessagingRedirectLink;
import com.ext.portlet.mass_messaging.model.MessagingRedirectLinkSoap;

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
 * <a href="MessagingRedirectLinkModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>MessagingRedirectLink</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.model.MessagingRedirectLink
 * @see com.ext.portlet.mass_messaging.model.MessagingRedirectLinkModel
 * @see com.ext.portlet.mass_messaging.model.impl.MessagingRedirectLinkImpl
 *
 */
public class MessagingRedirectLinkModelImpl extends BaseModelImpl<MessagingRedirectLink> {
    public static final String TABLE_NAME = "MessagingRedirectLink";
    public static final Object[][] TABLE_COLUMNS = {
            { "redirectId", new Integer(Types.BIGINT) },
            

            { "link", new Integer(Types.VARCHAR) },
            

            { "messageId", new Integer(Types.BIGINT) },
            

            { "createDate", new Integer(Types.TIMESTAMP) }
        };
    public static final String TABLE_SQL_CREATE = "create table MessagingRedirectLink (redirectId LONG not null primary key,link VARCHAR(75) null,messageId LONG,createDate DATE null)";
    public static final String TABLE_SQL_DROP = "drop table MessagingRedirectLink";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.mass_messaging.model.MessagingRedirectLink"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.mass_messaging.model.MessagingRedirectLink"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.mass_messaging.model.MessagingRedirectLink"));
    private Long _redirectId;
    private String _link;
    private Long _messageId;
    private Date _createDate;

    public MessagingRedirectLinkModelImpl() {
    }

    public static MessagingRedirectLink toModel(
        MessagingRedirectLinkSoap soapModel) {
        MessagingRedirectLink model = new MessagingRedirectLinkImpl();

        model.setRedirectId(soapModel.getRedirectId());
        model.setLink(soapModel.getLink());
        model.setMessageId(soapModel.getMessageId());
        model.setCreateDate(soapModel.getCreateDate());

        return model;
    }

    public static List<MessagingRedirectLink> toModels(
        MessagingRedirectLinkSoap[] soapModels) {
        List<MessagingRedirectLink> models = new ArrayList<MessagingRedirectLink>(soapModels.length);

        for (MessagingRedirectLinkSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _redirectId;
    }

    public void setPrimaryKey(Long pk) {
        setRedirectId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _redirectId;
    }

    public Long getRedirectId() {
        return _redirectId;
    }

    public void setRedirectId(Long redirectId) {
        _redirectId = redirectId;
    }

    public String getLink() {
        return GetterUtil.getString(_link);
    }

    public void setLink(String link) {
        _link = link;
    }

    public Long getMessageId() {
        return _messageId;
    }

    public void setMessageId(Long messageId) {
        _messageId = messageId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public MessagingRedirectLink toEscapedModel() {
        if (isEscapedModel()) {
            return (MessagingRedirectLink) this;
        } else {
            MessagingRedirectLink model = new MessagingRedirectLinkImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setRedirectId(getRedirectId());
            model.setLink(HtmlUtil.escape(getLink()));
            model.setMessageId(getMessageId());
            model.setCreateDate(getCreateDate());

            model = (MessagingRedirectLink) Proxy.newProxyInstance(MessagingRedirectLink.class.getClassLoader(),
                    new Class[] { MessagingRedirectLink.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        MessagingRedirectLinkImpl clone = new MessagingRedirectLinkImpl();

        clone.setRedirectId(getRedirectId());
        clone.setLink(getLink());
        clone.setMessageId(getMessageId());
        clone.setCreateDate(getCreateDate());

        return clone;
    }

    public int compareTo(MessagingRedirectLink messagingRedirectLink) {
        Long pk = messagingRedirectLink.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        MessagingRedirectLink messagingRedirectLink = null;

        try {
            messagingRedirectLink = (MessagingRedirectLink) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = messagingRedirectLink.getPrimaryKey();

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

        sb.append("{redirectId=");
        sb.append(getRedirectId());
        sb.append(", link=");
        sb.append(getLink());
        sb.append(", messageId=");
        sb.append(getMessageId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.mass_messaging.model.MessagingRedirectLink");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>redirectId</column-name><column-value><![CDATA[");
        sb.append(getRedirectId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>link</column-name><column-value><![CDATA[");
        sb.append(getLink());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>messageId</column-name><column-value><![CDATA[");
        sb.append(getMessageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
