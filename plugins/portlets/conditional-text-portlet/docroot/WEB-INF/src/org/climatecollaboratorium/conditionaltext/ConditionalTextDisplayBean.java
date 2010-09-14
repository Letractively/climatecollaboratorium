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

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.navigation.NavigationEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    private String value;
    private PortletPreferencesManagedBean preferences;
    private List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();
    private EventBus eventBus;
    private final static String CONDITIONAL_TEXT_SOURCE_NAME = "condText";
    private final static String CONDITIONAL_TEXT_VAL_PARAM = "value";

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
        String key = getPreferences().getValue(PreferenceKeys.MSG_KEY,null);
        if (key != null) {

            if (value != null) {
                ConditionalTextSetting setting = ConditionalTextSettingLocalServiceUtil.findByKeyVal(key, value);
                message = setting != null ? setting.getHtml() : null;
                style = setting != null ? setting.getStyleClass() : null;
            }
            else {
                message = null;
                style = null; 
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

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        bind();
    }
    
    private void bind() {
        for (HandlerRegistration reg: handlerRegistrations) {
            reg.unregister();
        }
        
        handlerRegistrations.clear();
        handlerRegistrations.add(eventBus.registerHandler(NavigationEvent.class, new EventHandler<NavigationEvent>() {

            @Override
            public void onEvent(NavigationEvent event) {
                Map<String, String> params = event.getParameters(CONDITIONAL_TEXT_SOURCE_NAME);
                if (params != null) {
                    value = params.get(CONDITIONAL_TEXT_VAL_PARAM);
                    if (value != null) {
                        refresh();
                    }
                }
                else {
                    value = null;
                }
            }
            
        }));
    }
}
