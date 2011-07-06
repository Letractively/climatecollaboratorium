package org.climatecollaboratorium.plans.wrappers;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.plans.NoSuchPlanPositionsException;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.PlanConstants.Columns;
import com.ext.portlet.plans.model.PlanAttribute;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.climatecollaboratorium.plans.Helper;
import org.climatecollaboratorium.plans.PlansIndexBean;
import org.climatecollaboratorium.plans.activity.PlanActivityKeys;

public class PlanIndexItemWrapper {

    private static Logger log = Logger.getLogger(PlanIndexItemWrapper.class);

    private PlanItem wrapped;
    private Map<Columns, Object> columnValues;
    private PlansIndexBean plansIndexBean;
    private List<DebateQuestionWrapper> questions;
    private List<Debate> availableDebates;
    private ThemeDisplay td = Helper.getThemeDisplay();

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

    public List<DebateQuestionWrapper> getPositions() throws PortalException, SystemException {
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
                    questions.add(new DebateQuestionWrapper(wrapped.getContest(),d.getCurrentRoot(), planPositionsIds));
                }
            }
        }
        return questions;
    }

    public boolean getHasPositions() throws SystemException, NoSuchPlanPositionsException {
        List<Long> positions = null;
        try {
            positions = wrapped.getPositionsIds();
        } catch(Exception e) {
            log.error("Error retrieving plan positions for "+wrapped.getName(),e);
        }
        return positions != null && positions.size() > 0;
    }

    public Map<Columns, Object> getColumnValues() {
        return columnValues;
    }

    public String getPlanName() throws SystemException {
        return wrapped.getName();
    }

    public Long getPlanId() {
        return wrapped.getPlanId();
    }
    
    public User getAuthor() throws PortalException, SystemException {
        return wrapped.getAuthor();
    }

    public Long getContestPhaseId() throws SystemException, PortalException {
        return wrapped.getContestPhase().getContestPhasePK();
    }
    
    public Long getContestId() throws PortalException, SystemException {
        return wrapped.getContest().getContestPK();
    }

    public boolean isVotedOn() throws PortalException, SystemException {
        boolean voted = false;
        if (Helper.isUserLoggedIn()) {
            voted = wrapped.hasUserVoted(Helper.getLiferayUser().getUserId());
        }
        return voted;
    }

    public void vote(ActionEvent e) throws PortalException, SystemException {
        PlanActivityKeys activityKey = PlanActivityKeys.VOTE_FOR_PLAN;


        if (Helper.isUserLoggedIn()) {
            if (isVotedOn()) {
                wrapped.unvote(Helper.getLiferayUser().getUserId());
                activityKey = PlanActivityKeys.RETRACT_VOTE_FOR_PLAN;
            } else {
                try {
                    if (PlanVoteLocalServiceUtil.getPlanVote(Helper.getLiferayUser().getUserId(), wrapped.getContest().getContestPK()) != null) {
                        activityKey = PlanActivityKeys.SWICTH_VOTE_FOR_PLAN;
                    }
                   
                }
                catch (Throwable ex) {
                    // backend can throw no such vote exception, it should be ignored as this is a normal case
                }
                wrapped.vote(Helper.getLiferayUser().getUserId());
            }
        }
        SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                PlanItem.class.getName(), wrapped.getPlanId(), activityKey.id(),null, 0);
        plansIndexBean.refresh();
    }
    
    public Integer getPlace() throws SystemException {
        PlanAttribute attr = wrapped.getPlanAttribute(PlanConstants.Attribute.PLAN_PLACE.name());
        return attr != null ? (Integer) attr.getTypedValue() : -1;
    }
    
    
    public Integer getRibbon() throws SystemException {
        PlanAttribute attr = wrapped.getPlanAttribute(PlanConstants.Attribute.PLAN_RIBBON.name());
        try {
            return attr != null ? Integer.parseInt(attr.getAttributeValue()) : null;
        }
        catch (NumberFormatException e) {
            return null;
        }
    }
    
    public String getRibbonText() throws SystemException {
        PlanAttribute attr = wrapped.getPlanAttribute(PlanConstants.Attribute.PLAN_RIBBON_TEXT.name());
        return attr != null ? attr.getAttributeValue() : null;
    }
    
    public boolean isScrapbook() throws SystemException {
        return wrapped.getPlanAttribute("SCRAPBOOK") != null;
    }
    
}