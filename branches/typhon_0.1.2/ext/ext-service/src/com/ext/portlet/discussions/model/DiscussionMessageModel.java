package com.ext.portlet.discussions.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="DiscussionMessageModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DiscussionMessage</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.model.DiscussionMessage
 * @see com.ext.portlet.discussions.model.impl.DiscussionMessageImpl
 * @see com.ext.portlet.discussions.model.impl.DiscussionMessageModelImpl
 *
 */
public interface DiscussionMessageModel extends BaseModel<DiscussionMessage> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getPk();

    public void setPk(Long pk);

    public Long getMessageId();

    public void setMessageId(Long messageId);

    public String getSubject();

    public void setSubject(String subject);

    public String getBody();

    public void setBody(String body);

    public Long getThreadId();

    public void setThreadId(Long threadId);

    public Long getCategoryId();

    public void setCategoryId(Long categoryId);

    public Long getCategoryGroupId();

    public void setCategoryGroupId(Long categoryGroupId);

    public Long getAuthorId();

    public void setAuthorId(Long authorId);

    public Date getCreateDate();

    public void setCreateDate(Date createDate);

    public Long getVersion();

    public void setVersion(Long version);

    public Date getDeleted();

    public void setDeleted(Date deleted);

    public Integer getResponsesCount();

    public void setResponsesCount(Integer responsesCount);

    public Date getLastActivityDate();

    public void setLastActivityDate(Date lastActivityDate);

    public Long getLastActivityAuthorId();

    public void setLastActivityAuthorId(Long lastActivityAuthorId);

    public DiscussionMessage toEscapedModel();
}
