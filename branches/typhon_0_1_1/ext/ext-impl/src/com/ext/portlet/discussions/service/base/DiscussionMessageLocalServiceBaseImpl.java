package com.ext.portlet.discussions.service.base;

import com.ext.portlet.discussions.model.DiscussionMessage;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalService;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupService;
import com.ext.portlet.discussions.service.DiscussionCategoryLocalService;
import com.ext.portlet.discussions.service.DiscussionCategoryService;
import com.ext.portlet.discussions.service.DiscussionMessageLocalService;
import com.ext.portlet.discussions.service.DiscussionMessageService;
import com.ext.portlet.discussions.service.persistence.DiscussionCategoryGroupPersistence;
import com.ext.portlet.discussions.service.persistence.DiscussionCategoryPersistence;
import com.ext.portlet.discussions.service.persistence.DiscussionMessagePersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class DiscussionMessageLocalServiceBaseImpl
    implements DiscussionMessageLocalService {
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

    public DiscussionMessage addDiscussionMessage(
        DiscussionMessage discussionMessage) throws SystemException {
        discussionMessage.setNew(true);

        return discussionMessagePersistence.update(discussionMessage, false);
    }

    public DiscussionMessage createDiscussionMessage(Long pk) {
        return discussionMessagePersistence.create(pk);
    }

    public void deleteDiscussionMessage(Long pk)
        throws PortalException, SystemException {
        discussionMessagePersistence.remove(pk);
    }

    public void deleteDiscussionMessage(DiscussionMessage discussionMessage)
        throws SystemException {
        discussionMessagePersistence.remove(discussionMessage);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return discussionMessagePersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return discussionMessagePersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    public DiscussionMessage getDiscussionMessage(Long pk)
        throws PortalException, SystemException {
        return discussionMessagePersistence.findByPrimaryKey(pk);
    }

    public List<DiscussionMessage> getDiscussionMessages(int start, int end)
        throws SystemException {
        return discussionMessagePersistence.findAll(start, end);
    }

    public int getDiscussionMessagesCount() throws SystemException {
        return discussionMessagePersistence.countAll();
    }

    public DiscussionMessage updateDiscussionMessage(
        DiscussionMessage discussionMessage) throws SystemException {
        discussionMessage.setNew(false);

        return discussionMessagePersistence.update(discussionMessage, true);
    }

    public DiscussionMessage updateDiscussionMessage(
        DiscussionMessage discussionMessage, boolean merge)
        throws SystemException {
        discussionMessage.setNew(false);

        return discussionMessagePersistence.update(discussionMessage, merge);
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

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
