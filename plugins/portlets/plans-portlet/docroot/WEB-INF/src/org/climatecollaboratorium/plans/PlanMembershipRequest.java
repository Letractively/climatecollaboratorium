package org.climatecollaboratorium.plans;

import java.util.Date;

import javax.faces.event.ActionEvent;

import com.ext.portlet.plans.model.PlanItem;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.User;
import com.liferay.portal.service.MembershipRequestLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

public class PlanMembershipRequest {
    private MembershipRequest request;
    private User user;
    private String response;
    private PlanItem plan;
    private PlansPermissionsBean permissions;
    private boolean approve = true;
    private PlanBean planBean;
    
    public PlanMembershipRequest(MembershipRequest request, PlanItem plan, PlanBean planBean, PlansPermissionsBean permissions) throws PortalException, SystemException {
        this.request = request;
        this.user = UserLocalServiceUtil.getUser(request.getUserId()); 
        this.plan = plan;
        this.permissions = permissions;
        this.planBean = planBean;
    }
    
    public String getScreenName() {
        return user.getScreenName();
    }
    
    public Date getRequestDate() {
        return request.getCreateDate();
    }
    
    public String getComment() {
        return request.getComments();
    }
    
    public Long getId() {
        return request.getMembershipRequestId();
    }
    
    public String getResponse() {
        return response;
    }
    
    public void setResponse(String response) {
        this.response = response;
    }
    
    public boolean getApprove() {
        return approve;
    }
    
    public void setApprove(boolean approve) {
        this.approve = approve;
    }
    
    public void update(ActionEvent e) throws PortalException, SystemException {
        if (permissions.getCanAdmin()) { 
            if (approve) {
                plan.approveMembershipRequest(Helper.getLiferayUser().getUserId(), request, response);
            }
            else {
                plan.dennyMembershipRequest(Helper.getLiferayUser().getUserId(), request, response);
            }
        }
        planBean.refresh();
    }
    
}
