package com.ext.portlet.plans.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="PlanSectionLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.plans.service.impl.PlanSectionLocalServiceImpl</code>.
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
 * @see com.ext.portlet.plans.service.PlanSectionLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface PlanSectionLocalService {
    public com.ext.portlet.plans.model.PlanSection addPlanSection(
        com.ext.portlet.plans.model.PlanSection planSection)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSection createPlanSection(
        java.lang.Long id);

    public void deletePlanSection(java.lang.Long id)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deletePlanSection(
        com.ext.portlet.plans.model.PlanSection planSection)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.plans.model.PlanSection getPlanSection(
        java.lang.Long id)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.PlanSection> getPlanSections(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getPlanSectionsCount() throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSection updatePlanSection(
        com.ext.portlet.plans.model.PlanSection planSection)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSection updatePlanSection(
        com.ext.portlet.plans.model.PlanSection planSection, boolean merge)
        throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.plans.model.PlanSection getCurrentForPlanSectionDef(
        com.ext.portlet.plans.model.PlanItem plan,
        com.ext.portlet.plans.model.PlanSectionDefinition def)
        throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.plans.model.PlanSection getCurrentForPlanSectionDef(
        com.ext.portlet.plans.model.PlanItem plan,
        com.ext.portlet.plans.model.PlanSectionDefinition def,
        boolean createOnEmpty) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.plans.model.PlanSection getForPlanSectionDef(
        com.ext.portlet.plans.model.PlanItem plan,
        com.ext.portlet.plans.model.PlanSectionDefinition def)
        throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.plans.model.PlanSection getForPlanSectionDef(
        com.ext.portlet.plans.model.PlanItem plan,
        com.ext.portlet.plans.model.PlanSectionDefinition def, boolean current,
        boolean createOnEmpty) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSection createNewVersionForPlanSectionDefinition(
        com.ext.portlet.plans.model.PlanItem plan,
        com.ext.portlet.plans.model.PlanSectionDefinition def)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSection createNewVersionForPlanSectionDefinition(
        com.ext.portlet.plans.model.PlanItem plan,
        com.ext.portlet.plans.model.PlanSectionDefinition def, boolean store)
        throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.PlanSection> getAllForPlanDefinition(
        com.ext.portlet.plans.model.PlanItem plan,
        com.ext.portlet.plans.model.PlanSectionDefinition def)
        throws com.liferay.portal.SystemException;
}
