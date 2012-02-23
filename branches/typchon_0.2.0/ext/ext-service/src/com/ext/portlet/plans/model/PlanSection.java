package com.ext.portlet.plans.model;


/**
 * <a href="PlanSection.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanSection</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.plans.model.impl.PlanSectionImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanSectionModel
 * @see com.ext.portlet.plans.model.impl.PlanSectionImpl
 * @see com.ext.portlet.plans.model.impl.PlanSectionModelImpl
 *
 */
public interface PlanSection extends PlanSectionModel {
    public void store() throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSectionDefinition getDefinition()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;
}
