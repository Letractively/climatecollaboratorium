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
    private PlansUserSettings plansUserSettings;

    public ConfigureColumnsBean(PlansIndexBean plansIndexBean) throws SystemException, PortalException {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        this.plansIndexBean = plansIndexBean;
        PlanType planType = plansIndexBean.getPlanType();
        
        for (Columns col: Columns.getPlanTypeColumns(planType)) {
            availableColumns.add(new ColumnsBean(col, plansIndexBean));
        }

        plansUserSettings = PlansUserSettingsLocalServiceUtil.getPlanUserSettings(ectx.getSessionMap(), ectx.getRequestMap(), plansIndexBean.getPlanType());
    }
    
    public List<ColumnsBean> getColumns() {
        return availableColumns;
    }
    
    public void update(ActionEvent e) throws PortalException, SystemException {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        for (ColumnsBean colWrapper: availableColumns) {
            System.out.println(colWrapper.getName() + "\t: " + colWrapper.getVisible() + "\told: " + colWrapper.getWrapped().getUserSetting(plansUserSettings));
            colWrapper.getWrapped().setUserSetting(plansUserSettings, colWrapper.getVisible());
            System.out.println(colWrapper.getName() + "\t: " + colWrapper.getVisible() + "\tnew: " + colWrapper.getWrapped().getUserSetting(plansUserSettings));
        }
        PlansUserSettingsLocalServiceUtil.saveUserSettings(ectx.getSessionMap(), ectx.getRequestMap(), plansUserSettings);
        plansIndexBean.refresh();
    }

}
