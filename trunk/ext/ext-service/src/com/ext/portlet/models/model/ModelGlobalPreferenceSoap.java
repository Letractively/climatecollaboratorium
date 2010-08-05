package com.ext.portlet.models.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="ModelGlobalPreferenceSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.models.service.http.ModelGlobalPreferenceServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.http.ModelGlobalPreferenceServiceSoap
 *
 */
public class ModelGlobalPreferenceSoap implements Serializable {
    private Long _modelGlobalPreferencePK;
    private Long _modelId;
    private Boolean _visible;
    private Integer _weight;
    private Long _expertEvaluationPageId;

    public ModelGlobalPreferenceSoap() {
    }

    public static ModelGlobalPreferenceSoap toSoapModel(
        ModelGlobalPreference model) {
        ModelGlobalPreferenceSoap soapModel = new ModelGlobalPreferenceSoap();

        soapModel.setModelGlobalPreferencePK(model.getModelGlobalPreferencePK());
        soapModel.setModelId(model.getModelId());
        soapModel.setVisible(model.getVisible());
        soapModel.setWeight(model.getWeight());
        soapModel.setExpertEvaluationPageId(model.getExpertEvaluationPageId());

        return soapModel;
    }

    public static ModelGlobalPreferenceSoap[] toSoapModels(
        ModelGlobalPreference[] models) {
        ModelGlobalPreferenceSoap[] soapModels = new ModelGlobalPreferenceSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ModelGlobalPreferenceSoap[][] toSoapModels(
        ModelGlobalPreference[][] models) {
        ModelGlobalPreferenceSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ModelGlobalPreferenceSoap[models.length][models[0].length];
        } else {
            soapModels = new ModelGlobalPreferenceSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ModelGlobalPreferenceSoap[] toSoapModels(
        List<ModelGlobalPreference> models) {
        List<ModelGlobalPreferenceSoap> soapModels = new ArrayList<ModelGlobalPreferenceSoap>(models.size());

        for (ModelGlobalPreference model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ModelGlobalPreferenceSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _modelGlobalPreferencePK;
    }

    public void setPrimaryKey(Long pk) {
        setModelGlobalPreferencePK(pk);
    }

    public Long getModelGlobalPreferencePK() {
        return _modelGlobalPreferencePK;
    }

    public void setModelGlobalPreferencePK(Long modelGlobalPreferencePK) {
        _modelGlobalPreferencePK = modelGlobalPreferencePK;
    }

    public Long getModelId() {
        return _modelId;
    }

    public void setModelId(Long modelId) {
        _modelId = modelId;
    }

    public Boolean getVisible() {
        return _visible;
    }

    public void setVisible(Boolean visible) {
        _visible = visible;
    }

    public Integer getWeight() {
        return _weight;
    }

    public void setWeight(Integer weight) {
        _weight = weight;
    }

    public Long getExpertEvaluationPageId() {
        return _expertEvaluationPageId;
    }

    public void setExpertEvaluationPageId(Long expertEvaluationPageId) {
        _expertEvaluationPageId = expertEvaluationPageId;
    }
}
