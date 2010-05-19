package com.ext.portlet.plans.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="PlanAttributeFilterModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanAttributeFilter</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanAttributeFilter
 * @see com.ext.portlet.plans.model.impl.PlanAttributeFilterImpl
 * @see com.ext.portlet.plans.model.impl.PlanAttributeFilterModelImpl
 *
 */
public interface PlanAttributeFilterModel extends BaseModel<PlanAttributeFilter> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getPlanAttributeFilterId();

    public void setPlanAttributeFilterId(Long planAttributeFilterId);

    public String getAttributeName();

    public void setAttributeName(String attributeName);

    public Long getPlanUserSettingsId();

    public void setPlanUserSettingsId(Long planUserSettingsId);

    public Double getMax();

    public void setMax(Double max);

    public Double getMin();

    public void setMin(Double min);

    public String getStringVal();

    public void setStringVal(String stringVal);

    public PlanAttributeFilter toEscapedModel();
}
