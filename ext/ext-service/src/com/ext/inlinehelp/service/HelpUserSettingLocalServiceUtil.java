package com.ext.inlinehelp.service;


/**
 * <a href="HelpUserSettingLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.inlinehelp.service.HelpUserSettingLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.inlinehelp.service.HelpUserSettingLocalService
 *
 */
public class HelpUserSettingLocalServiceUtil {
    private static HelpUserSettingLocalService _service;

    public static com.ext.inlinehelp.model.HelpUserSetting addHelpUserSetting(
        com.ext.inlinehelp.model.HelpUserSetting helpUserSetting)
        throws com.liferay.portal.SystemException {
        return getService().addHelpUserSetting(helpUserSetting);
    }

    public static com.ext.inlinehelp.model.HelpUserSetting createHelpUserSetting(
        java.lang.Long HelpUserSettingId) {
        return getService().createHelpUserSetting(HelpUserSettingId);
    }

    public static void deleteHelpUserSetting(java.lang.Long HelpUserSettingId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteHelpUserSetting(HelpUserSettingId);
    }

    public static void deleteHelpUserSetting(
        com.ext.inlinehelp.model.HelpUserSetting helpUserSetting)
        throws com.liferay.portal.SystemException {
        getService().deleteHelpUserSetting(helpUserSetting);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    public static com.ext.inlinehelp.model.HelpUserSetting getHelpUserSetting(
        java.lang.Long HelpUserSettingId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getHelpUserSetting(HelpUserSettingId);
    }

    public static java.util.List<com.ext.inlinehelp.model.HelpUserSetting> getHelpUserSettings(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getHelpUserSettings(start, end);
    }

    public static int getHelpUserSettingsCount()
        throws com.liferay.portal.SystemException {
        return getService().getHelpUserSettingsCount();
    }

    public static com.ext.inlinehelp.model.HelpUserSetting updateHelpUserSetting(
        com.ext.inlinehelp.model.HelpUserSetting helpUserSetting)
        throws com.liferay.portal.SystemException {
        return getService().updateHelpUserSetting(helpUserSetting);
    }

    public static com.ext.inlinehelp.model.HelpUserSetting updateHelpUserSetting(
        com.ext.inlinehelp.model.HelpUserSetting helpUserSetting, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updateHelpUserSetting(helpUserSetting, merge);
    }

    public static boolean isHelpVisible(java.lang.Long userId,
        java.lang.String messageId) throws com.liferay.portal.SystemException {
        return getService().isHelpVisible(userId, messageId);
    }

    public static void toggleHelpVisibility(java.lang.Long userId,
        java.lang.String messageId) throws com.liferay.portal.SystemException {
        getService().toggleHelpVisibility(userId, messageId);
    }

    public static HelpUserSettingLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("HelpUserSettingLocalService is not set");
        }

        return _service;
    }

    public void setService(HelpUserSettingLocalService service) {
        _service = service;
    }
}
