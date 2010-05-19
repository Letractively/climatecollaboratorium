package com.ext.portlet.debaterevision.service;


/**
 * <a href="DebateLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.debaterevision.service.DebateLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.DebateLocalService
 *
 */
public class DebateLocalServiceUtil {
    private static DebateLocalService _service;

    public static com.ext.portlet.debaterevision.model.Debate addDebate(
        com.ext.portlet.debaterevision.model.Debate debate)
        throws com.liferay.portal.SystemException {
        return getService().addDebate(debate);
    }

    public static com.ext.portlet.debaterevision.model.Debate createDebate(
        java.lang.Long debatePK) {
        return getService().createDebate(debatePK);
    }

    public static void deleteDebate(java.lang.Long debatePK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteDebate(debatePK);
    }

    public static void deleteDebate(
        com.ext.portlet.debaterevision.model.Debate debate)
        throws com.liferay.portal.SystemException {
        getService().deleteDebate(debate);
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

    public static com.ext.portlet.debaterevision.model.Debate getDebate(
        java.lang.Long debatePK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getDebate(debatePK);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.Debate> getDebates(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getDebates(start, end);
    }

    public static int getDebatesCount()
        throws com.liferay.portal.SystemException {
        return getService().getDebatesCount();
    }

    public static com.ext.portlet.debaterevision.model.Debate updateDebate(
        com.ext.portlet.debaterevision.model.Debate debate)
        throws com.liferay.portal.SystemException {
        return getService().updateDebate(debate);
    }

    public static com.ext.portlet.debaterevision.model.Debate updateDebate(
        com.ext.portlet.debaterevision.model.Debate debate, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updateDebate(debate, merge);
    }

    public static com.ext.portlet.debaterevision.model.Debate createNewDebate(
        java.lang.String title, java.lang.String detail, long authorId)
        throws com.liferay.portal.SystemException {
        return getService().createNewDebate(title, detail, authorId);
    }

    public static long getLastTreeVersion(long debateId) {
        return getService().getLastTreeVersion(debateId);
    }

    public static com.ext.portlet.debaterevision.model.Debate findLastVersion(
        long debateId) {
        return getService().findLastVersion(debateId);
    }

    public static com.ext.portlet.debaterevision.model.Debate findByVersion(
        long debateId, long treeVersion)
        throws com.liferay.portal.SystemException {
        return getService().findByVersion(debateId, treeVersion);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem getCurrentRoot(
        com.ext.portlet.debaterevision.model.Debate debate) {
        return getService().getCurrentRoot(debate);
    }

    public static com.ext.portlet.debaterevision.model.DebateComment getMostRecentComment(
        long debateId) {
        return getService().getMostRecentComment(debateId);
    }

    public static int getNumberOfComments(long debateId) {
        return getService().getNumberOfComments(debateId);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.Debate> getDebates() {
        return getService().getDebates();
    }

    public static DebateLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("DebateLocalService is not set");
        }

        return _service;
    }

    public void setService(DebateLocalService service) {
        _service = service;
    }
}
