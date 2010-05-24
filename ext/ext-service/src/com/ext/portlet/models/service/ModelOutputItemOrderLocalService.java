package com.ext.portlet.models.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="ModelOutputItemOrderLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.models.service.impl.ModelOutputItemOrderLocalServiceImpl</code>.
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
 * @see com.ext.portlet.models.service.ModelOutputItemOrderLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface ModelOutputItemOrderLocalService {
    public com.ext.portlet.models.model.ModelOutputItemOrder addModelOutputItemOrder(
        com.ext.portlet.models.model.ModelOutputItemOrder modelOutputItemOrder)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelOutputItemOrder createModelOutputItemOrder(
        java.lang.Long modelOutputItemModifierPK);

    public void deleteModelOutputItemOrder(
        java.lang.Long modelOutputItemModifierPK)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteModelOutputItemOrder(
        com.ext.portlet.models.model.ModelOutputItemOrder modelOutputItemOrder)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.models.model.ModelOutputItemOrder getModelOutputItemOrder(
        java.lang.Long modelOutputItemModifierPK)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.models.model.ModelOutputItemOrder> getModelOutputItemOrders(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getModelOutputItemOrdersCount()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelOutputItemOrder updateModelOutputItemOrder(
        com.ext.portlet.models.model.ModelOutputItemOrder modelOutputItemOrder)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelOutputItemOrder updateModelOutputItemOrder(
        com.ext.portlet.models.model.ModelOutputItemOrder modelOutputItemOrder,
        boolean merge) throws com.liferay.portal.SystemException;
}
