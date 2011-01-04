package org.climatecollaboratorium.userprofile;

import java.util.Date;

import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.model.PlanFan;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.liferay.portal.SystemException;

public class SupportedPlanBean {
    
    private PlanFan supportedPlanInfo;
    private PlanItem supportedPlan;
    
    public SupportedPlanBean(PlanFan supportedPlanInfo) throws NoSuchPlanItemException, SystemException {
        this.supportedPlanInfo = supportedPlanInfo;
        supportedPlan = PlanItemLocalServiceUtil.getPlan(supportedPlanInfo.getPlanId());
    }
    
    public String getPlanName() throws SystemException {
        return supportedPlan.getName();
    }
    
    public Date getCreatedDate() {
        return supportedPlanInfo.getCreated();
    }
    
    

}
