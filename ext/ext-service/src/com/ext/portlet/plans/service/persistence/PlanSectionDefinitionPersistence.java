package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanSectionDefinitionPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanSectionDefinition> planSectionDefinitions);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanSectionDefinition create(
        java.lang.Long id);

    public com.ext.portlet.plans.model.PlanSectionDefinition remove(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanSectionDefinitionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSectionDefinition remove(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlanSectionDefinition planSectionDefinition, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlanSectionDefinition update(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planSectionDefinition the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planSectionDefinition is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.plans.model.PlanSectionDefinition update(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSectionDefinition updateImpl(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSectionDefinition findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanSectionDefinitionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSectionDefinition fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSectionDefinition> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSectionDefinition> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSectionDefinition> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
