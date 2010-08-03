package com.ext.portlet.discussions.service.persistence;

public class DiscussionCategoryGroupUtil {
    private static DiscussionCategoryGroupPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup discussionCategoryGroup) {
        getPersistence().cacheResult(discussionCategoryGroup);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.discussions.model.DiscussionCategoryGroup> discussionCategoryGroups) {
        getPersistence().cacheResult(discussionCategoryGroups);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.discussions.model.DiscussionCategoryGroup create(
        java.lang.Long id) {
        return getPersistence().create(id);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategoryGroup remove(
        java.lang.Long id)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryGroupException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategoryGroup remove(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup discussionCategoryGroup)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(discussionCategoryGroup);
    }

    /**
     * @deprecated Use <code>update(DiscussionCategoryGroup discussionCategoryGroup, boolean merge)</code>.
     */
    public static com.ext.portlet.discussions.model.DiscussionCategoryGroup update(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup discussionCategoryGroup)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(discussionCategoryGroup);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                discussionCategoryGroup the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when discussionCategoryGroup is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.discussions.model.DiscussionCategoryGroup update(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup discussionCategoryGroup,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(discussionCategoryGroup, merge);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategoryGroup updateImpl(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup discussionCategoryGroup,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(discussionCategoryGroup, merge);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategoryGroup findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryGroupException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategoryGroup fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    public static java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    public static java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionCategoryGroup> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionCategoryGroup> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionCategoryGroup> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static DiscussionCategoryGroupPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(DiscussionCategoryGroupPersistence persistence) {
        _persistence = persistence;
    }
}
