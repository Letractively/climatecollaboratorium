package org.climatecollaboratorium.plans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.navigation.NavigationEvent;

import com.ext.portlet.contests.NoSuchContestPhaseException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class NavigationBean {
    private PlanPageType pageType = PlanPageType.getDefaultPageType();
    
    private PlanBean planBean;
    private PlansIndexBean plansIndexBean;
    private ContestsBean contestsBean;
    private PlansPermissionsBean plansPermissionsBean;
    private IssuesBean issuesBean;
    private CreatePlanBean createPlanBean;
    
    private EventBus eventBus;
    
    private List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();
    
    private static Log _log = LogFactoryUtil.getLog(NavigationBean.class);
    
    public NavigationBean() throws SystemException, PortalException {
        contestsBean = new ContestsBean();
        plansIndexBean = new PlansIndexBean(contestsBean.getCurrentPhase());
        planBean = new PlanBean();
        plansPermissionsBean = new PlansPermissionsBean();
        issuesBean = new IssuesBean();
        
        planBean.setPermissions(plansPermissionsBean);
        planBean.setPlansIndexBean(plansIndexBean);
        
    }
    
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        planBean.setEventBus(eventBus);
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
                // init all beans and update plan type
                pageType = PlanPageType.getPageTypeForNavEvent(event);
                try {
                    contestsBean.init(event);
                    
                    plansIndexBean.init(contestsBean.getCurrentPhase(), event);
                    planBean.init(event);
                    
                    issuesBean.init(event);
                } catch (NoSuchContestPhaseException e) {
                    _log.error("Can't init plan related beans", e);
                } catch (PortalException e) {
                    _log.error("Can't init plan related beans", e);
                } catch (SystemException e) {
                    _log.error("Can't init plan related beans", e);
                }
                
            }

        }));
    }
    

    public PlansIndexBean getPlansIndexBean() {
        return plansIndexBean;
    }

    public PlanBean getPlanBean() {
        return planBean;
    }

    public CreatePlanBean getCreatePlanBean() {
        if (createPlanBean == null) {
            createPlanBean = new CreatePlanBean(plansIndexBean);
        } else {
            createPlanBean.setPlansIndex(plansIndexBean);
        }
        return createPlanBean;
    }

    public PlanPageType getPageType() {
        return pageType;
    }

    public ContestsBean getContestsBean() {
        return contestsBean;
    }

    public PlansPermissionsBean getPlansPermissionsBean() {
        return plansPermissionsBean;
    }

    public IssuesBean getIssuesBean() {
        return issuesBean;
    }

}
