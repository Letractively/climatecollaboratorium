package com.ext.portlet.models.service.base;

import com.ext.portlet.models.model.ModelInputGroup;
import com.ext.portlet.models.service.ModelDiscussionLocalService;
import com.ext.portlet.models.service.ModelDiscussionService;
import com.ext.portlet.models.service.ModelGlobalPreferenceLocalService;
import com.ext.portlet.models.service.ModelGlobalPreferenceService;
import com.ext.portlet.models.service.ModelInputGroupLocalService;
import com.ext.portlet.models.service.ModelInputGroupService;
import com.ext.portlet.models.service.ModelInputItemLocalService;
import com.ext.portlet.models.service.ModelInputItemService;
import com.ext.portlet.models.service.ModelOutputChartOrderLocalService;
import com.ext.portlet.models.service.ModelOutputChartOrderService;
import com.ext.portlet.models.service.ModelOutputItemLocalService;
import com.ext.portlet.models.service.ModelOutputItemService;
import com.ext.portlet.models.service.ModelPositionLocalService;
import com.ext.portlet.models.service.ModelPositionService;
import com.ext.portlet.models.service.persistence.ModelDiscussionPersistence;
import com.ext.portlet.models.service.persistence.ModelGlobalPreferencePersistence;
import com.ext.portlet.models.service.persistence.ModelInputGroupPersistence;
import com.ext.portlet.models.service.persistence.ModelInputItemPersistence;
import com.ext.portlet.models.service.persistence.ModelOutputChartOrderPersistence;
import com.ext.portlet.models.service.persistence.ModelOutputItemPersistence;
import com.ext.portlet.models.service.persistence.ModelPositionPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class ModelInputGroupLocalServiceBaseImpl
    implements ModelInputGroupLocalService {
    @BeanReference(name = "com.ext.portlet.models.service.ModelDiscussionLocalService.impl")
    protected ModelDiscussionLocalService modelDiscussionLocalService;
    @BeanReference(name = "com.ext.portlet.models.service.ModelDiscussionService.impl")
    protected ModelDiscussionService modelDiscussionService;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelDiscussionPersistence.impl")
    protected ModelDiscussionPersistence modelDiscussionPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.ModelPositionLocalService.impl")
    protected ModelPositionLocalService modelPositionLocalService;
    @BeanReference(name = "com.ext.portlet.models.service.ModelPositionService.impl")
    protected ModelPositionService modelPositionService;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelPositionPersistence.impl")
    protected ModelPositionPersistence modelPositionPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.ModelGlobalPreferenceLocalService.impl")
    protected ModelGlobalPreferenceLocalService modelGlobalPreferenceLocalService;
    @BeanReference(name = "com.ext.portlet.models.service.ModelGlobalPreferenceService.impl")
    protected ModelGlobalPreferenceService modelGlobalPreferenceService;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelGlobalPreferencePersistence.impl")
    protected ModelGlobalPreferencePersistence modelGlobalPreferencePersistence;
    @BeanReference(name = "com.ext.portlet.models.service.ModelInputGroupLocalService.impl")
    protected ModelInputGroupLocalService modelInputGroupLocalService;
    @BeanReference(name = "com.ext.portlet.models.service.ModelInputGroupService.impl")
    protected ModelInputGroupService modelInputGroupService;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelInputGroupPersistence.impl")
    protected ModelInputGroupPersistence modelInputGroupPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.ModelInputItemLocalService.impl")
    protected ModelInputItemLocalService modelInputItemLocalService;
    @BeanReference(name = "com.ext.portlet.models.service.ModelInputItemService.impl")
    protected ModelInputItemService modelInputItemService;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelInputItemPersistence.impl")
    protected ModelInputItemPersistence modelInputItemPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.ModelOutputChartOrderLocalService.impl")
    protected ModelOutputChartOrderLocalService modelOutputChartOrderLocalService;
    @BeanReference(name = "com.ext.portlet.models.service.ModelOutputChartOrderService.impl")
    protected ModelOutputChartOrderService modelOutputChartOrderService;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelOutputChartOrderPersistence.impl")
    protected ModelOutputChartOrderPersistence modelOutputChartOrderPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.ModelOutputItemLocalService.impl")
    protected ModelOutputItemLocalService modelOutputItemLocalService;
    @BeanReference(name = "com.ext.portlet.models.service.ModelOutputItemService.impl")
    protected ModelOutputItemService modelOutputItemService;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelOutputItemPersistence.impl")
    protected ModelOutputItemPersistence modelOutputItemPersistence;

    public ModelInputGroup addModelInputGroup(ModelInputGroup modelInputGroup)
        throws SystemException {
        modelInputGroup.setNew(true);

        return modelInputGroupPersistence.update(modelInputGroup, false);
    }

    public ModelInputGroup createModelInputGroup(Long modelInputGroupPK) {
        return modelInputGroupPersistence.create(modelInputGroupPK);
    }

    public void deleteModelInputGroup(Long modelInputGroupPK)
        throws PortalException, SystemException {
        modelInputGroupPersistence.remove(modelInputGroupPK);
    }

    public void deleteModelInputGroup(ModelInputGroup modelInputGroup)
        throws SystemException {
        modelInputGroupPersistence.remove(modelInputGroup);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return modelInputGroupPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return modelInputGroupPersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    public ModelInputGroup getModelInputGroup(Long modelInputGroupPK)
        throws PortalException, SystemException {
        return modelInputGroupPersistence.findByPrimaryKey(modelInputGroupPK);
    }

    public List<ModelInputGroup> getModelInputGroups(int start, int end)
        throws SystemException {
        return modelInputGroupPersistence.findAll(start, end);
    }

    public int getModelInputGroupsCount() throws SystemException {
        return modelInputGroupPersistence.countAll();
    }

    public ModelInputGroup updateModelInputGroup(
        ModelInputGroup modelInputGroup) throws SystemException {
        modelInputGroup.setNew(false);

        return modelInputGroupPersistence.update(modelInputGroup, true);
    }

    public ModelInputGroup updateModelInputGroup(
        ModelInputGroup modelInputGroup, boolean merge)
        throws SystemException {
        modelInputGroup.setNew(false);

        return modelInputGroupPersistence.update(modelInputGroup, merge);
    }

    public ModelDiscussionLocalService getModelDiscussionLocalService() {
        return modelDiscussionLocalService;
    }

    public void setModelDiscussionLocalService(
        ModelDiscussionLocalService modelDiscussionLocalService) {
        this.modelDiscussionLocalService = modelDiscussionLocalService;
    }

    public ModelDiscussionService getModelDiscussionService() {
        return modelDiscussionService;
    }

    public void setModelDiscussionService(
        ModelDiscussionService modelDiscussionService) {
        this.modelDiscussionService = modelDiscussionService;
    }

    public ModelDiscussionPersistence getModelDiscussionPersistence() {
        return modelDiscussionPersistence;
    }

    public void setModelDiscussionPersistence(
        ModelDiscussionPersistence modelDiscussionPersistence) {
        this.modelDiscussionPersistence = modelDiscussionPersistence;
    }

    public ModelPositionLocalService getModelPositionLocalService() {
        return modelPositionLocalService;
    }

    public void setModelPositionLocalService(
        ModelPositionLocalService modelPositionLocalService) {
        this.modelPositionLocalService = modelPositionLocalService;
    }

    public ModelPositionService getModelPositionService() {
        return modelPositionService;
    }

    public void setModelPositionService(
        ModelPositionService modelPositionService) {
        this.modelPositionService = modelPositionService;
    }

    public ModelPositionPersistence getModelPositionPersistence() {
        return modelPositionPersistence;
    }

    public void setModelPositionPersistence(
        ModelPositionPersistence modelPositionPersistence) {
        this.modelPositionPersistence = modelPositionPersistence;
    }

    public ModelGlobalPreferenceLocalService getModelGlobalPreferenceLocalService() {
        return modelGlobalPreferenceLocalService;
    }

    public void setModelGlobalPreferenceLocalService(
        ModelGlobalPreferenceLocalService modelGlobalPreferenceLocalService) {
        this.modelGlobalPreferenceLocalService = modelGlobalPreferenceLocalService;
    }

    public ModelGlobalPreferenceService getModelGlobalPreferenceService() {
        return modelGlobalPreferenceService;
    }

    public void setModelGlobalPreferenceService(
        ModelGlobalPreferenceService modelGlobalPreferenceService) {
        this.modelGlobalPreferenceService = modelGlobalPreferenceService;
    }

    public ModelGlobalPreferencePersistence getModelGlobalPreferencePersistence() {
        return modelGlobalPreferencePersistence;
    }

    public void setModelGlobalPreferencePersistence(
        ModelGlobalPreferencePersistence modelGlobalPreferencePersistence) {
        this.modelGlobalPreferencePersistence = modelGlobalPreferencePersistence;
    }

    public ModelInputGroupLocalService getModelInputGroupLocalService() {
        return modelInputGroupLocalService;
    }

    public void setModelInputGroupLocalService(
        ModelInputGroupLocalService modelInputGroupLocalService) {
        this.modelInputGroupLocalService = modelInputGroupLocalService;
    }

    public ModelInputGroupService getModelInputGroupService() {
        return modelInputGroupService;
    }

    public void setModelInputGroupService(
        ModelInputGroupService modelInputGroupService) {
        this.modelInputGroupService = modelInputGroupService;
    }

    public ModelInputGroupPersistence getModelInputGroupPersistence() {
        return modelInputGroupPersistence;
    }

    public void setModelInputGroupPersistence(
        ModelInputGroupPersistence modelInputGroupPersistence) {
        this.modelInputGroupPersistence = modelInputGroupPersistence;
    }

    public ModelInputItemLocalService getModelInputItemLocalService() {
        return modelInputItemLocalService;
    }

    public void setModelInputItemLocalService(
        ModelInputItemLocalService modelInputItemLocalService) {
        this.modelInputItemLocalService = modelInputItemLocalService;
    }

    public ModelInputItemService getModelInputItemService() {
        return modelInputItemService;
    }

    public void setModelInputItemService(
        ModelInputItemService modelInputItemService) {
        this.modelInputItemService = modelInputItemService;
    }

    public ModelInputItemPersistence getModelInputItemPersistence() {
        return modelInputItemPersistence;
    }

    public void setModelInputItemPersistence(
        ModelInputItemPersistence modelInputItemPersistence) {
        this.modelInputItemPersistence = modelInputItemPersistence;
    }

    public ModelOutputChartOrderLocalService getModelOutputChartOrderLocalService() {
        return modelOutputChartOrderLocalService;
    }

    public void setModelOutputChartOrderLocalService(
        ModelOutputChartOrderLocalService modelOutputChartOrderLocalService) {
        this.modelOutputChartOrderLocalService = modelOutputChartOrderLocalService;
    }

    public ModelOutputChartOrderService getModelOutputChartOrderService() {
        return modelOutputChartOrderService;
    }

    public void setModelOutputChartOrderService(
        ModelOutputChartOrderService modelOutputChartOrderService) {
        this.modelOutputChartOrderService = modelOutputChartOrderService;
    }

    public ModelOutputChartOrderPersistence getModelOutputChartOrderPersistence() {
        return modelOutputChartOrderPersistence;
    }

    public void setModelOutputChartOrderPersistence(
        ModelOutputChartOrderPersistence modelOutputChartOrderPersistence) {
        this.modelOutputChartOrderPersistence = modelOutputChartOrderPersistence;
    }

    public ModelOutputItemLocalService getModelOutputItemLocalService() {
        return modelOutputItemLocalService;
    }

    public void setModelOutputItemLocalService(
        ModelOutputItemLocalService modelOutputItemLocalService) {
        this.modelOutputItemLocalService = modelOutputItemLocalService;
    }

    public ModelOutputItemService getModelOutputItemService() {
        return modelOutputItemService;
    }

    public void setModelOutputItemService(
        ModelOutputItemService modelOutputItemService) {
        this.modelOutputItemService = modelOutputItemService;
    }

    public ModelOutputItemPersistence getModelOutputItemPersistence() {
        return modelOutputItemPersistence;
    }

    public void setModelOutputItemPersistence(
        ModelOutputItemPersistence modelOutputItemPersistence) {
        this.modelOutputItemPersistence = modelOutputItemPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
