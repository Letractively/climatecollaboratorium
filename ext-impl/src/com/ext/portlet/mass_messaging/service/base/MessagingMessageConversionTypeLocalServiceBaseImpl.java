/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.base;

import com.ext.portlet.mass_messaging.model.MessagingMessageConversionType;
import com.ext.portlet.mass_messaging.service.MessagingIgnoredRecipientsLocalService;
import com.ext.portlet.mass_messaging.service.MessagingIgnoredRecipientsService;
import com.ext.portlet.mass_messaging.service.MessagingMessageConversionLocalService;
import com.ext.portlet.mass_messaging.service.MessagingMessageConversionService;
import com.ext.portlet.mass_messaging.service.MessagingMessageConversionTypeLocalService;
import com.ext.portlet.mass_messaging.service.MessagingMessageConversionTypeService;
import com.ext.portlet.mass_messaging.service.MessagingMessageLocalService;
import com.ext.portlet.mass_messaging.service.MessagingMessageRecipientLocalService;
import com.ext.portlet.mass_messaging.service.MessagingMessageRecipientService;
import com.ext.portlet.mass_messaging.service.MessagingMessageService;
import com.ext.portlet.mass_messaging.service.MessagingRedirectLinkLocalService;
import com.ext.portlet.mass_messaging.service.MessagingRedirectLinkService;
import com.ext.portlet.mass_messaging.service.persistence.MessagingIgnoredRecipientsPersistence;
import com.ext.portlet.mass_messaging.service.persistence.MessagingMessageConversionPersistence;
import com.ext.portlet.mass_messaging.service.persistence.MessagingMessageConversionTypePersistence;
import com.ext.portlet.mass_messaging.service.persistence.MessagingMessagePersistence;
import com.ext.portlet.mass_messaging.service.persistence.MessagingMessageRecipientPersistence;
import com.ext.portlet.mass_messaging.service.persistence.MessagingRedirectLinkPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class MessagingMessageConversionTypeLocalServiceBaseImpl
    implements MessagingMessageConversionTypeLocalService {
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.MessagingMessageLocalService.impl")
    protected MessagingMessageLocalService messagingMessageLocalService;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.MessagingMessageService.impl")
    protected MessagingMessageService messagingMessageService;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.persistence.MessagingMessagePersistence.impl")
    protected MessagingMessagePersistence messagingMessagePersistence;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.MessagingMessageRecipientLocalService.impl")
    protected MessagingMessageRecipientLocalService messagingMessageRecipientLocalService;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.MessagingMessageRecipientService.impl")
    protected MessagingMessageRecipientService messagingMessageRecipientService;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.persistence.MessagingMessageRecipientPersistence.impl")
    protected MessagingMessageRecipientPersistence messagingMessageRecipientPersistence;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.MessagingMessageConversionTypeLocalService.impl")
    protected MessagingMessageConversionTypeLocalService messagingMessageConversionTypeLocalService;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.MessagingMessageConversionTypeService.impl")
    protected MessagingMessageConversionTypeService messagingMessageConversionTypeService;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.persistence.MessagingMessageConversionTypePersistence.impl")
    protected MessagingMessageConversionTypePersistence messagingMessageConversionTypePersistence;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.MessagingMessageConversionLocalService.impl")
    protected MessagingMessageConversionLocalService messagingMessageConversionLocalService;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.MessagingMessageConversionService.impl")
    protected MessagingMessageConversionService messagingMessageConversionService;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.persistence.MessagingMessageConversionPersistence.impl")
    protected MessagingMessageConversionPersistence messagingMessageConversionPersistence;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.MessagingRedirectLinkLocalService.impl")
    protected MessagingRedirectLinkLocalService messagingRedirectLinkLocalService;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.MessagingRedirectLinkService.impl")
    protected MessagingRedirectLinkService messagingRedirectLinkService;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.persistence.MessagingRedirectLinkPersistence.impl")
    protected MessagingRedirectLinkPersistence messagingRedirectLinkPersistence;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.MessagingIgnoredRecipientsLocalService.impl")
    protected MessagingIgnoredRecipientsLocalService messagingIgnoredRecipientsLocalService;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.MessagingIgnoredRecipientsService.impl")
    protected MessagingIgnoredRecipientsService messagingIgnoredRecipientsService;
    @BeanReference(name = "com.ext.portlet.mass_messaging.service.persistence.MessagingIgnoredRecipientsPersistence.impl")
    protected MessagingIgnoredRecipientsPersistence messagingIgnoredRecipientsPersistence;

    public MessagingMessageConversionType addMessagingMessageConversionType(
        MessagingMessageConversionType messagingMessageConversionType)
        throws SystemException {
        messagingMessageConversionType.setNew(true);

        return messagingMessageConversionTypePersistence.update(messagingMessageConversionType,
            false);
    }

    public MessagingMessageConversionType createMessagingMessageConversionType(
        Long typeId) {
        return messagingMessageConversionTypePersistence.create(typeId);
    }

    public void deleteMessagingMessageConversionType(Long typeId)
        throws PortalException, SystemException {
        messagingMessageConversionTypePersistence.remove(typeId);
    }

    public void deleteMessagingMessageConversionType(
        MessagingMessageConversionType messagingMessageConversionType)
        throws SystemException {
        messagingMessageConversionTypePersistence.remove(messagingMessageConversionType);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return messagingMessageConversionTypePersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return messagingMessageConversionTypePersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    public MessagingMessageConversionType getMessagingMessageConversionType(
        Long typeId) throws PortalException, SystemException {
        return messagingMessageConversionTypePersistence.findByPrimaryKey(typeId);
    }

    public List<MessagingMessageConversionType> getMessagingMessageConversionTypes(
        int start, int end) throws SystemException {
        return messagingMessageConversionTypePersistence.findAll(start, end);
    }

    public int getMessagingMessageConversionTypesCount()
        throws SystemException {
        return messagingMessageConversionTypePersistence.countAll();
    }

    public MessagingMessageConversionType updateMessagingMessageConversionType(
        MessagingMessageConversionType messagingMessageConversionType)
        throws SystemException {
        messagingMessageConversionType.setNew(false);

        return messagingMessageConversionTypePersistence.update(messagingMessageConversionType,
            true);
    }

    public MessagingMessageConversionType updateMessagingMessageConversionType(
        MessagingMessageConversionType messagingMessageConversionType,
        boolean merge) throws SystemException {
        messagingMessageConversionType.setNew(false);

        return messagingMessageConversionTypePersistence.update(messagingMessageConversionType,
            merge);
    }

    public MessagingMessageLocalService getMessagingMessageLocalService() {
        return messagingMessageLocalService;
    }

    public void setMessagingMessageLocalService(
        MessagingMessageLocalService messagingMessageLocalService) {
        this.messagingMessageLocalService = messagingMessageLocalService;
    }

    public MessagingMessageService getMessagingMessageService() {
        return messagingMessageService;
    }

    public void setMessagingMessageService(
        MessagingMessageService messagingMessageService) {
        this.messagingMessageService = messagingMessageService;
    }

    public MessagingMessagePersistence getMessagingMessagePersistence() {
        return messagingMessagePersistence;
    }

    public void setMessagingMessagePersistence(
        MessagingMessagePersistence messagingMessagePersistence) {
        this.messagingMessagePersistence = messagingMessagePersistence;
    }

    public MessagingMessageRecipientLocalService getMessagingMessageRecipientLocalService() {
        return messagingMessageRecipientLocalService;
    }

    public void setMessagingMessageRecipientLocalService(
        MessagingMessageRecipientLocalService messagingMessageRecipientLocalService) {
        this.messagingMessageRecipientLocalService = messagingMessageRecipientLocalService;
    }

    public MessagingMessageRecipientService getMessagingMessageRecipientService() {
        return messagingMessageRecipientService;
    }

    public void setMessagingMessageRecipientService(
        MessagingMessageRecipientService messagingMessageRecipientService) {
        this.messagingMessageRecipientService = messagingMessageRecipientService;
    }

    public MessagingMessageRecipientPersistence getMessagingMessageRecipientPersistence() {
        return messagingMessageRecipientPersistence;
    }

    public void setMessagingMessageRecipientPersistence(
        MessagingMessageRecipientPersistence messagingMessageRecipientPersistence) {
        this.messagingMessageRecipientPersistence = messagingMessageRecipientPersistence;
    }

    public MessagingMessageConversionTypeLocalService getMessagingMessageConversionTypeLocalService() {
        return messagingMessageConversionTypeLocalService;
    }

    public void setMessagingMessageConversionTypeLocalService(
        MessagingMessageConversionTypeLocalService messagingMessageConversionTypeLocalService) {
        this.messagingMessageConversionTypeLocalService = messagingMessageConversionTypeLocalService;
    }

    public MessagingMessageConversionTypeService getMessagingMessageConversionTypeService() {
        return messagingMessageConversionTypeService;
    }

    public void setMessagingMessageConversionTypeService(
        MessagingMessageConversionTypeService messagingMessageConversionTypeService) {
        this.messagingMessageConversionTypeService = messagingMessageConversionTypeService;
    }

    public MessagingMessageConversionTypePersistence getMessagingMessageConversionTypePersistence() {
        return messagingMessageConversionTypePersistence;
    }

    public void setMessagingMessageConversionTypePersistence(
        MessagingMessageConversionTypePersistence messagingMessageConversionTypePersistence) {
        this.messagingMessageConversionTypePersistence = messagingMessageConversionTypePersistence;
    }

    public MessagingMessageConversionLocalService getMessagingMessageConversionLocalService() {
        return messagingMessageConversionLocalService;
    }

    public void setMessagingMessageConversionLocalService(
        MessagingMessageConversionLocalService messagingMessageConversionLocalService) {
        this.messagingMessageConversionLocalService = messagingMessageConversionLocalService;
    }

    public MessagingMessageConversionService getMessagingMessageConversionService() {
        return messagingMessageConversionService;
    }

    public void setMessagingMessageConversionService(
        MessagingMessageConversionService messagingMessageConversionService) {
        this.messagingMessageConversionService = messagingMessageConversionService;
    }

    public MessagingMessageConversionPersistence getMessagingMessageConversionPersistence() {
        return messagingMessageConversionPersistence;
    }

    public void setMessagingMessageConversionPersistence(
        MessagingMessageConversionPersistence messagingMessageConversionPersistence) {
        this.messagingMessageConversionPersistence = messagingMessageConversionPersistence;
    }

    public MessagingRedirectLinkLocalService getMessagingRedirectLinkLocalService() {
        return messagingRedirectLinkLocalService;
    }

    public void setMessagingRedirectLinkLocalService(
        MessagingRedirectLinkLocalService messagingRedirectLinkLocalService) {
        this.messagingRedirectLinkLocalService = messagingRedirectLinkLocalService;
    }

    public MessagingRedirectLinkService getMessagingRedirectLinkService() {
        return messagingRedirectLinkService;
    }

    public void setMessagingRedirectLinkService(
        MessagingRedirectLinkService messagingRedirectLinkService) {
        this.messagingRedirectLinkService = messagingRedirectLinkService;
    }

    public MessagingRedirectLinkPersistence getMessagingRedirectLinkPersistence() {
        return messagingRedirectLinkPersistence;
    }

    public void setMessagingRedirectLinkPersistence(
        MessagingRedirectLinkPersistence messagingRedirectLinkPersistence) {
        this.messagingRedirectLinkPersistence = messagingRedirectLinkPersistence;
    }

    public MessagingIgnoredRecipientsLocalService getMessagingIgnoredRecipientsLocalService() {
        return messagingIgnoredRecipientsLocalService;
    }

    public void setMessagingIgnoredRecipientsLocalService(
        MessagingIgnoredRecipientsLocalService messagingIgnoredRecipientsLocalService) {
        this.messagingIgnoredRecipientsLocalService = messagingIgnoredRecipientsLocalService;
    }

    public MessagingIgnoredRecipientsService getMessagingIgnoredRecipientsService() {
        return messagingIgnoredRecipientsService;
    }

    public void setMessagingIgnoredRecipientsService(
        MessagingIgnoredRecipientsService messagingIgnoredRecipientsService) {
        this.messagingIgnoredRecipientsService = messagingIgnoredRecipientsService;
    }

    public MessagingIgnoredRecipientsPersistence getMessagingIgnoredRecipientsPersistence() {
        return messagingIgnoredRecipientsPersistence;
    }

    public void setMessagingIgnoredRecipientsPersistence(
        MessagingIgnoredRecipientsPersistence messagingIgnoredRecipientsPersistence) {
        this.messagingIgnoredRecipientsPersistence = messagingIgnoredRecipientsPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
