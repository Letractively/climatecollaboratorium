package com.ext.portlet.discussions.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="DiscussionCategoryGroupModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DiscussionCategoryGroup</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.model.DiscussionCategoryGroup
 * @see com.ext.portlet.discussions.model.impl.DiscussionCategoryGroupImpl
 * @see com.ext.portlet.discussions.model.impl.DiscussionCategoryGroupModelImpl
 *
 */
public interface DiscussionCategoryGroupModel extends BaseModel<DiscussionCategoryGroup> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getId();

    public void setId(Long id);

    public String getDescription();

    public void setDescription(String description);

    public DiscussionCategoryGroup toEscapedModel();
}
