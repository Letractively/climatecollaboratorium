package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanTypeColumn;
import com.ext.portlet.plans.model.PlanTypeColumnSoap;

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
 * <a href="PlanTypeColumnModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanTypeColumn</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanTypeColumn
 * @see com.ext.portlet.plans.model.PlanTypeColumnModel
 * @see com.ext.portlet.plans.model.impl.PlanTypeColumnImpl
 *
 */
public class PlanTypeColumnModelImpl extends BaseModelImpl<PlanTypeColumn> {
    public static final String TABLE_NAME = "PlanTypeColumn";
    public static final Object[][] TABLE_COLUMNS = {
            { "planTypeColumnId", new Integer(Types.BIGINT) },
            

            { "planTypeId", new Integer(Types.BIGINT) },
            

            { "weight", new Integer(Types.INTEGER) },
            

            { "columnName", new Integer(Types.VARCHAR) },
            

            { "visibleByDefault", new Integer(Types.BOOLEAN) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanTypeColumn (planTypeColumnId LONG not null primary key,planTypeId LONG,weight INTEGER,columnName VARCHAR(75) null,visibleByDefault BOOLEAN)";
    public static final String TABLE_SQL_DROP = "drop table PlanTypeColumn";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanTypeColumn"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanTypeColumn"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanTypeColumn"));
    private Long _planTypeColumnId;
    private Long _planTypeId;
    private Integer _weight;
    private String _columnName;
    private Boolean _visibleByDefault;

    public PlanTypeColumnModelImpl() {
    }

    public static PlanTypeColumn toModel(PlanTypeColumnSoap soapModel) {
        PlanTypeColumn model = new PlanTypeColumnImpl();

        model.setPlanTypeColumnId(soapModel.getPlanTypeColumnId());
        model.setPlanTypeId(soapModel.getPlanTypeId());
        model.setWeight(soapModel.getWeight());
        model.setColumnName(soapModel.getColumnName());
        model.setVisibleByDefault(soapModel.getVisibleByDefault());

        return model;
    }

    public static List<PlanTypeColumn> toModels(PlanTypeColumnSoap[] soapModels) {
        List<PlanTypeColumn> models = new ArrayList<PlanTypeColumn>(soapModels.length);

        for (PlanTypeColumnSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _planTypeColumnId;
    }

    public void setPrimaryKey(Long pk) {
        setPlanTypeColumnId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _planTypeColumnId;
    }

    public Long getPlanTypeColumnId() {
        return _planTypeColumnId;
    }

    public void setPlanTypeColumnId(Long planTypeColumnId) {
        _planTypeColumnId = planTypeColumnId;
    }

    public Long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(Long planTypeId) {
        _planTypeId = planTypeId;
    }

    public Integer getWeight() {
        return _weight;
    }

    public void setWeight(Integer weight) {
        _weight = weight;
    }

    public String getColumnName() {
        return GetterUtil.getString(_columnName);
    }

    public void setColumnName(String columnName) {
        _columnName = columnName;
    }

    public Boolean getVisibleByDefault() {
        return _visibleByDefault;
    }

    public void setVisibleByDefault(Boolean visibleByDefault) {
        _visibleByDefault = visibleByDefault;
    }

    public PlanTypeColumn toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanTypeColumn) this;
        } else {
            PlanTypeColumn model = new PlanTypeColumnImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setPlanTypeColumnId(getPlanTypeColumnId());
            model.setPlanTypeId(getPlanTypeId());
            model.setWeight(getWeight());
            model.setColumnName(HtmlUtil.escape(getColumnName()));
            model.setVisibleByDefault(getVisibleByDefault());

            model = (PlanTypeColumn) Proxy.newProxyInstance(PlanTypeColumn.class.getClassLoader(),
                    new Class[] { PlanTypeColumn.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanTypeColumnImpl clone = new PlanTypeColumnImpl();

        clone.setPlanTypeColumnId(getPlanTypeColumnId());
        clone.setPlanTypeId(getPlanTypeId());
        clone.setWeight(getWeight());
        clone.setColumnName(getColumnName());
        clone.setVisibleByDefault(getVisibleByDefault());

        return clone;
    }

    public int compareTo(PlanTypeColumn planTypeColumn) {
        int value = 0;

        value = getWeight().compareTo(planTypeColumn.getWeight());

        if (value != 0) {
            return value;
        }

        value = getColumnName().toLowerCase()
                    .compareTo(planTypeColumn.getColumnName().toLowerCase());

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanTypeColumn planTypeColumn = null;

        try {
            planTypeColumn = (PlanTypeColumn) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = planTypeColumn.getPrimaryKey();

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

        sb.append("{planTypeColumnId=");
        sb.append(getPlanTypeColumnId());
        sb.append(", planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", weight=");
        sb.append(getWeight());
        sb.append(", columnName=");
        sb.append(getColumnName());
        sb.append(", visibleByDefault=");
        sb.append(getVisibleByDefault());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanTypeColumn");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planTypeColumnId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeColumnId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>weight</column-name><column-value><![CDATA[");
        sb.append(getWeight());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>columnName</column-name><column-value><![CDATA[");
        sb.append(getColumnName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>visibleByDefault</column-name><column-value><![CDATA[");
        sb.append(getVisibleByDefault());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
