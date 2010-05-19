package com.ext.portlet.debaterevision.service;


/**
 * <a href="DebateItemVoteLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.debaterevision.service.DebateItemVoteLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.DebateItemVoteLocalService
 *
 */
public class DebateItemVoteLocalServiceUtil {
    private static DebateItemVoteLocalService _service;

    public static com.ext.portlet.debaterevision.model.DebateItemVote addDebateItemVote(
        com.ext.portlet.debaterevision.model.DebateItemVote debateItemVote)
        throws com.liferay.portal.SystemException {
        return getService().addDebateItemVote(debateItemVote);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVote createDebateItemVote(
        java.lang.Long debateItemVoteId) {
        return getService().createDebateItemVote(debateItemVoteId);
    }

    public static void deleteDebateItemVote(java.lang.Long debateItemVoteId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteDebateItemVote(debateItemVoteId);
    }

    public static void deleteDebateItemVote(
        com.ext.portlet.debaterevision.model.DebateItemVote debateItemVote)
        throws com.liferay.portal.SystemException {
        getService().deleteDebateItemVote(debateItemVote);
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

    public static com.ext.portlet.debaterevision.model.DebateItemVote getDebateItemVote(
        java.lang.Long debateItemVoteId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getDebateItemVote(debateItemVoteId);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> getDebateItemVotes(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getDebateItemVotes(start, end);
    }

    public static int getDebateItemVotesCount()
        throws com.liferay.portal.SystemException {
        return getService().getDebateItemVotesCount();
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVote updateDebateItemVote(
        com.ext.portlet.debaterevision.model.DebateItemVote debateItemVote)
        throws com.liferay.portal.SystemException {
        return getService().updateDebateItemVote(debateItemVote);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemVote updateDebateItemVote(
        com.ext.portlet.debaterevision.model.DebateItemVote debateItemVote,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateDebateItemVote(debateItemVote, merge);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> getVotesByDebateItemIdUserId(
        long debateItemId, long userId)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException {
        return getService().getVotesByDebateItemIdUserId(debateItemId, userId);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> getVotesByUserId(
        long userId)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException {
        return getService().getVotesByUserId(userId);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> getVotesByDebateItemId(
        long debateItemId) throws com.liferay.portal.SystemException {
        return getService().getVotesByDebateItemId(debateItemId);
    }

    public static void flush() {
        getService().flush();
    }

    public static void removeVote(long debateItemId, long userId) {
        getService().removeVote(debateItemId, userId);
    }

    public static DebateItemVoteLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("DebateItemVoteLocalService is not set");
        }

        return _service;
    }

    public void setService(DebateItemVoteLocalService service) {
        _service = service;
    }
}
