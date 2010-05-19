package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanAttributeFilter;
import com.ext.portlet.plans.model.PlanAttributeFilterSoap;

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
 * <a href="PlanAttributeFilterModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanAttributeFilter</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanAttributeFilter
 * @see com.ext.portlet.plans.model.PlanAttributeFilterModel
 * @see com.ext.portlet.plans.model.impl.PlanAttributeFilterImpl
 *
 */
public class PlanAttributeFilterModelImpl extends BaseModelImpl<PlanAttributeFilter> {
    public static final String TABLE_NAME = "PlanAttributeFilter";
    public static final Object[][] TABLE_COLUMNS = {
            { "planAttributeFilterId", new Integer(Types.BIGINT) },
            

            { "attributeName", new Integer(Types.VARCHAR) },
            

            { "planUserSettingsId", new Integer(Types.BIGINT) },
            

            { "max", new Integer(Types.DOUBLE) },
            

            { "min", new Integer(Types.DOUBLE) },
            

            { "stringVal", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanAttributeFilter (planAttributeFilterId LONG not null primary key,attributeName VARCHAR(75) null,planUserSettingsId LONG,max DOUBLE,min DOUBLE,stringVal VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table PlanAttributeFilter";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanAttributeFilter"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanAttributeFilter"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanAttributeFilter"));
    private Long _planAttributeFilterId;
    private String _attributeName;
    private String _originalAttributeName;
    private Long _planUserSettingsId;
    private Long _originalPlanUserSettingsId;
    private Double _max;
    private Double _min;
    private String _stringVal;

    public PlanAttributeFilterModelImpl() {
    }

    public static PlanAttributeFilter toModel(PlanAttributeFilterSoap soapModel) {
        PlanAttributeFilter model = new PlanAttributeFilterImpl();

        model.setPlanAttributeFilterId(soapModel.getPlanAttributeFilterId());
        model.setAttributeName(soapModel.getAttributeName());
        model.setPlanUserSettingsId(soapModel.getPlanUserSettingsId());
        model.setMax(soapModel.getMax());
        model.setMin(soapModel.getMin());
        model.setStringVal(soapModel.getStringVal());

        return model;
    }

    public static List<PlanAttributeFilter> toModels(
        PlanAttributeFilterSoap[] soapModels) {
        List<PlanAttributeFilter> models = new ArrayList<PlanAttributeFilter>(soapModels.length);

        for (PlanAttributeFilterSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _planAttributeFilterId;
    }

    public void setPrimaryKey(Long pk) {
        setPlanAttributeFilterId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _planAttributeFilterId;
    }

    public Long getPlanAttributeFilterId() {
        return _planAttributeFilterId;
    }

    public void setPlanAttributeFilterId(Long planAttributeFilterId) {
        _planAttributeFilterId = planAttributeFilterId;
    }

    public String getAttributeName() {
        return GetterUtil.getString(_attributeName);
    }

    public void setAttributeName(String attributeName) {
        _attributeName = attributeName;

        if (_originalAttributeName == null) {
            _originalAttributeName = attributeName;
        }
    }

    public String getOriginalAttributeName() {
        return GetterUtil.getString(_originalAttributeName);
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

    public Double getMax() {
        return _max;
    }

    public void setMax(Double max) {
        _max = max;
    }

    public Double getMin() {
        return _min;
    }

    public void setMin(Double min) {
        _min = min;
    }

    public String getStringVal() {
        return GetterUtil.getString(_stringVal);
    }

    public void setStringVal(String stringVal) {
        _stringVal = stringVal;
    }

    public PlanAttributeFilter toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanAttributeFilter) this;
        } else {
            PlanAttributeFilter model = new PlanAttributeFilterImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setPlanAttributeFilterId(getPlanAttributeFilterId());
            model.setAttributeName(HtmlUtil.escape(getAttributeName()));
            model.setPlanUserSettingsId(getPlanUserSettingsId());
            model.setMax(getMax());
            model.setMin(getMin());
            model.setStringVal(HtmlUtil.escape(getStringVal()));

            model = (PlanAttributeFilter) Proxy.newProxyInstance(PlanAttributeFilter.class.getClassLoader(),
                    new Class[] { PlanAttributeFilter.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanAttributeFilterImpl clone = new PlanAttributeFilterImpl();

        clone.setPlanAttributeFilterId(getPlanAttributeFilterId());
        clone.setAttributeName(getAttributeName());
        clone.setPlanUserSettingsId(getPlanUserSettingsId());
        clone.setMax(getMax());
        clone.setMin(getMin());
        clone.setStringVal(getStringVal());

        return clone;
    }

    public int compareTo(PlanAttributeFilter planAttributeFilter) {
        Long pk = planAttributeFilter.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanAttributeFilter planAttributeFilter = null;

        try {
            planAttributeFilter = (PlanAttributeFilter) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = planAttributeFilter.getPrimaryKey();

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

        sb.append("{planAttributeFilterId=");
        sb.append(getPlanAttributeFilterId());
        sb.append(", attributeName=");
        sb.append(getAttributeName());
        sb.append(", planUserSettingsId=");
        sb.append(getPlanUserSettingsId());
        sb.append(", max=");
        sb.append(getMax());
        sb.append(", min=");
        sb.append(getMin());
        sb.append(", stringVal=");
        sb.append(getStringVal());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanAttributeFilter");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planAttributeFilterId</column-name><column-value><![CDATA[");
        sb.append(getPlanAttributeFilterId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>attributeName</column-name><column-value><![CDATA[");
        sb.append(getAttributeName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planUserSettingsId</column-name><column-value><![CDATA[");
        sb.append(getPlanUserSettingsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>max</column-name><column-value><![CDATA[");
        sb.append(getMax());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>min</column-name><column-value><![CDATA[");
        sb.append(getMin());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>stringVal</column-name><column-value><![CDATA[");
        sb.append(getStringVal());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
