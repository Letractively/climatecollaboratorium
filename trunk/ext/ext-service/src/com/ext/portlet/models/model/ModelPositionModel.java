package com.ext.portlet.models.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="ModelPositionModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ModelPosition</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelPosition
 * @see com.ext.portlet.models.model.impl.ModelPositionImpl
 * @see com.ext.portlet.models.model.impl.ModelPositionModelImpl
 *
 */
public interface ModelPositionModel extends BaseModel<ModelPosition> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getId();

    public void setId(Long id);

    public Long getPositionId();

    public void setPositionId(Long positionId);

    public Long getModelId();

    public void setModelId(Long modelId);

    public ModelPosition toEscapedModel();
}