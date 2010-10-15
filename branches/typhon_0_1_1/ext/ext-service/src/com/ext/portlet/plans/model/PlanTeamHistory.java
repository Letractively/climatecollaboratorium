package com.ext.portlet.plans.model;


/**
 * <a href="PlanTeamHistory.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanTeamHistory</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.plans.model.impl.PlanTeamHistoryImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanTeamHistoryModel
 * @see com.ext.portlet.plans.model.impl.PlanTeamHistoryImpl
 * @see com.ext.portlet.plans.model.impl.PlanTeamHistoryModelImpl
 *
 */
public interface PlanTeamHistory extends PlanTeamHistoryModel {
    public void store() throws com.liferay.portal.SystemException;

    public com.liferay.portal.model.User getUser()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem getPlan()
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.SystemException;
}
