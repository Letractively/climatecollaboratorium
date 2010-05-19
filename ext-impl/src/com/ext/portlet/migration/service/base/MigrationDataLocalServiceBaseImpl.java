/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.service.base;

import com.ext.portlet.migration.model.MigrationData;
import com.ext.portlet.migration.service.MigrationDataLocalService;
import com.ext.portlet.migration.service.MigrationDataService;
import com.ext.portlet.migration.service.MigrationMappingLocalService;
import com.ext.portlet.migration.service.MigrationMappingService;
import com.ext.portlet.migration.service.persistence.MigrationDataPersistence;
import com.ext.portlet.migration.service.persistence.MigrationMappingPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class MigrationDataLocalServiceBaseImpl
    implements MigrationDataLocalService {
    @BeanReference(name = "com.ext.portlet.migration.service.MigrationDataLocalService.impl")
    protected MigrationDataLocalService migrationDataLocalService;
    @BeanReference(name = "com.ext.portlet.migration.service.MigrationDataService.impl")
    protected MigrationDataService migrationDataService;
    @BeanReference(name = "com.ext.portlet.migration.service.persistence.MigrationDataPersistence.impl")
    protected MigrationDataPersistence migrationDataPersistence;
    @BeanReference(name = "com.ext.portlet.migration.service.MigrationMappingLocalService.impl")
    protected MigrationMappingLocalService migrationMappingLocalService;
    @BeanReference(name = "com.ext.portlet.migration.service.MigrationMappingService.impl")
    protected MigrationMappingService migrationMappingService;
    @BeanReference(name = "com.ext.portlet.migration.service.persistence.MigrationMappingPersistence.impl")
    protected MigrationMappingPersistence migrationMappingPersistence;

    public MigrationData addMigrationData(MigrationData migrationData)
        throws SystemException {
        migrationData.setNew(true);

        return migrationDataPersistence.update(migrationData, false);
    }

    public MigrationData createMigrationData(Long migrationId) {
        return migrationDataPersistence.create(migrationId);
    }

    public void deleteMigrationData(Long migrationId)
        throws PortalException, SystemException {
        migrationDataPersistence.remove(migrationId);
    }

    public void deleteMigrationData(MigrationData migrationData)
        throws SystemException {
        migrationDataPersistence.remove(migrationData);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return migrationDataPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return migrationDataPersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    public MigrationData getMigrationData(Long migrationId)
        throws PortalException, SystemException {
        return migrationDataPersistence.findByPrimaryKey(migrationId);
    }

    public List<MigrationData> getMigrationDatas(int start, int end)
        throws SystemException {
        return migrationDataPersistence.findAll(start, end);
    }

    public int getMigrationDatasCount() throws SystemException {
        return migrationDataPersistence.countAll();
    }

    public MigrationData updateMigrationData(MigrationData migrationData)
        throws SystemException {
        migrationData.setNew(false);

        return migrationDataPersistence.update(migrationData, true);
    }

    public MigrationData updateMigrationData(MigrationData migrationData,
        boolean merge) throws SystemException {
        migrationData.setNew(false);

        return migrationDataPersistence.update(migrationData, merge);
    }

    public MigrationDataLocalService getMigrationDataLocalService() {
        return migrationDataLocalService;
    }

    public void setMigrationDataLocalService(
        MigrationDataLocalService migrationDataLocalService) {
        this.migrationDataLocalService = migrationDataLocalService;
    }

    public MigrationDataService getMigrationDataService() {
        return migrationDataService;
    }

    public void setMigrationDataService(
        MigrationDataService migrationDataService) {
        this.migrationDataService = migrationDataService;
    }

    public MigrationDataPersistence getMigrationDataPersistence() {
        return migrationDataPersistence;
    }

    public void setMigrationDataPersistence(
        MigrationDataPersistence migrationDataPersistence) {
        this.migrationDataPersistence = migrationDataPersistence;
    }

    public MigrationMappingLocalService getMigrationMappingLocalService() {
        return migrationMappingLocalService;
    }

    public void setMigrationMappingLocalService(
        MigrationMappingLocalService migrationMappingLocalService) {
        this.migrationMappingLocalService = migrationMappingLocalService;
    }

    public MigrationMappingService getMigrationMappingService() {
        return migrationMappingService;
    }

    public void setMigrationMappingService(
        MigrationMappingService migrationMappingService) {
        this.migrationMappingService = migrationMappingService;
    }

    public MigrationMappingPersistence getMigrationMappingPersistence() {
        return migrationMappingPersistence;
    }

    public void setMigrationMappingPersistence(
        MigrationMappingPersistence migrationMappingPersistence) {
        this.migrationMappingPersistence = migrationMappingPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
