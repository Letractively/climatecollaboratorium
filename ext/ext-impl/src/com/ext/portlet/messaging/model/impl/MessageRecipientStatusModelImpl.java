package com.ext.portlet.messaging.model.impl;

import com.ext.portlet.messaging.model.MessageRecipientStatus;
import com.ext.portlet.messaging.model.MessageRecipientStatusSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="MessageRecipientStatusModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>MessageRecipientStatus</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.messaging.model.MessageRecipientStatus
 * @see com.ext.portlet.messaging.model.MessageRecipientStatusModel
 * @see com.ext.portlet.messaging.model.impl.MessageRecipientStatusImpl
 *
 */
public class MessageRecipientStatusModelImpl extends BaseModelImpl<MessageRecipientStatus> {
    public static final String TABLE_NAME = "MessageRecipientStatus";
    public static final Object[][] TABLE_COLUMNS = {
            { "messageRecipientId", new Integer(Types.BIGINT) },
            

            { "messageId", new Integer(Types.BIGINT) },
            

            { "userId", new Integer(Types.BIGINT) },
            

            { "opened", new Integer(Types.BOOLEAN) },
            

            { "archived", new Integer(Types.BOOLEAN) }
        };
    public static final String TABLE_SQL_CREATE = "create table MessageRecipientStatus (messageRecipientId LONG not null primary key,messageId LONG,userId LONG,opened BOOLEAN,archived BOOLEAN)";
    public static final String TABLE_SQL_DROP = "drop table MessageRecipientStatus";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.messaging.model.MessageRecipientStatus"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.messaging.model.MessageRecipientStatus"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.messaging.model.MessageRecipientStatus"));
    private Long _messageRecipientId;
    private Long _messageId;
    private Long _originalMessageId;
    private Long _userId;
    private Long _originalUserId;
    private Boolean _opened;
    private Boolean _archived;

    public MessageRecipientStatusModelImpl() {
    }

    public static MessageRecipientStatus toModel(
        MessageRecipientStatusSoap soapModel) {
        MessageRecipientStatus model = new MessageRecipientStatusImpl();

        model.setMessageRecipientId(soapModel.getMessageRecipientId());
        model.setMessageId(soapModel.getMessageId());
        model.setUserId(soapModel.getUserId());
        model.setOpened(soapModel.getOpened());
        model.setArchived(soapModel.getArchived());

        return model;
    }

    public static List<MessageRecipientStatus> toModels(
        MessageRecipientStatusSoap[] soapModels) {
        List<MessageRecipientStatus> models = new ArrayList<MessageRecipientStatus>(soapModels.length);

        for (MessageRecipientStatusSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _messageRecipientId;
    }

    public void setPrimaryKey(Long pk) {
        setMessageRecipientId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _messageRecipientId;
    }

    public Long getMessageRecipientId() {
        return _messageRecipientId;
    }

    public void setMessageRecipientId(Long messageRecipientId) {
        _messageRecipientId = messageRecipientId;
    }

    public Long getMessageId() {
        return _messageId;
    }

    public void setMessageId(Long messageId) {
        _messageId = messageId;

        if (_originalMessageId == null) {
            _originalMessageId = messageId;
        }
    }

    public Long getOriginalMessageId() {
        return _originalMessageId;
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

    public Boolean getOpened() {
        return _opened;
    }

    public void setOpened(Boolean opened) {
        _opened = opened;
    }

    public Boolean getArchived() {
        return _archived;
    }

    public void setArchived(Boolean archived) {
        _archived = archived;
    }

    public MessageRecipientStatus toEscapedModel() {
        if (isEscapedModel()) {
            return (MessageRecipientStatus) this;
        } else {
            MessageRecipientStatus model = new MessageRecipientStatusImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setMessageRecipientId(getMessageRecipientId());
            model.setMessageId(getMessageId());
            model.setUserId(getUserId());
            model.setOpened(getOpened());
            model.setArchived(getArchived());

            model = (MessageRecipientStatus) Proxy.newProxyInstance(MessageRecipientStatus.class.getClassLoader(),
                    new Class[] { MessageRecipientStatus.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        MessageRecipientStatusImpl clone = new MessageRecipientStatusImpl();

        clone.setMessageRecipientId(getMessageRecipientId());
        clone.setMessageId(getMessageId());
        clone.setUserId(getUserId());
        clone.setOpened(getOpened());
        clone.setArchived(getArchived());

        return clone;
    }

    public int compareTo(MessageRecipientStatus messageRecipientStatus) {
        int value = 0;

        value = getMessageRecipientId()
                    .compareTo(messageRecipientStatus.getMessageRecipientId());

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

        MessageRecipientStatus messageRecipientStatus = null;

        try {
            messageRecipientStatus = (MessageRecipientStatus) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = messageRecipientStatus.getPrimaryKey();

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

        sb.append("{messageRecipientId=");
        sb.append(getMessageRecipientId());
        sb.append(", messageId=");
        sb.append(getMessageId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", opened=");
        sb.append(getOpened());
        sb.append(", archived=");
        sb.append(getArchived());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.messaging.model.MessageRecipientStatus");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>messageRecipientId</column-name><column-value><![CDATA[");
        sb.append(getMessageRecipientId());
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
            "<column><column-name>opened</column-name><column-value><![CDATA[");
        sb.append(getOpened());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>archived</column-name><column-value><![CDATA[");
        sb.append(getArchived());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
