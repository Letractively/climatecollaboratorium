package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanAttributePersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.plans.model.PlanAttribute planAttribute);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanAttribute> planAttributes);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanAttribute create(
        java.lang.Long attributeId);

    public com.ext.portlet.plans.model.PlanAttribute remove(
        java.lang.Long attributeId)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttribute remove(
        com.ext.portlet.plans.model.PlanAttribute planAttribute)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlanAttribute planAttribute, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlanAttribute update(
        com.ext.portlet.plans.model.PlanAttribute planAttribute)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planAttribute the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planAttribute is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.plans.model.PlanAttribute update(
        com.ext.portlet.plans.model.PlanAttribute planAttribute, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttribute updateImpl(
        com.ext.portlet.plans.model.PlanAttribute planAttribute, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttribute findByPrimaryKey(
        java.lang.Long attributeId)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttribute fetchByPrimaryKey(
        java.lang.Long attributeId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanAttribute> findByplanAttributes(
        java.lang.Long planId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanAttribute> findByplanAttributes(
        java.lang.Long planId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanAttribute> findByplanAttributes(
        java.lang.Long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttribute findByplanAttributes_First(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttribute findByplanAttributes_Last(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttribute[] findByplanAttributes_PrevAndNext(
        java.lang.Long attributeId, java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttribute findByattributeForPlan(
        java.lang.Long planId, java.lang.String attributeName)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttribute fetchByattributeForPlan(
        java.lang.Long planId, java.lang.String attributeName)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttribute fetchByattributeForPlan(
        java.lang.Long planId, java.lang.String attributeName,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanAttribute> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanAttribute> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanAttribute> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByplanAttributes(java.lang.Long planId)
        throws com.liferay.portal.SystemException;

    public void removeByattributeForPlan(java.lang.Long planId,
        java.lang.String attributeName)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByplanAttributes(java.lang.Long planId)
        throws com.liferay.portal.SystemException;

    public int countByattributeForPlan(java.lang.Long planId,
        java.lang.String attributeName)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
