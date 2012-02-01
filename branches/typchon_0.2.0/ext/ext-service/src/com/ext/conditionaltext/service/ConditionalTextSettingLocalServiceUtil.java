package com.ext.conditionaltext.service;


/**
 * <a href="ConditionalTextSettingLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.conditionaltext.service.ConditionalTextSettingLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.conditionaltext.service.ConditionalTextSettingLocalService
 *
 */
public class ConditionalTextSettingLocalServiceUtil {
    private static ConditionalTextSettingLocalService _service;

    public static com.ext.conditionaltext.model.ConditionalTextSetting addConditionalTextSetting(
        com.ext.conditionaltext.model.ConditionalTextSetting conditionalTextSetting)
        throws com.liferay.portal.SystemException {
        return getService().addConditionalTextSetting(conditionalTextSetting);
    }

    public static com.ext.conditionaltext.model.ConditionalTextSetting createConditionalTextSetting(
        java.lang.Long ConditionalTextSettingId) {
        return getService()
                   .createConditionalTextSetting(ConditionalTextSettingId);
    }

    public static void deleteConditionalTextSetting(
        java.lang.Long ConditionalTextSettingId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteConditionalTextSetting(ConditionalTextSettingId);
    }

    public static void deleteConditionalTextSetting(
        com.ext.conditionaltext.model.ConditionalTextSetting conditionalTextSetting)
        throws com.liferay.portal.SystemException {
        getService().deleteConditionalTextSetting(conditionalTextSetting);
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

    public static com.ext.conditionaltext.model.ConditionalTextSetting getConditionalTextSetting(
        java.lang.Long ConditionalTextSettingId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getConditionalTextSetting(ConditionalTextSettingId);
    }

    public static java.util.List<com.ext.conditionaltext.model.ConditionalTextSetting> getConditionalTextSettings(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getConditionalTextSettings(start, end);
    }

    public static int getConditionalTextSettingsCount()
        throws com.liferay.portal.SystemException {
        return getService().getConditionalTextSettingsCount();
    }

    public static com.ext.conditionaltext.model.ConditionalTextSetting updateConditionalTextSetting(
        com.ext.conditionaltext.model.ConditionalTextSetting conditionalTextSetting)
        throws com.liferay.portal.SystemException {
        return getService().updateConditionalTextSetting(conditionalTextSetting);
    }

    public static com.ext.conditionaltext.model.ConditionalTextSetting updateConditionalTextSetting(
        com.ext.conditionaltext.model.ConditionalTextSetting conditionalTextSetting,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateConditionalTextSetting(conditionalTextSetting, merge);
    }

    public static java.util.List<com.ext.conditionaltext.model.ConditionalTextSetting> findByKey(
        java.lang.String key) {
        return getService().findByKey(key);
    }

    public static com.ext.conditionaltext.model.ConditionalTextSetting findByKeyVal(
        java.lang.String key, java.lang.String val) {
        return getService().findByKeyVal(key, val);
    }

    public static void updateSetting(java.lang.String key,
        java.lang.String val, java.lang.String style, java.lang.String text)
        throws com.liferay.portal.SystemException {
        getService().updateSetting(key, val, style, text);
    }

    public static ConditionalTextSettingLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "ConditionalTextSettingLocalService is not set");
        }

        return _service;
    }

    public void setService(ConditionalTextSettingLocalService service) {
        _service = service;
    }
}
