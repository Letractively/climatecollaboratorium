package org.climatecollaboratorium.plans;

import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.navigation.NavigationEvent;

public class NavigationBean {
    private final static String INDEX_PAGE = "INDEX";
    private final static String DETAILS_PAGE = "DETAILS";
    private PlanBean planBean;
    private PlansIndexBean plansIndex;
    private static String PORTLET_ID;
    private EventBus eventBus;
    private List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();
    private Map<String, String> navigationParameters = new HashMap<String, String>();

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
            if (planId != null && planId > 0) {
                plan = PlanItemLocalServiceUtil.getPlan(planId);
            }

            if (plan != null) {
                if (planBean != null) {
                    planBean.cleanup();
                }
                planBean = new PlanBean(plan, eventBus, navigationParameters);
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
        bindEvents();
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    private void bindEvents() {
        // unregister old handlers
        for (HandlerRegistration registration: handlerRegistrations) {
            registration.unregister();
        }
        handlerRegistrations.clear();

        handlerRegistrations.add(eventBus.registerHandler(NavigationEvent.class, new EventHandler<NavigationEvent>() {

            @Override
            public void onEvent(NavigationEvent event) {
                // check if event
                if (event.getSource().equals("plans")) {
                    navigationParameters = event.getParameters();
                    String planIdStr = event.getParameters().get("planId");
                    try {
                        planId = planIdStr == null ? null : Long.parseLong(planIdStr);
                    }
                    catch (NumberFormatException e) {
                        _log.error("can't parse planIdStr: " + planIdStr, e);
                    }
                    updateView();
                }

            }

        }));
    }

}