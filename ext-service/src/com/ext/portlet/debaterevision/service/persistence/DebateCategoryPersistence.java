package com.ext.portlet.debaterevision.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface DebateCategoryPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.debaterevision.model.DebateCategory debateCategory);

    public void cacheResult(
        java.util.List<com.ext.portlet.debaterevision.model.DebateCategory> debateCategories);

    public void clearCache();

    public com.ext.portlet.debaterevision.model.DebateCategory create(
        java.lang.Long debateCategoryPK);

    public com.ext.portlet.debaterevision.model.DebateCategory remove(
        java.lang.Long debateCategoryPK)
        throws com.ext.portlet.debaterevision.NoSuchDebateCategoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateCategory remove(
        com.ext.portlet.debaterevision.model.DebateCategory debateCategory)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(DebateCategory debateCategory, boolean merge)</code>.
     */
    public com.ext.portlet.debaterevision.model.DebateCategory update(
        com.ext.portlet.debaterevision.model.DebateCategory debateCategory)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateCategory the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateCategory is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.debaterevision.model.DebateCategory update(
        com.ext.portlet.debaterevision.model.DebateCategory debateCategory,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateCategory updateImpl(
        com.ext.portlet.debaterevision.model.DebateCategory debateCategory,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateCategory findByPrimaryKey(
        java.lang.Long debateCategoryPK)
        throws com.ext.portlet.debaterevision.NoSuchDebateCategoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateCategory fetchByPrimaryKey(
        java.lang.Long debateCategoryPK)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateCategory> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateCategory> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateCategory> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
