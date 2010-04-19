/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.service.persistence;

public class ModelsFilterPositionUtil {
    private static ModelsFilterPositionPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.models.model.ModelsFilterPosition modelsFilterPosition) {
        getPersistence().cacheResult(modelsFilterPosition);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelsFilterPosition> modelsFilterPositions) {
        getPersistence().cacheResult(modelsFilterPositions);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.models.model.ModelsFilterPosition create(
        ModelsFilterPositionPK modelsFilterPositionPK) {
        return getPersistence().create(modelsFilterPositionPK);
    }

    public static com.ext.portlet.models.model.ModelsFilterPosition remove(
        ModelsFilterPositionPK modelsFilterPositionPK)
        throws com.ext.portlet.models.NoSuchFilterPositionException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(modelsFilterPositionPK);
    }

    public static com.ext.portlet.models.model.ModelsFilterPosition remove(
        com.ext.portlet.models.model.ModelsFilterPosition modelsFilterPosition)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(modelsFilterPosition);
    }

    /**
     * @deprecated Use <code>update(ModelsFilterPosition modelsFilterPosition, boolean merge)</code>.
     */
    public static com.ext.portlet.models.model.ModelsFilterPosition update(
        com.ext.portlet.models.model.ModelsFilterPosition modelsFilterPosition)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(modelsFilterPosition);
    }

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
    public static com.ext.portlet.models.model.ModelsFilterPosition update(
        com.ext.portlet.models.model.ModelsFilterPosition modelsFilterPosition,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(modelsFilterPosition, merge);
    }

    public static com.ext.portlet.models.model.ModelsFilterPosition updateImpl(
        com.ext.portlet.models.model.ModelsFilterPosition modelsFilterPosition,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(modelsFilterPosition, merge);
    }

    public static com.ext.portlet.models.model.ModelsFilterPosition findByPrimaryKey(
        ModelsFilterPositionPK modelsFilterPositionPK)
        throws com.ext.portlet.models.NoSuchFilterPositionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(modelsFilterPositionPK);
    }

    public static com.ext.portlet.models.model.ModelsFilterPosition fetchByPrimaryKey(
        ModelsFilterPositionPK modelsFilterPositionPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(modelsFilterPositionPK);
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

    public static java.util.List<com.ext.portlet.models.model.ModelsFilterPosition> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.models.model.ModelsFilterPosition> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelsFilterPosition> findAll(
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

    public static ModelsFilterPositionPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ModelsFilterPositionPersistence persistence) {
        _persistence = persistence;
    }
}
