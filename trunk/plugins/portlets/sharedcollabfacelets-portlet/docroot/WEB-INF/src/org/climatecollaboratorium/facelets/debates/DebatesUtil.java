/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.facelets.debates;

import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateCategory;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.service.DebateItemLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.compass.core.util.backport.java.util.Collections;

public class DebatesUtil {

    public static SimpleDateFormat longformat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");

    public static Object getItemURL(DebateItem item) {
        return "/web/guest/debates#debate=" + item.getDebateId() + ";item=" + item.getDebateItemId();
    }


    public static Object getCategoryURL(DebateCategory category) {
       return "/web/guest/debates";

    }

    public static String formatDateLong(Date d) {
      return longformat.format(d);
    }
    
    
    public static Long[] getIdsOnDebatePath(DebateItem item) throws PortalException, SystemException {            
        DebateItem currentItem = item;
        Debate debate = item.getDebate();
        ArrayList<Long> itemsIds = new ArrayList<Long>();
    
        itemsIds.add(currentItem.getDebateItemId());
    
        while (! currentItem.getDebateItemId().equals(debate.getCurrentRoot().getDebateItemId())) {
            currentItem = currentItem.getParent();
            itemsIds.add(currentItem.getDebateItemId());
        }
        Collections.reverse(itemsIds);
        
        return itemsIds.toArray(new Long[] {});
    }
    
    public static String getActivityExtraData(DebateItem item) throws PortalException, SystemException {
        return ActivityUtil.getExtraDataForIds(getIdsOnDebatePath(item));
    }
    
    public static DebateItem getlastDebateItemFromExtraData(String extraData) throws PortalException, SystemException {
        Long[] ids = ActivityUtil.getIdsFromExtraData(extraData);
        return DebateItemLocalServiceUtil.getLastActiveItem(ids[ids.length-1]);
    }

}
