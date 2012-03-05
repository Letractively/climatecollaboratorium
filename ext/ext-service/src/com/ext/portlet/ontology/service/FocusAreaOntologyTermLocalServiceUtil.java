package com.ext.portlet.ontology.service;


/**
 * <a href="FocusAreaOntologyTermLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.ontology.service.FocusAreaOntologyTermLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.service.FocusAreaOntologyTermLocalService
 *
 */
public class FocusAreaOntologyTermLocalServiceUtil {
    private static FocusAreaOntologyTermLocalService _service;

    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm addFocusAreaOntologyTerm(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm focusAreaOntologyTerm)
        throws com.liferay.portal.SystemException {
        return getService().addFocusAreaOntologyTerm(focusAreaOntologyTerm);
    }

    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm createFocusAreaOntologyTerm(
        com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK) {
        return getService().createFocusAreaOntologyTerm(focusAreaOntologyTermPK);
    }

    public static void deleteFocusAreaOntologyTerm(
        com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteFocusAreaOntologyTerm(focusAreaOntologyTermPK);
    }

    public static void deleteFocusAreaOntologyTerm(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm focusAreaOntologyTerm)
        throws com.liferay.portal.SystemException {
        getService().deleteFocusAreaOntologyTerm(focusAreaOntologyTerm);
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

    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm getFocusAreaOntologyTerm(
        com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getFocusAreaOntologyTerm(focusAreaOntologyTermPK);
    }

    public static java.util.List<com.ext.portlet.ontology.model.FocusAreaOntologyTerm> getFocusAreaOntologyTerms(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getFocusAreaOntologyTerms(start, end);
    }

    public static int getFocusAreaOntologyTermsCount()
        throws com.liferay.portal.SystemException {
        return getService().getFocusAreaOntologyTermsCount();
    }

    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm updateFocusAreaOntologyTerm(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm focusAreaOntologyTerm)
        throws com.liferay.portal.SystemException {
        return getService().updateFocusAreaOntologyTerm(focusAreaOntologyTerm);
    }

    public static com.ext.portlet.ontology.model.FocusAreaOntologyTerm updateFocusAreaOntologyTerm(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm focusAreaOntologyTerm,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateFocusAreaOntologyTerm(focusAreaOntologyTerm, merge);
    }

    public static java.util.List<com.ext.portlet.ontology.model.FocusAreaOntologyTerm> findTermsByFocusArea(
        java.lang.Long focusAreaId) throws com.liferay.portal.SystemException {
        return getService().findTermsByFocusArea(focusAreaId);
    }

    public static void addAreaTerm(java.lang.Long focusAreaId,
        java.lang.Long termId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().addAreaTerm(focusAreaId, termId);
    }

    public static void removeAreaTerm(java.lang.Long focusAreaId,
        java.lang.Long termId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().removeAreaTerm(focusAreaId, termId);
    }

    public static FocusAreaOntologyTermLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "FocusAreaOntologyTermLocalService is not set");
        }

        return _service;
    }

    public void setService(FocusAreaOntologyTermLocalService service) {
        _service = service;
    }
}
