package com.ext.portlet.models.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="ModelGlobalPreferenceLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.models.service.impl.ModelGlobalPreferenceLocalServiceImpl</code>.
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
 * @see com.ext.portlet.models.service.ModelGlobalPreferenceLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface ModelGlobalPreferenceLocalService {
    public com.ext.portlet.models.model.ModelGlobalPreference addModelGlobalPreference(
        com.ext.portlet.models.model.ModelGlobalPreference modelGlobalPreference)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelGlobalPreference createModelGlobalPreference(
        java.lang.Long modelGlobalPreferencePK);

    public void deleteModelGlobalPreference(
        java.lang.Long modelGlobalPreferencePK)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteModelGlobalPreference(
        com.ext.portlet.models.model.ModelGlobalPreference modelGlobalPreference)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.models.model.ModelGlobalPreference getModelGlobalPreference(
        java.lang.Long modelGlobalPreferencePK)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.models.model.ModelGlobalPreference> getModelGlobalPreferences(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getModelGlobalPreferencesCount()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelGlobalPreference updateModelGlobalPreference(
        com.ext.portlet.models.model.ModelGlobalPreference modelGlobalPreference)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.models.model.ModelGlobalPreference updateModelGlobalPreference(
        com.ext.portlet.models.model.ModelGlobalPreference modelGlobalPreference,
        boolean merge) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean isVisible(mit.simulation.climate.client.Simulation s)
        throws com.liferay.portal.SystemException;

    public void setVisible(mit.simulation.climate.client.Simulation s,
        boolean visible) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getWeight(mit.simulation.climate.client.Simulation s)
        throws com.liferay.portal.SystemException;

    public void setWeight(mit.simulation.climate.client.Simulation s, int weight)
        throws com.liferay.portal.SystemException;
}
