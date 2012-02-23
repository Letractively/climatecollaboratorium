package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanSectionPersistence extends BasePersistence {
    public void cacheResult(com.ext.portlet.plans.model.PlanSection planSection);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanSection> planSections);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanSection create(java.lang.Long id);

    public com.ext.portlet.plans.model.PlanSection remove(java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSection remove(
        com.ext.portlet.plans.model.PlanSection planSection)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlanSection planSection, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlanSection update(
        com.ext.portlet.plans.model.PlanSection planSection)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planSection the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planSection is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.plans.model.PlanSection update(
        com.ext.portlet.plans.model.PlanSection planSection, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSection updateImpl(
        com.ext.portlet.plans.model.PlanSection planSection, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSection findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSection fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSection> findByPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSection> findByPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId,
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSection> findByPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId,
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSection findByPlanIdSectionDefinitionId_First(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSection findByPlanIdSectionDefinitionId_Last(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSection[] findByPlanIdSectionDefinitionId_PrevAndNext(
        java.lang.Long id, java.lang.Long planId,
        java.lang.Long planSectionDefinitionId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSection findByCurrentPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSection fetchByCurrentPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSection fetchByCurrentPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSection> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSection> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSection> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByPlanIdSectionDefinitionId(java.lang.Long planId,
        java.lang.Long planSectionDefinitionId)
        throws com.liferay.portal.SystemException;

    public void removeByCurrentPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByPlanIdSectionDefinitionId(java.lang.Long planId,
        java.lang.Long planSectionDefinitionId)
        throws com.liferay.portal.SystemException;

    public int countByCurrentPlanIdSectionDefinitionId(java.lang.Long planId,
        java.lang.Long planSectionDefinitionId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
