package com.ext.portlet.debatemigration.service.base;

import com.ext.portlet.debatemigration.service.DebateMigrationCategoryLocalService;
import com.ext.portlet.debatemigration.service.DebateMigrationCategoryService;
import com.ext.portlet.debatemigration.service.DebateMigrationCommentLocalService;
import com.ext.portlet.debatemigration.service.DebateMigrationCommentService;
import com.ext.portlet.debatemigration.service.DebateMigrationDebateLocalService;
import com.ext.portlet.debatemigration.service.DebateMigrationDebateService;
import com.ext.portlet.debatemigration.service.DebateMigrationItemLocalService;
import com.ext.portlet.debatemigration.service.DebateMigrationItemService;
import com.ext.portlet.debatemigration.service.DebateMigrationLocalService;
import com.ext.portlet.debatemigration.service.DebateMigrationService;
import com.ext.portlet.debatemigration.service.persistence.DebateMigrationCategoryPersistence;
import com.ext.portlet.debatemigration.service.persistence.DebateMigrationCommentPersistence;
import com.ext.portlet.debatemigration.service.persistence.DebateMigrationDebatePersistence;
import com.ext.portlet.debatemigration.service.persistence.DebateMigrationItemPersistence;
import com.ext.portlet.debatemigration.service.persistence.DebateMigrationPersistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.util.PortalUtil;


