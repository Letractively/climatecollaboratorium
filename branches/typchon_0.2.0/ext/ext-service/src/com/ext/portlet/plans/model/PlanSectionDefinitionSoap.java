package com.ext.portlet.plans.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="PlanSectionDefinitionSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanSectionDefinitionServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanSectionDefinitionServiceSoap
 *
 */
public class PlanSectionDefinitionSoap implements Serializable {
    private Long _id;
    private String _title;
    private String _defaultText;
    private Long _ontologyTermId;

    public PlanSectionDefinitionSoap() {
    }

    public static PlanSectionDefinitionSoap toSoapModel(
        PlanSectionDefinition model) {
        PlanSectionDefinitionSoap soapModel = new PlanSectionDefinitionSoap();

        soapModel.setId(model.getId());
        soapModel.setTitle(model.getTitle());
        soapModel.setDefaultText(model.getDefaultText());
        soapModel.setOntologyTermId(model.getOntologyTermId());

        return soapModel;
    }

    public static PlanSectionDefinitionSoap[] toSoapModels(
        PlanSectionDefinition[] models) {
        PlanSectionDefinitionSoap[] soapModels = new PlanSectionDefinitionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanSectionDefinitionSoap[][] toSoapModels(
        PlanSectionDefinition[][] models) {
        PlanSectionDefinitionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanSectionDefinitionSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanSectionDefinitionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanSectionDefinitionSoap[] toSoapModels(
        List<PlanSectionDefinition> models) {
        List<PlanSectionDefinitionSoap> soapModels = new ArrayList<PlanSectionDefinitionSoap>(models.size());

        for (PlanSectionDefinition model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanSectionDefinitionSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(Long pk) {
        setId(pk);
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        _id = id;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getDefaultText() {
        return _defaultText;
    }

    public void setDefaultText(String defaultText) {
        _defaultText = defaultText;
    }

    public Long getOntologyTermId() {
        return _ontologyTermId;
    }

    public void setOntologyTermId(Long ontologyTermId) {
        _ontologyTermId = ontologyTermId;
    }
}
