package com.ext.portlet.plans.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="PlanSectionPlanMapLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.plans.service.impl.PlanSectionPlanMapLocalServiceImpl</code>.
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
 * @see com.ext.portlet.plans.service.PlanSectionPlanMapLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface PlanSectionPlanMapLocalService {
    public com.ext.portlet.plans.model.PlanSectionPlanMap addPlanSectionPlanMap(
        com.ext.portlet.plans.model.PlanSectionPlanMap planSectionPlanMap)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSectionPlanMap createPlanSectionPlanMap(
        com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK);

    public void deletePlanSectionPlanMap(
        com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deletePlanSectionPlanMap(
        com.ext.portlet.plans.model.PlanSectionPlanMap planSectionPlanMap)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.plans.model.PlanSectionPlanMap getPlanSectionPlanMap(
        com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> getPlanSectionPlanMaps(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getPlanSectionPlanMapsCount()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSectionPlanMap updatePlanSectionPlanMap(
        com.ext.portlet.plans.model.PlanSectionPlanMap planSectionPlanMap)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanSectionPlanMap updatePlanSectionPlanMap(
        com.ext.portlet.plans.model.PlanSectionPlanMap planSectionPlanMap,
        boolean merge) throws com.liferay.portal.SystemException;

    public java.util.List<Long> findPlanIdsForSection(java.lang.Long sectionId)
        throws com.liferay.portal.SystemException;
}
