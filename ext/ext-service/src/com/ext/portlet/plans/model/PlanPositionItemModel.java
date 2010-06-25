package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.persistence.PlanPositionItemPK;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="PlanPositionItemModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanPositionItem</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanPositionItem
 * @see com.ext.portlet.plans.model.impl.PlanPositionItemImpl
 * @see com.ext.portlet.plans.model.impl.PlanPositionItemModelImpl
 *
 */
public interface PlanPositionItemModel extends BaseModel<PlanPositionItem> {
    public PlanPositionItemPK getPrimaryKey();

    public void setPrimaryKey(PlanPositionItemPK pk);

    public Long getPlanPositionsId();

    public void setPlanPositionsId(Long planPositionsId);

    public Long getPositionId();

    public void setPositionId(Long positionId);

    public PlanPositionItem toEscapedModel();
}
