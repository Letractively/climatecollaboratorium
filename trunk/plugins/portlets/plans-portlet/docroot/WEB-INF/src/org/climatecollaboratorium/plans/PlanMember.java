package org.climatecollaboratorium.plans;

import com.ext.portlet.plans.model.PlanItem;

import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;

public class PlanMember {
    private PlanItem plan;
    private User user;
    boolean member;
    boolean owner;

    public PlanMember(PlanItem plan, User user) throws SystemException {
        this.plan = plan;
        this.user = user;
        if (plan.getAuthorId().equals(user.getUserId())) {
            owner = true;
        }
        else {
            member = true;
        }
    }

    public String getScreenName() {
        return user.getScreenName();
    }

    public boolean isMember() {
        return member;
    }

    public boolean isOwner() {
        return owner;
    }

}