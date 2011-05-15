package com.ext.portlet.debaterevision.model.impl;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateSoap;

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
 * <a href="DebateModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>Debate</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.model.Debate
 * @see com.ext.portlet.debaterevision.model.DebateModel
 * @see com.ext.portlet.debaterevision.model.impl.DebateImpl
 *
 */
public class DebateModelImpl extends BaseModelImpl<Debate> {
    public static final String TABLE_NAME = "Debate";
    public static final Object[][] TABLE_COLUMNS = {
            { "debatePK", new Integer(Types.BIGINT) },
            

            { "debateId", new Integer(Types.BIGINT) },
            

            { "treeVersion", new Integer(Types.BIGINT) },
            

            { "updated", new Integer(Types.TIMESTAMP) },
            

            { "status", new Integer(Types.VARCHAR) },
            

            { "rootCommentId", new Integer(Types.BIGINT) },
            

            { "authorId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table Debate (debatePK LONG not null primary key,debateId LONG,treeVersion LONG,updated DATE null,status VARCHAR(75) null,rootCommentId LONG,authorId LONG)";
    public static final String TABLE_SQL_DROP = "drop table Debate";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.debaterevision.model.Debate"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.debaterevision.model.Debate"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.debaterevision.model.Debate"));
    private Long _debatePK;
    private Long _debateId;
    private Long _treeVersion;
    private Date _updated;
    private String _status;
    private Long _rootCommentId;
    private Long _authorId;

    public DebateModelImpl() {
    }

    public static Debate toModel(DebateSoap soapModel) {
        Debate model = new DebateImpl();

        model.setDebatePK(soapModel.getDebatePK());
        model.setDebateId(soapModel.getDebateId());
        model.setTreeVersion(soapModel.getTreeVersion());
        model.setUpdated(soapModel.getUpdated());
        model.setStatus(soapModel.getStatus());
        model.setRootCommentId(soapModel.getRootCommentId());
        model.setAuthorId(soapModel.getAuthorId());

        return model;
    }

    public static List<Debate> toModels(DebateSoap[] soapModels) {
        List<Debate> models = new ArrayList<Debate>(soapModels.length);

        for (DebateSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _debatePK;
    }

    public void setPrimaryKey(Long pk) {
        setDebatePK(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _debatePK;
    }

    public Long getDebatePK() {
        return _debatePK;
    }

    public void setDebatePK(Long debatePK) {
        _debatePK = debatePK;
    }

    public Long getDebateId() {
        return _debateId;
    }

    public void setDebateId(Long debateId) {
        _debateId = debateId;
    }

    public Long getTreeVersion() {
        return _treeVersion;
    }

    public void setTreeVersion(Long treeVersion) {
        _treeVersion = treeVersion;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }

    public String getStatus() {
        return GetterUtil.getString(_status);
    }

    public void setStatus(String status) {
        _status = status;
    }

    public Long getRootCommentId() {
        return _rootCommentId;
    }

    public void setRootCommentId(Long rootCommentId) {
        _rootCommentId = rootCommentId;
    }

    public Long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(Long authorId) {
        _authorId = authorId;
    }

    public Debate toEscapedModel() {
        if (isEscapedModel()) {
            return (Debate) this;
        } else {
            Debate model = new DebateImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setDebatePK(getDebatePK());
            model.setDebateId(getDebateId());
            model.setTreeVersion(getTreeVersion());
            model.setUpdated(getUpdated());
            model.setStatus(HtmlUtil.escape(getStatus()));
            model.setRootCommentId(getRootCommentId());
            model.setAuthorId(getAuthorId());

            model = (Debate) Proxy.newProxyInstance(Debate.class.getClassLoader(),
                    new Class[] { Debate.class }, new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        DebateImpl clone = new DebateImpl();

        clone.setDebatePK(getDebatePK());
        clone.setDebateId(getDebateId());
        clone.setTreeVersion(getTreeVersion());
        clone.setUpdated(getUpdated());
        clone.setStatus(getStatus());
        clone.setRootCommentId(getRootCommentId());
        clone.setAuthorId(getAuthorId());

        return clone;
    }

    public int compareTo(Debate debate) {
        int value = 0;

        value = getDebateId().compareTo(debate.getDebateId());

        if (value != 0) {
            return value;
        }

        value = getTreeVersion().compareTo(debate.getTreeVersion());

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        Debate debate = null;

        try {
            debate = (Debate) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = debate.getPrimaryKey();

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

        sb.append("{debatePK=");
        sb.append(getDebatePK());
        sb.append(", debateId=");
        sb.append(getDebateId());
        sb.append(", treeVersion=");
        sb.append(getTreeVersion());
        sb.append(", updated=");
        sb.append(getUpdated());
        sb.append(", status=");
        sb.append(getStatus());
        sb.append(", rootCommentId=");
        sb.append(getRootCommentId());
        sb.append(", authorId=");
        sb.append(getAuthorId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.debaterevision.model.Debate");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>debatePK</column-name><column-value><![CDATA[");
        sb.append(getDebatePK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debateId</column-name><column-value><![CDATA[");
        sb.append(getDebateId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>treeVersion</column-name><column-value><![CDATA[");
        sb.append(getTreeVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updated</column-name><column-value><![CDATA[");
        sb.append(getUpdated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>status</column-name><column-value><![CDATA[");
        sb.append(getStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rootCommentId</column-name><column-value><![CDATA[");
        sb.append(getRootCommentId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorId</column-name><column-value><![CDATA[");
        sb.append(getAuthorId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
