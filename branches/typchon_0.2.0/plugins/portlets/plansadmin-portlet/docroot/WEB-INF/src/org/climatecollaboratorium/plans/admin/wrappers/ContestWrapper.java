package org.climatecollaboratorium.plans.admin.wrappers;

import java.io.File;
import java.io.IOException;
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
import com.icesoft.faces.component.inputfile.InputFile;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ImageServletTokenUtil;
import com.liferay.portal.model.Image;
import com.liferay.portal.service.ImageLocalServiceUtil;


import org.climatecollaboratorium.plans.admin.Helper;

public class ContestWrapper {
    private Contest contest;
    private File newContestLogo;
    
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
    
    public boolean getFeatured() {
        return contest.getFeatured();
    }
    
    public void setFeatured(boolean featured) {
        contest.setFeatured(featured);
    }
    
    
    public void save() throws SystemException, IOException {
        if (newContestLogo != null) {
            contest.setLogo(newContestLogo);
        }
        contest.store();
    }
    
    public List<SelectItem> getAvailablePlanTemplates() throws SystemException {
        List<SelectItem> ret = new ArrayList<SelectItem>();
        
        ret.add(new SelectItem(null, "-- none --"));
        for (PlanTemplate tmpl: PlanTemplateLocalServiceUtil.getPlanTemplates(0, Integer.MAX_VALUE)) {
            ret.add(new SelectItem(tmpl.getId(), tmpl.getName()));
        }
        return ret;
    }
    
    public List<SelectItem> getAvailableFocusAreas() throws SystemException {
        List<SelectItem> ret = new ArrayList<SelectItem>();
        ret.add(new SelectItem(null, "-- none --"));
        
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
    
    public void uploadLogo(ActionEvent e) throws IOException {
        InputFile inputFile = (InputFile) e.getSource();
        if (inputFile.getFileInfo().getStatus() == InputFile.INVALID) {
            //fileErrorMessage = "Provided file isn't a valid image.";
            _log.error("There was an error when uploading file", inputFile.getFileInfo().getException());
            return;
        }
        
        if (! inputFile.getFileInfo().getContentType().startsWith("image")) {
            //fileErrorMessage = "Provided file isn't a valid image.";
            _log.error("There was an error when uploading file", inputFile.getFileInfo().getException());
            return;
        }
        newContestLogo = inputFile.getFileInfo().getFile();
        Image i = ImageLocalServiceUtil.getImage(newContestLogo);
        System.out.println(i);
        //fileErrorMessage = null;
        //resiseAndCropImage(inputFile.getFile());
        //newUserProfile = inputFile.getFile();
        
    }
    
    public String getLogo() throws PortalException, SystemException {
        System.out.println(ImageLocalServiceUtil.getDefaultSpacer());
        
        return Helper.getThemeDisplay().getPathImage() + contest.getLogoPath();
    }
    
    
    
    
    private final static Log _log = LogFactoryUtil.getLog(ContestWrapper.class);
}
