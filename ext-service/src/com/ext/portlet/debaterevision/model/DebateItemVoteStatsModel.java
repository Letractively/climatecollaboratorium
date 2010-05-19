package com.ext.portlet.debaterevision.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="DebateItemVoteStatsModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DebateItemVoteStats</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.model.DebateItemVoteStats
 * @see com.ext.portlet.debaterevision.model.impl.DebateItemVoteStatsImpl
 * @see com.ext.portlet.debaterevision.model.impl.DebateItemVoteStatsModelImpl
 *
 */
public interface DebateItemVoteStatsModel extends BaseModel<DebateItemVoteStats> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getDebateItemVotesStats();

    public void setDebateItemVotesStats(Long debateItemVotesStats);

    public Long getDebateItemId();

    public void setDebateItemId(Long debateItemId);

    public Long getVotesCount();

    public void setVotesCount(Long votesCount);

    public DebateItemVoteStats toEscapedModel();
}
