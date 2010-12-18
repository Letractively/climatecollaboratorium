/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface MessagingMessageConversionPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversion messagingMessageConversion);

    public void cacheResult(
        java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversion> messagingMessageConversions);

    public void clearCache();

    public com.ext.portlet.mass_messaging.model.MessagingMessageConversion create(
        java.lang.Long conversionId);

    public com.ext.portlet.mass_messaging.model.MessagingMessageConversion remove(
        java.lang.Long conversionId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageConversionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingMessageConversion remove(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversion messagingMessageConversion)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(MessagingMessageConversion messagingMessageConversion, boolean merge)</code>.
     */
    public com.ext.portlet.mass_messaging.model.MessagingMessageConversion update(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversion messagingMessageConversion)
        throws com.liferay.portal.SystemException;

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
    public com.ext.portlet.mass_messaging.model.MessagingMessageConversion update(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversion messagingMessageConversion,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingMessageConversion updateImpl(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversion messagingMessageConversion,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingMessageConversion findByPrimaryKey(
        java.lang.Long conversionId)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageConversionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingMessageConversion fetchByPrimaryKey(
        java.lang.Long conversionId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversion> findByfindByType(
        java.lang.Long messageId, java.lang.Long conversionTypeId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversion> findByfindByType(
        java.lang.Long messageId, java.lang.Long conversionTypeId, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversion> findByfindByType(
        java.lang.Long messageId, java.lang.Long conversionTypeId, int start,
        int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingMessageConversion findByfindByType_First(
        java.lang.Long messageId, java.lang.Long conversionTypeId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageConversionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingMessageConversion findByfindByType_Last(
        java.lang.Long messageId, java.lang.Long conversionTypeId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageConversionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.mass_messaging.model.MessagingMessageConversion[] findByfindByType_PrevAndNext(
        java.lang.Long conversionId, java.lang.Long messageId,
        java.lang.Long conversionTypeId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.mass_messaging.NoSuchMessagingMessageConversionException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversion> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversion> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.mass_messaging.model.MessagingMessageConversion> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByfindByType(java.lang.Long messageId,
        java.lang.Long conversionTypeId)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByfindByType(java.lang.Long messageId,
        java.lang.Long conversionTypeId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
