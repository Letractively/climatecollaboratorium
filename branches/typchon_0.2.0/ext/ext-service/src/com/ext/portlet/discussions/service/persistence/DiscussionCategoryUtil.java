package com.ext.portlet.discussions.service.persistence;

public class DiscussionCategoryUtil {
    private static DiscussionCategoryPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory) {
        getPersistence().cacheResult(discussionCategory);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> discussionCategories) {
        getPersistence().cacheResult(discussionCategories);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.discussions.model.DiscussionCategory create(
        java.lang.Long pk) {
        return getPersistence().create(pk);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategory remove(
        java.lang.Long pk)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(pk);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategory remove(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(discussionCategory);
    }

    /**
     * @deprecated Use <code>update(DiscussionCategory discussionCategory, boolean merge)</code>.
     */
    public static com.ext.portlet.discussions.model.DiscussionCategory update(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(discussionCategory);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                discussionCategory the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when discussionCategory is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.discussions.model.DiscussionCategory update(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(discussionCategory, merge);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategory updateImpl(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(discussionCategory, merge);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategory findByPrimaryKey(
        java.lang.Long pk)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(pk);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategory fetchByPrimaryKey(
        java.lang.Long pk) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(pk);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> findByCategoryGroupId(
        java.lang.Long categoryGroupId)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByCategoryGroupId(categoryGroupId);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> findByCategoryGroupId(
        java.lang.Long categoryGroupId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByCategoryGroupId(categoryGroupId, start, end);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> findByCategoryGroupId(
        java.lang.Long categoryGroupId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByCategoryGroupId(categoryGroupId, start, end, obc);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategory findByCategoryGroupId_First(
        java.lang.Long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.SystemException {
        return getPersistence().findByCategoryGroupId_First(categoryGroupId, obc);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategory findByCategoryGroupId_Last(
        java.lang.Long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.SystemException {
        return getPersistence().findByCategoryGroupId_Last(categoryGroupId, obc);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategory[] findByCategoryGroupId_PrevAndNext(
        java.lang.Long pk, java.lang.Long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByCategoryGroupId_PrevAndNext(pk, categoryGroupId, obc);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategory findByCategoryId(
        java.lang.Long categoryId)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.SystemException {
        return getPersistence().findByCategoryId(categoryId);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategory fetchByCategoryId(
        java.lang.Long categoryId) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByCategoryId(categoryId);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategory fetchByCategoryId(
        java.lang.Long categoryId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByCategoryId(categoryId, retrieveFromCache);
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

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByCategoryGroupId(java.lang.Long categoryGroupId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByCategoryGroupId(categoryGroupId);
    }

    public static void removeByCategoryId(java.lang.Long categoryId)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.SystemException {
        getPersistence().removeByCategoryId(categoryId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByCategoryGroupId(java.lang.Long categoryGroupId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByCategoryGroupId(categoryGroupId);
    }

    public static int countByCategoryId(java.lang.Long categoryId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByCategoryId(categoryId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static DiscussionCategoryPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(DiscussionCategoryPersistence persistence) {
        _persistence = persistence;
    }
}
