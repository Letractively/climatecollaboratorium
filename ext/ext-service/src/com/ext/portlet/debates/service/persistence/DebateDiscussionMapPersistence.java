package com.ext.portlet.debates.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface DebateDiscussionMapPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.debates.model.DebateDiscussionMap debateDiscussionMap);

    public void cacheResult(
        java.util.List<com.ext.portlet.debates.model.DebateDiscussionMap> debateDiscussionMaps);

    public void clearCache();

    public com.ext.portlet.debates.model.DebateDiscussionMap create(
        java.lang.Long debateMessageId);

    public com.ext.portlet.debates.model.DebateDiscussionMap remove(
        java.lang.Long debateMessageId)
        throws com.ext.portlet.debates.NoSuchDebateDiscussionMapException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debates.model.DebateDiscussionMap remove(
        com.ext.portlet.debates.model.DebateDiscussionMap debateDiscussionMap)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(DebateDiscussionMap debateDiscussionMap, boolean merge)</code>.
     */
    public com.ext.portlet.debates.model.DebateDiscussionMap update(
        com.ext.portlet.debates.model.DebateDiscussionMap debateDiscussionMap)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateDiscussionMap the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateDiscussionMap is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.debates.model.DebateDiscussionMap update(
        com.ext.portlet.debates.model.DebateDiscussionMap debateDiscussionMap,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debates.model.DebateDiscussionMap updateImpl(
        com.ext.portlet.debates.model.DebateDiscussionMap debateDiscussionMap,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debates.model.DebateDiscussionMap findByPrimaryKey(
        java.lang.Long debateMessageId)
        throws com.ext.portlet.debates.NoSuchDebateDiscussionMapException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debates.model.DebateDiscussionMap fetchByPrimaryKey(
        java.lang.Long debateMessageId)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debates.model.DebateDiscussionMap findByDebateMessageFromDiscussion(
        java.lang.Long debateMessageId)
        throws com.ext.portlet.debates.NoSuchDebateDiscussionMapException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debates.model.DebateDiscussionMap fetchByDebateMessageFromDiscussion(
        java.lang.Long debateMessageId)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debates.model.DebateDiscussionMap fetchByDebateMessageFromDiscussion(
        java.lang.Long debateMessageId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debates.model.DebateDiscussionMap> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debates.model.DebateDiscussionMap> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debates.model.DebateDiscussionMap> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByDebateMessageFromDiscussion(
        java.lang.Long debateMessageId)
        throws com.ext.portlet.debates.NoSuchDebateDiscussionMapException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByDebateMessageFromDiscussion(
        java.lang.Long debateMessageId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}