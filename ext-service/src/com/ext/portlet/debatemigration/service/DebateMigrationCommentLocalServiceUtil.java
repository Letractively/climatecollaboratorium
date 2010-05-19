package com.ext.portlet.debatemigration.service;


/**
 * <a href="DebateMigrationCommentLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.debatemigration.service.DebateMigrationCommentLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.service.DebateMigrationCommentLocalService
 *
 */
public class DebateMigrationCommentLocalServiceUtil {
    private static DebateMigrationCommentLocalService _service;

    public static com.ext.portlet.debatemigration.model.DebateMigrationComment addDebateMigrationComment(
        com.ext.portlet.debatemigration.model.DebateMigrationComment debateMigrationComment)
        throws com.liferay.portal.SystemException {
        return getService().addDebateMigrationComment(debateMigrationComment);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationComment createDebateMigrationComment(
        java.lang.Long debateMigrationCommentPK) {
        return getService()
                   .createDebateMigrationComment(debateMigrationCommentPK);
    }

    public static void deleteDebateMigrationComment(
        java.lang.Long debateMigrationCommentPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteDebateMigrationComment(debateMigrationCommentPK);
    }

    public static void deleteDebateMigrationComment(
        com.ext.portlet.debatemigration.model.DebateMigrationComment debateMigrationComment)
        throws com.liferay.portal.SystemException {
        getService().deleteDebateMigrationComment(debateMigrationComment);
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

    public static com.ext.portlet.debatemigration.model.DebateMigrationComment getDebateMigrationComment(
        java.lang.Long debateMigrationCommentPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getDebateMigrationComment(debateMigrationCommentPK);
    }

    public static java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationComment> getDebateMigrationComments(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getDebateMigrationComments(start, end);
    }

    public static int getDebateMigrationCommentsCount()
        throws com.liferay.portal.SystemException {
        return getService().getDebateMigrationCommentsCount();
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationComment updateDebateMigrationComment(
        com.ext.portlet.debatemigration.model.DebateMigrationComment debateMigrationComment)
        throws com.liferay.portal.SystemException {
        return getService().updateDebateMigrationComment(debateMigrationComment);
    }

    public static com.ext.portlet.debatemigration.model.DebateMigrationComment updateDebateMigrationComment(
        com.ext.portlet.debatemigration.model.DebateMigrationComment debateMigrationComment,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateDebateMigrationComment(debateMigrationComment, merge);
    }

    public static DebateMigrationCommentLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "DebateMigrationCommentLocalService is not set");
        }

        return _service;
    }

    public void setService(DebateMigrationCommentLocalService service) {
        _service = service;
    }
}
