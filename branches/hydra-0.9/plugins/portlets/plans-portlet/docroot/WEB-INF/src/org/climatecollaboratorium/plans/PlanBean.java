package org.climatecollaboratorium.plans;

import javax.faces.event.ActionEvent;

import com.ext.portlet.plans.NoSuchPlanPositionsException;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class PlanBean {
    private PlanItemWrapper plan;
    private PlanItem planItem;
    private PlanPositionsBean planPositionsBean;
    private SimulationBean simulationBean;

    private boolean editingDescription;
    private boolean editingName;
    private CreatePlanBean createPlanBean;
    private PlanMembershipBean membershipBean;
    private PlansPermissionsBean permissions;
    
    
    public PlanBean(PlanItem planItem) throws SystemException, PortalException {
        this.planItem = planItem;
        this.permissions = new PlansPermissionsBean(planItem);
        planPositionsBean = new PlanPositionsBean(planItem, this);
        plan = new PlanItemWrapper(planItem, this, permissions);
        simulationBean = new SimulationBean(planItem, this);
    }
    
    public void refresh() throws SystemException, PortalException {
        planItem = PlanItemLocalServiceUtil.getPlan(planItem.getPlanId());
        planPositionsBean = new PlanPositionsBean(planItem, this);
        plan = new PlanItemWrapper(planItem, this, permissions);
        simulationBean = new SimulationBean(planItem, this);
        membershipBean = new PlanMembershipBean(planItem, this, permissions);
    }

    public PlanItemWrapper getPlan() {
        return plan;
    }

    public void setEditingDescription(boolean editingDescription) throws SystemException, PortalException {
        this.editingDescription = editingDescription;
        refresh();
    }

    public boolean isEditingDescription() {
        return editingDescription;
    }

    public void editDescription(ActionEvent e) {
        editingDescription = !editingDescription;
    }

    public void setEditingName(boolean editingName) throws SystemException, PortalException {
        this.editingName = editingName;
        refresh();
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
    
    public int getVotesPercent() throws SystemException {
        int votes = plan.getVotes();
        int votesTotal = PlanVoteLocalServiceUtil.getPlanVotesCount();
        if (votes == 0 || votesTotal == 0) {
            return 0;
        }
        
        return (100 * votes) / votesTotal;
    }
    
    
    public CreatePlanBean getCreatePlanBean() {
        if (createPlanBean == null) {
            createPlanBean = new CreatePlanBean(this);
        }
        return createPlanBean;
    }
    
    public PlanMembershipBean getMembershipBean() throws PortalException, SystemException {
        if (membershipBean == null) {
            membershipBean = new PlanMembershipBean(plan.getWrapped(), this, permissions);
        }
        return membershipBean;
    }
    

}
