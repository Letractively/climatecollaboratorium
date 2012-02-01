package com.ext.portlet.plans.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="PlanFanModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanFan</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanFan
 * @see com.ext.portlet.plans.model.impl.PlanFanImpl
 * @see com.ext.portlet.plans.model.impl.PlanFanModelImpl
 *
 */
public interface PlanFanModel extends BaseModel<PlanFan> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getId();

    public void setId(Long id);

    public Long getUserId();

    public void setUserId(Long userId);

    public Long getPlanId();

    public void setPlanId(Long planId);

    public Date getCreated();

    public void setCreated(Date created);

    public Date getDeleted();

    public void setDeleted(Date deleted);

    public PlanFan toEscapedModel();
}
