package org.climatecollaboratorium.plans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.plans.PlanActivityKeys;
import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanType;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;

public class PlanItemWrapper {
    private PlanItem wrapped;
    private PlanBean planBean;
    private String candidateName;
    private String candidateDescription;
    private List<PlanDescription> planDescriptions;
    private Map<Long, PlanDescription> planDescriptionsById = new HashMap<Long, PlanDescription>();
    private List<SelectItem> planDescriptionItems = new ArrayList<SelectItem>();
    private Long currentDescriptionVersion;

    public PlanItemWrapper(PlanItem plan, PlanBean planBean) throws SystemException, PortalException {
        wrapped = plan;
        this.planBean = planBean;
        planDescriptions = wrapped.getAllDescriptionVersions();

        for (PlanDescription planDescription: planDescriptions) {
            planDescriptionsById.put(planDescription.getId(), planDescription);
            planDescriptionItems.add(new SelectItem(planDescription.getId(), planDescription.getCreated() + " by " + planDescription.getUpdateAuthor().getScreenName()));
        }
        
        currentDescriptionVersion = planDescriptions.get(0).getId();
        
        
    }
    

    public String getDescription() throws SystemException {
        return planDescriptionsById.get(currentDescriptionVersion).getDescription();
    }
    
    public void setDescription(String description) {
        candidateDescription = description;
    }


    public String getName() throws SystemException {
        return planDescriptionsById.get(currentDescriptionVersion).getName();
    }

    public void setName(String name) {
        candidateName = name;
    }
    
    public void saveDescription(ActionEvent e) throws SystemException, PortalException {
        if (Helper.isUserLoggedIn()) {
            if (candidateDescription != null) {
                wrapped.setDescription(candidateDescription, Helper.getLiferayUser().getUserId());
            }
        }
        planBean.setEditingDescription(false);
    }
    
    public void saveName(ActionEvent e) throws SystemException, PortalException {
        if (Helper.isUserLoggedIn()) {
            if (candidateName != null) {
                wrapped.setName(candidateName, Helper.getLiferayUser().getUserId());
            }
        }
        planBean.setEditingName(false);
    }

    
    public int getVotes() throws SystemException {
        return wrapped.getVotes();
    }
    public void vote(ActionEvent e) throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            wrapped.vote(Helper.getLiferayUser().getUserId());
        }
    }
    
    public void unvote(ActionEvent e) throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            wrapped.unvote(Helper.getLiferayUser().getUserId());
        }
    }
    

    public boolean isVotedOn() throws PortalException, SystemException {
        boolean voted = false;
        if (Helper.isUserLoggedIn()) {
            voted = wrapped.hasUserVoted(Helper.getLiferayUser().getUserId());
        }
        return voted;
    }
    
    public void subscribe(ActionEvent e) throws SystemException {
        if (Helper.isUserLoggedIn()) {
            ActivityUtil.addSubscription(PlanActivityKeys.ALL, Helper.getLiferayUser().getUserId(), wrapped.getPlanId());
        }
    }

    public PlanItem getWrapped() {
        return wrapped;
    }
    
    public List<SelectItem> getAllDescriptionVersions() throws PortalException, SystemException {
        return planDescriptionItems;
    }
    
    public Long getDescriptionVersion() {
        return currentDescriptionVersion;
    }
    
    public void setDescriptionVersion(Long descriptionVersion) {
        currentDescriptionVersion = descriptionVersion;
        candidateName = planDescriptionsById.get(descriptionVersion).getName();
        candidateDescription = planDescriptionsById.get(descriptionVersion).getDescription();
        
    }
    
    public void descriptionVersion(ValueChangeEvent event) {
        candidateName = planDescriptionsById.get(currentDescriptionVersion).getName();
        candidateDescription = planDescriptionsById.get(currentDescriptionVersion).getDescription();
        System.out.println("currentDesc ver: " + currentDescriptionVersion + "\t " + candidateDescription);
        
    }
}
