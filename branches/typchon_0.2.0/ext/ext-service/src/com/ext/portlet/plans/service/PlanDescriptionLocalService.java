package com.ext.portlet.plans.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="PlanDescriptionLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.plans.service.impl.PlanDescriptionLocalServiceImpl</code>.
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
 * @see com.ext.portlet.plans.service.PlanDescriptionLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface PlanDescriptionLocalService {
    public com.ext.portlet.plans.model.PlanDescription addPlanDescription(
        com.ext.portlet.plans.model.PlanDescription planDescription)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanDescription createPlanDescription(
        java.lang.Long id);

    public void deletePlanDescription(java.lang.Long id)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deletePlanDescription(
        com.ext.portlet.plans.model.PlanDescription planDescription)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.plans.model.PlanDescription getPlanDescription(
        java.lang.Long id)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.PlanDescription> getPlanDescriptions(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getPlanDescriptionsCount()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanDescription updatePlanDescription(
        com.ext.portlet.plans.model.PlanDescription planDescription)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanDescription updatePlanDescription(
        com.ext.portlet.plans.model.PlanDescription planDescription,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanDescription createPlanDescription(
        com.ext.portlet.plans.model.PlanItem plan, java.lang.String name)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanDescription createPlanDescription(
        com.ext.portlet.plans.model.PlanItem plan, java.lang.String name,
        boolean store) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.plans.model.PlanDescription getCurrentForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.plans.model.PlanDescription getForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.PlanDescription> getAllForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanDescription createNewVersionForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanDescription createNewVersionForPlan(
        com.ext.portlet.plans.model.PlanItem plan, boolean store)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanDescription createNewVersionForPlanFrom(
        com.ext.portlet.plans.model.PlanItem plan,
        com.ext.portlet.plans.model.PlanDescription from, boolean store)
        throws com.liferay.portal.SystemException;
}
