package com.ext.inlinehelp.service.base;

import com.ext.inlinehelp.model.HelpUserSetting;
import com.ext.inlinehelp.service.HelpUserSettingLocalService;
import com.ext.inlinehelp.service.HelpUserSettingService;
import com.ext.inlinehelp.service.persistence.HelpUserSettingPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class HelpUserSettingLocalServiceBaseImpl
    implements HelpUserSettingLocalService {
    @BeanReference(name = "com.ext.inlinehelp.service.HelpUserSettingLocalService.impl")
    protected HelpUserSettingLocalService helpUserSettingLocalService;
    @BeanReference(name = "com.ext.inlinehelp.service.HelpUserSettingService.impl")
    protected HelpUserSettingService helpUserSettingService;
    @BeanReference(name = "com.ext.inlinehelp.service.persistence.HelpUserSettingPersistence.impl")
    protected HelpUserSettingPersistence helpUserSettingPersistence;

    public HelpUserSetting addHelpUserSetting(HelpUserSetting helpUserSetting)
        throws SystemException {
        helpUserSetting.setNew(true);

        return helpUserSettingPersistence.update(helpUserSetting, false);
    }

    public HelpUserSetting createHelpUserSetting(Long HelpUserSettingId) {
        return helpUserSettingPersistence.create(HelpUserSettingId);
    }

    public void deleteHelpUserSetting(Long HelpUserSettingId)
        throws PortalException, SystemException {
        helpUserSettingPersistence.remove(HelpUserSettingId);
    }

    public void deleteHelpUserSetting(HelpUserSetting helpUserSetting)
        throws SystemException {
        helpUserSettingPersistence.remove(helpUserSetting);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return helpUserSettingPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return helpUserSettingPersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    public HelpUserSetting getHelpUserSetting(Long HelpUserSettingId)
        throws PortalException, SystemException {
        return helpUserSettingPersistence.findByPrimaryKey(HelpUserSettingId);
    }

    public List<HelpUserSetting> getHelpUserSettings(int start, int end)
        throws SystemException {
        return helpUserSettingPersistence.findAll(start, end);
    }

    public int getHelpUserSettingsCount() throws SystemException {
        return helpUserSettingPersistence.countAll();
    }

    public HelpUserSetting updateHelpUserSetting(
        HelpUserSetting helpUserSetting) throws SystemException {
        helpUserSetting.setNew(false);

        return helpUserSettingPersistence.update(helpUserSetting, true);
    }

    public HelpUserSetting updateHelpUserSetting(
        HelpUserSetting helpUserSetting, boolean merge)
        throws SystemException {
        helpUserSetting.setNew(false);

        return helpUserSettingPersistence.update(helpUserSetting, merge);
    }

    public HelpUserSettingLocalService getHelpUserSettingLocalService() {
        return helpUserSettingLocalService;
    }

    public void setHelpUserSettingLocalService(
        HelpUserSettingLocalService helpUserSettingLocalService) {
        this.helpUserSettingLocalService = helpUserSettingLocalService;
    }

    public HelpUserSettingService getHelpUserSettingService() {
        return helpUserSettingService;
    }

    public void setHelpUserSettingService(
        HelpUserSettingService helpUserSettingService) {
        this.helpUserSettingService = helpUserSettingService;
    }

    public HelpUserSettingPersistence getHelpUserSettingPersistence() {
        return helpUserSettingPersistence;
    }

    public void setHelpUserSettingPersistence(
        HelpUserSettingPersistence helpUserSettingPersistence) {
        this.helpUserSettingPersistence = helpUserSettingPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
