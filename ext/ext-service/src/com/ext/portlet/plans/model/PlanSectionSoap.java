package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.persistence.PlanSectionPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="PlanSectionSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanSectionServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanSectionServiceSoap
 *
 */
public class PlanSectionSoap implements Serializable {
    private Long _planSectionDefinitionId;
    private Long _planId;
    private String _content;

    public PlanSectionSoap() {
    }

    public static PlanSectionSoap toSoapModel(PlanSection model) {
        PlanSectionSoap soapModel = new PlanSectionSoap();

        soapModel.setPlanSectionDefinitionId(model.getPlanSectionDefinitionId());
        soapModel.setPlanId(model.getPlanId());
        soapModel.setContent(model.getContent());

        return soapModel;
    }

    public static PlanSectionSoap[] toSoapModels(PlanSection[] models) {
        PlanSectionSoap[] soapModels = new PlanSectionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanSectionSoap[][] toSoapModels(PlanSection[][] models) {
        PlanSectionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanSectionSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanSectionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanSectionSoap[] toSoapModels(List<PlanSection> models) {
        List<PlanSectionSoap> soapModels = new ArrayList<PlanSectionSoap>(models.size());

        for (PlanSection model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanSectionSoap[soapModels.size()]);
    }

    public PlanSectionPK getPrimaryKey() {
        return new PlanSectionPK(_planSectionDefinitionId, _planId);
    }

    public void setPrimaryKey(PlanSectionPK pk) {
        setPlanSectionDefinitionId(pk.planSectionDefinitionId);
        setPlanId(pk.planId);
    }

    public Long getPlanSectionDefinitionId() {
        return _planSectionDefinitionId;
    }

    public void setPlanSectionDefinitionId(Long planSectionDefinitionId) {
        _planSectionDefinitionId = planSectionDefinitionId;
    }

    public Long getPlanId() {
        return _planId;
    }

    public void setPlanId(Long planId) {
        _planId = planId;
    }

    public String getContent() {
        return _content;
    }

    public void setContent(String content) {
        _content = content;
    }
}
