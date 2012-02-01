package com.ext.portlet.ontology.service;


/**
 * <a href="OntologyTermLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.ontology.service.OntologyTermLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.service.OntologyTermLocalService
 *
 */
public class OntologyTermLocalServiceUtil {
    private static OntologyTermLocalService _service;

    public static com.ext.portlet.ontology.model.OntologyTerm addOntologyTerm(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.SystemException {
        return getService().addOntologyTerm(ontologyTerm);
    }

    public static com.ext.portlet.ontology.model.OntologyTerm createOntologyTerm(
        java.lang.Long id) {
        return getService().createOntologyTerm(id);
    }

    public static void deleteOntologyTerm(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteOntologyTerm(id);
    }

    public static void deleteOntologyTerm(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.SystemException {
        getService().deleteOntologyTerm(ontologyTerm);
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

    public static com.ext.portlet.ontology.model.OntologyTerm getOntologyTerm(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getOntologyTerm(id);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTerm> getOntologyTerms(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getOntologyTerms(start, end);
    }

    public static int getOntologyTermsCount()
        throws com.liferay.portal.SystemException {
        return getService().getOntologyTermsCount();
    }

    public static com.ext.portlet.ontology.model.OntologyTerm updateOntologyTerm(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.SystemException {
        return getService().updateOntologyTerm(ontologyTerm);
    }

    public static com.ext.portlet.ontology.model.OntologyTerm updateOntologyTerm(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updateOntologyTerm(ontologyTerm, merge);
    }

    public static java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findByParentId(
        java.lang.Long parentId) throws com.liferay.portal.SystemException {
        return getService().findByParentId(parentId);
    }

    public static com.ext.portlet.ontology.model.OntologyTerm createTerm(
        java.lang.Long parentId, java.lang.String name)
        throws com.liferay.portal.SystemException {
        return getService().createTerm(parentId, name);
    }

    public static int countChildTerms(java.lang.Long parentId)
        throws com.liferay.portal.SystemException {
        return getService().countChildTerms(parentId);
    }

    public static OntologyTermLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("OntologyTermLocalService is not set");
        }

        return _service;
    }

    public void setService(OntologyTermLocalService service) {
        _service = service;
    }
}
