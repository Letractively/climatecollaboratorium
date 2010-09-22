package com.ext.portlet.debates.service;


/**
 * <a href="DebateDiscussionMapLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.debates.service.DebateDiscussionMapLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debates.service.DebateDiscussionMapLocalService
 *
 */
public class DebateDiscussionMapLocalServiceUtil {
    private static DebateDiscussionMapLocalService _service;

    public static com.ext.portlet.debates.model.DebateDiscussionMap addDebateDiscussionMap(
        com.ext.portlet.debates.model.DebateDiscussionMap debateDiscussionMap)
        throws com.liferay.portal.SystemException {
        return getService().addDebateDiscussionMap(debateDiscussionMap);
    }

    public static com.ext.portlet.debates.model.DebateDiscussionMap createDebateDiscussionMap(
        java.lang.Long debateMessageId) {
        return getService().createDebateDiscussionMap(debateMessageId);
    }

    public static void deleteDebateDiscussionMap(java.lang.Long debateMessageId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteDebateDiscussionMap(debateMessageId);
    }

    public static void deleteDebateDiscussionMap(
        com.ext.portlet.debates.model.DebateDiscussionMap debateDiscussionMap)
        throws com.liferay.portal.SystemException {
        getService().deleteDebateDiscussionMap(debateDiscussionMap);
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

    public static com.ext.portlet.debates.model.DebateDiscussionMap getDebateDiscussionMap(
        java.lang.Long debateMessageId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getDebateDiscussionMap(debateMessageId);
    }

    public static java.util.List<com.ext.portlet.debates.model.DebateDiscussionMap> getDebateDiscussionMaps(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getDebateDiscussionMaps(start, end);
    }

    public static int getDebateDiscussionMapsCount()
        throws com.liferay.portal.SystemException {
        return getService().getDebateDiscussionMapsCount();
    }

    public static com.ext.portlet.debates.model.DebateDiscussionMap updateDebateDiscussionMap(
        com.ext.portlet.debates.model.DebateDiscussionMap debateDiscussionMap)
        throws com.liferay.portal.SystemException {
        return getService().updateDebateDiscussionMap(debateDiscussionMap);
    }

    public static com.ext.portlet.debates.model.DebateDiscussionMap updateDebateDiscussionMap(
        com.ext.portlet.debates.model.DebateDiscussionMap debateDiscussionMap,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateDebateDiscussionMap(debateDiscussionMap, merge);
    }

    public static DebateDiscussionMapLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "DebateDiscussionMapLocalService is not set");
        }

        return _service;
    }

    public void setService(DebateDiscussionMapLocalService service) {
        _service = service;
    }
}
