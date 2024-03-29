package com.ext.portlet.plans.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="PlanFanSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanFanServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanFanServiceSoap
 *
 */
public class PlanFanSoap implements Serializable {
    private Long _id;
    private Long _userId;
    private Long _planId;
    private Date _created;
    private Date _deleted;

    public PlanFanSoap() {
    }

    public static PlanFanSoap toSoapModel(PlanFan model) {
        PlanFanSoap soapModel = new PlanFanSoap();

        soapModel.setId(model.getId());
        soapModel.setUserId(model.getUserId());
        soapModel.setPlanId(model.getPlanId());
        soapModel.setCreated(model.getCreated());
        soapModel.setDeleted(model.getDeleted());

        return soapModel;
    }

    public static PlanFanSoap[] toSoapModels(PlanFan[] models) {
        PlanFanSoap[] soapModels = new PlanFanSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanFanSoap[][] toSoapModels(PlanFan[][] models) {
        PlanFanSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanFanSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanFanSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanFanSoap[] toSoapModels(List<PlanFan> models) {
        List<PlanFanSoap> soapModels = new ArrayList<PlanFanSoap>(models.size());

        for (PlanFan model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanFanSoap[soapModels.size()]);
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

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public Long getPlanId() {
        return _planId;
    }

    public void setPlanId(Long planId) {
        _planId = planId;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Date getDeleted() {
        return _deleted;
    }

    public void setDeleted(Date deleted) {
        _deleted = deleted;
    }
}
