package com.ext.portlet.models.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface ModelDiscussionPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.models.model.ModelDiscussion modelDiscussion);

    public void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelDiscussion> modelDiscussions);

    public void clearCache();

    public com.ext.portlet.models.model.ModelDiscussion create(
        java.lang.Long modelDiscussionId);

    public com.ext.portlet.models.model.ModelDiscussion remove(
        java.lang.Long modelDiscussionId)
        throws com.ext.portlet.models.NoSuchModelDiscussionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelDiscussion remove(
        com.ext.portlet.models.model.ModelDiscussion modelDiscussion)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(ModelDiscussion modelDiscussion, boolean merge)</code>.
     */
    public com.ext.portlet.models.model.ModelDiscussion update(
        com.ext.portlet.models.model.ModelDiscussion modelDiscussion)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelDiscussion the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelDiscussion is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.models.model.ModelDiscussion update(
        com.ext.portlet.models.model.ModelDiscussion modelDiscussion,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelDiscussion updateImpl(
        com.ext.portlet.models.model.ModelDiscussion modelDiscussion,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelDiscussion findByPrimaryKey(
        java.lang.Long modelDiscussionId)
        throws com.ext.portlet.models.NoSuchModelDiscussionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelDiscussion fetchByPrimaryKey(
        java.lang.Long modelDiscussionId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelDiscussion> findByModelId(
        java.lang.Long modelId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelDiscussion> findByModelId(
        java.lang.Long modelId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelDiscussion> findByModelId(
        java.lang.Long modelId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelDiscussion findByModelId_First(
        java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelDiscussionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelDiscussion findByModelId_Last(
        java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelDiscussionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelDiscussion[] findByModelId_PrevAndNext(
        java.lang.Long modelDiscussionId, java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelDiscussionException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelDiscussion> findByDiscussionId(
        java.lang.Long categoryId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelDiscussion> findByDiscussionId(
        java.lang.Long categoryId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelDiscussion> findByDiscussionId(
        java.lang.Long categoryId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelDiscussion findByDiscussionId_First(
        java.lang.Long categoryId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelDiscussionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelDiscussion findByDiscussionId_Last(
        java.lang.Long categoryId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelDiscussionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelDiscussion[] findByDiscussionId_PrevAndNext(
        java.lang.Long modelDiscussionId, java.lang.Long categoryId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelDiscussionException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelDiscussion> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelDiscussion> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.models.model.ModelDiscussion> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByModelId(java.lang.Long modelId)
        throws com.liferay.portal.SystemException;

    public void removeByDiscussionId(java.lang.Long categoryId)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByModelId(java.lang.Long modelId)
        throws com.liferay.portal.SystemException;

    public int countByDiscussionId(java.lang.Long categoryId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
