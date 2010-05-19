package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.persistence.PlanPositionPK;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="PlanPositionModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanPosition</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanPosition
 * @see com.ext.portlet.plans.model.impl.PlanPositionImpl
 * @see com.ext.portlet.plans.model.impl.PlanPositionModelImpl
 *
 */
public interface PlanPositionModel extends BaseModel<PlanPosition> {
    public PlanPositionPK getPrimaryKey();

    public void setPrimaryKey(PlanPositionPK pk);

    public Long getPlanId();

    public void setPlanId(Long planId);

    public Long getPositionId();

    public void setPositionId(Long positionId);

    public Long getUserId();

    public void setUserId(Long userId);

    public String getUserName();

    public void setUserName(String userName);

    public Date getCreateDate();

    public void setCreateDate(Date createDate);

    public Date getModifiedDate();

    public void setModifiedDate(Date modifiedDate);

    public PlanPosition toEscapedModel();
}
