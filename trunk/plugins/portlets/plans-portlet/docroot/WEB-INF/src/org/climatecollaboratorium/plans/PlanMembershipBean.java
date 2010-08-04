package org.climatecollaboratorium.plans;

import com.ext.portlet.plans.model.PlanItem;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

public class PlanMembershipBean {
    private PlanItem plan;
    private PlanBean planBean;
    private List<PlanMember> planMembers;
    private List<PlanMembershipRequest> planMembershipRequests;
    private String comment;
    private PlansPermissionsBean permissions;

    public PlanMembershipBean(PlanItem plan, PlanBean planBean, PlansPermissionsBean permissions) throws PortalException, SystemException {
        this.plan = plan;
        this.planBean = planBean;
        this.permissions = permissions;

        planMembers = new ArrayList<PlanMember>();

        for (User user: plan.getMembers()) {
            planMembers.add(new PlanMember(plan, user));
        }

        planMembershipRequests = new ArrayList<PlanMembershipRequest>();
        for (MembershipRequest request: plan.getMembershipRequests()) {
            planMembershipRequests.add(new PlanMembershipRequest(request, plan, planBean, permissions));
        }
    }


    public List<PlanMember> getPlanMembers() throws SystemException {
        return planMembers;
    }

    public List<PlanMembershipRequest> getPlanMembershipRequests() throws SystemException {
        return planMembershipRequests;
    }

    public void requestMembership(ActionEvent e) throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            plan.addMembershipRequest(Helper.getLiferayUser().getUserId(), comment);
            planBean.refresh();
        }
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}