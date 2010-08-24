package org.climatecollaboratorium.plans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.navigation.NavigationEvent;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class NavigationBean {
    private final static String CONTEST_INDEX_PAGE = "CONTEST_INDEX";
    private final static String PHASE_INDEX_PAGE = "PHASE_INDEX";
    private final static String DETAILS_PAGE = "DETAILS";
    private PlanBean planBean;
    private PlansIndexBean plansIndex;
    private PlanTypeIndexBean plansTypeIndex;
    private static String PORTLET_ID;
    private EventBus eventBus;
    private List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();
    private Map<String, String> navigationParameters = new HashMap<String, String>();
    private PlansPermissionsBean permissions;

    public PlansIndexBean getPlansIndex() {
        return plansIndex;
    }

    public void setPlansIndex(PlansIndexBean plansIndex) {
        this.plansIndex = plansIndex;
    }
    
    public PlanTypeIndexBean getPlansTypeIndex() {
           return plansTypeIndex;
       }

       public void setPlansTypeIndex(PlanTypeIndexBean planTypeIndex) {
           this.plansTypeIndex = planTypeIndex;
       }
    



    private CreatePlanBean createPlanBean;

    private static Log _log = LogFactoryUtil.getLog(NavigationBean.class);

    public NavigationBean() throws SystemException, PortalException {
        PORTLET_ID = Helper.getPortletID();
        plansTypeIndex = new PlanTypeIndexBean();
        plansIndex = new PlansIndexBean(plansTypeIndex);
        planBean = new PlanBean();
    }

   public String getPageType() {
       if (planBean.getPlanId() != null && planBean.getPlanId().compareTo(0L) > 0) {
            return DETAILS_PAGE;
        } else if (plansIndex.getContestPhase() !=null) {
            return PHASE_INDEX_PAGE;
        }
        return CONTEST_INDEX_PAGE;
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
        } else {
            createPlanBean.setPlansIndex(plansIndex);
        }
        return createPlanBean;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        plansTypeIndex.setEventBus(eventBus);
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
                    String contestPhaseIdStr = event.getParameters().get("phaseId");
                    try {
                        Long contestPhaseId = contestPhaseIdStr==null?null:Long.parseLong(contestPhaseIdStr);
                        plansIndex.init(contestPhaseId,event.getParameters());
                    } catch (NumberFormatException e) {
                        _log.error("can't parse contestPhaseIdStr: "+contestPhaseIdStr,e);
                    } catch (PortalException e) {
                         _log.error("Can't init contest phase index "+contestPhaseIdStr,e);  //To change body of catch statement use File | Settings | File Templates.
                    } catch (SystemException e) {
                     _log.error("Can't init contest phase index "+contestPhaseIdStr,e);  //To change body of catch statement use File | Settings | File Templates.
                    }

                    String planIdStr = event.getParameters().get("planId");
                    try {
                        Long planId = planIdStr == null ? null : Long.parseLong(planIdStr);
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

    public PlansPermissionsBean getPermissions() {
        return permissions;
    }

    public void setPermissions(PlansPermissionsBean permissions) {
        planBean.setPermissions(permissions);
        this.permissions = permissions;
    }

}