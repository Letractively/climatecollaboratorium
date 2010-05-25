package com.ext.portlet.models.model.impl;

import com.ext.portlet.models.model.ModelInputItem;
import com.ext.portlet.models.model.ModelInputItemSoap;

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
 * <a href="ModelInputItemModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>ModelInputItem</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelInputItem
 * @see com.ext.portlet.models.model.ModelInputItemModel
 * @see com.ext.portlet.models.model.impl.ModelInputItemImpl
 *
 */
public class ModelInputItemModelImpl extends BaseModelImpl<ModelInputItem> {
    public static final String TABLE_NAME = "ModelInputItem";
    public static final Object[][] TABLE_COLUMNS = {
            { "modelInputItemPK", new Integer(Types.BIGINT) },
            

            { "modelId", new Integer(Types.BIGINT) },
            

            { "modelInputItemID", new Integer(Types.BIGINT) },
            

            { "modelGroupId", new Integer(Types.BIGINT) },
            

            { "order", new Integer(Types.INTEGER) },
            

            { "type_", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table ModelInputItem (modelInputItemPK LONG not null primary key,modelId LONG,modelInputItemID LONG,modelGroupId LONG,order INTEGER,type_ VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table ModelInputItem";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.models.model.ModelInputItem"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.models.model.ModelInputItem"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.models.model.ModelInputItem"));
    private Long _modelInputItemPK;
    private Long _modelId;
    private Long _modelInputItemID;
    private Long _originalModelInputItemID;
    private Long _modelGroupId;
    private Integer _order;
    private String _type;

    public ModelInputItemModelImpl() {
    }

    public static ModelInputItem toModel(ModelInputItemSoap soapModel) {
        ModelInputItem model = new ModelInputItemImpl();

        model.setModelInputItemPK(soapModel.getModelInputItemPK());
        model.setModelId(soapModel.getModelId());
        model.setModelInputItemID(soapModel.getModelInputItemID());
        model.setModelGroupId(soapModel.getModelGroupId());
        model.setOrder(soapModel.getOrder());
        model.setType(soapModel.getType());

        return model;
    }

    public static List<ModelInputItem> toModels(ModelInputItemSoap[] soapModels) {
        List<ModelInputItem> models = new ArrayList<ModelInputItem>(soapModels.length);

        for (ModelInputItemSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _modelInputItemPK;
    }

    public void setPrimaryKey(Long pk) {
        setModelInputItemPK(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _modelInputItemPK;
    }

    public Long getModelInputItemPK() {
        return _modelInputItemPK;
    }

    public void setModelInputItemPK(Long modelInputItemPK) {
        _modelInputItemPK = modelInputItemPK;
    }

    public Long getModelId() {
        return _modelId;
    }

    public void setModelId(Long modelId) {
        _modelId = modelId;
    }

    public Long getModelInputItemID() {
        return _modelInputItemID;
    }

    public void setModelInputItemID(Long modelInputItemID) {
        _modelInputItemID = modelInputItemID;

        if (_originalModelInputItemID == null) {
            _originalModelInputItemID = modelInputItemID;
        }
    }

    public Long getOriginalModelInputItemID() {
        return _originalModelInputItemID;
    }

    public Long getModelGroupId() {
        return _modelGroupId;
    }

    public void setModelGroupId(Long modelGroupId) {
        _modelGroupId = modelGroupId;
    }

    public Integer getOrder() {
        return _order;
    }

    public void setOrder(Integer order) {
        _order = order;
    }

    public String getType() {
        return GetterUtil.getString(_type);
    }

    public void setType(String type) {
        _type = type;
    }

    public ModelInputItem toEscapedModel() {
        if (isEscapedModel()) {
            return (ModelInputItem) this;
        } else {
            ModelInputItem model = new ModelInputItemImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setModelInputItemPK(getModelInputItemPK());
            model.setModelId(getModelId());
            model.setModelInputItemID(getModelInputItemID());
            model.setModelGroupId(getModelGroupId());
            model.setOrder(getOrder());
            model.setType(HtmlUtil.escape(getType()));

            model = (ModelInputItem) Proxy.newProxyInstance(ModelInputItem.class.getClassLoader(),
                    new Class[] { ModelInputItem.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        ModelInputItemImpl clone = new ModelInputItemImpl();

        clone.setModelInputItemPK(getModelInputItemPK());
        clone.setModelId(getModelId());
        clone.setModelInputItemID(getModelInputItemID());
        clone.setModelGroupId(getModelGroupId());
        clone.setOrder(getOrder());
        clone.setType(getType());

        return clone;
    }

    public int compareTo(ModelInputItem modelInputItem) {
        Long pk = modelInputItem.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ModelInputItem modelInputItem = null;

        try {
            modelInputItem = (ModelInputItem) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = modelInputItem.getPrimaryKey();

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

        sb.append("{modelInputItemPK=");
        sb.append(getModelInputItemPK());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append(", modelInputItemID=");
        sb.append(getModelInputItemID());
        sb.append(", modelGroupId=");
        sb.append(getModelGroupId());
        sb.append(", order=");
        sb.append(getOrder());
        sb.append(", type=");
        sb.append(getType());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.models.model.ModelInputItem");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modelInputItemPK</column-name><column-value><![CDATA[");
        sb.append(getModelInputItemPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelInputItemID</column-name><column-value><![CDATA[");
        sb.append(getModelInputItemID());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelGroupId</column-name><column-value><![CDATA[");
        sb.append(getModelGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>order</column-name><column-value><![CDATA[");
        sb.append(getOrder());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>type</column-name><column-value><![CDATA[");
        sb.append(getType());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
