package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlansUserSettings;
import com.ext.portlet.plans.model.PlansUserSettingsSoap;

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
 * <a href="PlansUserSettingsModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlansUserSettings</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlansUserSettings
 * @see com.ext.portlet.plans.model.PlansUserSettingsModel
 * @see com.ext.portlet.plans.model.impl.PlansUserSettingsImpl
 *
 */
public class PlansUserSettingsModelImpl extends BaseModelImpl<PlansUserSettings> {
    public static final String TABLE_NAME = "PlansUserSettings";
    public static final Object[][] TABLE_COLUMNS = {
            { "planUserSettingsId", new Integer(Types.BIGINT) },
            

            { "userId", new Integer(Types.BIGINT) },
            

            { "planTypeId", new Integer(Types.BIGINT) },
            

            { "sortColumn", new Integer(Types.VARCHAR) },
            

            { "sortDirection", new Integer(Types.VARCHAR) },
            

            { "filterEnabled", new Integer(Types.BOOLEAN) },
            

            { "filterPositionsAll", new Integer(Types.BOOLEAN) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlansUserSettings (planUserSettingsId LONG not null primary key,userId LONG,planTypeId LONG,sortColumn VARCHAR(75) null,sortDirection VARCHAR(75) null,filterEnabled BOOLEAN,filterPositionsAll BOOLEAN)";
    public static final String TABLE_SQL_DROP = "drop table PlansUserSettings";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlansUserSettings"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlansUserSettings"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlansUserSettings"));
    private Long _planUserSettingsId;
    private Long _userId;
    private Long _originalUserId;
    private Long _planTypeId;
    private Long _originalPlanTypeId;
    private String _sortColumn;
    private String _sortDirection;
    private Boolean _filterEnabled;
    private Boolean _filterPositionsAll;

    public PlansUserSettingsModelImpl() {
    }

    public static PlansUserSettings toModel(PlansUserSettingsSoap soapModel) {
        PlansUserSettings model = new PlansUserSettingsImpl();

        model.setPlanUserSettingsId(soapModel.getPlanUserSettingsId());
        model.setUserId(soapModel.getUserId());
        model.setPlanTypeId(soapModel.getPlanTypeId());
        model.setSortColumn(soapModel.getSortColumn());
        model.setSortDirection(soapModel.getSortDirection());
        model.setFilterEnabled(soapModel.getFilterEnabled());
        model.setFilterPositionsAll(soapModel.getFilterPositionsAll());

        return model;
    }

    public static List<PlansUserSettings> toModels(
        PlansUserSettingsSoap[] soapModels) {
        List<PlansUserSettings> models = new ArrayList<PlansUserSettings>(soapModels.length);

        for (PlansUserSettingsSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _planUserSettingsId;
    }

    public void setPrimaryKey(Long pk) {
        setPlanUserSettingsId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _planUserSettingsId;
    }

    public Long getPlanUserSettingsId() {
        return _planUserSettingsId;
    }

    public void setPlanUserSettingsId(Long planUserSettingsId) {
        _planUserSettingsId = planUserSettingsId;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;

        if (_originalUserId == null) {
            _originalUserId = userId;
        }
    }

    public Long getOriginalUserId() {
        return _originalUserId;
    }

    public Long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(Long planTypeId) {
        _planTypeId = planTypeId;

        if (_originalPlanTypeId == null) {
            _originalPlanTypeId = planTypeId;
        }
    }

    public Long getOriginalPlanTypeId() {
        return _originalPlanTypeId;
    }

    public String getSortColumn() {
        return GetterUtil.getString(_sortColumn);
    }

    public void setSortColumn(String sortColumn) {
        _sortColumn = sortColumn;
    }

    public String getSortDirection() {
        return GetterUtil.getString(_sortDirection);
    }

    public void setSortDirection(String sortDirection) {
        _sortDirection = sortDirection;
    }

    public Boolean getFilterEnabled() {
        return _filterEnabled;
    }

    public void setFilterEnabled(Boolean filterEnabled) {
        _filterEnabled = filterEnabled;
    }

    public Boolean getFilterPositionsAll() {
        return _filterPositionsAll;
    }

    public void setFilterPositionsAll(Boolean filterPositionsAll) {
        _filterPositionsAll = filterPositionsAll;
    }

    public PlansUserSettings toEscapedModel() {
        if (isEscapedModel()) {
            return (PlansUserSettings) this;
        } else {
            PlansUserSettings model = new PlansUserSettingsImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setPlanUserSettingsId(getPlanUserSettingsId());
            model.setUserId(getUserId());
            model.setPlanTypeId(getPlanTypeId());
            model.setSortColumn(HtmlUtil.escape(getSortColumn()));
            model.setSortDirection(HtmlUtil.escape(getSortDirection()));
            model.setFilterEnabled(getFilterEnabled());
            model.setFilterPositionsAll(getFilterPositionsAll());

            model = (PlansUserSettings) Proxy.newProxyInstance(PlansUserSettings.class.getClassLoader(),
                    new Class[] { PlansUserSettings.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlansUserSettingsImpl clone = new PlansUserSettingsImpl();

        clone.setPlanUserSettingsId(getPlanUserSettingsId());
        clone.setUserId(getUserId());
        clone.setPlanTypeId(getPlanTypeId());
        clone.setSortColumn(getSortColumn());
        clone.setSortDirection(getSortDirection());
        clone.setFilterEnabled(getFilterEnabled());
        clone.setFilterPositionsAll(getFilterPositionsAll());

        return clone;
    }

    public int compareTo(PlansUserSettings plansUserSettings) {
        Long pk = plansUserSettings.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlansUserSettings plansUserSettings = null;

        try {
            plansUserSettings = (PlansUserSettings) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = plansUserSettings.getPrimaryKey();

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

        sb.append("{planUserSettingsId=");
        sb.append(getPlanUserSettingsId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", sortColumn=");
        sb.append(getSortColumn());
        sb.append(", sortDirection=");
        sb.append(getSortDirection());
        sb.append(", filterEnabled=");
        sb.append(getFilterEnabled());
        sb.append(", filterPositionsAll=");
        sb.append(getFilterPositionsAll());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlansUserSettings");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planUserSettingsId</column-name><column-value><![CDATA[");
        sb.append(getPlanUserSettingsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sortColumn</column-name><column-value><![CDATA[");
        sb.append(getSortColumn());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sortDirection</column-name><column-value><![CDATA[");
        sb.append(getSortDirection());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>filterEnabled</column-name><column-value><![CDATA[");
        sb.append(getFilterEnabled());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>filterPositionsAll</column-name><column-value><![CDATA[");
        sb.append(getFilterPositionsAll());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
