package com.ext.portlet.plans.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="PlanItemLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.plans.service.impl.PlanItemLocalServiceImpl</code>.
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
 * @see com.ext.portlet.plans.service.PlanItemLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface PlanItemLocalService {
    public com.ext.portlet.plans.model.PlanItem addPlanItem(
        com.ext.portlet.plans.model.PlanItem planItem)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem createPlanItem(
        java.lang.Long id);

    public void deletePlanItem(java.lang.Long id)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deletePlanItem(com.ext.portlet.plans.model.PlanItem planItem)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.plans.model.PlanItem getPlanItem(java.lang.Long id)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlanItems(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getPlanItemsCount() throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem updatePlanItem(
        com.ext.portlet.plans.model.PlanItem planItem)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem updatePlanItem(
        com.ext.portlet.plans.model.PlanItem planItem, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem createPlan(
        com.ext.portlet.contests.model.ContestPhase phase,
        java.lang.Long authorId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem createPlan(
        java.lang.Long planTypeId, java.lang.Long authorId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem createPlan(
        com.ext.portlet.plans.model.PlanItem basePlan, java.lang.Long authorId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem createPlan(
        com.ext.portlet.plans.model.PlanItem basePlan,
        com.ext.portlet.contests.model.ContestPhase contestPhase,
        java.lang.Long authorId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem createPlan(
        com.ext.portlet.plans.model.Plan basePlan)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans()
        throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlansInContestPhase(
        com.ext.portlet.contests.model.ContestPhase contestPhase)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.plans.model.PlanItem getPlan(java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        com.ext.portlet.contests.model.ContestPhase phase, int start, int end,
        java.lang.String sortColumn, java.lang.String sortDirection)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        com.ext.portlet.contests.model.ContestPhase phase, int start, int end,
        java.lang.String sortColumn, java.lang.String sortDirection,
        boolean applyFilters)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        java.util.List<com.ext.portlet.contests.model.ContestPhase> phases,
        int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        java.util.List<com.ext.portlet.contests.model.ContestPhase> phases,
        int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection, boolean applyFilters)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean isNameAvailable(java.lang.String planName,
        com.ext.portlet.contests.model.Contest c)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanItem> applyFilters(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        java.util.List<com.ext.portlet.plans.model.PlanItem> plans)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void removePlanWithEntireHistory(java.lang.Long planId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.PlanItem> getAllVersions(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.plans.model.PlanAttribute getPlanAttribute(
        com.ext.portlet.plans.model.PlanItem plan, java.lang.String name)
        throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public void reIndex() throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public void reIndex(long planId) throws com.liferay.portal.SystemException;
}
