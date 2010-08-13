package org.climatecollaboratorium.plans;

import com.ext.portlet.plans.PlanUserPermission;
import com.ext.portlet.plans.model.PlanItem;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

public class PlansPermissionsBean {

    private final PermissionChecker permissionChecker;
    private final String portletId;
    private final long groupId;
    private final String primKey;
    private PlanItem plan;
    private boolean planPublished = false;
    private PlanUserPermission planUserPermission;
    private Long planGroupId;
    private Long userId = 0L;

    public PlansPermissionsBean() throws SystemException, PortalException {
        permissionChecker = Helper.getPermissionChecker();
        groupId = Helper.groupId;
        primKey = Helper.primKey;
        portletId = Helper.portletId;
        if (Helper.isUserLoggedIn()) {
            userId = Helper.getLiferayUser().getUserId();
        }

        planGroupId = groupId;
        updatePlanUserPermission();
    }
    
    private void updatePlanUserPermission() throws SystemException, PortalException {
        if (getPlanOwner()) {
            planUserPermission = PlanUserPermission.OWNER;
        }
        else if (getCanAdmin()) {
            planUserPermission = PlanUserPermission.ADMIN;
        }
        else if (getPlanMember()) {
            planUserPermission = PlanUserPermission.MEMBER;
        }
    }

    public PlansPermissionsBean(PlanItem plan) throws SystemException, PortalException {
        this();
        setPlan(plan);
    }

    public boolean getCanView() {
        return true;
    }

    // when plan is published only site admin can modify it
    public boolean getCanEdit() throws SystemException, PortalException {
        return (!planPublished && (
                        getPlanOpen() && Helper.isUserLoggedIn()
                        || getPlanMember()
                        || getCanAdmin()
                    )
                ) || getCanAdminAll();
    }

    // when plan is published only site admin can admin it
    public boolean getCanAdmin() throws SystemException, PortalException {
        return (!planPublished && (
                        (getPlanMember() && permissionChecker.isCommunityAdmin(planGroupId)) 
                        || getPlanOwner() 
                    )
                ) || getCanAdminAll();
                
    }

    public boolean getCanAdminAll() {
        return permissionChecker.hasPermission(groupId, portletId, primKey, PlansActions.CAN_ADMIN_ALL);
    }

    public boolean getCanDelete() throws SystemException, PortalException {
        return getCanAdmin();
    }

    public boolean getPlanOwner() {
        return permissionChecker.isCommunityOwner(planGroupId);
    }

    public boolean getPlanMember() throws SystemException {
        if (Helper.isUserLoggedIn() && plan != null) {
            boolean x = GroupLocalServiceUtil.hasUserGroup(Helper.getLiferayUser().getUserId(), planGroupId);
            return x;
        }
        return false;
    }

    public boolean getPlanGuest() throws SystemException {
        return !getPlanOwner() && !getPlanMember();
    }

    public void setPlan(PlanItem plan) throws SystemException, PortalException {
        this.plan = plan;
        if (plan!=null) {
        // use group id from plans community
        planGroupId = plan.getPlanGroupId();
        planPublished = plan.getPlanType().getPublished();
        updatePlanUserPermission();
        }
    }

    public PlanItem getPlan() {
        return plan;
    }
    
    public PlanUserPermission getPlanUserPermission() {
        return planUserPermission;
    }
    
    public boolean getPlanOpen() throws SystemException {
        return plan == null ? false : plan.getOpen();
    }

}