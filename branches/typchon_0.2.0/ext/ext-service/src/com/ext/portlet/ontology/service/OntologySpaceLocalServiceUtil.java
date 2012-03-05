package com.ext.portlet.ontology.service;


/**
 * <a href="OntologySpaceLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.ontology.service.OntologySpaceLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.service.OntologySpaceLocalService
 *
 */
public class OntologySpaceLocalServiceUtil {
    private static OntologySpaceLocalService _service;

    public static com.ext.portlet.ontology.model.OntologySpace addOntologySpace(
        com.ext.portlet.ontology.model.OntologySpace ontologySpace)
        throws com.liferay.portal.SystemException {
        return getService().addOntologySpace(ontologySpace);
    }

    public static com.ext.portlet.ontology.model.OntologySpace createOntologySpace(
        java.lang.Long id) {
        return getService().createOntologySpace(id);
    }

    public static void deleteOntologySpace(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteOntologySpace(id);
    }

    public static void deleteOntologySpace(
        com.ext.portlet.ontology.model.OntologySpace ontologySpace)
        throws com.liferay.portal.SystemException {
        getService().deleteOntologySpace(ontologySpace);
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

    public static com.ext.portlet.ontology.model.OntologySpace getOntologySpace(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getOntologySpace(id);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologySpace> getOntologySpaces(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getOntologySpaces(start, end);
    }

    public static int getOntologySpacesCount()
        throws com.liferay.portal.SystemException {
        return getService().getOntologySpacesCount();
    }

    public static com.ext.portlet.ontology.model.OntologySpace updateOntologySpace(
        com.ext.portlet.ontology.model.OntologySpace ontologySpace)
        throws com.liferay.portal.SystemException {
        return getService().updateOntologySpace(ontologySpace);
    }

    public static com.ext.portlet.ontology.model.OntologySpace updateOntologySpace(
        com.ext.portlet.ontology.model.OntologySpace ontologySpace,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateOntologySpace(ontologySpace, merge);
    }

    public static com.ext.portlet.ontology.model.OntologySpace createSpace(
        java.lang.String name, java.lang.String description)
        throws com.liferay.portal.SystemException {
        return getService().createSpace(name, description);
    }

    public static OntologySpaceLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("OntologySpaceLocalService is not set");
        }

        return _service;
    }

    public void setService(OntologySpaceLocalService service) {
        _service = service;
    }
}
