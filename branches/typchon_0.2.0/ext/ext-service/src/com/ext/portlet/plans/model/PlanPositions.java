package com.ext.portlet.plans.model;


/**
 * <a href="PlanPositions.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanPositions</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.plans.model.impl.PlanPositionsImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanPositionsModel
 * @see com.ext.portlet.plans.model.impl.PlanPositionsImpl
 * @see com.ext.portlet.plans.model.impl.PlanPositionsModelImpl
 *
 */
public interface PlanPositions extends PlanPositionsModel {
    public java.util.List<Long> getPositionsIds()
        throws com.liferay.portal.SystemException;

    public void store() throws com.liferay.portal.SystemException;

    public void setPositionsIds(java.util.List<Long> positionsIds)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.liferay.portal.model.User getUpdateAuthor()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;
}
