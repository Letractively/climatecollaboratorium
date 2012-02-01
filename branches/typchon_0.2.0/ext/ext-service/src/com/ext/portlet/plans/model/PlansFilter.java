package com.ext.portlet.plans.model;


/**
 * <a href="PlansFilter.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlansFilter</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.plans.model.impl.PlansFilterImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlansFilterModel
 * @see com.ext.portlet.plans.model.impl.PlansFilterImpl
 * @see com.ext.portlet.plans.model.impl.PlansFilterModelImpl
 *
 */
public interface PlansFilter extends PlansFilterModel {
    public java.util.List<Long> getPositionsIds();

    public void setPositionsIds(java.util.List<Long> positionsIds);
}
