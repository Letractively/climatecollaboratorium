package org.climatecollaboratorium.contests;


import javax.faces.event.ActionEvent;

import com.ext.portlet.contests.model.Contest;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.service.ImageLocalServiceUtil;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Aug 6, 2010
 * Time: 2:53:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContestWrapper {
    private Contest contest;
    private String debatesIdsStr = null;
    
    public boolean flag;
    
     public void setFlag(boolean b) {
        flag = b;
    }

    public boolean getFlag() {
        return flag;
    }

    public void test(ActionEvent e) {
        setFlag(!flag);
    }

    public ContestWrapper(Contest contest) throws SystemException, PortalException {
        this.contest = contest;
    }

    public String getName() {
        return contest.getContestName();

    }


    public Contest getContest() {
        return contest;
    }

    public String getShortName() {
        return contest.getContestShortName();
    }
    
    public String getLogo() throws PortalException, SystemException {
        return Helper.getThemeDisplay().getPathImage() + contest.getLogoPath();
    }
    
    public boolean isFeatured() {
        return contest.getFeatured();
    }
   
    
    public boolean isContestActive() {
        return contest.getContestActive();
    }
    
    public Long getModelId() throws PortalException, SystemException {
        
        return contest.getPlanType().getDefaultModelId();
    }
    
    
    public Long getContestId() {
        return contest.getContestPK();
    }
    
    public boolean getHasModel() throws PortalException, SystemException {
        Long modelId = getContest().getPlanType().getDefaultModelId();
        return modelId != null && modelId > 0;
    }
    
    public long getProposalsCount() throws SystemException, PortalException {
        return contest.getProposalsCount();
    }
    
    public long getCommentsCount() throws PortalException, SystemException {
        return contest.getTotalComments();
    }
    
}
