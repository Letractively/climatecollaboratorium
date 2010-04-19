package com.ext.inlinehelp.service.base;

import com.ext.inlinehelp.service.HelpUserSettingLocalService;
import com.ext.inlinehelp.service.HelpUserSettingService;
import com.ext.inlinehelp.service.persistence.HelpUserSettingPersistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.util.PortalUtil;


public abstract class HelpUserSettingServiceBaseImpl extends PrincipalBean
    implements HelpUserSettingService {
    @BeanReference(name = "com.ext.inlinehelp.service.HelpUserSettingLocalService.impl")
    protected HelpUserSettingLocalService helpUserSettingLocalService;
    @BeanReference(name = "com.ext.inlinehelp.service.HelpUserSettingService.impl")
    protected HelpUserSettingService helpUserSettingService;
    @BeanReference(name = "com.ext.inlinehelp.service.persistence.HelpUserSettingPersistence.impl")
    protected HelpUserSettingPersistence helpUserSettingPersistence;

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
