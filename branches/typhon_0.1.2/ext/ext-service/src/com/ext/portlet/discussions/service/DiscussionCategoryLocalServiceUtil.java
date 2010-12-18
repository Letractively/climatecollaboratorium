package com.ext.portlet.discussions.service;


/**
 * <a href="DiscussionCategoryLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.discussions.service.DiscussionCategoryLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.service.DiscussionCategoryLocalService
 *
 */
public class DiscussionCategoryLocalServiceUtil {
    private static DiscussionCategoryLocalService _service;

    public static com.ext.portlet.discussions.model.DiscussionCategory addDiscussionCategory(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory)
        throws com.liferay.portal.SystemException {
        return getService().addDiscussionCategory(discussionCategory);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategory createDiscussionCategory(
        java.lang.Long pk) {
        return getService().createDiscussionCategory(pk);
    }

    public static void deleteDiscussionCategory(java.lang.Long pk)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteDiscussionCategory(pk);
    }

    public static void deleteDiscussionCategory(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory)
        throws com.liferay.portal.SystemException {
        getService().deleteDiscussionCategory(discussionCategory);
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

    public static com.ext.portlet.discussions.model.DiscussionCategory getDiscussionCategory(
        java.lang.Long pk)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getDiscussionCategory(pk);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> getDiscussionCategories(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getDiscussionCategories(start, end);
    }

    public static int getDiscussionCategoriesCount()
        throws com.liferay.portal.SystemException {
        return getService().getDiscussionCategoriesCount();
    }

    public static com.ext.portlet.discussions.model.DiscussionCategory updateDiscussionCategory(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory)
        throws com.liferay.portal.SystemException {
        return getService().updateDiscussionCategory(discussionCategory);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategory updateDiscussionCategory(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateDiscussionCategory(discussionCategory, merge);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> getCategoriesByCategoryGroupId(
        java.lang.Long categoryGroupId)
        throws com.liferay.portal.SystemException {
        return getService().getCategoriesByCategoryGroupId(categoryGroupId);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategory getDiscussionCategoryById(
        java.lang.Long categoryId)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.SystemException {
        return getService().getDiscussionCategoryById(categoryId);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategory createDebateCategory(
        java.lang.Long categoryGroupId, java.lang.String name,
        java.lang.String description, com.liferay.portal.model.User author)
        throws com.liferay.portal.SystemException {
        return getService()
                   .createDebateCategory(categoryGroupId, name, description,
            author);
    }

    public static DiscussionCategoryLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "DiscussionCategoryLocalService is not set");
        }

        return _service;
    }

    public void setService(DiscussionCategoryLocalService service) {
        _service = service;
    }
}
