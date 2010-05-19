package com.ext.portlet.plans.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="PlanColumnSettingsModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanColumnSettings</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanColumnSettings
 * @see com.ext.portlet.plans.model.impl.PlanColumnSettingsImpl
 * @see com.ext.portlet.plans.model.impl.PlanColumnSettingsModelImpl
 *
 */
public interface PlanColumnSettingsModel extends BaseModel<PlanColumnSettings> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getPlanColumnSettingsId();

    public void setPlanColumnSettingsId(Long planColumnSettingsId);

    public String getColumnName();

    public void setColumnName(String columnName);

    public Long getPlanUserSettingsId();

    public void setPlanUserSettingsId(Long planUserSettingsId);

    public Boolean getVisible();

    public void setVisible(Boolean visible);

    public PlanColumnSettings toEscapedModel();
}
