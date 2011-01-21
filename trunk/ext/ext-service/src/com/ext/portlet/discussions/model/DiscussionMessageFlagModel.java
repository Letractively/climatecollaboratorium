package com.ext.portlet.discussions.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="DiscussionMessageFlagModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DiscussionMessageFlag</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.model.DiscussionMessageFlag
 * @see com.ext.portlet.discussions.model.impl.DiscussionMessageFlagImpl
 * @see com.ext.portlet.discussions.model.impl.DiscussionMessageFlagModelImpl
 *
 */
public interface DiscussionMessageFlagModel extends BaseModel<DiscussionMessageFlag> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getPk();

    public void setPk(Long pk);

    public Long getMessageId();

    public void setMessageId(Long messageId);

    public String getFlagType();

    public void setFlagType(String flagType);

    public String getData();

    public void setData(String data);

    public Date getCreated();

    public void setCreated(Date created);

    public Long getUserId();

    public void setUserId(Long userId);

    public DiscussionMessageFlag toEscapedModel();
}
