package com.ext.portlet.models.model;


/**
 * <a href="ModelInputItem.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ModelInputItem</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.models.model.impl.ModelInputItemImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelInputItemModel
 * @see com.ext.portlet.models.model.impl.ModelInputItemImpl
 * @see com.ext.portlet.models.model.impl.ModelInputItemModelImpl
 *
 */
public interface ModelInputItem extends ModelInputItemModel {
    public edu.mit.cci.simulation.client.MetaData getMetaData()
        throws com.liferay.portal.SystemException, java.io.IOException;

    public edu.mit.cci.simulation.client.Simulation getModel()
        throws com.liferay.portal.SystemException, java.io.IOException;

    public java.util.Map<String, String> getPropertyMap();

    public void saveProperties(java.util.Map<String, String> props)
        throws com.liferay.portal.SystemException;

    public void store() throws com.liferay.portal.SystemException;
}
