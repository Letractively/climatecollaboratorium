package com.ext.portlet.contests.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface ContestPersistence extends BasePersistence {
    public void cacheResult(com.ext.portlet.contests.model.Contest contest);

    public void cacheResult(
        java.util.List<com.ext.portlet.contests.model.Contest> contests);

    public void clearCache();

    public com.ext.portlet.contests.model.Contest create(
        java.lang.Long ContestPK);

    public com.ext.portlet.contests.model.Contest remove(
        java.lang.Long ContestPK)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.Contest remove(
        com.ext.portlet.contests.model.Contest contest)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(Contest contest, boolean merge)</code>.
     */
    public com.ext.portlet.contests.model.Contest update(
        com.ext.portlet.contests.model.Contest contest)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                contest the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when contest is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.contests.model.Contest update(
        com.ext.portlet.contests.model.Contest contest, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.Contest updateImpl(
        com.ext.portlet.contests.model.Contest contest, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.Contest findByPrimaryKey(
        java.lang.Long ContestPK)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.Contest fetchByPrimaryKey(
        java.lang.Long ContestPK) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.Contest> findByType(
        java.lang.Long PlanTypeId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.Contest> findByType(
        java.lang.Long PlanTypeId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.Contest> findByType(
        java.lang.Long PlanTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.Contest findByType_First(
        java.lang.Long PlanTypeId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.Contest findByType_Last(
        java.lang.Long PlanTypeId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.Contest[] findByType_PrevAndNext(
        java.lang.Long ContestPK, java.lang.Long PlanTypeId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.Contest findBycontestActive(
        java.lang.Boolean contestActive)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.Contest fetchBycontestActive(
        java.lang.Boolean contestActive)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.Contest fetchBycontestActive(
        java.lang.Boolean contestActive, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.Contest> findByActiveFeatured(
        java.lang.Boolean contestActive, java.lang.Boolean featured)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.Contest> findByActiveFeatured(
        java.lang.Boolean contestActive, java.lang.Boolean featured, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.Contest> findByActiveFeatured(
        java.lang.Boolean contestActive, java.lang.Boolean featured, int start,
        int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.Contest findByActiveFeatured_First(
        java.lang.Boolean contestActive, java.lang.Boolean featured,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.Contest findByActiveFeatured_Last(
        java.lang.Boolean contestActive, java.lang.Boolean featured,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.Contest[] findByActiveFeatured_PrevAndNext(
        java.lang.Long ContestPK, java.lang.Boolean contestActive,
        java.lang.Boolean featured,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.Contest> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.Contest> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.Contest> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByType(java.lang.Long PlanTypeId)
        throws com.liferay.portal.SystemException;

    public void removeBycontestActive(java.lang.Boolean contestActive)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.SystemException;

    public void removeByActiveFeatured(java.lang.Boolean contestActive,
        java.lang.Boolean featured) throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByType(java.lang.Long PlanTypeId)
        throws com.liferay.portal.SystemException;

    public int countBycontestActive(java.lang.Boolean contestActive)
        throws com.liferay.portal.SystemException;

    public int countByActiveFeatured(java.lang.Boolean contestActive,
        java.lang.Boolean featured) throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
