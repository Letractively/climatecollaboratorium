package com.ext.portlet.plans.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="PlanTypeAttributeSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanTypeAttributeServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanTypeAttributeServiceSoap
 *
 */
public class PlanTypeAttributeSoap implements Serializable {
    private Long _planTypeAttributeId;
    private Long _planTypeId;
    private String _attributeName;

    public PlanTypeAttributeSoap() {
    }

    public static PlanTypeAttributeSoap toSoapModel(PlanTypeAttribute model) {
        PlanTypeAttributeSoap soapModel = new PlanTypeAttributeSoap();

        soapModel.setPlanTypeAttributeId(model.getPlanTypeAttributeId());
        soapModel.setPlanTypeId(model.getPlanTypeId());
        soapModel.setAttributeName(model.getAttributeName());

        return soapModel;
    }

    public static PlanTypeAttributeSoap[] toSoapModels(
        PlanTypeAttribute[] models) {
        PlanTypeAttributeSoap[] soapModels = new PlanTypeAttributeSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanTypeAttributeSoap[][] toSoapModels(
        PlanTypeAttribute[][] models) {
        PlanTypeAttributeSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanTypeAttributeSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanTypeAttributeSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanTypeAttributeSoap[] toSoapModels(
        List<PlanTypeAttribute> models) {
        List<PlanTypeAttributeSoap> soapModels = new ArrayList<PlanTypeAttributeSoap>(models.size());

        for (PlanTypeAttribute model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanTypeAttributeSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _planTypeAttributeId;
    }

    public void setPrimaryKey(Long pk) {
        setPlanTypeAttributeId(pk);
    }

    public Long getPlanTypeAttributeId() {
        return _planTypeAttributeId;
    }

    public void setPlanTypeAttributeId(Long planTypeAttributeId) {
        _planTypeAttributeId = planTypeAttributeId;
    }

    public Long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(Long planTypeId) {
        _planTypeId = planTypeId;
    }

    public String getAttributeName() {
        return _attributeName;
    }

    public void setAttributeName(String attributeName) {
        _attributeName = attributeName;
    }
}
