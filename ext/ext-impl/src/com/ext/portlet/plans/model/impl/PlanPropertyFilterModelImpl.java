package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanPropertyFilter;
import com.ext.portlet.plans.model.PlanPropertyFilterSoap;

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
 * <a href="PlanPropertyFilterModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanPropertyFilter</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanPropertyFilter
 * @see com.ext.portlet.plans.model.PlanPropertyFilterModel
 * @see com.ext.portlet.plans.model.impl.PlanPropertyFilterImpl
 *
 */
public class PlanPropertyFilterModelImpl extends BaseModelImpl<PlanPropertyFilter> {
    public static final String TABLE_NAME = "PlanPropertyFilter";
    public static final Object[][] TABLE_COLUMNS = {
            { "planPropertyFilterId", new Integer(Types.BIGINT) },
            

            { "propertyName", new Integer(Types.VARCHAR) },
            

            { "planUserSettingsId", new Integer(Types.BIGINT) },
            

            { "value", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanPropertyFilter (planPropertyFilterId LONG not null primary key,propertyName VARCHAR(75) null,planUserSettingsId LONG,value VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table PlanPropertyFilter";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanPropertyFilter"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanPropertyFilter"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanPropertyFilter"));
    private Long _planPropertyFilterId;
    private String _propertyName;
    private String _originalPropertyName;
    private Long _planUserSettingsId;
    private Long _originalPlanUserSettingsId;
    private String _value;

    public PlanPropertyFilterModelImpl() {
    }

    public static PlanPropertyFilter toModel(PlanPropertyFilterSoap soapModel) {
        PlanPropertyFilter model = new PlanPropertyFilterImpl();

        model.setPlanPropertyFilterId(soapModel.getPlanPropertyFilterId());
        model.setPropertyName(soapModel.getPropertyName());
        model.setPlanUserSettingsId(soapModel.getPlanUserSettingsId());
        model.setValue(soapModel.getValue());

        return model;
    }

    public static List<PlanPropertyFilter> toModels(
        PlanPropertyFilterSoap[] soapModels) {
        List<PlanPropertyFilter> models = new ArrayList<PlanPropertyFilter>(soapModels.length);

        for (PlanPropertyFilterSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _planPropertyFilterId;
    }

    public void setPrimaryKey(Long pk) {
        setPlanPropertyFilterId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _planPropertyFilterId;
    }

    public Long getPlanPropertyFilterId() {
        return _planPropertyFilterId;
    }

    public void setPlanPropertyFilterId(Long planPropertyFilterId) {
        _planPropertyFilterId = planPropertyFilterId;
    }

    public String getPropertyName() {
        return GetterUtil.getString(_propertyName);
    }

    public void setPropertyName(String propertyName) {
        _propertyName = propertyName;

        if (_originalPropertyName == null) {
            _originalPropertyName = propertyName;
        }
    }

    public String getOriginalPropertyName() {
        return GetterUtil.getString(_originalPropertyName);
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

    public String getValue() {
        return GetterUtil.getString(_value);
    }

    public void setValue(String value) {
        _value = value;
    }

    public PlanPropertyFilter toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanPropertyFilter) this;
        } else {
            PlanPropertyFilter model = new PlanPropertyFilterImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setPlanPropertyFilterId(getPlanPropertyFilterId());
            model.setPropertyName(HtmlUtil.escape(getPropertyName()));
            model.setPlanUserSettingsId(getPlanUserSettingsId());
            model.setValue(HtmlUtil.escape(getValue()));

            model = (PlanPropertyFilter) Proxy.newProxyInstance(PlanPropertyFilter.class.getClassLoader(),
                    new Class[] { PlanPropertyFilter.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanPropertyFilterImpl clone = new PlanPropertyFilterImpl();

        clone.setPlanPropertyFilterId(getPlanPropertyFilterId());
        clone.setPropertyName(getPropertyName());
        clone.setPlanUserSettingsId(getPlanUserSettingsId());
        clone.setValue(getValue());

        return clone;
    }

    public int compareTo(PlanPropertyFilter planPropertyFilter) {
        Long pk = planPropertyFilter.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanPropertyFilter planPropertyFilter = null;

        try {
            planPropertyFilter = (PlanPropertyFilter) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = planPropertyFilter.getPrimaryKey();

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

        sb.append("{planPropertyFilterId=");
        sb.append(getPlanPropertyFilterId());
        sb.append(", propertyName=");
        sb.append(getPropertyName());
        sb.append(", planUserSettingsId=");
        sb.append(getPlanUserSettingsId());
        sb.append(", value=");
        sb.append(getValue());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanPropertyFilter");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planPropertyFilterId</column-name><column-value><![CDATA[");
        sb.append(getPlanPropertyFilterId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>propertyName</column-name><column-value><![CDATA[");
        sb.append(getPropertyName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planUserSettingsId</column-name><column-value><![CDATA[");
        sb.append(getPlanUserSettingsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>value</column-name><column-value><![CDATA[");
        sb.append(getValue());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
