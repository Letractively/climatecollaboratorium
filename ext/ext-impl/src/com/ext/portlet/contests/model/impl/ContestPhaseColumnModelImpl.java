package com.ext.portlet.contests.model.impl;

import com.ext.portlet.contests.model.ContestPhaseColumn;
import com.ext.portlet.contests.model.ContestPhaseColumnSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="ContestPhaseColumnModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>ContestPhaseColumn</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.model.ContestPhaseColumn
 * @see com.ext.portlet.contests.model.ContestPhaseColumnModel
 * @see com.ext.portlet.contests.model.impl.ContestPhaseColumnImpl
 *
 */
public class ContestPhaseColumnModelImpl extends BaseModelImpl<ContestPhaseColumn> {
    public static final String TABLE_NAME = "ContestPhaseColumn";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", new Integer(Types.BIGINT) },
            

            { "ContestPhasePK", new Integer(Types.BIGINT) },
            

            { "columnName", new Integer(Types.VARCHAR) },
            

            { "columnWeight", new Integer(Types.INTEGER) }
        };
    public static final String TABLE_SQL_CREATE = "create table ContestPhaseColumn (id_ LONG not null primary key,ContestPhasePK LONG,columnName VARCHAR(75) null,columnWeight INTEGER)";
    public static final String TABLE_SQL_DROP = "drop table ContestPhaseColumn";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.contests.model.ContestPhaseColumn"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.contests.model.ContestPhaseColumn"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.contests.model.ContestPhaseColumn"));
    private Long _id;
    private Long _ContestPhasePK;
    private String _columnName;
    private Integer _columnWeight;

    public ContestPhaseColumnModelImpl() {
    }

    public static ContestPhaseColumn toModel(ContestPhaseColumnSoap soapModel) {
        ContestPhaseColumn model = new ContestPhaseColumnImpl();

        model.setId(soapModel.getId());
        model.setContestPhasePK(soapModel.getContestPhasePK());
        model.setColumnName(soapModel.getColumnName());
        model.setColumnWeight(soapModel.getColumnWeight());

        return model;
    }

    public static List<ContestPhaseColumn> toModels(
        ContestPhaseColumnSoap[] soapModels) {
        List<ContestPhaseColumn> models = new ArrayList<ContestPhaseColumn>(soapModels.length);

        for (ContestPhaseColumnSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(Long pk) {
        setId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _id;
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        _id = id;
    }

    public Long getContestPhasePK() {
        return _ContestPhasePK;
    }

    public void setContestPhasePK(Long ContestPhasePK) {
        _ContestPhasePK = ContestPhasePK;
    }

    public String getColumnName() {
        return GetterUtil.getString(_columnName);
    }

    public void setColumnName(String columnName) {
        _columnName = columnName;
    }

    public Integer getColumnWeight() {
        return _columnWeight;
    }

    public void setColumnWeight(Integer columnWeight) {
        _columnWeight = columnWeight;
    }

    public ContestPhaseColumn toEscapedModel() {
        if (isEscapedModel()) {
            return (ContestPhaseColumn) this;
        } else {
            ContestPhaseColumn model = new ContestPhaseColumnImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setId(getId());
            model.setContestPhasePK(getContestPhasePK());
            model.setColumnName(HtmlUtil.escape(getColumnName()));
            model.setColumnWeight(getColumnWeight());

            model = (ContestPhaseColumn) Proxy.newProxyInstance(ContestPhaseColumn.class.getClassLoader(),
                    new Class[] { ContestPhaseColumn.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        ContestPhaseColumnImpl clone = new ContestPhaseColumnImpl();

        clone.setId(getId());
        clone.setContestPhasePK(getContestPhasePK());
        clone.setColumnName(getColumnName());
        clone.setColumnWeight(getColumnWeight());

        return clone;
    }

    public int compareTo(ContestPhaseColumn contestPhaseColumn) {
        int value = 0;

        value = getColumnWeight().compareTo(contestPhaseColumn.getColumnWeight());

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ContestPhaseColumn contestPhaseColumn = null;

        try {
            contestPhaseColumn = (ContestPhaseColumn) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = contestPhaseColumn.getPrimaryKey();

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

        sb.append("{id=");
        sb.append(getId());
        sb.append(", ContestPhasePK=");
        sb.append(getContestPhasePK());
        sb.append(", columnName=");
        sb.append(getColumnName());
        sb.append(", columnWeight=");
        sb.append(getColumnWeight());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.contests.model.ContestPhaseColumn");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestPhasePK</column-name><column-value><![CDATA[");
        sb.append(getContestPhasePK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>columnName</column-name><column-value><![CDATA[");
        sb.append(getColumnName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>columnWeight</column-name><column-value><![CDATA[");
        sb.append(getColumnWeight());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
