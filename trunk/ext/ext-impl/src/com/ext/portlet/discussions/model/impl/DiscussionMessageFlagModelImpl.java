package com.ext.portlet.discussions.model.impl;

import com.ext.portlet.discussions.model.DiscussionMessageFlag;
import com.ext.portlet.discussions.model.DiscussionMessageFlagSoap;

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
 * <a href="DiscussionMessageFlagModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>DiscussionMessageFlag</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.model.DiscussionMessageFlag
 * @see com.ext.portlet.discussions.model.DiscussionMessageFlagModel
 * @see com.ext.portlet.discussions.model.impl.DiscussionMessageFlagImpl
 *
 */
public class DiscussionMessageFlagModelImpl extends BaseModelImpl<DiscussionMessageFlag> {
    public static final String TABLE_NAME = "DiscussionMessageFlag";
    public static final Object[][] TABLE_COLUMNS = {
            { "pk", new Integer(Types.BIGINT) },
            

            { "messageId", new Integer(Types.BIGINT) },
            

            { "flagType", new Integer(Types.VARCHAR) },
            

            { "data_", new Integer(Types.VARCHAR) },
            

            { "created", new Integer(Types.TIMESTAMP) },
            

            { "userId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table DiscussionMessageFlag (pk LONG not null primary key,messageId LONG,flagType VARCHAR(75) null,data_ VARCHAR(75) null,created DATE null,userId LONG)";
    public static final String TABLE_SQL_DROP = "drop table DiscussionMessageFlag";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.discussions.model.DiscussionMessageFlag"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.discussions.model.DiscussionMessageFlag"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.discussions.model.DiscussionMessageFlag"));
    private Long _pk;
    private Long _messageId;
    private String _flagType;
    private String _data;
    private Date _created;
    private Long _userId;

    public DiscussionMessageFlagModelImpl() {
    }

    public static DiscussionMessageFlag toModel(
        DiscussionMessageFlagSoap soapModel) {
        DiscussionMessageFlag model = new DiscussionMessageFlagImpl();

        model.setPk(soapModel.getPk());
        model.setMessageId(soapModel.getMessageId());
        model.setFlagType(soapModel.getFlagType());
        model.setData(soapModel.getData());
        model.setCreated(soapModel.getCreated());
        model.setUserId(soapModel.getUserId());

        return model;
    }

    public static List<DiscussionMessageFlag> toModels(
        DiscussionMessageFlagSoap[] soapModels) {
        List<DiscussionMessageFlag> models = new ArrayList<DiscussionMessageFlag>(soapModels.length);

        for (DiscussionMessageFlagSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _pk;
    }

    public void setPrimaryKey(Long pk) {
        setPk(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _pk;
    }

    public Long getPk() {
        return _pk;
    }

    public void setPk(Long pk) {
        _pk = pk;
    }

    public Long getMessageId() {
        return _messageId;
    }

    public void setMessageId(Long messageId) {
        _messageId = messageId;
    }

    public String getFlagType() {
        return GetterUtil.getString(_flagType);
    }

    public void setFlagType(String flagType) {
        _flagType = flagType;
    }

    public String getData() {
        return GetterUtil.getString(_data);
    }

    public void setData(String data) {
        _data = data;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public DiscussionMessageFlag toEscapedModel() {
        if (isEscapedModel()) {
            return (DiscussionMessageFlag) this;
        } else {
            DiscussionMessageFlag model = new DiscussionMessageFlagImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setPk(getPk());
            model.setMessageId(getMessageId());
            model.setFlagType(HtmlUtil.escape(getFlagType()));
            model.setData(HtmlUtil.escape(getData()));
            model.setCreated(getCreated());
            model.setUserId(getUserId());

            model = (DiscussionMessageFlag) Proxy.newProxyInstance(DiscussionMessageFlag.class.getClassLoader(),
                    new Class[] { DiscussionMessageFlag.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        DiscussionMessageFlagImpl clone = new DiscussionMessageFlagImpl();

        clone.setPk(getPk());
        clone.setMessageId(getMessageId());
        clone.setFlagType(getFlagType());
        clone.setData(getData());
        clone.setCreated(getCreated());
        clone.setUserId(getUserId());

        return clone;
    }

    public int compareTo(DiscussionMessageFlag discussionMessageFlag) {
        Long pk = discussionMessageFlag.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        DiscussionMessageFlag discussionMessageFlag = null;

        try {
            discussionMessageFlag = (DiscussionMessageFlag) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = discussionMessageFlag.getPrimaryKey();

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

        sb.append("{pk=");
        sb.append(getPk());
        sb.append(", messageId=");
        sb.append(getMessageId());
        sb.append(", flagType=");
        sb.append(getFlagType());
        sb.append(", data=");
        sb.append(getData());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.discussions.model.DiscussionMessageFlag");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>pk</column-name><column-value><![CDATA[");
        sb.append(getPk());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>messageId</column-name><column-value><![CDATA[");
        sb.append(getMessageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>flagType</column-name><column-value><![CDATA[");
        sb.append(getFlagType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>data</column-name><column-value><![CDATA[");
        sb.append(getData());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
