package com.ext.inlinehelp.service;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletSession;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public class HelpUserSettingHelper {
    private static final String HELP_USER_SETTINGS_SESSION_KEY = "HELP_USER_SETTINGS";

    public static boolean getHelpUserSetting(Long userId, String messageId, PortletSession session, boolean defaultOpen) throws PortalException,
            SystemException {
        Map<String, Boolean> helpUserSettings = getHelpUserSettingsFromSession(session);
        if (!helpUserSettings.containsKey(messageId)) {
            User user = UserLocalServiceUtil.getUser(userId);
            if (user.isDefaultUser()) {
                helpUserSettings.put(messageId, defaultOpen);
            }
            if (!user.isDefaultUser()) {
                helpUserSettings.put(messageId, HelpUserSettingLocalServiceUtil.isHelpVisible(userId, messageId,defaultOpen));
            }
        }

        return helpUserSettings.get(messageId);
    }
    
    public static void toogleHelpUserSetting(Long userId, String messageId, PortletSession session,boolean defaultOpen) throws PortalException, SystemException {
        Map<String, Boolean> helpUserSettings = getHelpUserSettingsFromSession(session);
        User user = UserLocalServiceUtil.getUser(userId);
        if (!helpUserSettings.containsKey(messageId)) {
            helpUserSettings.put(messageId, defaultOpen);
        }
        
        helpUserSettings.put(messageId, ! helpUserSettings.get(messageId).booleanValue());

        if (! user.isDefaultUser()) {
            HelpUserSettingLocalServiceUtil.toggleHelpVisibility(userId, messageId,defaultOpen);
        }
    }

    private static Map<String, Boolean> getHelpUserSettingsFromSession(PortletSession session) {
        if (session.getAttribute(HELP_USER_SETTINGS_SESSION_KEY) != null) {
            return (Map<String, Boolean>) session.getAttribute(HELP_USER_SETTINGS_SESSION_KEY);
        }
        Map<String, Boolean> helpUserSettings = new HashMap<String, Boolean>();
        session.setAttribute(HELP_USER_SETTINGS_SESSION_KEY, helpUserSettings);
        return helpUserSettings;
    }

}
