package com.ext.portlet.plans.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="PlanAttributeFilterSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanAttributeFilterServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanAttributeFilterServiceSoap
 *
 */
public class PlanAttributeFilterSoap implements Serializable {
    private Long _planAttributeFilterId;
    private String _attributeName;
    private Long _planUserSettingsId;
    private Double _max;
    private Double _min;
    private String _stringVal;

    public PlanAttributeFilterSoap() {
    }

    public static PlanAttributeFilterSoap toSoapModel(PlanAttributeFilter model) {
        PlanAttributeFilterSoap soapModel = new PlanAttributeFilterSoap();

        soapModel.setPlanAttributeFilterId(model.getPlanAttributeFilterId());
        soapModel.setAttributeName(model.getAttributeName());
        soapModel.setPlanUserSettingsId(model.getPlanUserSettingsId());
        soapModel.setMax(model.getMax());
        soapModel.setMin(model.getMin());
        soapModel.setStringVal(model.getStringVal());

        return soapModel;
    }

    public static PlanAttributeFilterSoap[] toSoapModels(
        PlanAttributeFilter[] models) {
        PlanAttributeFilterSoap[] soapModels = new PlanAttributeFilterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanAttributeFilterSoap[][] toSoapModels(
        PlanAttributeFilter[][] models) {
        PlanAttributeFilterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanAttributeFilterSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanAttributeFilterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanAttributeFilterSoap[] toSoapModels(
        List<PlanAttributeFilter> models) {
        List<PlanAttributeFilterSoap> soapModels = new ArrayList<PlanAttributeFilterSoap>(models.size());

        for (PlanAttributeFilter model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanAttributeFilterSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _planAttributeFilterId;
    }

    public void setPrimaryKey(Long pk) {
        setPlanAttributeFilterId(pk);
    }

    public Long getPlanAttributeFilterId() {
        return _planAttributeFilterId;
    }

    public void setPlanAttributeFilterId(Long planAttributeFilterId) {
        _planAttributeFilterId = planAttributeFilterId;
    }

    public String getAttributeName() {
        return _attributeName;
    }

    public void setAttributeName(String attributeName) {
        _attributeName = attributeName;
    }

    public Long getPlanUserSettingsId() {
        return _planUserSettingsId;
    }

    public void setPlanUserSettingsId(Long planUserSettingsId) {
        _planUserSettingsId = planUserSettingsId;
    }

    public Double getMax() {
        return _max;
    }

    public void setMax(Double max) {
        _max = max;
    }

    public Double getMin() {
        return _min;
    }

    public void setMin(Double min) {
        _min = min;
    }

    public String getStringVal() {
        return _stringVal;
    }

    public void setStringVal(String stringVal) {
        _stringVal = stringVal;
    }
}
