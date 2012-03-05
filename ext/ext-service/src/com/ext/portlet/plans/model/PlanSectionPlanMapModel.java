package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="PlanSectionPlanMapModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanSectionPlanMap</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanSectionPlanMap
 * @see com.ext.portlet.plans.model.impl.PlanSectionPlanMapImpl
 * @see com.ext.portlet.plans.model.impl.PlanSectionPlanMapModelImpl
 *
 */
public interface PlanSectionPlanMapModel extends BaseModel<PlanSectionPlanMap> {
    public PlanSectionPlanMapPK getPrimaryKey();

    public void setPrimaryKey(PlanSectionPlanMapPK pk);

    public Long getSectionId();

    public void setSectionId(Long sectionId);

    public Long getRelatedPlanId();

    public void setRelatedPlanId(Long relatedPlanId);

    public PlanSectionPlanMap toEscapedModel();
}
