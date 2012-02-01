package com.ext.portlet.debaterevision.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="DebateModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>Debate</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.model.Debate
 * @see com.ext.portlet.debaterevision.model.impl.DebateImpl
 * @see com.ext.portlet.debaterevision.model.impl.DebateModelImpl
 *
 */
public interface DebateModel extends BaseModel<Debate> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getDebatePK();

    public void setDebatePK(Long debatePK);

    public Long getDebateId();

    public void setDebateId(Long debateId);

    public Long getTreeVersion();

    public void setTreeVersion(Long treeVersion);

    public Date getUpdated();

    public void setUpdated(Date updated);

    public String getStatus();

    public void setStatus(String status);

    public Long getRootCommentId();

    public void setRootCommentId(Long rootCommentId);

    public Long getAuthorId();

    public void setAuthorId(Long authorId);

    public Debate toEscapedModel();
}
