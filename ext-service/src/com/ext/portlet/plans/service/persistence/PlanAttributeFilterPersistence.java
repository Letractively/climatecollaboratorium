package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanAttributeFilterPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.plans.model.PlanAttributeFilter planAttributeFilter);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> planAttributeFilters);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanAttributeFilter create(
        java.lang.Long planAttributeFilterId);

    public com.ext.portlet.plans.model.PlanAttributeFilter remove(
        java.lang.Long planAttributeFilterId)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttributeFilter remove(
        com.ext.portlet.plans.model.PlanAttributeFilter planAttributeFilter)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlanAttributeFilter planAttributeFilter, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlanAttributeFilter update(
        com.ext.portlet.plans.model.PlanAttributeFilter planAttributeFilter)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planAttributeFilter the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planAttributeFilter is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.plans.model.PlanAttributeFilter update(
        com.ext.portlet.plans.model.PlanAttributeFilter planAttributeFilter,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttributeFilter updateImpl(
        com.ext.portlet.plans.model.PlanAttributeFilter planAttributeFilter,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttributeFilter findByPrimaryKey(
        java.lang.Long planAttributeFilterId)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttributeFilter fetchByPrimaryKey(
        java.lang.Long planAttributeFilterId)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttributeFilter findByPlanUserSettingsIdAttributeName(
        java.lang.Long planUserSettingsId, java.lang.String attributeName)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttributeFilter fetchByPlanUserSettingsIdAttributeName(
        java.lang.Long planUserSettingsId, java.lang.String attributeName)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttributeFilter fetchByPlanUserSettingsIdAttributeName(
        java.lang.Long planUserSettingsId, java.lang.String attributeName,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByPlanUserSettingsIdAttributeName(
        java.lang.Long planUserSettingsId, java.lang.String attributeName)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByPlanUserSettingsIdAttributeName(
        java.lang.Long planUserSettingsId, java.lang.String attributeName)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
