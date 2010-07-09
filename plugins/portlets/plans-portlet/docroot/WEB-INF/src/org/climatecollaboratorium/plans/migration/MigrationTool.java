package org.climatecollaboratorium.plans.migration;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.PlanLocalServiceHelper;
import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.model.Plan;
import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanMeta;
import com.ext.portlet.plans.model.PlanModelRun;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class MigrationTool {
    private static Logger _log = Logger.getLogger(MigrationTool.class);

    public String migrate() {

        try {
            int counter = 1;
            int plansCount = PlanLocalServiceUtil.getPlansCount();
            _log.info("Starting migration of plans, plans to process: " + plansCount);
            for (Plan basePlan : PlanLocalServiceUtil.getPlans(0, Integer.MAX_VALUE)) {
                try {
                    String progressIndicator = counter + " of " + plansCount + ": [" + basePlan.getName() + " (" + basePlan.getPlanId() + ")] ";

                    _log.info(progressIndicator + "migration started");
                    boolean alreadyMigrated = false;
                    try {
                        if (PlanItemLocalServiceUtil.getPlan(basePlan.getPlanId()) != null) {
                            alreadyMigrated = true;
                            _log.info(progressIndicator + "already migrated");
                        }
                    }
                    catch (NoSuchPlanItemException e) {
                        // ignore
                    }
                    if (!alreadyMigrated) {
                        PlanItemLocalServiceUtil.createPlan(basePlan);
                    }

                    _log.info(progressIndicator + "migration finished");
                    counter++;
                    FacesMessage fm = new FacesMessage();
                    fm.setSeverity(FacesMessage.SEVERITY_INFO);
                    fm.setSummary("Migration of plan " + basePlan.getName() + " was successful");
                    FacesContext.getCurrentInstance().addMessage(null, fm);
                } catch (Throwable ex) {
                    FacesMessage fm = new FacesMessage();
                    fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                    fm.setSummary("Migration of plan " + basePlan.getName() + " (" + basePlan.getPlanId()
                            + ") has failed");
                    fm.setDetail(ex.getMessage());
                    FacesContext.getCurrentInstance().addMessage(null, fm);
                    _log.error("An exception was thrown when migrating plan " + basePlan.getPlanId() + " "
                            + basePlan.getName(), ex);

                }
            }
        } catch (Throwable ex) {
            FacesMessage fm = new FacesMessage();
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            fm.setSummary("Migration has failed");
            fm.setDetail(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
            _log.error("Migration failed" + ex.getMessage(), ex);
            return "";
        }
        _log.info("Migration successful");
        FacesMessage fm = new FacesMessage();
        fm.setSummary("Settings saved successfully");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);

        FacesContext.getCurrentInstance().addMessage(null, fm);

        return "";
    }

}
