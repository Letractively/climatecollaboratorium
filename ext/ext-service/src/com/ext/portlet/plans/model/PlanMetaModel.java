package com.ext.portlet.plans.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="PlanMetaModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanMeta</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanMeta
 * @see com.ext.portlet.plans.model.impl.PlanMetaImpl
 * @see com.ext.portlet.plans.model.impl.PlanMetaModelImpl
 *
 */
public interface PlanMetaModel extends BaseModel<PlanMeta> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getId();

    public void setId(Long id);

    public Long getPlanId();

    public void setPlanId(Long planId);

    public Long getPlanTypeId();

    public void setPlanTypeId(Long planTypeId);

    public Long getPlanCreated();

    public void setPlanCreated(Long planCreated);

    public Long getAuthorId();

    public void setAuthorId(Long authorId);

    public Long getPlanGroupId();

    public void setPlanGroupId(Long planGroupId);

    public Long getMbCategoryId();

    public void setMbCategoryId(Long mbCategoryId);

    public Long getVersion();

    public void setVersion(Long version);

    public Long getPlanVersion();

    public void setPlanVersion(Long planVersion);

    public Date getCreated();

    public void setCreated(Date created);

    public Long getUpdateAuthorId();

    public void setUpdateAuthorId(Long updateAuthorId);

    public PlanMeta toEscapedModel();
}
