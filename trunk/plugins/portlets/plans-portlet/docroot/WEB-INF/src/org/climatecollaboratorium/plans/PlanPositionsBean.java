package org.climatecollaboratorium.plans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.service.DebateCategoryLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.ext.portlet.plans.NoSuchPlanPositionsException;
import com.ext.portlet.plans.model.PlanItem;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class PlanPositionsBean {
    private List<DebateQuestionWrapper> questions = new ArrayList<DebateQuestionWrapper>();
    private PlanItem plan;
    private boolean editing;
    
    public PlanPositionsBean(PlanItem plan) throws SystemException, NoSuchPlanPositionsException {
        List<Debate> debates = DebateLocalServiceUtil.getDebates();

        int i=0;
        for (Debate d : debates) {
            questions.add(new DebateQuestionWrapper(d.getCurrentRoot(), plan));
            if (++i==2) {
                break;
            }
        }
        this.plan = plan;
    }
    
    public List<DebateQuestionWrapper> getAvailablePositions() {
        return questions;
    }
    
    
    public void edit(ActionEvent e) {
        editing = !editing;
    }
    
    public boolean isEditing() {
        return editing;
    }
    
    public void updatePositions(ActionEvent e) throws PortalException, SystemException {
        List<Long> positions = new ArrayList<Long>();
        for (DebateQuestionWrapper question: questions) {
            if (question.getPosition() != null) {
                positions.add(question.getPosition());
            }
        }
        plan.setPositions(positions, 10L);
        editing = false;
    }
    
    
}
