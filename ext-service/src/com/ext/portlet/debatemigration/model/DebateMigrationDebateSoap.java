package com.ext.portlet.debatemigration.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="DebateMigrationDebateSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debatemigration.service.http.DebateMigrationDebateServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.service.http.DebateMigrationDebateServiceSoap
 *
 */
public class DebateMigrationDebateSoap implements Serializable {
    private Long _debateMigrationDebatePK;
    private Long _debateMigrationId;
    private Long _oldMBCategoryId;
    private Long _newDebateId;

    public DebateMigrationDebateSoap() {
    }

    public static DebateMigrationDebateSoap toSoapModel(
        DebateMigrationDebate model) {
        DebateMigrationDebateSoap soapModel = new DebateMigrationDebateSoap();

        soapModel.setDebateMigrationDebatePK(model.getDebateMigrationDebatePK());
        soapModel.setDebateMigrationId(model.getDebateMigrationId());
        soapModel.setOldMBCategoryId(model.getOldMBCategoryId());
        soapModel.setNewDebateId(model.getNewDebateId());

        return soapModel;
    }

    public static DebateMigrationDebateSoap[] toSoapModels(
        DebateMigrationDebate[] models) {
        DebateMigrationDebateSoap[] soapModels = new DebateMigrationDebateSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DebateMigrationDebateSoap[][] toSoapModels(
        DebateMigrationDebate[][] models) {
        DebateMigrationDebateSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DebateMigrationDebateSoap[models.length][models[0].length];
        } else {
            soapModels = new DebateMigrationDebateSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DebateMigrationDebateSoap[] toSoapModels(
        List<DebateMigrationDebate> models) {
        List<DebateMigrationDebateSoap> soapModels = new ArrayList<DebateMigrationDebateSoap>(models.size());

        for (DebateMigrationDebate model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DebateMigrationDebateSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _debateMigrationDebatePK;
    }

    public void setPrimaryKey(Long pk) {
        setDebateMigrationDebatePK(pk);
    }

    public Long getDebateMigrationDebatePK() {
        return _debateMigrationDebatePK;
    }

    public void setDebateMigrationDebatePK(Long debateMigrationDebatePK) {
        _debateMigrationDebatePK = debateMigrationDebatePK;
    }

    public Long getDebateMigrationId() {
        return _debateMigrationId;
    }

    public void setDebateMigrationId(Long debateMigrationId) {
        _debateMigrationId = debateMigrationId;
    }

    public Long getOldMBCategoryId() {
        return _oldMBCategoryId;
    }

    public void setOldMBCategoryId(Long oldMBCategoryId) {
        _oldMBCategoryId = oldMBCategoryId;
    }

    public Long getNewDebateId() {
        return _newDebateId;
    }

    public void setNewDebateId(Long newDebateId) {
        _newDebateId = newDebateId;
    }
}
