package com.ext.portlet.discussions.service;


/**
 * <a href="DiscussionMessageFlagLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.discussions.service.DiscussionMessageFlagLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.service.DiscussionMessageFlagLocalService
 *
 */
public class DiscussionMessageFlagLocalServiceUtil {
    private static DiscussionMessageFlagLocalService _service;

    public static com.ext.portlet.discussions.model.DiscussionMessageFlag addDiscussionMessageFlag(
        com.ext.portlet.discussions.model.DiscussionMessageFlag discussionMessageFlag)
        throws com.liferay.portal.SystemException {
        return getService().addDiscussionMessageFlag(discussionMessageFlag);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessageFlag createDiscussionMessageFlag(
        java.lang.Long pk) {
        return getService().createDiscussionMessageFlag(pk);
    }

    public static void deleteDiscussionMessageFlag(java.lang.Long pk)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteDiscussionMessageFlag(pk);
    }

    public static void deleteDiscussionMessageFlag(
        com.ext.portlet.discussions.model.DiscussionMessageFlag discussionMessageFlag)
        throws com.liferay.portal.SystemException {
        getService().deleteDiscussionMessageFlag(discussionMessageFlag);
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

    public static com.ext.portlet.discussions.model.DiscussionMessageFlag getDiscussionMessageFlag(
        java.lang.Long pk)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getDiscussionMessageFlag(pk);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessageFlag> getDiscussionMessageFlags(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getDiscussionMessageFlags(start, end);
    }

    public static int getDiscussionMessageFlagsCount()
        throws com.liferay.portal.SystemException {
        return getService().getDiscussionMessageFlagsCount();
    }

    public static com.ext.portlet.discussions.model.DiscussionMessageFlag updateDiscussionMessageFlag(
        com.ext.portlet.discussions.model.DiscussionMessageFlag discussionMessageFlag)
        throws com.liferay.portal.SystemException {
        return getService().updateDiscussionMessageFlag(discussionMessageFlag);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessageFlag updateDiscussionMessageFlag(
        com.ext.portlet.discussions.model.DiscussionMessageFlag discussionMessageFlag,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateDiscussionMessageFlag(discussionMessageFlag, merge);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessageFlag> findMessageFlags(
        java.lang.Long messageId) throws com.liferay.portal.SystemException {
        return getService().findMessageFlags(messageId);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessageFlag createFlag(
        java.lang.Long messageId, java.lang.String flagType,
        java.lang.String data, java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        return getService().createFlag(messageId, flagType, data, userId);
    }

    public static DiscussionMessageFlagLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "DiscussionMessageFlagLocalService is not set");
        }

        return _service;
    }

    public void setService(DiscussionMessageFlagLocalService service) {
        _service = service;
    }
}
