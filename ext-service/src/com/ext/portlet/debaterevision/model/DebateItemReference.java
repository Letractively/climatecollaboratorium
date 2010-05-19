package com.ext.portlet.debaterevision.model;


/**
 * <a href="DebateItemReference.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DebateItemReference</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.debaterevision.model.impl.DebateItemReferenceImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.model.DebateItemReferenceModel
 * @see com.ext.portlet.debaterevision.model.impl.DebateItemReferenceImpl
 * @see com.ext.portlet.debaterevision.model.impl.DebateItemReferenceModelImpl
 *
 */
public interface DebateItemReference extends DebateItemReferenceModel {
    public void moveForward(java.lang.Long version)
        throws com.liferay.portal.SystemException;
}
