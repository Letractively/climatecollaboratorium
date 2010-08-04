package org.climatecollaboratorium.plans;

import com.ext.portlet.plans.UpdateType;
import com.ext.portlet.plans.model.PlanItem;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;

import java.util.Date;

public class PlanHistoryItem {
    private PlanItem plan;
    private String updateType;
    private User updateAuthor;

    public PlanHistoryItem(PlanItem plan) throws PortalException, SystemException  {
        this.plan = plan;
        updateAuthor = plan.getUpdateAuthor();
        updateType = plan.getUpdateType();
    }

    public String getUpdateType() {
        return UpdateType.valueOf(plan.getUpdateType()).description();
    }

    public User getUpdateAuthor() {
        return updateAuthor;
    }

    public Date getUpdateDate() {
        return plan.getUpdated();
    }
}