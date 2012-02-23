package org.climatecollaboratorium.plans.wrappers;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.plans.Helper;

import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.model.PlanSection;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class PlanSectionWrapper {
    private PlanSection section;
    private boolean editing;
    private PlanItemWrapper piw;

    public PlanSectionWrapper(PlanSection section, PlanItemWrapper planItemWrapper) {
        this.section = section;
        piw = planItemWrapper;
        
        
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
                    .setSectionContent(section.getDefinition(), section.getContent(), Helper.getLiferayUser().getUserId());
            piw.refresh();
        }
        toggleEditing(e);
    }
    

}
