package org.climatecollaboratorium.plans;

import java.util.Date;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.ext.portlet.plans.NoSuchPlanAttributeFilterException;
import com.ext.portlet.plans.TypedValueConverter;
import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.model.PlanAttributeFilter;
import com.ext.portlet.plans.model.PlansUserSettings;
import com.ext.portlet.plans.service.PlanAttributeFilterLocalServiceUtil;
import com.ext.portlet.plans.service.PlansUserSettingsLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class FilterPlansBean {
    private String name;
    private String creator;
    private String description;
    private Date dateFrom;
    private Date dateTo;
    private Double votesFrom;
    private Double votesTo;
    private Double mitigationCostFrom;
    private Double mitigationCostTo;
    private Double damageCostFrom; 
    private Double damageCostTo;
    private Double co2From;
    private Double co2To;
    private PlansIndexBean plansIndexBean;
    private PlansUserSettings plansUserSettings;
    private boolean enabled;
    
    public FilterPlansBean(PlansIndexBean plansIndexBean) throws PortalException, SystemException {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        this.plansIndexBean = plansIndexBean;
        plansUserSettings = PlansUserSettingsLocalServiceUtil.getPlanUserSettings(ectx.getSessionMap(), ectx.getRequestMap(), plansIndexBean.getPlanType());
        if (plansUserSettings.getAttributeFilter("NAME") != null) {
            name = plansUserSettings.getAttributeFilter("NAME").getStringVal();
        }
        
        name = (String) getFilterValue(Attribute.NAME);
        creator = (String) getFilterValue(Attribute.CREATOR);
        description = (String) getFilterValue(Attribute.DESCRIPTION);
        Object[] dates = TypedValueConverter.getValues(Date.class, (String) getFilterValue(Attribute.CREATE_DATE, false, false, false));
        dateFrom = (Date) (dates.length > 0 ? dates[0] : null);
        dateTo = (Date) (dates.length > 1 ? dates[1] : null); 
        votesFrom = (Double) getFilterValue(Attribute.VOTES, false, false, true);
        votesTo = (Double) getFilterValue(Attribute.VOTES, false, false, false);
        mitigationCostFrom = (Double) getFilterValue(Attribute.MIN_MITIGATION_COST);
        mitigationCostTo = (Double) getFilterValue(Attribute.MAX_MITIGATION_COST);
        damageCostFrom = (Double) getFilterValue(Attribute.MIN_DAMAGE_COST);
        damageCostTo = (Double) getFilterValue(Attribute.MAX_DAMAGE_COST);
        co2From = (Double) getFilterValue(Attribute.CO2, false, false, true);
        co2To = (Double) getFilterValue(Attribute.CO2, false, false, false);
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCreator() {
        return creator;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getDateFrom() {
        return dateFrom;
    }
    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }
    public Date getDateTo() {
        return dateTo;
    }
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
    public Double getVotesFrom() {
        return votesFrom;
    }
    
    public void setVotesFrom(Object votesFrom) {
        System.out.println("votes from: " + votesFrom);
    }
    public void setVotesFrom(Double votesFrom) {
        this.votesFrom = votesFrom;
    }
    public Double getVotesTo() {
        return votesTo;
    }
    public void setVotesTo(Double votesTo) {
        this.votesTo = votesTo;
    }
    public Double getMitigationCostFrom() {
        return mitigationCostFrom;
    }
    public void setMitigationCostFrom(Double mitigationCostFrom) {
        this.mitigationCostFrom = mitigationCostFrom;
    }
    public Double getMitigationCostTo() {
        return mitigationCostTo;
    }
    public void setMitigationCostTo(Double mitigationCostTo) {
        this.mitigationCostTo = mitigationCostTo;
    }
    public Double getDamageCostFrom() {
        return damageCostFrom;
    }
    public void setDamageCostFrom(Double damageCostFrom) {
        this.damageCostFrom = damageCostFrom;
    }
    public Double getDamageCostTo() {
        return damageCostTo;
    }
    public void setDamageCostTo(Double damageCostTo) {
        this.damageCostTo = damageCostTo;
    }
    public Double getCo2From() {
        return co2From;
    }
    public void setCo2From(Double co2From) {
        this.co2From = co2From;
    }
    public Double getCo2To() {
        return co2To;
    }
    public void setCo2To(Double co2To) {
        this.co2To = co2To;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void update(ActionEvent e) throws PortalException, SystemException {
        
        PlanAttributeFilter nameFilter = getFilter(Attribute.NAME);
        nameFilter.setStringVal(name);
        
        // creator
        PlanAttributeFilter creatorFilter = getFilter(Attribute.CREATOR);
        creatorFilter.setStringVal(creator);
        
        // description
        PlanAttributeFilter descriptionFilter = getFilter(Attribute.DESCRIPTION);
        descriptionFilter.setStringVal(description);
        
        // date
        PlanAttributeFilter dateFilter = null; 
        if (plansIndexBean.getPlanType().getPublished()) {
            dateFilter = getFilter(Attribute.PUBLISH_DATE);
        } 
        else {
            dateFilter = getFilter(Attribute.CREATE_DATE);
        }
        dateFilter.setStringVal(TypedValueConverter.getString(dateFrom) + "|" + TypedValueConverter.getString(dateTo));
        
        
        // votes
        PlanAttributeFilter votesFilter = getFilter(Attribute.VOTES);
        votesFilter.setMin(votesFrom);
        votesFilter.setMax(votesTo);

        // mitigation cost
        PlanAttributeFilter mitigationMinFilter = getFilter(Attribute.MIN_MITIGATION_COST);
        mitigationMinFilter.setStringVal(String.valueOf(mitigationCostFrom));

        PlanAttributeFilter mitigationMaxFilter = getFilter(Attribute.MAX_MITIGATION_COST);
        mitigationMaxFilter.setStringVal(String.valueOf(mitigationCostTo));
        
        
        // damage cost
        PlanAttributeFilter damageMinFilter = getFilter(Attribute.MIN_DAMAGE_COST);
        damageMinFilter.setStringVal(String.valueOf(damageCostFrom));

        PlanAttributeFilter damageMaxFilter = getFilter(Attribute.MAX_DAMAGE_COST);
        damageMaxFilter.setStringVal(String.valueOf(damageCostTo));
        // co2
        PlanAttributeFilter co2filter = getFilter(Attribute.CO2);
        co2filter.setMin(co2From);
        co2filter.setMax(co2To);
        
        plansUserSettings.setFilterEnabled(enabled);

        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        PlansUserSettingsLocalServiceUtil.saveUserSettings(ectx.getSessionMap(), ectx.getRequestMap(), plansUserSettings);
        plansIndexBean.filtersUpdate();
    }
    
    private PlanAttributeFilter getFilter(Attribute attribute) throws NoSuchPlanAttributeFilterException, SystemException {
        PlanAttributeFilter filter = plansUserSettings.getAttributeFilter(attribute.name());
        if (filter == null) {
            filter = PlanAttributeFilterLocalServiceUtil.createPlanAttributeFilter(null);
            filter.setAttributeName(attribute.name());
            filter.setPlanUserSettingsId(plansUserSettings.getPlanUserSettingsId());
            
            plansUserSettings.addPlanAttributeFilter(filter);
        }
        return filter;
    }
    
    private Object getFilterValue(Attribute attr) throws NoSuchPlanAttributeFilterException, SystemException {
        return getFilterValue(attr, true, false, false);
    }
    private Object getFilterValue(Attribute attr, boolean typed, boolean single, boolean min) throws NoSuchPlanAttributeFilterException, SystemException {
        PlanAttributeFilter filter = plansUserSettings.getAttributeFilter(attr.name());
        if (filter != null) {
            if (single) {
                if (typed) {
                    return filter.getTypedValue();
                }
                else {
                    return filter.getStringVal();
                }
            }
            else {
                if(min) {
                    return filter.getMin();
                }
                return filter.getMax();
            }
        }
        return null;
    }
    

}
