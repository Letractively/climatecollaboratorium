package com.ext.portlet.debaterevision.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="DebateItemVoteSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debaterevision.service.http.DebateItemVoteServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.http.DebateItemVoteServiceSoap
 *
 */
public class DebateItemVoteSoap implements Serializable {
    private Long _debateItemVoteId;
    private Long _debateItemId;
    private Long _userId;

    public DebateItemVoteSoap() {
    }

    public static DebateItemVoteSoap toSoapModel(DebateItemVote model) {
        DebateItemVoteSoap soapModel = new DebateItemVoteSoap();

        soapModel.setDebateItemVoteId(model.getDebateItemVoteId());
        soapModel.setDebateItemId(model.getDebateItemId());
        soapModel.setUserId(model.getUserId());

        return soapModel;
    }

    public static DebateItemVoteSoap[] toSoapModels(DebateItemVote[] models) {
        DebateItemVoteSoap[] soapModels = new DebateItemVoteSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DebateItemVoteSoap[][] toSoapModels(DebateItemVote[][] models) {
        DebateItemVoteSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DebateItemVoteSoap[models.length][models[0].length];
        } else {
            soapModels = new DebateItemVoteSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DebateItemVoteSoap[] toSoapModels(List<DebateItemVote> models) {
        List<DebateItemVoteSoap> soapModels = new ArrayList<DebateItemVoteSoap>(models.size());

        for (DebateItemVote model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DebateItemVoteSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _debateItemVoteId;
    }

    public void setPrimaryKey(Long pk) {
        setDebateItemVoteId(pk);
    }

    public Long getDebateItemVoteId() {
        return _debateItemVoteId;
    }

    public void setDebateItemVoteId(Long debateItemVoteId) {
        _debateItemVoteId = debateItemVoteId;
    }

    public Long getDebateItemId() {
        return _debateItemId;
    }

    public void setDebateItemId(Long debateItemId) {
        _debateItemId = debateItemId;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }
}
