/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="ModelCategoryModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ModelCategory</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelCategory
 * @see com.ext.portlet.models.model.impl.ModelCategoryImpl
 * @see com.ext.portlet.models.model.impl.ModelCategoryModelImpl
 *
 */
public interface ModelCategoryModel extends BaseModel<ModelCategory> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getModelCategoryPK();

    public void setModelCategoryPK(Long modelCategoryPK);

    public String getModelCategoryName();

    public void setModelCategoryName(String modelCategoryName);

    public String getModelCategoryDescription();

    public void setModelCategoryDescription(String modelCategoryDescription);

    public Integer getModelCategoryDisplayWeight();

    public void setModelCategoryDisplayWeight(
        Integer modelCategoryDisplayWeight);

    public ModelCategory toEscapedModel();
}
