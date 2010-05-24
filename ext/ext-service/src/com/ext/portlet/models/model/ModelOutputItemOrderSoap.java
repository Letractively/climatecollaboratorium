package com.ext.portlet.models.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="ModelOutputItemOrderSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.models.service.http.ModelOutputItemOrderServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.http.ModelOutputItemOrderServiceSoap
 *
 */
public class ModelOutputItemOrderSoap implements Serializable {
    private Long _modelOutputItemModifierPK;
    private Long _modelId;
    private Long _modelOutputItemId;
    private Long _modelOutputItemOrder;
    private String _type;

    public ModelOutputItemOrderSoap() {
    }

    public static ModelOutputItemOrderSoap toSoapModel(
        ModelOutputItemOrder model) {
        ModelOutputItemOrderSoap soapModel = new ModelOutputItemOrderSoap();

        soapModel.setModelOutputItemModifierPK(model.getModelOutputItemModifierPK());
        soapModel.setModelId(model.getModelId());
        soapModel.setModelOutputItemId(model.getModelOutputItemId());
        soapModel.setModelOutputItemOrder(model.getModelOutputItemOrder());
        soapModel.setType(model.getType());

        return soapModel;
    }

    public static ModelOutputItemOrderSoap[] toSoapModels(
        ModelOutputItemOrder[] models) {
        ModelOutputItemOrderSoap[] soapModels = new ModelOutputItemOrderSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ModelOutputItemOrderSoap[][] toSoapModels(
        ModelOutputItemOrder[][] models) {
        ModelOutputItemOrderSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ModelOutputItemOrderSoap[models.length][models[0].length];
        } else {
            soapModels = new ModelOutputItemOrderSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ModelOutputItemOrderSoap[] toSoapModels(
        List<ModelOutputItemOrder> models) {
        List<ModelOutputItemOrderSoap> soapModels = new ArrayList<ModelOutputItemOrderSoap>(models.size());

        for (ModelOutputItemOrder model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ModelOutputItemOrderSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _modelOutputItemModifierPK;
    }

    public void setPrimaryKey(Long pk) {
        setModelOutputItemModifierPK(pk);
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
        return _type;
    }

    public void setType(String type) {
        _type = type;
    }
}
