/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.plans.wrappers;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.models.ui.ModelUIFactory;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.plans.PlansIndexBean;

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
    private String debatesIdsStr = null;
    private PlansIndexBean plansIndex;
    private EventBus eventBus;
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
        ContestPhaseWrapper activePhase = null;
        for (ContestPhase phase:contest.getPhases()) {
            ContestPhaseWrapper phaseWrapper = new ContestPhaseWrapper(this,phase);
            phases.add(phaseWrapper);
            index.put(phase.getContestPhasePK(),phaseWrapper);
            if (phase.getPhaseActive() != null && phase.getPhaseActive()) {
                activePhase = phaseWrapper;
            }
        }
        editor = new EditContestBean();
        plansIndex = new PlansIndexBean(activePhase);
    }

    public String getName() {
        return contest.getContestName();

    }

    public String getDescription() {
        return contest.getContestDescription();
    }

    public String getModelDescription() {
        return contest.getContestModelDescription();
    }

    public String getPositionsDescription() {
        return contest.getContestPositionsDescription();
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
    
    public List<Long> getDebatesIds() throws SystemException {
        return contest.getDebatesIds();
    }

    public String getShortName() {
        return contest.getContestShortName();
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
        String modelDescription;
        String positionsDescription;
        String shortName;
        boolean editing = false;
        boolean editingPositions = false;
        private List<DebateQuestionWrapper> questions = new ArrayList<DebateQuestionWrapper>();
        private List<DebateQuestionWrapper> positionsQuestions = new ArrayList<DebateQuestionWrapper>();
        private List<SelectItem> questionItems ;


        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getModelDescription() {
            return modelDescription;
        }

        public void setModelDescription(String description) {
            this.modelDescription = description;
        }

        public String getPositionsDescription() {
            return positionsDescription;
        }

        public void setPositionsDescription(String description) {
            this.positionsDescription = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        
        public String getShortName() {
            return shortName;
        }
        
        public void setShortName(String shortName) {
            this.shortName = shortName; 
        }


        public boolean getEditing() {
            return editing;
        }


        public void edit() {
            editing = true;
            this.name = contest.getContestName();
            this.description = contest.getContestDescription();
            this.positionsDescription = contest.getContestPositionsDescription();
            this.modelDescription = contest.getContestModelDescription();
            this.shortName = contest.getContestShortName();

        }

        public void save() throws SystemException {

            contest.setContestName(name);
            contest.setContestDescription(description);
            contest.setContestModelDescription(modelDescription);
            contest.setContestPositionsDescription(positionsDescription);
            contest.setContestShortName(shortName);
            ContestLocalServiceUtil.updateContest(contest);
            editing = false;
        }

        public void cancel() {
            editing = false;
        }

        public boolean isEditingPositions() {
            return editingPositions;
        }
        
        public void editPositions() {
            editingPositions = ! editingPositions;
        }
        
        public List<DebateQuestionWrapper> getAvailableDebates() throws SystemException {
            if (questions.size() == 0) {
                Set<Long> selectedPositionsIds = new HashSet<Long>(contest.getDebatesIds());
                for (Debate debate : DebateLocalServiceUtil.getDebates()) {
                    questions.add(new DebateQuestionWrapper(contest,debate.getCurrentRoot(), selectedPositionsIds));
                }
            }
            return questions;
        }
        
        public List<SelectItem> getAvailableDebatesItems() throws SystemException {
            if (questionItems == null) {
                questionItems = new ArrayList<SelectItem>();
                
                for (Debate debate : DebateLocalServiceUtil.getDebates()) {
                    questionItems.add(new SelectItem(debate.getDebateId(), debate.getCurrentRoot().getDebateSummary()));
                }
            }
            return questionItems;
        }
        
        public Long[] getContestDebates() throws SystemException {
            return contest.getDebatesIds().toArray(new Long[0]);
        }
        
        public void setContestDebates(Long[] debatesIds) throws SystemException {
            try {
                contest.setDebates(Arrays.asList(debatesIds));
            }
            catch (Exception e) {
                
                e.printStackTrace();
            }
        }

    }
    
    public boolean isContestActive() {
        return contest.getContestActive();
    }
    
    public Long getModelId() throws PortalException, SystemException {
        
        return contest.getPlanType().getDefaultModelId();
    }
    
    public PlansIndexBean getPlansIndex() {
        return plansIndex;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        plansIndex.setEventBus(eventBus);
    }
    
}
