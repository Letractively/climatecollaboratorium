package org.climatecollaboratorium.plans.admin.wrappers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.ontology.model.FocusArea;
import com.ext.portlet.ontology.model.OntologyTermEntity;
import com.ext.portlet.ontology.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.ontology.service.OntologyTermEntityLocalServiceUtil;
import com.ext.portlet.ontology.service.OntologyTermLocalServiceUtil;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanTemplate;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTemplateLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class ContestWrapper {
    private Contest contest;
    
    public ContestWrapper(Contest contest) {
        this.contest = contest;
    }
    
    public String getContestShortName() {
        return contest.getContestShortName();
    }
    
    public String getContestName() {
        return contest.getContestName();
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }
    
    
    public void save() throws SystemException {
        contest.store();
    }
    
    public List<SelectItem> getAvailablePlanTemplates() throws SystemException {
        List<SelectItem> ret = new ArrayList<SelectItem>();
        
        for (PlanTemplate tmpl: PlanTemplateLocalServiceUtil.getPlanTemplates(0, Integer.MAX_VALUE)) {
            ret.add(new SelectItem(tmpl.getId(), tmpl.getName()));
        }
        return ret;
    }
    
    public List<SelectItem> getAvailableFocusAreas() throws SystemException {
        List<SelectItem> ret = new ArrayList<SelectItem>();
        
        for (FocusArea fa: FocusAreaLocalServiceUtil.getFocusAreas(0, Integer.MAX_VALUE)) {
            ret.add(new SelectItem(fa.getId(), fa.getName()));
        }
        return ret;
    }
    

    public void planTemplateChange(ValueChangeEvent e) throws NumberFormatException, PortalException, SystemException {
        PlanTemplate tmpl = PlanTemplateLocalServiceUtil.getPlanTemplate(Long.parseLong(e.getNewValue().toString()));
        
        contest.setPlanTemplateId(tmpl.getId());
        contest.store();
    }
    
    public void planFocusAreaChange(ValueChangeEvent e) throws NumberFormatException, PortalException, SystemException {
        FocusArea fa = FocusAreaLocalServiceUtil.getFocusArea(Long.parseLong(e.getNewValue().toString()));
        
        contest.setFocusAreaId(fa.getId());
        contest.store();
    }
    
    public void populateFAtoPlans(ActionEvent e) throws PortalException, SystemException {
        FocusArea fa = contest.getFocusArea();
        
        for (ContestPhase phase: contest.getPhases()) {
            for (PlanItem plan: PlanItemLocalServiceUtil.getPlansInContestPhase(phase)) {
                fa.tagClass(PlanItem.class, plan.getPlanId());
            }
        }
    }
    
    

}
