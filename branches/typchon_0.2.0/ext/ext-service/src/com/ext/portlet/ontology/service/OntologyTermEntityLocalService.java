package com.ext.portlet.ontology.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="OntologyTermEntityLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.ontology.service.impl.OntologyTermEntityLocalServiceImpl</code>.
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
 * @see com.ext.portlet.ontology.service.OntologyTermEntityLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface OntologyTermEntityLocalService {
    public com.ext.portlet.ontology.model.OntologyTermEntity addOntologyTermEntity(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTermEntity createOntologyTermEntity(
        java.lang.Long id);

    public void deleteOntologyTermEntity(java.lang.Long id)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteOntologyTermEntity(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.ontology.model.OntologyTermEntity getOntologyTermEntity(
        java.lang.Long id)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> getOntologyTermEntities(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getOntologyTermEntitiesCount()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTermEntity updateOntologyTermEntity(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTermEntity updateOntologyTermEntity(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity,
        boolean merge) throws com.liferay.portal.SystemException;

    public java.util.List<Long> findTagedIdsForClass(java.lang.Long termId,
        java.lang.Class clasz) throws com.liferay.portal.SystemException;
}
