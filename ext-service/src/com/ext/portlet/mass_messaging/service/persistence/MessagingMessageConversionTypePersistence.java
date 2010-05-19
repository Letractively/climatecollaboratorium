/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface MessagingMessageConversionTypePersistence
    extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversionType messagingMessageConversionType);

    public void cacheResult(
        java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversionType> messagingMessageConversionTypes);

    public void clearCache();

    public com.ext.portlet.mass_messaging.model.MessagingMessageConversionType create(
        java.lang.Long typeId);

    public com.ext.portlet.mass_messaging.model.MessagingMessageConversionType remove(
        java.lang.Long typeId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageConversionTypeException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingMessageConversionType remove(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversionType messagingMessageConversionType)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(MessagingMessageConversionType messagingMessageConversionType, boolean merge)</code>.
     */
    public com.ext.portlet.mass_messaging.model.MessagingMessageConversionType update(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversionType messagingMessageConversionType)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                messagingMessageConversionType the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when messagingMessageConversionType is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.mass_messaging.model.MessagingMessageConversionType update(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversionType messagingMessageConversionType,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingMessageConversionType updateImpl(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversionType messagingMessageConversionType,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingMessageConversionType findByPrimaryKey(
        java.lang.Long typeId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageConversionTypeException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingMessageConversionType fetchByPrimaryKey(
        java.lang.Long typeId) throws com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingMessageConversionType findByfindByName(
        java.lang.String name)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageConversionTypeException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingMessageConversionType fetchByfindByName(
        java.lang.String name) throws com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingMessageConversionType fetchByfindByName(
        java.lang.String name, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversionType> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversionType> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversionType> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByfindByName(java.lang.String name)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageConversionTypeException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByfindByName(java.lang.String name)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
