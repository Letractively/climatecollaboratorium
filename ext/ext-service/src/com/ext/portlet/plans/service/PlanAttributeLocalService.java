package com.ext.portlet.plans.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="PlanAttributeLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.plans.service.impl.PlanAttributeLocalServiceImpl</code>.
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
 * @see com.ext.portlet.plans.service.PlanAttributeLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface PlanAttributeLocalService {
    public com.ext.portlet.plans.model.PlanAttribute addPlanAttribute(
        com.ext.portlet.plans.model.PlanAttribute planAttribute)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttribute createPlanAttribute(
        java.lang.Long attributeId);

    public void deletePlanAttribute(java.lang.Long attributeId)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deletePlanAttribute(
        com.ext.portlet.plans.model.PlanAttribute planAttribute)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.plans.model.PlanAttribute getPlanAttribute(
        java.lang.Long attributeId)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getPlanAttributesCount()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttribute updatePlanAttribute(
        com.ext.portlet.plans.model.PlanAttribute planAttribute)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttribute updatePlanAttribute(
        com.ext.portlet.plans.model.PlanAttribute planAttribute, boolean merge)
        throws com.liferay.portal.SystemException;

    public void addPlanAttribute(long planId, java.lang.String attributeName,
        java.lang.String attributeValue)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttribute findPlanAttribute(
        long planId, java.lang.String attributeName)
        throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes(
        java.lang.Long planId) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributesByNameValue(
        java.lang.String attributeName, java.lang.String attributeValue)
        throws com.liferay.portal.SystemException;
}
