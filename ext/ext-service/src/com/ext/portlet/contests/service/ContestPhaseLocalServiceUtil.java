package com.ext.portlet.contests.service;


/**
 * <a href="ContestPhaseLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.contests.service.ContestPhaseLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.service.ContestPhaseLocalService
 *
 */
public class ContestPhaseLocalServiceUtil {
    private static ContestPhaseLocalService _service;

    public static com.ext.portlet.contests.model.ContestPhase addContestPhase(
        com.ext.portlet.contests.model.ContestPhase contestPhase)
        throws com.liferay.portal.SystemException {
        return getService().addContestPhase(contestPhase);
    }

    public static com.ext.portlet.contests.model.ContestPhase createContestPhase(
        java.lang.Long ContestPhasePK) {
        return getService().createContestPhase(ContestPhasePK);
    }

    public static void deleteContestPhase(java.lang.Long ContestPhasePK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteContestPhase(ContestPhasePK);
    }

    public static void deleteContestPhase(
        com.ext.portlet.contests.model.ContestPhase contestPhase)
        throws com.liferay.portal.SystemException {
        getService().deleteContestPhase(contestPhase);
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

    public static com.ext.portlet.contests.model.ContestPhase getContestPhase(
        java.lang.Long ContestPhasePK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getContestPhase(ContestPhasePK);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestPhase> getContestPhases(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getContestPhases(start, end);
    }

    public static int getContestPhasesCount()
        throws com.liferay.portal.SystemException {
        return getService().getContestPhasesCount();
    }

    public static com.ext.portlet.contests.model.ContestPhase updateContestPhase(
        com.ext.portlet.contests.model.ContestPhase contestPhase)
        throws com.liferay.portal.SystemException {
        return getService().updateContestPhase(contestPhase);
    }

    public static com.ext.portlet.contests.model.ContestPhase updateContestPhase(
        com.ext.portlet.contests.model.ContestPhase contestPhase, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updateContestPhase(contestPhase, merge);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestPhase> getPhasesForContest(
        com.ext.portlet.contests.model.Contest contest)
        throws com.liferay.portal.SystemException {
        return getService().getPhasesForContest(contest);
    }

    public static com.ext.portlet.contests.model.ContestPhase getActivePhaseForContest(
        com.ext.portlet.contests.model.Contest contest)
        throws com.ext.portlet.contests.NoSuchContestPhaseException,
            com.liferay.portal.SystemException {
        return getService().getActivePhaseForContest(contest);
    }

    public static ContestPhaseLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("ContestPhaseLocalService is not set");
        }

        return _service;
    }

    public void setService(ContestPhaseLocalService service) {
        _service = service;
    }
}
