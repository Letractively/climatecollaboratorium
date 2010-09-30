/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.debaterevision.model.impl;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateCategory;
import com.ext.portlet.debaterevision.model.DebateCategoryMap;
import com.ext.portlet.debaterevision.service.DebateCategoryLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateCategoryMapLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;

import java.util.List;


public class DebateCategoryImpl extends DebateCategoryModelImpl
    implements DebateCategory {
    public DebateCategoryImpl() {
    }

    public void addDebate(long debateId) throws SystemException {
        long pk = CounterLocalServiceUtil.increment(DebateCategoryMap.class.getName());
        DebateCategoryMap relation = DebateCategoryMapLocalServiceUtil.createDebateCategoryMap(pk);
        relation.setDebateCategoryPK(this.getDebateCategoryPK());
        relation.setDebateId(debateId);
        DebateCategoryMapLocalServiceUtil.updateDebateCategoryMap(relation);
    }

    public List<Debate> getDebates() throws SystemException {
        return DebateCategoryLocalServiceUtil.getDebates(this.getDebateCategoryPK());
    }

    public void removeDebate(long debateId) throws SystemException {
        DebateCategoryMap map = DebateCategoryMapLocalServiceUtil.getMapping(debateId,this.getDebateCategoryPK());
        DebateCategoryMapLocalServiceUtil.deleteDebateCategoryMap(map);
    }
}