public abstract class DebateMigrationDebateServiceBaseImpl extends PrincipalBean
    implements DebateMigrationDebateService {
    @BeanReference(name = "com.ext.portlet.debatemigration.service.DebateMigrationLocalService.impl")
    protected DebateMigrationLocalService debateMigrationLocalService;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.DebateMigrationService.impl")
    protected DebateMigrationService debateMigrationService;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.persistence.DebateMigrationPersistence.impl")
    protected DebateMigrationPersistence debateMigrationPersistence;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.DebateMigrationCategoryLocalService.impl")
    protected DebateMigrationCategoryLocalService debateMigrationCategoryLocalService;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.DebateMigrationCategoryService.impl")
    protected DebateMigrationCategoryService debateMigrationCategoryService;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.persistence.DebateMigrationCategoryPersistence.impl")
    protected DebateMigrationCategoryPersistence debateMigrationCategoryPersistence;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.DebateMigrationDebateLocalService.impl")
    protected DebateMigrationDebateLocalService debateMigrationDebateLocalService;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.DebateMigrationDebateService.impl")
    protected DebateMigrationDebateService debateMigrationDebateService;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.persistence.DebateMigrationDebatePersistence.impl")
    protected DebateMigrationDebatePersistence debateMigrationDebatePersistence;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.DebateMigrationItemLocalService.impl")
    protected DebateMigrationItemLocalService debateMigrationItemLocalService;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.DebateMigrationItemService.impl")
    protected DebateMigrationItemService debateMigrationItemService;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.persistence.DebateMigrationItemPersistence.impl")
    protected DebateMigrationItemPersistence debateMigrationItemPersistence;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.DebateMigrationCommentLocalService.impl")
    protected DebateMigrationCommentLocalService debateMigrationCommentLocalService;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.DebateMigrationCommentService.impl")
    protected DebateMigrationCommentService debateMigrationCommentService;
    @BeanReference(name = "com.ext.portlet.debatemigration.service.persistence.DebateMigrationCommentPersistence.impl")
    protected DebateMigrationCommentPersistence debateMigrationCommentPersistence;

    public DebateMigrationLocalService getDebateMigrationLocalService() {
        return debateMigrationLocalService;
    }

    public void setDebateMigrationLocalService(
        DebateMigrationLocalService debateMigrationLocalService) {
        this.debateMigrationLocalService = debateMigrationLocalService;
    }

    public DebateMigrationService getDebateMigrationService() {
        return debateMigrationService;
    }

    public void setDebateMigrationService(
        DebateMigrationService debateMigrationService) {
        this.debateMigrationService = debateMigrationService;
    }

    public DebateMigrationPersistence getDebateMigrationPersistence() {
        return debateMigrationPersistence;
    }

    public void setDebateMigrationPersistence(
        DebateMigrationPersistence debateMigrationPersistence) {
        this.debateMigrationPersistence = debateMigrationPersistence;
    }

    public DebateMigrationCategoryLocalService getDebateMigrationCategoryLocalService() {
        return debateMigrationCategoryLocalService;
    }

    public void setDebateMigrationCategoryLocalService(
        DebateMigrationCategoryLocalService debateMigrationCategoryLocalService) {
        this.debateMigrationCategoryLocalService = debateMigrationCategoryLocalService;
    }

    public DebateMigrationCategoryService getDebateMigrationCategoryService() {
        return debateMigrationCategoryService;
    }

    public void setDebateMigrationCategoryService(
        DebateMigrationCategoryService debateMigrationCategoryService) {
        this.debateMigrationCategoryService = debateMigrationCategoryService;
    }

    public DebateMigrationCategoryPersistence getDebateMigrationCategoryPersistence() {
        return debateMigrationCategoryPersistence;
    }

    public void setDebateMigrationCategoryPersistence(
        DebateMigrationCategoryPersistence debateMigrationCategoryPersistence) {
        this.debateMigrationCategoryPersistence = debateMigrationCategoryPersistence;
    }

    public DebateMigrationDebateLocalService getDebateMigrationDebateLocalService() {
        return debateMigrationDebateLocalService;
    }

    public void setDebateMigrationDebateLocalService(
        DebateMigrationDebateLocalService debateMigrationDebateLocalService) {
        this.debateMigrationDebateLocalService = debateMigrationDebateLocalService;
    }

    public DebateMigrationDebateService getDebateMigrationDebateService() {
        return debateMigrationDebateService;
    }

    public void setDebateMigrationDebateService(
        DebateMigrationDebateService debateMigrationDebateService) {
        this.debateMigrationDebateService = debateMigrationDebateService;
    }

    public DebateMigrationDebatePersistence getDebateMigrationDebatePersistence() {
        return debateMigrationDebatePersistence;
    }

    public void setDebateMigrationDebatePersistence(
        DebateMigrationDebatePersistence debateMigrationDebatePersistence) {
        this.debateMigrationDebatePersistence = debateMigrationDebatePersistence;
    }

    public DebateMigrationItemLocalService getDebateMigrationItemLocalService() {
        return debateMigrationItemLocalService;
    }

    public void setDebateMigrationItemLocalService(
        DebateMigrationItemLocalService debateMigrationItemLocalService) {
        this.debateMigrationItemLocalService = debateMigrationItemLocalService;
    }

    public DebateMigrationItemService getDebateMigrationItemService() {
        return debateMigrationItemService;
    }

    public void setDebateMigrationItemService(
        DebateMigrationItemService debateMigrationItemService) {
        this.debateMigrationItemService = debateMigrationItemService;
    }

    public DebateMigrationItemPersistence getDebateMigrationItemPersistence() {
        return debateMigrationItemPersistence;
    }

    public void setDebateMigrationItemPersistence(
        DebateMigrationItemPersistence debateMigrationItemPersistence) {
        this.debateMigrationItemPersistence = debateMigrationItemPersistence;
    }

    public DebateMigrationCommentLocalService getDebateMigrationCommentLocalService() {
        return debateMigrationCommentLocalService;
    }

    public void setDebateMigrationCommentLocalService(
        DebateMigrationCommentLocalService debateMigrationCommentLocalService) {
        this.debateMigrationCommentLocalService = debateMigrationCommentLocalService;
    }

    public DebateMigrationCommentService getDebateMigrationCommentService() {
        return debateMigrationCommentService;
    }

    public void setDebateMigrationCommentService(
        DebateMigrationCommentService debateMigrationCommentService) {
        this.debateMigrationCommentService = debateMigrationCommentService;
    }

    public DebateMigrationCommentPersistence getDebateMigrationCommentPersistence() {
        return debateMigrationCommentPersistence;
    }

    public void setDebateMigrationCommentPersistence(
        DebateMigrationCommentPersistence debateMigrationCommentPersistence) {
        this.debateMigrationCommentPersistence = debateMigrationCommentPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
