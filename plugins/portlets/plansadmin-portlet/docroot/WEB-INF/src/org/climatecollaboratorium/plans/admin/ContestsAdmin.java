package org.climatecollaboratorium.plans.admin;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.plans.admin.wrappers.ContestWrapper;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.liferay.portal.SystemException;

public class ContestsAdmin {
    private ContestWrapper editedContest;
    
    public List<ContestWrapper> getContests() throws SystemException {
        List<ContestWrapper> ret = new ArrayList<ContestWrapper>();
        
        for (Contest contest: ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
            ret.add(new ContestWrapper(contest));
        }
        
        return ret;
    }
    
    public void editCotnestActionListener(ActionEvent e) {
        editedContest = (ContestWrapper) e.getComponent().getAttributes().get("contest");
        
        
        
    }
    
    public String editContest() {
        return "editContest";
    }

    public void setEditedContest(ContestWrapper editedContest) {
        this.editedContest = editedContest;
    }

    public ContestWrapper getEditedContest() {
        return editedContest;
    }

}
