/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.service.impl;

import com.ext.portlet.models.model.ModelCategory;
import com.ext.portlet.models.service.base.ModelCategoryLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;


public class ModelCategoryLocalServiceImpl
    extends ModelCategoryLocalServiceBaseImpl {

    public ModelCategory addCategory(String name,String description) throws SystemException {
        Long pk = CounterLocalServiceUtil.increment(ModelCategory.class.getName());
        ModelCategory cat = createModelCategory(pk);
        cat.setModelCategoryName(name);
        cat.setModelCategoryDescription(description);
        cat.setModelCategoryDisplayWeight(0);
        addModelCategory(cat);
        return cat;
    }

}
