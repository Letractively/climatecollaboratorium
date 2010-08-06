package org.climatecollaboratorium.plans;

import com.ext.portlet.plans.PlanUserPermission;
import com.ext.portlet.plans.model.PlanItem;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

public class PlanMembershipBean {
    private PlanItem plan;
    private PlanBean planBean;
    private List<PlanMember> planMembers;
    private List<PlanMembershipRequest> planMembershipRequests;
    private String comment;
    private PlansPermissionsBean permissions;
    private boolean requesting;

    public PlanMembershipBean(PlanItem plan, PlanBean planBean, PlansPermissionsBean permissions) throws PortalException, SystemException {
        this.plan = plan;
        this.planBean = planBean;
        this.permissions = permissions;

        planMembers = new ArrayList<PlanMember>();



        planMembershipRequests = new ArrayList<PlanMembershipRequest>();

    }


    public List<PlanMember> getPlanMembers() throws SystemException {
        planMembers.clear();
        for (User user: plan.getMembers()) {
            planMembers.add(new PlanMember(plan, user, this, permissions));
        }

        return planMembers;
    }
    

    public List<PlanMembershipRequest> getPlanMembershipRequests() throws SystemException, PortalException {
        planMembershipRequests.clear();
        for (MembershipRequest request: plan.getMembershipRequests()) {
            planMembershipRequests.add(new PlanMembershipRequest(request, plan, planBean, permissions));
        }
        return planMembershipRequests;
    }

    public void requestMembership(ActionEvent e) throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            plan.addMembershipRequest(Helper.getLiferayUser().getUserId(), comment.length() == 0 ? "No comments" : comment);
            planBean.refresh();
        }
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public void toggleRequesting(ActionEvent e) {
        requesting = !requesting;
    }


    public boolean isRequesting() {
        return requesting;
    }
    
    public List<SelectItem> getAvailableUserRoles() throws SystemException, PortalException {
        List<SelectItem> items = new ArrayList<SelectItem>();

        if (permissions.getCanAdminAll()) {
            items.add(new SelectItem(PlanUserPermission.OWNER, PlanUserPermission.OWNER.getDescription()));
        }
        if (permissions.getCanAdmin()) {
            items.add(new SelectItem(PlanUserPermission.MEMBER, PlanUserPermission.MEMBER.getDescription()));
            items.add(new SelectItem(PlanUserPermission.ADMIN, PlanUserPermission.ADMIN.getDescription()));
        }
        return items;
        
    }


    public void removeMember(PlanMember planMember) {
        planMembers.remove(planMembers.indexOf(planMember));
    }
    
    public void removeCurrentUser(ActionEvent e) throws SystemException {
        if (Helper.isUserLoggedIn()) {
            plan.removeMember(Helper.getLiferayUser().getUserId(), Helper.getLiferayUser().getUserId());
        }
    }

}