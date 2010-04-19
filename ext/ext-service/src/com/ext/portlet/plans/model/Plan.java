package com.ext.portlet.plans.model;


/**
 * <a href="Plan.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>Plan</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.plans.model.impl.PlanImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanModel
 * @see com.ext.portlet.plans.model.impl.PlanImpl
 * @see com.ext.portlet.plans.model.impl.PlanModelImpl
 *
 */
public interface Plan extends PlanModel {
    public java.util.List<com.liferay.portlet.messageboards.model.MBMessage> getPositions();

    public void setPositions(
        java.util.List<com.liferay.portlet.messageboards.model.MBMessage> positions);

    public com.ext.portlet.plans.model.PlanType getPlanType()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;
}
