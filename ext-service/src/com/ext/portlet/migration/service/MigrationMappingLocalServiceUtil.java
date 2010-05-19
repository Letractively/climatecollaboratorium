/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.service;


/**
 * <a href="MigrationMappingLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.migration.service.MigrationMappingLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.migration.service.MigrationMappingLocalService
 *
 */
public class MigrationMappingLocalServiceUtil {
    private static MigrationMappingLocalService _service;

    public static com.ext.portlet.migration.model.MigrationMapping addMigrationMapping(
        com.ext.portlet.migration.model.MigrationMapping migrationMapping)
        throws com.liferay.portal.SystemException {
        return getService().addMigrationMapping(migrationMapping);
    }

    public static com.ext.portlet.migration.model.MigrationMapping createMigrationMapping(
        com.ext.portlet.migration.service.persistence.MigrationMappingPK migrationMappingPK) {
        return getService().createMigrationMapping(migrationMappingPK);
    }

    public static void deleteMigrationMapping(
        com.ext.portlet.migration.service.persistence.MigrationMappingPK migrationMappingPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteMigrationMapping(migrationMappingPK);
    }

    public static void deleteMigrationMapping(
        com.ext.portlet.migration.model.MigrationMapping migrationMapping)
        throws com.liferay.portal.SystemException {
        getService().deleteMigrationMapping(migrationMapping);
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

    public static com.ext.portlet.migration.model.MigrationMapping getMigrationMapping(
        com.ext.portlet.migration.service.persistence.MigrationMappingPK migrationMappingPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getMigrationMapping(migrationMappingPK);
    }

    public static java.util.List<com.ext.portlet.migration.model.MigrationMapping> getMigrationMappings(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getMigrationMappings(start, end);
    }

    public static int getMigrationMappingsCount()
        throws com.liferay.portal.SystemException {
        return getService().getMigrationMappingsCount();
    }

    public static com.ext.portlet.migration.model.MigrationMapping updateMigrationMapping(
        com.ext.portlet.migration.model.MigrationMapping migrationMapping)
        throws com.liferay.portal.SystemException {
        return getService().updateMigrationMapping(migrationMapping);
    }

    public static com.ext.portlet.migration.model.MigrationMapping updateMigrationMapping(
        com.ext.portlet.migration.model.MigrationMapping migrationMapping,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateMigrationMapping(migrationMapping, merge);
    }

    public static MigrationMappingLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "MigrationMappingLocalService is not set");
        }

        return _service;
    }

    public void setService(MigrationMappingLocalService service) {
        _service = service;
    }
}
