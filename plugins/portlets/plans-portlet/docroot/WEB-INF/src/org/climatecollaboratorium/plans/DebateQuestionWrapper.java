package org.climatecollaboratorium.plans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.plans.NoSuchPlanPositionsException;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanPositions;
import com.liferay.portal.SystemException;

public class DebateQuestionWrapper {
    private DebateItem wrapped;
    private List<SelectItem> positions = new ArrayList<SelectItem>();
    private Long position;
    private DebateItem selectedPosition;
    
    /*
    public DebateQuestionWrapper(DebateItem wrapped, PlanPositions planPositions) throws SystemException, NoSuchPlanPositionsException {
        this(wrapped, planPositions.getPositionsIds());
    }
    */
    public DebateQuestionWrapper(DebateItem wrapped, Set<Long> planPositionsIds) {
        
        this.wrapped = wrapped;
        
        for (DebateItem position: wrapped.getChildren()) {
            positions.add(new SelectItem(position.getDebateItemId(), position.getDebateSummary()));
            if (planPositionsIds.contains(position.getDebateItemId())) {
                this.position = position.getDebateItemId();
            }
        }
        updateSelectedPosition();
        
    }
    
    public String getDebateSummary() {
        return wrapped.getDebateSummary();
    }
    
    public List<DebateItem> getChildren() {
        return wrapped.getChildren();
    }
    
    public List<SelectItem> getPositions() {
        return positions; 
    }
    
    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
        updateSelectedPosition();
    }
    
    public DebateItem getSelectedPosition() {
        return selectedPosition;
    }

    
    private void updateSelectedPosition() {
        for (DebateItem item: wrapped.getChildren()) {
            if (item.getDebateItemId().equals(position)) {
                selectedPosition = item;
            }
        }
    }
}
