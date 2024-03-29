package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.persistence.PlanPositionPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="PlanPositionSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanPositionServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanPositionServiceSoap
 *
 */
public class PlanPositionSoap implements Serializable {
    private Long _planId;
    private Long _positionId;
    private Long _userId;
    private String _userName;
    private Date _createDate;
    private Date _modifiedDate;

    public PlanPositionSoap() {
    }

    public static PlanPositionSoap toSoapModel(PlanPosition model) {
        PlanPositionSoap soapModel = new PlanPositionSoap();

        soapModel.setPlanId(model.getPlanId());
        soapModel.setPositionId(model.getPositionId());
        soapModel.setUserId(model.getUserId());
        soapModel.setUserName(model.getUserName());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setModifiedDate(model.getModifiedDate());

        return soapModel;
    }

    public static PlanPositionSoap[] toSoapModels(PlanPosition[] models) {
        PlanPositionSoap[] soapModels = new PlanPositionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanPositionSoap[][] toSoapModels(PlanPosition[][] models) {
        PlanPositionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanPositionSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanPositionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanPositionSoap[] toSoapModels(List<PlanPosition> models) {
        List<PlanPositionSoap> soapModels = new ArrayList<PlanPositionSoap>(models.size());

        for (PlanPosition model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanPositionSoap[soapModels.size()]);
    }

    public PlanPositionPK getPrimaryKey() {
        return new PlanPositionPK(_planId, _positionId);
    }

    public void setPrimaryKey(PlanPositionPK pk) {
        setPlanId(pk.planId);
        setPositionId(pk.positionId);
    }

    public Long getPlanId() {
        return _planId;
    }

    public void setPlanId(Long planId) {
        _planId = planId;
    }

    public Long getPositionId() {
        return _positionId;
    }

    public void setPositionId(Long positionId) {
        _positionId = positionId;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public String getUserName() {
        return _userName;
    }

    public void setUserName(String userName) {
        _userName = userName;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }
}
