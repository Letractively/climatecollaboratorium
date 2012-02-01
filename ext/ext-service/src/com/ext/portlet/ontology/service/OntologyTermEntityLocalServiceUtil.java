package com.ext.portlet.ontology.service;


/**
 * <a href="OntologyTermEntityLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.ontology.service.OntologyTermEntityLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.service.OntologyTermEntityLocalService
 *
 */
public class OntologyTermEntityLocalServiceUtil {
    private static OntologyTermEntityLocalService _service;

    public static com.ext.portlet.ontology.model.OntologyTermEntity addOntologyTermEntity(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity)
        throws com.liferay.portal.SystemException {
        return getService().addOntologyTermEntity(ontologyTermEntity);
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity createOntologyTermEntity(
        java.lang.Long id) {
        return getService().createOntologyTermEntity(id);
    }

    public static void deleteOntologyTermEntity(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteOntologyTermEntity(id);
    }

    public static void deleteOntologyTermEntity(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity)
        throws com.liferay.portal.SystemException {
        getService().deleteOntologyTermEntity(ontologyTermEntity);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity getOntologyTermEntity(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getOntologyTermEntity(id);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> getOntologyTermEntities(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getOntologyTermEntities(start, end);
    }

    public static int getOntologyTermEntitiesCount()
        throws com.liferay.portal.SystemException {
        return getService().getOntologyTermEntitiesCount();
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity updateOntologyTermEntity(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity)
        throws com.liferay.portal.SystemException {
        return getService().updateOntologyTermEntity(ontologyTermEntity);
    }

    public static com.ext.portlet.ontology.model.OntologyTermEntity updateOntologyTermEntity(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateOntologyTermEntity(ontologyTermEntity, merge);
    }

    public static OntologyTermEntityLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "OntologyTermEntityLocalService is not set");
        }

        return _service;
    }

    public void setService(OntologyTermEntityLocalService service) {
        _service = service;
    }
}
