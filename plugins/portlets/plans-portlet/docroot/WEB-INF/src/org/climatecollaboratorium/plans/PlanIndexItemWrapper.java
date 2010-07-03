package org.climatecollaboratorium.plans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.faces.event.ActionEvent;

import com.ext.portlet.plans.PlanConstants.Columns;
import com.ext.portlet.plans.model.PlanItem;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class PlanIndexItemWrapper {
    private PlanItem wrapped;
    private Map<Columns, Object> columnValues;
    private PlansIndexBean plansIndexBean; 

    public PlanIndexItemWrapper(PlanItem wrapped, PlansIndexBean plansIndexBean) throws SystemException, PortalException {
        this.wrapped = wrapped;
        columnValues = new HashMap<Columns, Object>();
        
        for (Columns col: plansIndexBean.getColumns()) {
            columnValues.put(col, col.getValue(wrapped));
        }
        
        this.plansIndexBean = plansIndexBean;
    }
    
    
    public Map<Columns, Object> getColumnValues() {
        return columnValues;
    }
    
    public Long getPlanId() {
        return wrapped.getPlanId();
    }
    
    public boolean isVotedOn() throws PortalException, SystemException {
        System.out.print("Has user voted: " + " " + wrapped.getPlanId() + " '" + wrapped.getName() + "' ");
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
            }
            else {
                wrapped.vote(Helper.getLiferayUser().getUserId());
            }
        }
        plansIndexBean.refresh();
    }
}
