package org.climatecollaboratorium.plans;

import com.ext.portlet.models.ui.IllegalUIConfigurationException;
import com.ext.portlet.plans.PlanUserPermission;
import com.ext.portlet.plans.model.PlanItem;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.climatecollaboratorium.plans.wrappers.PlanMember;

public class PlanMembershipBean {
    private PlanItem plan;
    private PlanBean planBean;
    private List<PlanMember> planMembers;
    private List<PlanMember> planMembersHalf1;
    private List<PlanMember> planMembersHalf2;
    private List<PlanMembershipRequest> planMembershipRequests;
    private String comment;
    private PlansPermissionsBean permissions;
    private boolean requesting;

    public PlanMembershipBean(PlanItem plan, PlanBean planBean, PlansPermissionsBean permissions) throws PortalException, SystemException {
        this.plan = plan;
        this.planBean = planBean;
        this.permissions = permissions;

        planMembers = new ArrayList<PlanMember>();
        planMembersHalf1 = new ArrayList<PlanMember>();
        planMembersHalf2 = new ArrayList<PlanMember>();


        planMembershipRequests = new ArrayList<PlanMembershipRequest>();

    }


    public List<PlanMember> getPlanMembers() throws SystemException {
        if (planMembers.size() == 0) {
            planMembers.clear();
            for (User user: plan.getMembers()) {
                planMembers.add(new PlanMember(plan, user, this, permissions));
            }
        }
        return planMembers;
    }
    
    public List<PlanMember> getPlanMembersHalf1() throws SystemException {
        /*
        if (planMembersHalf1.size() == 0) {
            planMembersHalf1.addAll(getPlanMembersSublist(0, (plan.getMembers().size() / 2) + (plan.getMembers().size() % 2)));
        }
        return planMembersHalf1;
        */
        return getPlanMembersSublist(0, (plan.getMembers().size() / 2) + (plan.getMembers().size() % 2)); 
    }
    
    public List<PlanMember> getPlanMembersHalf2() throws SystemException {
        /*
        if (planMembersHalf2.size() == 0) {
            planMembersHalf2.addAll(getPlanMembersSublist((plan.getMembers().size() / 2) + (plan.getMembers().size() % 2), plan.getMembers().size()));
        }
        return planMembersHalf2;
        */
        return getPlanMembersSublist((plan.getMembers().size() / 2) + (plan.getMembers().size() % 2), plan.getMembers().size());
    }
    
    private List<PlanMember> getPlanMembersSublist(int from, int to) throws SystemException {
        List<PlanMember> ret = new ArrayList<PlanMember>();
        for (int i=from; i < to; i++) {
            ret.add(new PlanMember(plan, plan.getMembers().get(i), this, permissions));
        }
        return ret;
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


    public void toggleRequestingOrJoin(ActionEvent e) throws SystemException, PortalException {
        if (plan.getOpen()) {
           plan.joinIfNotAMember(Helper.getLiferayUser().getUserId());
        } else {
            requesting = !requesting;
        }
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
    
    public void removeCurrentUser(ActionEvent e) throws SystemException, PortalException, IllegalUIConfigurationException, IOException {
        if (Helper.isUserLoggedIn()) {
            plan.removeMember(Helper.getLiferayUser().getUserId(), Helper.getLiferayUser().getUserId());
            planBean.refreshFull();
        }
    }

}