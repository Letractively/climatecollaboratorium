package com.ext.portlet.plans.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="PlanModelRunModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanModelRun</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanModelRun
 * @see com.ext.portlet.plans.model.impl.PlanModelRunImpl
 * @see com.ext.portlet.plans.model.impl.PlanModelRunModelImpl
 *
 */
public interface PlanModelRunModel extends BaseModel<PlanModelRun> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getId();

    public void setId(Long id);

    public Long getPlanId();

    public void setPlanId(Long planId);

    public Long getScenarioId();

    public void setScenarioId(Long scenarioId);

    public Long getPlanVersion();

    public void setPlanVersion(Long planVersion);

    public Long getVersion();

    public void setVersion(Long version);

    public Date getCreated();

    public void setCreated(Date created);

    public Long getUpdateAuthorId();

    public void setUpdateAuthorId(Long updateAuthorId);

    public PlanModelRun toEscapedModel();
}
