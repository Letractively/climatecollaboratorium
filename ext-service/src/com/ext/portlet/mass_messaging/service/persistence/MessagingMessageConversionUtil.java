/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.persistence;

public class MessagingMessageConversionUtil {
    private static MessagingMessageConversionPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversion messagingMessageConversion) {
        getPersistence().cacheResult(messagingMessageConversion);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversion> messagingMessageConversions) {
        getPersistence().cacheResult(messagingMessageConversions);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversion create(
        java.lang.Long conversionId) {
        return getPersistence().create(conversionId);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversion remove(
        java.lang.Long conversionId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageConversionException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(conversionId);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversion remove(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversion messagingMessageConversion)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(messagingMessageConversion);
    }

    /**
     * @deprecated Use <code>update(MessagingMessageConversion messagingMessageConversion, boolean merge)</code>.
     */
    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversion update(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversion messagingMessageConversion)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(messagingMessageConversion);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                messagingMessageConversion the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when messagingMessageConversion is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversion update(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversion messagingMessageConversion,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(messagingMessageConversion, merge);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversion updateImpl(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversion messagingMessageConversion,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(messagingMessageConversion, merge);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversion findByPrimaryKey(
        java.lang.Long conversionId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageConversionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(conversionId);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversion fetchByPrimaryKey(
        java.lang.Long conversionId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(conversionId);
    }

    public static java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversion> findByfindByType(
        java.lang.Long messageId, java.lang.Long conversionTypeId)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByfindByType(messageId, conversionTypeId);
    }

    public static java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversion> findByfindByType(
        java.lang.Long messageId, java.lang.Long conversionTypeId, int start,
        int end) throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByfindByType(messageId, conversionTypeId, start, end);
    }

    public static java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversion> findByfindByType(
        java.lang.Long messageId, java.lang.Long conversionTypeId, int start,
        int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByfindByType(messageId, conversionTypeId, start, end,
            obc);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversion findByfindByType_First(
        java.lang.Long messageId, java.lang.Long conversionTypeId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageConversionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByfindByType_First(messageId, conversionTypeId, obc);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversion findByfindByType_Last(
        java.lang.Long messageId, java.lang.Long conversionTypeId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageConversionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByfindByType_Last(messageId, conversionTypeId, obc);
    }

    public static com.ext.portlet.mass_messaging.model.MessagingMessageConversion[] findByfindByType_PrevAndNext(
        java.lang.Long conversionId, java.lang.Long messageId,
        java.lang.Long conversionTypeId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageConversionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByfindByType_PrevAndNext(conversionId, messageId,
            conversionTypeId, obc);
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

    public static java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversion> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversion> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversion> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByfindByType(java.lang.Long messageId,
        java.lang.Long conversionTypeId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByfindByType(messageId, conversionTypeId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByfindByType(java.lang.Long messageId,
        java.lang.Long conversionTypeId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByfindByType(messageId, conversionTypeId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static MessagingMessageConversionPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(
        MessagingMessageConversionPersistence persistence) {
        _persistence = persistence;
    }
}
