package org.climatecollaboratorium.plans.migration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.plans.model.PlanFan;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanVote;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.sun.facelets.FaceletContext;

public class PlanCopyTool {
    private Long sourceContestPhase;
    private Long targetContestPhase;
    private List<SelectItem> availableContestPhases = new ArrayList<SelectItem>();
    private boolean readyForCopy;
    
    public PlanCopyTool() throws PortalException, SystemException {
        availableContestPhases.add(new SelectItem(-1, "-- Select --"));
        for (ContestPhase phase: ContestPhaseLocalServiceUtil.getContestPhases(0, Integer.MAX_VALUE)) {
            availableContestPhases.add(new SelectItem(phase.getContestPhasePK(), 
                    phase.getContest().getContestShortName() + ": " + phase.getContestPhaseName()));
        }
    }

    public List<SelectItem> getAvailableContestPhases() {
        return availableContestPhases;
    }

    public boolean isReadyForCopy() {
        return readyForCopy;
    }

    public Long getSourceContestPhase() {
        return sourceContestPhase;
    }

    public void setSourceContestPhase(Long sourceContestPhase) throws PortalException, SystemException {
        this.sourceContestPhase = sourceContestPhase;
        updateReady();
    }

    public Long getTargetContestPhase() {
        return targetContestPhase;
    }

    public void setTargetContestPhase(Long targetContestPhase) throws PortalException, SystemException {
        this.targetContestPhase = targetContestPhase;
        updateReady();
    }
    
    
    private void updateReady() throws PortalException, SystemException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (sourceContestPhase != null && targetContestPhase != null) {
            if (sourceContestPhase > 0 && targetContestPhase > 0) {
                readyForCopy = true;
                
                // check if contest phases have the same plan types
                ContestPhase sourcePhase = ContestPhaseLocalServiceUtil.getContestPhase(sourceContestPhase);
                ContestPhase targetPhase = ContestPhaseLocalServiceUtil.getContestPhase(targetContestPhase);
                if (! targetPhase.getContest().getPlanTypeId().equals(sourcePhase.getContest().getPlanTypeId())) {
                    ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contest phases are incompatible", ""));
                    readyForCopy = false;
                }
                else {
                    readyForCopy = true;
                }
            }
            else {
                readyForCopy = false;
            }
        }
        else {
            readyForCopy = false;
        }
    }
    
    public void copy(ActionEvent e) throws PortalException, SystemException {
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        // check if contest phases have the same plan types
        ContestPhase sourcePhase = ContestPhaseLocalServiceUtil.getContestPhase(sourceContestPhase);
        ContestPhase targetPhase = ContestPhaseLocalServiceUtil.getContestPhase(targetContestPhase);
        if (! targetPhase.getContest().getPlanTypeId().equals(sourcePhase.getContest().getPlanTypeId())) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contest phases are incompatible", ""));
            return;
        }
        
        Set<Long> plansToBeCopiedIds = new HashSet<Long>();
        List<PlanItem> plansToBeCopied = new ArrayList<PlanItem>();
        for (PlanItem plan: PlanItemLocalServiceUtil.getPlanItems(0, Integer.MAX_VALUE)) {
            if (plan.getContestPhase().getContestPhasePK().equals(sourceContestPhase) && ! plansToBeCopiedIds.contains(plan.getPlanId())) {
                // get latest version of a plan
                plansToBeCopied.add(PlanItemLocalServiceUtil.getPlan(plan.getPlanId()));
                plansToBeCopiedIds.add(plan.getPlanId());
            }
        }
        
        // create new plan in new contest phase
        for (PlanItem plan: plansToBeCopied) {
            PlanItem newPlan = PlanItemLocalServiceUtil.createPlan(plan, targetPhase, plan.getAuthorId());
            newPlan.setName(plan.getName(), plan.getAuthorId());
            
            // copy fans
            for (PlanFan planFan: plan.getFans()) {
                newPlan.addFan(planFan.getUserId());
            }
            
            // copy votes
            for (PlanVote planVote: plan.getPlanVotes()) {
                newPlan.vote(planVote.getUserId());
            }
            
            
            // copy entire discussions, comments migration
            DiscussionCategoryGroup dcg = newPlan.getDiscussionCategoryGroup();
            dcg.copyEverything(plan.getDiscussionCategoryGroup());
            

            // update plan version
            newPlan.setVersion(2L);
            newPlan.store();
            
        }
        
        
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Plans copied", ""));
        
        return;
    }
    

}
