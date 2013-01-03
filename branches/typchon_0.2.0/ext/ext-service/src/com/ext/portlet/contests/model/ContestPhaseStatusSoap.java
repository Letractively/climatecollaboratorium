package com.ext.portlet.contests.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="ContestPhaseStatusSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.contests.service.http.ContestPhaseStatusServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.service.http.ContestPhaseStatusServiceSoap
 *
 */
public class ContestPhaseStatusSoap implements Serializable {
    private String _name;
    private String _description;

    public ContestPhaseStatusSoap() {
    }

    public static ContestPhaseStatusSoap toSoapModel(ContestPhaseStatus model) {
        ContestPhaseStatusSoap soapModel = new ContestPhaseStatusSoap();

        soapModel.setName(model.getName());
        soapModel.setDescription(model.getDescription());

        return soapModel;
    }

    public static ContestPhaseStatusSoap[] toSoapModels(
        ContestPhaseStatus[] models) {
        ContestPhaseStatusSoap[] soapModels = new ContestPhaseStatusSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ContestPhaseStatusSoap[][] toSoapModels(
        ContestPhaseStatus[][] models) {
        ContestPhaseStatusSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ContestPhaseStatusSoap[models.length][models[0].length];
        } else {
            soapModels = new ContestPhaseStatusSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ContestPhaseStatusSoap[] toSoapModels(
        List<ContestPhaseStatus> models) {
        List<ContestPhaseStatusSoap> soapModels = new ArrayList<ContestPhaseStatusSoap>(models.size());

        for (ContestPhaseStatus model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ContestPhaseStatusSoap[soapModels.size()]);
    }

    public String getPrimaryKey() {
        return _name;
    }

    public void setPrimaryKey(String pk) {
        setName(pk);
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
}
