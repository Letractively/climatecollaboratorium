package com.ext.portlet.plans.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="PlanTypeColumnModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanTypeColumn</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanTypeColumn
 * @see com.ext.portlet.plans.model.impl.PlanTypeColumnImpl
 * @see com.ext.portlet.plans.model.impl.PlanTypeColumnModelImpl
 *
 */
public interface PlanTypeColumnModel extends BaseModel<PlanTypeColumn> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getPlanTypeColumnId();

    public void setPlanTypeColumnId(Long planTypeColumnId);

    public Long getPlanTypeId();

    public void setPlanTypeId(Long planTypeId);

    public Integer getWeight();

    public void setWeight(Integer weight);

    public String getColumnName();

    public void setColumnName(String columnName);

    public Boolean getVisibleByDefault();

    public void setVisibleByDefault(Boolean visibleByDefault);

    public PlanTypeColumn toEscapedModel();
}
