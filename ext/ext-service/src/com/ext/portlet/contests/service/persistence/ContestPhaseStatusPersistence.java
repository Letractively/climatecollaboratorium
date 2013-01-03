package com.ext.portlet.contests.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface ContestPhaseStatusPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.contests.model.ContestPhaseStatus contestPhaseStatus);

    public void cacheResult(
        java.util.List<com.ext.portlet.contests.model.ContestPhaseStatus> contestPhaseStatuses);

    public void clearCache();

    public com.ext.portlet.contests.model.ContestPhaseStatus create(
        java.lang.String name);

    public com.ext.portlet.contests.model.ContestPhaseStatus remove(
        java.lang.String name)
        throws com.ext.portlet.contests.NoSuchContestPhaseStatusException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhaseStatus remove(
        com.ext.portlet.contests.model.ContestPhaseStatus contestPhaseStatus)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(ContestPhaseStatus contestPhaseStatus, boolean merge)</code>.
     */
    public com.ext.portlet.contests.model.ContestPhaseStatus update(
        com.ext.portlet.contests.model.ContestPhaseStatus contestPhaseStatus)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                contestPhaseStatus the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when contestPhaseStatus is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.contests.model.ContestPhaseStatus update(
        com.ext.portlet.contests.model.ContestPhaseStatus contestPhaseStatus,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhaseStatus updateImpl(
        com.ext.portlet.contests.model.ContestPhaseStatus contestPhaseStatus,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhaseStatus findByPrimaryKey(
        java.lang.String name)
        throws com.ext.portlet.contests.NoSuchContestPhaseStatusException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhaseStatus fetchByPrimaryKey(
        java.lang.String name) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhaseStatus> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhaseStatus> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhaseStatus> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
