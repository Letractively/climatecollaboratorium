package com.ext.portlet.contests.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface ContestPhaseColumnPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.contests.model.ContestPhaseColumn contestPhaseColumn);

    public void cacheResult(
        java.util.List<com.ext.portlet.contests.model.ContestPhaseColumn> contestPhaseColumns);

    public void clearCache();

    public com.ext.portlet.contests.model.ContestPhaseColumn create(
        java.lang.Long id);

    public com.ext.portlet.contests.model.ContestPhaseColumn remove(
        java.lang.Long id)
        throws com.ext.portlet.contests.NoSuchContestPhaseColumnException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhaseColumn remove(
        com.ext.portlet.contests.model.ContestPhaseColumn contestPhaseColumn)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(ContestPhaseColumn contestPhaseColumn, boolean merge)</code>.
     */
    public com.ext.portlet.contests.model.ContestPhaseColumn update(
        com.ext.portlet.contests.model.ContestPhaseColumn contestPhaseColumn)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                contestPhaseColumn the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when contestPhaseColumn is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.contests.model.ContestPhaseColumn update(
        com.ext.portlet.contests.model.ContestPhaseColumn contestPhaseColumn,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhaseColumn updateImpl(
        com.ext.portlet.contests.model.ContestPhaseColumn contestPhaseColumn,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhaseColumn findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.contests.NoSuchContestPhaseColumnException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhaseColumn fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhaseColumn> findByContestPhasePK(
        java.lang.Long ContestPhasePK)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhaseColumn> findByContestPhasePK(
        java.lang.Long ContestPhasePK, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhaseColumn> findByContestPhasePK(
        java.lang.Long ContestPhasePK, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhaseColumn findByContestPhasePK_First(
        java.lang.Long ContestPhasePK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestPhaseColumnException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhaseColumn findByContestPhasePK_Last(
        java.lang.Long ContestPhasePK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestPhaseColumnException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhaseColumn[] findByContestPhasePK_PrevAndNext(
        java.lang.Long id, java.lang.Long ContestPhasePK,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.contests.NoSuchContestPhaseColumnException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhaseColumn> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhaseColumn> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhaseColumn> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByContestPhasePK(java.lang.Long ContestPhasePK)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByContestPhasePK(java.lang.Long ContestPhasePK)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
