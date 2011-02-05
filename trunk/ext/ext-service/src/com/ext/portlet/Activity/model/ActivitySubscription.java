package com.ext.portlet.Activity.model;


/**
 * <a href="ActivitySubscription.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ActivitySubscription</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.Activity.model.impl.ActivitySubscriptionImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.Activity.model.ActivitySubscriptionModel
 * @see com.ext.portlet.Activity.model.impl.ActivitySubscriptionImpl
 * @see com.ext.portlet.Activity.model.impl.ActivitySubscriptionModelImpl
 *
 */
public interface ActivitySubscription extends ActivitySubscriptionModel {
    public void store() throws com.liferay.portal.SystemException;

    public com.ext.portlet.Activity.ICollabActivityInterpreter getInterpreter();

    public java.lang.String getName();

    public com.ext.portlet.Activity.SubscriptionType getSubscriptionType();

    public void delete() throws com.liferay.portal.SystemException;
}
