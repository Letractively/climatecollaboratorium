/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface MigrationMappingPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.migration.model.MigrationMapping migrationMapping);

    public void cacheResult(
        java.util.List<com.ext.portlet.migration.model.MigrationMapping> migrationMappings);

    public void clearCache();

    public com.ext.portlet.migration.model.MigrationMapping create(
        com.ext.portlet.migration.service.persistence.MigrationMappingPK migrationMappingPK);

    public com.ext.portlet.migration.model.MigrationMapping remove(
        com.ext.portlet.migration.service.persistence.MigrationMappingPK migrationMappingPK)
        throws com.ext.portlet.migration.NoSuchMappingException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.migration.model.MigrationMapping remove(
        com.ext.portlet.migration.model.MigrationMapping migrationMapping)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(MigrationMapping migrationMapping, boolean merge)</code>.
     */
    public com.ext.portlet.migration.model.MigrationMapping update(
        com.ext.portlet.migration.model.MigrationMapping migrationMapping)
        throws com.liferay.portal.SystemException;

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
    public com.ext.portlet.migration.model.MigrationMapping update(
        com.ext.portlet.migration.model.MigrationMapping migrationMapping,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.migration.model.MigrationMapping updateImpl(
        com.ext.portlet.migration.model.MigrationMapping migrationMapping,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.migration.model.MigrationMapping findByPrimaryKey(
        com.ext.portlet.migration.service.persistence.MigrationMappingPK migrationMappingPK)
        throws com.ext.portlet.migration.NoSuchMappingException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.migration.model.MigrationMapping fetchByPrimaryKey(
        com.ext.portlet.migration.service.persistence.MigrationMappingPK migrationMappingPK)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.migration.model.MigrationMapping findByOldId(
        java.lang.String oldId)
        throws com.ext.portlet.migration.NoSuchMappingException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.migration.model.MigrationMapping fetchByOldId(
        java.lang.String oldId) throws com.liferay.portal.SystemException;

    public com.ext.portlet.migration.model.MigrationMapping fetchByOldId(
        java.lang.String oldId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.migration.model.MigrationMapping findByNewId(
        java.lang.Long newId)
        throws com.ext.portlet.migration.NoSuchMappingException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.migration.model.MigrationMapping fetchByNewId(
        java.lang.Long newId) throws com.liferay.portal.SystemException;

    public com.ext.portlet.migration.model.MigrationMapping fetchByNewId(
        java.lang.Long newId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.migration.model.MigrationMapping> findByentityName(
        java.lang.Long newId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.migration.model.MigrationMapping> findByentityName(
        java.lang.Long newId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.migration.model.MigrationMapping> findByentityName(
        java.lang.Long newId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.migration.model.MigrationMapping findByentityName_First(
        java.lang.Long newId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.migration.NoSuchMappingException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.migration.model.MigrationMapping findByentityName_Last(
        java.lang.Long newId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.migration.NoSuchMappingException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.migration.model.MigrationMapping[] findByentityName_PrevAndNext(
        com.ext.portlet.migration.service.persistence.MigrationMappingPK migrationMappingPK,
        java.lang.Long newId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.migration.NoSuchMappingException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.migration.model.MigrationMapping> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.migration.model.MigrationMapping> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.migration.model.MigrationMapping> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByOldId(java.lang.String oldId)
        throws com.ext.portlet.migration.NoSuchMappingException,
            com.liferay.portal.SystemException;

    public void removeByNewId(java.lang.Long newId)
        throws com.ext.portlet.migration.NoSuchMappingException,
            com.liferay.portal.SystemException;

    public void removeByentityName(java.lang.Long newId)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByOldId(java.lang.String oldId)
        throws com.liferay.portal.SystemException;

    public int countByNewId(java.lang.Long newId)
        throws com.liferay.portal.SystemException;

    public int countByentityName(java.lang.Long newId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
