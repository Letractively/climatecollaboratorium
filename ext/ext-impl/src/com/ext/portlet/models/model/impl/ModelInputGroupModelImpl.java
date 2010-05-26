package com.ext.portlet.models.model.impl;

import com.ext.portlet.models.model.ModelInputGroup;
import com.ext.portlet.models.model.ModelInputGroupSoap;

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
 * <a href="ModelInputGroupModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>ModelInputGroup</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelInputGroup
 * @see com.ext.portlet.models.model.ModelInputGroupModel
 * @see com.ext.portlet.models.model.impl.ModelInputGroupImpl
 *
 */
public class ModelInputGroupModelImpl extends BaseModelImpl<ModelInputGroup> {
    public static final String TABLE_NAME = "ModelInputGroup";
    public static final Object[][] TABLE_COLUMNS = {
            { "modelInputGroupPK", new Integer(Types.BIGINT) },
            

            { "modelId", new Integer(Types.BIGINT) },
            

            { "nameAndDescriptionMetaDataId", new Integer(Types.BIGINT) },
            

            { "name", new Integer(Types.VARCHAR) },
            

            { "description", new Integer(Types.VARCHAR) },
            

            { "groupOrder", new Integer(Types.INTEGER) }
        };
    public static final String TABLE_SQL_CREATE = "create table ModelInputGroup (modelInputGroupPK LONG not null primary key,modelId LONG,nameAndDescriptionMetaDataId LONG,name VARCHAR(75) null,description VARCHAR(75) null,groupOrder INTEGER)";
    public static final String TABLE_SQL_DROP = "drop table ModelInputGroup";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.models.model.ModelInputGroup"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.models.model.ModelInputGroup"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.models.model.ModelInputGroup"));
    private Long _modelInputGroupPK;
    private Long _modelId;
    private Long _nameAndDescriptionMetaDataId;
    private String _name;
    private String _description;
    private Integer _groupOrder;

    public ModelInputGroupModelImpl() {
    }

    public static ModelInputGroup toModel(ModelInputGroupSoap soapModel) {
        ModelInputGroup model = new ModelInputGroupImpl();

        model.setModelInputGroupPK(soapModel.getModelInputGroupPK());
        model.setModelId(soapModel.getModelId());
        model.setNameAndDescriptionMetaDataId(soapModel.getNameAndDescriptionMetaDataId());
        model.setName(soapModel.getName());
        model.setDescription(soapModel.getDescription());
        model.setGroupOrder(soapModel.getGroupOrder());

        return model;
    }

    public static List<ModelInputGroup> toModels(
        ModelInputGroupSoap[] soapModels) {
        List<ModelInputGroup> models = new ArrayList<ModelInputGroup>(soapModels.length);

        for (ModelInputGroupSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _modelInputGroupPK;
    }

    public void setPrimaryKey(Long pk) {
        setModelInputGroupPK(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _modelInputGroupPK;
    }

    public Long getModelInputGroupPK() {
        return _modelInputGroupPK;
    }

    public void setModelInputGroupPK(Long modelInputGroupPK) {
        _modelInputGroupPK = modelInputGroupPK;
    }

    public Long getModelId() {
        return _modelId;
    }

    public void setModelId(Long modelId) {
        _modelId = modelId;
    }

    public Long getNameAndDescriptionMetaDataId() {
        return _nameAndDescriptionMetaDataId;
    }

    public void setNameAndDescriptionMetaDataId(
        Long nameAndDescriptionMetaDataId) {
        _nameAndDescriptionMetaDataId = nameAndDescriptionMetaDataId;
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

    public Integer getGroupOrder() {
        return _groupOrder;
    }

    public void setGroupOrder(Integer groupOrder) {
        _groupOrder = groupOrder;
    }

    public ModelInputGroup toEscapedModel() {
        if (isEscapedModel()) {
            return (ModelInputGroup) this;
        } else {
            ModelInputGroup model = new ModelInputGroupImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setModelInputGroupPK(getModelInputGroupPK());
            model.setModelId(getModelId());
            model.setNameAndDescriptionMetaDataId(getNameAndDescriptionMetaDataId());
            model.setName(HtmlUtil.escape(getName()));
            model.setDescription(HtmlUtil.escape(getDescription()));
            model.setGroupOrder(getGroupOrder());

            model = (ModelInputGroup) Proxy.newProxyInstance(ModelInputGroup.class.getClassLoader(),
                    new Class[] { ModelInputGroup.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        ModelInputGroupImpl clone = new ModelInputGroupImpl();

        clone.setModelInputGroupPK(getModelInputGroupPK());
        clone.setModelId(getModelId());
        clone.setNameAndDescriptionMetaDataId(getNameAndDescriptionMetaDataId());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setGroupOrder(getGroupOrder());

        return clone;
    }

    public int compareTo(ModelInputGroup modelInputGroup) {
        Long pk = modelInputGroup.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ModelInputGroup modelInputGroup = null;

        try {
            modelInputGroup = (ModelInputGroup) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = modelInputGroup.getPrimaryKey();

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

        sb.append("{modelInputGroupPK=");
        sb.append(getModelInputGroupPK());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append(", nameAndDescriptionMetaDataId=");
        sb.append(getNameAndDescriptionMetaDataId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", groupOrder=");
        sb.append(getGroupOrder());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.models.model.ModelInputGroup");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modelInputGroupPK</column-name><column-value><![CDATA[");
        sb.append(getModelInputGroupPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>nameAndDescriptionMetaDataId</column-name><column-value><![CDATA[");
        sb.append(getNameAndDescriptionMetaDataId());
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
            "<column><column-name>groupOrder</column-name><column-value><![CDATA[");
        sb.append(getGroupOrder());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
