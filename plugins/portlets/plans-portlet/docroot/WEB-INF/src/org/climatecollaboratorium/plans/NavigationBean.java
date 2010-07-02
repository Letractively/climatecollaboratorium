package org.climatecollaboratorium.plans;

import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class NavigationBean {
    private final static String INDEX_PAGE = "INDEX";
    private final static String DETAILS_PAGE = "DETAILS";
    private PlanBean planBean; 
    private PlansIndexBean plansIndex;
    private static String PORTLET_ID;
    
    public PlansIndexBean getPlansIndex() {
        return plansIndex;
    }


    public void setPlansIndex(PlansIndexBean plansIndex) {
        this.plansIndex = plansIndex;
    }


    private Long planId;
    

    private static Log _log = LogFactoryUtil.getLog(NavigationBean.class);
    
    
    public NavigationBean() throws SystemException, PortalException {
        PORTLET_ID = Helper.getPortletID();
        plansIndex = new PlansIndexBean();
        // get request parameters
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();//.getRequestPathInfo())

        if (ctx.getRequestParameterMap().containsKey("planId")) {
            try {
                planId = Long.parseLong(ctx.getRequestParameterMap().get("planId").toString());
                try {
                    // FIXME   
                    PlanItem plan = null;
                    List<PlanItem> plans = PlanItemLocalServiceUtil.getPlans();
                    for (PlanItem item: PlanItemLocalServiceUtil.getPlans()) {
                        if (item.getPlanId().equals(planId)) {
                            plan = item;
                        }
                    }
                    planBean = new PlanBean(plan);
                } 
                catch (Throwable e) {
                    _log.error("Can't retrieve plan with id: " + planId, e);
                }
                
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        updateView();
    }


    private void updateView() {
        if (planId != null && planBean != null) {
            
        }
        
    }
    
    public String getPageType() {
        if (planBean != null) {
            return DETAILS_PAGE;
        }
        return INDEX_PAGE;
    }


    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }


    public PlanBean getPlanBean() {
        return planBean;
    }


    public void setPlanBean(PlanBean planBean) {
        this.planBean = planBean;
        updateView();
    }
    
    public String getPortletId() {
        return PORTLET_ID;
    }

}
