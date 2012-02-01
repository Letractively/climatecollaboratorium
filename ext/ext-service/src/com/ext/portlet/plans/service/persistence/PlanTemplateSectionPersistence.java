package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface PlanTemplateSectionPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.plans.model.PlanTemplateSection planTemplateSection);

    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanTemplateSection> planTemplateSections);

    public void clearCache();

    public com.ext.portlet.plans.model.PlanTemplateSection create(
        PlanTemplateSectionPK planTemplateSectionPK);

    public com.ext.portlet.plans.model.PlanTemplateSection remove(
        PlanTemplateSectionPK planTemplateSectionPK)
        throws com.ext.portlet.plans.NoSuchPlanTemplateSectionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTemplateSection remove(
        com.ext.portlet.plans.model.PlanTemplateSection planTemplateSection)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(PlanTemplateSection planTemplateSection, boolean merge)</code>.
     */
    public com.ext.portlet.plans.model.PlanTemplateSection update(
        com.ext.portlet.plans.model.PlanTemplateSection planTemplateSection)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                planTemplateSection the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when planTemplateSection is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.plans.model.PlanTemplateSection update(
        com.ext.portlet.plans.model.PlanTemplateSection planTemplateSection,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTemplateSection updateImpl(
        com.ext.portlet.plans.model.PlanTemplateSection planTemplateSection,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTemplateSection findByPrimaryKey(
        PlanTemplateSectionPK planTemplateSectionPK)
        throws com.ext.portlet.plans.NoSuchPlanTemplateSectionException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTemplateSection fetchByPrimaryKey(
        PlanTemplateSectionPK planTemplateSectionPK)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTemplateSection> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTemplateSection> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTemplateSection> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
