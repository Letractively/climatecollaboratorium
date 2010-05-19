package com.ext.portlet.debatemigration.service;


/**
 * <a href="DebateMigrationItemLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.debatemigration.service.DebateMigrationItemLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.service.DebateMigrationItemLocalService
 *
 */
public class DebateMigrationItemLocalServiceUtil {
    private static DebateMigrationItemLocalService _service;

    public static com.ext.portlet.debatemigration.model.DebateMigrationItem addDebateMigrationItem(
        com.ext.portlet.debatemigration.model.DebateMigrationItem debateMigrationItem)
        throws com.liferay.portal.SystemException {
        return getService().addDebateMigrationItem(debateMigrationItem);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationItem createDebateMigrationItem(
        java.lang.Long debateMigrationItemPK) {
        return getService().createDebateMigrationItem(debateMigrationItemPK);
    }

    public static void deleteDebateMigrationItem(
        java.lang.Long debateMigrationItemPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteDebateMigrationItem(debateMigrationItemPK);
    }

    public static void deleteDebateMigrationItem(
        com.ext.portlet.debatemigration.model.DebateMigrationItem debateMigrationItem)
        throws com.liferay.portal.SystemException {
        getService().deleteDebateMigrationItem(debateMigrationItem);
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

    public static com.ext.portlet.debatemigration.model.DebateMigrationItem getDebateMigrationItem(
        java.lang.Long debateMigrationItemPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getDebateMigrationItem(debateMigrationItemPK);
    }

    public static java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationItem> getDebateMigrationItems(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getDebateMigrationItems(start, end);
    }

    public static int getDebateMigrationItemsCount()
        throws com.liferay.portal.SystemException {
        return getService().getDebateMigrationItemsCount();
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationItem updateDebateMigrationItem(
        com.ext.portlet.debatemigration.model.DebateMigrationItem debateMigrationItem)
        throws com.liferay.portal.SystemException {
        return getService().updateDebateMigrationItem(debateMigrationItem);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationItem updateDebateMigrationItem(
        com.ext.portlet.debatemigration.model.DebateMigrationItem debateMigrationItem,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateDebateMigrationItem(debateMigrationItem, merge);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationItem findItemMigration(
        java.lang.Long migrationId, java.lang.Long mbmessageid) {
        return getService().findItemMigration(migrationId, mbmessageid);
    }

    public static DebateMigrationItemLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "DebateMigrationItemLocalService is not set");
        }

        return _service;
    }

    public void setService(DebateMigrationItemLocalService service) {
        _service = service;
    }
}
