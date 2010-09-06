/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.facelets.debates;

import com.ext.portlet.debaterevision.model.DebateCategory;
import com.ext.portlet.debaterevision.model.DebateItem;

import java.text.SimpleDateFormat;
import java.util.Date;

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

}
