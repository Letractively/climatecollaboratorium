/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface MessagingRedirectLinkPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.mass_messaging.model.MessagingRedirectLink messagingRedirectLink);

    public void cacheResult(
        java.util.List<com.ext.portlet.mass_messaging.model.MessagingRedirectLink> messagingRedirectLinks);

    public void clearCache();

    public com.ext.portlet.mass_messaging.model.MessagingRedirectLink create(
        java.lang.Long redirectId);

    public com.ext.portlet.mass_messaging.model.MessagingRedirectLink remove(
        java.lang.Long redirectId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingRedirectLinkException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingRedirectLink remove(
        com.ext.portlet.mass_messaging.model.MessagingRedirectLink messagingRedirectLink)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(MessagingRedirectLink messagingRedirectLink, boolean merge)</code>.
     */
    public com.ext.portlet.mass_messaging.model.MessagingRedirectLink update(
        com.ext.portlet.mass_messaging.model.MessagingRedirectLink messagingRedirectLink)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                messagingRedirectLink the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when messagingRedirectLink is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.mass_messaging.model.MessagingRedirectLink update(
        com.ext.portlet.mass_messaging.model.MessagingRedirectLink messagingRedirectLink,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingRedirectLink updateImpl(
        com.ext.portlet.mass_messaging.model.MessagingRedirectLink messagingRedirectLink,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingRedirectLink findByPrimaryKey(
        java.lang.Long redirectId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingRedirectLinkException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingRedirectLink fetchByPrimaryKey(
        java.lang.Long redirectId) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.mass_messaging.model.MessagingRedirectLink> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.mass_messaging.model.MessagingRedirectLink> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.mass_messaging.model.MessagingRedirectLink> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
