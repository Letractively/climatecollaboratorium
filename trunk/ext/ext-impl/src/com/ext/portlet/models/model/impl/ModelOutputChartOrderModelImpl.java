package com.ext.portlet.models.model.impl;

import com.ext.portlet.models.model.ModelOutputChartOrder;
import com.ext.portlet.models.model.ModelOutputChartOrderSoap;

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
 * <a href="ModelOutputChartOrderModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>ModelOutputChartOrder</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelOutputChartOrder
 * @see com.ext.portlet.models.model.ModelOutputChartOrderModel
 * @see com.ext.portlet.models.model.impl.ModelOutputChartOrderImpl
 *
 */
public class ModelOutputChartOrderModelImpl extends BaseModelImpl<ModelOutputChartOrder> {
    public static final String TABLE_NAME = "ModelOutputChartOrder";
    public static final Object[][] TABLE_COLUMNS = {
            { "modelOutputChartOrderPK", new Integer(Types.BIGINT) },
            

            { "modelId", new Integer(Types.BIGINT) },
            

            { "modelOutputLabel", new Integer(Types.VARCHAR) },
            

            { "modelOutputChartOrder", new Integer(Types.INTEGER) },
            

            { "modelIndexRangePolicy", new Integer(Types.VARCHAR) },
            

            { "modelIndexRangeMessage", new Integer(Types.VARCHAR) },
            

            { "modelIndexErrorPolicy", new Integer(Types.VARCHAR) },
            

            { "modelIndexErrorMessage", new Integer(Types.VARCHAR) },
            

            { "modelChartIsVisible", new Integer(Types.BOOLEAN) }
        };
    public static final String TABLE_SQL_CREATE = "create table ModelOutputChartOrder (modelOutputChartOrderPK LONG not null primary key,modelId LONG,modelOutputLabel VARCHAR(75) null,modelOutputChartOrder INTEGER,modelIndexRangePolicy VARCHAR(75) null,modelIndexRangeMessage VARCHAR(75) null,modelIndexErrorPolicy VARCHAR(75) null,modelIndexErrorMessage VARCHAR(75) null,modelChartIsVisible BOOLEAN)";
    public static final String TABLE_SQL_DROP = "drop table ModelOutputChartOrder";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.models.model.ModelOutputChartOrder"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.models.model.ModelOutputChartOrder"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.models.model.ModelOutputChartOrder"));
    private Long _modelOutputChartOrderPK;
    private Long _modelId;
    private Long _originalModelId;
    private String _modelOutputLabel;
    private String _originalModelOutputLabel;
    private Integer _modelOutputChartOrder;
    private String _modelIndexRangePolicy;
    private String _modelIndexRangeMessage;
    private String _modelIndexErrorPolicy;
    private String _modelIndexErrorMessage;
    private Boolean _modelChartIsVisible;

    public ModelOutputChartOrderModelImpl() {
    }

    public static ModelOutputChartOrder toModel(
        ModelOutputChartOrderSoap soapModel) {
        ModelOutputChartOrder model = new ModelOutputChartOrderImpl();

        model.setModelOutputChartOrderPK(soapModel.getModelOutputChartOrderPK());
        model.setModelId(soapModel.getModelId());
        model.setModelOutputLabel(soapModel.getModelOutputLabel());
        model.setModelOutputChartOrder(soapModel.getModelOutputChartOrder());
        model.setModelIndexRangePolicy(soapModel.getModelIndexRangePolicy());
        model.setModelIndexRangeMessage(soapModel.getModelIndexRangeMessage());
        model.setModelIndexErrorPolicy(soapModel.getModelIndexErrorPolicy());
        model.setModelIndexErrorMessage(soapModel.getModelIndexErrorMessage());
        model.setModelChartIsVisible(soapModel.getModelChartIsVisible());

        return model;
    }

