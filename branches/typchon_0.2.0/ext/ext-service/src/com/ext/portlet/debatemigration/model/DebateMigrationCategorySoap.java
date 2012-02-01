package com.ext.portlet.debatemigration.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="DebateMigrationCategorySoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debatemigration.service.http.DebateMigrationCategoryServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.service.http.DebateMigrationCategoryServiceSoap
 *
 */
public class DebateMigrationCategorySoap implements Serializable {
    private Long _debateMigrationCategoryPK;
    private Long _debateMigrationId;
    private Long _oldMBCategoryId;
    private Long _newCategoryId;

    public DebateMigrationCategorySoap() {
    }

    public static DebateMigrationCategorySoap toSoapModel(
        DebateMigrationCategory model) {
        DebateMigrationCategorySoap soapModel = new DebateMigrationCategorySoap();

        soapModel.setDebateMigrationCategoryPK(model.getDebateMigrationCategoryPK());
        soapModel.setDebateMigrationId(model.getDebateMigrationId());
        soapModel.setOldMBCategoryId(model.getOldMBCategoryId());
        soapModel.setNewCategoryId(model.getNewCategoryId());

        return soapModel;
    }

    public static DebateMigrationCategorySoap[] toSoapModels(
        DebateMigrationCategory[] models) {
        DebateMigrationCategorySoap[] soapModels = new DebateMigrationCategorySoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DebateMigrationCategorySoap[][] toSoapModels(
        DebateMigrationCategory[][] models) {
        DebateMigrationCategorySoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DebateMigrationCategorySoap[models.length][models[0].length];
        } else {
            soapModels = new DebateMigrationCategorySoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DebateMigrationCategorySoap[] toSoapModels(
        List<DebateMigrationCategory> models) {
        List<DebateMigrationCategorySoap> soapModels = new ArrayList<DebateMigrationCategorySoap>(models.size());

        for (DebateMigrationCategory model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DebateMigrationCategorySoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _debateMigrationCategoryPK;
    }

    public void setPrimaryKey(Long pk) {
        setDebateMigrationCategoryPK(pk);
    }

    public Long getDebateMigrationCategoryPK() {
        return _debateMigrationCategoryPK;
    }

    public void setDebateMigrationCategoryPK(Long debateMigrationCategoryPK) {
        _debateMigrationCategoryPK = debateMigrationCategoryPK;
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

    public Long getNewCategoryId() {
        return _newCategoryId;
    }

    public void setNewCategoryId(Long newCategoryId) {
        _newCategoryId = newCategoryId;
    }
}
