package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.persistence.PlanTemplateSectionPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="PlanTemplateSectionSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanTemplateSectionServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanTemplateSectionServiceSoap
 *
 */
public class PlanTemplateSectionSoap implements Serializable {
    private Long _planTemplateId;
    private Long _planSectionId;
    private Integer _weight;

    public PlanTemplateSectionSoap() {
    }

    public static PlanTemplateSectionSoap toSoapModel(PlanTemplateSection model) {
        PlanTemplateSectionSoap soapModel = new PlanTemplateSectionSoap();

        soapModel.setPlanTemplateId(model.getPlanTemplateId());
        soapModel.setPlanSectionId(model.getPlanSectionId());
        soapModel.setWeight(model.getWeight());

        return soapModel;
    }

    public static PlanTemplateSectionSoap[] toSoapModels(
        PlanTemplateSection[] models) {
        PlanTemplateSectionSoap[] soapModels = new PlanTemplateSectionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanTemplateSectionSoap[][] toSoapModels(
        PlanTemplateSection[][] models) {
        PlanTemplateSectionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanTemplateSectionSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanTemplateSectionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanTemplateSectionSoap[] toSoapModels(
        List<PlanTemplateSection> models) {
        List<PlanTemplateSectionSoap> soapModels = new ArrayList<PlanTemplateSectionSoap>(models.size());

        for (PlanTemplateSection model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanTemplateSectionSoap[soapModels.size()]);
    }

    public PlanTemplateSectionPK getPrimaryKey() {
        return new PlanTemplateSectionPK(_planTemplateId, _planSectionId);
    }

    public void setPrimaryKey(PlanTemplateSectionPK pk) {
        setPlanTemplateId(pk.planTemplateId);
        setPlanSectionId(pk.planSectionId);
    }

    public Long getPlanTemplateId() {
        return _planTemplateId;
    }

    public void setPlanTemplateId(Long planTemplateId) {
        _planTemplateId = planTemplateId;
    }

    public Long getPlanSectionId() {
        return _planSectionId;
    }

    public void setPlanSectionId(Long planSectionId) {
        _planSectionId = planSectionId;
    }

    public Integer getWeight() {
        return _weight;
    }

    public void setWeight(Integer weight) {
        _weight = weight;
    }
}
