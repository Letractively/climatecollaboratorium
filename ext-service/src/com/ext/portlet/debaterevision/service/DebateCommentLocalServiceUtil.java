package com.ext.portlet.debaterevision.service;


/**
 * <a href="DebateCommentLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.debaterevision.service.DebateCommentLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.DebateCommentLocalService
 *
 */
public class DebateCommentLocalServiceUtil {
    private static DebateCommentLocalService _service;

    public static com.ext.portlet.debaterevision.model.DebateComment addDebateComment(
        com.ext.portlet.debaterevision.model.DebateComment debateComment)
        throws com.liferay.portal.SystemException {
        return getService().addDebateComment(debateComment);
    }

    public static com.ext.portlet.debaterevision.model.DebateComment createDebateComment(
        java.lang.Long debateCommentId) {
        return getService().createDebateComment(debateCommentId);
    }

    public static void deleteDebateComment(java.lang.Long debateCommentId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteDebateComment(debateCommentId);
    }

    public static void deleteDebateComment(
        com.ext.portlet.debaterevision.model.DebateComment debateComment)
        throws com.liferay.portal.SystemException {
        getService().deleteDebateComment(debateComment);
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

    public static com.ext.portlet.debaterevision.model.DebateComment getDebateComment(
        java.lang.Long debateCommentId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getDebateComment(debateCommentId);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateComment> getDebateComments(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getDebateComments(start, end);
    }

    public static int getDebateCommentsCount()
        throws com.liferay.portal.SystemException {
        return getService().getDebateCommentsCount();
    }

    public static com.ext.portlet.debaterevision.model.DebateComment updateDebateComment(
        com.ext.portlet.debaterevision.model.DebateComment debateComment)
        throws com.liferay.portal.SystemException {
        return getService().updateDebateComment(debateComment);
    }

    public static com.ext.portlet.debaterevision.model.DebateComment updateDebateComment(
        com.ext.portlet.debaterevision.model.DebateComment debateComment,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateDebateComment(debateComment, merge);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateComment> getCommentsForItem(
        long debateItemId) throws com.liferay.portal.SystemException {
        return getService().getCommentsForItem(debateItemId);
    }

    public static DebateCommentLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("DebateCommentLocalService is not set");
        }

        return _service;
    }

    public void setService(DebateCommentLocalService service) {
        _service = service;
    }
}
