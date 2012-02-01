package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.persistence.PlanRelatedPK;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="PlanRelatedModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanRelated</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanRelated
 * @see com.ext.portlet.plans.model.impl.PlanRelatedImpl
 * @see com.ext.portlet.plans.model.impl.PlanRelatedModelImpl
 *
 */
public interface PlanRelatedModel extends BaseModel<PlanRelated> {
    public PlanRelatedPK getPrimaryKey();

    public void setPrimaryKey(PlanRelatedPK pk);

    public Long getSectionId();

    public void setSectionId(Long sectionId);

    public Long getRelatedPlanId();

    public void setRelatedPlanId(Long relatedPlanId);

    public PlanRelated toEscapedModel();
}
