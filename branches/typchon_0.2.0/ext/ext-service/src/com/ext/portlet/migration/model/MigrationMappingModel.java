/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.model;

import com.ext.portlet.migration.service.persistence.MigrationMappingPK;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="MigrationMappingModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>MigrationMapping</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.migration.model.MigrationMapping
 * @see com.ext.portlet.migration.model.impl.MigrationMappingImpl
 * @see com.ext.portlet.migration.model.impl.MigrationMappingModelImpl
 *
 */
public interface MigrationMappingModel extends BaseModel<MigrationMapping> {
    public MigrationMappingPK getPrimaryKey();

    public void setPrimaryKey(MigrationMappingPK pk);

    public String getEntityName();

    public void setEntityName(String entityName);

    public String getOldId();

    public void setOldId(String oldId);

    public Long getNewId();

    public void setNewId(Long newId);

    public Long getUserId();

    public void setUserId(Long userId);

    public Date getCreateDate();

    public void setCreateDate(Date createDate);

    public Date getModifiedDate();

    public void setModifiedDate(Date modifiedDate);

    public MigrationMapping toEscapedModel();
}
