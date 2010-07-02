package org.climatecollaboratorium.plans;

import javax.faces.event.ActionEvent;

import com.ext.portlet.plans.model.PlanItem;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class SimulationBean {
    private Long scenario;

    private PlanItem plan;
    
    public SimulationBean(PlanItem plan) {
        this.plan = plan;
    }
    
    public void update(ActionEvent e) throws SystemException, PortalException {
        plan.setScenarioId(scenario, 10L);
    }
    
    public Long getScenario() {
        return scenario;
    }

    public void setScenario(Long scenario) {
        this.scenario = scenario;
    }

}
