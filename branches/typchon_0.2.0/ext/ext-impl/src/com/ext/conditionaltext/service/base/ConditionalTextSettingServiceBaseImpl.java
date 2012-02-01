package com.ext.conditionaltext.service.base;

import com.ext.conditionaltext.service.ConditionalTextSettingLocalService;
import com.ext.conditionaltext.service.ConditionalTextSettingService;
import com.ext.conditionaltext.service.persistence.ConditionalTextSettingPersistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.util.PortalUtil;


public abstract class ConditionalTextSettingServiceBaseImpl
    extends PrincipalBean implements ConditionalTextSettingService {
    @BeanReference(name = "com.ext.conditionaltext.service.ConditionalTextSettingLocalService.impl")
    protected ConditionalTextSettingLocalService conditionalTextSettingLocalService;
    @BeanReference(name = "com.ext.conditionaltext.service.ConditionalTextSettingService.impl")
    protected ConditionalTextSettingService conditionalTextSettingService;
    @BeanReference(name = "com.ext.conditionaltext.service.persistence.ConditionalTextSettingPersistence.impl")
    protected ConditionalTextSettingPersistence conditionalTextSettingPersistence;

    public ConditionalTextSettingLocalService getConditionalTextSettingLocalService() {
        return conditionalTextSettingLocalService;
    }

    public void setConditionalTextSettingLocalService(
        ConditionalTextSettingLocalService conditionalTextSettingLocalService) {
        this.conditionalTextSettingLocalService = conditionalTextSettingLocalService;
    }

    public ConditionalTextSettingService getConditionalTextSettingService() {
        return conditionalTextSettingService;
    }

    public void setConditionalTextSettingService(
        ConditionalTextSettingService conditionalTextSettingService) {
        this.conditionalTextSettingService = conditionalTextSettingService;
    }

    public ConditionalTextSettingPersistence getConditionalTextSettingPersistence() {
        return conditionalTextSettingPersistence;
    }

    public void setConditionalTextSettingPersistence(
        ConditionalTextSettingPersistence conditionalTextSettingPersistence) {
        this.conditionalTextSettingPersistence = conditionalTextSettingPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
