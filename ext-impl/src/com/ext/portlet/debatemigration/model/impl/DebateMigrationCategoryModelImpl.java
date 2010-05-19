package com.ext.portlet.debatemigration.model.impl;

import com.ext.portlet.debatemigration.model.DebateMigrationCategory;
import com.ext.portlet.debatemigration.model.DebateMigrationCategorySoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="DebateMigrationCategoryModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>DebateMigrationCategory</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.model.DebateMigrationCategory
 * @see com.ext.portlet.debatemigration.model.DebateMigrationCategoryModel
 * @see com.ext.portlet.debatemigration.model.impl.DebateMigrationCategoryImpl
 *
 */
public class DebateMigrationCategoryModelImpl extends BaseModelImpl<DebateMigrationCategory> {
    public static final String TABLE_NAME = "DebateMigrationCategory";
    public static final Object[][] TABLE_COLUMNS = {
            { "debateMigrationCategoryPK", new Integer(Types.BIGINT) },
            

            { "debateMigrationId", new Integer(Types.BIGINT) },
            

            { "oldMBCategoryId", new Integer(Types.BIGINT) },
            

            { "newCategoryId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table DebateMigrationCategory (debateMigrationCategoryPK LONG not null primary key,debateMigrationId LONG,oldMBCategoryId LONG,newCategoryId LONG)";
    public static final String TABLE_SQL_DROP = "drop table DebateMigrationCategory";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.debatemigration.model.DebateMigrationCategory"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.debatemigration.model.DebateMigrationCategory"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.debatemigration.model.DebateMigrationCategory"));
    private Long _debateMigrationCategoryPK;
    private Long _debateMigrationId;
    private Long _oldMBCategoryId;
    private Long _newCategoryId;

    public DebateMigrationCategoryModelImpl() {
    }

    public static DebateMigrationCategory toModel(
        DebateMigrationCategorySoap soapModel) {
        DebateMigrationCategory model = new DebateMigrationCategoryImpl();

        model.setDebateMigrationCategoryPK(soapModel.getDebateMigrationCategoryPK());
        model.setDebateMigrationId(soapModel.getDebateMigrationId());
        model.setOldMBCategoryId(soapModel.getOldMBCategoryId());
        model.setNewCategoryId(soapModel.getNewCategoryId());

        return model;
    }

    public static List<DebateMigrationCategory> toModels(
        DebateMigrationCategorySoap[] soapModels) {
        List<DebateMigrationCategory> models = new ArrayList<DebateMigrationCategory>(soapModels.length);

        for (DebateMigrationCategorySoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _debateMigrationCategoryPK;
    }

    public void setPrimaryKey(Long pk) {
        setDebateMigrationCategoryPK(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _debateMigrationCategoryPK;
    }

    public Long getDebateMigrationCategoryPK() {
        return _debateMigrationCategoryPK;
    }

    public void setDebateMigrationCategoryPK(Long debateMigrationCategoryPK) {
        _debateMigrationCategoryPK = debateMigrationCategoryPK;
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

    public Long getNewCategoryId() {
        return _newCategoryId;
    }

    public void setNewCategoryId(Long newCategoryId) {
        _newCategoryId = newCategoryId;
    }

    public DebateMigrationCategory toEscapedModel() {
        if (isEscapedModel()) {
            return (DebateMigrationCategory) this;
        } else {
            DebateMigrationCategory model = new DebateMigrationCategoryImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setDebateMigrationCategoryPK(getDebateMigrationCategoryPK());
            model.setDebateMigrationId(getDebateMigrationId());
            model.setOldMBCategoryId(getOldMBCategoryId());
            model.setNewCategoryId(getNewCategoryId());

            model = (DebateMigrationCategory) Proxy.newProxyInstance(DebateMigrationCategory.class.getClassLoader(),
                    new Class[] { DebateMigrationCategory.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        DebateMigrationCategoryImpl clone = new DebateMigrationCategoryImpl();

        clone.setDebateMigrationCategoryPK(getDebateMigrationCategoryPK());
        clone.setDebateMigrationId(getDebateMigrationId());
        clone.setOldMBCategoryId(getOldMBCategoryId());
        clone.setNewCategoryId(getNewCategoryId());

        return clone;
    }

    public int compareTo(DebateMigrationCategory debateMigrationCategory) {
        Long pk = debateMigrationCategory.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        DebateMigrationCategory debateMigrationCategory = null;

        try {
            debateMigrationCategory = (DebateMigrationCategory) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = debateMigrationCategory.getPrimaryKey();

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

        sb.append("{debateMigrationCategoryPK=");
        sb.append(getDebateMigrationCategoryPK());
        sb.append(", debateMigrationId=");
        sb.append(getDebateMigrationId());
        sb.append(", oldMBCategoryId=");
        sb.append(getOldMBCategoryId());
        sb.append(", newCategoryId=");
        sb.append(getNewCategoryId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append(
            "com.ext.portlet.debatemigration.model.DebateMigrationCategory");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>debateMigrationCategoryPK</column-name><column-value><![CDATA[");
        sb.append(getDebateMigrationCategoryPK());
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
            "<column><column-name>newCategoryId</column-name><column-value><![CDATA[");
        sb.append(getNewCategoryId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
