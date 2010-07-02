package org.climatecollaboratorium.plans;

import javax.faces.event.ActionEvent;

import com.ext.portlet.plans.NoSuchPlanPositionsException;
import com.ext.portlet.plans.model.PlanItem;
import com.liferay.portal.SystemException;

public class PlanBean {
    private PlanItemWrapper plan;
    private PlanItem planItem;
    private PlanPositionsBean planPositionsBean;
    private SimulationBean simulationBean;

    private boolean editingDescription;
    private boolean editingName;
    
    public PlanBean(PlanItem planItem) throws SystemException, NoSuchPlanPositionsException {
        this.planItem = planItem;
        planPositionsBean = new PlanPositionsBean(planItem);
        plan = new PlanItemWrapper(planItem, this);
        simulationBean = new SimulationBean(planItem);
    }

    public PlanItemWrapper getPlan() {
        return plan;
    }

    public void setEditingDescription(boolean editingDescription) {
        this.editingDescription = editingDescription;
    }

    public boolean isEditingDescription() {
        return editingDescription;
    }

    public void editDescription(ActionEvent e) {
        editingDescription = !editingDescription;
    }

    public void setEditingName(boolean editingName) {
        this.editingName = editingName;
    }

    public boolean isEditingName() {
        return editingName;
    }
    
    public void editName(ActionEvent e) {
        editingName = !editingName;
    }
    
    public PlanPositionsBean getPlanPositionsBean() {
        return planPositionsBean;
    }

    public SimulationBean getSimulationBean() {
        return simulationBean;
    }

}
