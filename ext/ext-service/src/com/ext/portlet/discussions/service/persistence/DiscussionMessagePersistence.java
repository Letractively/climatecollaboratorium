package com.ext.portlet.discussions.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface DiscussionMessagePersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage);

    public void cacheResult(
        java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> discussionMessages);

    public void clearCache();

    public com.ext.portlet.discussions.model.DiscussionMessage create(
        java.lang.Long pk);

    public com.ext.portlet.discussions.model.DiscussionMessage remove(
        java.lang.Long pk)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage remove(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(DiscussionMessage discussionMessage, boolean merge)</code>.
     */
    public com.ext.portlet.discussions.model.DiscussionMessage update(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                discussionMessage the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when discussionMessage is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.discussions.model.DiscussionMessage update(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage updateImpl(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage findByPrimaryKey(
        java.lang.Long pk)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage fetchByPrimaryKey(
        java.lang.Long pk) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByCategoryIdThreadId(
        java.lang.Long categoryId, java.lang.Long threadId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByCategoryIdThreadId(
        java.lang.Long categoryId, java.lang.Long threadId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByCategoryIdThreadId(
        java.lang.Long categoryId, java.lang.Long threadId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage findByCategoryIdThreadId_First(
        java.lang.Long categoryId, java.lang.Long threadId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage findByCategoryIdThreadId_Last(
        java.lang.Long categoryId, java.lang.Long threadId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage[] findByCategoryIdThreadId_PrevAndNext(
        java.lang.Long pk, java.lang.Long categoryId, java.lang.Long threadId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByThreadId(
        java.lang.Long threadId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByThreadId(
        java.lang.Long threadId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByThreadId(
        java.lang.Long threadId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage findByThreadId_First(
        java.lang.Long threadId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage findByThreadId_Last(
        java.lang.Long threadId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage[] findByThreadId_PrevAndNext(
        java.lang.Long pk, java.lang.Long threadId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage findBySingleThreadId(
        java.lang.Long threadId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage fetchBySingleThreadId(
        java.lang.Long threadId) throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage fetchBySingleThreadId(
        java.lang.Long threadId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByCategoryIdThreadId(java.lang.Long categoryId,
        java.lang.Long threadId) throws com.liferay.portal.SystemException;

    public void removeByThreadId(java.lang.Long threadId)
        throws com.liferay.portal.SystemException;

    public void removeBySingleThreadId(java.lang.Long threadId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByCategoryIdThreadId(java.lang.Long categoryId,
        java.lang.Long threadId) throws com.liferay.portal.SystemException;

    public int countByThreadId(java.lang.Long threadId)
        throws com.liferay.portal.SystemException;

    public int countBySingleThreadId(java.lang.Long threadId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
