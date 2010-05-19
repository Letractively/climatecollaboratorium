package com.ext.portlet.debatemigration.model.impl;

import com.ext.portlet.debatemigration.model.DebateMigrationComment;
import com.ext.portlet.debatemigration.model.DebateMigrationCommentSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="DebateMigrationCommentModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>DebateMigrationComment</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.model.DebateMigrationComment
 * @see com.ext.portlet.debatemigration.model.DebateMigrationCommentModel
 * @see com.ext.portlet.debatemigration.model.impl.DebateMigrationCommentImpl
 *
 */
public class DebateMigrationCommentModelImpl extends BaseModelImpl<DebateMigrationComment> {
    public static final String TABLE_NAME = "DebateMigrationComment";
    public static final Object[][] TABLE_COLUMNS = {
            { "debateMigrationCommentPK", new Integer(Types.BIGINT) },
            

            { "debateMigrationId", new Integer(Types.BIGINT) },
            

            { "oldMBMessageId", new Integer(Types.BIGINT) },
            

            { "oldMBThreadId", new Integer(Types.BIGINT) },
            

            { "newDebateCommentId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table DebateMigrationComment (debateMigrationCommentPK LONG not null primary key,debateMigrationId LONG,oldMBMessageId LONG,oldMBThreadId LONG,newDebateCommentId LONG)";
    public static final String TABLE_SQL_DROP = "drop table DebateMigrationComment";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.debatemigration.model.DebateMigrationComment"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.debatemigration.model.DebateMigrationComment"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.debatemigration.model.DebateMigrationComment"));
    private Long _debateMigrationCommentPK;
    private Long _debateMigrationId;
    private Long _oldMBMessageId;
    private Long _oldMBThreadId;
    private Long _newDebateCommentId;

    public DebateMigrationCommentModelImpl() {
    }

    public static DebateMigrationComment toModel(
        DebateMigrationCommentSoap soapModel) {
        DebateMigrationComment model = new DebateMigrationCommentImpl();

        model.setDebateMigrationCommentPK(soapModel.getDebateMigrationCommentPK());
        model.setDebateMigrationId(soapModel.getDebateMigrationId());
        model.setOldMBMessageId(soapModel.getOldMBMessageId());
        model.setOldMBThreadId(soapModel.getOldMBThreadId());
        model.setNewDebateCommentId(soapModel.getNewDebateCommentId());

        return model;
    }

    public static List<DebateMigrationComment> toModels(
        DebateMigrationCommentSoap[] soapModels) {
        List<DebateMigrationComment> models = new ArrayList<DebateMigrationComment>(soapModels.length);

        for (DebateMigrationCommentSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _debateMigrationCommentPK;
    }

    public void setPrimaryKey(Long pk) {
        setDebateMigrationCommentPK(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _debateMigrationCommentPK;
    }

    public Long getDebateMigrationCommentPK() {
        return _debateMigrationCommentPK;
    }

    public void setDebateMigrationCommentPK(Long debateMigrationCommentPK) {
        _debateMigrationCommentPK = debateMigrationCommentPK;
    }

    public Long getDebateMigrationId() {
        return _debateMigrationId;
    }

    public void setDebateMigrationId(Long debateMigrationId) {
        _debateMigrationId = debateMigrationId;
    }

    public Long getOldMBMessageId() {
        return _oldMBMessageId;
    }

    public void setOldMBMessageId(Long oldMBMessageId) {
        _oldMBMessageId = oldMBMessageId;
    }

    public Long getOldMBThreadId() {
        return _oldMBThreadId;
    }

    public void setOldMBThreadId(Long oldMBThreadId) {
        _oldMBThreadId = oldMBThreadId;
    }

    public Long getNewDebateCommentId() {
        return _newDebateCommentId;
    }

    public void setNewDebateCommentId(Long newDebateCommentId) {
        _newDebateCommentId = newDebateCommentId;
    }

    public DebateMigrationComment toEscapedModel() {
        if (isEscapedModel()) {
            return (DebateMigrationComment) this;
        } else {
            DebateMigrationComment model = new DebateMigrationCommentImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setDebateMigrationCommentPK(getDebateMigrationCommentPK());
            model.setDebateMigrationId(getDebateMigrationId());
            model.setOldMBMessageId(getOldMBMessageId());
            model.setOldMBThreadId(getOldMBThreadId());
            model.setNewDebateCommentId(getNewDebateCommentId());

            model = (DebateMigrationComment) Proxy.newProxyInstance(DebateMigrationComment.class.getClassLoader(),
                    new Class[] { DebateMigrationComment.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        DebateMigrationCommentImpl clone = new DebateMigrationCommentImpl();

        clone.setDebateMigrationCommentPK(getDebateMigrationCommentPK());
        clone.setDebateMigrationId(getDebateMigrationId());
        clone.setOldMBMessageId(getOldMBMessageId());
        clone.setOldMBThreadId(getOldMBThreadId());
        clone.setNewDebateCommentId(getNewDebateCommentId());

        return clone;
    }

    public int compareTo(DebateMigrationComment debateMigrationComment) {
        Long pk = debateMigrationComment.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        DebateMigrationComment debateMigrationComment = null;

        try {
            debateMigrationComment = (DebateMigrationComment) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = debateMigrationComment.getPrimaryKey();

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

        sb.append("{debateMigrationCommentPK=");
        sb.append(getDebateMigrationCommentPK());
        sb.append(", debateMigrationId=");
        sb.append(getDebateMigrationId());
        sb.append(", oldMBMessageId=");
        sb.append(getOldMBMessageId());
        sb.append(", oldMBThreadId=");
        sb.append(getOldMBThreadId());
        sb.append(", newDebateCommentId=");
        sb.append(getNewDebateCommentId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append(
            "com.ext.portlet.debatemigration.model.DebateMigrationComment");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>debateMigrationCommentPK</column-name><column-value><![CDATA[");
        sb.append(getDebateMigrationCommentPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debateMigrationId</column-name><column-value><![CDATA[");
        sb.append(getDebateMigrationId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>oldMBMessageId</column-name><column-value><![CDATA[");
        sb.append(getOldMBMessageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>oldMBThreadId</column-name><column-value><![CDATA[");
        sb.append(getOldMBThreadId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>newDebateCommentId</column-name><column-value><![CDATA[");
        sb.append(getNewDebateCommentId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
