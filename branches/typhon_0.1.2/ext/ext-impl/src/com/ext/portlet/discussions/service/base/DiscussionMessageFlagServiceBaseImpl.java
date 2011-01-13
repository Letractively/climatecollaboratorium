package com.ext.portlet.discussions.service.base;

import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalService;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupService;
import com.ext.portlet.discussions.service.DiscussionCategoryLocalService;
import com.ext.portlet.discussions.service.DiscussionCategoryService;
import com.ext.portlet.discussions.service.DiscussionMessageFlagLocalService;
import com.ext.portlet.discussions.service.DiscussionMessageFlagService;
import com.ext.portlet.discussions.service.DiscussionMessageLocalService;
import com.ext.portlet.discussions.service.DiscussionMessageService;
import com.ext.portlet.discussions.service.persistence.DiscussionCategoryGroupPersistence;
import com.ext.portlet.discussions.service.persistence.DiscussionCategoryPersistence;
import com.ext.portlet.discussions.service.persistence.DiscussionMessageFlagPersistence;
import com.ext.portlet.discussions.service.persistence.DiscussionMessagePersistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.util.PortalUtil;


public abstract class DiscussionMessageFlagServiceBaseImpl extends PrincipalBean
    implements DiscussionMessageFlagService {
    @BeanReference(name = "com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalService.impl")
    protected DiscussionCategoryGroupLocalService discussionCategoryGroupLocalService;
    @BeanReference(name = "com.ext.portlet.discussions.service.DiscussionCategoryGroupService.impl")
    protected DiscussionCategoryGroupService discussionCategoryGroupService;
    @BeanReference(name = "com.ext.portlet.discussions.service.persistence.DiscussionCategoryGroupPersistence.impl")
    protected DiscussionCategoryGroupPersistence discussionCategoryGroupPersistence;
    @BeanReference(name = "com.ext.portlet.discussions.service.DiscussionCategoryLocalService.impl")
    protected DiscussionCategoryLocalService discussionCategoryLocalService;
    @BeanReference(name = "com.ext.portlet.discussions.service.DiscussionCategoryService.impl")
    protected DiscussionCategoryService discussionCategoryService;
    @BeanReference(name = "com.ext.portlet.discussions.service.persistence.DiscussionCategoryPersistence.impl")
    protected DiscussionCategoryPersistence discussionCategoryPersistence;
    @BeanReference(name = "com.ext.portlet.discussions.service.DiscussionMessageLocalService.impl")
    protected DiscussionMessageLocalService discussionMessageLocalService;
    @BeanReference(name = "com.ext.portlet.discussions.service.DiscussionMessageService.impl")
    protected DiscussionMessageService discussionMessageService;
    @BeanReference(name = "com.ext.portlet.discussions.service.persistence.DiscussionMessagePersistence.impl")
    protected DiscussionMessagePersistence discussionMessagePersistence;
    @BeanReference(name = "com.ext.portlet.discussions.service.DiscussionMessageFlagLocalService.impl")
    protected DiscussionMessageFlagLocalService discussionMessageFlagLocalService;
    @BeanReference(name = "com.ext.portlet.discussions.service.DiscussionMessageFlagService.impl")
    protected DiscussionMessageFlagService discussionMessageFlagService;
    @BeanReference(name = "com.ext.portlet.discussions.service.persistence.DiscussionMessageFlagPersistence.impl")
    protected DiscussionMessageFlagPersistence discussionMessageFlagPersistence;

    public DiscussionCategoryGroupLocalService getDiscussionCategoryGroupLocalService() {
        return discussionCategoryGroupLocalService;
    }

    public void setDiscussionCategoryGroupLocalService(
        DiscussionCategoryGroupLocalService discussionCategoryGroupLocalService) {
        this.discussionCategoryGroupLocalService = discussionCategoryGroupLocalService;
    }

    public DiscussionCategoryGroupService getDiscussionCategoryGroupService() {
        return discussionCategoryGroupService;
    }

    public void setDiscussionCategoryGroupService(
        DiscussionCategoryGroupService discussionCategoryGroupService) {
        this.discussionCategoryGroupService = discussionCategoryGroupService;
    }

    public DiscussionCategoryGroupPersistence getDiscussionCategoryGroupPersistence() {
        return discussionCategoryGroupPersistence;
    }

    public void setDiscussionCategoryGroupPersistence(
        DiscussionCategoryGroupPersistence discussionCategoryGroupPersistence) {
        this.discussionCategoryGroupPersistence = discussionCategoryGroupPersistence;
    }

    public DiscussionCategoryLocalService getDiscussionCategoryLocalService() {
        return discussionCategoryLocalService;
    }

    public void setDiscussionCategoryLocalService(
        DiscussionCategoryLocalService discussionCategoryLocalService) {
        this.discussionCategoryLocalService = discussionCategoryLocalService;
    }

    public DiscussionCategoryService getDiscussionCategoryService() {
        return discussionCategoryService;
    }

    public void setDiscussionCategoryService(
        DiscussionCategoryService discussionCategoryService) {
        this.discussionCategoryService = discussionCategoryService;
    }

    public DiscussionCategoryPersistence getDiscussionCategoryPersistence() {
        return discussionCategoryPersistence;
    }

    public void setDiscussionCategoryPersistence(
        DiscussionCategoryPersistence discussionCategoryPersistence) {
        this.discussionCategoryPersistence = discussionCategoryPersistence;
    }

    public DiscussionMessageLocalService getDiscussionMessageLocalService() {
        return discussionMessageLocalService;
    }

    public void setDiscussionMessageLocalService(
        DiscussionMessageLocalService discussionMessageLocalService) {
        this.discussionMessageLocalService = discussionMessageLocalService;
    }

    public DiscussionMessageService getDiscussionMessageService() {
        return discussionMessageService;
    }

    public void setDiscussionMessageService(
        DiscussionMessageService discussionMessageService) {
        this.discussionMessageService = discussionMessageService;
    }

    public DiscussionMessagePersistence getDiscussionMessagePersistence() {
        return discussionMessagePersistence;
    }

    public void setDiscussionMessagePersistence(
        DiscussionMessagePersistence discussionMessagePersistence) {
        this.discussionMessagePersistence = discussionMessagePersistence;
    }

    public DiscussionMessageFlagLocalService getDiscussionMessageFlagLocalService() {
        return discussionMessageFlagLocalService;
    }

    public void setDiscussionMessageFlagLocalService(
        DiscussionMessageFlagLocalService discussionMessageFlagLocalService) {
        this.discussionMessageFlagLocalService = discussionMessageFlagLocalService;
    }

    public DiscussionMessageFlagService getDiscussionMessageFlagService() {
        return discussionMessageFlagService;
    }

    public void setDiscussionMessageFlagService(
        DiscussionMessageFlagService discussionMessageFlagService) {
        this.discussionMessageFlagService = discussionMessageFlagService;
    }

    public DiscussionMessageFlagPersistence getDiscussionMessageFlagPersistence() {
        return discussionMessageFlagPersistence;
    }

    public void setDiscussionMessageFlagPersistence(
        DiscussionMessageFlagPersistence discussionMessageFlagPersistence) {
        this.discussionMessageFlagPersistence = discussionMessageFlagPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
