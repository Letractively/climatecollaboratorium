package com.ext.portlet.debatemigration.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="DebateMigrationItemSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debatemigration.service.http.DebateMigrationItemServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.service.http.DebateMigrationItemServiceSoap
 *
 */
public class DebateMigrationItemSoap implements Serializable {
    private Long _debateMigrationItemPK;
    private Long _debateMigrationId;
    private Long _oldMBMessageId;
    private Long _newDebateItemId;

    public DebateMigrationItemSoap() {
    }

    public static DebateMigrationItemSoap toSoapModel(DebateMigrationItem model) {
        DebateMigrationItemSoap soapModel = new DebateMigrationItemSoap();

        soapModel.setDebateMigrationItemPK(model.getDebateMigrationItemPK());
        soapModel.setDebateMigrationId(model.getDebateMigrationId());
        soapModel.setOldMBMessageId(model.getOldMBMessageId());
        soapModel.setNewDebateItemId(model.getNewDebateItemId());

        return soapModel;
    }

    public static DebateMigrationItemSoap[] toSoapModels(
        DebateMigrationItem[] models) {
        DebateMigrationItemSoap[] soapModels = new DebateMigrationItemSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DebateMigrationItemSoap[][] toSoapModels(
        DebateMigrationItem[][] models) {
        DebateMigrationItemSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DebateMigrationItemSoap[models.length][models[0].length];
        } else {
            soapModels = new DebateMigrationItemSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DebateMigrationItemSoap[] toSoapModels(
        List<DebateMigrationItem> models) {
        List<DebateMigrationItemSoap> soapModels = new ArrayList<DebateMigrationItemSoap>(models.size());

        for (DebateMigrationItem model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DebateMigrationItemSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _debateMigrationItemPK;
    }

    public void setPrimaryKey(Long pk) {
        setDebateMigrationItemPK(pk);
    }

    public Long getDebateMigrationItemPK() {
        return _debateMigrationItemPK;
    }

    public void setDebateMigrationItemPK(Long debateMigrationItemPK) {
        _debateMigrationItemPK = debateMigrationItemPK;
    }

    public Long getDebateMigrationId() {
        return _debateMigrationId;
    }

    public void setDebateMigrationId(Long debateMigrationId) {
        _debateMigrationId = debateMigrationId;
    }

    public Long getOldMBMessageId() {
        return _oldMBMessageId;
    }

    public void setOldMBMessageId(Long oldMBMessageId) {
        _oldMBMessageId = oldMBMessageId;
    }

    public Long getNewDebateItemId() {
        return _newDebateItemId;
    }

    public void setNewDebateItemId(Long newDebateItemId) {
        _newDebateItemId = newDebateItemId;
    }
}
