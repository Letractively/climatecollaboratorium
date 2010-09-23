/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.model;

import com.ext.portlet.models.service.persistence.ModelsFilterPositionPK;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="ModelsFilterPositionModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ModelsFilterPosition</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelsFilterPosition
 * @see com.ext.portlet.models.model.impl.ModelsFilterPositionImpl
 * @see com.ext.portlet.models.model.impl.ModelsFilterPositionModelImpl
 *
 */
public interface ModelsFilterPositionModel extends BaseModel<ModelsFilterPosition> {
    public ModelsFilterPositionPK getPrimaryKey();

    public void setPrimaryKey(ModelsFilterPositionPK pk);

    public Long getUserId();

    public void setUserId(Long userId);

    public Boolean getPublished();

    public void setPublished(Boolean published);

    public Long getPositionId();

    public void setPositionId(Long positionId);

    public ModelsFilterPosition toEscapedModel();
}