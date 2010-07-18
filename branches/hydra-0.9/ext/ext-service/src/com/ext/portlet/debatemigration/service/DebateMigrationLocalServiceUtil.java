package com.ext.portlet.debatemigration.service;


/**
 * <a href="DebateMigrationLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.debatemigration.service.DebateMigrationLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.service.DebateMigrationLocalService
 *
 */
public class DebateMigrationLocalServiceUtil {
    private static DebateMigrationLocalService _service;

    public static com.ext.portlet.debatemigration.model.DebateMigration addDebateMigration(
        com.ext.portlet.debatemigration.model.DebateMigration debateMigration)
        throws com.liferay.portal.SystemException {
        return getService().addDebateMigration(debateMigration);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigration createDebateMigration(
        java.lang.Long debateMigrationPK) {
        return getService().createDebateMigration(debateMigrationPK);
    }

    public static void deleteDebateMigration(java.lang.Long debateMigrationPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteDebateMigration(debateMigrationPK);
    }

    public static void deleteDebateMigration(
        com.ext.portlet.debatemigration.model.DebateMigration debateMigration)
        throws com.liferay.portal.SystemException {
        getService().deleteDebateMigration(debateMigration);
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

    public static com.ext.portlet.debatemigration.model.DebateMigration getDebateMigration(
        java.lang.Long debateMigrationPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getDebateMigration(debateMigrationPK);
    }

    public static java.util.List<com.ext.portlet.debatemigration.model.DebateMigration> getDebateMigrations(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getDebateMigrations(start, end);
    }

    public static int getDebateMigrationsCount()
        throws com.liferay.portal.SystemException {
        return getService().getDebateMigrationsCount();
    }

    public static com.ext.portlet.debatemigration.model.DebateMigration updateDebateMigration(
        com.ext.portlet.debatemigration.model.DebateMigration debateMigration)
        throws com.liferay.portal.SystemException {
        return getService().updateDebateMigration(debateMigration);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigration updateDebateMigration(
        com.ext.portlet.debatemigration.model.DebateMigration debateMigration,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateDebateMigration(debateMigration, merge);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigration addMigration()
        throws com.liferay.portal.SystemException {
        return getService().addMigration();
    }

    public static DebateMigrationLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("DebateMigrationLocalService is not set");
        }

        return _service;
    }

    public void setService(DebateMigrationLocalService service) {
        _service = service;
    }
}
