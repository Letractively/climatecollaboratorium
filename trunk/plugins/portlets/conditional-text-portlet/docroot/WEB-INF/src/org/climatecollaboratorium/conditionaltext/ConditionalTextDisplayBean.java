/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.conditionaltext;

import com.ext.conditionaltext.model.ConditionalTextSetting;
import com.ext.conditionaltext.service.ConditionalTextSettingLocalServiceUtil;
import com.liferay.util.bridges.jsf.common.PortletPreferencesManagedBean;

import javax.faces.context.FacesContext;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Sep 10, 2010
 * Time: 5:33:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConditionalTextDisplayBean {

    public String message;
    public String style;
    private PortletPreferencesManagedBean preferences;

    public ConditionalTextDisplayBean() {

        //  FacesContext.getCurrentInstance().getELContext().getVariableMapper().


    }

    public String getMessage() {
        if (message == null) {
            refresh();
        }
        return message;
    }

    public void refresh() {
        Map map = getRawParameters(FacesContext.getCurrentInstance());
        String key = getPreferences().getValue(PreferenceKeys.MSG_KEY,null);
        String value = getPreferences().getValue(PreferenceKeys.MSG_VAL,null);
        if (key != null && value != null) {
            String[] param = (String[]) map.get(key);

            if (param != null && param.length > 0 && value.equals(param[0])) {
                ConditionalTextSetting setting = ConditionalTextSettingLocalServiceUtil.findByKeyVal(key, value);
                message = setting != null ? setting.getHtml() : null;
                style = setting != null ? setting.getStyleClass() : null;
            }
        }
    }

    public String getStyle() {
        return style;
    }


    public static Map getRawParameters(FacesContext context) {
        Map<String, Object> requests = context.getExternalContext().getRequestMap();
        for (String requestName : requests.keySet()) {
            if (requests.get(requestName) instanceof HttpServletRequestWrapper) {
                ServletRequest result = ((HttpServletRequestWrapper) requests.get(requestName)).getRequest();
                return result.getParameterMap();
            }
        }
        return Collections.emptyMap();
    }

    public void setPreferences(PortletPreferencesManagedBean preferences) {
        this.preferences = preferences;
    }

    private PortletPreferences getPreferences() {
        return ((PortletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getPreferences();

    }
}
