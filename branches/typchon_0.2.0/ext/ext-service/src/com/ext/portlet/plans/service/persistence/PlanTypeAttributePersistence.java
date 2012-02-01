package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanTypeAttributePersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.plans.model.PlanTypeAttribute planTypeAttribute);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> planTypeAttributes);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanTypeAttribute create(
        java.lang.Long planTypeAttributeId);

    public com.ext.portlet.plans.model.PlanTypeAttribute remove(
        java.lang.Long planTypeAttributeId)
        throws com.ext.portlet.plans.NoSuchPlanTypeAttributeException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTypeAttribute remove(
        com.ext.portlet.plans.model.PlanTypeAttribute planTypeAttribute)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlanTypeAttribute planTypeAttribute, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlanTypeAttribute update(
        com.ext.portlet.plans.model.PlanTypeAttribute planTypeAttribute)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planTypeAttribute the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planTypeAttribute is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.plans.model.PlanTypeAttribute update(
        com.ext.portlet.plans.model.PlanTypeAttribute planTypeAttribute,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTypeAttribute updateImpl(
        com.ext.portlet.plans.model.PlanTypeAttribute planTypeAttribute,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTypeAttribute findByPrimaryKey(
        java.lang.Long planTypeAttributeId)
        throws com.ext.portlet.plans.NoSuchPlanTypeAttributeException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTypeAttribute fetchByPrimaryKey(
        java.lang.Long planTypeAttributeId)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTypeAttribute findByPlanTypeIdAttributeName(
        java.lang.Long planTypeId, java.lang.String attributeName)
        throws com.ext.portlet.plans.NoSuchPlanTypeAttributeException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTypeAttribute fetchByPlanTypeIdAttributeName(
        java.lang.Long planTypeId, java.lang.String attributeName)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTypeAttribute fetchByPlanTypeIdAttributeName(
        java.lang.Long planTypeId, java.lang.String attributeName,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByPlanTypeIdAttributeName(java.lang.Long planTypeId,
        java.lang.String attributeName)
        throws com.ext.portlet.plans.NoSuchPlanTypeAttributeException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByPlanTypeIdAttributeName(java.lang.Long planTypeId,
        java.lang.String attributeName)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
