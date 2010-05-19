package com.ext.portlet.debatemigration.model.impl;

import com.ext.portlet.debatemigration.model.DebateMigrationItem;
import com.ext.portlet.debatemigration.model.DebateMigrationItemSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="DebateMigrationItemModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>DebateMigrationItem</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.model.DebateMigrationItem
 * @see com.ext.portlet.debatemigration.model.DebateMigrationItemModel
 * @see com.ext.portlet.debatemigration.model.impl.DebateMigrationItemImpl
 *
 */
public class DebateMigrationItemModelImpl extends BaseModelImpl<DebateMigrationItem> {
    public static final String TABLE_NAME = "DebateMigrationItem";
    public static final Object[][] TABLE_COLUMNS = {
            { "debateMigrationItemPK", new Integer(Types.BIGINT) },
            

            { "debateMigrationId", new Integer(Types.BIGINT) },
            

            { "oldMBMessageId", new Integer(Types.BIGINT) },
            

            { "newDebateItemId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table DebateMigrationItem (debateMigrationItemPK LONG not null primary key,debateMigrationId LONG,oldMBMessageId LONG,newDebateItemId LONG)";
    public static final String TABLE_SQL_DROP = "drop table DebateMigrationItem";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.debatemigration.model.DebateMigrationItem"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.debatemigration.model.DebateMigrationItem"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.debatemigration.model.DebateMigrationItem"));
    private Long _debateMigrationItemPK;
    private Long _debateMigrationId;
    private Long _originalDebateMigrationId;
    private Long _oldMBMessageId;
    private Long _originalOldMBMessageId;
    private Long _newDebateItemId;

    public DebateMigrationItemModelImpl() {
    }

    public static DebateMigrationItem toModel(DebateMigrationItemSoap soapModel) {
        DebateMigrationItem model = new DebateMigrationItemImpl();

        model.setDebateMigrationItemPK(soapModel.getDebateMigrationItemPK());
        model.setDebateMigrationId(soapModel.getDebateMigrationId());
        model.setOldMBMessageId(soapModel.getOldMBMessageId());
        model.setNewDebateItemId(soapModel.getNewDebateItemId());

        return model;
    }

    public static List<DebateMigrationItem> toModels(
        DebateMigrationItemSoap[] soapModels) {
        List<DebateMigrationItem> models = new ArrayList<DebateMigrationItem>(soapModels.length);

        for (DebateMigrationItemSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _debateMigrationItemPK;
    }

    public void setPrimaryKey(Long pk) {
        setDebateMigrationItemPK(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _debateMigrationItemPK;
    }

    public Long getDebateMigrationItemPK() {
        return _debateMigrationItemPK;
    }

    public void setDebateMigrationItemPK(Long debateMigrationItemPK) {
        _debateMigrationItemPK = debateMigrationItemPK;
    }

    public Long getDebateMigrationId() {
        return _debateMigrationId;
    }

    public void setDebateMigrationId(Long debateMigrationId) {
        _debateMigrationId = debateMigrationId;

        if (_originalDebateMigrationId == null) {
            _originalDebateMigrationId = debateMigrationId;
        }
    }

    public Long getOriginalDebateMigrationId() {
        return _originalDebateMigrationId;
    }

    public Long getOldMBMessageId() {
        return _oldMBMessageId;
    }

    public void setOldMBMessageId(Long oldMBMessageId) {
        _oldMBMessageId = oldMBMessageId;

        if (_originalOldMBMessageId == null) {
            _originalOldMBMessageId = oldMBMessageId;
        }
    }

    public Long getOriginalOldMBMessageId() {
        return _originalOldMBMessageId;
    }

    public Long getNewDebateItemId() {
        return _newDebateItemId;
    }

    public void setNewDebateItemId(Long newDebateItemId) {
        _newDebateItemId = newDebateItemId;
    }

    public DebateMigrationItem toEscapedModel() {
        if (isEscapedModel()) {
            return (DebateMigrationItem) this;
        } else {
            DebateMigrationItem model = new DebateMigrationItemImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setDebateMigrationItemPK(getDebateMigrationItemPK());
            model.setDebateMigrationId(getDebateMigrationId());
            model.setOldMBMessageId(getOldMBMessageId());
            model.setNewDebateItemId(getNewDebateItemId());

            model = (DebateMigrationItem) Proxy.newProxyInstance(DebateMigrationItem.class.getClassLoader(),
                    new Class[] { DebateMigrationItem.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        DebateMigrationItemImpl clone = new DebateMigrationItemImpl();

        clone.setDebateMigrationItemPK(getDebateMigrationItemPK());
        clone.setDebateMigrationId(getDebateMigrationId());
        clone.setOldMBMessageId(getOldMBMessageId());
        clone.setNewDebateItemId(getNewDebateItemId());

        return clone;
    }

    public int compareTo(DebateMigrationItem debateMigrationItem) {
        Long pk = debateMigrationItem.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        DebateMigrationItem debateMigrationItem = null;

        try {
            debateMigrationItem = (DebateMigrationItem) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = debateMigrationItem.getPrimaryKey();

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

        sb.append("{debateMigrationItemPK=");
        sb.append(getDebateMigrationItemPK());
        sb.append(", debateMigrationId=");
        sb.append(getDebateMigrationId());
        sb.append(", oldMBMessageId=");
        sb.append(getOldMBMessageId());
        sb.append(", newDebateItemId=");
        sb.append(getNewDebateItemId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.debatemigration.model.DebateMigrationItem");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>debateMigrationItemPK</column-name><column-value><![CDATA[");
        sb.append(getDebateMigrationItemPK());
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
            "<column><column-name>newDebateItemId</column-name><column-value><![CDATA[");
        sb.append(getNewDebateItemId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
