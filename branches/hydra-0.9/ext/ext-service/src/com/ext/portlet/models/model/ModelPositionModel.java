package com.ext.portlet.models.model;

import com.ext.portlet.models.service.persistence.ModelPositionPK;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


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
    public ModelPositionPK getPrimaryKey();

    public void setPrimaryKey(ModelPositionPK pk);

    public Long getModelId();

    public void setModelId(Long modelId);

    public Long getPositionId();

    public void setPositionId(Long positionId);

    public Long getUserId();

    public void setUserId(Long userId);

    public String getUserName();

    public void setUserName(String userName);

    public Date getCreateDate();

    public void setCreateDate(Date createDate);

    public Date getModifiedDate();

    public void setModifiedDate(Date modifiedDate);

    public ModelPosition toEscapedModel();
}
