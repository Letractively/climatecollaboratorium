/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.conditionaltext;

import com.ext.conditionaltext.model.ConditionalTextSetting;
import com.ext.conditionaltext.service.ConditionalTextSettingLocalServiceUtil;
import com.liferay.portal.SystemException;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Sep 11, 2010
 * Time: 12:28:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConditionalTextSettingWrapper  {

    boolean dirty = false;
     private ConditionalTextSetting delegate;

    public ConditionalTextSettingWrapper(ConditionalTextSetting setting) {
        this.delegate = setting;
    }


    public String getStyleClass() {
        return delegate.getStyleClass();
    }

    public void setStyleClass(String styleClass) {
        dirty = (!styleClass.equals(getStyleClass()));
        delegate.setStyleClass(styleClass);
    }

    public String getParamKey() {
        return delegate.getParamKey();
    }

    public void setParamKey(String paramKey) {
        dirty = (!paramKey.equals(getParamKey()));
        delegate.setParamKey(paramKey);
    }

    public String getParamValue() {
        return delegate.getParamValue();
    }

    public void setParamValue(String paramValue) {
        dirty = (!paramValue.equals(getParamValue()));
        delegate.setParamValue(paramValue);
    }

    public String getHtml() {
        return delegate.getHtml();
    }

    public void setHtml(String text) {
        dirty = (!text.equals(getHtml()));
        delegate.setHtml(text);
    }

    public void update() throws SystemException {
        ConditionalTextSettingLocalServiceUtil.updateConditionalTextSetting(delegate);
        
    }




}
