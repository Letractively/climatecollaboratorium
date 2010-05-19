/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface MessagingMessagePersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.mass_messaging.model.MessagingMessage messagingMessage);

    public void cacheResult(
        java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessage> messagingMessages);

    public void clearCache();

    public com.ext.portlet.mass_messaging.model.MessagingMessage create(
        java.lang.Long messageId);

    public com.ext.portlet.mass_messaging.model.MessagingMessage remove(
        java.lang.Long messageId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingMessage remove(
        com.ext.portlet.mass_messaging.model.MessagingMessage messagingMessage)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(MessagingMessage messagingMessage, boolean merge)</code>.
     */
    public com.ext.portlet.mass_messaging.model.MessagingMessage update(
        com.ext.portlet.mass_messaging.model.MessagingMessage messagingMessage)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                messagingMessage the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when messagingMessage is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.mass_messaging.model.MessagingMessage update(
        com.ext.portlet.mass_messaging.model.MessagingMessage messagingMessage,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingMessage updateImpl(
        com.ext.portlet.mass_messaging.model.MessagingMessage messagingMessage,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingMessage findByPrimaryKey(
        java.lang.Long messageId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingMessage fetchByPrimaryKey(
        java.lang.Long messageId) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessage> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessage> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessage> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
