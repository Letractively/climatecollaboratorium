/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.persistence;

public class MessagingMessageRecipientUtil {
    private static MessagingMessageRecipientPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.mass_messaging.model.MessagingMessageRecipient messagingMessageRecipient) {
        getPersistence().cacheResult(messagingMessageRecipient);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageRecipient> messagingMessageRecipients) {
        getPersistence().cacheResult(messagingMessageRecipients);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageRecipient create(
        java.lang.Long recipientId) {
        return getPersistence().create(recipientId);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageRecipient remove(
        java.lang.Long recipientId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageRecipientException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(recipientId);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageRecipient remove(
        com.ext.portlet.mass_messaging.model.MessagingMessageRecipient messagingMessageRecipient)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(messagingMessageRecipient);
    }

    /**
     * @deprecated Use <code>update(MessagingMessageRecipient messagingMessageRecipient, boolean merge)</code>.
     */
    public static com.ext.portlet.mass_messaging.model.MessagingMessageRecipient update(
        com.ext.portlet.mass_messaging.model.MessagingMessageRecipient messagingMessageRecipient)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(messagingMessageRecipient);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                messagingMessageRecipient the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when messagingMessageRecipient is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.mass_messaging.model.MessagingMessageRecipient update(
        com.ext.portlet.mass_messaging.model.MessagingMessageRecipient messagingMessageRecipient,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(messagingMessageRecipient, merge);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageRecipient updateImpl(
        com.ext.portlet.mass_messaging.model.MessagingMessageRecipient messagingMessageRecipient,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(messagingMessageRecipient, merge);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageRecipient findByPrimaryKey(
        java.lang.Long recipientId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageRecipientException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(recipientId);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageRecipient fetchByPrimaryKey(
        java.lang.Long recipientId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(recipientId);
    }

    public static java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    public static java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    public static java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageRecipient> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageRecipient> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageRecipient> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static MessagingMessageRecipientPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(MessagingMessageRecipientPersistence persistence) {
        _persistence = persistence;
    }
}
