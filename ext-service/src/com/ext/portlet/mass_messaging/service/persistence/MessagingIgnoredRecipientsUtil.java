/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.persistence;

public class MessagingIgnoredRecipientsUtil {
    private static MessagingIgnoredRecipientsPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients messagingIgnoredRecipients) {
        getPersistence().cacheResult(messagingIgnoredRecipients);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients> messagingIgnoredRecipientses) {
        getPersistence().cacheResult(messagingIgnoredRecipientses);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients create(
        java.lang.Long ignoredRecipientId) {
        return getPersistence().create(ignoredRecipientId);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients remove(
        java.lang.Long ignoredRecipientId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(ignoredRecipientId);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients remove(
        com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients messagingIgnoredRecipients)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(messagingIgnoredRecipients);
    }

    /**
     * @deprecated Use <code>update(MessagingIgnoredRecipients messagingIgnoredRecipients, boolean merge)</code>.
     */
    public static com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients update(
        com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients messagingIgnoredRecipients)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(messagingIgnoredRecipients);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                messagingIgnoredRecipients the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when messagingIgnoredRecipients is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients update(
        com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients messagingIgnoredRecipients,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(messagingIgnoredRecipients, merge);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients updateImpl(
        com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients messagingIgnoredRecipients,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(messagingIgnoredRecipients, merge);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients findByPrimaryKey(
        java.lang.Long ignoredRecipientId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(ignoredRecipientId);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients fetchByPrimaryKey(
        java.lang.Long ignoredRecipientId)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(ignoredRecipientId);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients findByfindByEmail(
        java.lang.String email)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.SystemException {
        return getPersistence().findByfindByEmail(email);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients fetchByfindByEmail(
        java.lang.String email) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByfindByEmail(email);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients fetchByfindByEmail(
        java.lang.String email, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByfindByEmail(email, retrieveFromCache);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients findByfindByUserId(
        java.lang.Long userId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.SystemException {
        return getPersistence().findByfindByUserId(userId);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients fetchByfindByUserId(
        java.lang.Long userId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByfindByUserId(userId);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients fetchByfindByUserId(
        java.lang.Long userId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByfindByUserId(userId, retrieveFromCache);
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

    public static java.util.List<com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByfindByEmail(java.lang.String email)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.SystemException {
        getPersistence().removeByfindByEmail(email);
    }

    public static void removeByfindByUserId(java.lang.Long userId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.SystemException {
        getPersistence().removeByfindByUserId(userId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByfindByEmail(java.lang.String email)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByfindByEmail(email);
    }

    public static int countByfindByUserId(java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByfindByUserId(userId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static MessagingIgnoredRecipientsPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(
        MessagingIgnoredRecipientsPersistence persistence) {
        _persistence = persistence;
    }
}
