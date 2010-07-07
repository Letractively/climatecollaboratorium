package org.climatecollaboratorium.plans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.service.DebateCategoryLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.ext.portlet.plans.NoSuchPlanPositionsException;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanPositions;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

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
    }
    
    public List<DebateQuestionWrapper> getAvailablePositions() throws NoSuchPlanPositionsException, SystemException {
        if (! lastPositionsVersion.equals(planPositionsVersion)) {
            List<Debate> debates = DebateLocalServiceUtil.getDebates();
            questions.clear();
            int i=0;
            for (Debate d : debates) {
                questions.add(new DebateQuestionWrapper(d.getCurrentRoot(), plan.getPositionsIds()));
                if (i++ >= 4) {
                    break;
                }
            }
            lastPositionsVersion = planPositionsVersion;
        }
        return questions;
    }

    public void edit(ActionEvent e) {
        editing = !editing;
    }

    public boolean isEditing() {
        return editing;
    }

    public void updatePositions(ActionEvent e) throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            List<Long> positions = new ArrayList<Long>();
            for (DebateQuestionWrapper question : questions) {
                if (question.getPosition() != null) {
                    positions.add(question.getPosition());
                }
            }
            plan.setPositions(positions, Helper.getLiferayUser().getUserId());
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

}
