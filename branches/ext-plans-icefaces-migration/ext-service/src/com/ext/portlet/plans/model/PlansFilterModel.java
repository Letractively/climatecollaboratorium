package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.persistence.PlansFilterPK;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="PlansFilterModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlansFilter</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlansFilter
 * @see com.ext.portlet.plans.model.impl.PlansFilterImpl
 * @see com.ext.portlet.plans.model.impl.PlansFilterModelImpl
 *
 */
public interface PlansFilterModel extends BaseModel<PlansFilter> {
    public PlansFilterPK getPrimaryKey();

    public void setPrimaryKey(PlansFilterPK pk);

    public Long getUserId();

    public void setUserId(Long userId);

    public Long getPlanTypeId();

    public void setPlanTypeId(Long planTypeId);

    public String getName();

    public void setName(String name);

    public String getCreator();

    public void setCreator(String creator);

    public String getDescription();

    public void setDescription(String description);

    public Double getCO2From();

    public void setCO2From(Double CO2From);

    public Double getCO2To();

    public void setCO2To(Double CO2To);

    public Double getVotesFrom();

    public void setVotesFrom(Double votesFrom);

    public Double getVotesTo();

    public void setVotesTo(Double votesTo);

    public Double getDamageFrom();

    public void setDamageFrom(Double damageFrom);

    public Double getDamageTo();

    public void setDamageTo(Double damageTo);

    public Double getMitigationFrom();

    public void setMitigationFrom(Double mitigationFrom);

    public Double getMitigationTo();

    public void setMitigationTo(Double mitigationTo);

    public Date getDateFrom();

    public void setDateFrom(Date dateFrom);

    public Date getDateTo();

    public void setDateTo(Date dateTo);

    public Boolean getFilterPositionsAll();

    public void setFilterPositionsAll(Boolean filterPositionsAll);

    public Boolean getEnabled();

    public void setEnabled(Boolean enabled);

    public PlansFilter toEscapedModel();
}
