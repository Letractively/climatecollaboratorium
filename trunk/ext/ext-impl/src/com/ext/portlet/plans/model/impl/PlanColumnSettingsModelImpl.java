package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanColumnSettings;
import com.ext.portlet.plans.model.PlanColumnSettingsSoap;

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
 * <a href="PlanColumnSettingsModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanColumnSettings</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanColumnSettings
 * @see com.ext.portlet.plans.model.PlanColumnSettingsModel
 * @see com.ext.portlet.plans.model.impl.PlanColumnSettingsImpl
 *
 */
public class PlanColumnSettingsModelImpl extends BaseModelImpl<PlanColumnSettings> {
    public static final String TABLE_NAME = "PlanColumnSettings";
    public static final Object[][] TABLE_COLUMNS = {
            { "planColumnSettingsId", new Integer(Types.BIGINT) },
            

            { "columnName", new Integer(Types.VARCHAR) },
            

            { "planUserSettingsId", new Integer(Types.BIGINT) },
            

            { "visible", new Integer(Types.BOOLEAN) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanColumnSettings (planColumnSettingsId LONG not null primary key,columnName VARCHAR(75) null,planUserSettingsId LONG,visible BOOLEAN)";
    public static final String TABLE_SQL_DROP = "drop table PlanColumnSettings";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanColumnSettings"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanColumnSettings"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanColumnSettings"));
    private Long _planColumnSettingsId;
    private String _columnName;
    private String _originalColumnName;
    private Long _planUserSettingsId;
    private Long _originalPlanUserSettingsId;
    private Boolean _visible;

    public PlanColumnSettingsModelImpl() {
    }

    public static PlanColumnSettings toModel(PlanColumnSettingsSoap soapModel) {
        PlanColumnSettings model = new PlanColumnSettingsImpl();

        model.setPlanColumnSettingsId(soapModel.getPlanColumnSettingsId());
        model.setColumnName(soapModel.getColumnName());
        model.setPlanUserSettingsId(soapModel.getPlanUserSettingsId());
        model.setVisible(soapModel.getVisible());

        return model;
    }

    public static List<PlanColumnSettings> toModels(
        PlanColumnSettingsSoap[] soapModels) {
        List<PlanColumnSettings> models = new ArrayList<PlanColumnSettings>(soapModels.length);

        for (PlanColumnSettingsSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _planColumnSettingsId;
    }

    public void setPrimaryKey(Long pk) {
        setPlanColumnSettingsId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _planColumnSettingsId;
    }

    public Long getPlanColumnSettingsId() {
        return _planColumnSettingsId;
    }

    public void setPlanColumnSettingsId(Long planColumnSettingsId) {
        _planColumnSettingsId = planColumnSettingsId;
    }

    public String getColumnName() {
        return GetterUtil.getString(_columnName);
    }

    public void setColumnName(String columnName) {
        _columnName = columnName;

        if (_originalColumnName == null) {
            _originalColumnName = columnName;
        }
    }

    public String getOriginalColumnName() {
        return GetterUtil.getString(_originalColumnName);
    }

    public Long getPlanUserSettingsId() {
        return _planUserSettingsId;
    }

    public void setPlanUserSettingsId(Long planUserSettingsId) {
        _planUserSettingsId = planUserSettingsId;

        if (_originalPlanUserSettingsId == null) {
            _originalPlanUserSettingsId = planUserSettingsId;
        }
    }

    public Long getOriginalPlanUserSettingsId() {
        return _originalPlanUserSettingsId;
    }

    public Boolean getVisible() {
        return _visible;
    }

    public void setVisible(Boolean visible) {
        _visible = visible;
    }

    public PlanColumnSettings toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanColumnSettings) this;
        } else {
            PlanColumnSettings model = new PlanColumnSettingsImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setPlanColumnSettingsId(getPlanColumnSettingsId());
            model.setColumnName(HtmlUtil.escape(getColumnName()));
            model.setPlanUserSettingsId(getPlanUserSettingsId());
            model.setVisible(getVisible());

            model = (PlanColumnSettings) Proxy.newProxyInstance(PlanColumnSettings.class.getClassLoader(),
                    new Class[] { PlanColumnSettings.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanColumnSettingsImpl clone = new PlanColumnSettingsImpl();

        clone.setPlanColumnSettingsId(getPlanColumnSettingsId());
        clone.setColumnName(getColumnName());
        clone.setPlanUserSettingsId(getPlanUserSettingsId());
        clone.setVisible(getVisible());

        return clone;
    }

    public int compareTo(PlanColumnSettings planColumnSettings) {
        Long pk = planColumnSettings.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanColumnSettings planColumnSettings = null;

        try {
            planColumnSettings = (PlanColumnSettings) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = planColumnSettings.getPrimaryKey();

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

        sb.append("{planColumnSettingsId=");
        sb.append(getPlanColumnSettingsId());
        sb.append(", columnName=");
        sb.append(getColumnName());
        sb.append(", planUserSettingsId=");
        sb.append(getPlanUserSettingsId());
        sb.append(", visible=");
        sb.append(getVisible());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanColumnSettings");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planColumnSettingsId</column-name><column-value><![CDATA[");
        sb.append(getPlanColumnSettingsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>columnName</column-name><column-value><![CDATA[");
        sb.append(getColumnName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planUserSettingsId</column-name><column-value><![CDATA[");
        sb.append(getPlanUserSettingsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>visible</column-name><column-value><![CDATA[");
        sb.append(getVisible());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
