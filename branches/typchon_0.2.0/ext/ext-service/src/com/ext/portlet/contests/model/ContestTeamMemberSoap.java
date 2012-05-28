package com.ext.portlet.contests.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="ContestTeamMemberSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.contests.service.http.ContestTeamMemberServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.service.http.ContestTeamMemberServiceSoap
 *
 */
public class ContestTeamMemberSoap implements Serializable {
    private Long _id;
    private Long _contestId;
    private Long _userId;
    private String _role;

    public ContestTeamMemberSoap() {
    }

    public static ContestTeamMemberSoap toSoapModel(ContestTeamMember model) {
        ContestTeamMemberSoap soapModel = new ContestTeamMemberSoap();

        soapModel.setId(model.getId());
        soapModel.setContestId(model.getContestId());
        soapModel.setUserId(model.getUserId());
        soapModel.setRole(model.getRole());

        return soapModel;
    }

    public static ContestTeamMemberSoap[] toSoapModels(
        ContestTeamMember[] models) {
        ContestTeamMemberSoap[] soapModels = new ContestTeamMemberSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ContestTeamMemberSoap[][] toSoapModels(
        ContestTeamMember[][] models) {
        ContestTeamMemberSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ContestTeamMemberSoap[models.length][models[0].length];
        } else {
            soapModels = new ContestTeamMemberSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ContestTeamMemberSoap[] toSoapModels(
        List<ContestTeamMember> models) {
        List<ContestTeamMemberSoap> soapModels = new ArrayList<ContestTeamMemberSoap>(models.size());

        for (ContestTeamMember model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ContestTeamMemberSoap[soapModels.size()]);
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

    public Long getContestId() {
        return _contestId;
    }

    public void setContestId(Long contestId) {
        _contestId = contestId;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public String getRole() {
        return _role;
    }

    public void setRole(String role) {
        _role = role;
    }
}
