package com.ext.portlet.debates.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="DebateDiscussionMapModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DebateDiscussionMap</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debates.model.DebateDiscussionMap
 * @see com.ext.portlet.debates.model.impl.DebateDiscussionMapImpl
 * @see com.ext.portlet.debates.model.impl.DebateDiscussionMapModelImpl
 *
 */
public interface DebateDiscussionMapModel extends BaseModel<DebateDiscussionMap> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getDebateMessageId();

    public void setDebateMessageId(Long debateMessageId);

    public Long getDiscussionThreadId();

    public void setDiscussionThreadId(Long discussionThreadId);

    public DebateDiscussionMap toEscapedModel();
}
