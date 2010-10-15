package com.ext.inlinehelp.service.impl;

import java.util.List;

import com.ext.inlinehelp.model.HelpUserSetting;
import com.ext.inlinehelp.service.HelpUserSettingLocalServiceUtil;
import com.ext.inlinehelp.service.base.HelpUserSettingLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;


public class HelpUserSettingLocalServiceImpl
    extends HelpUserSettingLocalServiceBaseImpl {
    
    public boolean isHelpVisible(Long userId, String messageId, boolean defaultOpen) throws SystemException {
        List<HelpUserSetting> userSettingList = this.helpUserSettingPersistence.findByUserIdMessageId(userId, messageId);
        if (userSettingList.size() == 0) {
            return defaultOpen;
        }
        return userSettingList.get(0).getVisible();
    }
    
    public void toggleHelpVisibility(Long userId, String messageId, boolean defaultOpen) throws SystemException {
        List<HelpUserSetting> userSettingList = this.helpUserSettingPersistence.findByUserIdMessageId(userId, messageId);
        HelpUserSetting userSetting;
        if (userSettingList.size() == 0) {
            userSetting = HelpUserSettingLocalServiceUtil.createHelpUserSetting(
                    CounterLocalServiceUtil.increment(HelpUserSetting.class.getName()));
            userSetting.setUserId(userId);
            userSetting.setMessageId(messageId);
            userSetting.setVisible(defaultOpen);
        }
        else {
            userSetting = userSettingList.get(0);
        }
        
        userSetting.setVisible(!userSetting.getVisible().booleanValue());
        
        HelpUserSettingLocalServiceUtil.updateHelpUserSetting(userSetting);
    }
}
