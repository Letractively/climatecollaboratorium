package org.climatecollaboratorium.plans;

import javax.faces.event.ActionEvent;

import com.ext.portlet.plans.PlanUserPermission;
import com.ext.portlet.plans.model.PlanItem;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserGroupLocalServiceUtil;

public class PlanMember {
    private PlanItem plan;
    private User user;
    private PlanUserPermission planUserPermission;
    private boolean member;
    private boolean owner;
    private boolean permissionChanged;
    private PlanMembershipBean planMembershipBean;
    private PlansPermissionsBean permissions;
    

    public PlanMember(PlanItem plan, User user, PlanMembershipBean planMembershipBean, PlansPermissionsBean permissions) throws SystemException {
        this.plan = plan;
        this.user = user;
        this.planMembershipBean = planMembershipBean;
        this.permissions = permissions;
        
    }

    public String getScreenName() {
        return user.getScreenName();
    }

    public boolean isMember() throws SystemException {
        return plan.isUserAMember(user.getUserId());
    }

    public boolean isOwner() throws PortalException, SystemException {
        return plan.isOwner(user.getUserId());
    }

    public boolean isAdmin() throws PortalException, SystemException {
        return plan.isAdmin(user.getUserId());
    }
    
    public PlanUserPermission getPlanUserPermission() throws PortalException, SystemException {
        if (planUserPermission == null) {
            if (isOwner()) {
                planUserPermission = PlanUserPermission.OWNER; 
            }
            else if (isAdmin()) {
                planUserPermission = PlanUserPermission.ADMIN;
            }
            else {
                planUserPermission = PlanUserPermission.MEMBER;
            }
        }
        return planUserPermission;
    }
    
    public String getPlanUserPermissionStr() {
        return planUserPermission.name();
    }
    
    public void setPlanUserPermissionStr(String planUserPermissionStr) throws PortalException, SystemException {
        PlanUserPermission tmp = PlanUserPermission.valueOf(planUserPermissionStr);
        if (tmp != planUserPermission && permissions.getCanAdmin()) {
            System.out.println(tmp.compareTo(planUserPermission));
            
            if (tmp.compareTo(planUserPermission) > 0 && ! (permissions.getPlanOwner() || permissions.getCanAdminAll())) {
                // only admin can downgrade a user
                return;
            }
            planUserPermission = tmp;
            
            plan.setUserPermission(user.getUserId(), planUserPermission.name(), Helper.getLiferayUser().getUserId());
        }
    }
    
    public void remove(ActionEvent e) throws SystemException {
        plan.removeMember(user.getUserId(), Helper.getLiferayUser().getUserId());
        planMembershipBean.removeMember(this);
    }

    public Long getUserId() {
        return user.getUserId();
    }

    public boolean isPermissionChanged() {
        return permissionChanged;
    }
    
    
}