/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface ModelsFilterPositionPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.models.model.ModelsFilterPosition modelsFilterPosition);

    public void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelsFilterPosition> modelsFilterPositions);

    public void clearCache();

    public com.ext.portlet.models.model.ModelsFilterPosition create(
        ModelsFilterPositionPK modelsFilterPositionPK);

    public com.ext.portlet.models.model.ModelsFilterPosition remove(
        ModelsFilterPositionPK modelsFilterPositionPK)
        throws com.ext.portlet.models.NoSuchFilterPositionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelsFilterPosition remove(
        com.ext.portlet.models.model.ModelsFilterPosition modelsFilterPosition)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(ModelsFilterPosition modelsFilterPosition, boolean merge)</code>.
     */
    public com.ext.portlet.models.model.ModelsFilterPosition update(
        com.ext.portlet.models.model.ModelsFilterPosition modelsFilterPosition)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelsFilterPosition the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelsFilterPosition is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.models.model.ModelsFilterPosition update(
        com.ext.portlet.models.model.ModelsFilterPosition modelsFilterPosition,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelsFilterPosition updateImpl(
        com.ext.portlet.models.model.ModelsFilterPosition modelsFilterPosition,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelsFilterPosition findByPrimaryKey(
        ModelsFilterPositionPK modelsFilterPositionPK)
        throws com.ext.portlet.models.NoSuchFilterPositionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelsFilterPosition fetchByPrimaryKey(
        ModelsFilterPositionPK modelsFilterPositionPK)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelsFilterPosition> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelsFilterPosition> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelsFilterPosition> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
