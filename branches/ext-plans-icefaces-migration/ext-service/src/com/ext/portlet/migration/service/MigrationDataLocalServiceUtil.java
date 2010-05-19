/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.service;


/**
 * <a href="MigrationDataLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.migration.service.MigrationDataLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.migration.service.MigrationDataLocalService
 *
 */
public class MigrationDataLocalServiceUtil {
    private static MigrationDataLocalService _service;

    public static com.ext.portlet.migration.model.MigrationData addMigrationData(
        com.ext.portlet.migration.model.MigrationData migrationData)
        throws com.liferay.portal.SystemException {
        return getService().addMigrationData(migrationData);
    }

    public static com.ext.portlet.migration.model.MigrationData createMigrationData(
        java.lang.Long migrationId) {
        return getService().createMigrationData(migrationId);
    }

    public static void deleteMigrationData(java.lang.Long migrationId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteMigrationData(migrationId);
    }

    public static void deleteMigrationData(
        com.ext.portlet.migration.model.MigrationData migrationData)
        throws com.liferay.portal.SystemException {
        getService().deleteMigrationData(migrationData);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    public static com.ext.portlet.migration.model.MigrationData getMigrationData(
        java.lang.Long migrationId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getMigrationData(migrationId);
    }

    public static java.util.List<com.ext.portlet.migration.model.MigrationData> getMigrationDatas(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getMigrationDatas(start, end);
    }

    public static int getMigrationDatasCount()
        throws com.liferay.portal.SystemException {
        return getService().getMigrationDatasCount();
    }

    public static com.ext.portlet.migration.model.MigrationData updateMigrationData(
        com.ext.portlet.migration.model.MigrationData migrationData)
        throws com.liferay.portal.SystemException {
        return getService().updateMigrationData(migrationData);
    }

    public static com.ext.portlet.migration.model.MigrationData updateMigrationData(
        com.ext.portlet.migration.model.MigrationData migrationData,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateMigrationData(migrationData, merge);
    }

    public static MigrationDataLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("MigrationDataLocalService is not set");
        }

        return _service;
    }

    public void setService(MigrationDataLocalService service) {
        _service = service;
    }
}
