package com.ext.portlet.debatemigration.service;


/**
 * <a href="DebateMigrationDebateLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.debatemigration.service.DebateMigrationDebateLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.service.DebateMigrationDebateLocalService
 *
 */
public class DebateMigrationDebateLocalServiceUtil {
    private static DebateMigrationDebateLocalService _service;

    public static com.ext.portlet.debatemigration.model.DebateMigrationDebate addDebateMigrationDebate(
        com.ext.portlet.debatemigration.model.DebateMigrationDebate debateMigrationDebate)
        throws com.liferay.portal.SystemException {
        return getService().addDebateMigrationDebate(debateMigrationDebate);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationDebate createDebateMigrationDebate(
        java.lang.Long debateMigrationDebatePK) {
        return getService().createDebateMigrationDebate(debateMigrationDebatePK);
    }

    public static void deleteDebateMigrationDebate(
        java.lang.Long debateMigrationDebatePK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteDebateMigrationDebate(debateMigrationDebatePK);
    }

    public static void deleteDebateMigrationDebate(
        com.ext.portlet.debatemigration.model.DebateMigrationDebate debateMigrationDebate)
        throws com.liferay.portal.SystemException {
        getService().deleteDebateMigrationDebate(debateMigrationDebate);
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

    public static com.ext.portlet.debatemigration.model.DebateMigrationDebate getDebateMigrationDebate(
        java.lang.Long debateMigrationDebatePK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getDebateMigrationDebate(debateMigrationDebatePK);
    }

    public static java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationDebate> getDebateMigrationDebates(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getDebateMigrationDebates(start, end);
    }

    public static int getDebateMigrationDebatesCount()
        throws com.liferay.portal.SystemException {
        return getService().getDebateMigrationDebatesCount();
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationDebate updateDebateMigrationDebate(
        com.ext.portlet.debatemigration.model.DebateMigrationDebate debateMigrationDebate)
        throws com.liferay.portal.SystemException {
        return getService().updateDebateMigrationDebate(debateMigrationDebate);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationDebate updateDebateMigrationDebate(
        com.ext.portlet.debatemigration.model.DebateMigrationDebate debateMigrationDebate,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateDebateMigrationDebate(debateMigrationDebate, merge);
    }

    public static DebateMigrationDebateLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "DebateMigrationDebateLocalService is not set");
        }

        return _service;
    }

    public void setService(DebateMigrationDebateLocalService service) {
        _service = service;
    }
}
