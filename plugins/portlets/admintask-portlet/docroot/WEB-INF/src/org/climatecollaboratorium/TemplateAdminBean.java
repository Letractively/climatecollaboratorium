package org.climatecollaboratorium;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanSection;
import com.ext.portlet.plans.model.PlanSectionDefinition;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanSectionDefinitionLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class TemplateAdminBean {
    
    public Contest selectedContest;
    public Long selectedContestId;
    
    public Contest getSelectedContest() {
        return selectedContest;
    }

    public void setSelectedContest(Contest selectedContest) {
        this.selectedContest = selectedContest;
    }

    public Long getSelectedContestId() {
        return selectedContestId;
    }

    public void setSelectedContestId(Long selectedContestId) {
        this.selectedContestId = selectedContestId;
    }

    public PlanSectionDefinition getSection() {
        return section;
    }

    public void setSection(PlanSectionDefinition section) {
        this.section = section;
    }

    public Long getSelectedSectionId() {
        return selectedSectionId;
    }

    public void setSelectedSectionId(Long selectedSectionId) {
        this.selectedSectionId = selectedSectionId;
    }



    public PlanSectionDefinition section;
    public Long selectedSectionId;
    
    public List<SelectItem> getAvailableContests() throws SystemException {
        List<SelectItem> ret = new ArrayList<SelectItem>();
        for (Contest c: ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
            ret.add(new SelectItem(c.getContestPK(), c.getContestShortName()));
            
        }
        return ret;
    }
    
    public void changeContest(ValueChangeEvent e) throws NumberFormatException, PortalException, SystemException {
        selectedContestId = null;
        selectedSectionId = null;
        section = null;
        if (e.getNewValue() != null) {
            selectedContestId = Long.parseLong(e.getNewValue().toString());
            selectedContest = ContestLocalServiceUtil.getContest(selectedContestId);
        }
    }
    
    
    public List<SelectItem> getAvailableSections() throws SystemException, PortalException {
        if (selectedContest == null || selectedContest.getPlanTemplate() == null) return null;
        
        List<SelectItem> ret = new ArrayList<SelectItem>();
        for (PlanSectionDefinition def: selectedContest.getPlanTemplate().getSections()) {
            ret.add(new SelectItem(def.getId(), def.getAdminTitle() + " - " + def.getTitle()));
        }
        return ret;
    }
    
    public void changeSection(ValueChangeEvent e) throws NumberFormatException, PortalException, SystemException {
        selectedSectionId = null;
        section = null;
        if (e.getNewValue() != null) {
            selectedSectionId = Long.parseLong(e.getNewValue().toString());
            section = PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(selectedSectionId);
        }
    }
    
    
    
    public String populateSectionWithDescription() throws PortalException, SystemException {
        if (section == null) {
            Helper.sendError("Select contest and section");
            return null;
        }
        
        for (PlanItem p: PlanItemLocalServiceUtil.getPlansByContest(selectedContest.getContestPK())) {
            
            boolean migrate = true;
            for (PlanSection s: p.getPlanSections()) {
                if (s.getPlanSectionDefinitionId().equals(section.getId()) && s.getContent() != null && s.getContent().trim().length() > 0) {
                    Helper.sendError("Plan " + p.getPlanId() + " has content in requested section - ignoring plan");
                    migrate = false;
                }
            }
            if (!migrate) continue;
            
            
            p.setSectionContent(section, p.getDescription(), new ArrayList<Long>(), p.getAuthorId());
            Helper.sendMessage("Section populated for plan " + p.getPlanId());
        }
        
        
        return null;
    }
    
    
    

}
