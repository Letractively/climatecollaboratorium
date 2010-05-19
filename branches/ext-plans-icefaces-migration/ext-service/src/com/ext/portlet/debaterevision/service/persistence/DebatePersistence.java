package com.ext.portlet.debaterevision.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface DebatePersistence extends BasePersistence {
    public void cacheResult(com.ext.portlet.debaterevision.model.Debate debate);

    public void cacheResult(
        java.util.List<com.ext.portlet.debaterevision.model.Debate> debates);

    public void clearCache();

    public com.ext.portlet.debaterevision.model.Debate create(
        java.lang.Long debatePK);

    public com.ext.portlet.debaterevision.model.Debate remove(
        java.lang.Long debatePK)
        throws com.ext.portlet.debaterevision.NoSuchDebateException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.Debate remove(
        com.ext.portlet.debaterevision.model.Debate debate)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(Debate debate, boolean merge)</code>.
     */
    public com.ext.portlet.debaterevision.model.Debate update(
        com.ext.portlet.debaterevision.model.Debate debate)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debate the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debate is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.debaterevision.model.Debate update(
        com.ext.portlet.debaterevision.model.Debate debate, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.Debate updateImpl(
        com.ext.portlet.debaterevision.model.Debate debate, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.Debate findByPrimaryKey(
        java.lang.Long debatePK)
        throws com.ext.portlet.debaterevision.NoSuchDebateException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.Debate fetchByPrimaryKey(
        java.lang.Long debatePK) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.Debate> findBybyIdStatus(
        java.lang.Long debateId, java.lang.String status)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.Debate> findBybyIdStatus(
        java.lang.Long debateId, java.lang.String status, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.Debate> findBybyIdStatus(
        java.lang.Long debateId, java.lang.String status, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.Debate findBybyIdStatus_First(
        java.lang.Long debateId, java.lang.String status,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.Debate findBybyIdStatus_Last(
        java.lang.Long debateId, java.lang.String status,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.Debate[] findBybyIdStatus_PrevAndNext(
        java.lang.Long debatePK, java.lang.Long debateId,
        java.lang.String status,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.Debate> findBybyIdVersion(
        java.lang.Long debateId, java.lang.Long treeVersion)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.Debate> findBybyIdVersion(
        java.lang.Long debateId, java.lang.Long treeVersion, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.Debate> findBybyIdVersion(
        java.lang.Long debateId, java.lang.Long treeVersion, int start,
        int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.Debate findBybyIdVersion_First(
        java.lang.Long debateId, java.lang.Long treeVersion,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.Debate findBybyIdVersion_Last(
        java.lang.Long debateId, java.lang.Long treeVersion,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.Debate[] findBybyIdVersion_PrevAndNext(
        java.lang.Long debatePK, java.lang.Long debateId,
        java.lang.Long treeVersion,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.Debate> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.Debate> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.Debate> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeBybyIdStatus(java.lang.Long debateId,
        java.lang.String status) throws com.liferay.portal.SystemException;

    public void removeBybyIdVersion(java.lang.Long debateId,
        java.lang.Long treeVersion) throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countBybyIdStatus(java.lang.Long debateId,
        java.lang.String status) throws com.liferay.portal.SystemException;

    public int countBybyIdVersion(java.lang.Long debateId,
        java.lang.Long treeVersion) throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
