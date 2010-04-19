/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.debaterevision.service.impl;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.service.base.DebateCategoryLocalServiceBaseImpl;
import com.ext.portlet.debaterevision.service.persistence.DebateCategoryFinderUtil;

import java.util.List;


public class DebateCategoryLocalServiceImpl
    extends DebateCategoryLocalServiceBaseImpl {

    public List<Debate> getDebates(long categoryId) {
        return DebateCategoryFinderUtil.getCategoryDebates(categoryId);
    }
}
