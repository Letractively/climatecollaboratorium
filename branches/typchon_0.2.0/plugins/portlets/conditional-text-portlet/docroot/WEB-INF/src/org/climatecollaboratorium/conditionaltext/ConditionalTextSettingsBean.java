/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.conditionaltext;

import com.ext.conditionaltext.model.ConditionalTextSetting;
import com.ext.conditionaltext.service.ConditionalTextSettingLocalServiceUtil;
import com.liferay.portal.SystemException;

import com.liferay.util.bridges.jsf.common.PortletPreferencesManagedBean;

import javax.faces.context.FacesContext;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Sep 11, 2010
 * Time: 12:00:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConditionalTextSettingsBean {


    private AddSettingBean addSetting = new AddSettingBean();

    private List<ConditionalTextSettingWrapper> existing = new ArrayList<ConditionalTextSettingWrapper>();

    public ConditionalTextSettingsBean() throws SystemException {
       refresh(); 
    }


    public void setPortletKey(String key) throws ReadOnlyException {
        getPreferences().setValue(PreferenceKeys.MSG_KEY,key);
    }

    public void setPortletValue(String val) throws ReadOnlyException {
        getPreferences().setValue(PreferenceKeys.MSG_VAL,val);


    }

    public String getPortletKey() {
        return getPreferences().getValue(PreferenceKeys.MSG_KEY,null);

    }

    public String getPortletValue() {
        return getPreferences().getValue(PreferenceKeys.MSG_VAL,null);
    }

    public void refresh() throws SystemException {
        existing.clear();
        for (ConditionalTextSetting setting:ConditionalTextSettingLocalServiceUtil.getConditionalTextSettings(0,Integer.MAX_VALUE)) {
           existing.add(new ConditionalTextSettingWrapper(setting)); 
        }
    }

    public void update() throws SystemException, ValidatorException, IOException {
        for (ConditionalTextSettingWrapper wrapper:existing) {
            wrapper.update();
        }
        getPreferences().store();
    }

    public void add() throws SystemException {
        addSetting.add();
        refresh();
    }

    public AddSettingBean getAddSettingBean() {
       return addSetting; 
    }

    private PortletPreferences getPreferences() {
        return ((PortletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getPreferences();

    }


    public List<ConditionalTextSettingWrapper> getSettings() throws SystemException {
       return existing;
    }



}
