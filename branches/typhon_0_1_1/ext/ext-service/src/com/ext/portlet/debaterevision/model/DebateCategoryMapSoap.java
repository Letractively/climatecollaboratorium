package com.ext.portlet.debaterevision.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="DebateCategoryMapSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debaterevision.service.http.DebateCategoryMapServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.http.DebateCategoryMapServiceSoap
 *
 */
public class DebateCategoryMapSoap implements Serializable {
    private Long _debateCategoryMapPK;
    private Long _debateCategoryPK;
    private Long _debateId;

    public DebateCategoryMapSoap() {
    }

    public static DebateCategoryMapSoap toSoapModel(DebateCategoryMap model) {
        DebateCategoryMapSoap soapModel = new DebateCategoryMapSoap();

        soapModel.setDebateCategoryMapPK(model.getDebateCategoryMapPK());
        soapModel.setDebateCategoryPK(model.getDebateCategoryPK());
        soapModel.setDebateId(model.getDebateId());

        return soapModel;
    }

    public static DebateCategoryMapSoap[] toSoapModels(
        DebateCategoryMap[] models) {
        DebateCategoryMapSoap[] soapModels = new DebateCategoryMapSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DebateCategoryMapSoap[][] toSoapModels(
        DebateCategoryMap[][] models) {
        DebateCategoryMapSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DebateCategoryMapSoap[models.length][models[0].length];
        } else {
            soapModels = new DebateCategoryMapSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DebateCategoryMapSoap[] toSoapModels(
        List<DebateCategoryMap> models) {
        List<DebateCategoryMapSoap> soapModels = new ArrayList<DebateCategoryMapSoap>(models.size());

        for (DebateCategoryMap model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DebateCategoryMapSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _debateCategoryMapPK;
    }

    public void setPrimaryKey(Long pk) {
        setDebateCategoryMapPK(pk);
    }

    public Long getDebateCategoryMapPK() {
        return _debateCategoryMapPK;
    }

    public void setDebateCategoryMapPK(Long debateCategoryMapPK) {
        _debateCategoryMapPK = debateCategoryMapPK;
    }

    public Long getDebateCategoryPK() {
        return _debateCategoryPK;
    }

    public void setDebateCategoryPK(Long debateCategoryPK) {
        _debateCategoryPK = debateCategoryPK;
    }

    public Long getDebateId() {
        return _debateId;
    }

    public void setDebateId(Long debateId) {
        _debateId = debateId;
    }
}
