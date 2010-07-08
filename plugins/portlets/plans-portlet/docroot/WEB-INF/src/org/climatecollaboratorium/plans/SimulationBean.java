package org.climatecollaboratorium.plans;

import javax.faces.event.ActionEvent;

import com.ext.portlet.plans.model.PlanItem;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class SimulationBean {
    private Long scenario;

    private PlanItem plan;
    private PlanBean planBean;
    private boolean editing;
    
    public SimulationBean(PlanItem plan, PlanBean planBean) {
        this.plan = plan;
        this.planBean = planBean;
    }
    
    public void update(ActionEvent e) throws SystemException, PortalException {
        if (Helper.isUserLoggedIn()) {
            plan.setScenarioId(scenario, Helper.getLiferayUser().getUserId());
            planBean.refresh();
        }
        editing = false;
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
    
    public void edit(ActionEvent e) {
        editing = true;
    }

}
