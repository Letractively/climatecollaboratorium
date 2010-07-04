package com.ext.portlet.plans.model;


/**
 * <a href="PlanMeta.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanMeta</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.plans.model.impl.PlanMetaImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanMetaModel
 * @see com.ext.portlet.plans.model.impl.PlanMetaImpl
 * @see com.ext.portlet.plans.model.impl.PlanMetaModelImpl
 *
 */
public interface PlanMeta extends PlanMetaModel {
    public void store() throws com.liferay.portal.SystemException;

    public void vote() throws com.liferay.portal.SystemException;

    public void unvote() throws com.liferay.portal.SystemException;
}
