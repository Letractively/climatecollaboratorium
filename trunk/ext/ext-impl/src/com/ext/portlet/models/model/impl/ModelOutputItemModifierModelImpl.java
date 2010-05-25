package com.ext.portlet.models.model.impl;

import com.ext.portlet.models.model.ModelOutputItemModifier;
import com.ext.portlet.models.model.ModelOutputItemModifierSoap;

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
 * <a href="ModelOutputItemModifierModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>ModelOutputItemModifier</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelOutputItemModifier
 * @see com.ext.portlet.models.model.ModelOutputItemModifierModel
 * @see com.ext.portlet.models.model.impl.ModelOutputItemModifierImpl
 *
 */
public class ModelOutputItemModifierModelImpl extends BaseModelImpl<ModelOutputItemModifier> {
    public static final String TABLE_NAME = "ModelOutputItemModifier";
    public static final Object[][] TABLE_COLUMNS = {
            { "modelOutputItemModifierPK", new Integer(Types.BIGINT) },
            

            { "modelId", new Integer(Types.BIGINT) },
            

            { "modelOutputItemId", new Integer(Types.BIGINT) },
            

            { "sourceItemId", new Integer(Types.BIGINT) },
            

            { "type_", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table ModelOutputItemModifier (modelOutputItemModifierPK LONG not null primary key,modelId LONG,modelOutputItemId LONG,sourceItemId LONG,type_ VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table ModelOutputItemModifier";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.models.model.ModelOutputItemModifier"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.models.model.ModelOutputItemModifier"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.models.model.ModelOutputItemModifier"));
    private Long _modelOutputItemModifierPK;
    private Long _modelId;
    private Long _modelOutputItemId;
    private Long _sourceItemId;
    private String _type;

    public ModelOutputItemModifierModelImpl() {
    }

    public static ModelOutputItemModifier toModel(
        ModelOutputItemModifierSoap soapModel) {
        ModelOutputItemModifier model = new ModelOutputItemModifierImpl();

        model.setModelOutputItemModifierPK(soapModel.getModelOutputItemModifierPK());
        model.setModelId(soapModel.getModelId());
        model.setModelOutputItemId(soapModel.getModelOutputItemId());
        model.setSourceItemId(soapModel.getSourceItemId());
        model.setType(soapModel.getType());

        return model;
    }

    public static List<ModelOutputItemModifier> toModels(
        ModelOutputItemModifierSoap[] soapModels) {
        List<ModelOutputItemModifier> models = new ArrayList<ModelOutputItemModifier>(soapModels.length);

        for (ModelOutputItemModifierSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _modelOutputItemModifierPK;
    }

    public void setPrimaryKey(Long pk) {
        setModelOutputItemModifierPK(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _modelOutputItemModifierPK;
    }

    public Long getModelOutputItemModifierPK() {
        return _modelOutputItemModifierPK;
    }

    public void setModelOutputItemModifierPK(Long modelOutputItemModifierPK) {
        _modelOutputItemModifierPK = modelOutputItemModifierPK;
    }

    public Long getModelId() {
        return _modelId;
    }

    public void setModelId(Long modelId) {
        _modelId = modelId;
    }

    public Long getModelOutputItemId() {
        return _modelOutputItemId;
    }

    public void setModelOutputItemId(Long modelOutputItemId) {
        _modelOutputItemId = modelOutputItemId;
    }

    public Long getSourceItemId() {
        return _sourceItemId;
    }

    public void setSourceItemId(Long sourceItemId) {
        _sourceItemId = sourceItemId;
    }

    public String getType() {
        return GetterUtil.getString(_type);
    }

    public void setType(String type) {
        _type = type;
    }

    public ModelOutputItemModifier toEscapedModel() {
        if (isEscapedModel()) {
            return (ModelOutputItemModifier) this;
        } else {
            ModelOutputItemModifier model = new ModelOutputItemModifierImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setModelOutputItemModifierPK(getModelOutputItemModifierPK());
            model.setModelId(getModelId());
            model.setModelOutputItemId(getModelOutputItemId());
            model.setSourceItemId(getSourceItemId());
            model.setType(HtmlUtil.escape(getType()));

            model = (ModelOutputItemModifier) Proxy.newProxyInstance(ModelOutputItemModifier.class.getClassLoader(),
                    new Class[] { ModelOutputItemModifier.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        ModelOutputItemModifierImpl clone = new ModelOutputItemModifierImpl();

        clone.setModelOutputItemModifierPK(getModelOutputItemModifierPK());
        clone.setModelId(getModelId());
        clone.setModelOutputItemId(getModelOutputItemId());
        clone.setSourceItemId(getSourceItemId());
        clone.setType(getType());

        return clone;
    }

    public int compareTo(ModelOutputItemModifier modelOutputItemModifier) {
        Long pk = modelOutputItemModifier.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ModelOutputItemModifier modelOutputItemModifier = null;

        try {
            modelOutputItemModifier = (ModelOutputItemModifier) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = modelOutputItemModifier.getPrimaryKey();

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

        sb.append("{modelOutputItemModifierPK=");
        sb.append(getModelOutputItemModifierPK());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append(", modelOutputItemId=");
        sb.append(getModelOutputItemId());
        sb.append(", sourceItemId=");
        sb.append(getSourceItemId());
        sb.append(", type=");
        sb.append(getType());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.models.model.ModelOutputItemModifier");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modelOutputItemModifierPK</column-name><column-value><![CDATA[");
        sb.append(getModelOutputItemModifierPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelOutputItemId</column-name><column-value><![CDATA[");
        sb.append(getModelOutputItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sourceItemId</column-name><column-value><![CDATA[");
        sb.append(getSourceItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>type</column-name><column-value><![CDATA[");
        sb.append(getType());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
