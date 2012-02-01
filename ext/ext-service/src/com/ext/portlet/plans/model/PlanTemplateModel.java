package com.ext.portlet.plans.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="PlanTemplateModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanTemplate</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanTemplate
 * @see com.ext.portlet.plans.model.impl.PlanTemplateImpl
 * @see com.ext.portlet.plans.model.impl.PlanTemplateModelImpl
 *
 */
public interface PlanTemplateModel extends BaseModel<PlanTemplate> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);

    public PlanTemplate toEscapedModel();
}
