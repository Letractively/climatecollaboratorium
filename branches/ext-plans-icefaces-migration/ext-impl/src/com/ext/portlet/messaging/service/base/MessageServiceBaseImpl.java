package com.ext.portlet.messaging.service.base;

import com.ext.portlet.messaging.service.MessageLocalService;
import com.ext.portlet.messaging.service.MessageRecipientStatusLocalService;
import com.ext.portlet.messaging.service.MessageRecipientStatusService;
import com.ext.portlet.messaging.service.MessageService;
import com.ext.portlet.messaging.service.MessagingUserPreferencesLocalService;
import com.ext.portlet.messaging.service.MessagingUserPreferencesService;
import com.ext.portlet.messaging.service.persistence.MessagePersistence;
import com.ext.portlet.messaging.service.persistence.MessageRecipientStatusPersistence;
import com.ext.portlet.messaging.service.persistence.MessagingUserPreferencesPersistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.util.PortalUtil;


public abstract class MessageServiceBaseImpl extends PrincipalBean
    implements MessageService {
    @BeanReference(name = "com.ext.portlet.messaging.service.MessageLocalService.impl")
    protected MessageLocalService messageLocalService;
    @BeanReference(name = "com.ext.portlet.messaging.service.MessageService.impl")
    protected MessageService messageService;
    @BeanReference(name = "com.ext.portlet.messaging.service.persistence.MessagePersistence.impl")
    protected MessagePersistence messagePersistence;
    @BeanReference(name = "com.ext.portlet.messaging.service.MessageRecipientStatusLocalService.impl")
    protected MessageRecipientStatusLocalService messageRecipientStatusLocalService;
    @BeanReference(name = "com.ext.portlet.messaging.service.MessageRecipientStatusService.impl")
    protected MessageRecipientStatusService messageRecipientStatusService;
    @BeanReference(name = "com.ext.portlet.messaging.service.persistence.MessageRecipientStatusPersistence.impl")
    protected MessageRecipientStatusPersistence messageRecipientStatusPersistence;
    @BeanReference(name = "com.ext.portlet.messaging.service.MessagingUserPreferencesLocalService.impl")
    protected MessagingUserPreferencesLocalService messagingUserPreferencesLocalService;
    @BeanReference(name = "com.ext.portlet.messaging.service.MessagingUserPreferencesService.impl")
    protected MessagingUserPreferencesService messagingUserPreferencesService;
    @BeanReference(name = "com.ext.portlet.messaging.service.persistence.MessagingUserPreferencesPersistence.impl")
    protected MessagingUserPreferencesPersistence messagingUserPreferencesPersistence;
    @BeanReference(name = "com.liferay.portal.service.UserLocalService.impl")
    protected UserLocalService userLocalService;
    @BeanReference(name = "com.liferay.portal.service.UserService.impl")
    protected UserService userService;
    @BeanReference(name = "com.liferay.portal.service.persistence.UserPersistence.impl")
    protected UserPersistence userPersistence;

    public MessageLocalService getMessageLocalService() {
        return messageLocalService;
    }

    public void setMessageLocalService(MessageLocalService messageLocalService) {
        this.messageLocalService = messageLocalService;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public MessagePersistence getMessagePersistence() {
        return messagePersistence;
    }

    public void setMessagePersistence(MessagePersistence messagePersistence) {
        this.messagePersistence = messagePersistence;
    }

    public MessageRecipientStatusLocalService getMessageRecipientStatusLocalService() {
        return messageRecipientStatusLocalService;
    }

    public void setMessageRecipientStatusLocalService(
        MessageRecipientStatusLocalService messageRecipientStatusLocalService) {
        this.messageRecipientStatusLocalService = messageRecipientStatusLocalService;
    }

    public MessageRecipientStatusService getMessageRecipientStatusService() {
        return messageRecipientStatusService;
    }

    public void setMessageRecipientStatusService(
        MessageRecipientStatusService messageRecipientStatusService) {
        this.messageRecipientStatusService = messageRecipientStatusService;
    }

    public MessageRecipientStatusPersistence getMessageRecipientStatusPersistence() {
        return messageRecipientStatusPersistence;
    }

    public void setMessageRecipientStatusPersistence(
        MessageRecipientStatusPersistence messageRecipientStatusPersistence) {
        this.messageRecipientStatusPersistence = messageRecipientStatusPersistence;
    }

    public MessagingUserPreferencesLocalService getMessagingUserPreferencesLocalService() {
        return messagingUserPreferencesLocalService;
    }

    public void setMessagingUserPreferencesLocalService(
        MessagingUserPreferencesLocalService messagingUserPreferencesLocalService) {
        this.messagingUserPreferencesLocalService = messagingUserPreferencesLocalService;
    }

    public MessagingUserPreferencesService getMessagingUserPreferencesService() {
        return messagingUserPreferencesService;
    }

    public void setMessagingUserPreferencesService(
        MessagingUserPreferencesService messagingUserPreferencesService) {
        this.messagingUserPreferencesService = messagingUserPreferencesService;
    }

    public MessagingUserPreferencesPersistence getMessagingUserPreferencesPersistence() {
        return messagingUserPreferencesPersistence;
    }

    public void setMessagingUserPreferencesPersistence(
        MessagingUserPreferencesPersistence messagingUserPreferencesPersistence) {
        this.messagingUserPreferencesPersistence = messagingUserPreferencesPersistence;
    }

    public UserLocalService getUserLocalService() {
        return userLocalService;
    }

    public void setUserLocalService(UserLocalService userLocalService) {
        this.userLocalService = userLocalService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserPersistence getUserPersistence() {
        return userPersistence;
    }

    public void setUserPersistence(UserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
