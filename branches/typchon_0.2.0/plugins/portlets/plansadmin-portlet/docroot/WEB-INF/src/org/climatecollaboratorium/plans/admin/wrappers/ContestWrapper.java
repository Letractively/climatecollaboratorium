package org.climatecollaboratorium.plans.admin.wrappers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.plans.model.PlanTemplate;
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
    

    public void planTemplateChange(ValueChangeEvent e) throws NumberFormatException, PortalException, SystemException {
        PlanTemplate tmpl = PlanTemplateLocalServiceUtil.getPlanTemplate(Long.parseLong(e.getNewValue().toString()));
        
        contest.setPlanTemplateId(tmpl.getId());
        contest.store();
    }
    

}
