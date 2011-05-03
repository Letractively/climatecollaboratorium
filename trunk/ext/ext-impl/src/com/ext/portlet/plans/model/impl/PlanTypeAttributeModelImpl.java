package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanTypeAttribute;
import com.ext.portlet.plans.model.PlanTypeAttributeSoap;

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
 * <a href="PlanTypeAttributeModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanTypeAttribute</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanTypeAttribute
 * @see com.ext.portlet.plans.model.PlanTypeAttributeModel
 * @see com.ext.portlet.plans.model.impl.PlanTypeAttributeImpl
 *
 */
public class PlanTypeAttributeModelImpl extends BaseModelImpl<PlanTypeAttribute> {
    public static final String TABLE_NAME = "PlanTypeAttribute";
    public static final Object[][] TABLE_COLUMNS = {
            { "planTypeAttributeId", new Integer(Types.BIGINT) },
            

            { "planTypeId", new Integer(Types.BIGINT) },
            

            { "attributeName", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanTypeAttribute (planTypeAttributeId LONG not null primary key,planTypeId LONG,attributeName VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table PlanTypeAttribute";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanTypeAttribute"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanTypeAttribute"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanTypeAttribute"));
    private Long _planTypeAttributeId;
    private Long _planTypeId;
    private Long _originalPlanTypeId;
    private String _attributeName;
    private String _originalAttributeName;

    public PlanTypeAttributeModelImpl() {
    }

    public static PlanTypeAttribute toModel(PlanTypeAttributeSoap soapModel) {
        PlanTypeAttribute model = new PlanTypeAttributeImpl();

        model.setPlanTypeAttributeId(soapModel.getPlanTypeAttributeId());
        model.setPlanTypeId(soapModel.getPlanTypeId());
        model.setAttributeName(soapModel.getAttributeName());

        return model;
    }

    public static List<PlanTypeAttribute> toModels(
        PlanTypeAttributeSoap[] soapModels) {
        List<PlanTypeAttribute> models = new ArrayList<PlanTypeAttribute>(soapModels.length);

        for (PlanTypeAttributeSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _planTypeAttributeId;
    }

    public void setPrimaryKey(Long pk) {
        setPlanTypeAttributeId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _planTypeAttributeId;
    }

    public Long getPlanTypeAttributeId() {
        return _planTypeAttributeId;
    }

    public void setPlanTypeAttributeId(Long planTypeAttributeId) {
        _planTypeAttributeId = planTypeAttributeId;
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

    public PlanTypeAttribute toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanTypeAttribute) this;
        } else {
            PlanTypeAttribute model = new PlanTypeAttributeImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setPlanTypeAttributeId(getPlanTypeAttributeId());
            model.setPlanTypeId(getPlanTypeId());
            model.setAttributeName(HtmlUtil.escape(getAttributeName()));

            model = (PlanTypeAttribute) Proxy.newProxyInstance(PlanTypeAttribute.class.getClassLoader(),
                    new Class[] { PlanTypeAttribute.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanTypeAttributeImpl clone = new PlanTypeAttributeImpl();

        clone.setPlanTypeAttributeId(getPlanTypeAttributeId());
        clone.setPlanTypeId(getPlanTypeId());
        clone.setAttributeName(getAttributeName());

        return clone;
    }

    public int compareTo(PlanTypeAttribute planTypeAttribute) {
        Long pk = planTypeAttribute.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanTypeAttribute planTypeAttribute = null;

        try {
            planTypeAttribute = (PlanTypeAttribute) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = planTypeAttribute.getPrimaryKey();

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

        sb.append("{planTypeAttributeId=");
        sb.append(getPlanTypeAttributeId());
        sb.append(", planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", attributeName=");
        sb.append(getAttributeName());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanTypeAttribute");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planTypeAttributeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeAttributeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>attributeName</column-name><column-value><![CDATA[");
        sb.append(getAttributeName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
