package com.ext.portlet.contests.service;


/**
 * <a href="ContestDebateLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.contests.service.ContestDebateLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.service.ContestDebateLocalService
 *
 */
public class ContestDebateLocalServiceUtil {
    private static ContestDebateLocalService _service;

    public static com.ext.portlet.contests.model.ContestDebate addContestDebate(
        com.ext.portlet.contests.model.ContestDebate contestDebate)
        throws com.liferay.portal.SystemException {
        return getService().addContestDebate(contestDebate);
    }

    public static com.ext.portlet.contests.model.ContestDebate createContestDebate(
        java.lang.Long id) {
        return getService().createContestDebate(id);
    }

    public static void deleteContestDebate(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteContestDebate(id);
    }

    public static void deleteContestDebate(
        com.ext.portlet.contests.model.ContestDebate contestDebate)
        throws com.liferay.portal.SystemException {
        getService().deleteContestDebate(contestDebate);
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

    public static com.ext.portlet.contests.model.ContestDebate getContestDebate(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getContestDebate(id);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestDebate> getContestDebates(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getContestDebates(start, end);
    }

    public static int getContestDebatesCount()
        throws com.liferay.portal.SystemException {
        return getService().getContestDebatesCount();
    }

    public static com.ext.portlet.contests.model.ContestDebate updateContestDebate(
        com.ext.portlet.contests.model.ContestDebate contestDebate)
        throws com.liferay.portal.SystemException {
        return getService().updateContestDebate(contestDebate);
    }

    public static com.ext.portlet.contests.model.ContestDebate updateContestDebate(
        com.ext.portlet.contests.model.ContestDebate contestDebate,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateContestDebate(contestDebate, merge);
    }

    public static com.ext.portlet.contests.model.ContestDebate createContestDebate(
        java.lang.Long debateId, java.lang.Long contestId)
        throws com.liferay.portal.SystemException {
        return getService().createContestDebate(debateId, contestId);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestDebate> getContestDebates(
        java.lang.Long contestId) throws com.liferay.portal.SystemException {
        return getService().getContestDebates(contestId);
    }

    public static ContestDebateLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("ContestDebateLocalService is not set");
        }

        return _service;
    }

    public void setService(ContestDebateLocalService service) {
        _service = service;
    }
}
