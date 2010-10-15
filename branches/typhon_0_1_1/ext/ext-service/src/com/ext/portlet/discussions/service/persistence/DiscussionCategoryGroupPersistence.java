package com.ext.portlet.discussions.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface DiscussionCategoryGroupPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup discussionCategoryGroup);

    public void cacheResult(
        java.util.List<com.ext.portlet.discussions.model.DiscussionCategoryGroup> discussionCategoryGroups);

    public void clearCache();

    public com.ext.portlet.discussions.model.DiscussionCategoryGroup create(
        java.lang.Long id);

    public com.ext.portlet.discussions.model.DiscussionCategoryGroup remove(
        java.lang.Long id)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryGroupException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategoryGroup remove(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup discussionCategoryGroup)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(DiscussionCategoryGroup discussionCategoryGroup, boolean merge)</code>.
     */
    public com.ext.portlet.discussions.model.DiscussionCategoryGroup update(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup discussionCategoryGroup)
        throws com.liferay.portal.SystemException;

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
    public com.ext.portlet.discussions.model.DiscussionCategoryGroup update(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup discussionCategoryGroup,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategoryGroup updateImpl(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup discussionCategoryGroup,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategoryGroup findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryGroupException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategoryGroup fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionCategoryGroup> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionCategoryGroup> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionCategoryGroup> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
