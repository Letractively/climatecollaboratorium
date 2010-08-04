package org.climatecollaboratorium.plans;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.plans.NoSuchPlanPositionsException;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanPositions;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.climatecollaboratorium.plans.activity.PlanActivityKeys;

public class PlanPositionsBean {
    private List<DebateQuestionWrapper> questions = new ArrayList<DebateQuestionWrapper>();
    private PlanItem plan;
    private boolean editing;
    private PlanBean planBean;
    private List<PlanPositions> planPositions;
    private Map<Long, PlanPositions> planPositionsById = new HashMap<Long, PlanPositions>();
    private List<SelectItem> planPositionItems = new ArrayList<SelectItem>();
    private Long planPositionsVersion;
    private Long lastPositionsVersion;
    private boolean positionsSet;
    private List<Debate> availableDebates;
    private ThemeDisplay td = Helper.getThemeDisplay();
    private boolean updatePositions;

    public PlanPositionsBean(PlanItem plan, PlanBean planBean) throws SystemException, PortalException {

        this.plan = plan;
        this.planBean = planBean;

        planPositions = plan.getAllPositionsVersions();
        for (PlanPositions positions: planPositions) {
            planPositionsById.put(positions.getId(), positions);
            planPositionItems.add(new SelectItem(positions.getId(), positions.getCreated() + " by " + positions.getUpdateAuthor().getScreenName()));
        }
        planPositionsVersion = planPositions.get(0).getId();
        lastPositionsVersion = -1L;
        if (plan.getPositionsIds().size() > 0) {
            positionsSet = true;
        }
        availableDebates = PlansPreferencesBean.getQuestionDebates();
    }

    public List<DebateQuestionWrapper> getAvailablePositions() throws NoSuchPlanPositionsException, SystemException {
        if (! lastPositionsVersion.equals(planPositionsVersion) || updatePositions) {
            Set<Long> planPositionsIds = new HashSet<Long>(planPositionsById.get(planPositionsVersion).getPositionsIds());
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
            lastPositionsVersion = planPositionsVersion;
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
        planBean.refresh();
    }

    public List<SelectItem> getPlanPositionsVersions() {
        return planPositionItems;
    }

    public Long getPlanPositionsVersion() {
        return planPositionsVersion;
    }

    public void setPlanPositionsVersion(Long planPositionsVersion) {
        this.planPositionsVersion = planPositionsVersion;
    }

    public String getSelectedPositionsIds() throws SystemException {
        return planPositionsById.get(planPositionsVersion).getPositionsIds().toString();
    }

    public void setSelectedPositionsIds(String value) {
        // ignore

    }

    public boolean isPositionsSet() {
        return positionsSet;
    }

    public void setPositionsSet(boolean empty) {
        this.positionsSet = empty;
    }

    public boolean isLatestVersion() {
        return planPositionsById.get(planPositionsVersion) == planPositions.get(0);
    }

    public Date getVersionDate() {
        return planPositionsById.get(planPositionsVersion).getCreated();
    }

    public User getVersionAuthor() throws PortalException, SystemException {
        return planPositionsById.get(planPositionsVersion).getUpdateAuthor();
    }

}