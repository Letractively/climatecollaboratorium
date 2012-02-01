package com.ext.portlet.debaterevision.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface DebateItemVoteStatsPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.debaterevision.model.DebateItemVoteStats debateItemVoteStats);

    public void cacheResult(
        java.util.List<com.ext.portlet.debaterevision.model.DebateItemVoteStats> debateItemVoteStatses);

    public void clearCache();

    public com.ext.portlet.debaterevision.model.DebateItemVoteStats create(
        java.lang.Long debateItemVotesStats);

    public com.ext.portlet.debaterevision.model.DebateItemVoteStats remove(
        java.lang.Long debateItemVotesStats)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteStatsException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVoteStats remove(
        com.ext.portlet.debaterevision.model.DebateItemVoteStats debateItemVoteStats)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(DebateItemVoteStats debateItemVoteStats, boolean merge)</code>.
     */
    public com.ext.portlet.debaterevision.model.DebateItemVoteStats update(
        com.ext.portlet.debaterevision.model.DebateItemVoteStats debateItemVoteStats)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateItemVoteStats the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateItemVoteStats is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.debaterevision.model.DebateItemVoteStats update(
        com.ext.portlet.debaterevision.model.DebateItemVoteStats debateItemVoteStats,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVoteStats updateImpl(
        com.ext.portlet.debaterevision.model.DebateItemVoteStats debateItemVoteStats,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVoteStats findByPrimaryKey(
        java.lang.Long debateItemVotesStats)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteStatsException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVoteStats fetchByPrimaryKey(
        java.lang.Long debateItemVotesStats)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVoteStats findByDebateItemId(
        java.lang.Long debateItemId)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteStatsException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVoteStats fetchByDebateItemId(
        java.lang.Long debateItemId) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemVoteStats fetchByDebateItemId(
        java.lang.Long debateItemId, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemVoteStats> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemVoteStats> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemVoteStats> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByDebateItemId(java.lang.Long debateItemId)
        throws com.ext.portlet.debaterevision.NoSuchDebateItemVoteStatsException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByDebateItemId(java.lang.Long debateItemId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
