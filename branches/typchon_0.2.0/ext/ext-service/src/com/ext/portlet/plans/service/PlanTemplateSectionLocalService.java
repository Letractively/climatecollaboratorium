package com.ext.portlet.plans.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="PlanTemplateSectionLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.plans.service.impl.PlanTemplateSectionLocalServiceImpl</code>.
 * Modify methods in that class and rerun ServiceBuilder to populate this class
 * and all other generated classes.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanTemplateSectionLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface PlanTemplateSectionLocalService {
    public com.ext.portlet.plans.model.PlanTemplateSection addPlanTemplateSection(
        com.ext.portlet.plans.model.PlanTemplateSection planTemplateSection)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTemplateSection createPlanTemplateSection(
        com.ext.portlet.plans.service.persistence.PlanTemplateSectionPK planTemplateSectionPK);

    public void deletePlanTemplateSection(
        com.ext.portlet.plans.service.persistence.PlanTemplateSectionPK planTemplateSectionPK)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deletePlanTemplateSection(
        com.ext.portlet.plans.model.PlanTemplateSection planTemplateSection)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.plans.model.PlanTemplateSection getPlanTemplateSection(
        com.ext.portlet.plans.service.persistence.PlanTemplateSectionPK planTemplateSectionPK)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.PlanTemplateSection> getPlanTemplateSections(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getPlanTemplateSectionsCount()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTemplateSection updatePlanTemplateSection(
        com.ext.portlet.plans.model.PlanTemplateSection planTemplateSection)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTemplateSection updatePlanTemplateSection(
        com.ext.portlet.plans.model.PlanTemplateSection planTemplateSection,
        boolean merge) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTemplateSection> findByPlanTemplateId(
        java.lang.Long planTemplateId)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTemplateSection addPlanTemplateSection(
        java.lang.Long planTemplateId, java.lang.Long sectionId, int weight)
        throws com.liferay.portal.SystemException;

    public void removePlanTemplateSection(java.lang.Long planTemplateId,
        java.lang.Long sectionId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;
}