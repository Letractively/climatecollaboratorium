/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.debates;

import com.ext.portlet.contests.NoSuchContestException;
import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.ext.portlet.debaterevision.model.DebateCategory;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DebatesUtil {

    public static SimpleDateFormat longformat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");

    private final static String DEBATE_ITEM_URL_ACTIVE_FORMAT = "/web/guest/plans/-/plans/contestId/%d#plans=subview:issues;debate=debateId:%d,itemId:%d";
    private final static String DEBATE_ITEM_URL_PAST_FORMAT = "/web/guest/plans/-/plans/contestId/%d#plans=subview:issues;debate=debateId:%d,itemId:%d";
    
    private final static Log _log = LogFactoryUtil.getLog(DebatesUtil.class);
    public static Object getItemURL(DebateItem item) {

        
        Long debateId = item.getDebateId();
        Long itemId = item.getDebateItemId();

        // check if debate item is part of
        Contest activeContest;
        Long pastContestId = 1L;
        try {
            activeContest = ContestLocalServiceUtil.getContestByActiveFlag(true);
            pastContestId = ContestLocalServiceUtil.getContestByActiveFlag(false).getContestPK();
            Set<Long> debateIds = new HashSet<Long>(activeContest.getDebatesIds());
            if (debateIds.contains(debateId)) {
                return String.format(DEBATE_ITEM_URL_ACTIVE_FORMAT, activeContest.getContestPK(), debateId, itemId);
            }
        } catch (NoSuchContestException e) {
            _log.error("Can't find active contest", e);
        } catch (SystemException e) {
            _log.error("Error when retrieving contest and its debates", e);
        }
        return String.format(DEBATE_ITEM_URL_PAST_FORMAT, pastContestId, debateId, itemId);
    }


    public static Object getCategoryURL(DebateCategory category) {
       return "/web/guest/debates";

    }

    public static String formatDateLong(Date d) {
      return longformat.format(d);
    }

}
