package com.ext.portlet.messaging.model.impl;

import com.ext.portlet.messaging.model.Message;
import com.ext.portlet.messaging.model.MessageSoap;

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
 * <a href="MessageModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>Message</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.messaging.model.Message
 * @see com.ext.portlet.messaging.model.MessageModel
 * @see com.ext.portlet.messaging.model.impl.MessageImpl
 *
 */
public class MessageModelImpl extends BaseModelImpl<Message> {
    public static final String TABLE_NAME = "Message";
    public static final Object[][] TABLE_COLUMNS = {
            { "messageId", new Integer(Types.BIGINT) },
            

            { "fromId", new Integer(Types.BIGINT) },
            

            { "repliesTo", new Integer(Types.BIGINT) },
            

            { "createDate", new Integer(Types.TIMESTAMP) },
            

            { "subject", new Integer(Types.VARCHAR) },
            

            { "content", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table Message (messageId LONG not null primary key,fromId LONG,repliesTo LONG,createDate DATE null,subject VARCHAR(75) null,content VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table Message";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.messaging.model.Message"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.messaging.model.Message"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.messaging.model.Message"));
    private Long _messageId;
    private Long _fromId;
    private Long _repliesTo;
    private Date _createDate;
    private String _subject;
    private String _content;

    public MessageModelImpl() {
    }

    public static Message toModel(MessageSoap soapModel) {
        Message model = new MessageImpl();

        model.setMessageId(soapModel.getMessageId());
        model.setFromId(soapModel.getFromId());
        model.setRepliesTo(soapModel.getRepliesTo());
        model.setCreateDate(soapModel.getCreateDate());
        model.setSubject(soapModel.getSubject());
        model.setContent(soapModel.getContent());

        return model;
    }

    public static List<Message> toModels(MessageSoap[] soapModels) {
        List<Message> models = new ArrayList<Message>(soapModels.length);

        for (MessageSoap soapModel : soapModels) {
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

    public Long getFromId() {
        return _fromId;
    }

    public void setFromId(Long fromId) {
        _fromId = fromId;
    }

    public Long getRepliesTo() {
        return _repliesTo;
    }

    public void setRepliesTo(Long repliesTo) {
        _repliesTo = repliesTo;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public String getSubject() {
        return GetterUtil.getString(_subject);
    }

    public void setSubject(String subject) {
        _subject = subject;
    }

    public String getContent() {
        return GetterUtil.getString(_content);
    }

    public void setContent(String content) {
        _content = content;
    }

    public Message toEscapedModel() {
        if (isEscapedModel()) {
            return (Message) this;
        } else {
            Message model = new MessageImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setMessageId(getMessageId());
            model.setFromId(getFromId());
            model.setRepliesTo(getRepliesTo());
            model.setCreateDate(getCreateDate());
            model.setSubject(HtmlUtil.escape(getSubject()));
            model.setContent(HtmlUtil.escape(getContent()));

            model = (Message) Proxy.newProxyInstance(Message.class.getClassLoader(),
                    new Class[] { Message.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        MessageImpl clone = new MessageImpl();

        clone.setMessageId(getMessageId());
        clone.setFromId(getFromId());
        clone.setRepliesTo(getRepliesTo());
        clone.setCreateDate(getCreateDate());
        clone.setSubject(getSubject());
        clone.setContent(getContent());

        return clone;
    }

    public int compareTo(Message message) {
        int value = 0;

        value = DateUtil.compareTo(getCreateDate(), message.getCreateDate());

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

        Message message = null;

        try {
            message = (Message) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = message.getPrimaryKey();

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
        sb.append(", fromId=");
        sb.append(getFromId());
        sb.append(", repliesTo=");
        sb.append(getRepliesTo());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", subject=");
        sb.append(getSubject());
        sb.append(", content=");
        sb.append(getContent());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.messaging.model.Message");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>messageId</column-name><column-value><![CDATA[");
        sb.append(getMessageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fromId</column-name><column-value><![CDATA[");
        sb.append(getFromId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>repliesTo</column-name><column-value><![CDATA[");
        sb.append(getRepliesTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>subject</column-name><column-value><![CDATA[");
        sb.append(getSubject());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>content</column-name><column-value><![CDATA[");
        sb.append(getContent());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
