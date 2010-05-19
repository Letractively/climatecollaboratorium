package com.ext.portlet.plans.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="PlanAttributeModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanAttribute</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanAttribute
 * @see com.ext.portlet.plans.model.impl.PlanAttributeImpl
 * @see com.ext.portlet.plans.model.impl.PlanAttributeModelImpl
 *
 */
public interface PlanAttributeModel extends BaseModel<PlanAttribute> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getAttributeId();

    public void setAttributeId(Long attributeId);

    public Long getPlanId();

    public void setPlanId(Long planId);

    public String getAttributeName();

    public void setAttributeName(String attributeName);

    public String getAttributeValue();

    public void setAttributeValue(String attributeValue);

    public PlanAttribute toEscapedModel();
}
