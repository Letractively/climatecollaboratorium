/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.plans;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestPhase;
import com.liferay.portal.SystemException;

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


    public ContestWrapper(Contest contest) throws SystemException {
        this.contest = contest;
        for (ContestPhase phase:contest.getPhases()) {
            ContestPhaseWrapper phaseWrapper = new ContestPhaseWrapper(this,phase);
            phases.add(phaseWrapper);
            index.put(phase.getContestPhasePK(),phaseWrapper);
        }
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
}
