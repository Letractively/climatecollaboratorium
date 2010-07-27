package com.ext.portlet.discussions.service;


/**
 * <a href="DiscussionCategoryGroupLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalService
 *
 */
public class DiscussionCategoryGroupLocalServiceUtil {
    private static DiscussionCategoryGroupLocalService _service;

    public static com.ext.portlet.discussions.model.DiscussionCategoryGroup addDiscussionCategoryGroup(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup discussionCategoryGroup)
        throws com.liferay.portal.SystemException {
        return getService().addDiscussionCategoryGroup(discussionCategoryGroup);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategoryGroup createDiscussionCategoryGroup(
        java.lang.Long id) {
        return getService().createDiscussionCategoryGroup(id);
    }

    public static void deleteDiscussionCategoryGroup(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteDiscussionCategoryGroup(id);
    }

    public static void deleteDiscussionCategoryGroup(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup discussionCategoryGroup)
        throws com.liferay.portal.SystemException {
        getService().deleteDiscussionCategoryGroup(discussionCategoryGroup);
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

    public static com.ext.portlet.discussions.model.DiscussionCategoryGroup getDiscussionCategoryGroup(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getDiscussionCategoryGroup(id);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionCategoryGroup> getDiscussionCategoryGroups(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getDiscussionCategoryGroups(start, end);
    }

    public static int getDiscussionCategoryGroupsCount()
        throws com.liferay.portal.SystemException {
        return getService().getDiscussionCategoryGroupsCount();
    }

    public static com.ext.portlet.discussions.model.DiscussionCategoryGroup updateDiscussionCategoryGroup(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup discussionCategoryGroup)
        throws com.liferay.portal.SystemException {
        return getService()
                   .updateDiscussionCategoryGroup(discussionCategoryGroup);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategoryGroup updateDiscussionCategoryGroup(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup discussionCategoryGroup,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateDiscussionCategoryGroup(discussionCategoryGroup, merge);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategoryGroup createDiscussionCategoryGroup(
        java.lang.String description) throws com.liferay.portal.SystemException {
        return getService().createDiscussionCategoryGroup(description);
    }

    public static DiscussionCategoryGroupLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "DiscussionCategoryGroupLocalService is not set");
        }

        return _service;
    }

    public void setService(DiscussionCategoryGroupLocalService service) {
        _service = service;
    }
}
