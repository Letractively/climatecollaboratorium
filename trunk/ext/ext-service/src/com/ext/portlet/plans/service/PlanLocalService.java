package com.ext.portlet.plans.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="PlanLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.plans.service.impl.PlanLocalServiceImpl</code>.
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
 * @see com.ext.portlet.plans.service.PlanLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface PlanLocalService {
    public com.ext.portlet.plans.model.Plan addPlan(
        com.ext.portlet.plans.model.Plan plan)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.Plan createPlan(java.lang.Long planId);

    public void deletePlan(java.lang.Long planId)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deletePlan(com.ext.portlet.plans.model.Plan plan)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.plans.model.Plan getPlan(java.lang.Long planId)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.Plan> getPlans(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getPlansCount() throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.Plan updatePlan(
        com.ext.portlet.plans.model.Plan plan)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.Plan updatePlan(
        com.ext.portlet.plans.model.Plan plan, boolean merge)
        throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.Plan> getPlans(
        long planTypeId, int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.liferay.portal.SystemException;

    public int countPlans(long planTypeId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.Plan> getFilteredPlans(
        com.ext.portlet.plans.model.PlansUserSettings filter, int start,
        int end, java.lang.String sortColumn, java.lang.String sortDirection)
        throws java.lang.Exception;

    public int countFilteredPlans(
        com.ext.portlet.plans.model.PlansUserSettings filter)
        throws java.lang.Exception;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getUserVotePosition(long userId, java.lang.String sortColumn,
        java.lang.String sortOrder)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.ext.portlet.plans.NoSuchPlanTypeException,
            com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getFilteredUserVotePosition(
        com.ext.portlet.plans.model.PlansUserSettings filter, long userId,
        java.lang.String sortColumn, java.lang.String sortOrder)
        throws java.lang.Exception;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.PlanPosition> getPlansPositions(
        java.util.List<com.ext.portlet.plans.model.Plan> plans);
}
