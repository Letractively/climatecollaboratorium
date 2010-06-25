package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.persistence.PlanPositionItemPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="PlanPositionItemSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanPositionItemServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanPositionItemServiceSoap
 *
 */
public class PlanPositionItemSoap implements Serializable {
    private Long _planPositionsId;
    private Long _positionId;

    public PlanPositionItemSoap() {
    }

    public static PlanPositionItemSoap toSoapModel(PlanPositionItem model) {
        PlanPositionItemSoap soapModel = new PlanPositionItemSoap();

        soapModel.setPlanPositionsId(model.getPlanPositionsId());
        soapModel.setPositionId(model.getPositionId());

        return soapModel;
    }

    public static PlanPositionItemSoap[] toSoapModels(PlanPositionItem[] models) {
        PlanPositionItemSoap[] soapModels = new PlanPositionItemSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanPositionItemSoap[][] toSoapModels(
        PlanPositionItem[][] models) {
        PlanPositionItemSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanPositionItemSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanPositionItemSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanPositionItemSoap[] toSoapModels(
        List<PlanPositionItem> models) {
        List<PlanPositionItemSoap> soapModels = new ArrayList<PlanPositionItemSoap>(models.size());

        for (PlanPositionItem model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanPositionItemSoap[soapModels.size()]);
    }

    public PlanPositionItemPK getPrimaryKey() {
        return new PlanPositionItemPK(_planPositionsId, _positionId);
    }

    public void setPrimaryKey(PlanPositionItemPK pk) {
        setPlanPositionsId(pk.planPositionsId);
        setPositionId(pk.positionId);
    }

    public Long getPlanPositionsId() {
        return _planPositionsId;
    }

    public void setPlanPositionsId(Long planPositionsId) {
        _planPositionsId = planPositionsId;
    }

    public Long getPositionId() {
        return _positionId;
    }

    public void setPositionId(Long positionId) {
        _positionId = positionId;
    }
}
