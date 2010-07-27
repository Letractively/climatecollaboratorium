package org.climatecollaboratorium.facelets.discussions.permissions;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.climatecollaboratorium.facelets.discussions.DiscussionBean;
import org.climatecollaboratorium.utils.Helper;

import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Permission;
import com.liferay.portal.model.Resource;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.PermissionLocalServiceUtil;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.permission.RolePermissionUtil;
import com.liferay.portal.service.permission.UserPermission;
import com.liferay.portal.service.permission.UserPermissionUtil;

public class DiscussionsPermissionsConfig {
    private static final String RESOURCE_NAME = DiscussionCategoryGroup.class.getName();
    private static final int RESOURCE_SCOPE = 1;
    private static final Long companyId = Helper.getThemeDisplay().getCompanyId();
    private Resource resource = null;
    private String primKey;
    private List<PermissionItem> permissionItems;

    private static Log _log = LogFactoryUtil.getLog(DiscussionsPermissionsConfig.class);
    
    public DiscussionsPermissionsConfig(DiscussionBean discussionBean) throws SystemException {
        // check if resource has been added, if not, add it
        primKey = discussionBean.getDiscussion().getId().toString();
        try {
            resource = ResourceLocalServiceUtil.getResource(companyId, RESOURCE_NAME, RESOURCE_SCOPE, primKey);
        }
        catch (Exception e) {
            _log.debug(e);
            resource = ResourceLocalServiceUtil.addResource(companyId, RESOURCE_NAME, RESOURCE_SCOPE, primKey);
        }
        
    }
    
    public class PermissionItem {
        private Role role;
        private String[] actionIds;
        
        public PermissionItem(Role role) {
            this.role = role;
        }
        public String getName() {
            return role.getName();
        }
        
        public String[] getActionIds() throws SystemException {
            if (actionIds == null) {
                List<Permission> permissions = PermissionLocalServiceUtil.getRolePermissions(role.getRoleId(), resource.getResourceId());
                actionIds = new String[permissions.size()];
                int i=0;
                for (Permission perm: permissions) {
                    actionIds[i++] = perm.getActionId();
                }
            }
            return actionIds;
        }
        
        public void setActionIds(String[] actionIds) {
            this.actionIds = actionIds;
        }
        
        public void updatePermissions() throws PortalException, SystemException {
            PermissionLocalServiceUtil.setRolePermissions(role.getRoleId(), 
                    companyId, 
                    RESOURCE_NAME, 
                    RESOURCE_SCOPE, 
                    primKey, 
                    actionIds
            );
        }
    }
    
    public List<PermissionItem> getItems() throws SystemException {
        if (permissionItems == null) {
            permissionItems = new ArrayList<PermissionItem>();
            for (Role role: RoleLocalServiceUtil.getRoles(companyId)) {
                permissionItems.add(new PermissionItem(role));
            }
        }
        return permissionItems;
    }
    
    public SelectItem[] getActionItems() {
        DiscussionActions[] actions = DiscussionActions.values();
        SelectItem[] items = new SelectItem[actions.length];
        for (int i=0; i < items.length; i++) {
            items[i] = new SelectItem(actions[i].name(), actions[i].getPrintName());
        }
        return items;
    }
    
    public void update(ActionEvent e) throws PortalException, SystemException {
        for (PermissionItem item: permissionItems) {
            item.updatePermissions();
        }
        Helper.sendInfoMessage("Permissions updated");
    }
}
