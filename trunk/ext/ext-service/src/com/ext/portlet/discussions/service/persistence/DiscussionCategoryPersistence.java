package com.ext.portlet.discussions.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface DiscussionCategoryPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory);

    public void cacheResult(
        java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> discussionCategories);

    public void clearCache();

    public com.ext.portlet.discussions.model.DiscussionCategory create(
        java.lang.Long pk);

    public com.ext.portlet.discussions.model.DiscussionCategory remove(
        java.lang.Long pk)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategory remove(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(DiscussionCategory discussionCategory, boolean merge)</code>.
     */
    public com.ext.portlet.discussions.model.DiscussionCategory update(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory)
        throws com.liferay.portal.SystemException;

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
    public com.ext.portlet.discussions.model.DiscussionCategory update(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategory updateImpl(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategory findByPrimaryKey(
        java.lang.Long pk)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategory fetchByPrimaryKey(
        java.lang.Long pk) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> findByCategoryGroupId(
        java.lang.Long categoryGroupId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> findByCategoryGroupId(
        java.lang.Long categoryGroupId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> findByCategoryGroupId(
        java.lang.Long categoryGroupId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategory findByCategoryGroupId_First(
        java.lang.Long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategory findByCategoryGroupId_Last(
        java.lang.Long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategory[] findByCategoryGroupId_PrevAndNext(
        java.lang.Long pk, java.lang.Long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategory findByCategoryId(
        java.lang.Long categoryId)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategory fetchByCategoryId(
        java.lang.Long categoryId) throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategory fetchByCategoryId(
        java.lang.Long categoryId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByCategoryGroupId(java.lang.Long categoryGroupId)
        throws com.liferay.portal.SystemException;

    public void removeByCategoryId(java.lang.Long categoryId)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByCategoryGroupId(java.lang.Long categoryGroupId)
        throws com.liferay.portal.SystemException;

    public int countByCategoryId(java.lang.Long categoryId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
