package com.ext.portlet.models.model;


/**
 * <a href="ModelInputGroup.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ModelInputGroup</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.models.model.impl.ModelInputGroupImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelInputGroupModel
 * @see com.ext.portlet.models.model.impl.ModelInputGroupImpl
 * @see com.ext.portlet.models.model.impl.ModelInputGroupModelImpl
 *
 */
public interface ModelInputGroup extends ModelInputGroupModel {
    public java.util.List<com.ext.portlet.models.model.ModelInputItem> getInputItems();

    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> getChildGroups();

    public com.ext.portlet.models.model.ModelInputGroup getParent();

    public mit.simulation.climate.client.Simulation getModel()
        throws com.liferay.portal.SystemException;

    public mit.simulation.climate.client.MetaData getMetaData()
        throws com.liferay.portal.SystemException;
}
