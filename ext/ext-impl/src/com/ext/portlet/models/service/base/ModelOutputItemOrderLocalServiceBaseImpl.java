package com.ext.portlet.models.service.base;

import com.ext.portlet.models.model.ModelOutputItemOrder;
import com.ext.portlet.models.service.ModelDiscussionLocalService;
import com.ext.portlet.models.service.ModelDiscussionService;
import com.ext.portlet.models.service.ModelInputGroupLocalService;
import com.ext.portlet.models.service.ModelInputGroupService;
import com.ext.portlet.models.service.ModelInputItemLocalService;
import com.ext.portlet.models.service.ModelInputItemService;
import com.ext.portlet.models.service.ModelOutputChartOrderLocalService;
import com.ext.portlet.models.service.ModelOutputChartOrderService;
import com.ext.portlet.models.service.ModelOutputItemModifierLocalService;
import com.ext.portlet.models.service.ModelOutputItemModifierService;
import com.ext.portlet.models.service.ModelOutputItemOrderLocalService;
import com.ext.portlet.models.service.ModelOutputItemOrderService;
import com.ext.portlet.models.service.ModelPositionLocalService;
import com.ext.portlet.models.service.ModelPositionService;
import com.ext.portlet.models.service.persistence.ModelDiscussionPersistence;
import com.ext.portlet.models.service.persistence.ModelInputGroupPersistence;
import com.ext.portlet.models.service.persistence.ModelInputItemPersistence;
import com.ext.portlet.models.service.persistence.ModelOutputChartOrderPersistence;
import com.ext.portlet.models.service.persistence.ModelOutputItemModifierPersistence;
import com.ext.portlet.models.service.persistence.ModelOutputItemOrderPersistence;
import com.ext.portlet.models.service.persistence.ModelPositionPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class ModelOutputItemOrderLocalServiceBaseImpl
    implements ModelOutputItemOrderLocalService {
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
    @BeanReference(name = "com.ext.portlet.models.service.ModelOutputItemOrderLocalService.impl")
    protected ModelOutputItemOrderLocalService modelOutputItemOrderLocalService;
    @BeanReference(name = "com.ext.portlet.models.service.ModelOutputItemOrderService.impl")
    protected ModelOutputItemOrderService modelOutputItemOrderService;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelOutputItemOrderPersistence.impl")
    protected ModelOutputItemOrderPersistence modelOutputItemOrderPersistence;
    @BeanReference(name = "com.ext.portlet.models.service.ModelOutputItemModifierLocalService.impl")
    protected ModelOutputItemModifierLocalService modelOutputItemModifierLocalService;
    @BeanReference(name = "com.ext.portlet.models.service.ModelOutputItemModifierService.impl")
    protected ModelOutputItemModifierService modelOutputItemModifierService;
    @BeanReference(name = "com.ext.portlet.models.service.persistence.ModelOutputItemModifierPersistence.impl")
    protected ModelOutputItemModifierPersistence modelOutputItemModifierPersistence;

    public ModelOutputItemOrder addModelOutputItemOrder(
        ModelOutputItemOrder modelOutputItemOrder) throws SystemException {
        modelOutputItemOrder.setNew(true);

        return modelOutputItemOrderPersistence.update(modelOutputItemOrder,
            false);
    }

    public ModelOutputItemOrder createModelOutputItemOrder(
        Long modelOutputItemModifierPK) {
        return modelOutputItemOrderPersistence.create(modelOutputItemModifierPK);
    }

    public void deleteModelOutputItemOrder(Long modelOutputItemModifierPK)
        throws PortalException, SystemException {
        modelOutputItemOrderPersistence.remove(modelOutputItemModifierPK);
    }

    public void deleteModelOutputItemOrder(
        ModelOutputItemOrder modelOutputItemOrder) throws SystemException {
        modelOutputItemOrderPersistence.remove(modelOutputItemOrder);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return modelOutputItemOrderPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return modelOutputItemOrderPersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    public ModelOutputItemOrder getModelOutputItemOrder(
        Long modelOutputItemModifierPK) throws PortalException, SystemException {
        return modelOutputItemOrderPersistence.findByPrimaryKey(modelOutputItemModifierPK);
    }

    public List<ModelOutputItemOrder> getModelOutputItemOrders(int start,
        int end) throws SystemException {
        return modelOutputItemOrderPersistence.findAll(start, end);
    }

    public int getModelOutputItemOrdersCount() throws SystemException {
        return modelOutputItemOrderPersistence.countAll();
    }

    public ModelOutputItemOrder updateModelOutputItemOrder(
        ModelOutputItemOrder modelOutputItemOrder) throws SystemException {
        modelOutputItemOrder.setNew(false);

        return modelOutputItemOrderPersistence.update(modelOutputItemOrder, true);
    }

    public ModelOutputItemOrder updateModelOutputItemOrder(
        ModelOutputItemOrder modelOutputItemOrder, boolean merge)
        throws SystemException {
        modelOutputItemOrder.setNew(false);

        return modelOutputItemOrderPersistence.update(modelOutputItemOrder,
            merge);
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

    public ModelOutputItemOrderLocalService getModelOutputItemOrderLocalService() {
        return modelOutputItemOrderLocalService;
    }

    public void setModelOutputItemOrderLocalService(
        ModelOutputItemOrderLocalService modelOutputItemOrderLocalService) {
        this.modelOutputItemOrderLocalService = modelOutputItemOrderLocalService;
    }

    public ModelOutputItemOrderService getModelOutputItemOrderService() {
        return modelOutputItemOrderService;
    }

    public void setModelOutputItemOrderService(
        ModelOutputItemOrderService modelOutputItemOrderService) {
        this.modelOutputItemOrderService = modelOutputItemOrderService;
    }

    public ModelOutputItemOrderPersistence getModelOutputItemOrderPersistence() {
        return modelOutputItemOrderPersistence;
    }

    public void setModelOutputItemOrderPersistence(
        ModelOutputItemOrderPersistence modelOutputItemOrderPersistence) {
        this.modelOutputItemOrderPersistence = modelOutputItemOrderPersistence;
    }

    public ModelOutputItemModifierLocalService getModelOutputItemModifierLocalService() {
        return modelOutputItemModifierLocalService;
    }

    public void setModelOutputItemModifierLocalService(
        ModelOutputItemModifierLocalService modelOutputItemModifierLocalService) {
        this.modelOutputItemModifierLocalService = modelOutputItemModifierLocalService;
    }

    public ModelOutputItemModifierService getModelOutputItemModifierService() {
        return modelOutputItemModifierService;
    }

    public void setModelOutputItemModifierService(
        ModelOutputItemModifierService modelOutputItemModifierService) {
        this.modelOutputItemModifierService = modelOutputItemModifierService;
    }

    public ModelOutputItemModifierPersistence getModelOutputItemModifierPersistence() {
        return modelOutputItemModifierPersistence;
    }

    public void setModelOutputItemModifierPersistence(
        ModelOutputItemModifierPersistence modelOutputItemModifierPersistence) {
        this.modelOutputItemModifierPersistence = modelOutputItemModifierPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
