/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.model;


/**
 * <a href="ModelCategory.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ModelCategory</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.models.model.impl.ModelCategoryImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelCategoryModel
 * @see com.ext.portlet.models.model.impl.ModelCategoryImpl
 * @see com.ext.portlet.models.model.impl.ModelCategoryModelImpl
 *
 */
public interface ModelCategory extends ModelCategoryModel {
    public java.util.List<com.ext.portlet.models.model.ModelGlobalPreference> getModelPreferences()
        throws com.liferay.portal.SystemException;
}
