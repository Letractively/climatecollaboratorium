package com.ext.portlet.debaterevision.service.base;

import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.service.DebateCategoryLocalService;
import com.ext.portlet.debaterevision.service.DebateCategoryMapLocalService;
import com.ext.portlet.debaterevision.service.DebateCategoryMapService;
import com.ext.portlet.debaterevision.service.DebateCategoryService;
import com.ext.portlet.debaterevision.service.DebateCommentLocalService;
import com.ext.portlet.debaterevision.service.DebateCommentService;
import com.ext.portlet.debaterevision.service.DebateItemLocalService;
import com.ext.portlet.debaterevision.service.DebateItemReferenceLocalService;
import com.ext.portlet.debaterevision.service.DebateItemReferenceService;
import com.ext.portlet.debaterevision.service.DebateItemService;
import com.ext.portlet.debaterevision.service.DebateItemVoteLocalService;
import com.ext.portlet.debaterevision.service.DebateItemVoteService;
import com.ext.portlet.debaterevision.service.DebateItemVoteStatsLocalService;
import com.ext.portlet.debaterevision.service.DebateItemVoteStatsService;
import com.ext.portlet.debaterevision.service.DebateLocalService;
import com.ext.portlet.debaterevision.service.DebateService;
import com.ext.portlet.debaterevision.service.persistence.DebateCategoryFinder;
import com.ext.portlet.debaterevision.service.persistence.DebateCategoryMapPersistence;
import com.ext.portlet.debaterevision.service.persistence.DebateCategoryPersistence;
import com.ext.portlet.debaterevision.service.persistence.DebateCommentPersistence;
import com.ext.portlet.debaterevision.service.persistence.DebateFinder;
import com.ext.portlet.debaterevision.service.persistence.DebateItemFinder;
import com.ext.portlet.debaterevision.service.persistence.DebateItemPersistence;
import com.ext.portlet.debaterevision.service.persistence.DebateItemReferencePersistence;
import com.ext.portlet.debaterevision.service.persistence.DebateItemVotePersistence;
import com.ext.portlet.debaterevision.service.persistence.DebateItemVoteStatsPersistence;
import com.ext.portlet.debaterevision.service.persistence.DebatePersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class DebateItemLocalServiceBaseImpl
    implements DebateItemLocalService {
    @BeanReference(name = "com.ext.portlet.debaterevision.service.DebateLocalService.impl")
    protected DebateLocalService debateLocalService;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.DebateService.impl")
    protected DebateService debateService;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebatePersistence.impl")
    protected DebatePersistence debatePersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateFinder.impl")
    protected DebateFinder debateFinder;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.DebateItemLocalService.impl")
    protected DebateItemLocalService debateItemLocalService;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.DebateItemService.impl")
    protected DebateItemService debateItemService;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateItemPersistence.impl")
    protected DebateItemPersistence debateItemPersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateItemFinder.impl")
    protected DebateItemFinder debateItemFinder;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.DebateItemReferenceLocalService.impl")
    protected DebateItemReferenceLocalService debateItemReferenceLocalService;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.DebateItemReferenceService.impl")
    protected DebateItemReferenceService debateItemReferenceService;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateItemReferencePersistence.impl")
    protected DebateItemReferencePersistence debateItemReferencePersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.DebateCategoryLocalService.impl")
    protected DebateCategoryLocalService debateCategoryLocalService;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.DebateCategoryService.impl")
    protected DebateCategoryService debateCategoryService;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateCategoryPersistence.impl")
    protected DebateCategoryPersistence debateCategoryPersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateCategoryFinder.impl")
    protected DebateCategoryFinder debateCategoryFinder;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.DebateCategoryMapLocalService.impl")
    protected DebateCategoryMapLocalService debateCategoryMapLocalService;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.DebateCategoryMapService.impl")
    protected DebateCategoryMapService debateCategoryMapService;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateCategoryMapPersistence.impl")
    protected DebateCategoryMapPersistence debateCategoryMapPersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.DebateCommentLocalService.impl")
    protected DebateCommentLocalService debateCommentLocalService;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.DebateCommentService.impl")
    protected DebateCommentService debateCommentService;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateCommentPersistence.impl")
    protected DebateCommentPersistence debateCommentPersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.DebateItemVoteLocalService.impl")
    protected DebateItemVoteLocalService debateItemVoteLocalService;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.DebateItemVoteService.impl")
    protected DebateItemVoteService debateItemVoteService;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateItemVotePersistence.impl")
    protected DebateItemVotePersistence debateItemVotePersistence;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.DebateItemVoteStatsLocalService.impl")
    protected DebateItemVoteStatsLocalService debateItemVoteStatsLocalService;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.DebateItemVoteStatsService.impl")
    protected DebateItemVoteStatsService debateItemVoteStatsService;
    @BeanReference(name = "com.ext.portlet.debaterevision.service.persistence.DebateItemVoteStatsPersistence.impl")
    protected DebateItemVoteStatsPersistence debateItemVoteStatsPersistence;

    public DebateItem addDebateItem(DebateItem debateItem)
        throws SystemException {
        debateItem.setNew(true);

        return debateItemPersistence.update(debateItem, false);
    }

    public DebateItem createDebateItem(Long debateItemPK) {
        return debateItemPersistence.create(debateItemPK);
    }

    public void deleteDebateItem(Long debateItemPK)
        throws PortalException, SystemException {
        debateItemPersistence.remove(debateItemPK);
    }

    public void deleteDebateItem(DebateItem debateItem)
        throws SystemException {
        debateItemPersistence.remove(debateItem);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return debateItemPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return debateItemPersistence.findWithDynamicQuery(dynamicQuery, start,
            end);
    }

    public DebateItem getDebateItem(Long debateItemPK)
        throws PortalException, SystemException {
        return debateItemPersistence.findByPrimaryKey(debateItemPK);
    }

    public List<DebateItem> getDebateItems(int start, int end)
        throws SystemException {
        return debateItemPersistence.findAll(start, end);
    }

    public int getDebateItemsCount() throws SystemException {
        return debateItemPersistence.countAll();
    }

    public DebateItem updateDebateItem(DebateItem debateItem)
        throws SystemException {
        debateItem.setNew(false);

        return debateItemPersistence.update(debateItem, true);
    }

    public DebateItem updateDebateItem(DebateItem debateItem, boolean merge)
        throws SystemException {
        debateItem.setNew(false);

        return debateItemPersistence.update(debateItem, merge);
    }

    public DebateLocalService getDebateLocalService() {
        return debateLocalService;
    }

    public void setDebateLocalService(DebateLocalService debateLocalService) {
        this.debateLocalService = debateLocalService;
    }

    public DebateService getDebateService() {
        return debateService;
    }

    public void setDebateService(DebateService debateService) {
        this.debateService = debateService;
    }

    public DebatePersistence getDebatePersistence() {
        return debatePersistence;
    }

    public void setDebatePersistence(DebatePersistence debatePersistence) {
        this.debatePersistence = debatePersistence;
    }

    public DebateFinder getDebateFinder() {
        return debateFinder;
    }

    public void setDebateFinder(DebateFinder debateFinder) {
        this.debateFinder = debateFinder;
    }

    public DebateItemLocalService getDebateItemLocalService() {
        return debateItemLocalService;
    }

    public void setDebateItemLocalService(
        DebateItemLocalService debateItemLocalService) {
        this.debateItemLocalService = debateItemLocalService;
    }

    public DebateItemService getDebateItemService() {
        return debateItemService;
    }

    public void setDebateItemService(DebateItemService debateItemService) {
        this.debateItemService = debateItemService;
    }

    public DebateItemPersistence getDebateItemPersistence() {
        return debateItemPersistence;
    }

    public void setDebateItemPersistence(
        DebateItemPersistence debateItemPersistence) {
        this.debateItemPersistence = debateItemPersistence;
    }

    public DebateItemFinder getDebateItemFinder() {
        return debateItemFinder;
    }

    public void setDebateItemFinder(DebateItemFinder debateItemFinder) {
        this.debateItemFinder = debateItemFinder;
    }

    public DebateItemReferenceLocalService getDebateItemReferenceLocalService() {
        return debateItemReferenceLocalService;
    }

    public void setDebateItemReferenceLocalService(
        DebateItemReferenceLocalService debateItemReferenceLocalService) {
        this.debateItemReferenceLocalService = debateItemReferenceLocalService;
    }

    public DebateItemReferenceService getDebateItemReferenceService() {
        return debateItemReferenceService;
    }

    public void setDebateItemReferenceService(
        DebateItemReferenceService debateItemReferenceService) {
        this.debateItemReferenceService = debateItemReferenceService;
    }

    public DebateItemReferencePersistence getDebateItemReferencePersistence() {
        return debateItemReferencePersistence;
    }

    public void setDebateItemReferencePersistence(
        DebateItemReferencePersistence debateItemReferencePersistence) {
        this.debateItemReferencePersistence = debateItemReferencePersistence;
    }

    public DebateCategoryLocalService getDebateCategoryLocalService() {
        return debateCategoryLocalService;
    }

    public void setDebateCategoryLocalService(
        DebateCategoryLocalService debateCategoryLocalService) {
        this.debateCategoryLocalService = debateCategoryLocalService;
    }

    public DebateCategoryService getDebateCategoryService() {
        return debateCategoryService;
    }

    public void setDebateCategoryService(
        DebateCategoryService debateCategoryService) {
        this.debateCategoryService = debateCategoryService;
    }

    public DebateCategoryPersistence getDebateCategoryPersistence() {
        return debateCategoryPersistence;
    }

    public void setDebateCategoryPersistence(
        DebateCategoryPersistence debateCategoryPersistence) {
        this.debateCategoryPersistence = debateCategoryPersistence;
    }

    public DebateCategoryFinder getDebateCategoryFinder() {
        return debateCategoryFinder;
    }

    public void setDebateCategoryFinder(
        DebateCategoryFinder debateCategoryFinder) {
        this.debateCategoryFinder = debateCategoryFinder;
    }

    public DebateCategoryMapLocalService getDebateCategoryMapLocalService() {
        return debateCategoryMapLocalService;
    }

    public void setDebateCategoryMapLocalService(
        DebateCategoryMapLocalService debateCategoryMapLocalService) {
        this.debateCategoryMapLocalService = debateCategoryMapLocalService;
    }

    public DebateCategoryMapService getDebateCategoryMapService() {
        return debateCategoryMapService;
    }

    public void setDebateCategoryMapService(
        DebateCategoryMapService debateCategoryMapService) {
        this.debateCategoryMapService = debateCategoryMapService;
    }

    public DebateCategoryMapPersistence getDebateCategoryMapPersistence() {
        return debateCategoryMapPersistence;
    }

    public void setDebateCategoryMapPersistence(
        DebateCategoryMapPersistence debateCategoryMapPersistence) {
        this.debateCategoryMapPersistence = debateCategoryMapPersistence;
    }

    public DebateCommentLocalService getDebateCommentLocalService() {
        return debateCommentLocalService;
    }

    public void setDebateCommentLocalService(
        DebateCommentLocalService debateCommentLocalService) {
        this.debateCommentLocalService = debateCommentLocalService;
    }

    public DebateCommentService getDebateCommentService() {
        return debateCommentService;
    }

    public void setDebateCommentService(
        DebateCommentService debateCommentService) {
        this.debateCommentService = debateCommentService;
    }

    public DebateCommentPersistence getDebateCommentPersistence() {
        return debateCommentPersistence;
    }

    public void setDebateCommentPersistence(
        DebateCommentPersistence debateCommentPersistence) {
        this.debateCommentPersistence = debateCommentPersistence;
    }

    public DebateItemVoteLocalService getDebateItemVoteLocalService() {
        return debateItemVoteLocalService;
    }

    public void setDebateItemVoteLocalService(
        DebateItemVoteLocalService debateItemVoteLocalService) {
        this.debateItemVoteLocalService = debateItemVoteLocalService;
    }

    public DebateItemVoteService getDebateItemVoteService() {
        return debateItemVoteService;
    }

    public void setDebateItemVoteService(
        DebateItemVoteService debateItemVoteService) {
        this.debateItemVoteService = debateItemVoteService;
    }

    public DebateItemVotePersistence getDebateItemVotePersistence() {
        return debateItemVotePersistence;
    }

    public void setDebateItemVotePersistence(
        DebateItemVotePersistence debateItemVotePersistence) {
        this.debateItemVotePersistence = debateItemVotePersistence;
    }

    public DebateItemVoteStatsLocalService getDebateItemVoteStatsLocalService() {
        return debateItemVoteStatsLocalService;
    }

    public void setDebateItemVoteStatsLocalService(
        DebateItemVoteStatsLocalService debateItemVoteStatsLocalService) {
        this.debateItemVoteStatsLocalService = debateItemVoteStatsLocalService;
    }

    public DebateItemVoteStatsService getDebateItemVoteStatsService() {
        return debateItemVoteStatsService;
    }

    public void setDebateItemVoteStatsService(
        DebateItemVoteStatsService debateItemVoteStatsService) {
        this.debateItemVoteStatsService = debateItemVoteStatsService;
    }

    public DebateItemVoteStatsPersistence getDebateItemVoteStatsPersistence() {
        return debateItemVoteStatsPersistence;
    }

    public void setDebateItemVoteStatsPersistence(
        DebateItemVoteStatsPersistence debateItemVoteStatsPersistence) {
        this.debateItemVoteStatsPersistence = debateItemVoteStatsPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
