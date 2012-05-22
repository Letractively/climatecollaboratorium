package org.climatecollaboratorium.plans.wrappers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.climatecollaboratorium.plans.Helper;

import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanSection;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class PlanSectionWrapper {
    private PlanSection section;
    private boolean editing;
    private PlanItemWrapper piw;
    private List<Long> referencedPlans;

    public PlanSectionWrapper(PlanSection section, PlanItemWrapper planItemWrapper) throws NoSuchPlanItemException, SystemException {
        this.section = section;
        piw = planItemWrapper;
        referencedPlans = new ArrayList<Long>();
        for (PlanItem plan: section.getReferencedPlans()) {
            referencedPlans.add(plan.getId());
        }
        
        
    }

    public PlanSection getSection() {
        return section;
    }

    public void setSection(PlanSection section) {
        this.section = section;
    }
    
    public boolean isEditing() {
        return editing;
    }
    
    public void toggleEditing(ActionEvent e) {
        editing = !editing;
    }
    
    public void save(ActionEvent e) throws NoSuchPlanItemException, PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            System.out.println("new content: " + section.getContent());
            PlanItemLocalServiceUtil.getPlan(section.getPlanId())
                    .setSectionContent(section.getDefinition(), section.getContent(), referencedPlans, Helper.getLiferayUser().getUserId());
        }
        toggleEditing(e);
    }
    
    public List<SelectItem> getPlansForReference() throws PortalException, SystemException {
        List<SelectItem> ret = new ArrayList<SelectItem>();
        
        if (section.getDefinition().getFocusArea() != null) {
            for (PlanItem plan: PlanItemLocalServiceUtil.findPlansForFocusArea(section.getDefinition().getFocusArea())) {
                ret.add(new SelectItem(plan.getPlanId(), plan.getName()));
            }
        }

        return ret;
    }
    
    public List<Long> getReferencedPlanIds() throws NoSuchPlanItemException, SystemException {
        return referencedPlans;
    }
    
    public void setReferencedPlanIds(List<String> plans) {
        referencedPlans.clear();
        for (String planId: plans) {
            referencedPlans.add(Long.parseLong(planId));
        }
        
        
    }
    
    
    

}
