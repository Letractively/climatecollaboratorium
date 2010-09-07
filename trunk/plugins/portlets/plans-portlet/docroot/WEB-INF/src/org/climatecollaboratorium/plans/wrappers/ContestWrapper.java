/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.plans.wrappers;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.ext.portlet.models.CollaboratoriumModelingService;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

import javax.faces.event.ActionEvent;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Aug 6, 2010
 * Time: 2:53:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContestWrapper {
    private Contest contest;
    private List<ContestPhaseWrapper> phases = new ArrayList<ContestPhaseWrapper>();
    private Map<Long,ContestPhaseWrapper> index = new HashMap<Long,ContestPhaseWrapper>();
    private EditContestBean editor;


    public ContestWrapper(Contest contest) throws SystemException {
        this.contest = contest;
        for (ContestPhase phase:contest.getPhases()) {
            ContestPhaseWrapper phaseWrapper = new ContestPhaseWrapper(this,phase);
            phases.add(phaseWrapper);
            index.put(phase.getContestPhasePK(),phaseWrapper);
        }
        editor = new EditContestBean();
    }

    public String getName() {
        return contest.getContestName();

    }

    public String getDescription() {
        return contest.getContestDescription();
    }

    public List<ContestPhaseWrapper> getPhases() {
        return phases;
    }

    public ContestPhaseWrapper findPhase(Long contestPhaseId) {
        return index.get(contestPhaseId);
    }

    public Contest getContest() {
        return contest;
    }

    public EditContestBean getEditor() {
        return editor;
    }

   

    /**
     * Created by IntelliJ IDEA.
     * User: jintrone
     * Date: Aug 17, 2010
     * Time: 10:08:58 AM
     * To change this template use File | Settings | File Templates.
     */
    public class EditContestBean {


        String name;
        String description;
        boolean editing = false;



        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public boolean getEditing() {
            return editing;
        }


        public void edit() {
            editing = true;
            this.name = contest.getContestName();
            this.description = contest.getContestDescription();
        }

        public void save() throws SystemException {

            contest.setContestName(name);
            contest.setContestDescription(description);
            ContestLocalServiceUtil.updateContest(contest);
            editing = false;
        }

        public void cancel() {
            editing = false;
        }



    }
    
    public boolean isContestActive() {
        return contest.getContestActive();
    }
    
    public Long getModelId() throws PortalException, SystemException {
        
        return contest.getPlanType().getDefaultModelId();
    }
}
