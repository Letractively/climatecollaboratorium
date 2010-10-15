package com.ext.conditionaltext.service.base;

import com.ext.conditionaltext.model.ConditionalTextSetting;
import com.ext.conditionaltext.service.ConditionalTextSettingLocalService;
import com.ext.conditionaltext.service.ConditionalTextSettingService;
import com.ext.conditionaltext.service.persistence.ConditionalTextSettingPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class ConditionalTextSettingLocalServiceBaseImpl
    implements ConditionalTextSettingLocalService {
    @BeanReference(name = "com.ext.conditionaltext.service.ConditionalTextSettingLocalService.impl")
    protected ConditionalTextSettingLocalService conditionalTextSettingLocalService;
    @BeanReference(name = "com.ext.conditionaltext.service.ConditionalTextSettingService.impl")
    protected ConditionalTextSettingService conditionalTextSettingService;
    @BeanReference(name = "com.ext.conditionaltext.service.persistence.ConditionalTextSettingPersistence.impl")
    protected ConditionalTextSettingPersistence conditionalTextSettingPersistence;

    public ConditionalTextSetting addConditionalTextSetting(
        ConditionalTextSetting conditionalTextSetting)
        throws SystemException {
        conditionalTextSetting.setNew(true);

        return conditionalTextSettingPersistence.update(conditionalTextSetting,
            false);
    }

    public ConditionalTextSetting createConditionalTextSetting(
        Long ConditionalTextSettingId) {
        return conditionalTextSettingPersistence.create(ConditionalTextSettingId);
    }

    public void deleteConditionalTextSetting(Long ConditionalTextSettingId)
        throws PortalException, SystemException {
        conditionalTextSettingPersistence.remove(ConditionalTextSettingId);
    }

    public void deleteConditionalTextSetting(
        ConditionalTextSetting conditionalTextSetting)
        throws SystemException {
        conditionalTextSettingPersistence.remove(conditionalTextSetting);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return conditionalTextSettingPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return conditionalTextSettingPersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    public ConditionalTextSetting getConditionalTextSetting(
        Long ConditionalTextSettingId) throws PortalException, SystemException {
        return conditionalTextSettingPersistence.findByPrimaryKey(ConditionalTextSettingId);
    }

    public List<ConditionalTextSetting> getConditionalTextSettings(int start,
        int end) throws SystemException {
        return conditionalTextSettingPersistence.findAll(start, end);
    }

    public int getConditionalTextSettingsCount() throws SystemException {
        return conditionalTextSettingPersistence.countAll();
    }

    public ConditionalTextSetting updateConditionalTextSetting(
        ConditionalTextSetting conditionalTextSetting)
        throws SystemException {
        conditionalTextSetting.setNew(false);

        return conditionalTextSettingPersistence.update(conditionalTextSetting,
            true);
    }

    public ConditionalTextSetting updateConditionalTextSetting(
        ConditionalTextSetting conditionalTextSetting, boolean merge)
        throws SystemException {
        conditionalTextSetting.setNew(false);

        return conditionalTextSettingPersistence.update(conditionalTextSetting,
            merge);
    }

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
