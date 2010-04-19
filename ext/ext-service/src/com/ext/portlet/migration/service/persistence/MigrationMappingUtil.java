/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.service.persistence;

public class MigrationMappingUtil {
    private static MigrationMappingPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.migration.model.MigrationMapping migrationMapping) {
        getPersistence().cacheResult(migrationMapping);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.migration.model.MigrationMapping> migrationMappings) {
        getPersistence().cacheResult(migrationMappings);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.migration.model.MigrationMapping create(
        com.ext.portlet.migration.service.persistence.MigrationMappingPK migrationMappingPK) {
        return getPersistence().create(migrationMappingPK);
    }

    public static com.ext.portlet.migration.model.MigrationMapping remove(
        com.ext.portlet.migration.service.persistence.MigrationMappingPK migrationMappingPK)
        throws com.ext.portlet.migration.NoSuchMappingException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(migrationMappingPK);
    }

    public static com.ext.portlet.migration.model.MigrationMapping remove(
        com.ext.portlet.migration.model.MigrationMapping migrationMapping)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(migrationMapping);
    }

    /**
     * @deprecated Use <code>update(MigrationMapping migrationMapping, boolean merge)</code>.
     */
    public static com.ext.portlet.migration.model.MigrationMapping update(
        com.ext.portlet.migration.model.MigrationMapping migrationMapping)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(migrationMapping);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                migrationMapping the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when migrationMapping is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.migration.model.MigrationMapping update(
        com.ext.portlet.migration.model.MigrationMapping migrationMapping,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(migrationMapping, merge);
    }

    public static com.ext.portlet.migration.model.MigrationMapping updateImpl(
        com.ext.portlet.migration.model.MigrationMapping migrationMapping,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(migrationMapping, merge);
    }

    public static com.ext.portlet.migration.model.MigrationMapping findByPrimaryKey(
        com.ext.portlet.migration.service.persistence.MigrationMappingPK migrationMappingPK)
        throws com.ext.portlet.migration.NoSuchMappingException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(migrationMappingPK);
    }

    public static com.ext.portlet.migration.model.MigrationMapping fetchByPrimaryKey(
        com.ext.portlet.migration.service.persistence.MigrationMappingPK migrationMappingPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(migrationMappingPK);
    }

    public static com.ext.portlet.migration.model.MigrationMapping findByOldId(
        java.lang.String oldId)
        throws com.ext.portlet.migration.NoSuchMappingException,
            com.liferay.portal.SystemException {
        return getPersistence().findByOldId(oldId);
    }

    public static com.ext.portlet.migration.model.MigrationMapping fetchByOldId(
        java.lang.String oldId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByOldId(oldId);
    }

    public static com.ext.portlet.migration.model.MigrationMapping fetchByOldId(
        java.lang.String oldId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByOldId(oldId, retrieveFromCache);
    }

    public static com.ext.portlet.migration.model.MigrationMapping findByNewId(
        java.lang.Long newId)
        throws com.ext.portlet.migration.NoSuchMappingException,
            com.liferay.portal.SystemException {
        return getPersistence().findByNewId(newId);
    }

    public static com.ext.portlet.migration.model.MigrationMapping fetchByNewId(
        java.lang.Long newId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByNewId(newId);
    }

    public static com.ext.portlet.migration.model.MigrationMapping fetchByNewId(
        java.lang.Long newId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByNewId(newId, retrieveFromCache);
    }

    public static java.util.List<com.ext.portlet.migration.model.MigrationMapping> findByentityName(
        java.lang.Long newId) throws com.liferay.portal.SystemException {
        return getPersistence().findByentityName(newId);
    }

    public static java.util.List<com.ext.portlet.migration.model.MigrationMapping> findByentityName(
        java.lang.Long newId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByentityName(newId, start, end);
    }

    public static java.util.List<com.ext.portlet.migration.model.MigrationMapping> findByentityName(
        java.lang.Long newId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByentityName(newId, start, end, obc);
    }

    public static com.ext.portlet.migration.model.MigrationMapping findByentityName_First(
        java.lang.Long newId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.migration.NoSuchMappingException,
            com.liferay.portal.SystemException {
        return getPersistence().findByentityName_First(newId, obc);
    }

    public static com.ext.portlet.migration.model.MigrationMapping findByentityName_Last(
        java.lang.Long newId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.migration.NoSuchMappingException,
            com.liferay.portal.SystemException {
        return getPersistence().findByentityName_Last(newId, obc);
    }

    public static com.ext.portlet.migration.model.MigrationMapping[] findByentityName_PrevAndNext(
        com.ext.portlet.migration.service.persistence.MigrationMappingPK migrationMappingPK,
        java.lang.Long newId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.migration.NoSuchMappingException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByentityName_PrevAndNext(migrationMappingPK, newId, obc);
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

    public static java.util.List<com.ext.portlet.migration.model.MigrationMapping> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.migration.model.MigrationMapping> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.migration.model.MigrationMapping> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByOldId(java.lang.String oldId)
        throws com.ext.portlet.migration.NoSuchMappingException,
            com.liferay.portal.SystemException {
        getPersistence().removeByOldId(oldId);
    }

    public static void removeByNewId(java.lang.Long newId)
        throws com.ext.portlet.migration.NoSuchMappingException,
            com.liferay.portal.SystemException {
        getPersistence().removeByNewId(newId);
    }

    public static void removeByentityName(java.lang.Long newId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByentityName(newId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByOldId(java.lang.String oldId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByOldId(oldId);
    }

    public static int countByNewId(java.lang.Long newId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByNewId(newId);
    }

    public static int countByentityName(java.lang.Long newId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByentityName(newId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static MigrationMappingPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(MigrationMappingPersistence persistence) {
        _persistence = persistence;
    }
}
