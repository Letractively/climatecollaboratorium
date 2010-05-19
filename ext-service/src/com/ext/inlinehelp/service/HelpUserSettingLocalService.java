package com.ext.inlinehelp.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="HelpUserSettingLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.inlinehelp.service.impl.HelpUserSettingLocalServiceImpl</code>.
 * Modify methods in that class and rerun ServiceBuilder to populate this class
 * and all other generated classes.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.inlinehelp.service.HelpUserSettingLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface HelpUserSettingLocalService {
    public com.ext.inlinehelp.model.HelpUserSetting addHelpUserSetting(
        com.ext.inlinehelp.model.HelpUserSetting helpUserSetting)
        throws com.liferay.portal.SystemException;

    public com.ext.inlinehelp.model.HelpUserSetting createHelpUserSetting(
        java.lang.Long HelpUserSettingId);

    public void deleteHelpUserSetting(java.lang.Long HelpUserSettingId)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteHelpUserSetting(
        com.ext.inlinehelp.model.HelpUserSetting helpUserSetting)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.inlinehelp.model.HelpUserSetting getHelpUserSetting(
        java.lang.Long HelpUserSettingId)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.inlinehelp.model.HelpUserSetting> getHelpUserSettings(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getHelpUserSettingsCount()
        throws com.liferay.portal.SystemException;

    public com.ext.inlinehelp.model.HelpUserSetting updateHelpUserSetting(
        com.ext.inlinehelp.model.HelpUserSetting helpUserSetting)
        throws com.liferay.portal.SystemException;

    public com.ext.inlinehelp.model.HelpUserSetting updateHelpUserSetting(
        com.ext.inlinehelp.model.HelpUserSetting helpUserSetting, boolean merge)
        throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean isHelpVisible(java.lang.Long userId,
        java.lang.String messageId) throws com.liferay.portal.SystemException;

    public void toggleHelpVisibility(java.lang.Long userId,
        java.lang.String messageId) throws com.liferay.portal.SystemException;
}
