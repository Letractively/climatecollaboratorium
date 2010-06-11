package org.climatecollaboratorium.modelsmigration;

import javax.faces.event.ActionEvent;

import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.liferay.portal.SystemException;

public class PlanTypeWrapper {
    private PlanType wrappedPlanType;
    private Long modelId;
    
    public PlanTypeWrapper(PlanType wrapped) {
        this.wrappedPlanType = wrapped;
    }
    
    public String getName() {
        return wrappedPlanType.getName();
    }

    public Long getPlanTypeId() {
        return wrappedPlanType.getPlanTypeId();
    }
    
    public boolean isPublished() {
        return wrappedPlanType.getPublished();
    }
    
    public Long getPublishedCounterpartId() {
        return wrappedPlanType.getPublishedCounterpartId();
    }
    
    public Long getModelId() {
        return wrappedPlanType.getModelId();
    }
    
    public void setModelId(Long modelId) {
        wrappedPlanType.setModelId(modelId);
    }
    
    public void update(ActionEvent e) throws SystemException {
        PlanTypeLocalServiceUtil.updatePlanType(wrappedPlanType);
    }
    
}
