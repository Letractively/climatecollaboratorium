package com.ext.portlet.contests.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="ContestPhaseSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.contests.service.http.ContestPhaseServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.service.http.ContestPhaseServiceSoap
 *
 */
public class ContestPhaseSoap implements Serializable {
    private Long _ContestPhasePK;
    private Long _ContestPK;
    private String _ContestPhaseName;
    private String _ContestPhaseDescription;
    private String _ContestPhaseStatus;
    private Date _PhaseStartDate;
    private Date _PhaseEndDate;
    private String _nextStatus;
    private Date _created;
    private Date _updated;
    private Long _authorId;
    private Boolean _phaseActive;

    public ContestPhaseSoap() {
    }

    public static ContestPhaseSoap toSoapModel(ContestPhase model) {
        ContestPhaseSoap soapModel = new ContestPhaseSoap();

        soapModel.setContestPhasePK(model.getContestPhasePK());
        soapModel.setContestPK(model.getContestPK());
        soapModel.setContestPhaseName(model.getContestPhaseName());
        soapModel.setContestPhaseDescription(model.getContestPhaseDescription());
        soapModel.setContestPhaseStatus(model.getContestPhaseStatus());
        soapModel.setPhaseStartDate(model.getPhaseStartDate());
        soapModel.setPhaseEndDate(model.getPhaseEndDate());
        soapModel.setNextStatus(model.getNextStatus());
        soapModel.setCreated(model.getCreated());
        soapModel.setUpdated(model.getUpdated());
        soapModel.setAuthorId(model.getAuthorId());
        soapModel.setPhaseActive(model.getPhaseActive());

        return soapModel;
    }

    public static ContestPhaseSoap[] toSoapModels(ContestPhase[] models) {
        ContestPhaseSoap[] soapModels = new ContestPhaseSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ContestPhaseSoap[][] toSoapModels(ContestPhase[][] models) {
        ContestPhaseSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ContestPhaseSoap[models.length][models[0].length];
        } else {
            soapModels = new ContestPhaseSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ContestPhaseSoap[] toSoapModels(List<ContestPhase> models) {
        List<ContestPhaseSoap> soapModels = new ArrayList<ContestPhaseSoap>(models.size());

        for (ContestPhase model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ContestPhaseSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _ContestPhasePK;
    }

    public void setPrimaryKey(Long pk) {
        setContestPhasePK(pk);
    }

    public Long getContestPhasePK() {
        return _ContestPhasePK;
    }

    public void setContestPhasePK(Long ContestPhasePK) {
        _ContestPhasePK = ContestPhasePK;
    }

    public Long getContestPK() {
        return _ContestPK;
    }

    public void setContestPK(Long ContestPK) {
        _ContestPK = ContestPK;
    }

    public String getContestPhaseName() {
        return _ContestPhaseName;
    }

    public void setContestPhaseName(String ContestPhaseName) {
        _ContestPhaseName = ContestPhaseName;
    }

    public String getContestPhaseDescription() {
        return _ContestPhaseDescription;
    }

    public void setContestPhaseDescription(String ContestPhaseDescription) {
        _ContestPhaseDescription = ContestPhaseDescription;
    }

    public String getContestPhaseStatus() {
        return _ContestPhaseStatus;
    }

    public void setContestPhaseStatus(String ContestPhaseStatus) {
        _ContestPhaseStatus = ContestPhaseStatus;
    }

    public Date getPhaseStartDate() {
        return _PhaseStartDate;
    }

    public void setPhaseStartDate(Date PhaseStartDate) {
        _PhaseStartDate = PhaseStartDate;
    }

    public Date getPhaseEndDate() {
        return _PhaseEndDate;
    }

    public void setPhaseEndDate(Date PhaseEndDate) {
        _PhaseEndDate = PhaseEndDate;
    }

    public String getNextStatus() {
        return _nextStatus;
    }

    public void setNextStatus(String nextStatus) {
        _nextStatus = nextStatus;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }

    public Long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(Long authorId) {
        _authorId = authorId;
    }

    public Boolean getPhaseActive() {
        return _phaseActive;
    }

    public void setPhaseActive(Boolean phaseActive) {
        _phaseActive = phaseActive;
    }
}