package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanTemplatePersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.plans.model.PlanTemplate planTemplate);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanTemplate> planTemplates);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanTemplate create(java.lang.Long id);

    public com.ext.portlet.plans.model.PlanTemplate remove(java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanTemplateException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTemplate remove(
        com.ext.portlet.plans.model.PlanTemplate planTemplate)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlanTemplate planTemplate, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlanTemplate update(
        com.ext.portlet.plans.model.PlanTemplate planTemplate)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planTemplate the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planTemplate is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.plans.model.PlanTemplate update(
        com.ext.portlet.plans.model.PlanTemplate planTemplate, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTemplate updateImpl(
        com.ext.portlet.plans.model.PlanTemplate planTemplate, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTemplate findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanTemplateException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTemplate fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTemplate> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTemplate> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTemplate> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
