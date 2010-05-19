package com.ext.portlet.debaterevision.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="DebateCommentModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DebateComment</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.model.DebateComment
 * @see com.ext.portlet.debaterevision.model.impl.DebateCommentImpl
 * @see com.ext.portlet.debaterevision.model.impl.DebateCommentModelImpl
 *
 */
public interface DebateCommentModel extends BaseModel<DebateComment> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getDebateCommentId();

    public void setDebateCommentId(Long debateCommentId);

    public Long getDebateItemId();

    public void setDebateItemId(Long debateItemId);

    public String getDebateCommentTitle();

    public void setDebateCommentTitle(String debateCommentTitle);

    public String getDebateCommentDetail();

    public void setDebateCommentDetail(String debateCommentDetail);

    public Long getItemVersion();

    public void setItemVersion(Long itemVersion);

    public Long getAuthorId();

    public void setAuthorId(Long authorId);

    public Date getUpdated();

    public void setUpdated(Date updated);

    public DebateComment toEscapedModel();
}
