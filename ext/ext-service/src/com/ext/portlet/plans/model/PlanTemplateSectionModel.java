package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.persistence.PlanTemplateSectionPK;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="PlanTemplateSectionModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanTemplateSection</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanTemplateSection
 * @see com.ext.portlet.plans.model.impl.PlanTemplateSectionImpl
 * @see com.ext.portlet.plans.model.impl.PlanTemplateSectionModelImpl
 *
 */
public interface PlanTemplateSectionModel extends BaseModel<PlanTemplateSection> {
    public PlanTemplateSectionPK getPrimaryKey();

    public void setPrimaryKey(PlanTemplateSectionPK pk);

    public Long getPlanTemplateId();

    public void setPlanTemplateId(Long planTemplateId);

    public Long getPlanSectionId();

    public void setPlanSectionId(Long planSectionId);

    public Integer getWeight();

    public void setWeight(Integer weight);

    public PlanTemplateSection toEscapedModel();
}
