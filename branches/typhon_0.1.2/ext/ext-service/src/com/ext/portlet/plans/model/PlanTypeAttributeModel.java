package com.ext.portlet.plans.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="PlanTypeAttributeModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanTypeAttribute</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanTypeAttribute
 * @see com.ext.portlet.plans.model.impl.PlanTypeAttributeImpl
 * @see com.ext.portlet.plans.model.impl.PlanTypeAttributeModelImpl
 *
 */
public interface PlanTypeAttributeModel extends BaseModel<PlanTypeAttribute> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getPlanTypeAttributeId();

    public void setPlanTypeAttributeId(Long planTypeAttributeId);

    public Long getPlanTypeId();

    public void setPlanTypeId(Long planTypeId);

    public String getAttributeName();

    public void setAttributeName(String attributeName);

    public PlanTypeAttribute toEscapedModel();
}
