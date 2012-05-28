package com.ext.portlet.contests.service;


/**
 * <a href="ContestTeamMemberLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.contests.service.ContestTeamMemberLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.service.ContestTeamMemberLocalService
 *
 */
public class ContestTeamMemberLocalServiceUtil {
    private static ContestTeamMemberLocalService _service;

    public static com.ext.portlet.contests.model.ContestTeamMember addContestTeamMember(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember)
        throws com.liferay.portal.SystemException {
        return getService().addContestTeamMember(contestTeamMember);
    }

    public static com.ext.portlet.contests.model.ContestTeamMember createContestTeamMember(
        java.lang.Long id) {
        return getService().createContestTeamMember(id);
    }

    public static void deleteContestTeamMember(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteContestTeamMember(id);
    }

    public static void deleteContestTeamMember(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember)
        throws com.liferay.portal.SystemException {
        getService().deleteContestTeamMember(contestTeamMember);
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

    public static com.ext.portlet.contests.model.ContestTeamMember getContestTeamMember(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getContestTeamMember(id);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestTeamMember> getContestTeamMembers(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getContestTeamMembers(start, end);
    }

    public static int getContestTeamMembersCount()
        throws com.liferay.portal.SystemException {
        return getService().getContestTeamMembersCount();
    }

    public static com.ext.portlet.contests.model.ContestTeamMember updateContestTeamMember(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember)
        throws com.liferay.portal.SystemException {
        return getService().updateContestTeamMember(contestTeamMember);
    }

    public static com.ext.portlet.contests.model.ContestTeamMember updateContestTeamMember(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateContestTeamMember(contestTeamMember, merge);
    }

    public static com.ext.portlet.contests.model.ContestTeamMember addContestTeamMember(
        java.lang.Long userId, java.lang.Long contestPk, java.lang.String role)
        throws com.liferay.portal.SystemException {
        return getService().addContestTeamMember(userId, contestPk, role);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestTeamMember> findForContest(
        java.lang.Long contestPk) throws com.liferay.portal.SystemException {
        return getService().findForContest(contestPk);
    }

    public static ContestTeamMemberLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "ContestTeamMemberLocalService is not set");
        }

        return _service;
    }

    public void setService(ContestTeamMemberLocalService service) {
        _service = service;
    }
}
