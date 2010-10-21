/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.velocity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.events.ServicePreAction;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.model.Theme;
import com.liferay.portal.service.ThemeLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;

public class EXTServicePreAction extends ServicePreAction {
    private static final String COLLABORATORIUM_THEME_NAME = "Collaboratorium";
    private static final String THEME_TIMESTAMP_ATTRIBUTE = "THEME_TIMESTAMP";

    public void run(HttpServletRequest req, HttpServletResponse res)
    throws ActionException {

        Map<String, Object> vmVariables = new HashMap<String, Object>();
        ThemeDisplay themeDisplay = (ThemeDisplay) req.getAttribute(WebKeys.THEME_DISPLAY);
        
        
        List<Theme> themes = ThemeLocalServiceUtil.getThemes(themeDisplay.getCompanyId());
        
        String themeTimestamp = "";
        
        for (Theme theme: themes) {
            if (theme.getName().equals(COLLABORATORIUM_THEME_NAME)) {
                themeTimestamp = String.valueOf(theme.getTimestamp());
            }
        }
        
        // Setup some variables
        vmVariables.put("themeTimestamp", themeTimestamp);
        req.setAttribute(WebKeys.VM_VARIABLES, vmVariables);
        req.setAttribute(THEME_TIMESTAMP_ATTRIBUTE, themeTimestamp);
//         throw(new ActionException("Test"));
    }
}
