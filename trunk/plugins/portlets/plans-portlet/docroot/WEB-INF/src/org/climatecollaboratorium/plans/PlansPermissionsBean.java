package org.climatecollaboratorium.plans;


import com.ext.portlet.plans.model.PlanItem;
import com.liferay.portal.SystemException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

public class PlansPermissionsBean {
    
    private final PermissionChecker permissionChecker;
    private final String portletId;
    private final long groupId;
    private final String primKey;
    private PlanItem plan;
    private Long planGroupId;
    
    public PlansPermissionsBean() {
        permissionChecker = Helper.getPermissionChecker();
        groupId = Helper.groupId;
        primKey = Helper.primKey;
        portletId = Helper.portletId;
        
        planGroupId = groupId;
    }
    
    public PlansPermissionsBean(PlanItem plan) throws SystemException {
        this();
        setPlan(plan);
    }
    
    public boolean getCanView() {
        return permissionChecker.hasPermission(groupId, portletId, primKey, PlansActions.CAN_VIEW);
    }
    
    
    public boolean getCanEdit() throws SystemException {
        return getPlanMember() && permissionChecker.hasPermission(groupId, portletId, primKey, PlansActions.CAN_EDIT);
    }
    
    public boolean getCanAdmin() throws SystemException {
        return getPlanMember() && permissionChecker.hasPermission(groupId, portletId, primKey, PlansActions.CAN_ADMIN);
    }
    
    public boolean getCanAdminAll() {
        return permissionChecker.hasPermission(groupId, portletId, primKey, PlansActions.CAN_ADMIN_ALL);
    }
    
    public boolean getCanDelete() throws SystemException {
        return getPlanMember() && permissionChecker.hasPermission(groupId, portletId, primKey, PlansActions.CAN_DELETE);
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

    public void setPlan(PlanItem plan) throws SystemException {
        this.plan = plan;
        // use group id from plans community
        planGroupId = plan.getPlanGroupId();
    }

    public PlanItem getPlan() {
        return plan;
    }
    
}
