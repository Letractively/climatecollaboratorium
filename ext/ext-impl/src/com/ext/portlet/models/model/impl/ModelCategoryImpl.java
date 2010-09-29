/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.model.impl;

import com.ext.portlet.models.model.ModelCategory;
import com.ext.portlet.models.model.ModelGlobalPreference;
import com.ext.portlet.models.service.ModelGlobalPreferenceLocalServiceUtil;
import com.liferay.portal.SystemException;

import java.util.List;


public class ModelCategoryImpl extends ModelCategoryModelImpl
    implements ModelCategory {

    public ModelCategoryImpl() {


    }


    public List<ModelGlobalPreference> getModelPreferences() throws SystemException {
        return ModelGlobalPreferenceLocalServiceUtil.findByCategory(this);

    }
}