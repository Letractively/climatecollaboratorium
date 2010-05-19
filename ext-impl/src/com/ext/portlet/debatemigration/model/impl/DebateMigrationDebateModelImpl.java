package com.ext.portlet.debatemigration.model.impl;

import com.ext.portlet.debatemigration.model.DebateMigrationDebate;
import com.ext.portlet.debatemigration.model.DebateMigrationDebateSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="DebateMigrationDebateModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>DebateMigrationDebate</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.model.DebateMigrationDebate
 * @see com.ext.portlet.debatemigration.model.DebateMigrationDebateModel
 * @see com.ext.portlet.debatemigration.model.impl.DebateMigrationDebateImpl
 *
 */
public class DebateMigrationDebateModelImpl extends BaseModelImpl<DebateMigrationDebate> {
    public static final String TABLE_NAME = "DebateMigrationDebate";
    public static final Object[][] TABLE_COLUMNS = {
            { "debateMigrationDebatePK", new Integer(Types.BIGINT) },
            

            { "debateMigrationId", new Integer(Types.BIGINT) },
            

            { "oldMBCategoryId", new Integer(Types.BIGINT) },
            

            { "newDebateId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table DebateMigrationDebate (debateMigrationDebatePK LONG not null primary key,debateMigrationId LONG,oldMBCategoryId LONG,newDebateId LONG)";
    public static final String TABLE_SQL_DROP = "drop table DebateMigrationDebate";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.debatemigration.model.DebateMigrationDebate"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.debatemigration.model.DebateMigrationDebate"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.debatemigration.model.DebateMigrationDebate"));
    private Long _debateMigrationDebatePK;
    private Long _debateMigrationId;
    private Long _oldMBCategoryId;
    private Long _newDebateId;

    public DebateMigrationDebateModelImpl() {
    }

    public static DebateMigrationDebate toModel(
        DebateMigrationDebateSoap soapModel) {
        DebateMigrationDebate model = new DebateMigrationDebateImpl();

        model.setDebateMigrationDebatePK(soapModel.getDebateMigrationDebatePK());
        model.setDebateMigrationId(soapModel.getDebateMigrationId());
        model.setOldMBCategoryId(soapModel.getOldMBCategoryId());
        model.setNewDebateId(soapModel.getNewDebateId());

        return model;
    }

    public static List<DebateMigrationDebate> toModels(
        DebateMigrationDebateSoap[] soapModels) {
        List<DebateMigrationDebate> models = new ArrayList<DebateMigrationDebate>(soapModels.length);

        for (DebateMigrationDebateSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _debateMigrationDebatePK;
    }

    public void setPrimaryKey(Long pk) {
        setDebateMigrationDebatePK(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _debateMigrationDebatePK;
    }

    public Long getDebateMigrationDebatePK() {
        return _debateMigrationDebatePK;
    }

    public void setDebateMigrationDebatePK(Long debateMigrationDebatePK) {
        _debateMigrationDebatePK = debateMigrationDebatePK;
    }

    public Long getDebateMigrationId() {
        return _debateMigrationId;
    }

    public void setDebateMigrationId(Long debateMigrationId) {
        _debateMigrationId = debateMigrationId;
    }

    public Long getOldMBCategoryId() {
        return _oldMBCategoryId;
    }

    public void setOldMBCategoryId(Long oldMBCategoryId) {
        _oldMBCategoryId = oldMBCategoryId;
    }

    public Long getNewDebateId() {
        return _newDebateId;
    }

    public void setNewDebateId(Long newDebateId) {
        _newDebateId = newDebateId;
    }

    public DebateMigrationDebate toEscapedModel() {
        if (isEscapedModel()) {
            return (DebateMigrationDebate) this;
        } else {
            DebateMigrationDebate model = new DebateMigrationDebateImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setDebateMigrationDebatePK(getDebateMigrationDebatePK());
            model.setDebateMigrationId(getDebateMigrationId());
            model.setOldMBCategoryId(getOldMBCategoryId());
            model.setNewDebateId(getNewDebateId());

            model = (DebateMigrationDebate) Proxy.newProxyInstance(DebateMigrationDebate.class.getClassLoader(),
                    new Class[] { DebateMigrationDebate.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        DebateMigrationDebateImpl clone = new DebateMigrationDebateImpl();

        clone.setDebateMigrationDebatePK(getDebateMigrationDebatePK());
        clone.setDebateMigrationId(getDebateMigrationId());
        clone.setOldMBCategoryId(getOldMBCategoryId());
        clone.setNewDebateId(getNewDebateId());

        return clone;
    }

    public int compareTo(DebateMigrationDebate debateMigrationDebate) {
        Long pk = debateMigrationDebate.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        DebateMigrationDebate debateMigrationDebate = null;

        try {
            debateMigrationDebate = (DebateMigrationDebate) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = debateMigrationDebate.getPrimaryKey();

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

        sb.append("{debateMigrationDebatePK=");
        sb.append(getDebateMigrationDebatePK());
        sb.append(", debateMigrationId=");
        sb.append(getDebateMigrationId());
        sb.append(", oldMBCategoryId=");
        sb.append(getOldMBCategoryId());
        sb.append(", newDebateId=");
        sb.append(getNewDebateId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.debatemigration.model.DebateMigrationDebate");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>debateMigrationDebatePK</column-name><column-value><![CDATA[");
        sb.append(getDebateMigrationDebatePK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debateMigrationId</column-name><column-value><![CDATA[");
        sb.append(getDebateMigrationId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>oldMBCategoryId</column-name><column-value><![CDATA[");
        sb.append(getOldMBCategoryId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>newDebateId</column-name><column-value><![CDATA[");
        sb.append(getNewDebateId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
