/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="ModelDiscussionModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ModelDiscussion</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelDiscussion
 * @see com.ext.portlet.models.model.impl.ModelDiscussionImpl
 * @see com.ext.portlet.models.model.impl.ModelDiscussionModelImpl
 *
 */
public interface ModelDiscussionModel extends BaseModel<ModelDiscussion> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getModelDiscussionId();

    public void setModelDiscussionId(Long modelDiscussionId);

    public Long getModelId();

    public void setModelId(Long modelId);

    public Long getCategoryId();

    public void setCategoryId(Long categoryId);

    public ModelDiscussion toEscapedModel();
}
