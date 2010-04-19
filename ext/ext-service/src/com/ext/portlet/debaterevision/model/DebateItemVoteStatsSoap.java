package com.ext.portlet.debaterevision.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="DebateItemVoteStatsSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debaterevision.service.http.DebateItemVoteStatsServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.http.DebateItemVoteStatsServiceSoap
 *
 */
public class DebateItemVoteStatsSoap implements Serializable {
    private Long _debateItemVotesStats;
    private Long _debateItemId;
    private Long _votesCount;

    public DebateItemVoteStatsSoap() {
    }

    public static DebateItemVoteStatsSoap toSoapModel(DebateItemVoteStats model) {
        DebateItemVoteStatsSoap soapModel = new DebateItemVoteStatsSoap();

        soapModel.setDebateItemVotesStats(model.getDebateItemVotesStats());
        soapModel.setDebateItemId(model.getDebateItemId());
        soapModel.setVotesCount(model.getVotesCount());

        return soapModel;
    }

    public static DebateItemVoteStatsSoap[] toSoapModels(
        DebateItemVoteStats[] models) {
        DebateItemVoteStatsSoap[] soapModels = new DebateItemVoteStatsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DebateItemVoteStatsSoap[][] toSoapModels(
        DebateItemVoteStats[][] models) {
        DebateItemVoteStatsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DebateItemVoteStatsSoap[models.length][models[0].length];
        } else {
            soapModels = new DebateItemVoteStatsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DebateItemVoteStatsSoap[] toSoapModels(
        List<DebateItemVoteStats> models) {
        List<DebateItemVoteStatsSoap> soapModels = new ArrayList<DebateItemVoteStatsSoap>(models.size());

        for (DebateItemVoteStats model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DebateItemVoteStatsSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _debateItemVotesStats;
    }

    public void setPrimaryKey(Long pk) {
        setDebateItemVotesStats(pk);
    }

    public Long getDebateItemVotesStats() {
        return _debateItemVotesStats;
    }

    public void setDebateItemVotesStats(Long debateItemVotesStats) {
        _debateItemVotesStats = debateItemVotesStats;
    }

    public Long getDebateItemId() {
        return _debateItemId;
    }

    public void setDebateItemId(Long debateItemId) {
        _debateItemId = debateItemId;
    }

    public Long getVotesCount() {
        return _votesCount;
    }

    public void setVotesCount(Long votesCount) {
        _votesCount = votesCount;
    }
}
