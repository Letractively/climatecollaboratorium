package org.climatecollaboratorium.plans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.ext.portlet.plans.PlanConstants.Columns;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlansUserSettings;
import com.ext.portlet.plans.service.PlansUserSettingsLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class ConfigureColumnsBean {
    private PlansIndexBean plansIndexBean;
    private List<ColumnsBean> availableColumns = new ArrayList<ColumnsBean>();

    public ConfigureColumnsBean(PlansIndexBean plansIndexBean) throws SystemException, PortalException {
        this.plansIndexBean = plansIndexBean;
        PlanType planType = plansIndexBean.getPlanType();
        
        for (Columns col: Columns.getPlanTypeColumns(planType)) {
            availableColumns.add(new ColumnsBean(col, plansIndexBean));
        }
    }
    
    public List<ColumnsBean> getColumns() {
        return availableColumns;
    }
    
    public void update(ActionEvent e) throws PortalException, SystemException {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        PlansUserSettings plansUserSettings = PlansUserSettingsLocalServiceUtil.getPlanUserSettings(ectx.getSessionMap(), ectx.getRequestMap(), plansIndexBean.getPlanType());
        for (ColumnsBean colWrapper: availableColumns) {
            System.out.println(colWrapper.getName() + ": " + colWrapper.getVisible());
            colWrapper.getWrapped().setUserSetting(plansUserSettings, colWrapper.getVisible());
        }
        PlansUserSettingsLocalServiceUtil.saveUserSettings(ectx.getSessionMap(), ectx.getRequestMap(), plansUserSettings);
        plansIndexBean.refresh();
    }

}
