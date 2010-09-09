package com.ext.portlet.contests.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="ContestDebateModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ContestDebate</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.model.ContestDebate
 * @see com.ext.portlet.contests.model.impl.ContestDebateImpl
 * @see com.ext.portlet.contests.model.impl.ContestDebateModelImpl
 *
 */
public interface ContestDebateModel extends BaseModel<ContestDebate> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getId();

    public void setId(Long id);

    public Long getDebateId();

    public void setDebateId(Long debateId);

    public Long getContestPK();

    public void setContestPK(Long ContestPK);

    public ContestDebate toEscapedModel();
}
