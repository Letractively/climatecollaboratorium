package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.persistence.PlanSectionPK;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="PlanSectionModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanSection</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanSection
 * @see com.ext.portlet.plans.model.impl.PlanSectionImpl
 * @see com.ext.portlet.plans.model.impl.PlanSectionModelImpl
 *
 */
public interface PlanSectionModel extends BaseModel<PlanSection> {
    public PlanSectionPK getPrimaryKey();

    public void setPrimaryKey(PlanSectionPK pk);

    public Long getPlanSectionDefinitionId();

    public void setPlanSectionDefinitionId(Long planSectionDefinitionId);

    public Long getPlanId();

    public void setPlanId(Long planId);

    public String getContent();

    public void setContent(String content);

    public PlanSection toEscapedModel();
}
