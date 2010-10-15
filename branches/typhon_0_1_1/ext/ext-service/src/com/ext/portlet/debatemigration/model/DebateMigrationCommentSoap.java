package com.ext.portlet.debatemigration.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="DebateMigrationCommentSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debatemigration.service.http.DebateMigrationCommentServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.service.http.DebateMigrationCommentServiceSoap
 *
 */
public class DebateMigrationCommentSoap implements Serializable {
    private Long _debateMigrationCommentPK;
    private Long _debateMigrationId;
    private Long _oldMBMessageId;
    private Long _oldMBThreadId;
    private Long _newDebateCommentId;

    public DebateMigrationCommentSoap() {
    }

    public static DebateMigrationCommentSoap toSoapModel(
        DebateMigrationComment model) {
        DebateMigrationCommentSoap soapModel = new DebateMigrationCommentSoap();

        soapModel.setDebateMigrationCommentPK(model.getDebateMigrationCommentPK());
        soapModel.setDebateMigrationId(model.getDebateMigrationId());
        soapModel.setOldMBMessageId(model.getOldMBMessageId());
        soapModel.setOldMBThreadId(model.getOldMBThreadId());
        soapModel.setNewDebateCommentId(model.getNewDebateCommentId());

        return soapModel;
    }

    public static DebateMigrationCommentSoap[] toSoapModels(
        DebateMigrationComment[] models) {
        DebateMigrationCommentSoap[] soapModels = new DebateMigrationCommentSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DebateMigrationCommentSoap[][] toSoapModels(
        DebateMigrationComment[][] models) {
        DebateMigrationCommentSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DebateMigrationCommentSoap[models.length][models[0].length];
        } else {
            soapModels = new DebateMigrationCommentSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DebateMigrationCommentSoap[] toSoapModels(
        List<DebateMigrationComment> models) {
        List<DebateMigrationCommentSoap> soapModels = new ArrayList<DebateMigrationCommentSoap>(models.size());

        for (DebateMigrationComment model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DebateMigrationCommentSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _debateMigrationCommentPK;
    }

    public void setPrimaryKey(Long pk) {
        setDebateMigrationCommentPK(pk);
    }

    public Long getDebateMigrationCommentPK() {
        return _debateMigrationCommentPK;
    }

    public void setDebateMigrationCommentPK(Long debateMigrationCommentPK) {
        _debateMigrationCommentPK = debateMigrationCommentPK;
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

    public Long getOldMBThreadId() {
        return _oldMBThreadId;
    }

    public void setOldMBThreadId(Long oldMBThreadId) {
        _oldMBThreadId = oldMBThreadId;
    }

    public Long getNewDebateCommentId() {
        return _newDebateCommentId;
    }

    public void setNewDebateCommentId(Long newDebateCommentId) {
        _newDebateCommentId = newDebateCommentId;
    }
}
