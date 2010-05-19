package com.ext.portlet.debatemigration.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="DebateMigrationSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debatemigration.service.http.DebateMigrationServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.service.http.DebateMigrationServiceSoap
 *
 */
public class DebateMigrationSoap implements Serializable {
    private Long _debateMigrationPK;
    private Date _migrationDate;

    public DebateMigrationSoap() {
    }

    public static DebateMigrationSoap toSoapModel(DebateMigration model) {
        DebateMigrationSoap soapModel = new DebateMigrationSoap();

        soapModel.setDebateMigrationPK(model.getDebateMigrationPK());
        soapModel.setMigrationDate(model.getMigrationDate());

        return soapModel;
    }

    public static DebateMigrationSoap[] toSoapModels(DebateMigration[] models) {
        DebateMigrationSoap[] soapModels = new DebateMigrationSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DebateMigrationSoap[][] toSoapModels(
        DebateMigration[][] models) {
        DebateMigrationSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DebateMigrationSoap[models.length][models[0].length];
        } else {
            soapModels = new DebateMigrationSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DebateMigrationSoap[] toSoapModels(
        List<DebateMigration> models) {
        List<DebateMigrationSoap> soapModels = new ArrayList<DebateMigrationSoap>(models.size());

        for (DebateMigration model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DebateMigrationSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _debateMigrationPK;
    }

    public void setPrimaryKey(Long pk) {
        setDebateMigrationPK(pk);
    }

    public Long getDebateMigrationPK() {
        return _debateMigrationPK;
    }

    public void setDebateMigrationPK(Long debateMigrationPK) {
        _debateMigrationPK = debateMigrationPK;
    }

    public Date getMigrationDate() {
        return _migrationDate;
    }

    public void setMigrationDate(Date migrationDate) {
        _migrationDate = migrationDate;
    }
}
