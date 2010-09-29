package org.climatecollaboratorium.plans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.plans.activity.PlanActivityKeys;
import org.climatecollaboratorium.plans.events.PlanUpdatedEvent;
import org.climatecollaboratorium.plans.wrappers.DebateQuestionWrapper;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.ext.portlet.plans.NoSuchPlanPositionsException;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanPositions;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

public class PlanPositionsBean {
    private List<DebateQuestionWrapper> questions = new ArrayList<DebateQuestionWrapper>();
    
    private PlanItem plan;
    private boolean editing;
    
    private List<PlanHistoryWrapper> planPositionHistoryItems = new ArrayList<PlanHistoryWrapper>();
    private PlanHistoryWrapper<PlanPositions> planPositionsHisotryItem;
    
    private boolean positionsSet;
    private List<Debate> availableDebates;
    private ThemeDisplay td = Helper.getThemeDisplay();
    private boolean updatePositions;
    private EventBus eventBus;
    private List<Long> positionsIds;

    public PlanPositionsBean(PlanItem plan) throws SystemException, PortalException {

        this.plan = plan;
        positionsIds = plan.getPositionsIds();

        if (plan.getPositionsIds().size() > 0) {
            positionsSet = true;
        }
        
        availableDebates = new ArrayList<Debate>();
        for (Long debateId: plan.getContest().getDebatesIds()) {
            availableDebates.add(DebateLocalServiceUtil.findLastVersion(debateId));
        }
        updatePositions = true;
    }

    public List<DebateQuestionWrapper> getAvailablePositions() throws NoSuchPlanPositionsException, SystemException {
        if (updatePositions) {
            Set<Long> planPositionsIds = new HashSet<Long>(positionsIds);
            positionsSet = planPositionsIds.size() > 0;

            questions.clear();
            if (editing) {
                // we are editing all debates should be shown
                for (Debate d : availableDebates) {
                    questions.add(new DebateQuestionWrapper(d.getCurrentRoot(), planPositionsIds));
                }
            }
            else {
                // we are viewing, show only debates that have position set
                for (Debate d : availableDebates) {
                    boolean positionFromDebateSelected = false;
                    for (DebateItem position: d.getCurrentRoot().getChildren()) {
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
            updatePositions = false;
        }
        return questions;
    }

    public void edit(ActionEvent e) {
        editing = !editing;
        updatePositions = true;
    }

    public boolean isEditing() {
        return editing;
    }

    public void updatePositions(ActionEvent e) throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            List<Long> positions = new ArrayList<Long>();
            for (DebateQuestionWrapper question : questions) {
                if (question.getPosition() != null && question.getPosition() != 0L) {
                    positions.add(question.getPosition());
                }
            }
            plan.setPositions(positions, Helper.getLiferayUser().getUserId());

            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    PlanItem.class.getName(), plan.getPlanId(), PlanActivityKeys.EDIT_POSITIONS.id(),null, 0);
        }
        editing = false;

        eventBus.fireEvent(new PlanUpdatedEvent(plan));
    }

    public List<PlanHistoryWrapper> getPlanPositionsVersions() throws SystemException {
        if (planPositionHistoryItems.size() == 0) {
            boolean isLatest = true;
            for (PlanPositions positions: plan.getAllPositionsVersions()) {
                planPositionHistoryItems.add(PlanHistoryWrapper.getWrapper(positions, isLatest));
                isLatest = false;
            }
        }
        return planPositionHistoryItems;
    }

    public void selectVersion(ActionEvent evt) throws SystemException {
        PlanHistoryWrapper wrapper = 
        planPositionsHisotryItem = (PlanHistoryWrapper<PlanPositions>) evt.getComponent().getAttributes().get("item");
        positionsIds = planPositionsHisotryItem.getWrapped().getPositionsIds();
        updatePositions = true;
    }


    public boolean isPositionsSet() {
        return positionsSet;
    }

    public boolean isLatestVersion() {
        return planPositionsHisotryItem == null || planPositionsHisotryItem.isLatest();
    }

    public Date getVersionDate() {
        return planPositionsHisotryItem.getUpdateDate();
    }

    public User getVersionAuthor() throws PortalException, SystemException {
        return planPositionsHisotryItem.getUpdateAuthor();
    }
    
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }
    
    public List<Long> getPositionsIds() {
        return positionsIds;
    }

}