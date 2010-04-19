package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.persistence.PlansFilterPositionPK;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="PlansFilterPositionModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlansFilterPosition</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlansFilterPosition
 * @see com.ext.portlet.plans.model.impl.PlansFilterPositionImpl
 * @see com.ext.portlet.plans.model.impl.PlansFilterPositionModelImpl
 *
 */
public interface PlansFilterPositionModel extends BaseModel<PlansFilterPosition> {
    public PlansFilterPositionPK getPrimaryKey();

    public void setPrimaryKey(PlansFilterPositionPK pk);

    public Long getUserId();

    public void setUserId(Long userId);

    public Long getPlanTypeId();

    public void setPlanTypeId(Long planTypeId);

    public Long getPositionId();

    public void setPositionId(Long positionId);

    public PlansFilterPosition toEscapedModel();
}
