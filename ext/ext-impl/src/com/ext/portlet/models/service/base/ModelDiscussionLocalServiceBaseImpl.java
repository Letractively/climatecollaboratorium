/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.service.base;

import com.ext.portlet.models.model.ModelDiscussion;
import com.ext.portlet.models.service.ModelDiscussionLocalService;
import com.ext.portlet.models.service.ModelDiscussionService;
import com.ext.portlet.models.service.ModelPositionLocalService;
import com.ext.portlet.models.service.ModelPositionService;
import com.ext.portlet.models.service.persistence.ModelDiscussionPersistence;
import com.ext.portlet.models.service.persistence.ModelPositionPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class ModelDiscussionLocalServiceBaseImpl
    implements ModelDiscussionLocalService {
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

    public ModelDiscussion addModelDiscussion(ModelDiscussion modelDiscussion)
        throws SystemException {
        modelDiscussion.setNew(true);

        return modelDiscussionPersistence.update(modelDiscussion, false);
    }

    public ModelDiscussion createModelDiscussion(Long modelDiscussionId) {
        return modelDiscussionPersistence.create(modelDiscussionId);
    }

    public void deleteModelDiscussion(Long modelDiscussionId)
        throws PortalException, SystemException {
        modelDiscussionPersistence.remove(modelDiscussionId);
    }

    public void deleteModelDiscussion(ModelDiscussion modelDiscussion)
        throws SystemException {
        modelDiscussionPersistence.remove(modelDiscussion);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return modelDiscussionPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return modelDiscussionPersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    public ModelDiscussion getModelDiscussion(Long modelDiscussionId)
        throws PortalException, SystemException {
        return modelDiscussionPersistence.findByPrimaryKey(modelDiscussionId);
    }

    public List<ModelDiscussion> getModelDiscussions(int start, int end)
        throws SystemException {
        return modelDiscussionPersistence.findAll(start, end);
    }

    public int getModelDiscussionsCount() throws SystemException {
        return modelDiscussionPersistence.countAll();
    }

    public ModelDiscussion updateModelDiscussion(
        ModelDiscussion modelDiscussion) throws SystemException {
        modelDiscussion.setNew(false);

        return modelDiscussionPersistence.update(modelDiscussion, true);
    }

    public ModelDiscussion updateModelDiscussion(
        ModelDiscussion modelDiscussion, boolean merge)
        throws SystemException {
        modelDiscussion.setNew(false);

        return modelDiscussionPersistence.update(modelDiscussion, merge);
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

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
