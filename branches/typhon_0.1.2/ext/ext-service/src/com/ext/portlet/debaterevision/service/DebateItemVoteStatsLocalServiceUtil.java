package com.ext.portlet.debaterevision.service;


/**
 * <a href="DebateItemVoteStatsLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.debaterevision.service.DebateItemVoteStatsLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.DebateItemVoteStatsLocalService
 *
 */
public class DebateItemVoteStatsLocalServiceUtil {
    private static DebateItemVoteStatsLocalService _service;

    public static com.ext.portlet.debaterevision.model.DebateItemVoteStats addDebateItemVoteStats(
        com.ext.portlet.debaterevision.model.DebateItemVoteStats debateItemVoteStats)
        throws com.liferay.portal.SystemException {
        return getService().addDebateItemVoteStats(debateItemVoteStats);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVoteStats createDebateItemVoteStats(
        java.lang.Long debateItemVotesStats) {
        return getService().createDebateItemVoteStats(debateItemVotesStats);
    }

    public static void deleteDebateItemVoteStats(
        java.lang.Long debateItemVotesStats)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteDebateItemVoteStats(debateItemVotesStats);
    }

    public static void deleteDebateItemVoteStats(
        com.ext.portlet.debaterevision.model.DebateItemVoteStats debateItemVoteStats)
        throws com.liferay.portal.SystemException {
        getService().deleteDebateItemVoteStats(debateItemVoteStats);
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

    public static com.ext.portlet.debaterevision.model.DebateItemVoteStats getDebateItemVoteStats(
        java.lang.Long debateItemVotesStats)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getDebateItemVoteStats(debateItemVotesStats);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemVoteStats> getDebateItemVoteStatses(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getDebateItemVoteStatses(start, end);
    }

    public static int getDebateItemVoteStatsesCount()
        throws com.liferay.portal.SystemException {
        return getService().getDebateItemVoteStatsesCount();
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVoteStats updateDebateItemVoteStats(
        com.ext.portlet.debaterevision.model.DebateItemVoteStats debateItemVoteStats)
        throws com.liferay.portal.SystemException {
        return getService().updateDebateItemVoteStats(debateItemVoteStats);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVoteStats updateDebateItemVoteStats(
        com.ext.portlet.debaterevision.model.DebateItemVoteStats debateItemVoteStats,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateDebateItemVoteStats(debateItemVoteStats, merge);
    }

    public static void decrementStats(long debateItemId)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteStatsException,
            com.liferay.portal.SystemException {
        getService().decrementStats(debateItemId);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVoteStats getByDebateItemId(
        long debateItemId) throws com.liferay.portal.SystemException {
        return getService().getByDebateItemId(debateItemId);
    }

    public static void incrementStats(long debateItemId)
        throws com.liferay.portal.SystemException {
        getService().incrementStats(debateItemId);
    }

    public static DebateItemVoteStatsLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "DebateItemVoteStatsLocalService is not set");
        }

        return _service;
    }

    public void setService(DebateItemVoteStatsLocalService service) {
        _service = service;
    }
}
