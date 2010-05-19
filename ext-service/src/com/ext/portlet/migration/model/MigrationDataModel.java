/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="MigrationDataModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>MigrationData</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.migration.model.MigrationData
 * @see com.ext.portlet.migration.model.impl.MigrationDataImpl
 * @see com.ext.portlet.migration.model.impl.MigrationDataModelImpl
 *
 */
public interface MigrationDataModel extends BaseModel<MigrationData> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getMigrationId();

    public void setMigrationId(Long migrationId);

    public String getName();

    public void setName(String name);

    public String getXml();

    public void setXml(String xml);

    public String getDescription();

    public void setDescription(String description);

    public Integer getUsers();

    public void setUsers(Integer users);

    public Integer getPlans();

    public void setPlans(Integer plans);

    public Integer getQuestions();

    public void setQuestions(Integer questions);

    public Integer getAlternatives();

    public void setAlternatives(Integer alternatives);

    public Integer getArguments();

    public void setArguments(Integer arguments);

    public Integer getVotes();

    public void setVotes(Integer votes);

    public Long getUserId();

    public void setUserId(Long userId);

    public Date getModifiedDate();

    public void setModifiedDate(Date modifiedDate);

    public Date getCreatedDate();

    public void setCreatedDate(Date createdDate);

    public MigrationData toEscapedModel();
}
