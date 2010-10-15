package com.ext.portlet.models.model.impl;

import com.ext.portlet.models.model.ModelOutputItemOrder;
import com.ext.portlet.models.model.ModelOutputItemOrderSoap;

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
 * <a href="ModelOutputItemOrderModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>ModelOutputItemOrder</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelOutputItemOrder
 * @see com.ext.portlet.models.model.ModelOutputItemOrderModel
 * @see com.ext.portlet.models.model.impl.ModelOutputItemOrderImpl
 *
 */
public class ModelOutputItemOrderModelImpl extends BaseModelImpl<ModelOutputItemOrder> {
    public static final String TABLE_NAME = "ModelOutputItemOrder";
    public static final Object[][] TABLE_COLUMNS = {
            { "modelOutputItemModifierPK", new Integer(Types.BIGINT) },
            

            { "modelId", new Integer(Types.BIGINT) },
            

            { "modelOutputItemId", new Integer(Types.BIGINT) },
            

            { "modelOutputItemOrder", new Integer(Types.BIGINT) },
            

            { "type_", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table ModelOutputItemOrder (modelOutputItemModifierPK LONG not null primary key,modelId LONG,modelOutputItemId LONG,modelOutputItemOrder LONG,type_ VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table ModelOutputItemOrder";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.models.model.ModelOutputItemOrder"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.models.model.ModelOutputItemOrder"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.models.model.ModelOutputItemOrder"));
    private Long _modelOutputItemModifierPK;
    private Long _modelId;
    private Long _modelOutputItemId;
    private Long _modelOutputItemOrder;
    private String _type;

    public ModelOutputItemOrderModelImpl() {
    }

    public static ModelOutputItemOrder toModel(
        ModelOutputItemOrderSoap soapModel) {
        ModelOutputItemOrder model = new ModelOutputItemOrderImpl();

        model.setModelOutputItemModifierPK(soapModel.getModelOutputItemModifierPK());
        model.setModelId(soapModel.getModelId());
        model.setModelOutputItemId(soapModel.getModelOutputItemId());
        model.setModelOutputItemOrder(soapModel.getModelOutputItemOrder());
        model.setType(soapModel.getType());

        return model;
    }

    public static List<ModelOutputItemOrder> toModels(
        ModelOutputItemOrderSoap[] soapModels) {
        List<ModelOutputItemOrder> models = new ArrayList<ModelOutputItemOrder>(soapModels.length);

        for (ModelOutputItemOrderSoap soapModel : soapModels) {
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

    public Long getModelOutputItemOrder() {
        return _modelOutputItemOrder;
    }

    public void setModelOutputItemOrder(Long modelOutputItemOrder) {
        _modelOutputItemOrder = modelOutputItemOrder;
    }

    public String getType() {
        return GetterUtil.getString(_type);
    }

    public void setType(String type) {
        _type = type;
    }

    public ModelOutputItemOrder toEscapedModel() {
        if (isEscapedModel()) {
            return (ModelOutputItemOrder) this;
        } else {
            ModelOutputItemOrder model = new ModelOutputItemOrderImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setModelOutputItemModifierPK(getModelOutputItemModifierPK());
            model.setModelId(getModelId());
            model.setModelOutputItemId(getModelOutputItemId());
            model.setModelOutputItemOrder(getModelOutputItemOrder());
            model.setType(HtmlUtil.escape(getType()));

            model = (ModelOutputItemOrder) Proxy.newProxyInstance(ModelOutputItemOrder.class.getClassLoader(),
                    new Class[] { ModelOutputItemOrder.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        ModelOutputItemOrderImpl clone = new ModelOutputItemOrderImpl();

        clone.setModelOutputItemModifierPK(getModelOutputItemModifierPK());
        clone.setModelId(getModelId());
        clone.setModelOutputItemId(getModelOutputItemId());
        clone.setModelOutputItemOrder(getModelOutputItemOrder());
        clone.setType(getType());

        return clone;
    }

    public int compareTo(ModelOutputItemOrder modelOutputItemOrder) {
        Long pk = modelOutputItemOrder.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ModelOutputItemOrder modelOutputItemOrder = null;

        try {
            modelOutputItemOrder = (ModelOutputItemOrder) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = modelOutputItemOrder.getPrimaryKey();

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
        sb.append(", modelOutputItemOrder=");
        sb.append(getModelOutputItemOrder());
        sb.append(", type=");
        sb.append(getType());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.models.model.ModelOutputItemOrder");
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
            "<column><column-name>modelOutputItemOrder</column-name><column-value><![CDATA[");
        sb.append(getModelOutputItemOrder());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>type</column-name><column-value><![CDATA[");
        sb.append(getType());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
