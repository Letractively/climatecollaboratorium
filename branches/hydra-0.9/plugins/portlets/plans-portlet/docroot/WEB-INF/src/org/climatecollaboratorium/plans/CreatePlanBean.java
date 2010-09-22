package org.climatecollaboratorium.plans;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.ext.portlet.plans.NoSuchPlanTypeException;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class CreatePlanBean {
    
    private PlansIndexBean plansIndexBean;
    private String name;
    private PlanBean planBean;
    private Long planId;
    private boolean navigateToPlan;

    public CreatePlanBean(PlansIndexBean plansIndexBean) {
        this.plansIndexBean = plansIndexBean;
        name = "";
    }
    
    public CreatePlanBean(PlanBean planBean) {
        this.planBean = planBean;
        name = "";
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    public void createPlan(ActionEvent e) throws SystemException, PortalException {
        if (Helper.isUserLoggedIn() && name.trim().length() != 0) {
            PlanItem planItem = null;
            if (plansIndexBean != null) {
                planItem = PlanItemLocalServiceUtil.createPlan(name, PlanTypeLocalServiceUtil.getDefaultPlanType().getPlanTypeId(), Helper.getLiferayUser().getUserId());
                plansIndexBean.refresh();
            }
            else if (planBean != null) {
                // we need to create a plan based on a plan that is currently visible
                planItem = PlanItemLocalServiceUtil.createPlan(name, planBean.getPlan().getWrapped(), Helper.getLiferayUser().getUserId());
            }
            planId = planItem.getPlanId();
            navigateToPlan = true;
        }
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public boolean isNavigateToPlan() {
        return navigateToPlan;
    }

    public void setNavigateToPlan(boolean navigateToPlan) {
        this.navigateToPlan = navigateToPlan;
    }
    
    

}