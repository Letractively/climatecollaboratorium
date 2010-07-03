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
    
    private NavigationBean navigationBean;
    private PlansIndexBean plansIndexBean;
    private String name;

    public CreatePlanBean(NavigationBean navigationBean, PlansIndexBean plansIndexBean) {
        this.navigationBean = navigationBean;
        this.plansIndexBean = plansIndexBean;
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
            PlanItem planItem = PlanItemLocalServiceUtil.createPlan(name, PlanTypeLocalServiceUtil.getDefaultPlanType().getPlanTypeId(), Helper.getLiferayUser().getUserId());
            plansIndexBean.refresh();
            navigationBean.setPlanId(planItem.getPlanId());
        }
    }
    
    

}
