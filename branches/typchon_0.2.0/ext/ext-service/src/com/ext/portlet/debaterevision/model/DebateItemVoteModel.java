package com.ext.portlet.debaterevision.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="DebateItemVoteModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DebateItemVote</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.model.DebateItemVote
 * @see com.ext.portlet.debaterevision.model.impl.DebateItemVoteImpl
 * @see com.ext.portlet.debaterevision.model.impl.DebateItemVoteModelImpl
 *
 */
public interface DebateItemVoteModel extends BaseModel<DebateItemVote> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getDebateItemVoteId();

    public void setDebateItemVoteId(Long debateItemVoteId);

    public Long getDebateItemId();

    public void setDebateItemId(Long debateItemId);

    public Long getUserId();

    public void setUserId(Long userId);

    public DebateItemVote toEscapedModel();
}
