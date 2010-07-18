package com.ext.portlet.plans.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="PlanPositionsModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanPositions</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanPositions
 * @see com.ext.portlet.plans.model.impl.PlanPositionsImpl
 * @see com.ext.portlet.plans.model.impl.PlanPositionsModelImpl
 *
 */
public interface PlanPositionsModel extends BaseModel<PlanPositions> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getId();

    public void setId(Long id);

    public Long getPlanId();

    public void setPlanId(Long planId);

    public Long getPlanVersion();

    public void setPlanVersion(Long planVersion);

    public Long getVersion();

    public void setVersion(Long version);

    public Date getCreated();

    public void setCreated(Date created);

    public Long getUpdateAuthorId();

    public void setUpdateAuthorId(Long updateAuthorId);

    public PlanPositions toEscapedModel();
}
