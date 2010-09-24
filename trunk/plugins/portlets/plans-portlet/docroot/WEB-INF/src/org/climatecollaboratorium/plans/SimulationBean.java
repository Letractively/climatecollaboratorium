package org.climatecollaboratorium.plans;

import com.ext.portlet.plans.model.PlanItem;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.facelets.simulations.ScenarioEditEvent;
import org.climatecollaboratorium.facelets.simulations.ScenarioSavedEvent;
import org.climatecollaboratorium.plans.activity.PlanActivityKeys;

public class SimulationBean {
    private Long scenario;

    private PlanItem plan;
    private PlanBean planBean;
    private boolean editing;
    private ThemeDisplay td = Helper.getThemeDisplay();
    private EventBus eventBus;
    private List<HandlerRegistration> eventHandlers = new ArrayList<HandlerRegistration>();
    private static Log _log = LogFactoryUtil.getLog(SimulationBean.class);

    public SimulationBean() {
        
    }
    
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        
        bind();
    }
    
    private void bind() {
        
        for (HandlerRegistration reg: eventHandlers) {
            reg.unregister();
        }

        eventHandlers.add(eventBus.registerHandler(ScenarioSavedEvent.class, new EventHandler<ScenarioSavedEvent>() {
            @Override
            public void onEvent(ScenarioSavedEvent arg0) {
                if (Helper.isUserLoggedIn()) {
                    try {
                        plan.setScenarioId(arg0.getScenario().getId(), Helper.getLiferayUser().getUserId());
                        //System.out.println("Scenario id: " + arg0.getScenario().getId());

                        SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                                PlanItem.class.getName(), plan.getPlanId(), PlanActivityKeys.EDIT_SCENARIO.id(),null, 0);
                        planBean.refresh();
                    } catch (PortalException e) {
                        _log.error("Can't save scenario in a plan", e);
                    } catch (SystemException e) {
                        _log.error("Can't save scenario in a plan", e);
                    }

                }
            }

        }));

        eventHandlers.add(eventBus.registerHandler(ScenarioEditEvent.class, new EventHandler<ScenarioEditEvent>() {

            @Override
            public void onEvent(ScenarioEditEvent event) {
                    setEditing(event.isEditing());
            }

        }));
    }

    public void cancel(ActionEvent e) throws SystemException, PortalException {
        editing = false;
    }

    public Long getScenario() {
        return scenario;
    }

    public void setScenario(Long scenario) {
        this.scenario = scenario;
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    public void edit(ActionEvent e) {
        editing = true;
    }

    public void cleanup() {
        for (HandlerRegistration handlerRegistration: eventHandlers) {
            handlerRegistration.unregister();
        }
    }

    public void setPlan(PlanItem planItem, PlanBean planBean2) {
        plan = planItem;
        this.planBean = planBean2;
    }

}