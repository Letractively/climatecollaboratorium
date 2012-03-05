package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanSectionPlanMapPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.plans.model.PlanSectionPlanMap planSectionPlanMap);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> planSectionPlanMaps);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanSectionPlanMap create(
        com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK);

    public com.ext.portlet.plans.model.PlanSectionPlanMap remove(
        com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSectionPlanMap remove(
        com.ext.portlet.plans.model.PlanSectionPlanMap planSectionPlanMap)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlanSectionPlanMap planSectionPlanMap, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlanSectionPlanMap update(
        com.ext.portlet.plans.model.PlanSectionPlanMap planSectionPlanMap)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planSectionPlanMap the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planSectionPlanMap is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.plans.model.PlanSectionPlanMap update(
        com.ext.portlet.plans.model.PlanSectionPlanMap planSectionPlanMap,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSectionPlanMap updateImpl(
        com.ext.portlet.plans.model.PlanSectionPlanMap planSectionPlanMap,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSectionPlanMap findByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSectionPlanMap fetchByPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findByPlanId(
        java.lang.Long relatedPlanId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findByPlanId(
        java.lang.Long relatedPlanId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findByPlanId(
        java.lang.Long relatedPlanId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSectionPlanMap findByPlanId_First(
        java.lang.Long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSectionPlanMap findByPlanId_Last(
        java.lang.Long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSectionPlanMap[] findByPlanId_PrevAndNext(
        com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK,
        java.lang.Long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findBySectionId(
        java.lang.Long sectionId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findBySectionId(
        java.lang.Long sectionId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findBySectionId(
        java.lang.Long sectionId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSectionPlanMap findBySectionId_First(
        java.lang.Long sectionId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSectionPlanMap findBySectionId_Last(
        java.lang.Long sectionId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSectionPlanMap[] findBySectionId_PrevAndNext(
        com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK,
        java.lang.Long sectionId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByPlanId(java.lang.Long relatedPlanId)
        throws com.liferay.portal.SystemException;

    public void removeBySectionId(java.lang.Long sectionId)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByPlanId(java.lang.Long relatedPlanId)
        throws com.liferay.portal.SystemException;

    public int countBySectionId(java.lang.Long sectionId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
