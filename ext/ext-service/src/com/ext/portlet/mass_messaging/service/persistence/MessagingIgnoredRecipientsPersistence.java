/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface MessagingIgnoredRecipientsPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients messagingIgnoredRecipients);

    public void cacheResult(
        java.util.List<com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients> messagingIgnoredRecipientses);

    public void clearCache();

    public com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients create(
        java.lang.Long ignoredRecipientId);

    public com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients remove(
        java.lang.Long ignoredRecipientId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients remove(
        com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients messagingIgnoredRecipients)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(MessagingIgnoredRecipients messagingIgnoredRecipients, boolean merge)</code>.
     */
    public com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients update(
        com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients messagingIgnoredRecipients)
        throws com.liferay.portal.SystemException;

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
    public com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients update(
        com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients messagingIgnoredRecipients,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients updateImpl(
        com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients messagingIgnoredRecipients,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients findByPrimaryKey(
        java.lang.Long ignoredRecipientId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients fetchByPrimaryKey(
        java.lang.Long ignoredRecipientId)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients findByfindByEmail(
        java.lang.String email)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients fetchByfindByEmail(
        java.lang.String email) throws com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients fetchByfindByEmail(
        java.lang.String email, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients findByfindByUserId(
        java.lang.Long userId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients fetchByfindByUserId(
        java.lang.Long userId) throws com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients fetchByfindByUserId(
        java.lang.Long userId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByfindByEmail(java.lang.String email)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.SystemException;

    public void removeByfindByUserId(java.lang.Long userId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByfindByEmail(java.lang.String email)
        throws com.liferay.portal.SystemException;

    public int countByfindByUserId(java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
