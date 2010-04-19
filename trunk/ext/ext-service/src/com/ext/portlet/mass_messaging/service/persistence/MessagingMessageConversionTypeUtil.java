/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.persistence;

public class MessagingMessageConversionTypeUtil {
    private static MessagingMessageConversionTypePersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversionType messagingMessageConversionType) {
        getPersistence().cacheResult(messagingMessageConversionType);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversionType> messagingMessageConversionTypes) {
        getPersistence().cacheResult(messagingMessageConversionTypes);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversionType create(
        java.lang.Long typeId) {
        return getPersistence().create(typeId);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversionType remove(
        java.lang.Long typeId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageConversionTypeException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(typeId);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversionType remove(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversionType messagingMessageConversionType)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(messagingMessageConversionType);
    }

    /**
     * @deprecated Use <code>update(MessagingMessageConversionType messagingMessageConversionType, boolean merge)</code>.
     */
    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversionType update(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversionType messagingMessageConversionType)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(messagingMessageConversionType);
    }

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
    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversionType update(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversionType messagingMessageConversionType,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(messagingMessageConversionType, merge);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversionType updateImpl(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversionType messagingMessageConversionType,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(messagingMessageConversionType, merge);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversionType findByPrimaryKey(
        java.lang.Long typeId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageConversionTypeException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(typeId);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversionType fetchByPrimaryKey(
        java.lang.Long typeId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(typeId);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversionType findByfindByName(
        java.lang.String name)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageConversionTypeException,
            com.liferay.portal.SystemException {
        return getPersistence().findByfindByName(name);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversionType fetchByfindByName(
        java.lang.String name) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByfindByName(name);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversionType fetchByfindByName(
        java.lang.String name, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByfindByName(name, retrieveFromCache);
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

    public static java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversionType> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversionType> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversionType> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByfindByName(java.lang.String name)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageConversionTypeException,
            com.liferay.portal.SystemException {
        getPersistence().removeByfindByName(name);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByfindByName(java.lang.String name)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByfindByName(name);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static MessagingMessageConversionTypePersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(
        MessagingMessageConversionTypePersistence persistence) {
        _persistence = persistence;
    }
}
