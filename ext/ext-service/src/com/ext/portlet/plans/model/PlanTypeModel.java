package com.ext.portlet.plans.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="PlanTypeModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanType</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanType
 * @see com.ext.portlet.plans.model.impl.PlanTypeImpl
 * @see com.ext.portlet.plans.model.impl.PlanTypeModelImpl
 *
 */
public interface PlanTypeModel extends BaseModel<PlanType> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getPlanTypeId();

    public void setPlanTypeId(Long planTypeId);

    public String getName();

    public void setName(String name);

    public String getDescription();

    public void setDescription(String description);

    public Long getModelId();

    public void setModelId(Long modelId);

    public Boolean getPublished();

    public void setPublished(Boolean published);

    public Long getPublishedCounterpartId();

    public void setPublishedCounterpartId(Long publishedCounterpartId);

    public Boolean getIsDefault();

    public void setIsDefault(Boolean isDefault);

    public PlanType toEscapedModel();
}
