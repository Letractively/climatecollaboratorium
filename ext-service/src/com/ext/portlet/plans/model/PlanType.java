package com.ext.portlet.plans.model;


/**
 * <a href="PlanType.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanType</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.plans.model.impl.PlanTypeImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanTypeModel
 * @see com.ext.portlet.plans.model.impl.PlanTypeImpl
 * @see com.ext.portlet.plans.model.impl.PlanTypeModelImpl
 *
 */
public interface PlanType extends PlanTypeModel {
    public java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> getColumns()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> getAttributes()
        throws com.liferay.portal.SystemException;
}
