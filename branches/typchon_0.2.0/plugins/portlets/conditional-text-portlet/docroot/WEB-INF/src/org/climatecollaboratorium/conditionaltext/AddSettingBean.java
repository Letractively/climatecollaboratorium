/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.conditionaltext;

import com.ext.conditionaltext.service.ConditionalTextSettingLocalServiceUtil;
import com.liferay.portal.SystemException;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Sep 11, 2010
 * Time: 12:41:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddSettingBean {


    private String style;
    private String text;
    private String key;
    private String value;

    public void add() throws SystemException {
        if (style!=null && style.trim().length()!=0 &&
            text!=null && text.trim().length()!=0 &&
            key!=null && key.trim().length()!=0 &&
            value!=null && value.trim().length()!=0) {
        ConditionalTextSettingLocalServiceUtil.updateSetting(key,value,style,text);
        }
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
