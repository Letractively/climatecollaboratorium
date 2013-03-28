package org.climatecollaboratorium.plans.admin.wrappers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.climatecollaboratorium.plans.admin.Helper;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.ext.portlet.ontology.model.FocusArea;
import com.ext.portlet.ontology.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanTemplate;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTemplateLocalServiceUtil;
import com.icesoft.faces.component.inputfile.InputFile;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.AdvancedPermissionChecker;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

public class ContestWrapper {
    private Contest contest;
    private File newContestLogo;
	private File newSponsorLogo;
	private boolean isNew = false;
    
    public ContestWrapper(Contest contest) {
        this.contest = contest;
        if (contest.getContestPK() == null) {
        	isNew = true;
        }
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
    
    public Boolean getFeatured() {
        return contest.getFeatured();
    }
    
    public void setFeatured(Boolean featured) {
        contest.setFeatured(featured);
    }
    
    public Integer getFlag() {
        return contest.getFlag();
    }
    
    public void setFlag(Integer flag) {
        contest.setFlag(flag);
    }
    
    public String getFlagText() {
        return contest.getFlagText();
    }
    
    public void setFlagText(String flagText) {
        contest.setFlagText(flagText);
    }

    public String getFlagTooltip() {
        return contest.getFlagTooltip();
    }
    
    public void setFlagTooltip(String flagTooltip) {
        contest.setFlagTooltip(flagTooltip);
    }
    
    public Boolean getPlansOpenByDefault() {
    	return contest.getPlansOpenByDefault();
    }
    
    public void setPlansOpenByDefault(Boolean open) {
    	contest.setPlansOpenByDefault(open);
    }
    
    public String getSponsorText() {
    	return contest.getSponsorText();
    }
    
    public void setSponsorText(String sponsorText) {
    	contest.setSponsorText(sponsorText);
    }
    
    
    public void save() throws SystemException, IOException {
        if (newContestLogo != null) {
            contest.setLogo(newContestLogo);
        }
        if (newSponsorLogo != null) {
            contest.setSponsorLogo(newSponsorLogo);
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
        Long id = 0L;
        if (e.getNewValue() == null) {
            id = 0L;
        }
        else {
            PlanTemplate tmpl = PlanTemplateLocalServiceUtil.getPlanTemplate(Long.parseLong(e.getNewValue().toString()));
        }
        
        contest.setPlanTemplateId(id);
        contest.store();
    }
    
    public void planFocusAreaChange(ValueChangeEvent e) throws NumberFormatException, PortalException, SystemException {        
        Long id = 0L;
        if (e.getNewValue() == null) {
            id = 0L;
        }
        else {
            FocusArea fa = FocusAreaLocalServiceUtil.getFocusArea(Long.parseLong(e.getNewValue().toString()));
        }
        
        contest.setFocusAreaId(id);
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
    
    public void uploadSponsorLogo(ActionEvent e) throws IOException {
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
        newSponsorLogo = inputFile.getFileInfo().getFile();
        Image i = ImageLocalServiceUtil.getImage(newSponsorLogo);
        
    }
    
    
    public String getLogo() throws PortalException, SystemException {
        return Helper.getThemeDisplay().getPathImage() + contest.getLogoPath();
    }
    
    public String getSponsorLogo() throws PortalException, SystemException {
        
        return Helper.getThemeDisplay().getPathImage() + contest.getSponsorLogoPath();
    }
    
    public void save(ActionEvent e) throws PortalException, SystemException, InstantiationException, IllegalAccessException {

    }
    
    
    
    private final static Log _log = LogFactoryUtil.getLog(ContestWrapper.class);
}
