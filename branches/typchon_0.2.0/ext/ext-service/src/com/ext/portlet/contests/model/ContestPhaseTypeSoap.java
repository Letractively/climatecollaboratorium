package com.ext.portlet.contests.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="ContestPhaseTypeSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.contests.service.http.ContestPhaseTypeServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.service.http.ContestPhaseTypeServiceSoap
 *
 */
public class ContestPhaseTypeSoap implements Serializable {
    private Long _id;
    private String _name;
    private String _description;
    private String _status;

    public ContestPhaseTypeSoap() {
    }

    public static ContestPhaseTypeSoap toSoapModel(ContestPhaseType model) {
        ContestPhaseTypeSoap soapModel = new ContestPhaseTypeSoap();

        soapModel.setId(model.getId());
        soapModel.setName(model.getName());
        soapModel.setDescription(model.getDescription());
        soapModel.setStatus(model.getStatus());

        return soapModel;
    }

    public static ContestPhaseTypeSoap[] toSoapModels(ContestPhaseType[] models) {
        ContestPhaseTypeSoap[] soapModels = new ContestPhaseTypeSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ContestPhaseTypeSoap[][] toSoapModels(
        ContestPhaseType[][] models) {
        ContestPhaseTypeSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ContestPhaseTypeSoap[models.length][models[0].length];
        } else {
            soapModels = new ContestPhaseTypeSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ContestPhaseTypeSoap[] toSoapModels(
        List<ContestPhaseType> models) {
        List<ContestPhaseTypeSoap> soapModels = new ArrayList<ContestPhaseTypeSoap>(models.size());

        for (ContestPhaseType model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ContestPhaseTypeSoap[soapModels.size()]);
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

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getStatus() {
        return _status;
    }

    public void setStatus(String status) {
        _status = status;
    }
}
