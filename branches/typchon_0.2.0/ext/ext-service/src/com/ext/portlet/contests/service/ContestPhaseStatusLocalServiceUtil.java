package com.ext.portlet.contests.service;


/**
 * <a href="ContestPhaseStatusLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.contests.service.ContestPhaseStatusLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.service.ContestPhaseStatusLocalService
 *
 */
public class ContestPhaseStatusLocalServiceUtil {
    private static ContestPhaseStatusLocalService _service;

    public static com.ext.portlet.contests.model.ContestPhaseStatus addContestPhaseStatus(
        com.ext.portlet.contests.model.ContestPhaseStatus contestPhaseStatus)
        throws com.liferay.portal.SystemException {
        return getService().addContestPhaseStatus(contestPhaseStatus);
    }

    public static com.ext.portlet.contests.model.ContestPhaseStatus createContestPhaseStatus(
        java.lang.String name) {
        return getService().createContestPhaseStatus(name);
    }

    public static void deleteContestPhaseStatus(java.lang.String name)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteContestPhaseStatus(name);
    }

    public static void deleteContestPhaseStatus(
        com.ext.portlet.contests.model.ContestPhaseStatus contestPhaseStatus)
        throws com.liferay.portal.SystemException {
        getService().deleteContestPhaseStatus(contestPhaseStatus);
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

    public static com.ext.portlet.contests.model.ContestPhaseStatus getContestPhaseStatus(
        java.lang.String name)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getContestPhaseStatus(name);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestPhaseStatus> getContestPhaseStatuses(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getContestPhaseStatuses(start, end);
    }

    public static int getContestPhaseStatusesCount()
        throws com.liferay.portal.SystemException {
        return getService().getContestPhaseStatusesCount();
    }

    public static com.ext.portlet.contests.model.ContestPhaseStatus updateContestPhaseStatus(
        com.ext.portlet.contests.model.ContestPhaseStatus contestPhaseStatus)
        throws com.liferay.portal.SystemException {
        return getService().updateContestPhaseStatus(contestPhaseStatus);
    }

    public static com.ext.portlet.contests.model.ContestPhaseStatus updateContestPhaseStatus(
        com.ext.portlet.contests.model.ContestPhaseStatus contestPhaseStatus,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateContestPhaseStatus(contestPhaseStatus, merge);
    }

    public static ContestPhaseStatusLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "ContestPhaseStatusLocalService is not set");
        }

        return _service;
    }

    public void setService(ContestPhaseStatusLocalService service) {
        _service = service;
    }
}
