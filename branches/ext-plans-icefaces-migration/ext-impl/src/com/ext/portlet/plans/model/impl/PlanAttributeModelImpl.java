package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanAttribute;
import com.ext.portlet.plans.model.PlanAttributeSoap;

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
 * <a href="PlanAttributeModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanAttribute</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanAttribute
 * @see com.ext.portlet.plans.model.PlanAttributeModel
 * @see com.ext.portlet.plans.model.impl.PlanAttributeImpl
 *
 */
public class PlanAttributeModelImpl extends BaseModelImpl<PlanAttribute> {
    public static final String TABLE_NAME = "PlanAttribute";
    public static final Object[][] TABLE_COLUMNS = {
            { "attributeId", new Integer(Types.BIGINT) },
            

            { "planId", new Integer(Types.BIGINT) },
            

            { "attributeName", new Integer(Types.VARCHAR) },
            

            { "attributeValue", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanAttribute (attributeId LONG not null primary key,planId LONG,attributeName VARCHAR(75) null,attributeValue VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table PlanAttribute";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanAttribute"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanAttribute"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanAttribute"));
    private Long _attributeId;
    private Long _planId;
    private Long _originalPlanId;
    private String _attributeName;
    private String _originalAttributeName;
    private String _attributeValue;

    public PlanAttributeModelImpl() {
    }

    public static PlanAttribute toModel(PlanAttributeSoap soapModel) {
        PlanAttribute model = new PlanAttributeImpl();

        model.setAttributeId(soapModel.getAttributeId());
        model.setPlanId(soapModel.getPlanId());
        model.setAttributeName(soapModel.getAttributeName());
        model.setAttributeValue(soapModel.getAttributeValue());

        return model;
    }

    public static List<PlanAttribute> toModels(PlanAttributeSoap[] soapModels) {
        List<PlanAttribute> models = new ArrayList<PlanAttribute>(soapModels.length);

        for (PlanAttributeSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _attributeId;
    }

    public void setPrimaryKey(Long pk) {
        setAttributeId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _attributeId;
    }

    public Long getAttributeId() {
        return _attributeId;
    }

    public void setAttributeId(Long attributeId) {
        _attributeId = attributeId;
    }

    public Long getPlanId() {
        return _planId;
    }

    public void setPlanId(Long planId) {
        _planId = planId;

        if (_originalPlanId == null) {
            _originalPlanId = planId;
        }
    }

    public Long getOriginalPlanId() {
        return _originalPlanId;
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

    public String getAttributeValue() {
        return GetterUtil.getString(_attributeValue);
    }

    public void setAttributeValue(String attributeValue) {
        _attributeValue = attributeValue;
    }

    public PlanAttribute toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanAttribute) this;
        } else {
            PlanAttribute model = new PlanAttributeImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setAttributeId(getAttributeId());
            model.setPlanId(getPlanId());
            model.setAttributeName(HtmlUtil.escape(getAttributeName()));
            model.setAttributeValue(HtmlUtil.escape(getAttributeValue()));

            model = (PlanAttribute) Proxy.newProxyInstance(PlanAttribute.class.getClassLoader(),
                    new Class[] { PlanAttribute.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanAttributeImpl clone = new PlanAttributeImpl();

        clone.setAttributeId(getAttributeId());
        clone.setPlanId(getPlanId());
        clone.setAttributeName(getAttributeName());
        clone.setAttributeValue(getAttributeValue());

        return clone;
    }

    public int compareTo(PlanAttribute planAttribute) {
        Long pk = planAttribute.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanAttribute planAttribute = null;

        try {
            planAttribute = (PlanAttribute) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = planAttribute.getPrimaryKey();

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

        sb.append("{attributeId=");
        sb.append(getAttributeId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", attributeName=");
        sb.append(getAttributeName());
        sb.append(", attributeValue=");
        sb.append(getAttributeValue());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanAttribute");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>attributeId</column-name><column-value><![CDATA[");
        sb.append(getAttributeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>attributeName</column-name><column-value><![CDATA[");
        sb.append(getAttributeName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>attributeValue</column-name><column-value><![CDATA[");
        sb.append(getAttributeValue());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
