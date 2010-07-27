package com.ext.portlet.discussions.model.impl;

import com.ext.portlet.discussions.model.DiscussionMessage;
import com.ext.portlet.discussions.model.DiscussionMessageSoap;

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
 * <a href="DiscussionMessageModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>DiscussionMessage</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.model.DiscussionMessage
 * @see com.ext.portlet.discussions.model.DiscussionMessageModel
 * @see com.ext.portlet.discussions.model.impl.DiscussionMessageImpl
 *
 */
public class DiscussionMessageModelImpl extends BaseModelImpl<DiscussionMessage> {
    public static final String TABLE_NAME = "DiscussionMessage";
    public static final Object[][] TABLE_COLUMNS = {
            { "pk", new Integer(Types.BIGINT) },
            

            { "messageId", new Integer(Types.BIGINT) },
            

            { "subject", new Integer(Types.VARCHAR) },
            

            { "body", new Integer(Types.VARCHAR) },
            

            { "threadId", new Integer(Types.BIGINT) },
            

            { "categoryId", new Integer(Types.BIGINT) },
            

            { "categoryGroupId", new Integer(Types.BIGINT) },
            

            { "authorId", new Integer(Types.BIGINT) },
            

            { "createDate", new Integer(Types.TIMESTAMP) },
            

            { "version", new Integer(Types.BIGINT) },
            

            { "deleted", new Integer(Types.TIMESTAMP) },
            

            { "responsesCount", new Integer(Types.INTEGER) },
            

            { "lastActivityDate", new Integer(Types.TIMESTAMP) },
            

            { "lastActivityAuthorId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table DiscussionMessage (pk LONG not null primary key,messageId LONG,subject VARCHAR(75) null,body VARCHAR(75) null,threadId LONG,categoryId LONG,categoryGroupId LONG,authorId LONG,createDate DATE null,version LONG,deleted DATE null,responsesCount INTEGER,lastActivityDate DATE null,lastActivityAuthorId LONG)";
    public static final String TABLE_SQL_DROP = "drop table DiscussionMessage";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.discussions.model.DiscussionMessage"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.discussions.model.DiscussionMessage"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.discussions.model.DiscussionMessage"));
    private Long _pk;
    private Long _messageId;
    private String _subject;
    private String _body;
    private Long _threadId;
    private Long _originalThreadId;
    private Long _categoryId;
    private Long _categoryGroupId;
    private Long _authorId;
    private Date _createDate;
    private Long _version;
    private Date _deleted;
    private Integer _responsesCount;
    private Date _lastActivityDate;
    private Long _lastActivityAuthorId;

    public DiscussionMessageModelImpl() {
    }

    public static DiscussionMessage toModel(DiscussionMessageSoap soapModel) {
        DiscussionMessage model = new DiscussionMessageImpl();

        model.setPk(soapModel.getPk());
        model.setMessageId(soapModel.getMessageId());
        model.setSubject(soapModel.getSubject());
        model.setBody(soapModel.getBody());
        model.setThreadId(soapModel.getThreadId());
        model.setCategoryId(soapModel.getCategoryId());
        model.setCategoryGroupId(soapModel.getCategoryGroupId());
        model.setAuthorId(soapModel.getAuthorId());
        model.setCreateDate(soapModel.getCreateDate());
        model.setVersion(soapModel.getVersion());
        model.setDeleted(soapModel.getDeleted());
        model.setResponsesCount(soapModel.getResponsesCount());
        model.setLastActivityDate(soapModel.getLastActivityDate());
        model.setLastActivityAuthorId(soapModel.getLastActivityAuthorId());

        return model;
    }

    public static List<DiscussionMessage> toModels(
        DiscussionMessageSoap[] soapModels) {
        List<DiscussionMessage> models = new ArrayList<DiscussionMessage>(soapModels.length);

        for (DiscussionMessageSoap soapModel : soapModels) {
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

    public Long getThreadId() {
        return _threadId;
    }

    public void setThreadId(Long threadId) {
        _threadId = threadId;

        if (_originalThreadId == null) {
            _originalThreadId = threadId;
        }
    }

    public Long getOriginalThreadId() {
        return _originalThreadId;
    }

    public Long getCategoryId() {
        return _categoryId;
    }

    public void setCategoryId(Long categoryId) {
        _categoryId = categoryId;
    }

    public Long getCategoryGroupId() {
        return _categoryGroupId;
    }

    public void setCategoryGroupId(Long categoryGroupId) {
        _categoryGroupId = categoryGroupId;
    }

    public Long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(Long authorId) {
        _authorId = authorId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Long getVersion() {
        return _version;
    }

    public void setVersion(Long version) {
        _version = version;
    }

    public Date getDeleted() {
        return _deleted;
    }

    public void setDeleted(Date deleted) {
        _deleted = deleted;
    }

    public Integer getResponsesCount() {
        return _responsesCount;
    }

    public void setResponsesCount(Integer responsesCount) {
        _responsesCount = responsesCount;
    }

    public Date getLastActivityDate() {
        return _lastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate) {
        _lastActivityDate = lastActivityDate;
    }

    public Long getLastActivityAuthorId() {
        return _lastActivityAuthorId;
    }

    public void setLastActivityAuthorId(Long lastActivityAuthorId) {
        _lastActivityAuthorId = lastActivityAuthorId;
    }

    public DiscussionMessage toEscapedModel() {
        if (isEscapedModel()) {
            return (DiscussionMessage) this;
        } else {
            DiscussionMessage model = new DiscussionMessageImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setPk(getPk());
            model.setMessageId(getMessageId());
            model.setSubject(HtmlUtil.escape(getSubject()));
            model.setBody(HtmlUtil.escape(getBody()));
            model.setThreadId(getThreadId());
            model.setCategoryId(getCategoryId());
            model.setCategoryGroupId(getCategoryGroupId());
            model.setAuthorId(getAuthorId());
            model.setCreateDate(getCreateDate());
            model.setVersion(getVersion());
            model.setDeleted(getDeleted());
            model.setResponsesCount(getResponsesCount());
            model.setLastActivityDate(getLastActivityDate());
            model.setLastActivityAuthorId(getLastActivityAuthorId());

            model = (DiscussionMessage) Proxy.newProxyInstance(DiscussionMessage.class.getClassLoader(),
                    new Class[] { DiscussionMessage.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        DiscussionMessageImpl clone = new DiscussionMessageImpl();

        clone.setPk(getPk());
        clone.setMessageId(getMessageId());
        clone.setSubject(getSubject());
        clone.setBody(getBody());
        clone.setThreadId(getThreadId());
        clone.setCategoryId(getCategoryId());
        clone.setCategoryGroupId(getCategoryGroupId());
        clone.setAuthorId(getAuthorId());
        clone.setCreateDate(getCreateDate());
        clone.setVersion(getVersion());
        clone.setDeleted(getDeleted());
        clone.setResponsesCount(getResponsesCount());
        clone.setLastActivityDate(getLastActivityDate());
        clone.setLastActivityAuthorId(getLastActivityAuthorId());

        return clone;
    }

    public int compareTo(DiscussionMessage discussionMessage) {
        int value = 0;

        value = DateUtil.compareTo(getCreateDate(),
                discussionMessage.getCreateDate());

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

        DiscussionMessage discussionMessage = null;

        try {
            discussionMessage = (DiscussionMessage) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = discussionMessage.getPrimaryKey();

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
        sb.append(", subject=");
        sb.append(getSubject());
        sb.append(", body=");
        sb.append(getBody());
        sb.append(", threadId=");
        sb.append(getThreadId());
        sb.append(", categoryId=");
        sb.append(getCategoryId());
        sb.append(", categoryGroupId=");
        sb.append(getCategoryGroupId());
        sb.append(", authorId=");
        sb.append(getAuthorId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append(", deleted=");
        sb.append(getDeleted());
        sb.append(", responsesCount=");
        sb.append(getResponsesCount());
        sb.append(", lastActivityDate=");
        sb.append(getLastActivityDate());
        sb.append(", lastActivityAuthorId=");
        sb.append(getLastActivityAuthorId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.discussions.model.DiscussionMessage");
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
            "<column><column-name>subject</column-name><column-value><![CDATA[");
        sb.append(getSubject());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>body</column-name><column-value><![CDATA[");
        sb.append(getBody());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>threadId</column-name><column-value><![CDATA[");
        sb.append(getThreadId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryId</column-name><column-value><![CDATA[");
        sb.append(getCategoryId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryGroupId</column-name><column-value><![CDATA[");
        sb.append(getCategoryGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorId</column-name><column-value><![CDATA[");
        sb.append(getAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>version</column-name><column-value><![CDATA[");
        sb.append(getVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deleted</column-name><column-value><![CDATA[");
        sb.append(getDeleted());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>responsesCount</column-name><column-value><![CDATA[");
        sb.append(getResponsesCount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastActivityDate</column-name><column-value><![CDATA[");
        sb.append(getLastActivityDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastActivityAuthorId</column-name><column-value><![CDATA[");
        sb.append(getLastActivityAuthorId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
