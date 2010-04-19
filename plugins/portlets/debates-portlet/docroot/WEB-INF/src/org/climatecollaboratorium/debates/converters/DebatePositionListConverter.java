/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.debates.converters;

import com.ext.portlet.debaterevision.model.DebateItem;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.Collections;
import java.util.List;

/**
 * @author: jintrone
 * @date: Apr 1, 2010
 */
public class DebatePositionListConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        //not bothering with the conversion back
        return Collections.<DebateItem>emptyList();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        StringBuffer result = new StringBuffer();

        if (!(o instanceof List)) {
            return o==null?"":o.toString();
        } else {
            int idx = 0;
            for (Object item:(List)o) {
                 if (idx++ > 0) result.append(", ");
                if (!(item instanceof DebateItem)) {
                    result.append(o.toString());
                    continue;
                }
                DebateItem di = (DebateItem)item;

                result.append(di.getDebateSummary());
                try {
                    long vcount = di.getVotesCount();
                    if (vcount == 0) {
                        return "No votes.";
                    }
                    result.append(" (").append(vcount).append(" vote");
                    if (vcount>1) result.append("s");
                    result.append(")");
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            }
        }
        return result.toString();

    }
}
