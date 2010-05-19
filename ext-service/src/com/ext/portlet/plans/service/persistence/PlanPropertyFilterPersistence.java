package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanPropertyFilterPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.plans.model.PlanPropertyFilter planPropertyFilter);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanPropertyFilter> planPropertyFilters);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanPropertyFilter create(
        java.lang.Long planPropertyFilterId);

    public com.ext.portlet.plans.model.PlanPropertyFilter remove(
        java.lang.Long planPropertyFilterId)
        throws com.ext.portlet.plans.NoSuchPlanPropertyFilterException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPropertyFilter remove(
        com.ext.portlet.plans.model.PlanPropertyFilter planPropertyFilter)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlanPropertyFilter planPropertyFilter, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlanPropertyFilter update(
        com.ext.portlet.plans.model.PlanPropertyFilter planPropertyFilter)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planPropertyFilter the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planPropertyFilter is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.plans.model.PlanPropertyFilter update(
        com.ext.portlet.plans.model.PlanPropertyFilter planPropertyFilter,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPropertyFilter updateImpl(
        com.ext.portlet.plans.model.PlanPropertyFilter planPropertyFilter,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPropertyFilter findByPrimaryKey(
        java.lang.Long planPropertyFilterId)
        throws com.ext.portlet.plans.NoSuchPlanPropertyFilterException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPropertyFilter fetchByPrimaryKey(
        java.lang.Long planPropertyFilterId)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPropertyFilter findByPlanUserSettingsIdPropertyName(
        java.lang.Long planUserSettingsId, java.lang.String propertyName)
        throws com.ext.portlet.plans.NoSuchPlanPropertyFilterException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPropertyFilter fetchByPlanUserSettingsIdPropertyName(
        java.lang.Long planUserSettingsId, java.lang.String propertyName)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPropertyFilter fetchByPlanUserSettingsIdPropertyName(
        java.lang.Long planUserSettingsId, java.lang.String propertyName,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPropertyFilter> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPropertyFilter> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPropertyFilter> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByPlanUserSettingsIdPropertyName(
        java.lang.Long planUserSettingsId, java.lang.String propertyName)
        throws com.ext.portlet.plans.NoSuchPlanPropertyFilterException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByPlanUserSettingsIdPropertyName(
        java.lang.Long planUserSettingsId, java.lang.String propertyName)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
