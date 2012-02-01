package com.ext.portlet.debaterevision.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface DebateItemVotePersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.debaterevision.model.DebateItemVote debateItemVote);

    public void cacheResult(
        java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> debateItemVotes);

    public void clearCache();

    public com.ext.portlet.debaterevision.model.DebateItemVote create(
        java.lang.Long debateItemVoteId);

    public com.ext.portlet.debaterevision.model.DebateItemVote remove(
        java.lang.Long debateItemVoteId)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVote remove(
        com.ext.portlet.debaterevision.model.DebateItemVote debateItemVote)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(DebateItemVote debateItemVote, boolean merge)</code>.
     */
    public com.ext.portlet.debaterevision.model.DebateItemVote update(
        com.ext.portlet.debaterevision.model.DebateItemVote debateItemVote)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateItemVote the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateItemVote is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.debaterevision.model.DebateItemVote update(
        com.ext.portlet.debaterevision.model.DebateItemVote debateItemVote,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVote updateImpl(
        com.ext.portlet.debaterevision.model.DebateItemVote debateItemVote,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVote findByPrimaryKey(
        java.lang.Long debateItemVoteId)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVote fetchByPrimaryKey(
        java.lang.Long debateItemVoteId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findByDebateItemId(
        java.lang.Long debateItemId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findByDebateItemId(
        java.lang.Long debateItemId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findByDebateItemId(
        java.lang.Long debateItemId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVote findByDebateItemId_First(
        java.lang.Long debateItemId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVote findByDebateItemId_Last(
        java.lang.Long debateItemId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVote[] findByDebateItemId_PrevAndNext(
        java.lang.Long debateItemVoteId, java.lang.Long debateItemId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findByUserId(
        java.lang.Long userId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findByUserId(
        java.lang.Long userId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findByUserId(
        java.lang.Long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVote findByUserId_First(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVote findByUserId_Last(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVote[] findByUserId_PrevAndNext(
        java.lang.Long debateItemVoteId, java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findByDebateItemIdUserId(
        java.lang.Long debateItemId, java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findByDebateItemIdUserId(
        java.lang.Long debateItemId, java.lang.Long userId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findByDebateItemIdUserId(
        java.lang.Long debateItemId, java.lang.Long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVote findByDebateItemIdUserId_First(
        java.lang.Long debateItemId, java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVote findByDebateItemIdUserId_Last(
        java.lang.Long debateItemId, java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVote[] findByDebateItemIdUserId_PrevAndNext(
        java.lang.Long debateItemVoteId, java.lang.Long debateItemId,
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemVote> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByDebateItemId(java.lang.Long debateItemId)
        throws com.liferay.portal.SystemException;

    public void removeByUserId(java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public void removeByDebateItemIdUserId(java.lang.Long debateItemId,
        java.lang.Long userId) throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByDebateItemId(java.lang.Long debateItemId)
        throws com.liferay.portal.SystemException;

    public int countByUserId(java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public int countByDebateItemIdUserId(java.lang.Long debateItemId,
        java.lang.Long userId) throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
