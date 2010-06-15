package com.ext.portlet.models.model.impl;

import com.ext.portlet.models.model.ModelOutputItem;
import com.ext.portlet.models.model.ModelOutputItemSoap;

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
 * <a href="ModelOutputItemModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>ModelOutputItem</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelOutputItem
 * @see com.ext.portlet.models.model.ModelOutputItemModel
 * @see com.ext.portlet.models.model.impl.ModelOutputItemImpl
 *
 */
public class ModelOutputItemModelImpl extends BaseModelImpl<ModelOutputItem> {
    public static final String TABLE_NAME = "ModelOutputItem";
    public static final Object[][] TABLE_COLUMNS = {
            { "modelOutputItemModifierPK", new Integer(Types.BIGINT) },
            

            { "modelId", new Integer(Types.BIGINT) },
            

            { "modelOutputItemId", new Integer(Types.BIGINT) },
            

            { "modelOutputItemOrder", new Integer(Types.INTEGER) },
            

            { "modelItemRangePolicy", new Integer(Types.VARCHAR) },
            

            { "modelItemRangeMessage", new Integer(Types.VARCHAR) },
            

            { "modelItemErrorPolicy", new Integer(Types.VARCHAR) },
            

            { "modelItemErrorMessage", new Integer(Types.VARCHAR) },
            

            { "modelItemLabelFormat", new Integer(Types.VARCHAR) },
            

            { "modelItemIsVisible", new Integer(Types.BOOLEAN) },
            

            { "itemType", new Integer(Types.VARCHAR) },
            

            { "relatedOutputItem", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table ModelOutputItem (modelOutputItemModifierPK LONG not null primary key,modelId LONG,modelOutputItemId LONG,modelOutputItemOrder INTEGER,modelItemRangePolicy VARCHAR(75) null,modelItemRangeMessage VARCHAR(75) null,modelItemErrorPolicy VARCHAR(75) null,modelItemErrorMessage VARCHAR(75) null,modelItemLabelFormat VARCHAR(75) null,modelItemIsVisible BOOLEAN,itemType VARCHAR(75) null,relatedOutputItem LONG)";
    public static final String TABLE_SQL_DROP = "drop table ModelOutputItem";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.models.model.ModelOutputItem"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.models.model.ModelOutputItem"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.models.model.ModelOutputItem"));
    private Long _modelOutputItemModifierPK;
    private Long _modelId;
    private Long _modelOutputItemId;
    private Long _originalModelOutputItemId;
    private Integer _modelOutputItemOrder;
    private String _modelItemRangePolicy;
    private String _modelItemRangeMessage;
    private String _modelItemErrorPolicy;
    private String _modelItemErrorMessage;
    private String _modelItemLabelFormat;
    private Boolean _modelItemIsVisible;
    private String _itemType;
    private Long _relatedOutputItem;

    public ModelOutputItemModelImpl() {
    }

    public static ModelOutputItem toModel(ModelOutputItemSoap soapModel) {
        ModelOutputItem model = new ModelOutputItemImpl();

        model.setModelOutputItemModifierPK(soapModel.getModelOutputItemModifierPK());
        model.setModelId(soapModel.getModelId());
        model.setModelOutputItemId(soapModel.getModelOutputItemId());
        model.setModelOutputItemOrder(soapModel.getModelOutputItemOrder());
        model.setModelItemRangePolicy(soapModel.getModelItemRangePolicy());
        model.setModelItemRangeMessage(soapModel.getModelItemRangeMessage());
        model.setModelItemErrorPolicy(soapModel.getModelItemErrorPolicy());
        model.setModelItemErrorMessage(soapModel.getModelItemErrorMessage());
        model.setModelItemLabelFormat(soapModel.getModelItemLabelFormat());
        model.setModelItemIsVisible(soapModel.getModelItemIsVisible());
        model.setItemType(soapModel.getItemType());
        model.setRelatedOutputItem(soapModel.getRelatedOutputItem());

        return model;
    }

    public static List<ModelOutputItem> toModels(
        ModelOutputItemSoap[] soapModels) {
        List<ModelOutputItem> models = new ArrayList<ModelOutputItem>(soapModels.length);

        for (ModelOutputItemSoap soapModel : soapModels) {
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

        if (_originalModelOutputItemId == null) {
            _originalModelOutputItemId = modelOutputItemId;
        }
    }

    public Long getOriginalModelOutputItemId() {
        return _originalModelOutputItemId;
    }

    public Integer getModelOutputItemOrder() {
        return _modelOutputItemOrder;
    }

    public void setModelOutputItemOrder(Integer modelOutputItemOrder) {
        _modelOutputItemOrder = modelOutputItemOrder;
    }

    public String getModelItemRangePolicy() {
        return GetterUtil.getString(_modelItemRangePolicy);
    }

    public void setModelItemRangePolicy(String modelItemRangePolicy) {
        _modelItemRangePolicy = modelItemRangePolicy;
    }

    public String getModelItemRangeMessage() {
        return GetterUtil.getString(_modelItemRangeMessage);
    }

    public void setModelItemRangeMessage(String modelItemRangeMessage) {
        _modelItemRangeMessage = modelItemRangeMessage;
    }

    public String getModelItemErrorPolicy() {
        return GetterUtil.getString(_modelItemErrorPolicy);
    }

    public void setModelItemErrorPolicy(String modelItemErrorPolicy) {
        _modelItemErrorPolicy = modelItemErrorPolicy;
    }

    public String getModelItemErrorMessage() {
        return GetterUtil.getString(_modelItemErrorMessage);
    }

    public void setModelItemErrorMessage(String modelItemErrorMessage) {
        _modelItemErrorMessage = modelItemErrorMessage;
    }

    public String getModelItemLabelFormat() {
        return GetterUtil.getString(_modelItemLabelFormat);
    }

    public void setModelItemLabelFormat(String modelItemLabelFormat) {
        _modelItemLabelFormat = modelItemLabelFormat;
    }

    public Boolean getModelItemIsVisible() {
        return _modelItemIsVisible;
    }

    public void setModelItemIsVisible(Boolean modelItemIsVisible) {
        _modelItemIsVisible = modelItemIsVisible;
    }

    public String getItemType() {
        return GetterUtil.getString(_itemType);
    }

    public void setItemType(String itemType) {
        _itemType = itemType;
    }

    public Long getRelatedOutputItem() {
        return _relatedOutputItem;
    }

    public void setRelatedOutputItem(Long relatedOutputItem) {
        _relatedOutputItem = relatedOutputItem;
    }

    public ModelOutputItem toEscapedModel() {
        if (isEscapedModel()) {
            return (ModelOutputItem) this;
        } else {
            ModelOutputItem model = new ModelOutputItemImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setModelOutputItemModifierPK(getModelOutputItemModifierPK());
            model.setModelId(getModelId());
            model.setModelOutputItemId(getModelOutputItemId());
            model.setModelOutputItemOrder(getModelOutputItemOrder());
            model.setModelItemRangePolicy(HtmlUtil.escape(
                    getModelItemRangePolicy()));
            model.setModelItemRangeMessage(HtmlUtil.escape(
                    getModelItemRangeMessage()));
            model.setModelItemErrorPolicy(HtmlUtil.escape(
                    getModelItemErrorPolicy()));
            model.setModelItemErrorMessage(HtmlUtil.escape(
                    getModelItemErrorMessage()));
            model.setModelItemLabelFormat(HtmlUtil.escape(
                    getModelItemLabelFormat()));
            model.setModelItemIsVisible(getModelItemIsVisible());
            model.setItemType(HtmlUtil.escape(getItemType()));
            model.setRelatedOutputItem(getRelatedOutputItem());

            model = (ModelOutputItem) Proxy.newProxyInstance(ModelOutputItem.class.getClassLoader(),
                    new Class[] { ModelOutputItem.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        ModelOutputItemImpl clone = new ModelOutputItemImpl();

        clone.setModelOutputItemModifierPK(getModelOutputItemModifierPK());
        clone.setModelId(getModelId());
        clone.setModelOutputItemId(getModelOutputItemId());
        clone.setModelOutputItemOrder(getModelOutputItemOrder());
        clone.setModelItemRangePolicy(getModelItemRangePolicy());
        clone.setModelItemRangeMessage(getModelItemRangeMessage());
        clone.setModelItemErrorPolicy(getModelItemErrorPolicy());
        clone.setModelItemErrorMessage(getModelItemErrorMessage());
        clone.setModelItemLabelFormat(getModelItemLabelFormat());
        clone.setModelItemIsVisible(getModelItemIsVisible());
        clone.setItemType(getItemType());
        clone.setRelatedOutputItem(getRelatedOutputItem());

        return clone;
    }

    public int compareTo(ModelOutputItem modelOutputItem) {
        Long pk = modelOutputItem.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ModelOutputItem modelOutputItem = null;

        try {
            modelOutputItem = (ModelOutputItem) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = modelOutputItem.getPrimaryKey();

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
        sb.append(", modelItemRangePolicy=");
        sb.append(getModelItemRangePolicy());
        sb.append(", modelItemRangeMessage=");
        sb.append(getModelItemRangeMessage());
        sb.append(", modelItemErrorPolicy=");
        sb.append(getModelItemErrorPolicy());
        sb.append(", modelItemErrorMessage=");
        sb.append(getModelItemErrorMessage());
        sb.append(", modelItemLabelFormat=");
        sb.append(getModelItemLabelFormat());
        sb.append(", modelItemIsVisible=");
        sb.append(getModelItemIsVisible());
        sb.append(", itemType=");
        sb.append(getItemType());
        sb.append(", relatedOutputItem=");
        sb.append(getRelatedOutputItem());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.models.model.ModelOutputItem");
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
            "<column><column-name>modelItemRangePolicy</column-name><column-value><![CDATA[");
        sb.append(getModelItemRangePolicy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelItemRangeMessage</column-name><column-value><![CDATA[");
        sb.append(getModelItemRangeMessage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelItemErrorPolicy</column-name><column-value><![CDATA[");
        sb.append(getModelItemErrorPolicy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelItemErrorMessage</column-name><column-value><![CDATA[");
        sb.append(getModelItemErrorMessage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelItemLabelFormat</column-name><column-value><![CDATA[");
        sb.append(getModelItemLabelFormat());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelItemIsVisible</column-name><column-value><![CDATA[");
        sb.append(getModelItemIsVisible());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemType</column-name><column-value><![CDATA[");
        sb.append(getItemType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>relatedOutputItem</column-name><column-value><![CDATA[");
        sb.append(getRelatedOutputItem());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
