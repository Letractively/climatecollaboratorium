package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlanTypeSoap;

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
 * <a href="PlanTypeModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanType</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanType
 * @see com.ext.portlet.plans.model.PlanTypeModel
 * @see com.ext.portlet.plans.model.impl.PlanTypeImpl
 *
 */
public class PlanTypeModelImpl extends BaseModelImpl<PlanType> {
    public static final String TABLE_NAME = "PlanType";
    public static final Object[][] TABLE_COLUMNS = {
            { "planTypeId", new Integer(Types.BIGINT) },
            

            { "name", new Integer(Types.VARCHAR) },
            

            { "description", new Integer(Types.VARCHAR) },
            

            { "modelId", new Integer(Types.BIGINT) },
            

            { "modelTypeName", new Integer(Types.VARCHAR) },
            

            { "published", new Integer(Types.BOOLEAN) },
            

            { "publishedCounterpartId", new Integer(Types.BIGINT) },
            

            { "isDefault", new Integer(Types.BOOLEAN) },
            

            { "defaultModelId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanType (planTypeId LONG not null primary key,name VARCHAR(75) null,description VARCHAR(75) null,modelId LONG,modelTypeName VARCHAR(75) null,published BOOLEAN,publishedCounterpartId LONG,isDefault BOOLEAN,defaultModelId LONG)";
    public static final String TABLE_SQL_DROP = "drop table PlanType";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanType"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanType"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanType"));
    private Long _planTypeId;
    private String _name;
    private String _description;
    private Long _modelId;
    private String _modelTypeName;
    private Boolean _published;
    private Long _publishedCounterpartId;
    private Boolean _isDefault;
    private Boolean _originalIsDefault;
    private Long _defaultModelId;

    public PlanTypeModelImpl() {
    }

    public static PlanType toModel(PlanTypeSoap soapModel) {
        PlanType model = new PlanTypeImpl();

        model.setPlanTypeId(soapModel.getPlanTypeId());
        model.setName(soapModel.getName());
        model.setDescription(soapModel.getDescription());
        model.setModelId(soapModel.getModelId());
        model.setModelTypeName(soapModel.getModelTypeName());
        model.setPublished(soapModel.getPublished());
        model.setPublishedCounterpartId(soapModel.getPublishedCounterpartId());
        model.setIsDefault(soapModel.getIsDefault());
        model.setDefaultModelId(soapModel.getDefaultModelId());

        return model;
    }

    public static List<PlanType> toModels(PlanTypeSoap[] soapModels) {
        List<PlanType> models = new ArrayList<PlanType>(soapModels.length);

        for (PlanTypeSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _planTypeId;
    }

    public void setPrimaryKey(Long pk) {
        setPlanTypeId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _planTypeId;
    }

    public Long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(Long planTypeId) {
        _planTypeId = planTypeId;
    }

    public String getName() {
        return GetterUtil.getString(_name);
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return GetterUtil.getString(_description);
    }

    public void setDescription(String description) {
        _description = description;
    }

    public Long getModelId() {
        return _modelId;
    }

    public void setModelId(Long modelId) {
        _modelId = modelId;
    }

    public String getModelTypeName() {
        return GetterUtil.getString(_modelTypeName);
    }

    public void setModelTypeName(String modelTypeName) {
        _modelTypeName = modelTypeName;
    }

    public Boolean getPublished() {
        return _published;
    }

    public void setPublished(Boolean published) {
        _published = published;
    }

    public Long getPublishedCounterpartId() {
        return _publishedCounterpartId;
    }

    public void setPublishedCounterpartId(Long publishedCounterpartId) {
        _publishedCounterpartId = publishedCounterpartId;
    }

    public Boolean getIsDefault() {
        return _isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        _isDefault = isDefault;

        if (_originalIsDefault == null) {
            _originalIsDefault = isDefault;
        }
    }

    public Boolean getOriginalIsDefault() {
        return _originalIsDefault;
    }

    public Long getDefaultModelId() {
        return _defaultModelId;
    }

    public void setDefaultModelId(Long defaultModelId) {
        _defaultModelId = defaultModelId;
    }

    public PlanType toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanType) this;
        } else {
            PlanType model = new PlanTypeImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setPlanTypeId(getPlanTypeId());
            model.setName(HtmlUtil.escape(getName()));
            model.setDescription(HtmlUtil.escape(getDescription()));
            model.setModelId(getModelId());
            model.setModelTypeName(HtmlUtil.escape(getModelTypeName()));
            model.setPublished(getPublished());
            model.setPublishedCounterpartId(getPublishedCounterpartId());
            model.setIsDefault(getIsDefault());
            model.setDefaultModelId(getDefaultModelId());

            model = (PlanType) Proxy.newProxyInstance(PlanType.class.getClassLoader(),
                    new Class[] { PlanType.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanTypeImpl clone = new PlanTypeImpl();

        clone.setPlanTypeId(getPlanTypeId());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setModelId(getModelId());
        clone.setModelTypeName(getModelTypeName());
        clone.setPublished(getPublished());
        clone.setPublishedCounterpartId(getPublishedCounterpartId());
        clone.setIsDefault(getIsDefault());
        clone.setDefaultModelId(getDefaultModelId());

        return clone;
    }

    public int compareTo(PlanType planType) {
        Long pk = planType.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanType planType = null;

        try {
            planType = (PlanType) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = planType.getPrimaryKey();

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

        sb.append("{planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append(", modelTypeName=");
        sb.append(getModelTypeName());
        sb.append(", published=");
        sb.append(getPublished());
        sb.append(", publishedCounterpartId=");
        sb.append(getPublishedCounterpartId());
        sb.append(", isDefault=");
        sb.append(getIsDefault());
        sb.append(", defaultModelId=");
        sb.append(getDefaultModelId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanType");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelTypeName</column-name><column-value><![CDATA[");
        sb.append(getModelTypeName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>published</column-name><column-value><![CDATA[");
        sb.append(getPublished());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>publishedCounterpartId</column-name><column-value><![CDATA[");
        sb.append(getPublishedCounterpartId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isDefault</column-name><column-value><![CDATA[");
        sb.append(getIsDefault());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>defaultModelId</column-name><column-value><![CDATA[");
        sb.append(getDefaultModelId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
