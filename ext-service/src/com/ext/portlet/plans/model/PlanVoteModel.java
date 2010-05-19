package com.ext.portlet.plans.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="PlanVoteModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanVote</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanVote
 * @see com.ext.portlet.plans.model.impl.PlanVoteImpl
 * @see com.ext.portlet.plans.model.impl.PlanVoteModelImpl
 *
 */
public interface PlanVoteModel extends BaseModel<PlanVote> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getUserId();

    public void setUserId(Long userId);

    public Long getPlanId();

    public void setPlanId(Long planId);

    public Date getCreateDate();

    public void setCreateDate(Date createDate);

    public PlanVote toEscapedModel();
}
