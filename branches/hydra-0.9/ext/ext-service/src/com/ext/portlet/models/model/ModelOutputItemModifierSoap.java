package com.ext.portlet.models.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="ModelOutputItemModifierSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.models.service.http.ModelOutputItemModifierServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.http.ModelOutputItemModifierServiceSoap
 *
 */
public class ModelOutputItemModifierSoap implements Serializable {
    private Long _modelOutputItemModifierPK;
    private Long _modelId;
    private Long _modelOutputItemId;
    private Long _sourceItemId;
    private String _type;

    public ModelOutputItemModifierSoap() {
    }

    public static ModelOutputItemModifierSoap toSoapModel(
        ModelOutputItemModifier model) {
        ModelOutputItemModifierSoap soapModel = new ModelOutputItemModifierSoap();

        soapModel.setModelOutputItemModifierPK(model.getModelOutputItemModifierPK());
        soapModel.setModelId(model.getModelId());
        soapModel.setModelOutputItemId(model.getModelOutputItemId());
        soapModel.setSourceItemId(model.getSourceItemId());
        soapModel.setType(model.getType());

        return soapModel;
    }

    public static ModelOutputItemModifierSoap[] toSoapModels(
        ModelOutputItemModifier[] models) {
        ModelOutputItemModifierSoap[] soapModels = new ModelOutputItemModifierSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ModelOutputItemModifierSoap[][] toSoapModels(
        ModelOutputItemModifier[][] models) {
        ModelOutputItemModifierSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ModelOutputItemModifierSoap[models.length][models[0].length];
        } else {
            soapModels = new ModelOutputItemModifierSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ModelOutputItemModifierSoap[] toSoapModels(
        List<ModelOutputItemModifier> models) {
        List<ModelOutputItemModifierSoap> soapModels = new ArrayList<ModelOutputItemModifierSoap>(models.size());

        for (ModelOutputItemModifier model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ModelOutputItemModifierSoap[soapModels.size()]);
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

    public Long getSourceItemId() {
        return _sourceItemId;
    }

    public void setSourceItemId(Long sourceItemId) {
        _sourceItemId = sourceItemId;
    }

    public String getType() {
        return _type;
    }

    public void setType(String type) {
        _type = type;
    }
}
