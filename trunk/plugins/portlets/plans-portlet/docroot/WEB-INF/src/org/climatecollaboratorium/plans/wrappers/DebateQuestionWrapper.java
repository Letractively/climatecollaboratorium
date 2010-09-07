package org.climatecollaboratorium.plans.wrappers;

import com.ext.portlet.debaterevision.model.DebateItem;

import java.util.*;

import javax.faces.model.SelectItem;

public class DebateQuestionWrapper {
    private DebateItem wrapped;
    private List<SelectItem> positions = new ArrayList<SelectItem>();
    private Long position;
   private List<Long> multiplePositions = new ArrayList<Long>();

    private DebateItemWrapper selectedPosition;
    private List<DebateItem> multipleSelectedPositions = new ArrayList<DebateItem>();

    /*
    public DebateQuestionWrapper(DebateItem wrapped, PlanPositions planPositions) throws SystemException, NoSuchPlanPositionsException {
        this(wrapped, planPositions.getPositionsIds());
    }
    */
    public DebateQuestionWrapper(DebateItem wrapped, Set<Long> planPositionsIds) {

        this.wrapped = wrapped;

        for (DebateItem position: wrapped.getChildren()) {
            positions.add(new SelectItem(position.getDebateItemId(), getPositionAnchor(position)));
            if (planPositionsIds.contains(position.getDebateItemId())) {
                this.position = position.getDebateItemId();
                //sorry about this - fugly code
                multiplePositions.add(position.getDebateItemId());
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

    public DebateItemWrapper getSelectedPosition() {
        return selectedPosition;
    }

    public void setMultiplePositions(Long[] positions) {
        this.multiplePositions.clear();
        for (Long s:positions) {
            multiplePositions.add(s);
        }

    }

    public Long[] getMultiplePositions() {
        Long[] ret = new Long[multiplePositions.size()];
        return multiplePositions.toArray(ret);
    }

//    private void updateMultipleSelection() {
//        multipleSelectedPositions.clear();
//        for (DebateItem item: wrapped.getChildren()) {
//            if (multiplePositions.contains(item.getDebateItemId())) {
//                multipleSelectedPositions.add(item);
//            }
//        }
//    }
//
    private void updateSelectedPosition() {
        for (DebateItem item: wrapped.getChildren()) {
            if (item.getDebateItemId().equals(position)) {
                selectedPosition = new DebateItemWrapper(item);
            }
        }
    }

    public Long getDebateId() {
        return wrapped.getDebateId();
    }

    public String getDebateLink() {
        return "/web/guest/debates#debate=" + wrapped.getDebateId();
    }

    public String getPositionLink(DebateItem position) {
        return "/web/guest/debates#debate=" + wrapped.getDebateId() + ";item=" + position.getDebateItemId();
    }

    public String getPositionAnchor(DebateItem position) {
        return "<a href='" + getPositionLink(position) + "' target='_blank'>" + position.getDebateSummary() + "</a>";
    }

    public String getDebateAnchor() {
        return "<a href='" + getDebateLink() + "' target='_blank'>" + getDebateSummary() + "</a>";
    }
}