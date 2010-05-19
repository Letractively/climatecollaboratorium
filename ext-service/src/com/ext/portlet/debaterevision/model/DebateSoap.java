package com.ext.portlet.debaterevision.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="DebateSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debaterevision.service.http.DebateServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.http.DebateServiceSoap
 *
 */
public class DebateSoap implements Serializable {
    private Long _debatePK;
    private Long _debateId;
    private Long _treeVersion;
    private Date _updated;
    private String _status;
    private Long _rootCommentId;
    private Long _authorId;

    public DebateSoap() {
    }

    public static DebateSoap toSoapModel(Debate model) {
        DebateSoap soapModel = new DebateSoap();

        soapModel.setDebatePK(model.getDebatePK());
        soapModel.setDebateId(model.getDebateId());
        soapModel.setTreeVersion(model.getTreeVersion());
        soapModel.setUpdated(model.getUpdated());
        soapModel.setStatus(model.getStatus());
        soapModel.setRootCommentId(model.getRootCommentId());
        soapModel.setAuthorId(model.getAuthorId());

        return soapModel;
    }

    public static DebateSoap[] toSoapModels(Debate[] models) {
        DebateSoap[] soapModels = new DebateSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DebateSoap[][] toSoapModels(Debate[][] models) {
        DebateSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DebateSoap[models.length][models[0].length];
        } else {
            soapModels = new DebateSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DebateSoap[] toSoapModels(List<Debate> models) {
        List<DebateSoap> soapModels = new ArrayList<DebateSoap>(models.size());

        for (Debate model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DebateSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _debatePK;
    }

    public void setPrimaryKey(Long pk) {
        setDebatePK(pk);
    }

    public Long getDebatePK() {
        return _debatePK;
    }

    public void setDebatePK(Long debatePK) {
        _debatePK = debatePK;
    }

    public Long getDebateId() {
        return _debateId;
    }

    public void setDebateId(Long debateId) {
        _debateId = debateId;
    }

    public Long getTreeVersion() {
        return _treeVersion;
    }

    public void setTreeVersion(Long treeVersion) {
        _treeVersion = treeVersion;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }

    public String getStatus() {
        return _status;
    }

    public void setStatus(String status) {
        _status = status;
    }

    public Long getRootCommentId() {
        return _rootCommentId;
    }

    public void setRootCommentId(Long rootCommentId) {
        _rootCommentId = rootCommentId;
    }

    public Long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(Long authorId) {
        _authorId = authorId;
    }
}
