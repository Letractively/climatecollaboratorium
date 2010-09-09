package com.ext.portlet.contests.service;


/**
 * <a href="ContestPhaseColumnLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.contests.service.ContestPhaseColumnLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.service.ContestPhaseColumnLocalService
 *
 */
public class ContestPhaseColumnLocalServiceUtil {
    private static ContestPhaseColumnLocalService _service;

    public static com.ext.portlet.contests.model.ContestPhaseColumn addContestPhaseColumn(
        com.ext.portlet.contests.model.ContestPhaseColumn contestPhaseColumn)
        throws com.liferay.portal.SystemException {
        return getService().addContestPhaseColumn(contestPhaseColumn);
    }

    public static com.ext.portlet.contests.model.ContestPhaseColumn createContestPhaseColumn(
        java.lang.Long id) {
        return getService().createContestPhaseColumn(id);
    }

    public static void deleteContestPhaseColumn(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteContestPhaseColumn(id);
    }

    public static void deleteContestPhaseColumn(
        com.ext.portlet.contests.model.ContestPhaseColumn contestPhaseColumn)
        throws com.liferay.portal.SystemException {
        getService().deleteContestPhaseColumn(contestPhaseColumn);
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

    public static com.ext.portlet.contests.model.ContestPhaseColumn getContestPhaseColumn(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getContestPhaseColumn(id);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestPhaseColumn> getContestPhaseColumns(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getContestPhaseColumns(start, end);
    }

    public static int getContestPhaseColumnsCount()
        throws com.liferay.portal.SystemException {
        return getService().getContestPhaseColumnsCount();
    }

    public static com.ext.portlet.contests.model.ContestPhaseColumn updateContestPhaseColumn(
        com.ext.portlet.contests.model.ContestPhaseColumn contestPhaseColumn)
        throws com.liferay.portal.SystemException {
        return getService().updateContestPhaseColumn(contestPhaseColumn);
    }

    public static com.ext.portlet.contests.model.ContestPhaseColumn updateContestPhaseColumn(
        com.ext.portlet.contests.model.ContestPhaseColumn contestPhaseColumn,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateContestPhaseColumn(contestPhaseColumn, merge);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestPhaseColumn> getPhaseColumns(
        java.lang.Long contestPhasePK)
        throws com.liferay.portal.SystemException {
        return getService().getPhaseColumns(contestPhasePK);
    }

    public static ContestPhaseColumnLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "ContestPhaseColumnLocalService is not set");
        }

        return _service;
    }

    public void setService(ContestPhaseColumnLocalService service) {
        _service = service;
    }
}
