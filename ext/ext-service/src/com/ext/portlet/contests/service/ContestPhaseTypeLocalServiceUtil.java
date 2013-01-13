package com.ext.portlet.contests.service;


/**
 * <a href="ContestPhaseTypeLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.contests.service.ContestPhaseTypeLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.service.ContestPhaseTypeLocalService
 *
 */
public class ContestPhaseTypeLocalServiceUtil {
    private static ContestPhaseTypeLocalService _service;

    public static com.ext.portlet.contests.model.ContestPhaseType addContestPhaseType(
        com.ext.portlet.contests.model.ContestPhaseType contestPhaseType)
        throws com.liferay.portal.SystemException {
        return getService().addContestPhaseType(contestPhaseType);
    }

    public static com.ext.portlet.contests.model.ContestPhaseType createContestPhaseType(
        java.lang.Long id) {
        return getService().createContestPhaseType(id);
    }

    public static void deleteContestPhaseType(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteContestPhaseType(id);
    }

    public static void deleteContestPhaseType(
        com.ext.portlet.contests.model.ContestPhaseType contestPhaseType)
        throws com.liferay.portal.SystemException {
        getService().deleteContestPhaseType(contestPhaseType);
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

    public static com.ext.portlet.contests.model.ContestPhaseType getContestPhaseType(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getContestPhaseType(id);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestPhaseType> getContestPhaseTypes(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getContestPhaseTypes(start, end);
    }

    public static int getContestPhaseTypesCount()
        throws com.liferay.portal.SystemException {
        return getService().getContestPhaseTypesCount();
    }

    public static com.ext.portlet.contests.model.ContestPhaseType updateContestPhaseType(
        com.ext.portlet.contests.model.ContestPhaseType contestPhaseType)
        throws com.liferay.portal.SystemException {
        return getService().updateContestPhaseType(contestPhaseType);
    }

    public static com.ext.portlet.contests.model.ContestPhaseType updateContestPhaseType(
        com.ext.portlet.contests.model.ContestPhaseType contestPhaseType,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateContestPhaseType(contestPhaseType, merge);
    }

    public static ContestPhaseTypeLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "ContestPhaseTypeLocalService is not set");
        }

        return _service;
    }

    public void setService(ContestPhaseTypeLocalService service) {
        _service = service;
    }
}
