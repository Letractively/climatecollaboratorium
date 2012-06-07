package org.climatecollaboratorium.plans.migration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.climatecollaboratorium.plans.Helper;

import com.ext.portlet.Activity.model.ActivitySubscription;
import com.ext.portlet.Activity.service.ActivitySubscriptionLocalServiceUtil;
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
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.sun.facelets.FaceletContext;

public class PlanCopyTool {
    private Long sourceContestPhase;
    private Long targetContestPhase;
    private List<SelectItem> availableContestPhases = new ArrayList<SelectItem>();
    private boolean readyForCopy;
    private boolean addSemiFinalistRibbon;
    private String planAdvancedText = "Plan advanced to next phase";

    private List<PlanCopyItem> planCopyItems;
    
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
                    planCopyItems = new ArrayList<PlanCopyItem>();
                    for (PlanItem plan: sourcePhase.getPlans()) {
                        if (plan.getVersion() > 1 && !plan.getState().equals("DELETED")) {
                            planCopyItems.add(new PlanCopyItem(plan));
                        }
                    }
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
    
    
    public List<PlanCopyItem> getPlanCopyItems() {
        return planCopyItems;
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
        
        //Set<Long> plansToBeCopiedIds = new HashSet<Long>();
        List<PlanItem> plansToBeCopied = new ArrayList<PlanItem>();
        /*
        for (PlanItem plan: PlanItemLocalServiceUtil.getPlanItems(0, Integer.MAX_VALUE)) {
            if (plan.getContestPhase() != null && 
                    plan.getContestPhase().getContestPhasePK().equals(sourceContestPhase) && 
                    ! plansToBeCopiedIds.contains(plan.getPlanId()) && 
                    plan.getVersion() > 1 && 
                    !plan.getState().equals("DELETED")) {
                // get latest version of a plan
                plansToBeCopied.add(PlanItemLocalServiceUtil.getPlan(plan.getPlanId()));
                plansToBeCopiedIds.add(plan.getPlanId());
            }
        }*/
        for (PlanCopyItem item: planCopyItems) {
            if (item.isSelected()) {
                plansToBeCopied.add(item.getPlan());
            }
        }
        
        // create new plan in new contest phase
        System.out.println("Plans to copy: " + plansToBeCopied.size());
        int count = 0;
        for (PlanItem plan: plansToBeCopied) {
            count++;
            System.out.println("Copying plan " + count + " of " + plansToBeCopied.size());
            PlanItem newPlan = PlanItemLocalServiceUtil.createPlan(plan, targetPhase, plan.getAuthorId());
            //newPlan.setName(plan.getName(), plan.getAuthorId());
            
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

            long[] userIds = UserLocalServiceUtil.getGroupUserIds(plan.getPlanGroupId());
            UserLocalServiceUtil.addGroupUsers(newPlan.getPlanGroupId(), userIds);
            
            if (addSemiFinalistRibbon) {
                plan.setRibbon(1);
                plan.setRibbonText(planAdvancedText);
            }
            
            // copy subscriptions for plan
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(ActivitySubscription.class);
            
            ClassName cn = ClassNameLocalServiceUtil.getClassName(PlanItem.class.getName());
            Criterion criterionClassNameId = RestrictionsFactoryUtil.eq("classNameId", cn.getClassNameId());
            Criterion criterionClassPK = RestrictionsFactoryUtil.eq("classPK", plan.getPlanId());
            query.add(RestrictionsFactoryUtil.and(criterionClassNameId, criterionClassPK));
            
            for (Object subscriptionObj : ActivitySubscriptionLocalServiceUtil.dynamicQuery(query)) {
                ActivitySubscription subscription = (ActivitySubscription) subscriptionObj;
                
                ActivitySubscriptionLocalServiceUtil.addSubscription(PlanItem.class, newPlan.getPlanId(), null, "", subscription.getReceiverId());
            }
            
            // copy subscriptions for comments
            query = DynamicQueryFactoryUtil.forClass(ActivitySubscription.class);
            
            cn = ClassNameLocalServiceUtil.getClassName(DiscussionCategoryGroup.class.getName());
            criterionClassNameId = RestrictionsFactoryUtil.eq("classNameId", cn.getClassNameId());
            criterionClassPK = RestrictionsFactoryUtil.eq("classPK", plan.getCategoryGroupId());
            query.add(RestrictionsFactoryUtil.and(criterionClassNameId, criterionClassPK));
            
            for (Object subscriptionObj : ActivitySubscriptionLocalServiceUtil.dynamicQuery(query)) {
                ActivitySubscription subscription = (ActivitySubscription) subscriptionObj;
                
                ActivitySubscriptionLocalServiceUtil.addSubscription(DiscussionCategoryGroup.class, newPlan.getCategoryGroupId(), null, "", subscription.getReceiverId());
            }
            
            
        }
        
        System.out.println("Plans copied");
        
        
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Plans copied", ""));
        
        return;
    }

    public void setAddSemiFinalistRibbon(boolean addSemiFinalistRibbon) {
        this.addSemiFinalistRibbon = addSemiFinalistRibbon;
    }

    public boolean isAddSemiFinalistRibbon() {
        return addSemiFinalistRibbon;
    }

    public void setPlanAdvancedText(String planAdvancedText) {
        this.planAdvancedText = planAdvancedText;
    }

    public String getPlanAdvancedText() {
        return planAdvancedText;
    }
    

}