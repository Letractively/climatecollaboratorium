package com.ext.conditionaltext.service.impl;

import com.ext.conditionaltext.NoSuchConditionalTextSettingException;
import com.ext.conditionaltext.model.ConditionalTextSetting;
import com.ext.conditionaltext.service.base.ConditionalTextSettingLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConditionalTextSettingLocalServiceImpl
    extends ConditionalTextSettingLocalServiceBaseImpl {



    public List<ConditionalTextSetting> findByKey(String key) {
        try {
           List<ConditionalTextSetting> vals =  conditionalTextSettingPersistence.findByparamKey(key);
           return vals;

        } catch (SystemException e) {
            return Collections.emptyList();
        }
    }

    public ConditionalTextSetting findByKeyVal(String key, String val) {
        try {
            return conditionalTextSettingPersistence.findByparamKeyParamValue(key,val);
        } catch (SystemException e) {
            return null;
        } catch (NoSuchConditionalTextSettingException e) {
            return null;
        }
    }

    public void updateSetting(String key, String val, String style,String text) throws SystemException {
        ConditionalTextSetting existing = findByKeyVal(key,val);
        if (existing!=null) {
            existing.setParamKey(key);
            existing.setParamValue(val);
            existing.setStyleClass(style);
            existing.setHtml(text);
        } else {
            long pk = CounterLocalServiceUtil.increment(ConditionalTextSetting.class.getName());
            ConditionalTextSetting setting = createConditionalTextSetting(pk);
            setting.setParamKey(key);
            setting.setParamValue(val);
            setting.setStyleClass(style);
            setting.setHtml(text);
            addConditionalTextSetting(setting);
        }
    }


}
