package com.ext.portlet.discussions.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="DiscussionCategoryModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DiscussionCategory</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.model.DiscussionCategory
 * @see com.ext.portlet.discussions.model.impl.DiscussionCategoryImpl
 * @see com.ext.portlet.discussions.model.impl.DiscussionCategoryModelImpl
 *
 */
public interface DiscussionCategoryModel extends BaseModel<DiscussionCategory> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getPk();

    public void setPk(Long pk);

    public Long getCategoryId();

    public void setCategoryId(Long categoryId);

    public Long getCategoryGroupId();

    public void setCategoryGroupId(Long categoryGroupId);

    public Long getAuthorId();

    public void setAuthorId(Long authorId);

    public String getName();

    public void setName(String name);

    public String getDescription();

    public void setDescription(String description);

    public Date getCreateDate();

    public void setCreateDate(Date createDate);

    public Date getDeleted();

    public void setDeleted(Date deleted);

    public Integer getThreadsCount();

    public void setThreadsCount(Integer threadsCount);

    public Date getLastActivityDate();

    public void setLastActivityDate(Date lastActivityDate);

    public Long getLastActivityAuthorId();

    public void setLastActivityAuthorId(Long lastActivityAuthorId);

    public DiscussionCategory toEscapedModel();
}
