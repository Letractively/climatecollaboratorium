/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.debaterevision.service.impl;

import com.ext.portlet.debaterevision.model.DebateCategoryMap;
import com.ext.portlet.debaterevision.service.base.DebateCategoryMapLocalServiceBaseImpl;
import com.liferay.portal.SystemException;


public class DebateCategoryMapLocalServiceImpl
    extends DebateCategoryMapLocalServiceBaseImpl {

    public DebateCategoryMap getMapping(Long categoryPK, Long debateId) throws SystemException {
        return debateCategoryMapPersistence.fetchBydebateIdCategoryId(debateId,categoryPK);    
    }
}
