package org.climatecollaboratorium.plans;

import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.events.EventBus;

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
    private EventBus eventBus;
    
    public PlansIndexBean getPlansIndex() {
        return plansIndex;
    }


    public void setPlansIndex(PlansIndexBean plansIndex) {
        this.plansIndex = plansIndex;
    }


    private Long planId;
    private CreatePlanBean createPlanBean;
    

    private static Log _log = LogFactoryUtil.getLog(NavigationBean.class);
    
    
    public NavigationBean() throws SystemException, PortalException {
        PORTLET_ID = Helper.getPortletID();
        plansIndex = new PlansIndexBean();
        // get request parameters
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();//.getRequestPathInfo())

        if (ctx.getRequestParameterMap().containsKey("planId")) {
            try {
                planId = Long.parseLong(ctx.getRequestParameterMap().get("planId").toString());
                updateView();
            } catch (NumberFormatException e) {
                // ignore
            }
        }
    }


    private void updateView() {
        try {
            PlanItem plan = null;
            if (planId > 0) {
                plan = PlanItemLocalServiceUtil.getPlan(planId);
            }
            
            if (plan != null) {
                if (planBean != null) {
                    planBean.cleanup();
                }
                planBean = new PlanBean(plan, eventBus);
                return;
            }
        } 
        catch (Throwable e) {
            _log.error("Can't retrieve plan with id: " + planId, e);
        }
        planBean = null;
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
        updateView();
    }


    public PlanBean getPlanBean() {
        return planBean;
    }


    public void setPlanBean(PlanBean planBean) {
        this.planBean = planBean;
    }
    
    public String getPortletId() {
        return PORTLET_ID;
    }
    
    public CreatePlanBean getCreatePlanBean() {
        if (createPlanBean == null) {
            createPlanBean = new CreatePlanBean(plansIndex);
        }
        return createPlanBean;
    }
    
    public void update(ActionEvent e) {
        updateView();
    }


    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }


    public EventBus getEventBus() {
        return eventBus;
    }

}
