package com.ext.portlet.plans.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="PlanTeamHistoryModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanTeamHistory</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanTeamHistory
 * @see com.ext.portlet.plans.model.impl.PlanTeamHistoryImpl
 * @see com.ext.portlet.plans.model.impl.PlanTeamHistoryModelImpl
 *
 */
public interface PlanTeamHistoryModel extends BaseModel<PlanTeamHistory> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getId();

    public void setId(Long id);

    public Long getPlanId();

    public void setPlanId(Long planId);

    public Long getUserId();

    public void setUserId(Long userId);

    public String getAction();

    public void setAction(String action);

    public String getPayload();

    public void setPayload(String payload);

    public Date getCreated();

    public void setCreated(Date created);

    public Long getUpdateAuthorId();

    public void setUpdateAuthorId(Long updateAuthorId);

    public PlanTeamHistory toEscapedModel();
}
