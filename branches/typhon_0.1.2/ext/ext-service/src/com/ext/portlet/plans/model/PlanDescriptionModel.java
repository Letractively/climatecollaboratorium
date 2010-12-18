package com.ext.portlet.plans.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="PlanDescriptionModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanDescription</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanDescription
 * @see com.ext.portlet.plans.model.impl.PlanDescriptionImpl
 * @see com.ext.portlet.plans.model.impl.PlanDescriptionModelImpl
 *
 */
public interface PlanDescriptionModel extends BaseModel<PlanDescription> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getId();

    public void setId(Long id);

    public Long getPlanId();

    public void setPlanId(Long planId);

    public String getName();

    public void setName(String name);

    public String getDescription();

    public void setDescription(String description);

    public Long getVersion();

    public void setVersion(Long version);

    public Long getPlanVersion();

    public void setPlanVersion(Long planVersion);

    public Date getCreated();

    public void setCreated(Date created);

    public Long getUpdateAuthorId();

    public void setUpdateAuthorId(Long updateAuthorId);

    public PlanDescription toEscapedModel();
}
