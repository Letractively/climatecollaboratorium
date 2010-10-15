package com.ext.portlet.plans.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="PlansUserSettingsModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlansUserSettings</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlansUserSettings
 * @see com.ext.portlet.plans.model.impl.PlansUserSettingsImpl
 * @see com.ext.portlet.plans.model.impl.PlansUserSettingsModelImpl
 *
 */
public interface PlansUserSettingsModel extends BaseModel<PlansUserSettings> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getPlanUserSettingsId();

    public void setPlanUserSettingsId(Long planUserSettingsId);

    public Long getUserId();

    public void setUserId(Long userId);

    public Long getPlanTypeId();

    public void setPlanTypeId(Long planTypeId);

    public String getSortColumn();

    public void setSortColumn(String sortColumn);

    public String getSortDirection();

    public void setSortDirection(String sortDirection);

    public Boolean getFilterEnabled();

    public void setFilterEnabled(Boolean filterEnabled);

    public Boolean getFilterPositionsAll();

    public void setFilterPositionsAll(Boolean filterPositionsAll);

    public PlansUserSettings toEscapedModel();
}
