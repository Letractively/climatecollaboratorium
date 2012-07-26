package com.ext.portlet.contests.service;


/**
 * <a href="ContestLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.contests.service.ContestLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.service.ContestLocalService
 *
 */
public class ContestLocalServiceUtil {
    private static ContestLocalService _service;

    public static com.ext.portlet.contests.model.Contest addContest(
        com.ext.portlet.contests.model.Contest contest)
        throws com.liferay.portal.SystemException {
        return getService().addContest(contest);
    }

    public static com.ext.portlet.contests.model.Contest createContest(
        java.lang.Long ContestPK) {
        return getService().createContest(ContestPK);
    }

    public static void deleteContest(java.lang.Long ContestPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteContest(ContestPK);
    }

    public static void deleteContest(
        com.ext.portlet.contests.model.Contest contest)
        throws com.liferay.portal.SystemException {
        getService().deleteContest(contest);
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

    public static com.ext.portlet.contests.model.Contest getContest(
        java.lang.Long ContestPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getContest(ContestPK);
    }

    public static java.util.List<com.ext.portlet.contests.model.Contest> getContests(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getContests(start, end);
    }

    public static int getContestsCount()
        throws com.liferay.portal.SystemException {
        return getService().getContestsCount();
    }

    public static com.ext.portlet.contests.model.Contest updateContest(
        com.ext.portlet.contests.model.Contest contest)
        throws com.liferay.portal.SystemException {
        return getService().updateContest(contest);
    }

    public static com.ext.portlet.contests.model.Contest updateContest(
        com.ext.portlet.contests.model.Contest contest, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updateContest(contest, merge);
    }

    public static com.ext.portlet.contests.model.Contest getContestByActiveFlag(
        boolean contestActive)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.SystemException {
        return getService().getContestByActiveFlag(contestActive);
    }

    public static com.ext.portlet.contests.model.Contest createNewContest(
        java.lang.Long userId, java.lang.String name)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().createNewContest(userId, name);
    }

    public static void updateContestGroupsAndDiscussions()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().updateContestGroupsAndDiscussions();
    }

    public static java.util.List<com.ext.portlet.contests.model.Contest> findByActiveFeatured(
        boolean active, boolean featured)
        throws com.liferay.portal.SystemException {
        return getService().findByActiveFeatured(active, featured);
    }

    public static java.util.List<com.ext.portlet.contests.model.Contest> findByActiveFlag(
        boolean active, int flag) throws com.liferay.portal.SystemException {
        return getService().findByActiveFlag(active, flag);
    }

    public static java.util.List<com.ext.portlet.contests.model.Contest> findByActiveFlagText(
        boolean active, java.lang.String flagText)
        throws com.liferay.portal.SystemException {
        return getService().findByActiveFlagText(active, flagText);
    }

    public static ContestLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("ContestLocalService is not set");
        }

        return _service;
    }

    public void setService(ContestLocalService service) {
        _service = service;
    }
}
