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
        plansIndex = new PlansIndexBean(eventBus);
        planBean = new PlanBean();
    }

    public String getPageType() {
        if (planBean.getPlanId() != null && planBean.getPlanId().compareTo(0L) > 0) {
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

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        plansIndex.setEventBus(eventBus);
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
                        planBean.init(planId, eventBus, event.getParameters());
                    }
                    catch (NumberFormatException e) {
                        planBean.clear();
                        _log.error("can't parse planIdStr: " + planIdStr, e);
                    } catch (SystemException e) {
                        planBean.clear();
                        _log.error("can't init planbean : " + planIdStr, e);
                        e.printStackTrace();
                    } catch (PortalException e) {
                        planBean.clear();
                        _log.error("can't init planbean : " + planIdStr, e);
                    }
                } 

            }

        }));
    }

}