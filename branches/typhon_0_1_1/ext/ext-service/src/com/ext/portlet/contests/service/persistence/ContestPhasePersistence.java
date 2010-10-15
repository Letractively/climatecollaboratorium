package com.ext.portlet.contests.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface ContestPhasePersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.contests.model.ContestPhase contestPhase);

    public void cacheResult(
        java.util.List<com.ext.portlet.contests.model.ContestPhase> contestPhases);

    public void clearCache();

    public com.ext.portlet.contests.model.ContestPhase create(
        java.lang.Long ContestPhasePK);

    public com.ext.portlet.contests.model.ContestPhase remove(
        java.lang.Long ContestPhasePK)
        throws com.ext.portlet.contests.NoSuchContestPhaseException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhase remove(
        com.ext.portlet.contests.model.ContestPhase contestPhase)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(ContestPhase contestPhase, boolean merge)</code>.
     */
    public com.ext.portlet.contests.model.ContestPhase update(
        com.ext.portlet.contests.model.ContestPhase contestPhase)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                contestPhase the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when contestPhase is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.contests.model.ContestPhase update(
        com.ext.portlet.contests.model.ContestPhase contestPhase, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhase updateImpl(
        com.ext.portlet.contests.model.ContestPhase contestPhase, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhase findByPrimaryKey(
        java.lang.Long ContestPhasePK)
        throws com.ext.portlet.contests.NoSuchContestPhaseException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhase fetchByPrimaryKey(
        java.lang.Long ContestPhasePK)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhase> findByContestId(
        java.lang.Long ContestPK) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhase> findByContestId(
        java.lang.Long ContestPK, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhase> findByContestId(
        java.lang.Long ContestPK, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhase findByContestId_First(
        java.lang.Long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestPhaseException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhase findByContestId_Last(
        java.lang.Long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestPhaseException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhase[] findByContestId_PrevAndNext(
        java.lang.Long ContestPhasePK, java.lang.Long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestPhaseException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhase findByContestIdActive(
        java.lang.Long ContestPK, java.lang.Boolean phaseActive)
        throws com.ext.portlet.contests.NoSuchContestPhaseException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhase fetchByContestIdActive(
        java.lang.Long ContestPK, java.lang.Boolean phaseActive)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhase fetchByContestIdActive(
        java.lang.Long ContestPK, java.lang.Boolean phaseActive,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhase> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhase> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhase> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByContestId(java.lang.Long ContestPK)
        throws com.liferay.portal.SystemException;

    public void removeByContestIdActive(java.lang.Long ContestPK,
        java.lang.Boolean phaseActive)
        throws com.ext.portlet.contests.NoSuchContestPhaseException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByContestId(java.lang.Long ContestPK)
        throws com.liferay.portal.SystemException;

    public int countByContestIdActive(java.lang.Long ContestPK,
        java.lang.Boolean phaseActive)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
