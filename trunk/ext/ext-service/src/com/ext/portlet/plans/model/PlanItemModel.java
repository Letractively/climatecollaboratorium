package com.ext.portlet.plans.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="PlanItemModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanItem</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanItem
 * @see com.ext.portlet.plans.model.impl.PlanItemImpl
 * @see com.ext.portlet.plans.model.impl.PlanItemModelImpl
 *
 */
public interface PlanItemModel extends BaseModel<PlanItem> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getId();

    public void setId(Long id);

    public Long getPlanId();

    public void setPlanId(Long planId);

    public String getState();

    public void setState(String state);

    public Date getUpdated();

    public void setUpdated(Date updated);

    public Long getUpdateAuthorId();

    public void setUpdateAuthorId(Long updateAuthorId);

    public String getUpdateType();

    public void setUpdateType(String updateType);

    public Long getVersion();

    public void setVersion(Long version);

    public Long getContestPhase();

    public void setContestPhase(Long ContestPhase);

    public PlanItem toEscapedModel();
}
