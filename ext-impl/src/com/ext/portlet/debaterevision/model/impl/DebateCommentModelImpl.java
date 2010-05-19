package com.ext.portlet.debaterevision.model.impl;

import com.ext.portlet.debaterevision.model.DebateComment;
import com.ext.portlet.debaterevision.model.DebateCommentSoap;

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
 * <a href="DebateCommentModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>DebateComment</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.model.DebateComment
 * @see com.ext.portlet.debaterevision.model.DebateCommentModel
 * @see com.ext.portlet.debaterevision.model.impl.DebateCommentImpl
 *
 */
public class DebateCommentModelImpl extends BaseModelImpl<DebateComment> {
    public static final String TABLE_NAME = "DebateComment";
    public static final Object[][] TABLE_COLUMNS = {
            { "debateCommentId", new Integer(Types.BIGINT) },
            

            { "debateItemId", new Integer(Types.BIGINT) },
            

            { "debateCommentTitle", new Integer(Types.VARCHAR) },
            

            { "debateCommentDetail", new Integer(Types.VARCHAR) },
            

            { "itemVersion", new Integer(Types.BIGINT) },
            

            { "authorId", new Integer(Types.BIGINT) },
            

            { "updated", new Integer(Types.TIMESTAMP) }
        };
    public static final String TABLE_SQL_CREATE = "create table DebateComment (debateCommentId LONG not null primary key,debateItemId LONG,debateCommentTitle VARCHAR(75) null,debateCommentDetail VARCHAR(75) null,itemVersion LONG,authorId LONG,updated DATE null)";
    public static final String TABLE_SQL_DROP = "drop table DebateComment";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.debaterevision.model.DebateComment"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.debaterevision.model.DebateComment"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.debaterevision.model.DebateComment"));
    private Long _debateCommentId;
    private Long _debateItemId;
    private String _debateCommentTitle;
    private String _debateCommentDetail;
    private Long _itemVersion;
    private Long _authorId;
    private Date _updated;

    public DebateCommentModelImpl() {
    }

    public static DebateComment toModel(DebateCommentSoap soapModel) {
        DebateComment model = new DebateCommentImpl();

        model.setDebateCommentId(soapModel.getDebateCommentId());
        model.setDebateItemId(soapModel.getDebateItemId());
        model.setDebateCommentTitle(soapModel.getDebateCommentTitle());
        model.setDebateCommentDetail(soapModel.getDebateCommentDetail());
        model.setItemVersion(soapModel.getItemVersion());
        model.setAuthorId(soapModel.getAuthorId());
        model.setUpdated(soapModel.getUpdated());

        return model;
    }

    public static List<DebateComment> toModels(DebateCommentSoap[] soapModels) {
        List<DebateComment> models = new ArrayList<DebateComment>(soapModels.length);

        for (DebateCommentSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _debateCommentId;
    }

    public void setPrimaryKey(Long pk) {
        setDebateCommentId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _debateCommentId;
    }

    public Long getDebateCommentId() {
        return _debateCommentId;
    }

    public void setDebateCommentId(Long debateCommentId) {
        _debateCommentId = debateCommentId;
    }

    public Long getDebateItemId() {
        return _debateItemId;
    }

    public void setDebateItemId(Long debateItemId) {
        _debateItemId = debateItemId;
    }

    public String getDebateCommentTitle() {
        return GetterUtil.getString(_debateCommentTitle);
    }

    public void setDebateCommentTitle(String debateCommentTitle) {
        _debateCommentTitle = debateCommentTitle;
    }

    public String getDebateCommentDetail() {
        return GetterUtil.getString(_debateCommentDetail);
    }

    public void setDebateCommentDetail(String debateCommentDetail) {
        _debateCommentDetail = debateCommentDetail;
    }

    public Long getItemVersion() {
        return _itemVersion;
    }

    public void setItemVersion(Long itemVersion) {
        _itemVersion = itemVersion;
    }

    public Long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(Long authorId) {
        _authorId = authorId;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }

    public DebateComment toEscapedModel() {
        if (isEscapedModel()) {
            return (DebateComment) this;
        } else {
            DebateComment model = new DebateCommentImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setDebateCommentId(getDebateCommentId());
            model.setDebateItemId(getDebateItemId());
            model.setDebateCommentTitle(HtmlUtil.escape(getDebateCommentTitle()));
            model.setDebateCommentDetail(HtmlUtil.escape(
                    getDebateCommentDetail()));
            model.setItemVersion(getItemVersion());
            model.setAuthorId(getAuthorId());
            model.setUpdated(getUpdated());

            model = (DebateComment) Proxy.newProxyInstance(DebateComment.class.getClassLoader(),
                    new Class[] { DebateComment.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        DebateCommentImpl clone = new DebateCommentImpl();

        clone.setDebateCommentId(getDebateCommentId());
        clone.setDebateItemId(getDebateItemId());
        clone.setDebateCommentTitle(getDebateCommentTitle());
        clone.setDebateCommentDetail(getDebateCommentDetail());
        clone.setItemVersion(getItemVersion());
        clone.setAuthorId(getAuthorId());
        clone.setUpdated(getUpdated());

        return clone;
    }

    public int compareTo(DebateComment debateComment) {
        int value = 0;

        value = DateUtil.compareTo(getUpdated(), debateComment.getUpdated());

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        DebateComment debateComment = null;

        try {
            debateComment = (DebateComment) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = debateComment.getPrimaryKey();

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

        sb.append("{debateCommentId=");
        sb.append(getDebateCommentId());
        sb.append(", debateItemId=");
        sb.append(getDebateItemId());
        sb.append(", debateCommentTitle=");
        sb.append(getDebateCommentTitle());
        sb.append(", debateCommentDetail=");
        sb.append(getDebateCommentDetail());
        sb.append(", itemVersion=");
        sb.append(getItemVersion());
        sb.append(", authorId=");
        sb.append(getAuthorId());
        sb.append(", updated=");
        sb.append(getUpdated());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.debaterevision.model.DebateComment");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>debateCommentId</column-name><column-value><![CDATA[");
        sb.append(getDebateCommentId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debateItemId</column-name><column-value><![CDATA[");
        sb.append(getDebateItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debateCommentTitle</column-name><column-value><![CDATA[");
        sb.append(getDebateCommentTitle());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debateCommentDetail</column-name><column-value><![CDATA[");
        sb.append(getDebateCommentDetail());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemVersion</column-name><column-value><![CDATA[");
        sb.append(getItemVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorId</column-name><column-value><![CDATA[");
        sb.append(getAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updated</column-name><column-value><![CDATA[");
        sb.append(getUpdated());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
