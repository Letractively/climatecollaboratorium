/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.facelets.debates.support;

import java.util.Date;
import java.util.List;

import org.climatecollaboratorium.facelets.debates.backing.Helper;

import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.model.DebateItemReference;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;

/**
 * @author: jintrone
 * @date: Mar 23, 2010
 */
public class DebateItemWrapper {
    private DebateItem item;


   public DebateItemWrapper(DebateItem item) {
       this.item = item;
   }



    public String getDebateDetail() {
        return Helper.filterContent(item.getDebateDetail());
    }

    public String getDebateSummary() {
        return item.getDebateSummary();
    }

    public User getAuthor() throws SystemException, PortalException {
        return item.getAuthor();
    }

    public Date getUpdated() {
        return item.getUpdated();
    }

    public DebateItem getItem() {
        return item;
    }

    public String getDebatePostType() {
        return item.getDebatePostType();
    }

    public Long getDebateId() {
        return item.getDebateId();
    }

    public Long getDebateItemId() {
        return item.getDebateItemId();
    }
    
    public List<DebateItemReference> getReferences() throws SystemException, PortalException  {
        return item.getReferences();
    }
    public DebateItem getParent() throws SystemException, PortalException {
        return item.getParent();
    }
}