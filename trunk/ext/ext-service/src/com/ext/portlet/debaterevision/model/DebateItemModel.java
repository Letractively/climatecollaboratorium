package com.ext.portlet.debaterevision.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="DebateItemModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DebateItem</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.model.DebateItem
 * @see com.ext.portlet.debaterevision.model.impl.DebateItemImpl
 * @see com.ext.portlet.debaterevision.model.impl.DebateItemModelImpl
 *
 */
public interface DebateItemModel extends BaseModel<DebateItem> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getDebateItemPK();

    public void setDebateItemPK(Long debateItemPK);

    public Long getDebateItemId();

    public void setDebateItemId(Long debateItemId);

    public Long getDebateItemParentId();

    public void setDebateItemParentId(Long debateItemParentId);

    public Long getDebateId();

    public void setDebateId(Long debateId);

    public String getDebateSummary();

    public void setDebateSummary(String debateSummary);

    public String getDebateDetail();

    public void setDebateDetail(String debateDetail);

    public String getDebatePostType();

    public void setDebatePostType(String debatePostType);

    public Long getAuthorId();

    public void setAuthorId(Long authorId);

    public Long getItemVersion();

    public void setItemVersion(Long itemVersion);

    public Long getTreeVersion();

    public void setTreeVersion(Long treeVersion);

    public Date getUpdated();

    public void setUpdated(Date updated);

    public String getStatus();

    public void setStatus(String status);

    public DebateItem toEscapedModel();
}
