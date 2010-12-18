package com.ext.portlet.debatemigration.service;


/**
 * <a href="DebateMigrationCategoryLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.debatemigration.service.DebateMigrationCategoryLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.service.DebateMigrationCategoryLocalService
 *
 */
public class DebateMigrationCategoryLocalServiceUtil {
    private static DebateMigrationCategoryLocalService _service;

    public static com.ext.portlet.debatemigration.model.DebateMigrationCategory addDebateMigrationCategory(
        com.ext.portlet.debatemigration.model.DebateMigrationCategory debateMigrationCategory)
        throws com.liferay.portal.SystemException {
        return getService().addDebateMigrationCategory(debateMigrationCategory);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationCategory createDebateMigrationCategory(
        java.lang.Long debateMigrationCategoryPK) {
        return getService()
                   .createDebateMigrationCategory(debateMigrationCategoryPK);
    }

    public static void deleteDebateMigrationCategory(
        java.lang.Long debateMigrationCategoryPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteDebateMigrationCategory(debateMigrationCategoryPK);
    }

    public static void deleteDebateMigrationCategory(
        com.ext.portlet.debatemigration.model.DebateMigrationCategory debateMigrationCategory)
        throws com.liferay.portal.SystemException {
        getService().deleteDebateMigrationCategory(debateMigrationCategory);
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

    public static com.ext.portlet.debatemigration.model.DebateMigrationCategory getDebateMigrationCategory(
        java.lang.Long debateMigrationCategoryPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getDebateMigrationCategory(debateMigrationCategoryPK);
    }

    public static java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationCategory> getDebateMigrationCategories(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getDebateMigrationCategories(start, end);
    }

    public static int getDebateMigrationCategoriesCount()
        throws com.liferay.portal.SystemException {
        return getService().getDebateMigrationCategoriesCount();
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationCategory updateDebateMigrationCategory(
        com.ext.portlet.debatemigration.model.DebateMigrationCategory debateMigrationCategory)
        throws com.liferay.portal.SystemException {
        return getService()
                   .updateDebateMigrationCategory(debateMigrationCategory);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationCategory updateDebateMigrationCategory(
        com.ext.portlet.debatemigration.model.DebateMigrationCategory debateMigrationCategory,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateDebateMigrationCategory(debateMigrationCategory, merge);
    }

    public static DebateMigrationCategoryLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "DebateMigrationCategoryLocalService is not set");
        }

        return _service;
    }

    public void setService(DebateMigrationCategoryLocalService service) {
        _service = service;
    }
}
