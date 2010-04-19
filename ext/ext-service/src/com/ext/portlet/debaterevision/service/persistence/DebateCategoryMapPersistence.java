package com.ext.portlet.debaterevision.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface DebateCategoryMapPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.debaterevision.model.DebateCategoryMap debateCategoryMap);

    public void cacheResult(
        java.util.List<com.ext.portlet.debaterevision.model.DebateCategoryMap> debateCategoryMaps);

    public void clearCache();

    public com.ext.portlet.debaterevision.model.DebateCategoryMap create(
        java.lang.Long debateCategoryMapPK);

    public com.ext.portlet.debaterevision.model.DebateCategoryMap remove(
        java.lang.Long debateCategoryMapPK)
        throws com.ext.portlet.debaterevision.NoSuchDebateCategoryMapException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateCategoryMap remove(
        com.ext.portlet.debaterevision.model.DebateCategoryMap debateCategoryMap)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(DebateCategoryMap debateCategoryMap, boolean merge)</code>.
     */
    public com.ext.portlet.debaterevision.model.DebateCategoryMap update(
        com.ext.portlet.debaterevision.model.DebateCategoryMap debateCategoryMap)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateCategoryMap the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateCategoryMap is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.debaterevision.model.DebateCategoryMap update(
        com.ext.portlet.debaterevision.model.DebateCategoryMap debateCategoryMap,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateCategoryMap updateImpl(
        com.ext.portlet.debaterevision.model.DebateCategoryMap debateCategoryMap,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateCategoryMap findByPrimaryKey(
        java.lang.Long debateCategoryMapPK)
        throws com.ext.portlet.debaterevision.NoSuchDebateCategoryMapException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateCategoryMap fetchByPrimaryKey(
        java.lang.Long debateCategoryMapPK)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateCategoryMap findBydebateIdCategoryId(
        java.lang.Long debateCategoryPK, java.lang.Long debateId)
        throws com.ext.portlet.debaterevision.NoSuchDebateCategoryMapException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateCategoryMap fetchBydebateIdCategoryId(
        java.lang.Long debateCategoryPK, java.lang.Long debateId)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateCategoryMap fetchBydebateIdCategoryId(
        java.lang.Long debateCategoryPK, java.lang.Long debateId,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateCategoryMap> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateCategoryMap> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateCategoryMap> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeBydebateIdCategoryId(java.lang.Long debateCategoryPK,
        java.lang.Long debateId)
        throws com.ext.portlet.debaterevision.NoSuchDebateCategoryMapException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countBydebateIdCategoryId(java.lang.Long debateCategoryPK,
        java.lang.Long debateId) throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
