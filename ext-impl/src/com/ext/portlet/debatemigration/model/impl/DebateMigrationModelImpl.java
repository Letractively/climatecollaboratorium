package com.ext.portlet.debatemigration.model.impl;

import com.ext.portlet.debatemigration.model.DebateMigration;
import com.ext.portlet.debatemigration.model.DebateMigrationSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="DebateMigrationModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>DebateMigration</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.model.DebateMigration
 * @see com.ext.portlet.debatemigration.model.DebateMigrationModel
 * @see com.ext.portlet.debatemigration.model.impl.DebateMigrationImpl
 *
 */
public class DebateMigrationModelImpl extends BaseModelImpl<DebateMigration> {
    public static final String TABLE_NAME = "DebateMigration";
    public static final Object[][] TABLE_COLUMNS = {
            { "debateMigrationPK", new Integer(Types.BIGINT) },
            

            { "migrationDate", new Integer(Types.TIMESTAMP) }
        };
    public static final String TABLE_SQL_CREATE = "create table DebateMigration (debateMigrationPK LONG not null primary key,migrationDate DATE null)";
    public static final String TABLE_SQL_DROP = "drop table DebateMigration";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.debatemigration.model.DebateMigration"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.debatemigration.model.DebateMigration"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.debatemigration.model.DebateMigration"));
    private Long _debateMigrationPK;
    private Date _migrationDate;

    public DebateMigrationModelImpl() {
    }

    public static DebateMigration toModel(DebateMigrationSoap soapModel) {
        DebateMigration model = new DebateMigrationImpl();

        model.setDebateMigrationPK(soapModel.getDebateMigrationPK());
        model.setMigrationDate(soapModel.getMigrationDate());

        return model;
    }

    public static List<DebateMigration> toModels(
        DebateMigrationSoap[] soapModels) {
        List<DebateMigration> models = new ArrayList<DebateMigration>(soapModels.length);

        for (DebateMigrationSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _debateMigrationPK;
    }

    public void setPrimaryKey(Long pk) {
        setDebateMigrationPK(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _debateMigrationPK;
    }

    public Long getDebateMigrationPK() {
        return _debateMigrationPK;
    }

    public void setDebateMigrationPK(Long debateMigrationPK) {
        _debateMigrationPK = debateMigrationPK;
    }

    public Date getMigrationDate() {
        return _migrationDate;
    }

    public void setMigrationDate(Date migrationDate) {
        _migrationDate = migrationDate;
    }

    public DebateMigration toEscapedModel() {
        if (isEscapedModel()) {
            return (DebateMigration) this;
        } else {
            DebateMigration model = new DebateMigrationImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setDebateMigrationPK(getDebateMigrationPK());
            model.setMigrationDate(getMigrationDate());

            model = (DebateMigration) Proxy.newProxyInstance(DebateMigration.class.getClassLoader(),
                    new Class[] { DebateMigration.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        DebateMigrationImpl clone = new DebateMigrationImpl();

        clone.setDebateMigrationPK(getDebateMigrationPK());
        clone.setMigrationDate(getMigrationDate());

        return clone;
    }

    public int compareTo(DebateMigration debateMigration) {
        Long pk = debateMigration.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        DebateMigration debateMigration = null;

        try {
            debateMigration = (DebateMigration) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = debateMigration.getPrimaryKey();

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

        sb.append("{debateMigrationPK=");
        sb.append(getDebateMigrationPK());
        sb.append(", migrationDate=");
        sb.append(getMigrationDate());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.debatemigration.model.DebateMigration");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>debateMigrationPK</column-name><column-value><![CDATA[");
        sb.append(getDebateMigrationPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>migrationDate</column-name><column-value><![CDATA[");
        sb.append(getMigrationDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
