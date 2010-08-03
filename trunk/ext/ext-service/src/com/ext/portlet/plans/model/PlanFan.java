package com.ext.portlet.plans.model;


/**
 * <a href="PlanFan.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanFan</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.plans.model.impl.PlanFanImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanFanModel
 * @see com.ext.portlet.plans.model.impl.PlanFanImpl
 * @see com.ext.portlet.plans.model.impl.PlanFanModelImpl
 *
 */
public interface PlanFan extends PlanFanModel {
    public void store() throws com.liferay.portal.SystemException;

    public com.liferay.portal.model.User getUser()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem getPlan()
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.SystemException;
}
