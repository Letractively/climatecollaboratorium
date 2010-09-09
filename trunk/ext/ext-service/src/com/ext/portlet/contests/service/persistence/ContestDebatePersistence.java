package com.ext.portlet.contests.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface ContestDebatePersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.contests.model.ContestDebate contestDebate);

    public void cacheResult(
        java.util.List<com.ext.portlet.contests.model.ContestDebate> contestDebates);

    public void clearCache();

    public com.ext.portlet.contests.model.ContestDebate create(
        java.lang.Long id);

    public com.ext.portlet.contests.model.ContestDebate remove(
        java.lang.Long id)
        throws com.ext.portlet.contests.NoSuchContestDebateException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestDebate remove(
        com.ext.portlet.contests.model.ContestDebate contestDebate)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(ContestDebate contestDebate, boolean merge)</code>.
     */
    public com.ext.portlet.contests.model.ContestDebate update(
        com.ext.portlet.contests.model.ContestDebate contestDebate)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                contestDebate the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when contestDebate is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.contests.model.ContestDebate update(
        com.ext.portlet.contests.model.ContestDebate contestDebate,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestDebate updateImpl(
        com.ext.portlet.contests.model.ContestDebate contestDebate,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestDebate findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.contests.NoSuchContestDebateException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestDebate fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestDebate> findByContestPK(
        java.lang.Long ContestPK) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestDebate> findByContestPK(
        java.lang.Long ContestPK, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestDebate> findByContestPK(
        java.lang.Long ContestPK, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestDebate findByContestPK_First(
        java.lang.Long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestDebateException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestDebate findByContestPK_Last(
        java.lang.Long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestDebateException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestDebate[] findByContestPK_PrevAndNext(
        java.lang.Long id, java.lang.Long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestDebateException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestDebate> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestDebate> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestDebate> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByContestPK(java.lang.Long ContestPK)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByContestPK(java.lang.Long ContestPK)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
