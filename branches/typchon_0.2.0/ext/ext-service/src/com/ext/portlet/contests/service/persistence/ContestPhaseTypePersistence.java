package com.ext.portlet.contests.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface ContestPhaseTypePersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.contests.model.ContestPhaseType contestPhaseType);

    public void cacheResult(
        java.util.List<com.ext.portlet.contests.model.ContestPhaseType> contestPhaseTypes);

    public void clearCache();

    public com.ext.portlet.contests.model.ContestPhaseType create(
        java.lang.Long id);

    public com.ext.portlet.contests.model.ContestPhaseType remove(
        java.lang.Long id)
        throws com.ext.portlet.contests.NoSuchContestPhaseTypeException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhaseType remove(
        com.ext.portlet.contests.model.ContestPhaseType contestPhaseType)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(ContestPhaseType contestPhaseType, boolean merge)</code>.
     */
    public com.ext.portlet.contests.model.ContestPhaseType update(
        com.ext.portlet.contests.model.ContestPhaseType contestPhaseType)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                contestPhaseType the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when contestPhaseType is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.contests.model.ContestPhaseType update(
        com.ext.portlet.contests.model.ContestPhaseType contestPhaseType,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhaseType updateImpl(
        com.ext.portlet.contests.model.ContestPhaseType contestPhaseType,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhaseType findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.contests.NoSuchContestPhaseTypeException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhaseType fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhaseType> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhaseType> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhaseType> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
