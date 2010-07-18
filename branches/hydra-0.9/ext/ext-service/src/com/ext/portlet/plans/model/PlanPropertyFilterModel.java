package com.ext.portlet.plans.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="PlanPropertyFilterModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanPropertyFilter</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanPropertyFilter
 * @see com.ext.portlet.plans.model.impl.PlanPropertyFilterImpl
 * @see com.ext.portlet.plans.model.impl.PlanPropertyFilterModelImpl
 *
 */
public interface PlanPropertyFilterModel extends BaseModel<PlanPropertyFilter> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getPlanPropertyFilterId();

    public void setPlanPropertyFilterId(Long planPropertyFilterId);

    public String getPropertyName();

    public void setPropertyName(String propertyName);

    public Long getPlanUserSettingsId();

    public void setPlanUserSettingsId(Long planUserSettingsId);

    public String getValue();

    public void setValue(String value);

    public PlanPropertyFilter toEscapedModel();
}
