/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.debaterevision.service.impl;

import com.ext.portlet.debaterevision.DebateItemStatus;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.model.DebateItemReference;
import com.ext.portlet.debaterevision.service.base.DebateItemReferenceLocalServiceBaseImpl;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.SystemException;

import java.util.List;


public class DebateItemReferenceLocalServiceImpl
    extends DebateItemReferenceLocalServiceBaseImpl {


    public DebateItemReference createNew(String text, String url) throws SystemException {
        Long pk = CounterUtil.increment(DebateItemReference.class.getName());
        DebateItemReference result = debateItemReferenceLocalService.createDebateItemReference(pk);
         result.setText(text);
        result.setUrl(url);
        return result;
    }

    public DebateItemReference createNewFrom(DebateItem item, DebateItemReference template) throws SystemException {
        Long pk = CounterUtil.increment(DebateItemReference.class.getName());
        DebateItemReference result = debateItemReferenceLocalService.createDebateItemReference(pk);
        result.setStatus(DebateItemStatus.ACTIVE.name());
        result.setText(template.getText());
        result.setUrl(template.getUrl());
        result.setDebateId(item.getDebateId());
        result.setDebateItemId(item.getDebateItemId());
        result.setItemVersion(item.getItemVersion());
        updateDebateItemReference(result);
        return result;
    }
    
    public List<DebateItemReference> getDebateItemReferences(DebateItem item) throws SystemException {
        return this.debateItemReferencePersistence.findByDebateItemIdItemVersionStatus(
                item.getDebateItemId(), item.getItemVersion(), DebateItemStatus.ACTIVE.name());
    }

}
