package com.ext.portlet.discussions.service.base;

import com.ext.portlet.discussions.model.DiscussionMessageFlag;
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

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class DiscussionMessageFlagLocalServiceBaseImpl
    implements DiscussionMessageFlagLocalService {
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

    public DiscussionMessageFlag addDiscussionMessageFlag(
        DiscussionMessageFlag discussionMessageFlag) throws SystemException {
        discussionMessageFlag.setNew(true);

        return discussionMessageFlagPersistence.update(discussionMessageFlag,
            false);
    }

    public DiscussionMessageFlag createDiscussionMessageFlag(Long pk) {
        return discussionMessageFlagPersistence.create(pk);
    }

    public void deleteDiscussionMessageFlag(Long pk)
        throws PortalException, SystemException {
        discussionMessageFlagPersistence.remove(pk);
    }

    public void deleteDiscussionMessageFlag(
        DiscussionMessageFlag discussionMessageFlag) throws SystemException {
        discussionMessageFlagPersistence.remove(discussionMessageFlag);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return discussionMessageFlagPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return discussionMessageFlagPersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    public DiscussionMessageFlag getDiscussionMessageFlag(Long pk)
        throws PortalException, SystemException {
        return discussionMessageFlagPersistence.findByPrimaryKey(pk);
    }

    public List<DiscussionMessageFlag> getDiscussionMessageFlags(int start,
        int end) throws SystemException {
        return discussionMessageFlagPersistence.findAll(start, end);
    }

    public int getDiscussionMessageFlagsCount() throws SystemException {
        return discussionMessageFlagPersistence.countAll();
    }

    public DiscussionMessageFlag updateDiscussionMessageFlag(
        DiscussionMessageFlag discussionMessageFlag) throws SystemException {
        discussionMessageFlag.setNew(false);

        return discussionMessageFlagPersistence.update(discussionMessageFlag,
            true);
    }

    public DiscussionMessageFlag updateDiscussionMessageFlag(
        DiscussionMessageFlag discussionMessageFlag, boolean merge)
        throws SystemException {
        discussionMessageFlag.setNew(false);

        return discussionMessageFlagPersistence.update(discussionMessageFlag,
            merge);
    }

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
