package org.climatecollaboratorium.plans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.faces.event.ActionEvent;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.plans.NoSuchPlanPositionsException;
import com.ext.portlet.plans.PlanConstants.Columns;
import com.ext.portlet.plans.model.PlanItem;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class PlanIndexItemWrapper {
    private PlanItem wrapped;
    private Map<Columns, Object> columnValues;
    private PlansIndexBean plansIndexBean;
    private List<DebateQuestionWrapper> questions;
    private List<Debate> availableDebates;

    public PlanIndexItemWrapper(PlanItem wrapped, PlansIndexBean plansIndexBean, List<Debate> availableDebates)
            throws SystemException, PortalException {
        this.wrapped = wrapped;
        columnValues = new HashMap<Columns, Object>();

        for (Columns col : plansIndexBean.getColumns()) {
            columnValues.put(col, col.getValue(wrapped));
        }

        this.plansIndexBean = plansIndexBean;
        this.availableDebates = availableDebates;
    }

    public List<DebateQuestionWrapper> getPositions() throws NoSuchPlanPositionsException, SystemException {
        if (questions == null) {
            questions = new ArrayList<DebateQuestionWrapper>();
            Set<Long> planPositionsIds = new HashSet<Long>(wrapped.getPositionsIds());
            // we are viewing, show only debates that have position set
            for (Debate d : availableDebates) {
                boolean positionFromDebateSelected = false;
                for (DebateItem position : d.getCurrentRoot().getChildren()) {
                    if (planPositionsIds.contains(position.getDebateItemId())) {
                        positionFromDebateSelected = true;
                        break;
                    }
                }
                if (positionFromDebateSelected) {
                    questions.add(new DebateQuestionWrapper(d.getCurrentRoot(), planPositionsIds));
                }
            }
        }
        return questions;
    }

    public Map<Columns, Object> getColumnValues() {
        return columnValues;
    }

    public Long getPlanId() {
        return wrapped.getPlanId();
    }

    public boolean isVotedOn() throws PortalException, SystemException {
        boolean voted = false;
        if (Helper.isUserLoggedIn()) {
            voted = wrapped.hasUserVoted(Helper.getLiferayUser().getUserId());
        }
        System.out.println(voted);
        return voted;
    }

    public void vote(ActionEvent e) throws PortalException, SystemException {

        if (Helper.isUserLoggedIn()) {
            if (isVotedOn()) {
                wrapped.unvote(Helper.getLiferayUser().getUserId());
            } else {
                wrapped.vote(Helper.getLiferayUser().getUserId());
            }
        }
        plansIndexBean.refresh();
    }
}