    public static List<ModelOutputChartOrder> toModels(
        ModelOutputChartOrderSoap[] soapModels) {
        List<ModelOutputChartOrder> models = new ArrayList<ModelOutputChartOrder>(soapModels.length);

        for (ModelOutputChartOrderSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _modelOutputChartOrderPK;
    }

    public void setPrimaryKey(Long pk) {
        setModelOutputChartOrderPK(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _modelOutputChartOrderPK;
    }

    public Long getModelOutputChartOrderPK() {
        return _modelOutputChartOrderPK;
    }

    public void setModelOutputChartOrderPK(Long modelOutputChartOrderPK) {
        _modelOutputChartOrderPK = modelOutputChartOrderPK;
    }

    public Long getModelId() {
        return _modelId;
    }

    public void setModelId(Long modelId) {
        _modelId = modelId;

        if (_originalModelId == null) {
            _originalModelId = modelId;
        }
    }

    public Long getOriginalModelId() {
        return _originalModelId;
    }

    public String getModelOutputLabel() {
        return GetterUtil.getString(_modelOutputLabel);
    }

    public void setModelOutputLabel(String modelOutputLabel) {
        _modelOutputLabel = modelOutputLabel;

        if (_originalModelOutputLabel == null) {
            _originalModelOutputLabel = modelOutputLabel;
        }
    }

    public String getOriginalModelOutputLabel() {
        return GetterUtil.getString(_originalModelOutputLabel);
    }

    public Integer getModelOutputChartOrder() {
        return _modelOutputChartOrder;
    }

    public void setModelOutputChartOrder(Integer modelOutputChartOrder) {
        _modelOutputChartOrder = modelOutputChartOrder;
    }

    public String getModelIndexRangePolicy() {
        return GetterUtil.getString(_modelIndexRangePolicy);
    }

    public void setModelIndexRangePolicy(String modelIndexRangePolicy) {
        _modelIndexRangePolicy = modelIndexRangePolicy;
    }

    public String getModelIndexRangeMessage() {
        return GetterUtil.getString(_modelIndexRangeMessage);
    }

    public void setModelIndexRangeMessage(String modelIndexRangeMessage) {
        _modelIndexRangeMessage = modelIndexRangeMessage;
    }

    public String getModelIndexErrorPolicy() {
        return GetterUtil.getString(_modelIndexErrorPolicy);
    }

    public void setModelIndexErrorPolicy(String modelIndexErrorPolicy) {
        _modelIndexErrorPolicy = modelIndexErrorPolicy;
    }

    public String getModelIndexErrorMessage() {
        return GetterUtil.getString(_modelIndexErrorMessage);
    }

    public void setModelIndexErrorMessage(String modelIndexErrorMessage) {
        _modelIndexErrorMessage = modelIndexErrorMessage;
    }

    public Boolean getModelChartIsVisible() {
        return _modelChartIsVisible;
    }

    public void setModelChartIsVisible(Boolean modelChartIsVisible) {
        _modelChartIsVisible = modelChartIsVisible;
    }

    public ModelOutputChartOrder toEscapedModel() {
        if (isEscapedModel()) {
            return (ModelOutputChartOrder) this;
        } else {
            ModelOutputChartOrder model = new ModelOutputChartOrderImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setModelOutputChartOrderPK(getModelOutputChartOrderPK());
            model.setModelId(getModelId());
            model.setModelOutputLabel(HtmlUtil.escape(getModelOutputLabel()));
            model.setModelOutputChartOrder(getModelOutputChartOrder());
            model.setModelIndexRangePolicy(HtmlUtil.escape(
                    getModelIndexRangePolicy()));
            model.setModelIndexRangeMessage(HtmlUtil.escape(
                    getModelIndexRangeMessage()));
            model.setModelIndexErrorPolicy(HtmlUtil.escape(
                    getModelIndexErrorPolicy()));
            model.setModelIndexErrorMessage(HtmlUtil.escape(
                    getModelIndexErrorMessage()));
            model.setModelChartIsVisible(getModelChartIsVisible());

            model = (ModelOutputChartOrder) Proxy.newProxyInstance(ModelOutputChartOrder.class.getClassLoader(),
                    new Class[] { ModelOutputChartOrder.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        ModelOutputChartOrderImpl clone = new ModelOutputChartOrderImpl();

        clone.setModelOutputChartOrderPK(getModelOutputChartOrderPK());
        clone.setModelId(getModelId());
        clone.setModelOutputLabel(getModelOutputLabel());
        clone.setModelOutputChartOrder(getModelOutputChartOrder());
        clone.setModelIndexRangePolicy(getModelIndexRangePolicy());
        clone.setModelIndexRangeMessage(getModelIndexRangeMessage());
        clone.setModelIndexErrorPolicy(getModelIndexErrorPolicy());
        clone.setModelIndexErrorMessage(getModelIndexErrorMessage());
        clone.setModelChartIsVisible(getModelChartIsVisible());

        return clone;
    }

    public int compareTo(ModelOutputChartOrder modelOutputChartOrder) {
        Long pk = modelOutputChartOrder.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ModelOutputChartOrder modelOutputChartOrder = null;

        try {
            modelOutputChartOrder = (ModelOutputChartOrder) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = modelOutputChartOrder.getPrimaryKey();

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

        sb.append("{modelOutputChartOrderPK=");
        sb.append(getModelOutputChartOrderPK());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append(", modelOutputLabel=");
        sb.append(getModelOutputLabel());
        sb.append(", modelOutputChartOrder=");
        sb.append(getModelOutputChartOrder());
        sb.append(", modelIndexRangePolicy=");
        sb.append(getModelIndexRangePolicy());
        sb.append(", modelIndexRangeMessage=");
        sb.append(getModelIndexRangeMessage());
        sb.append(", modelIndexErrorPolicy=");
        sb.append(getModelIndexErrorPolicy());
        sb.append(", modelIndexErrorMessage=");
        sb.append(getModelIndexErrorMessage());
        sb.append(", modelChartIsVisible=");
        sb.append(getModelChartIsVisible());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.models.model.ModelOutputChartOrder");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modelOutputChartOrderPK</column-name><column-value><![CDATA[");
        sb.append(getModelOutputChartOrderPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelOutputLabel</column-name><column-value><![CDATA[");
        sb.append(getModelOutputLabel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelOutputChartOrder</column-name><column-value><![CDATA[");
        sb.append(getModelOutputChartOrder());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelIndexRangePolicy</column-name><column-value><![CDATA[");
        sb.append(getModelIndexRangePolicy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelIndexRangeMessage</column-name><column-value><![CDATA[");
        sb.append(getModelIndexRangeMessage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelIndexErrorPolicy</column-name><column-value><![CDATA[");
        sb.append(getModelIndexErrorPolicy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelIndexErrorMessage</column-name><column-value><![CDATA[");
        sb.append(getModelIndexErrorMessage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelChartIsVisible</column-name><column-value><![CDATA[");
        sb.append(getModelChartIsVisible());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
